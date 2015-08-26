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

import com.soffid.iam.model.GroupEntity;
import es.caib.seycon.ng.utils.TipusAutoritzacioPuntEntrada;

/**
 * @see es.caib.seycon.ng.model.AutoritzacioPUEGrupEntity
 */
public class EntryPointGroupEntityDaoImpl
    extends com.soffid.iam.model.EntryPointGroupEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUEGrupEntityDao#toAutoritzacioPuntEntrada(es.caib.seycon.ng.model.AutoritzacioPUEGrupEntity, es.caib.seycon.ng.comu.AutoritzacioPuntEntrada)
     */
    public void toAccessTreeAuthorization(com.soffid.iam.model.EntryPointGroupEntity source, com.soffid.iam.api.AccessTreeAuthorization target) {
        // @todo verify behavior of toAutoritzacioPuntEntrada
        super.toAccessTreeAuthorization(source, target);
        
        toAutoritzacioPuntEntradaCustom(source,target);
    }
    
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUEGrupEntityDao#toAutoritzacioPuntEntrada(es.caib.seycon.ng.model.AutoritzacioPUEGrupEntity)
     */
    public com.soffid.iam.api.AccessTreeAuthorization toAccessTreeAuthorization(final com.soffid.iam.model.EntryPointGroupEntity entity) {
        // @todo verify behavior of toAutoritzacioPuntEntrada
        return super.toAccessTreeAuthorization(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.EntryPointGroupEntity loadAutoritzacioPUEGrupEntityFromAutoritzacioPuntEntrada(com.soffid.iam.api.AccessTreeAuthorization autoritzacioPuntEntrada) {
    	com.soffid.iam.model.EntryPointGroupEntity autoritzacioPUEGrupEntity = null;
    	if (autoritzacioPuntEntrada.getId()!=null)
    		autoritzacioPUEGrupEntity = this.load(autoritzacioPuntEntrada.getId());
        if (autoritzacioPUEGrupEntity == null)
        {
            autoritzacioPUEGrupEntity = newEntryPointGroupEntity();
        }
        return autoritzacioPUEGrupEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUEGrupEntityDao#autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada)
     */
    public com.soffid.iam.model.EntryPointGroupEntity accessTreeAuthorizationToEntity(com.soffid.iam.api.AccessTreeAuthorization autoritzacioPuntEntrada) {
        // @todo verify behavior of autoritzacioPuntEntradaToEntity
        com.soffid.iam.model.EntryPointGroupEntity entity = this.loadAutoritzacioPUEGrupEntityFromAutoritzacioPuntEntrada(autoritzacioPuntEntrada);
        this.accessTreeAuthorizationToEntity(autoritzacioPuntEntrada, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUEGrupEntityDao#autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada, es.caib.seycon.ng.model.AutoritzacioPUEGrupEntity)
     */
    public void accessTreeAuthorizationToEntity(com.soffid.iam.api.AccessTreeAuthorization source, com.soffid.iam.model.EntryPointGroupEntity target, boolean copyIfNull) {
        // @todo verify behavior of autoritzacioPuntEntradaToEntity
        super.accessTreeAuthorizationToEntity(source, target, copyIfNull);
        autoritzacioPuntEntradaToEntityCustom(source, target);
    }
    
    
	private void toAutoritzacioPuntEntradaCustom(com.soffid.iam.model.EntryPointGroupEntity source, com.soffid.iam.api.AccessTreeAuthorization target) {
		//Ponemos el ID de la entidad
		target.setId(source.getId());
		
		// Ponemos la descripción del nivel de autorización
		// Hay 2 tipos: Administrador (A) y Autoritzat (C)
		if (TipusAutoritzacioPuntEntrada.NIVELL_A.equals(source.getAuhtorizationLevel()))
			target.setAuthorizationLevelDescription(TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO);
		else
			target.setAuthorizationLevelDescription(TipusAutoritzacioPuntEntrada.NIVELL_ALTRES_DESCRIPCIO);		
		
		//Punt d'entrada
		target.setAccessTreeId(source.getEntryPoint().getId());
		// Informació relacionada amb l'entitat autoritzada
		target.setAuthorizationEntityType(TipusAutoritzacioPuntEntrada.GRUP);
		target.setAuthorizationEntityId(source.getGroupId());
		GroupEntity grup = getGroupEntityDao().load(source.getGroupId());
		target.setAuthorizedEntityDescription(grup.getDescription() + " [" + grup.getName() + "]"); //$NON-NLS-1$ //$NON-NLS-2$
		target.setAuthorizedEntityCode(grup.getName());
	}
    
    private void autoritzacioPuntEntradaToEntityCustom(com.soffid.iam.api.AccessTreeAuthorization source, com.soffid.iam.model.EntryPointGroupEntity target) {

		// Aquí se supone que la entidad está cargada o es nueva..
		
		// Ponemos el nivel de autorización 
		// Hay 2 tipos: Administrador (A) y Autoritzat (C)
		if ("Administrador".equals(source.getAuthorizationLevelDescription()))  //$NON-NLS-1$
			target.setAuhtorizationLevel("A"); //$NON-NLS-1$
		else
			target.setAuhtorizationLevel("C"); //$NON-NLS-1$
		
		target.setGroupId(source.getAuthorizationEntityId());
	}

}
