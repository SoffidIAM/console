// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import es.caib.seycon.ng.utils.TipusAutoritzacioPuntEntrada;

/**
 * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntity
 */
public class AutoritzacioPUERolEntityDaoImpl
    extends es.caib.seycon.ng.model.AutoritzacioPUERolEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntityDao#toAutoritzacioPuntEntrada(es.caib.seycon.ng.model.AutoritzacioPUERolEntity, es.caib.seycon.ng.comu.AutoritzacioPuntEntrada)
     */
    public void toAutoritzacioPuntEntrada(
        es.caib.seycon.ng.model.AutoritzacioPUERolEntity source,
        es.caib.seycon.ng.comu.AutoritzacioPuntEntrada target)
    {
        // @todo verify behavior of toAutoritzacioPuntEntrada
        super.toAutoritzacioPuntEntrada(source, target);
        toAutoritzacioPuntEntradaCustom(source,target);
    }
    
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntityDao#toAutoritzacioPuntEntrada(es.caib.seycon.ng.model.AutoritzacioPUERolEntity)
     */
    public es.caib.seycon.ng.comu.AutoritzacioPuntEntrada toAutoritzacioPuntEntrada(final es.caib.seycon.ng.model.AutoritzacioPUERolEntity entity)
    {
        // @todo verify behavior of toAutoritzacioPuntEntrada
        return super.toAutoritzacioPuntEntrada(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private es.caib.seycon.ng.model.AutoritzacioPUERolEntity loadAutoritzacioPUERolEntityFromAutoritzacioPuntEntrada(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada autoritzacioPuntEntrada)
    {
    	es.caib.seycon.ng.model.AutoritzacioPUERolEntity autoritzacioPUERolEntity = null;
    	if (autoritzacioPuntEntrada.getId()!=null)
    		autoritzacioPUERolEntity = this.load(autoritzacioPuntEntrada.getId());
        if (autoritzacioPUERolEntity == null)
        {
            autoritzacioPUERolEntity = newAutoritzacioPUERolEntity();
        }
        return autoritzacioPUERolEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntityDao#autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada)
     */
    public es.caib.seycon.ng.model.AutoritzacioPUERolEntity autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada autoritzacioPuntEntrada)
    {
        // @todo verify behavior of autoritzacioPuntEntradaToEntity
        es.caib.seycon.ng.model.AutoritzacioPUERolEntity entity = this.loadAutoritzacioPUERolEntityFromAutoritzacioPuntEntrada(autoritzacioPuntEntrada);
        this.autoritzacioPuntEntradaToEntity(autoritzacioPuntEntrada, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntityDao#autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada, es.caib.seycon.ng.model.AutoritzacioPUERolEntity)
     */
    public void autoritzacioPuntEntradaToEntity(
        es.caib.seycon.ng.comu.AutoritzacioPuntEntrada source,
        es.caib.seycon.ng.model.AutoritzacioPUERolEntity target,
        boolean copyIfNull)
    {
        // @todo verify behavior of autoritzacioPuntEntradaToEntity
        super.autoritzacioPuntEntradaToEntity(source, target, copyIfNull);
        autoritzacioPuntEntradaToEntityCustom(source, target);
    }
    
    
	private void toAutoritzacioPuntEntradaCustom( // Entity to VO
		es.caib.seycon.ng.model.AutoritzacioPUERolEntity source,
		es.caib.seycon.ng.comu.AutoritzacioPuntEntrada target)
	{
		// Ponemos la descripción del nivel de autorización
		// Hay 2 tipos: Administrador (A) y Autoritzat (C)
		if (TipusAutoritzacioPuntEntrada
				.NIVELL_A.equals(source.getNivellAutoritzacio()))
			target.setDescripcioNivellAutoritzacio(
				TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO);
		else
			target.setDescripcioNivellAutoritzacio(
				TipusAutoritzacioPuntEntrada.NIVELL_ALTRES_DESCRIPCIO);
		
		//Punt d'entrada
		target.setIdPuntEntrada(source.getPuntEntrada().getId());
		// Informació relacionada amb l'entitat autoritzada
		target.setTipusEntitatAutoritzada(TipusAutoritzacioPuntEntrada.ROL);
		target.setIdEntitatAutoritzada(source.getIdRol());
		RolEntity rol = getRolEntityDao().findById(source.getIdRol());
		// Format de toDescripcioRol: nomRol+"@"+codiBbdd+">"+codiAplicacio)
		String descripcioUnicaRol = rol.toDescripcioRol();
		// Guardem només el nom del rol (com es fa al seycon-net)
		target.setCodiEntitatAutoritzada(rol.getNom() + "@" + //$NON-NLS-1$
			rol.getBaseDeDades().getCodi());
		target.setDescripcioEntitatAutoritzada(descripcioUnicaRol);
	}
	
	private void autoritzacioPuntEntradaToEntityCustom( //VO to Entity
		es.caib.seycon.ng.comu.AutoritzacioPuntEntrada source,
		es.caib.seycon.ng.model.AutoritzacioPUERolEntity target)
	{
		// Aquí se supone que la entidad está cargada o es nueva..
    	
		// Ponemos el nivel de autorización 
		// Hay 2 tipos: Administrador (A) y Autoritzat (C)
		if ("Administrador".equals(source.getDescripcioNivellAutoritzacio()))  //$NON-NLS-1$
			target.setNivellAutoritzacio("A"); //$NON-NLS-1$
		else
			target.setNivellAutoritzacio("C"); //$NON-NLS-1$
		
		target.setIdRol(source.getIdEntitatAutoritzada());
	}
}