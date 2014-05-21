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
 * @see es.caib.seycon.ng.model.TipusUsuariEntity
 */
public class TipusUsuariEntityDaoImpl
    extends es.caib.seycon.ng.model.TipusUsuariEntityDaoBase
{
    private void audit(String accio, TipusUsuariEntity type) {
        String codiUsuari = Security.getCurrentAccount();
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(accio);
        auditoria.setUserType(type.getCodi());
        auditoria.setAutor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance()
                .getTime()));
        auditoria.setObjecte("SC_TIPUSU"); //$NON-NLS-1$
        AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao()
                .auditoriaToEntity(auditoria);
        getAuditoriaEntityDao().create(auditoriaEntity);
    }
    /**
     * @see es.caib.seycon.ng.model.TipusUsuariEntityDao#toTipusUsuari(es.caib.seycon.ng.model.TipusUsuariEntity, es.caib.seycon.ng.comu.TipusUsuari)
     */
    public void toTipusUsuari(
        es.caib.seycon.ng.model.TipusUsuariEntity source,
        es.caib.seycon.ng.comu.TipusUsuari target)
    {
        // @todo verify behavior of toTipusUsuari
        super.toTipusUsuari(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.TipusUsuariEntityDao#toTipusUsuari(es.caib.seycon.ng.model.TipusUsuariEntity)
     */
    public es.caib.seycon.ng.comu.TipusUsuari toTipusUsuari(final es.caib.seycon.ng.model.TipusUsuariEntity entity)
    {
        // @todo verify behavior of toTipusUsuari
        return super.toTipusUsuari(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private es.caib.seycon.ng.model.TipusUsuariEntity loadTipusUsuariEntityFromTipusUsuari(es.caib.seycon.ng.comu.TipusUsuari tipusUsuari)
    {
		es.caib.seycon.ng.model.TipusUsuariEntity tipusUsuariEntity = null;
		if (tipusUsuari.getId() != null) {
			tipusUsuariEntity = this.load(tipusUsuari.getId());
		}
		if (tipusUsuariEntity == null) {
			tipusUsuariEntity = newTipusUsuariEntity();
		}
		return tipusUsuariEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.TipusUsuariEntityDao#tipusUsuariToEntity(es.caib.seycon.ng.comu.TipusUsuari)
     */
    public es.caib.seycon.ng.model.TipusUsuariEntity tipusUsuariToEntity(es.caib.seycon.ng.comu.TipusUsuari tipusUsuari)
    {
        // @todo verify behavior of tipusUsuariToEntity
        es.caib.seycon.ng.model.TipusUsuariEntity entity = this.loadTipusUsuariEntityFromTipusUsuari(tipusUsuari);
        this.tipusUsuariToEntity(tipusUsuari, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.TipusUsuariEntityDao#tipusUsuariToEntity(es.caib.seycon.ng.comu.TipusUsuari, es.caib.seycon.ng.model.TipusUsuariEntity)
     */
    public void tipusUsuariToEntity(
        es.caib.seycon.ng.comu.TipusUsuari source,
        es.caib.seycon.ng.model.TipusUsuariEntity target,
        boolean copyIfNull)
    {
        // @todo verify behavior of tipusUsuariToEntity
        super.tipusUsuariToEntity(source, target, copyIfNull);
    }
	@Override
	public void create (TipusUsuariEntity entity)
	{
		super.create(entity);
		audit ("C", entity); //$NON-NLS-1$
	}
	@Override
	public void update (TipusUsuariEntity entity)
	{
		super.update(entity);
		audit ("U", entity); //$NON-NLS-1$
	}
	@Override
	public void remove (TipusUsuariEntity entity)
	{
		audit ("D", entity); //$NON-NLS-1$
		super.remove(entity);
	}

}