//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service.ejb;
/**
 * EJB CertificateValidationService
 */
public interface CertificateValidationService

 {

	boolean validateCertificate(
		final java.util.List<java.security.cert.X509Certificate> certs)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.Account getCertificateAccount(
		final java.util.List<java.security.cert.X509Certificate> certs)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.User getCertificateUser(
		final java.util.List<java.security.cert.X509Certificate> certs)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<java.security.cert.X509Certificate> getRootCertificateList()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
