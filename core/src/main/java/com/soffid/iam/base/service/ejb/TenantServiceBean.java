//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service.ejb;
/**
 * @see <code>com.soffid.iam.base.service.TenantService</code>,
 * @see <code>com.soffid.iam.base.service.TenantService</code>,
 */
@jakarta.ejb.Stateless(name="TenantService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.base.service.TenantService")
@jakarta.ejb.Local(com.soffid.iam.base.service.ejb.TenantService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class TenantServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.base.service.ejb.TenantService
{
	private com.soffid.iam.base.service.TenantService tenantService;

	/**
	 * @see com.soffid.iam.base.service.TenantService#com.soffid.iam.base.api.Tenant importTenant(java.io.InputStream in)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.Tenant importTenant(
		final java.io.InputStream in)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("tenant:create"))
			throw new SecurityException("Unable to execute TenantService.importTenant. Required roles: [tenant:create]");
		try
		{
			return this.tenantService.importTenant(in); 
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
	 * @see com.soffid.iam.base.service.TenantService#com.soffid.iam.base.api.Tenant create(com.soffid.iam.base.api.Tenant tenant)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.Tenant create(
		final com.soffid.iam.base.api.Tenant tenant)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("tenant:create"))
			throw new SecurityException("Unable to execute TenantService.create. Required roles: [tenant:create]");
		try
		{
			return this.tenantService.create(tenant); 
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
	 * @see com.soffid.iam.base.service.TenantService#com.soffid.iam.base.api.Tenant getMasterTenant()
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.Tenant getMasterTenant()
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("tenant:query"))
			throw new SecurityException("Unable to execute TenantService.getMasterTenant. Required roles: [tenant:query]");
		try
		{
			return this.tenantService.getMasterTenant(); 
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
	 * @see com.soffid.iam.base.service.TenantService#com.soffid.iam.base.api.Tenant getTenant(java.lang.Long id)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.Tenant getTenant(
		final java.lang.Long id)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("tenant:query"))
			throw new SecurityException("Unable to execute TenantService.getTenant. Required roles: [tenant:query]");
		try
		{
			return this.tenantService.getTenant(id); 
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
	 * @see com.soffid.iam.base.service.TenantService#com.soffid.iam.base.api.Tenant getTenant(java.lang.String name)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.Tenant getTenant(
		final java.lang.String name)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("tenant:query"))
			throw new SecurityException("Unable to execute TenantService.getTenant. Required roles: [tenant:query]");
		try
		{
			return this.tenantService.getTenant(name); 
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
	 * @see com.soffid.iam.base.service.TenantService#com.soffid.iam.base.api.Tenant update(com.soffid.iam.base.api.Tenant tenant)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.Tenant update(
		final com.soffid.iam.base.api.Tenant tenant)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("tenant:update"))
			throw new SecurityException("Unable to execute TenantService.update. Required roles: [tenant:update]");
		try
		{
			return this.tenantService.update(tenant); 
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
	 * @see com.soffid.iam.base.service.TenantService#java.util.Collection<com.soffid.iam.base.api.Tenant> listTenants()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.Tenant> listTenants()
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.tenantService.listTenants(); 
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
	 * @see com.soffid.iam.base.service.TenantService#java.util.List<java.lang.String> getDisabledPermissions(com.soffid.iam.base.api.Tenant tenant)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<java.lang.String> getDisabledPermissions(
		final com.soffid.iam.base.api.Tenant tenant)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("tenant:update"))
			throw new SecurityException("Unable to execute TenantService.getDisabledPermissions. Required roles: [tenant:update]");
		try
		{
			return this.tenantService.getDisabledPermissions(tenant); 
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
	 * @see com.soffid.iam.base.service.TenantService#java.util.List<java.lang.String> getTenantServers(com.soffid.iam.base.api.Tenant tenant)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<java.lang.String> getTenantServers(
		final com.soffid.iam.base.api.Tenant tenant)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("tenant:update"))
			throw new SecurityException("Unable to execute TenantService.getTenantServers. Required roles: [tenant:update]");
		try
		{
			return this.tenantService.getTenantServers(tenant); 
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
	 * @see com.soffid.iam.base.service.TenantService#void addTenantServer(com.soffid.iam.base.api.Tenant tenant, java.lang.String server)
	 */
	@jakarta.annotation.security.PermitAll
	public void addTenantServer(
		final com.soffid.iam.base.api.Tenant tenant, 
		final java.lang.String server)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("tenant:update"))
			throw new SecurityException("Unable to execute TenantService.addTenantServer. Required roles: [tenant:update]");
		try
		{
			this.tenantService.addTenantServer(tenant, server); 
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
	 * @see com.soffid.iam.base.service.TenantService#void disablePermission(com.soffid.iam.base.api.Tenant tenant, java.lang.String permission)
	 */
	@jakarta.annotation.security.PermitAll
	public void disablePermission(
		final com.soffid.iam.base.api.Tenant tenant, 
		final java.lang.String permission)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("tenant:update"))
			throw new SecurityException("Unable to execute TenantService.disablePermission. Required roles: [tenant:update]");
		try
		{
			this.tenantService.disablePermission(tenant, permission); 
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
	 * @see com.soffid.iam.base.service.TenantService#void enablePermission(com.soffid.iam.base.api.Tenant tenant, java.lang.String permission)
	 */
	@jakarta.annotation.security.PermitAll
	public void enablePermission(
		final com.soffid.iam.base.api.Tenant tenant, 
		final java.lang.String permission)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("tenant:update"))
			throw new SecurityException("Unable to execute TenantService.enablePermission. Required roles: [tenant:update]");
		try
		{
			this.tenantService.enablePermission(tenant, permission); 
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
	 * @see com.soffid.iam.base.service.TenantService#void remove(com.soffid.iam.base.api.Tenant tenant)
	 */
	@jakarta.annotation.security.PermitAll
	public void remove(
		final com.soffid.iam.base.api.Tenant tenant)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("tenant:create"))
			throw new SecurityException("Unable to execute TenantService.remove. Required roles: [tenant:create]");
		try
		{
			this.tenantService.remove(tenant); 
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
	 * @see com.soffid.iam.base.service.TenantService#void removeTenantServer(com.soffid.iam.base.api.Tenant tenant, java.lang.String server)
	 */
	@jakarta.annotation.security.PermitAll
	public void removeTenantServer(
		final com.soffid.iam.base.api.Tenant tenant, 
		final java.lang.String server)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("tenant:update"))
			throw new SecurityException("Unable to execute TenantService.removeTenantServer. Required roles: [tenant:update]");
		try
		{
			this.tenantService.removeTenantServer(tenant, server); 
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
	 * @see com.soffid.iam.base.service.TenantService#void exportTenant(com.soffid.iam.base.api.Tenant tenant, java.io.OutputStream out)
	 */
	@jakarta.annotation.security.PermitAll
	public void exportTenant(
		final com.soffid.iam.base.api.Tenant tenant, 
		final java.io.OutputStream out)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("tenant:update"))
			throw new SecurityException("Unable to execute TenantService.exportTenant. Required roles: [tenant:update]");
		try
		{
			this.tenantService.exportTenant(tenant, out); 
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

		this.tenantService = (com.soffid.iam.base.service.TenantService)
		getBeanFactory().getBean("com.soffid.iam.base.service.TenantService");
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
