//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
/**
 * Service ApplicationShutdownService
 */
public interface ApplicationShutdownService {
	public final static String SERVICE_NAME = "com.soffid.iam.impl.service.ApplicationShutdownService";

	/**
	 * Operation consoleShutdown

	 */
	void consoleShutdown()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation syncServerShutdown

	 */
	void syncServerShutdown()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
