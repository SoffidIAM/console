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
import es.caib.seycon.ng.utils.TipusAutoritzacioPuntEntrada;

/**
 * @see es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity
 */
public class EntryPointUserEntityDaoImpl
    extends com.soffid.iam.model.EntryPointUserEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntityDao#toAutoritzacioPuntEntrada(es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity, es.caib.seycon.ng.comu.AutoritzacioPuntEntrada)
     */
    public void toAutoritzacioPuntEntrada(com.soffid.iam.model.EntryPointUserEntity source, es.caib.seycon.ng.comu.AutoritzacioPuntEntrada target) {
        // @todo verify behavior of toAutoritzacioPuntEntrada
        super.toAutoritzacioPuntEntrada(source, target);
        
        toAutoritzacioPuntEntradaCustom(source,target);
    }
    
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntityDao#toAutoritzacioPuntEntrada(es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity)
     */
    public es.caib.seycon.ng.comu.AutoritzacioPuntEntrada toAutoritzacioPuntEntrada(final com.soffid.iam.model.EntryPointUserEntity entity) {
        // @todo verify behavior of toAutoritzacioPuntEntrada
        return super.toAutoritzacioPuntEntrada(entity);
    }
    
    
    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.EntryPointUserEntity loadAutoritzacioPUEUsuariEntityFromAutoritzacioPuntEntrada(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada autoritzacioPuntEntrada) {
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
    public com.soffid.iam.model.EntryPointUserEntity autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada autoritzacioPuntEntrada) {
        // @todo verify behavior of autoritzacioPuntEntradaToEntity
        com.soffid.iam.model.EntryPointUserEntity entity = this.loadAutoritzacioPUEUsuariEntityFromAutoritzacioPuntEntrada(autoritzacioPuntEntrada);
        this.autoritzacioPuntEntradaToEntity(autoritzacioPuntEntrada, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntityDao#autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada, es.caib.seycon.ng.model.AutoritzacioPUEUsuariEntity)
     */
    public void autoritzacioPuntEntradaToEntity(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada source, com.soffid.iam.model.EntryPointUserEntity target, boolean copyIfNull) {
        // @todo verify behavior of autoritzacioPuntEntradaToEntity
        super.autoritzacioPuntEntradaToEntity(source, target, copyIfNull);
        
        autoritzacioPuntEntradaToEntityCustom(source, target);
    }
    
    
	private void toAutoritzacioPuntEntradaCustom(com.soffid.iam.model.EntryPointUserEntity source, es.caib.seycon.ng.comu.AutoritzacioPuntEntrada target) {
		//Ponemos el ID de la entidad
		target.setId(source.getId());

		// Ponemos la descripción del nivel de autorización
		// Hay 2 tipos: Administrador (A) y Autoritzat (C)
		if (TipusAutoritzacioPuntEntrada.NIVELL_A.equals(source.getAuthorizationLevel()))
			target.setDescripcioNivellAutoritzacio(TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO);
		else
			target.setDescripcioNivellAutoritzacio(TipusAutoritzacioPuntEntrada.NIVELL_ALTRES_DESCRIPCIO);		
		
		//Punt d'entrada
		target.setIdPuntEntrada(source.getEntryPoint().getId());
		// Informació relacionada amb l'entitat autoritzada
		target.setTipusEntitatAutoritzada(TipusAutoritzacioPuntEntrada.USUARI);
		target.setIdEntitatAutoritzada(source.getUserId());
		UserEntity usuari = getUserEntityDao().findById(source.getUserId());
		target.setDescripcioEntitatAutoritzada(usuari.getFirstName() + " " + usuari.getLastName() + " " + usuari.getMiddleName() + " [" + usuari.getUserName() + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		target.setCodiEntitatAutoritzada(usuari.getUserName());
	}
    
	private void autoritzacioPuntEntradaToEntityCustom(es.caib.seycon.ng.comu.AutoritzacioPuntEntrada source, com.soffid.iam.model.EntryPointUserEntity target) {
		
		// Aquí se supone que la entidad está cargada o es nueva..
		target.setUserId(source.getIdEntitatAutoritzada());
		
		// Ponemos el nivel de autorización 
		// Hay 2 tipos: Administrador (A) y Autoritzat (C)
		if ("Administrador".equals(source.getDescripcioNivellAutoritzacio()))  //$NON-NLS-1$
			target.setAuthorizationLevel("A"); //$NON-NLS-1$
		else
			target.setAuthorizationLevel("C"); //$NON-NLS-1$
		

	}

}