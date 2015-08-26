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
    public void toUsuariWFProcess(com.soffid.iam.model.UserProcessEntity source, es.caib.seycon.ng.comu.UsuariWFProcess target) {
        // @todo verify behavior of toUsuariWFProcess
        super.toUsuariWFProcess(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.UsuariWFProcessEntityDao#toUsuariWFProcess(es.caib.seycon.ng.model.UsuariWFProcessEntity)
     */
    public es.caib.seycon.ng.comu.UsuariWFProcess toUsuariWFProcess(final com.soffid.iam.model.UserProcessEntity entity) {
        // @todo verify behavior of toUsuariWFProcess
        return super.toUsuariWFProcess(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.UserProcessEntity loadUsuariWFProcessEntityFromUsuariWFProcess(es.caib.seycon.ng.comu.UsuariWFProcess usuariWFProcess) {

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
    public com.soffid.iam.model.UserProcessEntity usuariWFProcessToEntity(es.caib.seycon.ng.comu.UsuariWFProcess usuariWFProcess) {
        // @todo verify behavior of usuariWFProcessToEntity
        com.soffid.iam.model.UserProcessEntity entity = this.loadUsuariWFProcessEntityFromUsuariWFProcess(usuariWFProcess);
        this.usuariWFProcessToEntity(usuariWFProcess, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.UsuariWFProcessEntityDao#usuariWFProcessToEntity(es.caib.seycon.ng.comu.UsuariWFProcess, es.caib.seycon.ng.model.UsuariWFProcessEntity)
     */
    public void usuariWFProcessToEntity(es.caib.seycon.ng.comu.UsuariWFProcess source, com.soffid.iam.model.UserProcessEntity target, boolean copyIfNull) {
        // @todo verify behavior of usuariWFProcessToEntity
        super.usuariWFProcessToEntity(source, target, copyIfNull);
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
