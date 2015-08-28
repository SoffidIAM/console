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

import com.soffid.iam.model.UserEntity;
import com.soffid.iam.utils.TipusAutoritzacioPuntEntrada;

/**
 * @see es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity
 */
public class EntryPointUserEntityDaoImpl
    extends com.soffid.iam.model.EntryPointUserEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntityDao#toAutoritzacioPuntEntrada(es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity, es.caib.seycon.ng.comu.AutoritzacioPuntEntrada)
     */
    public void toAccessTreeAuthorization(com.soffid.iam.model.EntryPointUserEntity source, com.soffid.iam.api.AccessTreeAuthorization target) {
        // @todo verify behavior of toAutoritzacioPuntEntrada
        super.toAccessTreeAuthorization(source, target);
        
        toAutoritzacioPuntEntradaCustom(source,target);
    }
    
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntityDao#toAutoritzacioPuntEntrada(es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity)
     */
    public com.soffid.iam.api.AccessTreeAuthorization toAccessTreeAuthorization(final com.soffid.iam.model.EntryPointUserEntity entity) {
        // @todo verify behavior of toAutoritzacioPuntEntrada
        return super.toAccessTreeAuthorization(entity);
    }
    
    
    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.EntryPointUserEntity loadAutoritzacioPUEUsuariEntityFromAutoritzacioPuntEntrada(com.soffid.iam.api.AccessTreeAuthorization autoritzacioPuntEntrada) {
    	com.soffid.iam.model.EntryPointUserEntity autoritzacioPUEUsuariEntity = null;
    	if (autoritzacioPuntEntrada.getId()!=null)
    		autoritzacioPUEUsuariEntity = this.load(autoritzacioPuntEntrada.getId());
        if (autoritzacioPUEUsuariEntity == null)
        {
            autoritzacioPUEUsuariEntity = newEntryPointUserEntity();
        }
        return autoritzacioPUEUsuariEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntityDao#autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada)
     */
    public com.soffid.iam.model.EntryPointUserEntity accessTreeAuthorizationToEntity(com.soffid.iam.api.AccessTreeAuthorization autoritzacioPuntEntrada) {
        // @todo verify behavior of autoritzacioPuntEntradaToEntity
        com.soffid.iam.model.EntryPointUserEntity entity = this.loadAutoritzacioPUEUsuariEntityFromAutoritzacioPuntEntrada(autoritzacioPuntEntrada);
        this.accessTreeAuthorizationToEntity(autoritzacioPuntEntrada, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntityDao#autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada, es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity)
     */
    public void accessTreeAuthorizationToEntity(com.soffid.iam.api.AccessTreeAuthorization source, com.soffid.iam.model.EntryPointUserEntity target, boolean copyIfNull) {
        // @todo verify behavior of autoritzacioPuntEntradaToEntity
        super.accessTreeAuthorizationToEntity(source, target, copyIfNull);
        
        autoritzacioPuntEntradaToEntityCustom(source, target);
    }
    
    
	private void toAutoritzacioPuntEntradaCustom(com.soffid.iam.model.EntryPointUserEntity source, com.soffid.iam.api.AccessTreeAuthorization target) {
		//Ponemos el ID de la entidad
		target.setId(source.getId());

		// Ponemos la descripción del nivel de autorización
		// Hay 2 tipos: Administrador (A) y Autoritzat (C)
		if (TipusAutoritzacioPuntEntrada.NIVELL_A.equals(source.getAuthorizationLevel()))
			target.setAuthorizationLevelDescription(TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO);
		else
			target.setAuthorizationLevelDescription(TipusAutoritzacioPuntEntrada.NIVELL_ALTRES_DESCRIPCIO);		
		
		//Punt d'entrada
		target.setAccessTreeId(source.getEntryPoint().getId());
		// Informació relacionada amb l'entitat autoritzada
		target.setAuthorizationEntityType(TipusAutoritzacioPuntEntrada.USUARI);
		target.setAuthorizationEntityId(source.getUser().getId());
		UserEntity usuari = source.getUser();
		target.setAuthorizedEntityDescription(usuari.getFirstName() + " " + usuari.getLastName() + " " + usuari.getMiddleName() + " [" + usuari.getUserName() + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		target.setAuthorizedEntityCode(usuari.getUserName());
	}
    
	private void autoritzacioPuntEntradaToEntityCustom(com.soffid.iam.api.AccessTreeAuthorization source, com.soffid.iam.model.EntryPointUserEntity target) {
		
		// Aquí se supone que la entidad está cargada o es nueva..
		target.setUser(getUserEntityDao().load(source.getAuthorizationEntityId()));
		
		// Ponemos el nivel de autorización 
		// Hay 2 tipos: Administrador (A) y Autoritzat (C)
		if ("Administrador".equals(source.getAuthorizationLevelDescription()))  //$NON-NLS-1$
			target.setAuthorizationLevel("A"); //$NON-NLS-1$
		else
			target.setAuthorizationLevel("C"); //$NON-NLS-1$
		

	}

}
