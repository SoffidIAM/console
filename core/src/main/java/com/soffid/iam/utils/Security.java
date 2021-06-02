package com.soffid.iam.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.AccessController;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.UserTransaction;

import org.apache.catalina.realm.GenericPrincipal;
import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Tenant;
import com.soffid.iam.api.User;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.config.Config;
import com.soffid.iam.juli.AsyncFileHandler;
import com.soffid.iam.model.TenantEntity;
import com.soffid.iam.security.SoffidPrincipalImpl;
import com.soffid.iam.service.TenantService;
import com.soffid.iam.service.TenantServiceImpl;

import es.caib.seycon.ng.exception.InternalErrorException;

// import org.jboss.security.SecurityAssociation;

/**
 * @author u88683
 * 
 */
public class Security {
	static Log log = LogFactory.getLog(Security.class);
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
    public static final String AUTO_ACCOUNT_HP_PASSWORD = "account:hp-password"; //$NON-NLS-1$
    
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
    private static boolean onSyncProxy = false;
    
	public static boolean isSyncServer() {
		return onSyncServer;
	}

	public static boolean isSyncProxy() {
		return onSyncProxy;
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

    public static String[] getPrincipalSoffidRoles (GenericPrincipal p)
    {
    	try {
    		return ((SoffidPrincipal) p).getSoffidRoles();
		} catch (Throwable e) {
			return new String[0];
		}
    }
    
    public static String[] getPrincipalGroups (GenericPrincipal p)
    {
    	try {
    		return ((SoffidPrincipal) p).getGroups();
		} catch (Throwable e) {
			return new String[0];
		}
    }
    

    public static String[] getPrincipalGroupsAndRoles (GenericPrincipal p)
    {
    	try {
    		return ((SoffidPrincipal) p).getGroupsAndRoles();
		} catch (Throwable e) {
			return new String[0];
		}
    }
    
    public static boolean isUserInRole(String role) {
        if (onSyncServer)
            return true;

        SoffidPrincipal principal = getSoffidPrincipal ();

        if (principal == null)
        	return false;
        
    	if (principal.hasRole(role))
    		return true;
    	
//    	if (principal.hasRole(AUTO_AUTHORIZATION_ALL))
//    		return true;

    	int i = role.indexOf('/');
        if (i <= 0)
        {
        	for ( String s: principal.getRoles())
        	{
        		if (s.startsWith(role+"/"))
        			return true;
        	}
        }
        else
        {
        	if (principal.hasRole(role.substring(0, i)+Security.AUTO_ALL) ||
                principal.hasRole(role) )
        		return true;
        }
        
        return false;
    }

    public static List<String> getAuthorizations() {
    	SoffidPrincipal principal = getSoffidPrincipal();
    	if (principal == null)
    		return Collections.emptyList();
    	else
    		return Arrays.asList(principal.getRoles());
    }

    public static Principal getPrincipal() {
    	return getSoffidPrincipal();
    }

    public static SoffidPrincipal getSoffidPrincipal() {
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
            return new SoffidPrincipalImpl(Security.getMasterTenantName()+"\\"+host, 
            		null, null, null,
            		Collections.singletonList(AUTO_AUTHORIZATION_ALL), null, null );
        } else {
       		return null;
        }
    }

    private static TenantService tenantService = null;
    private static TenantService getTenantService ()
    {
    	if (onSyncProxy)
    		return null;
    	
    	if (tenantService == null)
    	{
       		tenantService = ServiceLocator.instance().getTenantService ();
    	}
    	return tenantService;
    }
    
    private static Collection<SoffidAuthorization> auths = null;
    private static void internalNestedLogin(String tenant, String user, String roles[])  {
    	SoffidPrincipal p;
    	try {
    		if (getTenantService() == null) // For proxy servers
    		{
		        p = new SoffidPrincipalImpl(tenant+"\\"+user, null, null, null, Arrays.asList(roles), null, null);
    		} else {
    			SoffidPrincipal currentPrincipal = Security.getSoffidPrincipal();
    			if (debugNestedLogin()) {
    				log.info("Nested login from: "+(currentPrincipal == null? "NONE": currentPrincipal.getName()));
    				log.info("Nested login to:   "+tenant+"\\"+user);
    				dumpStack();
    			}
				Long tenantId = getTenantId(tenant);
				if ( tenantId == null)
					throw new RuntimeException("Invalid tenant: "+tenant);
				Tenant t = new Tenant();
				t.setId(tenantId);
				t.setName(tenant);
				t.setDescription(tenant);
				if (roles == Security.ALL_PERMISSIONS)
				{
					if (auths == null)
						auths = ServiceLocator.instance().getAuthorizationService().findAuthorizations(null, null, null);
					LinkedList<String> auths2 = new LinkedList<String>();
					if ( ! onSyncServer ) {
						List<String> dp = onSyncServer ? 
								new LinkedList<String>() :
									getTenantService().getDisabledPermissions(t);
						for (SoffidAuthorization a: auths)
						{
							if (!dp.contains(a))
							{
								auths2.add(a.getCodi());
								auths2.add(a.getCodi()+Security.AUTO_ALL);
							}
						}
						if ( !dp.contains (AUTO_METADATA_UPDATE_ALL))
							auths2.add(AUTO_METADATA_UPDATE_ALL);
					}
			        p = new SoffidPrincipalImpl(tenant+"\\"+user, auths2, currentPrincipal);
				}
				else
				{
					List<String> rolesList = new LinkedList<String>();
					
					for ( String role: roles) rolesList.add(role);
					
					for ( String tp: getTenantService().getDisabledPermissions(t))
					{
						for ( Iterator<String> it = rolesList.iterator(); it.hasNext();)
						{
							String role = it.next();
							if (role.startsWith(tp))
							{
								it.remove();
							}
						}
					}
			        p = new SoffidPrincipalImpl(tenant+"\\"+user, rolesList, currentPrincipal);
				}
    		}
		} catch (InternalErrorException e) {
			throw new RuntimeException(e);
		}
        getIdentities().push(p);
    }

	public static void dumpStack() {
		StackTraceElement[] st = Thread.currentThread().getStackTrace();
		for (int i = 2; i <= 5 && i < st.length; i++) {
			log.info("Stack trace: "+ st[i].toString());
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
    	assertCanSetTenant(tenant);
    	assertCanSetIdentity();
        internalNestedLogin(tenant, user, roles);
    }

    public static void nestedLogin(SoffidPrincipal principal)  {
        int i = principal.getName().indexOf('\\');
        if ( i >= 0)
        	assertCanSetTenant(principal.getName().substring(0, i));
    	assertCanSetIdentity();
    	
		if (debugNestedLogin()) {
			SoffidPrincipal soffidPrincipal = Security.getSoffidPrincipal();
			log.info("Nested login from: "+(soffidPrincipal == null ? "NONE": soffidPrincipal.getName()));
			log.info("Nested login to:   "+principal.getName());
			dumpStack();
		}
    	getIdentities().push((SoffidPrincipal) principal);
    }


    public static void nestedLogin( String roles[])  {
        nestedLogin (getSoffidPrincipal().getName(), roles);
    }

    public static void clearNestedLogins() {
        getIdentities().clear();
    }

    public static void nestedLogoff() {
    	SoffidPrincipal soffidPrincipal = getIdentities().pop();
		if (debugNestedLogin()) {
	    	SoffidPrincipal newPrincipal = Security.getSoffidPrincipal();
			log.info("Nested logoff from: "+(soffidPrincipal == null ? "NONE": soffidPrincipal.getName()));
			log.info("Nested logoff to:   "+(newPrincipal == null ? "NONE": newPrincipal.getName()));
			dumpStack();
		}
    }

    public static void onSyncServer() {
    	if (System.getSecurityManager() != null)
    		AccessController.checkPermission(new NestedLoginPermission("tenant"));
        onSyncServer = true;
    }

    public static void onSyncProxy() {
    	if (System.getSecurityManager() != null)
    		AccessController.checkPermission(new NestedLoginPermission("tenant"));
        onSyncServer = true;
        onSyncProxy = true;
    }

    public static String getCurrentAccount ()
    {
    	Principal p = getSoffidPrincipal();
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
    	SoffidPrincipal p = getSoffidPrincipal();
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
    	SoffidPrincipal p;
    	if (! getIdentities().isEmpty())
    	{
    		p = getIdentities().peek();
    	}
    	else if (onSyncServer)
    		return null;
    	else
    	{
    		p = getSoffidPrincipal();
    	}
    	
		if (p == null)
			return null;
		return p.getUserName();
    }
    
    static HashMap<String, Long> tenants = new HashMap<String,Long>();
    private static Long getTenantId (String tenantName) throws InternalErrorException {
		if (onSyncProxy)
			throw new InternalErrorException("Tenant service is not available in proxy servers");

		Long id = tenants.get(tenantName);
    	if (id == null)
    	{
    		
    		Tenant tenant = tenantName == null ? 
    			getTenantService().getMasterTenant() :
    			getTenantService().getTenant(tenantName);
    		if (tenant != null && tenant.isEnabled())
    		{
    			id = tenant.getId();
    			tenants.put(tenantName, id);
    		}
    	}
    	return id;
    }
    
    static HashMap<Long, String> tenantNames = new HashMap<Long,String>();
    public static String getTenantName (Long tenantId) throws InternalErrorException {
		if (onSyncProxy)
			throw new InternalErrorException("Tenant service is not available in proxy servers");

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
	
	public static String getMasterTenantName () {
		return TenantServiceImpl.MASTER_NAME;
	}
	
	static ThreadLocal<String> clientIp = new ThreadLocal<String>();
	public static void setClientIp (String ip)
	{
		clientIp.set(ip);
	}

	public static String getClientIp ()
	{
		return clientIp.get();
	}
	
	public static void setClientRequest (HttpServletRequest req)
	{
		String ip = req.getRemoteAddr();
		String trustedProxies;
		try {
			if ( Config.getConfig().isAgent()) 
				trustedProxies = System.getProperty("soffid.proxy.trustedIps");
			else
				trustedProxies = ConfigurationCache.getMasterProperty("soffid.proxy.trustedIps");
		} catch (IOException e1) {
			trustedProxies = null;
		}
		String forwardedFor = req.getHeader("x-forwarded-for");
		if (trustedProxies != null)
		{
			String[] tp = trustedProxies.split("[, ]+");
			if ( isTrusted (ip, tp) )
			{
				if (forwardedFor == null || forwardedFor.trim().isEmpty())
				{
					try {
						ip = InetAddress.getLocalHost().getHostAddress();
					} catch (UnknownHostException e) {
						ip = "127.0.0.1" ;
					}
				} else {
					String[] ff = forwardedFor.split("[ ,]+");
					for (int i = ff.length - 1; i >= 0; i--)
					{
						ip = ff[i];
						if ( ! isTrusted(ip, tp))
							break;
					}
				}
			}
		}
		setClientIp(ip);
	}

	private static boolean isTrusted(String ip, String[] tp) {
		for (String t: tp)
			if (ip.equalsIgnoreCase(t))
				return true;
			else if (t.contains("*"))
			{
				String t2 = t.replaceAll("\\*", "[0-9]*");
				if ( Pattern.matches("^"+t2+"$", ip))
					return true;
			}
		return false;
	}
	
	static boolean debugNestedLogin() {
		return "true".equals(System.getProperty("soffid.nestedLogin.debug"));
	}
}

class Identity {
    Principal principal;
    String roles[];
    boolean transactionInitiated;
    UserTransaction transaction;
}
