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
import java.util.List;

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Identitat;
import es.caib.seycon.ng.comu.NetworkAuthorization;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.XarxaACEntity
 */
public class XarxaACEntityDaoImpl extends
		es.caib.seycon.ng.model.XarxaACEntityDaoBase {

	private void auditarXarxaAC(String accio, String codiXarxa,
			String codiGrup, String codiUsuariAuditat, String rol,
			String codiAplicacio, String codiBbdd, String maquines) {
		String codiUsuari = Security.getCurrentAccount(); //$NON-NLS-1$
		Auditoria auditoria = new Auditoria();
		auditoria.setAccio(accio);
		auditoria.setAplicacio(codiAplicacio);
		auditoria.setXarxa(codiXarxa);
		auditoria.setGrup(codiGrup);
		auditoria.setUsuari(codiUsuariAuditat);
		auditoria.setMaquina(maquines);
		auditoria.setAutor(codiUsuari);
		auditoria.setRol(rol);
		auditoria.setBbdd(codiBbdd);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				Messages.getString("XarxaACEntityDaoImpl.1")); //$NON-NLS-1$
		auditoria.setData(dateFormat.format(GregorianCalendar.getInstance()
				.getTime()));
		auditoria.setObjecte("SC_AUTXAR"); //$NON-NLS-1$
		AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao()
				.auditoriaToEntity(auditoria);
		getAuditoriaEntityDao().create(auditoriaEntity);
	}

	public void update(XarxaACEntity xarxaAC) {
		try {
			super.update(xarxaAC);
			getSession(false).flush();
			String codiGrup = xarxaAC.getGrup() == null ? null : xarxaAC
					.getGrup().getCodi();
			String codiUsuari = xarxaAC.getUsuari() == null ? null : xarxaAC
					.getUsuari().getCodi();
			String nomRol = null;
			String codiAplicacio = null;
			String codiBbdd = null;
			if (xarxaAC.getRole() != null) {
				nomRol = xarxaAC.getRole().getNom();
				codiAplicacio = xarxaAC.getRole().getAplicacio().getCodi();
				codiBbdd = xarxaAC.getRole().getBaseDeDades().getCodi();
			}
			String maquines = xarxaAC.getNomMaquines();
			auditarXarxaAC("U", xarxaAC.getXarxa().getCodi(), codiGrup, //$NON-NLS-1$
					codiUsuari, nomRol, codiAplicacio, codiBbdd, maquines);
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("XarxaACEntityDaoImpl.errorUpdating"), //$NON-NLS-1$
					xarxaAC.getNomMaquines(), message));
		}
	}

	public void create(es.caib.seycon.ng.model.XarxaACEntity xarxaAC)
			throws RuntimeException {
		try {
			super.create(xarxaAC);
			getSession(false).flush();
			String codiGrup = xarxaAC.getGrup() == null ? null : xarxaAC
					.getGrup().getCodi();
			String codiUsuari = xarxaAC.getUsuari() == null ? null : xarxaAC
					.getUsuari().getCodi();
			String nomRol = null;
			String codiAplicacio = null;
			String codiBbdd = null;
			if (xarxaAC.getRole() != null) {
				nomRol = xarxaAC.getRole().getNom();
				codiAplicacio = xarxaAC.getRole().getAplicacio().getCodi();
				codiBbdd = xarxaAC.getRole().getBaseDeDades().getCodi();
			}
			String maquines = xarxaAC.getNomMaquines();
			auditarXarxaAC("C", xarxaAC.getXarxa().getCodi(), codiGrup, //$NON-NLS-1$
					codiUsuari, nomRol, codiAplicacio, codiBbdd, maquines);
			xarxaAC.setId(xarxaAC.getId());
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("XarxaACEntityDaoImpl.errorCreating"), //$NON-NLS-1$
					xarxaAC.getNomMaquines(), 
					message));
		}
	}

	public void remove(es.caib.seycon.ng.model.XarxaACEntity xarxaAC)
			throws RuntimeException {
		try {
			String codiXarxa = xarxaAC.getXarxa().getCodi();
			String codiGrup = xarxaAC.getGrup() == null ? null : xarxaAC
					.getGrup().getCodi();
			String codiUsuari = xarxaAC.getUsuari() == null ? null : xarxaAC
					.getUsuari().getCodi();
			String maquines = xarxaAC.getNomMaquines();
			String nomRol = null;
			String codiAplicacio = null;
			String codiBbdd = null;
			if (xarxaAC.getRole() != null) {
				nomRol = xarxaAC.getRole().getNom();
				codiAplicacio = xarxaAC.getRole().getAplicacio().getCodi();
				codiBbdd = xarxaAC.getRole().getBaseDeDades().getCodi();
			}
			super.remove(xarxaAC);
			getSession(false).flush();
			auditarXarxaAC("D", codiXarxa, codiGrup, codiUsuari, nomRol, //$NON-NLS-1$
					codiAplicacio, codiBbdd, maquines);
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("XarxaACEntityDaoImpl.errorDeleting"), //$NON-NLS-1$
					xarxaAC.getNomMaquines(), message));
		}
	}

	public void toNetworkAuthorization(
			es.caib.seycon.ng.model.XarxaACEntity source,
			es.caib.seycon.ng.comu.NetworkAuthorization target) {
		super.toNetworkAuthorization(source, target);
		toNetworkAuthorizationCustom(source, target);
	}

	private void toNetworkAuthorizationCustom(
			es.caib.seycon.ng.model.XarxaACEntity source,
			es.caib.seycon.ng.comu.NetworkAuthorization target) {
		XarxaEntity xarxa = source.getXarxa();
		if (xarxa != null) {
			target.setCodiXarxa(xarxa.getCodi());
		}
		GrupEntity grup = source.getGrup();
		RolEntity rol = source.getRole();
		UsuariEntity usuari = source.getUsuari();
		if (grup != null) {
			Identitat identitat = getGrupEntityDao().toIdentitat(grup);
			target.setIdentitat(identitat);
		} else if (rol != null) {
			Identitat identitat = getRolEntityDao().toIdentitat(rol);
			target.setIdentitat(identitat);
		} else {
			Identitat identitat = getUsuariEntityDao().toIdentitat(usuari);
			target.setIdentitat(identitat);
		}
		target.setMascara(source.getNomMaquines());
	}

	/**
	 * @see es.caib.seycon.ng.model.XarxaACEntityDao#toNetworkAuthorization(es.caib.seycon.ng.model.XarxaACEntity)
	 */
	public es.caib.seycon.ng.comu.NetworkAuthorization toNetworkAuthorization(
			final es.caib.seycon.ng.model.XarxaACEntity entity) {
		NetworkAuthorization newtowkAuthorization = super
				.toNetworkAuthorization(entity);
		toNetworkAuthorizationCustom(entity, newtowkAuthorization);
		return newtowkAuthorization;
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private es.caib.seycon.ng.model.XarxaACEntity loadXarxaACEntityFromNetworkAuthorization(
			es.caib.seycon.ng.comu.NetworkAuthorization networkAuthorization) {
		XarxaACEntity xarxaACEntity = null;
		if (networkAuthorization.getId() != null) {
			xarxaACEntity = load(networkAuthorization.getId());
		}
		if (xarxaACEntity == null) {
			xarxaACEntity = newXarxaACEntity();
		}
		return xarxaACEntity;
	}

	/**
	 * @see es.caib.seycon.ng.model.XarxaACEntityDao#networkAuthorizationToEntity(es.caib.seycon.ng.comu.NetworkAuthorization)
	 */
	public es.caib.seycon.ng.model.XarxaACEntity networkAuthorizationToEntity(
			es.caib.seycon.ng.comu.NetworkAuthorization networkAuthorization) {
		es.caib.seycon.ng.model.XarxaACEntity entity = this
				.loadXarxaACEntityFromNetworkAuthorization(networkAuthorization);
		this.networkAuthorizationToEntity(networkAuthorization, entity, true);
		return entity;
	}

	public void networkAuthorizationToEntityCustom(
			es.caib.seycon.ng.comu.NetworkAuthorization source,
			es.caib.seycon.ng.model.XarxaACEntity target) {
		XarxaEntity xarxa = getXarxaEntityDao().findByCodi(
				source.getCodiXarxa());
		if (xarxa != null) {
			target.setXarxa(xarxa);
		} else {
			throw new SeyconException(String.format(Messages.getString("XarxaACEntityDaoImpl.networkNotFound"), source.getCodiXarxa())); //$NON-NLS-1$
		}
		target.setNomMaquines(source.getMascara());

		Identitat identitat = source.getIdentitat();
		String nomRol = identitat.getNomRol();
		if (nomRol != null && nomRol.trim().compareTo("") == 0) { //$NON-NLS-1$
			nomRol = null;
		}
		String codiGrup = identitat.getCodiGrup();
		if (codiGrup != null && codiGrup.trim().compareTo("") == 0) { //$NON-NLS-1$
			codiGrup = null;
		}
		String codiUsuari = identitat.getCodiUsuari();
		if (codiUsuari != null && codiUsuari.trim().compareTo("") == 0) { //$NON-NLS-1$
			codiUsuari = null;
		}

		String codiIdentitat = identitat.getCodiIdentitat();
		if (codiIdentitat != null && codiIdentitat.trim().compareTo("") == 0) { //$NON-NLS-1$
			codiIdentitat = null;
		}

		if (codiUsuari == null && codiGrup == null && nomRol == null
				&& codiIdentitat != null) {

			// ROL
			String codiAplicacio = null;
			String nomRolReal = null;
			String codiBbdd = null;
			String partDeAplicacio = null;
			boolean esRol = false;
			String[] partsDeRol = codiIdentitat.split("@"); //$NON-NLS-1$
			esRol = partsDeRol.length == 2;
			if (esRol) {
				nomRolReal = partsDeRol[0];
				partDeAplicacio = partsDeRol[1];
				String partsDeAplicacio[] = partDeAplicacio.split(">"); //$NON-NLS-1$
				esRol = partsDeRol.length == 2;
				if (esRol) {
					codiBbdd = partsDeAplicacio[0];
					codiAplicacio = partsDeAplicacio[1];
					RolEntity rolEntity = getRolEntityDao()
							.findByNomRolAndCodiAplicacioAndCodiDispatcher(
									nomRolReal, codiAplicacio, codiBbdd);
					esRol = rolEntity != null;
					if (esRol) {
						nomRol = codiIdentitat;
					}
				}
			}

			// USUARI
			boolean esUsuari = false;
			if (!esRol) {
				UsuariEntity usuariEntity = getUsuariEntityDao().findByCodi(
						codiIdentitat);
				esUsuari = usuariEntity != null;
				if (esUsuari) {
					codiUsuari = codiIdentitat;
				}
			}

			// GRUP
			boolean esGrup = false;
			if (!esRol && !esUsuari) {
				GrupEntity grupEntity = getGrupEntityDao().findByCodi(
						codiIdentitat);
				esGrup = grupEntity != null;
				if (esGrup) {
					codiGrup = codiIdentitat;
				}
			}

			if (!esRol && !esUsuari && !esGrup) {
				throw new SeyconException(String.format(Messages.getString("XarxaACEntityDaoImpl.identityNotFound"), identitat.getCodiIdentitat())); //$NON-NLS-1$
			}
		}

		if (nomRol != null) {
			String codiAplicacio = null;
			String nomRolReal = null;
			String codiBbdd = null;
			String partDeAplicacio = null;
			String[] partsDeRol = nomRol.split("@"); //$NON-NLS-1$
			nomRolReal = partsDeRol[0];
			partDeAplicacio = partsDeRol[1];
			String partsDeAplicacio[] = partDeAplicacio.split(">"); //$NON-NLS-1$
			codiBbdd = partsDeAplicacio[0];
			codiAplicacio = partsDeAplicacio[1];

			RolEntity rolEntity = getRolEntityDao()
					.findByNomRolAndCodiAplicacioAndCodiDispatcher(nomRolReal,
							codiAplicacio, codiBbdd);
			if (rolEntity == null) {
				throw new SeyconException(String.format(Messages.getString("XarxaACEntityDaoImpl.roleNotFound"), nomRol)); //$NON-NLS-1$
			}
			if (rolEntity != null) {
				target.setRole(rolEntity);
				target.setUsuari(null);
				target.setGrup(null);
			}
		}
		if (codiUsuari != null) {
			UsuariEntity usuariEntity = getUsuariEntityDao().findByCodi(
					codiUsuari);
			if (usuariEntity == null) {
				throw new SeyconException(String.format(Messages.getString("XarxaACEntityDaoImpl.userNotFound"), codiUsuari)); //$NON-NLS-1$
			}
			if (usuariEntity != null) {
				target.setRole(null);
				target.setUsuari(usuariEntity);
				target.setGrup(null);
			}
		}
		if (codiGrup != null) {
			GrupEntity grupEntity = getGrupEntityDao().findByCodi(codiGrup);
			if (grupEntity == null) {
				throw new SeyconException(String.format(Messages.getString("XarxaACEntityDaoImpl.groupNotFound"), codiGrup)); //$NON-NLS-1$
			}
			if (grupEntity != null) {
				target.setRole(null);
				target.setUsuari(null);
				target.setGrup(grupEntity);
			}
		}
	}

	/**
	 * @see es.caib.seycon.ng.model.XarxaACEntityDao#networkAuthorizationToEntity(es.caib.seycon.ng.comu.NetworkAuthorization,
	 *      es.caib.seycon.ng.model.XarxaACEntity)
	 */
	public void networkAuthorizationToEntity(
			es.caib.seycon.ng.comu.NetworkAuthorization source,
			es.caib.seycon.ng.model.XarxaACEntity target, boolean copyIfNull) {
		super.networkAuthorizationToEntity(source, target, copyIfNull);
		networkAuthorizationToEntityCustom(source, target);
	}

	public List<XarxaACEntity> find(
			final java.lang.String queryString,
			final es.caib.seycon.ng.model.Parameter[] parameters) {
		try {
			java.util.List results = new QueryBuilder().query(this,
					queryString, parameters);
			return results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof XarxaACEntity) {
				XarxaACEntity entity = (XarxaACEntity) obj;
				this.create(entity); // cridem al mètode 1 per 1
			}
		}
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof XarxaACEntity) {
				XarxaACEntity entity = (XarxaACEntity) obj;
				this.update(entity);// cridem al mètode 1 per 1
			}
		}
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof XarxaACEntity) {
				XarxaACEntity entity = (XarxaACEntity) obj;
				this.remove(entity);// cridem al mètode 1 per 1
			}
		}
	}

}