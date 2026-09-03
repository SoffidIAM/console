//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * @see <code>com.soffid.iam.iga.service.RoleSuggestionService</code>,
 * @see <code>com.soffid.iam.iga.service.RoleSuggestionService</code>,
 */
@jakarta.ejb.Stateless(name="RoleSuggestionService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.iga.service.RoleSuggestionService")
@jakarta.ejb.Local(com.soffid.iam.iga.service.ejb.RoleSuggestionService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class RoleSuggestionServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.iga.service.ejb.RoleSuggestionService
{
	private com.soffid.iam.iga.service.RoleSuggestionService roleSuggestionService;

	/**
	 * @see com.soffid.iam.iga.service.RoleSuggestionService#java.util.List<com.soffid.iam.iga.api.RoleAccount> suggest(java.lang.String user)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.iga.api.RoleAccount> suggest(
		final java.lang.String user)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:role:query"))
			throw new SecurityException("Unable to execute RoleSuggestionService.suggest. Required roles: [user:role:query]");
		try
		{
			return this.roleSuggestionService.suggest(user); 
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

		this.roleSuggestionService = (com.soffid.iam.iga.service.RoleSuggestionService)
		getBeanFactory().getBean("com.soffid.iam.iga.service.RoleSuggestionService");
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
