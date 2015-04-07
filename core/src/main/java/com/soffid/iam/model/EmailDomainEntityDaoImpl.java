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

import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.EmailDomainEntity;
import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;
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
		Auditoria auditoria = new Auditoria();
		auditoria.setAccio(accio);
		auditoria.setDominiCorreu(codiDomini);
		auditoria.setAutor(codiUsuari);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
		auditoria.setData(dateFormat.format(GregorianCalendar.getInstance()
				.getTime()));
		auditoria.setObjecte("SC_DOMCOR"); //$NON-NLS-1$
		AuditEntity auditoriaEntity = getAuditEntityDao().auditoriaToEntity(auditoria);
		getAuditEntityDao().create(auditoriaEntity);
	}
	
	
	public void create(com.soffid.iam.model.EmailDomainEntity dominiCorreu) throws RuntimeException {
		try {
			super.create(dominiCorreu);
			getSession(false).flush();
			auditarDominiCorreu("C", dominiCorreu.getCode()); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("EmailDomainEntityDaoImpl.0"), dominiCorreu.getCode(), message));
		}
	}
	
	public void remove(com.soffid.iam.model.EmailDomainEntity dominiCorreu) throws RuntimeException {
		try {
			String codiDominiCorreu = dominiCorreu.getCode();
			super.remove(dominiCorreu);
			getSession(false).flush();
			auditarDominiCorreu("D", codiDominiCorreu);			 //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("EmailDomainEntityDaoImpl.6"), dominiCorreu.getCode(), message));
		}
	}
	
	public void update(com.soffid.iam.model.EmailDomainEntity dominiCorreu) throws RuntimeException {
		try {
			super.update(dominiCorreu);
			getSession(false).flush();
			auditarDominiCorreu("U", dominiCorreu.getCode()); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("EmailDomainEntityDaoImpl.8"), dominiCorreu.getCode(), message));
		}
	}
	
    public void toDominiCorreu(com.soffid.iam.model.EmailDomainEntity sourceEntity, es.caib.seycon.ng.comu.DominiCorreu targetVO) {
        // @todo verify behavior of toDominiCorreu
        super.toDominiCorreu(sourceEntity, targetVO);
        String obsolet = sourceEntity.getObsolete();
        if(obsolet != null){
        	targetVO.setObsolet(new Boolean(obsolet.compareTo("S") == 0)); //$NON-NLS-1$
        }else{
        	targetVO.setObsolet(new Boolean(false));
        }
    }


    /**
     * @see es.caib.seycon.ng.model.DominiCorreuEntityDao#toDominiCorreu(es.caib.seycon.ng.model.DominiCorreuEntity)
     */
    public es.caib.seycon.ng.comu.DominiCorreu toDominiCorreu(final com.soffid.iam.model.EmailDomainEntity entity) {
        // @todo verify behavior of toDominiCorreu
        return super.toDominiCorreu(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.EmailDomainEntity loadDominiCorreuEntityFromDominiCorreu(es.caib.seycon.ng.comu.DominiCorreu dominiCorreu) {
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
    public com.soffid.iam.model.EmailDomainEntity dominiCorreuToEntity(es.caib.seycon.ng.comu.DominiCorreu dominiCorreu) {
        // @todo verify behavior of dominiCorreuToEntity
        com.soffid.iam.model.EmailDomainEntity entity = this.loadDominiCorreuEntityFromDominiCorreu(dominiCorreu);
        this.dominiCorreuToEntity(dominiCorreu, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.DominiCorreuEntityDao#dominiCorreuToEntity(es.caib.seycon.ng.DominiCorreu, es.caib.seycon.ng.model.DominiCorreuEntity)
     */
    public void dominiCorreuToEntity(es.caib.seycon.ng.comu.DominiCorreu sourceVO, com.soffid.iam.model.EmailDomainEntity targetEntity, boolean copyIfNull) {
        // @todo verify behavior of dominiCorreuToEntity
        super.dominiCorreuToEntity(sourceVO, targetEntity, copyIfNull);
        targetEntity.setObsolete(sourceVO.getObsolet().booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
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