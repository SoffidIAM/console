//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service.ejb;
/**
 * EJB ServiceService
 */
public interface ServiceService

 {

	com.soffid.iam.am.api.Service create(
		final com.soffid.iam.am.api.Service servei)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.Service update(
		final com.soffid.iam.am.api.Service servei)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Service> findServices(
		final com.soffid.zkdb.api.Query q)
	throws com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.am.api.Service servei)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
