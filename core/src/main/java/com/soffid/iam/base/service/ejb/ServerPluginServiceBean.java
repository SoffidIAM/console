//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service.ejb;
/**
 * @see <code>com.soffid.iam.base.service.ServerPluginService</code>,
 * @see <code>com.soffid.iam.base.service.ServerPluginService</code>,
 */
@jakarta.ejb.Stateless(name="ServerPluginService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.base.service.ServerPluginService")
@jakarta.ejb.Local(com.soffid.iam.base.service.ejb.ServerPluginService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class ServerPluginServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.base.service.ejb.ServerPluginService
{
	private com.soffid.iam.base.service.ServerPluginService serverPluginService;

	/**
	 * @see com.soffid.iam.base.service.ServerPluginService#boolean deployPlugin(byte[] i)
	 */
	@jakarta.annotation.security.PermitAll
	public boolean deployPlugin(
		final byte[] i)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.DuplicatedClassException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("plugins:update"))
			throw new SecurityException("Unable to execute ServerPluginService.deployPlugin. Required roles: [plugins:update]");
		try
		{
			return this.serverPluginService.deployPlugin(i); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.DuplicatedClassException)
				throw (com.soffid.iam.exception.DuplicatedClassException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.base.service.ServerPluginService#com.soffid.iam.base.api.AgentDescriptor getAgentDescriptor(java.lang.String className)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.AgentDescriptor getAgentDescriptor(
		final java.lang.String className)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("plugins:query"))
			throw new SecurityException("Unable to execute ServerPluginService.getAgentDescriptor. Required roles: [plugins:query]");
		try
		{
			return this.serverPluginService.getAgentDescriptor(className); 
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
	 * @see com.soffid.iam.base.service.ServerPluginService#java.lang.String getServerVersion()
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String getServerVersion()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("plugins:update"))
			throw new SecurityException("Unable to execute ServerPluginService.getServerVersion. Required roles: [plugins:update]");
		try
		{
			return this.serverPluginService.getServerVersion(); 
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
	 * @see com.soffid.iam.base.service.ServerPluginService#java.util.Collection<com.soffid.iam.base.api.AgentDescriptorWorkflow> findAgentDescriptorWorkflows(com.soffid.iam.base.api.AgentDescriptor agent)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.AgentDescriptorWorkflow> findAgentDescriptorWorkflows(
		final com.soffid.iam.base.api.AgentDescriptor agent)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("plugins:query"))
			throw new SecurityException("Unable to execute ServerPluginService.findAgentDescriptorWorkflows. Required roles: [plugins:query]");
		try
		{
			return this.serverPluginService.findAgentDescriptorWorkflows(agent); 
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
	 * @see com.soffid.iam.base.service.ServerPluginService#java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> getAgentDescriptors()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> getAgentDescriptors()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("plugins:query"))
			throw new SecurityException("Unable to execute ServerPluginService.getAgentDescriptors. Required roles: [plugins:query]");
		try
		{
			return this.serverPluginService.getAgentDescriptors(); 
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
	 * @see com.soffid.iam.base.service.ServerPluginService#java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> getAllAgentDescriptorsInfo()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> getAllAgentDescriptorsInfo()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("plugins:update"))
			throw new SecurityException("Unable to execute ServerPluginService.getAllAgentDescriptorsInfo. Required roles: [plugins:update]");
		try
		{
			return this.serverPluginService.getAllAgentDescriptorsInfo(); 
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
	 * @see com.soffid.iam.base.service.ServerPluginService#java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> getPluginAgentDescriptors(com.soffid.iam.base.api.ServerPlugin plugin)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> getPluginAgentDescriptors(
		final com.soffid.iam.base.api.ServerPlugin plugin)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("plugins:update"))
			throw new SecurityException("Unable to execute ServerPluginService.getPluginAgentDescriptors. Required roles: [plugins:update]");
		try
		{
			return this.serverPluginService.getPluginAgentDescriptors(plugin); 
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
	 * @see com.soffid.iam.base.service.ServerPluginService#java.util.Collection<com.soffid.iam.base.api.ServerPlugin> listServerPlugins()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.ServerPlugin> listServerPlugins()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("plugins:update"))
			throw new SecurityException("Unable to execute ServerPluginService.listServerPlugins. Required roles: [plugins:update]");
		try
		{
			return this.serverPluginService.listServerPlugins(); 
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
	 * @see com.soffid.iam.base.service.ServerPluginService#void deletePlugin(com.soffid.iam.base.api.ServerPlugin plugin)
	 */
	@jakarta.annotation.security.PermitAll
	public void deletePlugin(
		final com.soffid.iam.base.api.ServerPlugin plugin)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("plugins:update"))
			throw new SecurityException("Unable to execute ServerPluginService.deletePlugin. Required roles: [plugins:update]");
		try
		{
			this.serverPluginService.deletePlugin(plugin); 
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
	 * @see com.soffid.iam.base.service.ServerPluginService#void enablePlugin(com.soffid.iam.base.api.ServerPlugin plugin, boolean status)
	 */
	@jakarta.annotation.security.PermitAll
	public void enablePlugin(
		final com.soffid.iam.base.api.ServerPlugin plugin, 
		final boolean status)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("plugins:update"))
			throw new SecurityException("Unable to execute ServerPluginService.enablePlugin. Required roles: [plugins:update]");
		try
		{
			this.serverPluginService.enablePlugin(plugin, status); 
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
	 * @see com.soffid.iam.base.service.ServerPluginService#void updatePlugin(com.soffid.iam.base.api.ServerPlugin plugin)
	 */
	@jakarta.annotation.security.PermitAll
	public void updatePlugin(
		final com.soffid.iam.base.api.ServerPlugin plugin)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("plugins:update"))
			throw new SecurityException("Unable to execute ServerPluginService.updatePlugin. Required roles: [plugins:update]");
		try
		{
			this.serverPluginService.updatePlugin(plugin); 
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

		this.serverPluginService = (com.soffid.iam.base.service.ServerPluginService)
		getBeanFactory().getBean("com.soffid.iam.base.service.ServerPluginService");
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
