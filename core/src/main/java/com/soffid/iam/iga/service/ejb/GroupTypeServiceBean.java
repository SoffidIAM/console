//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * @see <code>com.soffid.iam.iga.service.GroupTypeService</code>,
 * @see <code>com.soffid.iam.iga.service.GroupTypeService</code>,
 */
@jakarta.ejb.Stateless(name="GroupTypeService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.iga.service.GroupTypeService")
@jakarta.ejb.Local(com.soffid.iam.iga.service.ejb.GroupTypeService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class GroupTypeServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.iga.service.ejb.GroupTypeService
{
	private com.soffid.iam.iga.service.GroupTypeService groupTypeService;

	/**
	 * @see com.soffid.iam.iga.service.GroupTypeService#com.soffid.iam.iga.api.GroupType create(com.soffid.iam.iga.api.GroupType tipus)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.GroupType create(
		final com.soffid.iam.iga.api.GroupType tipus)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("organizationalUnit:create"))
			throw new SecurityException("Unable to execute GroupTypeService.create. Required roles: [organizationalUnit:create]");
		try
		{
			return this.groupTypeService.create(tipus); 
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
	 * @see com.soffid.iam.iga.service.GroupTypeService#com.soffid.iam.iga.api.GroupType findGroupTypeByName(java.lang.String CodiTipusUnitatOrganitzativa)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.GroupType findGroupTypeByName(
		final java.lang.String CodiTipusUnitatOrganitzativa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("organizationalUnit:query"))
			throw new SecurityException("Unable to execute GroupTypeService.findGroupTypeByName. Required roles: [organizationalUnit:query]");
		try
		{
			return this.groupTypeService.findGroupTypeByName(CodiTipusUnitatOrganitzativa); 
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
	 * @see com.soffid.iam.iga.service.GroupTypeService#com.soffid.iam.iga.api.GroupType update(com.soffid.iam.iga.api.GroupType tipus)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.GroupType update(
		final com.soffid.iam.iga.api.GroupType tipus)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("organizationalUnit:update"))
			throw new SecurityException("Unable to execute GroupTypeService.update. Required roles: [organizationalUnit:update]");
		try
		{
			return this.groupTypeService.update(tipus); 
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
	 * @see com.soffid.iam.iga.service.GroupTypeService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.GroupType> findGroupTypes(com.soffid.zkdb.api.Query q)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.GroupType> findGroupTypes(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("organizationalUnit:query"))
			throw new SecurityException("Unable to execute GroupTypeService.findGroupTypes. Required roles: [organizationalUnit:query]");
		try
		{
			return this.groupTypeService.findGroupTypes(q); 
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
	 * @see com.soffid.iam.iga.service.GroupTypeService#java.util.List<com.soffid.iam.iga.api.GroupType> findAllGroupTypes()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.iga.api.GroupType> findAllGroupTypes()
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("organizationalUnit:query"))
			throw new SecurityException("Unable to execute GroupTypeService.findAllGroupTypes. Required roles: [organizationalUnit:query]");
		try
		{
			return this.groupTypeService.findAllGroupTypes(); 
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
	 * @see com.soffid.iam.iga.service.GroupTypeService#void delete(com.soffid.iam.iga.api.GroupType tipus)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.GroupType tipus)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("organizationalUnit:delete"))
			throw new SecurityException("Unable to execute GroupTypeService.delete. Required roles: [organizationalUnit:delete]");
		try
		{
			this.groupTypeService.delete(tipus); 
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

		this.groupTypeService = (com.soffid.iam.iga.service.GroupTypeService)
		getBeanFactory().getBean("com.soffid.iam.iga.service.GroupTypeService");
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
