//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service.ejb;
/**
 * @see <code>com.soffid.iam.rc.service.NetworkIntelligenceService</code>,
 * @see <code>com.soffid.iam.rc.service.NetworkIntelligenceService</code>,
 */
@jakarta.ejb.Stateless(name="NetworkIntelligenceService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.rc.service.NetworkIntelligenceService")
@jakarta.ejb.Local(com.soffid.iam.rc.service.ejb.NetworkIntelligenceService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class NetworkIntelligenceServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.rc.service.ejb.NetworkIntelligenceService
{
	private com.soffid.iam.rc.service.NetworkIntelligenceService networkIntelligenceService;

	/**
	 * @see com.soffid.iam.rc.service.NetworkIntelligenceService#com.soffid.iam.rc.api.NetworkIntelligence getConfiguration()
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.rc.api.NetworkIntelligence getConfiguration()
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("networkIntelligence:configure"))
			throw new SecurityException("Unable to execute NetworkIntelligenceService.getConfiguration. Required roles: [networkIntelligence:configure]");
		try
		{
			return this.networkIntelligenceService.getConfiguration(); 
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
	 * @see com.soffid.iam.rc.service.NetworkIntelligenceService#com.soffid.iam.rc.api.NetworkIntelligence saveConfiguration(com.soffid.iam.rc.api.NetworkIntelligence ni)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.rc.api.NetworkIntelligence saveConfiguration(
		final com.soffid.iam.rc.api.NetworkIntelligence ni)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("networkIntelligence:configure"))
			throw new SecurityException("Unable to execute NetworkIntelligenceService.saveConfiguration. Required roles: [networkIntelligence:configure]");
		try
		{
			return this.networkIntelligenceService.saveConfiguration(ni); 
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
	 * @see com.soffid.iam.rc.service.NetworkIntelligenceService#com.soffid.iam.rc.api.NetworkIntelligence validateToken(java.lang.String token)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.rc.api.NetworkIntelligence validateToken(
		final java.lang.String token)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("networkIntelligence:configure"))
			throw new SecurityException("Unable to execute NetworkIntelligenceService.validateToken. Required roles: [networkIntelligence:configure]");
		try
		{
			return this.networkIntelligenceService.validateToken(token); 
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
	 * @see com.soffid.iam.rc.service.NetworkIntelligenceService#java.lang.Boolean isAccountBreached(java.lang.String account, java.lang.String system)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.Boolean isAccountBreached(
		final java.lang.String account, 
		final java.lang.String system)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkIntelligenceService.isAccountBreached(account, system); 
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
	 * @see com.soffid.iam.rc.service.NetworkIntelligenceService#java.lang.Boolean isPasswordBreached(java.lang.String password)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.Boolean isPasswordBreached(
		final java.lang.String password)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkIntelligenceService.isPasswordBreached(password); 
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
	 * @see com.soffid.iam.rc.service.NetworkIntelligenceService#java.lang.String isEmailBreached(java.lang.String shortName, java.lang.String mailDomain)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String isEmailBreached(
		final java.lang.String shortName, 
		final java.lang.String mailDomain)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkIntelligenceService.isEmailBreached(shortName, mailDomain); 
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
	 * @see com.soffid.iam.rc.service.NetworkIntelligenceService#void verifyDomains(java.io.PrintWriter out)
	 */
	@jakarta.annotation.security.PermitAll
	public void verifyDomains(
		final java.io.PrintWriter out)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.networkIntelligenceService.verifyDomains(out); 
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

		this.networkIntelligenceService = (com.soffid.iam.rc.service.NetworkIntelligenceService)
		getBeanFactory().getBean("com.soffid.iam.rc.service.NetworkIntelligenceService");
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
