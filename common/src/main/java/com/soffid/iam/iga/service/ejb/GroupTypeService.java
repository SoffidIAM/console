//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * EJB GroupTypeService
 */
public interface GroupTypeService

 {

	com.soffid.iam.iga.api.GroupType create(
		final com.soffid.iam.iga.api.GroupType tipus)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.GroupType findGroupTypeByName(
		final java.lang.String CodiTipusUnitatOrganitzativa)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.GroupType update(
		final com.soffid.iam.iga.api.GroupType tipus)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.GroupType> findGroupTypes(
		final com.soffid.zkdb.api.Query q)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.iga.api.GroupType> findAllGroupTypes()
	throws com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.GroupType tipus)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
