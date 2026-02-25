//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * EJB ApplicationService
 */
public interface ApplicationService

 {

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> findDomainValueByText(
		final com.soffid.iam.iga.api.Domain domain, 
		final java.lang.String text)
	throws com.soffid.iam.exception.InternalErrorException, java.lang.Exception;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> findDomainValueByTextAsync(
		final com.soffid.iam.iga.api.Domain domain, 
		final java.lang.String text)
	throws com.soffid.iam.exception.InternalErrorException, java.lang.Exception;

	com.soffid.iam.base.api.AsyncProcessTracker removeRedundantRoles(
		final java.lang.String query)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.InformationSystem create(
		final com.soffid.iam.iga.api.InformationSystem aplicacio)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.InformationSystem findApplicationByApplicationNameUnrestricted(
		final java.lang.String codiAplicacio)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.InformationSystem findApplicationByApplicationName(
		final java.lang.String codiAplicacio)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.InformationSystem findApplicationById(
		final java.lang.Long id)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.Role approveRoleDefinition(
		final com.soffid.iam.iga.api.Role rol)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.Role create(
		final com.soffid.iam.iga.api.Role rol)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.Role create2(
		final com.soffid.iam.iga.api.Role rol)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.Role denyRoleDefinition(
		final com.soffid.iam.iga.api.Role rol)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.Role findRoleById(
		final java.lang.Long rolId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.Role findRoleByRoleNameAndApplicationNameAndDispatcherName(
		final java.lang.String nomRol, 
		final java.lang.String codiAplicacio, 
		final java.lang.String codiDispatcher)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.Role findRoleByNameAndSystem(
		final java.lang.String name, 
		final java.lang.String system)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.Role findRoleByShortName(
		final java.lang.String name)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.Role update(
		final com.soffid.iam.iga.api.Role rol)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.Role update2(
		final com.soffid.iam.iga.api.Role rol)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.RoleAccount create(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.RoleAccount findRoleAccountById(
		final long id)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.RoleAccount updateAttributes(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.RoleGrant create(
		final com.soffid.iam.iga.api.RoleGrant grant)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.RoleGrant update(
		final com.soffid.iam.iga.api.RoleGrant grant)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.InformationSystem> findApplications(
		final com.soffid.zkdb.api.Query query)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> findDomainValues(
		final com.soffid.zkdb.api.Query query)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.RoleAccount> findRedundantRoles(
		final java.lang.String query)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.RoleAccount> findRoleAccounts(
		final com.soffid.zkdb.api.Query query)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Role> findRoles(
		final com.soffid.zkdb.api.Query query)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String generateChangesReport(
		final com.soffid.iam.iga.api.Role rol)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String generateChangesReport(
		final com.soffid.iam.iga.api.Role rol, 
		final java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToAdd, 
		final java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToRemove)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.am.api.NetworkAuthorization> findNetworkACLRolesByRoleNameAndApplicationNameAndDispatcherName(
		final java.lang.String nomRole, 
		final java.lang.String codiAplicacioRol, 
		final java.lang.String codiDispatcher)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.InformationSystem> findApplicationChildren(
		final java.lang.String applicationName)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.Role> findApplicationManagementRoles()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findApplicationManagers(
		final java.lang.String informationSystem, 
		final java.lang.String roleName)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> findRoleAuthorizationsByRoleNameAndApplicationNameAndDispatcherName(
		final java.lang.String nomRole, 
		final java.lang.String codiAplicacioRol, 
		final java.lang.String codiDispatcher)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findEffectiveRoleGrantByAccount(
		final long accountId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findEffectiveRoleGrantByUser(
		final long userId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findEffectiveRoleGrantByUserAndHolderGroup(
		final long userId, 
		final long groupId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findEffectiveUserRolesByInformationSystem(
		final java.lang.String informationSystem)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.Role> findGroupManagementRoles()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findGroupManagers(
		final java.lang.String group, 
		final java.lang.String roleName)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.Group> findRoleHoldersGroupsByRole(
		final com.soffid.iam.iga.api.Role rol)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> findApplicationAccessTreeRolesByRoleNameAndRoleApplicationNameAndDispatcherName(
		final java.lang.String nomRole, 
		final java.lang.String codiAplicacioRol, 
		final java.lang.String codiDispatcher)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findRoleAccountByAccount(
		final long accountId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findRoleGrantByAccount(
		final java.lang.Long accountId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findRoleGrantByRole(
		final java.lang.Long rolId, 
		final java.lang.Long numRegistres)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findRoleAccountByAccountNoRule(
		final long accountId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.RoleGrantHierarchy> findRoleGrantHierarchyByAccount(
		final long accountId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.RoleGrantHierarchy> findRoleGrantHierarchyByUser(
		final long userId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findRoleGrantsByGroup(
		final com.soffid.iam.iga.api.Group grup)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByApplicationName(
		final java.lang.String codiAplicacio)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByApplicationNameUnrestricted(
		final java.lang.String codiAplicacio)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByUserName(
		final java.lang.String codiUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByDomainNameAndApplicationName(
		final java.lang.String nomDomini, 
		final java.lang.String codiAplicacio)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.Role> findGrantedRolesToGroupByGroup(
		final com.soffid.iam.iga.api.Group grup)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByRoleNameAndRoleApplicationNameAndDispatcherName(
		final java.lang.String nomRole, 
		final java.lang.String codiAplicacioRol, 
		final java.lang.String codiDispatcher)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByUserName(
		final java.lang.String codiUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesHistoryByUserName(
		final java.lang.String codiUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByUserNameNoSoD(
		final java.lang.String codiUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByInformationSystem(
		final java.lang.String informationSystem)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByUserNameNoRules(
		final java.lang.String codiUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.User> findUsersByRoleNameAndRoleApplicationNameAndDispatcherName(
		final java.lang.String nomRole, 
		final java.lang.String codiAplicacioRol, 
		final java.lang.String codiDispatcher)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.Role> getRoles()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.InformationSystem aplicacio)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.Role rol)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.RoleGrant grant)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void synchronizeRole(
		final com.soffid.iam.iga.api.Role rol)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void update(
		final com.soffid.iam.iga.api.InformationSystem aplicacio)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
