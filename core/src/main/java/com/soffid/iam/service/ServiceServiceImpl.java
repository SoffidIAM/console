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
package com.soffid.iam.service;

import es.caib.seycon.ng.servei.*;

import com.soffid.iam.api.Service;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.ServiceEntity;
import com.soffid.iam.utils.ConfigurationCache;

import es.caib.seycon.ng.comu.ServeiSearchCriteria;
import es.caib.seycon.ng.exception.InternalErrorException;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

/**
 * @see es.caib.seycon.ng.servei.ServeiService
 */
public class ServiceServiceImpl extends
		com.soffid.iam.service.ServiceServiceBase {

	/**
	 * @see es.caib.seycon.ng.servei.ServeiService#create(es.caib.seycon.ng.comu.Servei)
	 */
	protected com.soffid.iam.api.Service handleCreate(com.soffid.iam.api.Service servei) throws java.lang.Exception {
		ServiceEntity servicesSameCode = getServiceEntityDao().findByName(servei.getCode());
		if(servicesSameCode != null)
			throw new InternalErrorException(String.format(Messages.getString("ServiceServiceImpl.CodeServiceExists"), servei.getCode())); 
		ServiceEntity serveiEntity = getServiceEntityDao().serviceToEntity(servei);
		getServiceEntityDao().create(serveiEntity);
		servei.setId(serveiEntity.getId());
		return getServiceEntityDao().toService(serveiEntity);
	}

	/**
	 * @see es.caib.seycon.ng.servei.ServeiService#delete(es.caib.seycon.ng.comu.Servei)
	 */
	protected void handleDelete(com.soffid.iam.api.Service servei) throws java.lang.Exception {
		ServiceEntity serveiEntity = getServiceEntityDao().findByName(servei.getCode());
		if(serveiEntity != null){
			getServiceEntityDao().remove(serveiEntity);
		}
	}

	/**
	 * @see es.caib.seycon.ng.servei.ServeiService#update(es.caib.seycon.ng.comu.Servei)
	 */
	protected com.soffid.iam.api.Service handleUpdate(com.soffid.iam.api.Service servei) throws java.lang.Exception {
		ServiceEntity entity = getServiceEntityDao().serviceToEntity(servei);
		getServiceEntityDao().update(entity);
		return getServiceEntityDao().toService(entity);
	}

	protected Collection<Service> handleFindServicesByCriteria(String codi, String descripcio) throws Exception {
		ServeiSearchCriteria criteri = new ServeiSearchCriteria();
		int limitResults = Integer.parseInt(ConfigurationCache.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
		
		if ((codi != null) && codi.equals("%")) //$NON-NLS-1$
		{
			criteri.setCodi(null);
		}
		
		else
		{
			criteri.setCodi(codi);
		}
		
		if ((descripcio != null) && (descripcio.equals("%"))) //$NON-NLS-1$
		{
			criteri.setDescripcio(null);
		}
		
		else
		{
			criteri.setDescripcio(descripcio);
		}
		
		Collection<ServiceEntity> serveis = getServiceEntityDao().findByCriteria(criteri);
		if(serveis != null)
		{
			// Check maximum number of results
			if (serveis.size() > limitResults)
			{
				return getServiceEntityDao().toServiceList(serveis).subList(0, limitResults);
			}
			
			return getServiceEntityDao().toServiceList(serveis);
		}
		
		return new Vector();
	}

	protected Service handleFindServiceByName(String codi) throws Exception {
		ServiceEntity serveiEntity = getServiceEntityDao().findByName(codi);
		if(serveiEntity != null){
			Service servei = getServiceEntityDao().toService(serveiEntity);
			return servei;
		}
		return null;
	}

	protected Collection<Service> handleGetServices() throws Exception {
		List<ServiceEntity> col = getServiceEntityDao().loadAll();
		return getServiceEntityDao().toServiceList(col);
	}
}