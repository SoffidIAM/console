// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import com.soffid.iam.model.ServiceEntity;
import com.soffid.iam.utils.ExceptionTranslator;

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.*;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * @see es.caib.seycon.ng.model.ServeiEntity
 */
public class ServiceEntityDaoImpl extends
		com.soffid.iam.model.ServiceEntityDaoBase {
		
	public void create(com.soffid.iam.model.ServiceEntity servei) throws RuntimeException {
		try {
			super.create(servei);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);

			throw new SeyconException(String.format(Messages.getString("ServiceEntityDaoImpl.0"), servei.getName(), message));
		}
	}

	public void remove(com.soffid.iam.model.ServiceEntity servei) throws RuntimeException {
		try {
			super.remove(servei);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("ServiceEntityDaoImpl.1"), servei.getName(), message));
		}
	}

	public void update(com.soffid.iam.model.ServiceEntity servei) throws RuntimeException {
		try {
			super.update(servei);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("ServiceEntityDaoImpl.2"), servei.getName(), message));
		}
	}

	public void toService(com.soffid.iam.model.ServiceEntity sourceEntity, com.soffid.iam.api.Service targetVO) {
		super.toService(sourceEntity, targetVO);
	}

	/**
	 * @see es.caib.seycon.ng.model.ServeiEntityDao#toServei(es.caib.seycon.ng.model.ServeiEntity)
	 */
	public com.soffid.iam.api.Service toService(final com.soffid.iam.model.ServiceEntity entity) {
		return super.toService(entity);
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private com.soffid.iam.model.ServiceEntity loadServeiEntityFromServei(com.soffid.iam.api.Service servei) {
		com.soffid.iam.model.ServiceEntity serveiEntity = null;
		if (servei.getId() != null) {
			serveiEntity = load(servei.getId());
		}
		if (serveiEntity == null) {
			serveiEntity = newServiceEntity();
		}
		return serveiEntity;
	}

	/**
	 * @see es.caib.seycon.ng.model.ServeiEntityDao#serveiToEntity(es.caib.seycon.ng.comu.Servei)
	 */
	public com.soffid.iam.model.ServiceEntity serviceToEntity(com.soffid.iam.api.Service servei) {
		com.soffid.iam.model.ServiceEntity entity = this.loadServeiEntityFromServei(servei);
		this.serviceToEntity(servei, entity, true);
		return entity;
	}

	/**
	 * @see es.caib.seycon.ng.model.ServeiEntityDao#serveiToEntity(es.caib.seycon.ng.comu.Servei,
	 *      es.caib.seycon.ng.model.ServeiEntity)
	 */
	public void serviceToEntity(com.soffid.iam.api.Service sourceVO, com.soffid.iam.model.ServiceEntity targetEntity, boolean copyIfNull) {
		super.serviceToEntity(sourceVO, targetEntity, copyIfNull);
	}

	public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof ServiceEntity) {
                ServiceEntity entity = (ServiceEntity) obj;
                this.create(entity);
            }
        }
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof ServiceEntity) {
                ServiceEntity entity = (ServiceEntity) obj;
                this.update(entity);
            }
        }
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof ServiceEntity) {
                ServiceEntity entity = (ServiceEntity) obj;
                this.remove(entity);
            }
        }
	}

}