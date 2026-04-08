//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.rc.service.SamlService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.rc.service.SamlService
 */
public abstract class SamlServiceBase
	implements com.soffid.iam.rc.service.SamlService
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

	private com.soffid.iam.am.model.SamlAssertionEntityDao samlAssertionEntityDao;

	/**
	 * Sets reference to <code>samlAssertionEntityDao</code>.
	 */
	public void setSamlAssertionEntityDao (com.soffid.iam.am.model.SamlAssertionEntityDao samlAssertionEntityDao) {
		this.samlAssertionEntityDao = samlAssertionEntityDao;
	}

	/**
	 * Gets reference to <code>samlAssertionEntityDao</code>.
	 */
	public com.soffid.iam.am.model.SamlAssertionEntityDao getSamlAssertionEntityDao () {
		return samlAssertionEntityDao;
	}

	private com.soffid.iam.am.model.SamlRequestEntityDao samlRequestEntityDao;

	/**
	 * Sets reference to <code>samlRequestEntityDao</code>.
	 */
	public void setSamlRequestEntityDao (com.soffid.iam.am.model.SamlRequestEntityDao samlRequestEntityDao) {
		this.samlRequestEntityDao = samlRequestEntityDao;
	}

	/**
	 * Gets reference to <code>samlRequestEntityDao</code>.
	 */
	public com.soffid.iam.am.model.SamlRequestEntityDao getSamlRequestEntityDao () {
		return samlRequestEntityDao;
	}


	/**
	 * @see com.soffid.iam.rc.service.SamlService#	 * @see com.soffid.iam.rc.service.SamlService#com.soffid.iam.am.api.SamlRequest generateLogoutRequest(java.lang.String hostName, java.lang.String user)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.SamlRequest generateLogoutRequest(
		final java.lang.String hostName, 
		final java.lang.String user)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (hostName == null || hostName.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.SamlRequest com.soffid.iam.rc.service.SamlService.generateLogoutRequest(java.lang.String hostName, java.lang.String user) - hostName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGenerateLogoutRequest(hostName, user)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.SamlRequest) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SamlService.class).
			warn ("Error on SamlService.generateLogoutRequest", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SamlService.generateLogoutRequest", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.SamlRequest handleGenerateLogoutRequest(java.lang.String hostName, java.lang.String user) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SamlService#	 * @see com.soffid.iam.rc.service.SamlService#com.soffid.iam.am.api.SamlRequest generateSamlRequest(java.lang.String hostName, java.lang.String app)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.SamlRequest generateSamlRequest(
		final java.lang.String hostName, 
		final java.lang.String app)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (hostName == null || hostName.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.SamlRequest com.soffid.iam.rc.service.SamlService.generateSamlRequest(java.lang.String hostName, java.lang.String app) - hostName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGenerateSamlRequest(hostName, app)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.SamlRequest) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SamlService.class).
			warn ("Error on SamlService.generateSamlRequest", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SamlService.generateSamlRequest", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.SamlRequest handleGenerateSamlRequest(java.lang.String hostName, java.lang.String app) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SamlService#	 * @see com.soffid.iam.rc.service.SamlService#java.lang.String checkAuthenticationToken(java.lang.String[] token)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String checkAuthenticationToken(
		final java.lang.String[] token)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (token == null ) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.rc.service.SamlService.checkAuthenticationToken(java.lang.String[] token) - token cannot be empty");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCheckAuthenticationToken(token)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SamlService.class).
			warn ("Error on SamlService.checkAuthenticationToken", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SamlService.checkAuthenticationToken", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleCheckAuthenticationToken(java.lang.String[] token) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SamlService#	 * @see com.soffid.iam.rc.service.SamlService#java.lang.String generateMetadata(java.lang.String hostName)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String generateMetadata(
		final java.lang.String hostName)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (hostName == null || hostName.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.rc.service.SamlService.generateMetadata(java.lang.String hostName) - hostName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGenerateMetadata(hostName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SamlService.class).
			warn ("Error on SamlService.generateMetadata", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SamlService.generateMetadata", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGenerateMetadata(java.lang.String hostName) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SamlService#	 * @see com.soffid.iam.rc.service.SamlService#java.lang.String validateOpenidToken(java.lang.String token)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String validateOpenidToken(
		final java.lang.String token)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (token == null || token.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.rc.service.SamlService.validateOpenidToken(java.lang.String token) - token cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleValidateOpenidToken(token)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SamlService.class).
			warn ("Error on SamlService.validateOpenidToken", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SamlService.validateOpenidToken", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleValidateOpenidToken(java.lang.String token) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SamlService#	 * @see com.soffid.iam.rc.service.SamlService#java.lang.String[] authenticate(java.lang.String hostName, java.lang.String app, java.lang.String protocol, java.util.Map<java.lang.String,java.lang.String> response)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String[] authenticate(
		final java.lang.String hostName, 
		final java.lang.String app, 
		final java.lang.String protocol, 
		final java.util.Map<java.lang.String,java.lang.String> response)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (hostName == null || hostName.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String[] com.soffid.iam.rc.service.SamlService.authenticate(java.lang.String hostName, java.lang.String app, java.lang.String protocol, java.util.Map<java.lang.String,java.lang.String> response) - hostName cannot be null");
		}
		if (protocol == null || protocol.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String[] com.soffid.iam.rc.service.SamlService.authenticate(java.lang.String hostName, java.lang.String app, java.lang.String protocol, java.util.Map<java.lang.String,java.lang.String> response) - protocol cannot be null");
		}
		if (response == null) {
			throw new IllegalArgumentException("java.lang.String[] com.soffid.iam.rc.service.SamlService.authenticate(java.lang.String hostName, java.lang.String app, java.lang.String protocol, java.util.Map<java.lang.String,java.lang.String> response) - response cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleAuthenticate(hostName, app, protocol, response)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SamlService.class).
			warn ("Error on SamlService.authenticate", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SamlService.authenticate", (Throwable) __r[1]);
	}

	protected abstract java.lang.String[] handleAuthenticate(java.lang.String hostName, java.lang.String app, java.lang.String protocol, java.util.Map<java.lang.String,java.lang.String> response) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SamlService#	 * @see com.soffid.iam.rc.service.SamlService#java.util.List<java.lang.String> findIdentityProviders()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<java.lang.String> findIdentityProviders()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindIdentityProviders()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SamlService.class).
			warn ("Error on SamlService.findIdentityProviders", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SamlService.findIdentityProviders", (Throwable) __r[1]);
	}

	protected abstract java.util.List<java.lang.String> handleFindIdentityProviders() throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SamlService#	 * @see com.soffid.iam.rc.service.SamlService#java.util.List<java.lang.String> findIdentityProviders(java.lang.String url)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<java.lang.String> findIdentityProviders(
		final java.lang.String url)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (url == null || url.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<java.lang.String> com.soffid.iam.rc.service.SamlService.findIdentityProviders(java.lang.String url) - url cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindIdentityProviders(url)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SamlService.class).
			warn ("Error on SamlService.findIdentityProviders", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SamlService.findIdentityProviders", (Throwable) __r[1]);
	}

	protected abstract java.util.List<java.lang.String> handleFindIdentityProviders(java.lang.String url) throws Exception;

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
