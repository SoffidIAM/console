//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
/**
 * Service StatsService
 */
public interface StatsService {
	public final static String SERVICE_NAME = "com.soffid.iam.base.service.StatsService";

	/**
	 * Operation findStats

	 * @param name 
	 * @param since 
	 * @param until 
	 * @param step 
	 * @return 
	 */
	com.soffid.iam.base.api.Stats findStats(
		final java.lang.String name, 
		final java.util.Date since, 
		final java.util.Date until, 
		final int step)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation purge

	 */
	void purge()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateStats

	 */
	void updateStats()
			throws com.soffid.iam.exception.InternalErrorException;

}
