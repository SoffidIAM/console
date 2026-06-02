//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.am.service.EntryPointService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.am.service.EntryPointService
 */
public abstract class EntryPointServiceBase
	implements com.soffid.iam.am.service.EntryPointService
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

	private com.soffid.iam.base.service.ConfigurationService configurationService;

	/**
	 * Sets reference to <code>configurationService</code>.
	 */
	public void setConfigurationService (com.soffid.iam.base.service.ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	/**
	 * Gets reference to <code>configurationService</code>.
	 */
	public com.soffid.iam.base.service.ConfigurationService getConfigurationService () {
		return configurationService;
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

	private com.soffid.iam.am.model.EntryPointAccountEntityDao entryPointAccountEntityDao;

	/**
	 * Sets reference to <code>entryPointAccountEntityDao</code>.
	 */
	public void setEntryPointAccountEntityDao (com.soffid.iam.am.model.EntryPointAccountEntityDao entryPointAccountEntityDao) {
		this.entryPointAccountEntityDao = entryPointAccountEntityDao;
	}

	/**
	 * Gets reference to <code>entryPointAccountEntityDao</code>.
	 */
	public com.soffid.iam.am.model.EntryPointAccountEntityDao getEntryPointAccountEntityDao () {
		return entryPointAccountEntityDao;
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

	private com.soffid.iam.am.model.EntryPointExecutableEntityDao entryPointExecutableEntityDao;

	/**
	 * Sets reference to <code>entryPointExecutableEntityDao</code>.
	 */
	public void setEntryPointExecutableEntityDao (com.soffid.iam.am.model.EntryPointExecutableEntityDao entryPointExecutableEntityDao) {
		this.entryPointExecutableEntityDao = entryPointExecutableEntityDao;
	}

	/**
	 * Gets reference to <code>entryPointExecutableEntityDao</code>.
	 */
	public com.soffid.iam.am.model.EntryPointExecutableEntityDao getEntryPointExecutableEntityDao () {
		return entryPointExecutableEntityDao;
	}

	private com.soffid.iam.am.model.EntryPointExecutionTypeEntityDao entryPointExecutionTypeEntityDao;

	/**
	 * Sets reference to <code>entryPointExecutionTypeEntityDao</code>.
	 */
	public void setEntryPointExecutionTypeEntityDao (com.soffid.iam.am.model.EntryPointExecutionTypeEntityDao entryPointExecutionTypeEntityDao) {
		this.entryPointExecutionTypeEntityDao = entryPointExecutionTypeEntityDao;
	}

	/**
	 * Gets reference to <code>entryPointExecutionTypeEntityDao</code>.
	 */
	public com.soffid.iam.am.model.EntryPointExecutionTypeEntityDao getEntryPointExecutionTypeEntityDao () {
		return entryPointExecutionTypeEntityDao;
	}

	private com.soffid.iam.am.model.EntryPointGroupEntityDao entryPointGroupEntityDao;

	/**
	 * Sets reference to <code>entryPointGroupEntityDao</code>.
	 */
	public void setEntryPointGroupEntityDao (com.soffid.iam.am.model.EntryPointGroupEntityDao entryPointGroupEntityDao) {
		this.entryPointGroupEntityDao = entryPointGroupEntityDao;
	}

	/**
	 * Gets reference to <code>entryPointGroupEntityDao</code>.
	 */
	public com.soffid.iam.am.model.EntryPointGroupEntityDao getEntryPointGroupEntityDao () {
		return entryPointGroupEntityDao;
	}

	private com.soffid.iam.am.model.EntryPointIconEntityDao entryPointIconEntityDao;

	/**
	 * Sets reference to <code>entryPointIconEntityDao</code>.
	 */
	public void setEntryPointIconEntityDao (com.soffid.iam.am.model.EntryPointIconEntityDao entryPointIconEntityDao) {
		this.entryPointIconEntityDao = entryPointIconEntityDao;
	}

	/**
	 * Gets reference to <code>entryPointIconEntityDao</code>.
	 */
	public com.soffid.iam.am.model.EntryPointIconEntityDao getEntryPointIconEntityDao () {
		return entryPointIconEntityDao;
	}

	private com.soffid.iam.am.model.EntryPointRoleEntityDao entryPointRoleEntityDao;

	/**
	 * Sets reference to <code>entryPointRoleEntityDao</code>.
	 */
	public void setEntryPointRoleEntityDao (com.soffid.iam.am.model.EntryPointRoleEntityDao entryPointRoleEntityDao) {
		this.entryPointRoleEntityDao = entryPointRoleEntityDao;
	}

	/**
	 * Gets reference to <code>entryPointRoleEntityDao</code>.
	 */
	public com.soffid.iam.am.model.EntryPointRoleEntityDao getEntryPointRoleEntityDao () {
		return entryPointRoleEntityDao;
	}

	private com.soffid.iam.am.model.EntryPointTreeEntityDao entryPointTreeEntityDao;

	/**
	 * Sets reference to <code>entryPointTreeEntityDao</code>.
	 */
	public void setEntryPointTreeEntityDao (com.soffid.iam.am.model.EntryPointTreeEntityDao entryPointTreeEntityDao) {
		this.entryPointTreeEntityDao = entryPointTreeEntityDao;
	}

	/**
	 * Gets reference to <code>entryPointTreeEntityDao</code>.
	 */
	public com.soffid.iam.am.model.EntryPointTreeEntityDao getEntryPointTreeEntityDao () {
		return entryPointTreeEntityDao;
	}

	private com.soffid.iam.am.model.EntryPointUserEntityDao entryPointUserEntityDao;

	/**
	 * Sets reference to <code>entryPointUserEntityDao</code>.
	 */
	public void setEntryPointUserEntityDao (com.soffid.iam.am.model.EntryPointUserEntityDao entryPointUserEntityDao) {
		this.entryPointUserEntityDao = entryPointUserEntityDao;
	}

	/**
	 * Gets reference to <code>entryPointUserEntityDao</code>.
	 */
	public com.soffid.iam.am.model.EntryPointUserEntityDao getEntryPointUserEntityDao () {
		return entryPointUserEntityDao;
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

	private com.soffid.iam.iga.model.InformationSystemEntityDao informationSystemEntityDao;

	/**
	 * Sets reference to <code>informationSystemEntityDao</code>.
	 */
	public void setInformationSystemEntityDao (com.soffid.iam.iga.model.InformationSystemEntityDao informationSystemEntityDao) {
		this.informationSystemEntityDao = informationSystemEntityDao;
	}

	/**
	 * Gets reference to <code>informationSystemEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.InformationSystemEntityDao getInformationSystemEntityDao () {
		return informationSystemEntityDao;
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
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#boolean canAdmin(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean canAdmin(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntrada == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.canAdmin(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada cannot be null");
		}
		if (puntEntrada.getName() == null || puntEntrada.getName().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.canAdmin(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCanAdmin(puntEntrada)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.canAdmin", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.canAdmin", (Throwable) __r[1]);
	}

	protected abstract boolean handleCanAdmin(com.soffid.iam.am.api.AccessTree puntEntrada) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#boolean canExecute(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean canExecute(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntrada == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.canExecute(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada cannot be null");
		}
		if (puntEntrada.getName() == null || puntEntrada.getName().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.canExecute(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCanExecute(puntEntrada)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.canExecute", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.canExecute", (Throwable) __r[1]);
	}

	protected abstract boolean handleCanExecute(com.soffid.iam.am.api.AccessTree puntEntrada) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#boolean canQuery(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean canQuery(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntrada == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.canQuery(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada cannot be null");
		}
		if (puntEntrada.getName() == null || puntEntrada.getName().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.canQuery(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCanQuery(puntEntrada)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.canQuery", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.canQuery", (Throwable) __r[1]);
	}

	protected abstract boolean handleCanQuery(com.soffid.iam.am.api.AccessTree puntEntrada) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#boolean canView(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean canView(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntrada == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.canView(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada cannot be null");
		}
		if (puntEntrada.getName() == null || puntEntrada.getName().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.canView(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCanView(puntEntrada)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.canView", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.canView", (Throwable) __r[1]);
	}

	protected abstract boolean handleCanView(com.soffid.iam.am.api.AccessTree puntEntrada) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#boolean copyApplicationAccessLink(com.soffid.iam.am.api.AccessTree puntEntradaCopiar, com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean copyApplicationAccessLink(
		final com.soffid.iam.am.api.AccessTree puntEntradaCopiar, 
		final com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntradaCopiar == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.copyApplicationAccessLink(com.soffid.iam.am.api.AccessTree puntEntradaCopiar, com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti) - puntEntradaCopiar cannot be null");
		}
		if (puntEntradaCopiar.getName() == null || puntEntradaCopiar.getName().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.copyApplicationAccessLink(com.soffid.iam.am.api.AccessTree puntEntradaCopiar, com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti) - puntEntradaCopiar.name cannot be null");
		}
		if (puntEntradaMenuDesti == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.copyApplicationAccessLink(com.soffid.iam.am.api.AccessTree puntEntradaCopiar, com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti) - puntEntradaMenuDesti cannot be null");
		}
		if (puntEntradaMenuDesti.getName() == null || puntEntradaMenuDesti.getName().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.copyApplicationAccessLink(com.soffid.iam.am.api.AccessTree puntEntradaCopiar, com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti) - puntEntradaMenuDesti.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCopyApplicationAccessLink(puntEntradaCopiar, puntEntradaMenuDesti)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.copyApplicationAccessLink", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.copyApplicationAccessLink", (Throwable) __r[1]);
	}

	protected abstract boolean handleCopyApplicationAccessLink(com.soffid.iam.am.api.AccessTree puntEntradaCopiar, com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#boolean copyApplicationAccess(com.soffid.iam.am.api.AccessTree puntEntradaCopiar, com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean copyApplicationAccess(
		final com.soffid.iam.am.api.AccessTree puntEntradaCopiar, 
		final com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntradaCopiar == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.copyApplicationAccess(com.soffid.iam.am.api.AccessTree puntEntradaCopiar, com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti) - puntEntradaCopiar cannot be null");
		}
		if (puntEntradaCopiar.getName() == null || puntEntradaCopiar.getName().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.copyApplicationAccess(com.soffid.iam.am.api.AccessTree puntEntradaCopiar, com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti) - puntEntradaCopiar.name cannot be null");
		}
		if (puntEntradaMenuDesti == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.copyApplicationAccess(com.soffid.iam.am.api.AccessTree puntEntradaCopiar, com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti) - puntEntradaMenuDesti cannot be null");
		}
		if (puntEntradaMenuDesti.getName() == null || puntEntradaMenuDesti.getName().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.copyApplicationAccess(com.soffid.iam.am.api.AccessTree puntEntradaCopiar, com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti) - puntEntradaMenuDesti.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCopyApplicationAccess(puntEntradaCopiar, puntEntradaMenuDesti)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.copyApplicationAccess", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.copyApplicationAccess", (Throwable) __r[1]);
	}

	protected abstract boolean handleCopyApplicationAccess(com.soffid.iam.am.api.AccessTree puntEntradaCopiar, com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#boolean isAuthorized(com.soffid.iam.am.api.AccessTree puntEntrada, java.lang.String nivell)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean isAuthorized(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final java.lang.String nivell)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntrada == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.isAuthorized(com.soffid.iam.am.api.AccessTree puntEntrada, java.lang.String nivell) - puntEntrada cannot be null");
		}
		if (puntEntrada.getName() == null || puntEntrada.getName().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.isAuthorized(com.soffid.iam.am.api.AccessTree puntEntrada, java.lang.String nivell) - puntEntrada.name cannot be null");
		}
		if (nivell == null || nivell.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.isAuthorized(com.soffid.iam.am.api.AccessTree puntEntrada, java.lang.String nivell) - nivell cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsAuthorized(puntEntrada, nivell)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.isAuthorized", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.isAuthorized", (Throwable) __r[1]);
	}

	protected abstract boolean handleIsAuthorized(com.soffid.iam.am.api.AccessTree puntEntrada, java.lang.String nivell) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#boolean isAuthorized(java.lang.String codiUsuari, java.lang.Long idPuntEntrada, java.lang.String nivell)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean isAuthorized(
		final java.lang.String codiUsuari, 
		final java.lang.Long idPuntEntrada, 
		final java.lang.String nivell)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.isAuthorized(java.lang.String codiUsuari, java.lang.Long idPuntEntrada, java.lang.String nivell) - codiUsuari cannot be null");
		}
		if (idPuntEntrada == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.isAuthorized(java.lang.String codiUsuari, java.lang.Long idPuntEntrada, java.lang.String nivell) - idPuntEntrada cannot be null");
		}
		if (nivell == null || nivell.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.isAuthorized(java.lang.String codiUsuari, java.lang.Long idPuntEntrada, java.lang.String nivell) - nivell cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsAuthorized(codiUsuari, idPuntEntrada, nivell)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.isAuthorized", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.isAuthorized", (Throwable) __r[1]);
	}

	protected abstract boolean handleIsAuthorized(java.lang.String codiUsuari, java.lang.Long idPuntEntrada, java.lang.String nivell) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#boolean applicationAccessTreeHasAnyACL(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean applicationAccessTreeHasAnyACL(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.applicationAccessTreeHasAnyACL(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleApplicationAccessTreeHasAnyACL(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.applicationAccessTreeHasAnyACL", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.applicationAccessTreeHasAnyACL", (Throwable) __r[1]);
	}

	protected abstract boolean handleApplicationAccessTreeHasAnyACL(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#boolean moveApplicationAccessTreeMenu(com.soffid.iam.am.api.AccessTree puntEntradaMoure, com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean moveApplicationAccessTreeMenu(
		final com.soffid.iam.am.api.AccessTree puntEntradaMoure, 
		final com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntradaMoure == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.moveApplicationAccessTreeMenu(com.soffid.iam.am.api.AccessTree puntEntradaMoure, com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti) - puntEntradaMoure cannot be null");
		}
		if (puntEntradaMoure.getName() == null || puntEntradaMoure.getName().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.moveApplicationAccessTreeMenu(com.soffid.iam.am.api.AccessTree puntEntradaMoure, com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti) - puntEntradaMoure.name cannot be null");
		}
		if (puntEntradaMenuDesti == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.moveApplicationAccessTreeMenu(com.soffid.iam.am.api.AccessTree puntEntradaMoure, com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti) - puntEntradaMenuDesti cannot be null");
		}
		if (puntEntradaMenuDesti.getName() == null || puntEntradaMenuDesti.getName().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.moveApplicationAccessTreeMenu(com.soffid.iam.am.api.AccessTree puntEntradaMoure, com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti) - puntEntradaMenuDesti.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleMoveApplicationAccessTreeMenu(puntEntradaMoure, puntEntradaMenuDesti)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.moveApplicationAccessTreeMenu", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.moveApplicationAccessTreeMenu", (Throwable) __r[1]);
	}

	protected abstract boolean handleMoveApplicationAccessTreeMenu(com.soffid.iam.am.api.AccessTree puntEntradaMoure, com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#boolean reorderApplicationAccess(com.soffid.iam.am.api.AccessTree puntEntradaOrdenar, com.soffid.iam.am.api.AccessTree puntEntradaSeguent)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean reorderApplicationAccess(
		final com.soffid.iam.am.api.AccessTree puntEntradaOrdenar, 
		final com.soffid.iam.am.api.AccessTree puntEntradaSeguent)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntradaOrdenar == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.reorderApplicationAccess(com.soffid.iam.am.api.AccessTree puntEntradaOrdenar, com.soffid.iam.am.api.AccessTree puntEntradaSeguent) - puntEntradaOrdenar cannot be null");
		}
		if (puntEntradaOrdenar.getName() == null || puntEntradaOrdenar.getName().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.reorderApplicationAccess(com.soffid.iam.am.api.AccessTree puntEntradaOrdenar, com.soffid.iam.am.api.AccessTree puntEntradaSeguent) - puntEntradaOrdenar.name cannot be null");
		}
		if (puntEntradaSeguent == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.reorderApplicationAccess(com.soffid.iam.am.api.AccessTree puntEntradaOrdenar, com.soffid.iam.am.api.AccessTree puntEntradaSeguent) - puntEntradaSeguent cannot be null");
		}
		if (puntEntradaSeguent.getName() == null || puntEntradaSeguent.getName().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.EntryPointService.reorderApplicationAccess(com.soffid.iam.am.api.AccessTree puntEntradaOrdenar, com.soffid.iam.am.api.AccessTree puntEntradaSeguent) - puntEntradaSeguent.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleReorderApplicationAccess(puntEntradaOrdenar, puntEntradaSeguent)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.reorderApplicationAccess", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.reorderApplicationAccess", (Throwable) __r[1]);
	}

	protected abstract boolean handleReorderApplicationAccess(com.soffid.iam.am.api.AccessTree puntEntradaOrdenar, com.soffid.iam.am.api.AccessTree puntEntradaSeguent) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#com.soffid.iam.am.api.AccessTree create(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.AccessTree create(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntrada == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.AccessTree com.soffid.iam.am.service.EntryPointService.create(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada cannot be null");
		}
		if (puntEntrada.getName() == null || puntEntrada.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.AccessTree com.soffid.iam.am.service.EntryPointService.create(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(puntEntrada)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.AccessTree) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.AccessTree handleCreate(com.soffid.iam.am.api.AccessTree puntEntrada) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#com.soffid.iam.am.api.AccessTree findApplicationAccessById(long id)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.AccessTree findApplicationAccessById(
		final long id)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindApplicationAccessById(id)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.AccessTree) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.findApplicationAccessById", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.findApplicationAccessById", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.AccessTree handleFindApplicationAccessById(long id) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#com.soffid.iam.am.api.AccessTree findRoot()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.AccessTree findRoot()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRoot()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.AccessTree) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.findRoot", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.findRoot", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.AccessTree handleFindRoot() throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#com.soffid.iam.am.api.AccessTree update(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.AccessTree update(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntrada == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.AccessTree com.soffid.iam.am.service.EntryPointService.update(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada cannot be null");
		}
		if (puntEntrada.getName() == null || puntEntrada.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.AccessTree com.soffid.iam.am.service.EntryPointService.update(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(puntEntrada)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.AccessTree) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.AccessTree handleUpdate(com.soffid.iam.am.api.AccessTree puntEntrada) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#com.soffid.iam.am.api.AccessTreeAuthorization createAuthorization(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.AccessTreeAuthorization createAuthorization(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntrada == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.AccessTreeAuthorization com.soffid.iam.am.service.EntryPointService.createAuthorization(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio) - puntEntrada cannot be null");
		}
		if (puntEntrada.getName() == null || puntEntrada.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.AccessTreeAuthorization com.soffid.iam.am.service.EntryPointService.createAuthorization(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio) - puntEntrada.name cannot be null");
		}
		if (autoritzacio == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.AccessTreeAuthorization com.soffid.iam.am.service.EntryPointService.createAuthorization(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio) - autoritzacio cannot be null");
		}
		if (autoritzacio.getAuthorizationLevelDescription() == null || autoritzacio.getAuthorizationLevelDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.AccessTreeAuthorization com.soffid.iam.am.service.EntryPointService.createAuthorization(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio) - autoritzacio.authorizationLevelDescription cannot be null");
		}
		if (autoritzacio.getAuthorizationEntityType() == null || autoritzacio.getAuthorizationEntityType().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.AccessTreeAuthorization com.soffid.iam.am.service.EntryPointService.createAuthorization(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio) - autoritzacio.authorizationEntityType cannot be null");
		}
		if (autoritzacio.getAuthorizedEntityDescription() == null || autoritzacio.getAuthorizedEntityDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.AccessTreeAuthorization com.soffid.iam.am.service.EntryPointService.createAuthorization(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio) - autoritzacio.authorizedEntityDescription cannot be null");
		}
		if (autoritzacio.getAuthorizedEntityCode() == null || autoritzacio.getAuthorizedEntityCode().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.AccessTreeAuthorization com.soffid.iam.am.service.EntryPointService.createAuthorization(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio) - autoritzacio.authorizedEntityCode cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreateAuthorization(puntEntrada, autoritzacio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.AccessTreeAuthorization) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.createAuthorization", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.createAuthorization", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.AccessTreeAuthorization handleCreateAuthorization(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#com.soffid.iam.am.api.AccessTreeExecution createExecution(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeExecution execucio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.AccessTreeExecution createExecution(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final com.soffid.iam.am.api.AccessTreeExecution execucio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntrada == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.AccessTreeExecution com.soffid.iam.am.service.EntryPointService.createExecution(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeExecution execucio) - puntEntrada cannot be null");
		}
		if (puntEntrada.getName() == null || puntEntrada.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.AccessTreeExecution com.soffid.iam.am.service.EntryPointService.createExecution(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeExecution execucio) - puntEntrada.name cannot be null");
		}
		if (execucio == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.AccessTreeExecution com.soffid.iam.am.service.EntryPointService.createExecution(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeExecution execucio) - execucio cannot be null");
		}
		if (execucio.getScope() == null || execucio.getScope().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.AccessTreeExecution com.soffid.iam.am.service.EntryPointService.createExecution(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeExecution execucio) - execucio.scope cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreateExecution(puntEntrada, execucio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.AccessTreeExecution) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.createExecution", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.createExecution", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.AccessTreeExecution handleCreateExecution(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeExecution execucio) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#com.soffid.iam.am.api.AccessTreeExecution updateExecution(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeExecution execucio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.AccessTreeExecution updateExecution(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final com.soffid.iam.am.api.AccessTreeExecution execucio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntrada == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.AccessTreeExecution com.soffid.iam.am.service.EntryPointService.updateExecution(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeExecution execucio) - puntEntrada cannot be null");
		}
		if (puntEntrada.getName() == null || puntEntrada.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.AccessTreeExecution com.soffid.iam.am.service.EntryPointService.updateExecution(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeExecution execucio) - puntEntrada.name cannot be null");
		}
		if (execucio == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.AccessTreeExecution com.soffid.iam.am.service.EntryPointService.updateExecution(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeExecution execucio) - execucio cannot be null");
		}
		if (execucio.getScope() == null || execucio.getScope().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.AccessTreeExecution com.soffid.iam.am.service.EntryPointService.updateExecution(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeExecution execucio) - execucio.scope cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdateExecution(puntEntrada, execucio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.AccessTreeExecution) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.updateExecution", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.updateExecution", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.AccessTreeExecution handleUpdateExecution(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeExecution execucio) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.AccessTree> findAccessTrees(com.soffid.zkdb.api.Query q)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.AccessTree> findAccessTrees(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (q == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.AccessTree> com.soffid.iam.am.service.EntryPointService.findAccessTrees(com.soffid.zkdb.api.Query q) - q cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAccessTrees(q)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.AccessTree>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.findAccessTrees", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.findAccessTrees", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.AccessTree> handleFindAccessTrees(com.soffid.zkdb.api.Query q) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#java.lang.String getScopeForAddress(java.lang.String address)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String getScopeForAddress(
		final java.lang.String address)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetScopeForAddress(address)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.getScopeForAddress", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.getScopeForAddress", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGetScopeForAddress(java.lang.String address) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#java.lang.String validateXMLApplicationAccess(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String validateXMLApplicationAccess(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntrada == null) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.am.service.EntryPointService.validateXMLApplicationAccess(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada cannot be null");
		}
		if (puntEntrada.getName() == null || puntEntrada.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.am.service.EntryPointService.validateXMLApplicationAccess(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleValidateXMLApplicationAccess(puntEntrada)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.validateXMLApplicationAccess", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.validateXMLApplicationAccess", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleValidateXMLApplicationAccess(com.soffid.iam.am.api.AccessTree puntEntrada) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#java.util.Collection<com.soffid.iam.am.api.AccessTree> findChildren(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.api.AccessTree> findChildren(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntrada == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.AccessTree> com.soffid.iam.am.service.EntryPointService.findChildren(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada cannot be null");
		}
		if (puntEntrada.getName() == null || puntEntrada.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.AccessTree> com.soffid.iam.am.service.EntryPointService.findChildren(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindChildren(puntEntrada)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.api.AccessTree>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.findChildren", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.findChildren", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.api.AccessTree> handleFindChildren(com.soffid.iam.am.api.AccessTree puntEntrada) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#java.util.Collection<com.soffid.iam.am.api.AccessTree> findMenuChildren(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.api.AccessTree> findMenuChildren(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntrada == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.AccessTree> com.soffid.iam.am.service.EntryPointService.findMenuChildren(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada cannot be null");
		}
		if (puntEntrada.getName() == null || puntEntrada.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.AccessTree> com.soffid.iam.am.service.EntryPointService.findMenuChildren(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindMenuChildren(puntEntrada)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.api.AccessTree>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.findMenuChildren", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.findMenuChildren", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.api.AccessTree> handleFindMenuChildren(com.soffid.iam.am.api.AccessTree puntEntrada) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#java.util.Collection<com.soffid.iam.am.api.AccessTree> findApplicationAccessByFilter(java.lang.String nomPUE, java.lang.String codiPUE, java.lang.String codiAplicacio, java.lang.String codiRol, java.lang.String codiGrup, java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.api.AccessTree> findApplicationAccessByFilter(
		final java.lang.String nomPUE, 
		final java.lang.String codiPUE, 
		final java.lang.String codiAplicacio, 
		final java.lang.String codiRol, 
		final java.lang.String codiGrup, 
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindApplicationAccessByFilter(nomPUE, codiPUE, codiAplicacio, codiRol, codiGrup, codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.api.AccessTree>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.findApplicationAccessByFilter", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.findApplicationAccessByFilter", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.api.AccessTree> handleFindApplicationAccessByFilter(java.lang.String nomPUE, java.lang.String codiPUE, java.lang.String codiAplicacio, java.lang.String codiRol, java.lang.String codiGrup, java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#java.util.Collection<com.soffid.iam.am.api.AccessTreeExecutionType> getAllMimeTypeExecution()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.api.AccessTreeExecutionType> getAllMimeTypeExecution()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetAllMimeTypeExecution()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.api.AccessTreeExecutionType>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.getAllMimeTypeExecution", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.getAllMimeTypeExecution", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.api.AccessTreeExecutionType> handleGetAllMimeTypeExecution() throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#java.util.Collection<java.lang.String> getReverseApplicationAccessTree(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<java.lang.String> getReverseApplicationAccessTree(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntrada == null) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.String> com.soffid.iam.am.service.EntryPointService.getReverseApplicationAccessTree(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada cannot be null");
		}
		if (puntEntrada.getName() == null || puntEntrada.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.String> com.soffid.iam.am.service.EntryPointService.getReverseApplicationAccessTree(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetReverseApplicationAccessTree(puntEntrada)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.getReverseApplicationAccessTree", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.getReverseApplicationAccessTree", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<java.lang.String> handleGetReverseApplicationAccessTree(com.soffid.iam.am.api.AccessTree puntEntrada) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> getAuthorizationsApplicationAcessTree(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> getAuthorizationsApplicationAcessTree(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntrada == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> com.soffid.iam.am.service.EntryPointService.getAuthorizationsApplicationAcessTree(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada cannot be null");
		}
		if (puntEntrada.getName() == null || puntEntrada.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> com.soffid.iam.am.service.EntryPointService.getAuthorizationsApplicationAcessTree(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetAuthorizationsApplicationAcessTree(puntEntrada)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.getAuthorizationsApplicationAcessTree", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.getAuthorizationsApplicationAcessTree", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> handleGetAuthorizationsApplicationAcessTree(com.soffid.iam.am.api.AccessTree puntEntrada) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#java.util.Collection<com.soffid.iam.am.api.AccessTreeExecution> getExecutions(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.api.AccessTreeExecution> getExecutions(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntrada == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.AccessTreeExecution> com.soffid.iam.am.service.EntryPointService.getExecutions(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada cannot be null");
		}
		if (puntEntrada.getName() == null || puntEntrada.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.AccessTreeExecution> com.soffid.iam.am.service.EntryPointService.getExecutions(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetExecutions(puntEntrada)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.api.AccessTreeExecution>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.getExecutions", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.getExecutions", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.api.AccessTreeExecution> handleGetExecutions(com.soffid.iam.am.api.AccessTree puntEntrada) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#void delete(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntrada == null) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.EntryPointService.delete(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada cannot be null");
		}
		if (puntEntrada.getName() == null || puntEntrada.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.EntryPointService.delete(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(puntEntrada);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.am.api.AccessTree puntEntrada) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#void deleteAuthorization(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void deleteAuthorization(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntrada == null) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.EntryPointService.deleteAuthorization(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio) - puntEntrada cannot be null");
		}
		if (puntEntrada.getName() == null || puntEntrada.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.EntryPointService.deleteAuthorization(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio) - puntEntrada.name cannot be null");
		}
		if (autoritzacio == null) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.EntryPointService.deleteAuthorization(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio) - autoritzacio cannot be null");
		}
		if (autoritzacio.getAuthorizationLevelDescription() == null || autoritzacio.getAuthorizationLevelDescription().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.EntryPointService.deleteAuthorization(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio) - autoritzacio.authorizationLevelDescription cannot be null");
		}
		if (autoritzacio.getAuthorizationEntityType() == null || autoritzacio.getAuthorizationEntityType().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.EntryPointService.deleteAuthorization(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio) - autoritzacio.authorizationEntityType cannot be null");
		}
		if (autoritzacio.getAuthorizedEntityDescription() == null || autoritzacio.getAuthorizedEntityDescription().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.EntryPointService.deleteAuthorization(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio) - autoritzacio.authorizedEntityDescription cannot be null");
		}
		if (autoritzacio.getAuthorizedEntityCode() == null || autoritzacio.getAuthorizedEntityCode().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.EntryPointService.deleteAuthorization(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio) - autoritzacio.authorizedEntityCode cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDeleteAuthorization(puntEntrada, autoritzacio);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.deleteAuthorization", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.deleteAuthorization", (Throwable) __r[1]);
	}

	protected abstract void handleDeleteAuthorization(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#void deleteExecution(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeExecution execucio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void deleteExecution(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final com.soffid.iam.am.api.AccessTreeExecution execucio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntrada == null) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.EntryPointService.deleteExecution(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeExecution execucio) - puntEntrada cannot be null");
		}
		if (puntEntrada.getName() == null || puntEntrada.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.EntryPointService.deleteExecution(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeExecution execucio) - puntEntrada.name cannot be null");
		}
		if (execucio == null) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.EntryPointService.deleteExecution(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeExecution execucio) - execucio cannot be null");
		}
		if (execucio.getScope() == null || execucio.getScope().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.EntryPointService.deleteExecution(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeExecution execucio) - execucio.scope cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDeleteExecution(puntEntrada, execucio);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.deleteExecution", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.deleteExecution", (Throwable) __r[1]);
	}

	protected abstract void handleDeleteExecution(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeExecution execucio) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#	 * @see com.soffid.iam.am.service.EntryPointService#void sortChildren(long entryPointId)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void sortChildren(
		final long entryPointId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSortChildren(entryPointId);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.EntryPointService.class).
			warn ("Error on EntryPointService.sortChildren", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntryPointService.sortChildren", (Throwable) __r[1]);
	}

	protected abstract void handleSortChildren(long entryPointId) throws Exception;

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
