//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
/**
 * Service ConfigurationService
 */
public interface ConfigurationService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.base.service.ConfigurationService";

	public final static String SERVICE_NAME = "com.soffid.iam.base.service.ConfigurationService";

	/**
	 * Operation getBlob

	 * @param name 
	 * @return 
	 */
	byte[] getBlob(
		final java.lang.String name)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param configuracio 
	 * @return 
	 */
	com.soffid.iam.base.api.Configuration create(
		final com.soffid.iam.base.api.Configuration configuracio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findMasterParameterByNameAndNetwork

	 * @param paramter 
	 * @param networkName 
	 * @return 
	 */
	com.soffid.iam.base.api.Configuration findMasterParameterByNameAndNetwork(
		final java.lang.String paramter, 
		final java.lang.String networkName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findParameterByNameAndNetworkName

	 * @param codiParametre 
	 * @param codiXarxa 
	 * @return 
	 */
	com.soffid.iam.base.api.Configuration findParameterByNameAndNetworkName(
		final java.lang.String codiParametre, 
		final java.lang.String codiXarxa)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param configuracio 
	 * @return 
	 */
	com.soffid.iam.base.api.Configuration update(
		final com.soffid.iam.base.api.Configuration configuracio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findConfigurations

	 * @param query 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.Configuration> findConfigurations(
		final com.soffid.zkdb.api.Query query)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findTenantParameter

	 * @param tenant 
	 * @param parameter 
	 * @return 
	 */
	java.lang.String findTenantParameter(
		final java.lang.String tenant, 
		final java.lang.String parameter)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getBlobVersion

	 * @param name 
	 * @return 
	 */
	java.lang.String getBlobVersion(
		final java.lang.String name)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getParameters

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.Configuration> getParameters()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param configuracio 
	 */
	void delete(
		final com.soffid.iam.base.api.Configuration configuracio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation deleteBlob

	 * @param name 
	 */
	void deleteBlob(
		final java.lang.String name)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateBlob

	 * @param name 
	 * @param data 
	 */
	void updateBlob(
		final java.lang.String name, 
		final byte[] data)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateBlob

	 * @param name 
	 * @param data 
	 * @param version 
	 */
	void updateBlob(
		final java.lang.String name, 
		final byte[] data, 
		final java.lang.String version)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
