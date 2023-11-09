package com.soffid.iam.tomcat.service;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.catalina.Realm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.openejb.loader.SystemInstance;
import org.apache.openejb.spi.Assembler;
import org.apache.tomee.catalina.TomcatSecurityService;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.Host;
import com.soffid.iam.api.Issue;
import com.soffid.iam.api.IssueHost;
import com.soffid.iam.api.IssueStatus;
import com.soffid.iam.api.IssueUser;
import com.soffid.iam.api.OUType;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.Tenant;
import com.soffid.iam.api.User;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.security.SoffidPrincipalImpl;
import com.soffid.iam.service.AccountService;
import com.soffid.iam.service.ApplicationBootService;
import com.soffid.iam.service.AuthorizationService;
import com.soffid.iam.service.IssueService;
import com.soffid.iam.service.PasswordService;
import com.soffid.iam.service.PreferencesService;
import com.soffid.iam.service.SamlService;
import com.soffid.iam.service.TenantService;
import com.soffid.iam.service.UserService;
import com.soffid.iam.sync.service.ServerService;
import com.soffid.iam.tomcat.LoginService;
import com.soffid.iam.tomcat.SoffidRealm;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.UnknownUserException;

//@Stateless(name = "com.soffid.iam.tomcat.LoginService")
//@Local({ com.soffid.iam.tomcat.LoginService.class })
public class LoginServiceImpl implements LoginService {
	Log log = LogFactory.getLog(getClass());

	static Set<String> tenants = new HashSet<String>();
	
	public LoginServiceImpl() {
		
	}

	public SoffidPrincipal authenticate(String username, String credentials) {
		String masterMessage = null;
		try {
			boolean samlAuthorized = false;
			if (username == null || username.trim().isEmpty())
				return null;
			
			String account;
			String holderGroup = null;
			Tenant tenant;
			ServiceLocator locator = ServiceLocator.instance();

			SamlService saml = locator.getSamlService();
			String samlPrincipal = saml.checkAuthenticationToken(new String[] {username, credentials});
			if (samlPrincipal != null)
			{
				samlAuthorized = true;
				username = samlPrincipal;
			}
			if (credentials.length() > 2000)
			{
				log.info(username + " login rejected. Password too long (more than 2000 characters)");
				return null;
			}
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
			
			
			Object state = enterWebapp();
			try {
				Security.nestedLogin(tenant.getName(), "$$INTERNAL$$", new String[] {
						Security.AUTO_APPLICATION_QUERY + Security.AUTO_ALL,
						Security.AUTO_GROUP_QUERY + Security.AUTO_ALL,
						Security.AUTO_USER_QUERY + Security.AUTO_ALL,
						Security.AUTO_ACCOUNT_QUERY + Security.AUTO_ALL });
				try {
					// Valida usuario/dni contra BBDD
					TenantService ts = ServiceLocator.instance().getTenantService();
					UserService us = ServiceLocator.instance().getUserService();
					AccountService as = ServiceLocator.instance().getAccountService();
					PasswordService ps = ServiceLocator.instance().getPasswordService();
					PreferencesService prefsSvc = ServiceLocator.instance().getPreferencesService();
					
					if ( ! tenants.contains(tenant.getName()))
					{
			            Map beans = com.soffid.iam.ServiceLocator.instance().getContext().
			            		getBeansOfType(ApplicationBootService.class);
	
			            for ( Object service: beans.keySet())
			            {
			            	log.info ("Executing startup bean: " + service);
			            	
			            	((ApplicationBootService) beans.get(service)).tenantBoot(tenant);
			            }
			            tenants.add(tenant.getName());
					}

					String dispatcher = ps.getDefaultDispatcher();
					Account acc = null;
					try {
						acc = as.findAccount(account, dispatcher);
					} catch (IllegalArgumentException e) {
						log.info(masterMessage = "Login rejected: username and/or credentials are empty");
						return null;
					}
					if (acc == null) {
						log.info(masterMessage = username + " login rejected. Unknown account");
						wrongUser (username);
						return null;
					}
					if (acc.isDisabled()) {
						wrongUser (username);
						log.info(masterMessage = username + " login rejected. Disabled account");
						return null;
					}
	
					SoffidPrincipal principal;
					String passwordDomain = ps.getDefaultDispatcher();
					List<Long> groupIds = new LinkedList<Long>();
					List<String> groups = getUserGroups (acc, null, groupIds);
					List<Long> roleIds = new LinkedList<Long>();
					List<String> soffidRoles = getUserRoles(acc, null, roleIds);
					List<String> roles = getRoles(acc, null);
					Map<String, SoffidPrincipal> holder =  new HashMap<String, SoffidPrincipal>();
					
					String userName = acc.getType().equals( AccountType.USER) ? acc.getOwnerUsers().iterator().next() : null;
					String fullName = acc.getDescription();
					List<Long> accountIds = new LinkedList<Long>();
					User userData = null;
					if (userName != null) {
						userData = us.findUserByUserName(userName);
						if (userData != null) {
							fullName = userData.getFullName();
							accountIds = getAccounts(acc, userData);
						}
					}
					
					if (samlAuthorized ||
							ps.checkPassword(account, passwordDomain, new Password(
							credentials), true, false)) {
						roles.add("PASSWORD:VALID");
						holderGroup = getHolderGroups(acc, userData, holder, "PASSWORD:VALID");
						principal = new SoffidPrincipalImpl(tenant.getName()+ "\\" + account,
								userName, fullName, holderGroup,
								roles, groups, soffidRoles,
								holder, 
								roleIds, accountIds,
								groupIds,
								userData == null? null: userData.getId());
						log.info(masterMessage = principal.getName() + " login accepted");

						if (userName != null) {
							prefsSvc.setUserPreference(userName,"last_login", "" + System.currentTimeMillis());
							auditLoginAction(userName, "S");
						}
						acc.setLastLogin(Calendar.getInstance());
						as.updateAccount(acc);
					} else if (ps.checkPassword(account, passwordDomain, new Password(
							credentials), false, true)) {
						roles.add("PASSWORD:EXPIRED");
						holderGroup = getHolderGroups(acc, userData, holder, "PASSWORD:EXPIRED");
						principal = new SoffidPrincipalImpl(tenant.getName()+ "\\" + account, 
								userName, fullName, holderGroup,
								roles,
								groups, soffidRoles,
								holder,
								roleIds, accountIds,
								groupIds,
								userData == null ? null: userData.getId());
						log.info(masterMessage = principal.getName() + " login accepted with expired password");
						acc.setLastLogin(Calendar.getInstance());
						as.updateAccount(acc);
						auditLoginAction(userName, "S");
					} else {
						auditLoginAction(userName, "P");
						log.info(masterMessage = username + " login rejected. Invalid password");
						return null;
					}
					
					
					if ("true".equals(ConfigurationCache.getProperty("soffid.auth.maintenance")) ||
						"true".equals(System.getProperty("soffid.auth.maintenance"))) {
						if (!principal.hasRole("authorization:all") && 
							Arrays.binarySearch(principal.getSoffidRoles(), "SOFFID_ADMIN") < 0) {
							log.info(masterMessage = username + " login rejected. System in maintenance mode");
							return null;
						}
					}
					
					return principal;
				} finally {
					Security.nestedLogoff();
				}
			} finally {
				exitWebapp(state);
			}
		} catch (AccountAlreadyExistsException e) {
			throw new SecurityException ("Error during login process", e);
		} catch (UnknownUserException e) {
			throw new SecurityException ("Error during login process", e);
		} catch (InternalErrorException e) {
			throw new SecurityException ("Error during login process", e);
		} finally {
			if (masterMessage != null)
				log.info(masterMessage);
		}
	}

	
    private List<Long> getAccounts(Account acc, User userData) throws InternalErrorException {
    	return new LinkedList<Long>(ServiceLocator.instance().getAccountService().getUserGrantedAccountIds(userData));
	}

	private String getHolderGroups(Account acc, User user, Map<String, SoffidPrincipal> r, String passwordRole) throws InternalErrorException, UnknownUserException {
    	if (acc.getType() != AccountType.USER || user == null)
    		return null;
    	
    	if (! "true".equals(ConfigurationCache.getProperty("soffid.selfservice.groupHolderFilter")))
    		return null;
    	
    	String holderGroup = null;
    	
    	if (isHolderGroup(user.getPrimaryGroup())) {
    		holderGroup = user.getPrimaryGroup();
			List<Long> groupIds = new LinkedList<Long>();
			List<String> groups = getUserGroups (acc, user.getPrimaryGroup(), groupIds);
			List<Long> accountIds = getAccounts(acc, user);
			List<Long> roleids = new LinkedList<Long>();
			List<String> soffidRoles = getUserRoles(acc, user.getPrimaryGroup(), roleids );
			List<String> roles = getRoles(acc, user.getPrimaryGroup());
			roles.add(passwordRole);
			String userName = user.getUserName();
			String fullName = user.getFullName();

			SoffidPrincipalImpl principal = new SoffidPrincipalImpl(Security.getCurrentTenantName() + "\\" + acc.getName(),
						userName, fullName, user.getPrimaryGroup(),
						roles, groups, soffidRoles,
						roleids, accountIds,
						groupIds, user == null? null: user.getId());

			r.put(user.getPrimaryGroup(), principal); 
    	}
    	
		for (GroupUser ug: ServiceLocator.instance().getGroupService().findUsersGroupByUserName(user.getUserName())) {
    		if (!r.containsKey(ug.getGroup()) && isHolderGroup(ug.getGroup())) {
    			if (holderGroup == null) holderGroup = ug.getGroup();
    			List<Long> groupIds = new LinkedList<Long>();
    			List<String> groups = getUserGroups (acc, ug.getGroup(), groupIds);
    			List<Long> accountIds = getAccounts(acc, user);
    			List<Long> roleids = new LinkedList<Long>();
    			List<String> soffidRoles = getUserRoles(acc, ug.getGroup(), roleids);
    			List<String> roles = getRoles(acc, ug.getGroup());
    			roles.add(passwordRole);
    			String userName = user.getUserName();
    			String fullName = user.getFullName();

    			SoffidPrincipalImpl principal = new SoffidPrincipalImpl(Security.getCurrentTenantName() + "\\" + acc.getName(),
    						userName, fullName, ug.getGroup(),
    						roles, groups, soffidRoles, roleids, accountIds,
    						groupIds, user == null ? null: user.getId());

    			r.put(ug.getGroup(), principal); 
    			
    		}
    	}
		
		return holderGroup;
	}

	private boolean isHolderGroup(String groupName) throws InternalErrorException {
		Group g = ServiceLocator.instance().getGroupService().findGroupByGroupName(groupName);
		if (g == null || g.getType() == null)
			return false;
		OUType gc = ServiceLocator.instance().getOrganizationalUnitTypeService().findOUTypeByName(g.getType());
		return gc != null && gc.isRoleHolder();
	}

	private List<String> getUserGroups(Account acc, String holderGroup, List<Long> groupIds) throws InternalErrorException, UnknownUserException {
    	List<String> result = new LinkedList<String>();
    	if (acc.getType().equals(AccountType.USER))
    	{
    		User u = ServiceLocator.instance().getUserService().findUserByUserName( acc.getOwnerUsers().iterator().next() );
    		Collection<Group> groups;
			if (holderGroup == null)
    			groups = ServiceLocator.instance().getUserService().getUserGroupsHierarchy(u.getId());
    		else
    			groups = ServiceLocator.instance().getUserService().getUserGroupsHierarchy(u.getId(), holderGroup );
			for ( Group g: groups) {
				if (groupIds != null) 
					groupIds.add(g.getId());
				result.add(g.getName());
			}
    	}
    	return result;
	}


    private List<String> getUserRoles(Account acc, String holderGroup, List<Long> roleIds) throws InternalErrorException {
    	List<String> result = new LinkedList<String>();
    	Collection<RoleGrant> groups;
    	if (acc.getType().equals(AccountType.USER) && acc.getOwnerUsers().size() == 1)
    	{
    		User u = ServiceLocator.instance().getUserService().findUserByUserName( acc.getOwnerUsers().iterator().next() );
    		result.add(u.getUserName());
			if (holderGroup == null)
    			groups = ServiceLocator.instance().getApplicationService().findEffectiveRoleGrantByUser(u.getId());
    		else {
    			Group group = ServiceLocator.instance().getGroupService().findGroupByGroupName(holderGroup);
    			if (group == null)
        			groups = ServiceLocator.instance().getApplicationService().findEffectiveRoleGrantByUser(u.getId());
    			else
    				groups = ServiceLocator.instance().getApplicationService().findEffectiveRoleGrantByUserAndHolderGroup(u.getId(), group.getId());
    		}
    	} else {
			groups = ServiceLocator.instance().getApplicationService().findEffectiveRoleGrantByAccount(acc.getId());
    	}
    	
    	com.soffid.iam.api.System soffidSystem = ServiceLocator.instance().getDispatcherService().findSoffidDispatcher();
    	for ( RoleGrant grant: groups)
    	{
    		if (soffidSystem.getName().equals( grant.getSystem() ) && acc.getName().equals(grant.getOwnerAccountName()))
    			result.add(grant.getRoleName());
    		result.add(grant.getRoleName()+"@"+grant.getSystem());
    		if (roleIds != null)
    			roleIds.add(grant.getRoleId());
    		if (grant.getDomainValue() != null)
    		{
    			if (grant.getSystem().equals(soffidSystem.getName()))
    				result.add(grant.getRoleName()+"/"+grant.getDomainValue());
    			result.add(grant.getRoleName()+"/"+grant.getDomainValue()+"@"+grant.getSystem());
    		}
    	}
    	return result;
	}

    Realm dummyRealm = new SoffidRealm();
    Principal dummyPrincipal = new SoffidPrincipalImpl("master\\$$ANONYMUOS", null, "Anonymous", null, 
    		new LinkedList<String>(), null, null, null, null,
    		null, null);

    private Object enterWebapp ()
	{
    	SystemInstance si = SystemInstance.get();
    	if (si == null)
    		return null;
    	Assembler a = si.getComponent(Assembler.class);
    	if (a == null)
    		return null;
        final TomcatSecurityService ss = (TomcatSecurityService) a.getSecurityService();
        if (ss == null)
        	return null;
        
        return ss.enterWebApp(dummyRealm, dummyPrincipal, null);
	}
	
    private void exitWebapp (Object state)
	{
    	if (state == null)
    		return;
    	SystemInstance si = SystemInstance.get();
    	if (si == null)
    		return ;
    	Assembler a = si.getComponent(Assembler.class);
    	if (a == null)
    		return;
        final TomcatSecurityService ss = (TomcatSecurityService) a.getSecurityService();
        if (ss == null)
        	return;
        
        ss.exitWebApp(state);
	}
	
	private List<String> getRoles(Account acc, String holderGroup) throws InternalErrorException {
		AuthorizationService us = ServiceLocator.instance()
				.getAuthorizationService();

		if (holderGroup == null)
		{
			String[] rolesArray = us.getUserAuthorizationsString(acc.getName(),
					new HashMap<String, String>());
			return new LinkedList<String>(Arrays.asList(rolesArray));
		} else {
			String[] rolesArray = us.getUserGroupAuthorizationString(acc.getName(), holderGroup);
			return new LinkedList<String>(Arrays.asList(rolesArray));
		}
	}
	
    private void auditLoginAction(String user, String action) throws InternalErrorException {

    	Audit auditoria = new Audit();
        auditoria.setAction(action); //$NON-NLS-1$
        auditoria.setUser(user);
        auditoria.setAuthor(null);
        auditoria.setCalendar(Calendar.getInstance());
        auditoria.setObject("LOGIN"); //$NON-NLS-1$
        
        ServiceLocator.instance().getAuditService().create(auditoria);
    }

	public static void wrongUser(String u) throws InternalErrorException {
		IssueService server = ServiceLocator.instance().getIssueService();
		Issue i = new Issue();
		i.setCreated(new Date());
		i.setStatus(IssueStatus.NEW);
		i.setType("login-not-recognized");
		IssueUser iu = new IssueUser();
		iu.setUserName(u);
		i.setUsers(Arrays.asList(iu));
		i.setHash(u);
//		IssueHost ih = new IssueHost();
//		Host h;
//		ih.setHostIp(ip);
//		i.setHosts(Arrays.asList(ih));

		server.createInternalIssue(i);
	}
}
