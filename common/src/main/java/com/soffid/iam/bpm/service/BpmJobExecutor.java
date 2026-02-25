//
// (C) 2013 Soffid
//
//

package com.soffid.iam.bpm.service;
/**
 * Service BpmJobExecutor
 */
public interface BpmJobExecutor {
	public final static String SERVICE_NAME = "com.soffid.iam.bpm.service.BpmJobExecutor";

	/**
	 * Operation lockJob

	 * @param id 
	 * @param lockOwner 
	 * @return 
	 */
	boolean lockJob(
		final long id, 
		final java.lang.String lockOwner)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.lang.Exception;

	/**
	 * Operation getNextDueDate

	 * @param lockOwner 
	 * @return 
	 */
	java.util.Date getNextDueDate(
		final java.lang.String lockOwner)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getJobs

	 * @param lockOwner 
	 * @return 
	 */
	java.util.List getJobs(
		final java.lang.String lockOwner)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation anotateFailure

	 * @param id 
	 * @param e 
	 */
	void anotateFailure(
		final long id, 
		final java.lang.Exception e)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation executeJob

	 * @param id 
	 */
	void executeJob(
		final long id)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.lang.Exception;

	/**
	 * Operation indexPendingProcesses

	 */
	void indexPendingProcesses()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation unlockOverdueJobs

	 * @param threshold 
	 */
	void unlockOverdueJobs(
		final java.util.Date threshold)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
