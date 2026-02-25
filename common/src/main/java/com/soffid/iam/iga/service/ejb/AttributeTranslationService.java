//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * EJB AttributeTranslationService
 */
public interface AttributeTranslationService

 {

	com.soffid.iam.iga.api.AttributeTranslation create(
		final com.soffid.iam.iga.api.AttributeTranslation att)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.AttributeTranslation update(
		final com.soffid.iam.iga.api.AttributeTranslation att)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.AttributeTranslation> findByQuery(
		final com.soffid.zkdb.api.Query query)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> findByColumn1(
		final java.lang.String domain, 
		final java.lang.String column1)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> findByColumn2(
		final java.lang.String domain, 
		final java.lang.String column2)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> findByExample(
		final java.lang.String domain, 
		final java.lang.String column1, 
		final java.lang.String column2)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<java.lang.String> findDomains()
	throws com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.AttributeTranslation att)
	throws com.soffid.iam.exception.InternalErrorException;

}
