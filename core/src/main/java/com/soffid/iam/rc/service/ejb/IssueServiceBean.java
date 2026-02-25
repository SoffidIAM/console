//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service.ejb;
/**
 * @see <code>com.soffid.iam.rc.service.IssueService</code>,
 * @see <code>com.soffid.iam.rc.service.IssueService</code>,
 */
@jakarta.ejb.Stateless(name="IssueService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.rc.service.IssueService")
@jakarta.ejb.Local(com.soffid.iam.rc.service.ejb.IssueService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class IssueServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.rc.service.ejb.IssueService
{
	private com.soffid.iam.rc.service.IssueService issueService;

	/**
	 * @see com.soffid.iam.rc.service.IssueService#com.soffid.iam.rc.api.Issue create(com.soffid.iam.rc.api.Issue event)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.rc.api.Issue create(
		final com.soffid.iam.rc.api.Issue event)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("issue:create"))
			throw new SecurityException("Unable to execute IssueService.create. Required roles: [issue:create]");
		try
		{
			return this.issueService.create(event); 
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
	 * @see com.soffid.iam.rc.service.IssueService#com.soffid.iam.rc.api.Issue notify(com.soffid.iam.rc.api.Issue issue, java.lang.String address, java.lang.String subject, java.lang.String body)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.rc.api.Issue notify(
		final com.soffid.iam.rc.api.Issue issue, 
		final java.lang.String address, 
		final java.lang.String subject, 
		final java.lang.String body)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("issue:query"))
			throw new SecurityException("Unable to execute IssueService.notify. Required roles: [issue:query]");
		try
		{
			return this.issueService.notify(issue, address, subject, body); 
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
	 * @see com.soffid.iam.rc.service.IssueService#com.soffid.iam.rc.api.Issue registerAction(com.soffid.iam.rc.api.Issue issue, java.lang.String action)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.rc.api.Issue registerAction(
		final com.soffid.iam.rc.api.Issue issue, 
		final java.lang.String action)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("issue:query"))
			throw new SecurityException("Unable to execute IssueService.registerAction. Required roles: [issue:query]");
		try
		{
			return this.issueService.registerAction(issue, action); 
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
	 * @see com.soffid.iam.rc.service.IssueService#com.soffid.iam.rc.api.Issue update(com.soffid.iam.rc.api.Issue event)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.rc.api.Issue update(
		final com.soffid.iam.rc.api.Issue event)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("issue:update"))
			throw new SecurityException("Unable to execute IssueService.update. Required roles: [issue:update]");
		try
		{
			return this.issueService.update(event); 
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
	 * @see com.soffid.iam.rc.service.IssueService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Issue> findIssues(com.soffid.zkdb.api.Query query)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Issue> findIssues(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("issue:query"))
			throw new SecurityException("Unable to execute IssueService.findIssues. Required roles: [issue:query]");
		try
		{
			return this.issueService.findIssues(query); 
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
	 * @see com.soffid.iam.rc.service.IssueService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Issue> findMyIssues(com.soffid.zkdb.api.Query query)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Issue> findMyIssues(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.issueService.findMyIssues(query); 
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
	 * @see com.soffid.iam.rc.service.IssueService#int countMyIssues()
	 */
	@jakarta.annotation.security.PermitAll
	public int countMyIssues()
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.issueService.countMyIssues(); 
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
	 * @see com.soffid.iam.rc.service.IssueService#java.util.List<com.soffid.iam.rc.api.Issue> findIssuesByUser(java.lang.String user)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.rc.api.Issue> findIssuesByUser(
		final java.lang.String user)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("issue:query"))
			throw new SecurityException("Unable to execute IssueService.findIssuesByUser. Required roles: [issue:query]");
		try
		{
			return this.issueService.findIssuesByUser(user); 
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
	 * @see com.soffid.iam.rc.service.IssueService#java.util.List<com.soffid.iam.rc.api.IssueActionDefinition> listManualActions()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.rc.api.IssueActionDefinition> listManualActions()
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("issue:query"))
			throw new SecurityException("Unable to execute IssueService.listManualActions. Required roles: [issue:query]");
		try
		{
			return this.issueService.listManualActions(); 
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
	 * @see com.soffid.iam.rc.service.IssueService#void delete(com.soffid.iam.rc.api.Issue event)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.rc.api.Issue event)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("issue:delete"))
			throw new SecurityException("Unable to execute IssueService.delete. Required roles: [issue:delete]");
		try
		{
			this.issueService.delete(event); 
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

		this.issueService = (com.soffid.iam.rc.service.IssueService)
		getBeanFactory().getBean("com.soffid.iam.rc.service.IssueService");
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
