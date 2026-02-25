//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
/**
 * Service ApplicationService
 */
public interface ApplicationService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.iga.service.ApplicationService";

	public final static String SERVICE_NAME = "com.soffid.iam.iga.service.ApplicationService";

	/**
	 * Operation findDomainValueByText

	 * @param domain 
	 * @param text 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> findDomainValueByText(
		final com.soffid.iam.iga.api.Domain domain, 
		final java.lang.String text)
			throws com.soffid.iam.exception.InternalErrorException, java.lang.Exception;

	/**
	 * Operation findDomainValueByTextAsync

	 * @param domain 
	 * @param text 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> findDomainValueByTextAsync(
		final com.soffid.iam.iga.api.Domain domain, 
		final java.lang.String text)
			throws com.soffid.iam.exception.InternalErrorException, java.lang.Exception;

	/**
	 * Operation findRoleNames

	 * @param systemName 
	 * @return 
	 */
	java.util.Collection<java.lang.String> findRoleNames(
		final java.lang.String systemName)
			throws com.soffid.iam.exception.InternalErrorException, java.lang.Exception;

	/**
	 * Operation removeRedundantRoles

	 * @param query 
	 * @return 
	 */
	com.soffid.iam.base.api.AsyncProcessTracker removeRedundantRoles(
		final java.lang.String query)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param aplicacio 
	 * @return 
	 */
	com.soffid.iam.iga.api.InformationSystem create(
		final com.soffid.iam.iga.api.InformationSystem aplicacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findApplicationByApplicationNameUnrestricted

	 * @param codiAplicacio 
	 * @return 
	 */
	com.soffid.iam.iga.api.InformationSystem findApplicationByApplicationNameUnrestricted(
		final java.lang.String codiAplicacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findApplicationByApplicationName

	 * @param codiAplicacio 
	 * @return 
	 */
	com.soffid.iam.iga.api.InformationSystem findApplicationByApplicationName(
		final java.lang.String codiAplicacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findApplicationById

	 * @param id 
	 * @return 
	 */
	com.soffid.iam.iga.api.InformationSystem findApplicationById(
		final java.lang.Long id)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation approveRoleDefinition
	 * Makes role dependencies persistent

	 * @param rol 
	 * @return 
	 */
	com.soffid.iam.iga.api.Role approveRoleDefinition(
		final com.soffid.iam.iga.api.Role rol)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create
	 * This method does NOT add grants made to the new role

	 * @param rol 
	 * @return 
	 */
	com.soffid.iam.iga.api.Role create(
		final com.soffid.iam.iga.api.Role rol)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create2
	 * This method does add grants made to the new role

	 * @param rol 
	 * @return 
	 */
	com.soffid.iam.iga.api.Role create2(
		final com.soffid.iam.iga.api.Role rol)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation denyRoleDefinition
	 * Makes role dependencies persistent

	 * @param rol 
	 * @return 
	 */
	com.soffid.iam.iga.api.Role denyRoleDefinition(
		final com.soffid.iam.iga.api.Role rol)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRoleById

	 * @param rolId 
	 * @return 
	 */
	com.soffid.iam.iga.api.Role findRoleById(
		final java.lang.Long rolId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRoleByRoleNameAndApplicationNameAndDispatcherName

	 * @param nomRol 
	 * @param codiAplicacio 
	 * @param codiDispatcher 
	 * @return 
	 */
	com.soffid.iam.iga.api.Role findRoleByRoleNameAndApplicationNameAndDispatcherName(
		final java.lang.String nomRol, 
		final java.lang.String codiAplicacio, 
		final java.lang.String codiDispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRoleByNameAndSystem

	 * @param name 
	 * @param system 
	 * @return 
	 */
	com.soffid.iam.iga.api.Role findRoleByNameAndSystem(
		final java.lang.String name, 
		final java.lang.String system)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRoleByShortName

	 * @param name 
	 * @return 
	 */
	com.soffid.iam.iga.api.Role findRoleByShortName(
		final java.lang.String name)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update
	 * Updates role, including grantee roles and groups, but not roles granted to this one

	 * @param rol 
	 * @return 
	 */
	com.soffid.iam.iga.api.Role update(
		final com.soffid.iam.iga.api.Role rol)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update2
	 * Updates role, including roles granted to this role, and role and group grantee

	 * @param rol 
	 * @return 
	 */
	com.soffid.iam.iga.api.Role update2(
		final com.soffid.iam.iga.api.Role rol)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param rolsUsuaris 
	 * @return 
	 */
	com.soffid.iam.iga.api.RoleAccount create(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation enableOrDisableOnDates
	 * Enables or disable a rolAccount based on the start and end dates

	 * @param rolsUsuaris 
	 * @return 
	 */
	com.soffid.iam.iga.api.RoleAccount enableOrDisableOnDates(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRoleAccountById

	 * @param id 
	 * @return 
	 */
	com.soffid.iam.iga.api.RoleAccount findRoleAccountById(
		final long id)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param rolsUsuaris 
	 * @return 
	 */
	com.soffid.iam.iga.api.RoleAccount update(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateAttributes

	 * @param rolsUsuaris 
	 * @return 
	 */
	com.soffid.iam.iga.api.RoleAccount updateAttributes(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param grant 
	 * @return 
	 */
	com.soffid.iam.iga.api.RoleGrant create(
		final com.soffid.iam.iga.api.RoleGrant grant)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param grant 
	 * @return 
	 */
	com.soffid.iam.iga.api.RoleGrant update(
		final com.soffid.iam.iga.api.RoleGrant grant)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findApplications

	 * @param query 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.InformationSystem> findApplications(
		final com.soffid.zkdb.api.Query query)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findDomainValues

	 * @param query 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> findDomainValues(
		final com.soffid.zkdb.api.Query query)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRedundantRoles

	 * @param query 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.RoleAccount> findRedundantRoles(
		final java.lang.String query)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRoleAccounts

	 * @param query 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.RoleAccount> findRoleAccounts(
		final com.soffid.zkdb.api.Query query)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRoles

	 * @param query 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Role> findRoles(
		final com.soffid.zkdb.api.Query query)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation generateChangesReport
	 * Generates a report to view the changes that a role change will generat

	 * @param rol 
	 * @return 
	 */
	java.lang.String generateChangesReport(
		final com.soffid.iam.iga.api.Role rol)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation generateChangesReport
	 * Generates a report to view the changes that a role change will generat

	 * @param rol 
	 * @param grantsToAdd 
	 * @param grantsToRemove 
	 * @return 
	 */
	java.lang.String generateChangesReport(
		final com.soffid.iam.iga.api.Role rol, 
		final java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToAdd, 
		final java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToRemove)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findNetworkACLRolesByRoleNameAndApplicationNameAndDispatcherName

	 * @param nomRole 
	 * @param codiAplicacioRol 
	 * @param codiDispatcher 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.NetworkAuthorization> findNetworkACLRolesByRoleNameAndApplicationNameAndDispatcherName(
		final java.lang.String nomRole, 
		final java.lang.String codiAplicacioRol, 
		final java.lang.String codiDispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findApplicationChildren

	 * @param applicationName 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.InformationSystem> findApplicationChildren(
		final java.lang.String applicationName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findApplicationManagementRoles
	 * Finds the managers with a set management role for an application

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Role> findApplicationManagementRoles()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findApplicationManagers
	 * Finds the management roles for any information system

	 * @param informationSystem 
	 * @param roleName 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findApplicationManagers(
		final java.lang.String informationSystem, 
		final java.lang.String roleName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRoleAuthorizationsByRoleNameAndApplicationNameAndDispatcherName

	 * @param nomRole 
	 * @param codiAplicacioRol 
	 * @param codiDispatcher 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> findRoleAuthorizationsByRoleNameAndApplicationNameAndDispatcherName(
		final java.lang.String nomRole, 
		final java.lang.String codiAplicacioRol, 
		final java.lang.String codiDispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findEffectiveRoleGrantByAccount

	 * @param accountId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findEffectiveRoleGrantByAccount(
		final long accountId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findEffectiveRoleGrantByUser

	 * @param userId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findEffectiveRoleGrantByUser(
		final long userId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findEffectiveRoleGrantByUserAndHolderGroup

	 * @param userId 
	 * @param groupId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findEffectiveRoleGrantByUserAndHolderGroup(
		final long userId, 
		final long groupId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findEffectiveRoleGrantsByRoleId

	 * @param rolId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findEffectiveRoleGrantsByRoleId(
		final java.lang.Long rolId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findEffectiveUserRolesByInformationSystem

	 * @param informationSystem 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findEffectiveUserRolesByInformationSystem(
		final java.lang.String informationSystem)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findGroupManagementRoles
	 * Finds the managers with a set management role for a group

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Role> findGroupManagementRoles()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findGroupManagers
	 * Finds the management roles for any group

	 * @param group 
	 * @param roleName 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findGroupManagers(
		final java.lang.String group, 
		final java.lang.String roleName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRoleHoldersGroupsByRole

	 * @param rol 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Group> findRoleHoldersGroupsByRole(
		final com.soffid.iam.iga.api.Role rol)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findApplicationAccessTreeRolesByRoleNameAndRoleApplicationNameAndDispatcherName

	 * @param nomRole 
	 * @param codiAplicacioRol 
	 * @param codiDispatcher 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> findApplicationAccessTreeRolesByRoleNameAndRoleApplicationNameAndDispatcherName(
		final java.lang.String nomRole, 
		final java.lang.String codiAplicacioRol, 
		final java.lang.String codiDispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRoleAccountByAccount

	 * @param accountId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findRoleAccountByAccount(
		final long accountId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRoleGrantByAccount

	 * @param accountId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findRoleGrantByAccount(
		final java.lang.Long accountId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRoleGrantByRole

	 * @param rolId 
	 * @param numRegistres 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findRoleGrantByRole(
		final java.lang.Long rolId, 
		final java.lang.Long numRegistres)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRoleAccountByAccountNoRule

	 * @param accountId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findRoleAccountByAccountNoRule(
		final long accountId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRoleGrantHierarchyByAccount

	 * @param accountId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleGrantHierarchy> findRoleGrantHierarchyByAccount(
		final long accountId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRoleGrantHierarchyByUser

	 * @param userId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleGrantHierarchy> findRoleGrantHierarchyByUser(
		final long userId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRoleGrantsByGroup

	 * @param grup 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findRoleGrantsByGroup(
		final com.soffid.iam.iga.api.Group grup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRolesByApplicationName

	 * @param codiAplicacio 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByApplicationName(
		final java.lang.String codiAplicacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRolesByApplicationNameUnrestricted

	 * @param codiAplicacio 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByApplicationNameUnrestricted(
		final java.lang.String codiAplicacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRolesByUserName

	 * @param codiUsuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByUserName(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRolesByDomainNameAndApplicationName

	 * @param nomDomini 
	 * @param codiAplicacio 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByDomainNameAndApplicationName(
		final java.lang.String nomDomini, 
		final java.lang.String codiAplicacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findGrantedRolesToGroupByGroup

	 * @param grup 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Role> findGrantedRolesToGroupByGroup(
		final com.soffid.iam.iga.api.Group grup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserRolesByRoleNameAndRoleApplicationNameAndDispatcherName

	 * @param nomRole 
	 * @param codiAplicacioRol 
	 * @param codiDispatcher 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByRoleNameAndRoleApplicationNameAndDispatcherName(
		final java.lang.String nomRole, 
		final java.lang.String codiAplicacioRol, 
		final java.lang.String codiDispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserRolesByUserName

	 * @param codiUsuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByUserName(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserRolesHistoryByUserName

	 * @param codiUsuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesHistoryByUserName(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserRolesByUserNameNoSoD

	 * @param codiUsuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByUserNameNoSoD(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserRolesByInformationSystem

	 * @param informationSystem 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByInformationSystem(
		final java.lang.String informationSystem)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserRolesByUserNameNoRules

	 * @param codiUsuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByUserNameNoRules(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUsersByRoleNameAndRoleApplicationNameAndDispatcherName

	 * @param nomRole 
	 * @param codiAplicacioRol 
	 * @param codiDispatcher 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.User> findUsersByRoleNameAndRoleApplicationNameAndDispatcherName(
		final java.lang.String nomRole, 
		final java.lang.String codiAplicacioRol, 
		final java.lang.String codiDispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getPendingAlerts

	 * @param codiAplicacio 
	 * @return 
	 */
	java.util.Collection<java.lang.Object> getPendingAlerts(
		final java.lang.String codiAplicacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getRoles

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Role> getRoles()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation approveDelete

	 * @param rolsUsuaris 
	 */
	void approveDelete(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param aplicacio 
	 */
	void delete(
		final com.soffid.iam.iga.api.InformationSystem aplicacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param rol 
	 */
	void delete(
		final com.soffid.iam.iga.api.Role rol)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param rolsUsuaris 
	 */
	void delete(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param grant 
	 */
	void delete(
		final com.soffid.iam.iga.api.RoleGrant grant)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation deleteByRuleEvaluation

	 * @param rolsUsuaris 
	 */
	void deleteByRuleEvaluation(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation removeSentAlerts

	 * @param codiAplicacio 
	 * @param dataDelete 
	 */
	void removeSentAlerts(
		final java.lang.String codiAplicacio, 
		final java.util.Date dataDelete)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation denyApproval

	 * @param rolsUsuaris 
	 */
	void denyApproval(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation denyDelete

	 * @param rolsUsuaris 
	 */
	void denyDelete(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation enableOrDisableAllOnDates
	 * Enables or disable any rolAccount based on the start and end dates

	 */
	void enableOrDisableAllOnDates()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation revokeRolesHoldedOnGroup

	 * @param userId 
	 * @param groupId 
	 */
	void revokeRolesHoldedOnGroup(
		final long userId, 
		final long groupId)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation synchronizeRole
	 * Synchronize any user with 

	 * @param rol 
	 */
	void synchronizeRole(
		final com.soffid.iam.iga.api.Role rol)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param aplicacio 
	 */
	void update(
		final com.soffid.iam.iga.api.InformationSystem aplicacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
