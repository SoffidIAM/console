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

import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.AuthorizationEntity;
import com.soffid.iam.model.RoleEntity;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.Domini;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.exception.SeyconException;
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
		
		Auditoria auditoria = new Auditoria();
		auditoria.setAccio(accio);
		auditoria.setAutor(codiUsuari);
		auditoria.setAutoritzacio(autoritzacio);
		
		auditoria.setRol(nomRol);
		auditoria.setBbdd(bbdd);
		auditoria.setAplicacio(codiAplicacio);
		auditoria.setDomini(domini);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
		auditoria.setData(dateFormat.format(GregorianCalendar.getInstance()
				.getTime()));
		auditoria.setObjecte("SC_AUTROL"); //$NON-NLS-1$

		AuditEntity auditoriaEntity = getAuditEntityDao().auditoriaToEntity(auditoria);
		getAuditEntityDao().create(auditoriaEntity);
		
	}		
	
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioRolEntityDao#toAutoritzacioRol(es.caib.seycon.ng.model.AutoritzacioRolEntity, es.caib.seycon.ng.comu.AutoritzacioRol)
     */
    public void toAutoritzacioRol(com.soffid.iam.model.AuthorizationEntity source, es.caib.seycon.ng.comu.AutoritzacioRol target) {
        // @todo verify behavior of toAutoritzacioRol
        super.toAutoritzacioRol(source, target);
        toAutoritzacioRolCustom(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.AutoritzacioRolEntityDao#toAutoritzacioRol(es.caib.seycon.ng.model.AutoritzacioRolEntity)
     */
    public es.caib.seycon.ng.comu.AutoritzacioRol toAutoritzacioRol(final com.soffid.iam.model.AuthorizationEntity entity) {
        // @todo verify behavior of toAutoritzacioRol
        return super.toAutoritzacioRol(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.AuthorizationEntity loadAutoritzacioRolEntityFromAutoritzacioRol(es.caib.seycon.ng.comu.AutoritzacioRol autoritzacioRol) {
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
    public com.soffid.iam.model.AuthorizationEntity autoritzacioRolToEntity(es.caib.seycon.ng.comu.AutoritzacioRol autoritzacioRol) {
        // @todo verify behavior of autoritzacioRolToEntity
        com.soffid.iam.model.AuthorizationEntity entity = this.loadAutoritzacioRolEntityFromAutoritzacioRol(autoritzacioRol);
        this.autoritzacioRolToEntity(autoritzacioRol, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.AutoritzacioRolEntityDao#autoritzacioRolToEntity(es.caib.seycon.ng.comu.AutoritzacioRol, es.caib.seycon.ng.model.AutoritzacioRolEntity)
     */
    public void autoritzacioRolToEntity(es.caib.seycon.ng.comu.AutoritzacioRol source, com.soffid.iam.model.AuthorizationEntity target, boolean copyIfNull) {
        // @todo verify behavior of autoritzacioRolToEntity
        super.autoritzacioRolToEntity(source, target, copyIfNull);
        autoritzacioRolToEntityCustom(source, target);
    }
    
    private void toAutoritzacioRolCustom(com.soffid.iam.model.AuthorizationEntity source, es.caib.seycon.ng.comu.AutoritzacioRol target) {
    	
    	//només tenim el codi d'autorització (camp autoritzacio)
    	RoleEntity rolE = source.getRole();
    	if (rolE!=null) {
    		Rol rol = getRoleEntityDao().toRol(rolE);
    		target.setRol(rol);
    	}
    	
    	//les dades de l'usuari s'obtenen des del service
    	
    	
    }
    
    private void autoritzacioRolToEntityCustom(es.caib.seycon.ng.comu.AutoritzacioRol source, com.soffid.iam.model.AuthorizationEntity target) {
    	// tenim autoritzacio
    	if (source.getId() != null) {
    		// Carreguem l'existent (no es poden modificar)
    		target = load(source.getId());
    		return;
    	}
    	// el rol en principio SIEMPRE TIENE QUE ESTAR
    	if (source.getRol()!=null) {
    		Rol rolSrc = source.getRol();
    		RoleEntity rol = getRoleEntityDao().rolToEntity(rolSrc);
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