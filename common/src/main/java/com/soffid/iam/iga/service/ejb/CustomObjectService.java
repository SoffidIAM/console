//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * EJB CustomObjectService
 */
public interface CustomObjectService

 {

	com.soffid.iam.iga.api.CustomObject createCustomObject(
		final com.soffid.iam.iga.api.CustomObject obj)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.CustomObject findCustomObjectByTypeAndName(
		final java.lang.String objectType, 
		final java.lang.String name)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.CustomObject updateCustomObject(
		final com.soffid.iam.iga.api.CustomObject obj)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.CustomObject> findCustomObjects(
		final java.lang.String objectType, 
		final com.soffid.zkdb.api.Query query)
	throws com.soffid.iam.exception.InternalErrorException;

	void deleteCustomObject(
		final com.soffid.iam.iga.api.CustomObject obj)
	throws com.soffid.iam.exception.InternalErrorException;

}
