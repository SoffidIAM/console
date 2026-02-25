//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.service;
/**
 * Service ConsoleLogonService
 */
public interface ConsoleLogonService {
	public final static String REMOTE_PATH = "/SEU/LogonService";

	public final static String SERVICE_NAME = "com.soffid.iam.sync.service.ConsoleLogonService";

	/**
	 * Operation validatePassword

	 * @param user 
	 * @param passwordDomain 
	 * @param password 
	 * @return 
	 */
	com.soffid.iam.am.api.PasswordValidation validatePassword(
		final java.lang.String user, 
		final java.lang.String passwordDomain, 
		final java.lang.String password)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.rmi.RemoteException, com.soffid.iam.exception.InternalErrorException;

}
