//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.am.service.NetworkService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.am.service.NetworkService
 */
public abstract class NetworkServiceBase
	implements com.soffid.iam.am.service.NetworkService
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

	private com.soffid.iam.service.impl.AttributeValidationService attributeValidationService;

	/**
	 * Sets reference to <code>attributeValidationService</code>.
	 */
	public void setAttributeValidationService (com.soffid.iam.service.impl.AttributeValidationService attributeValidationService) {
		this.attributeValidationService = attributeValidationService;
	}

	/**
	 * Gets reference to <code>attributeValidationService</code>.
	 */
	public com.soffid.iam.service.impl.AttributeValidationService getAttributeValidationService () {
		return attributeValidationService;
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

	private com.soffid.iam.am.model.EntryPointEntityDao entryPointEntityDao;

	/**
	 * Sets reference to <code>entryPointEntityDao</code>.
	 */
	public void setEntryPointEntityDao (com.soffid.iam.am.model.EntryPointEntityDao entryPointEntityDao) {
		this.entryPointEntityDao = entryPointEntityDao;
	}

	/**
	 * Gets reference to <code>entryPointEntityDao</code>.
	 */
	public com.soffid.iam.am.model.EntryPointEntityDao getEntryPointEntityDao () {
		return entryPointEntityDao;
	}

	private com.soffid.iam.am.service.EntryPointService entryPointService;

	/**
	 * Sets reference to <code>entryPointService</code>.
	 */
	public void setEntryPointService (com.soffid.iam.am.service.EntryPointService entryPointService) {
		this.entryPointService = entryPointService;
	}

	/**
	 * Gets reference to <code>entryPointService</code>.
	 */
	public com.soffid.iam.am.service.EntryPointService getEntryPointService () {
		return entryPointService;
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

	private com.soffid.iam.pam.model.HostAdminEntityDao hostAdminEntityDao;

	/**
	 * Sets reference to <code>hostAdminEntityDao</code>.
	 */
	public void setHostAdminEntityDao (com.soffid.iam.pam.model.HostAdminEntityDao hostAdminEntityDao) {
		this.hostAdminEntityDao = hostAdminEntityDao;
	}

	/**
	 * Gets reference to <code>hostAdminEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.HostAdminEntityDao getHostAdminEntityDao () {
		return hostAdminEntityDao;
	}

	private com.soffid.iam.iga.model.HostAliasEntityDao hostAliasEntityDao;

	/**
	 * Sets reference to <code>hostAliasEntityDao</code>.
	 */
	public void setHostAliasEntityDao (com.soffid.iam.iga.model.HostAliasEntityDao hostAliasEntityDao) {
		this.hostAliasEntityDao = hostAliasEntityDao;
	}

	/**
	 * Gets reference to <code>hostAliasEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.HostAliasEntityDao getHostAliasEntityDao () {
		return hostAliasEntityDao;
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

	private com.soffid.iam.iga.model.MetaDataEntityDao metaDataEntityDao;

	/**
	 * Sets reference to <code>metaDataEntityDao</code>.
	 */
	public void setMetaDataEntityDao (com.soffid.iam.iga.model.MetaDataEntityDao metaDataEntityDao) {
		this.metaDataEntityDao = metaDataEntityDao;
	}

	/**
	 * Gets reference to <code>metaDataEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.MetaDataEntityDao getMetaDataEntityDao () {
		return metaDataEntityDao;
	}

	private com.soffid.iam.am.model.NetworkAuthorizationEntityDao networkAuthorizationEntityDao;

	/**
	 * Sets reference to <code>networkAuthorizationEntityDao</code>.
	 */
	public void setNetworkAuthorizationEntityDao (com.soffid.iam.am.model.NetworkAuthorizationEntityDao networkAuthorizationEntityDao) {
		this.networkAuthorizationEntityDao = networkAuthorizationEntityDao;
	}

	/**
	 * Gets reference to <code>networkAuthorizationEntityDao</code>.
	 */
	public com.soffid.iam.am.model.NetworkAuthorizationEntityDao getNetworkAuthorizationEntityDao () {
		return networkAuthorizationEntityDao;
	}

	private com.soffid.iam.pam.model.NetworkDiscoverRangeEntityDao networkDiscoverRangeEntityDao;

	/**
	 * Sets reference to <code>networkDiscoverRangeEntityDao</code>.
	 */
	public void setNetworkDiscoverRangeEntityDao (com.soffid.iam.pam.model.NetworkDiscoverRangeEntityDao networkDiscoverRangeEntityDao) {
		this.networkDiscoverRangeEntityDao = networkDiscoverRangeEntityDao;
	}

	/**
	 * Gets reference to <code>networkDiscoverRangeEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.NetworkDiscoverRangeEntityDao getNetworkDiscoverRangeEntityDao () {
		return networkDiscoverRangeEntityDao;
	}

	private com.soffid.iam.am.model.NetworkEntityDao networkEntityDao;

	/**
	 * Sets reference to <code>networkEntityDao</code>.
	 */
	public void setNetworkEntityDao (com.soffid.iam.am.model.NetworkEntityDao networkEntityDao) {
		this.networkEntityDao = networkEntityDao;
	}

	/**
	 * Gets reference to <code>networkEntityDao</code>.
	 */
	public com.soffid.iam.am.model.NetworkEntityDao getNetworkEntityDao () {
		return networkEntityDao;
	}

	private com.soffid.iam.am.model.OsTypeEntityDao osTypeEntityDao;

	/**
	 * Sets reference to <code>osTypeEntityDao</code>.
	 */
	public void setOsTypeEntityDao (com.soffid.iam.am.model.OsTypeEntityDao osTypeEntityDao) {
		this.osTypeEntityDao = osTypeEntityDao;
	}

	/**
	 * Gets reference to <code>osTypeEntityDao</code>.
	 */
	public com.soffid.iam.am.model.OsTypeEntityDao getOsTypeEntityDao () {
		return osTypeEntityDao;
	}

	private com.soffid.iam.iga.service.PrinterService printerService;

	/**
	 * Sets reference to <code>printerService</code>.
	 */
	public void setPrinterService (com.soffid.iam.iga.service.PrinterService printerService) {
		this.printerService = printerService;
	}

	/**
	 * Gets reference to <code>printerService</code>.
	 */
	public com.soffid.iam.iga.service.PrinterService getPrinterService () {
		return printerService;
	}

	private com.soffid.iam.iga.model.RoleEntityDao roleEntityDao;

	/**
	 * Sets reference to <code>roleEntityDao</code>.
	 */
	public void setRoleEntityDao (com.soffid.iam.iga.model.RoleEntityDao roleEntityDao) {
		this.roleEntityDao = roleEntityDao;
	}

	/**
	 * Gets reference to <code>roleEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleEntityDao getRoleEntityDao () {
		return roleEntityDao;
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

	private com.soffid.iam.sync.model.TaskEntityDao taskEntityDao;

	/**
	 * Sets reference to <code>taskEntityDao</code>.
	 */
	public void setTaskEntityDao (com.soffid.iam.sync.model.TaskEntityDao taskEntityDao) {
		this.taskEntityDao = taskEntityDao;
	}

	/**
	 * Gets reference to <code>taskEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.TaskEntityDao getTaskEntityDao () {
		return taskEntityDao;
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
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#boolean canLogin(java.lang.String user, java.lang.String host)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean canLogin(
		final java.lang.String user, 
		final java.lang.String host)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.NetworkService.canLogin(java.lang.String user, java.lang.String host) - user cannot be null");
		}
		if (host == null || host.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.NetworkService.canLogin(java.lang.String user, java.lang.String host) - host cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCanLogin(user, host)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.canLogin", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.canLogin", (Throwable) __r[1]);
	}

	protected abstract boolean handleCanLogin(java.lang.String user, java.lang.String host) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.Host create(com.soffid.iam.am.api.Host maquina)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Host create(
		final com.soffid.iam.am.api.Host maquina)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (maquina == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Host com.soffid.iam.am.service.NetworkService.create(com.soffid.iam.am.api.Host maquina) - maquina cannot be null");
		}
		if (maquina.getName() == null || maquina.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Host com.soffid.iam.am.service.NetworkService.create(com.soffid.iam.am.api.Host maquina) - maquina.name cannot be null");
		}
		if (maquina.getNetwork() == null || maquina.getNetwork().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Host com.soffid.iam.am.service.NetworkService.create(com.soffid.iam.am.api.Host maquina) - maquina.network cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(maquina)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Host) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Host handleCreate(com.soffid.iam.am.api.Host maquina) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.Host findHostById(java.lang.Long idMaquina)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Host findHostById(
		final java.lang.Long idMaquina)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (idMaquina == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Host com.soffid.iam.am.service.NetworkService.findHostById(java.lang.Long idMaquina) - idMaquina cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindHostById(idMaquina)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Host) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findHostById", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findHostById", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Host handleFindHostById(java.lang.Long idMaquina) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.Host findHostByIp(java.lang.String ip)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Host findHostByIp(
		final java.lang.String ip)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (ip == null || ip.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Host com.soffid.iam.am.service.NetworkService.findHostByIp(java.lang.String ip) - ip cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindHostByIp(ip)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Host) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findHostByIp", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findHostByIp", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Host handleFindHostByIp(java.lang.String ip) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.Host findHostByName(java.lang.String nom)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Host findHostByName(
		final java.lang.String nom)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nom == null || nom.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Host com.soffid.iam.am.service.NetworkService.findHostByName(java.lang.String nom) - nom cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindHostByName(nom)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Host) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findHostByName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findHostByName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Host handleFindHostByName(java.lang.String nom) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.Host findHostBySerialNumber(java.lang.String serialNumber)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Host findHostBySerialNumber(
		final java.lang.String serialNumber)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (serialNumber == null || serialNumber.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Host com.soffid.iam.am.service.NetworkService.findHostBySerialNumber(java.lang.String serialNumber) - serialNumber cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindHostBySerialNumber(serialNumber)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Host) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findHostBySerialNumber", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findHostBySerialNumber", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Host handleFindHostBySerialNumber(java.lang.String serialNumber) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.Host registerDynamicIP(java.lang.String nomMaquina, java.lang.String ip, java.lang.String serialNumber)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackForClassName={"java.lang.Exception"}, noRollbackForClassName={"es.caib.seycon.ng.exception.UnknownNetworkException","es.caib.seycon.ng.exception.UnknownHostException"})
	public com.soffid.iam.am.api.Host registerDynamicIP(
		final java.lang.String nomMaquina, 
		final java.lang.String ip, 
		final java.lang.String serialNumber)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownHostException, com.soffid.iam.exception.UnknownNetworkException
	{
		if (nomMaquina == null || nomMaquina.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Host com.soffid.iam.am.service.NetworkService.registerDynamicIP(java.lang.String nomMaquina, java.lang.String ip, java.lang.String serialNumber) - nomMaquina cannot be null");
		}
		if (ip == null || ip.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Host com.soffid.iam.am.service.NetworkService.registerDynamicIP(java.lang.String nomMaquina, java.lang.String ip, java.lang.String serialNumber) - ip cannot be null");
		}
		if (serialNumber == null || serialNumber.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Host com.soffid.iam.am.service.NetworkService.registerDynamicIP(java.lang.String nomMaquina, java.lang.String ip, java.lang.String serialNumber) - serialNumber cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleRegisterDynamicIP(nomMaquina, ip, serialNumber)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Host) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.UnknownHostException) 
			throw (com.soffid.iam.exception.UnknownHostException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.UnknownNetworkException) 
			throw (com.soffid.iam.exception.UnknownNetworkException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.registerDynamicIP", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.registerDynamicIP", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Host handleRegisterDynamicIP(java.lang.String nomMaquina, java.lang.String ip, java.lang.String serialNumber) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.HostAlias create(com.soffid.iam.am.api.HostAlias aliasMaquina)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.HostAlias create(
		final com.soffid.iam.am.api.HostAlias aliasMaquina)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (aliasMaquina == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.HostAlias com.soffid.iam.am.service.NetworkService.create(com.soffid.iam.am.api.HostAlias aliasMaquina) - aliasMaquina cannot be null");
		}
		if (aliasMaquina.getAlias() == null || aliasMaquina.getAlias().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.HostAlias com.soffid.iam.am.service.NetworkService.create(com.soffid.iam.am.api.HostAlias aliasMaquina) - aliasMaquina.alias cannot be null");
		}
		if (aliasMaquina.getHostName() == null || aliasMaquina.getHostName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.HostAlias com.soffid.iam.am.service.NetworkService.create(com.soffid.iam.am.api.HostAlias aliasMaquina) - aliasMaquina.hostName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(aliasMaquina)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.HostAlias) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.HostAlias handleCreate(com.soffid.iam.am.api.HostAlias aliasMaquina) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.Network create(com.soffid.iam.am.api.Network xarxa)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Network create(
		final com.soffid.iam.am.api.Network xarxa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (xarxa == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Network com.soffid.iam.am.service.NetworkService.create(com.soffid.iam.am.api.Network xarxa) - xarxa cannot be null");
		}
		if (xarxa.getName() == null || xarxa.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Network com.soffid.iam.am.service.NetworkService.create(com.soffid.iam.am.api.Network xarxa) - xarxa.name cannot be null");
		}
		if (xarxa.getIp() == null || xarxa.getIp().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Network com.soffid.iam.am.service.NetworkService.create(com.soffid.iam.am.api.Network xarxa) - xarxa.ip cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(xarxa)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Network) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Network handleCreate(com.soffid.iam.am.api.Network xarxa) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.Network findNetworkByIpAddress(java.lang.String ipAdress)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Network findNetworkByIpAddress(
		final java.lang.String ipAdress)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (ipAdress == null || ipAdress.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Network com.soffid.iam.am.service.NetworkService.findNetworkByIpAddress(java.lang.String ipAdress) - ipAdress cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindNetworkByIpAddress(ipAdress)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Network) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findNetworkByIpAddress", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findNetworkByIpAddress", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Network handleFindNetworkByIpAddress(java.lang.String ipAdress) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.Network findNetworkByName(java.lang.String codi)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Network findNetworkByName(
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codi == null || codi.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Network com.soffid.iam.am.service.NetworkService.findNetworkByName(java.lang.String codi) - codi cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindNetworkByName(codi)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Network) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findNetworkByName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findNetworkByName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Network handleFindNetworkByName(java.lang.String codi) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.NetworkAuthorization create(com.soffid.iam.am.api.NetworkAuthorization accessList)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.NetworkAuthorization create(
		final com.soffid.iam.am.api.NetworkAuthorization accessList)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (accessList == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.NetworkAuthorization com.soffid.iam.am.service.NetworkService.create(com.soffid.iam.am.api.NetworkAuthorization accessList) - accessList cannot be null");
		}
		if (accessList.getIdentity() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.NetworkAuthorization com.soffid.iam.am.service.NetworkService.create(com.soffid.iam.am.api.NetworkAuthorization accessList) - accessList.identity cannot be null");
		}
		if (accessList.getLevel() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.NetworkAuthorization com.soffid.iam.am.service.NetworkService.create(com.soffid.iam.am.api.NetworkAuthorization accessList) - accessList.level cannot be null");
		}
		if (accessList.getNetworkCode() == null || accessList.getNetworkCode().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.NetworkAuthorization com.soffid.iam.am.service.NetworkService.create(com.soffid.iam.am.api.NetworkAuthorization accessList) - accessList.networkCode cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(accessList)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.NetworkAuthorization) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.NetworkAuthorization handleCreate(com.soffid.iam.am.api.NetworkAuthorization accessList) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.NetworkAuthorization findNetworkAuthorizationsByNetworkNameAndIdentityName(java.lang.String codiXarxa, java.lang.String codiIdentitat)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.NetworkAuthorization findNetworkAuthorizationsByNetworkNameAndIdentityName(
		final java.lang.String codiXarxa, 
		final java.lang.String codiIdentitat)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiXarxa == null || codiXarxa.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.NetworkAuthorization com.soffid.iam.am.service.NetworkService.findNetworkAuthorizationsByNetworkNameAndIdentityName(java.lang.String codiXarxa, java.lang.String codiIdentitat) - codiXarxa cannot be null");
		}
		if (codiIdentitat == null || codiIdentitat.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.NetworkAuthorization com.soffid.iam.am.service.NetworkService.findNetworkAuthorizationsByNetworkNameAndIdentityName(java.lang.String codiXarxa, java.lang.String codiIdentitat) - codiIdentitat cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindNetworkAuthorizationsByNetworkNameAndIdentityName(codiXarxa, codiIdentitat)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.NetworkAuthorization) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findNetworkAuthorizationsByNetworkNameAndIdentityName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findNetworkAuthorizationsByNetworkNameAndIdentityName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.NetworkAuthorization handleFindNetworkAuthorizationsByNetworkNameAndIdentityName(java.lang.String codiXarxa, java.lang.String codiIdentitat) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.NetworkAuthorization update(com.soffid.iam.am.api.NetworkAuthorization accessList)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.NetworkAuthorization update(
		final com.soffid.iam.am.api.NetworkAuthorization accessList)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (accessList == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.NetworkAuthorization com.soffid.iam.am.service.NetworkService.update(com.soffid.iam.am.api.NetworkAuthorization accessList) - accessList cannot be null");
		}
		if (accessList.getIdentity() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.NetworkAuthorization com.soffid.iam.am.service.NetworkService.update(com.soffid.iam.am.api.NetworkAuthorization accessList) - accessList.identity cannot be null");
		}
		if (accessList.getLevel() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.NetworkAuthorization com.soffid.iam.am.service.NetworkService.update(com.soffid.iam.am.api.NetworkAuthorization accessList) - accessList.level cannot be null");
		}
		if (accessList.getNetworkCode() == null || accessList.getNetworkCode().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.NetworkAuthorization com.soffid.iam.am.service.NetworkService.update(com.soffid.iam.am.api.NetworkAuthorization accessList) - accessList.networkCode cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(accessList)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.NetworkAuthorization) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.NetworkAuthorization handleUpdate(com.soffid.iam.am.api.NetworkAuthorization accessList) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.OsType create(com.soffid.iam.am.api.OsType osType)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.OsType create(
		final com.soffid.iam.am.api.OsType osType)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (osType == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.OsType com.soffid.iam.am.service.NetworkService.create(com.soffid.iam.am.api.OsType osType) - osType cannot be null");
		}
		if (osType.getName() == null || osType.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.OsType com.soffid.iam.am.service.NetworkService.create(com.soffid.iam.am.api.OsType osType) - osType.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(osType)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.OsType) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.OsType handleCreate(com.soffid.iam.am.api.OsType osType) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.OsType findOSTypeById(java.lang.Long osId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.OsType findOSTypeById(
		final java.lang.Long osId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (osId == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.OsType com.soffid.iam.am.service.NetworkService.findOSTypeById(java.lang.Long osId) - osId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindOSTypeById(osId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.OsType) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findOSTypeById", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findOSTypeById", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.OsType handleFindOSTypeById(java.lang.Long osId) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.OsType findOSTypeByName(java.lang.String osName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.OsType findOSTypeByName(
		final java.lang.String osName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (osName == null || osName.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.OsType com.soffid.iam.am.service.NetworkService.findOSTypeByName(java.lang.String osName) - osName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindOSTypeByName(osName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.OsType) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findOSTypeByName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findOSTypeByName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.OsType handleFindOSTypeByName(java.lang.String osName) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.base.api.Identity findIdentityByName(java.lang.String codi)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Identity findIdentityByName(
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codi == null || codi.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Identity com.soffid.iam.am.service.NetworkService.findIdentityByName(java.lang.String codi) - codi cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindIdentityByName(codi)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Identity) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findIdentityByName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findIdentityByName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Identity handleFindIdentityByName(java.lang.String codi) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.pam.api.HostAdmin create(com.soffid.iam.pam.api.HostAdmin autoritzacioAccesComAdministrador)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.pam.api.HostAdmin create(
		final com.soffid.iam.pam.api.HostAdmin autoritzacioAccesComAdministrador)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (autoritzacioAccesComAdministrador == null) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.HostAdmin com.soffid.iam.am.service.NetworkService.create(com.soffid.iam.pam.api.HostAdmin autoritzacioAccesComAdministrador) - autoritzacioAccesComAdministrador cannot be null");
		}
		if (autoritzacioAccesComAdministrador.getHostName() == null || autoritzacioAccesComAdministrador.getHostName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.HostAdmin com.soffid.iam.am.service.NetworkService.create(com.soffid.iam.pam.api.HostAdmin autoritzacioAccesComAdministrador) - autoritzacioAccesComAdministrador.hostName cannot be null");
		}
		if (autoritzacioAccesComAdministrador.getAuthorizationAccessExpirationDate() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.HostAdmin com.soffid.iam.am.service.NetworkService.create(com.soffid.iam.pam.api.HostAdmin autoritzacioAccesComAdministrador) - autoritzacioAccesComAdministrador.authorizationAccessExpirationDate cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(autoritzacioAccesComAdministrador)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.pam.api.HostAdmin) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.pam.api.HostAdmin handleCreate(com.soffid.iam.pam.api.HostAdmin autoritzacioAccesComAdministrador) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.pam.api.HostAdmin revokeAdministratorAccessHost(com.soffid.iam.pam.api.HostAdmin autoritzacioAccesComAdministrador)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.pam.api.HostAdmin revokeAdministratorAccessHost(
		final com.soffid.iam.pam.api.HostAdmin autoritzacioAccesComAdministrador)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (autoritzacioAccesComAdministrador == null) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.HostAdmin com.soffid.iam.am.service.NetworkService.revokeAdministratorAccessHost(com.soffid.iam.pam.api.HostAdmin autoritzacioAccesComAdministrador) - autoritzacioAccesComAdministrador cannot be null");
		}
		if (autoritzacioAccesComAdministrador.getHostName() == null || autoritzacioAccesComAdministrador.getHostName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.HostAdmin com.soffid.iam.am.service.NetworkService.revokeAdministratorAccessHost(com.soffid.iam.pam.api.HostAdmin autoritzacioAccesComAdministrador) - autoritzacioAccesComAdministrador.hostName cannot be null");
		}
		if (autoritzacioAccesComAdministrador.getAuthorizationAccessExpirationDate() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.HostAdmin com.soffid.iam.am.service.NetworkService.revokeAdministratorAccessHost(com.soffid.iam.pam.api.HostAdmin autoritzacioAccesComAdministrador) - autoritzacioAccesComAdministrador.authorizationAccessExpirationDate cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleRevokeAdministratorAccessHost(autoritzacioAccesComAdministrador)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.pam.api.HostAdmin) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.revokeAdministratorAccessHost", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.revokeAdministratorAccessHost", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.pam.api.HostAdmin handleRevokeAdministratorAccessHost(com.soffid.iam.pam.api.HostAdmin autoritzacioAccesComAdministrador) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Host> findHosts(com.soffid.zkdb.api.Query query)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Host> findHosts(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (query == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Host> com.soffid.iam.am.service.NetworkService.findHosts(com.soffid.zkdb.api.Query query) - query cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindHosts(query)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Host>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findHosts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findHosts", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Host> handleFindHosts(com.soffid.zkdb.api.Query query) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Network> findNetworks(com.soffid.zkdb.api.Query query)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Network> findNetworks(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (query == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Network> com.soffid.iam.am.service.NetworkService.findNetworks(com.soffid.zkdb.api.Query query) - query cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindNetworks(query)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Network>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findNetworks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findNetworks", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Network> handleFindNetworks(com.soffid.zkdb.api.Query query) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.lang.Boolean isManaged(java.lang.String codiXarxa)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.Boolean isManaged(
		final java.lang.String codiXarxa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiXarxa == null || codiXarxa.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.Boolean com.soffid.iam.am.service.NetworkService.isManaged(java.lang.String codiXarxa) - codiXarxa cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsManaged(codiXarxa)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.Boolean) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.isManaged", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.isManaged", (Throwable) __r[1]);
	}

	protected abstract java.lang.Boolean handleIsManaged(java.lang.String codiXarxa) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.lang.Boolean hasAnyACLNetworks(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.Boolean hasAnyACLNetworks(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.Boolean com.soffid.iam.am.service.NetworkService.hasAnyACLNetworks(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleHasAnyACLNetworks(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.Boolean) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.hasAnyACLNetworks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.hasAnyACLNetworks", (Throwable) __r[1]);
	}

	protected abstract java.lang.Boolean handleHasAnyACLNetworks(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.lang.Boolean launchVNC(java.lang.Long sessioId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.Boolean launchVNC(
		final java.lang.Long sessioId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (sessioId == null) {
			throw new IllegalArgumentException("java.lang.Boolean com.soffid.iam.am.service.NetworkService.launchVNC(java.lang.Long sessioId) - sessioId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleLaunchVNC(sessioId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.Boolean) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.launchVNC", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.launchVNC", (Throwable) __r[1]);
	}

	protected abstract java.lang.Boolean handleLaunchVNC(java.lang.Long sessioId) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.lang.Boolean hasNetworkAccess(java.lang.String codiUsuari, java.lang.String codiXarxa)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.Boolean hasNetworkAccess(
		final java.lang.String codiUsuari, 
		final java.lang.String codiXarxa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.Boolean com.soffid.iam.am.service.NetworkService.hasNetworkAccess(java.lang.String codiUsuari, java.lang.String codiXarxa) - codiUsuari cannot be null");
		}
		if (codiXarxa == null || codiXarxa.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.Boolean com.soffid.iam.am.service.NetworkService.hasNetworkAccess(java.lang.String codiUsuari, java.lang.String codiXarxa) - codiXarxa cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleHasNetworkAccess(codiUsuari, codiXarxa)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.Boolean) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.hasNetworkAccess", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.hasNetworkAccess", (Throwable) __r[1]);
	}

	protected abstract java.lang.Boolean handleHasNetworkAccess(java.lang.String codiUsuari, java.lang.String codiXarxa) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.lang.Boolean hasManagedNetwork()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.Boolean hasManagedNetwork()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleHasManagedNetwork()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.Boolean) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.hasManagedNetwork", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.hasManagedNetwork", (Throwable) __r[1]);
	}

	protected abstract java.lang.Boolean handleHasManagedNetwork() throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.lang.Long findAccessLevelByHostNameAndNetworkName(java.lang.String nomMaquina, java.lang.String codiXarxa)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.Long findAccessLevelByHostNameAndNetworkName(
		final java.lang.String nomMaquina, 
		final java.lang.String codiXarxa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomMaquina == null || nomMaquina.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.Long com.soffid.iam.am.service.NetworkService.findAccessLevelByHostNameAndNetworkName(java.lang.String nomMaquina, java.lang.String codiXarxa) - nomMaquina cannot be null");
		}
		if (codiXarxa == null || codiXarxa.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.Long com.soffid.iam.am.service.NetworkService.findAccessLevelByHostNameAndNetworkName(java.lang.String nomMaquina, java.lang.String codiXarxa) - codiXarxa cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAccessLevelByHostNameAndNetworkName(nomMaquina, codiXarxa)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.Long) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findAccessLevelByHostNameAndNetworkName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findAccessLevelByHostNameAndNetworkName", (Throwable) __r[1]);
	}

	protected abstract java.lang.Long handleFindAccessLevelByHostNameAndNetworkName(java.lang.String nomMaquina, java.lang.String codiXarxa) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.lang.Long getAvailableIPs(java.lang.String codiXarxa)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.Long getAvailableIPs(
		final java.lang.String codiXarxa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiXarxa == null || codiXarxa.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.Long com.soffid.iam.am.service.NetworkService.getAvailableIPs(java.lang.String codiXarxa) - codiXarxa cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetAvailableIPs(codiXarxa)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.Long) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.getAvailableIPs", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.getAvailableIPs", (Throwable) __r[1]);
	}

	protected abstract java.lang.Long handleGetAvailableIPs(java.lang.String codiXarxa) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.lang.Long getNotAvailableIPs(java.lang.String codiXarxa)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.Long getNotAvailableIPs(
		final java.lang.String codiXarxa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiXarxa == null || codiXarxa.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.Long com.soffid.iam.am.service.NetworkService.getNotAvailableIPs(java.lang.String codiXarxa) - codiXarxa cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetNotAvailableIPs(codiXarxa)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.Long) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.getNotAvailableIPs", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.getNotAvailableIPs", (Throwable) __r[1]);
	}

	protected abstract java.lang.Long handleGetNotAvailableIPs(java.lang.String codiXarxa) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.lang.String getFirstAvailableIP(java.lang.String codiXarxa)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String getFirstAvailableIP(
		final java.lang.String codiXarxa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiXarxa == null || codiXarxa.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.am.service.NetworkService.getFirstAvailableIP(java.lang.String codiXarxa) - codiXarxa cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetFirstAvailableIP(codiXarxa)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.getFirstAvailableIP", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.getFirstAvailableIP", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGetFirstAvailableIP(java.lang.String codiXarxa) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.lang.String[] getTasks(java.lang.String nomMaquina)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String[] getTasks(
		final java.lang.String nomMaquina)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomMaquina == null || nomMaquina.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String[] com.soffid.iam.am.service.NetworkService.getTasks(java.lang.String nomMaquina) - nomMaquina cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetTasks(nomMaquina)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.getTasks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.getTasks", (Throwable) __r[1]);
	}

	protected abstract java.lang.String[] handleGetTasks(java.lang.String nomMaquina) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.lang.String[] getHostAdminUserAndPassword(java.lang.String nomMaquina)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String[] getHostAdminUserAndPassword(
		final java.lang.String nomMaquina)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomMaquina == null || nomMaquina.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String[] com.soffid.iam.am.service.NetworkService.getHostAdminUserAndPassword(java.lang.String nomMaquina) - nomMaquina cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetHostAdminUserAndPassword(nomMaquina)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.getHostAdminUserAndPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.getHostAdminUserAndPassword", (Throwable) __r[1]);
	}

	protected abstract java.lang.String[] handleGetHostAdminUserAndPassword(java.lang.String nomMaquina) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findAllNetworkAuthorizationsByUserName(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findAllNetworkAuthorizationsByUserName(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.NetworkAuthorization> com.soffid.iam.am.service.NetworkService.findAllNetworkAuthorizationsByUserName(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAllNetworkAuthorizationsByUserName(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.am.api.NetworkAuthorization>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findAllNetworkAuthorizationsByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findAllNetworkAuthorizationsByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.am.api.NetworkAuthorization> handleFindAllNetworkAuthorizationsByUserName(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.am.api.HostAlias> findAliasByHostName(java.lang.String nomMaquina)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.am.api.HostAlias> findAliasByHostName(
		final java.lang.String nomMaquina)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomMaquina == null || nomMaquina.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.HostAlias> com.soffid.iam.am.service.NetworkService.findAliasByHostName(java.lang.String nomMaquina) - nomMaquina cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAliasByHostName(nomMaquina)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.am.api.HostAlias>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findAliasByHostName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findAliasByHostName", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.am.api.HostAlias> handleFindAliasByHostName(java.lang.String nomMaquina) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.am.api.OsType> findAllOSTypes()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.am.api.OsType> findAllOSTypes()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAllOSTypes()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.am.api.OsType>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findAllOSTypes", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findAllOSTypes", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.am.api.OsType> handleFindAllOSTypes() throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.pam.api.HostAdmin> findAuthorizationsToAccessHostWithAdministratorRigthsByHostsAndRequestDate(java.lang.String nomHost, java.lang.String dataPeticio, java.lang.String dataCaducitat)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.pam.api.HostAdmin> findAuthorizationsToAccessHostWithAdministratorRigthsByHostsAndRequestDate(
		final java.lang.String nomHost, 
		final java.lang.String dataPeticio, 
		final java.lang.String dataCaducitat)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomHost == null || nomHost.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.pam.api.HostAdmin> com.soffid.iam.am.service.NetworkService.findAuthorizationsToAccessHostWithAdministratorRigthsByHostsAndRequestDate(java.lang.String nomHost, java.lang.String dataPeticio, java.lang.String dataCaducitat) - nomHost cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAuthorizationsToAccessHostWithAdministratorRigthsByHostsAndRequestDate(nomHost, dataPeticio, dataCaducitat)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.pam.api.HostAdmin>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findAuthorizationsToAccessHostWithAdministratorRigthsByHostsAndRequestDate", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findAuthorizationsToAccessHostWithAdministratorRigthsByHostsAndRequestDate", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.pam.api.HostAdmin> handleFindAuthorizationsToAccessHostWithAdministratorRigthsByHostsAndRequestDate(java.lang.String nomHost, java.lang.String dataPeticio, java.lang.String dataCaducitat) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.am.api.Host> findHostsByNetwork_Discovery(com.soffid.iam.am.api.Network parent, java.lang.String text)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.am.api.Host> findHostsByNetwork_Discovery(
		final com.soffid.iam.am.api.Network parent, 
		final java.lang.String text)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (parent == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.Host> com.soffid.iam.am.service.NetworkService.findHostsByNetwork_Discovery(com.soffid.iam.am.api.Network parent, java.lang.String text) - parent cannot be null");
		}
		if (parent.getName() == null || parent.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.Host> com.soffid.iam.am.service.NetworkService.findHostsByNetwork_Discovery(com.soffid.iam.am.api.Network parent, java.lang.String text) - parent.name cannot be null");
		}
		if (parent.getIp() == null || parent.getIp().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.Host> com.soffid.iam.am.service.NetworkService.findHostsByNetwork_Discovery(com.soffid.iam.am.api.Network parent, java.lang.String text) - parent.ip cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindHostsByNetwork_Discovery(parent, text)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.am.api.Host>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findHostsByNetwork_Discovery", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findHostsByNetwork_Discovery", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.am.api.Host> handleFindHostsByNetwork_Discovery(com.soffid.iam.am.api.Network parent, java.lang.String text) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.base.api.Identity> findIdentitiesByName(java.lang.String codi)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.base.api.Identity> findIdentitiesByName(
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codi == null || codi.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.Identity> com.soffid.iam.am.service.NetworkService.findIdentitiesByName(java.lang.String codi) - codi cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindIdentitiesByName(codi)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.base.api.Identity>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findIdentitiesByName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findIdentitiesByName", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.base.api.Identity> handleFindIdentitiesByName(java.lang.String codi) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findNetworkAuthorizationsByGroupName(java.lang.String codiGrup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findNetworkAuthorizationsByGroupName(
		final java.lang.String codiGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiGrup == null || codiGrup.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.NetworkAuthorization> com.soffid.iam.am.service.NetworkService.findNetworkAuthorizationsByGroupName(java.lang.String codiGrup) - codiGrup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindNetworkAuthorizationsByGroupName(codiGrup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.am.api.NetworkAuthorization>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findNetworkAuthorizationsByGroupName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findNetworkAuthorizationsByGroupName", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.am.api.NetworkAuthorization> handleFindNetworkAuthorizationsByGroupName(java.lang.String codiGrup) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findNetworkAuthorizationsByUserName(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findNetworkAuthorizationsByUserName(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.NetworkAuthorization> com.soffid.iam.am.service.NetworkService.findNetworkAuthorizationsByUserName(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindNetworkAuthorizationsByUserName(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.am.api.NetworkAuthorization>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findNetworkAuthorizationsByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findNetworkAuthorizationsByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.am.api.NetworkAuthorization> handleFindNetworkAuthorizationsByUserName(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findNetworkAuthorizationsByRoleName(java.lang.String nomRol)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findNetworkAuthorizationsByRoleName(
		final java.lang.String nomRol)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomRol == null || nomRol.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.NetworkAuthorization> com.soffid.iam.am.service.NetworkService.findNetworkAuthorizationsByRoleName(java.lang.String nomRol) - nomRol cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindNetworkAuthorizationsByRoleName(nomRol)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.am.api.NetworkAuthorization>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findNetworkAuthorizationsByRoleName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findNetworkAuthorizationsByRoleName", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.am.api.NetworkAuthorization> handleFindNetworkAuthorizationsByRoleName(java.lang.String nomRol) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.am.api.Network> findNetworkByText_Discovery(java.lang.String text)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.am.api.Network> findNetworkByText_Discovery(
		final java.lang.String text)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindNetworkByText_Discovery(text)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.am.api.Network>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findNetworkByText_Discovery", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findNetworkByText_Discovery", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.am.api.Network> handleFindNetworkByText_Discovery(java.lang.String text) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.am.api.Session> findSessionsByHostName(java.lang.String codiMaquina)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.am.api.Session> findSessionsByHostName(
		final java.lang.String codiMaquina)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiMaquina == null || codiMaquina.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.Session> com.soffid.iam.am.service.NetworkService.findSessionsByHostName(java.lang.String codiMaquina) - codiMaquina cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindSessionsByHostName(codiMaquina)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.am.api.Session>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.findSessionsByHostName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.findSessionsByHostName", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.am.api.Session> handleFindSessionsByHostName(java.lang.String codiMaquina) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.am.api.NetworkAuthorization> getACL(com.soffid.iam.am.api.Network xarxa)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.am.api.NetworkAuthorization> getACL(
		final com.soffid.iam.am.api.Network xarxa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (xarxa == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.NetworkAuthorization> com.soffid.iam.am.service.NetworkService.getACL(com.soffid.iam.am.api.Network xarxa) - xarxa cannot be null");
		}
		if (xarxa.getName() == null || xarxa.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.NetworkAuthorization> com.soffid.iam.am.service.NetworkService.getACL(com.soffid.iam.am.api.Network xarxa) - xarxa.name cannot be null");
		}
		if (xarxa.getIp() == null || xarxa.getIp().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.NetworkAuthorization> com.soffid.iam.am.service.NetworkService.getACL(com.soffid.iam.am.api.Network xarxa) - xarxa.ip cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetACL(xarxa)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.am.api.NetworkAuthorization>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.getACL", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.getACL", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.am.api.NetworkAuthorization> handleGetACL(com.soffid.iam.am.api.Network xarxa) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.am.api.Network> getNetworks()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.am.api.Network> getNetworks()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetNetworks()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.am.api.Network>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.getNetworks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.getNetworks", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.am.api.Network> handleGetNetworks() throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#void delete(com.soffid.iam.am.api.Host maquina)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.am.api.Host maquina)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (maquina == null) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.delete(com.soffid.iam.am.api.Host maquina) - maquina cannot be null");
		}
		if (maquina.getName() == null || maquina.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.delete(com.soffid.iam.am.api.Host maquina) - maquina.name cannot be null");
		}
		if (maquina.getNetwork() == null || maquina.getNetwork().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.delete(com.soffid.iam.am.api.Host maquina) - maquina.network cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(maquina);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.am.api.Host maquina) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#void delete(com.soffid.iam.am.api.HostAlias aliasMaquina)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.am.api.HostAlias aliasMaquina)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (aliasMaquina == null) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.delete(com.soffid.iam.am.api.HostAlias aliasMaquina) - aliasMaquina cannot be null");
		}
		if (aliasMaquina.getAlias() == null || aliasMaquina.getAlias().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.delete(com.soffid.iam.am.api.HostAlias aliasMaquina) - aliasMaquina.alias cannot be null");
		}
		if (aliasMaquina.getHostName() == null || aliasMaquina.getHostName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.delete(com.soffid.iam.am.api.HostAlias aliasMaquina) - aliasMaquina.hostName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(aliasMaquina);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.am.api.HostAlias aliasMaquina) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#void delete(com.soffid.iam.am.api.Network xarxa)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.am.api.Network xarxa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (xarxa == null) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.delete(com.soffid.iam.am.api.Network xarxa) - xarxa cannot be null");
		}
		if (xarxa.getName() == null || xarxa.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.delete(com.soffid.iam.am.api.Network xarxa) - xarxa.name cannot be null");
		}
		if (xarxa.getIp() == null || xarxa.getIp().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.delete(com.soffid.iam.am.api.Network xarxa) - xarxa.ip cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(xarxa);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.am.api.Network xarxa) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#void delete(com.soffid.iam.am.api.NetworkAuthorization accessList)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.am.api.NetworkAuthorization accessList)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (accessList == null) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.delete(com.soffid.iam.am.api.NetworkAuthorization accessList) - accessList cannot be null");
		}
		if (accessList.getIdentity() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.delete(com.soffid.iam.am.api.NetworkAuthorization accessList) - accessList.identity cannot be null");
		}
		if (accessList.getLevel() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.delete(com.soffid.iam.am.api.NetworkAuthorization accessList) - accessList.level cannot be null");
		}
		if (accessList.getNetworkCode() == null || accessList.getNetworkCode().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.delete(com.soffid.iam.am.api.NetworkAuthorization accessList) - accessList.networkCode cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(accessList);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.am.api.NetworkAuthorization accessList) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#void delete(com.soffid.iam.am.api.OsType osType)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.am.api.OsType osType)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (osType == null) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.delete(com.soffid.iam.am.api.OsType osType) - osType cannot be null");
		}
		if (osType.getName() == null || osType.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.delete(com.soffid.iam.am.api.OsType osType) - osType.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(osType);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.am.api.OsType osType) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#void setAdministratorPassword(java.lang.String nomMaquina, java.lang.String adminUser, java.lang.String adminPass)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void setAdministratorPassword(
		final java.lang.String nomMaquina, 
		final java.lang.String adminUser, 
		final java.lang.String adminPass)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomMaquina == null || nomMaquina.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.setAdministratorPassword(java.lang.String nomMaquina, java.lang.String adminUser, java.lang.String adminPass) - nomMaquina cannot be null");
		}
		if (adminUser == null || adminUser.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.setAdministratorPassword(java.lang.String nomMaquina, java.lang.String adminUser, java.lang.String adminPass) - adminUser cannot be null");
		}
		if (adminPass == null || adminPass.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.setAdministratorPassword(java.lang.String nomMaquina, java.lang.String adminUser, java.lang.String adminPass) - adminPass cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSetAdministratorPassword(nomMaquina, adminUser, adminPass);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.setAdministratorPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.setAdministratorPassword", (Throwable) __r[1]);
	}

	protected abstract void handleSetAdministratorPassword(java.lang.String nomMaquina, java.lang.String adminUser, java.lang.String adminPass) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#void update(com.soffid.iam.am.api.Host maquina)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void update(
		final com.soffid.iam.am.api.Host maquina)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (maquina == null) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.update(com.soffid.iam.am.api.Host maquina) - maquina cannot be null");
		}
		if (maquina.getName() == null || maquina.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.update(com.soffid.iam.am.api.Host maquina) - maquina.name cannot be null");
		}
		if (maquina.getNetwork() == null || maquina.getNetwork().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.update(com.soffid.iam.am.api.Host maquina) - maquina.network cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpdate(maquina);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.update", (Throwable) __r[1]);
	}

	protected abstract void handleUpdate(com.soffid.iam.am.api.Host maquina) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#void update(com.soffid.iam.am.api.HostAlias aliasMaquina)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void update(
		final com.soffid.iam.am.api.HostAlias aliasMaquina)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (aliasMaquina == null) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.update(com.soffid.iam.am.api.HostAlias aliasMaquina) - aliasMaquina cannot be null");
		}
		if (aliasMaquina.getAlias() == null || aliasMaquina.getAlias().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.update(com.soffid.iam.am.api.HostAlias aliasMaquina) - aliasMaquina.alias cannot be null");
		}
		if (aliasMaquina.getHostName() == null || aliasMaquina.getHostName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.update(com.soffid.iam.am.api.HostAlias aliasMaquina) - aliasMaquina.hostName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpdate(aliasMaquina);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.update", (Throwable) __r[1]);
	}

	protected abstract void handleUpdate(com.soffid.iam.am.api.HostAlias aliasMaquina) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#void update(com.soffid.iam.am.api.Network xarxa)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void update(
		final com.soffid.iam.am.api.Network xarxa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (xarxa == null) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.update(com.soffid.iam.am.api.Network xarxa) - xarxa cannot be null");
		}
		if (xarxa.getName() == null || xarxa.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.update(com.soffid.iam.am.api.Network xarxa) - xarxa.name cannot be null");
		}
		if (xarxa.getIp() == null || xarxa.getIp().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.update(com.soffid.iam.am.api.Network xarxa) - xarxa.ip cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpdate(xarxa);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.update", (Throwable) __r[1]);
	}

	protected abstract void handleUpdate(com.soffid.iam.am.api.Network xarxa) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#	 * @see com.soffid.iam.am.service.NetworkService#void update(com.soffid.iam.am.api.OsType osType)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void update(
		final com.soffid.iam.am.api.OsType osType)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (osType == null) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.update(com.soffid.iam.am.api.OsType osType) - osType cannot be null");
		}
		if (osType.getName() == null || osType.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.NetworkService.update(com.soffid.iam.am.api.OsType osType) - osType.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpdate(osType);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.NetworkService.class).
			warn ("Error on NetworkService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkService.update", (Throwable) __r[1]);
	}

	protected abstract void handleUpdate(com.soffid.iam.am.api.OsType osType) throws Exception;

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
