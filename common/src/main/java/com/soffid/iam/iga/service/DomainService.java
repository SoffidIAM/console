//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
/**
 * Service DomainService
 */
public interface DomainService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.iga.service.DomainService";

	public final static String SERVICE_NAME = "com.soffid.iam.iga.service.DomainService";

	/**
	 * Operation create

	 * @param domini 
	 * @return 
	 */
	com.soffid.iam.iga.api.Domain create(
		final com.soffid.iam.iga.api.Domain domini)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findDomainByApplicationAndName

	 * @param codiAplicacio 
	 * @param name 
	 * @return 
	 */
	com.soffid.iam.iga.api.Domain findDomainByApplicationAndName(
		final java.lang.String codiAplicacio, 
		final java.lang.String name)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findApplicationDomainByDomianNameAndApplicationName

	 * @param nomDomini 
	 * @param codiAplicacio 
	 * @return 
	 */
	com.soffid.iam.iga.api.Domain findApplicationDomainByDomianNameAndApplicationName(
		final java.lang.String nomDomini, 
		final java.lang.String codiAplicacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findGroupsDomain

	 * @return 
	 */
	com.soffid.iam.iga.api.Domain findGroupsDomain()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserDomainGroup

	 * @return 
	 */
	com.soffid.iam.iga.api.Domain findUserDomainGroup()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param domini 
	 * @return 
	 */
	com.soffid.iam.iga.api.Domain update(
		final com.soffid.iam.iga.api.Domain domini)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param valorDomini 
	 * @return 
	 */
	com.soffid.iam.iga.api.DomainValue create(
		final com.soffid.iam.iga.api.DomainValue valorDomini)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findApplicationDomainValueByDomainNameAndDomainApplicationNameAndValue

	 * @param nomDomini 
	 * @param codiAplicacio 
	 * @param valor 
	 * @return 
	 */
	com.soffid.iam.iga.api.DomainValue findApplicationDomainValueByDomainNameAndDomainApplicationNameAndValue(
		final java.lang.String nomDomini, 
		final java.lang.String codiAplicacio, 
		final java.lang.String valor)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param valorDomini 
	 * @return 
	 */
	com.soffid.iam.iga.api.DomainValue update(
		final com.soffid.iam.iga.api.DomainValue valorDomini)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findDomainValues

	 * @param q 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> findDomainValues(
		final com.soffid.zkdb.api.Query q)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findApplicationDomainsByApplicationName

	 * @param codiAplicacio 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Domain> findApplicationDomainsByApplicationName(
		final java.lang.String codiAplicacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findDomainsByApplicationName

	 * @param codiAplicacio 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Domain> findDomainsByApplicationName(
		final java.lang.String codiAplicacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findDomainValuesByDomain

	 * @param domini 
	 * @return 
	 */
	java.util.List<com.soffid.iam.iga.api.DomainValue> findDomainValuesByDomain(
		final com.soffid.iam.iga.api.Domain domini)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param domini 
	 */
	void delete(
		final com.soffid.iam.iga.api.Domain domini)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param valorDomini 
	 */
	void delete(
		final com.soffid.iam.iga.api.DomainValue valorDomini)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
