//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * @see <code>com.soffid.iam.iga.service.RulesService</code>,
 * @see <code>com.soffid.iam.iga.service.RulesService</code>,
 */
@jakarta.ejb.Stateless(name="RulesService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.iga.service.RulesService")
@jakarta.ejb.Local(com.soffid.iam.iga.service.ejb.RulesService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class RulesServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.iga.service.ejb.RulesService
{
	private com.soffid.iam.iga.service.RulesService rulesService;

	/**
	 * @see com.soffid.iam.iga.service.RulesService#com.soffid.iam.base.api.AsyncProcessTracker applyAsync(com.soffid.iam.iga.api.Rule rule)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.AsyncProcessTracker applyAsync(
		final com.soffid.iam.iga.api.Rule rule)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("rule:admin"))
			throw new SecurityException("Unable to execute RulesService.applyAsync. Required roles: [rule:admin]");
		try
		{
			return this.rulesService.applyAsync(rule); 
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
	 * @see com.soffid.iam.iga.service.RulesService#com.soffid.iam.base.api.AsyncProcessTracker queryProcessStatus(com.soffid.iam.base.api.AsyncProcessTracker process)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.AsyncProcessTracker queryProcessStatus(
		final com.soffid.iam.base.api.AsyncProcessTracker process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("rule:admin"))
			throw new SecurityException("Unable to execute RulesService.queryProcessStatus. Required roles: [rule:admin]");
		try
		{
			return this.rulesService.queryProcessStatus(process); 
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
	 * @see com.soffid.iam.iga.service.RulesService#com.soffid.iam.iga.api.Rule create(com.soffid.iam.iga.api.Rule rule)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.Rule create(
		final com.soffid.iam.iga.api.Rule rule)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("rule:admin"))
			throw new SecurityException("Unable to execute RulesService.create. Required roles: [rule:admin]");
		try
		{
			return this.rulesService.create(rule); 
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
	 * @see com.soffid.iam.iga.service.RulesService#com.soffid.iam.iga.api.Rule update(com.soffid.iam.iga.api.Rule rule)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.Rule update(
		final com.soffid.iam.iga.api.Rule rule)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("rule:admin"))
			throw new SecurityException("Unable to execute RulesService.update. Required roles: [rule:admin]");
		try
		{
			return this.rulesService.update(rule); 
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
	 * @see com.soffid.iam.iga.service.RulesService#com.soffid.iam.iga.api.RuleAssignedRole create(com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.RuleAssignedRole create(
		final com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("rule:admin"))
			throw new SecurityException("Unable to execute RulesService.create. Required roles: [rule:admin]");
		try
		{
			return this.rulesService.create(ruleAssignment); 
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
	 * @see com.soffid.iam.iga.service.RulesService#com.soffid.iam.iga.api.RuleAssignedRole update(com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.RuleAssignedRole update(
		final com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("rule:admin"))
			throw new SecurityException("Unable to execute RulesService.update. Required roles: [rule:admin]");
		try
		{
			return this.rulesService.update(ruleAssignment); 
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
	 * @see com.soffid.iam.iga.service.RulesService#java.lang.String generateChangesReport(com.soffid.iam.iga.api.Rule rule, java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole> grants)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String generateChangesReport(
		final com.soffid.iam.iga.api.Rule rule, 
		final java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole> grants)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("rule:admin"))
			throw new SecurityException("Unable to execute RulesService.generateChangesReport. Required roles: [rule:admin]");
		try
		{
			return this.rulesService.generateChangesReport(rule, grants); 
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
	 * @see com.soffid.iam.iga.service.RulesService#java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole> findRuleAssignments(com.soffid.iam.iga.api.Rule rule)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole> findRuleAssignments(
		final com.soffid.iam.iga.api.Rule rule)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("rule:query"))
			throw new SecurityException("Unable to execute RulesService.findRuleAssignments. Required roles: [rule:query]");
		try
		{
			return this.rulesService.findRuleAssignments(rule); 
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
	 * @see com.soffid.iam.iga.service.RulesService#java.util.Collection<com.soffid.iam.iga.api.Rule> findRules(java.lang.String description)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.Rule> findRules(
		final java.lang.String description)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("rule:query"))
			throw new SecurityException("Unable to execute RulesService.findRules. Required roles: [rule:query]");
		try
		{
			return this.rulesService.findRules(description); 
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
	 * @see com.soffid.iam.iga.service.RulesService#java.util.Collection<com.soffid.iam.iga.api.Rule> findRulesByRole(java.lang.Long roleId)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.Rule> findRulesByRole(
		final java.lang.Long roleId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("rule:query"))
			throw new SecurityException("Unable to execute RulesService.findRulesByRole. Required roles: [rule:query]");
		try
		{
			return this.rulesService.findRulesByRole(roleId); 
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
	 * @see com.soffid.iam.iga.service.RulesService#void apply(com.soffid.iam.iga.api.Rule rule)
	 */
	@jakarta.annotation.security.PermitAll
	public void apply(
		final com.soffid.iam.iga.api.Rule rule)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("rule:admin"))
			throw new SecurityException("Unable to execute RulesService.apply. Required roles: [rule:admin]");
		try
		{
			this.rulesService.apply(rule); 
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
	 * @see com.soffid.iam.iga.service.RulesService#void delete(com.soffid.iam.iga.api.Rule rule)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.Rule rule)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("rule:admin"))
			throw new SecurityException("Unable to execute RulesService.delete. Required roles: [rule:admin]");
		try
		{
			this.rulesService.delete(rule); 
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
	 * @see com.soffid.iam.iga.service.RulesService#void delete(com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("rule:admin"))
			throw new SecurityException("Unable to execute RulesService.delete. Required roles: [rule:admin]");
		try
		{
			this.rulesService.delete(ruleAssignment); 
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

		this.rulesService = (com.soffid.iam.iga.service.RulesService)
		getBeanFactory().getBean("com.soffid.iam.iga.service.RulesService");
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
