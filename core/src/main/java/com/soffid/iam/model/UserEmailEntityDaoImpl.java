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
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.EmailDomainEntity;
import com.soffid.iam.model.EmailListEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserEmailEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.utils.ExceptionTranslator;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.hibernate.Hibernate;
import org.jbpm.db.hibernate.HibernateHelper;

/**
 * @see es.caib.seycon.ng.model.LlistaCorreuUsuariEntity
 */
public class UserEmailEntityDaoImpl extends
        com.soffid.iam.model.UserEmailEntityDaoBase {

    /**
     * Creem auditoria de les llistes de correu d'usuari (no existeix al entity)
     * 
     * @param accio
     * @param nomLlistaDeCorreu
     * @param codiUsuariLlista
     * @param dominiLlistaCorreu
     */
    private void auditarLlistaDeCorreuUsuari(String accio, UserEmailEntity llistaCorreuUsuari) {
        Audit auditoria = new Audit();
        auditoria.setAction(accio);
        EmailListEntity llista = llistaCorreuUsuari.getMailList();
        if (llista != null) {
            auditoria.setMailList(llista.getName());
            EmailDomainEntity domini = llista.getDomain();
            if (domini != null)
                auditoria.setMailDomain(domini.getName());
        }
        if (llistaCorreuUsuari.getUser() != null)
            auditoria.setUser(llistaCorreuUsuari.getUser().getUserName());
        auditoria.setAuthor(Security.getCurrentAccount()); //$NON-NLS-1$
        auditoria.setObject("SC_USULCO"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

    public void create(com.soffid.iam.model.UserEmailEntity llistaCorreuUsuari) throws RuntimeException {
        try {
            EmailDomainEntity dominiCorreu = llistaCorreuUsuari.getMailList().getDomain();
            if (dominiCorreu != null) {
                if (dominiCorreu.getObsolete().compareTo("S") == 0) { //$NON-NLS-1$
                    throw new SeyconException(
                            Messages.getString("UserEmailEntityDaoImpl.0")); //$NON-NLS-1$
                }
            }
            super.create(llistaCorreuUsuari);
            if (Hibernate.isInitialized(llistaCorreuUsuari.getMailList().getUserMailLists()))
            		llistaCorreuUsuari.getMailList().getUserMailLists().add(llistaCorreuUsuari);
            
            if (Hibernate.isInitialized(llistaCorreuUsuari.getUser().getUserMailList()))
            		llistaCorreuUsuari.getUser().getUserMailList().add(llistaCorreuUsuari);
            
            createTask(llistaCorreuUsuari);
            getSession(false).flush();

            // Creem auditoria
            auditarLlistaDeCorreuUsuari("C", llistaCorreuUsuari); //$NON-NLS-1$

        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("UserEmailEntityDaoImpl.1"), llistaCorreuUsuari.getMailList().getName(), llistaCorreuUsuari.getUser().getUserName(), message), e);
        }
    }

    private void createTask(com.soffid.iam.model.UserEmailEntity llistaCorreuUsuari) {
    	try {
    		getEmailListEntityDao().generateUpdateTasks(llistaCorreuUsuari.getMailList());
		} catch (InternalErrorException e) {
			throw new SeyconException("Error generating task", e);
		}
    }

    public void update(UserEmailEntity llistaCorreuUsuari) {
        super.update(llistaCorreuUsuari);
        createTask(llistaCorreuUsuari);
        getSession(false).flush();
        // Creem auditoria
        auditarLlistaDeCorreuUsuari("U", llistaCorreuUsuari); //$NON-NLS-1$

    }

    public void remove(com.soffid.iam.model.UserEmailEntity llistaCorreuUsuari) throws RuntimeException {
        try {
            if (Hibernate.isInitialized(llistaCorreuUsuari.getMailList().getUserMailLists()))
        		llistaCorreuUsuari.getMailList().getUserMailLists().remove(llistaCorreuUsuari);
        
            if (Hibernate.isInitialized(llistaCorreuUsuari.getUser().getUserMailList()))
        		llistaCorreuUsuari.getUser().getUserMailList().remove(llistaCorreuUsuari);
            super.remove(llistaCorreuUsuari);
            createTask(llistaCorreuUsuari);
            getSession(false).flush();
            // Creem auditoria
            auditarLlistaDeCorreuUsuari("D", llistaCorreuUsuari); //$NON-NLS-1$

        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("UserEmailEntityDaoImpl.2"), 
					llistaCorreuUsuari.getMailList().getName(), llistaCorreuUsuari.getUser().getUserName(), message),
					e);
        }
    }

    public void toUserMailList(com.soffid.iam.model.UserEmailEntity source, com.soffid.iam.api.UserMailList target) {
        super.toUserMailList(source, target);
        toLlistaCorreuUsuariCustom(source, target);
    }

    public void toLlistaCorreuUsuariCustom(com.soffid.iam.model.UserEmailEntity source, com.soffid.iam.api.UserMailList target) {
        EmailListEntity llistaCorreu = source.getMailList();
        target.setMailListName(llistaCorreu.getName());
        EmailDomainEntity dominiCorreu = llistaCorreu.getDomain();
        if (dominiCorreu != null) {
            target.setDomainCode(dominiCorreu.getName());
        }
        target.setMailListDescription(llistaCorreu.getDescription());
        UserEntity usuari = source.getUser();
        target.setUserCode(usuari.getUserName());
        String nomComplert = usuari.getFirstName() != null ? usuari.getFirstName() : ""; //$NON-NLS-1$
        nomComplert += usuari.getLastName() != null ? " " + usuari.getLastName() : ""; //$NON-NLS-1$
        nomComplert += usuari.getMiddleName() != null ? " " + usuari.getMiddleName() : ""; //$NON-NLS-1$
        target.setFullName(nomComplert);
    }

    /**
     * @see es.caib.seycon.ng.model.LlistaCorreuUsuariEntityDao#toLlistaCorreuUsuari(es.caib.seycon.ng.model.LlistaCorreuUsuariEntity)
     */
    public com.soffid.iam.api.UserMailList toUserMailList(final com.soffid.iam.model.UserEmailEntity entity) {
        return super.toUserMailList(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private com.soffid.iam.model.UserEmailEntity loadLlistaCorreuUsuariEntityFromLlistaCorreuUsuari(com.soffid.iam.api.UserMailList llistaCorreuUsuari) {
        UserEmailEntity llistaCorreuUsuariEntity = null;
        if (llistaCorreuUsuari.getId() != null) {
            llistaCorreuUsuariEntity = load(llistaCorreuUsuari.getId());
        }
        if (llistaCorreuUsuariEntity == null) {
            llistaCorreuUsuariEntity = newUserEmailEntity();
        }
        return llistaCorreuUsuariEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.LlistaCorreuUsuariEntityDao#llistaCorreuUsuariToEntity(es.caib.seycon.ng.comu.LlistaCorreuUsuari)
     */
    public com.soffid.iam.model.UserEmailEntity userMailListToEntity(com.soffid.iam.api.UserMailList llistaCorreuUsuari) {
        com.soffid.iam.model.UserEmailEntity entity = this.loadLlistaCorreuUsuariEntityFromLlistaCorreuUsuari(llistaCorreuUsuari);
        this.userMailListToEntity(llistaCorreuUsuari, entity, true);
        return entity;
    }

    public void llistaCorreuUsuariToEntityCustom(com.soffid.iam.api.UserMailList source, com.soffid.iam.model.UserEmailEntity target) {
        String codiUsuari = source.getUserCode();
        UserEntity usuari = getUserEntityDao().findByUserName(codiUsuari);
        if (usuari != null) {
            target.setUser(usuari);
        } else {
			throw new SeyconException(String.format(Messages.getString("UserEmailEntityDaoImpl.3"), codiUsuari)); //$NON-NLS-1$
        }

        String nomLlistaCorreu = source.getMailListName();
        String codiDomini = source.getDomainCode();
        EmailListEntity llistaCorreu = getEmailListEntityDao().findByNameAndDomain(nomLlistaCorreu, codiDomini);
        if (llistaCorreu != null) {
            target.setMailList(llistaCorreu);
        } else {
			throw new SeyconException(String.format(Messages.getString("UserEmailEntityDaoImpl.4") //$NON-NLS-1$
					+ (codiDomini == null ? "" : Messages.getString("UserEmailEntityDaoImpl.5")) + Messages.getString("UserEmailEntityDaoImpl.6"),  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					nomLlistaCorreu, 
					codiDomini));
        }
    }

    /**
     * @see es.caib.seycon.ng.model.LlistaCorreuUsuariEntityDao#llistaCorreuUsuariToEntity(es.caib.seycon.ng.comu.LlistaCorreuUsuari,
     *      es.caib.seycon.ng.model.LlistaCorreuUsuariEntity)
     */
    public void userMailListToEntity(com.soffid.iam.api.UserMailList source, com.soffid.iam.model.UserEmailEntity target, boolean copyIfNull) {
        super.userMailListToEntity(source, target, copyIfNull);
        llistaCorreuUsuariToEntityCustom(source, target);
    }

    public void create(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof UserEmailEntity) {
                UserEmailEntity entity = (UserEmailEntity) obj;
                this.create(entity);
            }
        }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof UserEmailEntity) {
                UserEmailEntity entity = (UserEmailEntity) obj;
                this.update(entity);
            }
        }
    }

    public void remove(Collection entities) {
        if (entities != null)
        {
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
	            Object obj = it.next();
	            if (obj instanceof UserEmailEntity) {
	                UserEmailEntity entity = (UserEmailEntity) obj;
	                this.remove(entity);
	            }
            }
        }
    }

}
