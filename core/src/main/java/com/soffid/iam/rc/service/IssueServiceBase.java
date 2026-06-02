//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.rc.service.IssueService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.rc.service.IssueService
 */
public abstract class IssueServiceBase
	implements com.soffid.iam.rc.service.IssueService
 {
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

	private com.soffid.iam.base.model.ConfigEntityDao configEntityDao;

	/**
	 * Sets reference to <code>configEntityDao</code>.
	 */
	public void setConfigEntityDao (com.soffid.iam.base.model.ConfigEntityDao configEntityDao) {
		this.configEntityDao = configEntityDao;
	}

	/**
	 * Gets reference to <code>configEntityDao</code>.
	 */
	public com.soffid.iam.base.model.ConfigEntityDao getConfigEntityDao () {
		return configEntityDao;
	}

	private com.soffid.iam.rc.service.GeoInformationService geoInformationService;

	/**
	 * Sets reference to <code>geoInformationService</code>.
	 */
	public void setGeoInformationService (com.soffid.iam.rc.service.GeoInformationService geoInformationService) {
		this.geoInformationService = geoInformationService;
	}

	/**
	 * Gets reference to <code>geoInformationService</code>.
	 */
	public com.soffid.iam.rc.service.GeoInformationService getGeoInformationService () {
		return geoInformationService;
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

	private com.soffid.iam.rc.service.IssuePolicyService issuePolicyService;

	/**
	 * Sets reference to <code>issuePolicyService</code>.
	 */
	public void setIssuePolicyService (com.soffid.iam.rc.service.IssuePolicyService issuePolicyService) {
		this.issuePolicyService = issuePolicyService;
	}

	/**
	 * Gets reference to <code>issuePolicyService</code>.
	 */
	public com.soffid.iam.rc.service.IssuePolicyService getIssuePolicyService () {
		return issuePolicyService;
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
	 * @see com.soffid.iam.rc.service.IssueService#	 * @see com.soffid.iam.rc.service.IssueService#com.soffid.iam.rc.api.Issue create(com.soffid.iam.rc.api.Issue event)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.rc.api.Issue create(
		final com.soffid.iam.rc.api.Issue event)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (event == null) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.create(com.soffid.iam.rc.api.Issue event) - event cannot be null");
		}
		if (event.getType() == null || event.getType().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.create(com.soffid.iam.rc.api.Issue event) - event.type cannot be null");
		}
		if (event.getTimes() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.create(com.soffid.iam.rc.api.Issue event) - event.times cannot be null");
		}
		if (event.getStatus() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.create(com.soffid.iam.rc.api.Issue event) - event.status cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(event)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.rc.api.Issue) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.IssueService.class).
			warn ("Error on IssueService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on IssueService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.rc.api.Issue handleCreate(com.soffid.iam.rc.api.Issue event) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.IssueService#	 * @see com.soffid.iam.rc.service.IssueService#com.soffid.iam.rc.api.Issue createInternalIssue(com.soffid.iam.rc.api.Issue event)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.rc.api.Issue createInternalIssue(
		final com.soffid.iam.rc.api.Issue event)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (event == null) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.createInternalIssue(com.soffid.iam.rc.api.Issue event) - event cannot be null");
		}
		if (event.getType() == null || event.getType().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.createInternalIssue(com.soffid.iam.rc.api.Issue event) - event.type cannot be null");
		}
		if (event.getTimes() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.createInternalIssue(com.soffid.iam.rc.api.Issue event) - event.times cannot be null");
		}
		if (event.getStatus() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.createInternalIssue(com.soffid.iam.rc.api.Issue event) - event.status cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreateInternalIssue(event)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.rc.api.Issue) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.IssueService.class).
			warn ("Error on IssueService.createInternalIssue", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on IssueService.createInternalIssue", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.rc.api.Issue handleCreateInternalIssue(com.soffid.iam.rc.api.Issue event) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.IssueService#	 * @see com.soffid.iam.rc.service.IssueService#com.soffid.iam.rc.api.Issue findIssueById(java.lang.Long id)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.rc.api.Issue findIssueById(
		final java.lang.Long id)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (id == null) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.findIssueById(java.lang.Long id) - id cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindIssueById(id)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.rc.api.Issue) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.IssueService.class).
			warn ("Error on IssueService.findIssueById", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on IssueService.findIssueById", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.rc.api.Issue handleFindIssueById(java.lang.Long id) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.IssueService#	 * @see com.soffid.iam.rc.service.IssueService#com.soffid.iam.rc.api.Issue notify(com.soffid.iam.rc.api.Issue issue, java.lang.String address, java.lang.String subject, java.lang.String body)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.rc.api.Issue notify(
		final com.soffid.iam.rc.api.Issue issue, 
		final java.lang.String address, 
		final java.lang.String subject, 
		final java.lang.String body)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (issue == null) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.notify(com.soffid.iam.rc.api.Issue issue, java.lang.String address, java.lang.String subject, java.lang.String body) - issue cannot be null");
		}
		if (issue.getType() == null || issue.getType().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.notify(com.soffid.iam.rc.api.Issue issue, java.lang.String address, java.lang.String subject, java.lang.String body) - issue.type cannot be null");
		}
		if (issue.getTimes() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.notify(com.soffid.iam.rc.api.Issue issue, java.lang.String address, java.lang.String subject, java.lang.String body) - issue.times cannot be null");
		}
		if (issue.getStatus() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.notify(com.soffid.iam.rc.api.Issue issue, java.lang.String address, java.lang.String subject, java.lang.String body) - issue.status cannot be null");
		}
		if (address == null || address.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.notify(com.soffid.iam.rc.api.Issue issue, java.lang.String address, java.lang.String subject, java.lang.String body) - address cannot be null");
		}
		if (subject == null || subject.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.notify(com.soffid.iam.rc.api.Issue issue, java.lang.String address, java.lang.String subject, java.lang.String body) - subject cannot be null");
		}
		if (body == null || body.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.notify(com.soffid.iam.rc.api.Issue issue, java.lang.String address, java.lang.String subject, java.lang.String body) - body cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleNotify(issue, address, subject, body)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.rc.api.Issue) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.IssueService.class).
			warn ("Error on IssueService.notify", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on IssueService.notify", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.rc.api.Issue handleNotify(com.soffid.iam.rc.api.Issue issue, java.lang.String address, java.lang.String subject, java.lang.String body) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.IssueService#	 * @see com.soffid.iam.rc.service.IssueService#com.soffid.iam.rc.api.Issue registerAction(com.soffid.iam.rc.api.Issue issue, java.lang.String action)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.rc.api.Issue registerAction(
		final com.soffid.iam.rc.api.Issue issue, 
		final java.lang.String action)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (issue == null) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.registerAction(com.soffid.iam.rc.api.Issue issue, java.lang.String action) - issue cannot be null");
		}
		if (issue.getType() == null || issue.getType().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.registerAction(com.soffid.iam.rc.api.Issue issue, java.lang.String action) - issue.type cannot be null");
		}
		if (issue.getTimes() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.registerAction(com.soffid.iam.rc.api.Issue issue, java.lang.String action) - issue.times cannot be null");
		}
		if (issue.getStatus() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.registerAction(com.soffid.iam.rc.api.Issue issue, java.lang.String action) - issue.status cannot be null");
		}
		if (action == null || action.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.registerAction(com.soffid.iam.rc.api.Issue issue, java.lang.String action) - action cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleRegisterAction(issue, action)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.rc.api.Issue) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.IssueService.class).
			warn ("Error on IssueService.registerAction", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on IssueService.registerAction", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.rc.api.Issue handleRegisterAction(com.soffid.iam.rc.api.Issue issue, java.lang.String action) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.IssueService#	 * @see com.soffid.iam.rc.service.IssueService#com.soffid.iam.rc.api.Issue update(com.soffid.iam.rc.api.Issue event)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.rc.api.Issue update(
		final com.soffid.iam.rc.api.Issue event)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (event == null) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.update(com.soffid.iam.rc.api.Issue event) - event cannot be null");
		}
		if (event.getType() == null || event.getType().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.update(com.soffid.iam.rc.api.Issue event) - event.type cannot be null");
		}
		if (event.getTimes() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.update(com.soffid.iam.rc.api.Issue event) - event.times cannot be null");
		}
		if (event.getStatus() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Issue com.soffid.iam.rc.service.IssueService.update(com.soffid.iam.rc.api.Issue event) - event.status cannot be null");
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
			return (com.soffid.iam.rc.api.Issue) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.IssueService.class).
			warn ("Error on IssueService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on IssueService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.rc.api.Issue handleUpdate(com.soffid.iam.rc.api.Issue event) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.IssueService#	 * @see com.soffid.iam.rc.service.IssueService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Issue> findIssues(com.soffid.zkdb.api.Query query)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Issue> findIssues(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (query == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Issue> com.soffid.iam.rc.service.IssueService.findIssues(com.soffid.zkdb.api.Query query) - query cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindIssues(query)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Issue>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.IssueService.class).
			warn ("Error on IssueService.findIssues", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on IssueService.findIssues", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Issue> handleFindIssues(com.soffid.zkdb.api.Query query) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.IssueService#	 * @see com.soffid.iam.rc.service.IssueService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Issue> findMyIssues(com.soffid.zkdb.api.Query query)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Issue> findMyIssues(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindMyIssues(query)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Issue>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.IssueService.class).
			warn ("Error on IssueService.findMyIssues", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on IssueService.findMyIssues", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Issue> handleFindMyIssues(com.soffid.zkdb.api.Query query) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.IssueService#	 * @see com.soffid.iam.rc.service.IssueService#int countMyIssues()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public int countMyIssues()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCountMyIssues()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Integer) __r[0]).intValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.IssueService.class).
			warn ("Error on IssueService.countMyIssues", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on IssueService.countMyIssues", (Throwable) __r[1]);
	}

	protected abstract int handleCountMyIssues() throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.IssueService#	 * @see com.soffid.iam.rc.service.IssueService#java.util.List<com.soffid.iam.rc.api.Issue> findIssuesByUser(java.lang.String user)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.rc.api.Issue> findIssuesByUser(
		final java.lang.String user)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindIssuesByUser(user)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.rc.api.Issue>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.IssueService.class).
			warn ("Error on IssueService.findIssuesByUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on IssueService.findIssuesByUser", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.rc.api.Issue> handleFindIssuesByUser(java.lang.String user) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.IssueService#	 * @see com.soffid.iam.rc.service.IssueService#java.util.List<com.soffid.iam.rc.api.IssueActionDefinition> listManualActions()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.rc.api.IssueActionDefinition> listManualActions()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleListManualActions()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.rc.api.IssueActionDefinition>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.IssueService.class).
			warn ("Error on IssueService.listManualActions", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on IssueService.listManualActions", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.rc.api.IssueActionDefinition> handleListManualActions() throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.IssueService#	 * @see com.soffid.iam.rc.service.IssueService#void delete(com.soffid.iam.rc.api.Issue event)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.rc.api.Issue event)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (event == null) {
			throw new IllegalArgumentException("void com.soffid.iam.rc.service.IssueService.delete(com.soffid.iam.rc.api.Issue event) - event cannot be null");
		}
		if (event.getType() == null || event.getType().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.rc.service.IssueService.delete(com.soffid.iam.rc.api.Issue event) - event.type cannot be null");
		}
		if (event.getTimes() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.rc.service.IssueService.delete(com.soffid.iam.rc.api.Issue event) - event.times cannot be null");
		}
		if (event.getStatus() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.rc.service.IssueService.delete(com.soffid.iam.rc.api.Issue event) - event.status cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(event);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.IssueService.class).
			warn ("Error on IssueService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on IssueService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.rc.api.Issue event) throws Exception;

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
