//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service.ejb;
/**
 * @see <code>com.soffid.iam.base.service.UserService</code>,
 * @see <code>com.soffid.iam.base.service.UserService</code>,
 */
@jakarta.ejb.Stateless(name="UserService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.base.service.UserService")
@jakarta.ejb.Local(com.soffid.iam.base.service.ejb.UserService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class UserServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.base.service.ejb.UserService
{
	private com.soffid.iam.base.service.UserService userService;

	/**
	 * @see com.soffid.iam.base.service.UserService#byte[] getESSORules(java.lang.String user)
	 */
	@jakarta.annotation.security.PermitAll
	public byte[] getESSORules(
		final java.lang.String user)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:role:query"))
			throw new SecurityException("Unable to execute UserService.getESSORules. Required roles: [user:role:query]");
		try
		{
			return this.userService.getESSORules(user); 
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
	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.base.api.AsyncProcessTracker disableUsers(java.lang.String scimQuery, java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.AsyncProcessTracker disableUsers(
		final java.lang.String scimQuery, 
		final java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:update"))
			throw new SecurityException("Unable to execute UserService.disableUsers. Required roles: [user:update]");
		try
		{
			return this.userService.disableUsers(scimQuery, rules); 
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
	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.base.api.AsyncProcessTracker disableUsersPreview(java.lang.String scimQuery, java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules, java.util.List<java.lang.Object[]> actions)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.AsyncProcessTracker disableUsersPreview(
		final java.lang.String scimQuery, 
		final java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules, 
		final java.util.List<java.lang.Object[]> actions)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:update"))
			throw new SecurityException("Unable to execute UserService.disableUsersPreview. Required roles: [user:update]");
		try
		{
			return this.userService.disableUsersPreview(scimQuery, rules, actions); 
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
	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.base.api.User create(com.soffid.iam.base.api.User usuari)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.User create(
		final com.soffid.iam.base.api.User usuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:create"))
			throw new SecurityException("Unable to execute UserService.create. Required roles: [user:create]");
		try
		{
			return this.userService.create(usuari); 
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
	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.base.api.User findUserByUserName(java.lang.String userName)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.User findUserByUserName(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.userService.findUserByUserName(userName); 
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
	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.base.api.User findUserByUserId(java.lang.Long id)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.User findUserByUserId(
		final java.lang.Long id)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute UserService.findUserByUserId. Required roles: [user:query]");
		try
		{
			return this.userService.findUserByUserId(id); 
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
	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.base.api.User getCurrentUser()
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.User getCurrentUser()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.userService.getCurrentUser(); 
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
	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.base.api.User update(com.soffid.iam.base.api.User usuari)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.User update(
		final com.soffid.iam.base.api.User usuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:custom:update")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:update"))
			throw new SecurityException("Unable to execute UserService.update. Required roles: [user:custom:update, user_update]");
		try
		{
			return this.userService.update(usuari); 
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
	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.base.api.UserData findDataByUserAndCode(java.lang.String userName, java.lang.String codiTipusDada)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.UserData findDataByUserAndCode(
		final java.lang.String userName, 
		final java.lang.String codiTipusDada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("metadata:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute UserService.findDataByUserAndCode. Required roles: [metadata:query, user_query]");
		try
		{
			return this.userService.findDataByUserAndCode(userName, codiTipusDada); 
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
	 * @see com.soffid.iam.base.service.UserService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.User> findUsers(com.soffid.zkdb.api.Query q)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.User> findUsers(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute UserService.findUsers. Required roles: [user:query]");
		try
		{
			return this.userService.findUsers(q); 
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
	 * @see com.soffid.iam.base.service.UserService#int isUpdatePendingExtended(java.lang.String userName)
	 */
	@jakarta.annotation.security.PermitAll
	public int isUpdatePendingExtended(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute UserService.isUpdatePendingExtended. Required roles: [user:query]");
		try
		{
			return this.userService.isUpdatePendingExtended(userName); 
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
	 * @see com.soffid.iam.base.service.UserService#java.lang.String setTemporaryPassword(java.lang.String userName, java.lang.String passwordDomain)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String setTemporaryPassword(
		final java.lang.String userName, 
		final java.lang.String passwordDomain)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:password:update"))
			throw new SecurityException("Unable to execute UserService.setTemporaryPassword. Required roles: [user:password:update]");
		try
		{
			return this.userService.setTemporaryPassword(userName, passwordDomain); 
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
	 * @see com.soffid.iam.base.service.UserService#java.lang.String createNewUserProcess(java.lang.String processName, java.lang.String userName, boolean canviaAProces)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String createNewUserProcess(
		final java.lang.String processName, 
		final java.lang.String userName, 
		final boolean canviaAProces)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.userService.createNewUserProcess(processName, userName, canviaAProces); 
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
	 * @see com.soffid.iam.base.service.UserService#java.lang.String getFollowingName()
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String getFollowingName()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:create"))
			throw new SecurityException("Unable to execute UserService.getFollowingName. Required roles: [user:create]");
		try
		{
			return this.userService.getFollowingName(); 
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
	 * @see com.soffid.iam.base.service.UserService#java.lang.String[] getTasks(java.lang.String userName)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String[] getTasks(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:refresh"))
			throw new SecurityException("Unable to execute UserService.getTasks. Required roles: [user:query, user_refresh]");
		try
		{
			return this.userService.getTasks(userName); 
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
	 * @see com.soffid.iam.base.service.UserService#java.lang.String[] refreshChanges(java.lang.String userName)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String[] refreshChanges(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:refresh"))
			throw new SecurityException("Unable to execute UserService.refreshChanges. Required roles: [user:refresh]");
		try
		{
			return this.userService.refreshChanges(userName); 
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
	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.base.api.UserData> findUserDataByUserName(java.lang.String userName)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.UserData> findUserDataByUserName(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute UserService.findUserDataByUserName. Required roles: [user:query]");
		try
		{
			return this.userService.findUserDataByUserName(userName); 
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
	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.bpm.api.ProcessInstance> findBpmUserProcessInstanceByUserName(java.lang.String userName)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.bpm.api.ProcessInstance> findBpmUserProcessInstanceByUserName(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute UserService.findBpmUserProcessInstanceByUserName. Required roles: [user:query]");
		try
		{
			return this.userService.findBpmUserProcessInstanceByUserName(userName); 
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
	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.am.api.Session> findSessionByUserName(java.lang.String userName)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.am.api.Session> findSessionByUserName(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:session:query"))
			throw new SecurityException("Unable to execute UserService.findSessionByUserName. Required roles: [user:session:query]");
		try
		{
			return this.userService.findSessionByUserName(userName); 
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
	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.iga.api.PrinterUser> findUserPrintersByUserName(java.lang.String userName)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.PrinterUser> findUserPrintersByUserName(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute UserService.findUserPrintersByUserName. Required roles: [user:query]");
		try
		{
			return this.userService.findUserPrintersByUserName(userName); 
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
	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.am.api.NetworkAuthorization> findNetworksACByUserName(java.lang.String userName)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.am.api.NetworkAuthorization> findNetworksACByUserName(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute UserService.findNetworksACByUserName. Required roles: [user:query]");
		try
		{
			return this.userService.findNetworksACByUserName(userName); 
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
	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> getActiveTasks(java.lang.String userName)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> getActiveTasks(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute UserService.getActiveTasks. Required roles: [user:query]");
		try
		{
			return this.userService.getActiveTasks(userName); 
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
	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.iga.api.BpmProcess> getBpmUserProcessList()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.BpmProcess> getBpmUserProcessList()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute UserService.getBpmUserProcessList. Required roles: [user:query]");
		try
		{
			return this.userService.getBpmUserProcessList(); 
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
	 * @see com.soffid.iam.base.service.UserService#java.util.List<com.soffid.iam.am.api.PasswordDomainStatus> findPasswordDomainStatus(java.lang.String user)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.am.api.PasswordDomainStatus> findPasswordDomainStatus(
		final java.lang.String user)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute UserService.findPasswordDomainStatus. Required roles: [user:query]");
		try
		{
			return this.userService.findPasswordDomainStatus(user); 
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
	 * @see com.soffid.iam.base.service.UserService#java.util.Map<java.lang.String,java.lang.Object> findUserAttributes(java.lang.String userName)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Map<java.lang.String,java.lang.Object> findUserAttributes(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute UserService.findUserAttributes. Required roles: [user:query]");
		try
		{
			return this.userService.findUserAttributes(userName); 
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
	 * @see com.soffid.iam.base.service.UserService#void delete(com.soffid.iam.base.api.User user)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.base.api.User user)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:delete"))
			throw new SecurityException("Unable to execute UserService.delete. Required roles: [user:delete]");
		try
		{
			this.userService.delete(user); 
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
	 * @see com.soffid.iam.base.service.UserService#void merge(java.lang.Long srcId, java.lang.Long targetId, java.lang.Long eventId)
	 */
	@jakarta.annotation.security.PermitAll
	public void merge(
		final java.lang.Long srcId, 
		final java.lang.Long targetId, 
		final java.lang.Long eventId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:merge"))
			throw new SecurityException("Unable to execute UserService.merge. Required roles: [user:merge]");
		try
		{
			this.userService.merge(srcId, targetId, eventId); 
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
	 * @see com.soffid.iam.base.service.UserService#void sendPassword(java.lang.String userName, java.lang.String passwordDomain)
	 */
	@jakarta.annotation.security.PermitAll
	public void sendPassword(
		final java.lang.String userName, 
		final java.lang.String passwordDomain)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:password:update"))
			throw new SecurityException("Unable to execute UserService.sendPassword. Required roles: [user:password:update]");
		try
		{
			this.userService.sendPassword(userName, passwordDomain); 
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
	 * @see com.soffid.iam.base.service.UserService#void setPassword(java.lang.String userName, java.lang.String passwordDomain, com.soffid.iam.am.api.Password newPassword)
	 */
	@jakarta.annotation.security.PermitAll
	public void setPassword(
		final java.lang.String userName, 
		final java.lang.String passwordDomain, 
		final com.soffid.iam.am.api.Password newPassword)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:password:update"))
			throw new SecurityException("Unable to execute UserService.setPassword. Required roles: [user:password:update]");
		try
		{
			this.userService.setPassword(userName, passwordDomain, newPassword); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.BadPasswordException)
				throw (com.soffid.iam.exception.BadPasswordException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.base.service.UserService#void setTemporaryPassword(java.lang.String userName, java.lang.String passwordDomain, com.soffid.iam.am.api.Password newPassword)
	 */
	@jakarta.annotation.security.PermitAll
	public void setTemporaryPassword(
		final java.lang.String userName, 
		final java.lang.String passwordDomain, 
		final com.soffid.iam.am.api.Password newPassword)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:password:update"))
			throw new SecurityException("Unable to execute UserService.setTemporaryPassword. Required roles: [user:password:update]");
		try
		{
			this.userService.setTemporaryPassword(userName, passwordDomain, newPassword); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.BadPasswordException)
				throw (com.soffid.iam.exception.BadPasswordException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.base.service.UserService#void unlockPasswordDomain(java.lang.String user, java.lang.String passwordDomain)
	 */
	@jakarta.annotation.security.PermitAll
	public void unlockPasswordDomain(
		final java.lang.String user, 
		final java.lang.String passwordDomain)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:password:set")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:password:update"))
			throw new SecurityException("Unable to execute UserService.unlockPasswordDomain. Required roles: [user:password:set, user_password_update]");
		try
		{
			this.userService.unlockPasswordDomain(user, passwordDomain); 
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
	 * @see com.soffid.iam.base.service.UserService#void updateUserAttributes(java.lang.String userName, java.util.Map<java.lang.String,java.lang.Object> attributes)
	 */
	@jakarta.annotation.security.PermitAll
	public void updateUserAttributes(
		final java.lang.String userName, 
		final java.util.Map<java.lang.String,java.lang.Object> attributes)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:metadata:update"))
			throw new SecurityException("Unable to execute UserService.updateUserAttributes. Required roles: [user:metadata:update]");
		try
		{
			this.userService.updateUserAttributes(userName, attributes); 
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

		this.userService = (com.soffid.iam.base.service.UserService)
		getBeanFactory().getBean("com.soffid.iam.base.service.UserService");
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
