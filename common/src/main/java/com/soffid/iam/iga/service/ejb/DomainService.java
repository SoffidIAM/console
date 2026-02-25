//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * EJB DomainService
 */
public interface DomainService

 {

	com.soffid.iam.iga.api.Domain create(
		final com.soffid.iam.iga.api.Domain domini)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.Domain findDomainByApplicationAndName(
		final java.lang.String codiAplicacio, 
		final java.lang.String name)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.Domain update(
		final com.soffid.iam.iga.api.Domain domini)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.DomainValue create(
		final com.soffid.iam.iga.api.DomainValue valorDomini)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.DomainValue update(
		final com.soffid.iam.iga.api.DomainValue valorDomini)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> findDomainValues(
		final com.soffid.zkdb.api.Query q)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.Domain> findApplicationDomainsByApplicationName(
		final java.lang.String codiAplicacio)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.Domain> findDomainsByApplicationName(
		final java.lang.String codiAplicacio)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.iga.api.DomainValue> findDomainValuesByDomain(
		final com.soffid.iam.iga.api.Domain domini)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.Domain domini)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.DomainValue valorDomini)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
