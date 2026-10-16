//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service.ejb;
/**
 * EJB AsyncRunnerService
 */
public interface AsyncRunnerService

 {

	java.lang.Object runNewTransaction(
		final com.soffid.iam.impl.TransactionalTask runnable)
	throws com.soffid.iam.exception.InternalErrorException;

	java.lang.Object runTransaction(
		final com.soffid.iam.impl.TransactionalTask runnable)
	throws com.soffid.iam.exception.InternalErrorException;

	void run(
		final java.lang.Runnable runnable, 
		final com.soffid.zkdb.api.PagedResult result)
	throws com.soffid.iam.exception.InternalErrorException;

	void runWrite(
		final java.lang.Runnable runnable, 
		final com.soffid.zkdb.api.PagedResult result)
	throws com.soffid.iam.exception.InternalErrorException;

}
