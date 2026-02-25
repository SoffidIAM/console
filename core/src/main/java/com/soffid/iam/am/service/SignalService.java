//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service;
/**
 * Service SignalService
 */
public interface SignalService {
	public final static String SERVICE_NAME = "com.soffid.iam.am.service.SignalService";

	/**
	 * Operation signal

	 * @param signal 
	 * @param attributes 
	 */
	void signal(
		final java.lang.String signal, 
		final java.lang.String[] attributes)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation signalAccount

	 * @param signal 
	 * @param account 
	 * @param system 
	 * @param attributes 
	 */
	void signalAccount(
		final java.lang.String signal, 
		final java.lang.String account, 
		final java.lang.String system, 
		final java.lang.String[] attributes)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation signalUser

	 * @param signal 
	 * @param user 
	 * @param attributes 
	 */
	void signalUser(
		final java.lang.String signal, 
		final java.lang.String user, 
		final java.lang.String[] attributes)
			throws com.soffid.iam.exception.InternalErrorException;

}
