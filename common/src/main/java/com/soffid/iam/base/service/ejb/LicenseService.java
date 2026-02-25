//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service.ejb;
/**
 * EJB LicenseService
 */
public interface LicenseService

 {

	java.lang.String getApplicationToken()
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.Date getExpirationDate()
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<java.lang.String> findEnabledProducts()
	throws com.soffid.iam.exception.InternalErrorException;

	void installLicense(
		final java.lang.String license)
	throws com.soffid.iam.exception.InternalErrorException;

}
