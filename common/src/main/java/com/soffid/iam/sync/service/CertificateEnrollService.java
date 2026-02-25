//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.service;
/**
 * Service CertificateEnrollService
 */
public interface CertificateEnrollService {
	public final static String REMOTE_PATH = "/seycon/CertificateEnrollService";

	public final static String SERVICE_NAME = "com.soffid.iam.sync.service.CertificateEnrollService";

	/**
	 * Operation getServerPort

	 * @return 
	 */
	int getServerPort()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getServerList

	 * @return 
	 */
	java.lang.String getServerList()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getCertificate

	 * @param tenant 
	 * @param user 
	 * @param password 
	 * @param domain 
	 * @param hostName 
	 * @param request 
	 * @return 
	 */
	java.security.cert.X509Certificate getCertificate(
		final java.lang.String tenant, 
		final java.lang.String user, 
		final java.lang.String password, 
		final java.lang.String domain, 
		final java.lang.String hostName, 
		final java.lang.Long request)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.CertificateEnrollDenied, com.soffid.iam.exception.CertificateEnrollWaitingForAproval;

	/**
	 * Operation getCertificate

	 * @param tenant 
	 * @param user 
	 * @param password 
	 * @param domain 
	 * @param hostName 
	 * @param request 
	 * @param remote 
	 * @return 
	 */
	java.security.cert.X509Certificate getCertificate(
		final java.lang.String tenant, 
		final java.lang.String user, 
		final java.lang.String password, 
		final java.lang.String domain, 
		final java.lang.String hostName, 
		final java.lang.Long request, 
		final boolean remote)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.CertificateEnrollDenied, com.soffid.iam.exception.CertificateEnrollWaitingForAproval;

	/**
	 * Operation getRootCertificate

	 * @return 
	 */
	java.security.cert.X509Certificate getRootCertificate()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getCertificates

	 * @return 
	 */
	java.util.List<java.security.cert.X509Certificate> getCertificates()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createRequest

	 * @param tenant 
	 * @param user 
	 * @param password 
	 * @param domain 
	 * @param hostName 
	 * @param key 
	 * @return 
	 */
	long createRequest(
		final java.lang.String tenant, 
		final java.lang.String user, 
		final java.lang.String password, 
		final java.lang.String domain, 
		final java.lang.String hostName, 
		final java.security.PublicKey key)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createRequest

	 * @param tenant 
	 * @param user 
	 * @param password 
	 * @param domain 
	 * @param hostName 
	 * @param cert 
	 * @return 
	 */
	long createRequest(
		final java.lang.String tenant, 
		final java.lang.String user, 
		final java.lang.String password, 
		final java.lang.String domain, 
		final java.lang.String hostName, 
		final java.security.cert.X509Certificate cert)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
