//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.base.service.LicenseService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.base.service.LicenseService
 */
public abstract class LicenseServiceBase
	implements com.soffid.iam.base.service.LicenseService
 {
	private com.soffid.iam.base.model.ConfigEntityDao configEntityDao;

	/**
	 * Sets reference to <code>configEntityDao</code>.
	 */
	public void setConfigEntityDao (com.soffid.iam.base.model.ConfigEntityDao configEntityDao) {
		this.configEntityDao = configEntityDao;
	}

	/**
	 * Gets reference to <code>configEntityDao</code>.
	 */
	public com.soffid.iam.base.model.ConfigEntityDao getConfigEntityDao () {
		return configEntityDao;
	}

	private com.soffid.iam.base.model.SoffidLicenseEntityDao soffidLicenseEntityDao;

	/**
	 * Sets reference to <code>soffidLicenseEntityDao</code>.
	 */
	public void setSoffidLicenseEntityDao (com.soffid.iam.base.model.SoffidLicenseEntityDao soffidLicenseEntityDao) {
		this.soffidLicenseEntityDao = soffidLicenseEntityDao;
	}

	/**
	 * Gets reference to <code>soffidLicenseEntityDao</code>.
	 */
	public com.soffid.iam.base.model.SoffidLicenseEntityDao getSoffidLicenseEntityDao () {
		return soffidLicenseEntityDao;
	}


	/**
	 * @see com.soffid.iam.base.service.LicenseService#	 * @see com.soffid.iam.base.service.LicenseService#java.lang.String getApplicationToken()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String getApplicationToken()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetApplicationToken()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.LicenseService.class).
			warn ("Error on LicenseService.getApplicationToken", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on LicenseService.getApplicationToken", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGetApplicationToken() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.LicenseService#	 * @see com.soffid.iam.base.service.LicenseService#java.util.Date getExpirationDate()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Date getExpirationDate()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetExpirationDate()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Date) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.LicenseService.class).
			warn ("Error on LicenseService.getExpirationDate", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on LicenseService.getExpirationDate", (Throwable) __r[1]);
	}

	protected abstract java.util.Date handleGetExpirationDate() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.LicenseService#	 * @see com.soffid.iam.base.service.LicenseService#java.util.List<java.lang.String> findEnabledProducts()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<java.lang.String> findEnabledProducts()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindEnabledProducts()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.LicenseService.class).
			warn ("Error on LicenseService.findEnabledProducts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on LicenseService.findEnabledProducts", (Throwable) __r[1]);
	}

	protected abstract java.util.List<java.lang.String> handleFindEnabledProducts() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.LicenseService#	 * @see com.soffid.iam.base.service.LicenseService#void installLicense(java.lang.String license)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void installLicense(
		final java.lang.String license)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (license == null || license.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.LicenseService.installLicense(java.lang.String license) - license cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleInstallLicense(license);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.LicenseService.class).
			warn ("Error on LicenseService.installLicense", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on LicenseService.installLicense", (Throwable) __r[1]);
	}

	protected abstract void handleInstallLicense(java.lang.String license) throws Exception;

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
