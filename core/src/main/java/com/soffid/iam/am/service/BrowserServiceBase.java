//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.am.service.BrowserService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.am.service.BrowserService
 */
public abstract class BrowserServiceBase
	implements com.soffid.iam.am.service.BrowserService
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

	private com.soffid.iam.am.model.BrowserEntityDao browserEntityDao;

	/**
	 * Sets reference to <code>browserEntityDao</code>.
	 */
	public void setBrowserEntityDao (com.soffid.iam.am.model.BrowserEntityDao browserEntityDao) {
		this.browserEntityDao = browserEntityDao;
	}

	/**
	 * Gets reference to <code>browserEntityDao</code>.
	 */
	public com.soffid.iam.am.model.BrowserEntityDao getBrowserEntityDao () {
		return browserEntityDao;
	}

	private com.soffid.iam.am.model.HostAttributeEntityDao hostAttributeEntityDao;

	/**
	 * Sets reference to <code>hostAttributeEntityDao</code>.
	 */
	public void setHostAttributeEntityDao (com.soffid.iam.am.model.HostAttributeEntityDao hostAttributeEntityDao) {
		this.hostAttributeEntityDao = hostAttributeEntityDao;
	}

	/**
	 * Gets reference to <code>hostAttributeEntityDao</code>.
	 */
	public com.soffid.iam.am.model.HostAttributeEntityDao getHostAttributeEntityDao () {
		return hostAttributeEntityDao;
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

	private com.soffid.iam.am.model.HostEntryPointEntityDao hostEntryPointEntityDao;

	/**
	 * Sets reference to <code>hostEntryPointEntityDao</code>.
	 */
	public void setHostEntryPointEntityDao (com.soffid.iam.am.model.HostEntryPointEntityDao hostEntryPointEntityDao) {
		this.hostEntryPointEntityDao = hostEntryPointEntityDao;
	}

	/**
	 * Gets reference to <code>hostEntryPointEntityDao</code>.
	 */
	public com.soffid.iam.am.model.HostEntryPointEntityDao getHostEntryPointEntityDao () {
		return hostEntryPointEntityDao;
	}

	private com.soffid.iam.pam.model.HostSystemEntityDao hostSystemEntityDao;

	/**
	 * Sets reference to <code>hostSystemEntityDao</code>.
	 */
	public void setHostSystemEntityDao (com.soffid.iam.pam.model.HostSystemEntityDao hostSystemEntityDao) {
		this.hostSystemEntityDao = hostSystemEntityDao;
	}

	/**
	 * Gets reference to <code>hostSystemEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.HostSystemEntityDao getHostSystemEntityDao () {
		return hostSystemEntityDao;
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


	/**
	 * @see com.soffid.iam.am.service.BrowserService#	 * @see com.soffid.iam.am.service.BrowserService#com.soffid.iam.am.api.Browser create(com.soffid.iam.am.api.Browser browser)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Browser create(
		final com.soffid.iam.am.api.Browser browser)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (browser == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Browser com.soffid.iam.am.service.BrowserService.create(com.soffid.iam.am.api.Browser browser) - browser cannot be null");
		}
		if (browser.getId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Browser com.soffid.iam.am.service.BrowserService.create(com.soffid.iam.am.api.Browser browser) - browser.id cannot be null");
		}
		if (browser.getIp() == null || browser.getIp().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Browser com.soffid.iam.am.service.BrowserService.create(com.soffid.iam.am.api.Browser browser) - browser.ip cannot be null");
		}
		if (browser.getDeleted() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Browser com.soffid.iam.am.service.BrowserService.create(com.soffid.iam.am.api.Browser browser) - browser.deleted cannot be null");
		}
		if (browser.getLocked() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Browser com.soffid.iam.am.service.BrowserService.create(com.soffid.iam.am.api.Browser browser) - browser.locked cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(browser)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Browser) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.BrowserService.class).
			warn ("Error on BrowserService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BrowserService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Browser handleCreate(com.soffid.iam.am.api.Browser browser) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.BrowserService#	 * @see com.soffid.iam.am.service.BrowserService#com.soffid.iam.am.api.Browser findByHost(com.soffid.iam.am.api.Host host)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Browser findByHost(
		final com.soffid.iam.am.api.Host host)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (host == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Browser com.soffid.iam.am.service.BrowserService.findByHost(com.soffid.iam.am.api.Host host) - host cannot be null");
		}
		if (host.getName() == null || host.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Browser com.soffid.iam.am.service.BrowserService.findByHost(com.soffid.iam.am.api.Host host) - host.name cannot be null");
		}
		if (host.getNetwork() == null || host.getNetwork().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Browser com.soffid.iam.am.service.BrowserService.findByHost(com.soffid.iam.am.api.Host host) - host.network cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindByHost(host)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Browser) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.BrowserService.class).
			warn ("Error on BrowserService.findByHost", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BrowserService.findByHost", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Browser handleFindByHost(com.soffid.iam.am.api.Host host) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.BrowserService#	 * @see com.soffid.iam.am.service.BrowserService#com.soffid.iam.am.api.Browser findBySerialNumber(java.lang.String serialNumber)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Browser findBySerialNumber(
		final java.lang.String serialNumber)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (serialNumber == null || serialNumber.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Browser com.soffid.iam.am.service.BrowserService.findBySerialNumber(java.lang.String serialNumber) - serialNumber cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindBySerialNumber(serialNumber)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Browser) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.BrowserService.class).
			warn ("Error on BrowserService.findBySerialNumber", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BrowserService.findBySerialNumber", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Browser handleFindBySerialNumber(java.lang.String serialNumber) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.BrowserService#	 * @see com.soffid.iam.am.service.BrowserService#com.soffid.iam.am.api.Browser registerBrowser(java.lang.String serialNumber, java.lang.String ipAddress, java.lang.String userAgent)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Browser registerBrowser(
		final java.lang.String serialNumber, 
		final java.lang.String ipAddress, 
		final java.lang.String userAgent)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleRegisterBrowser(serialNumber, ipAddress, userAgent)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Browser) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.BrowserService.class).
			warn ("Error on BrowserService.registerBrowser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BrowserService.registerBrowser", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Browser handleRegisterBrowser(java.lang.String serialNumber, java.lang.String ipAddress, java.lang.String userAgent) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.BrowserService#	 * @see com.soffid.iam.am.service.BrowserService#com.soffid.iam.am.api.Browser update(com.soffid.iam.am.api.Browser browser)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Browser update(
		final com.soffid.iam.am.api.Browser browser)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (browser == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Browser com.soffid.iam.am.service.BrowserService.update(com.soffid.iam.am.api.Browser browser) - browser cannot be null");
		}
		if (browser.getId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Browser com.soffid.iam.am.service.BrowserService.update(com.soffid.iam.am.api.Browser browser) - browser.id cannot be null");
		}
		if (browser.getIp() == null || browser.getIp().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Browser com.soffid.iam.am.service.BrowserService.update(com.soffid.iam.am.api.Browser browser) - browser.ip cannot be null");
		}
		if (browser.getDeleted() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Browser com.soffid.iam.am.service.BrowserService.update(com.soffid.iam.am.api.Browser browser) - browser.deleted cannot be null");
		}
		if (browser.getLocked() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Browser com.soffid.iam.am.service.BrowserService.update(com.soffid.iam.am.api.Browser browser) - browser.locked cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(browser)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Browser) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.BrowserService.class).
			warn ("Error on BrowserService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BrowserService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Browser handleUpdate(com.soffid.iam.am.api.Browser browser) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.BrowserService#	 * @see com.soffid.iam.am.service.BrowserService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Browser> findBrowsers(com.soffid.zkdb.api.Query q)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Browser> findBrowsers(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (q == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Browser> com.soffid.iam.am.service.BrowserService.findBrowsers(com.soffid.zkdb.api.Query q) - q cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindBrowsers(q)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Browser>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.BrowserService.class).
			warn ("Error on BrowserService.findBrowsers", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BrowserService.findBrowsers", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Browser> handleFindBrowsers(com.soffid.zkdb.api.Query q) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.BrowserService#	 * @see com.soffid.iam.am.service.BrowserService#java.util.List<com.soffid.iam.am.api.Session> handleFindSessionsByBrowserId(java.lang.Long browserId)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.am.api.Session> handleFindSessionsByBrowserId(
		final java.lang.Long browserId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (browserId == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.Session> com.soffid.iam.am.service.BrowserService.handleFindSessionsByBrowserId(java.lang.Long browserId) - browserId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleHandleFindSessionsByBrowserId(browserId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.am.api.Session>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.BrowserService.class).
			warn ("Error on BrowserService.handleFindSessionsByBrowserId", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BrowserService.handleFindSessionsByBrowserId", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.am.api.Session> handleHandleFindSessionsByBrowserId(java.lang.Long browserId) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.BrowserService#	 * @see com.soffid.iam.am.service.BrowserService#void delete(com.soffid.iam.am.api.Browser browser)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.am.api.Browser browser)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (browser == null) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.BrowserService.delete(com.soffid.iam.am.api.Browser browser) - browser cannot be null");
		}
		if (browser.getId() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.BrowserService.delete(com.soffid.iam.am.api.Browser browser) - browser.id cannot be null");
		}
		if (browser.getIp() == null || browser.getIp().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.BrowserService.delete(com.soffid.iam.am.api.Browser browser) - browser.ip cannot be null");
		}
		if (browser.getDeleted() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.BrowserService.delete(com.soffid.iam.am.api.Browser browser) - browser.deleted cannot be null");
		}
		if (browser.getLocked() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.BrowserService.delete(com.soffid.iam.am.api.Browser browser) - browser.locked cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(browser);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.BrowserService.class).
			warn ("Error on BrowserService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BrowserService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.am.api.Browser browser) throws Exception;

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
