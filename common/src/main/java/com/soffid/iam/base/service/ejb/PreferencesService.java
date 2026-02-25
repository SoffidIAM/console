//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service.ejb;
/**
 * EJB PreferencesService
 * Manages user preferences
 */
public interface PreferencesService

 {

	java.lang.String findMyPreference(
		final java.lang.String name)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.Map<java.lang.String,java.lang.String> findUserPreferences(
		final java.lang.String user)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.Map<java.lang.String,java.lang.String> setUserPreferences(
		final java.lang.String user, 
		final java.util.Map<java.lang.String,java.lang.String> preferences)
	throws com.soffid.iam.exception.InternalErrorException;

	void setUserPreference(
		final java.lang.String user, 
		final java.lang.String preference, 
		final java.lang.String value)
	throws com.soffid.iam.exception.InternalErrorException;

	void updateMyPreference(
		final java.lang.String name, 
		final java.lang.String value)
	throws com.soffid.iam.exception.InternalErrorException;

}
