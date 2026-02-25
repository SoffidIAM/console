//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.impl.service.RuleEvaluatorService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.impl.service.RuleEvaluatorService
 */
public abstract class RuleEvaluatorServiceBase
	implements com.soffid.iam.impl.service.RuleEvaluatorService
 {
	private com.soffid.iam.base.model.AccountEntityDao accountEntityDao;

	/**
	 * Sets reference to <code>accountEntityDao</code>.
	 */
	public void setAccountEntityDao (com.soffid.iam.base.model.AccountEntityDao accountEntityDao) {
		this.accountEntityDao = accountEntityDao;
	}

	/**
	 * Gets reference to <code>accountEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AccountEntityDao getAccountEntityDao () {
		return accountEntityDao;
	}

	private com.soffid.iam.base.service.AccountService accountService;

	/**
	 * Sets reference to <code>accountService</code>.
	 */
	public void setAccountService (com.soffid.iam.base.service.AccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * Gets reference to <code>accountService</code>.
	 */
	public com.soffid.iam.base.service.AccountService getAccountService () {
		return accountService;
	}

	private com.soffid.iam.iga.service.ApplicationService applicationService;

	/**
	 * Sets reference to <code>applicationService</code>.
	 */
	public void setApplicationService (com.soffid.iam.iga.service.ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	/**
	 * Gets reference to <code>applicationService</code>.
	 */
	public com.soffid.iam.iga.service.ApplicationService getApplicationService () {
		return applicationService;
	}

	private com.soffid.iam.impl.service.AsyncRunnerService asyncRunnerService;

	/**
	 * Sets reference to <code>asyncRunnerService</code>.
	 */
	public void setAsyncRunnerService (com.soffid.iam.impl.service.AsyncRunnerService asyncRunnerService) {
		this.asyncRunnerService = asyncRunnerService;
	}

	/**
	 * Gets reference to <code>asyncRunnerService</code>.
	 */
	public com.soffid.iam.impl.service.AsyncRunnerService getAsyncRunnerService () {
		return asyncRunnerService;
	}

	private com.soffid.iam.iga.service.DispatcherService dispatcherService;

	/**
	 * Sets reference to <code>dispatcherService</code>.
	 */
	public void setDispatcherService (com.soffid.iam.iga.service.DispatcherService dispatcherService) {
		this.dispatcherService = dispatcherService;
	}

	/**
	 * Gets reference to <code>dispatcherService</code>.
	 */
	public com.soffid.iam.iga.service.DispatcherService getDispatcherService () {
		return dispatcherService;
	}

	private com.soffid.iam.iga.model.GroupEntityDao groupEntityDao;

	/**
	 * Sets reference to <code>groupEntityDao</code>.
	 */
	public void setGroupEntityDao (com.soffid.iam.iga.model.GroupEntityDao groupEntityDao) {
		this.groupEntityDao = groupEntityDao;
	}

	/**
	 * Gets reference to <code>groupEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.GroupEntityDao getGroupEntityDao () {
		return groupEntityDao;
	}

	private com.soffid.iam.iga.model.RoleAccountEntityDao roleAccountEntityDao;

	/**
	 * Sets reference to <code>roleAccountEntityDao</code>.
	 */
	public void setRoleAccountEntityDao (com.soffid.iam.iga.model.RoleAccountEntityDao roleAccountEntityDao) {
		this.roleAccountEntityDao = roleAccountEntityDao;
	}

	/**
	 * Gets reference to <code>roleAccountEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleAccountEntityDao getRoleAccountEntityDao () {
		return roleAccountEntityDao;
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

	private com.soffid.iam.impl.service.SoffidEventListener soffidEventListener;

	/**
	 * Sets reference to <code>soffidEventListener</code>.
	 */
	public void setSoffidEventListener (com.soffid.iam.impl.service.SoffidEventListener soffidEventListener) {
		this.soffidEventListener = soffidEventListener;
	}

	/**
	 * Gets reference to <code>soffidEventListener</code>.
	 */
	public com.soffid.iam.impl.service.SoffidEventListener getSoffidEventListener () {
		return soffidEventListener;
	}

	private com.soffid.iam.iga.model.SystemEntityDao systemEntityDao;

	/**
	 * Sets reference to <code>systemEntityDao</code>.
	 */
	public void setSystemEntityDao (com.soffid.iam.iga.model.SystemEntityDao systemEntityDao) {
		this.systemEntityDao = systemEntityDao;
	}

	/**
	 * Gets reference to <code>systemEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.SystemEntityDao getSystemEntityDao () {
		return systemEntityDao;
	}

	private com.soffid.iam.sync.model.TaskEntityDao taskEntityDao;

	/**
	 * Sets reference to <code>taskEntityDao</code>.
	 */
	public void setTaskEntityDao (com.soffid.iam.sync.model.TaskEntityDao taskEntityDao) {
		this.taskEntityDao = taskEntityDao;
	}

	/**
	 * Gets reference to <code>taskEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.TaskEntityDao getTaskEntityDao () {
		return taskEntityDao;
	}

	private com.soffid.iam.base.model.UserEntityDao userEntityDao;

	/**
	 * Sets reference to <code>userEntityDao</code>.
	 */
	public void setUserEntityDao (com.soffid.iam.base.model.UserEntityDao userEntityDao) {
		this.userEntityDao = userEntityDao;
	}

	/**
	 * Gets reference to <code>userEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserEntityDao getUserEntityDao () {
		return userEntityDao;
	}

	private com.soffid.iam.base.service.UserService userService;

	/**
	 * Sets reference to <code>userService</code>.
	 */
	public void setUserService (com.soffid.iam.base.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Gets reference to <code>userService</code>.
	 */
	public com.soffid.iam.base.service.UserService getUserService () {
		return userService;
	}


	/**
	 * @see com.soffid.iam.impl.service.RuleEvaluatorService#	 * @see com.soffid.iam.impl.service.RuleEvaluatorService#com.soffid.iam.base.api.AsyncProcessTracker applyAsync(com.soffid.iam.iga.model.RuleEntity rule)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.AsyncProcessTracker applyAsync(
		final com.soffid.iam.iga.model.RuleEntity rule)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rule == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AsyncProcessTracker com.soffid.iam.impl.service.RuleEvaluatorService.applyAsync(com.soffid.iam.iga.model.RuleEntity rule) - rule cannot be null");
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
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.RuleEvaluatorService.class).
			warn ("Error on RuleEvaluatorService.applyAsync", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on RuleEvaluatorService.applyAsync", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.AsyncProcessTracker handleApplyAsync(com.soffid.iam.iga.model.RuleEntity rule) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.RuleEvaluatorService#	 * @see com.soffid.iam.impl.service.RuleEvaluatorService#com.soffid.iam.base.api.AsyncProcessTracker queryProcessStatus(com.soffid.iam.base.api.AsyncProcessTracker process)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.AsyncProcessTracker queryProcessStatus(
		final com.soffid.iam.base.api.AsyncProcessTracker process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (process == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AsyncProcessTracker com.soffid.iam.impl.service.RuleEvaluatorService.queryProcessStatus(com.soffid.iam.base.api.AsyncProcessTracker process) - process cannot be null");
		}
		if (process.getId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AsyncProcessTracker com.soffid.iam.impl.service.RuleEvaluatorService.queryProcessStatus(com.soffid.iam.base.api.AsyncProcessTracker process) - process.id cannot be null");
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
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.RuleEvaluatorService.class).
			warn ("Error on RuleEvaluatorService.queryProcessStatus", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on RuleEvaluatorService.queryProcessStatus", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.AsyncProcessTracker handleQueryProcessStatus(com.soffid.iam.base.api.AsyncProcessTracker process) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.RuleEvaluatorService#	 * @see com.soffid.iam.impl.service.RuleEvaluatorService#java.io.File dryRun(com.soffid.iam.iga.model.RuleEntity rule)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class}, readOnly=true)
	public java.io.File dryRun(
		final com.soffid.iam.iga.model.RuleEntity rule)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rule == null) {
			throw new IllegalArgumentException("java.io.File com.soffid.iam.impl.service.RuleEvaluatorService.dryRun(com.soffid.iam.iga.model.RuleEntity rule) - rule cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleDryRun(rule)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.io.File) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.RuleEvaluatorService.class).
			warn ("Error on RuleEvaluatorService.dryRun", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on RuleEvaluatorService.dryRun", (Throwable) __r[1]);
	}

	protected abstract java.io.File handleDryRun(com.soffid.iam.iga.model.RuleEntity rule) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.RuleEvaluatorService#	 * @see com.soffid.iam.impl.service.RuleEvaluatorService#void apply(com.soffid.iam.iga.model.RuleEntity rule)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void apply(
		final com.soffid.iam.iga.model.RuleEntity rule)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rule == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.RuleEvaluatorService.apply(com.soffid.iam.iga.model.RuleEntity rule) - rule cannot be null");
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
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.RuleEvaluatorService.class).
			warn ("Error on RuleEvaluatorService.apply", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on RuleEvaluatorService.apply", (Throwable) __r[1]);
	}

	protected abstract void handleApply(com.soffid.iam.iga.model.RuleEntity rule) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.RuleEvaluatorService#	 * @see com.soffid.iam.impl.service.RuleEvaluatorService#void apply(com.soffid.iam.iga.model.RuleEntity rule, com.soffid.iam.base.model.UserEntity user)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void apply(
		final com.soffid.iam.iga.model.RuleEntity rule, 
		final com.soffid.iam.base.model.UserEntity user)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rule == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.RuleEvaluatorService.apply(com.soffid.iam.iga.model.RuleEntity rule, com.soffid.iam.base.model.UserEntity user) - rule cannot be null");
		}
		if (user == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.RuleEvaluatorService.apply(com.soffid.iam.iga.model.RuleEntity rule, com.soffid.iam.base.model.UserEntity user) - user cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleApply(rule, user);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.RuleEvaluatorService.class).
			warn ("Error on RuleEvaluatorService.apply", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on RuleEvaluatorService.apply", (Throwable) __r[1]);
	}

	protected abstract void handleApply(com.soffid.iam.iga.model.RuleEntity rule, com.soffid.iam.base.model.UserEntity user) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.RuleEvaluatorService#	 * @see com.soffid.iam.impl.service.RuleEvaluatorService#void applyRules(com.soffid.iam.base.model.UserEntity user)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void applyRules(
		final com.soffid.iam.base.model.UserEntity user)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.RuleEvaluatorService.applyRules(com.soffid.iam.base.model.UserEntity user) - user cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleApplyRules(user);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.RuleEvaluatorService.class).
			warn ("Error on RuleEvaluatorService.applyRules", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on RuleEvaluatorService.applyRules", (Throwable) __r[1]);
	}

	protected abstract void handleApplyRules(com.soffid.iam.base.model.UserEntity user) throws Exception;

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
