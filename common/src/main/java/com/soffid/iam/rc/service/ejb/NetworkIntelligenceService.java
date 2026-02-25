//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service.ejb;
/**
 * EJB NetworkIntelligenceService
 */
public interface NetworkIntelligenceService

 {

	com.soffid.iam.rc.api.NetworkIntelligence getConfiguration()
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.rc.api.NetworkIntelligence saveConfiguration(
		final com.soffid.iam.rc.api.NetworkIntelligence ni)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.rc.api.NetworkIntelligence validateToken(
		final java.lang.String token)
	throws com.soffid.iam.exception.InternalErrorException;

	java.lang.Boolean isAccountBreached(
		final java.lang.String account, 
		final java.lang.String system)
	throws com.soffid.iam.exception.InternalErrorException;

	java.lang.Boolean isPasswordBreached(
		final java.lang.String password)
	throws com.soffid.iam.exception.InternalErrorException;

	java.lang.String isEmailBreached(
		final java.lang.String shortName, 
		final java.lang.String mailDomain)
	throws com.soffid.iam.exception.InternalErrorException;

	void verifyDomains(
		final java.io.PrintWriter out)
	throws com.soffid.iam.exception.InternalErrorException;

}
