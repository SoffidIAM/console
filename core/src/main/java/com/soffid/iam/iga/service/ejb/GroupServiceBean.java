//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * @see <code>com.soffid.iam.iga.service.GroupService</code>,
 * @see <code>com.soffid.iam.iga.service.GroupService</code>,
 */
@jakarta.ejb.Stateless(name="GroupService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.iga.service.GroupService")
@jakarta.ejb.Local(com.soffid.iam.iga.service.ejb.GroupService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class GroupServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.iga.service.ejb.GroupService
{
	private com.soffid.iam.iga.service.GroupService groupService;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.UserGroup> findGroupMembers(java.lang.String codiGrup)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.UserGroup> findGroupMembers(
		final java.lang.String codiGrup)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("group:query"))
			throw new SecurityException("Unable to execute GroupService.findGroupMembers. Required roles: [group:query]");
		try
		{
			return this.groupService.findGroupMembers(codiGrup); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.iam.iga.api.Group create(com.soffid.iam.iga.api.Group grup)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.Group create(
		final com.soffid.iam.iga.api.Group grup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("group:create"))
			throw new SecurityException("Unable to execute GroupService.create. Required roles: [group:create]");
		try
		{
			return this.groupService.create(grup); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.iam.iga.api.Group findGroupByGroupNameAndDate(java.lang.String codi, java.util.Date date)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.Group findGroupByGroupNameAndDate(
		final java.lang.String codi, 
		final java.util.Date date)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("group:query"))
			throw new SecurityException("Unable to execute GroupService.findGroupByGroupNameAndDate. Required roles: [group:query]");
		try
		{
			return this.groupService.findGroupByGroupNameAndDate(codi, date); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.iam.iga.api.Group findGroupByGroupName(java.lang.String codi)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.Group findGroupByGroupName(
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("group:query"))
			throw new SecurityException("Unable to execute GroupService.findGroupByGroupName. Required roles: [group:query]");
		try
		{
			return this.groupService.findGroupByGroupName(codi); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.iam.iga.api.Group findGroupById(java.lang.Long grupId)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.Group findGroupById(
		final java.lang.Long grupId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("group:query"))
			throw new SecurityException("Unable to execute GroupService.findGroupById. Required roles: [group:query]");
		try
		{
			return this.groupService.findGroupById(grupId); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.iam.iga.api.Group getSuperGroup(java.lang.String codiGrup)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.Group getSuperGroup(
		final java.lang.String codiGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("lopd:query"))
			throw new SecurityException("Unable to execute GroupService.getSuperGroup. Required roles: [lopd:query]");
		try
		{
			return this.groupService.getSuperGroup(codiGrup); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.iam.iga.api.Group update(com.soffid.iam.iga.api.Group grup)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.Group update(
		final com.soffid.iam.iga.api.Group grup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("group:update"))
			throw new SecurityException("Unable to execute GroupService.update. Required roles: [group:update]");
		try
		{
			return this.groupService.update(grup); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.iam.iga.api.UserGroup create(com.soffid.iam.iga.api.UserGroup usuariGrup)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.UserGroup create(
		final com.soffid.iam.iga.api.UserGroup usuariGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:group:create"))
			throw new SecurityException("Unable to execute GroupService.create. Required roles: [user:group:create]");
		try
		{
			return this.groupService.create(usuariGrup); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.iam.iga.api.UserGroup update(com.soffid.iam.iga.api.UserGroup usuariGrup)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.UserGroup update(
		final com.soffid.iam.iga.api.UserGroup usuariGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:group:create"))
			throw new SecurityException("Unable to execute GroupService.update. Required roles: [user:group:create]");
		try
		{
			return this.groupService.update(usuariGrup); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup> findGroupUserByJsonQuery(java.lang.String query, java.lang.Integer startIndex, java.lang.Integer count)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup> findGroupUserByJsonQuery(
		final java.lang.String query, 
		final java.lang.Integer startIndex, 
		final java.lang.Integer count)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute GroupService.findGroupUserByJsonQuery. Required roles: [user:query]");
		try
		{
			return this.groupService.findGroupUserByJsonQuery(query, startIndex, count); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Group> findGroups(com.soffid.zkdb.api.Query q)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Group> findGroups(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("group:query"))
			throw new SecurityException("Unable to execute GroupService.findGroups. Required roles: [group:query]");
		try
		{
			return this.groupService.findGroups(q); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup> findUserGroup(com.soffid.zkdb.api.Query query)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup> findUserGroup(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute GroupService.findUserGroup. Required roles: [application:query]");
		try
		{
			return this.groupService.findUserGroup(query); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup> findUsersBelongtoGroupByGroupName(java.lang.String codiGrup, java.util.Date date, java.lang.Integer start, java.lang.Integer pageSize)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup> findUsersBelongtoGroupByGroupName(
		final java.lang.String codiGrup, 
		final java.util.Date date, 
		final java.lang.Integer start, 
		final java.lang.Integer pageSize)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("group:query"))
			throw new SecurityException("Unable to execute GroupService.findUsersBelongtoGroupByGroupName. Required roles: [group:query]");
		try
		{
			return this.groupService.findUsersBelongtoGroupByGroupName(codiGrup, date, start, pageSize); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUsersRolesDomainTypeAndUserGroups(java.lang.String codiGrup)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUsersRolesDomainTypeAndUserGroups(
		final java.lang.String codiGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("group:role:query"))
			throw new SecurityException("Unable to execute GroupService.findUsersRolesDomainTypeAndUserGroups. Required roles: [group:role:query]");
		try
		{
			return this.groupService.findUsersRolesDomainTypeAndUserGroups(codiGrup); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.Group> findSubgroupsByGroupName(java.lang.String codi)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.Group> findSubgroupsByGroupName(
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("group:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("lopd:query"))
			throw new SecurityException("Unable to execute GroupService.findSubgroupsByGroupName. Required roles: [group:query, lopd_query]");
		try
		{
			return this.groupService.findSubgroupsByGroupName(codi); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.Group> findSubgroupsByGroupNameAndDate(java.lang.String codi, java.util.Date date)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.Group> findSubgroupsByGroupNameAndDate(
		final java.lang.String codi, 
		final java.util.Date date)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("group:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("lopd:query"))
			throw new SecurityException("Unable to execute GroupService.findSubgroupsByGroupNameAndDate. Required roles: [group:query, lopd_query]");
		try
		{
			return this.groupService.findSubgroupsByGroupNameAndDate(codi, date); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.UserGroup> findUserGroupHistoryByUserName(java.lang.String userName)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.UserGroup> findUserGroupHistoryByUserName(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute GroupService.findUserGroupHistoryByUserName. Required roles: [user:query]");
		try
		{
			return this.groupService.findUserGroupHistoryByUserName(userName); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.UserGroup> findUsersGroupByUserName(java.lang.String codiUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.UserGroup> findUsersGroupByUserName(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute GroupService.findUsersGroupByUserName. Required roles: [user:query]");
		try
		{
			return this.groupService.findUsersGroupByUserName(codiUsuari); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.Group> getParentList(java.lang.String codiGrup)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.Group> getParentList(
		final java.lang.String codiGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("group:query"))
			throw new SecurityException("Unable to execute GroupService.getParentList. Required roles: [group:query]");
		try
		{
			return this.groupService.getParentList(codiGrup); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.Role> getRolesFromGroup(com.soffid.iam.iga.api.Group grup)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.Role> getRolesFromGroup(
		final com.soffid.iam.iga.api.Group grup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("group:query"))
			throw new SecurityException("Unable to execute GroupService.getRolesFromGroup. Required roles: [group:query]");
		try
		{
			return this.groupService.getRolesFromGroup(grup); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.GroupRoles> getRolesFromGroupAndParentGroup(com.soffid.iam.iga.api.Group grup)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.GroupRoles> getRolesFromGroupAndParentGroup(
		final com.soffid.iam.iga.api.Group grup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("group:role:query"))
			throw new SecurityException("Unable to execute GroupService.getRolesFromGroupAndParentGroup. Required roles: [group:role:query]");
		try
		{
			return this.groupService.getRolesFromGroupAndParentGroup(grup); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#void addGroupToUser(java.lang.String codiUsuari, java.lang.String codiGrup)
	 */
	@jakarta.annotation.security.PermitAll
	public void addGroupToUser(
		final java.lang.String codiUsuari, 
		final java.lang.String codiGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:group:create"))
			throw new SecurityException("Unable to execute GroupService.addGroupToUser. Required roles: [user:group:create]");
		try
		{
			this.groupService.addGroupToUser(codiUsuari, codiGrup); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#void delete(com.soffid.iam.iga.api.Group grup)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.Group grup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("group:delete"))
			throw new SecurityException("Unable to execute GroupService.delete. Required roles: [group:delete]");
		try
		{
			this.groupService.delete(grup); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#void delete(com.soffid.iam.iga.api.UserGroup usuariGrup)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.UserGroup usuariGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:group:delete"))
			throw new SecurityException("Unable to execute GroupService.delete. Required roles: [user:group:delete]");
		try
		{
			this.groupService.delete(usuariGrup); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#void removeGroupFormUser(java.lang.String codiUsuari, java.lang.String codiGrup)
	 */
	@jakarta.annotation.security.PermitAll
	public void removeGroupFormUser(
		final java.lang.String codiUsuari, 
		final java.lang.String codiGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:group:delete"))
			throw new SecurityException("Unable to execute GroupService.removeGroupFormUser. Required roles: [user:group:delete]");
		try
		{
			this.groupService.removeGroupFormUser(codiUsuari, codiGrup); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.GroupService#void synchronize(java.lang.String groupName)
	 */
	@jakarta.annotation.security.PermitAll
	public void synchronize(
		final java.lang.String groupName)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("group:query"))
			throw new SecurityException("Unable to execute GroupService.synchronize. Required roles: [group:query]");
		try
		{
			this.groupService.synchronize(groupName); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * Initizlizes been
	 *
	 * @see org.springframework.ejb.support.AbstractStatelessSessionBean#onEjbCreate()
	 */

	@Override

	@jakarta.annotation.PostConstruct
	public void createBean() 
	{
		super.createBean();
	}


	protected void onEjbCreate()
	{

		this.groupService = (com.soffid.iam.iga.service.GroupService)
		getBeanFactory().getBean("com.soffid.iam.iga.service.GroupService");
	}

	
	/**
	 * Override default BeanFactoryLocator implementation to
	 * provide singleton loading of the application context Bean factory.
	 *
	 * @see jakarta.ejb.SessionBean#setSessionContext(jakarta.ejb.SessionContext)
	 */
	public void setSessionContext(jakarta.ejb.SessionContext sessionContext)
	{
		super.setSessionContext(sessionContext);
		super.setBeanFactoryLocator(
		org.springframework.context.access.ContextSingletonBeanFactoryLocator.getInstance("beanRefFactory.xml"));
		super.setBeanFactoryLocatorKey("beanRefFactory");
	}

	org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog (getClass());

	/**
	 * Finds the root cause of the parent exception
	 * by traveling up the exception tree.
	 */	private static Throwable getRootCause(Throwable throwable)
	{
		if (throwable != null)
		{
			// Reflectively get any exception causes.
			try
			{
				Throwable targetException = null;
				// java.lang.reflect.InvocationTargetException
				String exceptionProperty = "targetException";
				if (org.apache.commons.beanutils.PropertyUtils.isReadable(throwable, exceptionProperty))
				{
					targetException = (Throwable)org.apache.commons.beanutils.PropertyUtils.getProperty(throwable, exceptionProperty);
				}
				else
				{
					exceptionProperty = "causedByException";
					//jakarta.ejb.EJBException
					if (org.apache.commons.beanutils.PropertyUtils.isReadable(throwable, exceptionProperty))
					{
						targetException = (Throwable)org.apache.commons.beanutils.PropertyUtils.getProperty(throwable, exceptionProperty);
					}
				}
				if (targetException != null)
				{
					throwable = targetException;
				}
			}
			catch (Exception exception)
			{
				// just print the exception and continue
				exception.printStackTrace();
			}
			if (throwable.getCause() != null)
			{
				throwable = throwable.getCause();
				throwable = getRootCause(throwable);
			}
		}
		return throwable;
	}
}
