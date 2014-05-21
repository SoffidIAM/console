// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import es.caib.seycon.ng.comu.Sessio;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.utils.ExceptionTranslator;

/**
 * @see es.caib.seycon.ng.model.SessioEntity
 */
public class SessioEntityDaoImpl extends
		es.caib.seycon.ng.model.SessioEntityDaoBase {

	public void create(es.caib.seycon.ng.model.SessioEntity sessio)
			throws RuntimeException {
		try {
			super.create(sessio);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("SessioEntityDaoImpl.0"),  //$NON-NLS-1$
					sessio.getMaquina().getNom(), 
					sessio.getMaquinaClient().getNom(),
					message));
		}
	}

	public void remove(es.caib.seycon.ng.model.SessioEntity sessio)
			throws RuntimeException {
		try {
			super.remove(sessio);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("SessioEntityDaoImpl.1"),  //$NON-NLS-1$
					sessio.getMaquina().getNom(), 
					sessio.getMaquinaClient().getNom(),
					message));
		}
	}

	public void toSessio(es.caib.seycon.ng.model.SessioEntity sourceEntity,
			es.caib.seycon.ng.comu.Sessio targetVO) {
		super.toSessio(sourceEntity, targetVO);
		toSessioCustom(sourceEntity, targetVO);
	}

	public void toSessioCustom(
			es.caib.seycon.ng.model.SessioEntity sourceEntity,
			es.caib.seycon.ng.comu.Sessio targetVO) {
		targetVO.setPort(sourceEntity.getPort());
		targetVO.setUrl(sourceEntity.getWebHandler());
		if (sourceEntity.getDataInici() != null) {
			Calendar dadaIniciSessio = Calendar.getInstance();
			dadaIniciSessio.setTime(sourceEntity.getDataInici());
			targetVO.setDataInici(dadaIniciSessio);
		}
		
		MaquinaEntity maquinaClientEntity = sourceEntity.getMaquinaClient();
		if (maquinaClientEntity == null)
		{
			targetVO.setNomMaquinaClient(sourceEntity.getClientHostName());
		}
		else
		{
    		targetVO.setNomMaquinaClient(maquinaClientEntity == null ? "" //$NON-NLS-1$
    				: maquinaClientEntity.getNom());
		}
		if (sourceEntity.getMaquina() == null)
		{
			targetVO.setNomMaquinaServidora(sourceEntity.getHostName());
		} else {
			targetVO.setNomMaquinaServidora(sourceEntity.getMaquina().getNom());
		}
		targetVO.setCodiUsuari(sourceEntity.getUsuari().getCodi());
		targetVO.setNomComplertUsuari(sourceEntity.getUsuari().getFullName());
		targetVO.setIdRegistreAccess(sourceEntity.getRegIstreLogin().getId());
	}

	/**
	 * @see es.caib.seycon.ng.model.SessioEntityDao#toSessio(es.caib.seycon.ng.model.SessioEntity)
	 */
	public es.caib.seycon.ng.comu.Sessio toSessio(
			final es.caib.seycon.ng.model.SessioEntity entity) {
		Sessio sessio = super.toSessio(entity);
		toSessioCustom(entity, sessio);
		return sessio;
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private es.caib.seycon.ng.model.SessioEntity loadSessioEntityFromSessio(
			es.caib.seycon.ng.comu.Sessio sessio) {
		throw new SeyconException(
				Messages.getString("SessioEntityDaoImpl.2")); //$NON-NLS-1$
	}

	/**
	 * @see es.caib.seycon.ng.model.SessioEntityDao#sessioToEntity(es.caib.seycon.ng.comu.Sessio)
	 */
	public es.caib.seycon.ng.model.SessioEntity sessioToEntity(
			es.caib.seycon.ng.comu.Sessio sessio) {
		// @todo verify behavior of sessioToEntity
		es.caib.seycon.ng.model.SessioEntity entity = this
				.loadSessioEntityFromSessio(sessio);
		this.sessioToEntity(sessio, entity, true);
		return entity;
	}


	public void sessioToEntityCustom(es.caib.seycon.ng.comu.Sessio sourceVO,
			es.caib.seycon.ng.model.SessioEntity targetEntity) {
		// les sessions no es poden editar ni crear
	}

	/**
	 * @see es.caib.seycon.ng.model.SessioEntityDao#sessioToEntity(es.caib.seycon.ng.comu.Sessio,
	 *      es.caib.seycon.ng.model.SessioEntity)
	 */
	public void sessioToEntity(es.caib.seycon.ng.comu.Sessio sourceVO,
			es.caib.seycon.ng.model.SessioEntity targetEntity,
			boolean copyIfNull) {
		// @todo verify behavior of sessioToEntity
		super.sessioToEntity(sourceVO, targetEntity, copyIfNull);
	}

	public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof SessioEntity) {
				SessioEntity entity = (SessioEntity) obj;
				this.create(entity); // cridem al mètode 1 per 1
			}
		}
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof SessioEntity) {
				SessioEntity entity = (SessioEntity) obj;
				this.update(entity);// cridem al mètode 1 per 1
			}
		}
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof SessioEntity) {
				SessioEntity entity = (SessioEntity) obj;
				this.remove(entity);// cridem al mètode 1 per 1
			}
		}
	}

}