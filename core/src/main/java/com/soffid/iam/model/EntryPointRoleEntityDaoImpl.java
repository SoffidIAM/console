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

import com.soffid.iam.model.RoleEntity;
import es.caib.seycon.ng.utils.TipusAutoritzacioPuntEntrada;

/**
 * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntity
 */
public class EntryPointRoleEntityDaoImpl
    extends com.soffid.iam.model.EntryPointRoleEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntityDao#toAutoritzacioPuntEntrada(es.caib.seycon.ng.model.AutoritzacioPUERolEntity, es.caib.seycon.ng.comu.AutoritzacioPuntEntrada)
     */
    public void toAutoritzacioPuntEntrada(com.soffid.iam.model.EntryPointRoleEntity source, es.caib.seycon.ng.comu.AutoritzacioPuntEntrada target) {
        // @todo verify behavior of toAutoritzacioPuntEntrada
        super.toAutoritzacioPuntEntrada(source, target);
        toAutoritzacioPuntEntradaCustom(source,target);
    }
    
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntityDao#toAutoritzacioPuntEntrada(es.caib.seycon.ng.model.AutoritzacioPUERolEntity)
     */
    public es.caib.seycon.ng.comu.AutoritzacioPuntEntrada toAutoritzacioPuntEntrada(final com.soffid.iam.model.EntryPointRoleEntity entity) {
        // @todo verify behavior of toAutoritzacioPuntEntrada
        return super.toAutoritzacioPuntEntrada(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.EntryPointRoleEntity loadAutoritzacioPUERolEntityFromAutoritzacioPuntEntrada(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada autoritzacioPuntEntrada) {
    	com.soffid.iam.model.EntryPointRoleEntity autoritzacioPUERolEntity = null;
    	if (autoritzacioPuntEntrada.getId()!=null)
    		autoritzacioPUERolEntity = this.load(autoritzacioPuntEntrada.getId());
        if (autoritzacioPUERolEntity == null)
        {
            autoritzacioPUERolEntity = newEntryPointRoleEntity();
        }
        return autoritzacioPUERolEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntityDao#autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada)
     */
    public com.soffid.iam.model.EntryPointRoleEntity autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada autoritzacioPuntEntrada) {
        // @todo verify behavior of autoritzacioPuntEntradaToEntity
        com.soffid.iam.model.EntryPointRoleEntity entity = this.loadAutoritzacioPUERolEntityFromAutoritzacioPuntEntrada(autoritzacioPuntEntrada);
        this.autoritzacioPuntEntradaToEntity(autoritzacioPuntEntrada, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntityDao#autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada, es.caib.seycon.ng.model.AutoritzacioPUERolEntity)
     */
    public void autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada source, com.soffid.iam.model.EntryPointRoleEntity target, boolean copyIfNull) {
        // @todo verify behavior of autoritzacioPuntEntradaToEntity
        super.autoritzacioPuntEntradaToEntity(source, target, copyIfNull);
        autoritzacioPuntEntradaToEntityCustom(source, target);
    }
    
    
	private void toAutoritzacioPuntEntradaCustom(com.soffid.iam.model.EntryPointRoleEntity source, es.caib.seycon.ng.comu.AutoritzacioPuntEntrada target) {
		// Ponemos la descripción del nivel de autorización
		// Hay 2 tipos: Administrador (A) y Autoritzat (C)
		if (TipusAutoritzacioPuntEntrada.NIVELL_A.equals(source.getAuthorizationLevel()))
			target.setDescripcioNivellAutoritzacio(
				TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO);
		else
			target.setDescripcioNivellAutoritzacio(
				TipusAutoritzacioPuntEntrada.NIVELL_ALTRES_DESCRIPCIO);
		
		//Punt d'entrada
		target.setIdPuntEntrada(source.getEntryPoint().getId());
		// Informació relacionada amb l'entitat autoritzada
		target.setTipusEntitatAutoritzada(TipusAutoritzacioPuntEntrada.ROL);
		target.setIdEntitatAutoritzada(source.getRoleId());
		RoleEntity rol = getRoleEntityDao().findById(source.getRoleId());
		// Format de toDescripcioRol: nomRol+"@"+codiBbdd+">"+codiAplicacio)
		String descripcioUnicaRol = rol.toRoleDescription();
		// Guardem només el nom del rol (com es fa al seycon-net)
		target.setCodiEntitatAutoritzada(rol.getName() + "@" + rol.getSystem().getName());
		target.setDescripcioEntitatAutoritzada(descripcioUnicaRol);
	}
	
	private void autoritzacioPuntEntradaToEntityCustom(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada source, com.soffid.iam.model.EntryPointRoleEntity target) {
		// Aquí se supone que la entidad está cargada o es nueva..
    	
		// Ponemos el nivel de autorización 
		// Hay 2 tipos: Administrador (A) y Autoritzat (C)
		if ("Administrador".equals(source.getDescripcioNivellAutoritzacio()))  //$NON-NLS-1$
			target.setAuthorizationLevel("A"); //$NON-NLS-1$
		else
			target.setAuthorizationLevel("C"); //$NON-NLS-1$
		
		target.setRoleId(source.getIdEntitatAutoritzada());
	}
}