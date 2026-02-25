//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
/**
 * Service PreferencesService
 * Manages user preferences
 */
public interface PreferencesService {
	public final static String SERVICE_NAME = "com.soffid.iam.base.service.PreferencesService";

	/**
	 * Operation findMyPreference
	 * Retrieves current user preference

	 * @param name 
	 * @return 
	 */
	java.lang.String findMyPreference(
		final java.lang.String name)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserPreferences
	 * Retrieves all the preferences for the selected user

	 * @param user 
	 * @return 
	 */
	java.util.Map<java.lang.String,java.lang.String> findUserPreferences(
		final java.lang.String user)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation setUserPreferences
	 * Sets all user preferences for the selected user

	 * @param user 
	 * @param preferences 
	 * @return 
	 */
	java.util.Map<java.lang.String,java.lang.String> setUserPreferences(
		final java.lang.String user, 
		final java.util.Map<java.lang.String,java.lang.String> preferences)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation setUserPreference

	 * @param user 
	 * @param preference 
	 * @param value 
	 */
	void setUserPreference(
		final java.lang.String user, 
		final java.lang.String preference, 
		final java.lang.String value)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateMyPreference
	 * Changes current user preference

	 * @param name 
	 * @param value 
	 */
	void updateMyPreference(
		final java.lang.String name, 
		final java.lang.String value)
			throws com.soffid.iam.exception.InternalErrorException;

}
