package com.soffid.iam.utils;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Tenant;
import com.soffid.iam.api.User;
import com.soffid.iam.config.Config;
import com.soffid.iam.model.TenantEntity;
import com.soffid.iam.service.TenantService;
import com.soffid.iam.service.ejb.UserServiceHome;

import es.caib.seycon.ng.exception.InternalErrorException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Principal;
import java.security.acl.Group;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import org.apache.commons.collections.LRUMap;

// import org.jboss.security.SecurityAssociation;

/**
 * @author u88683
 * 
 */
public class Security {
    public static final String SC_ADM_APLICACIONS = "SC_ADM_APLICACIONS"; //$NON-NLS-1$
    public static final String SC_ADMINISTRADOR = "SC_ADMINISTRADOR"; //$NON-NLS-1$
    public static final String SC_ADMIN_SEG_ORG_USUARI = "SC_ADMIN_SEG_ORG_USUARI"; //$NON-NLS-1$
    public static final String SC_ADM_XARXES = "SC_ADM_XARXES"; //$NON-NLS-1$
    public static final String SC_RESP_SEG_ORG_USUARI = "SC_RESP_SEG_ORG_USUARI"; //$NON-NLS-1$
    public static final String SC_CAP_RRHH = "SC_CAP_RRHH"; //$NON-NLS-1$
    public static final String SC_RRHH = "SC_RRHH"; //$NON-NLS-1$
    public static final String SC_ADMINISTRADOR_SEGURETAT = "SC_ADMINISTRADOR_SEGURETAT"; //$NON-NLS-1$
    public static final String SC_RESPONSABLE_SEGURETAT = "SC_RESPONSABLE_SEGURETAT"; //$NON-NLS-1$
    public static final String SC_RESPONSABLE = "SC_RESPONSABLE"; //$NON-NLS-1$
    public static final String SC_OPERADOR = "SC_OPERADOR"; //$NON-NLS-1$
    public static final String SC_OPERADOR_WEB = "SC_OPERADOR_WEB"; //$NON-NLS-1$
    public static final String SC_CIUTADA = "SC_CIUTADA"; //$NON-NLS-1$
    public static final String SC_ADM_USUARIS = "SC_ADM_USUARIS"; //$NON-NLS-1$
    public static final String SC_ADM_GRUPS = "SC_ADM_GRUPS"; //$NON-NLS-1$
    public static final String SC_CON_APLICACIONS = "SC_CON_APLICACIONS"; //$NON-NLS-1$
    public static final String SC_VNC = "SC_VNC"; //$NON-NLS-1$
    public static final String SC_OPERADOR_CAU = "SC_OPERADOR_CAU"; //$NON-NLS-1$

    /* Afegim les autoritzacions */

    /**
     * Autorització GLOBAL, quan s'atorga, l'usuari té TOTES les autoritzacions
     */
    public static final String AUTO_AUTHORIZATION_ALL = "authorization:all"; // TOTES //$NON-NLS-1$
                                                                             // LES
                                                                             // AUTORITZACIONS

    /**
     * Tots els valors de domini. Nota: Inclou la barra
     */
    public static final String AUTO_ALL = "/*"; // PER LES AUTORITZACIONS AMB //$NON-NLS-1$
                                                // DOMINI

    public static final String AUTO_USER_CREATE = "user:create"; //$NON-NLS-1$
    public static final String AUTO_USER_UPDATE = "user:update"; //$NON-NLS-1$
    public static final String AUTO_USER_DELETE = "user:delete"; // donar de //$NON-NLS-1$
                                                                 // baixa usuari
                                                                 // (WF)
    public static final String AUTO_USER_UPDATE_CUSTOM = "user:custom:update"; // OperateUsuari //$NON-NLS-1$
    public static final String AUTO_USER_UPDATE_PASSWORD = "user:password:update"; // Generar //$NON-NLS-1$
    public static final String AUTO_USER_SET_PASSWORD = "user:password:set"; // pass
                                                                                   // temporal
    public static final String AUTO_USER_QUERY = "user:query"; //$NON-NLS-1$
    public static final String AUTO_USER_PROPAGATE = "user:refresh"; //$NON-NLS-1$
    public static final String AUTO_USER_ROLE_QUERY = "user:role:query"; //$NON-NLS-1$
    public static final String AUTO_USER_SESSION_QUERY = "user:session:query"; //$NON-NLS-1$
    // public static final String AUTO_USER_GROUP_QUERY = "user:group:query";
    // public static final String AUTO_USER_PRINTER_QUERY =
    // "user:printer:query";
    // public static final String AUTO_USER_METADATA_CREATE =
    // "user:metadata:create";
	public static final String AUTO_METADATA_UPDATE_ALL = "metadata:update:any";
    public static final String AUTO_USER_METADATA_UPDATE = "user:metadata:update"; //$NON-NLS-1$
    
    public static final String AUTO_USER_ROLE_CREATE = "user:role:create"; //$NON-NLS-1$
    public static final String AUTO_USER_ROLE_DELETE = "user:role:delete"; //$NON-NLS-1$
    public static final String AUTO_USER_GROUP_CREATE = "user:group:create"; //$NON-NLS-1$
    public static final String AUTO_USER_GROUP_DELETE = "user:group:delete"; //$NON-NLS-1$
    public static final String AUTO_USER_PRINTER_CREATE = "user:printer:create"; //$NON-NLS-1$
    public static final String AUTO_USER_PRINTER_DELETE = "user:printer:delete"; //$NON-NLS-1$
    public static final String AUTO_USER_ACL_PRINTER_CREATE = "user:printer:acl:create"; //$NON-NLS-1$
    public static final String AUTO_USER_ACL_PRINTER_DELETE = "user:printer:acl:delete"; //$NON-NLS-1$
    public static final String AUTO_USER_ACCESSREGISTER_QUERY = "user:accessRegister:query"; //$NON-NLS-1$
    public static final String AUTO_USER_MAZINGER_QUERY = "user:mazinger:query"; //$NON-NLS-1$

    public static final String AUTO_GROUP_CREATE = "group:create"; //$NON-NLS-1$
    public static final String AUTO_GROUP_UPDATE = "group:update"; //$NON-NLS-1$
    public static final String AUTO_GROUP_QUERY = "group:query"; //$NON-NLS-1$
    public static final String AUTO_GROUP_ROLE_QUERY = "group:role:query"; //$NON-NLS-1$
    public static final String AUTO_GROUP_USER_QUERY = "group:user:query"; //$NON-NLS-1$
    public static final String AUTO_GROUP_PRINTER_CREATE = "group:printer:create"; //$NON-NLS-1$
    public static final String AUTO_GROUP_PRINTER_DELETE = "group:printer:delete"; //$NON-NLS-1$

    public static final String AUTO_ORGANIZATIONALUNIT_CREATE = "organizationalUnit:create"; //$NON-NLS-1$
    public static final String AUTO_ORGANIZATIONALUNIT_UPDATE = "organizationalUnit:update"; //$NON-NLS-1$
    public static final String AUTO_ORGANIZATIONALUNIT_DELETE = "organizationalUnit:delete"; //$NON-NLS-1$
    public static final String AUTO_ORGANIZATIONALUNIT_QUERY = "organizationalUnit:query"; //$NON-NLS-1$

    public static final String AUTO_HOST_ALL_CREATE = "host:all:create"; //$NON-NLS-1$
    public static final String AUTO_HOST_ALL_UPDATE = "host:all:update"; //$NON-NLS-1$
    public static final String AUTO_HOST_ALL_DELETE = "host:all:delete"; //$NON-NLS-1$
    // public static final String AUTO_HOST_CREATE = "host:create";
    // public static final String AUTO_HOST_UPDATE = "host:update";
    // public static final String AUTO_HOST_DELETE = "host:delete";
    public static final String AUTO_HOST_UPDATE_OS = "host:os:update"; //$NON-NLS-1$

    /** Authorizations to manage OS types */
    public static final String AUTO_OS_QUERY = "operatingSystem:query"; //$NON-NLS-1$
    public static final String AUTO_OS_CREATE = "operatingSystem:create"; //$NON-NLS-1$
    public static final String AUTO_OS_DELETE = "operatingSystem:delete"; //$NON-NLS-1$
    public static final String AUTO_OS_UPDATE = "operatingSystem:update"; //$NON-NLS-1$
    
    public static final String AUTO_HOST_QUERY_ADMINISTRATOR_ACCESS = "host:admin:query"; //$NON-NLS-1$
    public static final String AUTO_HOST_ALL_SUPPORT_VNC = "host:support"; //$NON-NLS-1$
    /** Per poder veure només les màquines autoritzades */
    public static final String AUTO_HOST_QUERY = "host:query"; //$NON-NLS-1$
    /** Per poder veure TOTES les màquines */
    public static final String AUTO_HOST_ALL_QUERY = "host:all:query"; //$NON-NLS-1$

    public static final String AUTO_NETWORK_ALL_CREATE = "network:all:create"; //$NON-NLS-1$
    public static final String AUTO_NETWORK_ALL_UPDATE = "network:all:update"; //$NON-NLS-1$
    public static final String AUTO_NETWORK_ALL_DELETE = "network:all:delete"; //$NON-NLS-1$
    // public static final String AUTO_NETWORK_UPDATE = "network:update";
    // public static final String AUTO_NETWORK_DELETE = "network:delete";
    /** Per poder veure només les màquines autoritzades */
    // public static final String AUTO_NETWORK_QUERY = "network:query";
    /** Per poder veure TOTES les màquines */
    public static final String AUTO_NETWORK_ALL_QUERY = "network:all:query"; //$NON-NLS-1$

    public static final String AUTO_PRINTER_CREATE = "printer:create"; //$NON-NLS-1$
    public static final String AUTO_PRINTER_UPDATE = "printer:update"; //$NON-NLS-1$
    public static final String AUTO_PRINTER_DELETE = "printer:delete"; //$NON-NLS-1$
    public static final String AUTO_PRINTER_QUERY = "printer:query"; //$NON-NLS-1$
    public static final String AUTO_PRINTER_ACL_QUERY = "printer:acl:query"; //$NON-NLS-1$

    public static final String AUTO_APPLICATION_CREATE = "application:create"; //$NON-NLS-1$
    public static final String AUTO_APPLICATION_UPDATE = "application:update"; //$NON-NLS-1$
    public static final String AUTO_APPLICATION_DELETE = "application:delete"; //$NON-NLS-1$
    public static final String AUTO_APPLICATION_QUERY = "application:query"; //$NON-NLS-1$

    public static final String AUTO_ROLE_CREATE = "role:create"; //$NON-NLS-1$
    public static final String AUTO_ROLE_UPDATE = "role:update"; //$NON-NLS-1$
    public static final String AUTO_ROLE_DELETE = "role:delete"; //$NON-NLS-1$
    public static final String AUTO_ROLE_QUERY = "role:query"; //$NON-NLS-1$

    public static final String AUTO_ACCESSREGISTER_QUERY = "accessRegister:query"; //$NON-NLS-1$

    public static final String AUTO_METADATA_CREATE = "metadata:create"; //$NON-NLS-1$
    public static final String AUTO_METADATA_UPDATE = "metadata:update"; //$NON-NLS-1$
    public static final String AUTO_METADATA_DELETE = "metadata:delete"; //$NON-NLS-1$
    public static final String AUTO_METADATA_QUERY = "metadata:query"; //$NON-NLS-1$

    public static final String AUTO_SERVICE_CREATE = "service:create"; //$NON-NLS-1$
    public static final String AUTO_SERVICE_UPDATE = "service:update"; //$NON-NLS-1$
    public static final String AUTO_SERVICE_DELETE = "service:delete"; //$NON-NLS-1$
    public static final String AUTO_SERVICE_QUERY = "service:query"; //$NON-NLS-1$

    public static final String AUTO_MAIL_CREATE = "mail:create"; //$NON-NLS-1$
    public static final String AUTO_MAIL_UPDATE = "mail:update"; //$NON-NLS-1$
    public static final String AUTO_MAIL_DELETE = "mail:delete"; //$NON-NLS-1$
    public static final String AUTO_MAIL_QUERY = "mail:query"; //$NON-NLS-1$

    public static final String AUTO_LOPD_CREATE = "lopd:create"; //$NON-NLS-1$
    public static final String AUTO_LOPD_UPDATE = "lopd:update"; //$NON-NLS-1$
    public static final String AUTO_LOPD_DELETE = "lopd:delete"; //$NON-NLS-1$
    public static final String AUTO_LOPD_QUERY = "lopd:query"; //$NON-NLS-1$

    public static final String AUTO_PARAMETER_CREATE = "parameter:create"; //$NON-NLS-1$
    public static final String AUTO_PARAMETER_UPDATE = "parameter:update"; //$NON-NLS-1$
    public static final String AUTO_PARAMETER_DELETE = "parameter:delete"; //$NON-NLS-1$
    public static final String AUTO_PARAMETER_QUERY = "parameter:query"; //$NON-NLS-1$

    public static final String AUTO_AGENT_CREATE = "agent:create"; //$NON-NLS-1$
    public static final String AUTO_AGENT_UPDATE = "agent:update"; //$NON-NLS-1$
    public static final String AUTO_AGENT_DELETE = "agent:delete"; //$NON-NLS-1$
    public static final String AUTO_AGENT_QUERY = "agent:query"; //$NON-NLS-1$
    public static final String AUTO_AGENT_PROPAGATE_USERS = "agent:refreshUsers"; //$NON-NLS-1$
    public static final String AUTO_AGENT_PROPAGATE_ROLES = "agent:refreshRoles"; //$NON-NLS-1$
    public static final String AUTO_AGENT_PROPAGATE_GROUPS = "agent:refreshGroups"; //$NON-NLS-1$

    public static final String AUTO_AGENT_ACCESSCONTROL_CREATE = "agent:accessControl:create"; //$NON-NLS-1$
    public static final String AUTO_AGENT_ACCESSCONTROL_UPDATE = "agent:accessControl:update"; //$NON-NLS-1$
    public static final String AUTO_AGENT_ACCESSCONTROL_DELETE = "agent:accessControl:delete"; //$NON-NLS-1$
    public static final String AUTO_AGENT_ACCESSCONTROL_QUERY = "agent:accessControl:query"; //$NON-NLS-1$
    public static final String AUTO_AGENT_ACCESSCONTROL_SET = "agent:accessControl:set"; //$NON-NLS-1$

    public static final String AUTO_SERVER_MANAGE = "server:manage"; //$NON-NLS-1$
    public static final String AUTO_SERVER_QUERY = "server:query"; //$NON-NLS-1$

    public static final String AUTO_AUDIT_QUERY = "audit:query"; //$NON-NLS-1$
    // des d'usuaris
    public static final String AUTO_AUDIT_CUSTOM_QUERY = "audit:custom:query"; //$NON-NLS-1$

    public static final String AUTO_USERS_DOMAIN_CREATE = "usersDomain:create"; //$NON-NLS-1$
    public static final String AUTO_USERS_DOMAIN_UPDATE = "usersDomain:update"; //$NON-NLS-1$
    public static final String AUTO_USERS_DOMAIN_DELETE = "usersDomain:delete"; //$NON-NLS-1$
    public static final String AUTO_USERS_DOMAIN_QUERY = "usersDomain:query"; //$NON-NLS-1$

    // public static final String AUTO_INTRANETMENUS_QUERY =
    // "intranetMenus:query";
    public static final String AUTO_INTRANETMENUS_ALL_QUERY = "intranetMenus:all:query"; //$NON-NLS-1$
    public static final String AUTO_INTRANETMENUS_ADMIN = "intranetMenus:admin"; //$NON-NLS-1$

    public static final String AUTO_MONITOR_SERVER_LIST = "monitor:server:list"; //$NON-NLS-1$
    public static final String AUTO_MONITOR_AGENT_LIST = "monitor:agent:list"; //$NON-NLS-1$
    public static final String AUTO_MONITOR_AGENT_RESTART = "monitor:agent:restart"; //$NON-NLS-1$
    public static final String AUTO_BASE_LOG_QUERY = "base:log:query"; //$NON-NLS-1$
    public static final String AUTO_PLUGINS_UPDATE = "plugins:update"; //$NON-NLS-1$
    public static final String AUTO_PLUGINS_QUERY = "plugins:query"; //$NON-NLS-1$

    public static final String AUTO_AUTHORIZATION_ROL_CREATE = "authorization:rol:create"; //$NON-NLS-1$
    public static final String AUTO_AUTHORIZATION_ROL_DELETE = "authorization:rol:delete"; //$NON-NLS-1$
    public static final String AUTO_AUTHORIZATION_QUERY = "authorization:query"; //$NON-NLS-1$

    public static final String AUTO_PUPIL_CREATE = "pupil:create"; //$NON-NLS-1$
    /* public static final String AUTO_PUPIL_UPDATE = "pupil:update"; */

    public static final String AUTO_WORKFLOW_ADMIN = "workflow:admin"; //$NON-NLS-1$

    public static final String AUTO_IDENTITY_FEDERATION_CREATE = "federation:create"; //$NON-NLS-1$
    public static final String AUTO_IDENTITY_FEDERATION_UPDATE = "federation:update"; //$NON-NLS-1$
    public static final String AUTO_IDENTITY_FEDERATION_DELETE = "federation:delete"; //$NON-NLS-1$
    public static final String AUTO_IDENTITY_FEDERATION_QUERY = "federation:query"; //$NON-NLS-1$

    public static final String AUTO_SEU_VIEW_AGENTS = "seu:agents:show"; //$NON-NLS-1$
    public static final String AUTO_SEU_VIEW_APLICACIONS = "seu:aplicacions:show"; //$NON-NLS-1$
    public static final String AUTO_SEU_VIEW_AUDITORIA = "seu:auditoria:show"; //$NON-NLS-1$
    public static final String AUTO_SEU_VIEW_AUTORITZACIONS = "seu:autoritzacions:show"; //$NON-NLS-1$
    public static final String AUTO_SEU_VIEW_CORREU = "seu:correu:show"; //$NON-NLS-1$
    public static final String AUTO_SEU_VIEW_DOMINISCORREU = "seu:dominiscorreu:show"; //$NON-NLS-1$
    public static final String AUTO_SEU_VIEW_DADESADDICIONALS = "seu:dadesAddicionals:show"; //$NON-NLS-1$
    public static final String AUTO_SEU_VIEW_GRUPS = "seu:grups:show"; //$NON-NLS-1$
    public static final String AUTO_SEU_VIEW_IMPRESSORES = "seu:impressores:show"; //$NON-NLS-1$
    public static final String AUTO_SEU_VIEW_LOPD = "seu:lopd:show"; //$NON-NLS-1$
    // public static final String AUTO_SEU_VIEW_MAQUINES ="seu:maquines:show";
    public static final String AUTO_SEU_VIEW_PARAMETRES = "seu:parametres:show"; //$NON-NLS-1$
    public static final String AUTO_SEU_VIEW_PLUGINS = "seu:plugins:show"; //$NON-NLS-1$
    public static final String AUTO_SEU_VIEW_REGISTREACCES = "seu:registreAcces:show"; //$NON-NLS-1$
    public static final String AUTO_SEU_VIEW_SERVEIS = "seu:serveis:show"; //$NON-NLS-1$
    public static final String AUTO_SEU_VIEW_TIPUSUO = "seu:tipusUO:show"; //$NON-NLS-1$
    public static final String AUTO_SEU_VIEW_USUARIS = "seu:usuaris:show"; //$NON-NLS-1$
    // public static final String AUTO_SEU_VIEW_XARXES ="seu:xarxes:show";
    public static final String AUTO_SEU_VIEW_MENUSINTRANET = "seu:menusIntranet:show"; //$NON-NLS-1$
    public static final String AUTO_SEU_VIEW_SEYCONSERVER = "seu:monitor:show"; //$NON-NLS-1$
    public static final String AUTO_SEU_VIEW_FEDERACIOIDENTITATS = "seu:federacioIdentitats:show"; //$NON-NLS-1$
    public static final String AUTO_SEU_VIEW_USERS_DOMAIN = "seu:usersDomain:show"; //$NON-NLS-1$

    /* SCOPES de les autoritzacios de tipus GRUP */
    public static final String AUTO_SCOPE_FILLS = "children"; //$NON-NLS-1$
    public static final String AUTO_SCOPE_PARES = "parents"; //$NON-NLS-1$
    public static final String AUTO_SCOPE_BOTH = "both"; //$NON-NLS-1$
    public static final String AUTO_SCOPE_ONE = "one"; //$NON-NLS-1$

    /* Accunts authorization */
    public static final String AUTO_SEU_VIEW_ACCOUNTS = "seu:accounts:show"; //$NON-NLS-1$
    public static final String AUTO_ACCOUNT_CREATE = "account:create"; //$NON-NLS-1$
    public static final String AUTO_ACCOUNT_UPDATE = "account:update"; //$NON-NLS-1$
    public static final String AUTO_ACCOUNT_DELETE = "account:delete"; //$NON-NLS-1$
    public static final String AUTO_ACCOUNT_QUERY = "account:query"; //$NON-NLS-1$
    public static final String AUTO_ACCOUNT_PASSWORD = "account:password"; //$NON-NLS-1$
    
    public static final String AUTO_ACCOUNT_ATTRIBUTE_UPDATE = "account:attribute:update"; //$NON-NLS-1$
    public static final String AUTO_ACCOUNT_ATTRIBUTE_QUERY = "account:attribute:query";

    public static final String AUTO_REMEMBER_PASSWORD_CREATE = "rememberPassword:create"; //$NON-NLS-1$
    public static final String AUTO_REMEMBER_PASSWORD_UPDATE = "rememberPassword:update"; //$NON-NLS-1$
    public static final String AUTO_REMEMBER_PASSWORD_DELETE = "rememberPassword:delete"; //$NON-NLS-1$
    public static final String AUTO_REMEMBER_PASSWORD_QUERY = "rememberPassword:query"; //$NON-NLS-1$
    public static final String AUTO_SEU_VIEW_REMEMBER_PASSWORD = "seu:rememberPassword:show"; //$NON-NLS-1$
	
    private static Class securityAssociationClass;
    private static Method getSubjectMethod;

    private static ThreadLocal identities = new ThreadLocal();
    private static boolean disableAllSecurityForEver = false;
	public static boolean isDisableAllSecurityForEver() {
		return disableAllSecurityForEver;
	}

	private static com.soffid.iam.service.UserService userService = null;

    private static Stack getIdentities() {
        Stack s = (Stack) identities.get();
        if (s == null) {
            s = new Stack();
            identities.set(s);
        }
        return s;
    }

    public static boolean isUserInRole(String role) {
        if (disableAllSecurityForEver)
            return true;

        int roleCercatSenseDomini = role.lastIndexOf("/"); //$NON-NLS-1$
        boolean rolAmbDomini = (roleCercatSenseDomini != -1);

        if (getIdentities().isEmpty()) {
            if (disableAllSecurityForEver)
                return true;

            Subject subject;
            try {
                subject = getCurrentSubject();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

            if (subject == null)
                return false; // pot ésser nul??
            boolean userInRole = false;
            Iterator principalIterator = subject.getPrincipals().iterator();

            while (!userInRole && principalIterator.hasNext()) {
                Principal principal = (Principal) principalIterator.next();
                if (principal.getName().compareTo("Roles") == 0) { //$NON-NLS-1$
                    String[] roles = splitRoles(principal.toString());
                    for (int i = 0; (!userInRole) && i < roles.length; i++) {
                        if (rolAmbDomini) {
                            // Comprovem que siga la mateixa autorització
                            if (roles[i].startsWith(role.substring(0, roleCercatSenseDomini))) {
                                // l'usuari té l'autorització amb domini i tot o
                                // la té per tots els dominis
                                userInRole = (roles[i].compareTo(role) == 0 || roles[i]
                                        .endsWith("/*")); //$NON-NLS-1$
                            }
                            // userInRole =
                            // roles[i].startsWith(role.substring(0,role.length()-1));
                        } else {
                            userInRole = roles[i].compareTo(role) == 0;
                        }
                    }
                }
            }
            return userInRole;
        } else {
            Identity id = (Identity) getIdentities().peek();
            for (int i = 0; i < id.roles.length; i++) {
                if (id.roles[i].equals(AUTO_AUTHORIZATION_ALL))
                    return true;
                if (rolAmbDomini) {
                    /*
                     * return id.roles[i].startsWith(role.substring(0,
                     * role.length() - 1));
                     */
                    if (id.roles[i].startsWith(role.substring(0, roleCercatSenseDomini))) {
                        // l'usuari té l'autorització amb domini i tot o la té
                        // per tots els dominis
                        if (id.roles[i].compareTo(role) == 0 || id.roles[i].endsWith("/*")) //$NON-NLS-1$
                            return true;
                    }
                } else {
                    if (role.equals(id.roles[i]))
                        return true;
                }
            }
            return false;
        }
    }

	private static Subject getCurrentSubject() throws ClassNotFoundException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		Subject subject;
		if (securityAssociationClass == null) {
		    securityAssociationClass = Class
		            .forName("org.jboss.security.SecurityAssociation"); //$NON-NLS-1$
		}
		if (getSubjectMethod == null)
		    getSubjectMethod = securityAssociationClass.getMethod("getSubject"); //$NON-NLS-1$

		subject = (Subject) getSubjectMethod.invoke(null);
		return subject;
	}

    public static List<String> getAuthorizations() {
        String roles [] = null;

        if (getIdentities().isEmpty()) {
            if (disableAllSecurityForEver)
                return Collections.emptyList();

            Subject subject;
            try {
                subject = getCurrentSubject();
            } catch (Exception e) {
                e.printStackTrace();
                return Collections.emptyList();
            }

            if (subject == null)
                return Collections.emptyList();

            Iterator principalIterator = subject.getPrincipals().iterator();
            while (roles == null && principalIterator.hasNext()) {
                Principal principal = (Principal) principalIterator.next();
                if (principal.getName().compareTo("Roles") == 0) { //$NON-NLS-1$
                    roles = splitRoles(principal.toString());
                }
            }
        } else {
            Identity id = (Identity) getIdentities().peek();
            roles = id.roles;
        }
        if (roles == null)
        	return Collections.emptyList();
        	
        LinkedList<String> rolesList = new LinkedList <String>();
        for (String role: roles)
        {
        	rolesList.add (role);
        	int i = role.lastIndexOf("/"); //$NON-NLS-1$
        	if (i > 0)
        	{
        		rolesList.add (role.substring(0, i));
        	}
        }
        return rolesList;
    }


    private static String[] splitRoles(String roleList) {
        roleList = roleList.replace("Roles(members:", ""); //$NON-NLS-1$ //$NON-NLS-2$
        roleList = roleList.replace(")", ""); //$NON-NLS-1$ //$NON-NLS-2$
        String[] rolesInitial = roleList.split(","); //$NON-NLS-1$
        String[] rolesFinal = new String[rolesInitial.length];
        for (int i = 0; i < rolesInitial.length; i++) {
            rolesFinal[i] = rolesInitial[i].trim();
        }
        return rolesFinal;
    }

    public static Principal getPrincipal() {
        if (!getIdentities().isEmpty()) {
            return ((Identity) getIdentities().peek()).principal;
        } else if (disableAllSecurityForEver) {
            String host;
            try {
                host = Config.getConfig().getHostName();
            } catch (IOException e) {
                try {
                    host = InetAddress.getLocalHost().getHostName();
                } catch (UnknownHostException e1) {
                    host = "root"; //$NON-NLS-1$
                }
            }
            return new RunAsPrincipal(host);
        } else {
        	try {
				Subject s = getCurrentSubject();
				for (Principal p: s.getPrincipals())
				{
					if (! (p instanceof Group)) {
						return p;
					} 
				}
				return null;
			} catch (Exception e) {
				return null;
			}
        }
    }

    private static TenantService tenantService = null;
    public static TenantService getTenantService ()
    {
    	if (tenantService == null)
    		tenantService = ServiceLocator.instance().getTenantService ();
    	return tenantService;
    }
    
    private static void internalNestedLogin(String tenant, String user, String roles[])  {
        Identity i = new Identity();
        i.principal = new RunAsPrincipal(tenant+"\\"+user);
        i.roles = roles;
        i.transactionInitiated = false;
        getIdentities().push(i);
        boolean doTransaction = false;
        if (doTransaction) { 
            try {
                UserTransaction tx = (UserTransaction) new InitialContext().lookup("/UserTransaction"); //$NON-NLS-1$
                if (tx != null && tx.getStatus() == Status.STATUS_NO_TRANSACTION) {
                    tx.begin();
                    i.transaction = tx;
                    i.transactionInitiated = true;
                }
            } catch (Exception e1) {
            }
        }
    }

    public static void nestedLogin(String user, String roles[])  {
    	assertCanSetIdentity();
    	
        String ctn;
		try {
			ctn = getCurrentTenantName()+"\\";
		} catch (InternalErrorException e) {
			throw new RuntimeException(e);
		}
        if (! user.startsWith(ctn))
        	user = ctn + user;
        int i = user.indexOf('\\');
        internalNestedLogin(user.substring(0, i), user.substring(i+1), roles);
    }

    public static void nestedLogin(String tenant, String user, String roles[])  {
    	assertCanSetTenant();
    	
        internalNestedLogin(tenant, user, roles);
    }

    public static void nestedLogin( String roles[])  {
        nestedLogin (getPrincipal().getName(), roles);
    }

    public static void nestedLogoff() {
        Identity i = (Identity) getIdentities().pop();
    }

    public static void disableAllSecurityForEver() {
        disableAllSecurityForEver = true;
    }

    public static String getCurrentAccount ()
    {
    	Principal p = getPrincipal();
    	if (p == null)
    		return null;
    	else
    	{
    		String n = p.getName();
    		int i = n.lastIndexOf('\\');
    		if ( i >= 0)
    			return n.substring(i+1);
    		else
    			return n;
    	}
    }
    
    static Map<String, String> principalToUserMap = null;
    static long currentTenant = 0L;
    
    public static long getCurrentTenantId ()
    {
    	try {
			String t = getCurrentTenantName();
			return getTenantId(t);
		} catch (InternalErrorException e) {
			throw new RuntimeException("Unable to get current tenant id: "+e.getMessage(), e);
		} 
    }
    
    public static String getCurrentTenantName () throws InternalErrorException
    {
    	Principal p = getPrincipal();
    	Tenant t = null;
    	if (p != null)
    	{
    		int i = p.getName().indexOf('\\');
    		if (i >= 0)
    			return p.getName().substring(0, i);
    	}
    	
   		return getTenantService().getMasterTenant().getName();
    }
    

    public static boolean isAuthorizedTenant (TenantEntity tenant) 
    {
    	return (tenant.getId().longValue() == getCurrentTenantId());
    }
    
    public static String getCurrentUser () 
    {
    	Principal p;
    	if (! getIdentities().isEmpty())
    	{
    		Identity identity = (Identity) getIdentities().peek();
    		p = identity.principal;
    	}
    	else if (disableAllSecurityForEver)
    		return null;
    	else
    	{
    		p = getPrincipal();
    	}
    	
		if (p == null)
			return null;
		if (principalToUserMap == null)
		{
	    	int size = 500;
	    	try {
		    	String cacheSize = System.getProperty("soffid.cache.identity.size");
		    	if (cacheSize != null )
		    		size = Integer.parseInt(cacheSize);
	    	} catch (Throwable t) {
	    		
	    	}
	    	principalToUserMap = Collections.synchronizedMap(new LRUMap(size));			
		}
        try
        {
    		String user = principalToUserMap.get(p.getName());
    		if (user == null)
    		{
	            if (userService == null)
	            {
            		userService = ServiceLocator.instance().getUserService();
	            }
				User usuari = userService.getCurrentUser();
				if (usuari == null)
					return null;
				user = usuari.getUserName();
				principalToUserMap.put(p.getName(), user);
    		}
    		return user;
		}
		catch (InternalErrorException e)
		{
			return null;
		}
    }
    
    static HashMap<String, Long> tenants = new HashMap<String,Long>();
    public static Long getTenantId (String tenantName) throws InternalErrorException {
    	Long id = tenants.get(tenantName);
    	if (id == null)
    	{
    		Tenant tenant = tenantName == null ? 
    			getTenantService().getMasterTenant() :
    			getTenantService().getTenant(tenantName);
    		if (tenant != null)
    		{
    			id = tenant.getId();
    			tenants.put(tenantName, id);
    		}
    	}
    	return id;
    }
    
    private static StackTraceElement getCaller ()
    {
    	StackTraceElement trace[] = Thread.currentThread().getStackTrace();
    	// Find Security methods
    	int i = 0;
    	while (i < trace.length && ! trace[i].getClassName().equals(Security.class.getName()))
    		i++;
    	// Skip security methods
    	while (i < trace.length && trace[i].getClassName().equals(Security.class.getName()))
    		i++;
    	if (i < trace.length)
    		return trace[i];
    	
    	else
    		return null;
    	
    }

    private static void assertCanSetIdentity ()
    {
    	StackTraceElement caller = getCaller();
    }

    private static void assertCanSetTenant ()
    {
    	StackTraceElement caller = getCaller();
    	
    }
}

class Identity {
    Principal principal;
    String roles[];
    boolean transactionInitiated;
    UserTransaction transaction;
}
