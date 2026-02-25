//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
/**
 * Service AsyncRunnerService
 */
public interface AsyncRunnerService {
	public final static String SERVICE_NAME = "com.soffid.iam.impl.service.AsyncRunnerService";

	/**
	 * Operation runNewTransaction

	 * @param runnable 
	 * @return 
	 */
	java.lang.Object runNewTransaction(
		final com.soffid.iam.impl.TransactionalTask runnable)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation runTransaction

	 * @param runnable 
	 * @return 
	 */
	java.lang.Object runTransaction(
		final com.soffid.iam.impl.TransactionalTask runnable)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation run

	 * @param runnable 
	 * @param result 
	 */
	void run(
		final java.lang.Runnable runnable, 
		final com.soffid.zkdb.api.PagedResult result)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation runInternal

	 * @param runnable 
	 * @param result 
	 */
	void runInternal(
		final java.lang.Runnable runnable, 
		final com.soffid.zkdb.api.PagedResult result)
			throws com.soffid.iam.exception.InternalErrorException;

}
