//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.base.service.PreferencesService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.base.service.PreferencesService
 */
public abstract class PreferencesServiceBase
	implements com.soffid.iam.base.service.PreferencesService
 {
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

	private com.soffid.iam.base.model.UserPreferenceEntityDao userPreferenceEntityDao;

	/**
	 * Sets reference to <code>userPreferenceEntityDao</code>.
	 */
	public void setUserPreferenceEntityDao (com.soffid.iam.base.model.UserPreferenceEntityDao userPreferenceEntityDao) {
		this.userPreferenceEntityDao = userPreferenceEntityDao;
	}

	/**
	 * Gets reference to <code>userPreferenceEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserPreferenceEntityDao getUserPreferenceEntityDao () {
		return userPreferenceEntityDao;
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
	 * @see com.soffid.iam.base.service.PreferencesService#	 * @see com.soffid.iam.base.service.PreferencesService#java.lang.String findMyPreference(java.lang.String name)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String findMyPreference(
		final java.lang.String name)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.base.service.PreferencesService.findMyPreference(java.lang.String name) - name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindMyPreference(name)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.PreferencesService.class).
			warn ("Error on PreferencesService.findMyPreference", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PreferencesService.findMyPreference", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleFindMyPreference(java.lang.String name) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.PreferencesService#	 * @see com.soffid.iam.base.service.PreferencesService#java.util.Map<java.lang.String,java.lang.String> findUserPreferences(java.lang.String user)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Map<java.lang.String,java.lang.String> findUserPreferences(
		final java.lang.String user)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Map<java.lang.String,java.lang.String> com.soffid.iam.base.service.PreferencesService.findUserPreferences(java.lang.String user) - user cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserPreferences(user)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Map<java.lang.String,java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.PreferencesService.class).
			warn ("Error on PreferencesService.findUserPreferences", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PreferencesService.findUserPreferences", (Throwable) __r[1]);
	}

	protected abstract java.util.Map<java.lang.String,java.lang.String> handleFindUserPreferences(java.lang.String user) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.PreferencesService#	 * @see com.soffid.iam.base.service.PreferencesService#java.util.Map<java.lang.String,java.lang.String> setUserPreferences(java.lang.String user, java.util.Map<java.lang.String,java.lang.String> preferences)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Map<java.lang.String,java.lang.String> setUserPreferences(
		final java.lang.String user, 
		final java.util.Map<java.lang.String,java.lang.String> preferences)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Map<java.lang.String,java.lang.String> com.soffid.iam.base.service.PreferencesService.setUserPreferences(java.lang.String user, java.util.Map<java.lang.String,java.lang.String> preferences) - user cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleSetUserPreferences(user, preferences)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Map<java.lang.String,java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.PreferencesService.class).
			warn ("Error on PreferencesService.setUserPreferences", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PreferencesService.setUserPreferences", (Throwable) __r[1]);
	}

	protected abstract java.util.Map<java.lang.String,java.lang.String> handleSetUserPreferences(java.lang.String user, java.util.Map<java.lang.String,java.lang.String> preferences) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.PreferencesService#	 * @see com.soffid.iam.base.service.PreferencesService#void setUserPreference(java.lang.String user, java.lang.String preference, java.lang.String value)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void setUserPreference(
		final java.lang.String user, 
		final java.lang.String preference, 
		final java.lang.String value)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.PreferencesService.setUserPreference(java.lang.String user, java.lang.String preference, java.lang.String value) - user cannot be null");
		}
		if (preference == null || preference.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.PreferencesService.setUserPreference(java.lang.String user, java.lang.String preference, java.lang.String value) - preference cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSetUserPreference(user, preference, value);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.PreferencesService.class).
			warn ("Error on PreferencesService.setUserPreference", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PreferencesService.setUserPreference", (Throwable) __r[1]);
	}

	protected abstract void handleSetUserPreference(java.lang.String user, java.lang.String preference, java.lang.String value) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.PreferencesService#	 * @see com.soffid.iam.base.service.PreferencesService#void updateMyPreference(java.lang.String name, java.lang.String value)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void updateMyPreference(
		final java.lang.String name, 
		final java.lang.String value)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.PreferencesService.updateMyPreference(java.lang.String name, java.lang.String value) - name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpdateMyPreference(name, value);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.PreferencesService.class).
			warn ("Error on PreferencesService.updateMyPreference", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PreferencesService.updateMyPreference", (Throwable) __r[1]);
	}

	protected abstract void handleUpdateMyPreference(java.lang.String name, java.lang.String value) throws Exception;

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
