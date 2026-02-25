//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.rc.service.NetworkIntelligenceService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.rc.service.NetworkIntelligenceService
 */
public abstract class NetworkIntelligenceServiceBase
	implements com.soffid.iam.rc.service.NetworkIntelligenceService
 {
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

	private com.soffid.iam.iga.service.MailListsService mailListsService;

	/**
	 * Sets reference to <code>mailListsService</code>.
	 */
	public void setMailListsService (com.soffid.iam.iga.service.MailListsService mailListsService) {
		this.mailListsService = mailListsService;
	}

	/**
	 * Gets reference to <code>mailListsService</code>.
	 */
	public com.soffid.iam.iga.service.MailListsService getMailListsService () {
		return mailListsService;
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
	 * @see com.soffid.iam.rc.service.NetworkIntelligenceService#	 * @see com.soffid.iam.rc.service.NetworkIntelligenceService#com.soffid.iam.rc.api.NetworkIntelligence getConfiguration()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.rc.api.NetworkIntelligence getConfiguration()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetConfiguration()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.rc.api.NetworkIntelligence) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.NetworkIntelligenceService.class).
			warn ("Error on NetworkIntelligenceService.getConfiguration", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkIntelligenceService.getConfiguration", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.rc.api.NetworkIntelligence handleGetConfiguration() throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.NetworkIntelligenceService#	 * @see com.soffid.iam.rc.service.NetworkIntelligenceService#com.soffid.iam.rc.api.NetworkIntelligence saveConfiguration(com.soffid.iam.rc.api.NetworkIntelligence ni)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.rc.api.NetworkIntelligence saveConfiguration(
		final com.soffid.iam.rc.api.NetworkIntelligence ni)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleSaveConfiguration(ni)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.rc.api.NetworkIntelligence) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.NetworkIntelligenceService.class).
			warn ("Error on NetworkIntelligenceService.saveConfiguration", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkIntelligenceService.saveConfiguration", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.rc.api.NetworkIntelligence handleSaveConfiguration(com.soffid.iam.rc.api.NetworkIntelligence ni) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.NetworkIntelligenceService#	 * @see com.soffid.iam.rc.service.NetworkIntelligenceService#com.soffid.iam.rc.api.NetworkIntelligence validateToken(java.lang.String token)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.rc.api.NetworkIntelligence validateToken(
		final java.lang.String token)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (token == null || token.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.NetworkIntelligence com.soffid.iam.rc.service.NetworkIntelligenceService.validateToken(java.lang.String token) - token cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleValidateToken(token)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.rc.api.NetworkIntelligence) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.NetworkIntelligenceService.class).
			warn ("Error on NetworkIntelligenceService.validateToken", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkIntelligenceService.validateToken", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.rc.api.NetworkIntelligence handleValidateToken(java.lang.String token) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.NetworkIntelligenceService#	 * @see com.soffid.iam.rc.service.NetworkIntelligenceService#java.lang.Boolean isAccountBreached(java.lang.String account, java.lang.String system)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.Boolean isAccountBreached(
		final java.lang.String account, 
		final java.lang.String system)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (account == null || account.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.Boolean com.soffid.iam.rc.service.NetworkIntelligenceService.isAccountBreached(java.lang.String account, java.lang.String system) - account cannot be null");
		}
		if (system == null || system.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.Boolean com.soffid.iam.rc.service.NetworkIntelligenceService.isAccountBreached(java.lang.String account, java.lang.String system) - system cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsAccountBreached(account, system)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.Boolean) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.NetworkIntelligenceService.class).
			warn ("Error on NetworkIntelligenceService.isAccountBreached", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkIntelligenceService.isAccountBreached", (Throwable) __r[1]);
	}

	protected abstract java.lang.Boolean handleIsAccountBreached(java.lang.String account, java.lang.String system) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.NetworkIntelligenceService#	 * @see com.soffid.iam.rc.service.NetworkIntelligenceService#java.lang.Boolean isPasswordBreached(java.lang.String password)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.Boolean isPasswordBreached(
		final java.lang.String password)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (password == null || password.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.Boolean com.soffid.iam.rc.service.NetworkIntelligenceService.isPasswordBreached(java.lang.String password) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsPasswordBreached(password)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.Boolean) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.NetworkIntelligenceService.class).
			warn ("Error on NetworkIntelligenceService.isPasswordBreached", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkIntelligenceService.isPasswordBreached", (Throwable) __r[1]);
	}

	protected abstract java.lang.Boolean handleIsPasswordBreached(java.lang.String password) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.NetworkIntelligenceService#	 * @see com.soffid.iam.rc.service.NetworkIntelligenceService#java.lang.String isEmailBreached(java.lang.String shortName, java.lang.String mailDomain)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String isEmailBreached(
		final java.lang.String shortName, 
		final java.lang.String mailDomain)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (shortName == null || shortName.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.rc.service.NetworkIntelligenceService.isEmailBreached(java.lang.String shortName, java.lang.String mailDomain) - shortName cannot be null");
		}
		if (mailDomain == null || mailDomain.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.rc.service.NetworkIntelligenceService.isEmailBreached(java.lang.String shortName, java.lang.String mailDomain) - mailDomain cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsEmailBreached(shortName, mailDomain)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.NetworkIntelligenceService.class).
			warn ("Error on NetworkIntelligenceService.isEmailBreached", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkIntelligenceService.isEmailBreached", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleIsEmailBreached(java.lang.String shortName, java.lang.String mailDomain) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.NetworkIntelligenceService#	 * @see com.soffid.iam.rc.service.NetworkIntelligenceService#void verifyDomains(java.io.PrintWriter out)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void verifyDomains(
		final java.io.PrintWriter out)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (out == null) {
			throw new IllegalArgumentException("void com.soffid.iam.rc.service.NetworkIntelligenceService.verifyDomains(java.io.PrintWriter out) - out cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleVerifyDomains(out);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.NetworkIntelligenceService.class).
			warn ("Error on NetworkIntelligenceService.verifyDomains", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on NetworkIntelligenceService.verifyDomains", (Throwable) __r[1]);
	}

	protected abstract void handleVerifyDomains(java.io.PrintWriter out) throws Exception;

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
