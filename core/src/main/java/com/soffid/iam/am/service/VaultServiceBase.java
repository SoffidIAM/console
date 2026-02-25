//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.am.service.VaultService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.am.service.VaultService
 */
public abstract class VaultServiceBase
	implements com.soffid.iam.am.service.VaultService
 {
	private com.soffid.iam.pam.model.AccountAccessEntityDao accountAccessEntityDao;

	/**
	 * Sets reference to <code>accountAccessEntityDao</code>.
	 */
	public void setAccountAccessEntityDao (com.soffid.iam.pam.model.AccountAccessEntityDao accountAccessEntityDao) {
		this.accountAccessEntityDao = accountAccessEntityDao;
	}

	/**
	 * Gets reference to <code>accountAccessEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.AccountAccessEntityDao getAccountAccessEntityDao () {
		return accountAccessEntityDao;
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

	private com.soffid.iam.am.model.VaultFolderAccessEntityDao vaultFolderAccessEntityDao;

	/**
	 * Sets reference to <code>vaultFolderAccessEntityDao</code>.
	 */
	public void setVaultFolderAccessEntityDao (com.soffid.iam.am.model.VaultFolderAccessEntityDao vaultFolderAccessEntityDao) {
		this.vaultFolderAccessEntityDao = vaultFolderAccessEntityDao;
	}

	/**
	 * Gets reference to <code>vaultFolderAccessEntityDao</code>.
	 */
	public com.soffid.iam.am.model.VaultFolderAccessEntityDao getVaultFolderAccessEntityDao () {
		return vaultFolderAccessEntityDao;
	}

	private com.soffid.iam.am.model.VaultFolderEntityDao vaultFolderEntityDao;

	/**
	 * Sets reference to <code>vaultFolderEntityDao</code>.
	 */
	public void setVaultFolderEntityDao (com.soffid.iam.am.model.VaultFolderEntityDao vaultFolderEntityDao) {
		this.vaultFolderEntityDao = vaultFolderEntityDao;
	}

	/**
	 * Gets reference to <code>vaultFolderEntityDao</code>.
	 */
	public com.soffid.iam.am.model.VaultFolderEntityDao getVaultFolderEntityDao () {
		return vaultFolderEntityDao;
	}


	/**
	 * @see com.soffid.iam.am.service.VaultService#	 * @see com.soffid.iam.am.service.VaultService#com.soffid.iam.am.api.VaultElement create(com.soffid.iam.am.api.VaultElement folder)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.VaultElement create(
		final com.soffid.iam.am.api.VaultElement folder)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (folder == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.VaultElement com.soffid.iam.am.service.VaultService.create(com.soffid.iam.am.api.VaultElement folder) - folder cannot be null");
		}
		if (folder.getType() == null || folder.getType().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.VaultElement com.soffid.iam.am.service.VaultService.create(com.soffid.iam.am.api.VaultElement folder) - folder.type cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(folder)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.VaultElement) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.VaultService.class).
			warn ("Error on VaultService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on VaultService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.VaultElement handleCreate(com.soffid.iam.am.api.VaultElement folder) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.VaultService#	 * @see com.soffid.iam.am.service.VaultService#com.soffid.iam.am.api.VaultElement findVaultElement(long id)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.VaultElement findVaultElement(
		final long id)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindVaultElement(id)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.VaultElement) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.VaultService.class).
			warn ("Error on VaultService.findVaultElement", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on VaultService.findVaultElement", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.VaultElement handleFindVaultElement(long id) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.VaultService#	 * @see com.soffid.iam.am.service.VaultService#com.soffid.iam.am.api.VaultElement update(com.soffid.iam.am.api.VaultElement folder)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.VaultElement update(
		final com.soffid.iam.am.api.VaultElement folder)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (folder == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.VaultElement com.soffid.iam.am.service.VaultService.update(com.soffid.iam.am.api.VaultElement folder) - folder cannot be null");
		}
		if (folder.getType() == null || folder.getType().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.VaultElement com.soffid.iam.am.service.VaultService.update(com.soffid.iam.am.api.VaultElement folder) - folder.type cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(folder)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.VaultElement) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.VaultService.class).
			warn ("Error on VaultService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on VaultService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.VaultElement handleUpdate(com.soffid.iam.am.api.VaultElement folder) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.VaultService#	 * @see com.soffid.iam.am.service.VaultService#com.soffid.iam.am.api.VaultFolder create(com.soffid.iam.am.api.VaultFolder folder)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.VaultFolder create(
		final com.soffid.iam.am.api.VaultFolder folder)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (folder == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.VaultFolder com.soffid.iam.am.service.VaultService.create(com.soffid.iam.am.api.VaultFolder folder) - folder cannot be null");
		}
		if (folder.getName() == null || folder.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.VaultFolder com.soffid.iam.am.service.VaultService.create(com.soffid.iam.am.api.VaultFolder folder) - folder.name cannot be null");
		}
		if (folder.getDescription() == null || folder.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.VaultFolder com.soffid.iam.am.service.VaultService.create(com.soffid.iam.am.api.VaultFolder folder) - folder.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(folder)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.VaultFolder) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.VaultService.class).
			warn ("Error on VaultService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on VaultService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.VaultFolder handleCreate(com.soffid.iam.am.api.VaultFolder folder) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.VaultService#	 * @see com.soffid.iam.am.service.VaultService#com.soffid.iam.am.api.VaultFolder findFolder(long id)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.VaultFolder findFolder(
		final long id)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindFolder(id)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.VaultFolder) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.VaultService.class).
			warn ("Error on VaultService.findFolder", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on VaultService.findFolder", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.VaultFolder handleFindFolder(long id) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.VaultService#	 * @see com.soffid.iam.am.service.VaultService#com.soffid.iam.am.api.VaultFolder getPersonalFolder()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.VaultFolder getPersonalFolder()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetPersonalFolder()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.VaultFolder) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.VaultService.class).
			warn ("Error on VaultService.getPersonalFolder", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on VaultService.getPersonalFolder", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.VaultFolder handleGetPersonalFolder() throws Exception;

	/**
	 * @see com.soffid.iam.am.service.VaultService#	 * @see com.soffid.iam.am.service.VaultService#com.soffid.iam.am.api.VaultFolder update(com.soffid.iam.am.api.VaultFolder folder)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.VaultFolder update(
		final com.soffid.iam.am.api.VaultFolder folder)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (folder == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.VaultFolder com.soffid.iam.am.service.VaultService.update(com.soffid.iam.am.api.VaultFolder folder) - folder cannot be null");
		}
		if (folder.getName() == null || folder.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.VaultFolder com.soffid.iam.am.service.VaultService.update(com.soffid.iam.am.api.VaultFolder folder) - folder.name cannot be null");
		}
		if (folder.getDescription() == null || folder.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.VaultFolder com.soffid.iam.am.service.VaultService.update(com.soffid.iam.am.api.VaultFolder folder) - folder.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(folder)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.VaultFolder) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.VaultService.class).
			warn ("Error on VaultService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on VaultService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.VaultFolder handleUpdate(com.soffid.iam.am.api.VaultFolder folder) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.VaultService#	 * @see com.soffid.iam.am.service.VaultService#com.soffid.iam.am.api.VaultFolderPermissions getFolderPermissions(com.soffid.iam.am.api.VaultFolder folder)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.VaultFolderPermissions getFolderPermissions(
		final com.soffid.iam.am.api.VaultFolder folder)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (folder == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.VaultFolderPermissions com.soffid.iam.am.service.VaultService.getFolderPermissions(com.soffid.iam.am.api.VaultFolder folder) - folder cannot be null");
		}
		if (folder.getName() == null || folder.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.VaultFolderPermissions com.soffid.iam.am.service.VaultService.getFolderPermissions(com.soffid.iam.am.api.VaultFolder folder) - folder.name cannot be null");
		}
		if (folder.getDescription() == null || folder.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.VaultFolderPermissions com.soffid.iam.am.service.VaultService.getFolderPermissions(com.soffid.iam.am.api.VaultFolder folder) - folder.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetFolderPermissions(folder)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.VaultFolderPermissions) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.VaultService.class).
			warn ("Error on VaultService.getFolderPermissions", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on VaultService.getFolderPermissions", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.VaultFolderPermissions handleGetFolderPermissions(com.soffid.iam.am.api.VaultFolder folder) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.VaultService#	 * @see com.soffid.iam.am.service.VaultService#com.soffid.iam.base.api.Account addToFolder(com.soffid.iam.base.api.Account account)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Account addToFolder(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.am.service.VaultService.addToFolder(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.am.service.VaultService.addToFolder(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.am.service.VaultService.addToFolder(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.am.service.VaultService.addToFolder(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.am.service.VaultService.addToFolder(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.am.service.VaultService.addToFolder(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleAddToFolder(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Account) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.VaultService.class).
			warn ("Error on VaultService.addToFolder", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on VaultService.addToFolder", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Account handleAddToFolder(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.VaultService#	 * @see com.soffid.iam.am.service.VaultService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.VaultFolder> findFolders(com.soffid.zkdb.api.Query q)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.VaultFolder> findFolders(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (q == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.VaultFolder> com.soffid.iam.am.service.VaultService.findFolders(com.soffid.zkdb.api.Query q) - q cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindFolders(q)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.VaultFolder>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.VaultService.class).
			warn ("Error on VaultService.findFolders", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on VaultService.findFolders", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.VaultFolder> handleFindFolders(com.soffid.zkdb.api.Query q) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.VaultService#	 * @see com.soffid.iam.am.service.VaultService#java.util.List<com.soffid.iam.base.api.Account> findAccounts(java.lang.String filter)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.base.api.Account> findAccounts(
		final java.lang.String filter)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAccounts(filter)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.base.api.Account>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.VaultService.class).
			warn ("Error on VaultService.findAccounts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on VaultService.findAccounts", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.base.api.Account> handleFindAccounts(java.lang.String filter) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.VaultService#	 * @see com.soffid.iam.am.service.VaultService#java.util.List<com.soffid.iam.am.api.VaultFolder> findFolders(java.lang.String filter)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.am.api.VaultFolder> findFolders(
		final java.lang.String filter)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindFolders(filter)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.am.api.VaultFolder>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.VaultService.class).
			warn ("Error on VaultService.findFolders", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on VaultService.findFolders", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.am.api.VaultFolder> handleFindFolders(java.lang.String filter) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.VaultService#	 * @see com.soffid.iam.am.service.VaultService#java.util.List<com.soffid.iam.am.api.VaultElement> findVaultElementByText(java.lang.String filter)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.am.api.VaultElement> findVaultElementByText(
		final java.lang.String filter)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindVaultElementByText(filter)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.am.api.VaultElement>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.VaultService.class).
			warn ("Error on VaultService.findVaultElementByText", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on VaultService.findVaultElementByText", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.am.api.VaultElement> handleFindVaultElementByText(java.lang.String filter) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.VaultService#	 * @see com.soffid.iam.am.service.VaultService#java.util.List<com.soffid.iam.am.api.VaultElement> getChildren(com.soffid.iam.am.api.VaultElement parent)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.am.api.VaultElement> getChildren(
		final com.soffid.iam.am.api.VaultElement parent)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (parent == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.VaultElement> com.soffid.iam.am.service.VaultService.getChildren(com.soffid.iam.am.api.VaultElement parent) - parent cannot be null");
		}
		if (parent.getType() == null || parent.getType().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.VaultElement> com.soffid.iam.am.service.VaultService.getChildren(com.soffid.iam.am.api.VaultElement parent) - parent.type cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetChildren(parent)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.am.api.VaultElement>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.VaultService.class).
			warn ("Error on VaultService.getChildren", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on VaultService.getChildren", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.am.api.VaultElement> handleGetChildren(com.soffid.iam.am.api.VaultElement parent) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.VaultService#	 * @see com.soffid.iam.am.service.VaultService#java.util.List<com.soffid.iam.am.api.VaultFolder> getChildren(com.soffid.iam.am.api.VaultFolder parent)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.am.api.VaultFolder> getChildren(
		final com.soffid.iam.am.api.VaultFolder parent)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (parent == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.VaultFolder> com.soffid.iam.am.service.VaultService.getChildren(com.soffid.iam.am.api.VaultFolder parent) - parent cannot be null");
		}
		if (parent.getName() == null || parent.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.VaultFolder> com.soffid.iam.am.service.VaultService.getChildren(com.soffid.iam.am.api.VaultFolder parent) - parent.name cannot be null");
		}
		if (parent.getDescription() == null || parent.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.VaultFolder> com.soffid.iam.am.service.VaultService.getChildren(com.soffid.iam.am.api.VaultFolder parent) - parent.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetChildren(parent)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.am.api.VaultFolder>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.VaultService.class).
			warn ("Error on VaultService.getChildren", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on VaultService.getChildren", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.am.api.VaultFolder> handleGetChildren(com.soffid.iam.am.api.VaultFolder parent) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.VaultService#	 * @see com.soffid.iam.am.service.VaultService#java.util.List<com.soffid.iam.am.api.VaultFolder> getPublicRootFolders()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.am.api.VaultFolder> getPublicRootFolders()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetPublicRootFolders()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.am.api.VaultFolder>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.VaultService.class).
			warn ("Error on VaultService.getPublicRootFolders", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on VaultService.getPublicRootFolders", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.am.api.VaultFolder> handleGetPublicRootFolders() throws Exception;

	/**
	 * @see com.soffid.iam.am.service.VaultService#	 * @see com.soffid.iam.am.service.VaultService#java.util.List<com.soffid.iam.am.api.VaultFolder> getRootFolders()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.am.api.VaultFolder> getRootFolders()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetRootFolders()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.am.api.VaultFolder>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.VaultService.class).
			warn ("Error on VaultService.getRootFolders", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on VaultService.getRootFolders", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.am.api.VaultFolder> handleGetRootFolders() throws Exception;

	/**
	 * @see com.soffid.iam.am.service.VaultService#	 * @see com.soffid.iam.am.service.VaultService#java.util.List<com.soffid.iam.base.api.Account> list(com.soffid.iam.am.api.VaultFolder folder)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.base.api.Account> list(
		final com.soffid.iam.am.api.VaultFolder folder)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (folder == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.Account> com.soffid.iam.am.service.VaultService.list(com.soffid.iam.am.api.VaultFolder folder) - folder cannot be null");
		}
		if (folder.getName() == null || folder.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.Account> com.soffid.iam.am.service.VaultService.list(com.soffid.iam.am.api.VaultFolder folder) - folder.name cannot be null");
		}
		if (folder.getDescription() == null || folder.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.Account> com.soffid.iam.am.service.VaultService.list(com.soffid.iam.am.api.VaultFolder folder) - folder.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleList(folder)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.base.api.Account>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.VaultService.class).
			warn ("Error on VaultService.list", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on VaultService.list", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.base.api.Account> handleList(com.soffid.iam.am.api.VaultFolder folder) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.VaultService#	 * @see com.soffid.iam.am.service.VaultService#void applyFolderPermissions(com.soffid.iam.am.api.VaultFolderPermissions permissions)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void applyFolderPermissions(
		final com.soffid.iam.am.api.VaultFolderPermissions permissions)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (permissions == null) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.VaultService.applyFolderPermissions(com.soffid.iam.am.api.VaultFolderPermissions permissions) - permissions cannot be null");
		}
		if (permissions.getVaultId() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.VaultService.applyFolderPermissions(com.soffid.iam.am.api.VaultFolderPermissions permissions) - permissions.vaultId cannot be null");
		}
		if (permissions.getGrantee() == null || permissions.getGrantee().isEmpty()) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.VaultService.applyFolderPermissions(com.soffid.iam.am.api.VaultFolderPermissions permissions) - permissions.grantee cannot be empty");
		}
		if (permissions.getAccounts() == null || permissions.getAccounts().isEmpty()) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.VaultService.applyFolderPermissions(com.soffid.iam.am.api.VaultFolderPermissions permissions) - permissions.accounts cannot be empty");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleApplyFolderPermissions(permissions);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.VaultService.class).
			warn ("Error on VaultService.applyFolderPermissions", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on VaultService.applyFolderPermissions", (Throwable) __r[1]);
	}

	protected abstract void handleApplyFolderPermissions(com.soffid.iam.am.api.VaultFolderPermissions permissions) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.VaultService#	 * @see com.soffid.iam.am.service.VaultService#void remove(com.soffid.iam.am.api.VaultElement folder)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void remove(
		final com.soffid.iam.am.api.VaultElement folder)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (folder == null) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.VaultService.remove(com.soffid.iam.am.api.VaultElement folder) - folder cannot be null");
		}
		if (folder.getType() == null || folder.getType().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.VaultService.remove(com.soffid.iam.am.api.VaultElement folder) - folder.type cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRemove(folder);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.VaultService.class).
			warn ("Error on VaultService.remove", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on VaultService.remove", (Throwable) __r[1]);
	}

	protected abstract void handleRemove(com.soffid.iam.am.api.VaultElement folder) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.VaultService#	 * @see com.soffid.iam.am.service.VaultService#void remove(com.soffid.iam.am.api.VaultFolder folder)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void remove(
		final com.soffid.iam.am.api.VaultFolder folder)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (folder == null) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.VaultService.remove(com.soffid.iam.am.api.VaultFolder folder) - folder cannot be null");
		}
		if (folder.getName() == null || folder.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.VaultService.remove(com.soffid.iam.am.api.VaultFolder folder) - folder.name cannot be null");
		}
		if (folder.getDescription() == null || folder.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.VaultService.remove(com.soffid.iam.am.api.VaultFolder folder) - folder.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRemove(folder);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.VaultService.class).
			warn ("Error on VaultService.remove", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on VaultService.remove", (Throwable) __r[1]);
	}

	protected abstract void handleRemove(com.soffid.iam.am.api.VaultFolder folder) throws Exception;

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
