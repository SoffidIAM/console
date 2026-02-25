//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
/**
 * Service CreateDisableUserService
 */
public interface CreateDisableUserService {
	public final static String SERVICE_NAME = "com.soffid.iam.impl.service.CreateDisableUserService";

	/**
	 * Operation disableUser

	 * @param codiUsuari 
	 * @return 
	 */
	com.soffid.iam.base.api.User disableUser(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserByShortName

	 * @param nomCurt 
	 * @return 
	 */
	com.soffid.iam.base.api.User findUserByShortName(
		final java.lang.String nomCurt)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation setServersToUser

	 * @param codiUsuari 
	 * @param servidorPerfilId 
	 * @param servidorCorreuId 
	 * @param servidorHomeId 
	 * @return 
	 */
	com.soffid.iam.base.api.User setServersToUser(
		final java.lang.String codiUsuari, 
		final java.lang.String servidorPerfilId, 
		final java.lang.String servidorCorreuId, 
		final java.lang.String servidorHomeId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getSuperGroup

	 * @param codiSubGrup 
	 * @return 
	 */
	com.soffid.iam.iga.api.Group getSuperGroup(
		final java.lang.String codiSubGrup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAdministratorRoleByGroup

	 * @param codiGrup 
	 * @return 
	 */
	com.soffid.iam.iga.api.Role getAdministratorRoleByGroup(
		final java.lang.String codiGrup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation existShortName

	 * @param nomCurt 
	 * @return 
	 */
	java.lang.Boolean existShortName(
		final java.lang.String nomCurt)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation setInitialPasswordToUser

	 * @param codiUsuari 
	 * @param codiDominiContrasenyes 
	 * @return 
	 */
	java.lang.String setInitialPasswordToUser(
		final java.lang.String codiUsuari, 
		final java.lang.String codiDominiContrasenyes)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findGroupsByUserCode

	 * @param codiUsuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Group> findGroupsByUserCode(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getManagedGroups

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Group> getManagedGroups()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getManagedGroups

	 * @param codiUsuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Group> getManagedGroups(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getContractTypesUserCreate

	 * @return 
	 */
	java.util.Collection<java.lang.String> getContractTypesUserCreate()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getOUDependent

	 * @param codiUnitatOrganitzativa 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Group> getOUDependent(
		final java.lang.String codiUnitatOrganitzativa)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUsersByNIF

	 * @param nif 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.User> getUsersByNIF(
		final java.lang.String nif)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
