// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.springframework.dao.InvalidDataAccessResourceUsageException;

import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.model.criteria.CriteriaSearchConfiguration;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.DominiContrasenyaEntity
 */
public class DominiContrasenyaEntityDaoImpl
    extends es.caib.seycon.ng.model.DominiContrasenyaEntityDaoBase
{

    private void audit(String accio, DominiContrasenyaEntity domain) {
        String codiUsuari = Security.getCurrentAccount();
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(accio);
        auditoria.setPasswordDomain(domain.getCodi());
        auditoria.setAutor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance()
                .getTime()));
        auditoria.setObjecte("SC_DOMCON"); //$NON-NLS-1$
        AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao()
                .auditoriaToEntity(auditoria);
        getAuditoriaEntityDao().create(auditoriaEntity);
    }


	@Override
	public DominiContrasenyaEntity findDefaultDomain(String queryString,
			CriteriaSearchConfiguration criteria, long userId)
	{
    	try {
    		return super.findDefaultDomain(queryString, criteria, userId);
	    } catch (InvalidDataAccessResourceUsageException e) {
	    	// More than one domain
	        DominiContrasenyaEntity dcEntity = findByCodi(criteria, "DEFAULT"); //$NON-NLS-1$
	        if (dcEntity == null)
	        {
	        	DispatcherEntity dispatcher = getDispatcherEntityDao().findByCodi("soffid"); //$NON-NLS-1$
	        	if (dispatcher != null)
	        		dcEntity = dispatcher.getDomini();
	        }
	        if (dcEntity == null)
	        {
	        	DispatcherEntity dispatcher = getDispatcherEntityDao().findByCodi("seu"); //$NON-NLS-1$
	        	if (dispatcher != null)
	        		dcEntity = dispatcher.getDomini();
	        }
	        if (dcEntity == null)
	        	logger.warn(String.format(Messages.getString("DominiContrasenyaEntityDaoImpl.MoreThanOnePassword"), userId)); //$NON-NLS-1$
	        return dcEntity;
	    }
	}


	/**
     * @see es.caib.seycon.ng.model.DominiContrasenyaEntityDao#toDominiContrasenya(es.caib.seycon.ng.model.DominiContrasenyaEntity, es.caib.seycon.ng.comu.DominiContrasenya)
     */
    public void toDominiContrasenya(
        es.caib.seycon.ng.model.DominiContrasenyaEntity source,
        es.caib.seycon.ng.comu.DominiContrasenya target)
    {
        // @todo verify behavior of toDominiContrasenya
        super.toDominiContrasenya(source, target);
                	
    }


    @Override
	public void create (DominiContrasenyaEntity entity)
	{
		super.create(entity);
    	audit ("C", entity); //$NON-NLS-1$
	}


	@Override
	public void update (DominiContrasenyaEntity entity)
	{
		super.update(entity);
    	audit ("U", entity); //$NON-NLS-1$
	}


	@Override
	public void remove (DominiContrasenyaEntity entity)
	{
    	audit ("D", entity); //$NON-NLS-1$
		super.remove(entity);
	}


	/**
     * @see es.caib.seycon.ng.model.DominiContrasenyaEntityDao#toDominiContrasenya(es.caib.seycon.ng.model.DominiContrasenyaEntity)
     */
    public es.caib.seycon.ng.comu.DominiContrasenya toDominiContrasenya(final es.caib.seycon.ng.model.DominiContrasenyaEntity entity)
    {
        // @todo verify behavior of toDominiContrasenya
        return super.toDominiContrasenya(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private es.caib.seycon.ng.model.DominiContrasenyaEntity loadDominiContrasenyaEntityFromDominiContrasenya(es.caib.seycon.ng.comu.DominiContrasenya dominiContrasenya)
    {
        es.caib.seycon.ng.model.DominiContrasenyaEntity dominiContrasenyaEntity = null; 
        if (dominiContrasenya.getId() !=null) {
        	dominiContrasenyaEntity = this.load(dominiContrasenya.getId());
        }
        if (dominiContrasenyaEntity == null)
        {
            dominiContrasenyaEntity = newDominiContrasenyaEntity();
        }
        return dominiContrasenyaEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.DominiContrasenyaEntityDao#dominiContrasenyaToEntity(es.caib.seycon.ng.comu.DominiContrasenya)
     */
    public es.caib.seycon.ng.model.DominiContrasenyaEntity dominiContrasenyaToEntity(es.caib.seycon.ng.comu.DominiContrasenya dominiContrasenya)
    {
        // @todo verify behavior of dominiContrasenyaToEntity
        es.caib.seycon.ng.model.DominiContrasenyaEntity entity = this.loadDominiContrasenyaEntityFromDominiContrasenya(dominiContrasenya);
        this.dominiContrasenyaToEntity(dominiContrasenya, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.DominiContrasenyaEntityDao#dominiContrasenyaToEntity(es.caib.seycon.ng.comu.DominiContrasenya, es.caib.seycon.ng.model.DominiContrasenyaEntity)
     */
    public void dominiContrasenyaToEntity(
        es.caib.seycon.ng.comu.DominiContrasenya source,
        es.caib.seycon.ng.model.DominiContrasenyaEntity target,
        boolean copyIfNull)
    {
        // @todo verify behavior of dominiContrasenyaToEntity
        super.dominiContrasenyaToEntity(source, target, copyIfNull);
    }

}