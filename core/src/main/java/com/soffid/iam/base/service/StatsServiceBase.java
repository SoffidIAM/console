//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.base.service.StatsService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.base.service.StatsService
 */
public abstract class StatsServiceBase
	implements com.soffid.iam.base.service.StatsService
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

	private com.soffid.iam.base.model.AuthorizationEntityDao authorizationEntityDao;

	/**
	 * Sets reference to <code>authorizationEntityDao</code>.
	 */
	public void setAuthorizationEntityDao (com.soffid.iam.base.model.AuthorizationEntityDao authorizationEntityDao) {
		this.authorizationEntityDao = authorizationEntityDao;
	}

	/**
	 * Gets reference to <code>authorizationEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AuthorizationEntityDao getAuthorizationEntityDao () {
		return authorizationEntityDao;
	}

	private com.soffid.iam.base.service.AuthorizationService authorizationService;

	/**
	 * Sets reference to <code>authorizationService</code>.
	 */
	public void setAuthorizationService (com.soffid.iam.base.service.AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}

	/**
	 * Gets reference to <code>authorizationService</code>.
	 */
	public com.soffid.iam.base.service.AuthorizationService getAuthorizationService () {
		return authorizationService;
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

	private com.soffid.iam.pam.service.PamSessionService pamSessionService;

	/**
	 * Sets reference to <code>pamSessionService</code>.
	 */
	public void setPamSessionService (com.soffid.iam.pam.service.PamSessionService pamSessionService) {
		this.pamSessionService = pamSessionService;
	}

	/**
	 * Gets reference to <code>pamSessionService</code>.
	 */
	public com.soffid.iam.pam.service.PamSessionService getPamSessionService () {
		return pamSessionService;
	}

	private com.soffid.iam.base.model.StatsEntityDao statsEntityDao;

	/**
	 * Sets reference to <code>statsEntityDao</code>.
	 */
	public void setStatsEntityDao (com.soffid.iam.base.model.StatsEntityDao statsEntityDao) {
		this.statsEntityDao = statsEntityDao;
	}

	/**
	 * Gets reference to <code>statsEntityDao</code>.
	 */
	public com.soffid.iam.base.model.StatsEntityDao getStatsEntityDao () {
		return statsEntityDao;
	}


	/**
	 * @see com.soffid.iam.base.service.StatsService#	 * @see com.soffid.iam.base.service.StatsService#com.soffid.iam.base.api.Stats findStats(java.lang.String name, java.util.Date since, java.util.Date until, int step)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Stats findStats(
		final java.lang.String name, 
		final java.util.Date since, 
		final java.util.Date until, 
		final int step)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Stats com.soffid.iam.base.service.StatsService.findStats(java.lang.String name, java.util.Date since, java.util.Date until, int step) - name cannot be null");
		}
		if (since == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Stats com.soffid.iam.base.service.StatsService.findStats(java.lang.String name, java.util.Date since, java.util.Date until, int step) - since cannot be null");
		}
		if (until == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Stats com.soffid.iam.base.service.StatsService.findStats(java.lang.String name, java.util.Date since, java.util.Date until, int step) - until cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindStats(name, since, until, step)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Stats) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.StatsService.class).
			warn ("Error on StatsService.findStats", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on StatsService.findStats", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Stats handleFindStats(java.lang.String name, java.util.Date since, java.util.Date until, int step) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.StatsService#	 * @see com.soffid.iam.base.service.StatsService#void purge()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void purge()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handlePurge();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.StatsService.class).
			warn ("Error on StatsService.purge", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on StatsService.purge", (Throwable) __r[1]);
	}

	protected abstract void handlePurge() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.StatsService#	 * @see com.soffid.iam.base.service.StatsService#void updateStats()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void updateStats()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpdateStats();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.StatsService.class).
			warn ("Error on StatsService.updateStats", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on StatsService.updateStats", (Throwable) __r[1]);
	}

	protected abstract void handleUpdateStats() throws Exception;

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
