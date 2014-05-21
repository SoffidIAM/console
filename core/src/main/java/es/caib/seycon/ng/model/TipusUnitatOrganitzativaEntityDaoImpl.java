// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import es.caib.seycon.ng.comu.TipusUnitatOrganitzativa;
import es.caib.seycon.ng.exception.SeyconException;

/**
 * @author u91940
 * @see es.caib.seycon.ng.model.TipusUnitatOrganitzativaEntity
 */
public class TipusUnitatOrganitzativaEntityDaoImpl
    extends es.caib.seycon.ng.model.TipusUnitatOrganitzativaEntityDaoBase
{

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private es.caib.seycon.ng.model.TipusUnitatOrganitzativaEntity loadTipusUnitatOrganitzativaEntityFromTipusUnitatOrganitzativa(
			es.caib.seycon.ng.comu.TipusUnitatOrganitzativa tipusUnitatOrganitzativa) {
		TipusUnitatOrganitzativaEntity tipusUnitatOrganitzativaEntity = null;
		if (tipusUnitatOrganitzativa.getId() != null) {
			tipusUnitatOrganitzativaEntity = load(tipusUnitatOrganitzativa.getId());
		}
		if (tipusUnitatOrganitzativaEntity == null) {
			return newTipusUnitatOrganitzativaEntity();
		}
		return tipusUnitatOrganitzativaEntity;
	}
	
	
	public TipusUnitatOrganitzativaEntity tipusUnitatOrganitzativaToEntity(TipusUnitatOrganitzativa tipusUnitatOrganitzativa) {
		TipusUnitatOrganitzativaEntity entity=loadTipusUnitatOrganitzativaEntityFromTipusUnitatOrganitzativa(tipusUnitatOrganitzativa);
		tipusUnitatOrganitzativaToEntity(tipusUnitatOrganitzativa, entity, true);
		return entity;
		
	}


	public void remove(TipusUnitatOrganitzativaEntity tipusUnitatOrganitzativaEntity) {
		
		if (tipusUnitatOrganitzativaEntity.getGrupEntities().size() != 0) {
			throw new SeyconException(String.format(Messages.getString("TipusUnitatOrganitzativaEntityDaoImpl.0"), //$NON-NLS-1$
					tipusUnitatOrganitzativaEntity.getGrupEntities().size()));
		}		
		super.remove(tipusUnitatOrganitzativaEntity);
	}
	
	
	
	
}