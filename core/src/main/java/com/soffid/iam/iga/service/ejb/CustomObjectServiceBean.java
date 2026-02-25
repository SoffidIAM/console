//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * @see <code>com.soffid.iam.iga.service.CustomObjectService</code>,
 * @see <code>com.soffid.iam.iga.service.CustomObjectService</code>,
 */
@jakarta.ejb.Stateless(name="CustomObjectService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.iga.service.CustomObjectService")
@jakarta.ejb.Local(com.soffid.iam.iga.service.ejb.CustomObjectService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class CustomObjectServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.iga.service.ejb.CustomObjectService
{
	private com.soffid.iam.iga.service.CustomObjectService customObjectService;

	/**
	 * @see com.soffid.iam.iga.service.CustomObjectService#com.soffid.iam.iga.api.CustomObject createCustomObject(com.soffid.iam.iga.api.CustomObject obj)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.CustomObject createCustomObject(
		final com.soffid.iam.iga.api.CustomObject obj)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("customObject:create"))
			throw new SecurityException("Unable to execute CustomObjectService.createCustomObject. Required roles: [customObject:create]");
		try
		{
			return this.customObjectService.createCustomObject(obj); 
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
	 * @see com.soffid.iam.iga.service.CustomObjectService#com.soffid.iam.iga.api.CustomObject findCustomObjectByTypeAndName(java.lang.String objectType, java.lang.String name)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.CustomObject findCustomObjectByTypeAndName(
		final java.lang.String objectType, 
		final java.lang.String name)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("customObject:query"))
			throw new SecurityException("Unable to execute CustomObjectService.findCustomObjectByTypeAndName. Required roles: [customObject:query]");
		try
		{
			return this.customObjectService.findCustomObjectByTypeAndName(objectType, name); 
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
	 * @see com.soffid.iam.iga.service.CustomObjectService#com.soffid.iam.iga.api.CustomObject updateCustomObject(com.soffid.iam.iga.api.CustomObject obj)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.CustomObject updateCustomObject(
		final com.soffid.iam.iga.api.CustomObject obj)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("customObject:update"))
			throw new SecurityException("Unable to execute CustomObjectService.updateCustomObject. Required roles: [customObject:update]");
		try
		{
			return this.customObjectService.updateCustomObject(obj); 
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
	 * @see com.soffid.iam.iga.service.CustomObjectService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.CustomObject> findCustomObjects(java.lang.String objectType, com.soffid.zkdb.api.Query query)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.CustomObject> findCustomObjects(
		final java.lang.String objectType, 
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("customObject:query"))
			throw new SecurityException("Unable to execute CustomObjectService.findCustomObjects. Required roles: [customObject:query]");
		try
		{
			return this.customObjectService.findCustomObjects(objectType, query); 
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
	 * @see com.soffid.iam.iga.service.CustomObjectService#void deleteCustomObject(com.soffid.iam.iga.api.CustomObject obj)
	 */
	@jakarta.annotation.security.PermitAll
	public void deleteCustomObject(
		final com.soffid.iam.iga.api.CustomObject obj)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("customObject:delete"))
			throw new SecurityException("Unable to execute CustomObjectService.deleteCustomObject. Required roles: [customObject:delete]");
		try
		{
			this.customObjectService.deleteCustomObject(obj); 
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

		this.customObjectService = (com.soffid.iam.iga.service.CustomObjectService)
		getBeanFactory().getBean("com.soffid.iam.iga.service.CustomObjectService");
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
