//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service.ejb;
/**
 * EJB AdditionalDataService
 */
public interface AdditionalDataService

 {

	com.soffid.iam.base.api.AccountAccessLevelEnum getAccessLevel(
		final com.soffid.iam.iga.api.CustomObjectType type)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.DataType create(
		final com.soffid.iam.base.api.DataType tipusDada)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.DataType findSystemDataType(
		final java.lang.String system, 
		final java.lang.String name)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.DataType findDataTypeByName(
		final java.lang.String codi)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.DataType update(
		final com.soffid.iam.base.api.DataType tipusDada)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.UserData create(
		final com.soffid.iam.base.api.UserData dadaUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.UserData update(
		final com.soffid.iam.base.api.UserData dadaUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.CustomObjectType createCustomObjectType(
		final com.soffid.iam.iga.api.CustomObjectType obj)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.CustomObjectType findCustomObjectTypeByName(
		final java.lang.String name)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.CustomObjectType updateCustomObjectType(
		final com.soffid.iam.iga.api.CustomObjectType obj)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.CustomObjectType> findCustomObjectType(
		final com.soffid.zkdb.api.Query query)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypes(
		final com.soffid.iam.base.api.MetadataScope scope)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypes2(
		final com.soffid.iam.base.api.MetadataScope scope)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByObjectTypeAndName(
		final java.lang.String objectType, 
		final java.lang.String codi)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByObjectTypeAndName2(
		final java.lang.String objectType, 
		final java.lang.String codi)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByName(
		final java.lang.String codi)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByScopeAndName(
		final com.soffid.iam.base.api.MetadataScope scope, 
		final java.lang.String codi)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.DataType> getDataTypes()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.iga.api.ExtensibleObjectRegister> findExtensibleObjectRegisters()
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.base.api.DataType> findSystemDataTypes(
		final java.lang.String system)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.base.api.DataType> findSystemDataTypes2(
		final java.lang.String system)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.base.api.DataType tipusDada)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.base.api.UserData dadaUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void deleteCustomObjectType(
		final com.soffid.iam.iga.api.CustomObjectType obj)
	throws com.soffid.iam.exception.InternalErrorException;

	void registerStandardObject(
		final java.lang.String resourceName, 
		final com.soffid.iam.base.api.MetadataScope scope, 
		final boolean reset)
	throws com.soffid.iam.exception.InternalErrorException;

}
