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

import com.soffid.iam.api.Configuration;
import com.soffid.iam.config.Config;
import com.soffid.iam.doc.exception.NASException;
import com.soffid.iam.doc.nas.NASManager;
import com.soffid.iam.model.BlobConfigurationEntity;
import com.soffid.iam.model.BlobConfigurationEntityDao;
import com.soffid.iam.model.ConfigEntity;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @see es.caib.seycon.ng.servei.ConfiguracioService
 */
public class ConfigurationServiceImpl
    extends com.soffid.iam.service.ConfigurationServiceBase
{

	protected Collection<Configuration> handleGetParameters() throws Exception {
		List<ConfigEntity> col = this.getConfigEntityDao().loadAll();
		return getConfigEntityDao().toConfigurationList(col);
	}

	/**
	 * Method that implements the functionality to reconfigure NAS.
	 * @param configuracio
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws NASException
	 */
	private void reconfigureNAS(Configuration configuracio) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NASException {
		try
		{
			if (checkAllConfigurationSet(configuracio))
			{
				NASManager.getInstance();
				NASManager.loadConfiguration();
			}
		}
		
		catch (NASException ex)
		{
			Log log = LogFactory.getLog(getClass());
			log.warn(String.format("Document manager: parameter not defined: %1$s", configuracio.getCode()));
		}
	}
	
	/**
	 * Method that implements the functionality to check if all NAS configuration
	 * parameters are defined.
	 * @param configuracio
	 * @return <code>TRUE</code> if all configuration parameters are set.
	 * <code>FALSE</code> otherwise.
	 */
	private boolean checkAllConfigurationSet(Configuration configuracio) {
		boolean configSet = false;
		String docStrategy = ConfigurationCache.getProperty("soffid.ui.docStrategy"); //$NON-NLS-1$
		String tempPath = ConfigurationCache.getProperty("soffid.ui.docTempPath"); //$NON-NLS-1$
		
		if ((docStrategy != null) && (tempPath != null))
		{
			if (docStrategy.equals("es.caib.bpm.nas.comm.LocalFileSystemStrategy") && configuracio.getCode().equals("soffid.ui.docPath")) //$NON-NLS-1$
			{
				return true;
			}
			
			if ((docStrategy.equals("es.caib.bpm.nas.comm.FTPStrategy") || //$NON-NLS-1$
				docStrategy.equals("es.caib.bpm.nas.comm.CIFSStrategy")) && //$NON-NLS-1$
				(ConfigurationCache.getProperty("soffid.ui.docUsername") != null) &&  //$NON-NLS-1$
				(ConfigurationCache.getProperty("soffid.ui.docUserPassword") != null) && //$NON-NLS-1$
				(ConfigurationCache.getProperty("soffid.ui.docServer") != null)) //$NON-NLS-1$
			{
				return true;
			}
			
			if (docStrategy.equals("es.caib.bpm.nas.comm.HTTPStrategy") && //$NON-NLS-1$
				(ConfigurationCache.getProperty("soffid.ui.docServer") != null)) //$NON-NLS-1$
			{
				return true;
			}
		}

		return configSet;
	}

	protected Configuration handleCreate(Configuration configuracio) throws Exception {
		ConfigEntity configuracioEntity = getConfigEntityDao().configurationToEntity(configuracio);
		
		// Check configuration parameter
		getConfigEntityDao().create(configuracioEntity);
		configuracio.setId(new Long(configuracioEntity.getId().longValue()));
		
		if (configuracio.getNetworkCode() == null)
		{
			ConfigurationCache.setProperty(configuracio.getCode(), configuracio.getValue());
			if (Security.isMasterTenant())
				reconfigureNAS(configuracio);
			
		}

		return getConfigEntityDao().toConfiguration(configuracioEntity);
	}

	protected void handleDelete(Configuration configuracio) throws Exception {
		ConfigEntity configuracioEntity = getConfigEntityDao().configurationToEntity(configuracio);
		boolean toRemove = configuracioEntity.getNetwork() == null;
		String codi = configuracioEntity.getName();
		getConfigEntityDao().remove(configuracioEntity);
		if (toRemove) {
			ConfigurationCache.remove(codi);
		}
	}

	protected Configuration handleUpdate(Configuration configuracio) throws Exception {
		ConfigEntity configuracioEntity = getConfigEntityDao().configurationToEntity(configuracio);
		
//		System.setProperty(configuracio.getCode(), configuracio.getValue()); //$NON-NLS-1$
		
		getConfigEntityDao().update(configuracioEntity);
		configuracio = getConfigEntityDao().toConfiguration(configuracioEntity);
		
		if (configuracio.getNetworkCode() == null)
		{
			ConfigurationCache.setProperty(configuracio.getCode(), configuracio.getValue());
			if (Security.isMasterTenant())
				reconfigureNAS(configuracio);
		}
		return configuracio;
	}
	
	private Collection<ConfigEntity> localFindConfiguracioByFiltre(String codi, String valor, String descripcio, String codiXarxa) {
		return getConfigEntityDao().findByFilter (codi, codiXarxa, valor, descripcio);		
	}

	protected Collection<Configuration> handleFindConfigurationByFilter(String codi, String valor, String descripcio, String codiXarxa) throws Exception {
		int limitResults = 0;	// Limit of rows to obtain
		
		// Check limit defined
		if (ConfigurationCache.getProperty("soffid.ui.maxrows") != null) //$NON-NLS-1$
		{
			limitResults = Integer.parseInt(ConfigurationCache.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
		}
		
		if(codi != null && (codi.trim().compareTo("") == 0 || codi.trim().compareTo("%") == 0 )){ //$NON-NLS-1$ //$NON-NLS-2$
			codi = null;
		}
		if(valor != null && (valor.trim().compareTo("") == 0 || valor.trim().compareTo("%") == 0 )){ //$NON-NLS-1$ //$NON-NLS-2$
			valor = null;
		}
		if(descripcio != null && (descripcio.trim().compareTo("") == 0 || descripcio.trim().compareTo("%") == 0 )){ //$NON-NLS-1$ //$NON-NLS-2$
			descripcio = null;
		}
		if(codiXarxa != null && (codiXarxa.trim().compareTo("") == 0 || codiXarxa.trim().compareTo("%") == 0 )){ //$NON-NLS-1$ //$NON-NLS-2$
			codiXarxa = null;
		}
		
		Collection<ConfigEntity> configuracions = localFindConfiguracioByFiltre(codi, valor, descripcio, codiXarxa);
		if (configuracions != null)
		{
			if ((limitResults != 0) && (configuracions.size() > limitResults))
			{
				return getConfigEntityDao().toConfigurationList(configuracions).subList(0, limitResults);
			}
			
			return getConfigEntityDao().toConfigurationList(configuracions);
		}
		
		return new Vector();
	}

	protected Configuration handleFindParameterByNameAndNetworkName(String codiParametre, String codiXarxa) throws Exception {
		ConfigEntity configuracioEntity = getConfigEntityDao().findByCodeAndNetworkCode(codiParametre, codiXarxa);
		if(configuracioEntity != null){
			Configuration configuracio = getConfigEntityDao().toConfiguration(configuracioEntity);
			return configuracio;
		}
		return null;		
	}


	@SuppressWarnings("rawtypes")
	@Override
	protected byte[] handleGetBlob(String name) throws Exception {
		BlobConfigurationEntityDao dao = getBlobConfigurationEntityDao();
		Collection result = dao.findByName(name);
		if (result.isEmpty()) {
			return null;
		} else {
			BlobConfigurationEntity entry = (BlobConfigurationEntity) result.iterator().next();
			return entry.getValue();
		}
	}


	@SuppressWarnings("rawtypes")
	@Override
	protected void handleUpdateBlob(String name, byte[] data) throws Exception {
		BlobConfigurationEntityDao dao = getBlobConfigurationEntityDao();
		Collection result = dao.findByName(name);
		if (result.isEmpty()) {
			BlobConfigurationEntity entity = dao.newBlobConfigurationEntity();
			entity.setName(name);
			entity.setValue(data);
			entity.setVersion("<unknown>"); //$NON-NLS-1$
			dao.create(entity);
		} else {
			BlobConfigurationEntity entry = (BlobConfigurationEntity) result.iterator().next();
			entry.setValue(data);
			dao.update(entry);
		}
	}


	@SuppressWarnings("rawtypes")
	@Override
	protected void handleDeleteBlob(String name) throws Exception {
		BlobConfigurationEntityDao dao = getBlobConfigurationEntityDao();
		Collection result = dao.findByName(name);
		for (Iterator it = result.iterator(); it.hasNext(); )
		{
			BlobConfigurationEntity entry = (BlobConfigurationEntity) it.next();
			dao.remove(entry);
		}
	}


	@Override
	protected void handleUpdateBlob(String name, byte[] data, String version)
			throws Exception
	{
		BlobConfigurationEntityDao dao = getBlobConfigurationEntityDao();
		Collection result = dao.findByName(name);
		if (result.isEmpty()) {
			BlobConfigurationEntity entity = dao.newBlobConfigurationEntity();
			entity.setName(name);
			entity.setValue(data);
			entity.setVersion(version); //$NON-NLS-1$
			dao.create(entity);
		} else {
			BlobConfigurationEntity entry = (BlobConfigurationEntity) result.iterator().next();
			entry.setValue(data);
			entry.setVersion(version);
			dao.update(entry);
		}
	}


	@Override
	protected String handleGetBlobVersion(String name) throws Exception
	{
		BlobConfigurationEntityDao dao = getBlobConfigurationEntityDao();
		Collection result = dao.findByName(name);
		if (result.isEmpty()) {
			return null;
		}
		else
		{
			BlobConfigurationEntity entity = (BlobConfigurationEntity) result.iterator().next();
			return entity.getVersion();
		}
	}

	@Override
	protected Configuration handleFindMasterParameterByNameAndNetwork(
			String parameter, String networkName)
			throws Exception {
		ConfigEntity configuracioEntity = getConfigEntityDao().findByTenantNameAndNetwork("master", parameter, networkName);
		if(configuracioEntity != null){
			Configuration configuracio = getConfigEntityDao().toConfiguration(configuracioEntity);
			return configuracio;
		}
		return null;		
	}

	@Override
	protected String handleFindTenantParameter(String tenant, String parameter) throws Exception {
		if (Security.isMasterTenant() || Security.getCurrentTenantName().equals(tenant))
		{
			ConfigEntity configuracioEntity = getConfigEntityDao().findByTenantNameAndNetwork(tenant, parameter, null);
			if(configuracioEntity != null){
				return configuracioEntity.getValue();
			}
		}
		return null;		
	}
	
}       
