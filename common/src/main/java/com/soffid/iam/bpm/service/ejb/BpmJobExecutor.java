//
// (C) 2013 Soffid
//
//

package com.soffid.iam.bpm.service.ejb;
/**
 * EJB BpmJobExecutor
 */
public interface BpmJobExecutor

 {

	boolean lockJob(
		final long id, 
		final java.lang.String lockOwner)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.lang.Exception;

	java.util.Date getNextDueDate(
		final java.lang.String lockOwner)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List getJobs(
		final java.lang.String lockOwner)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void anotateFailure(
		final long id, 
		final java.lang.Exception e)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void executeJob(
		final long id)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.lang.Exception;

	void indexPendingProcesses()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void unlockOverdueJobs(
		final java.util.Date threshold)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
