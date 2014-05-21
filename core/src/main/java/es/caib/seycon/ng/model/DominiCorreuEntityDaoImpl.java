// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;
/**
 * @see es.caib.seycon.ng.model.DominiCorreuEntity
 */
public class DominiCorreuEntityDaoImpl
    extends es.caib.seycon.ng.model.DominiCorreuEntityDaoBase
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
		AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao()
				.auditoriaToEntity(auditoria);
		getAuditoriaEntityDao().create(auditoriaEntity);
	}
	
	
	public void create(
			es.caib.seycon.ng.model.DominiCorreuEntity dominiCorreu)
			throws RuntimeException {
		try {
			super.create(dominiCorreu);
			getSession(false).flush();
			auditarDominiCorreu("C", dominiCorreu.getCodi()); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("DominiCorreuEntityDaoImpl.0"),  //$NON-NLS-1$
					dominiCorreu.getCodi(),
					message));
		}
	}
	
	public void remove(
			es.caib.seycon.ng.model.DominiCorreuEntity dominiCorreu)
			throws RuntimeException {
		try {
			String codiDominiCorreu = dominiCorreu.getCodi();
			super.remove(dominiCorreu);
			getSession(false).flush();
			auditarDominiCorreu("D", codiDominiCorreu);			 //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("DominiCorreuEntityDaoImpl.6"),  //$NON-NLS-1$
					dominiCorreu.getCodi(),
					message));
		}
	}
	
	public void update(
			es.caib.seycon.ng.model.DominiCorreuEntity dominiCorreu)
			throws RuntimeException {
		try {
			super.update(dominiCorreu);
			getSession(false).flush();
			auditarDominiCorreu("U", dominiCorreu.getCodi()); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("DominiCorreuEntityDaoImpl.8"),  //$NON-NLS-1$
					dominiCorreu.getCodi(),
					message));
		}
	}
	
    public void toDominiCorreu(
        es.caib.seycon.ng.model.DominiCorreuEntity sourceEntity,
        es.caib.seycon.ng.comu.DominiCorreu targetVO)
    {
        // @todo verify behavior of toDominiCorreu
        super.toDominiCorreu(sourceEntity, targetVO);
        String obsolet = sourceEntity.getObsolet();
        if(obsolet != null){
        	targetVO.setObsolet(new Boolean(obsolet.compareTo("S") == 0)); //$NON-NLS-1$
        }else{
        	targetVO.setObsolet(new Boolean(false));
        }
    }


    /**
     * @see es.caib.seycon.ng.model.DominiCorreuEntityDao#toDominiCorreu(es.caib.seycon.ng.model.DominiCorreuEntity)
     */
    public es.caib.seycon.ng.comu.DominiCorreu toDominiCorreu(final es.caib.seycon.ng.model.DominiCorreuEntity entity)
    {
        // @todo verify behavior of toDominiCorreu
        return super.toDominiCorreu(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private es.caib.seycon.ng.model.DominiCorreuEntity loadDominiCorreuEntityFromDominiCorreu(es.caib.seycon.ng.comu.DominiCorreu dominiCorreu)
    {
    	es.caib.seycon.ng.model.DominiCorreuEntity dominiCorreuEntity = null;
    	if(dominiCorreu.getId() != null){
    		dominiCorreuEntity = load(dominiCorreu.getId());
    	}
        if (dominiCorreuEntity == null)
        {
            dominiCorreuEntity = newDominiCorreuEntity();
        }
        return dominiCorreuEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.DominiCorreuEntityDao#dominiCorreuToEntity(es.caib.seycon.ng.DominiCorreu)
     */
    public es.caib.seycon.ng.model.DominiCorreuEntity dominiCorreuToEntity(es.caib.seycon.ng.comu.DominiCorreu dominiCorreu)
    {
        // @todo verify behavior of dominiCorreuToEntity
        es.caib.seycon.ng.model.DominiCorreuEntity entity = this.loadDominiCorreuEntityFromDominiCorreu(dominiCorreu);
        this.dominiCorreuToEntity(dominiCorreu, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.DominiCorreuEntityDao#dominiCorreuToEntity(es.caib.seycon.ng.DominiCorreu, es.caib.seycon.ng.model.DominiCorreuEntity)
     */
    public void dominiCorreuToEntity(
        es.caib.seycon.ng.comu.DominiCorreu sourceVO,
        es.caib.seycon.ng.model.DominiCorreuEntity targetEntity,
        boolean copyIfNull)
    {
        // @todo verify behavior of dominiCorreuToEntity
        super.dominiCorreuToEntity(sourceVO, targetEntity, copyIfNull);
        targetEntity.setObsolet(sourceVO.getObsolet().booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof DominiCorreuEntity) {
				DominiCorreuEntity entity = (DominiCorreuEntity) obj;
				this.create(entity); // cridem al mètode 1 per 1
			}
		}
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof DominiCorreuEntity) {
				DominiCorreuEntity entity = (DominiCorreuEntity) obj;
				this.update(entity);// cridem al mètode 1 per 1
			}
		}
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof DominiCorreuEntity) {
				DominiCorreuEntity entity = (DominiCorreuEntity) obj;
				this.remove(entity);// cridem al mètode 1 per 1
			}
		}
	}    

}