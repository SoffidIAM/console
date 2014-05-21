// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.Domini;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.AutoritzacioRolEntity
 */
public class AutoritzacioRolEntityDaoImpl
    extends es.caib.seycon.ng.model.AutoritzacioRolEntityDaoBase
{
	
	private void auditarAutoritzacio(String accio, AutoritzacioRolEntity autoritzacioEntity) {
		String autoritzacio = autoritzacioEntity.getAutoritzacio();

		// Obtenim les dades per auditar
		RolEntity rol = autoritzacioEntity.getRol();
		String nomRol = rol.getNom();
		String bbdd = rol.getBaseDeDades().getCodi();
		String codiAplicacio = rol.getAplicacio().getCodi();
		String domini = rol.getTipusDomini();

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

		AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao()
				.auditoriaToEntity(auditoria);
		getAuditoriaEntityDao().create(auditoriaEntity);
		
	}		
	
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioRolEntityDao#toAutoritzacioRol(es.caib.seycon.ng.model.AutoritzacioRolEntity, es.caib.seycon.ng.comu.AutoritzacioRol)
     */
    public void toAutoritzacioRol(
        es.caib.seycon.ng.model.AutoritzacioRolEntity source,
        es.caib.seycon.ng.comu.AutoritzacioRol target)
    {
        // @todo verify behavior of toAutoritzacioRol
        super.toAutoritzacioRol(source, target);
        toAutoritzacioRolCustom(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.AutoritzacioRolEntityDao#toAutoritzacioRol(es.caib.seycon.ng.model.AutoritzacioRolEntity)
     */
    public es.caib.seycon.ng.comu.AutoritzacioRol toAutoritzacioRol(final es.caib.seycon.ng.model.AutoritzacioRolEntity entity)
    {
        // @todo verify behavior of toAutoritzacioRol
        return super.toAutoritzacioRol(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private es.caib.seycon.ng.model.AutoritzacioRolEntity loadAutoritzacioRolEntityFromAutoritzacioRol(es.caib.seycon.ng.comu.AutoritzacioRol autoritzacioRol)
    {
        es.caib.seycon.ng.model.AutoritzacioRolEntity autoritzacioRolEntity = null;
        if (autoritzacioRol.getId() != null) {
        	autoritzacioRolEntity = this.load(autoritzacioRol.getId());	
        }
        
        if (autoritzacioRolEntity == null)
        {
            autoritzacioRolEntity = newAutoritzacioRolEntity();
        }
        return autoritzacioRolEntity;

    }

    
    /**
     * @see es.caib.seycon.ng.model.AutoritzacioRolEntityDao#autoritzacioRolToEntity(es.caib.seycon.ng.comu.AutoritzacioRol)
     */
    public es.caib.seycon.ng.model.AutoritzacioRolEntity autoritzacioRolToEntity(es.caib.seycon.ng.comu.AutoritzacioRol autoritzacioRol)
    {
        // @todo verify behavior of autoritzacioRolToEntity
        es.caib.seycon.ng.model.AutoritzacioRolEntity entity = this.loadAutoritzacioRolEntityFromAutoritzacioRol(autoritzacioRol);
        this.autoritzacioRolToEntity(autoritzacioRol, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.AutoritzacioRolEntityDao#autoritzacioRolToEntity(es.caib.seycon.ng.comu.AutoritzacioRol, es.caib.seycon.ng.model.AutoritzacioRolEntity)
     */
    public void autoritzacioRolToEntity(
        es.caib.seycon.ng.comu.AutoritzacioRol source,
        es.caib.seycon.ng.model.AutoritzacioRolEntity target,
        boolean copyIfNull)
    {
        // @todo verify behavior of autoritzacioRolToEntity
        super.autoritzacioRolToEntity(source, target, copyIfNull);
        autoritzacioRolToEntityCustom(source, target);
    }
    
    private void toAutoritzacioRolCustom(
            es.caib.seycon.ng.model.AutoritzacioRolEntity source,
            es.caib.seycon.ng.comu.AutoritzacioRol target) {
    	
    	//només tenim el codi d'autorització (camp autoritzacio)
    	RolEntity rolE = source.getRol();
    	if (rolE!=null) {
    		Rol rol = getRolEntityDao().toRol(rolE);
    		target.setRol(rol);
    	}
    	
    	//les dades de l'usuari s'obtenen des del service
    	
    	
    }
    
    private void autoritzacioRolToEntityCustom(
            es.caib.seycon.ng.comu.AutoritzacioRol source,
            es.caib.seycon.ng.model.AutoritzacioRolEntity target) {
    	// tenim autoritzacio
    	if (source.getId() != null) {
    		// Carreguem l'existent (no es poden modificar)
    		target = load(source.getId());
    		return;
    	}
    	// el rol en principio SIEMPRE TIENE QUE ESTAR
    	if (source.getRol()!=null) {
    		Rol rolSrc = source.getRol();
    		RolEntity rol = getRolEntityDao().rolToEntity(rolSrc);
    		target.setRol(rol);
    	}
    	
    	// les dades de l'usuari s'obtenen des del service i no s'han de posar
    	
    	
    }

	public void create(
			AutoritzacioRolEntity autoritzacioRolEntity) {
		try {
			super.create(autoritzacioRolEntity);
			getSession(false).flush();

			auditarAutoritzacio("C", autoritzacioRolEntity); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("AutoritzacioRolEntityDaoImpl.0"), //$NON-NLS-1$
					autoritzacioRolEntity.getAutoritzacio(), 
					autoritzacioRolEntity.getRol().getNom(), 
					message));
		}
	}

	
	public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof AutoritzacioRolEntity) {
				AutoritzacioRolEntity auto = (AutoritzacioRolEntity) obj;
				this.create(auto); // cridem al mètode 1 per 1
			}
		}
	}

	
	public void update(AutoritzacioRolEntity autoritzacioRolEntity) {
		try {
			super.update(autoritzacioRolEntity);
			getSession(false).flush();
			auditarAutoritzacio("U", autoritzacioRolEntity); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("AutoritzacioRolEntityDaoImpl.1"), //$NON-NLS-1$
					autoritzacioRolEntity.getAutoritzacio(), 
					autoritzacioRolEntity.getRol().getNom(), 
					message));
		}

	}

	
	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof AutoritzacioRolEntity) {
				AutoritzacioRolEntity auto = (AutoritzacioRolEntity) obj;
				this.update(auto);// cridem al mètode 1 per 1
			}
		}
	}

	
	// NOTA: el método remove (Long id) llama a este método
	public void remove(AutoritzacioRolEntity autoritzacioRolEntity) {
		try {
			super.remove(autoritzacioRolEntity);
			getSession(false).flush();
			auditarAutoritzacio("D", autoritzacioRolEntity); //$NON-NLS-1$

		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("AutoritzacioRolEntityDaoImpl.2"), //$NON-NLS-1$
					autoritzacioRolEntity.getAutoritzacio(), 
					autoritzacioRolEntity.getRol().getNom(), 
					message));
		}
	}

	
	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof AutoritzacioRolEntity) {
				AutoritzacioRolEntity auto = (AutoritzacioRolEntity) obj;
				this.remove(auto);// cridem al mètode 1 per 1
			}
		}
	}

	
}