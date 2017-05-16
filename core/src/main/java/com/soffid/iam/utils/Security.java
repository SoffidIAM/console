package com.soffid.iam.utils;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Tenant;
import com.soffid.iam.api.User;
import com.soffid.iam.config.Config;
import com.soffid.iam.model.TenantEntity;
import com.soffid.iam.service.TenantService;
import com.soffid.iam.service.TenantServiceImpl;
import com.soffid.iam.service.ejb.UserServiceHome;
import com.soffid.iam.tomcat.SoffidPrincipal;

import es.caib.seycon.ng.exception.InternalErrorException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.AccessController;
import java.security.Principal;
import java.security.acl.Group;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
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

import org.apache.catalina.realm.GenericPrincipal;
import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.logging.LogFactory;
import org.apache.openejb.spi.Assembler;
import org.apache.openejb.spi.SecurityService;
import org.apache.tomee.catalina.TomcatSecurityService.TomcatUser;

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

    public static final String AUTO_SERVER_MANAGE_PROXY = "server:manage:proxy"; //$NON-NLS-1$
    public static final String AUTO_SERVER_MANAGE_SERVER = "server:manage:server"; //$NON-NLS-1$
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
	public static final String[] ALL_PERMISSIONS = new String [] { AUTO_AUTHORIZATION_ALL };
	public static final String AUTO_TENANT_QUERY = "tenant:query";
	
    private static ThreadLocal<Stack<SoffidPrincipal>> identities = new ThreadLocal<Stack<SoffidPrincipal>>();
    private static boolean onSyncServer = false;
	public static boolean isSyncServer() {
		return onSyncServer;
	}

	private static com.soffid.iam.service.UserService userService = null;

    private static Stack<SoffidPrincipal> getIdentities() {
        Stack<SoffidPrincipal> s = (Stack<SoffidPrincipal>) identities.get();
        if (s == null) {
            s = new Stack<SoffidPrincipal>();
            identities.set(s);
        }
        return s;
    }

    private static String[] getPrincipalRoles (GenericPrincipal p)
    {
    	try {
    		return p.getRoles();
		} catch (Exception e) {
			return new String[0];
		}
    }
    

    public static boolean isUserInRole(String role) {
        if (onSyncServer)
            return true;

        GenericPrincipal principal = getPrincipal ();

        if (principal == null)
        	return false;
        
    	if (principal.hasRole(role))
    		return true;
    	
//    	if (principal.hasRole(AUTO_AUTHORIZATION_ALL))
//    		return true;

    	int i = role.indexOf('/');
        if (i <= 0)
        {
        	for ( String s: getPrincipalRoles(principal))
        	{
        		if (s.startsWith(role+"/"))
        			return true;
        	}
        }
        else
        {
        	if (principal.hasRole(role.substring(0, i)+Security.AUTO_ALL));
        		return true;
        }
        
        return false;
    }

    public static List<String> getAuthorizations() {
    	GenericPrincipal principal = getPrincipal();
    	if (principal == null)
    		return Collections.emptyList();
    	else
    		return Arrays.asList(principal.getRoles());
    }


    public static GenericPrincipal getPrincipal() {
        if (!getIdentities().isEmpty()) {
            return getIdentities().peek();
        } else if (onSyncServer) {
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
            return new SoffidPrincipal(host, host, Collections.singletonList(AUTO_AUTHORIZATION_ALL) );
        } else {
        	return TomeePrincipalRetriever.getPrincipal ();
        }
    }

    private static TenantService tenantService = null;
    private static TenantService getTenantService ()
    {
    	if (tenantService == null)
    		tenantService = ServiceLocator.instance().getTenantService ();
    	return tenantService;
    }
    
    private static void internalNestedLogin(String tenant, String user, String roles[])  {
        SoffidPrincipal p = new SoffidPrincipal(tenant+"\\"+user, "*", Arrays.asList(roles));
        getIdentities().push(p);
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
    	assertCanSetTenant(tenant);
    	assertCanSetIdentity();
        internalNestedLogin(tenant, user, roles);
    }

    public static void nestedLogin(GenericPrincipal principal)  {
        if (principal instanceof SoffidPrincipal)
        {
            int i = principal.getName().indexOf('\\');
            if ( i >= 0)
            	assertCanSetTenant(principal.getName().substring(0, i));
        	assertCanSetIdentity();
        	getIdentities().push((SoffidPrincipal) principal);
        }
        else
        	nestedLogin(principal.getName(), principal.getRoles());
    }


    public static void nestedLogin( String roles[])  {
        nestedLogin (getPrincipal().getName(), roles);
    }

    public static void nestedLogoff() {
        getIdentities().pop();
    }

    public static void onSyncServer() {
        onSyncServer = true;
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
    	GenericPrincipal p = getPrincipal();
    	if (p != null)
    	{
    		int i = p.getName().indexOf('\\');
    		if (i >= 0)
    			return p.getName().substring(0, i);
    	}
    	
   		return getMasterTenantName();
    }
    

    public static boolean isAuthorizedTenant (TenantEntity tenant) 
    {
    	return (tenant.getId().longValue() == getCurrentTenantId());
    }
    
    public static String getCurrentUser () 
    {
    	GenericPrincipal p;
    	if (! getIdentities().isEmpty())
    	{
    		p = getIdentities().peek();
    	}
    	else if (onSyncServer)
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
		    	String cacheSize = ConfigurationCache.getMasterProperty("soffid.cache.identity.size");
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
    private static Long getTenantId (String tenantName) throws InternalErrorException {
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
    
    static HashMap<Long, String> tenantNames = new HashMap<Long,String>();
    public static String getTenantName (Long tenantId) throws InternalErrorException {
    	String name = tenantNames.get(tenantId);
    	if (name == null)
    	{
    		Tenant tenant = tenantId == null ? 
    			getTenantService().getMasterTenant() :
    			getTenantService().getTenant(tenantId);
    		if (tenant != null)
    		{
    			name = tenant.getName();
    			tenantNames.put(tenantId, name);
    		}
    	}
    	return name;
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
    	SecurityManager security = System.getSecurityManager();
    	if (security != null)
    		AccessController.checkPermission(new NestedLoginPermission("login"));
    }

    private static void assertCanSetTenant (String tenant)
    {
    	try {
			if (! isMasterTenant() && ! tenant.equals( getCurrentTenantName()))
		    	AccessController.checkPermission(new NestedLoginPermission("tenant"));
		} catch (InternalErrorException e) {
			throw new SecurityException("Not allowed to change tenant", e);
		}
    }

	public static boolean isMasterTenant() throws InternalErrorException {
		return getCurrentTenantName() == null || 
				getCurrentTenantName ().equals (getMasterTenantName());
	}
	
	static String masterTenantName = null;
	
	public static String getMasterTenantName () throws InternalErrorException{
		if (masterTenantName == null)
		{
			masterTenantName = ServiceLocator.instance().getTenantService().getMasterTenant().getName();
		}
		return masterTenantName;
	}

}

class Identity {
    Principal principal;
    String roles[];
    boolean transactionInitiated;
    UserTransaction transaction;
}
