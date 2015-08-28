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

import com.soffid.iam.model.AccessControlEntity;
import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.sync.engine.TaskHandler;

import es.caib.seycon.ng.exception.SeyconException;

import java.sql.Timestamp;

/**
 * @see es.caib.seycon.ng.model.ControlAccessEntity
 */
public class AccessControlEntityDaoImpl
    extends com.soffid.iam.model.AccessControlEntityDaoBase
{
    @Override
    public void create(AccessControlEntity controlAccessEntity) {
        super.create(controlAccessEntity);
        createTask(controlAccessEntity);
    }


    @Override
    public void update(AccessControlEntity controlAccessEntity) {
        super.update(controlAccessEntity);
        createTask(controlAccessEntity);
    }


    @Override
    public void remove(AccessControlEntity controlAccessEntity) {
        createTask(controlAccessEntity);
        super.remove(controlAccessEntity);
    }


    private void createTask(AccessControlEntity control) {
        TaskEntity tasque = getTaskEntityDao().newTaskEntity();
        tasque.setDate(new Timestamp(System.currentTimeMillis()));
        tasque.setTransaction(TaskHandler.UPDATE_ACESS_CONTROL);
        tasque.setSystemName(control.getAgent().getName());
        getTaskEntityDao().create(tasque);
    }
    /**
     * @see es.caib.seycon.ng.model.ControlAccessEntityDao#toControlAcces(es.caib.seycon.ng.model.ControlAccessEntity, es.caib.seycon.ng.comu.ControlAcces)
     */
    public void toAccessControl(com.soffid.iam.model.AccessControlEntity source, com.soffid.iam.api.AccessControl target) {
        // @todo verify behavior of toControlAcces
        super.toAccessControl(source, target);
        toControlAccesCustom(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.ControlAccessEntityDao#toControlAcces(es.caib.seycon.ng.model.ControlAccessEntity)
     */
    public com.soffid.iam.api.AccessControl toAccessControl(final com.soffid.iam.model.AccessControlEntity entity) {
        // @todo verify behavior of toControlAcces
        return super.toAccessControl(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.AccessControlEntity loadControlAccessEntityFromControlAcces(com.soffid.iam.api.AccessControl controlAcces) {
    	AccessControlEntity controlAccessEntity = null;
    	if (controlAcces.getId()!=null) {
    		controlAccessEntity = this.load(controlAcces.getId());
    	}
        if (controlAccessEntity == null)
        {
            controlAccessEntity = newAccessControlEntity();
        }
        return controlAccessEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.ControlAccessEntityDao#controlAccesToEntity(es.caib.seycon.ng.comu.ControlAcces)
     */
    public com.soffid.iam.model.AccessControlEntity accessControlToEntity(com.soffid.iam.api.AccessControl controlAcces) {
        // @todo verify behavior of controlAccesToEntity
        com.soffid.iam.model.AccessControlEntity entity = this.loadControlAccessEntityFromControlAcces(controlAcces);
        this.accessControlToEntity(controlAcces, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.ControlAccessEntityDao#controlAccesToEntity(es.caib.seycon.ng.comu.ControlAcces, es.caib.seycon.ng.model.ControlAccessEntity)
     */
    public void accessControlToEntity(com.soffid.iam.api.AccessControl source, com.soffid.iam.model.AccessControlEntity target, boolean copyIfNull) {
        // @todo verify behavior of controlAccesToEntity
        super.accessControlToEntity(source, target, copyIfNull);
        controlAccesToEntityCustom(source, target);
    }
    
	public void toControlAccesCustom(com.soffid.iam.model.AccessControlEntity source, com.soffid.iam.api.AccessControl target) {
		// Ya tenemos program, usuariGeneric y maquinaGeneric
		// Ponemos el resto
		target.setId(source.getId());
		target.setAgentId(source.getAgent().getId());
		target.setAgentName(source.getAgent().getName()); 
/*		if(source.getUsuari()!=null) {
			target.setCodiUsuari(source.getUsuari().getCodi());
			target.setIdUsuari(source.getUsuari().getId());
		} else { // Posem l'usuari genèric com a usuari
			if (source.getUsuariGeneric()!=null)
				target.setCodiUsuari(source.getUsuariGeneric());
		}*/
		if (source.getRole() != null) {
			target.setRoleDescription(source.getRole().getName());
			target.setRoleId(source.getRole().getId());
		}
		/*if (source.getMaquina() !=null) {
			target.setNomMaquina(source.getMaquina().getNom()+ " ["+source.getMaquina().getAdreca()+"]");
			target.setIdMaquina(source.getMaquina().getId());
		} else*/ 
		if (source.getGenericHost() != null) {
			HostEntity maquinaEntity = getHostEntityDao().findByName(source.getGenericHost());
			if (maquinaEntity != null) {
				target.setHostName(maquinaEntity.getName());//+ " ["+maquinaEntity.getAdreca()+"]");
			}
			else {
				target.setHostName(source.getGenericHost());
			}
		}
		
		//}
	}	

	public void controlAccesToEntityCustom(com.soffid.iam.api.AccessControl source, com.soffid.iam.model.AccessControlEntity target) {
		
		SystemEntity agent = null;
		// Obtenim el dispatcher (agent)
		if (source.getAgentName() == null)
			throw new SeyconException (Messages.getString("AccessControlEntityDaoImpl.0"));  //$NON-NLS-1$
		else {
			agent = getSystemEntityDao().findByName(source.getAgentName());
			if (agent!=null)
				target.setAgent(agent);
			else 
				throw new SeyconException (Messages.getString("AccessControlEntityDaoImpl.0"));  //$NON-NLS-1$
		} 
		
		// usuari: pot ésser usuari, genèric o rol
		UserEntity usuari = null;
		RoleEntity rol = null;
		/*if (source.getIdUsuari()!=null) {
			usuari = getUsuariEntityDao().findById(source.getIdUsuari());
			if (usuari==null)
				throw new SeyconException ("Error: No s'ha pogut obtindre l'usuari amb id "+source.getIdUsuari()+ " on se vol establir el control d'accés.");
			target.setUsuari(usuari);
			target.setRol(null);
			target.setUsuariGeneric(null);
		} else */
		if (source.getRoleId() != null && source.getRoleId() != 0) {
			rol = getRoleEntityDao().findById(source.getRoleId());
			if (rol==null)
				throw new SeyconException(String.format(Messages.getString("AccessControlEntityDaoImpl.1"), source.getRoleId()));
			//target.setUsuari(null);
			target.setRole(rol);
			target.setGenericUser(null);
			
		} else {
			if (source.getGenericUser() == null || "".equals(source.getGenericUser().trim())) //$NON-NLS-1$
				throw new SeyconException (Messages.getString("AccessControlEntityDaoImpl.2"));  //$NON-NLS-1$
			//target.setUsuari(null);
			target.setRole(null);
			target.setGenericUser(source.getGenericUser());
		}
		
		// màquina
		/*MaquinaEntity maquina = null;
		if (source.getIdMaquina()!=null) {
			maquina = getMaquinaEntityDao().findById(source.getIdMaquina());
			if (maquina==null)
				throw new SeyconException ("Error: No s'ha trobat la màquina amb id "+source.getIdMaquina()+ " on se vol establir el control d'accés.");
			else if (maquina.getAdreca()==null || "".equals(maquina.getAdreca().trim()))
				throw new SeyconException ("Error: La màquina "+maquina.getNom()+" no té IP assignada, i és necessària per establir el control d'accés.");
			target.setMaquina(maquina);
			target.setMaquinaGeneric(null);
		} else {*/
			if (source.getGenericHost() == null)
				throw new SeyconException (Messages.getString("AccessControlEntityDaoImpl.3"));  //$NON-NLS-1$
			target.setGenericHost(source.getGenericHost());		
		//}
		
		// El program 
		if (source.getProgram()==null || "".equals(source.getProgram().trim())) //$NON-NLS-1$
			throw new SeyconException (Messages.getString("AccessControlEntityDaoImpl.4"));  //$NON-NLS-1$
		
		target.setProgram(source.getProgram());
				
	}
    

}