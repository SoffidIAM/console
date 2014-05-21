// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;
/**
 * @see es.caib.seycon.ng.model.UsuariWFProcessEntity
 */
public class UsuariWFProcessEntityDaoImpl
    extends es.caib.seycon.ng.model.UsuariWFProcessEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.UsuariWFProcessEntityDao#toUsuariWFProcess(es.caib.seycon.ng.model.UsuariWFProcessEntity, es.caib.seycon.ng.comu.UsuariWFProcess)
     */
    public void toUsuariWFProcess(
        es.caib.seycon.ng.model.UsuariWFProcessEntity source,
        es.caib.seycon.ng.comu.UsuariWFProcess target)
    {
        // @todo verify behavior of toUsuariWFProcess
        super.toUsuariWFProcess(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.UsuariWFProcessEntityDao#toUsuariWFProcess(es.caib.seycon.ng.model.UsuariWFProcessEntity)
     */
    public es.caib.seycon.ng.comu.UsuariWFProcess toUsuariWFProcess(final es.caib.seycon.ng.model.UsuariWFProcessEntity entity)
    {
        // @todo verify behavior of toUsuariWFProcess
        return super.toUsuariWFProcess(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private es.caib.seycon.ng.model.UsuariWFProcessEntity loadUsuariWFProcessEntityFromUsuariWFProcess(es.caib.seycon.ng.comu.UsuariWFProcess usuariWFProcess)
    {

		es.caib.seycon.ng.model.UsuariWFProcessEntity usuariWFProcessEntity = null;
		
		if (usuariWFProcess.getId() !=null) {
			usuariWFProcessEntity = load(usuariWFProcess.getId());
		}
		
        if (usuariWFProcessEntity == null)
        {
            usuariWFProcessEntity = newUsuariWFProcessEntity();
        }
        return usuariWFProcessEntity;

    }

    
    /**
     * @see es.caib.seycon.ng.model.UsuariWFProcessEntityDao#usuariWFProcessToEntity(es.caib.seycon.ng.comu.UsuariWFProcess)
     */
    public es.caib.seycon.ng.model.UsuariWFProcessEntity usuariWFProcessToEntity(es.caib.seycon.ng.comu.UsuariWFProcess usuariWFProcess)
    {
        // @todo verify behavior of usuariWFProcessToEntity
        es.caib.seycon.ng.model.UsuariWFProcessEntity entity = this.loadUsuariWFProcessEntityFromUsuariWFProcess(usuariWFProcess);
        this.usuariWFProcessToEntity(usuariWFProcess, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.UsuariWFProcessEntityDao#usuariWFProcessToEntity(es.caib.seycon.ng.comu.UsuariWFProcess, es.caib.seycon.ng.model.UsuariWFProcessEntity)
     */
    public void usuariWFProcessToEntity(
        es.caib.seycon.ng.comu.UsuariWFProcess source,
        es.caib.seycon.ng.model.UsuariWFProcessEntity target,
        boolean copyIfNull)
    {
        // @todo verify behavior of usuariWFProcessToEntity
        super.usuariWFProcessToEntity(source, target, copyIfNull);
    }


	@Override
	public void create(UsuariWFProcessEntity usuariWFProcessEntity) {
		super.create(usuariWFProcessEntity);
		// fem un flush
		getSession(false).flush();
	}


	@Override
	public void update(UsuariWFProcessEntity usuariWFProcessEntity) {
		super.update(usuariWFProcessEntity);
		// fem un flush
		getSession(false).flush();
	}


	@Override
	public void remove(UsuariWFProcessEntity usuariWFProcessEntity) {
		super.remove(usuariWFProcessEntity);
		// fem un flush
		getSession(false).flush();
	}

}
