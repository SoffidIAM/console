//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.service;
/**
 * Service ChangePasswordNotificationQueue
 */
public interface ChangePasswordNotificationQueue {
	public final static String SERVICE_NAME = "com.soffid.iam.sync.service.ChangePasswordNotificationQueue";

	/**
	 * Operation peekNotification

	 * @return 
	 */
	com.soffid.iam.sync.engine.ChangePasswordNotification peekNotification()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation addNotification

	 * @param user 
	 */
	void addNotification(
		final java.lang.String user)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation endNotificationThread

	 */
	void endNotificationThread()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation sendNotification

	 * @param n 
	 */
	void sendNotification(
		final com.soffid.iam.sync.engine.ChangePasswordNotification n)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
