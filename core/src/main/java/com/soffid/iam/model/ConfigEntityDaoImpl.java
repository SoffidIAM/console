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
import com.soffid.iam.api.Configuration;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.ConfigEntity;
import com.soffid.iam.model.NetworkEntity;
import com.soffid.iam.utils.ExceptionTranslator;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.*;

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
		Audit auditoria = new Audit();
		auditoria.setAuthor(codiUsuari);
		auditoria.setAction(accio);
		auditoria.setConfigurationParameter(parametre);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
		auditoria.setAdditionalInfo(dateFormat.format(GregorianCalendar.getInstance().getTime()));
		auditoria.setObject("SC_CONFIG"); //$NON-NLS-1$

		AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
		getAuditEntityDao().create(auditoriaEntity);
	}
	
	public void create(com.soffid.iam.model.ConfigEntity configuracio) throws RuntimeException {
		try {
			super.create(configuracio);
			getSession(false).flush();
			String parametre = configuracio.getName();
			auditarConfiguracio("C", parametre); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("ConfigEntityDaoImpl.0"), configuracio.getName(), message));
		}
	}

	public void update(com.soffid.iam.model.ConfigEntity configuracio) throws RuntimeException {
		try {
			super.update(configuracio);
			getSession(false).flush();
			String parametre = configuracio.getName();
			auditarConfiguracio("U", parametre); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("ConfigEntityDaoImpl.1"), configuracio.getName(), message));
		}
	}

	public void remove(com.soffid.iam.model.ConfigEntity configuracio) throws RuntimeException {
		try {
			String parametre = configuracio.getName();
			super.remove(configuracio);
			getSession(false).flush();
			auditarConfiguracio("D", parametre);			 //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("ConfigEntityDaoImpl.2"), configuracio.getName(), message));
		}
	}

	public void toConfiguration(com.soffid.iam.model.ConfigEntity sourceEntity, com.soffid.iam.api.Configuration targetVO) {
		super.toConfiguration(sourceEntity, targetVO);
		toConfiguracioCustom(sourceEntity, targetVO);
	}

	public void toConfiguracioCustom(com.soffid.iam.model.ConfigEntity sourceEntity, com.soffid.iam.api.Configuration targetVO) {
		NetworkEntity xarxaEntity = sourceEntity.getNetwork();
		if (xarxaEntity != null) {
			targetVO.setNetworkCode(xarxaEntity.getName());
		}
	}

	public com.soffid.iam.api.Configuration toConfiguration(final com.soffid.iam.model.ConfigEntity entity) {
		Configuration configuracio = super.toConfiguration(entity);
		toConfiguracioCustom(entity, configuracio);
		return configuracio;
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private com.soffid.iam.model.ConfigEntity loadConfiguracioEntityFromConfiguracio(com.soffid.iam.api.Configuration configuracio) {
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
	public com.soffid.iam.model.ConfigEntity configurationToEntity(com.soffid.iam.api.Configuration configuracio) {
		com.soffid.iam.model.ConfigEntity entity = this.loadConfiguracioEntityFromConfiguracio(configuracio);
		this.configurationToEntity(configuracio, entity, true);
		return entity;
	}

	public void configuracioToEntityCustom(com.soffid.iam.api.Configuration sourceVO, com.soffid.iam.model.ConfigEntity targetEntity) {
		String codiXarxa = sourceVO.getNetworkCode();
		if (codiXarxa != null && codiXarxa.trim().compareTo("") != 0) { //$NON-NLS-1$
			NetworkEntity xarxaEntity = getNetworkEntityDao().findByName(codiXarxa);
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
	public void configurationToEntity(com.soffid.iam.api.Configuration sourceVO, com.soffid.iam.model.ConfigEntity targetEntity, boolean copyIfNull) {
		super.configurationToEntity(sourceVO, targetEntity, copyIfNull);
		configuracioToEntityCustom(sourceVO, targetEntity);
	}

	public ConfigEntity findByCodeAndNetworkCode(final java.lang.String codi, final java.lang.String codiXarxa) {
		if (codiXarxa != null) {
			Object result = findByCodiAndCodiXarxa(
					"select configuracio " //$NON-NLS-1$
							+ "from com.soffid.iam.model.ConfigEntity configuracio " //$NON-NLS-1$
							+ "left join configuracio.network as xarxa " //$NON-NLS-1$
							+ "where configuracio.tenant.id = :tenantId and configuracio.name = :codi and xarxa.name = :codiXarxa)", //$NON-NLS-1$
					codi, codiXarxa);
			return (ConfigEntity) result;
		}
		return findByCodiAndCodiXarxa(
				"select configuracio " //$NON-NLS-1$
						+ "from com.soffid.iam.model.ConfigEntity configuracio " //$NON-NLS-1$
						+ "where configuracio.tenant.id = :tenantId and configuracio.name = :codi and configuracio.network is null", //$NON-NLS-1$
				codi, null);
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
			queryObject.setParameter("tenantId", Security.getCurrentTenantId()); //$NON-NLS-1$
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

	@Override
	protected void handleCreateMasterConfig(ConfigEntity entity) throws Exception {
		if (! entity.getName().equals("soffid.schedule.timeStamp"))
			throw new InternalErrorException("Not allowed to create master configuration "+entity.getName());
		entity.setTenant( getTenantEntityDao().findByName("master"));
		getSession().save(entity);
		getSession(false).flush();
		String parametre = entity.getName();
		auditarConfiguracio("C", parametre); //$NON-NLS-1$
	}

}