//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
/**
 * Service CustomObjectService
 */
public interface CustomObjectService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.iga.service.CustomObjectService";

	public final static String SERVICE_NAME = "com.soffid.iam.iga.service.CustomObjectService";

	/**
	 * Operation createCustomObject

	 * @param obj 
	 * @return 
	 */
	com.soffid.iam.iga.api.CustomObject createCustomObject(
		final com.soffid.iam.iga.api.CustomObject obj)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findCustomObjectByTypeAndName

	 * @param objectType 
	 * @param name 
	 * @return 
	 */
	com.soffid.iam.iga.api.CustomObject findCustomObjectByTypeAndName(
		final java.lang.String objectType, 
		final java.lang.String name)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation load

	 * @param id 
	 * @return 
	 */
	com.soffid.iam.iga.api.CustomObject load(
		final java.lang.Long id)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateCustomObject

	 * @param obj 
	 * @return 
	 */
	com.soffid.iam.iga.api.CustomObject updateCustomObject(
		final com.soffid.iam.iga.api.CustomObject obj)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findCustomObjects

	 * @param objectType 
	 * @param query 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.CustomObject> findCustomObjects(
		final java.lang.String objectType, 
		final com.soffid.zkdb.api.Query query)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findCustomObjectNames

	 * @param objectType 
	 * @return 
	 */
	java.util.Collection<java.lang.String> findCustomObjectNames(
		final java.lang.String objectType)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation deleteCustomObject

	 * @param obj 
	 */
	void deleteCustomObject(
		final com.soffid.iam.iga.api.CustomObject obj)
			throws com.soffid.iam.exception.InternalErrorException;

}
