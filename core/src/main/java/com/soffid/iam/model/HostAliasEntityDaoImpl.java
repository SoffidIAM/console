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

import com.soffid.iam.api.HostAlias;
import com.soffid.iam.model.HostAliasEntity;
import com.soffid.iam.model.HostEntity;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.*;
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
	
	public HostAliasEntity hostAliasToEntity(HostAlias aliasMaquina) {
		HostAliasEntity entity = loadAliasMaquinaEntityFromAliasMaquina(aliasMaquina);
		this.hostAliasToEntity(aliasMaquina, entity, true);
		return entity;
	}
	
	private HostAliasEntity loadAliasMaquinaEntityFromAliasMaquina(HostAlias aliasMaquina) {
		
		HostAliasEntity aliasMaquinaEntity = null;
		if (aliasMaquina.getId() != null) {
			aliasMaquinaEntity = load(aliasMaquina.getId());
		}
		if (aliasMaquinaEntity == null) {
			aliasMaquinaEntity = newHostAliasEntity();
		}
		return aliasMaquinaEntity;

	}	


	public void hostAliasToEntity(HostAlias source, HostAliasEntity target, boolean copyIfNull) {
		super.hostAliasToEntity(source, target, copyIfNull);
		aliasMaquinaToEntityCustom(source, target);
	}

	public void toHostAlias(HostAliasEntity source, HostAlias target) {
		super.toHostAlias(source, target);
		toAliasMaquinaCustom(source, target);
	}


	private void toAliasMaquinaCustom(HostAliasEntity entity, HostAlias targetVO) {
		targetVO.setHostId(entity.getHost().getId());
		targetVO.setHostName(entity.getHost().getName());
	}

	private void aliasMaquinaToEntityCustom(HostAlias sourceVO, HostAliasEntity entity) {
		// Ponemos la máquina que corresponde
		HostEntity maquina = null;
		// Si es nuevo el id puede ser nulo 
		if (sourceVO.getHostId() != null) {
			maquina = getHostEntityDao().findById(sourceVO.getHostId());
		} 
		if (maquina == null && sourceVO.getHostName() != null) {
			maquina = getHostEntityDao().findByName(sourceVO.getHostName());
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