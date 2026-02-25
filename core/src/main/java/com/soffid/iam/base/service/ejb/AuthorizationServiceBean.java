//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service.ejb;
/**
 * @see <code>com.soffid.iam.base.service.AuthorizationService</code>,
 * @see <code>com.soffid.iam.base.service.AuthorizationService</code>,
 */
@jakarta.ejb.Stateless(name="AuthorizationService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.base.service.AuthorizationService")
@jakarta.ejb.Local(com.soffid.iam.base.service.ejb.AuthorizationService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class AuthorizationServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.base.service.ejb.AuthorizationService
{
	private com.soffid.iam.base.service.AuthorizationService authorizationService;

	/**
	 * @see com.soffid.iam.base.service.AuthorizationService#com.soffid.iam.base.api.AuthorizationRole create(com.soffid.iam.base.api.AuthorizationRole autoritzacio)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.AuthorizationRole create(
		final com.soffid.iam.base.api.AuthorizationRole autoritzacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("authorization:rol:create"))
			throw new SecurityException("Unable to execute AuthorizationService.create. Required roles: [authorization:rol:create]");
		try
		{
			return this.authorizationService.create(autoritzacio); 
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
	 * @see com.soffid.iam.base.service.AuthorizationService#com.soffid.iam.common.security.SoffidPrincipal getCurrentPrincipal()
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.common.security.SoffidPrincipal getCurrentPrincipal()
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.authorizationService.getCurrentPrincipal(); 
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
	 * @see com.soffid.iam.base.service.AuthorizationService#java.util.Collection findAuthorizations(java.lang.String ambit, java.lang.String descripcio, java.lang.String codi)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection findAuthorizations(
		final java.lang.String ambit, 
		final java.lang.String descripcio, 
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("authorization:query"))
			throw new SecurityException("Unable to execute AuthorizationService.findAuthorizations. Required roles: [authorization:query]");
		try
		{
			return this.authorizationService.findAuthorizations(ambit, descripcio, codi); 
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
	 * @see com.soffid.iam.base.service.AuthorizationService#java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getDescriptionUserAuthorizations()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getDescriptionUserAuthorizations()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.authorizationService.getDescriptionUserAuthorizations(); 
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
	 * @see com.soffid.iam.base.service.AuthorizationService#java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getDescriptionUserAuthorizations(java.lang.String userName)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getDescriptionUserAuthorizations(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.authorizationService.getDescriptionUserAuthorizations(userName); 
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
	 * @see com.soffid.iam.base.service.AuthorizationService#java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getAuthorizationRoles(java.lang.String authorization)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getAuthorizationRoles(
		final java.lang.String authorization)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.authorizationService.getAuthorizationRoles(authorization); 
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
	 * @see com.soffid.iam.base.service.AuthorizationService#java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getUserAuthorization(java.lang.String codiAutoritzacio)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getUserAuthorization(
		final java.lang.String codiAutoritzacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.authorizationService.getUserAuthorization(codiAutoritzacio); 
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
	 * @see com.soffid.iam.base.service.AuthorizationService#java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getUserAuthorization(java.lang.String codiAutoritzacio, java.lang.String codiUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getUserAuthorization(
		final java.lang.String codiAutoritzacio, 
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.authorizationService.getUserAuthorization(codiAutoritzacio, codiUsuari); 
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
	 * @see com.soffid.iam.base.service.AuthorizationService#java.util.List getScopeList()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List getScopeList()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("authorization:query"))
			throw new SecurityException("Unable to execute AuthorizationService.getScopeList. Required roles: [authorization:query]");
		try
		{
			return this.authorizationService.getScopeList(); 
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
	 * @see com.soffid.iam.base.service.AuthorizationService#void delete(com.soffid.iam.base.api.AuthorizationRole authorization)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.base.api.AuthorizationRole authorization)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("authorization:rol:delete"))
			throw new SecurityException("Unable to execute AuthorizationService.delete. Required roles: [authorization:rol:delete]");
		try
		{
			this.authorizationService.delete(authorization); 
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

		this.authorizationService = (com.soffid.iam.base.service.AuthorizationService)
		getBeanFactory().getBean("com.soffid.iam.base.service.AuthorizationService");
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
