//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service;
/**
 * Service NetworkIntelligenceService
 */
public interface NetworkIntelligenceService {
	public final static String SERVICE_NAME = "com.soffid.iam.rc.service.NetworkIntelligenceService";

	/**
	 * Operation getConfiguration

	 * @return 
	 */
	com.soffid.iam.rc.api.NetworkIntelligence getConfiguration()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation saveConfiguration

	 * @param ni 
	 * @return 
	 */
	com.soffid.iam.rc.api.NetworkIntelligence saveConfiguration(
		final com.soffid.iam.rc.api.NetworkIntelligence ni)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation validateToken

	 * @param token 
	 * @return 
	 */
	com.soffid.iam.rc.api.NetworkIntelligence validateToken(
		final java.lang.String token)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isAccountBreached

	 * @param account 
	 * @param system 
	 * @return 
	 */
	java.lang.Boolean isAccountBreached(
		final java.lang.String account, 
		final java.lang.String system)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isPasswordBreached

	 * @param password 
	 * @return 
	 */
	java.lang.Boolean isPasswordBreached(
		final java.lang.String password)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isEmailBreached

	 * @param shortName 
	 * @param mailDomain 
	 * @return 
	 */
	java.lang.String isEmailBreached(
		final java.lang.String shortName, 
		final java.lang.String mailDomain)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation verifyDomains

	 * @param out 
	 */
	void verifyDomains(
		final java.io.PrintWriter out)
			throws com.soffid.iam.exception.InternalErrorException;

}
