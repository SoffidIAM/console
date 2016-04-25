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

import com.soffid.iam.model.EntryPointExecutionTypeEntity;

/**
 * @see es.caib.seycon.ng.model.ExecucioPuntEntradaEntity
 */
public class EntryPointExecutableEntityDaoImpl
    extends com.soffid.iam.model.EntryPointExecutableEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.ExecucioPuntEntradaEntityDao#toExecucioPuntEntrada(es.caib.seycon.ng.model.ExecucioPuntEntradaEntity, es.caib.seycon.ng.comu.ExecucioPuntEntrada)
     */
    public void toAccessTreeExecution(com.soffid.iam.model.EntryPointExecutableEntity source, com.soffid.iam.api.AccessTreeExecution target) {
        // @todo verify behavior of toExecucioPuntEntrada
        super.toAccessTreeExecution(source, target);
        // WARNING! No conversion for target.codiTipusExecucio (can't convert source.getCodiTipusExecucio():es.caib.seycon.ng.model.TipusExecucioPuntEntradaEntity to java.lang.String
        toExecucioPuntEntradaCustom(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.ExecucioPuntEntradaEntityDao#toExecucioPuntEntrada(es.caib.seycon.ng.model.ExecucioPuntEntradaEntity)
     */
    public com.soffid.iam.api.AccessTreeExecution toAccessTreeExecution(final com.soffid.iam.model.EntryPointExecutableEntity entity) {
        // @todo verify behavior of toExecucioPuntEntrada
        return super.toAccessTreeExecution(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.EntryPointExecutableEntity loadExecucioPuntEntradaEntityFromExecucioPuntEntrada(com.soffid.iam.api.AccessTreeExecution execucioPuntEntrada) {
    	com.soffid.iam.model.EntryPointExecutableEntity execucioPuntEntradaEntity = null;
    	if (execucioPuntEntrada.getId()!=null)
    		execucioPuntEntradaEntity = this.load(execucioPuntEntrada.getId());
        if (execucioPuntEntradaEntity == null)
        {
            execucioPuntEntradaEntity = newEntryPointExecutableEntity();
        }
        return execucioPuntEntradaEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.ExecucioPuntEntradaEntityDao#execucioPuntEntradaToEntity(es.caib.seycon.ng.comu.ExecucioPuntEntrada)
     */
    public com.soffid.iam.model.EntryPointExecutableEntity accessTreeExecutionToEntity(com.soffid.iam.api.AccessTreeExecution execucioPuntEntrada) {
        // @todo verify behavior of execucioPuntEntradaToEntity
        com.soffid.iam.model.EntryPointExecutableEntity entity = this.loadExecucioPuntEntradaEntityFromExecucioPuntEntrada(execucioPuntEntrada);
        this.accessTreeExecutionToEntity(execucioPuntEntrada, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.ExecucioPuntEntradaEntityDao#execucioPuntEntradaToEntity(es.caib.seycon.ng.comu.ExecucioPuntEntrada, es.caib.seycon.ng.model.ExecucioPuntEntradaEntity)
     */
    public void accessTreeExecutionToEntity(com.soffid.iam.api.AccessTreeExecution source, com.soffid.iam.model.EntryPointExecutableEntity target, boolean copyIfNull) {
        // @todo verify behavior of execucioPuntEntradaToEntity
        super.accessTreeExecutionToEntity(source, target, copyIfNull);
        
        execucioPuntEntradaToEntityCustom(source, target);
    }
    
	private void execucioPuntEntradaToEntityCustom(com.soffid.iam.api.AccessTreeExecution source, com.soffid.iam.model.EntryPointExecutableEntity target) { //VO to Entity
		
		// [id], ambit y contingut ens vénen donats en la transformació
		// Tipus d'execució
		target.setExecutionCode(source.getExecutionTypeCode());
		
		// Posem el punt d'entrada
		if (source.getAccessTreeId() != null) {
			target.setEntryPoint(getEntryPointEntityDao().load(source.getAccessTreeId()));
		}


	}    
    
	private void toExecucioPuntEntradaCustom(com.soffid.iam.model.EntryPointExecutableEntity source, com.soffid.iam.api.AccessTreeExecution target) {

		// id, ambit y contingut ens vénen donats en la transformació
		// Assignem tipus d'execució
		if (source.getExecutionCode() != null) {
			EntryPointExecutionTypeEntity tipusExe = getEntryPointExecutionTypeEntityDao().findByName(source.getExecutionCode());
			target.setExecutionTypeCode(tipusExe.getName());
			target.setTypeMimeExecution(tipusExe.getMimeType());
		}
		else {
			target.setExecutionTypeCode(""); //$NON-NLS-1$
			target.setTypeMimeExecution(""); //$NON-NLS-1$
		}
		if (source.getEntryPoint() != null) 
			target.setAccessTreeId(source.getEntryPoint().getId());

	}
	
	

}