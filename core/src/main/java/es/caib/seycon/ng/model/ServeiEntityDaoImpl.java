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

/**
 * @see es.caib.seycon.ng.model.ServeiEntity
 */
public class ServeiEntityDaoImpl extends
		es.caib.seycon.ng.model.ServeiEntityDaoBase {
		
	public void create(es.caib.seycon.ng.model.ServeiEntity servei)
			throws RuntimeException {
		try {
			super.create(servei);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);

			throw new SeyconException(String.format(Messages.getString("ServeiEntityDaoImpl.0"),  //$NON-NLS-1$
					servei.getCodi(), 
					message));
		}
	}

	public void remove(es.caib.seycon.ng.model.ServeiEntity servei)
			throws RuntimeException {
		try {
			super.remove(servei);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("ServeiEntityDaoImpl.1"),  //$NON-NLS-1$
					servei.getCodi(), 
					message));
		}
	}

	public void update(es.caib.seycon.ng.model.ServeiEntity servei)
			throws RuntimeException {
		try {
			super.update(servei);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("ServeiEntityDaoImpl.2"),  //$NON-NLS-1$
					servei.getCodi(), 
					message));
		}
	}

	public void toServei(es.caib.seycon.ng.model.ServeiEntity sourceEntity,
			es.caib.seycon.ng.comu.Servei targetVO) {
		super.toServei(sourceEntity, targetVO);
	}

	/**
	 * @see es.caib.seycon.ng.model.ServeiEntityDao#toServei(es.caib.seycon.ng.model.ServeiEntity)
	 */
	public es.caib.seycon.ng.comu.Servei toServei(
			final es.caib.seycon.ng.model.ServeiEntity entity) {
		return super.toServei(entity);
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private es.caib.seycon.ng.model.ServeiEntity loadServeiEntityFromServei(
			es.caib.seycon.ng.comu.Servei servei) {
		es.caib.seycon.ng.model.ServeiEntity serveiEntity = null;
		if (servei.getId() != null) {
			serveiEntity = load(servei.getId());
		}
		if (serveiEntity == null) {
			serveiEntity = newServeiEntity();
		}
		return serveiEntity;
	}

	/**
	 * @see es.caib.seycon.ng.model.ServeiEntityDao#serveiToEntity(es.caib.seycon.ng.comu.Servei)
	 */
	public es.caib.seycon.ng.model.ServeiEntity serveiToEntity(
			es.caib.seycon.ng.comu.Servei servei) {
		es.caib.seycon.ng.model.ServeiEntity entity = this
				.loadServeiEntityFromServei(servei);
		this.serveiToEntity(servei, entity, true);
		return entity;
	}

	/**
	 * @see es.caib.seycon.ng.model.ServeiEntityDao#serveiToEntity(es.caib.seycon.ng.comu.Servei,
	 *      es.caib.seycon.ng.model.ServeiEntity)
	 */
	public void serveiToEntity(es.caib.seycon.ng.comu.Servei sourceVO,
			es.caib.seycon.ng.model.ServeiEntity targetEntity,
			boolean copyIfNull) {
		super.serveiToEntity(sourceVO, targetEntity, copyIfNull);
	}

	public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof ServeiEntity) {
				ServeiEntity entity = (ServeiEntity) obj;
				this.create(entity); // cridem al mètode 1 per 1
			}
		}
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof ServeiEntity) {
				ServeiEntity entity = (ServeiEntity) obj;
				this.update(entity);// cridem al mètode 1 per 1
			}
		}
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof ServeiEntity) {
				ServeiEntity entity = (ServeiEntity) obj;
				this.remove(entity);// cridem al mètode 1 per 1
			}
		}
	}

}