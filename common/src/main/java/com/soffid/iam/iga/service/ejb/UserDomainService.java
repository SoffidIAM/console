//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * EJB UserDomainService
 */
public interface UserDomainService

 {

	com.soffid.iam.am.api.ForbiddenWord create(
		final com.soffid.iam.am.api.ForbiddenWord paraulaProhibida)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.ForbiddenWord update(
		final com.soffid.iam.am.api.ForbiddenWord paraulaProhibida)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.PasswordDomain create(
		final com.soffid.iam.am.api.PasswordDomain dominiContrasenya)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.PasswordDomain findPasswordDomainByName(
		final java.lang.String codi)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.PasswordDomain update(
		final com.soffid.iam.am.api.PasswordDomain dominiContrasenya)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.PasswordPolicy create(
		final com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.PasswordPolicy findPolicyByTypeAndPasswordDomain(
		final java.lang.String tipus, 
		final java.lang.String domini)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.PasswordPolicy update(
		final com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.PasswordPolicyForbbidenWord create(
		final com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.PasswordPolicyForbbidenWord update(
		final com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.UserDomain create(
		final com.soffid.iam.iga.api.UserDomain dominiUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.UserDomain findUserDomainByName(
		final java.lang.String codiDominiUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.UserDomain update(
		final com.soffid.iam.iga.api.UserDomain dominiUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.UserType create(
		final com.soffid.iam.iga.api.UserType tipusUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.UserType update(
		final com.soffid.iam.iga.api.UserType tipusUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserType> findUserTypes(
		final com.soffid.zkdb.api.Query q)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.am.api.PasswordDomain> findAllPasswordDomain()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.UserDomain> findAllUserDomain()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.am.api.ForbiddenWord> findAllForbiddenWords()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.am.api.PasswordPolicy> findAllPasswordPolicyDomain(
		final java.lang.String codiDominiContrasenya)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.UserType> findAllUserType()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<java.lang.String> findNameGenerators()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.am.api.PasswordPolicyForbbidenWord> findForbiddenWordsPasswordPolicy(
		final com.soffid.iam.am.api.PasswordPolicy politicaContrasenya)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.am.api.ForbiddenWord paraulaProhibida)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.am.api.PasswordDomain dominiContrasenya)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.UserDomain dominiUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.UserType tipusUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
