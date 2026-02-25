//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
/**
 * Service LicenseService
 */
public interface LicenseService {
	public final static String SERVICE_NAME = "com.soffid.iam.base.service.LicenseService";

	/**
	 * Operation getApplicationToken

	 * @return 
	 */
	java.lang.String getApplicationToken()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getExpirationDate

	 * @return 
	 */
	java.util.Date getExpirationDate()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findEnabledProducts

	 * @return 
	 */
	java.util.List<java.lang.String> findEnabledProducts()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation installLicense

	 * @param license 
	 */
	void installLicense(
		final java.lang.String license)
			throws com.soffid.iam.exception.InternalErrorException;

}
