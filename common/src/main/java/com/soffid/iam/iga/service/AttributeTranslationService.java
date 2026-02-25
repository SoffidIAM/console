//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
/**
 * Service AttributeTranslationService
 */
public interface AttributeTranslationService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.iga.service.AttributeTranslationService";

	public final static String SERVICE_NAME = "com.soffid.iam.iga.service.AttributeTranslationService";

	/**
	 * Operation create

	 * @param att 
	 * @return 
	 */
	com.soffid.iam.iga.api.AttributeTranslation create(
		final com.soffid.iam.iga.api.AttributeTranslation att)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param att 
	 * @return 
	 */
	com.soffid.iam.iga.api.AttributeTranslation update(
		final com.soffid.iam.iga.api.AttributeTranslation att)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findByQuery

	 * @param query 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.AttributeTranslation> findByQuery(
		final com.soffid.zkdb.api.Query query)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findByColumn1

	 * @param domain 
	 * @param column1 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> findByColumn1(
		final java.lang.String domain, 
		final java.lang.String column1)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findByColumn2

	 * @param domain 
	 * @param column2 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> findByColumn2(
		final java.lang.String domain, 
		final java.lang.String column2)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findByExample

	 * @param domain 
	 * @param column1 
	 * @param column2 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> findByExample(
		final java.lang.String domain, 
		final java.lang.String column1, 
		final java.lang.String column2)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findDomains

	 * @return 
	 */
	java.util.Collection<java.lang.String> findDomains()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param att 
	 */
	void delete(
		final com.soffid.iam.iga.api.AttributeTranslation att)
			throws com.soffid.iam.exception.InternalErrorException;

}
