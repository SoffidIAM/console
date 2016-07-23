package com.soffid.iam.tomcat.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.Tenant;
import com.soffid.iam.service.AccountService;
import com.soffid.iam.service.AuthorizationService;
import com.soffid.iam.service.PasswordService;
import com.soffid.iam.service.TenantService;
import com.soffid.iam.service.UserService;
import com.soffid.iam.tomcat.LoginService;
import com.soffid.iam.tomcat.SoffidPrincipal;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;

@Stateless(name = "LoginService")
@Local({ LoginService.class })
public class LoginServiceImpl implements LoginService {
	Log log = LogFactory.getLog(getClass());

	public LoginServiceImpl() {
	}

	public SoffidPrincipal authenticate(String username, String credentials) {
		try {
			String account;
			Tenant tenant;
			log.info(username + " logging in");
			ServiceLocator locator = ServiceLocator.instance();

			int i = username.indexOf('\\');
			if (i < 0) {
				tenant = locator.getTenantService().getMasterTenant();
				account = username;
			} else {
				tenant = locator.getTenantService().getTenant(
						username.substring(0, i));
				if (tenant == null) {
					log.info(username + " login rejected. Tenant unknown");
					return null;
				}
				account = username.substring(i + 1);
			}

			if (!tenant.isEnabled()) {
				log.info(username + " login rejected. Tenant is disabled");
				return null;
			}

			Security.nestedLogin(tenant.getName(), "$$INTERNAL$$", new String[] {
					Security.AUTO_USER_QUERY + Security.AUTO_ALL,
					Security.AUTO_ACCOUNT_QUERY + Security.AUTO_ALL });
			try {
				// Valida usuario/dni contra BBDD
				TenantService ts = ServiceLocator.instance().getTenantService();
				UserService us = ServiceLocator.instance().getUserService();
				AccountService as = ServiceLocator.instance().getAccountService();
				PasswordService ps = ServiceLocator.instance().getPasswordService();

				String dispatcher = ps.getDefaultDispatcher();
				Account acc = as.findAccount(account, dispatcher);
				if (acc == null) {
					log.info(username + " login rejected. Unknown account");
					return null;
				}

				SoffidPrincipal principal;

				String passwordDomain = ps.getDefaultDispatcher();
				if (ps.checkPassword(account, passwordDomain, new Password(
						credentials), true, false)) {
					List<String> roles = getRoles(acc);
					roles.add("PASSWORD:VALID");
					principal = new SoffidPrincipal(tenant.getName()+ "\\" + account, 
							credentials,
							roles);
				} else if (ps.checkPassword(account, passwordDomain, new Password(
						credentials), false, true)) {
					List<String> roles = getRoles(acc);
					roles.add("PASSWORD:EXPIRED");
					principal = new SoffidPrincipal(tenant.getName()+ "\\" + account, 
							credentials,
							roles);
				} else {
					log.info(username + " login rejected. Invalid password");
					return null;
				}
				
				principal.setAccountId(acc.getId());
				principal.setFullName(acc.getDescription());
				principal.setTenant(tenant.getName());
				return principal;
			} finally {
				Security.nestedLogoff();
			}
		} catch (InternalErrorException e) {
			throw new SecurityException ("Error during login process", e);
		}
	}

	private List<String> getRoles(Account acc) throws InternalErrorException {
		AuthorizationService us = ServiceLocator.instance()
				.getAuthorizationService();

		String[] rolesArray = us.getUserAuthorizationsString(acc.getName(),
				new HashMap<String, String>());
		return new LinkedList<String>(Arrays.asList(rolesArray));
	}
}
