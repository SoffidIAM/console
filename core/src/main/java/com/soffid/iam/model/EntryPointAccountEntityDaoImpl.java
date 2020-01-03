// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import com.soffid.iam.api.AccessTreeAuthorization;

import es.caib.seycon.ng.comu.AutoritzacioPuntEntrada;
import es.caib.seycon.ng.utils.TipusAutoritzacioPuntEntrada;

/**
 * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntity
 */
public class EntryPointAccountEntityDaoImpl
    extends EntryPointAccountEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntityDao#toAccessTreeAuthorization(es.caib.seycon.ng.model.AutoritzacioPUERolEntity, es.caib.seycon.ng.comu.AccessTreeAuthorization)
     */
    public void toAccessTreeAuthorization(
        EntryPointAccountEntity source,
        AccessTreeAuthorization target)
    {
        // @todo verify behavior of toAccessTreeAuthorization
        super.toAccessTreeAuthorization(source, target);
        toAccessTreeAuthorizationCustom(source,target);
    }
    
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntityDao#toAccessTreeAuthorization(es.caib.seycon.ng.model.AutoritzacioPUERolEntity)
     */
    public AccessTreeAuthorization toAccessTreeAuthorization(final EntryPointAccountEntity entity)
    {
        // @todo verify behavior of toAccessTreeAuthorization
        return super.toAccessTreeAuthorization(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private EntryPointAccountEntity loadFromAccessTreeAuthorization(AccessTreeAuthorization accessTreeAuthorization)
    {
    	EntryPointAccountEntity entryPointAccountEntity = null;
    	if (accessTreeAuthorization.getId()!=null)
    		entryPointAccountEntity = this.load(accessTreeAuthorization.getId());
        if (entryPointAccountEntity == null)
        {
        	entryPointAccountEntity = newEntryPointAccountEntity();
        }
        return entryPointAccountEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntityDao#accessTreeAuthorizationToEntity(AccessTreeAuthorization)
     */
    public EntryPointAccountEntity accessTreeAuthorizationToEntity(AccessTreeAuthorization accessTreeAuthorization)
    {
        // @todo verify behavior of accessTreeAuthorizationToEntity
        EntryPointAccountEntity entity = this.loadFromAccessTreeAuthorization(accessTreeAuthorization);
        this.accessTreeAuthorizationToEntity(accessTreeAuthorization, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.AutoritzacioPUERolEntityDao#accessTreeAuthorizationToEntity(AccessTreeAuthorization, es.caib.seycon.ng.model.AutoritzacioPUERolEntity)
     */
    public void accessTreeAuthorizationToEntity(
        AccessTreeAuthorization source,
        EntryPointAccountEntity target,
        boolean copyIfNull)
    {
        // @todo verify behavior of accessTreeAuthorizationToEntity
        super.accessTreeAuthorizationToEntity(source, target, copyIfNull);
        accessTreeAuthorizationToEntityCustom(source, target);
    }
    
    
	private void toAccessTreeAuthorizationCustom( // Entity to VO
		EntryPointAccountEntity source,
		AccessTreeAuthorization target)
	{
		// Ponemos la descripción del nivel de autorización
		// Hay 2 tipos: Administrador (A) y Autoritzat (C)
		if (TipusAutoritzacioPuntEntrada
				.NIVELL_A.equals(source.getAuthorizationlevel()))
			target.setAuthorizationLevelDescription(
					TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO);
		else
			target.setAuthorizationLevelDescription(
				TipusAutoritzacioPuntEntrada.NIVELL_ALTRES_DESCRIPCIO);
		
		//Punt d'entrada
		target.setId(source.getEntryPoint().getId());
		// Informació relacionada amb l'entitat autoritzada
		target.setAuthorizationEntityType(TipusAutoritzacioPuntEntrada.ACCOUNT);
		target.setAuthorizationEntityId(source.getAccount().getId());
		// Format de toDescripcioRol: nomRol+"@"+codiBbdd+">"+codiAplicacio)
		String descripcioUnicaRol = source.getAccount().getDescription();
		// Guardem només el nom del rol (com es fa al seycon-net)
		target.setAuthorizedEntityDescription(descripcioUnicaRol);
		target.setAuthorizedEntityCode(source.getAccount().getName()+"@"+source.getAccount().getSystem().getName());
//		target.setAuthorizationLevelDescription(descripcioUnicaRol);
	}
	
	private void accessTreeAuthorizationToEntityCustom( //VO to Entity
		AccessTreeAuthorization source,
		EntryPointAccountEntity target)
	{
		// Aquí se supone que la entidad está cargada o es nueva..
    	
		// Ponemos el nivel de autorización 
		// Hay 2 tipos: Administrador (A) y Autoritzat (C)
		if ("Administrador".equals(source.getAuthorizationLevelDescription()))  //$NON-NLS-1$
			target.setAuthorizationlevel("A"); //$NON-NLS-1$
		else
			target.setAuthorizationlevel("C"); //$NON-NLS-1$
		
		target.setAccount(getAccountEntityDao().load(source.getAuthorizationEntityId()));
	}
}