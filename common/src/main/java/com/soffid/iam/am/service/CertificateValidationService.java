//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service;
/**
 * Service CertificateValidationService
 */
public interface CertificateValidationService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.am.service.CertificateValidationService";

	public final static String SERVICE_NAME = "com.soffid.iam.am.service.CertificateValidationService";

	/**
	 * Operation validateCertificate

	 * @param certs 
	 * @return 
	 */
	boolean validateCertificate(
		final java.util.List<java.security.cert.X509Certificate> certs)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getCertificateAccount

	 * @param certs 
	 * @return 
	 */
	com.soffid.iam.base.api.Account getCertificateAccount(
		final java.util.List<java.security.cert.X509Certificate> certs)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getCertificateUser

	 * @param certs 
	 * @return 
	 */
	com.soffid.iam.base.api.User getCertificateUser(
		final java.util.List<java.security.cert.X509Certificate> certs)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getRootCertificateList

	 * @return 
	 */
	java.util.Collection<java.security.cert.X509Certificate> getRootCertificateList()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
