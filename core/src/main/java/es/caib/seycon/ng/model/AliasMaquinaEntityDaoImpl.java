// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.util.Collection;
import java.util.Iterator;

import es.caib.seycon.ng.comu.AliasMaquina;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.utils.ExceptionTranslator;

/**
 * @see es.caib.seycon.ng.model.AliasMaquinaEntity
 */
public class AliasMaquinaEntityDaoImpl
    extends es.caib.seycon.ng.model.AliasMaquinaEntityDaoBase
{

	public void create(AliasMaquinaEntity aliasMaquinaEntity) {
		try {						
			super.create(aliasMaquinaEntity);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("AliasMaquinaEntityDaoImpl.0"), aliasMaquinaEntity.getAlias(), message)); //$NON-NLS-1$
		}		
		
	}

	public void remove(AliasMaquinaEntity aliasMaquinaEntity) {
		try {
			//auditarAlies("D", codiAplicacio);
			super.remove(aliasMaquinaEntity);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("AliasMaquinaEntityDaoImpl.1"), aliasMaquinaEntity.getAlias(), message)); //$NON-NLS-1$
		}		
	}

	public void update(AliasMaquinaEntity aliasMaquinaEntity) {
		try {
			super.update(aliasMaquinaEntity);
			getSession(false).flush();
			//auditarAlies("U", aplicacio.getCodi());
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("AliasMaquinaEntityDaoImpl.2"), aliasMaquinaEntity.getAlias(), message)); //$NON-NLS-1$
		}
	}
	
	public AliasMaquinaEntity aliasMaquinaToEntity(AliasMaquina aliasMaquina) {
		AliasMaquinaEntity entity = loadAliasMaquinaEntityFromAliasMaquina(aliasMaquina);
		this.aliasMaquinaToEntity(aliasMaquina, entity, true);
		return entity;
	}
	
	private AliasMaquinaEntity loadAliasMaquinaEntityFromAliasMaquina(AliasMaquina aliasMaquina) {
		
		AliasMaquinaEntity aliasMaquinaEntity = null;
		if (aliasMaquina.getId() != null) {
			aliasMaquinaEntity = load(aliasMaquina.getId());
		}
		if (aliasMaquinaEntity == null) {
			aliasMaquinaEntity = newAliasMaquinaEntity();
		}
		return aliasMaquinaEntity;

	}	


	public void aliasMaquinaToEntity(AliasMaquina source,
			AliasMaquinaEntity target, boolean copyIfNull) {
		super.aliasMaquinaToEntity(source, target, copyIfNull);
		aliasMaquinaToEntityCustom(source, target);
	}

	public void toAliasMaquina(AliasMaquinaEntity source, AliasMaquina target) {
		super.toAliasMaquina(source, target);
		toAliasMaquinaCustom(source, target);
	}


	private void toAliasMaquinaCustom (AliasMaquinaEntity entity, AliasMaquina targetVO) {
		targetVO.setIdMaquina(entity.getMaquina().getId());
		targetVO.setNomMaquina(entity.getMaquina().getNom());
	}

	private void aliasMaquinaToEntityCustom(AliasMaquina sourceVO,
			AliasMaquinaEntity entity) {
		// Ponemos la máquina que corresponde
		MaquinaEntity maquina = null;
		// Si es nuevo el id puede ser nulo 
		if (sourceVO.getIdMaquina()!=null) {
			maquina = getMaquinaEntityDao().findById(sourceVO.getIdMaquina());
		} 
		if (maquina==null && sourceVO.getNomMaquina()!=null) {
			maquina = getMaquinaEntityDao().findByNom(sourceVO.getNomMaquina());
		}
		entity.setMaquina(maquina);
	}

	public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof AliasMaquinaEntity) {
				AliasMaquinaEntity entity = (AliasMaquinaEntity) obj;
				this.create(entity); // cridem al mètode 1 per 1
			}
		}
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof AliasMaquinaEntity) {
				AliasMaquinaEntity entity = (AliasMaquinaEntity) obj;
				this.update(entity);// cridem al mètode 1 per 1
			}
		}
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof AliasMaquinaEntity) {
				AliasMaquinaEntity entity = (AliasMaquinaEntity) obj;
				this.remove(entity);// cridem al mètode 1 per 1
			}
		}
	}
}