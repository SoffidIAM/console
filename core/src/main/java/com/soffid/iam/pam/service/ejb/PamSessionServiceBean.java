//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.service.ejb;
/**
 * @see <code>com.soffid.iam.pam.service.PamSessionService</code>,
 * @see <code>com.soffid.iam.pam.service.PamSessionService</code>,
 */
@jakarta.ejb.Stateless(name="PamSessionService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.pam.service.PamSessionService")
@jakarta.ejb.Local(com.soffid.iam.pam.service.ejb.PamSessionService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class PamSessionServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.pam.service.ejb.PamSessionService
{
	private com.soffid.iam.pam.service.PamSessionService pamSessionService;

	/**
	 * @see com.soffid.iam.pam.service.PamSessionService#com.soffid.iam.pam.api.JumpServerGroup create(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.pam.api.JumpServerGroup create(
		final com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("jumpServer:create"))
			throw new SecurityException("Unable to execute PamSessionService.create. Required roles: [jumpServer:create]");
		try
		{
			return this.pamSessionService.create(jumpServerGroup); 
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
	 * @see com.soffid.iam.pam.service.PamSessionService#com.soffid.iam.pam.api.JumpServerGroup update(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.pam.api.JumpServerGroup update(
		final com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("jumpServer:update"))
			throw new SecurityException("Unable to execute PamSessionService.update. Required roles: [jumpServer:update]");
		try
		{
			return this.pamSessionService.update(jumpServerGroup); 
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
	 * @see com.soffid.iam.pam.service.PamSessionService#com.soffid.iam.pam.api.NewPamSession createJumpServerSession(com.soffid.iam.base.api.Account account)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.pam.api.NewPamSession createJumpServerSession(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("pamSession:create"))
			throw new SecurityException("Unable to execute PamSessionService.createJumpServerSession. Required roles: [pamSession:create]");
		try
		{
			return this.pamSessionService.createJumpServerSession(account); 
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
	 * @see com.soffid.iam.pam.service.PamSessionService#com.soffid.iam.pam.api.NewPamSession createJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String entryPointPath, java.lang.String entryPointDescriptor)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.pam.api.NewPamSession createJumpServerSession(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.String entryPointPath, 
		final java.lang.String entryPointDescriptor)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("pamSession:create"))
			throw new SecurityException("Unable to execute PamSessionService.createJumpServerSession. Required roles: [pamSession:create]");
		try
		{
			return this.pamSessionService.createJumpServerSession(account, entryPointPath, entryPointDescriptor); 
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
	 * @see com.soffid.iam.pam.service.PamSessionService#com.soffid.iam.pam.api.NewPamSession createJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String entryPointPath, java.lang.String entryPointDescriptor, java.lang.String pamPolicy)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.pam.api.NewPamSession createJumpServerSession(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.String entryPointPath, 
		final java.lang.String entryPointDescriptor, 
		final java.lang.String pamPolicy)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("pamSession:create"))
			throw new SecurityException("Unable to execute PamSessionService.createJumpServerSession. Required roles: [pamSession:create]");
		try
		{
			return this.pamSessionService.createJumpServerSession(account, entryPointPath, entryPointDescriptor, pamPolicy); 
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
	 * @see com.soffid.iam.pam.service.PamSessionService#com.soffid.iam.pam.api.NewPamSession createManualJumpServerSession(java.lang.String accountName, com.soffid.iam.am.api.Password accountPassword, java.lang.String entryPointPath, java.lang.String entryPointDescriptor, java.lang.String pamPolicy)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.pam.api.NewPamSession createManualJumpServerSession(
		final java.lang.String accountName, 
		final com.soffid.iam.am.api.Password accountPassword, 
		final java.lang.String entryPointPath, 
		final java.lang.String entryPointDescriptor, 
		final java.lang.String pamPolicy)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("pamSession:create"))
			throw new SecurityException("Unable to execute PamSessionService.createManualJumpServerSession. Required roles: [pamSession:create]");
		try
		{
			return this.pamSessionService.createManualJumpServerSession(accountName, accountPassword, entryPointPath, entryPointDescriptor, pamPolicy); 
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
	 * @see com.soffid.iam.pam.service.PamSessionService#com.soffid.iam.pam.api.PamSession findSession(java.lang.String serverGroup, java.lang.String sessionId)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.pam.api.PamSession findSession(
		final java.lang.String serverGroup, 
		final java.lang.String sessionId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("pamSession:query"))
			throw new SecurityException("Unable to execute PamSessionService.findSession. Required roles: [pamSession:query]");
		try
		{
			return this.pamSessionService.findSession(serverGroup, sessionId); 
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
	 * @see com.soffid.iam.pam.service.PamSessionService#java.util.List<com.soffid.iam.pam.api.JumpServerGroup> findJumpServerGroups()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.pam.api.JumpServerGroup> findJumpServerGroups()
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.pamSessionService.findJumpServerGroups(); 
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
	 * @see com.soffid.iam.pam.service.PamSessionService#java.util.List<com.soffid.iam.pam.api.PamSession> search(java.lang.String jumpServerGroup, java.lang.String url, java.lang.String text, java.lang.String screenshots, java.lang.String user, java.util.Date since, java.util.Date until)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.pam.api.PamSession> search(
		final java.lang.String jumpServerGroup, 
		final java.lang.String url, 
		final java.lang.String text, 
		final java.lang.String screenshots, 
		final java.lang.String user, 
		final java.util.Date since, 
		final java.util.Date until)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("pamSession:query"))
			throw new SecurityException("Unable to execute PamSessionService.search. Required roles: [pamSession:query]");
		try
		{
			return this.pamSessionService.search(jumpServerGroup, url, text, screenshots, user, since, until); 
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
	 * @see com.soffid.iam.pam.service.PamSessionService#java.util.List<com.soffid.iam.pam.api.PamSession> search(java.lang.String jumpServerGroup, java.lang.String url, java.lang.String text, java.lang.String user, java.util.Date since, java.util.Date until)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.pam.api.PamSession> search(
		final java.lang.String jumpServerGroup, 
		final java.lang.String url, 
		final java.lang.String text, 
		final java.lang.String user, 
		final java.util.Date since, 
		final java.util.Date until)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("pamSession:query"))
			throw new SecurityException("Unable to execute PamSessionService.search. Required roles: [pamSession:query]");
		try
		{
			return this.pamSessionService.search(jumpServerGroup, url, text, user, since, until); 
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
	 * @see com.soffid.iam.pam.service.PamSessionService#long getVideoSize(com.soffid.iam.pam.api.PamSession session, long chapter)
	 */
	@jakarta.annotation.security.PermitAll
	public long getVideoSize(
		final com.soffid.iam.pam.api.PamSession session, 
		final long chapter)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("pamSession:query"))
			throw new SecurityException("Unable to execute PamSessionService.getVideoSize. Required roles: [pamSession:query]");
		try
		{
			return this.pamSessionService.getVideoSize(session, chapter); 
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
	 * @see com.soffid.iam.pam.service.PamSessionService#void generateKeystrokes(com.soffid.iam.pam.api.PamSession session, java.io.OutputStream stream)
	 */
	@jakarta.annotation.security.PermitAll
	public void generateKeystrokes(
		final com.soffid.iam.pam.api.PamSession session, 
		final java.io.OutputStream stream)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("pamSession:query"))
			throw new SecurityException("Unable to execute PamSessionService.generateKeystrokes. Required roles: [pamSession:query]");
		try
		{
			this.pamSessionService.generateKeystrokes(session, stream); 
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
	 * @see com.soffid.iam.pam.service.PamSessionService#void generateVideo(com.soffid.iam.pam.api.PamSession session, long chapter, java.io.OutputStream stream, long start, long end)
	 */
	@jakarta.annotation.security.PermitAll
	public void generateVideo(
		final com.soffid.iam.pam.api.PamSession session, 
		final long chapter, 
		final java.io.OutputStream stream, 
		final long start, 
		final long end)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("pamSession:query"))
			throw new SecurityException("Unable to execute PamSessionService.generateVideo. Required roles: [pamSession:query]");
		try
		{
			this.pamSessionService.generateVideo(session, chapter, stream, start, end); 
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
	 * @see com.soffid.iam.pam.service.PamSessionService#void remove(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup)
	 */
	@jakarta.annotation.security.PermitAll
	public void remove(
		final com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("jumpServer:delete"))
			throw new SecurityException("Unable to execute PamSessionService.remove. Required roles: [jumpServer:delete]");
		try
		{
			this.pamSessionService.remove(jumpServerGroup); 
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

		this.pamSessionService = (com.soffid.iam.pam.service.PamSessionService)
		getBeanFactory().getBean("com.soffid.iam.pam.service.PamSessionService");
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
