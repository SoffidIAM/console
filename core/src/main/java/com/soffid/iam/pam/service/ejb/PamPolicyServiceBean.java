//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.service.ejb;
/**
 * @see <code>com.soffid.iam.pam.service.PamPolicyService</code>,
 * @see <code>com.soffid.iam.pam.service.PamPolicyService</code>,
 */
@jakarta.ejb.Stateless(name="PamPolicyService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.pam.service.PamPolicyService")
@jakarta.ejb.Local(com.soffid.iam.pam.service.ejb.PamPolicyService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class PamPolicyServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.pam.service.ejb.PamPolicyService
{
	private com.soffid.iam.pam.service.PamPolicyService pamPolicyService;

	/**
	 * @see com.soffid.iam.pam.service.PamPolicyService#com.soffid.iam.pam.api.PamAction updateAction(com.soffid.iam.pam.api.PamAction action)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.pam.api.PamAction updateAction(
		final com.soffid.iam.pam.api.PamAction action)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("pamPolicy:update"))
			throw new SecurityException("Unable to execute PamPolicyService.updateAction. Required roles: [pamPolicy:update]");
		try
		{
			return this.pamPolicyService.updateAction(action); 
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
	 * @see com.soffid.iam.pam.service.PamPolicyService#com.soffid.iam.pam.api.PamPolicy createPolicy(com.soffid.iam.pam.api.PamPolicy policy)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.pam.api.PamPolicy createPolicy(
		final com.soffid.iam.pam.api.PamPolicy policy)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("pamPolicy:create"))
			throw new SecurityException("Unable to execute PamPolicyService.createPolicy. Required roles: [pamPolicy:create]");
		try
		{
			return this.pamPolicyService.createPolicy(policy); 
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
	 * @see com.soffid.iam.pam.service.PamPolicyService#com.soffid.iam.pam.api.PamPolicy updatePolicy(com.soffid.iam.pam.api.PamPolicy policy)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.pam.api.PamPolicy updatePolicy(
		final com.soffid.iam.pam.api.PamPolicy policy)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("pamPolicy:update"))
			throw new SecurityException("Unable to execute PamPolicyService.updatePolicy. Required roles: [pamPolicy:update]");
		try
		{
			return this.pamPolicyService.updatePolicy(policy); 
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
	 * @see com.soffid.iam.pam.service.PamPolicyService#com.soffid.iam.pam.api.PamRule createRule(com.soffid.iam.pam.api.PamRule rule)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.pam.api.PamRule createRule(
		final com.soffid.iam.pam.api.PamRule rule)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("pamRule:create"))
			throw new SecurityException("Unable to execute PamPolicyService.createRule. Required roles: [pamRule:create]");
		try
		{
			return this.pamPolicyService.createRule(rule); 
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
	 * @see com.soffid.iam.pam.service.PamPolicyService#com.soffid.iam.pam.api.PamRule updateRule(com.soffid.iam.pam.api.PamRule rule)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.pam.api.PamRule updateRule(
		final com.soffid.iam.pam.api.PamRule rule)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("pamRule:update"))
			throw new SecurityException("Unable to execute PamPolicyService.updateRule. Required roles: [pamRule:update]");
		try
		{
			return this.pamPolicyService.updateRule(rule); 
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
	 * @see com.soffid.iam.pam.service.PamPolicyService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.pam.api.PamPolicy> findPolicies(com.soffid.zkdb.api.Query q)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.pam.api.PamPolicy> findPolicies(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("pamPolicy:query"))
			throw new SecurityException("Unable to execute PamPolicyService.findPolicies. Required roles: [pamPolicy:query]");
		try
		{
			return this.pamPolicyService.findPolicies(q); 
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
	 * @see com.soffid.iam.pam.service.PamPolicyService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.pam.api.PamRule> findRules(com.soffid.zkdb.api.Query q)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.pam.api.PamRule> findRules(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("pamRule:query"))
			throw new SecurityException("Unable to execute PamPolicyService.findRules. Required roles: [pamRule:query]");
		try
		{
			return this.pamPolicyService.findRules(q); 
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
	 * @see com.soffid.iam.pam.service.PamPolicyService#java.util.List<com.soffid.iam.pam.api.PamAction> findPolicyActions(com.soffid.iam.pam.api.PamPolicy policy)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.pam.api.PamAction> findPolicyActions(
		final com.soffid.iam.pam.api.PamPolicy policy)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("pamPolicy:query"))
			throw new SecurityException("Unable to execute PamPolicyService.findPolicyActions. Required roles: [pamPolicy:query]");
		try
		{
			return this.pamPolicyService.findPolicyActions(policy); 
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
	 * @see com.soffid.iam.pam.service.PamPolicyService#void deletePolicy(com.soffid.iam.pam.api.PamPolicy policy)
	 */
	@jakarta.annotation.security.PermitAll
	public void deletePolicy(
		final com.soffid.iam.pam.api.PamPolicy policy)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("pamPolicy:delete"))
			throw new SecurityException("Unable to execute PamPolicyService.deletePolicy. Required roles: [pamPolicy:delete]");
		try
		{
			this.pamPolicyService.deletePolicy(policy); 
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
	 * @see com.soffid.iam.pam.service.PamPolicyService#void deleteRule(com.soffid.iam.pam.api.PamRule rule)
	 */
	@jakarta.annotation.security.PermitAll
	public void deleteRule(
		final com.soffid.iam.pam.api.PamRule rule)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("pamRule:delete"))
			throw new SecurityException("Unable to execute PamPolicyService.deleteRule. Required roles: [pamRule:delete]");
		try
		{
			this.pamPolicyService.deleteRule(rule); 
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

		this.pamPolicyService = (com.soffid.iam.pam.service.PamPolicyService)
		getBeanFactory().getBean("com.soffid.iam.pam.service.PamPolicyService");
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
