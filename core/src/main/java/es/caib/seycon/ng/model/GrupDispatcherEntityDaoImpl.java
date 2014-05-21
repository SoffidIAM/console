// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;
/**
 * @see es.caib.seycon.ng.model.GrupDispatcherEntity
 */
public class GrupDispatcherEntityDaoImpl
    extends es.caib.seycon.ng.model.GrupDispatcherEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.GrupDispatcherEntityDao#toGrupDispatcher(es.caib.seycon.ng.model.GrupDispatcherEntity, es.caib.seycon.ng.comu.GrupDispatcher)
     */
    public void toGrupDispatcher(
        es.caib.seycon.ng.model.GrupDispatcherEntity source,
        es.caib.seycon.ng.comu.GrupDispatcher target)
    {
        // @todo verify behavior of toGrupDispatcher
        super.toGrupDispatcher(source, target);
        if (source.getDispatcher() !=null)
        	target.setCodiDispatcher(source.getDispatcher().getCodi());
        if (source.getGrup() !=null)
        	target.setCodiGrup(source.getGrup().getCodi());
    }


    /**
     * @see es.caib.seycon.ng.model.GrupDispatcherEntityDao#toGrupDispatcher(es.caib.seycon.ng.model.GrupDispatcherEntity)
     */
    public es.caib.seycon.ng.comu.GrupDispatcher toGrupDispatcher(final es.caib.seycon.ng.model.GrupDispatcherEntity entity)
    {
        // @todo verify behavior of toGrupDispatcher
        return super.toGrupDispatcher(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private es.caib.seycon.ng.model.GrupDispatcherEntity loadGrupDispatcherEntityFromGrupDispatcher(es.caib.seycon.ng.comu.GrupDispatcher grupDispatcher)
    {
        es.caib.seycon.ng.model.GrupDispatcherEntity grupDispatcherEntity = null;
        
        if (grupDispatcher.getId() !=null) {
        	grupDispatcherEntity = this.load(grupDispatcher.getId());
        }
        if (grupDispatcherEntity == null)
        {
            grupDispatcherEntity = newGrupDispatcherEntity();
        }
        return grupDispatcherEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.GrupDispatcherEntityDao#grupDispatcherToEntity(es.caib.seycon.ng.comu.GrupDispatcher)
     */
    public es.caib.seycon.ng.model.GrupDispatcherEntity grupDispatcherToEntity(es.caib.seycon.ng.comu.GrupDispatcher grupDispatcher)
    {
        // @todo verify behavior of grupDispatcherToEntity
        es.caib.seycon.ng.model.GrupDispatcherEntity entity = this.loadGrupDispatcherEntityFromGrupDispatcher(grupDispatcher);
        this.grupDispatcherToEntity(grupDispatcher, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.GrupDispatcherEntityDao#grupDispatcherToEntity(es.caib.seycon.ng.comu.GrupDispatcher, es.caib.seycon.ng.model.GrupDispatcherEntity)
     */
	public void grupDispatcherToEntity(es.caib.seycon.ng.comu.GrupDispatcher source,
			es.caib.seycon.ng.model.GrupDispatcherEntity target, boolean copyIfNull) {
		// @todo verify behavior of grupDispatcherToEntity
		super.grupDispatcherToEntity(source, target, copyIfNull);
		if (source.getId() != null)
			target.setId(source.getId());
		if (source.getCodiDispatcher() != null) {
			DispatcherEntity agent = getDispatcherEntityDao().findByCodi(source.getCodiDispatcher());
			if (agent != null)
				target.setDispatcher(agent);
		}
		if (source.getCodiGrup() != null) {
			GrupEntity grup = getGrupEntityDao().findByCodi(source.getCodiGrup());
			if (grup != null)
				target.setGrup(grup);
		}
	}

}
