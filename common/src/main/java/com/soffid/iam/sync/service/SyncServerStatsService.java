//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.service;
/**
 * Service SyncServerStatsService
 */
public interface SyncServerStatsService {
	public final static String REMOTE_PATH = "SEU/SyncServerStatsService";

	public final static String SERVICE_NAME = "com.soffid.iam.sync.service.SyncServerStatsService";

	/**
	 * Operation getStats

	 * @param metric 
	 * @param seconds 
	 * @param step 
	 * @return 
	 */
	java.util.Map<java.lang.String,int[]> getStats(
		final java.lang.String metric, 
		final int seconds, 
		final int step)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation register

	 * @param metric 
	 * @param submetric 
	 * @param value 
	 */
	void register(
		final java.lang.String metric, 
		final java.lang.String submetric, 
		final int value)
			throws com.soffid.iam.exception.InternalErrorException;

}
