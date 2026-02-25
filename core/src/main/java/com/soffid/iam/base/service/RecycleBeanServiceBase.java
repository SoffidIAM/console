//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.base.service.RecycleBeanService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.base.service.RecycleBeanService
 */
public abstract class RecycleBeanServiceBase
	implements com.soffid.iam.base.service.RecycleBeanService
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

	private com.soffid.iam.iga.model.CustomObjectEntityDao customObjectEntityDao;

	/**
	 * Sets reference to <code>customObjectEntityDao</code>.
	 */
	public void setCustomObjectEntityDao (com.soffid.iam.iga.model.CustomObjectEntityDao customObjectEntityDao) {
		this.customObjectEntityDao = customObjectEntityDao;
	}

	/**
	 * Gets reference to <code>customObjectEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.CustomObjectEntityDao getCustomObjectEntityDao () {
		return customObjectEntityDao;
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

	private com.soffid.iam.iga.model.MailListEntityDao mailListEntityDao;

	/**
	 * Sets reference to <code>mailListEntityDao</code>.
	 */
	public void setMailListEntityDao (com.soffid.iam.iga.model.MailListEntityDao mailListEntityDao) {
		this.mailListEntityDao = mailListEntityDao;
	}

	/**
	 * Gets reference to <code>mailListEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.MailListEntityDao getMailListEntityDao () {
		return mailListEntityDao;
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


	/**
	 * @see com.soffid.iam.base.service.RecycleBeanService#	 * @see com.soffid.iam.base.service.RecycleBeanService#void purge(java.util.Date date)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void purge(
		final java.util.Date date)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (date == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.RecycleBeanService.purge(java.util.Date date) - date cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handlePurge(date);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.RecycleBeanService.class).
			warn ("Error on RecycleBeanService.purge", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on RecycleBeanService.purge", (Throwable) __r[1]);
	}

	protected abstract void handlePurge(java.util.Date date) throws Exception;

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
