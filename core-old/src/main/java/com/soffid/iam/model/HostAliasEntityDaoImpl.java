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

import com.soffid.iam.model.HostAliasEntity;
import com.soffid.iam.model.HostEntity;
import es.caib.seycon.ng.comu.AliasMaquina;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import java.util.Collection;
import java.util.Iterator;

/**
 * @see es.caib.seycon.ng.model.AliasMaquinaEntity
 */
public class HostAliasEntityDaoImpl
    extends com.soffid.iam.model.HostAliasEntityDaoBase
{

	public void create(HostAliasEntity aliasMaquinaEntity) {
		try {						
			super.create(aliasMaquinaEntity);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("HostAliasEntityDaoImpl.0"), aliasMaquinaEntity.getAlias(), message)); //$NON-NLS-1$
		}		
		
	}

	public void remove(HostAliasEntity aliasMaquinaEntity) {
		try {
			//auditarAlies("D", codiAplicacio);
			super.remove(aliasMaquinaEntity);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("HostAliasEntityDaoImpl.1"), aliasMaquinaEntity.getAlias(), message)); //$NON-NLS-1$
		}		
	}

	public void update(HostAliasEntity aliasMaquinaEntity) {
		try {
			super.update(aliasMaquinaEntity);
			getSession(false).flush();
			//auditarAlies("U", aplicacio.getCodi());
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("HostAliasEntityDaoImpl.2"), aliasMaquinaEntity.getAlias(), message)); //$NON-NLS-1$
		}
	}
	
	public HostAliasEntity aliasMaquinaToEntity(AliasMaquina aliasMaquina) {
		HostAliasEntity entity = loadAliasMaquinaEntityFromAliasMaquina(aliasMaquina);
		this.aliasMaquinaToEntity(aliasMaquina, entity, true);
		return entity;
	}
	
	private HostAliasEntity loadAliasMaquinaEntityFromAliasMaquina(AliasMaquina aliasMaquina) {
		
		HostAliasEntity aliasMaquinaEntity = null;
		if (aliasMaquina.getId() != null) {
			aliasMaquinaEntity = load(aliasMaquina.getId());
		}
		if (aliasMaquinaEntity == null) {
			aliasMaquinaEntity = newHostAliasEntity();
		}
		return aliasMaquinaEntity;

	}	


	public void aliasMaquinaToEntity(AliasMaquina source, HostAliasEntity target, boolean copyIfNull) {
		super.aliasMaquinaToEntity(source, target, copyIfNull);
		aliasMaquinaToEntityCustom(source, target);
	}

	public void toAliasMaquina(HostAliasEntity source, AliasMaquina target) {
		super.toAliasMaquina(source, target);
		toAliasMaquinaCustom(source, target);
	}


	private void toAliasMaquinaCustom(HostAliasEntity entity, AliasMaquina targetVO) {
		targetVO.setIdMaquina(entity.getHost().getId());
		targetVO.setNomMaquina(entity.getHost().getName());
	}

	private void aliasMaquinaToEntityCustom(AliasMaquina sourceVO, HostAliasEntity entity) {
		// Ponemos la máquina que corresponde
		HostEntity maquina = null;
		// Si es nuevo el id puede ser nulo 
		if (sourceVO.getIdMaquina()!=null) {
			maquina = getHostEntityDao().findById(sourceVO.getIdMaquina());
		} 
		if (maquina==null && sourceVO.getNomMaquina()!=null) {
			maquina = getHostEntityDao().findByName(sourceVO.getNomMaquina());
		}
		entity.setHost(maquina);
	}

	public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof HostAliasEntity) {
                HostAliasEntity entity = (HostAliasEntity) obj;
                this.create(entity);
            }
        }
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof HostAliasEntity) {
                HostAliasEntity entity = (HostAliasEntity) obj;
                this.update(entity);
            }
        }
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof HostAliasEntity) {
                HostAliasEntity entity = (HostAliasEntity) obj;
                this.remove(entity);
            }
        }
	}
}