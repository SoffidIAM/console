// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;
/**
 * @see es.caib.seycon.ng.model.TipusUsuariDispatcherEntity
 */
public class TipusUsuariDispatcherEntityDaoImpl
    extends es.caib.seycon.ng.model.TipusUsuariDispatcherEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.TipusUsuariDispatcherEntityDao#toTipusUsuariDispatcher(es.caib.seycon.ng.model.TipusUsuariDispatcherEntity, es.caib.seycon.ng.comu.TipusUsuariDispatcher)
     */
    public void toTipusUsuariDispatcher(
        es.caib.seycon.ng.model.TipusUsuariDispatcherEntity source,
        es.caib.seycon.ng.comu.TipusUsuariDispatcher target)
    {
        // @todo verify behavior of toTipusUsuariDispatcher
        super.toTipusUsuariDispatcher(source, target);
        
        if (source.getDispatcher() != null) {
        	target.setCodiDispatcher(source.getDispatcher().getCodi());
        }
        if (source.getTipusUsuari() != null) {
        	target.setTipus(source.getTipusUsuari().getCodi());
        }
    }


    /**
     * @see es.caib.seycon.ng.model.TipusUsuariDispatcherEntityDao#toTipusUsuariDispatcher(es.caib.seycon.ng.model.TipusUsuariDispatcherEntity)
     */
    public es.caib.seycon.ng.comu.TipusUsuariDispatcher toTipusUsuariDispatcher(final es.caib.seycon.ng.model.TipusUsuariDispatcherEntity entity)
    {
        // @todo verify behavior of toTipusUsuariDispatcher
        return super.toTipusUsuariDispatcher(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private es.caib.seycon.ng.model.TipusUsuariDispatcherEntity loadTipusUsuariDispatcherEntityFromTipusUsuariDispatcher(es.caib.seycon.ng.comu.TipusUsuariDispatcher tipusUsuariDispatcher)
    {
        es.caib.seycon.ng.model.TipusUsuariDispatcherEntity tipusUsuariDispatcherEntity = null; 
        if (tipusUsuariDispatcher.getId() !=null) {
        	tipusUsuariDispatcherEntity = this.load(tipusUsuariDispatcher.getId());
        }
        if (tipusUsuariDispatcherEntity == null)
        {
            tipusUsuariDispatcherEntity = newTipusUsuariDispatcherEntity();
        }
        return tipusUsuariDispatcherEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.TipusUsuariDispatcherEntityDao#tipusUsuariDispatcherToEntity(es.caib.seycon.ng.comu.TipusUsuariDispatcher)
     */
    public es.caib.seycon.ng.model.TipusUsuariDispatcherEntity tipusUsuariDispatcherToEntity(es.caib.seycon.ng.comu.TipusUsuariDispatcher tipusUsuariDispatcher)
    {
        // @todo verify behavior of tipusUsuariDispatcherToEntity
        es.caib.seycon.ng.model.TipusUsuariDispatcherEntity entity = this.loadTipusUsuariDispatcherEntityFromTipusUsuariDispatcher(tipusUsuariDispatcher);
        this.tipusUsuariDispatcherToEntity(tipusUsuariDispatcher, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.TipusUsuariDispatcherEntityDao#tipusUsuariDispatcherToEntity(es.caib.seycon.ng.comu.TipusUsuariDispatcher, es.caib.seycon.ng.model.TipusUsuariDispatcherEntity)
     */
    public void tipusUsuariDispatcherToEntity(
        es.caib.seycon.ng.comu.TipusUsuariDispatcher source,
        es.caib.seycon.ng.model.TipusUsuariDispatcherEntity target,
        boolean copyIfNull)
    {

        super.tipusUsuariDispatcherToEntity(source, target, copyIfNull);
        
        if (source.getId()!=null) 
        	target.setId(source.getId());
        if (source.getTipus()!=null) {
        	TipusUsuariEntity tipusu = getTipusUsuariEntityDao().findByCodi(source.getTipus());
        	if (tipusu !=null)
        		target.setTipusUsuari(tipusu);
        }
        if (source.getCodiDispatcher() !=null) {
        	DispatcherEntity agent = getDispatcherEntityDao().findByCodi(source.getCodiDispatcher());
        	if (agent !=null) 
        		target.setDispatcher(agent);
        }
        
        
    }

}
