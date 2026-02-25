//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.service.ejb;
/**
 * @see <code>com.soffid.iam.pam.service.NetworkDiscoveryService</code>,
 * @see <code>com.soffid.iam.pam.service.NetworkDiscoveryService</code>,
 */
@jakarta.ejb.Stateless(name="NetworkDiscoveryService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.pam.service.NetworkDiscoveryService")
@jakarta.ejb.Local(com.soffid.iam.pam.service.ejb.NetworkDiscoveryService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class NetworkDiscoveryServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.pam.service.ejb.NetworkDiscoveryService
{
	private com.soffid.iam.pam.service.NetworkDiscoveryService networkDiscoveryService;

	/**
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#com.soffid.iam.iga.api.System createSystemCandidate(com.soffid.iam.am.api.Host host, java.lang.String type, java.lang.String userName, com.soffid.iam.am.api.Password password, java.lang.String instance)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.System createSystemCandidate(
		final com.soffid.iam.am.api.Host host, 
		final java.lang.String type, 
		final java.lang.String userName, 
		final com.soffid.iam.am.api.Password password, 
		final java.lang.String instance)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("networkDiscovery:schedule"))
			throw new SecurityException("Unable to execute NetworkDiscoveryService.createSystemCandidate. Required roles: [networkDiscovery:schedule]");
		try
		{
			return this.networkDiscoveryService.createSystemCandidate(host, type, userName, password, instance); 
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
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#com.soffid.iam.pam.api.HostService createHostService(com.soffid.iam.pam.api.HostService service)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.pam.api.HostService createHostService(
		final com.soffid.iam.pam.api.HostService service)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("networkDiscovery:schedule"))
			throw new SecurityException("Unable to execute NetworkDiscoveryService.createHostService. Required roles: [networkDiscovery:schedule]");
		try
		{
			return this.networkDiscoveryService.createHostService(service); 
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
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#com.soffid.iam.pam.api.HostService updateHostService(com.soffid.iam.pam.api.HostService service)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.pam.api.HostService updateHostService(
		final com.soffid.iam.pam.api.HostService service)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("networkDiscovery:schedule"))
			throw new SecurityException("Unable to execute NetworkDiscoveryService.updateHostService. Required roles: [networkDiscovery:schedule]");
		try
		{
			return this.networkDiscoveryService.updateHostService(service); 
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
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#com.soffid.iam.sync.api.ScheduledTask findNetworkDiscoveryScheduledTask(com.soffid.iam.am.api.Network network)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.sync.api.ScheduledTask findNetworkDiscoveryScheduledTask(
		final com.soffid.iam.am.api.Network network)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("networkDiscovery:schedule"))
			throw new SecurityException("Unable to execute NetworkDiscoveryService.findNetworkDiscoveryScheduledTask. Required roles: [networkDiscovery:schedule]");
		try
		{
			return this.networkDiscoveryService.findNetworkDiscoveryScheduledTask(network); 
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
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#java.util.List<com.soffid.iam.am.api.AccessTree> findHostEntryPoints(com.soffid.iam.am.api.Host host)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.am.api.AccessTree> findHostEntryPoints(
		final com.soffid.iam.am.api.Host host)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("networkDiscovery:query"))
			throw new SecurityException("Unable to execute NetworkDiscoveryService.findHostEntryPoints. Required roles: [networkDiscovery:query]");
		try
		{
			return this.networkDiscoveryService.findHostEntryPoints(host); 
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
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#java.util.List<com.soffid.iam.pam.api.HostPort> findHostPorts(com.soffid.iam.am.api.Host host)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.pam.api.HostPort> findHostPorts(
		final com.soffid.iam.am.api.Host host)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("networkDiscovery:query"))
			throw new SecurityException("Unable to execute NetworkDiscoveryService.findHostPorts. Required roles: [networkDiscovery:query]");
		try
		{
			return this.networkDiscoveryService.findHostPorts(host); 
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
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#java.util.List<com.soffid.iam.pam.api.HostService> findHostServices(com.soffid.iam.am.api.Host host)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.pam.api.HostService> findHostServices(
		final com.soffid.iam.am.api.Host host)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("networkDiscovery:query"))
			throw new SecurityException("Unable to execute NetworkDiscoveryService.findHostServices. Required roles: [networkDiscovery:query]");
		try
		{
			return this.networkDiscoveryService.findHostServices(host); 
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
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#java.util.List<com.soffid.iam.iga.api.System> findHostSystems(com.soffid.iam.am.api.Host host)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.iga.api.System> findHostSystems(
		final com.soffid.iam.am.api.Host host)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("networkDiscovery:query"))
			throw new SecurityException("Unable to execute NetworkDiscoveryService.findHostSystems. Required roles: [networkDiscovery:query]");
		try
		{
			return this.networkDiscoveryService.findHostSystems(host); 
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
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#java.util.List<com.soffid.iam.base.api.Account> findNetworkAccount(com.soffid.iam.am.api.Network network)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.base.api.Account> findNetworkAccount(
		final com.soffid.iam.am.api.Network network)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("networkDiscovery:schedule"))
			throw new SecurityException("Unable to execute NetworkDiscoveryService.findNetworkAccount. Required roles: [networkDiscovery:schedule]");
		try
		{
			return this.networkDiscoveryService.findNetworkAccount(network); 
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
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#java.util.List<com.soffid.iam.am.api.Host> findSystemHosts(com.soffid.iam.iga.api.System system)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.am.api.Host> findSystemHosts(
		final com.soffid.iam.iga.api.System system)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("networkDiscovery:query"))
			throw new SecurityException("Unable to execute NetworkDiscoveryService.findSystemHosts. Required roles: [networkDiscovery:query]");
		try
		{
			return this.networkDiscoveryService.findSystemHosts(system); 
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
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#void createNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account)
	 */
	@jakarta.annotation.security.PermitAll
	public void createNetworkAccount(
		final com.soffid.iam.am.api.Network network, 
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("networkDiscovery:schedule"))
			throw new SecurityException("Unable to execute NetworkDiscoveryService.createNetworkAccount. Required roles: [networkDiscovery:schedule]");
		try
		{
			this.networkDiscoveryService.createNetworkAccount(network, account); 
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
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#void deleteHostService(com.soffid.iam.pam.api.HostService service)
	 */
	@jakarta.annotation.security.PermitAll
	public void deleteHostService(
		final com.soffid.iam.pam.api.HostService service)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("networkDiscovery:schedule"))
			throw new SecurityException("Unable to execute NetworkDiscoveryService.deleteHostService. Required roles: [networkDiscovery:schedule]");
		try
		{
			this.networkDiscoveryService.deleteHostService(service); 
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
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#void disconnectSystemFromHost(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System system)
	 */
	@jakarta.annotation.security.PermitAll
	public void disconnectSystemFromHost(
		final com.soffid.iam.am.api.Host host, 
		final com.soffid.iam.iga.api.System system)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("networkDiscovery:schedule"))
			throw new SecurityException("Unable to execute NetworkDiscoveryService.disconnectSystemFromHost. Required roles: [networkDiscovery:schedule]");
		try
		{
			this.networkDiscoveryService.disconnectSystemFromHost(host, system); 
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
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#void enableNetworkDiscoveryScheduledTask(com.soffid.iam.am.api.Network network)
	 */
	@jakarta.annotation.security.PermitAll
	public void enableNetworkDiscoveryScheduledTask(
		final com.soffid.iam.am.api.Network network)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("networkDiscovery:schedule"))
			throw new SecurityException("Unable to execute NetworkDiscoveryService.enableNetworkDiscoveryScheduledTask. Required roles: [networkDiscovery:schedule]");
		try
		{
			this.networkDiscoveryService.enableNetworkDiscoveryScheduledTask(network); 
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
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#void registerHostEntryPoint(com.soffid.iam.am.api.Host host, com.soffid.iam.am.api.AccessTree entryPoint)
	 */
	@jakarta.annotation.security.PermitAll
	public void registerHostEntryPoint(
		final com.soffid.iam.am.api.Host host, 
		final com.soffid.iam.am.api.AccessTree entryPoint)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:create"))
			throw new SecurityException("Unable to execute NetworkDiscoveryService.registerHostEntryPoint. Required roles: [agent:create]");
		try
		{
			this.networkDiscoveryService.registerHostEntryPoint(host, entryPoint); 
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
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#void registerHostSystem(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System system)
	 */
	@jakarta.annotation.security.PermitAll
	public void registerHostSystem(
		final com.soffid.iam.am.api.Host host, 
		final com.soffid.iam.iga.api.System system)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:create"))
			throw new SecurityException("Unable to execute NetworkDiscoveryService.registerHostSystem. Required roles: [agent:create]");
		try
		{
			this.networkDiscoveryService.registerHostSystem(host, system); 
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
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#void removeNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account)
	 */
	@jakarta.annotation.security.PermitAll
	public void removeNetworkAccount(
		final com.soffid.iam.am.api.Network network, 
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("networkDiscovery:schedule"))
			throw new SecurityException("Unable to execute NetworkDiscoveryService.removeNetworkAccount. Required roles: [networkDiscovery:schedule]");
		try
		{
			this.networkDiscoveryService.removeNetworkAccount(network, account); 
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
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#void startDiscovery(com.soffid.iam.am.api.Network network)
	 */
	@jakarta.annotation.security.PermitAll
	public void startDiscovery(
		final com.soffid.iam.am.api.Network network)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("networkDiscovery:schedule"))
			throw new SecurityException("Unable to execute NetworkDiscoveryService.startDiscovery. Required roles: [networkDiscovery:schedule]");
		try
		{
			this.networkDiscoveryService.startDiscovery(network); 
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

		this.networkDiscoveryService = (com.soffid.iam.pam.service.NetworkDiscoveryService)
		getBeanFactory().getBean("com.soffid.iam.pam.service.NetworkDiscoveryService");
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
