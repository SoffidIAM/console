//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
/**
 * Service GroupTypeService
 */
public interface GroupTypeService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.iga.service.GroupTypeService";

	public final static String SERVICE_NAME = "com.soffid.iam.iga.service.GroupTypeService";

	/**
	 * Operation create

	 * @param tipus 
	 * @return 
	 */
	com.soffid.iam.iga.api.GroupType create(
		final com.soffid.iam.iga.api.GroupType tipus)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findGroupTypeByName

	 * @param CodiTipusUnitatOrganitzativa 
	 * @return 
	 */
	com.soffid.iam.iga.api.GroupType findGroupTypeByName(
		final java.lang.String CodiTipusUnitatOrganitzativa)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param tipus 
	 * @return 
	 */
	com.soffid.iam.iga.api.GroupType update(
		final com.soffid.iam.iga.api.GroupType tipus)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findGroupTypes

	 * @param q 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.GroupType> findGroupTypes(
		final com.soffid.zkdb.api.Query q)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAllGroupTypes

	 * @return 
	 */
	java.util.List<com.soffid.iam.iga.api.GroupType> findAllGroupTypes()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param tipus 
	 */
	void delete(
		final com.soffid.iam.iga.api.GroupType tipus)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
