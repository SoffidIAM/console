package es.caib.seycon.ng.utils;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserAccount;
import com.soffid.iam.service.AccountService;
import com.soffid.iam.service.InternalPasswordService;
import com.soffid.iam.service.UserService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class AutoritzacionsUsuari
{

	public static User getCurrentUsuari() throws InternalErrorException {
		InternalPasswordService ips = ServiceLocator.instance()
						.getInternalPasswordService();
		AccountService as = ServiceLocator.instance().getAccountService();
		UserService us = ServiceLocator.instance().getUserService();
		String dispatcher = ips.getDefaultDispatcher();
		Account caller = as.findAccount(Security.getPrincipal().getName(), dispatcher);
		if (caller == null)
			return null;
		if (caller instanceof UserAccount)
		{
			String codi = ((UserAccount) caller).getUser();
			return us.findUserByUserName(codi);
		}
		return null;
	}

	/*
	 * A NIVELL D'USUARI
	 */

	public static boolean hasCreateUser ()
	{
		return Security.isUserInRole(Security.AUTO_USER_CREATE);
	}


	public static boolean hasUpdateUser ()
	{
		return Security.isUserInRole(Security.AUTO_USER_UPDATE);
	}

	public static boolean hasUpdateCustomUser ()
	{
		return Security.isUserInRole(Security.AUTO_USER_UPDATE_CUSTOM);
	}

	public static boolean hasUpdateUserPassword ()
	{
		return Security.isUserInRole(Security.AUTO_USER_UPDATE_PASSWORD);
	}

	public static boolean hasQueryUser ()
	{
		return Security.isUserInRole(Security.AUTO_USER_QUERY);
	}

	public static boolean hasRefreshUser ()
	{
		return Security.isUserInRole(Security.AUTO_USER_PROPAGATE);
	}

	public static boolean hasQueryUserRole ()
	{
		return Security.isUserInRole(Security.AUTO_USER_ROLE_QUERY);
	}

	public static boolean canQueryAllUserRole ()
	{
		return Security.isUserInRole(Security.AUTO_USER_ROLE_QUERY + Security.AUTO_ALL);
	}

	public static boolean hasQueryUserSession ()
	{
		// user:session:query [GRUPS]
		return Security.isUserInRole(Security.AUTO_USER_SESSION_QUERY);
	}

	public static boolean canQueryAllUserSession ()
	{
		// user:session:query [GRUPS]
		return Security.isUserInRole(Security.AUTO_USER_SESSION_QUERY
						+ Security.AUTO_ALL);
	}

	/*
	 * public static boolean canCreateUserMetadata() { return
	 * Security.isUserInRole(Security.AUTO_USER_METADATA_CREATE); }
	 */
	public static boolean hasUpdateUserMetadata ()
	{
		// user:metadata:update [GRUPS]
		return Security.isUserInRole(Security.AUTO_USER_METADATA_UPDATE);
	}

	public static boolean canUpdateAllUserMetadata ()
	{
		// user:metadata:update [GRUPS]
		return Security.isUserInRole(Security.AUTO_USER_METADATA_UPDATE
						+ Security.AUTO_ALL);
	}

	public static boolean hasCreateUserRole ()
	{
		return Security.isUserInRole(Security.AUTO_USER_ROLE_CREATE);
	}

	public static boolean hasDeleteUserRole ()
	{
		return Security.isUserInRole(Security.AUTO_USER_ROLE_DELETE);
	}

	public static boolean hasCreateUserGroup ()
	{
		// user:group:create [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_USER_GROUP_CREATE);
	}

	public static boolean canCreateAllUserGroup ()
	{
		// user:group:create [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_USER_GROUP_CREATE + Security.AUTO_ALL);
	}

	public static boolean hasDeleteUserGroup ()
	{
		// user:group:delete [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_USER_GROUP_DELETE);
	}

	public static boolean canDeleteAllUserGroup ()
	{
		// user:group:delete [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_USER_GROUP_DELETE + Security.AUTO_ALL);
	}

	public static boolean hasCreateUserPrinter ()
	{
		// user:printer:create [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_USER_PRINTER_CREATE)
						|| Security.isUserInRole(Security.AUTO_USER_ACL_PRINTER_CREATE);
	}

	public static boolean canCreateAllUserPrinter ()
	{
		// user:printer:create [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_USER_PRINTER_CREATE
						+ Security.AUTO_ALL);
	}

	// A nivell de ZUL (si ha d'apareixer el butó)
	public static boolean hasDeleteUserPrinter ()
	{ // Totes les impressores o
	  // les que en té ACL
		return Security.isUserInRole(Security.AUTO_USER_PRINTER_DELETE)
						|| Security.isUserInRole(Security.AUTO_USER_ACL_PRINTER_DELETE);
	}

	public static boolean canDeleteAllUserPrinter ()
	{
		// user:printer:delete [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_USER_PRINTER_DELETE
						+ Security.AUTO_ALL);
	}


	public static boolean hasQueryUserAccessRegister ()
	{
		// user:accessRegister:query [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_USER_ACCESSREGISTER_QUERY);
	}

	public static boolean hasQueryUserMazinger ()
	{
		// user:mazinger:query [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_USER_MAZINGER_QUERY);
	}

	public static boolean canQueryAllUserAccessRegister ()
	{
		// user:accessRegister:query [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_USER_ACCESSREGISTER_QUERY
						+ Security.AUTO_ALL);
	}

	/*
	 * A NIVELL DE GRUPs
	 */
	public static boolean hasCreateGroup ()
	{
		return Security.isUserInRole(Security.AUTO_GROUP_CREATE);
	}

	public static boolean hasUpdateGroup ()
	{
		return Security.isUserInRole(Security.AUTO_GROUP_UPDATE);
	}

	public static boolean hasQueryGroup ()
	{
		return Security.isUserInRole(Security.AUTO_GROUP_QUERY);
	}

	public static boolean canQueryAllGroups ()
	{
		return Security.isUserInRole(Security.AUTO_GROUP_QUERY + Security.AUTO_ALL);
	}

	public static boolean hasQueryGroupRoles ()
	{
		return Security.isUserInRole(Security.AUTO_GROUP_ROLE_QUERY);
	}

	public static boolean canQueryAllGroupRoles ()
	{
		return Security.isUserInRole(Security.AUTO_GROUP_ROLE_QUERY + Security.AUTO_ALL);
	}

	public static boolean hasQueryGroupUsers ()
	{
		return Security.isUserInRole(Security.AUTO_GROUP_USER_QUERY);
	}

	public static boolean canQueryAllGroupUsers ()
	{
		return Security.isUserInRole(Security.AUTO_GROUP_USER_QUERY + Security.AUTO_ALL);
	}

	public static boolean hasCreateGroupPrinter ()
	{
		// group:printer:create [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_GROUP_PRINTER_CREATE);
	}

	public static boolean canCreateAllGroupPrinter ()
	{
		// group:printer:create [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_GROUP_PRINTER_CREATE
						+ Security.AUTO_ALL);
	}

	public static boolean hasDeleteGroupPrinter ()
	{
		// group:printer:delete [GRUPS]
		return Security.isUserInRole(Security.AUTO_GROUP_PRINTER_DELETE);
	}

	public static boolean canDeleteAllGroupPrinter ()
	{
		return Security.isUserInRole(Security.AUTO_GROUP_PRINTER_DELETE
						+ Security.AUTO_ALL);
	}

	/*
	 * A NIVELL DE TIPUS D'UNITAT ORGANITZATIVA
	 */
	public static boolean hasCreateOrganizationalUnit ()
	{
		return Security.isUserInRole(Security.AUTO_ORGANIZATIONALUNIT_CREATE);
	}

	public static boolean hasUpdateOrganizationalUnit ()
	{
		return Security.isUserInRole(Security.AUTO_ORGANIZATIONALUNIT_UPDATE);
	}

	public static boolean hasDeleteOrganizationalUnit ()
	{
		return Security.isUserInRole(Security.AUTO_ORGANIZATIONALUNIT_DELETE);
	}

	public static boolean hasQueryOrganizationalUnit ()
	{
		return Security.isUserInRole(Security.AUTO_ORGANIZATIONALUNIT_QUERY);
	}

	/*
	 * A NIVELL DE MÀQUINES
	 */
	public static boolean hasCreateAllHost ()
	{
		return Security.isUserInRole(Security.AUTO_HOST_ALL_CREATE);
	}

	public static boolean hasUpdateAllHost ()
	{
		return Security.isUserInRole(Security.AUTO_HOST_ALL_UPDATE);
	}

	// Des del zul no és permés eliminar màquines
	public static boolean hasDeleteAllHost ()
	{
		return Security.isUserInRole(Security.AUTO_HOST_ALL_DELETE);
	}

	public static boolean hasQueryHost ()
	{
		return Security.isUserInRole(Security.AUTO_HOST_QUERY);
	}

	public static boolean hasQueryAllHost ()
	{
		return Security.isUserInRole(Security.AUTO_HOST_ALL_QUERY);
	}

	public static boolean hasUpdateHostOS ()
	{
		return Security.isUserInRole(Security.AUTO_HOST_UPDATE_OS);
	}

	public static boolean canUpdateHostOS ()
	{
		// host:os:update [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_HOST_UPDATE_OS + Security.AUTO_ALL);
	}

	public static boolean hasQueryHostAdmin ()
	{
		return Security.isUserInRole(Security.AUTO_HOST_QUERY_ADMINISTRATOR_ACCESS);
	}

	public static boolean hasSupportHost_VNC ()
	{
		return Security.isUserInRole(Security.AUTO_HOST_ALL_SUPPORT_VNC);
	}

	/*
	 * A NIVELL DE XARXES
	 */
	public static boolean hasCreateNetwork ()
	{
		return Security.isUserInRole(Security.AUTO_NETWORK_ALL_CREATE);
	}

	public static boolean hasUpdateAllNetwork ()
	{
		return Security.isUserInRole(Security.AUTO_NETWORK_ALL_UPDATE);
	}

	public static boolean hasDeleteAllNetwork ()
	{
		return Security.isUserInRole(Security.AUTO_NETWORK_ALL_DELETE);
	}

	public static boolean hasQueryAllNetwork ()
	{
		return Security.isUserInRole(Security.AUTO_NETWORK_ALL_QUERY);
	}

	/*
	 * A NIVELL D'IMPRESSORES
	 */
	public static boolean hasCreatePrinter ()
	{// crear impressores
		return Security.isUserInRole(Security.AUTO_PRINTER_CREATE);
	}

	public static boolean hasUpdatePrinter ()
	{
		return Security.isUserInRole(Security.AUTO_PRINTER_UPDATE);
	}

	public static boolean hasDeletePrinter ()
	{
		return Security.isUserInRole(Security.AUTO_PRINTER_DELETE);
	}

	// Totes les impressores
	public static boolean hasQueryAllPrinter ()
	{
		return Security.isUserInRole(Security.AUTO_PRINTER_QUERY);
	}

	// Les impresores que pertanyen a màquinas on l'usuari te ACL
	public static boolean hasQueryACLPrinter ()
	{
		return Security.isUserInRole(Security.AUTO_PRINTER_ACL_QUERY);
	}

	/*
	 * A NIVELL D'APLICACIONS
	 */
	public static boolean hasCreateAplicacio ()
	{
		return Security.isUserInRole(Security.AUTO_APPLICATION_CREATE);
	}

	public static boolean hasUpdateAplicacio ()
	{
		return Security.isUserInRole(Security.AUTO_APPLICATION_UPDATE);
	}

	public static boolean hasDeleteAplicacio ()
	{
		return Security.isUserInRole(Security.AUTO_APPLICATION_DELETE);
	}

	public static boolean hasQueryAplicacio ()
	{
		return Security.isUserInRole(Security.AUTO_APPLICATION_QUERY);
	}

	/*
	 * A NIVELL DE REGISTRES D'ACCÉS
	 */
	public static boolean hasQueryRegistresAcces ()
	{
		return Security.isUserInRole(Security.AUTO_ACCESSREGISTER_QUERY);
	}

	/*
	 * A NIVELL DE DADES ADDICIONALS
	 */
	public static boolean hasCreateMetadata ()
	{
		return Security.isUserInRole(Security.AUTO_METADATA_CREATE);
	}

	public static boolean hasUpdateMetadata ()
	{
		return Security.isUserInRole(Security.AUTO_METADATA_UPDATE);
	}

	public static boolean hasDeleteMetadata ()
	{
		return Security.isUserInRole(Security.AUTO_METADATA_DELETE);
	}

	public static boolean hasQueryMetadata ()
	{
		return Security.isUserInRole(Security.AUTO_METADATA_QUERY);
	}

	/*
	 * A NIVELL DE SERVEIS
	 */
	public static boolean hasCreateServeis ()
	{
		return Security.isUserInRole(Security.AUTO_SERVICE_CREATE);
	}

	public static boolean hasUpdateServeis ()
	{
		return Security.isUserInRole(Security.AUTO_SERVICE_UPDATE);
	}

	public static boolean hasDeleteServeis ()
	{
		return Security.isUserInRole(Security.AUTO_SERVICE_DELETE);
	}

	public static boolean hasQueryServeis ()
	{
		return Security.isUserInRole(Security.AUTO_SERVICE_QUERY);
	}

	/*
	 * A NIVELL DE DOMINIS I LLISTES DE CORREU
	 */
	public static boolean hasCreateMail ()
	{
		return Security.isUserInRole(Security.AUTO_MAIL_CREATE);
	}

	public static boolean hasUpdateMail ()
	{
		return Security.isUserInRole(Security.AUTO_MAIL_UPDATE);
	}

	public static boolean hasDeleteMail ()
	{
		return Security.isUserInRole(Security.AUTO_MAIL_DELETE);
	}

	public static boolean hasQueryMail ()
	{
		return Security.isUserInRole(Security.AUTO_MAIL_QUERY);
	}

	/*
	 * A NIVELL DE LOPD
	 */
	public static boolean hasCreateLopd ()
	{
		return Security.isUserInRole(Security.AUTO_LOPD_CREATE);
	}

	public static boolean canCreateLopd ()
	{
		return Security.isUserInRole(Security.AUTO_LOPD_CREATE + Security.AUTO_ALL);
	}

	public static boolean hasUpdateLopd ()
	{
		return Security.isUserInRole(Security.AUTO_LOPD_UPDATE);
	}

	public static boolean canUpdateLopd ()
	{
		return Security.isUserInRole(Security.AUTO_LOPD_UPDATE + Security.AUTO_ALL);
	}

	public static boolean hasDeleteLopd ()
	{
		return Security.isUserInRole(Security.AUTO_LOPD_DELETE);
	}

	public static boolean canDeleteLopd ()
	{
		return Security.isUserInRole(Security.AUTO_LOPD_DELETE + Security.AUTO_ALL);
	}

	public static boolean hasQueryLopd ()
	{
		return Security.isUserInRole(Security.AUTO_LOPD_QUERY);
	}

	public static boolean canQueryLopd ()
	{
		return Security.isUserInRole(Security.AUTO_LOPD_QUERY + Security.AUTO_ALL);
	}

	/*
	 * A NIVELL DE PARÀMETRES
	 */
	public static boolean hasCreateParameter ()
	{
		return Security.isUserInRole(Security.AUTO_PARAMETER_CREATE);
	}

	public static boolean hasUpdateParameter ()
	{
		return Security.isUserInRole(Security.AUTO_PARAMETER_UPDATE);
	}

	public static boolean hasDeleteParameter ()
	{
		return Security.isUserInRole(Security.AUTO_PARAMETER_DELETE);
	}

	public static boolean hasQueryParameter ()
	{
		return Security.isUserInRole(Security.AUTO_PARAMETER_QUERY);
	}

	/*
	 * A NIVELL D'AGENTS
	 */
	public static boolean hasCreateAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_CREATE);
	}

	public static boolean hasUpdateAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_UPDATE);
	}

	public static boolean hasDeleteAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_DELETE);
	}

	public static boolean hasQueryAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_QUERY);
	}

	public static boolean hasPropagateAgentUsers ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_PROPAGATE_USERS);
	}

	public static boolean hasPropagateAgentRoles ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_PROPAGATE_ROLES);
	}

	public static boolean hasPropagateAgentGroups ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_PROPAGATE_GROUPS);
	}

	public static boolean hasCreateAccessControlAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_ACCESSCONTROL_CREATE);
	}

	public static boolean hasUpdateAccessControlAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_ACCESSCONTROL_UPDATE);
	}

	public static boolean hasDeleteAccessControlAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_ACCESSCONTROL_DELETE);
	}

	public static boolean hasQueryAccessControlAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_ACCESSCONTROL_QUERY);
	}

	public static boolean hasSetAccessControlAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_ACCESSCONTROL_SET);
	}

	public static boolean canCreateAccessControlAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_ACCESSCONTROL_CREATE
						+ Security.AUTO_ALL);
	}

	public static boolean canUpdateAccessControlAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_ACCESSCONTROL_UPDATE
						+ Security.AUTO_ALL);
	}

	public static boolean canDeleteAccessControlAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_ACCESSCONTROL_DELETE
						+ Security.AUTO_ALL);
	}

	public static boolean canQueryAccessControlAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_ACCESSCONTROL_QUERY
						+ Security.AUTO_ALL);
	}

	public static boolean canManageServers ()
	{
		return Security.isUserInRole(Security.AUTO_SERVER_MANAGE);
	}

	/*
	 * A NIVELL D'AUDITORIA
	 */
	public static boolean hasQueryAuditoria ()
	{
		return Security.isUserInRole(Security.AUTO_AUDIT_QUERY);
	}

	public static boolean hasQueryCustomAuditoria ()
	{
		return Security.isUserInRole(Security.AUTO_AUDIT_CUSTOM_QUERY);
	}

	public static boolean canQueryCustomAuditoria ()
	{
		return Security.isUserInRole(Security.AUTO_AUDIT_CUSTOM_QUERY
						+ Security.AUTO_ALL);
	}

	/*
	 * A NIVELL DE MENUS DE LA INTRANET
	 */
	public static boolean hasQueryAllMenusIntranet ()
	{
		return Security.isUserInRole(Security.AUTO_INTRANETMENUS_ALL_QUERY);
	}

	public static boolean hasAdminMenusIntranet ()
	{
		return Security.isUserInRole(Security.AUTO_INTRANETMENUS_ADMIN);
	}

	public static boolean canAdminMenusIntranet ()
	{
		return Security.isUserInRole(Security.AUTO_INTRANETMENUS_ADMIN
						+ Security.AUTO_ALL);
	}

	public static boolean canQueryAllMenusIntranet ()
	{
		return Security.isUserInRole(Security.AUTO_INTRANETMENUS_ALL_QUERY
						+ Security.AUTO_ALL);
	}

	/*
	 * A NIVELL DE SEYCON-BASE
	 */
	/*
	 * public static boolean hasUpdateBase() { return
	 * Security.isUserInRole(Security.AUTO_BASE_UPDATE); } public static boolean
	 * hasRestartBase() { return Security.isUserInRole(Security.AUTO_BASE_RESTART); }
	 * public static boolean hasQueryBase() { return
	 * Security.isUserInRole(Security.AUTO_BASE_QUERY); }
	 */

	public static boolean hasQueryServerListBase ()
	{
		return Security.isUserInRole(Security.AUTO_MONITOR_SERVER_LIST);
	}

	public static boolean hasQueryAgentListBase ()
	{
		return Security.isUserInRole(Security.AUTO_MONITOR_AGENT_LIST);
	}

	public static boolean hasRestartAgentBase ()
	{
		return Security.isUserInRole(Security.AUTO_MONITOR_AGENT_RESTART);
	}

	public static boolean hasQueryLogBase ()
	{
		return Security.isUserInRole(Security.AUTO_BASE_LOG_QUERY);
	}

	public static boolean hasUpdatePlugins ()
	{
		return Security.isUserInRole(Security.AUTO_PLUGINS_UPDATE);
	}

	public static boolean hasQueryPlugins ()
	{
		return Security.isUserInRole(Security.AUTO_PLUGINS_QUERY);
	}

	/*
	 * A NIVELL D'AUTORITZACIONS
	 */
	public static boolean hasCreateAuthorizationRol ()
	{
		return Security.isUserInRole(Security.AUTO_AUTHORIZATION_ROL_CREATE);
	}

	public static boolean hasDeleteAuthorizationRol ()
	{
		return Security.isUserInRole(Security.AUTO_AUTHORIZATION_ROL_DELETE);
	}

	public static boolean hasQueryAuthorization ()
	{
		return Security.isUserInRole(Security.AUTO_AUTHORIZATION_QUERY);
	}

	/*
	 * A NIVELL D'USUARIS DE TIPUS ALUMNE
	 */
	public static boolean hasCreatePupil ()
	{
		return Security.isUserInRole(Security.AUTO_PUPIL_CREATE);
	}

	/*
	 * A NIVELL DE WORKFLOWS
	 */
	public static boolean canAdminWorkflows ()
	{
		return Security.isUserInRole(Security.AUTO_WORKFLOW_ADMIN + Security.AUTO_ALL);
	}

	/*
	 * A NIVELL D'INTERFICIE DEL SEU
	 */
	public static boolean hasViewAgentsSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_AGENTS);
	}

	public static boolean hasViewAplicacionsSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_APLICACIONS);
	}

	public static boolean hasViewAuditoriaSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_AUDITORIA);
	}

	public static boolean hasViewAutoritzacionsSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_AUTORITZACIONS);
	}

	public static boolean hasViewCorreuSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_CORREU);
	}

	public static boolean hasViewDominisCorreuSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_DOMINISCORREU);
	}

	public static boolean hasViewDadesAddicionalsSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_DADESADDICIONALS);
	}

	public static boolean hasViewGrupsSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_GRUPS);
	}

	public static boolean hasViewImpressoresSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_IMPRESSORES);
	}

	public static boolean hasViewLopdSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_LOPD);
	}

	public static boolean hasViewParametresSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_PARAMETRES);
	}

	public static boolean hasViewPluginsSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_PARAMETRES);
	}

	public static boolean hasViewRegistreAccesSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_REGISTREACCES);
	}

	public static boolean hasViewServeisSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_SERVEIS);
	}

	public static boolean hasViewTipusUOSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_TIPUSUO);
	}

	public static boolean hasViewUsuarisSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_USUARIS);
	}

	public static boolean hasViewMenusIntranetSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_MENUSINTRANET);
	}

	public static boolean hasViewSeyconServerSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_SEYCONSERVER);
	}

	public static boolean hasViewFederacioIdentitatsSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_FEDERACIOIDENTITATS);
	}

	public static boolean hasViewDominiUsuaris ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_USERS_DOMAIN);
	}

	/*
	 * MÈTODES LOCALS D'ALTRES SERVICES (per centralitzar l'ús)
	 */

	//
	// A NIVELL D'APLICACIONS
	//

	public static boolean canQueryAllAplication ()
	{
		return Security.isUserInRole(Security.AUTO_APPLICATION_QUERY + Security.AUTO_ALL);
	}

	// A NIVELL DE XARXES
	//

	public static boolean canCreateAllNetworks ()
	{
		// network:all:create [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_NETWORK_ALL_CREATE
						+ Security.AUTO_ALL);
	}

	public static boolean canUpdateAllNetworks ()
	{
		// network:all:update [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_NETWORK_ALL_UPDATE
						+ Security.AUTO_ALL);
	}

	public static boolean canDeleteAllNetworks ()
	{
		// network:all:delete [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_NETWORK_ALL_DELETE
						+ Security.AUTO_ALL);
	}

	public static boolean canQueryAllNetworks ()
	{
		// network:all:query [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_NETWORK_ALL_QUERY + Security.AUTO_ALL);
	}

	public static boolean canCreateAllHosts ()
	{
		// host:all:create [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_HOST_ALL_CREATE + Security.AUTO_ALL);
	}

	public static boolean canUpdateAllHosts ()
	{
		// host:all:update [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_HOST_ALL_UPDATE + Security.AUTO_ALL);
	}

	public static boolean canQueryAllHosts ()
	{
		// host:query [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_HOST_ALL_QUERY + Security.AUTO_ALL);
	}

	public static boolean canDeleteAllHosts ()
	{
		// host:delete [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_HOST_ALL_DELETE + Security.AUTO_ALL);
	}

	public static boolean canSupportAllNetworks_VNC ()
	{
		// host:support [SENSE_DOMINI]
		// NOTA: S'ha de comprovar a nivel d'ACLs quan corresponga
		return Security.isUserInRole(Security.AUTO_HOST_ALL_SUPPORT_VNC
						+ Security.AUTO_ALL);
	}

	// A NIVELL DE FEDERACIÓ D'IDENTITATS

	public static boolean canQueryAllIdentityFederation ()
	{
		// federacioIdentitats:query [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_IDENTITY_FEDERATION_QUERY
						+ Security.AUTO_ALL);
	}

	public static boolean canDeleteAllIdentityFederation ()
	{
		// federacioIdentitats:delete [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_IDENTITY_FEDERATION_DELETE
						+ Security.AUTO_ALL);
	}

	public static boolean canCreateAllIdentityFederation ()
	{
		// federacioIdentitats:create [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_IDENTITY_FEDERATION_CREATE
						+ Security.AUTO_ALL);
	}

	public static boolean canUpdateAllIdentityFederation ()
	{
		// federacioIdentitats:upadate [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_IDENTITY_FEDERATION_UPDATE
						+ Security.AUTO_ALL);
	}

	/*
	 * A NIVELL DE DOMINIS D'USUARIS
	 */
	public static boolean hasCreateDominisUsuari ()
	{
		return Security.isUserInRole(Security.AUTO_USERS_DOMAIN_CREATE);
	}

	public static boolean hasUpdateDominisUsuari ()
	{
		return Security.isUserInRole(Security.AUTO_USERS_DOMAIN_UPDATE);
	}

	public static boolean hasDeleteDominisUsuari ()
	{
		return Security.isUserInRole(Security.AUTO_USERS_DOMAIN_DELETE);
	}

	public static boolean hasQueryDominisUsuari ()
	{
		return Security.isUserInRole(Security.AUTO_USERS_DOMAIN_QUERY);
	}

	/*
	 * Accounts level
	 */
	public static boolean hasCreateAccount ()
	{
		return Security.isUserInRole(Security.AUTO_ACCOUNT_CREATE);
	}

	public static boolean hasUpdateAccount ()
	{
		return Security.isUserInRole(Security.AUTO_ACCOUNT_UPDATE);
	}

	public static boolean hasDeleteAccount ()
	{
		return Security.isUserInRole(Security.AUTO_ACCOUNT_DELETE);
	}

	public static boolean hasQueryAccount ()
	{
		return Security.isUserInRole(Security.AUTO_ACCOUNT_QUERY);
	}

	public static boolean hasViewAccountsSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_ACCOUNTS);
	}

	/**
	 * Method to check if user can create new OS types.
	 * 
	 * @return <ul>
	 *         <li>
	 * @code TRUE If user is authorized to create OS types. </li>
	 * 
	 *       <li>
	 * @code FALSE If user is not authorized to create OS types. </li>
	 *       </ul>
	 */
	public static boolean canCreateOSType ()
	{
		return Security.isUserInRole(Security.AUTO_OS_CREATE);
	}

	/**
	 * Method to check if user can delete exiting OS types.
	 * 
	 * @return <ul>
	 *         <li>
	 * @code TRUE If user is authorized to delete OS types. </li>
	 * 
	 *       <li>
	 * @code FALSE If user is not authorized to delete OS types. </li>
	 *       </ul>
	 */
	public static boolean canDeleteOSType ()
	{
		return Security.isUserInRole(Security.AUTO_OS_DELETE);
	}

	/**
	 * Method to check if user can update existing OS types.
	 * 
	 * @return <ul>
	 *         <li>
	 * @code TRUE If user is authorized to update OS types. </li>
	 * 
	 *       <li>
	 * @code FALSE If user is not authorized to update OS types. </li>
	 *       </ul>
	 */
	public static boolean canUpdateOSType ()
	{
		return Security.isUserInRole(Security.AUTO_OS_UPDATE);
	}

	/**
	 * Method to check if user can query existing OS types.
	 * 
	 * @return <ul>
	 *         <li>
	 * @code TRUE If user is authorized to query OS types. </li>
	 * 
	 *       <li>
	 * @code FALSE If user is not authorized to query OS types. </li>
	 *       </ul>
	 */
	public static boolean canQueryOSType ()
	{
		return Security.isUserInRole(Security.AUTO_OS_QUERY);
	}

	/**
	 * Method to check if user can create new retrieve password questions.
	 * 
	 * @return <ul>
	 *         <li>
	 * @code TRUE If user is authorized to create retrieve password questions. </li>
	 * 
	 *       <li>
	 * @code FALSE If user is not authorized to create retrieve password questions.
	 *       </li>
	 *       </ul>
	 */
	public static boolean canCreateRetrivePassword ()
	{
		return Security.isUserInRole(Security.AUTO_REMEMBER_PASSWORD_CREATE);
	}

	/**
	 * Method to check if user can delete exiting retrieve password questions..
	 * 
	 * @return <ul>
	 *         <li>
	 * @code TRUE If user is authorized to delete retrieve password questions. </li>
	 * 
	 *       <li>
	 * @code FALSE If user is not authorized to delete retrieve password questions.
	 *       </li>
	 *       </ul>
	 */
	public static boolean canDeleteRetrievePassword ()
	{
		return Security.isUserInRole(Security.AUTO_REMEMBER_PASSWORD_DELETE);
	}

	/**
	 * Method to check if user can update existing retrieve password questions.
	 * 
	 * @return <ul>
	 *         <li>
	 * @code TRUE If user is authorized to update retrieve password questions. </li>
	 * 
	 *       <li>
	 * @code FALSE If user is not authorized to update retrieve password questions.
	 *       </li>
	 *       </ul>
	 */
	public static boolean canUpdateRetrivePassword ()
	{
		return Security.isUserInRole(Security.AUTO_REMEMBER_PASSWORD_UPDATE);
	}

	/**
	 * Method to check if user can query existing retrieve password questions.
	 * 
	 * @return <ul>
	 *         <li>
	 * @code TRUE If user is authorized to query retrieve password questions. </li>
	 * 
	 *       <li>
	 * @code FALSE If user is not authorized to query retrieve password questions. 
	 *       </li>
	 *       </ul>
	 */
	public static boolean canQueryRetrieve ()
	{
		return Security.isUserInRole(Security.AUTO_REMEMBER_PASSWORD_QUERY);
	}

}

