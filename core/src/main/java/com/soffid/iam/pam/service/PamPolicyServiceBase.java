//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.pam.service.PamPolicyService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.pam.service.PamPolicyService
 */
public abstract class PamPolicyServiceBase
	implements com.soffid.iam.pam.service.PamPolicyService
 {
	private com.soffid.iam.am.model.AccessLogEntityDao accessLogEntityDao;

	/**
	 * Sets reference to <code>accessLogEntityDao</code>.
	 */
	public void setAccessLogEntityDao (com.soffid.iam.am.model.AccessLogEntityDao accessLogEntityDao) {
		this.accessLogEntityDao = accessLogEntityDao;
	}

	/**
	 * Gets reference to <code>accessLogEntityDao</code>.
	 */
	public com.soffid.iam.am.model.AccessLogEntityDao getAccessLogEntityDao () {
		return accessLogEntityDao;
	}

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

	private com.soffid.iam.am.model.HostEntityDao hostEntityDao;

	/**
	 * Sets reference to <code>hostEntityDao</code>.
	 */
	public void setHostEntityDao (com.soffid.iam.am.model.HostEntityDao hostEntityDao) {
		this.hostEntityDao = hostEntityDao;
	}

	/**
	 * Gets reference to <code>hostEntityDao</code>.
	 */
	public com.soffid.iam.am.model.HostEntityDao getHostEntityDao () {
		return hostEntityDao;
	}

	private com.soffid.iam.rc.service.IssueService issueService;

	/**
	 * Sets reference to <code>issueService</code>.
	 */
	public void setIssueService (com.soffid.iam.rc.service.IssueService issueService) {
		this.issueService = issueService;
	}

	/**
	 * Gets reference to <code>issueService</code>.
	 */
	public com.soffid.iam.rc.service.IssueService getIssueService () {
		return issueService;
	}

	private com.soffid.iam.pam.model.JumpServerEntityDao jumpServerEntityDao;

	/**
	 * Sets reference to <code>jumpServerEntityDao</code>.
	 */
	public void setJumpServerEntityDao (com.soffid.iam.pam.model.JumpServerEntityDao jumpServerEntityDao) {
		this.jumpServerEntityDao = jumpServerEntityDao;
	}

	/**
	 * Gets reference to <code>jumpServerEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.JumpServerEntityDao getJumpServerEntityDao () {
		return jumpServerEntityDao;
	}

	private com.soffid.iam.pam.model.JumpServerGroupEntityDao jumpServerGroupEntityDao;

	/**
	 * Sets reference to <code>jumpServerGroupEntityDao</code>.
	 */
	public void setJumpServerGroupEntityDao (com.soffid.iam.pam.model.JumpServerGroupEntityDao jumpServerGroupEntityDao) {
		this.jumpServerGroupEntityDao = jumpServerGroupEntityDao;
	}

	/**
	 * Gets reference to <code>jumpServerGroupEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.JumpServerGroupEntityDao getJumpServerGroupEntityDao () {
		return jumpServerGroupEntityDao;
	}

	private com.soffid.iam.iga.service.MailService mailService;

	/**
	 * Sets reference to <code>mailService</code>.
	 */
	public void setMailService (com.soffid.iam.iga.service.MailService mailService) {
		this.mailService = mailService;
	}

	/**
	 * Gets reference to <code>mailService</code>.
	 */
	public com.soffid.iam.iga.service.MailService getMailService () {
		return mailService;
	}

	private com.soffid.iam.pam.model.PamActionEntityDao pamActionEntityDao;

	/**
	 * Sets reference to <code>pamActionEntityDao</code>.
	 */
	public void setPamActionEntityDao (com.soffid.iam.pam.model.PamActionEntityDao pamActionEntityDao) {
		this.pamActionEntityDao = pamActionEntityDao;
	}

	/**
	 * Gets reference to <code>pamActionEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.PamActionEntityDao getPamActionEntityDao () {
		return pamActionEntityDao;
	}

	private com.soffid.iam.pam.model.PamPolicyEntityDao pamPolicyEntityDao;

	/**
	 * Sets reference to <code>pamPolicyEntityDao</code>.
	 */
	public void setPamPolicyEntityDao (com.soffid.iam.pam.model.PamPolicyEntityDao pamPolicyEntityDao) {
		this.pamPolicyEntityDao = pamPolicyEntityDao;
	}

	/**
	 * Gets reference to <code>pamPolicyEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.PamPolicyEntityDao getPamPolicyEntityDao () {
		return pamPolicyEntityDao;
	}

	private com.soffid.iam.pam.model.PamPolicyJITPermissionEntityDao pamPolicyJITPermissionEntityDao;

	/**
	 * Sets reference to <code>pamPolicyJITPermissionEntityDao</code>.
	 */
	public void setPamPolicyJITPermissionEntityDao (com.soffid.iam.pam.model.PamPolicyJITPermissionEntityDao pamPolicyJITPermissionEntityDao) {
		this.pamPolicyJITPermissionEntityDao = pamPolicyJITPermissionEntityDao;
	}

	/**
	 * Gets reference to <code>pamPolicyJITPermissionEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.PamPolicyJITPermissionEntityDao getPamPolicyJITPermissionEntityDao () {
		return pamPolicyJITPermissionEntityDao;
	}

	private com.soffid.iam.pam.model.PamRuleEntityDao pamRuleEntityDao;

	/**
	 * Sets reference to <code>pamRuleEntityDao</code>.
	 */
	public void setPamRuleEntityDao (com.soffid.iam.pam.model.PamRuleEntityDao pamRuleEntityDao) {
		this.pamRuleEntityDao = pamRuleEntityDao;
	}

	/**
	 * Gets reference to <code>pamRuleEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.PamRuleEntityDao getPamRuleEntityDao () {
		return pamRuleEntityDao;
	}

	private com.soffid.iam.pam.service.PamSecurityHandlerService pamSecurityHandlerService;

	/**
	 * Sets reference to <code>pamSecurityHandlerService</code>.
	 */
	public void setPamSecurityHandlerService (com.soffid.iam.pam.service.PamSecurityHandlerService pamSecurityHandlerService) {
		this.pamSecurityHandlerService = pamSecurityHandlerService;
	}

	/**
	 * Gets reference to <code>pamSecurityHandlerService</code>.
	 */
	public com.soffid.iam.pam.service.PamSecurityHandlerService getPamSecurityHandlerService () {
		return pamSecurityHandlerService;
	}

	private com.soffid.iam.am.model.ServiceEntityDao serviceEntityDao;

	/**
	 * Sets reference to <code>serviceEntityDao</code>.
	 */
	public void setServiceEntityDao (com.soffid.iam.am.model.ServiceEntityDao serviceEntityDao) {
		this.serviceEntityDao = serviceEntityDao;
	}

	/**
	 * Gets reference to <code>serviceEntityDao</code>.
	 */
	public com.soffid.iam.am.model.ServiceEntityDao getServiceEntityDao () {
		return serviceEntityDao;
	}

	private com.soffid.iam.am.model.SessionEntityDao sessionEntityDao;

	/**
	 * Sets reference to <code>sessionEntityDao</code>.
	 */
	public void setSessionEntityDao (com.soffid.iam.am.model.SessionEntityDao sessionEntityDao) {
		this.sessionEntityDao = sessionEntityDao;
	}

	/**
	 * Gets reference to <code>sessionEntityDao</code>.
	 */
	public com.soffid.iam.am.model.SessionEntityDao getSessionEntityDao () {
		return sessionEntityDao;
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


	/**
	 * @see com.soffid.iam.pam.service.PamPolicyService#	 * @see com.soffid.iam.pam.service.PamPolicyService#com.soffid.iam.pam.api.PamAction updateAction(com.soffid.iam.pam.api.PamAction action)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.pam.api.PamAction updateAction(
		final com.soffid.iam.pam.api.PamAction action)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (action == null) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.PamAction com.soffid.iam.pam.service.PamPolicyService.updateAction(com.soffid.iam.pam.api.PamAction action) - action cannot be null");
		}
		if (action.getPolicyName() == null || action.getPolicyName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.PamAction com.soffid.iam.pam.service.PamPolicyService.updateAction(com.soffid.iam.pam.api.PamAction action) - action.policyName cannot be null");
		}
		if (action.getRuleName() == null || action.getRuleName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.PamAction com.soffid.iam.pam.service.PamPolicyService.updateAction(com.soffid.iam.pam.api.PamAction action) - action.ruleName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdateAction(action)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.pam.api.PamAction) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamPolicyService.class).
			warn ("Error on PamPolicyService.updateAction", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamPolicyService.updateAction", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.pam.api.PamAction handleUpdateAction(com.soffid.iam.pam.api.PamAction action) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamPolicyService#	 * @see com.soffid.iam.pam.service.PamPolicyService#com.soffid.iam.pam.api.PamPolicy createPolicy(com.soffid.iam.pam.api.PamPolicy policy)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.pam.api.PamPolicy createPolicy(
		final com.soffid.iam.pam.api.PamPolicy policy)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (policy == null) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.PamPolicy com.soffid.iam.pam.service.PamPolicyService.createPolicy(com.soffid.iam.pam.api.PamPolicy policy) - policy cannot be null");
		}
		if (policy.getName() == null || policy.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.PamPolicy com.soffid.iam.pam.service.PamPolicyService.createPolicy(com.soffid.iam.pam.api.PamPolicy policy) - policy.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreatePolicy(policy)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.pam.api.PamPolicy) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamPolicyService.class).
			warn ("Error on PamPolicyService.createPolicy", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamPolicyService.createPolicy", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.pam.api.PamPolicy handleCreatePolicy(com.soffid.iam.pam.api.PamPolicy policy) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamPolicyService#	 * @see com.soffid.iam.pam.service.PamPolicyService#com.soffid.iam.pam.api.PamPolicy updatePolicy(com.soffid.iam.pam.api.PamPolicy policy)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.pam.api.PamPolicy updatePolicy(
		final com.soffid.iam.pam.api.PamPolicy policy)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (policy == null) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.PamPolicy com.soffid.iam.pam.service.PamPolicyService.updatePolicy(com.soffid.iam.pam.api.PamPolicy policy) - policy cannot be null");
		}
		if (policy.getName() == null || policy.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.PamPolicy com.soffid.iam.pam.service.PamPolicyService.updatePolicy(com.soffid.iam.pam.api.PamPolicy policy) - policy.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdatePolicy(policy)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.pam.api.PamPolicy) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamPolicyService.class).
			warn ("Error on PamPolicyService.updatePolicy", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamPolicyService.updatePolicy", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.pam.api.PamPolicy handleUpdatePolicy(com.soffid.iam.pam.api.PamPolicy policy) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamPolicyService#	 * @see com.soffid.iam.pam.service.PamPolicyService#com.soffid.iam.pam.api.PamRule createRule(com.soffid.iam.pam.api.PamRule rule)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.pam.api.PamRule createRule(
		final com.soffid.iam.pam.api.PamRule rule)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (rule == null) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.PamRule com.soffid.iam.pam.service.PamPolicyService.createRule(com.soffid.iam.pam.api.PamRule rule) - rule cannot be null");
		}
		if (rule.getName() == null || rule.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.PamRule com.soffid.iam.pam.service.PamPolicyService.createRule(com.soffid.iam.pam.api.PamRule rule) - rule.name cannot be null");
		}
		if (rule.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.PamRule com.soffid.iam.pam.service.PamPolicyService.createRule(com.soffid.iam.pam.api.PamRule rule) - rule.type cannot be null");
		}
		if (rule.getContent() == null || rule.getContent().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.PamRule com.soffid.iam.pam.service.PamPolicyService.createRule(com.soffid.iam.pam.api.PamRule rule) - rule.content cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreateRule(rule)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.pam.api.PamRule) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamPolicyService.class).
			warn ("Error on PamPolicyService.createRule", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamPolicyService.createRule", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.pam.api.PamRule handleCreateRule(com.soffid.iam.pam.api.PamRule rule) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamPolicyService#	 * @see com.soffid.iam.pam.service.PamPolicyService#com.soffid.iam.pam.api.PamRule updateRule(com.soffid.iam.pam.api.PamRule rule)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.pam.api.PamRule updateRule(
		final com.soffid.iam.pam.api.PamRule rule)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (rule == null) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.PamRule com.soffid.iam.pam.service.PamPolicyService.updateRule(com.soffid.iam.pam.api.PamRule rule) - rule cannot be null");
		}
		if (rule.getName() == null || rule.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.PamRule com.soffid.iam.pam.service.PamPolicyService.updateRule(com.soffid.iam.pam.api.PamRule rule) - rule.name cannot be null");
		}
		if (rule.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.PamRule com.soffid.iam.pam.service.PamPolicyService.updateRule(com.soffid.iam.pam.api.PamRule rule) - rule.type cannot be null");
		}
		if (rule.getContent() == null || rule.getContent().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.PamRule com.soffid.iam.pam.service.PamPolicyService.updateRule(com.soffid.iam.pam.api.PamRule rule) - rule.content cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdateRule(rule)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.pam.api.PamRule) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamPolicyService.class).
			warn ("Error on PamPolicyService.updateRule", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamPolicyService.updateRule", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.pam.api.PamRule handleUpdateRule(com.soffid.iam.pam.api.PamRule rule) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamPolicyService#	 * @see com.soffid.iam.pam.service.PamPolicyService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.pam.api.PamPolicy> findPolicies(com.soffid.zkdb.api.Query q)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.pam.api.PamPolicy> findPolicies(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (q == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.pam.api.PamPolicy> com.soffid.iam.pam.service.PamPolicyService.findPolicies(com.soffid.zkdb.api.Query q) - q cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindPolicies(q)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.pam.api.PamPolicy>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamPolicyService.class).
			warn ("Error on PamPolicyService.findPolicies", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamPolicyService.findPolicies", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.pam.api.PamPolicy> handleFindPolicies(com.soffid.zkdb.api.Query q) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamPolicyService#	 * @see com.soffid.iam.pam.service.PamPolicyService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.pam.api.PamRule> findRules(com.soffid.zkdb.api.Query q)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.pam.api.PamRule> findRules(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (q == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.pam.api.PamRule> com.soffid.iam.pam.service.PamPolicyService.findRules(com.soffid.zkdb.api.Query q) - q cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRules(q)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.pam.api.PamRule>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamPolicyService.class).
			warn ("Error on PamPolicyService.findRules", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamPolicyService.findRules", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.pam.api.PamRule> handleFindRules(com.soffid.zkdb.api.Query q) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamPolicyService#	 * @see com.soffid.iam.pam.service.PamPolicyService#java.util.List<com.soffid.iam.pam.api.PamAction> findPolicyActions(com.soffid.iam.pam.api.PamPolicy policy)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.pam.api.PamAction> findPolicyActions(
		final com.soffid.iam.pam.api.PamPolicy policy)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (policy == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.pam.api.PamAction> com.soffid.iam.pam.service.PamPolicyService.findPolicyActions(com.soffid.iam.pam.api.PamPolicy policy) - policy cannot be null");
		}
		if (policy.getName() == null || policy.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.pam.api.PamAction> com.soffid.iam.pam.service.PamPolicyService.findPolicyActions(com.soffid.iam.pam.api.PamPolicy policy) - policy.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindPolicyActions(policy)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.pam.api.PamAction>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamPolicyService.class).
			warn ("Error on PamPolicyService.findPolicyActions", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamPolicyService.findPolicyActions", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.pam.api.PamAction> handleFindPolicyActions(com.soffid.iam.pam.api.PamPolicy policy) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamPolicyService#	 * @see com.soffid.iam.pam.service.PamPolicyService#void applyRule(java.lang.String sessionKey, java.lang.String policyName, java.lang.String ruleName)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void applyRule(
		final java.lang.String sessionKey, 
		final java.lang.String policyName, 
		final java.lang.String ruleName)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (sessionKey == null || sessionKey.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.PamPolicyService.applyRule(java.lang.String sessionKey, java.lang.String policyName, java.lang.String ruleName) - sessionKey cannot be null");
		}
		if (policyName == null || policyName.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.PamPolicyService.applyRule(java.lang.String sessionKey, java.lang.String policyName, java.lang.String ruleName) - policyName cannot be null");
		}
		if (ruleName == null || ruleName.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.PamPolicyService.applyRule(java.lang.String sessionKey, java.lang.String policyName, java.lang.String ruleName) - ruleName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleApplyRule(sessionKey, policyName, ruleName);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamPolicyService.class).
			warn ("Error on PamPolicyService.applyRule", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamPolicyService.applyRule", (Throwable) __r[1]);
	}

	protected abstract void handleApplyRule(java.lang.String sessionKey, java.lang.String policyName, java.lang.String ruleName) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamPolicyService#	 * @see com.soffid.iam.pam.service.PamPolicyService#void deletePolicy(com.soffid.iam.pam.api.PamPolicy policy)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void deletePolicy(
		final com.soffid.iam.pam.api.PamPolicy policy)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (policy == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.PamPolicyService.deletePolicy(com.soffid.iam.pam.api.PamPolicy policy) - policy cannot be null");
		}
		if (policy.getName() == null || policy.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.PamPolicyService.deletePolicy(com.soffid.iam.pam.api.PamPolicy policy) - policy.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDeletePolicy(policy);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamPolicyService.class).
			warn ("Error on PamPolicyService.deletePolicy", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamPolicyService.deletePolicy", (Throwable) __r[1]);
	}

	protected abstract void handleDeletePolicy(com.soffid.iam.pam.api.PamPolicy policy) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamPolicyService#	 * @see com.soffid.iam.pam.service.PamPolicyService#void deleteRule(com.soffid.iam.pam.api.PamRule rule)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void deleteRule(
		final com.soffid.iam.pam.api.PamRule rule)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (rule == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.PamPolicyService.deleteRule(com.soffid.iam.pam.api.PamRule rule) - rule cannot be null");
		}
		if (rule.getName() == null || rule.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.PamPolicyService.deleteRule(com.soffid.iam.pam.api.PamRule rule) - rule.name cannot be null");
		}
		if (rule.getType() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.PamPolicyService.deleteRule(com.soffid.iam.pam.api.PamRule rule) - rule.type cannot be null");
		}
		if (rule.getContent() == null || rule.getContent().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.PamPolicyService.deleteRule(com.soffid.iam.pam.api.PamRule rule) - rule.content cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDeleteRule(rule);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamPolicyService.class).
			warn ("Error on PamPolicyService.deleteRule", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamPolicyService.deleteRule", (Throwable) __r[1]);
	}

	protected abstract void handleDeleteRule(com.soffid.iam.pam.api.PamRule rule) throws Exception;

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
