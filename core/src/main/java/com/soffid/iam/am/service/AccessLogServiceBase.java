//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.am.service.AccessLogService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.am.service.AccessLogService
 */
public abstract class AccessLogServiceBase
	implements com.soffid.iam.am.service.AccessLogService
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
	 * @see com.soffid.iam.am.service.AccessLogService#	 * @see com.soffid.iam.am.service.AccessLogService#com.soffid.iam.am.api.AccessLog create(com.soffid.iam.am.api.AccessLog registre)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.AccessLog create(
		final com.soffid.iam.am.api.AccessLog registre)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (registre == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.AccessLog com.soffid.iam.am.service.AccessLogService.create(com.soffid.iam.am.api.AccessLog registre) - registre cannot be null");
		}
		if (registre.getId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.AccessLog com.soffid.iam.am.service.AccessLogService.create(com.soffid.iam.am.api.AccessLog registre) - registre.id cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(registre)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.AccessLog) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.AccessLogService.class).
			warn ("Error on AccessLogService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccessLogService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.AccessLog handleCreate(com.soffid.iam.am.api.AccessLog registre) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.AccessLogService#	 * @see com.soffid.iam.am.service.AccessLogService#com.soffid.iam.am.api.AccessLog findAccessLogById(java.lang.Long id)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.AccessLog findAccessLogById(
		final java.lang.Long id)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (id == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.AccessLog com.soffid.iam.am.service.AccessLogService.findAccessLogById(java.lang.Long id) - id cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAccessLogById(id)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.AccessLog) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.AccessLogService.class).
			warn ("Error on AccessLogService.findAccessLogById", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccessLogService.findAccessLogById", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.AccessLog handleFindAccessLogById(java.lang.Long id) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.AccessLogService#	 * @see com.soffid.iam.am.service.AccessLogService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.AccessLog> findAccessLogs(com.soffid.zkdb.api.Query q)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.AccessLog> findAccessLogs(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (q == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.AccessLog> com.soffid.iam.am.service.AccessLogService.findAccessLogs(com.soffid.zkdb.api.Query q) - q cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAccessLogs(q)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.AccessLog>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.AccessLogService.class).
			warn ("Error on AccessLogService.findAccessLogs", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccessLogService.findAccessLogs", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.AccessLog> handleFindAccessLogs(com.soffid.zkdb.api.Query q) throws Exception;

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
