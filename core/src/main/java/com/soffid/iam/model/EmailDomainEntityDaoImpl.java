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
import com.soffid.iam.utils.ExceptionTranslator;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.*;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * @see es.caib.seycon.ng.model.DominiCorreuEntity
 */
public class EmailDomainEntityDaoImpl
    extends com.soffid.iam.model.EmailDomainEntityDaoBase
{
	private void auditarDominiCorreu(String accio, String codiDomini) {
		String codiUsuari = Security.getCurrentAccount();
		Audit auditoria = new Audit();
		auditoria.setAction(accio);
		auditoria.setMailDomain(codiDomini);
		auditoria.setAuthor(codiUsuari);
		auditoria.setObject("SC_DOMCOR"); //$NON-NLS-1$
		AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
		getAuditEntityDao().create(auditoriaEntity);
	}
	
	
	public void create(com.soffid.iam.model.EmailDomainEntity dominiCorreu) throws RuntimeException {
		try {
			super.create(dominiCorreu);
			getSession(false).flush();
			auditarDominiCorreu("C", dominiCorreu.getName()); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("EmailDomainEntityDaoImpl.0"), dominiCorreu.getName(), message));
		}
	}
	
	public void remove(com.soffid.iam.model.EmailDomainEntity dominiCorreu) throws RuntimeException {
		try {
			String codiDominiCorreu = dominiCorreu.getName();
			super.remove(dominiCorreu);
			getSession(false).flush();
			auditarDominiCorreu("D", codiDominiCorreu);			 //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("EmailDomainEntityDaoImpl.6"), dominiCorreu.getName(), message));
		}
	}
	
	public void update(com.soffid.iam.model.EmailDomainEntity dominiCorreu) throws RuntimeException {
		try {
			super.update(dominiCorreu);
			getSession(false).flush();
			auditarDominiCorreu("U", dominiCorreu.getName()); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("EmailDomainEntityDaoImpl.8"), dominiCorreu.getName(), message));
		}
	}
	
    public void toMailDomain(com.soffid.iam.model.EmailDomainEntity sourceEntity, com.soffid.iam.api.MailDomain targetVO) {
        // @todo verify behavior of toDominiCorreu
        super.toMailDomain(sourceEntity, targetVO);
        String obsolet = sourceEntity.getObsolete();
        if(obsolet != null){
        	targetVO.setObsolete(new Boolean(obsolet.compareTo("S") == 0)); //$NON-NLS-1$
        }else{
        	targetVO.setObsolete(new Boolean(false));
        }
    }


    /**
     * @see es.caib.seycon.ng.model.DominiCorreuEntityDao#toDominiCorreu(es.caib.seycon.ng.model.DominiCorreuEntity)
     */
    public com.soffid.iam.api.MailDomain toMailDomain(final com.soffid.iam.model.EmailDomainEntity entity) {
        // @todo verify behavior of toDominiCorreu
        return super.toMailDomain(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.EmailDomainEntity loadDominiCorreuEntityFromDominiCorreu(com.soffid.iam.api.MailDomain dominiCorreu) {
    	com.soffid.iam.model.EmailDomainEntity dominiCorreuEntity = null;
    	if(dominiCorreu.getId() != null){
    		dominiCorreuEntity = load(dominiCorreu.getId());
    	}
        if (dominiCorreuEntity == null)
        {
            dominiCorreuEntity = newEmailDomainEntity();
        }
        return dominiCorreuEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.DominiCorreuEntityDao#dominiCorreuToEntity(es.caib.seycon.ng.DominiCorreu)
     */
    public com.soffid.iam.model.EmailDomainEntity mailDomainToEntity(com.soffid.iam.api.MailDomain dominiCorreu) {
        // @todo verify behavior of dominiCorreuToEntity
        com.soffid.iam.model.EmailDomainEntity entity = this.loadDominiCorreuEntityFromDominiCorreu(dominiCorreu);
        this.mailDomainToEntity(dominiCorreu, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.DominiCorreuEntityDao#dominiCorreuToEntity(es.caib.seycon.ng.DominiCorreu, es.caib.seycon.ng.model.DominiCorreuEntity)
     */
    public void mailDomainToEntity(com.soffid.iam.api.MailDomain sourceVO, com.soffid.iam.model.EmailDomainEntity targetEntity, boolean copyIfNull) {
        // @todo verify behavior of dominiCorreuToEntity
        super.mailDomainToEntity(sourceVO, targetEntity, copyIfNull);
        targetEntity.setObsolete(sourceVO.getObsolete().booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof EmailDomainEntity) {
                EmailDomainEntity entity = (EmailDomainEntity) obj;
                this.create(entity);
            }
        }
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof EmailDomainEntity) {
                EmailDomainEntity entity = (EmailDomainEntity) obj;
                this.update(entity);
            }
        }
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof EmailDomainEntity) {
                EmailDomainEntity entity = (EmailDomainEntity) obj;
                this.remove(entity);
            }
        }
	}    

}