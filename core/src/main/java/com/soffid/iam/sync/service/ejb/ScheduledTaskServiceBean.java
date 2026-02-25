//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.service.ejb;
/**
 * @see <code>com.soffid.iam.sync.service.ScheduledTaskService</code>,
 * @see <code>com.soffid.iam.sync.service.ScheduledTaskService</code>,
 */
@jakarta.ejb.Stateless(name="ScheduledTaskService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.sync.service.ScheduledTaskService")
@jakarta.ejb.Local(com.soffid.iam.sync.service.ejb.ScheduledTaskService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class ScheduledTaskServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.sync.service.ejb.ScheduledTaskService
{
	private com.soffid.iam.sync.service.ScheduledTaskService scheduledTaskService;

	/**
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#boolean isStopping(com.soffid.iam.sync.api.ScheduledTask task)
	 */
	@jakarta.annotation.security.PermitAll
	public boolean isStopping(
		final com.soffid.iam.sync.api.ScheduledTask task)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("schedule:admin"))
			throw new SecurityException("Unable to execute ScheduledTaskService.isStopping. Required roles: [schedule:admin]");
		try
		{
			return this.scheduledTaskService.isStopping(task); 
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
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#com.soffid.iam.sync.api.ScheduledTask create(com.soffid.iam.sync.api.ScheduledTask task)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.sync.api.ScheduledTask create(
		final com.soffid.iam.sync.api.ScheduledTask task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("schedule:admin"))
			throw new SecurityException("Unable to execute ScheduledTaskService.create. Required roles: [schedule:admin]");
		try
		{
			return this.scheduledTaskService.create(task); 
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
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#com.soffid.iam.sync.api.ScheduledTask findById(java.lang.Long id)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.sync.api.ScheduledTask findById(
		final java.lang.Long id)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("schedule:query"))
			throw new SecurityException("Unable to execute ScheduledTaskService.findById. Required roles: [schedule:query]");
		try
		{
			return this.scheduledTaskService.findById(id); 
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
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#com.soffid.iam.sync.api.ScheduledTask load(java.lang.Long taskId)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.sync.api.ScheduledTask load(
		final java.lang.Long taskId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("schedule:query"))
			throw new SecurityException("Unable to execute ScheduledTaskService.load. Required roles: [schedule:query]");
		try
		{
			return this.scheduledTaskService.load(taskId); 
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
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#com.soffid.iam.sync.api.ScheduledTask update(com.soffid.iam.sync.api.ScheduledTask task)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.sync.api.ScheduledTask update(
		final com.soffid.iam.sync.api.ScheduledTask task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("schedule:admin"))
			throw new SecurityException("Unable to execute ScheduledTaskService.update. Required roles: [schedule:admin]");
		try
		{
			return this.scheduledTaskService.update(task); 
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
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#java.util.List<com.soffid.iam.sync.api.ScheduledTask> listEnabledTasks()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.sync.api.ScheduledTask> listEnabledTasks()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("schedule:query"))
			throw new SecurityException("Unable to execute ScheduledTaskService.listEnabledTasks. Required roles: [schedule:query]");
		try
		{
			return this.scheduledTaskService.listEnabledTasks(); 
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
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#java.util.List<com.soffid.iam.sync.api.ScheduledTask> listServerTasks(java.lang.String server)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.sync.api.ScheduledTask> listServerTasks(
		final java.lang.String server)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("schedule:query"))
			throw new SecurityException("Unable to execute ScheduledTaskService.listServerTasks. Required roles: [schedule:query]");
		try
		{
			return this.scheduledTaskService.listServerTasks(server); 
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
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#java.util.List<com.soffid.iam.sync.api.ScheduledTask> listTasks()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.sync.api.ScheduledTask> listTasks()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("schedule:query"))
			throw new SecurityException("Unable to execute ScheduledTaskService.listTasks. Required roles: [schedule:query]");
		try
		{
			return this.scheduledTaskService.listTasks(); 
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
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#void remove(com.soffid.iam.sync.api.ScheduledTask task)
	 */
	@jakarta.annotation.security.PermitAll
	public void remove(
		final com.soffid.iam.sync.api.ScheduledTask task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("schedule:admin"))
			throw new SecurityException("Unable to execute ScheduledTaskService.remove. Required roles: [schedule:admin]");
		try
		{
			this.scheduledTaskService.remove(task); 
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
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#void startNow(com.soffid.iam.sync.api.ScheduledTask task)
	 */
	@jakarta.annotation.security.PermitAll
	public void startNow(
		final com.soffid.iam.sync.api.ScheduledTask task)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("schedule:query"))
			throw new SecurityException("Unable to execute ScheduledTaskService.startNow. Required roles: [schedule:query]");
		try
		{
			this.scheduledTaskService.startNow(task); 
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
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#void stop(com.soffid.iam.sync.api.ScheduledTask task)
	 */
	@jakarta.annotation.security.PermitAll
	public void stop(
		final com.soffid.iam.sync.api.ScheduledTask task)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("schedule:admin"))
			throw new SecurityException("Unable to execute ScheduledTaskService.stop. Required roles: [schedule:admin]");
		try
		{
			this.scheduledTaskService.stop(task); 
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

		this.scheduledTaskService = (com.soffid.iam.sync.service.ScheduledTaskService)
		getBeanFactory().getBean("com.soffid.iam.sync.service.ScheduledTaskService");
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
