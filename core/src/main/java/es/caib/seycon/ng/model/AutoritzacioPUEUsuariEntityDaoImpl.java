// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import es.caib.seycon.ng.utils.TipusAutoritzacioPuntEntrada;

/**
 * @see es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity
 */
public class AutoritzacioPUEUsuariEntityDaoImpl
    extends es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntityDao#toAutoritzacioPuntEntrada(es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity, es.caib.seycon.ng.comu.AutoritzacioPuntEntrada)
     */
    public void toAutoritzacioPuntEntrada(
        es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity source,
        es.caib.seycon.ng.comu.AutoritzacioPuntEntrada target)
    {
        // @todo verify behavior of toAutoritzacioPuntEntrada
        super.toAutoritzacioPuntEntrada(source, target);
        
        toAutoritzacioPuntEntradaCustom(source,target);
    }
    
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntityDao#toAutoritzacioPuntEntrada(es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity)
     */
    public es.caib.seycon.ng.comu.AutoritzacioPuntEntrada toAutoritzacioPuntEntrada(final es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity entity)
    {
        // @todo verify behavior of toAutoritzacioPuntEntrada
        return super.toAutoritzacioPuntEntrada(entity);
    }
    
    
    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity loadAutoritzacioPUEUsuariEntityFromAutoritzacioPuntEntrada(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada autoritzacioPuntEntrada)
    {
    	es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity autoritzacioPUEUsuariEntity  = null;
    	if (autoritzacioPuntEntrada.getId()!=null)
    		autoritzacioPUEUsuariEntity = this.load(autoritzacioPuntEntrada.getId());
        if (autoritzacioPUEUsuariEntity == null)
        {
            autoritzacioPUEUsuariEntity = newAutoritzacioPUEUsuariEntity();
        }
        return autoritzacioPUEUsuariEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntityDao#autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada)
     */
    public es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada autoritzacioPuntEntrada)
    {
        // @todo verify behavior of autoritzacioPuntEntradaToEntity
        es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity entity = this.loadAutoritzacioPUEUsuariEntityFromAutoritzacioPuntEntrada(autoritzacioPuntEntrada);
        this.autoritzacioPuntEntradaToEntity(autoritzacioPuntEntrada, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntityDao#autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada, es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity)
     */
    public void autoritzacioPuntEntradaToEntity(
        es.caib.seycon.ng.comu.AutoritzacioPuntEntrada source,
        es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity target,
        boolean copyIfNull)
    {
        // @todo verify behavior of autoritzacioPuntEntradaToEntity
        super.autoritzacioPuntEntradaToEntity(source, target, copyIfNull);
        
        autoritzacioPuntEntradaToEntityCustom(source, target);
    }
    
    
	private void toAutoritzacioPuntEntradaCustom( // Entity to VO
			es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity source,
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
		target.setTipusEntitatAutoritzada(TipusAutoritzacioPuntEntrada.USUARI);
		target.setIdEntitatAutoritzada(source.getUser().getId());
		UsuariEntity usuari = source.getUser();
		target.setDescripcioEntitatAutoritzada(usuari.getNom()+" "+usuari.getPrimerLlinatge()+" "+usuari.getSegonLlinatge()+" ["+usuari.getCodi()+"]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		target.setCodiEntitatAutoritzada(usuari.getCodi());
	}
    
	private void autoritzacioPuntEntradaToEntityCustom( //VO to Entity
			es.caib.seycon.ng.comu.AutoritzacioPuntEntrada source,
			es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity target) {
		
		// Aquí se supone que la entidad está cargada o es nueva..
		target.setUser(getUsuariEntityDao().load(source.getIdEntitatAutoritzada()));
		
		// Ponemos el nivel de autorización 
		// Hay 2 tipos: Administrador (A) y Autoritzat (C)
		if ("Administrador".equals(source.getDescripcioNivellAutoritzacio()))  //$NON-NLS-1$
			target.setNivellAutoritzacio("A"); //$NON-NLS-1$
		else
			target.setNivellAutoritzacio("C"); //$NON-NLS-1$
		

	}

}