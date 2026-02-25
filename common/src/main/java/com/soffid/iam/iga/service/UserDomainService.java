//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
/**
 * Service UserDomainService
 */
public interface UserDomainService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.iga.service.UserDomainService";

	public final static String SERVICE_NAME = "com.soffid.iam.iga.service.UserDomainService";

	/**
	 * Operation create

	 * @param paraulaProhibida 
	 * @return 
	 */
	com.soffid.iam.am.api.ForbiddenWord create(
		final com.soffid.iam.am.api.ForbiddenWord paraulaProhibida)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param paraulaProhibida 
	 * @return 
	 */
	com.soffid.iam.am.api.ForbiddenWord update(
		final com.soffid.iam.am.api.ForbiddenWord paraulaProhibida)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param dominiContrasenya 
	 * @return 
	 */
	com.soffid.iam.am.api.PasswordDomain create(
		final com.soffid.iam.am.api.PasswordDomain dominiContrasenya)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findPasswordDomainByName

	 * @param codi 
	 * @return 
	 */
	com.soffid.iam.am.api.PasswordDomain findPasswordDomainByName(
		final java.lang.String codi)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param dominiContrasenya 
	 * @return 
	 */
	com.soffid.iam.am.api.PasswordDomain update(
		final com.soffid.iam.am.api.PasswordDomain dominiContrasenya)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param politicaContrasenyaDomini 
	 * @return 
	 */
	com.soffid.iam.am.api.PasswordPolicy create(
		final com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findPolicyByTypeAndPasswordDomain

	 * @param tipus 
	 * @param domini 
	 * @return 
	 */
	com.soffid.iam.am.api.PasswordPolicy findPolicyByTypeAndPasswordDomain(
		final java.lang.String tipus, 
		final java.lang.String domini)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param politicaContrasenyaDomini 
	 * @return 
	 */
	com.soffid.iam.am.api.PasswordPolicy update(
		final com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param paraulaProhibidaContrasenya 
	 * @return 
	 */
	com.soffid.iam.am.api.PasswordPolicyForbbidenWord create(
		final com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param paraulaProhibidaContrasenya 
	 * @return 
	 */
	com.soffid.iam.am.api.PasswordPolicyForbbidenWord update(
		final com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param dominiUsuari 
	 * @return 
	 */
	com.soffid.iam.iga.api.UserDomain create(
		final com.soffid.iam.iga.api.UserDomain dominiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserDomainByName

	 * @param codiDominiUsuari 
	 * @return 
	 */
	com.soffid.iam.iga.api.UserDomain findUserDomainByName(
		final java.lang.String codiDominiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param dominiUsuari 
	 * @return 
	 */
	com.soffid.iam.iga.api.UserDomain update(
		final com.soffid.iam.iga.api.UserDomain dominiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param tipusUsuari 
	 * @return 
	 */
	com.soffid.iam.iga.api.UserType create(
		final com.soffid.iam.iga.api.UserType tipusUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param tipusUsuari 
	 * @return 
	 */
	com.soffid.iam.iga.api.UserType update(
		final com.soffid.iam.iga.api.UserType tipusUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserTypes

	 * @param q 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserType> findUserTypes(
		final com.soffid.zkdb.api.Query q)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAllPasswordDomain

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.PasswordDomain> findAllPasswordDomain()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAllUserDomain

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.UserDomain> findAllUserDomain()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAllForbiddenWords

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.ForbiddenWord> findAllForbiddenWords()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAllPasswordPolicyDomain

	 * @param codiDominiContrasenya 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.PasswordPolicy> findAllPasswordPolicyDomain(
		final java.lang.String codiDominiContrasenya)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAllUserType

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.UserType> findAllUserType()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findNameGenerators

	 * @return 
	 */
	java.util.Collection<java.lang.String> findNameGenerators()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findForbiddenWordsPasswordPolicy

	 * @param politicaContrasenya 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.PasswordPolicyForbbidenWord> findForbiddenWordsPasswordPolicy(
		final com.soffid.iam.am.api.PasswordPolicy politicaContrasenya)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param paraulaProhibida 
	 */
	void delete(
		final com.soffid.iam.am.api.ForbiddenWord paraulaProhibida)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param dominiContrasenya 
	 */
	void delete(
		final com.soffid.iam.am.api.PasswordDomain dominiContrasenya)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param politicaContrasenyaDomini 
	 */
	void delete(
		final com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param paraulaProhibidaContrasenya 
	 */
	void delete(
		final com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param dominiUsuari 
	 */
	void delete(
		final com.soffid.iam.iga.api.UserDomain dominiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param tipusUsuari 
	 */
	void delete(
		final com.soffid.iam.iga.api.UserType tipusUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
