//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service.ejb;
/**
 * EJB SamlService
 */
public interface SamlService

 {

	com.soffid.iam.am.api.SamlRequest generateLogoutRequest(
		final java.lang.String hostName, 
		final java.lang.String user)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.SamlRequest generateSamlRequest(
		final java.lang.String hostName, 
		final java.lang.String app)
	throws com.soffid.iam.exception.InternalErrorException;

	java.lang.String checkAuthenticationToken(
		final java.lang.String[] token)
	throws com.soffid.iam.exception.InternalErrorException;

	java.lang.String generateMetadata(
		final java.lang.String hostName)
	throws com.soffid.iam.exception.InternalErrorException;

	java.lang.String validateOpenidToken(
		final java.lang.String token)
	throws com.soffid.iam.exception.InternalErrorException;

	java.lang.String[] authenticate(
		final java.lang.String hostName, 
		final java.lang.String app, 
		final java.lang.String protocol, 
		final java.util.Map<java.lang.String,java.lang.String> response)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<java.lang.String> findIdentityProviders()
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<java.lang.String> findIdentityProviders(
		final java.lang.String url)
	throws com.soffid.iam.exception.InternalErrorException;

}
