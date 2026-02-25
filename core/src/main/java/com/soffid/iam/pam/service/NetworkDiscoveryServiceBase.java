//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.pam.service.NetworkDiscoveryService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.pam.service.NetworkDiscoveryService
 */
public abstract class NetworkDiscoveryServiceBase
	implements com.soffid.iam.pam.service.NetworkDiscoveryService
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

	private com.soffid.iam.pam.model.HostPortEntityDao hostPortEntityDao;

	/**
	 * Sets reference to <code>hostPortEntityDao</code>.
	 */
	public void setHostPortEntityDao (com.soffid.iam.pam.model.HostPortEntityDao hostPortEntityDao) {
		this.hostPortEntityDao = hostPortEntityDao;
	}

	/**
	 * Gets reference to <code>hostPortEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.HostPortEntityDao getHostPortEntityDao () {
		return hostPortEntityDao;
	}

	private com.soffid.iam.pam.model.HostServiceEntityDao hostServiceEntityDao;

	/**
	 * Sets reference to <code>hostServiceEntityDao</code>.
	 */
	public void setHostServiceEntityDao (com.soffid.iam.pam.model.HostServiceEntityDao hostServiceEntityDao) {
		this.hostServiceEntityDao = hostServiceEntityDao;
	}

	/**
	 * Gets reference to <code>hostServiceEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.HostServiceEntityDao getHostServiceEntityDao () {
		return hostServiceEntityDao;
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

	private com.soffid.iam.pam.model.NetworkDiscoveryAccountEntityDao networkDiscoveryAccountEntityDao;

	/**
	 * Sets reference to <code>networkDiscoveryAccountEntityDao</code>.
	 */
	public void setNetworkDiscoveryAccountEntityDao (com.soffid.iam.pam.model.NetworkDiscoveryAccountEntityDao networkDiscoveryAccountEntityDao) {
		this.networkDiscoveryAccountEntityDao = networkDiscoveryAccountEntityDao;
	}

	/**
	 * Gets reference to <code>networkDiscoveryAccountEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.NetworkDiscoveryAccountEntityDao getNetworkDiscoveryAccountEntityDao () {
		return networkDiscoveryAccountEntityDao;
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

	private com.soffid.iam.sync.service.ScheduledTaskService scheduledTaskService;

	/**
	 * Sets reference to <code>scheduledTaskService</code>.
	 */
	public void setScheduledTaskService (com.soffid.iam.sync.service.ScheduledTaskService scheduledTaskService) {
		this.scheduledTaskService = scheduledTaskService;
	}

	/**
	 * Gets reference to <code>scheduledTaskService</code>.
	 */
	public com.soffid.iam.sync.service.ScheduledTaskService getScheduledTaskService () {
		return scheduledTaskService;
	}

	private com.soffid.iam.sync.service.SyncStatusService syncStatusService;

	/**
	 * Sets reference to <code>syncStatusService</code>.
	 */
	public void setSyncStatusService (com.soffid.iam.sync.service.SyncStatusService syncStatusService) {
		this.syncStatusService = syncStatusService;
	}

	/**
	 * Gets reference to <code>syncStatusService</code>.
	 */
	public com.soffid.iam.sync.service.SyncStatusService getSyncStatusService () {
		return syncStatusService;
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

	private com.soffid.iam.iga.service.UserDomainService userDomainService;

	/**
	 * Sets reference to <code>userDomainService</code>.
	 */
	public void setUserDomainService (com.soffid.iam.iga.service.UserDomainService userDomainService) {
		this.userDomainService = userDomainService;
	}

	/**
	 * Gets reference to <code>userDomainService</code>.
	 */
	public com.soffid.iam.iga.service.UserDomainService getUserDomainService () {
		return userDomainService;
	}


	/**
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#com.soffid.iam.iga.api.System createSystemCandidate(com.soffid.iam.am.api.Host host, java.lang.String type, java.lang.String userName, com.soffid.iam.am.api.Password password, java.lang.String instance)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.System createSystemCandidate(
		final com.soffid.iam.am.api.Host host, 
		final java.lang.String type, 
		final java.lang.String userName, 
		final com.soffid.iam.am.api.Password password, 
		final java.lang.String instance)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (host == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.System com.soffid.iam.pam.service.NetworkDiscoveryService.createSystemCandidate(com.soffid.iam.am.api.Host host, java.lang.String type, java.lang.String userName, com.soffid.iam.am.api.Password password, java.lang.String instance) - host cannot be null");
		}
		if (host.getName() == null || host.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.System com.soffid.iam.pam.service.NetworkDiscoveryService.createSystemCandidate(com.soffid.iam.am.api.Host host, java.lang.String type, java.lang.String userName, com.soffid.iam.am.api.Password password, java.lang.String instance) - host.name cannot be null");
		}
		if (host.getNetwork() == null || host.getNetwork().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.System com.soffid.iam.pam.service.NetworkDiscoveryService.createSystemCandidate(com.soffid.iam.am.api.Host host, java.lang.String type, java.lang.String userName, com.soffid.iam.am.api.Password password, java.lang.String instance) - host.network cannot be null");
		}
		if (type == null || type.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.System com.soffid.iam.pam.service.NetworkDiscoveryService.createSystemCandidate(com.soffid.iam.am.api.Host host, java.lang.String type, java.lang.String userName, com.soffid.iam.am.api.Password password, java.lang.String instance) - type cannot be null");
		}
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.System com.soffid.iam.pam.service.NetworkDiscoveryService.createSystemCandidate(com.soffid.iam.am.api.Host host, java.lang.String type, java.lang.String userName, com.soffid.iam.am.api.Password password, java.lang.String instance) - userName cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.System com.soffid.iam.pam.service.NetworkDiscoveryService.createSystemCandidate(com.soffid.iam.am.api.Host host, java.lang.String type, java.lang.String userName, com.soffid.iam.am.api.Password password, java.lang.String instance) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreateSystemCandidate(host, type, userName, password, instance)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.System) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.NetworkDiscoveryService.class).
			warn ("Error on NetworkDiscoveryService.createSystemCandidate", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkDiscoveryService.createSystemCandidate", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.System handleCreateSystemCandidate(com.soffid.iam.am.api.Host host, java.lang.String type, java.lang.String userName, com.soffid.iam.am.api.Password password, java.lang.String instance) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#com.soffid.iam.pam.api.HostService createHostService(com.soffid.iam.pam.api.HostService service)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.pam.api.HostService createHostService(
		final com.soffid.iam.pam.api.HostService service)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (service == null) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.HostService com.soffid.iam.pam.service.NetworkDiscoveryService.createHostService(com.soffid.iam.pam.api.HostService service) - service cannot be null");
		}
		if (service.getHostId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.HostService com.soffid.iam.pam.service.NetworkDiscoveryService.createHostService(com.soffid.iam.pam.api.HostService service) - service.hostId cannot be null");
		}
		if (service.getHostName() == null || service.getHostName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.HostService com.soffid.iam.pam.service.NetworkDiscoveryService.createHostService(com.soffid.iam.pam.api.HostService service) - service.hostName cannot be null");
		}
		if (service.getService() == null || service.getService().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.HostService com.soffid.iam.pam.service.NetworkDiscoveryService.createHostService(com.soffid.iam.pam.api.HostService service) - service.service cannot be null");
		}
		if (service.getAccountId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.HostService com.soffid.iam.pam.service.NetworkDiscoveryService.createHostService(com.soffid.iam.pam.api.HostService service) - service.accountId cannot be null");
		}
		if (service.getAccountName() == null || service.getAccountName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.HostService com.soffid.iam.pam.service.NetworkDiscoveryService.createHostService(com.soffid.iam.pam.api.HostService service) - service.accountName cannot be null");
		}
		if (service.getAccountSystem() == null || service.getAccountSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.HostService com.soffid.iam.pam.service.NetworkDiscoveryService.createHostService(com.soffid.iam.pam.api.HostService service) - service.accountSystem cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreateHostService(service)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.pam.api.HostService) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.NetworkDiscoveryService.class).
			warn ("Error on NetworkDiscoveryService.createHostService", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkDiscoveryService.createHostService", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.pam.api.HostService handleCreateHostService(com.soffid.iam.pam.api.HostService service) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#com.soffid.iam.pam.api.HostService updateHostService(com.soffid.iam.pam.api.HostService service)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.pam.api.HostService updateHostService(
		final com.soffid.iam.pam.api.HostService service)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (service == null) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.HostService com.soffid.iam.pam.service.NetworkDiscoveryService.updateHostService(com.soffid.iam.pam.api.HostService service) - service cannot be null");
		}
		if (service.getHostId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.HostService com.soffid.iam.pam.service.NetworkDiscoveryService.updateHostService(com.soffid.iam.pam.api.HostService service) - service.hostId cannot be null");
		}
		if (service.getHostName() == null || service.getHostName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.HostService com.soffid.iam.pam.service.NetworkDiscoveryService.updateHostService(com.soffid.iam.pam.api.HostService service) - service.hostName cannot be null");
		}
		if (service.getService() == null || service.getService().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.HostService com.soffid.iam.pam.service.NetworkDiscoveryService.updateHostService(com.soffid.iam.pam.api.HostService service) - service.service cannot be null");
		}
		if (service.getAccountId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.HostService com.soffid.iam.pam.service.NetworkDiscoveryService.updateHostService(com.soffid.iam.pam.api.HostService service) - service.accountId cannot be null");
		}
		if (service.getAccountName() == null || service.getAccountName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.HostService com.soffid.iam.pam.service.NetworkDiscoveryService.updateHostService(com.soffid.iam.pam.api.HostService service) - service.accountName cannot be null");
		}
		if (service.getAccountSystem() == null || service.getAccountSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.HostService com.soffid.iam.pam.service.NetworkDiscoveryService.updateHostService(com.soffid.iam.pam.api.HostService service) - service.accountSystem cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdateHostService(service)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.pam.api.HostService) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.NetworkDiscoveryService.class).
			warn ("Error on NetworkDiscoveryService.updateHostService", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkDiscoveryService.updateHostService", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.pam.api.HostService handleUpdateHostService(com.soffid.iam.pam.api.HostService service) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#com.soffid.iam.sync.api.ScheduledTask findNetworkDiscoveryScheduledTask(com.soffid.iam.am.api.Network network)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.sync.api.ScheduledTask findNetworkDiscoveryScheduledTask(
		final com.soffid.iam.am.api.Network network)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (network == null) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTask com.soffid.iam.pam.service.NetworkDiscoveryService.findNetworkDiscoveryScheduledTask(com.soffid.iam.am.api.Network network) - network cannot be null");
		}
		if (network.getName() == null || network.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTask com.soffid.iam.pam.service.NetworkDiscoveryService.findNetworkDiscoveryScheduledTask(com.soffid.iam.am.api.Network network) - network.name cannot be null");
		}
		if (network.getIp() == null || network.getIp().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTask com.soffid.iam.pam.service.NetworkDiscoveryService.findNetworkDiscoveryScheduledTask(com.soffid.iam.am.api.Network network) - network.ip cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindNetworkDiscoveryScheduledTask(network)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.sync.api.ScheduledTask) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.NetworkDiscoveryService.class).
			warn ("Error on NetworkDiscoveryService.findNetworkDiscoveryScheduledTask", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkDiscoveryService.findNetworkDiscoveryScheduledTask", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.sync.api.ScheduledTask handleFindNetworkDiscoveryScheduledTask(com.soffid.iam.am.api.Network network) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#java.util.List<com.soffid.iam.am.api.AccessTree> findHostEntryPoints(com.soffid.iam.am.api.Host host)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.am.api.AccessTree> findHostEntryPoints(
		final com.soffid.iam.am.api.Host host)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (host == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.AccessTree> com.soffid.iam.pam.service.NetworkDiscoveryService.findHostEntryPoints(com.soffid.iam.am.api.Host host) - host cannot be null");
		}
		if (host.getName() == null || host.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.AccessTree> com.soffid.iam.pam.service.NetworkDiscoveryService.findHostEntryPoints(com.soffid.iam.am.api.Host host) - host.name cannot be null");
		}
		if (host.getNetwork() == null || host.getNetwork().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.AccessTree> com.soffid.iam.pam.service.NetworkDiscoveryService.findHostEntryPoints(com.soffid.iam.am.api.Host host) - host.network cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindHostEntryPoints(host)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.am.api.AccessTree>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.NetworkDiscoveryService.class).
			warn ("Error on NetworkDiscoveryService.findHostEntryPoints", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkDiscoveryService.findHostEntryPoints", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.am.api.AccessTree> handleFindHostEntryPoints(com.soffid.iam.am.api.Host host) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#java.util.List<com.soffid.iam.pam.api.HostPort> findHostPorts(com.soffid.iam.am.api.Host host)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.pam.api.HostPort> findHostPorts(
		final com.soffid.iam.am.api.Host host)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (host == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.pam.api.HostPort> com.soffid.iam.pam.service.NetworkDiscoveryService.findHostPorts(com.soffid.iam.am.api.Host host) - host cannot be null");
		}
		if (host.getName() == null || host.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.pam.api.HostPort> com.soffid.iam.pam.service.NetworkDiscoveryService.findHostPorts(com.soffid.iam.am.api.Host host) - host.name cannot be null");
		}
		if (host.getNetwork() == null || host.getNetwork().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.pam.api.HostPort> com.soffid.iam.pam.service.NetworkDiscoveryService.findHostPorts(com.soffid.iam.am.api.Host host) - host.network cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindHostPorts(host)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.pam.api.HostPort>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.NetworkDiscoveryService.class).
			warn ("Error on NetworkDiscoveryService.findHostPorts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkDiscoveryService.findHostPorts", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.pam.api.HostPort> handleFindHostPorts(com.soffid.iam.am.api.Host host) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#java.util.List<com.soffid.iam.pam.api.HostService> findHostServices(com.soffid.iam.am.api.Host host)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.pam.api.HostService> findHostServices(
		final com.soffid.iam.am.api.Host host)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (host == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.pam.api.HostService> com.soffid.iam.pam.service.NetworkDiscoveryService.findHostServices(com.soffid.iam.am.api.Host host) - host cannot be null");
		}
		if (host.getName() == null || host.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.pam.api.HostService> com.soffid.iam.pam.service.NetworkDiscoveryService.findHostServices(com.soffid.iam.am.api.Host host) - host.name cannot be null");
		}
		if (host.getNetwork() == null || host.getNetwork().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.pam.api.HostService> com.soffid.iam.pam.service.NetworkDiscoveryService.findHostServices(com.soffid.iam.am.api.Host host) - host.network cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindHostServices(host)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.pam.api.HostService>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.NetworkDiscoveryService.class).
			warn ("Error on NetworkDiscoveryService.findHostServices", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkDiscoveryService.findHostServices", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.pam.api.HostService> handleFindHostServices(com.soffid.iam.am.api.Host host) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#java.util.List<com.soffid.iam.iga.api.System> findHostSystems(com.soffid.iam.am.api.Host host)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.iga.api.System> findHostSystems(
		final com.soffid.iam.am.api.Host host)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (host == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.iga.api.System> com.soffid.iam.pam.service.NetworkDiscoveryService.findHostSystems(com.soffid.iam.am.api.Host host) - host cannot be null");
		}
		if (host.getName() == null || host.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.iga.api.System> com.soffid.iam.pam.service.NetworkDiscoveryService.findHostSystems(com.soffid.iam.am.api.Host host) - host.name cannot be null");
		}
		if (host.getNetwork() == null || host.getNetwork().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.iga.api.System> com.soffid.iam.pam.service.NetworkDiscoveryService.findHostSystems(com.soffid.iam.am.api.Host host) - host.network cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindHostSystems(host)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.iga.api.System>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.NetworkDiscoveryService.class).
			warn ("Error on NetworkDiscoveryService.findHostSystems", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkDiscoveryService.findHostSystems", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.iga.api.System> handleFindHostSystems(com.soffid.iam.am.api.Host host) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#java.util.List<com.soffid.iam.base.api.Account> findNetworkAccount(com.soffid.iam.am.api.Network network)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.base.api.Account> findNetworkAccount(
		final com.soffid.iam.am.api.Network network)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (network == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.Account> com.soffid.iam.pam.service.NetworkDiscoveryService.findNetworkAccount(com.soffid.iam.am.api.Network network) - network cannot be null");
		}
		if (network.getName() == null || network.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.Account> com.soffid.iam.pam.service.NetworkDiscoveryService.findNetworkAccount(com.soffid.iam.am.api.Network network) - network.name cannot be null");
		}
		if (network.getIp() == null || network.getIp().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.Account> com.soffid.iam.pam.service.NetworkDiscoveryService.findNetworkAccount(com.soffid.iam.am.api.Network network) - network.ip cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindNetworkAccount(network)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.base.api.Account>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.NetworkDiscoveryService.class).
			warn ("Error on NetworkDiscoveryService.findNetworkAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkDiscoveryService.findNetworkAccount", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.base.api.Account> handleFindNetworkAccount(com.soffid.iam.am.api.Network network) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#java.util.List<com.soffid.iam.am.api.Host> findSystemHosts(com.soffid.iam.iga.api.System system)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.am.api.Host> findSystemHosts(
		final com.soffid.iam.iga.api.System system)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (system == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.Host> com.soffid.iam.pam.service.NetworkDiscoveryService.findSystemHosts(com.soffid.iam.iga.api.System system) - system cannot be null");
		}
		if (system.getName() == null || system.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.Host> com.soffid.iam.pam.service.NetworkDiscoveryService.findSystemHosts(com.soffid.iam.iga.api.System system) - system.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindSystemHosts(system)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.am.api.Host>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.NetworkDiscoveryService.class).
			warn ("Error on NetworkDiscoveryService.findSystemHosts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkDiscoveryService.findSystemHosts", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.am.api.Host> handleFindSystemHosts(com.soffid.iam.iga.api.System system) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#void createNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void createNetworkAccount(
		final com.soffid.iam.am.api.Network network, 
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (network == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.createNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account) - network cannot be null");
		}
		if (network.getName() == null || network.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.createNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account) - network.name cannot be null");
		}
		if (network.getIp() == null || network.getIp().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.createNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account) - network.ip cannot be null");
		}
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.createNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.createNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.createNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.createNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.createNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.createNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleCreateNetworkAccount(network, account);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.NetworkDiscoveryService.class).
			warn ("Error on NetworkDiscoveryService.createNetworkAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkDiscoveryService.createNetworkAccount", (Throwable) __r[1]);
	}

	protected abstract void handleCreateNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#void deleteHostService(com.soffid.iam.pam.api.HostService service)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void deleteHostService(
		final com.soffid.iam.pam.api.HostService service)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (service == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.deleteHostService(com.soffid.iam.pam.api.HostService service) - service cannot be null");
		}
		if (service.getHostId() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.deleteHostService(com.soffid.iam.pam.api.HostService service) - service.hostId cannot be null");
		}
		if (service.getHostName() == null || service.getHostName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.deleteHostService(com.soffid.iam.pam.api.HostService service) - service.hostName cannot be null");
		}
		if (service.getService() == null || service.getService().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.deleteHostService(com.soffid.iam.pam.api.HostService service) - service.service cannot be null");
		}
		if (service.getAccountId() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.deleteHostService(com.soffid.iam.pam.api.HostService service) - service.accountId cannot be null");
		}
		if (service.getAccountName() == null || service.getAccountName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.deleteHostService(com.soffid.iam.pam.api.HostService service) - service.accountName cannot be null");
		}
		if (service.getAccountSystem() == null || service.getAccountSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.deleteHostService(com.soffid.iam.pam.api.HostService service) - service.accountSystem cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDeleteHostService(service);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.NetworkDiscoveryService.class).
			warn ("Error on NetworkDiscoveryService.deleteHostService", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkDiscoveryService.deleteHostService", (Throwable) __r[1]);
	}

	protected abstract void handleDeleteHostService(com.soffid.iam.pam.api.HostService service) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#void disconnectSystemFromHost(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System system)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void disconnectSystemFromHost(
		final com.soffid.iam.am.api.Host host, 
		final com.soffid.iam.iga.api.System system)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (host == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.disconnectSystemFromHost(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System system) - host cannot be null");
		}
		if (host.getName() == null || host.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.disconnectSystemFromHost(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System system) - host.name cannot be null");
		}
		if (host.getNetwork() == null || host.getNetwork().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.disconnectSystemFromHost(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System system) - host.network cannot be null");
		}
		if (system == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.disconnectSystemFromHost(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System system) - system cannot be null");
		}
		if (system.getName() == null || system.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.disconnectSystemFromHost(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System system) - system.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDisconnectSystemFromHost(host, system);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.NetworkDiscoveryService.class).
			warn ("Error on NetworkDiscoveryService.disconnectSystemFromHost", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkDiscoveryService.disconnectSystemFromHost", (Throwable) __r[1]);
	}

	protected abstract void handleDisconnectSystemFromHost(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System system) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#void enableNetworkDiscoveryScheduledTask(com.soffid.iam.am.api.Network network)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void enableNetworkDiscoveryScheduledTask(
		final com.soffid.iam.am.api.Network network)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (network == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.enableNetworkDiscoveryScheduledTask(com.soffid.iam.am.api.Network network) - network cannot be null");
		}
		if (network.getName() == null || network.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.enableNetworkDiscoveryScheduledTask(com.soffid.iam.am.api.Network network) - network.name cannot be null");
		}
		if (network.getIp() == null || network.getIp().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.enableNetworkDiscoveryScheduledTask(com.soffid.iam.am.api.Network network) - network.ip cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleEnableNetworkDiscoveryScheduledTask(network);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.NetworkDiscoveryService.class).
			warn ("Error on NetworkDiscoveryService.enableNetworkDiscoveryScheduledTask", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkDiscoveryService.enableNetworkDiscoveryScheduledTask", (Throwable) __r[1]);
	}

	protected abstract void handleEnableNetworkDiscoveryScheduledTask(com.soffid.iam.am.api.Network network) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#void registerHostEntryPoint(com.soffid.iam.am.api.Host host, com.soffid.iam.am.api.AccessTree entryPoint)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void registerHostEntryPoint(
		final com.soffid.iam.am.api.Host host, 
		final com.soffid.iam.am.api.AccessTree entryPoint)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (host == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.registerHostEntryPoint(com.soffid.iam.am.api.Host host, com.soffid.iam.am.api.AccessTree entryPoint) - host cannot be null");
		}
		if (host.getName() == null || host.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.registerHostEntryPoint(com.soffid.iam.am.api.Host host, com.soffid.iam.am.api.AccessTree entryPoint) - host.name cannot be null");
		}
		if (host.getNetwork() == null || host.getNetwork().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.registerHostEntryPoint(com.soffid.iam.am.api.Host host, com.soffid.iam.am.api.AccessTree entryPoint) - host.network cannot be null");
		}
		if (entryPoint == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.registerHostEntryPoint(com.soffid.iam.am.api.Host host, com.soffid.iam.am.api.AccessTree entryPoint) - entryPoint cannot be null");
		}
		if (entryPoint.getName() == null || entryPoint.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.registerHostEntryPoint(com.soffid.iam.am.api.Host host, com.soffid.iam.am.api.AccessTree entryPoint) - entryPoint.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRegisterHostEntryPoint(host, entryPoint);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.NetworkDiscoveryService.class).
			warn ("Error on NetworkDiscoveryService.registerHostEntryPoint", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkDiscoveryService.registerHostEntryPoint", (Throwable) __r[1]);
	}

	protected abstract void handleRegisterHostEntryPoint(com.soffid.iam.am.api.Host host, com.soffid.iam.am.api.AccessTree entryPoint) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#void registerHostPorts(com.soffid.iam.am.api.Host host, java.util.List<com.soffid.iam.pam.api.HostPort> ports)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void registerHostPorts(
		final com.soffid.iam.am.api.Host host, 
		final java.util.List<com.soffid.iam.pam.api.HostPort> ports)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (host == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.registerHostPorts(com.soffid.iam.am.api.Host host, java.util.List<com.soffid.iam.pam.api.HostPort> ports) - host cannot be null");
		}
		if (host.getName() == null || host.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.registerHostPorts(com.soffid.iam.am.api.Host host, java.util.List<com.soffid.iam.pam.api.HostPort> ports) - host.name cannot be null");
		}
		if (host.getNetwork() == null || host.getNetwork().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.registerHostPorts(com.soffid.iam.am.api.Host host, java.util.List<com.soffid.iam.pam.api.HostPort> ports) - host.network cannot be null");
		}
		if (ports == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.registerHostPorts(com.soffid.iam.am.api.Host host, java.util.List<com.soffid.iam.pam.api.HostPort> ports) - ports cannot be empty");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRegisterHostPorts(host, ports);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.NetworkDiscoveryService.class).
			warn ("Error on NetworkDiscoveryService.registerHostPorts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkDiscoveryService.registerHostPorts", (Throwable) __r[1]);
	}

	protected abstract void handleRegisterHostPorts(com.soffid.iam.am.api.Host host, java.util.List<com.soffid.iam.pam.api.HostPort> ports) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#void registerHostServices(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System dispatcher, java.util.List<com.soffid.iam.pam.api.HostService> services, java.util.Map<java.lang.String,java.lang.String> domainToSystemMap)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void registerHostServices(
		final com.soffid.iam.am.api.Host host, 
		final com.soffid.iam.iga.api.System dispatcher, 
		final java.util.List<com.soffid.iam.pam.api.HostService> services, 
		final java.util.Map<java.lang.String,java.lang.String> domainToSystemMap)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (host == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.registerHostServices(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System dispatcher, java.util.List<com.soffid.iam.pam.api.HostService> services, java.util.Map<java.lang.String,java.lang.String> domainToSystemMap) - host cannot be null");
		}
		if (host.getName() == null || host.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.registerHostServices(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System dispatcher, java.util.List<com.soffid.iam.pam.api.HostService> services, java.util.Map<java.lang.String,java.lang.String> domainToSystemMap) - host.name cannot be null");
		}
		if (host.getNetwork() == null || host.getNetwork().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.registerHostServices(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System dispatcher, java.util.List<com.soffid.iam.pam.api.HostService> services, java.util.Map<java.lang.String,java.lang.String> domainToSystemMap) - host.network cannot be null");
		}
		if (dispatcher == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.registerHostServices(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System dispatcher, java.util.List<com.soffid.iam.pam.api.HostService> services, java.util.Map<java.lang.String,java.lang.String> domainToSystemMap) - dispatcher cannot be null");
		}
		if (dispatcher.getName() == null || dispatcher.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.registerHostServices(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System dispatcher, java.util.List<com.soffid.iam.pam.api.HostService> services, java.util.Map<java.lang.String,java.lang.String> domainToSystemMap) - dispatcher.name cannot be null");
		}
		if (services == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.registerHostServices(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System dispatcher, java.util.List<com.soffid.iam.pam.api.HostService> services, java.util.Map<java.lang.String,java.lang.String> domainToSystemMap) - services cannot be empty");
		}
		if (domainToSystemMap == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.registerHostServices(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System dispatcher, java.util.List<com.soffid.iam.pam.api.HostService> services, java.util.Map<java.lang.String,java.lang.String> domainToSystemMap) - domainToSystemMap cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRegisterHostServices(host, dispatcher, services, domainToSystemMap);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.NetworkDiscoveryService.class).
			warn ("Error on NetworkDiscoveryService.registerHostServices", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkDiscoveryService.registerHostServices", (Throwable) __r[1]);
	}

	protected abstract void handleRegisterHostServices(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System dispatcher, java.util.List<com.soffid.iam.pam.api.HostService> services, java.util.Map<java.lang.String,java.lang.String> domainToSystemMap) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#void registerHostSystem(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System system)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void registerHostSystem(
		final com.soffid.iam.am.api.Host host, 
		final com.soffid.iam.iga.api.System system)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (host == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.registerHostSystem(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System system) - host cannot be null");
		}
		if (host.getName() == null || host.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.registerHostSystem(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System system) - host.name cannot be null");
		}
		if (host.getNetwork() == null || host.getNetwork().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.registerHostSystem(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System system) - host.network cannot be null");
		}
		if (system == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.registerHostSystem(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System system) - system cannot be null");
		}
		if (system.getName() == null || system.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.registerHostSystem(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System system) - system.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRegisterHostSystem(host, system);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.NetworkDiscoveryService.class).
			warn ("Error on NetworkDiscoveryService.registerHostSystem", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkDiscoveryService.registerHostSystem", (Throwable) __r[1]);
	}

	protected abstract void handleRegisterHostSystem(com.soffid.iam.am.api.Host host, com.soffid.iam.iga.api.System system) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#void removeNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void removeNetworkAccount(
		final com.soffid.iam.am.api.Network network, 
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (network == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.removeNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account) - network cannot be null");
		}
		if (network.getName() == null || network.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.removeNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account) - network.name cannot be null");
		}
		if (network.getIp() == null || network.getIp().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.removeNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account) - network.ip cannot be null");
		}
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.removeNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.removeNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.removeNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.removeNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.removeNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.removeNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRemoveNetworkAccount(network, account);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.NetworkDiscoveryService.class).
			warn ("Error on NetworkDiscoveryService.removeNetworkAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkDiscoveryService.removeNetworkAccount", (Throwable) __r[1]);
	}

	protected abstract void handleRemoveNetworkAccount(com.soffid.iam.am.api.Network network, com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#	 * @see com.soffid.iam.pam.service.NetworkDiscoveryService#void startDiscovery(com.soffid.iam.am.api.Network network)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void startDiscovery(
		final com.soffid.iam.am.api.Network network)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (network == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.startDiscovery(com.soffid.iam.am.api.Network network) - network cannot be null");
		}
		if (network.getName() == null || network.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.startDiscovery(com.soffid.iam.am.api.Network network) - network.name cannot be null");
		}
		if (network.getIp() == null || network.getIp().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.service.NetworkDiscoveryService.startDiscovery(com.soffid.iam.am.api.Network network) - network.ip cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleStartDiscovery(network);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.service.NetworkDiscoveryService.class).
			warn ("Error on NetworkDiscoveryService.startDiscovery", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkDiscoveryService.startDiscovery", (Throwable) __r[1]);
	}

	protected abstract void handleStartDiscovery(com.soffid.iam.am.api.Network network) throws Exception;

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
