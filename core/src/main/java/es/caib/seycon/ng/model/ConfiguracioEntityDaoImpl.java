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

import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.Configuracio;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.ConfiguracioEntity
 */

public class ConfiguracioEntityDaoImpl extends
		es.caib.seycon.ng.model.ConfiguracioEntityDaoBase {
	
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

		AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao()
				.auditoriaToEntity(auditoria);
		getAuditoriaEntityDao().create(auditoriaEntity);
	}
	
	public void create(
			es.caib.seycon.ng.model.ConfiguracioEntity configuracio)
			throws RuntimeException {
		try {
			super.create(configuracio);
			getSession(false).flush();
			String parametre = configuracio.getCodi();
			auditarConfiguracio("C", parametre); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("ConfiguracioEntityDaoImpl.0"),  //$NON-NLS-1$
					configuracio.getCodi(),
					message));
		}
	}

	public void update(es.caib.seycon.ng.model.ConfiguracioEntity configuracio)
			throws RuntimeException {
		try {
			super.update(configuracio);
			getSession(false).flush();
			String parametre = configuracio.getCodi();
			auditarConfiguracio("U", parametre); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("ConfiguracioEntityDaoImpl.1"),  //$NON-NLS-1$
					configuracio.getCodi(), 
					message));
		}
	}

	public void remove(es.caib.seycon.ng.model.ConfiguracioEntity configuracio)
			throws RuntimeException {
		try {
			String parametre = configuracio.getCodi();
			super.remove(configuracio);
			getSession(false).flush();
			auditarConfiguracio("D", parametre);			 //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("ConfiguracioEntityDaoImpl.2"),  //$NON-NLS-1$
					configuracio.getCodi(),
					message));
		}
	}

	public void toConfiguracio(
			es.caib.seycon.ng.model.ConfiguracioEntity sourceEntity,
			es.caib.seycon.ng.comu.Configuracio targetVO) {
		super.toConfiguracio(sourceEntity, targetVO);
		toConfiguracioCustom(sourceEntity, targetVO);
	}

	public void toConfiguracioCustom(
			es.caib.seycon.ng.model.ConfiguracioEntity sourceEntity,
			es.caib.seycon.ng.comu.Configuracio targetVO) {
		XarxaEntity xarxaEntity = sourceEntity.getXarxa();
		if (xarxaEntity != null) {
			targetVO.setCodiXarxa(xarxaEntity.getCodi());
		}
	}

	public es.caib.seycon.ng.comu.Configuracio toConfiguracio(
			final es.caib.seycon.ng.model.ConfiguracioEntity entity) {
		Configuracio configuracio = super.toConfiguracio(entity);
		toConfiguracioCustom(entity, configuracio);
		return configuracio;
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private es.caib.seycon.ng.model.ConfiguracioEntity loadConfiguracioEntityFromConfiguracio(
			es.caib.seycon.ng.comu.Configuracio configuracio) {
		ConfiguracioEntity configuracioEntity = null;
		if (configuracio.getId() != null) {
			configuracioEntity = load(configuracio.getId());
		}
		if (configuracioEntity == null) {
			configuracioEntity = newConfiguracioEntity();
		}
		return configuracioEntity;
	}

	/**
	 * @see es.caib.seycon.ng.model.ConfiguracioEntityDao#configuracioToEntity(es.caib.seycon.ng.Configuracio)
	 */
	public es.caib.seycon.ng.model.ConfiguracioEntity configuracioToEntity(
			es.caib.seycon.ng.comu.Configuracio configuracio) {
		es.caib.seycon.ng.model.ConfiguracioEntity entity = this
				.loadConfiguracioEntityFromConfiguracio(configuracio);
		this.configuracioToEntity(configuracio, entity, true);
		return entity;
	}

	public void configuracioToEntityCustom(
			es.caib.seycon.ng.comu.Configuracio sourceVO,
			es.caib.seycon.ng.model.ConfiguracioEntity targetEntity) {
		String codiXarxa = sourceVO.getCodiXarxa();
		if (codiXarxa != null && codiXarxa.trim().compareTo("") != 0) { //$NON-NLS-1$
			XarxaEntity xarxaEntity = getXarxaEntityDao().findByCodi(codiXarxa);
			if (xarxaEntity != null) {
				targetEntity.setXarxa(xarxaEntity);
			} else {
				throw new SeyconException(String.format(Messages.getString("ConfiguracioEntityDaoImpl.3"), codiXarxa)); //$NON-NLS-1$
			}
		} else {
			targetEntity.setXarxa(null);
		}
	}

	/**
	 * @see es.caib.seycon.ng.model.ConfiguracioEntityDao#configuracioToEntity(es.caib.seycon.ng.Configuracio,
	 *      es.caib.seycon.ng.model.ConfiguracioEntity)
	 */
	public void configuracioToEntity(
			es.caib.seycon.ng.comu.Configuracio sourceVO,
			es.caib.seycon.ng.model.ConfiguracioEntity targetEntity,
			boolean copyIfNull) {
		super.configuracioToEntity(sourceVO, targetEntity, copyIfNull);
		configuracioToEntityCustom(sourceVO, targetEntity);
	}

	public ConfiguracioEntity findByCodiAndCodiXarxa(final java.lang.String codi, final java.lang.String codiXarxa) {
		if (codiXarxa != null) {
			Object result = findByCodiAndCodiXarxa(
					"select configuracio " //$NON-NLS-1$
							+ "from es.caib.seycon.ng.model.ConfiguracioEntity configuracio " //$NON-NLS-1$
							+ "left join configuracio.xarxa as xarxa " //$NON-NLS-1$
							+ "where configuracio.codi = :codi and xarxa.codi = :codiXarxa)", //$NON-NLS-1$
					codi, codiXarxa);
			return (ConfiguracioEntity) result;
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
	public ConfiguracioEntity findByCodiAndCodiXarxa(
			final java.lang.String queryString, final java.lang.String codi,
			final java.lang.String codiXarxa) {
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
							Messages.getString("ConfiguracioEntityDaoImpl.4"),  //$NON-NLS-1$
									queryString));
				} else if (results.size() == 1) {
					result = results.iterator().next();
				}
			}
			return (ConfiguracioEntity) result;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof ConfiguracioEntity) {
				ConfiguracioEntity config = (ConfiguracioEntity) obj;
				this.create(config); // cridem al mètode 1 per 1
			}
		}
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof ConfiguracioEntity) {
				ConfiguracioEntity config = (ConfiguracioEntity) obj;
				this.update(config);// cridem al mètode 1 per 1
			}
		}
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof ConfiguracioEntity) {
				ConfiguracioEntity config = (ConfiguracioEntity) obj;
				this.remove(config);// cridem al mètode 1 per 1
			}
		}
	}

}