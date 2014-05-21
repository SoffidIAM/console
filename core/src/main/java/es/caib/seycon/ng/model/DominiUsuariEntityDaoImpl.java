// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.DominiUsuariEntity
 */
public class DominiUsuariEntityDaoImpl
    extends es.caib.seycon.ng.model.DominiUsuariEntityDaoBase
{
    private void audit(String accio, DominiUsuariEntity domain) {
        String codiUsuari = Security.getCurrentAccount();
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(accio);
        auditoria.setUserDomain(domain.getCodi());
        auditoria.setAutor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance()
                .getTime()));
        auditoria.setObjecte("SC_DOMUSU"); //$NON-NLS-1$
        AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao()
                .auditoriaToEntity(auditoria);
        getAuditoriaEntityDao().create(auditoriaEntity);
    }

    /**
     * @see es.caib.seycon.ng.model.DominiUsuariEntityDao#toDominiUsuari(es.caib.seycon.ng.model.DominiUsuariEntity, es.caib.seycon.ng.comu.DominiUsuari)
     */
    public void toDominiUsuari(
        es.caib.seycon.ng.model.DominiUsuariEntity source,
        es.caib.seycon.ng.comu.DominiUsuari target)
    {
        // @todo verify behavior of toDominiUsuari
        super.toDominiUsuari(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.DominiUsuariEntityDao#toDominiUsuari(es.caib.seycon.ng.model.DominiUsuariEntity)
     */
    public es.caib.seycon.ng.comu.DominiUsuari toDominiUsuari(final es.caib.seycon.ng.model.DominiUsuariEntity entity)
    {
        // @todo verify behavior of toDominiUsuari
        return super.toDominiUsuari(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private es.caib.seycon.ng.model.DominiUsuariEntity loadDominiUsuariEntityFromDominiUsuari(es.caib.seycon.ng.comu.DominiUsuari dominiUsuari)
    {
		es.caib.seycon.ng.model.DominiUsuariEntity dominiUsuariEntity = null;

		if (dominiUsuari.getId() != null) {
			dominiUsuariEntity = this.load(dominiUsuari.getId());
		}
		if (dominiUsuariEntity == null) {
			dominiUsuariEntity = newDominiUsuariEntity();
		}
		return dominiUsuariEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.DominiUsuariEntityDao#dominiUsuariToEntity(es.caib.seycon.ng.comu.DominiUsuari)
     */
    public es.caib.seycon.ng.model.DominiUsuariEntity dominiUsuariToEntity(es.caib.seycon.ng.comu.DominiUsuari dominiUsuari)
    {
        // @todo verify behavior of dominiUsuariToEntity
        es.caib.seycon.ng.model.DominiUsuariEntity entity = this.loadDominiUsuariEntityFromDominiUsuari(dominiUsuari);
        this.dominiUsuariToEntity(dominiUsuari, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.DominiUsuariEntityDao#dominiUsuariToEntity(es.caib.seycon.ng.comu.DominiUsuari, es.caib.seycon.ng.model.DominiUsuariEntity)
     */
    public void dominiUsuariToEntity(
        es.caib.seycon.ng.comu.DominiUsuari source,
        es.caib.seycon.ng.model.DominiUsuariEntity target,
        boolean copyIfNull)
    {
        // @todo verify behavior of dominiUsuariToEntity
        super.dominiUsuariToEntity(source, target, copyIfNull);
    }

	@Override
	public void create (DominiUsuariEntity entity)
	{
		super.create(entity);
		audit ("C", entity); //$NON-NLS-1$
	}

	@Override
	public void update (DominiUsuariEntity entity)
	{
		super.update(entity);
		audit ("U", entity); //$NON-NLS-1$
	}

	@Override
	public void remove (DominiUsuariEntity entity)
	{
		audit ("D", entity); //$NON-NLS-1$
		super.remove(entity);
	}

}