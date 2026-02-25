//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.rc.service.IssuePolicyService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.rc.service.IssuePolicyService
 */
public abstract class IssuePolicyServiceBase
	implements com.soffid.iam.rc.service.IssuePolicyService
 {
	private com.soffid.iam.base.service.AdditionalDataService additionalDataService;

	/**
	 * Sets reference to <code>additionalDataService</code>.
	 */
	public void setAdditionalDataService (com.soffid.iam.base.service.AdditionalDataService additionalDataService) {
		this.additionalDataService = additionalDataService;
	}

	/**
	 * Gets reference to <code>additionalDataService</code>.
	 */
	public com.soffid.iam.base.service.AdditionalDataService getAdditionalDataService () {
		return additionalDataService;
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

	private com.soffid.iam.rc.model.IssueEntityDao issueEntityDao;

	/**
	 * Sets reference to <code>issueEntityDao</code>.
	 */
	public void setIssueEntityDao (com.soffid.iam.rc.model.IssueEntityDao issueEntityDao) {
		this.issueEntityDao = issueEntityDao;
	}

	/**
	 * Gets reference to <code>issueEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.IssueEntityDao getIssueEntityDao () {
		return issueEntityDao;
	}

	private com.soffid.iam.rc.model.IssueHostEntityDao issueHostEntityDao;

	/**
	 * Sets reference to <code>issueHostEntityDao</code>.
	 */
	public void setIssueHostEntityDao (com.soffid.iam.rc.model.IssueHostEntityDao issueHostEntityDao) {
		this.issueHostEntityDao = issueHostEntityDao;
	}

	/**
	 * Gets reference to <code>issueHostEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.IssueHostEntityDao getIssueHostEntityDao () {
		return issueHostEntityDao;
	}

	private com.soffid.iam.rc.model.IssuePolicyActionEntityDao issuePolicyActionEntityDao;

	/**
	 * Sets reference to <code>issuePolicyActionEntityDao</code>.
	 */
	public void setIssuePolicyActionEntityDao (com.soffid.iam.rc.model.IssuePolicyActionEntityDao issuePolicyActionEntityDao) {
		this.issuePolicyActionEntityDao = issuePolicyActionEntityDao;
	}

	/**
	 * Gets reference to <code>issuePolicyActionEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.IssuePolicyActionEntityDao getIssuePolicyActionEntityDao () {
		return issuePolicyActionEntityDao;
	}

	private com.soffid.iam.rc.model.IssuePolicyEntityDao issuePolicyEntityDao;

	/**
	 * Sets reference to <code>issuePolicyEntityDao</code>.
	 */
	public void setIssuePolicyEntityDao (com.soffid.iam.rc.model.IssuePolicyEntityDao issuePolicyEntityDao) {
		this.issuePolicyEntityDao = issuePolicyEntityDao;
	}

	/**
	 * Gets reference to <code>issuePolicyEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.IssuePolicyEntityDao getIssuePolicyEntityDao () {
		return issuePolicyEntityDao;
	}

	private com.soffid.iam.rc.model.IssueUserEntityDao issueUserEntityDao;

	/**
	 * Sets reference to <code>issueUserEntityDao</code>.
	 */
	public void setIssueUserEntityDao (com.soffid.iam.rc.model.IssueUserEntityDao issueUserEntityDao) {
		this.issueUserEntityDao = issueUserEntityDao;
	}

	/**
	 * Gets reference to <code>issueUserEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.IssueUserEntityDao getIssueUserEntityDao () {
		return issueUserEntityDao;
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
	 * @see com.soffid.iam.rc.service.IssuePolicyService#	 * @see com.soffid.iam.rc.service.IssuePolicyService#com.soffid.iam.rc.api.IssuePolicy update(com.soffid.iam.rc.api.IssuePolicy event)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.rc.api.IssuePolicy update(
		final com.soffid.iam.rc.api.IssuePolicy event)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (event == null) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.IssuePolicy com.soffid.iam.rc.service.IssuePolicyService.update(com.soffid.iam.rc.api.IssuePolicy event) - event cannot be null");
		}
		if (event.getId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.IssuePolicy com.soffid.iam.rc.service.IssuePolicyService.update(com.soffid.iam.rc.api.IssuePolicy event) - event.id cannot be null");
		}
		if (event.getType() == null || event.getType().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.IssuePolicy com.soffid.iam.rc.service.IssuePolicyService.update(com.soffid.iam.rc.api.IssuePolicy event) - event.type cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(event)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.rc.api.IssuePolicy) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.IssuePolicyService.class).
			warn ("Error on IssuePolicyService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on IssuePolicyService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.rc.api.IssuePolicy handleUpdate(com.soffid.iam.rc.api.IssuePolicy event) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.IssuePolicyService#	 * @see com.soffid.iam.rc.service.IssuePolicyService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.IssuePolicy> findIssuePolicies(com.soffid.zkdb.api.Query q)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.IssuePolicy> findIssuePolicies(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (q == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.IssuePolicy> com.soffid.iam.rc.service.IssuePolicyService.findIssuePolicies(com.soffid.zkdb.api.Query q) - q cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindIssuePolicies(q)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.IssuePolicy>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.IssuePolicyService.class).
			warn ("Error on IssuePolicyService.findIssuePolicies", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on IssuePolicyService.findIssuePolicies", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.IssuePolicy> handleFindIssuePolicies(com.soffid.zkdb.api.Query q) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.IssuePolicyService#	 * @see com.soffid.iam.rc.service.IssuePolicyService#java.util.List<com.soffid.iam.rc.api.IssueActionDefinition> listAutomaticActions()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.rc.api.IssueActionDefinition> listAutomaticActions()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleListAutomaticActions()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.rc.api.IssueActionDefinition>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.IssuePolicyService.class).
			warn ("Error on IssuePolicyService.listAutomaticActions", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on IssuePolicyService.listAutomaticActions", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.rc.api.IssueActionDefinition> handleListAutomaticActions() throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.IssuePolicyService#	 * @see com.soffid.iam.rc.service.IssuePolicyService#void createPolicies()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void createPolicies()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleCreatePolicies();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.IssuePolicyService.class).
			warn ("Error on IssuePolicyService.createPolicies", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on IssuePolicyService.createPolicies", (Throwable) __r[1]);
	}

	protected abstract void handleCreatePolicies() throws Exception;

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
