//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.iga.service.RulesService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.iga.service.RulesService
 */
public abstract class RulesServiceBase
	implements com.soffid.iam.iga.service.RulesService
 {
	private com.soffid.iam.rc.model.AuditEntityDao auditEntityDao;

	/**
	 * Sets reference to <code>auditEntityDao</code>.
	 */
	public void setAuditEntityDao (com.soffid.iam.rc.model.AuditEntityDao auditEntityDao) {
		this.auditEntityDao = auditEntityDao;
	}

	/**
	 * Gets reference to <code>auditEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.AuditEntityDao getAuditEntityDao () {
		return auditEntityDao;
	}

	private com.soffid.iam.iga.model.RoleEntityDao roleEntityDao;

	/**
	 * Sets reference to <code>roleEntityDao</code>.
	 */
	public void setRoleEntityDao (com.soffid.iam.iga.model.RoleEntityDao roleEntityDao) {
		this.roleEntityDao = roleEntityDao;
	}

	/**
	 * Gets reference to <code>roleEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleEntityDao getRoleEntityDao () {
		return roleEntityDao;
	}

	private com.soffid.iam.iga.model.RuleAssignedRoleEntityDao ruleAssignedRoleEntityDao;

	/**
	 * Sets reference to <code>ruleAssignedRoleEntityDao</code>.
	 */
	public void setRuleAssignedRoleEntityDao (com.soffid.iam.iga.model.RuleAssignedRoleEntityDao ruleAssignedRoleEntityDao) {
		this.ruleAssignedRoleEntityDao = ruleAssignedRoleEntityDao;
	}

	/**
	 * Gets reference to <code>ruleAssignedRoleEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RuleAssignedRoleEntityDao getRuleAssignedRoleEntityDao () {
		return ruleAssignedRoleEntityDao;
	}

	private com.soffid.iam.iga.model.RuleEntityDao ruleEntityDao;

	/**
	 * Sets reference to <code>ruleEntityDao</code>.
	 */
	public void setRuleEntityDao (com.soffid.iam.iga.model.RuleEntityDao ruleEntityDao) {
		this.ruleEntityDao = ruleEntityDao;
	}

	/**
	 * Gets reference to <code>ruleEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RuleEntityDao getRuleEntityDao () {
		return ruleEntityDao;
	}

	private com.soffid.iam.impl.service.RuleEvaluatorService ruleEvaluatorService;

	/**
	 * Sets reference to <code>ruleEvaluatorService</code>.
	 */
	public void setRuleEvaluatorService (com.soffid.iam.impl.service.RuleEvaluatorService ruleEvaluatorService) {
		this.ruleEvaluatorService = ruleEvaluatorService;
	}

	/**
	 * Gets reference to <code>ruleEvaluatorService</code>.
	 */
	public com.soffid.iam.impl.service.RuleEvaluatorService getRuleEvaluatorService () {
		return ruleEvaluatorService;
	}


	/**
	 * @see com.soffid.iam.iga.service.RulesService#	 * @see com.soffid.iam.iga.service.RulesService#com.soffid.iam.base.api.AsyncProcessTracker applyAsync(com.soffid.iam.iga.api.Rule rule)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.AsyncProcessTracker applyAsync(
		final com.soffid.iam.iga.api.Rule rule)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rule == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AsyncProcessTracker com.soffid.iam.iga.service.RulesService.applyAsync(com.soffid.iam.iga.api.Rule rule) - rule cannot be null");
		}
		if (rule.getName() == null || rule.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AsyncProcessTracker com.soffid.iam.iga.service.RulesService.applyAsync(com.soffid.iam.iga.api.Rule rule) - rule.name cannot be null");
		}
		if (rule.getBshExpression() == null || rule.getBshExpression().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AsyncProcessTracker com.soffid.iam.iga.service.RulesService.applyAsync(com.soffid.iam.iga.api.Rule rule) - rule.bshExpression cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleApplyAsync(rule)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.AsyncProcessTracker) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.RulesService.class).
			warn ("Error on RulesService.applyAsync", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on RulesService.applyAsync", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.AsyncProcessTracker handleApplyAsync(com.soffid.iam.iga.api.Rule rule) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.RulesService#	 * @see com.soffid.iam.iga.service.RulesService#com.soffid.iam.base.api.AsyncProcessTracker queryProcessStatus(com.soffid.iam.base.api.AsyncProcessTracker process)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class}, readOnly=true)
	public com.soffid.iam.base.api.AsyncProcessTracker queryProcessStatus(
		final com.soffid.iam.base.api.AsyncProcessTracker process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (process == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AsyncProcessTracker com.soffid.iam.iga.service.RulesService.queryProcessStatus(com.soffid.iam.base.api.AsyncProcessTracker process) - process cannot be null");
		}
		if (process.getId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AsyncProcessTracker com.soffid.iam.iga.service.RulesService.queryProcessStatus(com.soffid.iam.base.api.AsyncProcessTracker process) - process.id cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleQueryProcessStatus(process)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.AsyncProcessTracker) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.RulesService.class).
			warn ("Error on RulesService.queryProcessStatus", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on RulesService.queryProcessStatus", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.AsyncProcessTracker handleQueryProcessStatus(com.soffid.iam.base.api.AsyncProcessTracker process) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.RulesService#	 * @see com.soffid.iam.iga.service.RulesService#com.soffid.iam.iga.api.Rule create(com.soffid.iam.iga.api.Rule rule)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Rule create(
		final com.soffid.iam.iga.api.Rule rule)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rule == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Rule com.soffid.iam.iga.service.RulesService.create(com.soffid.iam.iga.api.Rule rule) - rule cannot be null");
		}
		if (rule.getName() == null || rule.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Rule com.soffid.iam.iga.service.RulesService.create(com.soffid.iam.iga.api.Rule rule) - rule.name cannot be null");
		}
		if (rule.getBshExpression() == null || rule.getBshExpression().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Rule com.soffid.iam.iga.service.RulesService.create(com.soffid.iam.iga.api.Rule rule) - rule.bshExpression cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(rule)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Rule) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.RulesService.class).
			warn ("Error on RulesService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on RulesService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Rule handleCreate(com.soffid.iam.iga.api.Rule rule) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.RulesService#	 * @see com.soffid.iam.iga.service.RulesService#com.soffid.iam.iga.api.Rule update(com.soffid.iam.iga.api.Rule rule)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Rule update(
		final com.soffid.iam.iga.api.Rule rule)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rule == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Rule com.soffid.iam.iga.service.RulesService.update(com.soffid.iam.iga.api.Rule rule) - rule cannot be null");
		}
		if (rule.getName() == null || rule.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Rule com.soffid.iam.iga.service.RulesService.update(com.soffid.iam.iga.api.Rule rule) - rule.name cannot be null");
		}
		if (rule.getBshExpression() == null || rule.getBshExpression().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Rule com.soffid.iam.iga.service.RulesService.update(com.soffid.iam.iga.api.Rule rule) - rule.bshExpression cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(rule)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Rule) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.RulesService.class).
			warn ("Error on RulesService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on RulesService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Rule handleUpdate(com.soffid.iam.iga.api.Rule rule) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.RulesService#	 * @see com.soffid.iam.iga.service.RulesService#com.soffid.iam.iga.api.RuleAssignedRole create(com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.RuleAssignedRole create(
		final com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (ruleAssignment == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.RuleAssignedRole com.soffid.iam.iga.service.RulesService.create(com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment) - ruleAssignment cannot be null");
		}
		if (ruleAssignment.getRoleId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.RuleAssignedRole com.soffid.iam.iga.service.RulesService.create(com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment) - ruleAssignment.roleId cannot be null");
		}
		if (ruleAssignment.getRuleId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.RuleAssignedRole com.soffid.iam.iga.service.RulesService.create(com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment) - ruleAssignment.ruleId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(ruleAssignment)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.RuleAssignedRole) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.RulesService.class).
			warn ("Error on RulesService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on RulesService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.RuleAssignedRole handleCreate(com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.RulesService#	 * @see com.soffid.iam.iga.service.RulesService#com.soffid.iam.iga.api.RuleAssignedRole update(com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.RuleAssignedRole update(
		final com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (ruleAssignment == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.RuleAssignedRole com.soffid.iam.iga.service.RulesService.update(com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment) - ruleAssignment cannot be null");
		}
		if (ruleAssignment.getRoleId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.RuleAssignedRole com.soffid.iam.iga.service.RulesService.update(com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment) - ruleAssignment.roleId cannot be null");
		}
		if (ruleAssignment.getRuleId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.RuleAssignedRole com.soffid.iam.iga.service.RulesService.update(com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment) - ruleAssignment.ruleId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(ruleAssignment)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.RuleAssignedRole) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.RulesService.class).
			warn ("Error on RulesService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on RulesService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.RuleAssignedRole handleUpdate(com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.RulesService#	 * @see com.soffid.iam.iga.service.RulesService#java.lang.String generateChangesReport(com.soffid.iam.iga.api.Rule rule, java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole> grants)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class}, readOnly=true)
	public java.lang.String generateChangesReport(
		final com.soffid.iam.iga.api.Rule rule, 
		final java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole> grants)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rule == null) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.RulesService.generateChangesReport(com.soffid.iam.iga.api.Rule rule, java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole> grants) - rule cannot be null");
		}
		if (rule.getName() == null || rule.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.RulesService.generateChangesReport(com.soffid.iam.iga.api.Rule rule, java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole> grants) - rule.name cannot be null");
		}
		if (rule.getBshExpression() == null || rule.getBshExpression().trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.RulesService.generateChangesReport(com.soffid.iam.iga.api.Rule rule, java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole> grants) - rule.bshExpression cannot be null");
		}
		if (grants == null ) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.RulesService.generateChangesReport(com.soffid.iam.iga.api.Rule rule, java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole> grants) - grants cannot be empty");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGenerateChangesReport(rule, grants)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.RulesService.class).
			warn ("Error on RulesService.generateChangesReport", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on RulesService.generateChangesReport", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGenerateChangesReport(com.soffid.iam.iga.api.Rule rule, java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole> grants) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.RulesService#	 * @see com.soffid.iam.iga.service.RulesService#java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole> findRuleAssignments(com.soffid.iam.iga.api.Rule rule)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole> findRuleAssignments(
		final com.soffid.iam.iga.api.Rule rule)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rule == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole> com.soffid.iam.iga.service.RulesService.findRuleAssignments(com.soffid.iam.iga.api.Rule rule) - rule cannot be null");
		}
		if (rule.getName() == null || rule.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole> com.soffid.iam.iga.service.RulesService.findRuleAssignments(com.soffid.iam.iga.api.Rule rule) - rule.name cannot be null");
		}
		if (rule.getBshExpression() == null || rule.getBshExpression().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole> com.soffid.iam.iga.service.RulesService.findRuleAssignments(com.soffid.iam.iga.api.Rule rule) - rule.bshExpression cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRuleAssignments(rule)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.RulesService.class).
			warn ("Error on RulesService.findRuleAssignments", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on RulesService.findRuleAssignments", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole> handleFindRuleAssignments(com.soffid.iam.iga.api.Rule rule) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.RulesService#	 * @see com.soffid.iam.iga.service.RulesService#java.util.Collection<com.soffid.iam.iga.api.Rule> findRules(java.lang.String description)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Rule> findRules(
		final java.lang.String description)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRules(description)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Rule>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.RulesService.class).
			warn ("Error on RulesService.findRules", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on RulesService.findRules", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Rule> handleFindRules(java.lang.String description) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.RulesService#	 * @see com.soffid.iam.iga.service.RulesService#java.util.Collection<com.soffid.iam.iga.api.Rule> findRulesByRole(java.lang.Long roleId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Rule> findRulesByRole(
		final java.lang.Long roleId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (roleId == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Rule> com.soffid.iam.iga.service.RulesService.findRulesByRole(java.lang.Long roleId) - roleId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRulesByRole(roleId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Rule>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.RulesService.class).
			warn ("Error on RulesService.findRulesByRole", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on RulesService.findRulesByRole", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Rule> handleFindRulesByRole(java.lang.Long roleId) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.RulesService#	 * @see com.soffid.iam.iga.service.RulesService#void apply(com.soffid.iam.iga.api.Rule rule)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void apply(
		final com.soffid.iam.iga.api.Rule rule)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rule == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.RulesService.apply(com.soffid.iam.iga.api.Rule rule) - rule cannot be null");
		}
		if (rule.getName() == null || rule.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.RulesService.apply(com.soffid.iam.iga.api.Rule rule) - rule.name cannot be null");
		}
		if (rule.getBshExpression() == null || rule.getBshExpression().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.RulesService.apply(com.soffid.iam.iga.api.Rule rule) - rule.bshExpression cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleApply(rule);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.RulesService.class).
			warn ("Error on RulesService.apply", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on RulesService.apply", (Throwable) __r[1]);
	}

	protected abstract void handleApply(com.soffid.iam.iga.api.Rule rule) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.RulesService#	 * @see com.soffid.iam.iga.service.RulesService#void delete(com.soffid.iam.iga.api.Rule rule)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.Rule rule)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rule == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.RulesService.delete(com.soffid.iam.iga.api.Rule rule) - rule cannot be null");
		}
		if (rule.getName() == null || rule.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.RulesService.delete(com.soffid.iam.iga.api.Rule rule) - rule.name cannot be null");
		}
		if (rule.getBshExpression() == null || rule.getBshExpression().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.RulesService.delete(com.soffid.iam.iga.api.Rule rule) - rule.bshExpression cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(rule);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.RulesService.class).
			warn ("Error on RulesService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on RulesService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.Rule rule) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.RulesService#	 * @see com.soffid.iam.iga.service.RulesService#void delete(com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (ruleAssignment == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.RulesService.delete(com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment) - ruleAssignment cannot be null");
		}
		if (ruleAssignment.getRoleId() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.RulesService.delete(com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment) - ruleAssignment.roleId cannot be null");
		}
		if (ruleAssignment.getRuleId() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.RulesService.delete(com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment) - ruleAssignment.ruleId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(ruleAssignment);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.RulesService.class).
			warn ("Error on RulesService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on RulesService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment) throws Exception;

	/**
	 * Gets the current <code>principal</code> if one has been set,
	 * otherwise returns <code>null</code>.
	 *
	 * @return the current principal
	 */
	protected java.security.Principal getPrincipal()
	{
		return com.soffid.iam.PrincipalStore.get();
	}

}
