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
package com.soffid.iam.model;

import com.soffid.iam.api.Audit;
import com.soffid.iam.api.MailList;
import com.soffid.iam.api.MailListRoleMember;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.EmailDomainEntity;
import com.soffid.iam.model.EmailListContainerEntity;
import com.soffid.iam.model.EmailListEntity;
import com.soffid.iam.model.ExternEmailEntity;
import com.soffid.iam.model.MailListGroupMemberEntity;
import com.soffid.iam.model.MailListRoleMemberEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserEmailEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserGroupEntity;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.utils.ExceptionTranslator;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.*;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * @see es.caib.seycon.ng.model.LlistaCorreuEntity
 */
public class EmailListEntityDaoImpl extends
		com.soffid.iam.model.EmailListEntityDaoBase {

	private void auditarLlistaDeCorreu(String accio, String nomLlistaDeCorreu,
			String dominiLlistaCorreu) {
		String codiUsuari = Security.getCurrentAccount();
		Audit auditoria = new Audit();
		auditoria.setAction(accio);
		auditoria.setMailList(nomLlistaDeCorreu);
		auditoria.setMailDomain(dominiLlistaCorreu);
		auditoria.setAuthor(codiUsuari);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				Messages.getString("EmailListEntityDaoImpl.dateFormat")); //$NON-NLS-1$
		auditoria.setAdditionalInfo(dateFormat.format(GregorianCalendar.getInstance().getTime()));
		auditoria.setObject("SC_LLICOR"); //$NON-NLS-1$
		AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
		getAuditEntityDao().create(auditoriaEntity);
	}

	public void create(com.soffid.iam.model.EmailListEntity llistaCorreu) throws RuntimeException {
		try {
			super.create(llistaCorreu);
			getSession(false).flush();
			String domini = llistaCorreu.getDomain() == null ? null : llistaCorreu.getDomain().getName();
			auditarLlistaDeCorreu("C", llistaCorreu.getName(), domini); //$NON-NLS-1$
			generateUpdateTasks(llistaCorreu);
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("EmailListEntityDaoImpl.4"), llistaCorreu.getName(), message));
		}
	}

	public void update(com.soffid.iam.model.EmailListEntity llistaCorreu) throws RuntimeException {
		try {
			super.update(llistaCorreu);
			getSession(false).flush();
			String domini = llistaCorreu.getDomain() == null ? null : llistaCorreu.getDomain().getName();
			auditarLlistaDeCorreu("U", llistaCorreu.getName(), domini); //$NON-NLS-1$
			generateUpdateTasks(llistaCorreu);
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("EmailListEntityDaoImpl.6"), llistaCorreu.getName(), message));
		}
	}

	public void remove(com.soffid.iam.model.EmailListEntity llistaCorreu) throws RuntimeException {
		try {
			String nomLlistaDeCorreu = llistaCorreu.getName();
			String domini = llistaCorreu.getDomain() == null ? null : llistaCorreu.getDomain().getName();
			super.remove(llistaCorreu);
			getSession(false).flush();
			auditarLlistaDeCorreu("D", nomLlistaDeCorreu, domini); //$NON-NLS-1$
			generateUpdateTasks(llistaCorreu);
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("EmailListEntityDaoImpl.8"), llistaCorreu.getName(), message));
		}
	}

	private String findLlistaCompactaExternsByNomLlistaCorreuAndCodiDomini(
			String nomLlistaCorreu, String codiDomini) {
		String llistat = ""; //$NON-NLS-1$
		EmailListEntity llistaCorreuEntity = this.findByNameAndDomain(nomLlistaCorreu, codiDomini);
		Collection correusExterns = llistaCorreuEntity.getExternals();
		if (correusExterns != null) {
			Iterator iterator = correusExterns.iterator();
			while (iterator.hasNext()) {
				ExternEmailEntity correuExtern = (ExternEmailEntity) iterator.next();
				llistat += correuExtern.getAddress() + ", "; //$NON-NLS-1$
			}
		}
		if (llistat == "") { //$NON-NLS-1$
			return llistat;
		} else {
			return llistat.substring(0, llistat.length() - 2);
		}
	}

	private String findLlistaCompactaLlistesByNomLlistaCorreuAndCodiDomini(
			String nomLlistaCorreu, String codiDomini) {
		String llistat = ""; //$NON-NLS-1$
		Collection llistesCorreuEntities = getEmailListContainerEntityDao().findByContained(nomLlistaCorreu, codiDomini);
		if (llistesCorreuEntities != null) {
			Iterator iterator = llistesCorreuEntities.iterator();
			while (iterator.hasNext()) {
				EmailListContainerEntity relacioLlistaCorreuEntity = (EmailListContainerEntity) iterator.next();
				EmailListEntity llistaCorreuEntityPertany = relacioLlistaCorreuEntity.getPertains();
				String codiDominiCurrent = llistaCorreuEntityPertany.getDomain() == null ? null : llistaCorreuEntityPertany.getDomain().getName();
				llistat += llistaCorreuEntityPertany.getName() + "@" + codiDominiCurrent + ", "; //$NON-NLS-1$
			}
		}
		if (llistat == "") { //$NON-NLS-1$
			return llistat;
		} else {
			return llistat.substring(0, llistat.length() - 2);
		}
	}

	private String findLlistaCompactaUsuarisByNomLlistaCorreuAndCodiDomini(
			String nomLlistaCorreu, String codiDomini) {
		String llistat = ""; //$NON-NLS-1$
		EmailListEntity llistaCorreuEntity = this.findByNameAndDomain(nomLlistaCorreu, codiDomini);
		Collection llistaCorreuUsuaris = llistaCorreuEntity.getUserMailLists();
		if (llistaCorreuUsuaris != null) {
			Iterator iterator = llistaCorreuUsuaris.iterator();
			while (iterator.hasNext()) {
				UserEmailEntity llistaCorreuUsuari = (UserEmailEntity) iterator.next();
				llistat += llistaCorreuUsuari.getUser().getUserName() + ", "; //$NON-NLS-1$
			}
		}
		if (llistat == "") { //$NON-NLS-1$
			return llistat;
		} else {
			return llistat.substring(0, llistat.length() - 2);
		}
	}

	public void toMailList(com.soffid.iam.model.EmailListEntity sourceEntity, com.soffid.iam.api.MailList targetVO) {
		// @todo verify behavior of toLlistaCorreu
		super.toMailList(sourceEntity, targetVO);
		toLlistaCorreuCustom(sourceEntity, targetVO);
	}

	void toLlistaCorreuCustom(com.soffid.iam.model.EmailListEntity sourceEntity, com.soffid.iam.api.MailList targetVO) {

		EmailDomainEntity domini = sourceEntity.getDomain();
		if (domini != null) {
			targetVO.setDomainCode(domini.getName());
		}

		String nomLlista = sourceEntity.getName();
		String codiDomini = sourceEntity.getDomain() == null ? null : sourceEntity.getDomain().getName();
		targetVO.setLists(findLlistaCompactaLlistesByNomLlistaCorreuAndCodiDomini(nomLlista, codiDomini));
		
		targetVO.setExternalList(findLlistaCompactaExternsByNomLlistaCorreuAndCodiDomini(nomLlista, codiDomini));

		Set<String> explodedUsers = new HashSet<String>();
		findUserMembers (sourceEntity, targetVO, explodedUsers); 
		findGroupMembers (sourceEntity, targetVO, explodedUsers); 
		findRoleMembers (sourceEntity, targetVO, explodedUsers); 

		targetVO.setExplodedUsersList(flatten (explodedUsers));
		
		Collection col_llistesPertany = sourceEntity.getMailListPertain();
		String llistesPertany = ""; //$NON-NLS-1$
		if (col_llistesPertany != null) for (Iterator it = col_llistesPertany.iterator(); it.hasNext(); ) {
            EmailListContainerEntity rel = (EmailListContainerEntity) it.next();
            EmailListEntity pertanyA = rel.getContains();
            String codiDominiCurrent = pertanyA.getDomain() == null ? null : pertanyA.getDomain().getName();
            llistesPertany += pertanyA.getName() + "@" + codiDominiCurrent + ", ";
        }
		if (llistesPertany == "") {//Llevem coma final //$NON-NLS-1$
			targetVO.setListsBelong(llistesPertany);
		} else {
			targetVO.setListsBelong(llistesPertany.substring(0, llistesPertany.length() - 2));
		}
		targetVO.setAttributes(new HashMap<String, Object>());
		Map<String, Object> attributes = targetVO.getAttributes();
		for (MailListAttributeEntity att : sourceEntity.getAttributes()) {
			if (att.getMetadata().getMultiValued() != null && att.getMetadata().getMultiValued().booleanValue())
			{
				LinkedList<Object> r = (LinkedList<Object>) attributes.get(att.getMetadata().getName());
				if (r == null)
				{
					r = new LinkedList<Object>();
					attributes.put(att.getMetadata().getName(), r);
				}
				r.add(att.getObjectValue());
			}
			else
			{
				attributes.put(att.getMetadata().getName(),att.getObjectValue());
			}
		}


	}

	private void findRoleMembers(EmailListEntity sourceEntity, MailList targetVO, Set<String> explodedUsers) {
		LinkedList<String> roles = new LinkedList<String>();
		for (MailListRoleMemberEntity rm : sourceEntity.getRoles()) {
            MailListRoleMember r = getMailListRoleMemberEntityDao().toMailListRoleMember(rm);
            String c = r.getRoleName() + "@" + r.getDispatcherName();
            if (r.getScope() != null && r.getScope().trim().length() > 0) c = c + "/" + r.getScope();
            roles.add(c);
            Collection<RoleGrant> grants;
            try {
                grants = getApplicationService().findEffectiveRoleGrantsByRoleId(rm.getRole().getId());
            } catch (InternalErrorException e) {
                throw new RuntimeException(e);
            }
            for (RoleGrant grant : grants) {
                if (grant.getUser() != null) {
                    if (r.getScope() == null || r.getScope().trim().length() == 0 || r.getScope().equals(grant.getDomainValue())) {
                        UserEntity ue = getUserEntityDao().findByUserName(grant.getUser());
                        if (ue != null && "S".equals(ue.getActive())) {
                            explodedUsers.add(grant.getUser());
                        }
                    }
                }
            }
        }
		targetVO.setRoleMembers(flatten (roles));
		
	}

	private void findGroupMembers(EmailListEntity sourceEntity, MailList targetVO, Set<String> explodedUsers) {
		LinkedList<String> groups = new LinkedList<String>();
		for (MailListGroupMemberEntity ue : sourceEntity.getGroups()) {
            String c = ue.getGroup().getName();
            groups.add(c);
            for (UserEntity user : ue.getGroup().getPrimaryGroupUsers()) {
                if ("S".equals(user.getActive())) explodedUsers.add(user.getUserName());
            }
            for (UserGroupEntity userGroup : ue.getGroup().getSecondaryGroupUsers()) {
                UserEntity user = userGroup.getUser();
                if ("S".equals(user.getActive())) explodedUsers.add(user.getUserName());
            }
        }
		targetVO.setGroupMembers(flatten (groups));
		
		
	}

	private void findUserMembers(EmailListEntity sourceEntity, MailList targetVO, Set<String> explodedUsers) {
		LinkedList<String> users = new LinkedList<String>();
		for (UserEmailEntity ue : sourceEntity.getUserMailLists()) {
            users.add(ue.getUser().getUserName());
            if ("S".equals(ue.getUser().getActive()))
                explodedUsers.add(ue.getUser().getUserName());
        }
		targetVO.setUsersList(flatten(users));
	}

	private String flatten(Collection<String> users) {
		if (users == null || users.isEmpty())
			return "";
		else
		{
			StringBuffer sb = new StringBuffer();
			for (String s: users)
			{
				if (sb.length() > 0)
					sb.append (", ");
				sb.append (s);
			}
			return sb.toString();
		}
	}

	/**
	 * @see es.caib.seycon.ng.model.LlistaCorreuEntityDao#toLlistaCorreu(es.caib.seycon.ng.model.LlistaCorreuEntity)
	 */
	public com.soffid.iam.api.MailList toMailList(final com.soffid.iam.model.EmailListEntity entity) {
		MailList llistaCorreu = super.toMailList(entity);
		return llistaCorreu;
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private com.soffid.iam.model.EmailListEntity loadLlistaCorreuEntityFromLlistaCorreu(com.soffid.iam.api.MailList llistaCorreu) {
		com.soffid.iam.model.EmailListEntity llistaCorreuEntity = null;
		if (llistaCorreu.getId() != null) {
			llistaCorreuEntity = load(llistaCorreu.getId());
		}
		if (llistaCorreuEntity == null) {
			llistaCorreuEntity = newEmailListEntity();
		}
		return llistaCorreuEntity;
	}

	/**
	 * @see es.caib.seycon.ng.model.LlistaCorreuEntityDao#llistaCorreuToEntity(es.caib.seycon.ng.comu.LlistaCorreu)
	 */
	public com.soffid.iam.model.EmailListEntity mailListToEntity(com.soffid.iam.api.MailList llistaCorreu) {
		com.soffid.iam.model.EmailListEntity entity = this.loadLlistaCorreuEntityFromLlistaCorreu(llistaCorreu);
		this.mailListToEntity(llistaCorreu, entity, true);
		return entity;
	}

	private void llistaCorreuToEntityCustom(com.soffid.iam.api.MailList sourceVO, com.soffid.iam.model.EmailListEntity targetEntity) {
		String codiDomini = sourceVO.getDomainCode();
		if (codiDomini != null && codiDomini.trim().compareTo("") != 0) { //$NON-NLS-1$
			EmailDomainEntity dominiCorreu = getEmailDomainEntityDao().findByCode(codiDomini);
			if (dominiCorreu != null) {
				if (sourceVO.getDomainCode() != null && (targetEntity.getDomain() == null || sourceVO.getDomainCode().compareTo(targetEntity.getDomain().getName()) != 0) && dominiCorreu.getObsolete() != null && dominiCorreu.getObsolete().compareTo("S") == 0) { //$NON-NLS-1$
					throw new SeyconException(String.format(Messages.getString("EmailListEntityDaoImpl.obsoleteError"), sourceVO.getDomainCode()));
				}else{
					targetEntity.setDomain(dominiCorreu);
				}
			} else {
				throw new SeyconException(String.format(Messages.getString("EmailListEntityDaoImpl.unknownError"), codiDomini)); //$NON-NLS-1$
			}
		} else {
			targetEntity.setDomain(null);
		}
		
		// correus externs
		/*
		 * String llistaExterns = sourceVO.getLlistaExterns(); Collection
		 * externs = targetEntity.getExterns(); if (externs != null) { Iterator
		 * iterator = externs.iterator(); while (iterator.hasNext()) {
		 * CorreuExternEntity correuExtern = (CorreuExternEntity) iterator
		 * .next(); getCorreuExternEntityDao().remove(correuExtern); } } if
		 * (llistaExterns != null && llistaExterns.trim().compareTo("") != 0) {
		 * String[] arrayExterns = llistaExterns.split(","); for (int i = 0; i <
		 * arrayExterns.length; i++) { String correuExtern = arrayExterns[i];
		 * correuExtern = correuExtern.trim(); CorreuExtern correuExternVO = new
		 * CorreuExtern(); correuExternVO.setAdreca(correuExtern);
		 * correuExternVO.setLlistaCorreuNom(sourceVO.getNom());
		 * correuExternVO.setCodiDomini(sourceVO.getCodiDomini());
		 * CorreuExternEntity correuExternEntity = getCorreuExternEntityDao()
		 * .correuExternToEntity(correuExternVO);
		 * getCorreuExternEntityDao().create(correuExternEntity); } }
		 */

		// llistes de correu
		/*
		 * String llistaCorreus = sourceVO.getLlistaLlistes(); Collection
		 * llistesDeCorreus = targetEntity .getRelacioLlistaCorreuFromConte();
		 * if (llistesDeCorreus != null) { Iterator iterator =
		 * llistesDeCorreus.iterator(); while (iterator.hasNext()) {
		 * RelacioLlistaCorreuEntity realacioLlistaCorreu =
		 * (RelacioLlistaCorreuEntity) iterator .next();
		 * this.getRelacioLlistaCorreuEntityDao().remove( realacioLlistaCorreu); } }
		 * if (llistaCorreus != null && llistaCorreus.trim().compareTo("") != 0) {
		 * String[] arrayLlistesCorreu = llistaCorreus.split(","); for (int i =
		 * 0; i < arrayLlistesCorreu.length; i++) { String llistaCorreu =
		 * arrayLlistesCorreu[i]; llistaCorreu = llistaCorreu.trim(); String[]
		 * nomDomini = llistaCorreu.split("@"); String nomLlista = nomDomini[0];
		 * String codiDominiLlista = null; if (nomDomini.length > 1) {
		 * codiDominiLlista = nomDomini[1]; } RelacioLlistaCorreu llistaCorreuVO =
		 * new RelacioLlistaCorreu();
		 * llistaCorreuVO.setNomLlistaCorreuPertany(nomLlista);
		 * llistaCorreuVO.setNomLlistaCorreuConte(sourceVO.getNom());
		 * llistaCorreuVO.setCodiDominiCorreuPertany(codiDominiLlista);
		 * llistaCorreuVO.setCodiDominiCorreuConte(sourceVO .getCodiDomini());
		 * RelacioLlistaCorreuEntity relacioLlistaCorreuEntity =
		 * getRelacioLlistaCorreuEntityDao()
		 * .relacioLlistaCorreuToEntity(llistaCorreuVO);
		 * getRelacioLlistaCorreuEntityDao().create( relacioLlistaCorreuEntity); } }
		 */

		// llistes d'usuaris
		/*
		 * String llistaUsuaris = sourceVO.getLlistaUsuaris(); Collection
		 * llistesDUsuaris = targetEntity.getLlistaDeCorreuUsuari(); if
		 * (llistesDUsuaris != null) { Iterator iterator =
		 * llistesDUsuaris.iterator(); while (iterator.hasNext()) {
		 * LlistaCorreuUsuariEntity llistaCorreuUsuariEntity =
		 * (LlistaCorreuUsuariEntity) iterator .next();
		 * getLlistaCorreuUsuariEntityDao().remove( llistaCorreuUsuariEntity); } }
		 * if (llistaUsuaris != null && llistaUsuaris.trim().compareTo("") != 0) {
		 * String[] arrayLlistesCorreu = llistaUsuaris.split(","); for (int i =
		 * 0; i < arrayLlistesCorreu.length; i++) { String codiUsuari =
		 * arrayLlistesCorreu[i].trim(); LlistaCorreuUsuari llistaCorreuUsuariVO =
		 * new LlistaCorreuUsuari();
		 * llistaCorreuUsuariVO.setCodiDomini(sourceVO.getCodiDomini());
		 * llistaCorreuUsuariVO.setNomLlistaCorreu(sourceVO.getNom());
		 * llistaCorreuUsuariVO.setCodiUsuari(codiUsuari);
		 * LlistaCorreuUsuariEntity llistaCorreuUsuariEntity =
		 * getLlistaCorreuUsuariEntityDao()
		 * .llistaCorreuUsuariToEntity(llistaCorreuUsuariVO);
		 * getLlistaCorreuUsuariEntityDao().create(llistaCorreuUsuariEntity); } }
		 */

	}

	/**
	 * @see es.caib.seycon.ng.model.LlistaCorreuEntityDao#llistaCorreuToEntity(es.caib.seycon.ng.comu.LlistaCorreu,
	 *      es.caib.seycon.ng.model.LlistaCorreuEntity)
	 */
	public void mailListToEntity(com.soffid.iam.api.MailList sourceVO, com.soffid.iam.model.EmailListEntity targetEntity, boolean copyIfNull) {
		// @todo verify behavior of llistaCorreuToEntity
		super.mailListToEntity(sourceVO, targetEntity, copyIfNull);
		llistaCorreuToEntityCustom(sourceVO, targetEntity);
	}

	public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof EmailListEntity) {
                EmailListEntity entity = (EmailListEntity) obj;
                this.create(entity);
            }
        }
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof EmailListEntity) {
                EmailListEntity entity = (EmailListEntity) obj;
                this.update(entity);
            }
        }
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof EmailListEntity) {
                EmailListEntity entity = (EmailListEntity) obj;
                this.remove(entity);
            }
        }
	}


	@Override
    protected void handleGenerateUpdateTasks(EmailListEntity entity) throws Exception {
        TaskEntity tasque = getTaskEntityDao().newTaskEntity();
        tasque.setDate(new Timestamp(System.currentTimeMillis()));
        tasque.setTransaction(TaskHandler.UPDATE_LIST_ALIAS);
        tasque.setAlias(entity.getName());
        if (entity.getDomain() != null)
            tasque.setMailDomain(entity.getDomain().getName());
        getTaskEntityDao().create(tasque);
	}

}
