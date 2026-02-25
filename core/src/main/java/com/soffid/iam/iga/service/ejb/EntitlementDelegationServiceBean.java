//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * @see <code>com.soffid.iam.iga.service.EntitlementDelegationService</code>,
 * @see <code>com.soffid.iam.iga.service.EntitlementDelegationService</code>,
 */
@jakarta.ejb.Stateless(name="EntitlementDelegationService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.iga.service.EntitlementDelegationService")
@jakarta.ejb.Local(com.soffid.iam.iga.service.ejb.EntitlementDelegationService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class EntitlementDelegationServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.iga.service.ejb.EntitlementDelegationService
{
	private com.soffid.iam.iga.service.EntitlementDelegationService entitlementDelegationService;

	/**
	 * @see com.soffid.iam.iga.service.EntitlementDelegationService#com.soffid.iam.iga.api.RoleAccount acceptDelegation(com.soffid.iam.iga.api.RoleAccount ra)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.RoleAccount acceptDelegation(
		final com.soffid.iam.iga.api.RoleAccount ra)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entitlementDelegationService.acceptDelegation(ra); 
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
	 * @see com.soffid.iam.iga.service.EntitlementDelegationService#com.soffid.iam.iga.api.RoleAccount cancelDelegation(com.soffid.iam.iga.api.RoleAccount rolAccount)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.RoleAccount cancelDelegation(
		final com.soffid.iam.iga.api.RoleAccount rolAccount)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entitlementDelegationService.cancelDelegation(rolAccount); 
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
	 * @see com.soffid.iam.iga.service.EntitlementDelegationService#com.soffid.iam.iga.api.RoleAccount delegate(com.soffid.iam.iga.api.RoleAccount rolAccount, java.lang.String user, java.lang.String account, java.util.Date since, java.util.Date until)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.RoleAccount delegate(
		final com.soffid.iam.iga.api.RoleAccount rolAccount, 
		final java.lang.String user, 
		final java.lang.String account, 
		final java.util.Date since, 
		final java.util.Date until)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entitlementDelegationService.delegate(rolAccount, user, account, since, until); 
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
	 * @see com.soffid.iam.iga.service.EntitlementDelegationService#java.util.List<java.lang.String> findAccountsToDelegate(com.soffid.iam.iga.api.RoleAccount rolAccount, java.lang.String user)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<java.lang.String> findAccountsToDelegate(
		final com.soffid.iam.iga.api.RoleAccount rolAccount, 
		final java.lang.String user)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entitlementDelegationService.findAccountsToDelegate(rolAccount, user); 
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
	 * @see com.soffid.iam.iga.service.EntitlementDelegationService#java.util.List<com.soffid.iam.iga.api.RoleAccount> findActiveDelegations()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.iga.api.RoleAccount> findActiveDelegations()
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entitlementDelegationService.findActiveDelegations(); 
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
	 * @see com.soffid.iam.iga.service.EntitlementDelegationService#java.util.List<com.soffid.iam.iga.api.RoleAccount> findDelegationsToAccept()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.iga.api.RoleAccount> findDelegationsToAccept()
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entitlementDelegationService.findDelegationsToAccept(); 
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
	 * @see com.soffid.iam.iga.service.EntitlementDelegationService#void revertExpiredDelegations()
	 */
	@jakarta.annotation.security.PermitAll
	public void revertExpiredDelegations()
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.entitlementDelegationService.revertExpiredDelegations(); 
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

		this.entitlementDelegationService = (com.soffid.iam.iga.service.EntitlementDelegationService)
		getBeanFactory().getBean("com.soffid.iam.iga.service.EntitlementDelegationService");
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
