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
import com.soffid.iam.utils.TipusAutoritzacioPuntEntrada;

/**
 * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntity
 */
public class EntryPointRoleEntityDaoImpl
    extends com.soffid.iam.model.EntryPointRoleEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntityDao#toAutoritzacioPuntEntrada(es.caib.seycon.ng.model.AutoritzacioPUERolEntity, es.caib.seycon.ng.comu.AutoritzacioPuntEntrada)
     */
    public void toAccessTreeAuthorization(com.soffid.iam.model.EntryPointRoleEntity source, com.soffid.iam.api.AccessTreeAuthorization target) {
        // @todo verify behavior of toAutoritzacioPuntEntrada
        super.toAccessTreeAuthorization(source, target);
        toAutoritzacioPuntEntradaCustom(source,target);
    }
    
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntityDao#toAutoritzacioPuntEntrada(es.caib.seycon.ng.model.AutoritzacioPUERolEntity)
     */
    public com.soffid.iam.api.AccessTreeAuthorization toAccessTreeAuthorization(final com.soffid.iam.model.EntryPointRoleEntity entity) {
        // @todo verify behavior of toAutoritzacioPuntEntrada
        return super.toAccessTreeAuthorization(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.EntryPointRoleEntity loadAutoritzacioPUERolEntityFromAutoritzacioPuntEntrada(com.soffid.iam.api.AccessTreeAuthorization autoritzacioPuntEntrada) {
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
    public com.soffid.iam.model.EntryPointRoleEntity accessTreeAuthorizationToEntity(com.soffid.iam.api.AccessTreeAuthorization autoritzacioPuntEntrada) {
        // @todo verify behavior of autoritzacioPuntEntradaToEntity
        com.soffid.iam.model.EntryPointRoleEntity entity = this.loadAutoritzacioPUERolEntityFromAutoritzacioPuntEntrada(autoritzacioPuntEntrada);
        this.accessTreeAuthorizationToEntity(autoritzacioPuntEntrada, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntityDao#autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada, es.caib.seycon.ng.model.AutoritzacioPUERolEntity)
     */
    public void accessTreeAuthorizationToEntity(com.soffid.iam.api.AccessTreeAuthorization source, com.soffid.iam.model.EntryPointRoleEntity target, boolean copyIfNull) {
        // @todo verify behavior of autoritzacioPuntEntradaToEntity
        super.accessTreeAuthorizationToEntity(source, target, copyIfNull);
        autoritzacioPuntEntradaToEntityCustom(source, target);
    }
    
    
	private void toAutoritzacioPuntEntradaCustom(com.soffid.iam.model.EntryPointRoleEntity source, com.soffid.iam.api.AccessTreeAuthorization target) {
		// Ponemos la descripción del nivel de autorización
		// Hay 2 tipos: Administrador (A) y Autoritzat (C)
		if (TipusAutoritzacioPuntEntrada.NIVELL_A.equals(source.getAuthorizationLevel()))
			target.setAuthorizationLevelDescription(TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO);
		else
			target.setAuthorizationLevelDescription(TipusAutoritzacioPuntEntrada.NIVELL_ALTRES_DESCRIPCIO);
		
		//Punt d'entrada
		target.setAccessTreeId(source.getEntryPoint().getId());
		// Informació relacionada amb l'entitat autoritzada
		target.setAuthorizationEntityType(TipusAutoritzacioPuntEntrada.ROL);
		target.setAuthorizationEntityId(source.getRoleId());
		RoleEntity rol = getRoleEntityDao().findById(source.getRoleId());
		// Format de toDescripcioRol: nomRol+"@"+codiBbdd+">"+codiAplicacio)
		String descripcioUnicaRol = rol.toRoleDescription();
		// Guardem només el nom del rol (com es fa al seycon-net)
		target.setAuthorizedEntityCode(rol.getName() + "@" + rol.getSystem().getName());
		target.setAuthorizedEntityDescription(descripcioUnicaRol);
	}
	
	private void autoritzacioPuntEntradaToEntityCustom(com.soffid.iam.api.AccessTreeAuthorization source, com.soffid.iam.model.EntryPointRoleEntity target) {
		// Aquí se supone que la entidad está cargada o es nueva..
    	
		// Ponemos el nivel de autorización 
		// Hay 2 tipos: Administrador (A) y Autoritzat (C)
		if ("Administrador".equals(source.getAuthorizationLevelDescription()))  //$NON-NLS-1$
			target.setAuthorizationLevel("A"); //$NON-NLS-1$
		else
			target.setAuthorizationLevel("C"); //$NON-NLS-1$
		
		target.setRoleId(source.getAuthorizationEntityId());
	}
}