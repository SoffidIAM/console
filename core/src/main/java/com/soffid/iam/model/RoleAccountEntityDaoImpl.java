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

import com.soffid.iam.api.ApplicationAdministration;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.ContainerRole;
import com.soffid.iam.api.DomainValue;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.Task;
import com.soffid.iam.model.DomainValueEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.InformationSystemEntity;
import com.soffid.iam.model.NoticeEntity;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.RoleDependencyEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.service.SoffidEventListener;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.utils.DateUtils;
import com.soffid.iam.utils.ExceptionTranslator;
import com.soffid.iam.utils.MailUtils;
import com.soffid.iam.utils.Security;
import com.soffid.iam.utils.TipusContenidorRol;

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @see es.caib.seycon.ng.model.RolAccountEntity
 */
public class RoleAccountEntityDaoImpl 
	extends com.soffid.iam.model.RoleAccountEntityDaoBase
	implements ApplicationContextAware
{
	Log log = LogFactory.getLog(getClass());
	
	private ApplicationContext applicationContext;

	private void auditarRolAccount(String accio, RoleAccountEntity grant) {
		String codiUsuari = Security.getCurrentAccount();
		if (grant.getAccount().getType().equals(AccountType.USER)) {
			for (com.soffid.iam.model.UserAccountEntity ua : grant.getAccount()
					.getUsers()) {
				Audit auditoria = new Audit();
				auditoria.setAction(accio);
				auditoria.setRole(grant.getRole().getName());
				auditoria.setUser(ua.getUser().getUserName());
				auditoria.setAccount(grant.getAccount().getName());
				auditoria.setDatabase(grant.getRole().getSystem().getName());
				auditoria.setApplication(grant.getRole().getInformationSystem()
						.getName());
				auditoria.setAuthor(codiUsuari);
				if (grant.getRule() != null) {
					auditoria.setRule(grant.getRule().getDescription());
					auditoria.setAction(accio.toLowerCase());
				}
				if (grant.getDomainValue() != null) {
					auditoria.setDomainValue(grant.getDomainValue().getValue());
					auditoria
							.setDomain(grant.getDomainValue().getDescription());
				} else if (grant.getInformationSystem() != null) {
					auditoria.setDomainValue(grant.getInformationSystem()
							.getName());
					auditoria.setDomain(grant.getInformationSystem()
							.getDescription());
				} else if (grant.getGroup() != null) {
					auditoria.setDomainValue(grant.getGroup().getName());
					auditoria.setDomain(grant.getGroup().getDescription());
				} else {
					auditoria.setDomainValue(null);
					auditoria.setDomain(null);
				}
				auditoria.setObject("SC_ROLUSU");
				getAuditEntityDao().create(
						getAuditEntityDao().auditToEntity(auditoria));
			}
		} else {
			Audit auditoria = new Audit();
			auditoria.setAction(accio);
			auditoria.setRole(grant.getRole().getName());
			auditoria.setDatabase(grant.getRole().getSystem().getName());
			auditoria.setAccount(grant.getAccount().getName());
			auditoria.setApplication(grant.getRole().getInformationSystem()
					.getName());
			auditoria.setAuthor(codiUsuari);
			if (grant.getRule() != null) {
				auditoria.setRule(grant.getRule().getDescription());
				auditoria.setAction(accio.toLowerCase());
			}
			if (grant.getDomainValue() != null) {
				auditoria.setDomainValue(grant.getDomainValue().getValue());
				auditoria.setDomain(grant.getDomainValue().getDescription());
			} else if (grant.getInformationSystem() != null) {
				auditoria
						.setDomainValue(grant.getInformationSystem().getName());
				auditoria.setDomain(grant.getInformationSystem()
						.getDescription());
			} else if (grant.getGroup() != null) {
				auditoria.setDomainValue(grant.getGroup().getName());
				auditoria.setDomain(grant.getGroup().getDescription());
			} else {
				auditoria.setDomainValue(null);
				auditoria.setDomain(null);
			}
			auditoria.setObject("SC_ROLUSU"); //$NON-NLS-1$
			getAuditEntityDao().create(
					getAuditEntityDao().auditToEntity(auditoria));
		}
	}

	public void update(RoleAccountEntity rolsUsuaris) {
    	handleUpdate (rolsUsuaris, "U");
    }
    
    public void handleUpdate(RoleAccountEntity rolsUsuaris, String auditType) {
		// Aquest mètode s'empra només en SC_RESPONSABLE de les aplicacions
		// Només es pot tindre 1 responsable, i s'actualitza l'existent (si
		// existeix)
		// sino es crea un de nou
		try {
			// IMPORTANT: COM AQUI NO ES POT CANVIAR EL ROL, NO COMPROVEM
			// L'HERENCIA
			// DEL ROL ANTERIOR I EL ROL DESPRÉS DEL UPDATE (!!)
			// PERQUE SERÀ EL MATEIX

			RoleAccountEntity old = load(rolsUsuaris.getId());

			super.update(rolsUsuaris);
			getSession(false).flush();

			RoleAccount rolsUsuarisVO = toRoleAccount(rolsUsuaris);

			String codiAplicacio = null;
			codiAplicacio = rolsUsuarisVO.getInformationSystemName();

			DomainValue valorDomini = rolsUsuarisVO.getDomainValue();
			String nomDomini = null;
			String valorDominiString = null;
			if (valorDomini != null) {
				nomDomini = valorDomini.getDomainName();
				if (valorDomini.getValue() != null) {
					valorDominiString = valorDomini.getValue();
				}
			}

			// HERÈNCIA DE ROLS
			// Cerquem els rols que contenen (tenen atorgat) aquest rol per
			// propagar-los
			Collection rolsPropagar = getRolsContingutsPerPropagar(rolsUsuaris
					.getRole());
			// I si tenim cap... els propaguem
			if (rolsPropagar != null)
				propagarRols(rolsPropagar);

			// Enviem les notificacions de l'aplicació (si estan activades)
			if (rolsUsuaris.getRole().getInformationSystem() != null) {
				InformationSystemEntity aplic = rolsUsuaris.getRole()
						.getInformationSystem();
				String correusNotificacio = aplic.getNotificationMail();
				if (correusNotificacio != null) {
					String correus[] = correusNotificacio.split(","); //$NON-NLS-1$
					if (correus.length > 0) { // almeny existisca 1
						// Hay que notificar: Creamos la notificación
						for (com.soffid.iam.model.UserAccountEntity usu : rolsUsuaris
								.getAccount().getUsers()) {
							String informacio = Messages
									.getString("RolsUsuarisEntityDaoImpl.0");
							NoticeEntity notif = getNoticeEntityDao()
									.newNoticeEntity();
							notif.setModificationDate(new Date());
							notif.setInformation(informacio);
							notif.setRole(rolsUsuaris.getRole());
							notif.setApplication(aplic);
							notif.setUser(usu.getUser());
							getNoticeEntityDao().create(notif);
						}
					}
				}
			}

			generateTasks(rolsUsuaris);
			generateTasks(old);

			auditarRolAccount(auditType, rolsUsuaris); //$NON-NLS-1$
			
			for (String name : applicationContext.getBeanNamesForType(SoffidEventListener.class))
			{
				SoffidEventListener bean = (SoffidEventListener) applicationContext.getBean(name);
				if (bean != null)
				{
					if ( rolsUsuaris.isEnabled())
						bean.onGrant(rolsUsuaris);
					else
						bean.onRevoke(rolsUsuaris);
				}
			}

		} catch (Throwable e) {
			log.warn("Error updating role", e);
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages
					.getString("RolsUsuarisEntityDaoImpl.1"), rolsUsuaris
					.getRole().getName(), rolsUsuaris.getAccount().getName(),
					message), e);
		}
	}

	private void generateTasks(RoleAccountEntity grant)
			throws InternalErrorException {
		if (! grant.getAccount().getType().equals(AccountType.IGNORED))
	   	{
			TaskEntity tasque = getTaskEntityDao().newTaskEntity();
			tasque.setDate(new Timestamp(System.currentTimeMillis()));
			tasque.setTransaction(TaskHandler.UPDATE_ROLE);
			tasque.setRole(grant.getRole().getName());
			tasque.setDb(grant.getRole().getSystem().getName());
			getTaskEntityDao().create(tasque);
	
			if (grant.getAccount().getType().equals(AccountType.USER)) {
				for (com.soffid.iam.model.UserAccountEntity ua : grant.getAccount()
						.getUsers()) {
					tasque = getTaskEntityDao().newTaskEntity();
					tasque.setDate(new Timestamp(System.currentTimeMillis()));
					tasque.setTransaction(TaskHandler.UPDATE_USER);
					tasque.setUser(ua.getUser().getUserName());
					getTaskEntityDao().create(tasque);
				}
			} else {
				tasque = getTaskEntityDao().newTaskEntity();
				tasque.setDate(new Timestamp(System.currentTimeMillis()));
				tasque.setTransaction(TaskHandler.UPDATE_ACCOUNT);
				tasque.setSystemName(grant.getAccount().getSystem().getName());
				tasque.setDb(grant.getAccount().getSystem().getName());
				tasque.setUser(grant.getAccount().getName());
				getTaskEntityDao().create(tasque);
			}

			getRoleEntityDao().updateMailLists(grant.getRole());
	   	}

	}

	public void create(com.soffid.iam.model.RoleAccountEntity rolsUsuaris)
			throws RuntimeException {
		try {
			// Verificamos que no existe antes de crearlo:
			// - Si existe, no se crea, pero no se da ERROR (carga masiva)
			RoleAccountEntity rolUsuariExistent = findExisteixRolUsuari(rolsUsuaris);
			if (rolUsuariExistent == null) { // Lo creamos si no existe

				rolsUsuaris.setCertificationDate(new Date());

				super.create(rolsUsuaris);
				getSession(false).flush();
				RoleAccount rolsUsuarisVO = toRoleAccount(rolsUsuaris);

				String codiAplicacio = null;
				codiAplicacio = rolsUsuarisVO.getInformationSystemName();

				DomainValue valorDomini = rolsUsuarisVO.getDomainValue();
				String nomDomini = null;
				String valorDominiString = null;
				if (valorDomini != null) {
					nomDomini = valorDomini.getDomainName();
					if (valorDomini.getValue() != null) {
						valorDominiString = valorDomini.getValue();
					}
				}

				// HERÈNCIA DE ROLS
				// Cerquem els rols que contenen (tenen atorgat) aquest rol per
				// propagar-los
				Collection rolsPropagar = getRolsContingutsPerPropagar(rolsUsuaris
						.getRole());
				// I si tenim cap... els propaguem
				if (rolsPropagar != null)
					propagarRols(rolsPropagar);

				// Enviem les notificacions de l'aplicació (si estan activades)
				if (rolsUsuaris.getRole().getInformationSystem() != null) {
					InformationSystemEntity aplic = rolsUsuaris.getRole()
							.getInformationSystem();
					String correusNotificacio = aplic.getNotificationMail();
					if (correusNotificacio != null) {
						String correus[] = correusNotificacio.split(","); //$NON-NLS-1$
						if (correus.length > 0) {
							for (com.soffid.iam.model.UserAccountEntity usu : rolsUsuaris
									.getAccount().getUsers()) {
								String informacio = Messages
										.getString("RolsUsuarisEntityDaoImpl.2");
								NoticeEntity notif = getNoticeEntityDao()
										.newNoticeEntity();
								notif.setApplication(aplic);
								notif.setModificationDate(new Date());
								notif.setInformation(informacio);
								notif.setRole(rolsUsuaris.getRole());
								notif.setUser(usu.getUser());
								getNoticeEntityDao().create(notif);
							}
						}

					}
				}

				generateTasks(rolsUsuaris);
				auditarRolAccount("C", rolsUsuaris); //$NON-NLS-1$

				if ( rolsUsuaris.isEnabled())
				{
					for (String name : applicationContext.getBeanNamesForType(SoffidEventListener.class))
					{
						SoffidEventListener bean = (SoffidEventListener) applicationContext.getBean(name);
						if (bean != null)
							bean.onGrant(rolsUsuaris);
					}

				}
			} else {
				throw new SeyconException(
						Messages.getString("RolsUsuarisEntityDaoImpl.3")); //$NON-NLS-1$
			}
		} catch (Throwable e) {
			log.warn("Error updating role", e);
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages
					.getString("RolsUsuarisEntityDaoImpl.4"), rolsUsuaris
					.getRole().getName(), rolsUsuaris.getAccount().getName(),
					message), e);
		}
	}

	public void remove(com.soffid.iam.model.RoleAccountEntity rolsUsuaris)
			throws RuntimeException {
		try {
			RoleAccount rolsUsuarisVO = toRoleAccount(rolsUsuaris);
			DomainValue valorDomini = rolsUsuarisVO.getDomainValue();

			String codiAplicacio = null;
			codiAplicacio = rolsUsuarisVO.getInformationSystemName();

			String nomDomini = null;
			String valorDominiString = null;
			if (valorDomini != null) {
				nomDomini = valorDomini.getDomainName();
				if (valorDomini.getValue() != null) {
					valorDominiString = valorDomini.getValue();
				}
			}

			// HERÈNCIA DE ROLS
			// Cerquem els rols que contenen (tenen atorgat) aquest rol per
			// propagar-los
			Collection rolsPropagar = getRolsContingutsPerPropagar(rolsUsuaris
					.getRole());
			// I si tenim cap... els propaguem
			if (rolsPropagar != null)
				propagarRols(rolsPropagar);

			// Enviem les notificacions de l'aplicació (si estan activades)
			if (rolsUsuaris.getRole().getInformationSystem() != null) {
				InformationSystemEntity aplic = rolsUsuaris.getRole()
						.getInformationSystem();
				String correusNotificacio = aplic.getNotificationMail();
				if (correusNotificacio != null) {
					String correus[] = correusNotificacio.split(","); //$NON-NLS-1$
					if (correus.length > 0) {
						for (com.soffid.iam.model.UserAccountEntity usu : rolsUsuaris
								.getAccount().getUsers()) {
							String informacio = Messages
									.getString("RolsUsuarisEntityDaoImpl.5");
							NoticeEntity notif = getNoticeEntityDao()
									.newNoticeEntity();
							notif.setApplication(aplic);
							notif.setModificationDate(new Date());
							notif.setInformation(informacio);
							notif.setRole(rolsUsuaris.getRole());
							notif.setUser(usu.getUser());
							getNoticeEntityDao().create(notif);
						}
					}
				}
			}

			if (Hibernate.isInitialized(rolsUsuaris.getAccount())
					&& Hibernate.isInitialized(rolsUsuaris.getAccount()
							.getRoles())) {
				rolsUsuaris.getAccount().getRoles().remove(rolsUsuaris);
			}

			if ( rolsUsuaris.isEnabled())
			{
				generateTasks(rolsUsuaris);
				
				super.remove(rolsUsuaris);
				
				auditarRolAccount("D", rolsUsuaris); //$NON-NLS-1$
				
				for (String name : applicationContext.getBeanNamesForType(SoffidEventListener.class))
				{
					SoffidEventListener bean = (SoffidEventListener) applicationContext.getBean(name);
					if (bean != null)
						bean.onRevoke(rolsUsuaris);
				}

			}
			else
			{
				super.remove(rolsUsuaris);
			}
		} catch (Throwable e) {
			log.warn("Error updating role", e);
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages
					.getString("RolsUsuarisEntityDaoImpl.6"), rolsUsuaris
					.getRole().getName(), rolsUsuaris.getAccount().getName(),
					message), e);
		}
	}

	public void toRoleAccount(
			com.soffid.iam.model.RoleAccountEntity sourceEntity,
			com.soffid.iam.api.RoleAccount targetVO) {
		super.toRoleAccount(sourceEntity, targetVO);
		toRolAccountCustom(sourceEntity, targetVO);
	}

	private void toRolAccountCustom(
			com.soffid.iam.model.RoleAccountEntity sourceEntity,
			com.soffid.iam.api.RoleAccount targetVO) {
		UserEntity usuariEntity = null;
		if (sourceEntity.getAccount().getType().equals(AccountType.USER)) {
			for (com.soffid.iam.model.UserAccountEntity usu : sourceEntity
					.getAccount().getUsers()) {
				usuariEntity = usu.getUser();
			}
		}

		String tipusDomini = sourceEntity.getDomainType();
		if (tipusDomini == null || tipusDomini.trim().compareTo("") == 0) { //$NON-NLS-1$
			tipusDomini = TipusDomini.SENSE_DOMINI;
		}
		if (tipusDomini.compareTo(TipusDomini.DOMINI_APLICACIO) == 0  ||
				TipusDomini.CUSTOM.equals(tipusDomini)) {
			DomainValueEntity valorDominiEntity = sourceEntity.getDomainValue();
			if (valorDominiEntity != null) {
				DomainValue valorDomini = getDomainValueEntityDao().toDomainValue(
						valorDominiEntity);
				targetVO.setDomainValue(valorDomini);
			}
		} else if (tipusDomini.compareTo(TipusDomini.GRUPS) == 0
				|| tipusDomini.compareTo(TipusDomini.GRUPS_USUARI) == 0  ||
						TipusDomini.GROUPS.equals(tipusDomini)  ||
						TipusDomini.MEMBERSHIPS.equals(tipusDomini)) {
			if (sourceEntity.getGroup() != null) {
				DomainValue valorDomini = new DomainValue();
				valorDomini.setDescription(sourceEntity.getGroup().getDescription());
				if (tipusDomini.compareTo(TipusDomini.GRUPS_USUARI) == 0 ||
						TipusDomini.MEMBERSHIPS.equals(tipusDomini) ) {
					valorDomini.setExternalCodeDomain(usuariEntity == null ? null
							: usuariEntity.getUserName());
					valorDomini.setDomainName(TipusDomini.MEMBERSHIPS);
				} else {
					valorDomini.setExternalCodeDomain(null);
					valorDomini.setDomainName(TipusDomini.GROUPS);
				}
				valorDomini.setValue(sourceEntity.getGroup().getName());
				targetVO.setDomainValue(valorDomini);
			}
		} else if (tipusDomini.compareTo(TipusDomini.APLICACIONS) == 0 ||
				TipusDomini.APPLICATIONS.equals(tipusDomini) ) {
			if (sourceEntity.getInformationSystem() != null) {
				DomainValue valorDomini = new DomainValue();
				valorDomini.setExternalCodeDomain(null);
				valorDomini.setDescription(sourceEntity.getInformationSystem()
						.getDescription());
				valorDomini.setDomainName(TipusDomini.APPLICATIONS);
				valorDomini.setValue(sourceEntity.getInformationSystem().getName());
				targetVO.setDomainValue(valorDomini);
			}
		} else if (tipusDomini.compareTo(TipusDomini.SENSE_DOMINI) == 0) {
			DomainValue valorDomini = new DomainValue();
			valorDomini.setExternalCodeDomain(null);
			valorDomini.setDescription(null);
			valorDomini.setDomainName(null);
			valorDomini.setValue(null); //$NON-NLS-1$
			// targetVO.setValorDomini(valorDomini); // No se muestra
		}

		// Assign holder gorup
		if (sourceEntity.getHolderGroup() == null)
			targetVO.setHolderGroup(null);
		else
			targetVO.setHolderGroup(sourceEntity.getHolderGroup().getName());

		targetVO.setRoleName(sourceEntity.getRole().getName());
		targetVO.setRoleCategory(sourceEntity.getRole().getCategory());
		targetVO.setRoleId(sourceEntity.getRole().getId());
		String nom;
		if (usuariEntity != null) {
			nom = usuariEntity.getFirstName();
			nom = nom != null ? nom : ""; //$NON-NLS-1$
			String primerCognom = usuariEntity.getLastName();
			primerCognom = primerCognom != null ? primerCognom : ""; //$NON-NLS-1$
			String segonCognom = usuariEntity.getMiddleName();
			segonCognom = segonCognom != null ? segonCognom : ""; //$NON-NLS-1$
			targetVO.setUserFullName(usuariEntity.getFullName()); //$NON-NLS-1$ //$NON-NLS-2$
			targetVO.setUserCode(usuariEntity == null ? null : usuariEntity
					.getUserName());
		} else {
			targetVO.setUserFullName(sourceEntity.getAccount().getDescription());
		}

		targetVO.setAccountName(sourceEntity.getAccount().getName());
		targetVO.setAccountSystem(sourceEntity.getAccount().getSystem()
				.getName());
		targetVO.setRoleDescription(sourceEntity.getRole().getDescription());
		targetVO.setAccountId(sourceEntity.getAccount().getId());
		SystemEntity dispatcher = sourceEntity.getRole().getSystem();
		targetVO.setSystem(dispatcher == null ? null : dispatcher.getName());

		InformationSystemEntity aplicacio = sourceEntity.getRole()
				.getInformationSystem();
		if (aplicacio != null) {
			targetVO.setInformationSystemName(aplicacio.getName());
		}

		if (usuariEntity != null && usuariEntity.getPrimaryGroup() != null) {
			targetVO.setUserGroupCode(usuariEntity.getPrimaryGroup().getName());
		}

		targetVO.setBpmEnforced(sourceEntity.getRole().getManageableWF());

		if (sourceEntity.getRule() == null) {
			targetVO.setRuleId(null);
			targetVO.setRuleDescription(null);
		} else {
			targetVO.setRuleId(sourceEntity.getRule().getId());
			targetVO.setRuleDescription(sourceEntity.getRule().getDescription());
		}

        targetVO.setParentGrant( sourceEntity.getParent() == null ? null : sourceEntity.getParent().getId());
        if (sourceEntity.getParent() != null)
        {
        	targetVO.setRuleDescription(String.format("Granted to role %s @ %s", 
        			sourceEntity.getParent().getRole().getName(),
        			sourceEntity.getParent().getRole().getSystem().getName()));
        }

        if (sourceEntity.getOwnerAccount() != null)
        	targetVO.setOwnerAccount(sourceEntity.getOwnerAccount().getName());

        if (sourceEntity.getDelegateAccount() != null)
        	targetVO.setDelegateAccount(sourceEntity.getDelegateAccount().getName());
	}

	/**
	 * @see es.caib.seycon.ng.model.RolAccountEntityDao#toRolAccount(es.caib.seycon.ng.model.RolAccountEntity)
	 */
	public com.soffid.iam.api.RoleAccount toRoleAccount(
			final com.soffid.iam.model.RoleAccountEntity entity) {
		RoleAccount rolUsuari = super.toRoleAccount(entity);
		toRolAccountCustom(entity, rolUsuari);
		return rolUsuari;
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private com.soffid.iam.model.RoleAccountEntity loadRolAccountEntityFromRolAccount(
			com.soffid.iam.api.RoleAccount rolsUsuaris) {
		RoleAccountEntity rolsUsuarisEntity = null;
		if (rolsUsuaris.getId() != null) {
			rolsUsuarisEntity = load(rolsUsuaris.getId());
		}
		if (rolsUsuarisEntity == null) {
			rolsUsuarisEntity = newRoleAccountEntity();
		}
		return rolsUsuarisEntity;
	}

	/**
	 * @see es.caib.seycon.ng.model.RolAccountEntityDao#rolsUsuarisToEntity(es.caib.seycon.ng.comu.RolAccount)
	 */
	public com.soffid.iam.model.RoleAccountEntity roleAccountToEntity(
			com.soffid.iam.api.RoleAccount rolsUsuaris) {
		com.soffid.iam.model.RoleAccountEntity entity = this
				.loadRolAccountEntityFromRolAccount(rolsUsuaris);
		this.roleAccountToEntity(rolsUsuaris, entity, true);
		return entity;
	}

	private RoleEntity findRolByNomAndCodiApliacio(String nom,
			String codiAplicacio, String bbdd) {
		return getRoleEntityDao().findByNameAndSystem(nom, bbdd);
	}

	private void rolsUsuarisToEntityCustom(
			com.soffid.iam.api.RoleAccount sourceVO,
			com.soffid.iam.model.RoleAccountEntity targetEntity) {
		com.soffid.iam.model.AccountEntity account = null;
		com.soffid.iam.model.AccountEntityDao accDao = getAccountEntityDao();

		if (targetEntity.getStartDate() != null)
			targetEntity
					.setStartDate(removeSeconds(targetEntity.getStartDate()));
		if (targetEntity.getEndDate() != null)
			targetEntity.setEndDate(removeSeconds(targetEntity.getEndDate()));

		if (sourceVO.getAccountId() != null)
			account = accDao.load(sourceVO.getAccountId().longValue());
		else if (sourceVO.getAccountName() != null
				&& sourceVO.getAccountSystem() != null) {
			account = accDao.findByNameAndSystem(sourceVO.getAccountName(),
					sourceVO.getSystem());
		} else if (sourceVO.getUserCode() != null) {
			List<com.soffid.iam.model.AccountEntity> accounts = accDao
					.findByUserAndSystem(sourceVO.getUserCode(),
							sourceVO.getSystem());
			if (accounts.size() > 1)
				throw new IllegalArgumentException(
						String.format(
								Messages.getString("RoleAccountEntityDaoImpl.MoreThanOneUserAccount"),
								sourceVO.getUserCode(), sourceVO.getSystem()));
			if (accounts.size() == 1)
				account = accounts.get(0);
		}
		if (account == null) {
			throw new SeyconException(
					String.format(
							Messages.getString("RolsUsuarisEntityDaoImpl.7"), sourceVO.getUserCode())); //$NON-NLS-1$
		}
		targetEntity.setAccount(account);

		String nomRolDomini = sourceVO.getRoleName();
		String codiAplicacioRol = sourceVO.getInformationSystemName();
		String codiBBDD = sourceVO.getSystem();
		RoleEntity rol = null;
		if (rol == null && nomRolDomini != null) {
			rol = findRolByNomAndCodiApliacio(nomRolDomini, codiAplicacioRol,
					codiBBDD);
		}
		if (rol == null && sourceVO.getRoleId() != null)
			rol = getRoleEntityDao().load(sourceVO.getRoleId());
		if (rol == null) {
			throw new SeyconException(
					String.format(
							Messages.getString("RolsUsuarisEntityDaoImpl.8"), nomRolDomini)); //$NON-NLS-1$
		}

		targetEntity.setRole(rol);

		DomainValue valorDomini = sourceVO.getDomainValue();
		String nomDomini = rol.getDomainType();
		if ( TipusDomini.GROUPS.equals(nomDomini) ||
				TipusDomini.MEMBERSHIPS.equals(nomDomini) ||
				TipusDomini.GRUPS.equals(nomDomini) ||
				TipusDomini.GRUPS_USUARI.equals(nomDomini)) {
			String codiGrup = valorDomini == null ? null: valorDomini.getValue();
			GroupEntity grup = null;
			if (codiGrup != null && ! codiGrup.trim().isEmpty()) { //$NON-NLS-1$
				grup = getGroupEntityDao().findByName(codiGrup);
				if (grup != null) {
					if (TipusDomini.GRUPS_USUARI.equals(nomDomini) ||
						TipusDomini.MEMBERSHIPS.equals(nomDomini)) {
						String codiUsuari = sourceVO.getUserCode();
						if (codiUsuari != null) {
							if (!grupPertanyAUsuari(codiGrup, codiUsuari)) {
								throw new SeyconException(
										String.format(
												Messages.getString("RolsUsuarisEntityDaoImpl.9"), codiGrup, codiUsuari)); //$NON-NLS-1$
							}
							targetEntity.setGroup(grup);
							targetEntity.setDomainValue(null);
							targetEntity.setDomainType(TipusDomini.MEMBERSHIPS);
							targetEntity.setInformationSystem(null);
						} else {
							throw new SeyconException(
									Messages.getString("RolsUsuarisEntityDaoImpl.10")); //$NON-NLS-1$
						}
					} else {
						targetEntity.setGroup(grup);
						targetEntity.setDomainValue(null);
						targetEntity.setDomainType(TipusDomini.GROUPS);
						targetEntity.setInformationSystem(null);
					}
				} else {
					throw new SeyconException(
							String.format(
									Messages.getString("RolsUsuarisEntityDaoImpl.11"), codiGrup)); //$NON-NLS-1$
				}
			} else {
				throw new SeyconException(
						Messages.getString("RolsUsuarisEntityDaoImpl.12")); //$NON-NLS-1$

			}
		} else if (TipusDomini.APPLICATIONS.equals(nomDomini) ||
				TipusDomini.APLICACIONS.equals(nomDomini)) {
			String valor = valorDomini.getValue();
			InformationSystemEntity aplicacioEntity = getInformationSystemEntityDao()
					.findByCode(valor);
			if (aplicacioEntity == null) {
				throw new SeyconException(
						String.format(
								Messages.getString("RolsUsuarisEntityDaoImpl.13"), valor)); //"Aplicació amb codi '" + valor + "' no trobada."); //$NON-NLS-1$
			}
			targetEntity.setInformationSystem(aplicacioEntity);
			targetEntity.setGroup(null);
			targetEntity.setDomainValue(null);
			targetEntity.setDomainType(TipusDomini.APPLICATIONS);
		} else if (TipusDomini.SENSE_DOMINI.equals(nomDomini) ||
				nomDomini == null || 
				rol.getApplicationDomain() == null) {
			targetEntity.setInformationSystem(null);
			targetEntity.setGroup(null);
			targetEntity.setDomainValue(null);
			targetEntity.setDomainType(null);
		} else if (rol.getApplicationDomain() != null) {
			/*
			 * Domini d'aplicació
			 */
			nomDomini = rol.getApplicationDomain().getName();
			String codiAplicacio = rol.getInformationSystem().getName();
			if (valorDomini != null) {
				String valor = valorDomini.getValue();
				DomainValueEntity valorDominiAplicacioEntity = findValorDominiByNomDominiAndCodiAplicacioDominiAndValor(
						nomDomini, codiAplicacio, valor);
				if (valorDominiAplicacioEntity != null) {
					targetEntity.setDomainValue(valorDominiAplicacioEntity);
					targetEntity.setDomainType(TipusDomini.CUSTOM);
					targetEntity.setGroup(null);
					targetEntity.setInformationSystem(null);
				} else {
					throw new SeyconException(
							String.format(
									Messages.getString("RolsUsuarisEntityDaoImpl.14"), nomDomini, codiAplicacio, valor)); //$NON-NLS-1$
				}
			} else {
				throw new SeyconException(
						String.format(
								Messages.getString("RolsUsuarisEntityDaoImpl.14"), nomDomini, codiAplicacio, valorDomini)); //$NON-NLS-1$
			}
		}

		if (sourceVO.getRuleId() == null)
			targetEntity.setRule(null);
		else
			targetEntity.setRule(getRuleEntityDao().load(sourceVO.getRuleId()));

		if (sourceVO.getHolderGroup() == null)
			targetEntity.setHolderGroup(null);
		else {
			GroupEntity grup = getGroupEntityDao().findByName(
					sourceVO.getHolderGroup());
			if (grup == null)
				throw new SeyconException(String.format("Unknown group %s",
						sourceVO.getHolderGroup()));
			targetEntity.setHolderGroup(grup);
		}
        
        if (sourceVO.getParentGrant() != null)
        {
        	targetEntity.setParent(load(sourceVO.getParentGrant()));
        }
	}

	private DomainValueEntity findValorDominiByNomDominiAndCodiAplicacioDominiAndValor(
			String nomDomini, String codiAplicacio, String valor) {

		return getDomainValueEntityDao().findByApplicationDomainValue(
				codiAplicacio, nomDomini, valor);
	}

	private RoleAccountEntity findExisteixRolUsuari(RoleAccountEntity rolUsuari) {

		List<RoleAccountEntity> rolsUsu = findMatching(rolUsuari.getAccount()
				.getId(), rolUsuari.getRole().getId(),
				rolUsuari.getDomainType(), rolUsuari.getGroup() == null ? null
						: rolUsuari.getGroup().getName(),
				rolUsuari.getInformationSystem() == null ? null : rolUsuari
						.getInformationSystem().getName(),
				rolUsuari.getDomainValue() == null ? null : rolUsuari
						.getDomainValue().getValue());

		if (rolsUsu != null && rolsUsu.size() != 0) {
			return rolsUsu.get(0);
		}

		return null;
	}

	private boolean grupPertanyAUsuari(String codiGrup, String codiUsuari) {
		Collection<GroupEntity> grups = getGroupEntityDao().findGroupsByUser(
				codiUsuari);
		Iterator<GroupEntity> iterator = grups.iterator();
		while (iterator.hasNext()) {
			GroupEntity grupEntity = (GroupEntity) iterator.next();
			if (grupEntity.getName().compareTo(codiGrup) == 0) {
				return true;
			}
		}
		UserEntity usuari = getUserEntityDao().findByUserName(codiUsuari);
		GroupEntity grupPrimariEntity = usuari.getPrimaryGroup();
		if (grupPrimariEntity != null) {
			return grupPrimariEntity.getName().compareTo(codiGrup) == 0;
		}
		return false;
	}

	private Date removeSeconds(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * @see es.caib.seycon.ng.model.RolAccountEntityDao#rolsUsuarisToEntity(es.caib.seycon.ng.comu.RolAccount,
	 *      es.caib.seycon.ng.model.RolAccountEntity)
	 */
	public void roleAccountToEntity(com.soffid.iam.api.RoleAccount sourceVO,
			com.soffid.iam.model.RoleAccountEntity targetEntity,
			boolean copyIfNull) {
		super.roleAccountToEntity(sourceVO, targetEntity, copyIfNull);

		rolsUsuarisToEntityCustom(sourceVO, targetEntity);
	}

	private com.soffid.iam.model.RoleAccountEntity loadRolAccountEntityFromAdministracioAplicacio(
			com.soffid.iam.api.ApplicationAdministration administracioAplicacio) {
		RoleAccountEntity rolsUsuarisEntity = null;
		if (administracioAplicacio.getId() != null) {
			rolsUsuarisEntity = load(administracioAplicacio.getId());
		}
		if (rolsUsuarisEntity == null) {
			rolsUsuarisEntity = newRoleAccountEntity();
		}
		return rolsUsuarisEntity;
	}

	public RoleAccountEntity applicationAdministrationToEntity(
			ApplicationAdministration administracioAplicacio) {
		com.soffid.iam.model.RoleAccountEntity entity = this
				.loadRolAccountEntityFromAdministracioAplicacio(administracioAplicacio);
		this.administracioAplicacioToEntity(administracioAplicacio, entity);
		return entity;
	}

	public void toApplicationAdministration(RoleAccountEntity source,
			ApplicationAdministration target) {
		super.toApplicationAdministration(source, target);
		target.setInformationSystemName(source.getInformationSystem().getName());
		UserEntity usuariEntity = source.getAccount().getUsers().iterator()
				.next().getUser();
		target.setUserName(usuariEntity.getUserName());
		String nom = usuariEntity.getFirstName();
		nom = nom != null ? nom : ""; //$NON-NLS-1$
		String primerCognom = usuariEntity.getLastName();
		primerCognom = primerCognom != null ? primerCognom : ""; //$NON-NLS-1$
		String segonCognom = usuariEntity.getMiddleName();
		segonCognom = segonCognom != null ? segonCognom : ""; //$NON-NLS-1$
		target.setUserFullName(nom + " " + primerCognom + " " + segonCognom); //$NON-NLS-1$ //$NON-NLS-2$
		target.setRoleName(source.getRole().getName());
		target.setRoleInformationSystem(source.getRole().getInformationSystem()
				.getName());
		target.setRoleSystemName(source.getRole().getSystem().getName());
	}

	public ApplicationAdministration toApplicationAdministration(
			final RoleAccountEntity entity) {
		return super.toApplicationAdministration(entity);
	}

	public void administracioAplicacioToEntity(
			ApplicationAdministration administracioAplicacio,
			RoleAccountEntity targetEntity) {
		String aplicacioAdministrada = administracioAplicacio
				.getInformationSystemName();
		InformationSystemEntity aplicacioEntity = getInformationSystemEntityDao()
				.findByCode(aplicacioAdministrada);
		if (aplicacioEntity == null) {
			throw new SeyconException(
					String.format(
							Messages.getString("RolsUsuarisEntityDaoImpl.15"), aplicacioAdministrada)); //$NON-NLS-1$
		}
		targetEntity.setInformationSystem(aplicacioEntity);
		targetEntity.setGroup(null);
		targetEntity.setDomainValue(null);
		targetEntity.setDomainType(TipusDomini.APLICACIONS);

		RoleEntity rolEntity = getRoleEntityDao()
				.findRoleByNameInformationSystemAndStystem(
						administracioAplicacio.getRoleName(),
						administracioAplicacio.getRoleInformationSystem(),
						administracioAplicacio.getRoleSystemName());
		if (rolEntity == null) {
			throw new SeyconException(
					String.format(
							Messages.getString("RolsUsuarisEntityDaoImpl.16"), administracioAplicacio.getRoleName())); //$NON-NLS-1$
		}
		targetEntity.setRole(rolEntity);

		List<com.soffid.iam.model.AccountEntity> accs = getAccountEntityDao()
				.findByUserAndSystem(administracioAplicacio.getUserName(),
						administracioAplicacio.getRoleSystemName());
		if (accs.size() != 1) {
			throw new SeyconException(
					String.format(
							Messages.getString("RolsUsuarisEntityDaoImpl.17"), administracioAplicacio.getUserName())); //$NON-NLS-1$
		}
		targetEntity.setAccount(accs.get(0));
	}

	public RoleAccountEntity containerRoleToEntity(ContainerRole contenidorRol) {
		// TODO Auto-generated method stub
		return null;
	}

	public ContainerRole toContainerRole(RoleAccountEntity entity) {
		ContainerRole contenidorRol = super.toContainerRole(entity); // Pasamos
																		// el id
		contenidorRol.setType(TipusContenidorRol.ROL_USUARI);
		// Información específica:
		RoleEntity rol = entity.getRole();
		DomainValue valorDomini = null;

		String tipusDomini = rol.getDomainType();
		if (tipusDomini == null || tipusDomini.trim().compareTo("") == 0) { //$NON-NLS-1$
			tipusDomini = TipusDomini.SENSE_DOMINI;
		}
		if (tipusDomini.compareTo(TipusDomini.DOMINI_APLICACIO) == 0 ||
				TipusDomini.CUSTOM.equals(tipusDomini)) {
			DomainValueEntity valorDominiEntity = entity.getDomainValue();
			valorDomini = getDomainValueEntityDao().toDomainValue(
					valorDominiEntity);
		} else if (tipusDomini.compareTo(TipusDomini.GRUPS) == 0
				|| tipusDomini.compareTo(TipusDomini.GRUPS_USUARI) == 0 ||
						TipusDomini.MEMBERSHIPS.equals(tipusDomini) ||
						TipusDomini.GROUPS.equals(tipusDomini)) {
			valorDomini = new DomainValue();
			valorDomini.setDescription(entity.getGroup().getDescription());
			if (tipusDomini.compareTo(TipusDomini.GRUPS_USUARI) == 0 ||
					TipusDomini.MEMBERSHIPS.equals(tipusDomini)) {
				valorDomini.setExternalCodeDomain(entity.getAccount()
						.getUsers().iterator().next().getUser().getUserName());
				valorDomini.setDomainName(TipusDomini.MEMBERSHIPS);
			} else {
				valorDomini.setExternalCodeDomain(null);
				valorDomini.setDomainName(TipusDomini.GROUPS);
			}
			valorDomini.setValue(entity.getGroup().getName());
		} else if (tipusDomini.compareTo(TipusDomini.APLICACIONS) == 0 ||
				TipusDomini.APPLICATIONS.equals(tipusDomini)) {
			valorDomini = new DomainValue();
			valorDomini.setExternalCodeDomain(null);
			valorDomini.setDescription(entity.getInformationSystem()
					.getDescription());
			valorDomini.setDomainName(TipusDomini.APPLICATIONS);
			valorDomini.setValue(entity.getInformationSystem().getName());
		}

		// Asignamos el código:
		String sValorDomini = ""; //$NON-NLS-1$
		if (valorDomini != null) {
			sValorDomini = " {" + valorDomini.getDomainName() + "  -  " + valorDomini.getDescription() + "}"; //$NON-NLS-1$
		}
		contenidorRol.setContainerInfo(rol.getName() + "@"
				+ rol.getSystem().getName() + ">"
				+ rol.getInformationSystem().getName() + sValorDomini);

		return contenidorRol;
	}

	/**
	 * Obtenim els rols continguts en el rol (per propagar-los)
	 * 
	 * @param rol
	 * @return rols pares
	 */
	private Collection getRolsContingutsPerPropagar(RoleEntity rol) {
		// Si rol té atorgats d'altres rols (és conetnidor dele rols)
		// s'han de propagar tots els rols que conté (per assignar-lo a
		// l'usuari)
		HashSet rolsPropagar = new HashSet();
		// Sólo hemos de propagar a los usuarios que tienen el rol contenedor
		// con valor de dominio correspondiente (o si es SENSE_DOMINI o a
		// qualque valor)
		// Montamos un FIFO De roles (puede haber cadena de
		// herencia A atorgat B[sense domini] atorgat C ... atorgat Z[amb
		// domini]
		LinkedList rolsAnalitzar = new LinkedList(); // FIFO
		rolsAnalitzar.add(rol);
		RoleEntity rolActual = null;
		while ((rolActual = (RoleEntity) rolsAnalitzar.poll()) != null) {
			Collection socContenidor = rolActual.getContainedRoles();

			if (socContenidor != null)
				for (Iterator it = socContenidor.iterator(); it.hasNext();) {
					RoleDependencyEntity associacio = (RoleDependencyEntity) it
							.next();
					RoleEntity rolContingut = associacio.getContained();
					rolsPropagar.add(rolContingut);
					rolsAnalitzar.add(rolContingut);
				}
		}
		return rolsPropagar;
	}

	private void propagarRols(Collection rolsPropagar) {
		// Propaguem els rols
		if (rolsPropagar != null)
			for (Iterator it = rolsPropagar.iterator(); it.hasNext();) {
				Object obj = it.next();
				if (obj != null) {
					RoleEntity role = (RoleEntity) obj;
					Task updateRole = new Task();
					updateRole.setTransaction("UpdateRole");
					updateRole.setTaskDate(Calendar.getInstance());
					updateRole.setStatus("P");
					updateRole.setRole(role.getName());
					updateRole.setDatabase(role.getSystem().getName());
					TaskEntity tasca = getTaskEntityDao().taskToEntity(
							updateRole);
					getTaskEntityDao().create(tasca);
				}
			}
	}

	public void create(Collection entities) {
		if (entities != null)
			for (Iterator it = entities.iterator(); it.hasNext();) {
				Object obj = it.next();
				if (obj instanceof RoleAccountEntity) {
					RoleAccountEntity entity = (RoleAccountEntity) obj;
					this.create(entity);
				}
			}
	}

	public void update(Collection entities) {
		if (entities != null)
			for (Iterator it = entities.iterator(); it.hasNext();) {
				Object obj = it.next();
				if (obj instanceof RoleAccountEntity) {
					RoleAccountEntity entity = (RoleAccountEntity) obj;
					this.update(entity);
				}
			}
	}

	public void remove(Collection entities) {
		if (entities != null)
			for (Iterator it = entities.iterator(); it.hasNext();) {
				Object obj = it.next();
				if (obj instanceof RoleAccountEntity) {
					RoleAccountEntity entity = (RoleAccountEntity) obj;
					this.remove(entity);
				}
			}
	}

	public RoleAccountEntity roleGrantToEntity(RoleGrant rolGrant) {
		return load(rolGrant.getId());
	}

	@Override
	public void toRoleGrant(RoleAccountEntity source, RoleGrant target) {
		String tipus = source.getRole().getDomainType();
		if ((TipusDomini.APLICACIONS.equals(tipus) || 
				TipusDomini.APPLICATIONS.equals(tipus))
				&& source.getInformationSystem() != null) {
			target.setDomainValue(source.getInformationSystem().getName());
			target.setDomainDescription(source.getInformationSystem().getDescription());
			target.setHasDomain(true);
		} else if ((TipusDomini.GRUPS.equals(tipus) || 
				TipusDomini.GRUPS_USUARI.equals(tipus) ||
				TipusDomini.GROUPS.equals(tipus) ||
				TipusDomini.MEMBERSHIPS.equals(tipus)) && 
				source.getGroup() != null) {
			target.setDomainValue(source.getGroup().getName());
			target.setDomainDescription(source.getGroup().getDescription());
			target.setHasDomain(true);
		} else if ((TipusDomini.DOMINI_APLICACIO.equals(tipus) ||
				TipusDomini.CUSTOM.equals(tipus))
				&& source.getDomainValue() != null) {
			target.setDomainValue(source.getDomainValue().getValue());
			target.setDomainDescription(source.getDomainValue().getDescription());
			target.setHasDomain(true);
		} else if (TipusDomini.QUALQUE_VALOR_DOMINI.equals(tipus)) {
			target.setHasDomain(true);
			target.setDomainValue(null);
		} else {
			target.setHasDomain(false);
			target.setDomainValue(null);
		}
		target.setOwnerRole(null);
		target.setOwnerRoleName(null);
		target.setOwnerGroup(null);
		target.setOwnerAccountName(source.getAccount().getName());
		target.setOwnerSystem(source.getAccount().getSystem().getName());
		target.setId(source.getId());
		target.setRoleId(source.getRole().getId());
		target.setRoleName(source.getRole().getName());
		target.setRoleDescription(source.getRole().getDescription());
		target.setSystem(source.getRole().getSystem().getName());
		target.setInformationSystem(source.getRole().getInformationSystem()
				.getName());
		for (com.soffid.iam.model.UserAccountEntity ua : source.getAccount()
				.getUsers()) {
			target.setUser(ua.getUser().getUserName());
		}
		if (source.getHolderGroup() == null)
			target.setHolderGroup(null);
		else
			target.setHolderGroup(source.getHolderGroup().getName());
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
