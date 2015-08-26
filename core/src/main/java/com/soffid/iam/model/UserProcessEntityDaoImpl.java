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

import com.soffid.iam.model.UserProcessEntity;

/**
 * @see es.caib.seycon.ng.model.UsuariWFProcessEntity
 */
public class UserProcessEntityDaoImpl
    extends com.soffid.iam.model.UserProcessEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.UsuariWFProcessEntityDao#toUsuariWFProcess(es.caib.seycon.ng.model.UsuariWFProcessEntity, es.caib.seycon.ng.comu.UsuariWFProcess)
     */
    public void toBpmUserProcess(com.soffid.iam.model.UserProcessEntity source, com.soffid.iam.api.BpmUserProcess target) {
        // @todo verify behavior of toUsuariWFProcess
        super.toBpmUserProcess(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.UsuariWFProcessEntityDao#toUsuariWFProcess(es.caib.seycon.ng.model.UsuariWFProcessEntity)
     */
    public com.soffid.iam.api.BpmUserProcess toBpmUserProcess(final com.soffid.iam.model.UserProcessEntity entity) {
        // @todo verify behavior of toUsuariWFProcess
        return super.toBpmUserProcess(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.UserProcessEntity loadUsuariWFProcessEntityFromUsuariWFProcess(com.soffid.iam.api.BpmUserProcess usuariWFProcess) {

		com.soffid.iam.model.UserProcessEntity usuariWFProcessEntity = null;
		
		if (usuariWFProcess.getId() !=null) {
			usuariWFProcessEntity = load(usuariWFProcess.getId());
		}
		
        if (usuariWFProcessEntity == null)
        {
            usuariWFProcessEntity = newUserProcessEntity();
        }
        return usuariWFProcessEntity;

    }

    
    /**
     * @see es.caib.seycon.ng.model.UsuariWFProcessEntityDao#usuariWFProcessToEntity(es.caib.seycon.ng.comu.UsuariWFProcess)
     */
    public com.soffid.iam.model.UserProcessEntity bpmUserProcessToEntity(com.soffid.iam.api.BpmUserProcess usuariWFProcess) {
        // @todo verify behavior of usuariWFProcessToEntity
        com.soffid.iam.model.UserProcessEntity entity = this.loadUsuariWFProcessEntityFromUsuariWFProcess(usuariWFProcess);
        this.bpmUserProcessToEntity(usuariWFProcess, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.UsuariWFProcessEntityDao#usuariWFProcessToEntity(es.caib.seycon.ng.comu.UsuariWFProcess, es.caib.seycon.ng.model.UsuariWFProcessEntity)
     */
    public void bpmUserProcessToEntity(com.soffid.iam.api.BpmUserProcess source, com.soffid.iam.model.UserProcessEntity target, boolean copyIfNull) {
        // @todo verify behavior of usuariWFProcessToEntity
        super.bpmUserProcessToEntity(source, target, copyIfNull);
    }


	@Override
    public void create(UserProcessEntity usuariWFProcessEntity) {
		super.create(usuariWFProcessEntity);
		// fem un flush
		getSession(false).flush();
	}


	@Override
    public void update(UserProcessEntity usuariWFProcessEntity) {
		super.update(usuariWFProcessEntity);
		// fem un flush
		getSession(false).flush();
	}


	@Override
    public void remove(UserProcessEntity usuariWFProcessEntity) {
		super.remove(usuariWFProcessEntity);
		// fem un flush
		getSession(false).flush();
	}

}
