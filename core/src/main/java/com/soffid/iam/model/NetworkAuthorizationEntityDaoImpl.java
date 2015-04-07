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
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.NetworkAuthorizationEntity;
import com.soffid.iam.model.NetworkEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.UserEntity;
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
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

/**
 * @see es.caib.seycon.ng.model.XarxaACEntity
 */
public class NetworkAuthorizationEntityDaoImpl extends
		com.soffid.iam.model.NetworkAuthorizationEntityDaoBase {

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
				Messages.getString("NetworkAuthorizationEntityDaoImpl.1")); //$NON-NLS-1$
		auditoria.setData(dateFormat.format(GregorianCalendar.getInstance()
				.getTime()));
		auditoria.setObjecte("SC_AUTXAR"); //$NON-NLS-1$
		AuditEntity auditoriaEntity = getAuditEntityDao().auditoriaToEntity(auditoria);
		getAuditEntityDao().create(auditoriaEntity);
	}

	public void update(NetworkAuthorizationEntity xarxaAC) {
		try {
			super.update(xarxaAC);
			getSession(false).flush();
			String codiGrup = xarxaAC.getGroup() == null ? null : xarxaAC.getGroup().getCode();
			String codiUsuari = xarxaAC.getUser() == null ? null : xarxaAC.getUser().getUserName();
			String nomRol = null;
			String codiAplicacio = null;
			String codiBbdd = null;
			if (xarxaAC.getRole() != null) {
				nomRol = xarxaAC.getRole().getName();
				codiAplicacio = xarxaAC.getRole().getApplication().getCode();
				codiBbdd = xarxaAC.getRole().getDatabases().getCode();
			}
			String maquines = xarxaAC.getHostsName();
			auditarXarxaAC("U", xarxaAC.getNetwork().getCode(), codiGrup, codiUsuari, nomRol, codiAplicacio, codiBbdd, maquines);
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("NetworkAuthorizationEntityDaoImpl.errorUpdating"), xarxaAC.getHostsName(), message));
		}
	}

	public void create(com.soffid.iam.model.NetworkAuthorizationEntity xarxaAC) throws RuntimeException {
		try {
			super.create(xarxaAC);
			getSession(false).flush();
			String codiGrup = xarxaAC.getGroup() == null ? null : xarxaAC.getGroup().getCode();
			String codiUsuari = xarxaAC.getUser() == null ? null : xarxaAC.getUser().getUserName();
			String nomRol = null;
			String codiAplicacio = null;
			String codiBbdd = null;
			if (xarxaAC.getRole() != null) {
				nomRol = xarxaAC.getRole().getName();
				codiAplicacio = xarxaAC.getRole().getApplication().getCode();
				codiBbdd = xarxaAC.getRole().getDatabases().getCode();
			}
			String maquines = xarxaAC.getHostsName();
			auditarXarxaAC("C", xarxaAC.getNetwork().getCode(), codiGrup, codiUsuari, nomRol, codiAplicacio, codiBbdd, maquines);
			xarxaAC.setId(xarxaAC.getId());
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("NetworkAuthorizationEntityDaoImpl.errorCreating"), xarxaAC.getHostsName(), message));
		}
	}

	public void remove(com.soffid.iam.model.NetworkAuthorizationEntity xarxaAC) throws RuntimeException {
		try {
			String codiXarxa = xarxaAC.getNetwork().getCode();
			String codiGrup = xarxaAC.getGroup() == null ? null : xarxaAC.getGroup().getCode();
			String codiUsuari = xarxaAC.getUser() == null ? null : xarxaAC.getUser().getUserName();
			String maquines = xarxaAC.getHostsName();
			String nomRol = null;
			String codiAplicacio = null;
			String codiBbdd = null;
			if (xarxaAC.getRole() != null) {
				nomRol = xarxaAC.getRole().getName();
				codiAplicacio = xarxaAC.getRole().getApplication().getCode();
				codiBbdd = xarxaAC.getRole().getDatabases().getCode();
			}
			super.remove(xarxaAC);
			getSession(false).flush();
			auditarXarxaAC("D", codiXarxa, codiGrup, codiUsuari, nomRol, //$NON-NLS-1$
					codiAplicacio, codiBbdd, maquines);
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("NetworkAuthorizationEntityDaoImpl.errorDeleting"), xarxaAC.getHostsName(), message));
		}
	}

	public void toNetworkAuthorization(com.soffid.iam.model.NetworkAuthorizationEntity source, es.caib.seycon.ng.comu.NetworkAuthorization target) {
		super.toNetworkAuthorization(source, target);
		toNetworkAuthorizationCustom(source, target);
	}

	private void toNetworkAuthorizationCustom(com.soffid.iam.model.NetworkAuthorizationEntity source, es.caib.seycon.ng.comu.NetworkAuthorization target) {
		NetworkEntity xarxa = source.getNetwork();
		if (xarxa != null) {
			target.setCodiXarxa(xarxa.getCode());
		}
		GroupEntity grup = source.getGroup();
		RoleEntity rol = source.getRole();
		UserEntity usuari = source.getUser();
		if (grup != null) {
			Identitat identitat = getGroupEntityDao().toIdentitat(grup);
			target.setIdentitat(identitat);
		} else if (rol != null) {
			Identitat identitat = getRoleEntityDao().toIdentitat(rol);
			target.setIdentitat(identitat);
		} else {
			Identitat identitat = getUserEntityDao().toIdentitat(usuari);
			target.setIdentitat(identitat);
		}
		target.setMascara(source.getHostsName());
	}

	/**
	 * @see es.caib.seycon.ng.model.XarxaACEntityDao#toNetworkAuthorization(es.caib.seycon.ng.model.XarxaACEntity)
	 */
	public es.caib.seycon.ng.comu.NetworkAuthorization toNetworkAuthorization(final com.soffid.iam.model.NetworkAuthorizationEntity entity) {
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
	private com.soffid.iam.model.NetworkAuthorizationEntity loadXarxaACEntityFromNetworkAuthorization(es.caib.seycon.ng.comu.NetworkAuthorization networkAuthorization) {
		NetworkAuthorizationEntity xarxaACEntity = null;
		if (networkAuthorization.getId() != null) {
			xarxaACEntity = load(networkAuthorization.getId());
		}
		if (xarxaACEntity == null) {
			xarxaACEntity = newNetworkAuthorizationEntity();
		}
		return xarxaACEntity;
	}

	/**
	 * @see es.caib.seycon.ng.model.XarxaACEntityDao#networkAuthorizationToEntity(es.caib.seycon.ng.comu.NetworkAuthorization)
	 */
	public com.soffid.iam.model.NetworkAuthorizationEntity networkAuthorizationToEntity(es.caib.seycon.ng.comu.NetworkAuthorization networkAuthorization) {
		com.soffid.iam.model.NetworkAuthorizationEntity entity = this.loadXarxaACEntityFromNetworkAuthorization(networkAuthorization);
		this.networkAuthorizationToEntity(networkAuthorization, entity, true);
		return entity;
	}

	public void networkAuthorizationToEntityCustom(es.caib.seycon.ng.comu.NetworkAuthorization source, com.soffid.iam.model.NetworkAuthorizationEntity target) {
		NetworkEntity xarxa = getNetworkEntityDao().findByCode(source.getCodiXarxa());
		if (xarxa != null) {
			target.setNetwork(xarxa);
		} else {
			throw new SeyconException(String.format(Messages.getString("NetworkAuthorizationEntityDaoImpl.networkNotFound"), source.getCodiXarxa())); //$NON-NLS-1$
		}
		target.setHostsName(source.getMascara());

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
					RoleEntity rolEntity = getRoleEntityDao().findRoleByRoleNameAndApplicationCodeAndSystemCode(nomRolReal, codiAplicacio, codiBbdd);
					esRol = rolEntity != null;
					if (esRol) {
						nomRol = codiIdentitat;
					}
				}
			}

			// USUARI
			boolean esUsuari = false;
			if (!esRol) {
				UserEntity usuariEntity = getUserEntityDao().findByCode(codiIdentitat);
				esUsuari = usuariEntity != null;
				if (esUsuari) {
					codiUsuari = codiIdentitat;
				}
			}

			// GRUP
			boolean esGrup = false;
			if (!esRol && !esUsuari) {
				GroupEntity grupEntity = getGroupEntityDao().findByCode(codiIdentitat);
				esGrup = grupEntity != null;
				if (esGrup) {
					codiGrup = codiIdentitat;
				}
			}

			if (!esRol && !esUsuari && !esGrup) {
				throw new SeyconException(String.format(Messages.getString("NetworkAuthorizationEntityDaoImpl.identityNotFound"), identitat.getCodiIdentitat())); //$NON-NLS-1$
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

			RoleEntity rolEntity = getRoleEntityDao().findRoleByRoleNameAndApplicationCodeAndSystemCode(nomRolReal, codiAplicacio, codiBbdd);
			if (rolEntity == null) {
				throw new SeyconException(String.format(Messages.getString("NetworkAuthorizationEntityDaoImpl.roleNotFound"), nomRol)); //$NON-NLS-1$
			}
			if (rolEntity != null) {
				target.setRole(rolEntity);
				target.setUser(null);
				target.setGroup(null);
			}
		}
		if (codiUsuari != null) {
			UserEntity usuariEntity = getUserEntityDao().findByCode(codiUsuari);
			if (usuariEntity == null) {
				throw new SeyconException(String.format(Messages.getString("NetworkAuthorizationEntityDaoImpl.userNotFound"), codiUsuari)); //$NON-NLS-1$
			}
			if (usuariEntity != null) {
				target.setRole(null);
				target.setUser(usuariEntity);
				target.setGroup(null);
			}
		}
		if (codiGrup != null) {
			GroupEntity grupEntity = getGroupEntityDao().findByCode(codiGrup);
			if (grupEntity == null) {
				throw new SeyconException(String.format(Messages.getString("NetworkAuthorizationEntityDaoImpl.groupNotFound"), codiGrup)); //$NON-NLS-1$
			}
			if (grupEntity != null) {
				target.setRole(null);
				target.setUser(null);
				target.setGroup(grupEntity);
			}
		}
	}

	/**
	 * @see es.caib.seycon.ng.model.XarxaACEntityDao#networkAuthorizationToEntity(es.caib.seycon.ng.comu.NetworkAuthorization,
	 *      es.caib.seycon.ng.model.XarxaACEntity)
	 */
	public void networkAuthorizationToEntity(es.caib.seycon.ng.comu.NetworkAuthorization source, com.soffid.iam.model.NetworkAuthorizationEntity target, boolean copyIfNull) {
		super.networkAuthorizationToEntity(source, target, copyIfNull);
		networkAuthorizationToEntityCustom(source, target);
	}

	public List<NetworkAuthorizationEntity> find(final java.lang.String queryString, final es.caib.seycon.ng.model.Parameter[] parameters) {
		try {
			java.util.List results = new QueryBuilder().query(this,
					queryString, parameters);
			return results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof NetworkAuthorizationEntity) {
                NetworkAuthorizationEntity entity = (NetworkAuthorizationEntity) obj;
                this.create(entity);
            }
        }
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof NetworkAuthorizationEntity) {
                NetworkAuthorizationEntity entity = (NetworkAuthorizationEntity) obj;
                this.update(entity);
            }
        }
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof NetworkAuthorizationEntity) {
                NetworkAuthorizationEntity entity = (NetworkAuthorizationEntity) obj;
                this.remove(entity);
            }
        }
	}

}