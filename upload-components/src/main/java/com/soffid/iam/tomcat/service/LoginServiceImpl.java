package com.soffid.iam.tomcat.service;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.catalina.Realm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.openejb.loader.SystemInstance;
import org.apache.openejb.spi.Assembler;
import org.apache.openejb.spi.SecurityService;
import org.apache.tomee.catalina.TomcatSecurityService;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.Tenant;
import com.soffid.iam.api.User;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.security.SoffidPrincipalImpl;
import com.soffid.iam.service.AccountService;
import com.soffid.iam.service.ApplicationBootService;
import com.soffid.iam.service.AuthorizationService;
import com.soffid.iam.service.PasswordService;
import com.soffid.iam.service.SamlService;
import com.soffid.iam.service.TenantService;
import com.soffid.iam.service.UserService;
import com.soffid.iam.tomcat.LoginService;
import com.soffid.iam.tomcat.SoffidRealm;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.UnknownUserException;

@Stateless(name = "LoginService")
@Local({ LoginService.class })
public class LoginServiceImpl implements LoginService {
	Log log = LogFactory.getLog(getClass());

	static Set<String> tenants = new HashSet<String>();
	
	public LoginServiceImpl() {
	}

	public SoffidPrincipal authenticate(String username, String credentials) {
		try {
			boolean samlAuthorized = false;
		
			if (username == null || username.trim().isEmpty())
				return null;
			
			String account;
			String holderGroup = null;
			Tenant tenant;
			log.info(username + " logging in");
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
						Security.AUTO_USER_QUERY + Security.AUTO_ALL,
						Security.AUTO_ACCOUNT_QUERY + Security.AUTO_ALL });
				try {
					// Valida usuario/dni contra BBDD
					TenantService ts = ServiceLocator.instance().getTenantService();
					UserService us = ServiceLocator.instance().getUserService();
					AccountService as = ServiceLocator.instance().getAccountService();
					PasswordService ps = ServiceLocator.instance().getPasswordService();
	
					String dispatcher = ps.getDefaultDispatcher();
					Account acc = null;
					try {
						acc = as.findAccount(account, dispatcher);
					} catch (IllegalArgumentException e) {
						log.info("Login rejected: username and/or credentials are empty");
						return null;
					}
					if (acc == null) 
					{
						int idx = account.indexOf("\\");
						if (idx > 0)
						{
							holderGroup = account.substring(0, idx);
							account = account.substring(idx+1);
							try {
								acc = as.findAccount(account, dispatcher);
							} catch (IllegalArgumentException e) {
								log.info("Login rejected: username and/or credentials are empty");
								return null;
							}
						}
					}
					if (acc == null) {
						log.info(username + " login rejected. Unknown account");
						return null;
					}
					if (acc.isDisabled()) {
						log.info(username + " login rejected. Disabled account");
						return null;
					}
	
					SoffidPrincipal principal;
					String passwordDomain = ps.getDefaultDispatcher();
					List<String> groups = getUserGroups (acc, holderGroup);
					List<String> soffidRoles = getUserRoles(acc, holderGroup);
					List<String> roles = getRoles(acc, holderGroup);
					
					String userName = acc.getType().equals( AccountType.USER) ? acc.getOwnerUsers().iterator().next().getUserName() : null;
					String fullName = acc.getType().equals( AccountType.USER) ? acc.getOwnerUsers().iterator().next().getFullName() : acc.getDescription();
					
					if (samlAuthorized ||
							ps.checkPassword(account, passwordDomain, new Password(
							credentials), true, false)) {
						roles.add("PASSWORD:VALID");
						principal = new SoffidPrincipalImpl(tenant.getName()+ "\\" + account,
								userName, fullName, holderGroup,
								roles, groups, soffidRoles);
					} else if (ps.checkPassword(account, passwordDomain, new Password(
							credentials), false, true)) {
						roles.add("PASSWORD:EXPIRED");
						principal = new SoffidPrincipalImpl(tenant.getName()+ "\\" + account, 
								userName, fullName, holderGroup,
								roles,
								groups, soffidRoles);
					} else {
						log.info(username + " login rejected. Invalid password");
						return null;
					}
					
			
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
					
					return principal;
				} finally {
					Security.nestedLogoff();
				}
			} finally {
				exitWebapp(state);
			}
		} catch (UnknownUserException e) {
			throw new SecurityException ("Error during login process", e);
		} catch (InternalErrorException e) {
			throw new SecurityException ("Error during login process", e);
		}
	}

	
    private List<String> getUserGroups(Account acc, String holderGroup) throws InternalErrorException, UnknownUserException {
    	List<String> result = new LinkedList<String>();
    	if (acc.getType().equals(AccountType.USER))
    	{
    		User u = acc.getOwnerUsers().iterator().next();
    		Collection<Group> groups;
			if (holderGroup == null)
    			groups = ServiceLocator.instance().getUserService().getUserGroupsHierarchy(u.getId());
    		else
    			groups = ServiceLocator.instance().getUserService().getUserGroupsHierarchy(u.getId() );
			for ( Group g: groups)
				result.add(g.getName());
    	}
    	return result;
	}


    private List<String> getUserRoles(Account acc, String holderGroup) throws InternalErrorException {
    	List<String> result = new LinkedList<String>();
    	Collection<RoleGrant> groups;
    	if (acc.getType().equals(AccountType.USER) && acc.getOwnerUsers().size() == 1)
    	{
    		User u = acc.getOwnerUsers().iterator().next();
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
    		if (grant.getSystem().equals(soffidSystem.getName()))
    			result.add(grant.getRoleName());
    		result.add(grant.getRoleName()+"@"+grant.getSystem());
    		if (grant.isHasDomain())
    		{
    			if (grant.getSystem().equals(soffidSystem.getName()))
    				result.add(grant.getRoleName()+"/"+grant.getDomainValue());
    			result.add(grant.getRoleName()+"/"+grant.getDomainValue()+"@"+grant.getSystem());
    		}
    	}
    	return result;
	}

    Realm dummyRealm = new SoffidRealm();
    Principal dummyPrincipal = new SoffidPrincipalImpl("master\\$$ANONYMUOS", null, "Anonymous", null, new LinkedList<String>(), null, null);

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
}
