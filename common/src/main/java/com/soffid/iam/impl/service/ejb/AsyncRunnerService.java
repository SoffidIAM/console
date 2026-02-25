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

}
