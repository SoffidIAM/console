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
import com.soffid.iam.model.ConfigEntity;
import com.soffid.iam.model.NetworkEntity;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.Configuracio;
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
 * @see es.caib.seycon.ng.model.ConfiguracioEntity
 */

public class ConfigEntityDaoImpl extends
		com.soffid.iam.model.ConfigEntityDaoBase {
	
	private void auditarConfiguracio(String accio, String parametre) {
		String codiUsuari = Security.getCurrentAccount();
		Auditoria auditoria = new Auditoria();
		auditoria.setAutor(codiUsuari);
		auditoria.setAccio(accio);
		auditoria.setParametreConfiguracio(parametre);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
		auditoria.setData(dateFormat.format(GregorianCalendar.getInstance()
				.getTime()));
		auditoria.setObjecte("SC_CONFIG"); //$NON-NLS-1$

		AuditEntity auditoriaEntity = getAuditEntityDao().auditoriaToEntity(auditoria);
		getAuditEntityDao().create(auditoriaEntity);
	}
	
	public void create(com.soffid.iam.model.ConfigEntity configuracio) throws RuntimeException {
		try {
			super.create(configuracio);
			getSession(false).flush();
			String parametre = configuracio.getCode();
			auditarConfiguracio("C", parametre); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("ConfigEntityDaoImpl.0"), configuracio.getCode(), message));
		}
	}

	public void update(com.soffid.iam.model.ConfigEntity configuracio) throws RuntimeException {
		try {
			super.update(configuracio);
			getSession(false).flush();
			String parametre = configuracio.getCode();
			auditarConfiguracio("U", parametre); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("ConfigEntityDaoImpl.1"), configuracio.getCode(), message));
		}
	}

	public void remove(com.soffid.iam.model.ConfigEntity configuracio) throws RuntimeException {
		try {
			String parametre = configuracio.getCode();
			super.remove(configuracio);
			getSession(false).flush();
			auditarConfiguracio("D", parametre);			 //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("ConfigEntityDaoImpl.2"), configuracio.getCode(), message));
		}
	}

	public void toConfiguracio(com.soffid.iam.model.ConfigEntity sourceEntity, es.caib.seycon.ng.comu.Configuracio targetVO) {
		super.toConfiguracio(sourceEntity, targetVO);
		toConfiguracioCustom(sourceEntity, targetVO);
	}

	public void toConfiguracioCustom(com.soffid.iam.model.ConfigEntity sourceEntity, es.caib.seycon.ng.comu.Configuracio targetVO) {
		NetworkEntity xarxaEntity = sourceEntity.getNetwork();
		if (xarxaEntity != null) {
			targetVO.setCodiXarxa(xarxaEntity.getCode());
		}
	}

	public es.caib.seycon.ng.comu.Configuracio toConfiguracio(final com.soffid.iam.model.ConfigEntity entity) {
		Configuracio configuracio = super.toConfiguracio(entity);
		toConfiguracioCustom(entity, configuracio);
		return configuracio;
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private com.soffid.iam.model.ConfigEntity loadConfiguracioEntityFromConfiguracio(es.caib.seycon.ng.comu.Configuracio configuracio) {
		ConfigEntity configuracioEntity = null;
		if (configuracio.getId() != null) {
			configuracioEntity = load(configuracio.getId());
		}
		if (configuracioEntity == null) {
			configuracioEntity = newConfigEntity();
		}
		return configuracioEntity;
	}

	/**
	 * @see es.caib.seycon.ng.model.ConfiguracioEntityDao#configuracioToEntity(es.caib.seycon.ng.Configuracio)
	 */
	public com.soffid.iam.model.ConfigEntity configuracioToEntity(es.caib.seycon.ng.comu.Configuracio configuracio) {
		com.soffid.iam.model.ConfigEntity entity = this.loadConfiguracioEntityFromConfiguracio(configuracio);
		this.configuracioToEntity(configuracio, entity, true);
		return entity;
	}

	public void configuracioToEntityCustom(es.caib.seycon.ng.comu.Configuracio sourceVO, com.soffid.iam.model.ConfigEntity targetEntity) {
		String codiXarxa = sourceVO.getCodiXarxa();
		if (codiXarxa != null && codiXarxa.trim().compareTo("") != 0) { //$NON-NLS-1$
			NetworkEntity xarxaEntity = getNetworkEntityDao().findByCode(codiXarxa);
			if (xarxaEntity != null) {
				targetEntity.setNetwork(xarxaEntity);
			} else {
				throw new SeyconException(String.format(Messages.getString("ConfigEntityDaoImpl.3"), codiXarxa)); //$NON-NLS-1$
			}
		} else {
			targetEntity.setNetwork(null);
		}
	}

	/**
	 * @see es.caib.seycon.ng.model.ConfiguracioEntityDao#configuracioToEntity(es.caib.seycon.ng.Configuracio,
	 *      es.caib.seycon.ng.model.ConfiguracioEntity)
	 */
	public void configuracioToEntity(es.caib.seycon.ng.comu.Configuracio sourceVO, com.soffid.iam.model.ConfigEntity targetEntity, boolean copyIfNull) {
		super.configuracioToEntity(sourceVO, targetEntity, copyIfNull);
		configuracioToEntityCustom(sourceVO, targetEntity);
	}

	public ConfigEntity findByCodeAndNetworkCode(final java.lang.String codi, final java.lang.String codiXarxa) {
		if (codiXarxa != null) {
			Object result = findByCodiAndCodiXarxa(
					"select configuracio " //$NON-NLS-1$
							+ "from es.caib.seycon.ng.model.ConfiguracioEntity configuracio " //$NON-NLS-1$
							+ "left join configuracio.xarxa as xarxa " //$NON-NLS-1$
							+ "where configuracio.codi = :codi and xarxa.codi = :codiXarxa)", //$NON-NLS-1$
					codi, codiXarxa);
			return (ConfigEntity) result;
		}
		return findByCodiAndCodiXarxa(
				"select configuracio " //$NON-NLS-1$
						+ "from es.caib.seycon.ng.model.ConfiguracioEntity configuracio " //$NON-NLS-1$
						+ "where configuracio.codi = :codi and configuracio.xarxa is null", //$NON-NLS-1$
				codi, null);
	}

        /**
         * @see es.caib.seycon.ng.model.UsuariEntityDao#find(int, java.lang.String,
         *      es.caib.seycon.ng.model.Parameter[])
         */
        public List find(final java.lang.String queryString,
                        final es.caib.seycon.ng.model.Parameter[] parameters) {
                try {
                        java.util.List results = new QueryBuilder().query(this,
                                        queryString, parameters);
                        return results;
                } catch (org.hibernate.HibernateException ex) {
                        throw super.convertHibernateAccessException(ex);
                }
        }
        
	/**
	 * @see es.caib.seycon.ng.model.ConfiguracioEntityDao#findByCodiAndCodiXarxa(int,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	public ConfigEntity findByCodiAndCodiXarxa(final java.lang.String queryString, final java.lang.String codi, final java.lang.String codiXarxa) {
		try {
			org.hibernate.Query queryObject = super.getSession(false)
					.createQuery(queryString);
			queryObject.setParameter("codi", codi); //$NON-NLS-1$
			if (codiXarxa != null)
				queryObject.setParameter("codiXarxa", codiXarxa); //$NON-NLS-1$
			java.util.Set results = new java.util.LinkedHashSet(queryObject
					.list());
			java.lang.Object result = null;
			if (results != null) {
				if (results.size() > 1) {
					throw new org.springframework.dao.InvalidDataAccessResourceUsageException(String.format(
							Messages.getString("ConfigEntityDaoImpl.4"),  //$NON-NLS-1$
									queryString));
				} else if (results.size() == 1) {
					result = results.iterator().next();
				}
			}
			return (ConfigEntity) result;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof ConfigEntity) {
                ConfigEntity config = (ConfigEntity) obj;
                this.create(config);
            }
        }
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof ConfigEntity) {
                ConfigEntity config = (ConfigEntity) obj;
                this.update(config);
            }
        }
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof ConfigEntity) {
                ConfigEntity config = (ConfigEntity) obj;
                this.remove(config);
            }
        }
	}

}