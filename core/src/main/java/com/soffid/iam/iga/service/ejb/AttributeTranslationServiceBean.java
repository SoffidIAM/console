//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * @see <code>com.soffid.iam.iga.service.AttributeTranslationService</code>,
 * @see <code>com.soffid.iam.iga.service.AttributeTranslationService</code>,
 */
@jakarta.ejb.Stateless(name="AttributeTranslationService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.iga.service.AttributeTranslationService")
@jakarta.ejb.Local(com.soffid.iam.iga.service.ejb.AttributeTranslationService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class AttributeTranslationServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.iga.service.ejb.AttributeTranslationService
{
	private com.soffid.iam.iga.service.AttributeTranslationService attributeTranslationService;

	/**
	 * @see com.soffid.iam.iga.service.AttributeTranslationService#com.soffid.iam.iga.api.AttributeTranslation create(com.soffid.iam.iga.api.AttributeTranslation att)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.AttributeTranslation create(
		final com.soffid.iam.iga.api.AttributeTranslation att)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("attributeTranslation:create"))
			throw new SecurityException("Unable to execute AttributeTranslationService.create. Required roles: [attributeTranslation:create]");
		try
		{
			return this.attributeTranslationService.create(att); 
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
	 * @see com.soffid.iam.iga.service.AttributeTranslationService#com.soffid.iam.iga.api.AttributeTranslation update(com.soffid.iam.iga.api.AttributeTranslation att)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.AttributeTranslation update(
		final com.soffid.iam.iga.api.AttributeTranslation att)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("attributeTranslation:create"))
			throw new SecurityException("Unable to execute AttributeTranslationService.update. Required roles: [attributeTranslation:create]");
		try
		{
			return this.attributeTranslationService.update(att); 
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
	 * @see com.soffid.iam.iga.service.AttributeTranslationService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.AttributeTranslation> findByQuery(com.soffid.zkdb.api.Query query)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.AttributeTranslation> findByQuery(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("attributeTranslation:query"))
			throw new SecurityException("Unable to execute AttributeTranslationService.findByQuery. Required roles: [attributeTranslation:query]");
		try
		{
			return this.attributeTranslationService.findByQuery(query); 
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
	 * @see com.soffid.iam.iga.service.AttributeTranslationService#java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> findByColumn1(java.lang.String domain, java.lang.String column1)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> findByColumn1(
		final java.lang.String domain, 
		final java.lang.String column1)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("attributeTranslation:query"))
			throw new SecurityException("Unable to execute AttributeTranslationService.findByColumn1. Required roles: [attributeTranslation:query]");
		try
		{
			return this.attributeTranslationService.findByColumn1(domain, column1); 
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
	 * @see com.soffid.iam.iga.service.AttributeTranslationService#java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> findByColumn2(java.lang.String domain, java.lang.String column2)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> findByColumn2(
		final java.lang.String domain, 
		final java.lang.String column2)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("attributeTranslation:query"))
			throw new SecurityException("Unable to execute AttributeTranslationService.findByColumn2. Required roles: [attributeTranslation:query]");
		try
		{
			return this.attributeTranslationService.findByColumn2(domain, column2); 
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
	 * @see com.soffid.iam.iga.service.AttributeTranslationService#java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> findByExample(java.lang.String domain, java.lang.String column1, java.lang.String column2)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> findByExample(
		final java.lang.String domain, 
		final java.lang.String column1, 
		final java.lang.String column2)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("attributeTranslation:query"))
			throw new SecurityException("Unable to execute AttributeTranslationService.findByExample. Required roles: [attributeTranslation:query]");
		try
		{
			return this.attributeTranslationService.findByExample(domain, column1, column2); 
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
	 * @see com.soffid.iam.iga.service.AttributeTranslationService#java.util.Collection<java.lang.String> findDomains()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<java.lang.String> findDomains()
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("attributeTranslation:query"))
			throw new SecurityException("Unable to execute AttributeTranslationService.findDomains. Required roles: [attributeTranslation:query]");
		try
		{
			return this.attributeTranslationService.findDomains(); 
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
	 * @see com.soffid.iam.iga.service.AttributeTranslationService#void delete(com.soffid.iam.iga.api.AttributeTranslation att)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.AttributeTranslation att)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("attributeTranslation:delete"))
			throw new SecurityException("Unable to execute AttributeTranslationService.delete. Required roles: [attributeTranslation:delete]");
		try
		{
			this.attributeTranslationService.delete(att); 
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

		this.attributeTranslationService = (com.soffid.iam.iga.service.AttributeTranslationService)
		getBeanFactory().getBean("com.soffid.iam.iga.service.AttributeTranslationService");
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
