// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import es.caib.seycon.ng.utils.TipusAutoritzacioPuntEntrada;

/**
 * @see es.caib.seycon.ng.model.AutoritzacioPUEGrupEntity
 */
public class AutoritzacioPUEGrupEntityDaoImpl
    extends es.caib.seycon.ng.model.AutoritzacioPUEGrupEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUEGrupEntityDao#toAutoritzacioPuntEntrada(es.caib.seycon.ng.model.AutoritzacioPUEGrupEntity, es.caib.seycon.ng.comu.AutoritzacioPuntEntrada)
     */
    public void toAutoritzacioPuntEntrada(
        es.caib.seycon.ng.model.AutoritzacioPUEGrupEntity source,
        es.caib.seycon.ng.comu.AutoritzacioPuntEntrada target)
    {
        // @todo verify behavior of toAutoritzacioPuntEntrada
        super.toAutoritzacioPuntEntrada(source, target);
        
        toAutoritzacioPuntEntradaCustom(source,target);
    }
    
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUEGrupEntityDao#toAutoritzacioPuntEntrada(es.caib.seycon.ng.model.AutoritzacioPUEGrupEntity)
     */
    public es.caib.seycon.ng.comu.AutoritzacioPuntEntrada toAutoritzacioPuntEntrada(final es.caib.seycon.ng.model.AutoritzacioPUEGrupEntity entity)
    {
        // @todo verify behavior of toAutoritzacioPuntEntrada
        return super.toAutoritzacioPuntEntrada(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private es.caib.seycon.ng.model.AutoritzacioPUEGrupEntity loadAutoritzacioPUEGrupEntityFromAutoritzacioPuntEntrada(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada autoritzacioPuntEntrada)
    {
    	es.caib.seycon.ng.model.AutoritzacioPUEGrupEntity autoritzacioPUEGrupEntity = null;
    	if (autoritzacioPuntEntrada.getId()!=null)
    		autoritzacioPUEGrupEntity = this.load(autoritzacioPuntEntrada.getId());
        if (autoritzacioPUEGrupEntity == null)
        {
            autoritzacioPUEGrupEntity = newAutoritzacioPUEGrupEntity();
        }
        return autoritzacioPUEGrupEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUEGrupEntityDao#autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada)
     */
    public es.caib.seycon.ng.model.AutoritzacioPUEGrupEntity autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada autoritzacioPuntEntrada)
    {
        // @todo verify behavior of autoritzacioPuntEntradaToEntity
        es.caib.seycon.ng.model.AutoritzacioPUEGrupEntity entity = this.loadAutoritzacioPUEGrupEntityFromAutoritzacioPuntEntrada(autoritzacioPuntEntrada);
        this.autoritzacioPuntEntradaToEntity(autoritzacioPuntEntrada, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUEGrupEntityDao#autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada, es.caib.seycon.ng.model.AutoritzacioPUEGrupEntity)
     */
    public void autoritzacioPuntEntradaToEntity(
        es.caib.seycon.ng.comu.AutoritzacioPuntEntrada source,
        es.caib.seycon.ng.model.AutoritzacioPUEGrupEntity target,
        boolean copyIfNull)
    {
        // @todo verify behavior of autoritzacioPuntEntradaToEntity
        super.autoritzacioPuntEntradaToEntity(source, target, copyIfNull);
        autoritzacioPuntEntradaToEntityCustom(source, target);
    }
    
    
	private void toAutoritzacioPuntEntradaCustom( // Entity to VO
			es.caib.seycon.ng.model.AutoritzacioPUEGrupEntity source,
			es.caib.seycon.ng.comu.AutoritzacioPuntEntrada target) {
		//Ponemos el ID de la entidad
		target.setId(source.getId());
		
		// Ponemos la descripción del nivel de autorización
		// Hay 2 tipos: Administrador (A) y Autoritzat (C)
		if (TipusAutoritzacioPuntEntrada.NIVELL_A.equals(source.getNivellAutoritzacio()))
			target.setDescripcioNivellAutoritzacio(TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO);
		else
			target.setDescripcioNivellAutoritzacio(TipusAutoritzacioPuntEntrada.NIVELL_ALTRES_DESCRIPCIO);		
		
		//Punt d'entrada
		target.setIdPuntEntrada(source.getPuntEntrada().getId());
		// Informació relacionada amb l'entitat autoritzada
		target.setTipusEntitatAutoritzada(TipusAutoritzacioPuntEntrada.GRUP);
		target.setIdEntitatAutoritzada(source.getGroup().getId());
		GrupEntity grup = source.getGroup();
		target.setDescripcioEntitatAutoritzada(grup.getDescripcio()+" ["+grup.getCodi()+"]"); //$NON-NLS-1$ //$NON-NLS-2$
		target.setCodiEntitatAutoritzada(grup.getCodi());
	}
    
    private void autoritzacioPuntEntradaToEntityCustom( //VO to Entity
			es.caib.seycon.ng.comu.AutoritzacioPuntEntrada source,
			es.caib.seycon.ng.model.AutoritzacioPUEGrupEntity target) {

		// Aquí se supone que la entidad está cargada o es nueva..
		
		// Ponemos el nivel de autorización 
		// Hay 2 tipos: Administrador (A) y Autoritzat (C)
		if ("Administrador".equals(source.getDescripcioNivellAutoritzacio()))  //$NON-NLS-1$
			target.setNivellAutoritzacio("A"); //$NON-NLS-1$
		else
			target.setNivellAutoritzacio("C"); //$NON-NLS-1$
		
		target.setGroup(getGrupEntityDao().load(source.getIdEntitatAutoritzada()));
	}

}