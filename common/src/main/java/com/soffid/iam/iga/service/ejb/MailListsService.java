//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * EJB MailListsService
 */
public interface MailListsService

 {

	com.soffid.iam.iga.api.ExternalName create(
		final com.soffid.iam.iga.api.ExternalName correuExtern)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.ExternalName finExternalMailByEmail(
		final java.lang.String adreca)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.MailDomain create(
		final com.soffid.iam.iga.api.MailDomain dominiCorreu)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.MailDomain findMailDomainByName(
		final java.lang.String codi)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.MailDomain update(
		final com.soffid.iam.iga.api.MailDomain dominiCorreu)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.MailList create(
		final com.soffid.iam.iga.api.MailList llistaCorreu)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.MailList findMailListByNameAndDomainName(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.MailList update(
		final com.soffid.iam.iga.api.MailList llistaCorreu)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.MailListRelationship create(
		final com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.MailListRelationship findRelationsMailListByNameAndBelongsMailListNameAndNameAndContainsMailListName(
		final java.lang.String nomPertany, 
		final java.lang.String dominiCorreuPertany, 
		final java.lang.String nomConte, 
		final java.lang.String dominiCorreuConte)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.MailListRoleMember subscribeRole(
		final java.lang.String mailListName, 
		final java.lang.String mailListDomain, 
		final com.soffid.iam.iga.api.MailListRoleMember roleMember)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.UserMailList create(
		final com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.UserMailList findUserMailListByListNameAndDomainNameAndUserName(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini, 
		final java.lang.String codiUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.MailDomain> findMailDomains(
		final com.soffid.zkdb.api.Query query)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.MailList> findMailLists(
		final com.soffid.zkdb.api.Query query)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.ExternalName> findExternalMailsByNameListAndDomainName(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.Group> findGroupMembers(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.UserMailList> findUserMailListByUserName(
		final java.lang.String codiUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.UserMailList> findUserMailListByListNameAndDomainName(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.UserMailList> findUserMailListHistoryByUserName(
		final java.lang.String codiUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.MailList> findMailListsByData(
		final java.lang.String nom, 
		final java.lang.String domini, 
		final java.lang.String descripcio, 
		final java.lang.String membres)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.MailListRelationship> findRelationsMailListByNameContainsMailListAndDomainName(
		final java.lang.String nomLlistaCorreuConte, 
		final java.lang.String codiDomini)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.MailListRelationship> findRelationsMailListByNameBelongsMailListAndDomainName(
		final java.lang.String nomLlistaCorreuPertany, 
		final java.lang.String codiDomini)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.MailListRoleMember> findRoleMembers(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.User> findUsersByMailListNameAndDomainName(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.MailDomain> getDomainMails()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.MailList> getMailLists()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.ExternalName correuExtern)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.MailDomain dominiCorreu)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.MailList llistaCorreu)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void deleteUserMailList(
		final com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void deleteAtomic(
		final com.soffid.iam.iga.api.ExternalName correuExtern)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void deleteAtomic(
		final com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void deleteAtomic(
		final com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void subscribeGroup(
		final java.lang.String mailListName, 
		final java.lang.String mailListDomain, 
		final java.lang.String groupName)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void unsubscribeGroup(
		final java.lang.String mailListName, 
		final java.lang.String mailListDomain, 
		final java.lang.String groupName)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void unsubscribeRole(
		final java.lang.String mailListName, 
		final java.lang.String mailListDomain, 
		final com.soffid.iam.iga.api.MailListRoleMember roleMember)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
