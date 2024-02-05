package com.soffid.iam.web.vault;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.LaunchType;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.System;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserAccount;
import com.soffid.iam.api.VaultFolder;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.security.SoffidPrincipalImpl;
import com.soffid.iam.service.AdditionalDataService;
import com.soffid.iam.service.PasswordManagerService;
import com.soffid.iam.service.ejb.AccountService;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InternalErrorException;

@WebServlet(urlPatterns = {"/anonymous/vault/passwordManager",
		"/vault/passwordManager"})
public class PasswordManagerServlet extends HttpServlet {
	Log log = LogFactory.getLog(getClass());
	PasswordManagerService svc = ServiceLocator.instance().getPasswordManagerService();
	HashMap<String, SoffidPrincipal> principals = new HashMap<>();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String token = req.getParameter("token");
		String newToken = null;
		JSONObject r = new JSONObject();
		String user = null;
		try {
			if (token != null)
				user = svc.findUserByToken(token);
			if (Security.getCurrentUser() != null) {
				if (! Security.isUserInRole("sso:passwordManager")) {
					user = null;
					r.put("success",  false);					
				}
				if (user == null || !user.equals(Security.getCurrentUser())) {
					user = Security.getCurrentUser();
					if (token != null || req.getParameter("wantToken") != null)
						newToken = svc.generateToken(user);
				}
			}
			if (user == null) {
				r.put("success",  false);
			}
			else 
			{
				SoffidPrincipal principal = registerPrincipal(user);
				if (principal == null) {
					r.put("success",  false);
				} else {
					r.put("success", true);
					if (newToken == null) {
						newToken = svc.renewToken(token);
						if (newToken != null && newToken.equals(token))
							newToken = null;
					}
					Security.nestedLogin(principal);
					try {
						if ("list".equals(action)) {
							r.put("data",  doList(req));
						}
						else if ("get-password".equals(action)) {
							r.put("data",  getPassword(req));
						}
						else if ("set-password".equals(action)) {
							r.put("data",  setPassword(req, r));
						} else {
							r.put("success", false);
						}
					} finally {
						Security.nestedLogoff();
					}
				}
			}
		} catch (Exception e) {
			log.warn("Error fetching user "+user+" accounts", e);
			r.put("success", false);
		}
		if (newToken != null)
			r.put("newToken", newToken);
		resp.setContentType("application/json");
		ServletOutputStream out = resp.getOutputStream();
		out.write(r.toString().getBytes(StandardCharsets.UTF_8));
		out.close();
	}

	private SoffidPrincipal registerPrincipal(String user) throws InternalErrorException, NamingException, CreateException {
		final String tag = Security.getCurrentTenantName()+"\\"+user;
		SoffidPrincipal principal = principals.get(tag);
		if (principal != null) 
			return principal;
		
		Security.nestedLogin(Security.ALL_PERMISSIONS);
		try {
			User u = EJBLocator.getUserService().findUserByUserName(user);
			if (u == null || !u.getActive())
				return null;
			System d = EJBLocator.getDispatcherService().findSoffidDispatcher();
			for (UserAccount account: EJBLocator.getAccountService()
					.findUsersAccounts(user, d.getName())) {
				if (!account.isDisabled()) {
					SoffidPrincipalImpl p = new SoffidPrincipalImpl(
							Security.getCurrentTenantName()+"\\"+account.getName(), 
							u.getId());
					principals.put(tag, p);
					return p;
				}
			}
		} finally {
			Security.nestedLogoff();
		}
		return null;
	}

	private JSONArray doList(HttpServletRequest req) throws InternalErrorException, NamingException, CreateException {
		JSONArray a = new JSONArray();
		final String currentUser = Security.getCurrentUser();
		if (currentUser != null) {
			Security.nestedLogin(currentUser, Security.ALL_PERMISSIONS);
			try {
				List<Account> accounts = EJBLocator.getAccountService().findSharedAccountsByUser(currentUser);
				for (Account account: accounts) {
					if (!account.isDisabled() && account.getLoginUrl() != null) {
						JSONObject o = new JSONObject();
						o.put("type", account.getLaunchType() == null ? null: account.getLaunchType().toString());
						o.put("name", account.getLoginName());
						o.put("description", account.getDescription());
						o.put("url", account.getLoginUrl());
						o.put("id", account.getId());
						a.put(o);
					}
				}
			} finally {
				Security.nestedLogoff();
			}
		}
		return a;
	}

	private JSONObject getPassword(HttpServletRequest req) throws InternalErrorException, NamingException, CreateException {
		JSONObject a = new JSONObject();
		if (Security.getCurrentUser() != null) {
			Account acc;
			acc = ServiceLocator.instance().getAccountService().findAccountById(Long.parseLong(req.getParameter("accountId")));
			if (acc != null && acc.getLaunchType() != LaunchType.LAUNCH_TYPE_PAM && acc.getLaunchType() != LaunchType.LAUNCH_TYPE_WEBSSO) {
				if (acc.getAccessLevel() == AccountAccessLevelEnum.ACCESS_MANAGER ||
						acc.getAccessLevel() == AccountAccessLevelEnum.ACCESS_OWNER ||
						acc.getAccessLevel() == AccountAccessLevelEnum.ACCESS_USER)
				{
					Password pass = EJBLocator.getSelfService().queryAccountPasswordBypassPolicy(acc);
					a.put("password", pass.getPassword());
					a.put("loginName", acc.getLoginName());
					JSONObject attributes = new JSONObject();
					a.put("attributes", attributes);
					for ( Entry<String, Object> entry: acc.getAttributes().entrySet()) {
						final String key = entry.getKey();
						if (key.startsWith("SSO:") && 
								!key.equals("SSO:Server") &&
								!key.equals("SSO:URL")) {
							String value = (String) entry.getValue();
							int i = value.indexOf("=");
							if (i >= 0) {
								attributes.put(URLDecoder.decode(value.substring(0,i), StandardCharsets.UTF_8), 
										URLDecoder.decode(value.substring(i+1), StandardCharsets.UTF_8));
							}
						}
					}
				}
			}
		}
		return a;
	}

	private JSONObject setPassword(HttpServletRequest req, JSONObject r) throws InternalErrorException, NamingException, CreateException, AccountAlreadyExistsException, BadPasswordException {
		JSONObject a = new JSONObject();
		if (Security.getCurrentUser() != null) {
			final String accountId = req.getParameter("accountId");
			if (accountId == null || accountId.trim().isEmpty()) {
				if (Security.isUserInRole("sso:manageAccounts")) {
					createNewAccount(req, a, r);
				} else {
					failNoPermission(r);
				}
			} else {
				updateAccount(req, a, r);
			}
		} else {
			failNoPermission(r);
		}
		return a;
	}

	private void createNewAccount(HttpServletRequest req, JSONObject a, JSONObject r) throws InternalErrorException, AccountAlreadyExistsException, NamingException, CreateException, BadPasswordException {
		String system = ConfigurationCache.getProperty("AutoSSOSystem");
		if (system == null) {
			log.info("Property AutoSSOSystem is not defined");
			r.put("success", false);
			return;
		}
		long i = findLastAccount (system) + 1;

		Account acc = new Account();
		acc.setName(""+i);
		String description = "";
		acc.setAttributes(new HashMap<>());
		String loginUrl = req.getParameter("url");
		if (loginUrl.length() > 128 && loginUrl.contains("?"))
			loginUrl = loginUrl.substring(0, loginUrl.indexOf("?"));
		if (loginUrl.length() > 128) {
			try {
				URI uri = new URI(loginUrl);
				loginUrl = uri.getScheme()+"://"+uri.getHost();
				if (uri.getPort() > 0)
					loginUrl = loginUrl + ":"+uri.getPort();
			} catch (URISyntaxException e) {
			}
		}
		if (loginUrl.length() > 128)
			loginUrl = loginUrl.substring(0, 128);
		acc.setLoginUrl(loginUrl);
		try {
			acc.setServerName(new URI(acc.getLoginUrl()).getHost());
		} catch (Exception e) {
		}
		String user = Security.getCurrentUser();
		// Search for personal folder
		VaultFolder vf = EJBLocator.getVaultService().getPersonalFolder();
			
		Security.nestedLogin(Security.ALL_PERMISSIONS);
		try {
			int num = 1;
			for (Entry<String, String[]> param: req.getParameterMap().entrySet()) {
				String name = param.getKey();
				if (name.startsWith("SSO:") && param.getValue().length == 1) {
					String value = param.getValue()[0];
					description += value+" ";
	           		if ( value.length() < 1024)
	           		{
	    				if (acc.getLoginName() == null || acc.getLoginName().equals(acc.getName())) {
	    					acc.setLoginName(value);
	    				}
	    				String attributeName = "SSO:"+num;
	    				acc.getAttributes().put(attributeName, value);
	    				num++;
           				// Attribute not found
           				AdditionalDataService metadataService = ServiceLocator.instance().getAdditionalDataService();
           				DataType md = metadataService.findSystemDataType(system, attributeName);
           				if (md == null)
           				{
           					md = new DataType();
           					md.setAdminVisibility(AttributeVisibilityEnum.EDITABLE);
           					md.setUserVisibility(AttributeVisibilityEnum.EDITABLE);
           					md.setOperatorVisibility(AttributeVisibilityEnum.EDITABLE);
           					md.setCode(attributeName);
           					md.setValidationExpression("false");
           					md.setLabel("Form data");
      						md.setType(TypeEnumeration.SSO_FORM_TYPE);
           					md.setSize(1024);
           					md.setOrder(null);
           					md.setSystemName(system);
           					md.setRequired(false);
           					md = metadataService.create(md);
           				}
	           		}
				}
			}
			description += loginUrl;
			if (description.length() > 255)
				description = description.substring(0, 255);
			acc.setDescription(description);
			acc.setSystem(system);
			acc.setOwnerUsers(new LinkedList<String>());
			acc.getOwnerUsers().add(user);
			String ssoPolicy = ConfigurationCache.getProperty("AutoSSOPolicy"); //$NON-NLS-1$
			if (ssoPolicy == null) {
				log.info("Property AutoSSOPoliy is not defined");
				r.put("success", false);
				return;
			}
			acc.setType(AccountType.IGNORED);
			acc.setPasswordPolicy(ssoPolicy);
			
			if (vf != null)
			{
				acc.setVaultFolder(vf.getName());
				acc.setVaultFolderId(vf.getId());
			}
			acc = EJBLocator.getAccountService().createAccount2(acc);
			if (req.getParameter("password") != null) {
				EJBLocator.getAccountService().setAccountPassword(acc, new Password(req.getParameter("password")));
			}
			a.put("success", true);
			a.put("accountId", acc.getId());
		} finally {
			Security.nestedLogoff();
		}
	}

	private void updateAccount(HttpServletRequest req, JSONObject a, JSONObject r) throws InternalErrorException, AccountAlreadyExistsException, NamingException, CreateException, BadPasswordException {
		Account acc = EJBLocator.getSelfService().getAccountById(Long.parseLong(req.getParameter("accountId")));
		if (acc == null) {
			failNoPermission(r);
			return;
		}
		if (acc.getAccessLevel() != AccountAccessLevelEnum.ACCESS_MANAGER &&
				acc.getAccessLevel() != AccountAccessLevelEnum.ACCESS_OWNER) {
			failNoPermission(r);
			return;
		}
		int num = 1;
		for (Entry<String, String[]> param: req.getParameterMap().entrySet()) {
			String name = param.getKey();
			if (name.startsWith("SSO:") && param.getValue().length == 1) {
				String value = param.getValue()[0];
           		if ( value.length() < 1024)
           		{
           			acc.setLoginName(value);
    				String attributeName = "SSO:"+num;
    				acc.getAttributes().put(attributeName, value);
    				num++;
    				Security.nestedLogin(Security.ALL_PERMISSIONS);
    				try {
	       				AdditionalDataService metadataService = ServiceLocator.instance().getAdditionalDataService();
	       				DataType md = metadataService.findSystemDataType(acc.getSystem(), attributeName);
	       				if (md == null)
	       				{
	       					// Attribute not found
           					md = new DataType();
           					md.setAdminVisibility(AttributeVisibilityEnum.EDITABLE);
           					md.setUserVisibility(AttributeVisibilityEnum.EDITABLE);
           					md.setOperatorVisibility(AttributeVisibilityEnum.EDITABLE);
           					md.setCode(attributeName);
           					md.setValidationExpression("false");
           					md.setLabel("Form data");
      						md.setType(TypeEnumeration.SSO_FORM_TYPE);
           					md.setSize(1024);
           					md.setOrder(null);
           					md.setSystemName(acc.getSystem());
           					md.setRequired(false);
           					md = metadataService.create(md);
           				}
    				} finally {
    					Security.nestedLogoff();
	           		}
				}
			}
		}
		acc = EJBLocator.getAccountService().updateAccount2(acc);
		if (req.getParameter("password") != null) {
			EJBLocator.getAccountService().setAccountPassword(acc, new Password(req.getParameter("password")));
		}
	}

	private long findLastAccount (String system) throws InternalErrorException, NamingException, CreateException
	{
		long bits = 0;
		long top = 0;
		long attempt = 1;
		Security.nestedLogin(Security.ALL_PERMISSIONS);
		try {
			AccountService accountSvc = EJBLocator.getAccountService();
			/**
			 * Find radix the first account with number = 2 ^ radix
			 */
			do
			{
				Account acc = accountSvc .findAccount(""+attempt, system);
				if (acc == null) break;
				top = attempt;
				attempt = attempt + attempt;
				bits ++ ;
			} while (true);
			/**
			 * Now look for the other bits
			 * top exists
			 * attempt does not exist
			 */
			long step = top;
			while (bits > 1)
			{
				step = step / 2;
				attempt = top + step;
				Account acc = accountSvc.findAccount(""+attempt, system);
				if (acc != null) top = attempt;
				bits --;
			}
			return top;
		} finally {
			Security.nestedLogoff();
		}
	}

	protected void failNoPermission(JSONObject a) {
		log.info("User "+Security.getCurrentAccount()+" trying to register account without permissions");
		a.put("success", false);
	}
	
}
