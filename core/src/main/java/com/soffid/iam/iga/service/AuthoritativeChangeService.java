//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
/**
 * Service AuthoritativeChangeService
 */
public interface AuthoritativeChangeService {
	public final static String SERVICE_NAME = "com.soffid.iam.iga.service.AuthoritativeChangeService";

	/**
	 * Operation startAuthoritativeChange
	 * Notifies a new authoritative change has just arrived.
	 * Resturns true if the authoritative change has been performed

	 * @param change 
	 * @return 
	 */
	boolean startAuthoritativeChange(
		final com.soffid.iam.sync.api.AuthoritativeChange change)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation cancelAuthoritativeChange
	 * Cancels an authoritative change

	 * @param change 
	 */
	void cancelAuthoritativeChange(
		final com.soffid.iam.sync.api.AuthoritativeChange change)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation finishAuthoritativeChange
	 * Performs authoritative change

	 * @param change 
	 */
	void finishAuthoritativeChange(
		final com.soffid.iam.sync.api.AuthoritativeChange change)
			throws com.soffid.iam.exception.InternalErrorException;

}
