//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
/**
 * Service AdditionalDataService
 */
public interface AdditionalDataService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.base.service.AdditionalDataService";

	public final static String SERVICE_NAME = "com.soffid.iam.base.service.AdditionalDataService";

	/**
	 * Operation getAccessLevel

	 * @param type 
	 * @return 
	 */
	com.soffid.iam.base.api.AccountAccessLevelEnum getAccessLevel(
		final com.soffid.iam.iga.api.CustomObjectType type)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param tipusDada 
	 * @return 
	 */
	com.soffid.iam.base.api.DataType create(
		final com.soffid.iam.base.api.DataType tipusDada)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findSystemDataType

	 * @param system 
	 * @param name 
	 * @return 
	 */
	com.soffid.iam.base.api.DataType findSystemDataType(
		final java.lang.String system, 
		final java.lang.String name)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findDataTypeByName

	 * @param codi 
	 * @return 
	 */
	com.soffid.iam.base.api.DataType findDataTypeByName(
		final java.lang.String codi)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param tipusDada 
	 * @return 
	 */
	com.soffid.iam.base.api.DataType update(
		final com.soffid.iam.base.api.DataType tipusDada)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param dadaUsuari 
	 * @return 
	 */
	com.soffid.iam.base.api.UserData create(
		final com.soffid.iam.base.api.UserData dadaUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param dadaUsuari 
	 * @return 
	 */
	com.soffid.iam.base.api.UserData update(
		final com.soffid.iam.base.api.UserData dadaUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createCustomObjectType

	 * @param obj 
	 * @return 
	 */
	com.soffid.iam.iga.api.CustomObjectType createCustomObjectType(
		final com.soffid.iam.iga.api.CustomObjectType obj)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findCustomObjectTypeByName

	 * @param name 
	 * @return 
	 */
	com.soffid.iam.iga.api.CustomObjectType findCustomObjectTypeByName(
		final java.lang.String name)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateCustomObjectType

	 * @param obj 
	 * @return 
	 */
	com.soffid.iam.iga.api.CustomObjectType updateCustomObjectType(
		final com.soffid.iam.iga.api.CustomObjectType obj)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findExtensibleObjectRegister

	 * @param name 
	 * @return 
	 */
	com.soffid.iam.iga.api.ExtensibleObjectRegister findExtensibleObjectRegister(
		final java.lang.String name)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findCustomObjectType

	 * @param query 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.CustomObjectType> findCustomObjectType(
		final com.soffid.zkdb.api.Query query)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findDataTypes
	 * Retrieves the not builtin attributes

	 * @param scope 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypes(
		final com.soffid.iam.base.api.MetadataScope scope)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findDataTypes2
	 * Retrieves the bultin and not builtin attributes

	 * @param scope 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypes2(
		final com.soffid.iam.base.api.MetadataScope scope)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findDataTypesByObjectTypeAndName
	 * Retrieves the not builtin attributes

	 * @param objectType 
	 * @param codi 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByObjectTypeAndName(
		final java.lang.String objectType, 
		final java.lang.String codi)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findDataTypesByObjectTypeAndName2
	 * Retrieves the bultin and not builtin attributes

	 * @param objectType 
	 * @param codi 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByObjectTypeAndName2(
		final java.lang.String objectType, 
		final java.lang.String codi)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findDataTypesByName

	 * @param codi 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByName(
		final java.lang.String codi)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findDataTypesByScopeAndName

	 * @param scope 
	 * @param codi 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByScopeAndName(
		final com.soffid.iam.base.api.MetadataScope scope, 
		final java.lang.String codi)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getDataTypes

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.DataType> getDataTypes()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findExtensibleObjectRegisters

	 * @return 
	 */
	java.util.List<com.soffid.iam.iga.api.ExtensibleObjectRegister> findExtensibleObjectRegisters()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findSystemDataTypes
	 * Retrieves the not builtin attributes

	 * @param system 
	 * @return 
	 */
	java.util.List<com.soffid.iam.base.api.DataType> findSystemDataTypes(
		final java.lang.String system)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findSystemDataTypes2
	 * Retrieves the bultin and not builtin attributes

	 * @param system 
	 * @return 
	 */
	java.util.List<com.soffid.iam.base.api.DataType> findSystemDataTypes2(
		final java.lang.String system)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getGenAIMetadata

	 * @param engine 
	 * @return 
	 */
	java.util.List<java.lang.String> getGenAIMetadata(
		final java.lang.String engine)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param tipusDada 
	 */
	void delete(
		final com.soffid.iam.base.api.DataType tipusDada)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param dadaUsuari 
	 */
	void delete(
		final com.soffid.iam.base.api.UserData dadaUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation deleteCustomObjectType

	 * @param obj 
	 */
	void deleteCustomObjectType(
		final com.soffid.iam.iga.api.CustomObjectType obj)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation registerExtensibleObject

	 * @param register 
	 */
	void registerExtensibleObject(
		final com.soffid.iam.iga.api.ExtensibleObjectRegister register)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation registerStandardObject

	 * @param resourceName 
	 * @param scope 
	 * @param reset 
	 */
	void registerStandardObject(
		final java.lang.String resourceName, 
		final com.soffid.iam.base.api.MetadataScope scope, 
		final boolean reset)
			throws com.soffid.iam.exception.InternalErrorException;

}
