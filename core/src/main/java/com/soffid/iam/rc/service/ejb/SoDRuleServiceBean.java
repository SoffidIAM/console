//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service.ejb;
/**
 * @see <code>com.soffid.iam.rc.service.SoDRuleService</code>,
 * @see <code>com.soffid.iam.rc.service.SoDRuleService</code>,
 */
@jakarta.ejb.Stateless(name="SoDRuleService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.rc.service.SoDRuleService")
@jakarta.ejb.Local(com.soffid.iam.rc.service.ejb.SoDRuleService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class SoDRuleServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.rc.service.ejb.SoDRuleService
{
	private com.soffid.iam.rc.service.SoDRuleService soDRuleService;

	/**
	 * @see com.soffid.iam.rc.service.SoDRuleService#com.soffid.iam.rc.api.SoDRole create(com.soffid.iam.rc.api.SoDRole role)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.rc.api.SoDRole create(
		final com.soffid.iam.rc.api.SoDRole role)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("sod:update"))
			throw new SecurityException("Unable to execute SoDRuleService.create. Required roles: [sod:update]");
		try
		{
			return this.soDRuleService.create(role); 
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
	 * @see com.soffid.iam.rc.service.SoDRuleService#com.soffid.iam.rc.api.SoDRule create(com.soffid.iam.rc.api.SoDRule rule)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.rc.api.SoDRule create(
		final com.soffid.iam.rc.api.SoDRule rule)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("sod:create"))
			throw new SecurityException("Unable to execute SoDRuleService.create. Required roles: [sod:create]");
		try
		{
			return this.soDRuleService.create(rule); 
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
	 * @see com.soffid.iam.rc.service.SoDRuleService#com.soffid.iam.rc.api.SoDRule getRuleById(java.lang.Long ruleId)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.rc.api.SoDRule getRuleById(
		final java.lang.Long ruleId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:role:query"))
			throw new SecurityException("Unable to execute SoDRuleService.getRuleById. Required roles: [application:query, user_role_query]");
		try
		{
			return this.soDRuleService.getRuleById(ruleId); 
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
	 * @see com.soffid.iam.rc.service.SoDRuleService#com.soffid.iam.rc.api.SoDRule isAllowed(com.soffid.iam.iga.api.RoleAccount ra)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.rc.api.SoDRule isAllowed(
		final com.soffid.iam.iga.api.RoleAccount ra)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("sod:query"))
			throw new SecurityException("Unable to execute SoDRuleService.isAllowed. Required roles: [sod:query]");
		try
		{
			return this.soDRuleService.isAllowed(ra); 
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
	 * @see com.soffid.iam.rc.service.SoDRuleService#com.soffid.iam.rc.api.SoDRule update(com.soffid.iam.rc.api.SoDRule rule)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.rc.api.SoDRule update(
		final com.soffid.iam.rc.api.SoDRule rule)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("sod:update"))
			throw new SecurityException("Unable to execute SoDRuleService.update. Required roles: [sod:update]");
		try
		{
			return this.soDRuleService.update(rule); 
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
	 * @see com.soffid.iam.rc.service.SoDRuleService#com.soffid.iam.rc.api.SoDRuleMatrix create(com.soffid.iam.rc.api.SoDRuleMatrix role)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.rc.api.SoDRuleMatrix create(
		final com.soffid.iam.rc.api.SoDRuleMatrix role)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("sod:update"))
			throw new SecurityException("Unable to execute SoDRuleService.create. Required roles: [sod:update]");
		try
		{
			return this.soDRuleService.create(role); 
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
	 * @see com.soffid.iam.rc.service.SoDRuleService#com.soffid.iam.rc.api.SoDRuleMatrix update(com.soffid.iam.rc.api.SoDRuleMatrix role)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.rc.api.SoDRuleMatrix update(
		final com.soffid.iam.rc.api.SoDRuleMatrix role)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("sod:update"))
			throw new SecurityException("Unable to execute SoDRuleService.update. Required roles: [sod:update]");
		try
		{
			return this.soDRuleService.update(role); 
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
	 * @see com.soffid.iam.rc.service.SoDRuleService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.SoDRule> findSodRules(com.soffid.zkdb.api.Query query)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.SoDRule> findSodRules(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("sod:query"))
			throw new SecurityException("Unable to execute SoDRuleService.findSodRules. Required roles: [sod:query]");
		try
		{
			return this.soDRuleService.findSodRules(query); 
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
	 * @see com.soffid.iam.rc.service.SoDRuleService#java.lang.String generateChangesReport(com.soffid.iam.rc.api.SoDRule rule, java.util.Collection<com.soffid.iam.rc.api.SoDRole> grants, java.util.Collection<com.soffid.iam.rc.api.SoDRuleMatrix> matrix)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String generateChangesReport(
		final com.soffid.iam.rc.api.SoDRule rule, 
		final java.util.Collection<com.soffid.iam.rc.api.SoDRole> grants, 
		final java.util.Collection<com.soffid.iam.rc.api.SoDRuleMatrix> matrix)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("sod:update"))
			throw new SecurityException("Unable to execute SoDRuleService.generateChangesReport. Required roles: [sod:update]");
		try
		{
			return this.soDRuleService.generateChangesReport(rule, grants, matrix); 
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
	 * @see com.soffid.iam.rc.service.SoDRuleService#java.util.Collection<com.soffid.iam.rc.api.SoDRule> findAffectingRulesByRolAccount(com.soffid.iam.iga.api.RoleAccount ra)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.rc.api.SoDRule> findAffectingRulesByRolAccount(
		final com.soffid.iam.iga.api.RoleAccount ra)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:role:query"))
			throw new SecurityException("Unable to execute SoDRuleService.findAffectingRulesByRolAccount. Required roles: [application:query, user_role_query]");
		try
		{
			return this.soDRuleService.findAffectingRulesByRolAccount(ra); 
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
	 * @see com.soffid.iam.rc.service.SoDRuleService#java.util.Collection<com.soffid.iam.rc.api.SoDRuleMatrix> findMatrixByRule(java.lang.Long ruleId)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.rc.api.SoDRuleMatrix> findMatrixByRule(
		final java.lang.Long ruleId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("sod:query"))
			throw new SecurityException("Unable to execute SoDRuleService.findMatrixByRule. Required roles: [application:query, sod_query]");
		try
		{
			return this.soDRuleService.findMatrixByRule(ruleId); 
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
	 * @see com.soffid.iam.rc.service.SoDRuleService#java.util.Collection<com.soffid.iam.rc.api.SoDRole> findRolesByRule(java.lang.Long ruleId)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.rc.api.SoDRole> findRolesByRule(
		final java.lang.Long ruleId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("sod:query"))
			throw new SecurityException("Unable to execute SoDRuleService.findRolesByRule. Required roles: [application:query, sod_query]");
		try
		{
			return this.soDRuleService.findRolesByRule(ruleId); 
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
	 * @see com.soffid.iam.rc.service.SoDRuleService#java.util.Collection<com.soffid.iam.rc.api.SoDRule> findRuleByApplication(java.lang.Long applicationId)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.rc.api.SoDRule> findRuleByApplication(
		final java.lang.Long applicationId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("sod:query"))
			throw new SecurityException("Unable to execute SoDRuleService.findRuleByApplication. Required roles: [application:query, sod_query]");
		try
		{
			return this.soDRuleService.findRuleByApplication(applicationId); 
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
	 * @see com.soffid.iam.rc.service.SoDRuleService#java.util.List<com.soffid.iam.iga.api.RoleAccount> findViolotions(java.lang.String applicationName, com.soffid.iam.rc.api.SoDRisk riskLevel)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.iga.api.RoleAccount> findViolotions(
		final java.lang.String applicationName, 
		final com.soffid.iam.rc.api.SoDRisk riskLevel)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("sod:query"))
			throw new SecurityException("Unable to execute SoDRuleService.findViolotions. Required roles: [sod:query]");
		try
		{
			return this.soDRuleService.findViolotions(applicationName, riskLevel); 
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
	 * @see com.soffid.iam.rc.service.SoDRuleService#void qualifyRolAccountList(java.util.List<com.soffid.iam.iga.api.RoleAccount> ra)
	 */
	@jakarta.annotation.security.PermitAll
	public void qualifyRolAccountList(
		final java.util.List<com.soffid.iam.iga.api.RoleAccount> ra)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:role:query"))
			throw new SecurityException("Unable to execute SoDRuleService.qualifyRolAccountList. Required roles: [user:role:query]");
		try
		{
			this.soDRuleService.qualifyRolAccountList(ra); 
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
	 * @see com.soffid.iam.rc.service.SoDRuleService#void remove(com.soffid.iam.rc.api.SoDRole role)
	 */
	@jakarta.annotation.security.PermitAll
	public void remove(
		final com.soffid.iam.rc.api.SoDRole role)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("sod:update"))
			throw new SecurityException("Unable to execute SoDRuleService.remove. Required roles: [sod:update]");
		try
		{
			this.soDRuleService.remove(role); 
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
	 * @see com.soffid.iam.rc.service.SoDRuleService#void remove(com.soffid.iam.rc.api.SoDRule rule)
	 */
	@jakarta.annotation.security.PermitAll
	public void remove(
		final com.soffid.iam.rc.api.SoDRule rule)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("sod:delete"))
			throw new SecurityException("Unable to execute SoDRuleService.remove. Required roles: [sod:delete]");
		try
		{
			this.soDRuleService.remove(rule); 
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
	 * @see com.soffid.iam.rc.service.SoDRuleService#void remove(com.soffid.iam.rc.api.SoDRuleMatrix role)
	 */
	@jakarta.annotation.security.PermitAll
	public void remove(
		final com.soffid.iam.rc.api.SoDRuleMatrix role)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("sod:update"))
			throw new SecurityException("Unable to execute SoDRuleService.remove. Required roles: [sod:update]");
		try
		{
			this.soDRuleService.remove(role); 
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

		this.soDRuleService = (com.soffid.iam.rc.service.SoDRuleService)
		getBeanFactory().getBean("com.soffid.iam.rc.service.SoDRuleService");
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
