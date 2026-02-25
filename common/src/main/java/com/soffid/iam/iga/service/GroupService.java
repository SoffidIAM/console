//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
/**
 * Service GroupService
 */
public interface GroupService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.iga.service.GroupService";

	public final static String SERVICE_NAME = "com.soffid.iam.iga.service.GroupService";

	/**
	 * Operation findGroupMembers

	 * @param codiGrup 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.UserGroup> findGroupMembers(
		final java.lang.String codiGrup)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getOfficeServer

	 * @param grup 
	 * @return 
	 */
	com.soffid.iam.am.api.Host getOfficeServer(
		final com.soffid.iam.iga.api.Group grup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param grup 
	 * @return 
	 */
	com.soffid.iam.iga.api.Group create(
		final com.soffid.iam.iga.api.Group grup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createHistoric

	 * @param grup 
	 * @return 
	 */
	com.soffid.iam.iga.api.Group createHistoric(
		final com.soffid.iam.iga.api.Group grup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findGroupByGroupNameAndDate

	 * @param codi 
	 * @param date 
	 * @return 
	 */
	com.soffid.iam.iga.api.Group findGroupByGroupNameAndDate(
		final java.lang.String codi, 
		final java.util.Date date)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findGroupByGroupName

	 * @param codi 
	 * @return 
	 */
	com.soffid.iam.iga.api.Group findGroupByGroupName(
		final java.lang.String codi)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findGroupById

	 * @param grupId 
	 * @return 
	 */
	com.soffid.iam.iga.api.Group findGroupById(
		final java.lang.Long grupId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findPrimaryGroupByUserName

	 * @param codiUsuari 
	 * @return 
	 */
	com.soffid.iam.iga.api.Group findPrimaryGroupByUserName(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getSuperGroup

	 * @param codiGrup 
	 * @return 
	 */
	com.soffid.iam.iga.api.Group getSuperGroup(
		final java.lang.String codiGrup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param grup 
	 * @return 
	 */
	com.soffid.iam.iga.api.Group update(
		final com.soffid.iam.iga.api.Group grup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param usuariGrup 
	 * @return 
	 */
	com.soffid.iam.iga.api.UserGroup create(
		final com.soffid.iam.iga.api.UserGroup usuariGrup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserGroupByUserNameAndGroupName

	 * @param codiUsuari 
	 * @param codiGrup 
	 * @return 
	 */
	com.soffid.iam.iga.api.UserGroup findUserGroupByUserNameAndGroupName(
		final java.lang.String codiUsuari, 
		final java.lang.String codiGrup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param usuariGrup 
	 * @return 
	 */
	com.soffid.iam.iga.api.UserGroup update(
		final com.soffid.iam.iga.api.UserGroup usuariGrup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findGroupUserByJsonQuery

	 * @param query 
	 * @param startIndex 
	 * @param count 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup> findGroupUserByJsonQuery(
		final java.lang.String query, 
		final java.lang.Integer startIndex, 
		final java.lang.Integer count)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findGroups

	 * @param q 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Group> findGroups(
		final com.soffid.zkdb.api.Query q)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserGroup

	 * @param query 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup> findUserGroup(
		final com.soffid.zkdb.api.Query query)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUsersBelongtoGroupByGroupName

	 * @param codiGrup 
	 * @param date 
	 * @param start 
	 * @param pageSize 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup> findUsersBelongtoGroupByGroupName(
		final java.lang.String codiGrup, 
		final java.util.Date date, 
		final java.lang.Integer start, 
		final java.lang.Integer pageSize)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findGroupNames

	 * @return 
	 */
	java.util.Collection<java.lang.String> findGroupNames()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findGroupsByUserName

	 * @param codiUsuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Group> findGroupsByUserName(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findGroupsByGroupsType

	 * @param tipus 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Group> findGroupsByGroupsType(
		final java.lang.String tipus)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findGroupsFromRolesByUserName

	 * @param codiUsuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Group> findGroupsFromRolesByUserName(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findGroupsFromUsersByUserName

	 * @param codiUsuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Group> findGroupsFromUsersByUserName(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUsersRolesWithGroupByUserName

	 * @param codiUsuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUsersRolesWithGroupByUserName(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUsersRolesDomainTypeAndUserGroups

	 * @param codiGrup 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUsersRolesDomainTypeAndUserGroups(
		final java.lang.String codiGrup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findSubgroupsByGroupName

	 * @param codi 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Group> findSubgroupsByGroupName(
		final java.lang.String codi)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findSubgroupsByGroupNameAndDate

	 * @param codi 
	 * @param date 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Group> findSubgroupsByGroupNameAndDate(
		final java.lang.String codi, 
		final java.util.Date date)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserGroupHistoryByUserName

	 * @param userName 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.UserGroup> findUserGroupHistoryByUserName(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUsersGroupByUserName

	 * @param codiUsuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.UserGroup> findUsersGroupByUserName(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getGroups

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Group> getGroups()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getParentList

	 * @param codiGrup 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Group> getParentList(
		final java.lang.String codiGrup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getRolesFromGroup

	 * @param grup 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Role> getRolesFromGroup(
		final com.soffid.iam.iga.api.Group grup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getRolesFromGroup

	 * @param codiGrup 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.GroupRoles> getRolesFromGroup(
		final java.lang.String codiGrup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getRolesFromGroupAndParentGroup

	 * @param grup 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.GroupRoles> getRolesFromGroupAndParentGroup(
		final com.soffid.iam.iga.api.Group grup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation addGroupToUser

	 * @param codiUsuari 
	 * @param codiGrup 
	 */
	void addGroupToUser(
		final java.lang.String codiUsuari, 
		final java.lang.String codiGrup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param grup 
	 */
	void delete(
		final com.soffid.iam.iga.api.Group grup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param usuariGrup 
	 */
	void delete(
		final com.soffid.iam.iga.api.UserGroup usuariGrup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation propagateRolsChangesToDispatcher

	 * @param codiGrup 
	 */
	void propagateRolsChangesToDispatcher(
		final java.lang.String codiGrup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation removeGroupFormUser

	 * @param codiUsuari 
	 * @param codiGrup 
	 */
	void removeGroupFormUser(
		final java.lang.String codiUsuari, 
		final java.lang.String codiGrup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation setSuperGroup

	 * @param codiSubGrup 
	 * @param codiSuperGrup 
	 */
	void setSuperGroup(
		final java.lang.String codiSubGrup, 
		final java.lang.String codiSuperGrup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation synchronize

	 * @param groupName 
	 */
	void synchronize(
		final java.lang.String groupName)
			throws com.soffid.iam.exception.InternalErrorException;

}
