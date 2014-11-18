// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

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

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.AdministracioAplicacio;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.ContenidorRol;
import es.caib.seycon.ng.comu.Identitat;
import es.caib.seycon.ng.comu.Notificacio;
import es.caib.seycon.ng.comu.RolGrant;
import es.caib.seycon.ng.comu.RolAccount;
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

/**
 * @see es.caib.seycon.ng.model.RolAccountEntity
 */
public class RolAccountEntityDaoImpl extends es.caib.seycon.ng.model.RolAccountEntityDaoBase {

    private void auditarRolAccount(String accio, RolAccountEntity grant) {
        String codiUsuari = Security.getCurrentAccount();
        if (grant.getAccount().getType().equals (AccountType.USER))
        {
        	for (UserAccountEntity ua: grant.getAccount().getUsers())
        	{
                Auditoria auditoria = new Auditoria();
                auditoria.setAccio(accio);
                auditoria.setRol(grant.getRol().getNom());
                auditoria.setUsuari(ua.getUser().getCodi());
                auditoria.setAccount(grant.getAccount().getName());
                auditoria.setBbdd(grant.getRol().getBaseDeDades().getCodi());
                auditoria.setAplicacio(grant.getRol().getAplicacio().getCodi());
                auditoria.setAutor(codiUsuari);
                if (grant.getRule() != null)
                {
                    auditoria.setRule(grant.getRule().getDescription());
                	auditoria.setAccio(accio.toLowerCase());
                }
                if (grant.getValorDominiAplicacio() != null)
                {
                	auditoria.setValorDomini(grant.getValorDominiAplicacio().getValor());
                	auditoria.setDomini(grant.getValorDominiAplicacio().getDescripcio());
                }
                else if (grant.getAplicacioAdministrada() != null)
                {
                	auditoria.setValorDomini(grant.getAplicacioAdministrada().getCodi());
                	auditoria.setDomini(grant.getAplicacioAdministrada().getNom());
                }
                else if (grant.getGrup() != null)
                {
                	auditoria.setValorDomini(grant.getGrup().getCodi());
                	auditoria.setDomini(grant.getGrup().getDescripcio());
                }
                else
                {
                	auditoria.setValorDomini(null);
                	auditoria.setDomini(null);
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
                auditoria.setData(dateFormat.format(Calendar.getInstance().getTime()));
                auditoria.setObjecte("SC_ROLUSU"); //$NON-NLS-1$
                getAuditoriaEntityDao().create(getAuditoriaEntityDao().auditoriaToEntity(auditoria));
        	}
        }
        else
        {
            Auditoria auditoria = new Auditoria();
            auditoria.setAccio(accio);
            auditoria.setRol(grant.getRol().getNom());
            auditoria.setBbdd(grant.getRol().getBaseDeDades().getCodi());
            auditoria.setAccount(grant.getAccount().getName());
            auditoria.setAplicacio(grant.getRol().getAplicacio().getCodi());
            auditoria.setAutor(codiUsuari);
            if (grant.getRule() != null)
            {
                auditoria.setRule(grant.getRule().getDescription());
            	auditoria.setAccio(accio.toLowerCase());
            }
            if (grant.getValorDominiAplicacio() != null)
            {
            	auditoria.setValorDomini(grant.getValorDominiAplicacio().getValor());
            	auditoria.setDomini(grant.getValorDominiAplicacio().getDescripcio());
            }
            else if (grant.getAplicacioAdministrada() != null)
            {
            	auditoria.setValorDomini(grant.getAplicacioAdministrada().getCodi());
            	auditoria.setDomini(grant.getAplicacioAdministrada().getNom());
            }
            else if (grant.getGrup() != null)
            {
            	auditoria.setValorDomini(grant.getGrup().getCodi());
            	auditoria.setDomini(grant.getGrup().getDescripcio());
            }
            else
            {
            	auditoria.setValorDomini(null);
            	auditoria.setDomini(null);
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
            auditoria.setData(dateFormat.format(Calendar.getInstance().getTime()));
            auditoria.setObjecte("SC_ROLUSU"); //$NON-NLS-1$
            getAuditoriaEntityDao().create(getAuditoriaEntityDao().auditoriaToEntity(auditoria));
        }
    }

    public void update(RolAccountEntity rolsUsuaris) {
        // Aquest mètode s'empra només en SC_RESPONSABLE de les aplicacions
        // Només es pot tindre 1 responsable, i s'actualitza l'existent (si
        // existeix)
        // sino es crea un de nou
        try {
            // IMPORTANT: COM AQUI NO ES POT CANVIAR EL ROL, NO COMPROVEM
            // L'HERENCIA
            // DEL ROL ANTERIOR I EL ROL DESPRÉS DEL UPDATE (!!)
            // PERQUE SERÀ EL MATEIX

            RolAccountEntity old = load(rolsUsuaris.getId());
            
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
            Collection rolsPropagar = getRolsContingutsPerPropagar(rolsUsuaris.getRol());
            // I si tenim cap... els propaguem
            if (rolsPropagar != null)
                propagarRols(rolsPropagar);

            // Enviem les notificacions de l'aplicació (si estan activades)
            if (rolsUsuaris.getRol().getAplicacio() != null) {
                AplicacioEntity aplic = rolsUsuaris.getRol().getAplicacio();
                String correusNotificacio = aplic.getCorreusNotificacions();
                if (correusNotificacio != null) {
                    String correus[] = correusNotificacio.split(","); //$NON-NLS-1$
                    if (correus.length > 0) { // almeny existisca 1
                        // Hay que notificar: Creamos la notificación
                        for (UserAccountEntity usu: rolsUsuaris.getAccount().getUsers()) 
                        {
                            String informacio = Messages.getString("RolsUsuarisEntityDaoImpl.0"); //$NON-NLS-1$
                            // Creem l'instància de la notificació
                            NotificacioEntity notif = getNotificacioEntityDao().newNotificacioEntity();
                            notif.setDataModificacio(new Date());
                            notif.setInformacio(informacio);
                            notif.setRol(rolsUsuaris.getRol());
                            notif.setAplicacio(aplic);
                            notif.setUsuari(usu.getUser());
                            getNotificacioEntityDao().create(notif);
                        }
                    }
                }
            }

            generateTasks(rolsUsuaris);
            generateTasks(old);

            
            auditarRolAccount("U", rolsUsuaris); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(
                    Messages.getString("RolsUsuarisEntityDaoImpl.1"), rolsUsuaris.getRol().getNom(), //$NON-NLS-1$
                    rolsUsuaris.getAccount().getName(), message));
        }
    }

    private void generateTasks(RolAccountEntity grant) {
		TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
		tasque.setData(new Timestamp(System.currentTimeMillis()));
		tasque.setTransa(TaskHandler.UPDATE_ROLE);
		tasque.setRole(grant.getRol().getNom());
		tasque.setBd(grant.getRol().getBaseDeDades().getCodi());
		getTasqueEntityDao().create(tasque);
		
        if (grant.getAccount().getType().equals (AccountType.USER))
        {
        	for (UserAccountEntity ua: grant.getAccount().getUsers())
        	{
		        tasque = getTasqueEntityDao().newTasqueEntity();
		        tasque.setData(new Timestamp(System.currentTimeMillis()));
		        tasque.setTransa(TaskHandler.UPDATE_USER);
		        tasque.setUsuari(ua.getUser().getCodi());
		        getTasqueEntityDao().create(tasque);
        	}
        }
        else
        { 
	        tasque = getTasqueEntityDao().newTasqueEntity();
	        tasque.setData(new Timestamp(System.currentTimeMillis()));
	        tasque.setTransa(TaskHandler.UPDATE_ACCOUNT);
	        tasque.setCoddis(grant.getAccount().getDispatcher().getCodi());
	        tasque.setBd(grant.getAccount().getDispatcher().getCodi());
	        tasque.setUsuari(grant.getAccount().getName());
	        getTasqueEntityDao().create(tasque);
        }
    }

    public void create(es.caib.seycon.ng.model.RolAccountEntity rolsUsuaris)
            throws RuntimeException {
        try {
            // Verificamos que no existe antes de crearlo:
            // - Si existe, no se crea, pero no se da ERROR (carga masiva)
            RolAccountEntity rolUsuariExistent = findExisteixRolUsuari(rolsUsuaris);
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
                Collection rolsPropagar = getRolsContingutsPerPropagar(rolsUsuaris.getRol());
                // I si tenim cap... els propaguem
                if (rolsPropagar != null)
                    propagarRols(rolsPropagar);

                // Enviem les notificacions de l'aplicació (si estan activades)
                if (rolsUsuaris.getRol().getAplicacio() != null) {
                    AplicacioEntity aplic = rolsUsuaris.getRol().getAplicacio();
                    String correusNotificacio = aplic.getCorreusNotificacions();
                    if (correusNotificacio != null) {
                        String correus[] = correusNotificacio.split(","); //$NON-NLS-1$
                        if (correus.length > 0) {
                            for (UserAccountEntity usu: rolsUsuaris.getAccount().getUsers()) 
                            {
                            // Hay que notificar: Creamos la notificación
	                            String informacio = Messages.getString("RolsUsuarisEntityDaoImpl.2"); //$NON-NLS-1$
	                            // Creem l'instància de la notificació
	                            NotificacioEntity notif = getNotificacioEntityDao().newNotificacioEntity();
	                            notif.setAplicacio(aplic);
	                            notif.setDataModificacio(new Date());
	                            notif.setInformacio(informacio);
	                            notif.setRol(rolsUsuaris.getRol());
	                            notif.setUsuari(usu.getUser());
	                            getNotificacioEntityDao().create(notif);
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
            throw new SeyconException(String.format(
                    Messages.getString("RolsUsuarisEntityDaoImpl.4"), rolsUsuaris.getRol().getNom(), //$NON-NLS-1$
                    rolsUsuaris.getAccount().getName(), message));
        }
    }

    public void remove(es.caib.seycon.ng.model.RolAccountEntity rolsUsuaris)
            throws RuntimeException {
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
            Collection rolsPropagar = getRolsContingutsPerPropagar(rolsUsuaris.getRol());
            // I si tenim cap... els propaguem
            if (rolsPropagar != null)
                propagarRols(rolsPropagar);

            // Enviem les notificacions de l'aplicació (si estan activades)
            if (rolsUsuaris.getRol().getAplicacio() != null) {
                AplicacioEntity aplic = rolsUsuaris.getRol().getAplicacio();
                String correusNotificacio = aplic.getCorreusNotificacions();
                if (correusNotificacio != null) {
                    String correus[] = correusNotificacio.split(","); //$NON-NLS-1$
                    if (correus.length > 0) {
                        for (UserAccountEntity usu: rolsUsuaris.getAccount().getUsers()) 
                        {
	                        // Hay que notificar: Creamos la notificación
	                        String informacio = Messages.getString("RolsUsuarisEntityDaoImpl.5"); //$NON-NLS-1$
	                        // Creem l'instància de la notificació
	                        NotificacioEntity notif = getNotificacioEntityDao().newNotificacioEntity();
	                        notif.setAplicacio(aplic);
	                        notif.setDataModificacio(new Date());
	                        notif.setInformacio(informacio);
	                        notif.setRol(rolsUsuaris.getRol());
	                        notif.setUsuari(usu.getUser());
	                        getNotificacioEntityDao().create(notif);
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
            throw new SeyconException(String.format(
                    Messages.getString("RolsUsuarisEntityDaoImpl.6"), rolsUsuaris.getRol().getNom(), //$NON-NLS-1$
                    rolsUsuaris.getAccount().getName(), message));
        }
    }

    public void toRolAccount(es.caib.seycon.ng.model.RolAccountEntity sourceEntity,
            es.caib.seycon.ng.comu.RolAccount targetVO) {
        super.toRolAccount(sourceEntity, targetVO);
        toRolAccountCustom(sourceEntity, targetVO);
    }

    private void toRolAccountCustom(es.caib.seycon.ng.model.RolAccountEntity sourceEntity,
            es.caib.seycon.ng.comu.RolAccount targetVO) {
        UsuariEntity usuariEntity = null;
    	if (sourceEntity.getAccount().getType().equals (AccountType.USER))
    	{
            for (UserAccountEntity usu: sourceEntity.getAccount().getUsers()) 
            {
            	usuariEntity = usu.getUser();
            }
    	}

        String tipusDomini = sourceEntity.getTipusDomini();
        if (tipusDomini == null || tipusDomini.trim().compareTo("") == 0) { //$NON-NLS-1$
            tipusDomini = TipusDomini.SENSE_DOMINI;
        }
        if (tipusDomini.compareTo(TipusDomini.DOMINI_APLICACIO) == 0) {
            ValorDominiAplicacioEntity valorDominiEntity = sourceEntity.getValorDominiAplicacio();
            ValorDomini valorDomini = getValorDominiAplicacioEntityDao().toValorDomini(
                    valorDominiEntity);
            targetVO.setValorDomini(valorDomini);
        } else if (tipusDomini.compareTo(TipusDomini.GRUPS) == 0
                || tipusDomini.compareTo(TipusDomini.GRUPS_USUARI) == 0) {
            ValorDomini valorDomini = new ValorDomini();
            valorDomini.setDescripcio(sourceEntity.getGrup().getDescripcio());
            if (tipusDomini.compareTo(TipusDomini.GRUPS_USUARI) == 0) {
        		valorDomini.setCodiExternDomini(usuariEntity == null ? null: usuariEntity.getCodi());
                valorDomini.setNomDomini(TipusDomini.GRUPS_USUARI);
            } else {
                valorDomini.setCodiExternDomini(null);
                valorDomini.setNomDomini(TipusDomini.GRUPS);
            }
            valorDomini.setValor(sourceEntity.getGrup().getCodi());
            targetVO.setValorDomini(valorDomini);
        } else if (tipusDomini.compareTo(TipusDomini.APLICACIONS) == 0) {
            ValorDomini valorDomini = new ValorDomini();
            valorDomini.setCodiExternDomini(null);
            valorDomini.setDescripcio(sourceEntity.getAplicacioAdministrada().getNom());
            valorDomini.setNomDomini(TipusDomini.APLICACIONS);
            valorDomini.setValor(sourceEntity.getAplicacioAdministrada().getCodi());
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
        	targetVO.setHolderGroup(sourceEntity.getHolderGroup().getCodi());

        
        
        targetVO.setNomRol(sourceEntity.getRol().getNom());
        
        String nom;
        if (usuariEntity != null)
        {
	        nom = usuariEntity.getNom();
	        nom = nom != null ? nom : ""; //$NON-NLS-1$
	        String primerCognom = usuariEntity.getPrimerLlinatge();
	        primerCognom = primerCognom != null ? primerCognom : ""; //$NON-NLS-1$
	        String segonCognom = usuariEntity.getSegonLlinatge();
	        segonCognom = segonCognom != null ? segonCognom : ""; //$NON-NLS-1$
	        targetVO.setNomComplertUsuari(nom + " " + primerCognom + " " + segonCognom); //$NON-NLS-1$ //$NON-NLS-2$
	        targetVO.setCodiUsuari(usuariEntity == null? null: usuariEntity.getCodi());
        }
        else
        {
        	targetVO.setNomComplertUsuari(sourceEntity.getAccount().getDescription());
        }
        
        targetVO.setAccountName(sourceEntity.getAccount().getName());        
        targetVO.setAccountDispatcher(sourceEntity.getAccount().getDispatcher().getCodi());
        targetVO.setDescripcioRol(sourceEntity.getRol().getDescripcio());
        targetVO.setAccountId(sourceEntity.getAccount().getId());
        DispatcherEntity dispatcher = sourceEntity.getRol().getBaseDeDades();
        targetVO.setBaseDeDades(dispatcher == null ? null : dispatcher.getCodi());

        AplicacioEntity aplicacio = sourceEntity.getRol().getAplicacio();
        if (aplicacio != null) {
            targetVO.setCodiAplicacio(aplicacio.getCodi());
        }

        if (usuariEntity != null && usuariEntity.getGrupPrimari() != null) {
            targetVO.setCodiGrupUsuari(usuariEntity.getGrupPrimari().getCodi());
        }

        targetVO.setGestionableWF(sourceEntity.getRol().getGestionableWF());
        
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
    public es.caib.seycon.ng.comu.RolAccount toRolAccount(
            final es.caib.seycon.ng.model.RolAccountEntity entity) {
        RolAccount rolUsuari = super.toRolAccount(entity);
        toRolAccountCustom(entity, rolUsuari);
        return rolUsuari;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private es.caib.seycon.ng.model.RolAccountEntity loadRolAccountEntityFromRolAccount(
            es.caib.seycon.ng.comu.RolAccount rolsUsuaris) {
        RolAccountEntity rolsUsuarisEntity = null;
        if (rolsUsuaris.getId() != null) {
            rolsUsuarisEntity = load(rolsUsuaris.getId());
        }
        if (rolsUsuarisEntity == null) {
            rolsUsuarisEntity = newRolAccountEntity();
        }
        return rolsUsuarisEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.RolAccountEntityDao#rolsUsuarisToEntity(es.caib.seycon.ng.comu.RolAccount)
     */
    public es.caib.seycon.ng.model.RolAccountEntity rolAccountToEntity(
            es.caib.seycon.ng.comu.RolAccount rolsUsuaris) {
        es.caib.seycon.ng.model.RolAccountEntity entity = this
                .loadRolAccountEntityFromRolAccount(rolsUsuaris);
        this.rolAccountToEntity(rolsUsuaris, entity, true);
        return entity;
    }

    private RolEntity findRolByNomAndCodiApliacio(String nom, String codiAplicacio, String bbdd) {
        String query = "select rol " //$NON-NLS-1$
                + "from es.caib.seycon.ng.model.RolEntity rol " //$NON-NLS-1$
                + "where " //$NON-NLS-1$
                + "rol.nom = :nom and " + " rol.baseDeDades.codi = :bbdd"; //$NON-NLS-1$ //$NON-NLS-2$
        Parameter nomParameter = new Parameter("nom", nom); //$NON-NLS-1$
        Parameter bbddParameter = new Parameter("bbdd", bbdd); //$NON-NLS-1$
        Parameter[] parameters = { nomParameter, bbddParameter };
        Collection rols = getRolEntityDao().query(query, parameters);
        if (rols != null && !rols.isEmpty()) {
            return (RolEntity) rols.iterator().next();
        }
        return null;
    }

    private void rolsUsuarisToEntityCustom(es.caib.seycon.ng.comu.RolAccount sourceVO,
            es.caib.seycon.ng.model.RolAccountEntity targetEntity) {
    	AccountEntity account = null;
    	AccountEntityDao accDao = getAccountEntityDao();
    	
        if (targetEntity.getStartDate() != null)
        	targetEntity.setStartDate(removeSeconds(targetEntity.getStartDate()));
        if (targetEntity.getEndDate() != null)
        	targetEntity.setEndDate(removeSeconds(targetEntity.getEndDate()));

    	if (sourceVO.getAccountId() != null)
    		account = accDao.load (sourceVO.getAccountId().longValue());
    	else if (sourceVO.getAccountName() != null && sourceVO.getAccountDispatcher() != null)
    	{
    		account = accDao.findByNameAndDispatcher(sourceVO.getAccountName(), sourceVO.getBaseDeDades());
    	}
    	else if (sourceVO.getCodiUsuari() != null)
    	{
   			List<AccountEntity> accounts = accDao.findByUsuariAndDispatcher (sourceVO.getCodiUsuari(), sourceVO.getBaseDeDades());
   			if (accounts.size() > 1)
   				throw new IllegalArgumentException(
   						String.format (Messages.getString("RolAccountEntityDaoImpl.MoreThanOneUserAccount"), //$NON-NLS-1$
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
        RolEntity rol = null;
        if (nomRolDomini != null) {
            rol = findRolByNomAndCodiApliacio(nomRolDomini, codiAplicacioRol, codiBBDD);
        }
        if (rol == null) {
            throw new SeyconException(String.format(Messages.getString("RolsUsuarisEntityDaoImpl.8"),nomRolDomini)); //$NON-NLS-1$
        }

        targetEntity.setRol(rol);

        ValorDomini valorDomini = sourceVO.getValorDomini();
        if (valorDomini == null) {
            valorDomini = new ValorDomini();
            valorDomini.setCodiExternDomini(null);
            valorDomini.setNomDomini(TipusDomini.SENSE_DOMINI);
            valorDomini.setValor(TipusDomini.SENSE_DOMINI);
        }
        String nomDomini = rol.getTipusDomini();
        if (nomDomini.compareTo(TipusDomini.GRUPS) == 0
                || nomDomini.compareTo(TipusDomini.GRUPS_USUARI) == 0
                || nomDomini.compareTo(TipusDomini.APLICACIONS) == 0
                || nomDomini.compareTo(TipusDomini.SENSE_DOMINI) == 0) {
            if (nomDomini.compareTo(TipusDomini.GRUPS) == 0
                    || nomDomini.compareTo(TipusDomini.GRUPS_USUARI) == 0) {
                String codiGrup = valorDomini.getValor();
                GrupEntity grup = null;
                if (codiGrup != null && codiGrup.trim().compareTo("") != 0) { //$NON-NLS-1$
                    grup = getGrupEntityDao().findByCodi(codiGrup);
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
                targetEntity.setGrup(grup);
                targetEntity.setValorDominiAplicacio(null);
                targetEntity.setTipusDomini(nomDomini);
                targetEntity.setAplicacioAdministrada(null);
            } else if (nomDomini.compareTo(TipusDomini.APLICACIONS) == 0) {
                String valor = valorDomini.getValor();
                AplicacioEntity aplicacioEntity = getAplicacioEntityDao().findByCodi(valor);
                if (aplicacioEntity == null) {
                    throw new SeyconException(String.format(Messages.getString("RolsUsuarisEntityDaoImpl.13"),valor)); //"Aplicació amb codi '" + valor + "' no trobada."); //$NON-NLS-1$
                }
                targetEntity.setAplicacioAdministrada(aplicacioEntity);
                targetEntity.setGrup(null);
                targetEntity.setValorDominiAplicacio(null);
                targetEntity.setTipusDomini(TipusDomini.APLICACIONS);
            } else if (nomDomini.compareTo(TipusDomini.SENSE_DOMINI) == 0) {
                targetEntity.setAplicacioAdministrada(null);
                targetEntity.setGrup(null);
                targetEntity.setValorDominiAplicacio(null);
                targetEntity.setTipusDomini(TipusDomini.SENSE_DOMINI);
            }
        } else if (rol.getDominiAplicacio() != null){
            /*
             * Domini d'aplicació
             */
            nomDomini = rol.getDominiAplicacio().getNom();
            String codiAplicacio = rol.getAplicacio().getCodi();
            String valor = valorDomini.getValor();
            ValorDominiAplicacioEntity valorDominiAplicacioEntity = findValorDominiByNomDominiAndCodiAplicacioDominiAndValor(
                    nomDomini, codiAplicacio, valor);
            if (valorDominiAplicacioEntity != null) {
                targetEntity.setValorDominiAplicacio(valorDominiAplicacioEntity);
                targetEntity.setTipusDomini(TipusDomini.DOMINI_APLICACIO);
                targetEntity.setGrup(null);
                targetEntity.setAplicacioAdministrada(null);
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
        	GrupEntity grup = getGrupEntityDao().findByCodi(sourceVO.getHolderGroup());
        	if (grup == null)
        		throw new SeyconException (String.format("Unknown group %s", sourceVO.getHolderGroup()));
        	targetEntity.setHolderGroup(grup);
        }
    }

    private ValorDominiAplicacioEntity findValorDominiByNomDominiAndCodiAplicacioDominiAndValor(
            String nomDomini, String codiAplicacio, String valor) {
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

        Collection valorsDomini = getValorDominiAplicacioEntityDao().query(query, parametres);
        if (valorsDomini != null) {
            Iterator valorsDominiIterator = valorsDomini.iterator();
            if (valorsDominiIterator != null) {
                if (valorsDominiIterator.hasNext()) {
                    ValorDominiAplicacioEntity valorDominiEntity = (ValorDominiAplicacioEntity) valorsDominiIterator
                            .next();
                    return valorDominiEntity;
                }
            }
        }
        return null;
    }

    private RolAccountEntity findExisteixRolUsuari(RolAccountEntity rolUsuari) {
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
        Parameter p_rolID = new Parameter("rolID", rolUsuari.getRol().getId()); //$NON-NLS-1$
        Parameter p_tipusDomini = new Parameter("tipusDomini", rolUsuari.getTipusDomini()); //$NON-NLS-1$
        // Estos pueden ser nulos
        String grupCodi = rolUsuari.getGrup() != null ? rolUsuari.getGrup().getCodi() : null;
        Parameter p_grupCodi = new Parameter("grupCodi", grupCodi); //$NON-NLS-1$
        String aplicacioAdministrada = rolUsuari.getAplicacioAdministrada() != null ? rolUsuari
                .getAplicacioAdministrada().getCodi() : null;
        Parameter p_codiAplicacio = new Parameter("codiAplicacio", aplicacioAdministrada); //$NON-NLS-1$
        String valorDomini = rolUsuari.getValorDominiAplicacio() != null ? rolUsuari
                .getValorDominiAplicacio().getValor() : null;
        Parameter p_valorDomini = new Parameter("valorDomini", valorDomini); //$NON-NLS-1$

        Collection rolsUsu = find(query, new Parameter[] { p_usuID, p_rolID, p_tipusDomini,
                p_grupCodi, p_codiAplicacio, p_valorDomini });

        if (rolsUsu != null && rolsUsu.size() != 0) {
            return (RolAccountEntity) ((java.util.List) rolsUsu).get(0);
        }

        return null;
    }

    private boolean grupPertanyAUsuari(String codiGrup, String codiUsuari) {
        Collection grups = getUsuariEntityDao().findGrupsByCodi(codiUsuari);
        Iterator iterator = grups.iterator();
        while (iterator.hasNext()) {
            GrupEntity grupEntity = (GrupEntity) iterator.next();
            if (grupEntity.getCodi().compareTo(codiGrup) == 0) {
                return true;
            }
        }
        UsuariEntity usuari = getUsuariEntityDao().findByCodi(codiUsuari);
        GrupEntity grupPrimariEntity = usuari.getGrupPrimari();
        if (grupPrimariEntity != null) {
            return grupPrimariEntity.getCodi().compareTo(codiGrup) == 0;
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
    public void rolAccountToEntity(es.caib.seycon.ng.comu.RolAccount sourceVO,
            es.caib.seycon.ng.model.RolAccountEntity targetEntity, boolean copyIfNull) {
        super.rolAccountToEntity(sourceVO, targetEntity, copyIfNull);

        rolsUsuarisToEntityCustom(sourceVO, targetEntity);
    }

    /**
     * @see es.caib.seycon.ng.model.UsuariEntityDao#find(int, java.lang.String,
     *      es.caib.seycon.ng.model.Parameter[])
     */
    public List<RolAccountEntity> find(final java.lang.String queryString,
            final es.caib.seycon.ng.model.Parameter[] parameters) {
        try {
            java.util.List results = new QueryBuilder().query(this, queryString, parameters);
            return results;
        } catch (org.hibernate.HibernateException ex) {
            throw super.convertHibernateAccessException(ex);
        }
    }

    private es.caib.seycon.ng.model.RolAccountEntity loadRolAccountEntityFromAdministracioAplicacio(
            es.caib.seycon.ng.comu.AdministracioAplicacio administracioAplicacio) {
        RolAccountEntity rolsUsuarisEntity = null;
        if (administracioAplicacio.getId() != null) {
            rolsUsuarisEntity = load(administracioAplicacio.getId());
        }
        if (rolsUsuarisEntity == null) {
            rolsUsuarisEntity = newRolAccountEntity();
        }
        return rolsUsuarisEntity;
    }

    public RolAccountEntity administracioAplicacioToEntity(
            AdministracioAplicacio administracioAplicacio) {
        es.caib.seycon.ng.model.RolAccountEntity entity = this
                .loadRolAccountEntityFromAdministracioAplicacio(administracioAplicacio);
        this.administracioAplicacioToEntity(administracioAplicacio, entity);
        return entity;
    }

    public void toAdministracioAplicacio(RolAccountEntity source, AdministracioAplicacio target) {
        super.toAdministracioAplicacio(source, target);
        target.setCodiAplicacio(source.getAplicacioAdministrada().getCodi());
        UsuariEntity usuariEntity = source.getAccount().getUsers().iterator().next().getUser();
        target.setCodiUsuari(usuariEntity.getCodi());
        String nom = usuariEntity.getNom();
        nom = nom != null ? nom : ""; //$NON-NLS-1$
        String primerCognom = usuariEntity.getPrimerLlinatge();
        primerCognom = primerCognom != null ? primerCognom : ""; //$NON-NLS-1$
        String segonCognom = usuariEntity.getSegonLlinatge();
        segonCognom = segonCognom != null ? segonCognom : ""; //$NON-NLS-1$
        target.setNomComplertUsuari(nom + " " + primerCognom + " " + segonCognom); //$NON-NLS-1$ //$NON-NLS-2$
        target.setNomRol(source.getRol().getNom());
        target.setCodiAplicacioRol(source.getRol().getAplicacio().getCodi());
        target.setCodiBaseDeDadesRol(source.getRol().getBaseDeDades().getCodi());
    }

    public AdministracioAplicacio toAdministracioAplicacio(final RolAccountEntity entity) {
        return super.toAdministracioAplicacio(entity);
    }

    public void administracioAplicacioToEntity(AdministracioAplicacio administracioAplicacio,
            RolAccountEntity targetEntity) {
        String aplicacioAdministrada = administracioAplicacio.getCodiAplicacio();
        AplicacioEntity aplicacioEntity = getAplicacioEntityDao().findByCodi(aplicacioAdministrada);
        if (aplicacioEntity == null) {
            throw new SeyconException(String.format(Messages.getString("RolsUsuarisEntityDaoImpl.15"), aplicacioAdministrada)); //$NON-NLS-1$
        }
        targetEntity.setAplicacioAdministrada(aplicacioEntity);
        targetEntity.setGrup(null);
        targetEntity.setValorDominiAplicacio(null);
        targetEntity.setTipusDomini(TipusDomini.APLICACIONS);

        RolEntity rolEntity = getRolEntityDao().findByNomRolAndCodiAplicacioAndCodiDispatcher(
                administracioAplicacio.getNomRol(), administracioAplicacio.getCodiAplicacioRol(),
                administracioAplicacio.getCodiBaseDeDadesRol());
        if (rolEntity == null) {
            throw new SeyconException(String.format(Messages.getString("RolsUsuarisEntityDaoImpl.16"), administracioAplicacio.getNomRol())); //$NON-NLS-1$
        }
        targetEntity.setRol(rolEntity);

        List<AccountEntity> accs = getAccountEntityDao().findByUsuariAndDispatcher(administracioAplicacio.getCodiUsuari(), 
        		administracioAplicacio.getCodiBaseDeDadesRol());
        if (accs.size() != 1) {
            throw new SeyconException(String.format(Messages.getString("RolsUsuarisEntityDaoImpl.17"), administracioAplicacio.getCodiUsuari())); //$NON-NLS-1$
        }
        targetEntity.setAccount(accs.get(0));
    }

    public RolAccountEntity contenidorRolToEntity(ContenidorRol contenidorRol) {
        // TODO Auto-generated method stub
        return null;
    }

    public ContenidorRol toContenidorRol(RolAccountEntity entity) {
        ContenidorRol contenidorRol = super.toContenidorRol(entity); // Pasamos
                                                                     // el id
        contenidorRol.setTipus(TipusContenidorRol.ROL_USUARI);
        // Información específica:
        RolEntity rol = entity.getRol();
        ValorDomini valorDomini = null;

        String tipusDomini = rol.getTipusDomini();
        if (tipusDomini == null || tipusDomini.trim().compareTo("") == 0) { //$NON-NLS-1$
            tipusDomini = TipusDomini.SENSE_DOMINI;
        }
        if (tipusDomini.compareTo(TipusDomini.DOMINI_APLICACIO) == 0) {
            ValorDominiAplicacioEntity valorDominiEntity = entity.getValorDominiAplicacio();
            valorDomini = getValorDominiAplicacioEntityDao().toValorDomini(valorDominiEntity);
        } else if (tipusDomini.compareTo(TipusDomini.GRUPS) == 0
                || tipusDomini.compareTo(TipusDomini.GRUPS_USUARI) == 0) {
            valorDomini = new ValorDomini();
            valorDomini.setDescripcio(entity.getGrup().getDescripcio());
            if (tipusDomini.compareTo(TipusDomini.GRUPS_USUARI) == 0) {
                valorDomini.setCodiExternDomini(entity.getAccount().getUsers().iterator().next().getUser().getCodi());
                valorDomini.setNomDomini(TipusDomini.GRUPS_USUARI);
            } else {
                valorDomini.setCodiExternDomini(null);
                valorDomini.setNomDomini(TipusDomini.GRUPS);
            }
            valorDomini.setValor(entity.getGrup().getCodi());
        } else if (tipusDomini.compareTo(TipusDomini.APLICACIONS) == 0) {
            valorDomini = new ValorDomini();
            valorDomini.setCodiExternDomini(null);
            valorDomini.setDescripcio(entity.getAplicacioAdministrada().getNom());
            valorDomini.setNomDomini(TipusDomini.APLICACIONS);
            valorDomini.setValor(entity.getAplicacioAdministrada().getCodi());
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
        contenidorRol.setInfoContenidor(rol.getNom() + "@" + rol.getBaseDeDades().getCodi() + ">" //$NON-NLS-1$ //$NON-NLS-2$
                + rol.getAplicacio().getCodi() + sValorDomini);

        return contenidorRol;
    }

    /**
     * Obtenim els rols continguts en el rol (per propagar-los)
     * 
     * @param rol
     * @return rols pares
     */
    private Collection getRolsContingutsPerPropagar(RolEntity rol) {
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
        RolEntity rolActual = null;
        while ((rolActual = (RolEntity) rolsAnalitzar.poll()) != null) {
            Collection socContenidor = rolActual.getRolAssociacioRolSocContenidor();

            if (socContenidor != null)
                for (Iterator it = socContenidor.iterator(); it.hasNext();) {
                    RolAssociacioRolEntity associacio = (RolAssociacioRolEntity) it.next();
                    // Obtenemos el rol contenido
                    RolEntity rolContingut = associacio.getRolContingut();
                    // Guardamos el rol para propagarlo
                    rolsPropagar.add(rolContingut);
                    // Añadimos el rol contenido para analizar si a su vez es
                    // contenido en otro (atorgat)
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
                    RolEntity role = (RolEntity) obj;
                    // insert into sc_tasque
                    // (tas_id,tas_role,tas_bd,tas_status,tas_data,tas_transa)
                    // values
                    // (sc_tas_seq.nextval,codi_role,codi_bd,'P',sysdate,'UpdateRole');
                    Tasca updateRole = new Tasca();
                    updateRole.setTransa("UpdateRole");// Actualització del rol //$NON-NLS-1$
                    updateRole.setDataTasca(Calendar.getInstance());
                    updateRole.setStatus("P");// Posem com a pendent //$NON-NLS-1$
                    updateRole.setRole(role.getNom());
                    updateRole.setBd(role.getBaseDeDades().getCodi());
                    TasqueEntity tasca = getTasqueEntityDao().tascaToEntity(updateRole);
                    getTasqueEntityDao().create(tasca);
                }
            }
    }

    public void create(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof RolAccountEntity) {
                    RolAccountEntity entity = (RolAccountEntity) obj;
                    this.create(entity); // cridem al mètode 1 per 1
                }
            }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof RolAccountEntity) {
                    RolAccountEntity entity = (RolAccountEntity) obj;
                    this.update(entity);// cridem al mètode 1 per 1
                }
            }
    }

    public void remove(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof RolAccountEntity) {
                    RolAccountEntity entity = (RolAccountEntity) obj;
                    this.remove(entity);// cridem al mètode 1 per 1
                }
            }
    }

    public RolAccountEntity rolGrantToEntity(RolGrant rolGrant) {
        return load(rolGrant.getId());
    }

    @Override
    public void toRolGrant(RolAccountEntity source, RolGrant target) {
        String tipus = source.getRol().getTipusDomini();
        if (TipusDomini.APLICACIONS.equals(tipus) && source.getAplicacioAdministrada() != null) {
            target.setDomainValue(source.getAplicacioAdministrada().getCodi());
            target.setHasDomain(true);
        } else if ((TipusDomini.GRUPS.equals(tipus) || TipusDomini.GRUPS_USUARI.equals(tipus))
                && source.getGrup() != null) {
            target.setDomainValue(source.getGrup().getCodi());
            target.setHasDomain(true);
        } else if (TipusDomini.DOMINI_APLICACIO.equals(tipus)
                && source.getValorDominiAplicacio() != null) {
            target.setDomainValue(source.getValorDominiAplicacio().getValor());
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
        target.setOwnerDispatcher(source.getAccount().getDispatcher().getCodi());
        target.setId(source.getId());
        target.setIdRol(source.getRol().getId());
        target.setRolName(source.getRol().getNom());
        target.setDispatcher(source.getRol().getBaseDeDades().getCodi());
		for (UserAccountEntity ua: source.getAccount().getUsers())
		{
			target.setUser (ua.getUser().getCodi());
		}
		if (source.getHolderGroup() == null)
			target.setHolderGroup(null);
		else
			target.setHolderGroup(source.getHolderGroup().getCodi());
    }

}
