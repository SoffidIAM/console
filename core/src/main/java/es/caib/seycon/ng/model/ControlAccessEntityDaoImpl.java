// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.sql.Timestamp;

import es.caib.seycon.ng.comu.Maquina;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.sync.engine.TaskHandler;

/**
 * @see es.caib.seycon.ng.model.ControlAccessEntity
 */
public class ControlAccessEntityDaoImpl
    extends es.caib.seycon.ng.model.ControlAccessEntityDaoBase
{
    @Override
    public void create(ControlAccessEntity controlAccessEntity) {
        super.create(controlAccessEntity);
        createTask(controlAccessEntity);
    }


    @Override
    public void update(ControlAccessEntity controlAccessEntity) {
        super.update(controlAccessEntity);
        createTask(controlAccessEntity);
    }


    @Override
    public void remove(ControlAccessEntity controlAccessEntity) {
        createTask(controlAccessEntity);
        super.remove(controlAccessEntity);
    }


    private void createTask (ControlAccessEntity control) {
        TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
        tasque.setData(new Timestamp(System.currentTimeMillis()));
        tasque.setTransa(TaskHandler.UPDATE_ACESS_CONTROL);
        tasque.setCoddis(control.getAgent().getCodi());
        getTasqueEntityDao().create(tasque);
    }
    /**
     * @see es.caib.seycon.ng.model.ControlAccessEntityDao#toControlAcces(es.caib.seycon.ng.model.ControlAccessEntity, es.caib.seycon.ng.comu.ControlAcces)
     */
    public void toControlAcces(
        es.caib.seycon.ng.model.ControlAccessEntity source,
        es.caib.seycon.ng.comu.ControlAcces target)
    {
        // @todo verify behavior of toControlAcces
        super.toControlAcces(source, target);
        toControlAccesCustom(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.ControlAccessEntityDao#toControlAcces(es.caib.seycon.ng.model.ControlAccessEntity)
     */
    public es.caib.seycon.ng.comu.ControlAcces toControlAcces(final es.caib.seycon.ng.model.ControlAccessEntity entity)
    {
        // @todo verify behavior of toControlAcces
        return super.toControlAcces(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private es.caib.seycon.ng.model.ControlAccessEntity loadControlAccessEntityFromControlAcces(es.caib.seycon.ng.comu.ControlAcces controlAcces)
    {
    	ControlAccessEntity controlAccessEntity = null;
    	if (controlAcces.getId()!=null) {
    		controlAccessEntity = this.load(controlAcces.getId());
    	}
        if (controlAccessEntity == null)
        {
            controlAccessEntity = newControlAccessEntity();
        }
        return controlAccessEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.ControlAccessEntityDao#controlAccesToEntity(es.caib.seycon.ng.comu.ControlAcces)
     */
    public es.caib.seycon.ng.model.ControlAccessEntity controlAccesToEntity(es.caib.seycon.ng.comu.ControlAcces controlAcces)
    {
        // @todo verify behavior of controlAccesToEntity
        es.caib.seycon.ng.model.ControlAccessEntity entity = this.loadControlAccessEntityFromControlAcces(controlAcces);
        this.controlAccesToEntity(controlAcces, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.ControlAccessEntityDao#controlAccesToEntity(es.caib.seycon.ng.comu.ControlAcces, es.caib.seycon.ng.model.ControlAccessEntity)
     */
    public void controlAccesToEntity(
        es.caib.seycon.ng.comu.ControlAcces source,
        es.caib.seycon.ng.model.ControlAccessEntity target,
        boolean copyIfNull)
    {
        // @todo verify behavior of controlAccesToEntity
        super.controlAccesToEntity(source, target, copyIfNull);
        controlAccesToEntityCustom(source, target);
    }
    
	public void toControlAccesCustom(
			es.caib.seycon.ng.model.ControlAccessEntity source,
			es.caib.seycon.ng.comu.ControlAcces target) {
		// Ya tenemos program, usuariGeneric y maquinaGeneric
		// Ponemos el resto
		target.setId(source.getId());
		target.setIdAgent(source.getAgent().getId());
		target.setNomAgent(source.getAgent().getCodi()); 
/*		if(source.getUsuari()!=null) {
			target.setCodiUsuari(source.getUsuari().getCodi());
			target.setIdUsuari(source.getUsuari().getId());
		} else { // Posem l'usuari genèric com a usuari
			if (source.getUsuariGeneric()!=null)
				target.setCodiUsuari(source.getUsuariGeneric());
		}*/
		if (source.getRol() != null) {
			target.setDescripcioRol(source.getRol().getNom());
			target.setIdRol(source.getRol().getId());
		}
		/*if (source.getMaquina() !=null) {
			target.setNomMaquina(source.getMaquina().getNom()+ " ["+source.getMaquina().getAdreca()+"]");
			target.setIdMaquina(source.getMaquina().getId());
		} else*/ 
		if (source.getMaquinaGeneric() !=null) {
			MaquinaEntity maquinaEntity = getMaquinaEntityDao().findByNom(source.getMaquinaGeneric());
			if (maquinaEntity != null) {
				target.setNomMaquina(maquinaEntity.getNom());//+ " ["+maquinaEntity.getAdreca()+"]");
			}
			else {
				target.setNomMaquina(source.getMaquinaGeneric());
			}
		}
		
		//}
	}	

	public void controlAccesToEntityCustom(
			es.caib.seycon.ng.comu.ControlAcces source,
			es.caib.seycon.ng.model.ControlAccessEntity target) {
		
		DispatcherEntity agent = null;
		// Obtenim el dispatcher (agent)
		if (source.getNomAgent()==null)
			throw new SeyconException (Messages.getString("ControlAccessEntityDaoImpl.0"));  //$NON-NLS-1$
		else {
			agent = getDispatcherEntityDao().findByCodi(source.getNomAgent());
			if (agent!=null)
				target.setAgent(agent);
			else 
				throw new SeyconException (Messages.getString("ControlAccessEntityDaoImpl.0"));  //$NON-NLS-1$
		} 
		
		// usuari: pot ésser usuari, genèric o rol
		UsuariEntity usuari = null;
		RolEntity rol = null;
		/*if (source.getIdUsuari()!=null) {
			usuari = getUsuariEntityDao().findById(source.getIdUsuari());
			if (usuari==null)
				throw new SeyconException ("Error: No s'ha pogut obtindre l'usuari amb id "+source.getIdUsuari()+ " on se vol establir el control d'accés.");
			target.setUsuari(usuari);
			target.setRol(null);
			target.setUsuariGeneric(null);
		} else */
		if (source.getIdRol() != null && source.getIdRol()!=0) {
			rol = getRolEntityDao().findById(source.getIdRol());
			if (rol==null)
				throw new SeyconException(String.format(
						Messages.getString("ControlAccessEntityDaoImpl.1"),   //$NON-NLS-1$
						source.getIdRol()));
			//target.setUsuari(null);
			target.setRol(rol);
			target.setUsuariGeneric(null);
			
		} else {
			if (source.getUsuariGeneric()==null || "".equals(source.getUsuariGeneric().trim())) //$NON-NLS-1$
				throw new SeyconException (Messages.getString("ControlAccessEntityDaoImpl.2"));  //$NON-NLS-1$
			//target.setUsuari(null);
			target.setRol(null);
			target.setUsuariGeneric(source.getUsuariGeneric());
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
			if (source.getMaquinaGeneric()==null)
				throw new SeyconException (Messages.getString("ControlAccessEntityDaoImpl.3"));  //$NON-NLS-1$
			target.setMaquinaGeneric(source.getMaquinaGeneric());		
		//}
		
		// El program 
		if (source.getProgram()==null || "".equals(source.getProgram().trim())) //$NON-NLS-1$
			throw new SeyconException (Messages.getString("ControlAccessEntityDaoImpl.4"));  //$NON-NLS-1$
		
		target.setProgram(source.getProgram());
				
	}
    

}