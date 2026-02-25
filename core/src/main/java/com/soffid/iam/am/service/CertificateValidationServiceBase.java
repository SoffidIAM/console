//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.am.service.CertificateValidationService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.am.service.CertificateValidationService
 */
public abstract class CertificateValidationServiceBase
	implements com.soffid.iam.am.service.CertificateValidationService
 {

	/**
	 * @see com.soffid.iam.am.service.CertificateValidationService#	 * @see com.soffid.iam.am.service.CertificateValidationService#boolean validateCertificate(java.util.List<java.security.cert.X509Certificate> certs)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean validateCertificate(
		final java.util.List<java.security.cert.X509Certificate> certs)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (certs == null ) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.CertificateValidationService.validateCertificate(java.util.List<java.security.cert.X509Certificate> certs) - certs cannot be empty");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleValidateCertificate(certs)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.CertificateValidationService.class).
			warn ("Error on CertificateValidationService.validateCertificate", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CertificateValidationService.validateCertificate", (Throwable) __r[1]);
	}

	protected abstract boolean handleValidateCertificate(java.util.List<java.security.cert.X509Certificate> certs) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.CertificateValidationService#	 * @see com.soffid.iam.am.service.CertificateValidationService#com.soffid.iam.base.api.Account getCertificateAccount(java.util.List<java.security.cert.X509Certificate> certs)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Account getCertificateAccount(
		final java.util.List<java.security.cert.X509Certificate> certs)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (certs == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.am.service.CertificateValidationService.getCertificateAccount(java.util.List<java.security.cert.X509Certificate> certs) - certs cannot be empty");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetCertificateAccount(certs)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Account) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.CertificateValidationService.class).
			warn ("Error on CertificateValidationService.getCertificateAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CertificateValidationService.getCertificateAccount", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Account handleGetCertificateAccount(java.util.List<java.security.cert.X509Certificate> certs) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.CertificateValidationService#	 * @see com.soffid.iam.am.service.CertificateValidationService#com.soffid.iam.base.api.User getCertificateUser(java.util.List<java.security.cert.X509Certificate> certs)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.User getCertificateUser(
		final java.util.List<java.security.cert.X509Certificate> certs)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (certs == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.am.service.CertificateValidationService.getCertificateUser(java.util.List<java.security.cert.X509Certificate> certs) - certs cannot be empty");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetCertificateUser(certs)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.User) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.CertificateValidationService.class).
			warn ("Error on CertificateValidationService.getCertificateUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CertificateValidationService.getCertificateUser", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.User handleGetCertificateUser(java.util.List<java.security.cert.X509Certificate> certs) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.CertificateValidationService#	 * @see com.soffid.iam.am.service.CertificateValidationService#java.util.Collection<java.security.cert.X509Certificate> getRootCertificateList()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<java.security.cert.X509Certificate> getRootCertificateList()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetRootCertificateList()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<java.security.cert.X509Certificate>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.CertificateValidationService.class).
			warn ("Error on CertificateValidationService.getRootCertificateList", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CertificateValidationService.getRootCertificateList", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<java.security.cert.X509Certificate> handleGetRootCertificateList() throws Exception;

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
