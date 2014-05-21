// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.util.Collection;
import java.util.Iterator;

import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.utils.ExceptionTranslator;

/**
 * @see es.caib.seycon.ng.model.TipusDadaEntity
 */
public class TipusDadaEntityDaoImpl extends
		es.caib.seycon.ng.model.TipusDadaEntityDaoBase {

	public void create(es.caib.seycon.ng.model.TipusDadaEntity tipusDada)
			throws RuntimeException {
		try {
			super.create(tipusDada);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("TipusDadaEntityDaoImpl.0"),  //$NON-NLS-1$
					tipusDada.getCodi(),
					message));
		}
	}

	public void remove(es.caib.seycon.ng.model.TipusDadaEntity tipusDada)
			throws RuntimeException {
		try {
			super.remove(tipusDada);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("TipusDadaEntityDaoImpl.1"), //$NON-NLS-1$
					tipusDada.getCodi(), message));
		}
	}

	public void toTipusDada(
			es.caib.seycon.ng.model.TipusDadaEntity sourceEntity,
			es.caib.seycon.ng.comu.TipusDada targetVO) {
		super.toTipusDada(sourceEntity, targetVO);
	}

	/**
	 * @see es.caib.seycon.ng.model.TipusDadaEntityDao#toTipusDada(es.caib.seycon.ng.model.TipusDadaEntity)
	 */
	public es.caib.seycon.ng.comu.TipusDada toTipusDada(
			final es.caib.seycon.ng.model.TipusDadaEntity entity) {
		return super.toTipusDada(entity);
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private es.caib.seycon.ng.model.TipusDadaEntity loadTipusDadaEntityFromTipusDada(
			es.caib.seycon.ng.comu.TipusDada tipusDada) {
		TipusDadaEntity tipusDadaEntity = null;
		if (tipusDada.getId() != null) {
			tipusDadaEntity = load(tipusDada.getId());
		}
		if (tipusDadaEntity == null) {
			return newTipusDadaEntity();
		}
		return tipusDadaEntity;
	}

	/**
	 * @see es.caib.seycon.ng.model.TipusDadaEntityDao#tipusDadaToEntity(es.caib.seycon.ng.comu.TipusDada)
	 */
	public es.caib.seycon.ng.model.TipusDadaEntity tipusDadaToEntity(
			es.caib.seycon.ng.comu.TipusDada tipusDada) {
		es.caib.seycon.ng.model.TipusDadaEntity entity = this
				.loadTipusDadaEntityFromTipusDada(tipusDada);
		this.tipusDadaToEntity(tipusDada, entity, true);
		return entity;
	}

	/**
	 * @see es.caib.seycon.ng.model.TipusDadaEntityDao#tipusDadaToEntity(es.caib.seycon.ng.comu.TipusDada,
	 *      es.caib.seycon.ng.model.TipusDadaEntity)
	 */
	public void tipusDadaToEntity(es.caib.seycon.ng.comu.TipusDada sourceVO,
			es.caib.seycon.ng.model.TipusDadaEntity targetEntity,
			boolean copyIfNull) {
		super.tipusDadaToEntity(sourceVO, targetEntity, copyIfNull);
	}
	
	public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof TipusDadaEntity) {
				TipusDadaEntity entity = (TipusDadaEntity) obj;
				this.create(entity); // cridem al mètode 1 per 1
			}
		}
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof TipusDadaEntity) {
				TipusDadaEntity entity = (TipusDadaEntity) obj;
				this.update(entity);// cridem al mètode 1 per 1
			}
		}
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof TipusDadaEntity) {
				TipusDadaEntity entity = (TipusDadaEntity) obj;
				this.remove(entity);// cridem al mètode 1 per 1
			}
		}
	}	

}