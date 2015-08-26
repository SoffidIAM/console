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

import com.soffid.iam.api.Session;
import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.SessionEntity;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.*;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

/**
 * @see es.caib.seycon.ng.model.SessioEntity
 */
public class SessionEntityDaoImpl extends
		com.soffid.iam.model.SessionEntityDaoBase {

	public void create(com.soffid.iam.model.SessionEntity sessio) throws RuntimeException {
		try {
			super.create(sessio);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("SessionEntityDaoImpl.0"), sessio.getHost().getName(), sessio.getClientHost().getName(), message));
		}
	}

	public void remove(com.soffid.iam.model.SessionEntity sessio) throws RuntimeException {
		try {
			super.remove(sessio);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("SessionEntityDaoImpl.1"), sessio.getHost().getName(), sessio.getClientHost().getName(), message));
		}
	}

	public void toSession(com.soffid.iam.model.SessionEntity sourceEntity, com.soffid.iam.api.Session targetVO) {
		super.toSession(sourceEntity, targetVO);
		toSessioCustom(sourceEntity, targetVO);
	}

	public void toSessioCustom(com.soffid.iam.model.SessionEntity sourceEntity, com.soffid.iam.api.Session targetVO) {
		targetVO.setPort(sourceEntity.getPort());
		targetVO.setUrl(sourceEntity.getWebHandler());
		if (sourceEntity.getStartDate() != null) {
			Calendar dadaIniciSessio = Calendar.getInstance();
			dadaIniciSessio.setTime(sourceEntity.getStartDate());
			targetVO.setStartDate(dadaIniciSessio);
		}
		
		HostEntity maquinaClientEntity = sourceEntity.getClientHost();
		if (maquinaClientEntity == null)
		{
			targetVO.setClientHostName(sourceEntity.getClientHostName());
		}
		else
		{
    		targetVO.setClientHostName(maquinaClientEntity == null ? "" : maquinaClientEntity.getName());
		}
		if (sourceEntity.getHost() == null)
		{
			targetVO.setServerHostName(sourceEntity.getHostName());
		} else {
			targetVO.setServerHostName(sourceEntity.getHost().getName());
		}
		targetVO.setUserName(sourceEntity.getUser().getUserName());
		targetVO.setUserFullName(sourceEntity.getUser().getFullName());
		targetVO.setAccessLogId(sourceEntity.getLoginLogInfo().getId());
	}

	/**
	 * @see es.caib.seycon.ng.model.SessioEntityDao#toSessio(es.caib.seycon.ng.model.SessioEntity)
	 */
	public com.soffid.iam.api.Session toSession(final com.soffid.iam.model.SessionEntity entity) {
		Session sessio = super.toSession(entity);
		toSessioCustom(entity, sessio);
		return sessio;
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private com.soffid.iam.model.SessionEntity loadSessioEntityFromSessio(com.soffid.iam.api.Session sessio) {
		throw new SeyconException(
				Messages.getString("SessionEntityDaoImpl.2")); //$NON-NLS-1$
	}

	/**
	 * @see es.caib.seycon.ng.model.SessioEntityDao#sessioToEntity(es.caib.seycon.ng.comu.Sessio)
	 */
	public com.soffid.iam.model.SessionEntity sessionToEntity(com.soffid.iam.api.Session sessio) {
		// @todo verify behavior of sessioToEntity
		com.soffid.iam.model.SessionEntity entity = this.loadSessioEntityFromSessio(sessio);
		this.sessionToEntity(sessio, entity, true);
		return entity;
	}


	public void sessioToEntityCustom(com.soffid.iam.api.Session sourceVO, com.soffid.iam.model.SessionEntity targetEntity) {
		// les sessions no es poden editar ni crear
	}

	/**
	 * @see es.caib.seycon.ng.model.SessioEntityDao#sessioToEntity(es.caib.seycon.ng.comu.Sessio,
	 *      es.caib.seycon.ng.model.SessioEntity)
	 */
	public void sessionToEntity(com.soffid.iam.api.Session sourceVO, com.soffid.iam.model.SessionEntity targetEntity, boolean copyIfNull) {
		// @todo verify behavior of sessioToEntity
		super.sessionToEntity(sourceVO, targetEntity, copyIfNull);
	}

	public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof SessionEntity) {
                SessionEntity entity = (SessionEntity) obj;
                this.create(entity);
            }
        }
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof SessionEntity) {
                SessionEntity entity = (SessionEntity) obj;
                this.update(entity);
            }
        }
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof SessionEntity) {
                SessionEntity entity = (SessionEntity) obj;
                this.remove(entity);
            }
        }
	}

}