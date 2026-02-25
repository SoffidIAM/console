//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.iga.service.AuthoritativeChangeService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.iga.service.AuthoritativeChangeService
 */
public abstract class AuthoritativeChangeServiceBase
	implements com.soffid.iam.iga.service.AuthoritativeChangeService
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

	private com.soffid.iam.iga.model.AuthoritativeChangeEntityDao authoritativeChangeEntityDao;

	/**
	 * Sets reference to <code>authoritativeChangeEntityDao</code>.
	 */
	public void setAuthoritativeChangeEntityDao (com.soffid.iam.iga.model.AuthoritativeChangeEntityDao authoritativeChangeEntityDao) {
		this.authoritativeChangeEntityDao = authoritativeChangeEntityDao;
	}

	/**
	 * Gets reference to <code>authoritativeChangeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.AuthoritativeChangeEntityDao getAuthoritativeChangeEntityDao () {
		return authoritativeChangeEntityDao;
	}

	private com.soffid.iam.bpm.service.BpmConfigService bpmConfigService;

	/**
	 * Sets reference to <code>bpmConfigService</code>.
	 */
	public void setBpmConfigService (com.soffid.iam.bpm.service.BpmConfigService bpmConfigService) {
		this.bpmConfigService = bpmConfigService;
	}

	/**
	 * Gets reference to <code>bpmConfigService</code>.
	 */
	public com.soffid.iam.bpm.service.BpmConfigService getBpmConfigService () {
		return bpmConfigService;
	}

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

	private com.soffid.iam.iga.service.CustomObjectService customObjectService;

	/**
	 * Sets reference to <code>customObjectService</code>.
	 */
	public void setCustomObjectService (com.soffid.iam.iga.service.CustomObjectService customObjectService) {
		this.customObjectService = customObjectService;
	}

	/**
	 * Gets reference to <code>customObjectService</code>.
	 */
	public com.soffid.iam.iga.service.CustomObjectService getCustomObjectService () {
		return customObjectService;
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

	private com.soffid.iam.iga.service.GroupService groupService;

	/**
	 * Sets reference to <code>groupService</code>.
	 */
	public void setGroupService (com.soffid.iam.iga.service.GroupService groupService) {
		this.groupService = groupService;
	}

	/**
	 * Gets reference to <code>groupService</code>.
	 */
	public com.soffid.iam.iga.service.GroupService getGroupService () {
		return groupService;
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
	 * @see com.soffid.iam.iga.service.AuthoritativeChangeService#	 * @see com.soffid.iam.iga.service.AuthoritativeChangeService#boolean startAuthoritativeChange(com.soffid.iam.sync.api.AuthoritativeChange change)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean startAuthoritativeChange(
		final com.soffid.iam.sync.api.AuthoritativeChange change)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (change == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.iga.service.AuthoritativeChangeService.startAuthoritativeChange(com.soffid.iam.sync.api.AuthoritativeChange change) - change cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleStartAuthoritativeChange(change)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.AuthoritativeChangeService.class).
			warn ("Error on AuthoritativeChangeService.startAuthoritativeChange", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthoritativeChangeService.startAuthoritativeChange", (Throwable) __r[1]);
	}

	protected abstract boolean handleStartAuthoritativeChange(com.soffid.iam.sync.api.AuthoritativeChange change) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.AuthoritativeChangeService#	 * @see com.soffid.iam.iga.service.AuthoritativeChangeService#void cancelAuthoritativeChange(com.soffid.iam.sync.api.AuthoritativeChange change)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void cancelAuthoritativeChange(
		final com.soffid.iam.sync.api.AuthoritativeChange change)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (change == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.AuthoritativeChangeService.cancelAuthoritativeChange(com.soffid.iam.sync.api.AuthoritativeChange change) - change cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleCancelAuthoritativeChange(change);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.AuthoritativeChangeService.class).
			warn ("Error on AuthoritativeChangeService.cancelAuthoritativeChange", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthoritativeChangeService.cancelAuthoritativeChange", (Throwable) __r[1]);
	}

	protected abstract void handleCancelAuthoritativeChange(com.soffid.iam.sync.api.AuthoritativeChange change) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.AuthoritativeChangeService#	 * @see com.soffid.iam.iga.service.AuthoritativeChangeService#void finishAuthoritativeChange(com.soffid.iam.sync.api.AuthoritativeChange change)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void finishAuthoritativeChange(
		final com.soffid.iam.sync.api.AuthoritativeChange change)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (change == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.AuthoritativeChangeService.finishAuthoritativeChange(com.soffid.iam.sync.api.AuthoritativeChange change) - change cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleFinishAuthoritativeChange(change);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.AuthoritativeChangeService.class).
			warn ("Error on AuthoritativeChangeService.finishAuthoritativeChange", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthoritativeChangeService.finishAuthoritativeChange", (Throwable) __r[1]);
	}

	protected abstract void handleFinishAuthoritativeChange(com.soffid.iam.sync.api.AuthoritativeChange change) throws Exception;

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
