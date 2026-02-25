//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service.ejb;
/**
 * @see <code>com.soffid.iam.rc.service.IssuePolicyService</code>,
 * @see <code>com.soffid.iam.rc.service.IssuePolicyService</code>,
 */
@jakarta.ejb.Stateless(name="IssuePolicyService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.rc.service.IssuePolicyService")
@jakarta.ejb.Local(com.soffid.iam.rc.service.ejb.IssuePolicyService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class IssuePolicyServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.rc.service.ejb.IssuePolicyService
{
	private com.soffid.iam.rc.service.IssuePolicyService issuePolicyService;

	/**
	 * @see com.soffid.iam.rc.service.IssuePolicyService#com.soffid.iam.rc.api.IssuePolicy update(com.soffid.iam.rc.api.IssuePolicy event)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.rc.api.IssuePolicy update(
		final com.soffid.iam.rc.api.IssuePolicy event)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("issuePolicy:update"))
			throw new SecurityException("Unable to execute IssuePolicyService.update. Required roles: [issuePolicy:update]");
		try
		{
			return this.issuePolicyService.update(event); 
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
	 * @see com.soffid.iam.rc.service.IssuePolicyService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.IssuePolicy> findIssuePolicies(com.soffid.zkdb.api.Query q)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.IssuePolicy> findIssuePolicies(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("issuePolicy:query"))
			throw new SecurityException("Unable to execute IssuePolicyService.findIssuePolicies. Required roles: [issuePolicy:query]");
		try
		{
			return this.issuePolicyService.findIssuePolicies(q); 
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
	 * @see com.soffid.iam.rc.service.IssuePolicyService#java.util.List<com.soffid.iam.rc.api.IssueActionDefinition> listAutomaticActions()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.rc.api.IssueActionDefinition> listAutomaticActions()
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("issuePolicy:query"))
			throw new SecurityException("Unable to execute IssuePolicyService.listAutomaticActions. Required roles: [issuePolicy:query]");
		try
		{
			return this.issuePolicyService.listAutomaticActions(); 
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

		this.issuePolicyService = (com.soffid.iam.rc.service.IssuePolicyService)
		getBeanFactory().getBean("com.soffid.iam.rc.service.IssuePolicyService");
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
