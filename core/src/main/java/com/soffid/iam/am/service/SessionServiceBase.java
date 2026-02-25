//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.am.service.SessionService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.am.service.SessionService
 */
public abstract class SessionServiceBase
	implements com.soffid.iam.am.service.SessionService
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
	 * @see com.soffid.iam.am.service.SessionService#	 * @see com.soffid.iam.am.service.SessionService#com.soffid.iam.am.api.Session getSessionByHost(long id, java.lang.String hostIp)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Session getSessionByHost(
		final long id, 
		final java.lang.String hostIp)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (hostIp == null || hostIp.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Session com.soffid.iam.am.service.SessionService.getSessionByHost(long id, java.lang.String hostIp) - hostIp cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetSessionByHost(id, hostIp)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Session) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.SessionService.class).
			warn ("Error on SessionService.getSessionByHost", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SessionService.getSessionByHost", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Session handleGetSessionByHost(long id, java.lang.String hostIp) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.SessionService#	 * @see com.soffid.iam.am.service.SessionService#com.soffid.iam.am.api.Session getSession(long id, java.lang.String key)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Session getSession(
		final long id, 
		final java.lang.String key)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (key == null || key.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Session com.soffid.iam.am.service.SessionService.getSession(long id, java.lang.String key) - key cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetSession(id, key)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Session) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.SessionService.class).
			warn ("Error on SessionService.getSession", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SessionService.getSession", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Session handleGetSession(long id, java.lang.String key) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.SessionService#	 * @see com.soffid.iam.am.service.SessionService#com.soffid.iam.am.api.Session joinEssoSession(long id, java.lang.String key, int port)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Session joinEssoSession(
		final long id, 
		final java.lang.String key, 
		final int port)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (key == null || key.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Session com.soffid.iam.am.service.SessionService.joinEssoSession(long id, java.lang.String key, int port) - key cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleJoinEssoSession(id, key, port)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Session) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.SessionService.class).
			warn ("Error on SessionService.joinEssoSession", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SessionService.joinEssoSession", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Session handleJoinEssoSession(long id, java.lang.String key, int port) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.SessionService#	 * @see com.soffid.iam.am.service.SessionService#com.soffid.iam.am.api.Session registerBrowserSession(java.lang.String userName, java.lang.String browserSerialNumber, java.lang.String ipAddress, com.soffid.iam.am.api.SessionType sessionType, java.lang.String checkUrl, java.lang.String pamMonitorUrl, java.lang.String serviceProvider, java.lang.String authenticationMethod)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Session registerBrowserSession(
		final java.lang.String userName, 
		final java.lang.String browserSerialNumber, 
		final java.lang.String ipAddress, 
		final com.soffid.iam.am.api.SessionType sessionType, 
		final java.lang.String checkUrl, 
		final java.lang.String pamMonitorUrl, 
		final java.lang.String serviceProvider, 
		final java.lang.String authenticationMethod)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Session com.soffid.iam.am.service.SessionService.registerBrowserSession(java.lang.String userName, java.lang.String browserSerialNumber, java.lang.String ipAddress, com.soffid.iam.am.api.SessionType sessionType, java.lang.String checkUrl, java.lang.String pamMonitorUrl, java.lang.String serviceProvider, java.lang.String authenticationMethod) - userName cannot be null");
		}
		if (ipAddress == null || ipAddress.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Session com.soffid.iam.am.service.SessionService.registerBrowserSession(java.lang.String userName, java.lang.String browserSerialNumber, java.lang.String ipAddress, com.soffid.iam.am.api.SessionType sessionType, java.lang.String checkUrl, java.lang.String pamMonitorUrl, java.lang.String serviceProvider, java.lang.String authenticationMethod) - ipAddress cannot be null");
		}
		if (sessionType == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Session com.soffid.iam.am.service.SessionService.registerBrowserSession(java.lang.String userName, java.lang.String browserSerialNumber, java.lang.String ipAddress, com.soffid.iam.am.api.SessionType sessionType, java.lang.String checkUrl, java.lang.String pamMonitorUrl, java.lang.String serviceProvider, java.lang.String authenticationMethod) - sessionType cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleRegisterBrowserSession(userName, browserSerialNumber, ipAddress, sessionType, checkUrl, pamMonitorUrl, serviceProvider, authenticationMethod)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Session) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.UnknownUserException) 
			throw (com.soffid.iam.exception.UnknownUserException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.SessionService.class).
			warn ("Error on SessionService.registerBrowserSession", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SessionService.registerBrowserSession", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Session handleRegisterBrowserSession(java.lang.String userName, java.lang.String browserSerialNumber, java.lang.String ipAddress, com.soffid.iam.am.api.SessionType sessionType, java.lang.String checkUrl, java.lang.String pamMonitorUrl, java.lang.String serviceProvider, java.lang.String authenticationMethod) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.SessionService#	 * @see com.soffid.iam.am.service.SessionService#com.soffid.iam.am.api.Session registerSession(java.lang.String codiUsuari, java.lang.String hostKey, java.lang.String clientHost, int port, java.lang.String key, java.lang.String authenticationMethod)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Session registerSession(
		final java.lang.String codiUsuari, 
		final java.lang.String hostKey, 
		final java.lang.String clientHost, 
		final int port, 
		final java.lang.String key, 
		final java.lang.String authenticationMethod)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Session com.soffid.iam.am.service.SessionService.registerSession(java.lang.String codiUsuari, java.lang.String hostKey, java.lang.String clientHost, int port, java.lang.String key, java.lang.String authenticationMethod) - codiUsuari cannot be null");
		}
		if (hostKey == null || hostKey.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Session com.soffid.iam.am.service.SessionService.registerSession(java.lang.String codiUsuari, java.lang.String hostKey, java.lang.String clientHost, int port, java.lang.String key, java.lang.String authenticationMethod) - hostKey cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleRegisterSession(codiUsuari, hostKey, clientHost, port, key, authenticationMethod)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Session) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.SessionService.class).
			warn ("Error on SessionService.registerSession", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SessionService.registerSession", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Session handleRegisterSession(java.lang.String codiUsuari, java.lang.String hostKey, java.lang.String clientHost, int port, java.lang.String key, java.lang.String authenticationMethod) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.SessionService#	 * @see com.soffid.iam.am.service.SessionService#java.lang.String updateTransientKey(long id, java.lang.String key)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String updateTransientKey(
		final long id, 
		final java.lang.String key)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (key == null || key.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.am.service.SessionService.updateTransientKey(long id, java.lang.String key) - key cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdateTransientKey(id, key)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.SessionService.class).
			warn ("Error on SessionService.updateTransientKey", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SessionService.updateTransientKey", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleUpdateTransientKey(long id, java.lang.String key) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.SessionService#	 * @see com.soffid.iam.am.service.SessionService#java.util.Collection<com.soffid.iam.am.api.Session> findActiveSessions()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.api.Session> findActiveSessions()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindActiveSessions()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.api.Session>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.SessionService.class).
			warn ("Error on SessionService.findActiveSessions", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SessionService.findActiveSessions", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.api.Session> handleFindActiveSessions() throws Exception;

	/**
	 * @see com.soffid.iam.am.service.SessionService#	 * @see com.soffid.iam.am.service.SessionService#java.util.Collection<com.soffid.iam.am.api.Session> getActiveSessions()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.api.Session> getActiveSessions()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetActiveSessions()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.api.Session>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.SessionService.class).
			warn ("Error on SessionService.getActiveSessions", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SessionService.getActiveSessions", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.api.Session> handleGetActiveSessions() throws Exception;

	/**
	 * @see com.soffid.iam.am.service.SessionService#	 * @see com.soffid.iam.am.service.SessionService#java.util.Collection<com.soffid.iam.am.api.Session> getActiveSessions(long idUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.api.Session> getActiveSessions(
		final long idUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetActiveSessions(idUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.api.Session>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.SessionService.class).
			warn ("Error on SessionService.getActiveSessions", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SessionService.getActiveSessions", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.api.Session> handleGetActiveSessions(long idUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.SessionService#	 * @see com.soffid.iam.am.service.SessionService#void cleanTransientKey(long id, java.lang.String key)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void cleanTransientKey(
		final long id, 
		final java.lang.String key)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (key == null || key.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.SessionService.cleanTransientKey(long id, java.lang.String key) - key cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleCleanTransientKey(id, key);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.SessionService.class).
			warn ("Error on SessionService.cleanTransientKey", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SessionService.cleanTransientKey", (Throwable) __r[1]);
	}

	protected abstract void handleCleanTransientKey(long id, java.lang.String key) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.SessionService#	 * @see com.soffid.iam.am.service.SessionService#void destroySession(com.soffid.iam.am.api.Session sessio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void destroySession(
		final com.soffid.iam.am.api.Session sessio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (sessio == null) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.SessionService.destroySession(com.soffid.iam.am.api.Session sessio) - sessio cannot be null");
		}
		if (sessio.getUserName() == null || sessio.getUserName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.SessionService.destroySession(com.soffid.iam.am.api.Session sessio) - sessio.userName cannot be null");
		}
		if (sessio.getStartDate() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.SessionService.destroySession(com.soffid.iam.am.api.Session sessio) - sessio.startDate cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDestroySession(sessio);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.SessionService.class).
			warn ("Error on SessionService.destroySession", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SessionService.destroySession", (Throwable) __r[1]);
	}

	protected abstract void handleDestroySession(com.soffid.iam.am.api.Session sessio) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.SessionService#	 * @see com.soffid.iam.am.service.SessionService#void sessionKeepAlive(com.soffid.iam.am.api.Session session)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void sessionKeepAlive(
		final com.soffid.iam.am.api.Session session)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (session == null) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.SessionService.sessionKeepAlive(com.soffid.iam.am.api.Session session) - session cannot be null");
		}
		if (session.getUserName() == null || session.getUserName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.SessionService.sessionKeepAlive(com.soffid.iam.am.api.Session session) - session.userName cannot be null");
		}
		if (session.getStartDate() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.SessionService.sessionKeepAlive(com.soffid.iam.am.api.Session session) - session.startDate cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSessionKeepAlive(session);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.SessionService.class).
			warn ("Error on SessionService.sessionKeepAlive", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SessionService.sessionKeepAlive", (Throwable) __r[1]);
	}

	protected abstract void handleSessionKeepAlive(com.soffid.iam.am.api.Session session) throws Exception;

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
