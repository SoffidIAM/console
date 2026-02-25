//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
/**
 * Service MailListsService
 */
public interface MailListsService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.iga.service.MailListsService";

	public final static String SERVICE_NAME = "com.soffid.iam.iga.service.MailListsService";

	/**
	 * Operation create

	 * @param correuExtern 
	 * @return 
	 */
	com.soffid.iam.iga.api.ExternalName create(
		final com.soffid.iam.iga.api.ExternalName correuExtern)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation finExternalMailByEmail

	 * @param adreca 
	 * @return 
	 */
	com.soffid.iam.iga.api.ExternalName finExternalMailByEmail(
		final java.lang.String adreca)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param dominiCorreu 
	 * @return 
	 */
	com.soffid.iam.iga.api.MailDomain create(
		final com.soffid.iam.iga.api.MailDomain dominiCorreu)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findMailDomainByName

	 * @param codi 
	 * @return 
	 */
	com.soffid.iam.iga.api.MailDomain findMailDomainByName(
		final java.lang.String codi)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param dominiCorreu 
	 * @return 
	 */
	com.soffid.iam.iga.api.MailDomain update(
		final com.soffid.iam.iga.api.MailDomain dominiCorreu)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param llistaCorreu 
	 * @return 
	 */
	com.soffid.iam.iga.api.MailList create(
		final com.soffid.iam.iga.api.MailList llistaCorreu)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findMailListByNameAndDomainName

	 * @param nomLlistaCorreu 
	 * @param codiDomini 
	 * @return 
	 */
	com.soffid.iam.iga.api.MailList findMailListByNameAndDomainName(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param llistaCorreu 
	 * @return 
	 */
	com.soffid.iam.iga.api.MailList update(
		final com.soffid.iam.iga.api.MailList llistaCorreu)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param relacioLlistaCorreu 
	 * @return 
	 */
	com.soffid.iam.iga.api.MailListRelationship create(
		final com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRelationsMailListByNameAndBelongsMailListNameAndNameAndContainsMailListName

	 * @param nomPertany 
	 * @param dominiCorreuPertany 
	 * @param nomConte 
	 * @param dominiCorreuConte 
	 * @return 
	 */
	com.soffid.iam.iga.api.MailListRelationship findRelationsMailListByNameAndBelongsMailListNameAndNameAndContainsMailListName(
		final java.lang.String nomPertany, 
		final java.lang.String dominiCorreuPertany, 
		final java.lang.String nomConte, 
		final java.lang.String dominiCorreuConte)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation subscribeRole

	 * @param mailListName 
	 * @param mailListDomain 
	 * @param roleMember 
	 * @return 
	 */
	com.soffid.iam.iga.api.MailListRoleMember subscribeRole(
		final java.lang.String mailListName, 
		final java.lang.String mailListDomain, 
		final com.soffid.iam.iga.api.MailListRoleMember roleMember)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param llistaCorreuUsuari 
	 * @return 
	 */
	com.soffid.iam.iga.api.UserMailList create(
		final com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserMailListByListNameAndDomainNameAndUserName

	 * @param nomLlistaCorreu 
	 * @param codiDomini 
	 * @param codiUsuari 
	 * @return 
	 */
	com.soffid.iam.iga.api.UserMailList findUserMailListByListNameAndDomainNameAndUserName(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini, 
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findMailDomains

	 * @param query 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.MailDomain> findMailDomains(
		final com.soffid.zkdb.api.Query query)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findMailLists

	 * @param query 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.MailList> findMailLists(
		final com.soffid.zkdb.api.Query query)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findExternalMailsByNameListAndDomainName

	 * @param nomLlistaCorreu 
	 * @param codiDomini 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.ExternalName> findExternalMailsByNameListAndDomainName(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findGroupMembers

	 * @param nomLlistaCorreu 
	 * @param codiDomini 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Group> findGroupMembers(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserMailListByUserName

	 * @param codiUsuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.UserMailList> findUserMailListByUserName(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserMailListByListNameAndDomainName

	 * @param nomLlistaCorreu 
	 * @param codiDomini 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.UserMailList> findUserMailListByListNameAndDomainName(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserMailListHistoryByUserName

	 * @param codiUsuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.UserMailList> findUserMailListHistoryByUserName(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findMailListsByData

	 * @param nom 
	 * @param domini 
	 * @param descripcio 
	 * @param membres 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.MailList> findMailListsByData(
		final java.lang.String nom, 
		final java.lang.String domini, 
		final java.lang.String descripcio, 
		final java.lang.String membres)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRelationsMailListByNameContainsMailListAndDomainName

	 * @param nomLlistaCorreuConte 
	 * @param codiDomini 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.MailListRelationship> findRelationsMailListByNameContainsMailListAndDomainName(
		final java.lang.String nomLlistaCorreuConte, 
		final java.lang.String codiDomini)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRelationsMailListByNameBelongsMailListAndDomainName

	 * @param nomLlistaCorreuPertany 
	 * @param codiDomini 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.MailListRelationship> findRelationsMailListByNameBelongsMailListAndDomainName(
		final java.lang.String nomLlistaCorreuPertany, 
		final java.lang.String codiDomini)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRoleMembers

	 * @param nomLlistaCorreu 
	 * @param codiDomini 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.MailListRoleMember> findRoleMembers(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUsersByMailListNameAndDomainName

	 * @param nomLlistaCorreu 
	 * @param codiDomini 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.User> findUsersByMailListNameAndDomainName(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getDomainMails

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.MailDomain> getDomainMails()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getMailLists

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.MailList> getMailLists()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param correuExtern 
	 */
	void delete(
		final com.soffid.iam.iga.api.ExternalName correuExtern)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param dominiCorreu 
	 */
	void delete(
		final com.soffid.iam.iga.api.MailDomain dominiCorreu)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param llistaCorreu 
	 */
	void delete(
		final com.soffid.iam.iga.api.MailList llistaCorreu)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param relacioLlistaCorreu 
	 */
	void delete(
		final com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation deleteUserMailList

	 * @param llistaCorreuUsuari 
	 */
	void deleteUserMailList(
		final com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation deleteAtomic

	 * @param correuExtern 
	 */
	void deleteAtomic(
		final com.soffid.iam.iga.api.ExternalName correuExtern)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation deleteAtomic

	 * @param relacioLlistaCorreu 
	 */
	void deleteAtomic(
		final com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation deleteAtomic

	 * @param llistaCorreuUsuari 
	 */
	void deleteAtomic(
		final com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation checkEmptyMailList

	 * @param nomLlistaCorreu 
	 * @param codiDomini 
	 */
	void checkEmptyMailList(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation subscribeGroup

	 * @param mailListName 
	 * @param mailListDomain 
	 * @param groupName 
	 */
	void subscribeGroup(
		final java.lang.String mailListName, 
		final java.lang.String mailListDomain, 
		final java.lang.String groupName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation unsubscribeGroup

	 * @param mailListName 
	 * @param mailListDomain 
	 * @param groupName 
	 */
	void unsubscribeGroup(
		final java.lang.String mailListName, 
		final java.lang.String mailListDomain, 
		final java.lang.String groupName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation unsubscribeRole

	 * @param mailListName 
	 * @param mailListDomain 
	 * @param roleMember 
	 */
	void unsubscribeRole(
		final java.lang.String mailListName, 
		final java.lang.String mailListDomain, 
		final com.soffid.iam.iga.api.MailListRoleMember roleMember)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
