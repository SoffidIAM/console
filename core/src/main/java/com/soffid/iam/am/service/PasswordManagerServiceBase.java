//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.am.service.PasswordManagerService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.am.service.PasswordManagerService
 */
public abstract class PasswordManagerServiceBase
	implements com.soffid.iam.am.service.PasswordManagerService
 {
	private com.soffid.iam.am.model.PasswordManagerTokenEntityDao passwordManagerTokenEntityDao;

	/**
	 * Sets reference to <code>passwordManagerTokenEntityDao</code>.
	 */
	public void setPasswordManagerTokenEntityDao (com.soffid.iam.am.model.PasswordManagerTokenEntityDao passwordManagerTokenEntityDao) {
		this.passwordManagerTokenEntityDao = passwordManagerTokenEntityDao;
	}

	/**
	 * Gets reference to <code>passwordManagerTokenEntityDao</code>.
	 */
	public com.soffid.iam.am.model.PasswordManagerTokenEntityDao getPasswordManagerTokenEntityDao () {
		return passwordManagerTokenEntityDao;
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
	 * @see com.soffid.iam.am.service.PasswordManagerService#	 * @see com.soffid.iam.am.service.PasswordManagerService#java.lang.String findUserByToken(java.lang.String token)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String findUserByToken(
		final java.lang.String token)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (token == null || token.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.am.service.PasswordManagerService.findUserByToken(java.lang.String token) - token cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserByToken(token)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.PasswordManagerService.class).
			warn ("Error on PasswordManagerService.findUserByToken", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PasswordManagerService.findUserByToken", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleFindUserByToken(java.lang.String token) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.PasswordManagerService#	 * @see com.soffid.iam.am.service.PasswordManagerService#java.lang.String generateToken(java.lang.String user)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String generateToken(
		final java.lang.String user)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.am.service.PasswordManagerService.generateToken(java.lang.String user) - user cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGenerateToken(user)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.PasswordManagerService.class).
			warn ("Error on PasswordManagerService.generateToken", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PasswordManagerService.generateToken", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGenerateToken(java.lang.String user) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.PasswordManagerService#	 * @see com.soffid.iam.am.service.PasswordManagerService#java.lang.String renewToken(java.lang.String token)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String renewToken(
		final java.lang.String token)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (token == null || token.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.am.service.PasswordManagerService.renewToken(java.lang.String token) - token cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleRenewToken(token)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.PasswordManagerService.class).
			warn ("Error on PasswordManagerService.renewToken", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PasswordManagerService.renewToken", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleRenewToken(java.lang.String token) throws Exception;

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
