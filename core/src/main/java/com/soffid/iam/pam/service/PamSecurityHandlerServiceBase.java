//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.pam.service.PamSecurityHandlerService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.pam.service.PamSecurityHandlerService
 */
public abstract class PamSecurityHandlerServiceBase
	implements com.soffid.iam.pam.service.PamSecurityHandlerService
 {
	private com.soffid.iam.bpm.service.BpmEngine bpmEngine;

	/**
	 * Sets reference to <code>bpmEngine</code>.
	 */
	public void setBpmEngine (com.soffid.iam.bpm.service.BpmEngine bpmEngine) {
		this.bpmEngine = bpmEngine;
	}

	/**
	 * Gets reference to <code>bpmEngine</code>.
	 */
	public com.soffid.iam.bpm.service.BpmEngine getBpmEngine () {
		return bpmEngine;
	}

	private com.soffid.iam.base.model.UserAccountEntityDao userAccountEntityDao;

	/**
	 * Sets reference to <code>userAccountEntityDao</code>.
	 */
	public void setUserAccountEntityDao (com.soffid.iam.base.model.UserAccountEntityDao userAccountEntityDao) {
		this.userAccountEntityDao = userAccountEntityDao;
	}

	/**
	 * Gets reference to <code>userAccountEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserAccountEntityDao getUserAccountEntityDao () {
		return userAccountEntityDao;
	}


	/**
	 * @see com.soffid.iam.pam.service.PamSecurityHandlerService#	 * @see com.soffid.iam.pam.service.PamSecurityHandlerService#com.soffid.iam.pam.api.PamSecurityCheck checkPermissionImpl(com.soffid.iam.base.model.AccountEntity account, java.lang.String entryPoint, java.lang.String jumpServerGroup, java.lang.String url, java.lang.String action)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.pam.api.PamSecurityCheck checkPermissionImpl(
		final com.soffid.iam.base.model.AccountEntity account, 
		final java.lang.String entryPoint, 
		final java.lang.String jumpServerGroup, 
		final java.lang.String url, 
		final java.lang.String action)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCheckPermissionImpl(account, entryPoint, jumpServerGroup, url, action)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.pam.api.PamSecurityCheck) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamSecurityHandlerService.class).
			warn ("Error on PamSecurityHandlerService.checkPermissionImpl", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamSecurityHandlerService.checkPermissionImpl", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.pam.api.PamSecurityCheck handleCheckPermissionImpl(com.soffid.iam.base.model.AccountEntity account, java.lang.String entryPoint, java.lang.String jumpServerGroup, java.lang.String url, java.lang.String action) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamSecurityHandlerService#	 * @see com.soffid.iam.pam.service.PamSecurityHandlerService#com.soffid.iam.pam.api.PamSecurityCheck getObligations(com.soffid.iam.base.model.AccountEntity account, java.lang.String entryPoint, java.lang.String jumpServerGroup, java.lang.String url, java.lang.String action)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.pam.api.PamSecurityCheck getObligations(
		final com.soffid.iam.base.model.AccountEntity account, 
		final java.lang.String entryPoint, 
		final java.lang.String jumpServerGroup, 
		final java.lang.String url, 
		final java.lang.String action)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetObligations(account, entryPoint, jumpServerGroup, url, action)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.pam.api.PamSecurityCheck) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamSecurityHandlerService.class).
			warn ("Error on PamSecurityHandlerService.getObligations", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamSecurityHandlerService.getObligations", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.pam.api.PamSecurityCheck handleGetObligations(com.soffid.iam.base.model.AccountEntity account, java.lang.String entryPoint, java.lang.String jumpServerGroup, java.lang.String url, java.lang.String action) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamSecurityHandlerService#	 * @see com.soffid.iam.pam.service.PamSecurityHandlerService#void checkPermission(com.soffid.iam.base.model.AccountEntity account, java.lang.String entryPoint, java.lang.String jumpServerGroup, java.lang.String url, java.lang.String action)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void checkPermission(
		final com.soffid.iam.base.model.AccountEntity account, 
		final java.lang.String entryPoint, 
		final java.lang.String jumpServerGroup, 
		final java.lang.String url, 
		final java.lang.String action)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleCheckPermission(account, entryPoint, jumpServerGroup, url, action);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamSecurityHandlerService.class).
			warn ("Error on PamSecurityHandlerService.checkPermission", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamSecurityHandlerService.checkPermission", (Throwable) __r[1]);
	}

	protected abstract void handleCheckPermission(com.soffid.iam.base.model.AccountEntity account, java.lang.String entryPoint, java.lang.String jumpServerGroup, java.lang.String url, java.lang.String action) throws Exception;

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
