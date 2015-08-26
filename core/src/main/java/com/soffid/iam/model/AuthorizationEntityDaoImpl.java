// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import com.soffid.iam.api.Audit;
import com.soffid.iam.api.Role;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.AuthorizationEntity;
import com.soffid.iam.model.RoleEntity;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.*;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * @see es.caib.seycon.ng.model.AutoritzacioRolEntity
 */
public class AuthorizationEntityDaoImpl
    extends com.soffid.iam.model.AuthorizationEntityDaoBase
{
	
	private void auditarAutoritzacio(String accio, AuthorizationEntity autoritzacioEntity) {
		String autoritzacio = autoritzacioEntity.getAuthorization();

		// Obtenim les dades per auditar
		RoleEntity rol = autoritzacioEntity.getRole();
		String nomRol = rol.getName();
		String bbdd = rol.getSystem().getName();
		String codiAplicacio = rol.getInformationSystem().getName();
		String domini = rol.getDomainType();

		String codiUsuari = Security.getCurrentAccount(); //$NON-NLS-1$
		
		Audit auditoria = new Audit();
		auditoria.setAction(accio);
		auditoria.setAuthor(codiUsuari);
		auditoria.setAuthorization(autoritzacio);
		
		auditoria.setRole(nomRol);
		auditoria.setDatabase(bbdd);
		auditoria.setApplication(codiAplicacio);
		auditoria.setDomain(domini);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
		auditoria.setAdditionalInfo(dateFormat.format(GregorianCalendar.getInstance().getTime()));
		auditoria.setObject("SC_AUTROL"); //$NON-NLS-1$

		AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
		getAuditEntityDao().create(auditoriaEntity);
		
	}		
	
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioRolEntityDao#toAutoritzacioRol(es.caib.seycon.ng.model.AutoritzacioRolEntity, es.caib.seycon.ng.comu.AutoritzacioRol)
     */
    public void toAuthorizationRole(com.soffid.iam.model.AuthorizationEntity source, com.soffid.iam.api.AuthorizationRole target) {
        // @todo verify behavior of toAutoritzacioRol
        super.toAuthorizationRole(source, target);
        toAutoritzacioRolCustom(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.AutoritzacioRolEntityDao#toAutoritzacioRol(es.caib.seycon.ng.model.AutoritzacioRolEntity)
     */
    public com.soffid.iam.api.AuthorizationRole toAuthorizationRole(final com.soffid.iam.model.AuthorizationEntity entity) {
        // @todo verify behavior of toAutoritzacioRol
        return super.toAuthorizationRole(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.AuthorizationEntity loadAutoritzacioRolEntityFromAutoritzacioRol(com.soffid.iam.api.AuthorizationRole autoritzacioRol) {
        com.soffid.iam.model.AuthorizationEntity autoritzacioRolEntity = null;
        if (autoritzacioRol.getId() != null) {
        	autoritzacioRolEntity = this.load(autoritzacioRol.getId());	
        }
        
        if (autoritzacioRolEntity == null)
        {
            autoritzacioRolEntity = newAuthorizationEntity();
        }
        return autoritzacioRolEntity;

    }

    
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioRolEntityDao#autoritzacioRolToEntity(es.caib.seycon.ng.comu.AutoritzacioRol)
     */
    public com.soffid.iam.model.AuthorizationEntity authorizationRoleToEntity(com.soffid.iam.api.AuthorizationRole autoritzacioRol) {
        // @todo verify behavior of autoritzacioRolToEntity
        com.soffid.iam.model.AuthorizationEntity entity = this.loadAutoritzacioRolEntityFromAutoritzacioRol(autoritzacioRol);
        this.authorizationRoleToEntity(autoritzacioRol, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.AutoritzacioRolEntityDao#autoritzacioRolToEntity(es.caib.seycon.ng.comu.AutoritzacioRol, es.caib.seycon.ng.model.AutoritzacioRolEntity)
     */
    public void authorizationRoleToEntity(com.soffid.iam.api.AuthorizationRole source, com.soffid.iam.model.AuthorizationEntity target, boolean copyIfNull) {
        // @todo verify behavior of autoritzacioRolToEntity
        super.authorizationRoleToEntity(source, target, copyIfNull);
        autoritzacioRolToEntityCustom(source, target);
    }
    
    private void toAutoritzacioRolCustom(com.soffid.iam.model.AuthorizationEntity source, com.soffid.iam.api.AuthorizationRole target) {
    	
    	//només tenim el codi d'autorització (camp autoritzacio)
    	RoleEntity rolE = source.getRole();
    	if (rolE!=null) {
    		Role rol = getRoleEntityDao().toRole(rolE);
    		target.setRole(rol);
    	}
    	
    	//les dades de l'usuari s'obtenen des del service
    	
    	
    }
    
    private void autoritzacioRolToEntityCustom(com.soffid.iam.api.AuthorizationRole source, com.soffid.iam.model.AuthorizationEntity target) {
    	// tenim autoritzacio
    	if (source.getId() != null) {
    		// Carreguem l'existent (no es poden modificar)
    		target = load(source.getId());
    		return;
    	}
    	// el rol en principio SIEMPRE TIENE QUE ESTAR
    	if (source.getRole() != null) {
    		Role rolSrc = source.getRole();
    		RoleEntity rol = getRoleEntityDao().roleToEntity(rolSrc);
    		target.setRole(rol);
    	}
    	
    	// les dades de l'usuari s'obtenen des del service i no s'han de posar
    	
    	
    }

	public void create(AuthorizationEntity autoritzacioRolEntity) {
		try {
			super.create(autoritzacioRolEntity);
			getSession(false).flush();

			auditarAutoritzacio("C", autoritzacioRolEntity); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("AuthorizationEntityDaoImpl.0"), autoritzacioRolEntity.getAuthorization(), autoritzacioRolEntity.getRole().getName(), message));
		}
	}

	
	public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof AuthorizationEntity) {
                AuthorizationEntity auto = (AuthorizationEntity) obj;
                this.create(auto);
            }
        }
	}

	
	public void update(AuthorizationEntity autoritzacioRolEntity) {
		try {
			super.update(autoritzacioRolEntity);
			getSession(false).flush();
			auditarAutoritzacio("U", autoritzacioRolEntity); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("AuthorizationEntityDaoImpl.1"), autoritzacioRolEntity.getAuthorization(), autoritzacioRolEntity.getRole().getName(), message));
		}

	}

	
	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof AuthorizationEntity) {
                AuthorizationEntity auto = (AuthorizationEntity) obj;
                this.update(auto);
            }
        }
	}

	
	// NOTA: el método remove (Long id) llama a este método
	public void remove(AuthorizationEntity autoritzacioRolEntity) {
		try {
			super.remove(autoritzacioRolEntity);
			getSession(false).flush();
			auditarAutoritzacio("D", autoritzacioRolEntity); //$NON-NLS-1$

		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("AuthorizationEntityDaoImpl.2"), autoritzacioRolEntity.getAuthorization(), autoritzacioRolEntity.getRole().getName(), message));
		}
	}

	
	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof AuthorizationEntity) {
                AuthorizationEntity auto = (AuthorizationEntity) obj;
                this.remove(auto);
            }
        }
	}

	
}