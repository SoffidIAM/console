// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

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
import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.AdministracioAplicacio;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.ContenidorRol;
import es.caib.seycon.ng.comu.Identitat;
import es.caib.seycon.ng.comu.Notificacio;
import es.caib.seycon.ng.comu.RolAccount;
import es.caib.seycon.ng.comu.RolGrant;
import es.caib.seycon.ng.comu.Tasca;
import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.ValorDomini;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.DateUtils;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.MailUtils;
import es.caib.seycon.ng.utils.Security;
import es.caib.seycon.ng.utils.TipusContenidorRol;
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
import org.hibernate.Hibernate;

/**
 * @see es.caib.seycon.ng.model.RolAccountEntity
 */
public class RoleAccountEntityDaoImpl extends com.soffid.iam.model.RoleAccountEntityDaoBase {

	
    private void auditarRolAccount(String accio, RoleAccountEntity grant) {
        String codiUsuari = Security.getCurrentAccount();
        if (grant.getAccount().getType().equals (AccountType.USER))
        {
        	for (com.soffid.iam.model.UserAccountEntity ua : grant.getAccount().getUsers()) {
                Auditoria auditoria = new Auditoria();
                auditoria.setAccio(accio);
                auditoria.setRol(grant.getRole().getName());
                auditoria.setUsuari(ua.getUser().getUserName());
                auditoria.setAccount(grant.getAccount().getName());
                auditoria.setBbdd(grant.getRole().getDatabases().getCode());
                auditoria.setAplicacio(grant.getRole().getApplication().getCode());
                auditoria.setAutor(codiUsuari);
                if (grant.getRule() != null) {
                    auditoria.setRule(grant.getRule().getDescription());
                    auditoria.setAccio(accio.toLowerCase());
                }
                if (grant.getDomainApplicationValue() != null) {
                    auditoria.setValorDomini(grant.getDomainApplicationValue().getValue());
                    auditoria.setDomini(grant.getDomainApplicationValue().getDescription());
                } else if (grant.getManagedApplication() != null) {
                    auditoria.setValorDomini(grant.getManagedApplication().getCode());
                    auditoria.setDomini(grant.getManagedApplication().getName());
                } else if (grant.getGroup() != null) {
                    auditoria.setValorDomini(grant.getGroup().getCode());
                    auditoria.setDomini(grant.getGroup().getDescription());
                } else {
                    auditoria.setValorDomini(null);
                    auditoria.setDomini(null);
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
                auditoria.setData(dateFormat.format(Calendar.getInstance().getTime()));
                auditoria.setObjecte("SC_ROLUSU");
                getAuditEntityDao().create(getAuditEntityDao().auditoriaToEntity(auditoria));
            }
        }
        else
        {
            Auditoria auditoria = new Auditoria();
            auditoria.setAccio(accio);
            auditoria.setRol(grant.getRole().getName());
            auditoria.setBbdd(grant.getRole().getDatabases().getCode());
            auditoria.setAccount(grant.getAccount().getName());
            auditoria.setAplicacio(grant.getRole().getApplication().getCode());
            auditoria.setAutor(codiUsuari);
            if (grant.getRule() != null)
            {
                auditoria.setRule(grant.getRule().getDescription());
            	auditoria.setAccio(accio.toLowerCase());
            }
            if (grant.getDomainApplicationValue() != null)
            {
            	auditoria.setValorDomini(grant.getDomainApplicationValue().getValue());
            	auditoria.setDomini(grant.getDomainApplicationValue().getDescription());
            }
            else if (grant.getManagedApplication() != null)
            {
            	auditoria.setValorDomini(grant.getManagedApplication().getCode());
            	auditoria.setDomini(grant.getManagedApplication().getName());
            }
            else if (grant.getGroup() != null)
            {
            	auditoria.setValorDomini(grant.getGroup().getCode());
            	auditoria.setDomini(grant.getGroup().getDescription());
            }
            else
            {
            	auditoria.setValorDomini(null);
            	auditoria.setDomini(null);
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
            auditoria.setData(dateFormat.format(Calendar.getInstance().getTime()));
            auditoria.setObjecte("SC_ROLUSU"); //$NON-NLS-1$
            getAuditEntityDao().create(getAuditEntityDao().auditoriaToEntity(auditoria));
        }
    }

    public void update(RoleAccountEntity rolsUsuaris) {
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

            RolAccount rolsUsuarisVO = toRolAccount(rolsUsuaris);

            String codiAplicacio = null;
            codiAplicacio = rolsUsuarisVO.getCodiAplicacio();

            ValorDomini valorDomini = rolsUsuarisVO.getValorDomini();
            String nomDomini = null;
            String valorDominiString = null;
            if (valorDomini != null) {
                nomDomini = valorDomini.getNomDomini();
                if (valorDomini.getValor() != null) {
                    valorDominiString = valorDomini.getValor();
                }
            }

            // HERÈNCIA DE ROLS
            // Cerquem els rols que contenen (tenen atorgat) aquest rol per
            // propagar-los
            Collection rolsPropagar = getRolsContingutsPerPropagar(rolsUsuaris.getRole());
            // I si tenim cap... els propaguem
            if (rolsPropagar != null)
                propagarRols(rolsPropagar);

            // Enviem les notificacions de l'aplicació (si estan activades)
            if (rolsUsuaris.getRole().getApplication() != null) {
                InformationSystemEntity aplic = rolsUsuaris.getRole().getApplication();
                String correusNotificacio = aplic.getNotificationMail();
                if (correusNotificacio != null) {
                    String correus[] = correusNotificacio.split(","); //$NON-NLS-1$
                    if (correus.length > 0) { // almeny existisca 1
                        // Hay que notificar: Creamos la notificación
                        for (com.soffid.iam.model.UserAccountEntity usu : rolsUsuaris.getAccount().getUsers()) {
                            String informacio = Messages.getString("RolsUsuarisEntityDaoImpl.0");
                            NoticeEntity notif = getNoticeEntityDao().newNoticeEntity();
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
            
            auditarRolAccount("U", rolsUsuaris); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(Messages.getString("RolsUsuarisEntityDaoImpl.1"), rolsUsuaris.getRole().getName(), rolsUsuaris.getAccount().getName(), message));
        }
    }

    private void generateTasks(RoleAccountEntity grant) throws InternalErrorException {
		TaskEntity tasque = getTaskEntityDao().newTaskEntity();
		tasque.setDate(new Timestamp(System.currentTimeMillis()));
		tasque.setTransaction(TaskHandler.UPDATE_ROLE);
		tasque.setRole(grant.getRole().getName());
		tasque.setDb(grant.getRole().getDatabases().getCode());
		getTaskEntityDao().create(tasque);
		
        if (grant.getAccount().getType().equals (AccountType.USER))
        {
        	for (com.soffid.iam.model.UserAccountEntity ua : grant.getAccount().getUsers()) {
                tasque = getTaskEntityDao().newTaskEntity();
                tasque.setDate(new Timestamp(System.currentTimeMillis()));
                tasque.setTransaction(TaskHandler.UPDATE_USER);
                tasque.setUser(ua.getUser().getUserName());
                getTaskEntityDao().create(tasque);
            }
        }
        else
        { 
	        tasque = getTaskEntityDao().newTaskEntity();
	        tasque.setDate(new Timestamp(System.currentTimeMillis()));
	        tasque.setTransaction(TaskHandler.UPDATE_ACCOUNT);
	        tasque.setSystemCode(grant.getAccount().getSystem().getCode());
	        tasque.setDb(grant.getAccount().getSystem().getCode());
	        tasque.setUser(grant.getAccount().getName());
	        getTaskEntityDao().create(tasque);
        }

		getRoleEntityDao().updateMailLists(grant.getRole());

    }

    public void create(com.soffid.iam.model.RoleAccountEntity rolsUsuaris) throws RuntimeException {
        try {
            // Verificamos que no existe antes de crearlo:
            // - Si existe, no se crea, pero no se da ERROR (carga masiva)
            RoleAccountEntity rolUsuariExistent = findExisteixRolUsuari(rolsUsuaris);
            if (rolUsuariExistent == null) { // Lo creamos si no existe
            	
            	rolsUsuaris.setCertificationDate(new Date());

                super.create(rolsUsuaris);
                getSession(false).flush();
                RolAccount rolsUsuarisVO = toRolAccount(rolsUsuaris);

                String codiAplicacio = null;
                codiAplicacio = rolsUsuarisVO.getCodiAplicacio();

                ValorDomini valorDomini = rolsUsuarisVO.getValorDomini();
                String nomDomini = null;
                String valorDominiString = null;
                if (valorDomini != null) {
                    nomDomini = valorDomini.getNomDomini();
                    if (valorDomini.getValor() != null) {
                        valorDominiString = valorDomini.getValor();
                    }
                }

                // HERÈNCIA DE ROLS
                // Cerquem els rols que contenen (tenen atorgat) aquest rol per
                // propagar-los
                Collection rolsPropagar = getRolsContingutsPerPropagar(rolsUsuaris.getRole());
                // I si tenim cap... els propaguem
                if (rolsPropagar != null)
                    propagarRols(rolsPropagar);

                // Enviem les notificacions de l'aplicació (si estan activades)
                if (rolsUsuaris.getRole().getApplication() != null) {
                    InformationSystemEntity aplic = rolsUsuaris.getRole().getApplication();
                    String correusNotificacio = aplic.getNotificationMail();
                    if (correusNotificacio != null) {
                        String correus[] = correusNotificacio.split(","); //$NON-NLS-1$
                        if (correus.length > 0) {
                            for (com.soffid.iam.model.UserAccountEntity usu : rolsUsuaris.getAccount().getUsers()) {
                                String informacio = Messages.getString("RolsUsuarisEntityDaoImpl.2");
                                NoticeEntity notif = getNoticeEntityDao().newNoticeEntity();
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

            } else {
                throw new SeyconException(Messages.getString("RolsUsuarisEntityDaoImpl.3")); //$NON-NLS-1$
            }
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(Messages.getString("RolsUsuarisEntityDaoImpl.4"), rolsUsuaris.getRole().getName(), rolsUsuaris.getAccount().getName(), message));
        }
    }

    public void remove(com.soffid.iam.model.RoleAccountEntity rolsUsuaris) throws RuntimeException {
        try {
            RolAccount rolsUsuarisVO = toRolAccount(rolsUsuaris);
            ValorDomini valorDomini = rolsUsuarisVO.getValorDomini();

            String codiAplicacio = null;
            codiAplicacio = rolsUsuarisVO.getCodiAplicacio();

            String nomDomini = null;
            String valorDominiString = null;
            if (valorDomini != null) {
                nomDomini = valorDomini.getNomDomini();
                if (valorDomini.getValor() != null) {
                    valorDominiString = valorDomini.getValor();
                }
            }

            // HERÈNCIA DE ROLS
            // Cerquem els rols que contenen (tenen atorgat) aquest rol per
            // propagar-los
            Collection rolsPropagar = getRolsContingutsPerPropagar(rolsUsuaris.getRole());
            // I si tenim cap... els propaguem
            if (rolsPropagar != null)
                propagarRols(rolsPropagar);

            // Enviem les notificacions de l'aplicació (si estan activades)
            if (rolsUsuaris.getRole().getApplication() != null) {
                InformationSystemEntity aplic = rolsUsuaris.getRole().getApplication();
                String correusNotificacio = aplic.getNotificationMail();
                if (correusNotificacio != null) {
                    String correus[] = correusNotificacio.split(","); //$NON-NLS-1$
                    if (correus.length > 0) {
                        for (com.soffid.iam.model.UserAccountEntity usu : rolsUsuaris.getAccount().getUsers()) {
                            String informacio = Messages.getString("RolsUsuarisEntityDaoImpl.5");
                            NoticeEntity notif = getNoticeEntityDao().newNoticeEntity();
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

            if (Hibernate.isInitialized(rolsUsuaris.getAccount()) &&
            				Hibernate.isInitialized(rolsUsuaris.getAccount().getRoles()))
            {
            	rolsUsuaris.getAccount().getRoles().remove(rolsUsuaris);
            }

            generateTasks(rolsUsuaris);

            super.remove(rolsUsuaris);

            auditarRolAccount("D", rolsUsuaris); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(Messages.getString("RolsUsuarisEntityDaoImpl.6"), rolsUsuaris.getRole().getName(), rolsUsuaris.getAccount().getName(), message));
        }
    }

    public void toRolAccount(com.soffid.iam.model.RoleAccountEntity sourceEntity, es.caib.seycon.ng.comu.RolAccount targetVO) {
        super.toRolAccount(sourceEntity, targetVO);
        toRolAccountCustom(sourceEntity, targetVO);
    }

    private void toRolAccountCustom(com.soffid.iam.model.RoleAccountEntity sourceEntity, es.caib.seycon.ng.comu.RolAccount targetVO) {
        UserEntity usuariEntity = null;
    	if (sourceEntity.getAccount().getType().equals (AccountType.USER))
    	{
            for (com.soffid.iam.model.UserAccountEntity usu : sourceEntity.getAccount().getUsers()) {
                usuariEntity = usu.getUser();
            }
    	}

        String tipusDomini = sourceEntity.getDomainTypes();
        if (tipusDomini == null || tipusDomini.trim().compareTo("") == 0) { //$NON-NLS-1$
            tipusDomini = TipusDomini.SENSE_DOMINI;
        }
        if (tipusDomini.compareTo(TipusDomini.DOMINI_APLICACIO) == 0) {
            DomainValueEntity valorDominiEntity = sourceEntity.getDomainApplicationValue();
            ValorDomini valorDomini = getDomainValueEntityDao().toValorDomini(valorDominiEntity);
            targetVO.setValorDomini(valorDomini);
        } else if (tipusDomini.compareTo(TipusDomini.GRUPS) == 0
                || tipusDomini.compareTo(TipusDomini.GRUPS_USUARI) == 0) {
            ValorDomini valorDomini = new ValorDomini();
            valorDomini.setDescripcio(sourceEntity.getGroup().getDescription());
            if (tipusDomini.compareTo(TipusDomini.GRUPS_USUARI) == 0) {
        		valorDomini.setCodiExternDomini(usuariEntity == null ? null : usuariEntity.getUserName());
                valorDomini.setNomDomini(TipusDomini.GRUPS_USUARI);
            } else {
                valorDomini.setCodiExternDomini(null);
                valorDomini.setNomDomini(TipusDomini.GRUPS);
            }
            valorDomini.setValor(sourceEntity.getGroup().getCode());
            targetVO.setValorDomini(valorDomini);
        } else if (tipusDomini.compareTo(TipusDomini.APLICACIONS) == 0) {
            ValorDomini valorDomini = new ValorDomini();
            valorDomini.setCodiExternDomini(null);
            valorDomini.setDescripcio(sourceEntity.getManagedApplication().getName());
            valorDomini.setNomDomini(TipusDomini.APLICACIONS);
            valorDomini.setValor(sourceEntity.getManagedApplication().getCode());
            targetVO.setValorDomini(valorDomini);
        } else if (tipusDomini.compareTo(TipusDomini.SENSE_DOMINI) == 0) {
            ValorDomini valorDomini = new ValorDomini();
            valorDomini.setCodiExternDomini(null);
            valorDomini.setDescripcio(TipusDomini.Descripcio.SENSE_DOMINI);
            valorDomini.setNomDomini(TipusDomini.SENSE_DOMINI);
            valorDomini.setValor(""); //$NON-NLS-1$
            // targetVO.setValorDomini(valorDomini); // No se muestra
        }

        // Assign holder gorup
        if (sourceEntity.getHolderGroup() == null)
        	targetVO.setHolderGroup(null);
        else
        	targetVO.setHolderGroup(sourceEntity.getHolderGroup().getCode());

        
        
        targetVO.setNomRol(sourceEntity.getRole().getName());
        
        String nom;
        if (usuariEntity != null)
        {
	        nom = usuariEntity.getFirstName();
	        nom = nom != null ? nom : ""; //$NON-NLS-1$
	        String primerCognom = usuariEntity.getLastName();
	        primerCognom = primerCognom != null ? primerCognom : ""; //$NON-NLS-1$
	        String segonCognom = usuariEntity.getMiddleName();
	        segonCognom = segonCognom != null ? segonCognom : ""; //$NON-NLS-1$
	        targetVO.setNomComplertUsuari(nom + " " + primerCognom + " " + segonCognom); //$NON-NLS-1$ //$NON-NLS-2$
	        targetVO.setCodiUsuari(usuariEntity == null ? null : usuariEntity.getUserName());
        }
        else
        {
        	targetVO.setNomComplertUsuari(sourceEntity.getAccount().getDescription());
        }
        
        targetVO.setAccountName(sourceEntity.getAccount().getName());        
        targetVO.setAccountDispatcher(sourceEntity.getAccount().getSystem().getCode());
        targetVO.setDescripcioRol(sourceEntity.getRole().getDescription());
        targetVO.setAccountId(sourceEntity.getAccount().getId());
        SystemEntity dispatcher = sourceEntity.getRole().getDatabases();
        targetVO.setBaseDeDades(dispatcher == null ? null : dispatcher.getCode());

        InformationSystemEntity aplicacio = sourceEntity.getRole().getApplication();
        if (aplicacio != null) {
            targetVO.setCodiAplicacio(aplicacio.getCode());
        }

        if (usuariEntity != null && usuariEntity.getPrimaryGroup() != null) {
            targetVO.setCodiGrupUsuari(usuariEntity.getPrimaryGroup().getCode());
        }

        targetVO.setGestionableWF(sourceEntity.getRole().getManageableWF());
        
        if (sourceEntity.getRule() == null)
        {
        	targetVO.setRuleId(null);
        	targetVO.setRuleDescription(null);
        }
        else
        {
        	targetVO.setRuleId(sourceEntity.getRule().getId());
        	targetVO.setRuleDescription(sourceEntity.getRule().getDescription());
        }
    }

    /**
     * @see es.caib.seycon.ng.model.RolAccountEntityDao#toRolAccount(es.caib.seycon.ng.model.RolAccountEntity)
     */
    public es.caib.seycon.ng.comu.RolAccount toRolAccount(final com.soffid.iam.model.RoleAccountEntity entity) {
        RolAccount rolUsuari = super.toRolAccount(entity);
        toRolAccountCustom(entity, rolUsuari);
        return rolUsuari;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private com.soffid.iam.model.RoleAccountEntity loadRolAccountEntityFromRolAccount(es.caib.seycon.ng.comu.RolAccount rolsUsuaris) {
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
    public com.soffid.iam.model.RoleAccountEntity rolAccountToEntity(es.caib.seycon.ng.comu.RolAccount rolsUsuaris) {
        com.soffid.iam.model.RoleAccountEntity entity = this.loadRolAccountEntityFromRolAccount(rolsUsuaris);
        this.rolAccountToEntity(rolsUsuaris, entity, true);
        return entity;
    }

    private RoleEntity findRolByNomAndCodiApliacio(String nom, String codiAplicacio, String bbdd) {
        String query = "select rol " //$NON-NLS-1$
                + "from es.caib.seycon.ng.model.RolEntity rol " //$NON-NLS-1$
                + "where " //$NON-NLS-1$
                + "rol.nom = :nom and " + " rol.baseDeDades.codi = :bbdd"; //$NON-NLS-1$ //$NON-NLS-2$
        Parameter nomParameter = new Parameter("nom", nom); //$NON-NLS-1$
        Parameter bbddParameter = new Parameter("bbdd", bbdd); //$NON-NLS-1$
        Parameter[] parameters = { nomParameter, bbddParameter };
        Collection rols = getRoleEntityDao().query(query, parameters);
        if (rols != null && !rols.isEmpty()) {
            return (RoleEntity) rols.iterator().next();
        }
        return null;
    }

    private void rolsUsuarisToEntityCustom(es.caib.seycon.ng.comu.RolAccount sourceVO, com.soffid.iam.model.RoleAccountEntity targetEntity) {
    	com.soffid.iam.model.AccountEntity account = null;
    	com.soffid.iam.model.AccountEntityDao accDao = getAccountEntityDao();
    	
        if (targetEntity.getStartDate() != null)
        	targetEntity.setStartDate(removeSeconds(targetEntity.getStartDate()));
        if (targetEntity.getEndDate() != null)
        	targetEntity.setEndDate(removeSeconds(targetEntity.getEndDate()));

    	if (sourceVO.getAccountId() != null)
    		account = accDao.load (sourceVO.getAccountId().longValue());
    	else if (sourceVO.getAccountName() != null && sourceVO.getAccountDispatcher() != null)
    	{
    		account = accDao.findByNameAndSystem(sourceVO.getAccountName(), sourceVO.getBaseDeDades());
    	}
    	else if (sourceVO.getCodiUsuari() != null)
    	{
   			List<com.soffid.iam.model.AccountEntity> accounts = accDao.findByUserAndSystem(sourceVO.getCodiUsuari(), sourceVO.getBaseDeDades());
   			if (accounts.size() > 1)
   				throw new IllegalArgumentException(
   						String.format (Messages.getString("RoleAccountEntityDaoImpl.MoreThanOneUserAccount"), //$NON-NLS-1$
   								sourceVO.getCodiUsuari(),
   								sourceVO.getBaseDeDades())
   						);
   			if (accounts.size() == 1)
   				account = accounts.get(0);
    	}
        if (account == null) {
            throw new SeyconException(String.format(Messages.getString("RolsUsuarisEntityDaoImpl.7"), sourceVO.getCodiUsuari())); //$NON-NLS-1$
        }
        targetEntity.setAccount(account);

        String nomRolDomini = sourceVO.getNomRol();
        String codiAplicacioRol = sourceVO.getCodiAplicacio();
        String codiBBDD = sourceVO.getBaseDeDades();
        RoleEntity rol = null;
        if (nomRolDomini != null) {
            rol = findRolByNomAndCodiApliacio(nomRolDomini, codiAplicacioRol, codiBBDD);
        }
        if (rol == null) {
            throw new SeyconException(String.format(Messages.getString("RolsUsuarisEntityDaoImpl.8"),nomRolDomini)); //$NON-NLS-1$
        }

        targetEntity.setRole(rol);

        ValorDomini valorDomini = sourceVO.getValorDomini();
        if (valorDomini == null) {
            valorDomini = new ValorDomini();
            valorDomini.setCodiExternDomini(null);
            valorDomini.setNomDomini(TipusDomini.SENSE_DOMINI);
            valorDomini.setValor(TipusDomini.SENSE_DOMINI);
        }
        String nomDomini = rol.getDomainType();
        if (nomDomini.compareTo(TipusDomini.GRUPS) == 0
                || nomDomini.compareTo(TipusDomini.GRUPS_USUARI) == 0
                || nomDomini.compareTo(TipusDomini.APLICACIONS) == 0
                || nomDomini.compareTo(TipusDomini.SENSE_DOMINI) == 0) {
            if (nomDomini.compareTo(TipusDomini.GRUPS) == 0
                    || nomDomini.compareTo(TipusDomini.GRUPS_USUARI) == 0) {
                String codiGrup = valorDomini.getValor();
                GroupEntity grup = null;
                if (codiGrup != null && codiGrup.trim().compareTo("") != 0) { //$NON-NLS-1$
                    grup = getGroupEntityDao().findByCode(codiGrup);
                    if (grup != null) {
                        if (nomDomini.compareTo(TipusDomini.GRUPS_USUARI) == 0) {
                            String codiUsuari = sourceVO.getCodiUsuari();
                            if (codiUsuari != null) {
                                if (!grupPertanyAUsuari(codiGrup, codiUsuari)) {
                                    throw new SeyconException(String.format(Messages.getString("RolsUsuarisEntityDaoImpl.9"),codiGrup,codiUsuari)); //$NON-NLS-1$
                                }
                            } else {
                                throw new SeyconException(
                                        Messages.getString("RolsUsuarisEntityDaoImpl.10")); //$NON-NLS-1$
                            }
                        }
                    } else {
                        throw new SeyconException(String.format(Messages.getString("RolsUsuarisEntityDaoImpl.11"), codiGrup)); //$NON-NLS-1$
                    }
                } else {
                    throw new SeyconException(
                            Messages.getString("RolsUsuarisEntityDaoImpl.12")); //$NON-NLS-1$

                }
                targetEntity.setGroup(grup);
                targetEntity.setDomainApplicationValue(null);
                targetEntity.setDomainTypes(nomDomini);
                targetEntity.setManagedApplication(null);
            } else if (nomDomini.compareTo(TipusDomini.APLICACIONS) == 0) {
                String valor = valorDomini.getValor();
                InformationSystemEntity aplicacioEntity = getInformationSystemEntityDao().findByCode(valor);
                if (aplicacioEntity == null) {
                    throw new SeyconException(String.format(Messages.getString("RolsUsuarisEntityDaoImpl.13"),valor)); //"Aplicació amb codi '" + valor + "' no trobada."); //$NON-NLS-1$
                }
                targetEntity.setManagedApplication(aplicacioEntity);
                targetEntity.setGroup(null);
                targetEntity.setDomainApplicationValue(null);
                targetEntity.setDomainTypes(TipusDomini.APLICACIONS);
            } else if (nomDomini.compareTo(TipusDomini.SENSE_DOMINI) == 0) {
                targetEntity.setManagedApplication(null);
                targetEntity.setGroup(null);
                targetEntity.setDomainApplicationValue(null);
                targetEntity.setDomainTypes(TipusDomini.SENSE_DOMINI);
            }
        } else if (rol.getApplicationDomain() != null){
            /*
             * Domini d'aplicació
             */
            nomDomini = rol.getApplicationDomain().getName();
            String codiAplicacio = rol.getApplication().getCode();
            String valor = valorDomini.getValor();
            DomainValueEntity valorDominiAplicacioEntity = findValorDominiByNomDominiAndCodiAplicacioDominiAndValor(nomDomini, codiAplicacio, valor);
            if (valorDominiAplicacioEntity != null) {
                targetEntity.setDomainApplicationValue(valorDominiAplicacioEntity);
                targetEntity.setDomainTypes(TipusDomini.DOMINI_APLICACIO);
                targetEntity.setGroup(null);
                targetEntity.setManagedApplication(null);
            } else {
                throw new SeyconException(String.format(Messages.getString("RolsUsuarisEntityDaoImpl.14"), nomDomini, codiAplicacio, valor )); //$NON-NLS-1$
            }
        }
        
        if (sourceVO.getRuleId() == null)
        	targetEntity.setRule(null);
        else
        	targetEntity.setRule(getRuleEntityDao().load(sourceVO.getRuleId()));
        
        if (sourceVO.getHolderGroup() == null)
        	targetEntity.setHolderGroup(null);
        else
        {
        	GroupEntity grup = getGroupEntityDao().findByCode(sourceVO.getHolderGroup());
        	if (grup == null)
        		throw new SeyconException (String.format("Unknown group %s", sourceVO.getHolderGroup()));
        	targetEntity.setHolderGroup(grup);
        }
    }

    private DomainValueEntity findValorDominiByNomDominiAndCodiAplicacioDominiAndValor(String nomDomini, String codiAplicacio, String valor) {
        String query = "select valorDominiAplicacio " //$NON-NLS-1$
                + "from " //$NON-NLS-1$
                + "es.caib.seycon.ng.model.ValorDominiAplicacioEntity valorDominiAplicacio " //$NON-NLS-1$
                + "left join valorDominiAplicacio.domini domini " //$NON-NLS-1$
                + "left join domini.aplicacio aplicacio " //$NON-NLS-1$
                + "where " //$NON-NLS-1$
                + "domini.nom = :nomDomini and " //$NON-NLS-1$
                + "((:codiAplicacio is null and aplicacio is null) or (aplicacio.codi = :codiAplicacio)) and " //$NON-NLS-1$
                + "valorDominiAplicacio.valor = :valor"; //$NON-NLS-1$

        Parameter nomDominiParameter = new Parameter("nomDomini", nomDomini); //$NON-NLS-1$
        Parameter codiAplicacioParameter = new Parameter("codiAplicacio", codiAplicacio); //$NON-NLS-1$
        Parameter valorParameter = new Parameter("valor", valor); //$NON-NLS-1$
        Parameter[] parametres = { nomDominiParameter, codiAplicacioParameter, valorParameter };

        Collection valorsDomini = getDomainValueEntityDao().query(query, parametres);
        if (valorsDomini != null) {
            Iterator valorsDominiIterator = valorsDomini.iterator();
            if (valorsDominiIterator != null) {
                if (valorsDominiIterator.hasNext()) {
                    DomainValueEntity valorDominiEntity = (DomainValueEntity) valorsDominiIterator.next();
                    return valorDominiEntity;
                }
            }
        }
        return null;
    }

    private RoleAccountEntity findExisteixRolUsuari(RoleAccountEntity rolUsuari) {
        String query = "select rolsUsuaris " //$NON-NLS-1$
                + "from " //$NON-NLS-1$
                + "es.caib.seycon.ng.model.RolAccountEntity rolsUsuaris " //$NON-NLS-1$
                + "left join rolsUsuaris.grup grup " //$NON-NLS-1$
                + "left join rolsUsuaris.aplicacioAdministrada aplicacio " //$NON-NLS-1$
                + "left join rolsUsuaris.valorDominiAplicacio valorDominiAplicacio " //$NON-NLS-1$
                + "where " //$NON-NLS-1$
                + "rolsUsuaris.account.id = :usuID and " //$NON-NLS-1$
                + "rolsUsuaris.rol.id = :rolID and " //$NON-NLS-1$
                + "(rolsUsuaris.tipusDomini = :tipusDomini) and " //$NON-NLS-1$
                + "((:grupCodi is null and grup is null) or (grup.codi = :grupCodi)) and " //$NON-NLS-1$
                + "((:codiAplicacio is null and aplicacio is null) or (aplicacio.codi = :codiAplicacio)) and " //$NON-NLS-1$
                + "((:valorDomini is null and valorDominiAplicacio is null) or (valorDominiAplicacio.valor = :valorDomini)) "; //$NON-NLS-1$

        // Comparamos el usuario y el rol por ID (siempre tiene ambos)
        Parameter p_usuID = new Parameter("usuID", rolUsuari.getAccount().getId()); //$NON-NLS-1$
        Parameter p_rolID = new Parameter("rolID", rolUsuari.getRole().getId()); //$NON-NLS-1$
        Parameter p_tipusDomini = new Parameter("tipusDomini", rolUsuari.getDomainTypes()); //$NON-NLS-1$
        // Estos pueden ser nulos
        String grupCodi = rolUsuari.getGroup() != null ? rolUsuari.getGroup().getCode() : null;
        Parameter p_grupCodi = new Parameter("grupCodi", grupCodi); //$NON-NLS-1$
        String aplicacioAdministrada = rolUsuari.getManagedApplication() != null ? rolUsuari.getManagedApplication().getCode() : null;
        Parameter p_codiAplicacio = new Parameter("codiAplicacio", aplicacioAdministrada); //$NON-NLS-1$
        String valorDomini = rolUsuari.getDomainApplicationValue() != null ? rolUsuari.getDomainApplicationValue().getValue() : null;
        Parameter p_valorDomini = new Parameter("valorDomini", valorDomini); //$NON-NLS-1$

        Collection rolsUsu = find(query, new Parameter[] { p_usuID, p_rolID, p_tipusDomini,
                p_grupCodi, p_codiAplicacio, p_valorDomini });

        if (rolsUsu != null && rolsUsu.size() != 0) {
            return (RoleAccountEntity) ((java.util.List) rolsUsu).get(0);
        }

        return null;
    }

    private boolean grupPertanyAUsuari(String codiGrup, String codiUsuari) {
        Collection grups = getUserEntityDao().findGroupByCode(codiUsuari);
        Iterator iterator = grups.iterator();
        while (iterator.hasNext()) {
            GroupEntity grupEntity = (GroupEntity) iterator.next();
            if (grupEntity.getCode().compareTo(codiGrup) == 0) {
                return true;
            }
        }
        UserEntity usuari = getUserEntityDao().findByCode(codiUsuari);
        GroupEntity grupPrimariEntity = usuari.getPrimaryGroup();
        if (grupPrimariEntity != null) {
            return grupPrimariEntity.getCode().compareTo(codiGrup) == 0;
        }
        return false;
    }

    private Date removeSeconds (Date d)
    {
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
    public void rolAccountToEntity(es.caib.seycon.ng.comu.RolAccount sourceVO, com.soffid.iam.model.RoleAccountEntity targetEntity, boolean copyIfNull) {
        super.rolAccountToEntity(sourceVO, targetEntity, copyIfNull);

        rolsUsuarisToEntityCustom(sourceVO, targetEntity);
    }

    /**
     * @see es.caib.seycon.ng.model.UsuariEntityDao#find(int, java.lang.String,
     *      es.caib.seycon.ng.model.Parameter[])
     */
    public List<RoleAccountEntity> find(final java.lang.String queryString, final es.caib.seycon.ng.model.Parameter[] parameters) {
        try {
            java.util.List results = new QueryBuilder().query(this, queryString, parameters);
            return results;
        } catch (org.hibernate.HibernateException ex) {
            throw super.convertHibernateAccessException(ex);
        }
    }

    private com.soffid.iam.model.RoleAccountEntity loadRolAccountEntityFromAdministracioAplicacio(es.caib.seycon.ng.comu.AdministracioAplicacio administracioAplicacio) {
        RoleAccountEntity rolsUsuarisEntity = null;
        if (administracioAplicacio.getId() != null) {
            rolsUsuarisEntity = load(administracioAplicacio.getId());
        }
        if (rolsUsuarisEntity == null) {
            rolsUsuarisEntity = newRoleAccountEntity();
        }
        return rolsUsuarisEntity;
    }

    public RoleAccountEntity administracioAplicacioToEntity(AdministracioAplicacio administracioAplicacio) {
        com.soffid.iam.model.RoleAccountEntity entity = this.loadRolAccountEntityFromAdministracioAplicacio(administracioAplicacio);
        this.administracioAplicacioToEntity(administracioAplicacio, entity);
        return entity;
    }

    public void toAdministracioAplicacio(RoleAccountEntity source, AdministracioAplicacio target) {
        super.toAdministracioAplicacio(source, target);
        target.setCodiAplicacio(source.getManagedApplication().getCode());
        UserEntity usuariEntity = source.getAccount().getUsers().iterator().next().getUser();
        target.setCodiUsuari(usuariEntity.getUserName());
        String nom = usuariEntity.getFirstName();
        nom = nom != null ? nom : ""; //$NON-NLS-1$
        String primerCognom = usuariEntity.getLastName();
        primerCognom = primerCognom != null ? primerCognom : ""; //$NON-NLS-1$
        String segonCognom = usuariEntity.getMiddleName();
        segonCognom = segonCognom != null ? segonCognom : ""; //$NON-NLS-1$
        target.setNomComplertUsuari(nom + " " + primerCognom + " " + segonCognom); //$NON-NLS-1$ //$NON-NLS-2$
        target.setNomRol(source.getRole().getName());
        target.setCodiAplicacioRol(source.getRole().getApplication().getCode());
        target.setCodiBaseDeDadesRol(source.getRole().getDatabases().getCode());
    }

    public AdministracioAplicacio toAdministracioAplicacio(final RoleAccountEntity entity) {
        return super.toAdministracioAplicacio(entity);
    }

    public void administracioAplicacioToEntity(AdministracioAplicacio administracioAplicacio, RoleAccountEntity targetEntity) {
        String aplicacioAdministrada = administracioAplicacio.getCodiAplicacio();
        InformationSystemEntity aplicacioEntity = getInformationSystemEntityDao().findByCode(aplicacioAdministrada);
        if (aplicacioEntity == null) {
            throw new SeyconException(String.format(Messages.getString("RolsUsuarisEntityDaoImpl.15"), aplicacioAdministrada)); //$NON-NLS-1$
        }
        targetEntity.setManagedApplication(aplicacioEntity);
        targetEntity.setGroup(null);
        targetEntity.setDomainApplicationValue(null);
        targetEntity.setDomainTypes(TipusDomini.APLICACIONS);

        RoleEntity rolEntity = getRoleEntityDao().findRoleByRoleNameAndApplicationCodeAndSystemCode(administracioAplicacio.getNomRol(), administracioAplicacio.getCodiAplicacioRol(), administracioAplicacio.getCodiBaseDeDadesRol());
        if (rolEntity == null) {
            throw new SeyconException(String.format(Messages.getString("RolsUsuarisEntityDaoImpl.16"), administracioAplicacio.getNomRol())); //$NON-NLS-1$
        }
        targetEntity.setRole(rolEntity);

        List<com.soffid.iam.model.AccountEntity> accs = getAccountEntityDao().findByUserAndSystem(administracioAplicacio.getCodiUsuari(), administracioAplicacio.getCodiBaseDeDadesRol());
        if (accs.size() != 1) {
            throw new SeyconException(String.format(Messages.getString("RolsUsuarisEntityDaoImpl.17"), administracioAplicacio.getCodiUsuari())); //$NON-NLS-1$
        }
        targetEntity.setAccount(accs.get(0));
    }

    public RoleAccountEntity contenidorRolToEntity(ContenidorRol contenidorRol) {
        // TODO Auto-generated method stub
        return null;
    }

    public ContenidorRol toContenidorRol(RoleAccountEntity entity) {
        ContenidorRol contenidorRol = super.toContenidorRol(entity); // Pasamos
                                                                     // el id
        contenidorRol.setTipus(TipusContenidorRol.ROL_USUARI);
        // Información específica:
        RoleEntity rol = entity.getRole();
        ValorDomini valorDomini = null;

        String tipusDomini = rol.getDomainType();
        if (tipusDomini == null || tipusDomini.trim().compareTo("") == 0) { //$NON-NLS-1$
            tipusDomini = TipusDomini.SENSE_DOMINI;
        }
        if (tipusDomini.compareTo(TipusDomini.DOMINI_APLICACIO) == 0) {
            DomainValueEntity valorDominiEntity = entity.getDomainApplicationValue();
            valorDomini = getDomainValueEntityDao().toValorDomini(valorDominiEntity);
        } else if (tipusDomini.compareTo(TipusDomini.GRUPS) == 0
                || tipusDomini.compareTo(TipusDomini.GRUPS_USUARI) == 0) {
            valorDomini = new ValorDomini();
            valorDomini.setDescripcio(entity.getGroup().getDescription());
            if (tipusDomini.compareTo(TipusDomini.GRUPS_USUARI) == 0) {
                valorDomini.setCodiExternDomini(entity.getAccount().getUsers().iterator().next().getUser().getUserName());
                valorDomini.setNomDomini(TipusDomini.GRUPS_USUARI);
            } else {
                valorDomini.setCodiExternDomini(null);
                valorDomini.setNomDomini(TipusDomini.GRUPS);
            }
            valorDomini.setValor(entity.getGroup().getCode());
        } else if (tipusDomini.compareTo(TipusDomini.APLICACIONS) == 0) {
            valorDomini = new ValorDomini();
            valorDomini.setCodiExternDomini(null);
            valorDomini.setDescripcio(entity.getManagedApplication().getName());
            valorDomini.setNomDomini(TipusDomini.APLICACIONS);
            valorDomini.setValor(entity.getManagedApplication().getCode());
        } else if (tipusDomini.compareTo(TipusDomini.SENSE_DOMINI) == 0) {
            /*
             * valorDomini = new ValorDomini();
             * valorDomini.setCodiExternDomini(null);
             * valorDomini.setDescripcio(TipusDomini.Descripcio.SENSE_DOMINI);
             * valorDomini.setNomDomini(TipusDomini.SENSE_DOMINI);
             * valorDomini.setValor(""); //targetVO.setValorDomini(valorDomini);
             * // No se muestra
             */
        }

        // Asignamos el código:
        String sValorDomini = ""; //$NON-NLS-1$
        if (valorDomini != null) {
            sValorDomini = " {" + valorDomini.getNomDomini() + "  -  " //$NON-NLS-1$ //$NON-NLS-2$
                    + valorDomini.getDescripcio() + "}"; //$NON-NLS-1$
        }
        contenidorRol.setInfoContenidor(rol.getName() + "@" + rol.getDatabases().getCode() + ">" + rol.getApplication().getCode() + sValorDomini);

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
            Collection socContenidor = rolActual.getRolAssociationContainer();

            if (socContenidor != null)
                for (Iterator it = socContenidor.iterator(); it.hasNext(); ) {
                RoleDependencyEntity associacio = (RoleDependencyEntity) it.next();
                RoleEntity rolContingut = associacio.getRoleContent();
                rolsPropagar.add(rolContingut);
                rolsAnalitzar.add(rolContingut);
            }
        }
        return rolsPropagar;
    }

    private void propagarRols(Collection rolsPropagar) {
        // Propaguem els rols
        if (rolsPropagar != null)
            for (Iterator it = rolsPropagar.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj != null) {
                RoleEntity role = (RoleEntity) obj;
                Tasca updateRole = new Tasca();
                updateRole.setTransa("UpdateRole");
                updateRole.setDataTasca(Calendar.getInstance());
                updateRole.setStatus("P");
                updateRole.setRole(role.getName());
                updateRole.setBd(role.getDatabases().getCode());
                TaskEntity tasca = getTaskEntityDao().tascaToEntity(updateRole);
                getTaskEntityDao().create(tasca);
            }
        }
    }

    public void create(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof RoleAccountEntity) {
                RoleAccountEntity entity = (RoleAccountEntity) obj;
                this.create(entity);
            }
        }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof RoleAccountEntity) {
                RoleAccountEntity entity = (RoleAccountEntity) obj;
                this.update(entity);
            }
        }
    }

    public void remove(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof RoleAccountEntity) {
                RoleAccountEntity entity = (RoleAccountEntity) obj;
                this.remove(entity);
            }
        }
    }

    public RoleAccountEntity rolGrantToEntity(RolGrant rolGrant) {
        return load(rolGrant.getId());
    }

    @Override
    public void toRolGrant(RoleAccountEntity source, RolGrant target) {
        String tipus = source.getRole().getDomainType();
        if (TipusDomini.APLICACIONS.equals(tipus) && source.getManagedApplication() != null) {
            target.setDomainValue(source.getManagedApplication().getCode());
            target.setHasDomain(true);
        } else if ((TipusDomini.GRUPS.equals(tipus) || TipusDomini.GRUPS_USUARI.equals(tipus)) && source.getGroup() != null) {
            target.setDomainValue(source.getGroup().getCode());
            target.setHasDomain(true);
        } else if (TipusDomini.DOMINI_APLICACIO.equals(tipus) && source.getDomainApplicationValue() != null) {
            target.setDomainValue(source.getDomainApplicationValue().getValue());
            target.setHasDomain(true);
        } else if (TipusDomini.QUALQUE_VALOR_DOMINI.equals(tipus)) {
            target.setHasDomain(true);
            target.setDomainValue(null);
        } else {
            target.setHasDomain(false);
            target.setDomainValue(null);
        }
        target.setOwnerRol(null);
        target.setOwnerRolName(null);
        target.setOwnerGroup(null);
        target.setOwnerAccountName(source.getAccount().getName());
        target.setOwnerDispatcher(source.getAccount().getSystem().getCode());
        target.setId(source.getId());
        target.setIdRol(source.getRole().getId());
        target.setRolName(source.getRole().getName());
        target.setDispatcher(source.getRole().getDatabases().getCode());
		for (com.soffid.iam.model.UserAccountEntity ua : source.getAccount().getUsers()) {
            target.setUser(ua.getUser().getUserName());
        }
		if (source.getHolderGroup() == null)
			target.setHolderGroup(null);
		else
			target.setHolderGroup(source.getHolderGroup().getCode());
    }

}
