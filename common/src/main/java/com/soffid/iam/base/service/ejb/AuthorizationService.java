//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service.ejb;
/**
 * EJB AuthorizationService
 * Manages find-grained, low-level authorization to Soffid objects
 */
public interface AuthorizationService

 {

	com.soffid.iam.base.api.AuthorizationRole create(
		final com.soffid.iam.base.api.AuthorizationRole autoritzacio)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.common.security.SoffidPrincipal getCurrentPrincipal()
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.Collection findAuthorizations(
		final java.lang.String ambit, 
		final java.lang.String descripcio, 
		final java.lang.String codi)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getDescriptionUserAuthorizations()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getDescriptionUserAuthorizations(
		final java.lang.String userName)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getAuthorizationRoles(
		final java.lang.String authorization)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getUserAuthorization(
		final java.lang.String codiAutoritzacio)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getUserAuthorization(
		final java.lang.String codiAutoritzacio, 
		final java.lang.String codiUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List getScopeList()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.base.api.AuthorizationRole authorization)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
