// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import es.caib.seycon.ng.utils.TipusAutoritzacioPuntEntrada;

/**
 * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntity
 */
public class EntryPointAccountEntityDaoImpl
    extends EntryPointAccountEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntityDao#toAutoritzacioPuntEntrada(es.caib.seycon.ng.model.AutoritzacioPUERolEntity, es.caib.seycon.ng.comu.AutoritzacioPuntEntrada)
     */
    public void toAutoritzacioPuntEntrada(
        EntryPointAccountEntity source,
        es.caib.seycon.ng.comu.AutoritzacioPuntEntrada target)
    {
        // @todo verify behavior of toAutoritzacioPuntEntrada
        super.toAutoritzacioPuntEntrada(source, target);
        toAutoritzacioPuntEntradaCustom(source,target);
    }
    
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntityDao#toAutoritzacioPuntEntrada(es.caib.seycon.ng.model.AutoritzacioPUERolEntity)
     */
    public es.caib.seycon.ng.comu.AutoritzacioPuntEntrada toAutoritzacioPuntEntrada(final EntryPointAccountEntity entity)
    {
        // @todo verify behavior of toAutoritzacioPuntEntrada
        return super.toAutoritzacioPuntEntrada(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private EntryPointAccountEntity loadFromAutoritzacioPuntEntrada(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada autoritzacioPuntEntrada)
    {
    	EntryPointAccountEntity entryPointAccountEntity = null;
    	if (autoritzacioPuntEntrada.getId()!=null)
    		entryPointAccountEntity = this.load(autoritzacioPuntEntrada.getId());
        if (entryPointAccountEntity == null)
        {
        	entryPointAccountEntity = newEntryPointAccountEntity();
        }
        return entryPointAccountEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntityDao#autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada)
     */
    public EntryPointAccountEntity autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada autoritzacioPuntEntrada)
    {
        // @todo verify behavior of autoritzacioPuntEntradaToEntity
        EntryPointAccountEntity entity = this.loadFromAutoritzacioPuntEntrada(autoritzacioPuntEntrada);
        this.autoritzacioPuntEntradaToEntity(autoritzacioPuntEntrada, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntityDao#autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada, es.caib.seycon.ng.model.AutoritzacioPUERolEntity)
     */
    public void autoritzacioPuntEntradaToEntity(
        es.caib.seycon.ng.comu.AutoritzacioPuntEntrada source,
        EntryPointAccountEntity target,
        boolean copyIfNull)
    {
        // @todo verify behavior of autoritzacioPuntEntradaToEntity
        super.autoritzacioPuntEntradaToEntity(source, target, copyIfNull);
        autoritzacioPuntEntradaToEntityCustom(source, target);
    }
    
    
	private void toAutoritzacioPuntEntradaCustom( // Entity to VO
		EntryPointAccountEntity source,
		es.caib.seycon.ng.comu.AutoritzacioPuntEntrada target)
	{
		// Ponemos la descripción del nivel de autorización
		// Hay 2 tipos: Administrador (A) y Autoritzat (C)
		if (TipusAutoritzacioPuntEntrada
				.NIVELL_A.equals(source.getAuthorizationlevel()))
			target.setDescripcioNivellAutoritzacio(
				TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO);
		else
			target.setDescripcioNivellAutoritzacio(
				TipusAutoritzacioPuntEntrada.NIVELL_ALTRES_DESCRIPCIO);
		
		//Punt d'entrada
		target.setIdPuntEntrada(source.getEntryPoint().getId());
		// Informació relacionada amb l'entitat autoritzada
		target.setTipusEntitatAutoritzada(TipusAutoritzacioPuntEntrada.ACCOUNT);
		target.setIdEntitatAutoritzada(source.getAccount().getId());
		// Format de toDescripcioRol: nomRol+"@"+codiBbdd+">"+codiAplicacio)
		String descripcioUnicaRol = source.getAccount().getDescription();
		// Guardem només el nom del rol (com es fa al seycon-net)
		target.setCodiEntitatAutoritzada(source.getAccount().getName()+"@"+source.getAccount().getSystem().getName());
		target.setDescripcioEntitatAutoritzada(descripcioUnicaRol);
	}
	
	private void autoritzacioPuntEntradaToEntityCustom( //VO to Entity
		es.caib.seycon.ng.comu.AutoritzacioPuntEntrada source,
		EntryPointAccountEntity target)
	{
		// Aquí se supone que la entidad está cargada o es nueva..
    	
		// Ponemos el nivel de autorización 
		// Hay 2 tipos: Administrador (A) y Autoritzat (C)
		if ("Administrador".equals(source.getDescripcioNivellAutoritzacio()))  //$NON-NLS-1$
			target.setAuthorizationlevel("A"); //$NON-NLS-1$
		else
			target.setAuthorizationlevel("C"); //$NON-NLS-1$
		
		target.setAccount(getAccountEntityDao().load(source.getIdEntitatAutoritzada()));
	}
}