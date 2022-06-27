package com.soffid.iam.web.vault;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.sys.ExecutionCtrl;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AccessTree;
import com.soffid.iam.api.AccessTreeAuthorization;
import com.soffid.iam.api.AccessTreeExecution;
import com.soffid.iam.api.AccessTreeExecutionType;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.LaunchType;
import com.soffid.iam.api.NewPamSession;
import com.soffid.iam.api.Password;
import com.soffid.iam.service.ejb.SelfService;
import com.soffid.iam.utils.Security;
import com.soffid.iam.utils.TipusAutoritzacioPuntEntrada;
import com.soffid.iam.web.launcher.ApplicationLauncher;
import com.soffid.iam.web.popup.AccountSelectorWindow;
import com.soffid.iam.web.popup.PasswordSelectorWindow;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;

public class LaunchHelper {
	Log log = LogFactory.getLog(getClass());
	
	public void launchAccount( Account account, boolean directLink ) throws InternalErrorException, NamingException, CreateException, UnsupportedEncodingException
	{
		String url = account.getLoginUrl();
		
		if ( url == null || url.trim().isEmpty())
			url = (String) account.getAttributes().get("SSO:URL");
		if (url != null)
		{
			if ( account.getLaunchType() == LaunchType.LAUNCH_TYPE_PAM)
				launchPamAccount(account, url, directLink);
			else if (account.getLaunchType() == LaunchType.LAUNCH_TYPE_WEBSSO)
				launchWssoAccount(account, url, directLink);
			else
				launchStandardAccount(account, url, directLink);
		}
	}

	private void launchPamAccount(Account account, String url, boolean directLink) throws InternalErrorException, NamingException, CreateException, UnsupportedEncodingException {
		NewPamSession s = EJBLocator.getPamSessionService().createJumpServerSession(account);
		if (s == null)
			throw new InternalErrorException("Unable to start session");
		else
		{
			URL u = s.getUrl();
			StringBuffer sb = new StringBuffer();
			String targetUrl = u.getProtocol()+"://"+u.getHost()+ ( u.getPort() > 0 ? ":"+u.getPort(): "" ) + u.getPath();
			
			sb.append("var f=document.getElementById(\"pamLauncherForm\");");
			sb.append("f.action = \""+encodeJS(targetUrl)+"\";");
			if (directLink)
				sb.append("f.target = \"\";");
			for (String part: (u.getQuery() != null && !u.getQuery().trim().isEmpty()? u.getQuery().split("&"): new String[0]))
			{
				int i = part.indexOf("=");
				String tag = i > 0? part.substring(0, i): part;
				String value = i >0? part.substring(i+1): "";
				sb.append("f.elements.namedItem(\""+encodeJS( URLDecoder.decode( tag, "UTF-8") )+"\").value=\""+
						URLDecoder.decode(encodeJS(value), "UTF-8")+"\";");
			}
			sb.append("f.submit();");
			Clients.evalJavaScript(sb.toString());
		}
	}

	private void launchWssoAccount(Account account, String url, boolean directLink) throws InternalErrorException, NamingException, CreateException {
		String name = account.getLoginName() == null ? account.getName() : account.getLoginName();
		com.soffid.iam.api.Password password = com.soffid.iam.EJBLocator.getSelfService().queryAccountPasswordBypassPolicy(account);
		org.json.JSONObject j = new org.json.JSONObject();
		for (String att: account.getAttributes().keySet())
		{
			if (att.startsWith("SSO:") &&
					!att.equals("SSO:URL") && 
					!att.equals("SSO:Server"))
			{
				try {
					String value = (String) account.getAttributes().get(att);
					String[] values = value.split("=");
					if (values.length == 2)
					{
						j.put( java.net.URLDecoder.decode(values[0], "UTF-8"), 
								java.net.URLDecoder.decode(values[1], "UTF-8"));
					}
				} catch (Exception e) {}
			}
		}
		try {
			j.put("url", url);
			j.put("account", name);
			j.put("password", password.getPassword());
			Clients.evalJavaScript("launchSsoUrl("+j.toString()+","+directLink+");");
		} catch (Exception e) {
			if (directLink) 
				Clients.evalJavaScript("window.location.href='"+encodeJS(url)+"';");
			else
				Clients.evalJavaScript("window.open('"+encodeJS(url)+"', '_blank');");
		}
	}

	private void launchStandardAccount(Account account, String url, boolean directLink) {
		if (directLink) 
			Clients.evalJavaScript("window.location.href='"+encodeJS(url)+"';");
		else
			Clients.evalJavaScript("window.open('"+encodeJS(url)+"', '_blank');");
	}

	public String encodeJS(String url) {
		return url.replaceAll("\\\\","\\\\\\\\").replaceAll("'", "\\'");
	}
	
	public void launchAccessTree(AccessTree item, AccessTreeExecution exec, boolean directLink) throws Exception {
		String system = item.getSystem();
		String type = exec.getExecutionTypeCode();
		List<com.soffid.iam.api.Account> accounts = fetchAccounts(item);
		Class executor = findExecutionType( exec );
		if (executor != null)
		{
			ApplicationLauncher l = (ApplicationLauncher) executor.newInstance();
			l.open(item,
					exec,
					accounts, directLink);
		}
		else if ( exec.getExecutionTypeCode().equals("PAM") )
		{
			openPamEntryPoint (item, exec, accounts, directLink);
		}
		else if ( exec.getExecutionTypeCode().equals("WSSO") )
		{
			openWssoEntryPoint(item, exec, accounts, directLink);
		}
		else if (directLink)
			Clients.evalJavaScript("window.location.href='"+ Executions.getCurrent().getContextPath()+ "/execucions?id="+item.getId()+"';");
		else
			Clients.evalJavaScript("window.open('"+ Executions.getCurrent().getContextPath()+ "/execucions?id="+item.getId()+"', '_blank');");

	}
	
	public List<com.soffid.iam.api.Account> fetchAccounts(AccessTree instance) throws InternalErrorException, NamingException, CreateException {
		String user = Security.getCurrentUser();
		String system = instance.getSystem();
		List<com.soffid.iam.api.Account> accounts = new LinkedList<com.soffid.iam.api.Account>();
		// Get accounts list
		Security.nestedLogin(Security.getCurrentAccount(), Security.ALL_PERMISSIONS);
		try {
			com.soffid.iam.service.ejb.AccountService accountService = EJBLocator.getAccountService();
			if (system != null &&  !system.trim().isEmpty())
			{
				accounts.addAll(accountService.findUsersAccounts(user, system));
				for (com.soffid.iam.api.Account acc: accountService.findSharedAccountsByUser(user))
				{
					if (acc.getSystem().equals(system))
						accounts.add(acc);
				}
			}
			
			for ( AccessTreeAuthorization auth: instance.getAuthorizations())
			{
				if (auth.getAuthorizationEntityType().equals(TipusAutoritzacioPuntEntrada.ACCOUNT) &&
						auth.getAuthorizationLevelDescription().equals(TipusAutoritzacioPuntEntrada.NIVELL_QUERY_DESCRIPCIO))
				{
					accounts.add(accountService.findAccountById(auth.getAuthorizationEntityId()));
				}
			}
			
		} finally {
			Security.nestedLogoff();
		}
		return accounts;
	}

	private void openPamEntryPoint(final AccessTree instance, final AccessTreeExecution exe, List<com.soffid.iam.api.Account> accounts, boolean directLink) throws NamingException, CreateException, InternalErrorException, UnsupportedEncodingException {
		Long name = instance.getId();
		// Get passwords
		final List<Account> r = new LinkedList<Account>();
		SelfService sss = EJBLocator.getSelfService();
		com.soffid.iam.service.ejb.AccountService accountService = EJBLocator.getAccountService();
		String url = instance.getExecutions().iterator().next().getContent();
		for (com.soffid.iam.api.Account account: accounts)
		{
			if (accountService.isAccountPasswordAvailable(account.getId()))
				r.add(account);
		}
		if (r.size() == 0)
		{
			throw new UiException("Sorry. There is no account available to open this service");
		}
		else if (r.size() == 1)
		{
			openPamEntryPoint (exe, r.get(0), directLink);
		} else {
			Page page = ((ExecutionCtrl) Executions.getCurrent()).getCurrentPage();
			AccountSelectorWindow accountSelectorWindow = (AccountSelectorWindow) page.getFellowIfAny("accountSelectorWindow");
			if (accountSelectorWindow == null) {
				accountSelectorWindow = (AccountSelectorWindow) Executions.getCurrent().createComponents("/popup/select-account.zul", new HashMap()) [0];
			}
			DataTable lb = (DataTable) accountSelectorWindow.getDataTable();
			Collections.sort(r, new Comparator<Account>() {
				public int compare(Account o1, Account o2) {
					return o1.getLoginName().compareTo(o2.getLoginName());
				}
				
			});
			JSONArray array = new JSONArray();
			for (Account rr: r) {
				JSONObject row = new JSONObject();
				row.put("name", rr.getLoginName());
				row.put("description", rr.getDescription());
				array.put(row);
			}
			lb.setData(array);

			final AccountSelectorWindow w = accountSelectorWindow;
			accountSelectorWindow.setListener((event)->{
				int i = w.getSelectedAccount();
				if (i >= 0 && i < r.size()) {
					openPamEntryPoint(exe, r.get(i), directLink);
				}
			});
			
			w.doHighlighted();
		}
	}
	
	private void openPamEntryPoint(AccessTreeExecution exe, com.soffid.iam.api.Account account, boolean directLink) throws UnsupportedEncodingException, InternalErrorException, NamingException, CreateException {
		NewPamSession s;
		if (exe == null || exe.getContent() == null || exe.getContent().trim().isEmpty()) {
			s= EJBLocator.getPamSessionService()
					.createJumpServerSession(account);
		} else {
			s= EJBLocator.getPamSessionService()
					.createJumpServerSession(account, exe.getContent());
		}
		if (s == null)
			throw new UiException("Unable to start session");
		else
		{
			URL u = s.getUrl();
			StringBuffer sb = new StringBuffer();
			String targetUrl = u.getProtocol()+"://"+u.getHost()+ ( u.getPort() > 0 ? ":"+u.getPort(): "" ) + u.getPath();
			
			sb.append("var f=document.getElementById(\"pamLauncherForm\");");
			if (directLink)
				sb.append("f.target = '';");
			sb.append("f.action = \""+encodeJS(targetUrl)+"\";");
			for (String part: (u.getQuery() != null && !u.getQuery().trim().isEmpty()? u.getQuery().split("&"): new String[0]))
			{
				int i = part.indexOf("=");
				String tag = i > 0? part.substring(0, i): part;
				String value = i >0? part.substring(i+1): "";
				sb.append("f.elements.namedItem(\""+encodeJS( URLDecoder.decode( tag, "UTF-8") )+"\").value=\""+
						URLDecoder.decode(encodeJS(value), "UTF-8")+"\";");
			}
			sb.append("f.submit();");
			Clients.evalJavaScript(sb.toString());
		}
	}

	
	public void openWssoEntryPoint(AccessTree instance, final AccessTreeExecution exe, List<com.soffid.iam.api.Account> accounts, boolean directLink)
			throws InternalErrorException, NamingException, CreateException {
		// Get passwords
		List<Account> r = new LinkedList<Account>();
		com.soffid.iam.service.ejb.AccountService accountService = EJBLocator.getAccountService();
		for (com.soffid.iam.api.Account account: accounts)
		{
			if ( account.getType() == AccountType.USER ||
				accountService.isAccountPasswordAvailable(account.getId()))
				r.add(account);
		}
		if (r.size() == 0)
		{
			throw new UiException("Sorry. There is no account available to open this service");
		}
		else if (r.size() == 1)
		{
			openWssoEntryPoint(exe, r.get(0), directLink);
		} else {
			Page page = ((ExecutionCtrl) Executions.getCurrent()).getCurrentPage();
			AccountSelectorWindow accountSelectorWindow = (AccountSelectorWindow) page.getFellowIfAny("accountSelectorWindow");
			if (accountSelectorWindow == null) {
				accountSelectorWindow = (AccountSelectorWindow) Executions.getCurrent().createComponents("/popup/select-account.zul", new HashMap()) [0];
			}
			DataTable lb = (DataTable) accountSelectorWindow.getDataTable();
			Collections.sort(r, new Comparator<Account>() {
				public int compare(Account o1, Account o2) {
					return o1.getLoginName().compareTo(o2.getLoginName());
				}
				
			});
			JSONArray array = new JSONArray();
			for (Account rr: r) {
				JSONObject row = new JSONObject();
				row.put("name", rr.getLoginName());
				row.put("description", rr.getDescription());
				array.put(row);
			}
			lb.setData(array);

			final AccountSelectorWindow w = accountSelectorWindow;
			accountSelectorWindow.setListener((event)->{
				int i = w.getSelectedAccount();
				if (i >= 0 && i < r.size()) {
					openWssoEntryPoint(exe, r.get(i), directLink);
				}
			});
			
			w.doHighlighted();
		}
	}

	private void openWssoEntryPoint(AccessTreeExecution exe, Account account, boolean directLink) throws InternalErrorException, NamingException, CreateException {
		try {
			com.soffid.iam.api.Password password = EJBLocator.getSelfService().queryAccountPasswordBypassPolicy(account);
			if (password == null) {
				if ( account.getType() == AccountType.USER) {
					wssoAskForAccountPassword(exe, account, directLink);
				} else {
					throw new InternalErrorException(
							String.format("Unable to fetch password for account %s at %s",
									account.getName(),
									account.getSystem()));
				}
			} else {
				JSONObject j = new JSONObject();
				j.put("url", exe.getContent());
				j.put("account", account.getLoginName());
				j.put("password", password.getPassword());
				Clients.evalJavaScript("launchSsoUrl("+j.toString()+","+directLink+");");
			}
		} catch (Exception e) {
			if (directLink) 
				Clients.evalJavaScript("window.location.href='"+encodeJS(exe.getContent())+"';");
			else
				Clients.evalJavaScript("window.open('"+encodeJS(exe.getContent())+"', '_blank');");
		}
	}

	private void wssoAskForAccountPassword(AccessTreeExecution exe, final Account account, boolean directLink) {
		Page page = ((ExecutionCtrl) Executions.getCurrent()).getCurrentPage();
		PasswordSelectorWindow passwordSelectorWindow = (PasswordSelectorWindow) page.getFellowIfAny("passwordSelectorWindow");
		if (passwordSelectorWindow == null) {
			passwordSelectorWindow = (PasswordSelectorWindow) Executions.getCurrent().createComponents("/popup/select-password.zul", new HashMap()) [0];
		}

		final PasswordSelectorWindow w = passwordSelectorWindow;
		passwordSelectorWindow.setListener((event)->{
			Password pass = w.getPassword();
			EJBLocator.getSelfService().setAccountPassword(account, pass);
			openWssoEntryPoint(exe, account, directLink);
		});
		
		w.doHighlighted();
	}

	private Class findExecutionType(AccessTreeExecution exe) throws InternalErrorException, NamingException, CreateException {
		for (AccessTreeExecutionType et: EJBLocator.getEntryPointService().getAllMimeTypeExecution())
		{
			if (et.getCode().equals(exe.getExecutionTypeCode()) && et.getJavaClass() != null)
			{
				Class c;
				ClassLoader cl = Thread.currentThread().getContextClassLoader(); 
				try {
					Thread.currentThread().setContextClassLoader( getClass().getClassLoader() );
					c = getClass().getClassLoader().loadClass(et.getJavaClass());
					if (c != null && ApplicationLauncher.class.isAssignableFrom(c))
					{
						return c;
					}
				} catch (ClassNotFoundException e) {
					log.warn("Error loading class "+et.getJavaClass(), e);
				} finally {
					Thread.currentThread().setContextClassLoader(cl);
				}
			}
		}
		return null;
	}
}
