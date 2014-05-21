// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;


/**
 * @see es.caib.seycon.ng.model.ExecucioPuntEntradaEntity
 */
public class ExecucioPuntEntradaEntityDaoImpl
    extends es.caib.seycon.ng.model.ExecucioPuntEntradaEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.ExecucioPuntEntradaEntityDao#toExecucioPuntEntrada(es.caib.seycon.ng.model.ExecucioPuntEntradaEntity, es.caib.seycon.ng.comu.ExecucioPuntEntrada)
     */
    public void toExecucioPuntEntrada(
        es.caib.seycon.ng.model.ExecucioPuntEntradaEntity source,
        es.caib.seycon.ng.comu.ExecucioPuntEntrada target)
    {
        // @todo verify behavior of toExecucioPuntEntrada
        super.toExecucioPuntEntrada(source, target);
        // WARNING! No conversion for target.codiTipusExecucio (can't convert source.getCodiTipusExecucio():es.caib.seycon.ng.model.TipusExecucioPuntEntradaEntity to java.lang.String
        toExecucioPuntEntradaCustom(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.ExecucioPuntEntradaEntityDao#toExecucioPuntEntrada(es.caib.seycon.ng.model.ExecucioPuntEntradaEntity)
     */
    public es.caib.seycon.ng.comu.ExecucioPuntEntrada toExecucioPuntEntrada(final es.caib.seycon.ng.model.ExecucioPuntEntradaEntity entity)
    {
        // @todo verify behavior of toExecucioPuntEntrada
        return super.toExecucioPuntEntrada(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private es.caib.seycon.ng.model.ExecucioPuntEntradaEntity loadExecucioPuntEntradaEntityFromExecucioPuntEntrada(es.caib.seycon.ng.comu.ExecucioPuntEntrada execucioPuntEntrada)
    {
    	es.caib.seycon.ng.model.ExecucioPuntEntradaEntity execucioPuntEntradaEntity = null;
    	if (execucioPuntEntrada.getId()!=null)
    		execucioPuntEntradaEntity = this.load(execucioPuntEntrada.getId());
        if (execucioPuntEntradaEntity == null)
        {
            execucioPuntEntradaEntity = newExecucioPuntEntradaEntity();
        }
        return execucioPuntEntradaEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.ExecucioPuntEntradaEntityDao#execucioPuntEntradaToEntity(es.caib.seycon.ng.comu.ExecucioPuntEntrada)
     */
    public es.caib.seycon.ng.model.ExecucioPuntEntradaEntity execucioPuntEntradaToEntity(es.caib.seycon.ng.comu.ExecucioPuntEntrada execucioPuntEntrada)
    {
        // @todo verify behavior of execucioPuntEntradaToEntity
        es.caib.seycon.ng.model.ExecucioPuntEntradaEntity entity = this.loadExecucioPuntEntradaEntityFromExecucioPuntEntrada(execucioPuntEntrada);
        this.execucioPuntEntradaToEntity(execucioPuntEntrada, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.ExecucioPuntEntradaEntityDao#execucioPuntEntradaToEntity(es.caib.seycon.ng.comu.ExecucioPuntEntrada, es.caib.seycon.ng.model.ExecucioPuntEntradaEntity)
     */
    public void execucioPuntEntradaToEntity(
        es.caib.seycon.ng.comu.ExecucioPuntEntrada source,
        es.caib.seycon.ng.model.ExecucioPuntEntradaEntity target,
        boolean copyIfNull)
    {
        // @todo verify behavior of execucioPuntEntradaToEntity
        super.execucioPuntEntradaToEntity(source, target, copyIfNull);
        
        execucioPuntEntradaToEntityCustom(source, target);
    }
    
	private void execucioPuntEntradaToEntityCustom(
			es.caib.seycon.ng.comu.ExecucioPuntEntrada source,
			es.caib.seycon.ng.model.ExecucioPuntEntradaEntity target) { //VO to Entity
		
		// [id], ambit y contingut ens vénen donats en la transformació
		// Tipus d'execució
		target.setCodiExecucio(source.getCodiTipusExecucio());
		
		// Posem el punt d'entrada
		if (source.getIdPuntEntrada()!=null) {
			target.setPuntEntrada(getPuntEntradaEntityDao().findById(source.getIdPuntEntrada()));
		}


	}    
    
	private void toExecucioPuntEntradaCustom( // Entity to VO
			es.caib.seycon.ng.model.ExecucioPuntEntradaEntity source,
			es.caib.seycon.ng.comu.ExecucioPuntEntrada target) {

		// id, ambit y contingut ens vénen donats en la transformació
		// Assignem tipus d'execució
		if (source.getCodiExecucio()!=null) {
			TipusExecucioPuntEntradaEntity tipusExe = getTipusExecucioPuntEntradaEntityDao().findByCodi(source.getCodiExecucio());
			target.setCodiTipusExecucio(tipusExe.getCodi());
			target.setTipusMimeExecucio(tipusExe.getTipusMime());
		}
		else {
			target.setCodiTipusExecucio(""); //$NON-NLS-1$
			target.setTipusMimeExecucio(""); //$NON-NLS-1$
		}
		if (source.getPuntEntrada()!=null) 
			target.setIdPuntEntrada(source.getPuntEntrada().getId());

	}
	
	

}