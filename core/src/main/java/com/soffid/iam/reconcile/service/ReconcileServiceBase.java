//
// (C) 2013 Soffid
//
//

package com.soffid.iam.reconcile.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.reconcile.service.ReconcileService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.reconcile.service.ReconcileService
 */
public abstract class ReconcileServiceBase
	implements com.soffid.iam.reconcile.service.ReconcileService
 {
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

	private com.soffid.iam.iga.model.ReconcileAccountAttributesEntityDao reconcileAccountAttributesEntityDao;

	/**
	 * Sets reference to <code>reconcileAccountAttributesEntityDao</code>.
	 */
	public void setReconcileAccountAttributesEntityDao (com.soffid.iam.iga.model.ReconcileAccountAttributesEntityDao reconcileAccountAttributesEntityDao) {
		this.reconcileAccountAttributesEntityDao = reconcileAccountAttributesEntityDao;
	}

	/**
	 * Gets reference to <code>reconcileAccountAttributesEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.ReconcileAccountAttributesEntityDao getReconcileAccountAttributesEntityDao () {
		return reconcileAccountAttributesEntityDao;
	}

	private com.soffid.iam.iga.model.ReconcileAccountEntityDao reconcileAccountEntityDao;

	/**
	 * Sets reference to <code>reconcileAccountEntityDao</code>.
	 */
	public void setReconcileAccountEntityDao (com.soffid.iam.iga.model.ReconcileAccountEntityDao reconcileAccountEntityDao) {
		this.reconcileAccountEntityDao = reconcileAccountEntityDao;
	}

	/**
	 * Gets reference to <code>reconcileAccountEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.ReconcileAccountEntityDao getReconcileAccountEntityDao () {
		return reconcileAccountEntityDao;
	}

	private com.soffid.iam.iga.model.ReconcileAssignmentEntityDao reconcileAssignmentEntityDao;

	/**
	 * Sets reference to <code>reconcileAssignmentEntityDao</code>.
	 */
	public void setReconcileAssignmentEntityDao (com.soffid.iam.iga.model.ReconcileAssignmentEntityDao reconcileAssignmentEntityDao) {
		this.reconcileAssignmentEntityDao = reconcileAssignmentEntityDao;
	}

	/**
	 * Gets reference to <code>reconcileAssignmentEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.ReconcileAssignmentEntityDao getReconcileAssignmentEntityDao () {
		return reconcileAssignmentEntityDao;
	}

	private com.soffid.iam.iga.model.ReconcileRoleEntityDao reconcileRoleEntityDao;

	/**
	 * Sets reference to <code>reconcileRoleEntityDao</code>.
	 */
	public void setReconcileRoleEntityDao (com.soffid.iam.iga.model.ReconcileRoleEntityDao reconcileRoleEntityDao) {
		this.reconcileRoleEntityDao = reconcileRoleEntityDao;
	}

	/**
	 * Gets reference to <code>reconcileRoleEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.ReconcileRoleEntityDao getReconcileRoleEntityDao () {
		return reconcileRoleEntityDao;
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
	 * @see com.soffid.iam.reconcile.service.ReconcileService#	 * @see com.soffid.iam.reconcile.service.ReconcileService#boolean isPendingTasks(java.lang.Long processId, java.lang.Long taskId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean isPendingTasks(
		final java.lang.Long processId, 
		final java.lang.Long taskId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (processId == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.reconcile.service.ReconcileService.isPendingTasks(java.lang.Long processId, java.lang.Long taskId) - processId cannot be null");
		}
		if (taskId == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.reconcile.service.ReconcileService.isPendingTasks(java.lang.Long processId, java.lang.Long taskId) - taskId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsPendingTasks(processId, taskId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.reconcile.service.ReconcileService.class).
			warn ("Error on ReconcileService.isPendingTasks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ReconcileService.isPendingTasks", (Throwable) __r[1]);
	}

	protected abstract boolean handleIsPendingTasks(java.lang.Long processId, java.lang.Long taskId) throws Exception;

	/**
	 * @see com.soffid.iam.reconcile.service.ReconcileService#	 * @see com.soffid.iam.reconcile.service.ReconcileService#com.soffid.iam.iga.api.ReconcileAccount addUser(com.soffid.iam.iga.api.ReconcileAccount userInfo)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.ReconcileAccount addUser(
		final com.soffid.iam.iga.api.ReconcileAccount userInfo)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userInfo == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileAccount com.soffid.iam.reconcile.service.ReconcileService.addUser(com.soffid.iam.iga.api.ReconcileAccount userInfo) - userInfo cannot be null");
		}
		if (userInfo.getAccountName() == null || userInfo.getAccountName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileAccount com.soffid.iam.reconcile.service.ReconcileService.addUser(com.soffid.iam.iga.api.ReconcileAccount userInfo) - userInfo.accountName cannot be null");
		}
		if (userInfo.getDescription() == null || userInfo.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileAccount com.soffid.iam.reconcile.service.ReconcileService.addUser(com.soffid.iam.iga.api.ReconcileAccount userInfo) - userInfo.description cannot be null");
		}
		if (userInfo.getProcessId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileAccount com.soffid.iam.reconcile.service.ReconcileService.addUser(com.soffid.iam.iga.api.ReconcileAccount userInfo) - userInfo.processId cannot be null");
		}
		if (userInfo.getProposedAction() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileAccount com.soffid.iam.reconcile.service.ReconcileService.addUser(com.soffid.iam.iga.api.ReconcileAccount userInfo) - userInfo.proposedAction cannot be null");
		}
		if (userInfo.getDispatcher() == null || userInfo.getDispatcher().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileAccount com.soffid.iam.reconcile.service.ReconcileService.addUser(com.soffid.iam.iga.api.ReconcileAccount userInfo) - userInfo.dispatcher cannot be null");
		}
		if (userInfo.getNewAccount() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileAccount com.soffid.iam.reconcile.service.ReconcileService.addUser(com.soffid.iam.iga.api.ReconcileAccount userInfo) - userInfo.newAccount cannot be null");
		}
		if (userInfo.getDeletedAccount() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileAccount com.soffid.iam.reconcile.service.ReconcileService.addUser(com.soffid.iam.iga.api.ReconcileAccount userInfo) - userInfo.deletedAccount cannot be null");
		}
		if (userInfo.getAttributes() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileAccount com.soffid.iam.reconcile.service.ReconcileService.addUser(com.soffid.iam.iga.api.ReconcileAccount userInfo) - userInfo.attributes cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleAddUser(userInfo)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.ReconcileAccount) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.reconcile.service.ReconcileService.class).
			warn ("Error on ReconcileService.addUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ReconcileService.addUser", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.ReconcileAccount handleAddUser(com.soffid.iam.iga.api.ReconcileAccount userInfo) throws Exception;

	/**
	 * @see com.soffid.iam.reconcile.service.ReconcileService#	 * @see com.soffid.iam.reconcile.service.ReconcileService#com.soffid.iam.iga.api.ReconcileAccount findReconAccountById(java.lang.Long accountId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.ReconcileAccount findReconAccountById(
		final java.lang.Long accountId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (accountId == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileAccount com.soffid.iam.reconcile.service.ReconcileService.findReconAccountById(java.lang.Long accountId) - accountId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindReconAccountById(accountId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.ReconcileAccount) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.reconcile.service.ReconcileService.class).
			warn ("Error on ReconcileService.findReconAccountById", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ReconcileService.findReconAccountById", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.ReconcileAccount handleFindReconAccountById(java.lang.Long accountId) throws Exception;

	/**
	 * @see com.soffid.iam.reconcile.service.ReconcileService#	 * @see com.soffid.iam.reconcile.service.ReconcileService#com.soffid.iam.iga.api.ReconcileAssignment addAssignment(com.soffid.iam.iga.api.ReconcileAssignment assignmentInfo)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.ReconcileAssignment addAssignment(
		final com.soffid.iam.iga.api.ReconcileAssignment assignmentInfo)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (assignmentInfo == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileAssignment com.soffid.iam.reconcile.service.ReconcileService.addAssignment(com.soffid.iam.iga.api.ReconcileAssignment assignmentInfo) - assignmentInfo cannot be null");
		}
		if (assignmentInfo.getAssignmentName() == null || assignmentInfo.getAssignmentName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileAssignment com.soffid.iam.reconcile.service.ReconcileService.addAssignment(com.soffid.iam.iga.api.ReconcileAssignment assignmentInfo) - assignmentInfo.assignmentName cannot be null");
		}
		if (assignmentInfo.getProcessId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileAssignment com.soffid.iam.reconcile.service.ReconcileService.addAssignment(com.soffid.iam.iga.api.ReconcileAssignment assignmentInfo) - assignmentInfo.processId cannot be null");
		}
		if (assignmentInfo.getAccountName() == null || assignmentInfo.getAccountName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileAssignment com.soffid.iam.reconcile.service.ReconcileService.addAssignment(com.soffid.iam.iga.api.ReconcileAssignment assignmentInfo) - assignmentInfo.accountName cannot be null");
		}
		if (assignmentInfo.getRoleName() == null || assignmentInfo.getRoleName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileAssignment com.soffid.iam.reconcile.service.ReconcileService.addAssignment(com.soffid.iam.iga.api.ReconcileAssignment assignmentInfo) - assignmentInfo.roleName cannot be null");
		}
		if (assignmentInfo.getProposedAction() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileAssignment com.soffid.iam.reconcile.service.ReconcileService.addAssignment(com.soffid.iam.iga.api.ReconcileAssignment assignmentInfo) - assignmentInfo.proposedAction cannot be null");
		}
		if (assignmentInfo.getDispatcher() == null || assignmentInfo.getDispatcher().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileAssignment com.soffid.iam.reconcile.service.ReconcileService.addAssignment(com.soffid.iam.iga.api.ReconcileAssignment assignmentInfo) - assignmentInfo.dispatcher cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleAddAssignment(assignmentInfo)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.ReconcileAssignment) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.reconcile.service.ReconcileService.class).
			warn ("Error on ReconcileService.addAssignment", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ReconcileService.addAssignment", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.ReconcileAssignment handleAddAssignment(com.soffid.iam.iga.api.ReconcileAssignment assignmentInfo) throws Exception;

	/**
	 * @see com.soffid.iam.reconcile.service.ReconcileService#	 * @see com.soffid.iam.reconcile.service.ReconcileService#com.soffid.iam.iga.api.ReconcileAssignment findReconAssignmentById(java.lang.Long assignId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.ReconcileAssignment findReconAssignmentById(
		final java.lang.Long assignId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (assignId == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileAssignment com.soffid.iam.reconcile.service.ReconcileService.findReconAssignmentById(java.lang.Long assignId) - assignId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindReconAssignmentById(assignId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.ReconcileAssignment) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.reconcile.service.ReconcileService.class).
			warn ("Error on ReconcileService.findReconAssignmentById", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ReconcileService.findReconAssignmentById", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.ReconcileAssignment handleFindReconAssignmentById(java.lang.Long assignId) throws Exception;

	/**
	 * @see com.soffid.iam.reconcile.service.ReconcileService#	 * @see com.soffid.iam.reconcile.service.ReconcileService#com.soffid.iam.iga.api.ReconcileRole addRole(com.soffid.iam.iga.api.ReconcileRole roleInfo)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.ReconcileRole addRole(
		final com.soffid.iam.iga.api.ReconcileRole roleInfo)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (roleInfo == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileRole com.soffid.iam.reconcile.service.ReconcileService.addRole(com.soffid.iam.iga.api.ReconcileRole roleInfo) - roleInfo cannot be null");
		}
		if (roleInfo.getRoleName() == null || roleInfo.getRoleName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileRole com.soffid.iam.reconcile.service.ReconcileService.addRole(com.soffid.iam.iga.api.ReconcileRole roleInfo) - roleInfo.roleName cannot be null");
		}
		if (roleInfo.getDescription() == null || roleInfo.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileRole com.soffid.iam.reconcile.service.ReconcileService.addRole(com.soffid.iam.iga.api.ReconcileRole roleInfo) - roleInfo.description cannot be null");
		}
		if (roleInfo.getProcessId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileRole com.soffid.iam.reconcile.service.ReconcileService.addRole(com.soffid.iam.iga.api.ReconcileRole roleInfo) - roleInfo.processId cannot be null");
		}
		if (roleInfo.getProposedAction() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileRole com.soffid.iam.reconcile.service.ReconcileService.addRole(com.soffid.iam.iga.api.ReconcileRole roleInfo) - roleInfo.proposedAction cannot be null");
		}
		if (roleInfo.getDispatcher() == null || roleInfo.getDispatcher().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileRole com.soffid.iam.reconcile.service.ReconcileService.addRole(com.soffid.iam.iga.api.ReconcileRole roleInfo) - roleInfo.dispatcher cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleAddRole(roleInfo)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.ReconcileRole) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.reconcile.service.ReconcileService.class).
			warn ("Error on ReconcileService.addRole", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ReconcileService.addRole", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.ReconcileRole handleAddRole(com.soffid.iam.iga.api.ReconcileRole roleInfo) throws Exception;

	/**
	 * @see com.soffid.iam.reconcile.service.ReconcileService#	 * @see com.soffid.iam.reconcile.service.ReconcileService#com.soffid.iam.iga.api.ReconcileRole findReconRoleById(java.lang.Long roleId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.ReconcileRole findReconRoleById(
		final java.lang.Long roleId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (roleId == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileRole com.soffid.iam.reconcile.service.ReconcileService.findReconRoleById(java.lang.Long roleId) - roleId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindReconRoleById(roleId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.ReconcileRole) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.reconcile.service.ReconcileService.class).
			warn ("Error on ReconcileService.findReconRoleById", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ReconcileService.findReconRoleById", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.ReconcileRole handleFindReconRoleById(java.lang.Long roleId) throws Exception;

	/**
	 * @see com.soffid.iam.reconcile.service.ReconcileService#	 * @see com.soffid.iam.reconcile.service.ReconcileService#java.util.List<com.soffid.iam.iga.api.ReconcileAccount> findAllReconAccounts(java.lang.Long processId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.iga.api.ReconcileAccount> findAllReconAccounts(
		final java.lang.Long processId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (processId == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.iga.api.ReconcileAccount> com.soffid.iam.reconcile.service.ReconcileService.findAllReconAccounts(java.lang.Long processId) - processId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAllReconAccounts(processId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.iga.api.ReconcileAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.reconcile.service.ReconcileService.class).
			warn ("Error on ReconcileService.findAllReconAccounts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ReconcileService.findAllReconAccounts", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.iga.api.ReconcileAccount> handleFindAllReconAccounts(java.lang.Long processId) throws Exception;

	/**
	 * @see com.soffid.iam.reconcile.service.ReconcileService#	 * @see com.soffid.iam.reconcile.service.ReconcileService#java.util.List<com.soffid.iam.iga.api.ReconcileAssignment> findAllReconAssignment(java.lang.Long processId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.iga.api.ReconcileAssignment> findAllReconAssignment(
		final java.lang.Long processId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (processId == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.iga.api.ReconcileAssignment> com.soffid.iam.reconcile.service.ReconcileService.findAllReconAssignment(java.lang.Long processId) - processId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAllReconAssignment(processId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.iga.api.ReconcileAssignment>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.reconcile.service.ReconcileService.class).
			warn ("Error on ReconcileService.findAllReconAssignment", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ReconcileService.findAllReconAssignment", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.iga.api.ReconcileAssignment> handleFindAllReconAssignment(java.lang.Long processId) throws Exception;

	/**
	 * @see com.soffid.iam.reconcile.service.ReconcileService#	 * @see com.soffid.iam.reconcile.service.ReconcileService#java.util.List<com.soffid.iam.iga.api.ReconcileRole> findAllReconRole(java.lang.Long processId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.iga.api.ReconcileRole> findAllReconRole(
		final java.lang.Long processId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (processId == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.iga.api.ReconcileRole> com.soffid.iam.reconcile.service.ReconcileService.findAllReconRole(java.lang.Long processId) - processId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAllReconRole(processId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.iga.api.ReconcileRole>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.reconcile.service.ReconcileService.class).
			warn ("Error on ReconcileService.findAllReconRole", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ReconcileService.findAllReconRole", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.iga.api.ReconcileRole> handleFindAllReconRole(java.lang.Long processId) throws Exception;

	/**
	 * @see com.soffid.iam.reconcile.service.ReconcileService#	 * @see com.soffid.iam.reconcile.service.ReconcileService#void createReconcileTask(java.lang.Long processId, java.lang.String dispatcher)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void createReconcileTask(
		final java.lang.Long processId, 
		final java.lang.String dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (processId == null) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.createReconcileTask(java.lang.Long processId, java.lang.String dispatcher) - processId cannot be null");
		}
		if (dispatcher == null || dispatcher.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.createReconcileTask(java.lang.Long processId, java.lang.String dispatcher) - dispatcher cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleCreateReconcileTask(processId, dispatcher);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.reconcile.service.ReconcileService.class).
			warn ("Error on ReconcileService.createReconcileTask", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ReconcileService.createReconcileTask", (Throwable) __r[1]);
	}

	protected abstract void handleCreateReconcileTask(java.lang.Long processId, java.lang.String dispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.reconcile.service.ReconcileService#	 * @see com.soffid.iam.reconcile.service.ReconcileService#void reconcileAssignment(java.lang.Long processId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void reconcileAssignment(
		final java.lang.Long processId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (processId == null) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.reconcileAssignment(java.lang.Long processId) - processId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleReconcileAssignment(processId);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.reconcile.service.ReconcileService.class).
			warn ("Error on ReconcileService.reconcileAssignment", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ReconcileService.reconcileAssignment", (Throwable) __r[1]);
	}

	protected abstract void handleReconcileAssignment(java.lang.Long processId) throws Exception;

	/**
	 * @see com.soffid.iam.reconcile.service.ReconcileService#	 * @see com.soffid.iam.reconcile.service.ReconcileService#void reconcileData(java.lang.Long processId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void reconcileData(
		final java.lang.Long processId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (processId == null) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.reconcileData(java.lang.Long processId) - processId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleReconcileData(processId);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.reconcile.service.ReconcileService.class).
			warn ("Error on ReconcileService.reconcileData", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ReconcileService.reconcileData", (Throwable) __r[1]);
	}

	protected abstract void handleReconcileData(java.lang.Long processId) throws Exception;

	/**
	 * @see com.soffid.iam.reconcile.service.ReconcileService#	 * @see com.soffid.iam.reconcile.service.ReconcileService#void reconcileRoles(java.lang.Long processId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void reconcileRoles(
		final java.lang.Long processId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (processId == null) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.reconcileRoles(java.lang.Long processId) - processId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleReconcileRoles(processId);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.reconcile.service.ReconcileService.class).
			warn ("Error on ReconcileService.reconcileRoles", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ReconcileService.reconcileRoles", (Throwable) __r[1]);
	}

	protected abstract void handleReconcileRoles(java.lang.Long processId) throws Exception;

	/**
	 * @see com.soffid.iam.reconcile.service.ReconcileService#	 * @see com.soffid.iam.reconcile.service.ReconcileService#void reconcileUsers(java.lang.Long processId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void reconcileUsers(
		final java.lang.Long processId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (processId == null) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.reconcileUsers(java.lang.Long processId) - processId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleReconcileUsers(processId);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.reconcile.service.ReconcileService.class).
			warn ("Error on ReconcileService.reconcileUsers", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ReconcileService.reconcileUsers", (Throwable) __r[1]);
	}

	protected abstract void handleReconcileUsers(java.lang.Long processId) throws Exception;

	/**
	 * @see com.soffid.iam.reconcile.service.ReconcileService#	 * @see com.soffid.iam.reconcile.service.ReconcileService#void updateAssignment(com.soffid.iam.iga.api.ReconcileAssignment assignInfo)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void updateAssignment(
		final com.soffid.iam.iga.api.ReconcileAssignment assignInfo)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (assignInfo == null) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.updateAssignment(com.soffid.iam.iga.api.ReconcileAssignment assignInfo) - assignInfo cannot be null");
		}
		if (assignInfo.getAssignmentName() == null || assignInfo.getAssignmentName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.updateAssignment(com.soffid.iam.iga.api.ReconcileAssignment assignInfo) - assignInfo.assignmentName cannot be null");
		}
		if (assignInfo.getProcessId() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.updateAssignment(com.soffid.iam.iga.api.ReconcileAssignment assignInfo) - assignInfo.processId cannot be null");
		}
		if (assignInfo.getAccountName() == null || assignInfo.getAccountName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.updateAssignment(com.soffid.iam.iga.api.ReconcileAssignment assignInfo) - assignInfo.accountName cannot be null");
		}
		if (assignInfo.getRoleName() == null || assignInfo.getRoleName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.updateAssignment(com.soffid.iam.iga.api.ReconcileAssignment assignInfo) - assignInfo.roleName cannot be null");
		}
		if (assignInfo.getProposedAction() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.updateAssignment(com.soffid.iam.iga.api.ReconcileAssignment assignInfo) - assignInfo.proposedAction cannot be null");
		}
		if (assignInfo.getDispatcher() == null || assignInfo.getDispatcher().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.updateAssignment(com.soffid.iam.iga.api.ReconcileAssignment assignInfo) - assignInfo.dispatcher cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpdateAssignment(assignInfo);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.reconcile.service.ReconcileService.class).
			warn ("Error on ReconcileService.updateAssignment", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ReconcileService.updateAssignment", (Throwable) __r[1]);
	}

	protected abstract void handleUpdateAssignment(com.soffid.iam.iga.api.ReconcileAssignment assignInfo) throws Exception;

	/**
	 * @see com.soffid.iam.reconcile.service.ReconcileService#	 * @see com.soffid.iam.reconcile.service.ReconcileService#void updateRole(com.soffid.iam.iga.api.ReconcileRole roleInfo)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void updateRole(
		final com.soffid.iam.iga.api.ReconcileRole roleInfo)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (roleInfo == null) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.updateRole(com.soffid.iam.iga.api.ReconcileRole roleInfo) - roleInfo cannot be null");
		}
		if (roleInfo.getRoleName() == null || roleInfo.getRoleName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.updateRole(com.soffid.iam.iga.api.ReconcileRole roleInfo) - roleInfo.roleName cannot be null");
		}
		if (roleInfo.getDescription() == null || roleInfo.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.updateRole(com.soffid.iam.iga.api.ReconcileRole roleInfo) - roleInfo.description cannot be null");
		}
		if (roleInfo.getProcessId() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.updateRole(com.soffid.iam.iga.api.ReconcileRole roleInfo) - roleInfo.processId cannot be null");
		}
		if (roleInfo.getProposedAction() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.updateRole(com.soffid.iam.iga.api.ReconcileRole roleInfo) - roleInfo.proposedAction cannot be null");
		}
		if (roleInfo.getDispatcher() == null || roleInfo.getDispatcher().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.updateRole(com.soffid.iam.iga.api.ReconcileRole roleInfo) - roleInfo.dispatcher cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpdateRole(roleInfo);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.reconcile.service.ReconcileService.class).
			warn ("Error on ReconcileService.updateRole", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ReconcileService.updateRole", (Throwable) __r[1]);
	}

	protected abstract void handleUpdateRole(com.soffid.iam.iga.api.ReconcileRole roleInfo) throws Exception;

	/**
	 * @see com.soffid.iam.reconcile.service.ReconcileService#	 * @see com.soffid.iam.reconcile.service.ReconcileService#void updateUser(com.soffid.iam.iga.api.ReconcileAccount userInfo)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void updateUser(
		final com.soffid.iam.iga.api.ReconcileAccount userInfo)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userInfo == null) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.updateUser(com.soffid.iam.iga.api.ReconcileAccount userInfo) - userInfo cannot be null");
		}
		if (userInfo.getAccountName() == null || userInfo.getAccountName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.updateUser(com.soffid.iam.iga.api.ReconcileAccount userInfo) - userInfo.accountName cannot be null");
		}
		if (userInfo.getDescription() == null || userInfo.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.updateUser(com.soffid.iam.iga.api.ReconcileAccount userInfo) - userInfo.description cannot be null");
		}
		if (userInfo.getProcessId() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.updateUser(com.soffid.iam.iga.api.ReconcileAccount userInfo) - userInfo.processId cannot be null");
		}
		if (userInfo.getProposedAction() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.updateUser(com.soffid.iam.iga.api.ReconcileAccount userInfo) - userInfo.proposedAction cannot be null");
		}
		if (userInfo.getDispatcher() == null || userInfo.getDispatcher().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.updateUser(com.soffid.iam.iga.api.ReconcileAccount userInfo) - userInfo.dispatcher cannot be null");
		}
		if (userInfo.getNewAccount() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.updateUser(com.soffid.iam.iga.api.ReconcileAccount userInfo) - userInfo.newAccount cannot be null");
		}
		if (userInfo.getDeletedAccount() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.updateUser(com.soffid.iam.iga.api.ReconcileAccount userInfo) - userInfo.deletedAccount cannot be null");
		}
		if (userInfo.getAttributes() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.updateUser(com.soffid.iam.iga.api.ReconcileAccount userInfo) - userInfo.attributes cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpdateUser(userInfo);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.reconcile.service.ReconcileService.class).
			warn ("Error on ReconcileService.updateUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ReconcileService.updateUser", (Throwable) __r[1]);
	}

	protected abstract void handleUpdateUser(com.soffid.iam.iga.api.ReconcileAccount userInfo) throws Exception;

	/**
	 * @see com.soffid.iam.reconcile.service.ReconcileService#	 * @see com.soffid.iam.reconcile.service.ReconcileService#void validateReconcileAccount(com.soffid.iam.iga.api.ReconcileAccount accountinfo)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void validateReconcileAccount(
		final com.soffid.iam.iga.api.ReconcileAccount accountinfo)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (accountinfo == null) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.validateReconcileAccount(com.soffid.iam.iga.api.ReconcileAccount accountinfo) - accountinfo cannot be null");
		}
		if (accountinfo.getAccountName() == null || accountinfo.getAccountName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.validateReconcileAccount(com.soffid.iam.iga.api.ReconcileAccount accountinfo) - accountinfo.accountName cannot be null");
		}
		if (accountinfo.getDescription() == null || accountinfo.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.validateReconcileAccount(com.soffid.iam.iga.api.ReconcileAccount accountinfo) - accountinfo.description cannot be null");
		}
		if (accountinfo.getProcessId() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.validateReconcileAccount(com.soffid.iam.iga.api.ReconcileAccount accountinfo) - accountinfo.processId cannot be null");
		}
		if (accountinfo.getProposedAction() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.validateReconcileAccount(com.soffid.iam.iga.api.ReconcileAccount accountinfo) - accountinfo.proposedAction cannot be null");
		}
		if (accountinfo.getDispatcher() == null || accountinfo.getDispatcher().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.validateReconcileAccount(com.soffid.iam.iga.api.ReconcileAccount accountinfo) - accountinfo.dispatcher cannot be null");
		}
		if (accountinfo.getNewAccount() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.validateReconcileAccount(com.soffid.iam.iga.api.ReconcileAccount accountinfo) - accountinfo.newAccount cannot be null");
		}
		if (accountinfo.getDeletedAccount() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.validateReconcileAccount(com.soffid.iam.iga.api.ReconcileAccount accountinfo) - accountinfo.deletedAccount cannot be null");
		}
		if (accountinfo.getAttributes() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.validateReconcileAccount(com.soffid.iam.iga.api.ReconcileAccount accountinfo) - accountinfo.attributes cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleValidateReconcileAccount(accountinfo);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.reconcile.service.ReconcileService.class).
			warn ("Error on ReconcileService.validateReconcileAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ReconcileService.validateReconcileAccount", (Throwable) __r[1]);
	}

	protected abstract void handleValidateReconcileAccount(com.soffid.iam.iga.api.ReconcileAccount accountinfo) throws Exception;

	/**
	 * @see com.soffid.iam.reconcile.service.ReconcileService#	 * @see com.soffid.iam.reconcile.service.ReconcileService#void validateReconcileRole(com.soffid.iam.iga.api.ReconcileRole roleInfo)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void validateReconcileRole(
		final com.soffid.iam.iga.api.ReconcileRole roleInfo)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (roleInfo == null) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.validateReconcileRole(com.soffid.iam.iga.api.ReconcileRole roleInfo) - roleInfo cannot be null");
		}
		if (roleInfo.getRoleName() == null || roleInfo.getRoleName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.validateReconcileRole(com.soffid.iam.iga.api.ReconcileRole roleInfo) - roleInfo.roleName cannot be null");
		}
		if (roleInfo.getDescription() == null || roleInfo.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.validateReconcileRole(com.soffid.iam.iga.api.ReconcileRole roleInfo) - roleInfo.description cannot be null");
		}
		if (roleInfo.getProcessId() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.validateReconcileRole(com.soffid.iam.iga.api.ReconcileRole roleInfo) - roleInfo.processId cannot be null");
		}
		if (roleInfo.getProposedAction() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.validateReconcileRole(com.soffid.iam.iga.api.ReconcileRole roleInfo) - roleInfo.proposedAction cannot be null");
		}
		if (roleInfo.getDispatcher() == null || roleInfo.getDispatcher().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.reconcile.service.ReconcileService.validateReconcileRole(com.soffid.iam.iga.api.ReconcileRole roleInfo) - roleInfo.dispatcher cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleValidateReconcileRole(roleInfo);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.reconcile.service.ReconcileService.class).
			warn ("Error on ReconcileService.validateReconcileRole", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ReconcileService.validateReconcileRole", (Throwable) __r[1]);
	}

	protected abstract void handleValidateReconcileRole(com.soffid.iam.iga.api.ReconcileRole roleInfo) throws Exception;

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
