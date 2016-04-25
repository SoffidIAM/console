// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.service.workflow;

import es.caib.seycon.ng.servei.workflow.*;

import com.soffid.iam.api.Application;
import com.soffid.iam.api.AuthorizationRole;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.User;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.service.ApplicationService;
import com.soffid.iam.service.AuthorizationService;
import com.soffid.iam.service.UserService;
import com.soffid.iam.service.workflow.CreateDisableUserServiceImpl.ComparaGrups;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.AccountType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author u89559
 * 
 */
public class AuthorizationInformationServiceImpl extends
		com.soffid.iam.service.workflow.AuthorizationInformationServiceBase {

	/**
	 * @return retorna totes les aplicacions
	 * @throws java.lang.Exception
	 */
	protected java.util.Collection<Application> handleGetApplications() throws java.lang.Exception {
		ApplicationService aplicacioService = getApplicationService();
		Collection<Application> aplicacions = aplicacioService.getApplications();
		return aplicacions;
	}

	/**
	 * @param codiAplicacio:
	 *            codi de l'aplicació. Obligatori
	 * @return collection dels rols de l'apliació
	 * @throws java.lang.Exception
	 */
	protected java.util.Collection<Role> handleFindRolesByApplicationCode(java.lang.String codiAplicacio) throws java.lang.Exception {
		ApplicationService aplicacioService = getApplicationService();
		Security.nestedLogin(Security.getCurrentAccount(), new String [] {
			Security.AUTO_APPLICATION_QUERY+Security.AUTO_ALL,
			Security.AUTO_ROLE_QUERY+Security.AUTO_ALL
		});
		try {
			Collection<Role> rols = aplicacioService.findRolesByApplicationName(codiAplicacio);
			for (Iterator<Role> it = rols.iterator(); it.hasNext(); ) {
                Role r = it.next();
                if (r.getBpmEnforced() == null || !r.getBpmEnforced().booleanValue()) it.remove();
            }
			return rols;
		} finally {
			Security.nestedLogoff();
		}
	}
	
	/**
	 * @param codiAplicacio:
	 *            codi de l'aplicació. Obligatori
	 * @return collection dels rols de l'apliació
	 * @throws java.lang.Exception
	 */
	protected Collection<Role> handleFindRolesByApplicationCodeUnrestricted(String codiAplicacio) throws Exception {
		ApplicationService aplicacioService = getApplicationService();
		Collection<Role> rols = aplicacioService.findRolesByApplicationNameUnrestricted(codiAplicacio);
		return rols;
	}	

	/**
	 * @param codiUsuari:
	 *            codi de l'usuari. Obligatori
	 * @return: collection dels rols de l'usuari
	 * @throws java.lang.Exception
	 */
	protected java.util.Collection<Role> handleFindRolesByUserCode(java.lang.String codiUsuari) throws java.lang.Exception {
		ApplicationService aplicacioService = getApplicationService();
		return aplicacioService.findRolesByUserName(codiUsuari);
	}

	/**
	 * @param dni:
	 *            DNI de l'usuari en format LIKE d'SQL (483%, 49217421,....).
	 *            Null per ignorar-lo. No obligatori
	 * @param nom:
	 *            Nom de l'usuari en format LIKE SQL. Null per ignorar-lo. No
	 *            obligatori
	 * @param primerLlinatge:
	 *            primer llinatge de l'usuari en format LIKE d'SQL. Null per
	 *            ignorar-lo. No obligatori
	 * @param segonLlinatge:
	 *            segon llinatge de l'usuari en format LIKE d'SQL. Null per
	 *            ignorar-lo. No obligatori
	 * @return collection dels usuaris que fan matching amb tots els paràmetres
	 * @throws java.lang.Exception
	 */
	protected java.util.Collection<User> handleFindUserByUserData(java.lang.String dni, String nom, String primerLlinatge, String segonLlinatge) throws java.lang.Exception {
		UserService usuariService = getUserService();
		Collection<User> usuaris = usuariService.findUsersByCoreData("%", nom, primerLlinatge, segonLlinatge, dni);
		return usuaris;
	}

	/**
	 * @param codiAplicacio:
	 *            codi de l'aplicació. Obligatori
	 * @return collection d'usuaris administradors de l'apliació
	 * @throws Exception
	 */
	protected Collection<User> handleFindApplicationManagersByApplicationCode(String codiAplicacio) throws Exception {
		
		AuthorizationService autService = getAuthorizationService();
		ApplicationService aplicacioService = getApplicationService();
		
		HashSet<User> users = new HashSet<User>();
		for (AuthorizationRole ar : autService.getAuthorizationRoles(Security.AUTO_USER_ROLE_CREATE)) {
            for (RoleGrant rg : aplicacioService.findEffectiveRoleGrantsByRoleId(ar.getRole().getId())) {
                if (rg.getUser() != null) {
                    if (rg.getDomainValue() == null || rg.getDomainValue().equals(codiAplicacio)) {
                        users.add(getUserService().findUserByUserName(rg.getUser()));
                    }
                }
            }
        }
		return users;
	}

	/**
	 * @param codiUsuari:
	 *            codi d'usuari. Obligatori. Obligatori
	 * @return collection de les aplicacions de les que l'usuari té rols
	 * @throws Exception
	 */
	protected Collection<Application> handleGetApplicationsByUserCode(String codiUsuari) throws Exception {
		UserService usuariService = getUserService();
		Collection aplicacions = usuariService.getBpmEnabledApplicationsByUserName(codiUsuari);
		return aplicacions;
	}

	/**
	 * @param codiUsuari
	 *            codi d'usuari. Obligatori
	 * @return collection de rols que te l'usuari
	 * @throws Exception
	 */
	protected Collection<Role> handleGetRolesByUserCode(String codiUsuari) throws Exception {
		ApplicationService aplicacioService = getApplicationService();
		Collection<Role> aplicacions = aplicacioService.findRolesByUserName(codiUsuari);
		return aplicacions;
	}

	/**
	 * @param codiUsuari:
	 *            codi l'usuari. Obligatori
	 * @param codiAplicacio:
	 *            codi aplicació. Obligatori
	 * @return rols de l'aplicació que te l'usuari
	 * @throws Exception
	 */
	protected Collection<Role> handleGetApplicationRolesByUserCodeAndApplicationCode(String codiUsuari, String codiAplicacio) throws Exception {
		UserService usuariService = getUserService();
		Collection<Role> aplicacions = usuariService.getApplicationRolesByuserNameAndApplicationName(codiUsuari, codiAplicacio);
		return aplicacions;
	}

	/**
	 * @param codiUsuari:
	 *            codi d'usuari. Obligatori
	 * @param codiAplicacio:
	 *            codi aplicacio. Obligatori
	 * @return retorna true si l'usuari és administrador de l'aplicació, false
	 *         altrament
	 * @throws Exception
	 */
	protected Boolean handleIsApplicationManager(String codiUsuari, String codiAplicacio) throws Exception {
		AuthorizationService autService = getAuthorizationService();
		ApplicationService aplicacioService = getApplicationService();
		
		HashSet<User> users = new HashSet<User>();
		for (AuthorizationRole ar : autService.getAuthorizationRoles(Security.AUTO_USER_ROLE_CREATE)) {
            for (RoleGrant rg : aplicacioService.findEffectiveRoleGrantsByRoleId(ar.getRole().getId())) {
                if (rg.getUser() != null && codiUsuari.equals(rg.getUser())) {
                    if (rg.getDomainValue() == null || rg.getDomainValue().equals(codiAplicacio)) {
                        return true;
                    }
                }
            }
        }
		return false;
	}

	protected Collection<Application> handleFindApplicationByCriteria(String codi, String nom, String directoriFonts, String responsable, String directoriExecutable, String bd) throws Exception {
		ApplicationService aplicacioService = getApplicationService();
		return aplicacioService.findApplicationByCriteriaUnrestricted(codi, nom, directoriFonts, responsable, directoriExecutable, bd, null, "S"); //$NON-NLS-1$
	}
	
	protected Application handleFindApplicationByApplicationCode(String codiAplicacio) throws Exception {
		ApplicationService aplicacioService = getApplicationService();
		return aplicacioService.findApplicationByApplicationNameUnrestricted(codiAplicacio);
	}	

	protected Role handleGetSystemsRoles(String codiAplicacio) throws Exception {
		ApplicationService aplicacioService = getApplicationService();
		Role rol = aplicacioService.findRoleByRoleNameAndApplicationNameAndDispatcherName("SC_DGTICSISTEMES", "SEYCON", "JBOSS"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return rol;
	}

	protected boolean handleInterventionNeeded(String codiAplicacio, String[] codisRols) throws Exception {
		return true;
	}

	protected Collection<Application> handleFindManagedApplicationsByUserCode(String codiUsuari) throws Exception {
		AuthorizationService autService = getAuthorizationService();
		ApplicationService aplicacioService = getApplicationService();
		
		HashSet<Application> apps = new HashSet<Application>();
		for (AuthorizationRole ar : autService.getAuthorizationRoles(Security.AUTO_USER_ROLE_CREATE)) {
            for (RoleGrant rg : aplicacioService.findEffectiveRoleGrantsByRoleId(ar.getRole().getId())) {
                if (rg.getUser() != null && rg.getUser().equals(codiUsuari)) {
                    if (rg.getDomainValue() == null) {
                        for (Application app : aplicacioService.findApplicationByCriteria(null, null, null, null, null, null, null, null)) {
                            apps.add(app);
                            break;
                        }
                    } else {
                        apps.add(getApplicationService().findApplicationByApplicationName(rg.getDomainValue()));
                    }
                }
            }
        }
		return apps;
	}

}