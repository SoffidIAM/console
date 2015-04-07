// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.SessionEntity;
import es.caib.seycon.ng.comu.Sessio;
import es.caib.seycon.ng.exception.SeyconException;
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

	public void toSessio(com.soffid.iam.model.SessionEntity sourceEntity, es.caib.seycon.ng.comu.Sessio targetVO) {
		super.toSessio(sourceEntity, targetVO);
		toSessioCustom(sourceEntity, targetVO);
	}

	public void toSessioCustom(com.soffid.iam.model.SessionEntity sourceEntity, es.caib.seycon.ng.comu.Sessio targetVO) {
		targetVO.setPort(sourceEntity.getPort());
		targetVO.setUrl(sourceEntity.getWebHandler());
		if (sourceEntity.getStartDate() != null) {
			Calendar dadaIniciSessio = Calendar.getInstance();
			dadaIniciSessio.setTime(sourceEntity.getStartDate());
			targetVO.setDataInici(dadaIniciSessio);
		}
		
		HostEntity maquinaClientEntity = sourceEntity.getClientHost();
		if (maquinaClientEntity == null)
		{
			targetVO.setNomMaquinaClient(sourceEntity.getClientHostName());
		}
		else
		{
    		targetVO.setNomMaquinaClient(maquinaClientEntity == null ? "" : maquinaClientEntity.getName());
		}
		if (sourceEntity.getHost() == null)
		{
			targetVO.setNomMaquinaServidora(sourceEntity.getHostName());
		} else {
			targetVO.setNomMaquinaServidora(sourceEntity.getHost().getName());
		}
		targetVO.setCodiUsuari(sourceEntity.getUser().getUserName());
		targetVO.setNomComplertUsuari(sourceEntity.getUser().getFullName());
		targetVO.setIdRegistreAccess(sourceEntity.getLoginLogInfo().getId());
	}

	/**
	 * @see es.caib.seycon.ng.model.SessioEntityDao#toSessio(es.caib.seycon.ng.model.SessioEntity)
	 */
	public es.caib.seycon.ng.comu.Sessio toSessio(final com.soffid.iam.model.SessionEntity entity) {
		Sessio sessio = super.toSessio(entity);
		toSessioCustom(entity, sessio);
		return sessio;
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private com.soffid.iam.model.SessionEntity loadSessioEntityFromSessio(es.caib.seycon.ng.comu.Sessio sessio) {
		throw new SeyconException(
				Messages.getString("SessionEntityDaoImpl.2")); //$NON-NLS-1$
	}

	/**
	 * @see es.caib.seycon.ng.model.SessioEntityDao#sessioToEntity(es.caib.seycon.ng.comu.Sessio)
	 */
	public com.soffid.iam.model.SessionEntity sessioToEntity(es.caib.seycon.ng.comu.Sessio sessio) {
		// @todo verify behavior of sessioToEntity
		com.soffid.iam.model.SessionEntity entity = this.loadSessioEntityFromSessio(sessio);
		this.sessioToEntity(sessio, entity, true);
		return entity;
	}


	public void sessioToEntityCustom(es.caib.seycon.ng.comu.Sessio sourceVO, com.soffid.iam.model.SessionEntity targetEntity) {
		// les sessions no es poden editar ni crear
	}

	/**
	 * @see es.caib.seycon.ng.model.SessioEntityDao#sessioToEntity(es.caib.seycon.ng.comu.Sessio,
	 *      es.caib.seycon.ng.model.SessioEntity)
	 */
	public void sessioToEntity(es.caib.seycon.ng.comu.Sessio sourceVO, com.soffid.iam.model.SessionEntity targetEntity, boolean copyIfNull) {
		// @todo verify behavior of sessioToEntity
		super.sessioToEntity(sourceVO, targetEntity, copyIfNull);
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