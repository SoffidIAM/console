//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service.ejb;
/**
 * @see <code>com.soffid.iam.base.service.AccountService</code>,
 * @see <code>com.soffid.iam.base.service.AccountService</code>,
 */
@jakarta.ejb.Stateless(name="AccountService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.base.service.AccountService")
@jakarta.ejb.Local(com.soffid.iam.base.service.ejb.AccountService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class AccountServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.base.service.ejb.AccountService
{
	private com.soffid.iam.base.service.AccountService accountService;

	/**
	 * @see com.soffid.iam.base.service.AccountService#boolean hasAccountSshKey(com.soffid.iam.base.api.Account account)
	 */
	@jakarta.annotation.security.PermitAll
	public boolean hasAccountSshKey(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.accountService.hasAccountSshKey(account); 
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
	 * @see com.soffid.iam.base.service.AccountService#boolean isAccountPasswordAvailable(long accountId)
	 */
	@jakarta.annotation.security.PermitAll
	public boolean isAccountPasswordAvailable(
		final long accountId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.accountService.isAccountPasswordAvailable(accountId); 
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
	 * @see com.soffid.iam.base.service.AccountService#boolean isUpdatePending(com.soffid.iam.base.api.Account account)
	 */
	@jakarta.annotation.security.PermitAll
	public boolean isUpdatePending(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.accountService.isUpdatePending(account); 
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
	 * @see com.soffid.iam.base.service.AccountService#boolean needsAccount(java.lang.String userName, java.lang.String dispatcherName)
	 */
	@jakarta.annotation.security.PermitAll
	public boolean needsAccount(
		final java.lang.String userName, 
		final java.lang.String dispatcherName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute AccountService.needsAccount. Required roles: [agent:update]");
		try
		{
			return this.accountService.needsAccount(userName, dispatcherName); 
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
	 * @see com.soffid.iam.base.service.AccountService#boolean setHPAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, java.util.Date untilDate, boolean force)
	 */
	@jakarta.annotation.security.PermitAll
	public boolean setHPAccountPassword(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.am.api.Password password, 
		final java.util.Date untilDate, 
		final boolean force)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.accountService.setHPAccountPassword(account, password, untilDate, force); 
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
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.am.api.Password generateAccountPassword(com.soffid.iam.base.api.Account account)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.Password generateAccountPassword(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.accountService.generateAccountPassword(account); 
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
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.am.api.Password generateAccountTemporaryPassword(com.soffid.iam.base.api.Account account)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.Password generateAccountTemporaryPassword(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.accountService.generateAccountTemporaryPassword(account); 
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
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.am.api.Password queryAccountPassword(com.soffid.iam.base.api.Account account)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.Password queryAccountPassword(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.accountService.queryAccountPassword(account); 
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
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.am.api.Password queryAccountSshKey(com.soffid.iam.base.api.Account account)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.Password queryAccountSshKey(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.accountService.queryAccountSshKey(account); 
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
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.am.api.Password setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, boolean temporary, boolean online)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.Password setAccountPassword(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.am.api.Password password, 
		final boolean temporary, 
		final boolean online)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.accountService.setAccountPassword(account, password, temporary, online); 
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
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.am.api.PasswordValidation checkPasswordSynchronizationStatus(com.soffid.iam.base.api.Account account)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.PasswordValidation checkPasswordSynchronizationStatus(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("account:update"))
			throw new SecurityException("Unable to execute AccountService.checkPasswordSynchronizationStatus. Required roles: [account:update]");
		try
		{
			return this.accountService.checkPasswordSynchronizationStatus(account); 
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
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.Account createAccount(com.soffid.iam.base.api.Account account)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.Account createAccount(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.AccountAlreadyExistsException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("account:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute AccountService.createAccount. Required roles: [account:create, agent_update]");
		try
		{
			return this.accountService.createAccount(account); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.AccountAlreadyExistsException)
				throw (com.soffid.iam.exception.AccountAlreadyExistsException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.Account createAccount2(com.soffid.iam.base.api.Account account)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.Account createAccount2(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.AccountAlreadyExistsException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("account:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute AccountService.createAccount2. Required roles: [account:create, agent_update]");
		try
		{
			return this.accountService.createAccount2(account); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.AccountAlreadyExistsException)
				throw (com.soffid.iam.exception.AccountAlreadyExistsException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.Account findAccount(java.lang.String accountAndDispatcher)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.Account findAccount(
		final java.lang.String accountAndDispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:update")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:update"))
			throw new SecurityException("Unable to execute AccountService.findAccount. Required roles: [agent:query, agent_update, user_create, user_query, user_update]");
		try
		{
			return this.accountService.findAccount(accountAndDispatcher); 
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
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.Account findAccount(java.lang.String accountName, java.lang.String dispatcherName)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.Account findAccount(
		final java.lang.String accountName, 
		final java.lang.String dispatcherName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:update")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:update"))
			throw new SecurityException("Unable to execute AccountService.findAccount. Required roles: [agent:query, agent_update, user_create, user_query, user_update]");
		try
		{
			return this.accountService.findAccount(accountName, dispatcherName); 
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
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.Account findAccountByExternalId(java.lang.String externalId, java.lang.String system)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.Account findAccountByExternalId(
		final java.lang.String externalId, 
		final java.lang.String system)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:update")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:update"))
			throw new SecurityException("Unable to execute AccountService.findAccountByExternalId. Required roles: [agent:query, agent_update, user_create, user_query, user_update]");
		try
		{
			return this.accountService.findAccountByExternalId(externalId, system); 
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
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.Account findAccountById(long id)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.Account findAccountById(
		final long id)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("account:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:update")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:update"))
			throw new SecurityException("Unable to execute AccountService.findAccountById. Required roles: [account:query, agent_query, agent_update, user_create, user_query, user_update]");
		try
		{
			return this.accountService.findAccountById(id); 
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
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.Account generateAccountSshPrivateKey(com.soffid.iam.base.api.Account account)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.Account generateAccountSshPrivateKey(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.accountService.generateAccountSshPrivateKey(account); 
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
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.Account removeAccountSnapshot(com.soffid.iam.base.api.Account account)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.Account removeAccountSnapshot(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("account:update"))
			throw new SecurityException("Unable to execute AccountService.removeAccountSnapshot. Required roles: [account:update]");
		try
		{
			return this.accountService.removeAccountSnapshot(account); 
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
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.Account setAccountSshPrivateKey(com.soffid.iam.base.api.Account account, java.lang.String privateKey)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.Account setAccountSshPrivateKey(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.String privateKey)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.accountService.setAccountSshPrivateKey(account, privateKey); 
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
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.Account updateAccount(com.soffid.iam.base.api.Account account)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.Account updateAccount(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.AccountAlreadyExistsException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("account:update")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute AccountService.updateAccount. Required roles: [account:update, agent_update]");
		try
		{
			return this.accountService.updateAccount(account); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.AccountAlreadyExistsException)
				throw (com.soffid.iam.exception.AccountAlreadyExistsException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.Account updateAccount2(com.soffid.iam.base.api.Account account)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.Account updateAccount2(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.AccountAlreadyExistsException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("account:update")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute AccountService.updateAccount2. Required roles: [account:update, agent_update]");
		try
		{
			return this.accountService.updateAccount2(account); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.AccountAlreadyExistsException)
				throw (com.soffid.iam.exception.AccountAlreadyExistsException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.AsyncProcessTracker disableAccounts(java.lang.String scimQuery, java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.AsyncProcessTracker disableAccounts(
		final java.lang.String scimQuery, 
		final java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("account:update"))
			throw new SecurityException("Unable to execute AccountService.disableAccounts. Required roles: [account:update]");
		try
		{
			return this.accountService.disableAccounts(scimQuery, rules); 
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
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.AsyncProcessTracker disableAccountsPreview(java.lang.String scimQuery, java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules, java.util.List<java.lang.Object[]> actions)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.AsyncProcessTracker disableAccountsPreview(
		final java.lang.String scimQuery, 
		final java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules, 
		final java.util.List<java.lang.Object[]> actions)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("account:update"))
			throw new SecurityException("Unable to execute AccountService.disableAccountsPreview. Required roles: [account:update]");
		try
		{
			return this.accountService.disableAccountsPreview(scimQuery, rules, actions); 
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
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.User getHPAccountOwner(com.soffid.iam.base.api.Account account)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.User getHPAccountOwner(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.accountService.getHPAccountOwner(account); 
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
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.UserAccount createAccount(com.soffid.iam.base.api.User usuari, com.soffid.iam.iga.api.System dispatcher, java.lang.String name)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.UserAccount createAccount(
		final com.soffid.iam.base.api.User usuari, 
		final com.soffid.iam.iga.api.System dispatcher, 
		final java.lang.String name)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.NeedsAccountNameException, com.soffid.iam.exception.AccountAlreadyExistsException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:update"))
			throw new SecurityException("Unable to execute AccountService.createAccount. Required roles: [user:create, user_update]");
		try
		{
			return this.accountService.createAccount(usuari, dispatcher, name); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.NeedsAccountNameException)
				throw (com.soffid.iam.exception.NeedsAccountNameException) cause;
			if (cause instanceof com.soffid.iam.exception.AccountAlreadyExistsException)
				throw (com.soffid.iam.exception.AccountAlreadyExistsException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.UserData createAccountAttribute(com.soffid.iam.base.api.UserData attribute)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.UserData createAccountAttribute(
		final com.soffid.iam.base.api.UserData attribute)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("account:attribute:update"))
			throw new SecurityException("Unable to execute AccountService.createAccountAttribute. Required roles: [account:attribute:update]");
		try
		{
			return this.accountService.createAccountAttribute(attribute); 
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
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.UserData updateAccountAttribute(com.soffid.iam.base.api.UserData attribute)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.UserData updateAccountAttribute(
		final com.soffid.iam.base.api.UserData attribute)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("account:attribute:update"))
			throw new SecurityException("Unable to execute AccountService.updateAccountAttribute. Required roles: [account:attribute:update]");
		try
		{
			return this.accountService.updateAccountAttribute(attribute); 
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
	 * @see com.soffid.iam.base.service.AccountService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.Account> findAccounts(com.soffid.zkdb.api.Query query)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.Account> findAccounts(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("account:query"))
			throw new SecurityException("Unable to execute AccountService.findAccounts. Required roles: [account:query]");
		try
		{
			return this.accountService.findAccounts(query); 
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
	 * @see com.soffid.iam.base.service.AccountService#int isUpdatePendingExtended(com.soffid.iam.base.api.Account account)
	 */
	@jakarta.annotation.security.PermitAll
	public int isUpdatePendingExtended(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.accountService.isUpdatePendingExtended(account); 
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
	 * @see com.soffid.iam.base.service.AccountService#java.lang.String guessAccountName(java.lang.String userName, java.lang.String dispatcherName)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String guessAccountName(
		final java.lang.String userName, 
		final java.lang.String dispatcherName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute AccountService.guessAccountName. Required roles: [agent:update, user_query]");
		try
		{
			return this.accountService.guessAccountName(userName, dispatcherName); 
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
	 * @see com.soffid.iam.base.service.AccountService#java.util.Collection<com.soffid.iam.pam.api.HostService> findAccountServices(com.soffid.iam.base.api.Account account)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.pam.api.HostService> findAccountServices(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("account:query"))
			throw new SecurityException("Unable to execute AccountService.findAccountServices. Required roles: [account:query]");
		try
		{
			return this.accountService.findAccountServices(account); 
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
	 * @see com.soffid.iam.base.service.AccountService#java.util.Collection<com.soffid.iam.base.api.UserAccount> getUserAccounts(com.soffid.iam.base.api.User usuari)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.UserAccount> getUserAccounts(
		final com.soffid.iam.base.api.User usuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.accountService.getUserAccounts(usuari); 
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
	 * @see com.soffid.iam.base.service.AccountService#java.util.Collection<com.soffid.iam.base.api.Account> getUserGrantedAccounts(com.soffid.iam.base.api.User usuari)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.Account> getUserGrantedAccounts(
		final com.soffid.iam.base.api.User usuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.accountService.getUserGrantedAccounts(usuari); 
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
	 * @see com.soffid.iam.base.service.AccountService#java.util.Collection<com.soffid.iam.base.api.Account> getUserGrantedAccounts(com.soffid.iam.base.api.User usuari, com.soffid.iam.base.api.AccountAccessLevelEnum level)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.Account> getUserGrantedAccounts(
		final com.soffid.iam.base.api.User usuari, 
		final com.soffid.iam.base.api.AccountAccessLevelEnum level)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.accountService.getUserGrantedAccounts(usuari, level); 
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
	 * @see com.soffid.iam.base.service.AccountService#java.util.List<com.soffid.iam.base.api.Account> findSharedAccountsByUser(java.lang.String userName)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.base.api.Account> findSharedAccountsByUser(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute AccountService.findSharedAccountsByUser. Required roles: [user:query]");
		try
		{
			return this.accountService.findSharedAccountsByUser(userName); 
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
	 * @see com.soffid.iam.base.service.AccountService#java.util.List<com.soffid.iam.iga.api.AccountHistory> findSharedAccountsHistoryByUser(java.lang.String userName)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.iga.api.AccountHistory> findSharedAccountsHistoryByUser(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute AccountService.findSharedAccountsHistoryByUser. Required roles: [user:query]");
		try
		{
			return this.accountService.findSharedAccountsHistoryByUser(userName); 
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
	 * @see com.soffid.iam.base.service.AccountService#java.util.List<com.soffid.iam.base.api.UserAccount> findUsersAccounts(java.lang.String userName, java.lang.String dispatcherName)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.base.api.UserAccount> findUsersAccounts(
		final java.lang.String userName, 
		final java.lang.String dispatcherName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute AccountService.findUsersAccounts. Required roles: [user:query]");
		try
		{
			return this.accountService.findUsersAccounts(userName, dispatcherName); 
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
	 * @see com.soffid.iam.base.service.AccountService#java.util.List<com.soffid.iam.base.api.UserAccount> findUserAccountsByDomain(java.lang.String user, java.lang.String passwordDomain)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.base.api.UserAccount> findUserAccountsByDomain(
		final java.lang.String user, 
		final java.lang.String passwordDomain)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute AccountService.findUserAccountsByDomain. Required roles: [user:query]");
		try
		{
			return this.accountService.findUserAccountsByDomain(user, passwordDomain); 
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
	 * @see com.soffid.iam.base.service.AccountService#java.util.List<com.soffid.iam.base.api.UserData> getAccountAttributes(com.soffid.iam.base.api.Account acc)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.base.api.UserData> getAccountAttributes(
		final com.soffid.iam.base.api.Account acc)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("account:attribute:query"))
			throw new SecurityException("Unable to execute AccountService.getAccountAttributes. Required roles: [account:attribute:query]");
		try
		{
			return this.accountService.getAccountAttributes(acc); 
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
	 * @see com.soffid.iam.base.service.AccountService#java.util.List<com.soffid.iam.base.api.UserAccountHistory> listAccountGrants(com.soffid.iam.base.api.Account acc)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.base.api.UserAccountHistory> listAccountGrants(
		final com.soffid.iam.base.api.Account acc)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.accountService.listAccountGrants(acc); 
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
	 * @see com.soffid.iam.base.service.AccountService#java.util.List<com.soffid.iam.base.api.Account> listNonUserAccounts(com.soffid.iam.iga.api.System dispatcher, java.lang.String nom)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.base.api.Account> listNonUserAccounts(
		final com.soffid.iam.iga.api.System dispatcher, 
		final java.lang.String nom)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute AccountService.listNonUserAccounts. Required roles: [agent:query, agent_update]");
		try
		{
			return this.accountService.listNonUserAccounts(dispatcher, nom); 
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
	 * @see com.soffid.iam.base.service.AccountService#java.util.List<com.soffid.iam.base.api.UserAccount> listUserAccounts(com.soffid.iam.base.api.User usuari)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.base.api.UserAccount> listUserAccounts(
		final com.soffid.iam.base.api.User usuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:update"))
			throw new SecurityException("Unable to execute AccountService.listUserAccounts. Required roles: [user:create, user_query, user_update]");
		try
		{
			return this.accountService.listUserAccounts(usuari); 
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
	 * @see com.soffid.iam.base.service.AccountService#void checkinHPAccount(com.soffid.iam.base.api.Account account)
	 */
	@jakarta.annotation.security.PermitAll
	public void checkinHPAccount(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.accountService.checkinHPAccount(account); 
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
	 * @see com.soffid.iam.base.service.AccountService#void removeAccount(com.soffid.iam.base.api.Account account)
	 */
	@jakarta.annotation.security.PermitAll
	public void removeAccount(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("account:delete")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute AccountService.removeAccount. Required roles: [account:delete, agent_update]");
		try
		{
			this.accountService.removeAccount(account); 
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
	 * @see com.soffid.iam.base.service.AccountService#void removeAccount(com.soffid.iam.base.api.UserAccount account)
	 */
	@jakarta.annotation.security.PermitAll
	public void removeAccount(
		final com.soffid.iam.base.api.UserAccount account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:update"))
			throw new SecurityException("Unable to execute AccountService.removeAccount. Required roles: [user:create, user_update]");
		try
		{
			this.accountService.removeAccount(account); 
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
	 * @see com.soffid.iam.base.service.AccountService#void removeAccountAttribute(com.soffid.iam.base.api.UserData attribute)
	 */
	@jakarta.annotation.security.PermitAll
	public void removeAccountAttribute(
		final com.soffid.iam.base.api.UserData attribute)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("account:attribute:update"))
			throw new SecurityException("Unable to execute AccountService.removeAccountAttribute. Required roles: [account:attribute:update]");
		try
		{
			this.accountService.removeAccountAttribute(attribute); 
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
	 * @see com.soffid.iam.base.service.AccountService#void removeAccountGrant(com.soffid.iam.base.api.UserAccountHistory acc)
	 */
	@jakarta.annotation.security.PermitAll
	public void removeAccountGrant(
		final com.soffid.iam.base.api.UserAccountHistory acc)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.accountService.removeAccountGrant(acc); 
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
	 * @see com.soffid.iam.base.service.AccountService#void renameAccount(com.soffid.iam.base.api.Account account)
	 */
	@jakarta.annotation.security.PermitAll
	public void renameAccount(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.AccountAlreadyExistsException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute AccountService.renameAccount. Required roles: [agent:update]");
		try
		{
			this.accountService.renameAccount(account); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.AccountAlreadyExistsException)
				throw (com.soffid.iam.exception.AccountAlreadyExistsException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.base.service.AccountService#void sendAccountPassword(com.soffid.iam.base.api.Account account)
	 */
	@jakarta.annotation.security.PermitAll
	public void sendAccountPassword(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.accountService.sendAccountPassword(account); 
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
	 * @see com.soffid.iam.base.service.AccountService#void setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password)
	 */
	@jakarta.annotation.security.PermitAll
	public void setAccountPassword(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.am.api.Password password)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.accountService.setAccountPassword(account, password); 
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
	 * @see com.soffid.iam.base.service.AccountService#void setAccountTemporaryPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password)
	 */
	@jakarta.annotation.security.PermitAll
	public void setAccountTemporaryPassword(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.am.api.Password password)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.accountService.setAccountTemporaryPassword(account, password); 
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

		this.accountService = (com.soffid.iam.base.service.AccountService)
		getBeanFactory().getBean("com.soffid.iam.base.service.AccountService");
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
