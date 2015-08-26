/**
 * 
 */
package com.soffid.iam.service;

/**
 * @author bubu
 *
 */
public interface CertificateValidationModule
{

	/**
	 * Operation getRootCertificateList

	 * @return 
	 */
	java.util.Collection<java.security.cert.X509Certificate> getRootCertificateList()
			throws es.caib.seycon.ng.exception.InternalErrorException;

	/**
	 * Operation validateCertificate

	 * @param certs 
	 * @return 
	 */
	boolean validateCertificate(
		java.util.List<java.security.cert.X509Certificate> certs)
			throws es.caib.seycon.ng.exception.InternalErrorException;

	/**
	 * Operation getCertificateUser

	 * @param certs 
	 * @return 
	 */
	com.soffid.iam.api.User getCertificateUser(java.util.List<java.security.cert.X509Certificate> certs) throws es.caib.seycon.ng.exception.InternalErrorException;

	/**
	 * Operation getCertificateAccount

	 * @param certs 
	 * @return 
	 */
	com.soffid.iam.api.Account getCertificateAccount(java.util.List<java.security.cert.X509Certificate> certs) throws es.caib.seycon.ng.exception.InternalErrorException;


}
