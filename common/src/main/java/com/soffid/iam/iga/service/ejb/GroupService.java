//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * EJB GroupService
 */
public interface GroupService

 {

	java.util.Collection<com.soffid.iam.iga.api.UserGroup> findGroupMembers(
		final java.lang.String codiGrup)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.Group create(
		final com.soffid.iam.iga.api.Group grup)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.Group findGroupByGroupNameAndDate(
		final java.lang.String codi, 
		final java.util.Date date)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.Group findGroupByGroupName(
		final java.lang.String codi)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.Group findGroupById(
		final java.lang.Long grupId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.Group getSuperGroup(
		final java.lang.String codiGrup)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.Group update(
		final com.soffid.iam.iga.api.Group grup)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.UserGroup create(
		final com.soffid.iam.iga.api.UserGroup usuariGrup)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.UserGroup update(
		final com.soffid.iam.iga.api.UserGroup usuariGrup)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup> findGroupUserByJsonQuery(
		final java.lang.String query, 
		final java.lang.Integer startIndex, 
		final java.lang.Integer count)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Group> findGroups(
		final com.soffid.zkdb.api.Query q)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup> findUserGroup(
		final com.soffid.zkdb.api.Query query)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup> findUsersBelongtoGroupByGroupName(
		final java.lang.String codiGrup, 
		final java.util.Date date, 
		final java.lang.Integer start, 
		final java.lang.Integer pageSize)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUsersRolesDomainTypeAndUserGroups(
		final java.lang.String codiGrup)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.Group> findSubgroupsByGroupName(
		final java.lang.String codi)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.Group> findSubgroupsByGroupNameAndDate(
		final java.lang.String codi, 
		final java.util.Date date)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.UserGroup> findUserGroupHistoryByUserName(
		final java.lang.String userName)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.UserGroup> findUsersGroupByUserName(
		final java.lang.String codiUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.Group> getParentList(
		final java.lang.String codiGrup)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.Role> getRolesFromGroup(
		final com.soffid.iam.iga.api.Group grup)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.GroupRoles> getRolesFromGroupAndParentGroup(
		final com.soffid.iam.iga.api.Group grup)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void addGroupToUser(
		final java.lang.String codiUsuari, 
		final java.lang.String codiGrup)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.Group grup)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.UserGroup usuariGrup)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void removeGroupFormUser(
		final java.lang.String codiUsuari, 
		final java.lang.String codiGrup)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void synchronize(
		final java.lang.String groupName)
	throws com.soffid.iam.exception.InternalErrorException;

}
