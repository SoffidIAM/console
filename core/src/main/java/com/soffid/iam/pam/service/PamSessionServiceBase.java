//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.pam.service.PamSessionService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.pam.service.PamSessionService
 */
public abstract class PamSessionServiceBase
	implements com.soffid.iam.pam.service.PamSessionService
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

	private com.soffid.iam.am.service.NetworkService networkService;

	/**
	 * Sets reference to <code>networkService</code>.
	 */
	public void setNetworkService (com.soffid.iam.am.service.NetworkService networkService) {
		this.networkService = networkService;
	}

	/**
	 * Gets reference to <code>networkService</code>.
	 */
	public com.soffid.iam.am.service.NetworkService getNetworkService () {
		return networkService;
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
	 * @see com.soffid.iam.pam.service.PamSessionService#	 * @see com.soffid.iam.pam.service.PamSessionService#boolean checkJumpServerSession(com.soffid.iam.am.api.Session sessio)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean checkJumpServerSession(
		final com.soffid.iam.am.api.Session sessio)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (sessio == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.pam.service.PamSessionService.checkJumpServerSession(com.soffid.iam.am.api.Session sessio) - sessio cannot be null");
		}
		if (sessio.getUserName() == null || sessio.getUserName().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.pam.service.PamSessionService.checkJumpServerSession(com.soffid.iam.am.api.Session sessio) - sessio.userName cannot be null");
		}
		if (sessio.getStartDate() == null ) {
			throw new IllegalArgumentException("boolean com.soffid.iam.pam.service.PamSessionService.checkJumpServerSession(com.soffid.iam.am.api.Session sessio) - sessio.startDate cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCheckJumpServerSession(sessio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamSessionService.class).
			warn ("Error on PamSessionService.checkJumpServerSession", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamSessionService.checkJumpServerSession", (Throwable) __r[1]);
	}

	protected abstract boolean handleCheckJumpServerSession(com.soffid.iam.am.api.Session sessio) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamSessionService#	 * @see com.soffid.iam.pam.service.PamSessionService#com.soffid.iam.pam.api.JumpServerGroup create(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.pam.api.JumpServerGroup create(
		final com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (jumpServerGroup == null) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.JumpServerGroup com.soffid.iam.pam.service.PamSessionService.create(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup) - jumpServerGroup cannot be null");
		}
		if (jumpServerGroup.getName() == null || jumpServerGroup.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.JumpServerGroup com.soffid.iam.pam.service.PamSessionService.create(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup) - jumpServerGroup.name cannot be null");
		}
		if (jumpServerGroup.getStoreUrl() == null || jumpServerGroup.getStoreUrl().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.JumpServerGroup com.soffid.iam.pam.service.PamSessionService.create(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup) - jumpServerGroup.storeUrl cannot be null");
		}
		if (jumpServerGroup.getStoreUserName() == null || jumpServerGroup.getStoreUserName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.JumpServerGroup com.soffid.iam.pam.service.PamSessionService.create(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup) - jumpServerGroup.storeUserName cannot be null");
		}
		if (jumpServerGroup.getPassword() == null || jumpServerGroup.getPassword().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.JumpServerGroup com.soffid.iam.pam.service.PamSessionService.create(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup) - jumpServerGroup.password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(jumpServerGroup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.pam.api.JumpServerGroup) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamSessionService.class).
			warn ("Error on PamSessionService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamSessionService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.pam.api.JumpServerGroup handleCreate(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamSessionService#	 * @see com.soffid.iam.pam.service.PamSessionService#com.soffid.iam.pam.api.JumpServerGroup update(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.pam.api.JumpServerGroup update(
		final com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (jumpServerGroup == null) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.JumpServerGroup com.soffid.iam.pam.service.PamSessionService.update(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup) - jumpServerGroup cannot be null");
		}
		if (jumpServerGroup.getName() == null || jumpServerGroup.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.JumpServerGroup com.soffid.iam.pam.service.PamSessionService.update(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup) - jumpServerGroup.name cannot be null");
		}
		if (jumpServerGroup.getStoreUrl() == null || jumpServerGroup.getStoreUrl().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.JumpServerGroup com.soffid.iam.pam.service.PamSessionService.update(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup) - jumpServerGroup.storeUrl cannot be null");
		}
		if (jumpServerGroup.getStoreUserName() == null || jumpServerGroup.getStoreUserName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.JumpServerGroup com.soffid.iam.pam.service.PamSessionService.update(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup) - jumpServerGroup.storeUserName cannot be null");
		}
		if (jumpServerGroup.getPassword() == null || jumpServerGroup.getPassword().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.JumpServerGroup com.soffid.iam.pam.service.PamSessionService.update(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup) - jumpServerGroup.password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(jumpServerGroup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.pam.api.JumpServerGroup) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamSessionService.class).
			warn ("Error on PamSessionService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamSessionService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.pam.api.JumpServerGroup handleUpdate(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamSessionService#	 * @see com.soffid.iam.pam.service.PamSessionService#com.soffid.iam.pam.api.NewPamSession createCustomJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String sourceIp, com.soffid.iam.am.api.SessionType type, java.lang.String info)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.pam.api.NewPamSession createCustomJumpServerSession(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.String sourceIp, 
		final com.soffid.iam.am.api.SessionType type, 
		final java.lang.String info)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createCustomJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String sourceIp, com.soffid.iam.am.api.SessionType type, java.lang.String info) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createCustomJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String sourceIp, com.soffid.iam.am.api.SessionType type, java.lang.String info) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createCustomJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String sourceIp, com.soffid.iam.am.api.SessionType type, java.lang.String info) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createCustomJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String sourceIp, com.soffid.iam.am.api.SessionType type, java.lang.String info) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createCustomJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String sourceIp, com.soffid.iam.am.api.SessionType type, java.lang.String info) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createCustomJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String sourceIp, com.soffid.iam.am.api.SessionType type, java.lang.String info) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreateCustomJumpServerSession(account, sourceIp, type, info)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.pam.api.NewPamSession) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamSessionService.class).
			warn ("Error on PamSessionService.createCustomJumpServerSession", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamSessionService.createCustomJumpServerSession", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.pam.api.NewPamSession handleCreateCustomJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String sourceIp, com.soffid.iam.am.api.SessionType type, java.lang.String info) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamSessionService#	 * @see com.soffid.iam.pam.service.PamSessionService#com.soffid.iam.pam.api.NewPamSession createJumpServerSession(com.soffid.iam.base.api.Account account)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.pam.api.NewPamSession createJumpServerSession(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createJumpServerSession(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createJumpServerSession(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createJumpServerSession(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createJumpServerSession(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createJumpServerSession(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createJumpServerSession(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreateJumpServerSession(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.pam.api.NewPamSession) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamSessionService.class).
			warn ("Error on PamSessionService.createJumpServerSession", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamSessionService.createJumpServerSession", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.pam.api.NewPamSession handleCreateJumpServerSession(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamSessionService#	 * @see com.soffid.iam.pam.service.PamSessionService#com.soffid.iam.pam.api.NewPamSession createJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String entryPointPath, java.lang.String entryPointDescriptor)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.pam.api.NewPamSession createJumpServerSession(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.String entryPointPath, 
		final java.lang.String entryPointDescriptor)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String entryPointPath, java.lang.String entryPointDescriptor) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String entryPointPath, java.lang.String entryPointDescriptor) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String entryPointPath, java.lang.String entryPointDescriptor) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String entryPointPath, java.lang.String entryPointDescriptor) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String entryPointPath, java.lang.String entryPointDescriptor) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String entryPointPath, java.lang.String entryPointDescriptor) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreateJumpServerSession(account, entryPointPath, entryPointDescriptor)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.pam.api.NewPamSession) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamSessionService.class).
			warn ("Error on PamSessionService.createJumpServerSession", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamSessionService.createJumpServerSession", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.pam.api.NewPamSession handleCreateJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String entryPointPath, java.lang.String entryPointDescriptor) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamSessionService#	 * @see com.soffid.iam.pam.service.PamSessionService#com.soffid.iam.pam.api.NewPamSession createJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String entryPointPath, java.lang.String entryPointDescriptor, java.lang.String pamPolicy)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.pam.api.NewPamSession createJumpServerSession(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.String entryPointPath, 
		final java.lang.String entryPointDescriptor, 
		final java.lang.String pamPolicy)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String entryPointPath, java.lang.String entryPointDescriptor, java.lang.String pamPolicy) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String entryPointPath, java.lang.String entryPointDescriptor, java.lang.String pamPolicy) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String entryPointPath, java.lang.String entryPointDescriptor, java.lang.String pamPolicy) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String entryPointPath, java.lang.String entryPointDescriptor, java.lang.String pamPolicy) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String entryPointPath, java.lang.String entryPointDescriptor, java.lang.String pamPolicy) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String entryPointPath, java.lang.String entryPointDescriptor, java.lang.String pamPolicy) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreateJumpServerSession(account, entryPointPath, entryPointDescriptor, pamPolicy)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.pam.api.NewPamSession) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamSessionService.class).
			warn ("Error on PamSessionService.createJumpServerSession", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamSessionService.createJumpServerSession", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.pam.api.NewPamSession handleCreateJumpServerSession(com.soffid.iam.base.api.Account account, java.lang.String entryPointPath, java.lang.String entryPointDescriptor, java.lang.String pamPolicy) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamSessionService#	 * @see com.soffid.iam.pam.service.PamSessionService#com.soffid.iam.pam.api.NewPamSession createManualJumpServerSession(java.lang.String accountName, com.soffid.iam.am.api.Password accountPassword, java.lang.String entryPointPath, java.lang.String entryPointDescriptor, java.lang.String pamPolicy)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.pam.api.NewPamSession createManualJumpServerSession(
		final java.lang.String accountName, 
		final com.soffid.iam.am.api.Password accountPassword, 
		final java.lang.String entryPointPath, 
		final java.lang.String entryPointDescriptor, 
		final java.lang.String pamPolicy)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (accountName == null || accountName.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createManualJumpServerSession(java.lang.String accountName, com.soffid.iam.am.api.Password accountPassword, java.lang.String entryPointPath, java.lang.String entryPointDescriptor, java.lang.String pamPolicy) - accountName cannot be null");
		}
		if (accountPassword == null) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.NewPamSession com.soffid.iam.pam.service.PamSessionService.createManualJumpServerSession(java.lang.String accountName, com.soffid.iam.am.api.Password accountPassword, java.lang.String entryPointPath, java.lang.String entryPointDescriptor, java.lang.String pamPolicy) - accountPassword cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreateManualJumpServerSession(accountName, accountPassword, entryPointPath, entryPointDescriptor, pamPolicy)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.pam.api.NewPamSession) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamSessionService.class).
			warn ("Error on PamSessionService.createManualJumpServerSession", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamSessionService.createManualJumpServerSession", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.pam.api.NewPamSession handleCreateManualJumpServerSession(java.lang.String accountName, com.soffid.iam.am.api.Password accountPassword, java.lang.String entryPointPath, java.lang.String entryPointDescriptor, java.lang.String pamPolicy) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamSessionService#	 * @see com.soffid.iam.pam.service.PamSessionService#com.soffid.iam.pam.api.PamSession findSession(java.lang.String serverGroup, java.lang.String sessionId)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.pam.api.PamSession findSession(
		final java.lang.String serverGroup, 
		final java.lang.String sessionId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (serverGroup == null || serverGroup.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.PamSession com.soffid.iam.pam.service.PamSessionService.findSession(java.lang.String serverGroup, java.lang.String sessionId) - serverGroup cannot be null");
		}
		if (sessionId == null || sessionId.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.PamSession com.soffid.iam.pam.service.PamSessionService.findSession(java.lang.String serverGroup, java.lang.String sessionId) - sessionId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindSession(serverGroup, sessionId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.pam.api.PamSession) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamSessionService.class).
			warn ("Error on PamSessionService.findSession", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamSessionService.findSession", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.pam.api.PamSession handleFindSession(java.lang.String serverGroup, java.lang.String sessionId) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamSessionService#	 * @see com.soffid.iam.pam.service.PamSessionService#java.lang.Integer getActiveSessions(java.lang.String server)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.Integer getActiveSessions(
		final java.lang.String server)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (server == null || server.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.Integer com.soffid.iam.pam.service.PamSessionService.getActiveSessions(java.lang.String server) - server cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetActiveSessions(server)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.Integer) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamSessionService.class).
			warn ("Error on PamSessionService.getActiveSessions", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamSessionService.getActiveSessions", (Throwable) __r[1]);
	}

	protected abstract java.lang.Integer handleGetActiveSessions(java.lang.String server) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamSessionService#	 * @see com.soffid.iam.pam.service.PamSessionService#java.lang.Long getConsoleFreeSpace(java.lang.String jumpServerGroup)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.Long getConsoleFreeSpace(
		final java.lang.String jumpServerGroup)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (jumpServerGroup == null || jumpServerGroup.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.Long com.soffid.iam.pam.service.PamSessionService.getConsoleFreeSpace(java.lang.String jumpServerGroup) - jumpServerGroup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetConsoleFreeSpace(jumpServerGroup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.Long) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamSessionService.class).
			warn ("Error on PamSessionService.getConsoleFreeSpace", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamSessionService.getConsoleFreeSpace", (Throwable) __r[1]);
	}

	protected abstract java.lang.Long handleGetConsoleFreeSpace(java.lang.String jumpServerGroup) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamSessionService#	 * @see com.soffid.iam.pam.service.PamSessionService#java.lang.Long getConsoleUsedSpace(java.lang.String jumpServerGroup)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.Long getConsoleUsedSpace(
		final java.lang.String jumpServerGroup)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (jumpServerGroup == null || jumpServerGroup.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.Long com.soffid.iam.pam.service.PamSessionService.getConsoleUsedSpace(java.lang.String jumpServerGroup) - jumpServerGroup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetConsoleUsedSpace(jumpServerGroup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.Long) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamSessionService.class).
			warn ("Error on PamSessionService.getConsoleUsedSpace", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamSessionService.getConsoleUsedSpace", (Throwable) __r[1]);
	}

	protected abstract java.lang.Long handleGetConsoleUsedSpace(java.lang.String jumpServerGroup) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamSessionService#	 * @see com.soffid.iam.pam.service.PamSessionService#java.util.List<com.soffid.iam.pam.api.JumpServerGroup> findJumpServerGroups()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.pam.api.JumpServerGroup> findJumpServerGroups()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindJumpServerGroups()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.pam.api.JumpServerGroup>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamSessionService.class).
			warn ("Error on PamSessionService.findJumpServerGroups", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamSessionService.findJumpServerGroups", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.pam.api.JumpServerGroup> handleFindJumpServerGroups() throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamSessionService#	 * @see com.soffid.iam.pam.service.PamSessionService#java.util.List<com.soffid.iam.pam.api.PamSession> search(java.lang.String jumpServerGroup, java.lang.String url, java.lang.String text, java.lang.String screenshots, java.lang.String user, java.util.Date since, java.util.Date until)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.pam.api.PamSession> search(
		final java.lang.String jumpServerGroup, 
		final java.lang.String url, 
		final java.lang.String text, 
		final java.lang.String screenshots, 
		final java.lang.String user, 
		final java.util.Date since, 
		final java.util.Date until)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleSearch(jumpServerGroup, url, text, screenshots, user, since, until)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.pam.api.PamSession>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamSessionService.class).
			warn ("Error on PamSessionService.search", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamSessionService.search", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.pam.api.PamSession> handleSearch(java.lang.String jumpServerGroup, java.lang.String url, java.lang.String text, java.lang.String screenshots, java.lang.String user, java.util.Date since, java.util.Date until) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamSessionService#	 * @see com.soffid.iam.pam.service.PamSessionService#java.util.List<com.soffid.iam.pam.api.PamSession> search(java.lang.String jumpServerGroup, java.lang.String url, java.lang.String text, java.lang.String user, java.util.Date since, java.util.Date until)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.pam.api.PamSession> search(
		final java.lang.String jumpServerGroup, 
		final java.lang.String url, 
		final java.lang.String text, 
		final java.lang.String user, 
		final java.util.Date since, 
		final java.util.Date until)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleSearch(jumpServerGroup, url, text, user, since, until)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.pam.api.PamSession>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamSessionService.class).
			warn ("Error on PamSessionService.search", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamSessionService.search", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.pam.api.PamSession> handleSearch(java.lang.String jumpServerGroup, java.lang.String url, java.lang.String text, java.lang.String user, java.util.Date since, java.util.Date until) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamSessionService#	 * @see com.soffid.iam.pam.service.PamSessionService#long getVideoSize(com.soffid.iam.pam.api.PamSession session, long chapter)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public long getVideoSize(
		final com.soffid.iam.pam.api.PamSession session, 
		final long chapter)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (session == null) {
			throw new IllegalArgumentException("long com.soffid.iam.pam.service.PamSessionService.getVideoSize(com.soffid.iam.pam.api.PamSession session, long chapter) - session cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetVideoSize(session, chapter)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Long) __r[0]).longValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamSessionService.class).
			warn ("Error on PamSessionService.getVideoSize", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamSessionService.getVideoSize", (Throwable) __r[1]);
	}

	protected abstract long handleGetVideoSize(com.soffid.iam.pam.api.PamSession session, long chapter) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamSessionService#	 * @see com.soffid.iam.pam.service.PamSessionService#void generateKeystrokes(com.soffid.iam.pam.api.PamSession session, java.io.OutputStream stream)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void generateKeystrokes(
		final com.soffid.iam.pam.api.PamSession session, 
		final java.io.OutputStream stream)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (session == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.PamSessionService.generateKeystrokes(com.soffid.iam.pam.api.PamSession session, java.io.OutputStream stream) - session cannot be null");
		}
		if (stream == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.PamSessionService.generateKeystrokes(com.soffid.iam.pam.api.PamSession session, java.io.OutputStream stream) - stream cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleGenerateKeystrokes(session, stream);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamSessionService.class).
			warn ("Error on PamSessionService.generateKeystrokes", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamSessionService.generateKeystrokes", (Throwable) __r[1]);
	}

	protected abstract void handleGenerateKeystrokes(com.soffid.iam.pam.api.PamSession session, java.io.OutputStream stream) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamSessionService#	 * @see com.soffid.iam.pam.service.PamSessionService#void generateVideo(com.soffid.iam.pam.api.PamSession session, long chapter, java.io.OutputStream stream, long start, long end)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void generateVideo(
		final com.soffid.iam.pam.api.PamSession session, 
		final long chapter, 
		final java.io.OutputStream stream, 
		final long start, 
		final long end)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (session == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.PamSessionService.generateVideo(com.soffid.iam.pam.api.PamSession session, long chapter, java.io.OutputStream stream, long start, long end) - session cannot be null");
		}
		if (stream == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.PamSessionService.generateVideo(com.soffid.iam.pam.api.PamSession session, long chapter, java.io.OutputStream stream, long start, long end) - stream cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleGenerateVideo(session, chapter, stream, start, end);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamSessionService.class).
			warn ("Error on PamSessionService.generateVideo", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamSessionService.generateVideo", (Throwable) __r[1]);
	}

	protected abstract void handleGenerateVideo(com.soffid.iam.pam.api.PamSession session, long chapter, java.io.OutputStream stream, long start, long end) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.PamSessionService#	 * @see com.soffid.iam.pam.service.PamSessionService#void remove(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void remove(
		final com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (jumpServerGroup == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.PamSessionService.remove(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup) - jumpServerGroup cannot be null");
		}
		if (jumpServerGroup.getName() == null || jumpServerGroup.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.PamSessionService.remove(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup) - jumpServerGroup.name cannot be null");
		}
		if (jumpServerGroup.getStoreUrl() == null || jumpServerGroup.getStoreUrl().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.PamSessionService.remove(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup) - jumpServerGroup.storeUrl cannot be null");
		}
		if (jumpServerGroup.getStoreUserName() == null || jumpServerGroup.getStoreUserName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.PamSessionService.remove(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup) - jumpServerGroup.storeUserName cannot be null");
		}
		if (jumpServerGroup.getPassword() == null || jumpServerGroup.getPassword().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.PamSessionService.remove(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup) - jumpServerGroup.password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRemove(jumpServerGroup);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.PamSessionService.class).
			warn ("Error on PamSessionService.remove", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PamSessionService.remove", (Throwable) __r[1]);
	}

	protected abstract void handleRemove(com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup) throws Exception;

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
