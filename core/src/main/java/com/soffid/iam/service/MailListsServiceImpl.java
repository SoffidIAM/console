// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.service;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.json.JSONException;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.ExternalName;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.MailDomain;
import com.soffid.iam.api.MailList;
import com.soffid.iam.api.MailListRelated;
import com.soffid.iam.api.MailListRoleMember;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserMailList;
import com.soffid.iam.bpm.service.scim.ScimHelper;
import com.soffid.iam.model.CustomDialect;
import com.soffid.iam.model.EmailDomainEntity;
import com.soffid.iam.model.EmailDomainEntityDao;
import com.soffid.iam.model.EmailListContainerEntity;
import com.soffid.iam.model.EmailListEntity;
import com.soffid.iam.model.EmailListEntityDao;
import com.soffid.iam.model.ExternEmailEntity;
import com.soffid.iam.model.MailListAttributeEntity;
import com.soffid.iam.model.MailListGroupMemberEntity;
import com.soffid.iam.model.MailListRoleMemberEntity;
import com.soffid.iam.model.MetaDataEntity;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.UserEmailEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;
import com.soffid.iam.utils.TimeOutUtils;
import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.HQLQuery;
import com.soffid.scimquery.expr.AbstractExpression;
import com.soffid.scimquery.parser.ExpressionParser;
import com.soffid.scimquery.parser.ParseException;
import com.soffid.scimquery.parser.TokenMgrError;

import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.UnknownApplicationException;
import es.caib.seycon.ng.exception.UnknownGroupException;
import es.caib.seycon.ng.exception.UnknownMailListException;
import es.caib.seycon.ng.exception.UnknownRoleException;

/**
 * @see es.caib.seycon.ng.servei.LlistesDeCorreuService
 */
public class MailListsServiceImpl extends com.soffid.iam.service.MailListsServiceBase {

	/**
	 * @see es.caib.seycon.ng.servei.LlistesDeCorreuService#getLlistesDeCorreu()
	 */
	protected java.util.Collection<MailList> handleGetMailLists() throws java.lang.Exception {
		return getEmailListEntityDao().toMailListList(getEmailListEntityDao().loadAll());
	}

	protected Collection<ExternalName> handleFindExternalMailsByNameListAndDomainName(String nomLlistaCorreu, String codiDomini) throws Exception {
		Collection<ExternEmailEntity> externs = getExternEmailEntityDao().findByList(nomLlistaCorreu, codiDomini);
		if (externs != null) {
			return getExternEmailEntityDao().toExternalNameList(externs);
		}
		return new Vector();
	}

	protected MailDomain handleFindDominiCorreuByNomLlistaCorreuAndCodiDomini(String nomLlistaCorreu, String codiDomini) throws Exception {
		EmailListEntity llistaCorreu = getEmailListEntityDao().findByNameAndDomain(nomLlistaCorreu, codiDomini);
		if (llistaCorreu != null) {
			EmailDomainEntity dominiCorreu = llistaCorreu.getDomain();
			if (dominiCorreu != null) {
				MailDomain dominiCorreuVO = getEmailDomainEntityDao().toMailDomain(dominiCorreu);
				return dominiCorreuVO;
			}
		}
		return null;
	}

	protected Collection<User> handleFindUsersByMailListNameAndDomainName(String nomLlistaCorreu, String codiDomini) throws Exception {
		EmailListEntity llista = getEmailListEntityDao().findByNameAndDomain(nomLlistaCorreu, codiDomini);
		Collection<UserEntity> usuaris = new LinkedList<UserEntity>();
		for (Iterator<UserEmailEntity> it = llista.getUserMailLists().iterator(); it.hasNext(); ) {
			UserEmailEntity llistaCorreuUsuari = it.next();
			if (! Boolean.TRUE.equals(llistaCorreuUsuari.getDisabled()))
				usuaris.add(llistaCorreuUsuari.getUser());
        }
		return getUserEntityDao().toUserList(usuaris);
	}

	protected Collection<MailList> handleFindMailListsByData(String nom, String domini, String descripcio, String membres) throws Exception {
		if (nom != null && (nom.trim().compareTo("") == 0)) { //$NON-NLS-1$
			nom = null;
		}
		
		if (domini != null && (domini.trim().compareTo("") == 0 || domini.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
			domini = null;
		}
		
		if (descripcio != null && (descripcio.trim().compareTo("") == 0 || descripcio.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
			descripcio = null;
		}
		
		if (membres != null && (membres.trim().compareTo("") == 0 || membres.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
			membres = null;
		}
		
		CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
		Collection<EmailListEntity> llistesCorreu = getEmailListEntityDao().findByData(config, nom, domini, descripcio);
		if (llistesCorreu != null)
		{
			return getEmailListEntityDao().toMailListList(llistesCorreu);
		}
		
		return new Vector();
	}

	protected MailList handleCreate(MailList llistaCorreu) throws Exception {
		EmailListEntity llistaCorreuEntity = getEmailListEntityDao().mailListToEntity(llistaCorreu);
		getEmailListEntityDao().create(llistaCorreuEntity);
		llistaCorreu.setId(llistaCorreuEntity.getId());
		updateUsers(llistaCorreuEntity, llistaCorreu.getUsersList());
		updateGroups(llistaCorreuEntity, llistaCorreu.getGroupMembers());
		updateRoles(llistaCorreuEntity, llistaCorreu.getRoleMembers());
		updateLists(llistaCorreuEntity, llistaCorreu.getLists());
		updateExternal(llistaCorreuEntity, llistaCorreu.getExternalList());
		updateMailListAttributes(llistaCorreu, llistaCorreuEntity);
		return getEmailListEntityDao().toMailList(llistaCorreuEntity);
	}

	private void updateUsers(EmailListEntity llistaCorreuEntity, List<String> usersList) throws InternalErrorException {
		LinkedList<String> l = new LinkedList<String>(fixNull(usersList));
		for (UserEmailEntity m: new LinkedList<UserEmailEntity>( llistaCorreuEntity.getUserMailLists())) {
			if (Boolean.TRUE.equals(m.getDisabled())) {
				// Ignore
			}
			else if (l.contains(m.getUser().getUserName())) 
				l.remove(m.getUser().getUserName());
			else {
				if (ConfigurationCache.isHistoryEnabled()) {
					m.setEnd(new Date());
					m.setDisabled(true);
					getUserEmailEntityDao().update(m);
				}
				else {
					getUserEmailEntityDao().remove(m);
				}
			}
		}
		
		for (String ll: l) {
			UserEmailEntity m = getUserEmailEntityDao().newUserEmailEntity();
			m.setUser(getUserEntityDao().findByUserName(ll));
			if (m.getUser() == null) throw new InternalErrorException("Unknown user "+ll);
			m.setMailList(llistaCorreuEntity);
			m.setStart(new Date());
			m.setDisabled(false);
			getUserEmailEntityDao().create(m);
		}
	}

	private void updateGroups(EmailListEntity llistaCorreuEntity, List<String> groupsList) throws InternalErrorException {
		LinkedList<String> l = new LinkedList<String>(fixNull(groupsList));
		for (MailListGroupMemberEntity m: llistaCorreuEntity.getGroups()) {
			if (l.contains(m.getGroup().getName())) 
				l.remove(m.getGroup().getName());
			else
				getMailListGroupMemberEntityDao().remove(m);
		}
		
		for (String ll: l) {
			MailListGroupMemberEntity m = getMailListGroupMemberEntityDao().newMailListGroupMemberEntity();
			m.setGroup(getGroupEntityDao().findByName(ll));
			if (m.getGroup() == null) throw new InternalErrorException("Unknown group "+ll);
			m.setMailList(llistaCorreuEntity);
			getMailListGroupMemberEntityDao().create(m);
		}
	}

	private void updateRoles(EmailListEntity llistaCorreuEntity, List<String> rolesList) throws InternalErrorException {
		LinkedList<String> l = new LinkedList<String>(fixNull(rolesList));
		for (MailListRoleMemberEntity m: llistaCorreuEntity.getRoles()) {
			String shortName = m.getRole().getName()+"@"+m.getRole().getSystem().getName();
			if (l.contains(shortName)) 
				l.remove(shortName);
			else
				getMailListRoleMemberEntityDao().remove(m);
		}
		
		for (String ll: l) {
			MailListRoleMemberEntity m = getMailListRoleMemberEntityDao().newMailListRoleMemberEntity();
			m.setRole(getRoleEntityDao().findByShortName(ll));
			if (m.getRole() == null) throw new InternalErrorException("Unknown role "+ll);
			m.setMailList(llistaCorreuEntity);
			getMailListRoleMemberEntityDao().create(m);
		}
	}

	private void updateLists(EmailListEntity llistaCorreuEntity, List<String> listList) throws InternalErrorException {
		LinkedList<String> l = new LinkedList<String>(fixNull(listList));
		for (EmailListContainerEntity m: llistaCorreuEntity.getMailListContent()) {
			String shortName = m.getContains().getName()+"@"+m.getContains().getDomain().getName();
			if (l.contains(shortName)) 
				l.remove(shortName);
			else
				getEmailListContainerEntityDao().remove(m);
		}
		
		for (String ll: l) {
			EmailListContainerEntity m = getEmailListContainerEntityDao().newEmailListContainerEntity();
			String split[] = ll.split("@");
			if (split.length != 2) throw new InternalErrorException ("Any mail list must have one and only one @ character");
			m.setContains(getEmailListEntityDao().findByNameAndDomain(split[0], split[1]));
			if (m.getContains() == null) throw new InternalErrorException("Unknown mail list "+ll);
			m.setPertains(llistaCorreuEntity);
			getEmailListContainerEntityDao().create(m);
		}
	}

	private Collection<? extends String> fixNull(List<String> list) {
		return list == null ? new LinkedList<>(): list;
	}

	private void updateExternal(EmailListEntity llistaCorreuEntity, List<String> externalList) throws InternalErrorException {
		LinkedList<String> l = new LinkedList<String>(fixNull(externalList));
		for (ExternEmailEntity m: llistaCorreuEntity.getExternals()) {
			String shortName = m.getAddress();
			if (l.contains(shortName)) 
				l.remove(shortName);
			else
				getExternEmailEntityDao().remove(m);
		}
		
		for (String ll: l) {
			ExternEmailEntity m = getExternEmailEntityDao().newExternEmailEntity();
			m.setAddress(ll);
			m.setMailList(llistaCorreuEntity);
			getExternEmailEntityDao().create(m);
		}
	}


	protected void handleDelete(MailList llistaCorreu) throws Exception {
		EmailListEntity llistaCorreuEntity = getEmailListEntityDao().mailListToEntity(llistaCorreu);
		
		getExternEmailEntityDao().remove(new LinkedList<>(llistaCorreuEntity.getExternals()));
		getUserEmailEntityDao().remove(  new LinkedList<>(llistaCorreuEntity.getUserMailLists()));
		getEmailListContainerEntityDao().remove(new LinkedList<>(llistaCorreuEntity.getMailListContent()));
		getMailListGroupMemberEntityDao().remove(new LinkedList<>(llistaCorreuEntity.getGroups()));
		getMailListRoleMemberEntityDao().remove(new LinkedList<>(llistaCorreuEntity.getRoles()));
		getEmailListEntityDao().remove(llistaCorreuEntity);
	}

	protected Collection<UserMailList> handleFindUserMailListByListNameAndDomainName(String nomLlistaCorreu, String codiDomini) throws Exception {
		Collection<UserEmailEntity> llistaCorreuUsuari = getUserEmailEntityDao().findByMailList(nomLlistaCorreu, codiDomini);
		if (llistaCorreuUsuari != null) {
			return getUserEmailEntityDao().toUserMailListList(llistaCorreuUsuari);
		}
		return new Vector();
	}

	protected MailList handleUpdate(MailList llistaCorreu) throws Exception {
		EmailListEntity llistaCorreuEntity = getEmailListEntityDao().load(llistaCorreu.getId());
		if (llistaCorreuEntity != null && (
				!llistaCorreuEntity.getName().equals(llistaCorreu.getName()) ||
				!llistaCorreuEntity.getDomain().getName().equals(llistaCorreu.getDomainName())))
			getMetaDataEntityDao().renameAttributeValues(TypeEnumeration.MAIL_LIST_TYPE, 
					llistaCorreuEntity.getName()+"@"+llistaCorreuEntity.getDomain().getName(), 
					llistaCorreu.getName()+"@"+llistaCorreu.getDomainName());
		llistaCorreuEntity = getEmailListEntityDao().mailListToEntity(llistaCorreu);
		if (llistaCorreuEntity.getId() != null)
			getEmailListEntityDao().update(llistaCorreuEntity);
		else
			getEmailListEntityDao().create(llistaCorreuEntity);
		updateUsers(llistaCorreuEntity, llistaCorreu.getUsersList());
		updateGroups(llistaCorreuEntity, llistaCorreu.getGroupMembers());
		updateRoles(llistaCorreuEntity, llistaCorreu.getRoleMembers());
		updateLists(llistaCorreuEntity, llistaCorreu.getLists());
		updateExternal(llistaCorreuEntity, llistaCorreu.getExternalList());
		updateMailListAttributes(llistaCorreu, llistaCorreuEntity);
		return getEmailListEntityDao().toMailList(llistaCorreuEntity);
	}

	protected UserMailList handleCreate(UserMailList llistaCorreuUsuari) throws Exception {
		UserEmailEntity llistaCorreuUsuariEntity = getUserEmailEntityDao().userMailListToEntity(llistaCorreuUsuari);

		llistaCorreuUsuariEntity.setDisabled(false);
		llistaCorreuUsuariEntity.setStart(new Date());
		llistaCorreuUsuariEntity.setEnd(null);
		getUserEmailEntityDao().create(llistaCorreuUsuariEntity);

		// Marquem l'usuari com a modificat
		updateUserModification(llistaCorreuUsuariEntity.getUser());

		return getUserEmailEntityDao().toUserMailList(llistaCorreuUsuariEntity);
	}

	protected ExternalName handleCreate(ExternalName correuExtern) throws Exception {
		ExternEmailEntity correuExternEntity = getExternEmailEntityDao().externalNameToEntity(correuExtern);
		getExternEmailEntityDao().create(correuExternEntity);
		correuExtern.setId(correuExternEntity.getId());
		return getExternEmailEntityDao().toExternalName(correuExternEntity);
	}

	protected void handleDeleteUserMailList(UserMailList llistaCorreuUsuari) throws Exception {
		UserEmailEntity llistaCorreuUsuariEntity = getUserEmailEntityDao().userMailListToEntity(llistaCorreuUsuari);
		// Dades per fer feina...
		UserEntity usuariLlista = llistaCorreuUsuariEntity.getUser();
		String alies = llistaCorreuUsuari.getMailListName();
		String domini = llistaCorreuUsuari.getDomainCode();

		// L'esborrem
		if ( ConfigurationCache.isHistoryEnabled())
		{
			if ( ! Boolean.TRUE.equals( llistaCorreuUsuariEntity.getDisabled()))
			{
				llistaCorreuUsuariEntity.setEnd(new Date());
				llistaCorreuUsuariEntity.setDisabled(true);
				getUserEmailEntityDao().update(llistaCorreuUsuariEntity);
			}
		}
		else
			getUserEmailEntityDao().remove(llistaCorreuUsuariEntity);

		// Mirem si hem de fer neteja de la llista:
		checkEmptyMailList(alies, domini);

		// Marquem l'usuari com a modificat
		updateUserModification(usuariLlista);
	}

	// Marquem l'usuari com a modificat quan es modifica una llista de correu
	// d'usuari
	private void updateUserModification(UserEntity usuariEntity) {// OK
		String usuModifica = getPrincipal() != null ? Security.getCurrentAccount() : "SOFFID"; //$NON-NLS-1$
		usuariEntity.setLastUserModification(usuModifica);
		usuariEntity.setLastModificationDate(GregorianCalendar.getInstance().getTime());
		getUserEntityDao().update(usuariEntity);
	}

	protected void handleDelete(ExternalName correuExtern) throws Exception {
		ExternEmailEntity correuExternEntity = getExternEmailEntityDao().externalNameToEntity(correuExtern);

		// Dades per fer feina...
		String alies = correuExtern.getMailListName();
		String domini = correuExtern.getDomainCode();

		// L'esborrem
		getExternEmailEntityDao().remove(correuExternEntity);

		// Mirem si hem de fer neteja de la llista:
		checkEmptyMailList(alies, domini);

	}

	protected UserMailList handleFindUserMailListByListNameAndDomainNameAndUserName(String nomLlistaCorreu, String codiDomini, String codiUsuari) throws Exception {
		UserEmailEntity llistaCorreuUsuari = getUserEmailEntityDao().findByListAndUser(nomLlistaCorreu, codiDomini, codiUsuari);
		if (llistaCorreuUsuari != null) {
			return getUserEmailEntityDao().toUserMailList(llistaCorreuUsuari);
		}
		return null;
	}

	protected UserMailList handleUpdate(UserMailList llistaCorreuUsuari) throws Exception {
		// NOTA: en principi no s'hauria d'utilitzar..
		UserEmailEntity llistaCorreu = getUserEmailEntityDao().userMailListToEntity(llistaCorreuUsuari);
		getUserEmailEntityDao().update(llistaCorreu);

		// Marquem l'usuari com a modificat
		updateUserModification(llistaCorreu.getUser());
		return getUserEmailEntityDao().toUserMailList(llistaCorreu);
	}

	protected MailDomain handleCreate(MailDomain dominiCorreu) throws Exception {
		EmailDomainEntity domainsSameCode = getEmailDomainEntityDao().findByCode(dominiCorreu.getCode());
		if(domainsSameCode != null)
			throw new InternalErrorException(String.format(Messages.getString("MailListsServiceImpl.CodeDomainExists"), dominiCorreu.getCode())); 
		EmailDomainEntity dominiCorreuEntity = this.getEmailDomainEntityDao().mailDomainToEntity(dominiCorreu);
		getEmailDomainEntityDao().create(dominiCorreuEntity);
		dominiCorreu.setId(dominiCorreuEntity.getId());
		return getEmailDomainEntityDao().toMailDomain(dominiCorreuEntity);
	}

	protected void handleDelete(MailDomain dominiCorreu) throws Exception {
		EmailDomainEntity dominiCorreuEntity = this.getEmailDomainEntityDao().mailDomainToEntity(dominiCorreu);
		if (!dominiCorreuEntity.getMailLists().isEmpty())
			throw new InternalErrorException(String.format(Messages.getString("DominiCorreuEntityDaoImpl.IntegrityException"), dominiCorreu.getCode()));
		if (!dominiCorreuEntity.getUsers().isEmpty())
			throw new InternalErrorException(String.format(Messages.getString("DominiCorreuEntityDaoImpl.IntegrityException2"), dominiCorreu.getCode()));
		getEmailDomainEntityDao().remove(dominiCorreuEntity);
	}

	protected ExternalName handleFinExternalMailByEmail(String adreca) throws Exception {
		ExternEmailEntity correuExternEntity = getExternEmailEntityDao().findByAddress(adreca);
		if (correuExternEntity != null) {
			ExternalName correuExtern = getExternEmailEntityDao().toExternalName(correuExternEntity);
			return correuExtern;
		}
		return null;
	}

	protected MailDomain handleFindMailDomainByName(String codi) throws Exception {
		EmailDomainEntity dominiCorreuEntity = getEmailDomainEntityDao().findByCode(codi);
		if (dominiCorreuEntity != null) {
			MailDomain dominiCorreu = getEmailDomainEntityDao().toMailDomain(dominiCorreuEntity);
			return dominiCorreu;
		}
		return null;
	}

	protected Collection<MailDomain> handleGetDomainMails() throws Exception {
		return getEmailDomainEntityDao().toMailDomainList(getEmailDomainEntityDao().loadAll());
	}

	protected MailDomain handleUpdate(MailDomain dominiCorreu) throws Exception {
		EmailDomainEntity dominiCorreuEntity = getEmailDomainEntityDao().load(dominiCorreu.getId());
		if (dominiCorreu != null && 
				!dominiCorreuEntity.getName().equals(dominiCorreu.getName()))
			getMetaDataEntityDao().renameAttributeValues(TypeEnumeration.MAIL_DOMAIN_TYPE, 
					dominiCorreuEntity.getName(), 
					dominiCorreu.getName());
		dominiCorreuEntity = this.getEmailDomainEntityDao().mailDomainToEntity(dominiCorreu);
		getEmailDomainEntityDao().update(dominiCorreuEntity);
		return getEmailDomainEntityDao().toMailDomain(dominiCorreuEntity);
	}

	protected MailList handleFindMailListByNameAndDomainName(String nomLlistaCorreu, String codiDomini) throws Exception {
		EmailListEntity llistaCorreuEntity = getEmailListEntityDao().findByNameAndDomain(nomLlistaCorreu, codiDomini);
		if (llistaCorreuEntity != null) {
			MailList llistaCorreu = getEmailListEntityDao().toMailList(llistaCorreuEntity);
			return llistaCorreu;
		}
		return null;
	}

	protected MailListRelated handleCreate(MailListRelated relacioLlistaCorreu) throws Exception {
		EmailListContainerEntity relacioLlistaCorreuEntity = getEmailListContainerEntityDao().mailListRelatedToEntity(relacioLlistaCorreu);
		getEmailListContainerEntityDao().create(relacioLlistaCorreuEntity);
		relacioLlistaCorreu.setId(relacioLlistaCorreuEntity.getId());
		relacioLlistaCorreu = getEmailListContainerEntityDao().toMailListRelated(relacioLlistaCorreuEntity);
		return relacioLlistaCorreu;
	}

	protected void handleDelete(MailListRelated relacioLlistaCorreu) throws Exception {
		EmailListContainerEntity relacioLlistaCorreuEntity = getEmailListContainerEntityDao().mailListRelatedToEntity(relacioLlistaCorreu);
		EmailListEntity llistaConte = relacioLlistaCorreuEntity.getContains();
		// Per procesar després la baixa de llistes buides (de la contenidora)
		String alies = llistaConte.getName();
		String domini = llistaConte.getDomain() != null ? llistaConte.getDomain().getName() : ""; //$NON-NLS-1$

		// Esborrem la relació entre llistes
		getEmailListContainerEntityDao().remove(relacioLlistaCorreuEntity);

		// Mirem si hem de fer neteja de la llista:
		checkEmptyMailList(alies, domini);
	}

	protected MailListRelated handleFindRelationsMailListByNameAndBelongsMailListNameAndNameAndContainsMailListName(String nomPertany, String dominiCorreuPertany, String nomConte, String dominiCorreuConte) throws Exception {
		EmailListContainerEntity relacioLlistaCorreuEntity = getEmailListContainerEntityDao().findByContainerAndContained(nomPertany, dominiCorreuPertany, nomConte, dominiCorreuConte);
		if (relacioLlistaCorreuEntity != null) {
			MailListRelated relacioLlistaCorreu = getEmailListContainerEntityDao().toMailListRelated(relacioLlistaCorreuEntity);
			return relacioLlistaCorreu;
		}
		return null;
	}

	protected Collection<MailListRelated> handleFindRelationsMailListByNameContainsMailListAndDomainName(String nomLlistaCorreuConte, String codiDomini) throws Exception {
		Collection<EmailListContainerEntity> relacionsLlistaCorreu = getEmailListContainerEntityDao().findByContained(nomLlistaCorreuConte, codiDomini);
		if (relacionsLlistaCorreu != null) {
			return getEmailListContainerEntityDao().toMailListRelatedList(relacionsLlistaCorreu);
		}
		return new Vector();
	}

	protected Collection<MailListRelated> handleFindRelationsMailListByNameBelongsMailListAndDomainName(String nomLlistaCorreuPertany, String codiDomini) throws Exception {
		Collection<EmailListContainerEntity> relacionsLlistaCorreu = getEmailListContainerEntityDao().findByContainer(nomLlistaCorreuPertany, codiDomini);
		if (relacionsLlistaCorreu != null) {
			return getEmailListContainerEntityDao().toMailListRelatedList(relacionsLlistaCorreu);
		}
		return new Vector();
	}

	protected MailListRelated handleUpdate(MailListRelated relacioLlistaCorreu) throws Exception {
		EmailListContainerEntity relacioLlistaCorreuEntity = getEmailListContainerEntityDao().mailListRelatedToEntity(relacioLlistaCorreu);
		getEmailListContainerEntityDao().update(relacioLlistaCorreuEntity);
		relacioLlistaCorreu = getEmailListContainerEntityDao().toMailListRelated(relacioLlistaCorreuEntity);
		return relacioLlistaCorreu;
	}

	protected Collection<MailDomain> handleFindMailDomainsByFilter(String codi, String descripcio, String obsolet) throws Exception {
		if (codi != null && ((codi.trim().compareTo("") == 0) || (codi.trim().compareTo("%") == 0))) { //$NON-NLS-1$ //$NON-NLS-2$
			codi = null;
		}
		
		if (descripcio != null && ((descripcio.trim().compareTo("") == 0) || (descripcio.trim().compareTo("%") == 0))) { //$NON-NLS-1$ //$NON-NLS-2$
			descripcio = null;
		}
		
		if (obsolet != null && ((obsolet.trim().compareTo("") == 0) || (obsolet.trim().compareTo("%") == 0))) { //$NON-NLS-1$ //$NON-NLS-2$
			obsolet = null;
		}
		
		Collection<EmailDomainEntity> dominisDeCorreu = getEmailDomainEntityDao().findByCriteria(codi, descripcio, obsolet);
		if (dominisDeCorreu != null)
		{
			return getEmailDomainEntityDao().toMailDomainList(dominisDeCorreu);
		}
		
		return new Vector();
	}

	protected Collection<UserMailList> handleFindUserMailListByUserName(String codiUsuari) throws Exception {
		Collection<UserEmailEntity> llistaCorreuUsuaris = getUserEmailEntityDao().findByUser(codiUsuari);
		if (llistaCorreuUsuaris != null) {
			return getUserEmailEntityDao().toUserMailListList(llistaCorreuUsuaris);
		}
		return new Vector();
	}

	protected Collection<UserMailList> handleFindUserMailListHistoryByUserName(String codiUsuari) throws Exception {
		UserEntity u = getUserEntityDao().findByUserName(codiUsuari);
		Collection<UserEmailEntity> llistaCorreuUsuaris = u.getUserMailList();
		if (llistaCorreuUsuaris != null) {
			return getUserEmailEntityDao().toUserMailListList(llistaCorreuUsuaris);
		}
		return new Vector();
	}

	protected String handleFindLlistaCompactaExternsByNomLlistaCorreuAndCodiDomini(String nomLlistaCorreu, String codiDomini)
			throws Exception {
		String llistat = ""; //$NON-NLS-1$
		EmailListEntity llistaCorreuEntity = getEmailListEntityDao().findByNameAndDomain(nomLlistaCorreu, codiDomini);
		Collection correusExterns = llistaCorreuEntity.getExternals();
		if (correusExterns != null) {
			Iterator iterator = correusExterns.iterator();
			while (iterator.hasNext()) {
				ExternEmailEntity correuExtern = (ExternEmailEntity) iterator.next();
				llistat += correuExtern.getAddress() + ", "; //$NON-NLS-1$
			}
		}
		if ("".equals(llistat)) { //$NON-NLS-1$
			return llistat;
		} else {
			return llistat.substring(0, llistat.length() - 2);
		}
	}

	protected String handleFindLlistaCompactaLlistesByNomLlistaCorreuAndCodiDomini(String nomLlistaCorreu, String codiDomini)
			throws Exception {
		String llistat = ""; //$NON-NLS-1$
		Collection llistesCorreuEntities = getEmailListContainerEntityDao().findByContained(nomLlistaCorreu, codiDomini);
		if (llistesCorreuEntities != null) {
			Iterator iterator = llistesCorreuEntities.iterator();
			while (iterator.hasNext()) {
				EmailListContainerEntity relacioLlistaCorreuEntity = (EmailListContainerEntity) iterator.next();
				EmailListEntity llistaCorreuEntityPertany = relacioLlistaCorreuEntity.getPertains();
				llistat += llistaCorreuEntityPertany.getName() + "@" + llistaCorreuEntityPertany.getDomain() + ", "; //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		if ("".equals(llistat)) { //$NON-NLS-1$
			return llistat;
		} else {
			return llistat.substring(0, llistat.length() - 2);
		}
	}

	protected String handleFindLlistaCompactaUsuarisByNomLlistaCorreuAndCodiDomini(String nomLlistaCorreu, String codiDomini)
			throws Exception {
		String llistat = ""; //$NON-NLS-1$
		EmailListEntity llistaCorreuEntity = getEmailListEntityDao().findByNameAndDomain(nomLlistaCorreu, codiDomini);
		Collection llistaCorreuUsuaris = llistaCorreuEntity.getUserMailLists();
		if (llistaCorreuUsuaris != null) {
			Iterator iterator = llistaCorreuUsuaris.iterator();
			while (iterator.hasNext()) {
				UserEmailEntity llistaCorreuUsuari = (UserEmailEntity) iterator.next();
				if (! Boolean.TRUE.equals(llistaCorreuUsuari.getDisabled()))
						llistat += llistaCorreuUsuari.getUser().getUserName() + ", "; //$NON-NLS-1$
			}
		}
		if ("".equals(llistat)) { //$NON-NLS-1$
			return llistat;
		} else {
			return llistat.substring(0, llistat.length() - 2);
		}
	}

	protected void handleCheckEmptyMailList(String nomLlistaCorreu, String codiDomini) throws Exception {
		// Mirem si en queda cap membre a la llista per
		// veure si l'hem d'esborrar:
		Collection correusExterns = findExternalMailsByNameListAndDomainName(nomLlistaCorreu, codiDomini);
		Collection usuaris = findUserMailListByListNameAndDomainName(nomLlistaCorreu, codiDomini);
		Collection llistesDeCorreuConte = findRelationsMailListByNameContainsMailListAndDomainName(nomLlistaCorreu, codiDomini);
		// no pot tindre tampoc llistes on pertany (donaria error)
		Collection llistesDeCorreuPertany = findRelationsMailListByNameBelongsMailListAndDomainName(nomLlistaCorreu, codiDomini);
		if (correusExterns.size() == 0 && usuaris.size() == 0 && llistesDeCorreuConte.size() == 0
				&& llistesDeCorreuPertany.size() == 0) {
			EmailListEntity llistaEsborrar = getEmailListEntityDao().findByNameAndDomain(nomLlistaCorreu, codiDomini);
			if (llistaEsborrar != null)
			{
				for (UserEmailEntity ul: llistaEsborrar.getUserMailLists())
				{
					getUserEmailEntityDao().remove(ul);
				}
				getEmailListEntityDao().remove(llistaEsborrar);
			}
		}

	}

	// és equivalent a handleDelete però no es fa neteja de llistes
	// perquè es puga emprar des dels WF
	protected void handleDeleteAtomic(UserMailList llistaCorreuUsuari) throws Exception {
		UserEmailEntity llistaCorreuUsuariEntity = getUserEmailEntityDao().userMailListToEntity(llistaCorreuUsuari);
		// Dades per fer feina...
		UserEntity usuariLlista = llistaCorreuUsuariEntity.getUser();

		// L'esborrem
		if (ConfigurationCache.isHistoryEnabled())
		{
			llistaCorreuUsuariEntity.setDisabled(true);
			llistaCorreuUsuariEntity.setEnd(new Date());
			getUserEmailEntityDao().update(llistaCorreuUsuariEntity);
		}
		else
			getUserEmailEntityDao().remove(llistaCorreuUsuariEntity);

		// Marquem l'usuari com a modificat
		updateUserModification(usuariLlista);
	}

	// és equivalent a handleDelete però no es fa neteja de llistes
	// perquè es puga emprar des dels WF
	protected void handleDeleteAtomic(ExternalName correuExtern) throws Exception {
		ExternEmailEntity correuExternEntity = getExternEmailEntityDao().externalNameToEntity(correuExtern);

		// Dades per fer feina...
		String alies = correuExtern.getMailListName();
		String domini = correuExtern.getDomainCode();

		// L'esborrem
		getExternEmailEntityDao().remove(correuExternEntity);
	}

	// és equivalent a handleDelete però no es fa neteja de la llista continguda
	// perquè es puga emprar des dels WF
	protected void handleDeleteAtomic(MailListRelated relacioLlistaCorreu) throws Exception {
		EmailListContainerEntity relacioLlistaCorreuEntity = getEmailListContainerEntityDao().mailListRelatedToEntity(relacioLlistaCorreu);

		// Esborrem la relació entre llistes
		getEmailListContainerEntityDao().remove(relacioLlistaCorreuEntity);
	}

	@Override
    protected Collection<Group> handleFindGroupMembers(String nomLlistaCorreu, String codiDomini) throws Exception {
		
		EmailListEntity list = getEmailListEntityDao().findByNameAndDomain(nomLlistaCorreu, codiDomini);
		List<Group> grups = new LinkedList<Group>();
		for (MailListGroupMemberEntity member : list.getGroups()) {
            grups.add(getGroupEntityDao().toGroup(member.getGroup()));
        }
		return grups;
	}

	@Override
	protected Collection<MailListRoleMember> handleFindRoleMembers(
			String nomLlistaCorreu, String codiDomini) throws Exception {
		EmailListEntity list = getEmailListEntityDao().findByNameAndDomain(nomLlistaCorreu, codiDomini);
		return getMailListRoleMemberEntityDao().toMailListRoleMemberList(list.getRoles());
	}

	@Override
	protected void handleSubscribeGroup(String mailListName,
			String mailListDomain, String groupName) throws Exception {
		MailListGroupMemberEntity entity = getMailListGroupMemberEntityDao().newMailListGroupMemberEntity();
		EmailListEntity list = getEmailListEntityDao().findByNameAndDomain(mailListName, mailListDomain);
		if (list == null)
			throw new UnknownMailListException(mailListName+"@"+mailListDomain);
		entity.setMailList(list);
		entity.setGroup(getGroupEntityDao().findByName(groupName));
		if (entity.getGroup() == null)
			throw new UnknownGroupException(groupName);
		getMailListGroupMemberEntityDao().create(entity);
	}

	@Override
	protected void handleUnsubscribeGroup(String mailListName,
			String mailListDomain, String groupName) throws Exception {
		
		EmailListEntity list = getEmailListEntityDao().findByNameAndDomain(mailListName, mailListDomain);
		if (list == null)
			throw new UnknownMailListException(mailListName+"@"+mailListDomain);
		for (MailListGroupMemberEntity group : list.getGroups()) {
            if (group.getGroup().getName().equals(groupName)) getMailListGroupMemberEntityDao().remove(group);
        }	
		
	}

	@Override
	protected MailListRoleMember handleSubscribeRole(String mailListName,
			String mailListDomain, MailListRoleMember roleMember)
			throws Exception {
		MailListRoleMemberEntity entity = getMailListRoleMemberEntityDao().newMailListRoleMemberEntity();
		EmailListEntity list = getEmailListEntityDao().findByNameAndDomain(mailListName, mailListDomain);
		if (list == null)
			throw new UnknownMailListException(mailListName+"@"+mailListDomain);
		entity.setMailList(list);
		RoleEntity role = getRoleEntityDao().findByNameAndSystem(roleMember.getRoleName(), roleMember.getDispatcherName());
		if (role == null)
			throw new UnknownRoleException(roleMember.getRoleName()+"@"+roleMember.getDispatcherName());
		entity.setRole(role);
		if (roleMember.getScope() != null && roleMember.getScope().length() > 0)
		{
			if (TipusDomini.APLICACIONS.equals(role.getDomainType())
					|| TipusDomini.APPLICATIONS.equals(role.getDomainType()))
			{
				entity.setInformationSystemScope(getInformationSystemEntityDao().findByCode(roleMember.getScope()));
				if (entity.getInformationSystemScope() == null)
					throw new UnknownApplicationException(roleMember.getScope());
			}
			else if (TipusDomini.GRUPS.equals(role.getDomainType()) || 
					TipusDomini.GRUPS_USUARI.equals(role.getDomainType()) ||
							TipusDomini.GROUPS.equals(role.getDomainType()) ||
							TipusDomini.MEMBERSHIPS.equals(role.getDomainType()))
			{
				entity.setGroupScope(getGroupEntityDao().findByName(roleMember.getScope()));
				if (entity.getGroupScope() == null)
					throw new UnknownGroupException(roleMember.getScope());
			}
			if (TipusDomini.DOMINI_APLICACIO.equals(role.getDomainType()) ||
					TipusDomini.CUSTOM.equals(role.getDomainType()))
			{
				entity.setDomainValueScope(getDomainValueEntityDao().findByApplicationDomainValue(role.getInformationSystem().getName(), role.getApplicationDomain().getName(), roleMember.getScope()));
				if (entity.getDomainValueScope() == null)
					throw new IllegalArgumentException(roleMember.getScope());
			}
			
		}
		entity.setRole(role);
		getMailListRoleMemberEntityDao().create(entity);
		return getMailListRoleMemberEntityDao().toMailListRoleMember(entity);
	}

	@Override
	protected void handleUnsubscribeRole(String mailListName,
			String mailListDomain, MailListRoleMember roleMember)
			throws Exception {
		MailListRoleMemberEntity entity = getMailListRoleMemberEntityDao().newMailListRoleMemberEntity();
		EmailListEntity list = getEmailListEntityDao().findByNameAndDomain(mailListName, mailListDomain);
		if (list == null)
			throw new UnknownMailListException(mailListName+"@"+mailListDomain);
		for (MailListRoleMemberEntity member : list.getRoles()) {
            entity.setMailList(list);
            RoleEntity role = member.getRole();
            if (role.getName().equals(roleMember.getRoleName()) && role.getSystem().getName().equals(roleMember.getDispatcherName())) {
                if (roleMember.getScope() == null || roleMember.getScope().length() == 0) {
                    if (member.getDomainValueScope() == null && member.getGroupScope() == null && member.getInformationSystemScope() == null) {
                        getMailListRoleMemberEntityDao().remove(member);
                    }
                } else {
                    if ((TipusDomini.APLICACIONS.equals(role.getDomainType()) ||
                    		TipusDomini.APPLICATIONS.equals(role.getDomainType())) && 
                    		member.getInformationSystemScope() != null && member.getInformationSystemScope().getName().equals(roleMember.getScope())) {
                        getMailListRoleMemberEntityDao().remove(member);
                    } else if ((TipusDomini.GRUPS.equals(role.getDomainType()) || 
                    		TipusDomini.GRUPS_USUARI.equals(role.getDomainType()) || 
                    		TipusDomini.GROUPS.equals(role.getDomainType()) || 
                    		TipusDomini.MEMBERSHIPS.equals(role.getDomainType())) && member.getGroupScope() != null && member.getGroupScope().getName().equals(roleMember.getScope())) {
                        getMailListRoleMemberEntityDao().remove(member);
                    } else if ((TipusDomini.DOMINI_APLICACIO.equals(role.getDomainType()) ||
                    		TipusDomini.CUSTOM.equals(role.getDomainType())) && 
                    		member.getDomainValueScope() != null && 
                    		member.getDomainValueScope().getValue().equals(roleMember.getScope())) {
                        getMailListRoleMemberEntityDao().remove(member);
                    }
                }
            }
        }
	}

	private void updateMailListAttributes (MailList app, EmailListEntity entity) throws InternalErrorException
	{
		if (entity != null)
		{
			Map<String, Object> attributes = app.getAttributes();
			if (attributes == null)
				attributes = (new HashMap<String, Object>());
			
			LinkedList<MailListAttributeEntity> entities = new LinkedList<MailListAttributeEntity> (entity.getAttributes());
			HashSet<String> keys = new HashSet<String>();
			for (String key: attributes.keySet() )
			{
				for (MetaDataEntity metadata: getMetaDataEntityDao().findDataTypesByScopeAndName(MetadataScope.MAIL_LIST, key))
				{
					Object v = attributes.get(key);
					if (v == null)
					{
						// Do nothing
					}
					else if (v instanceof Collection)
					{
						Collection l = (Collection) v;
						for (Object o: (Collection) v)
						{
							if (o != null)
							{
								updateMailListAttribute(entity, entities, key, metadata, o);
							}
						}
					}
					else
					{
						updateMailListAttribute(entity, entities, key, metadata, v);
					}
				}
			}
			
			entity.getAttributes().removeAll(entities);
			getEmailListEntityDao().update(entity);

			Collection<MetaDataEntity> md = getMetaDataEntityDao().findByScope(MetadataScope.MAIL_LIST);
			
			for ( MetaDataEntity m: md) if ( m.getBuiltin() == null || ! m.getBuiltin().booleanValue() )
			{
				Object o = attributes.get(m.getName());
				if ( o == null || "".equals(o))
				{
					if (m.getRequired() != null && m.getRequired().booleanValue())
						throw new InternalErrorException(String.format("Missing attribute %s", m.getLabel()));
				} else {
					if (m.getUnique() != null && m.getUnique().booleanValue())
					{
						Collection<String> l = (Collection<String>) ( o instanceof Collection? (Collection) o: Collections.singletonList(o) );
						for (String v: l)
						{
							List<MailListAttributeEntity> p = getMailListAttributeEntityDao().findByNameAndValue(m.getName(), v);
							if (p.size() > 1)
								throw new InternalErrorException(String.format("Already exists a user with %s %s",
										m.getLabel(), v));
						}
					}
				}
			}
		}
	}

	private void updateMailListAttribute(EmailListEntity entity, LinkedList<MailListAttributeEntity> attributes, String key,
			MetaDataEntity metadata, Object value) throws InternalErrorException {
		MailListAttributeEntity aae = findMailListAttributeEntity(attributes, key, value);
		if (aae == null)
		{
			getAttributeValidationService().validate(metadata, value);
			aae = getMailListAttributeEntityDao().newMailListAttributeEntity();
			aae.setMailList(entity);
			aae.setMetadata(metadata);
			aae.setObjectValue(value);
			getMailListAttributeEntityDao().create(aae);
			entity.getAttributes().add(aae);
		}
		else
			attributes.remove(aae);
	}

	private MailListAttributeEntity findMailListAttributeEntity(LinkedList<MailListAttributeEntity> entities, String key,
			Object o) {
		for (MailListAttributeEntity aae: entities)
		{
			if (aae.getMetadata().getName().equals(key))
			{
				if (aae.getObjectValue() != null && aae.getObjectValue().equals(o))
					return aae;
			}
		}
		return null;
	}



	protected void findByJsonQuery ( AsyncList<MailList> result, String query) throws EvalException, InternalErrorException, UnsupportedEncodingException, ClassNotFoundException, JSONException, ParseException, TokenMgrError
	{
		// Register virtual attributes for additional data
		AdditionalDataJSONConfiguration.registerVirtualAttributes();

		// Prepare query HQL
		AbstractExpression expression = ExpressionParser.parse(query);
		expression.setOracleWorkaround( CustomDialect.isOracle());
		HQLQuery hql = expression.generateHSQLString(MailList.class);
		String qs = hql.getWhereString().toString();
		if (qs.isEmpty())
			qs = "o.tenant.id = :tenantId";
		else
			qs = "(" + qs + ") and o.tenant.id = :tenantId";
		hql.setWhereString(new StringBuffer(qs));

		// Include HQL parameters
		Map<String, Object> params = hql.getParameters();
		Parameter paramArray[] = new Parameter[params.size() + 1];
		int i = 0;
		for (String s : params.keySet())
			paramArray[i++] = new Parameter(s, params.get(s));
		paramArray[i++] = new Parameter("tenantId", Security.getCurrentTenantId());

		// Execute HQL and generate result
		for (EmailListEntity ge : getEmailListEntityDao().query(hql.toString(), paramArray)) {
			if (result.isCancelled())
				return;
			MailList g = getEmailListEntityDao().toMailList(ge);
			if (!hql.isNonHQLAttributeUsed() || expression.evaluate(g)) {
				result.add(g);
			}
		}
	}
	@Override
	protected Collection<MailList> handleFindMailListByJsonQuery(String query) throws InternalErrorException, Exception {
		AsyncList<MailList> result = new AsyncList<MailList>();
		result.setTimeout(TimeOutUtils.getGlobalTimeOut());
		findByJsonQuery(result, query);
		if (result.isCancelled())
			TimeOutUtils.generateException();
		result.done();
		return result.get();
	}


	@Override
	protected AsyncList<MailList> handleFindMailListByJsonQueryAsync(final String query) throws Exception {
		final AsyncList<MailList> result = new AsyncList<MailList>();
		getAsyncRunnerService().run(new Runnable() {
			public void run() {
				try {
					findByJsonQuery(result, query);
				} catch (Exception e) {
					result.cancel(e);
				}
			}
		}, result);
		return result;
	}

	@Override
	protected AsyncList<MailDomain> handleFindMailDomainsByJsonQueryAsync(String query) throws Exception {
		return handleFindMailDomainsByTextAndFilterAsync(null, query);
	}

	@Override
	protected AsyncList<MailDomain> handleFindMailDomainsByTextAndFilterAsync(String text, String query) throws Exception {
		final AsyncList<MailDomain> result = new AsyncList<MailDomain>();
		getAsyncRunnerService().run(new Runnable() {
			@Override
			public void run() {
				try {
					doFindMailDomainByTextAndJsonQuery(text, query, null, null, result);
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}				
			}
		}, result);

		return result;
	}

	@Override
	protected PagedResult<MailDomain> handleFindMailDomainsByJsonQuery(String query, Integer first, Integer pageSize)
			throws Exception {
		final LinkedList<MailDomain> result = new LinkedList<MailDomain>();
		return doFindMailDomainByTextAndJsonQuery(null, query, first, pageSize, result);
	}

	@Override
	protected PagedResult<MailDomain> handleFindMailDomainsByTextAndFilter(String text, String query, Integer first,
			Integer pageSize) throws Exception {
		final LinkedList<MailDomain> result = new LinkedList<MailDomain>();
		return doFindMailDomainByTextAndJsonQuery(text, query, first, pageSize, result);
	}
	
	private PagedResult<MailDomain> doFindMailDomainByTextAndJsonQuery(String text, String jsonQuery,
			Integer start, Integer pageSize,
			List<MailDomain> result) throws UnsupportedEncodingException, ClassNotFoundException, InternalErrorException, EvalException, JSONException, ParseException, TokenMgrError {
		final EmailDomainEntityDao dao = getEmailDomainEntityDao();
		ScimHelper h = new ScimHelper(MailDomain.class);
		h.setPrimaryAttributes(new String[] { "name", "description"});
		CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
		config.setFirstResult(start);
		config.setMaximumResultSize(pageSize);
		h.setConfig(config);
		h.setTenantFilter("tenant.id");
		h.setGenerator((entity) -> {
			return dao.toMailDomain((EmailDomainEntity) entity);
		}); 
		h.search(text, jsonQuery, (Collection) result); 
		PagedResult<MailDomain> pr = new PagedResult<>();
		pr.setStartIndex(start);
		pr.setItemsPerPage(pageSize);
		pr.setTotalResults(h.count());
		pr.setResources(result);
		return pr;
	}
	
	protected PagedResult<MailList> handleFindMailListByTextAndFilter(String text, String query, Integer first,
			Integer pageSize) throws Exception {
		final LinkedList<MailList> result = new LinkedList<MailList>();
		return doFindMailListByTextAndJsonQuery(text, query, first, pageSize, result);
	}
	
	private PagedResult<MailList> doFindMailListByTextAndJsonQuery(String text, String jsonQuery,
			Integer start, Integer pageSize,
			List<MailList> result) throws UnsupportedEncodingException, ClassNotFoundException, InternalErrorException, EvalException, JSONException, ParseException, TokenMgrError {
		final EmailListEntityDao dao = getEmailListEntityDao();
		ScimHelper h = new ScimHelper(MailList.class);
		h.setPrimaryAttributes(new String[] { "name", "domain", "description"});
		CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
		config.setFirstResult(start);
		config.setMaximumResultSize(pageSize);
		h.setConfig(config);
		h.setTenantFilter("tenant.id");
		h.setGenerator((entity) -> {
			return dao.toMailList((EmailListEntity) entity);
		}); 
		h.search(text, jsonQuery, (Collection) result); 
		PagedResult<MailList> pr = new PagedResult<>();
		pr.setStartIndex(start);
		pr.setItemsPerPage(pageSize);
		pr.setTotalResults(h.count());
		pr.setResources(result);
		return pr;
	}
	
	@Override
	protected AsyncList<MailList> handleFindMailListByTextAndFilterAsync(String text, String query) throws Exception {
		final AsyncList<MailList> result = new AsyncList<MailList>();
		getAsyncRunnerService().run(new Runnable() {
			@Override
			public void run() {
				try {
					doFindMailListByTextAndJsonQuery(text, query, null, null, result);
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}				
			}
		}, result);

		return result;
	}


}
