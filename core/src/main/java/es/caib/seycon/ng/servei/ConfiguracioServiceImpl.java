// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

import com.soffid.iam.doc.exception.NASException;
import com.soffid.iam.doc.nas.NASManager;
import com.soffid.iam.model.BlobConfigurationEntity;
import com.soffid.iam.model.BlobConfigurationEntityDao;
import com.soffid.iam.model.ConfigEntity;
import es.caib.seycon.ng.comu.Configuracio;
import es.caib.seycon.ng.config.Config;
import es.caib.seycon.ng.model.Parameter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @see es.caib.seycon.ng.servei.ConfiguracioService
 */
public class ConfiguracioServiceImpl
    extends es.caib.seycon.ng.servei.ConfiguracioServiceBase
{

	protected Collection<Configuracio> handleGetParametres() throws Exception {
		List<ConfigEntity> col = this.getConfigEntityDao().loadAll();
		return getConfigEntityDao().toConfiguracioList(col);
	}

	/**
	 * Method that implements the functionality to reconfigure NAS.
	 * @param configuracio
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws NASException
	 */
	private void reconfigureNAS (Configuracio configuracio)
		throws InstantiationException, IllegalAccessException,
		ClassNotFoundException, NASException
	{
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
			log.warn(String.format("Document manager: parameter not defined: %1$s", //$NON-NLS-1$
				configuracio.getCodi()));
		}
	}
	
	/**
	 * Method that implements the functionality to check if all NAS configuration
	 * parameters are defined.
	 * @param configuracio
	 * @return <code>TRUE</code> if all configuration parameters are set.
	 * <code>FALSE</code> otherwise.
	 */
	private boolean checkAllConfigurationSet (Configuracio configuracio)
	{
		boolean configSet = false;
		String docStrategy = System.getProperty("soffid.ui.docStrategy"); //$NON-NLS-1$
		String tempPath = System.getProperty("soffid.ui.docTempPath"); //$NON-NLS-1$
		
		if ((docStrategy != null) && (tempPath != null))
		{
			if (docStrategy
					.equals("es.caib.bpm.nas.comm.LocalFileSystemStrategy") && //$NON-NLS-1$
				configuracio.getCodi().equals("soffid.ui.docPath")) //$NON-NLS-1$
			{
				return true;
			}
			
			if ((docStrategy.equals("es.caib.bpm.nas.comm.FTPStrategy") || //$NON-NLS-1$
				docStrategy.equals("es.caib.bpm.nas.comm.CIFSStrategy")) && //$NON-NLS-1$
				(System.getProperty("soffid.ui.docUsername") != null) &&  //$NON-NLS-1$
				(System.getProperty("soffid.ui.docUserPassword") != null) && //$NON-NLS-1$
				(System.getProperty("soffid.ui.docServer") != null)) //$NON-NLS-1$
			{
				return true;
			}
			
			if (docStrategy.equals("es.caib.bpm.nas.comm.HTTPStrategy") && //$NON-NLS-1$
				(System.getProperty("soffid.ui.docServer") != null)) //$NON-NLS-1$
			{
				return true;
			}
		}

		return configSet;
	}

	protected Configuracio handleCreate(Configuracio configuracio)
		throws Exception
	{
		ConfigEntity configuracioEntity = getConfigEntityDao().configuracioToEntity(configuracio);
		
		// Check configuration parameter
		System.setProperty(configuracio.getCodi(), configuracio.getValor()); //$NON-NLS-1$
		
		reconfigureNAS(configuracio);
		
		getConfigEntityDao().create(configuracioEntity);
		configuracio.setId(new Long(configuracioEntity.getId().longValue()));
		
		if (configuracio.getCodiXarxa() == null)
		{
			System.setProperty(configuracio.getCodi(), configuracio.getValor());
		}

		return getConfigEntityDao().toConfiguracio(configuracioEntity);
	}

	protected void handleDelete(Configuracio configuracio) throws Exception {
		ConfigEntity configuracioEntity = getConfigEntityDao().configuracioToEntity(configuracio);
		boolean toRemove = configuracioEntity.getNetwork() == null;
		String codi = configuracioEntity.getCode();
		getConfigEntityDao().remove(configuracioEntity);
		if (toRemove) {
			System.getProperties().remove(codi);
		}
	}

	protected Configuracio handleUpdate(Configuracio configuracio)
		throws Exception
	{
		ConfigEntity configuracioEntity = getConfigEntityDao().configuracioToEntity(configuracio);
		
		System.setProperty(configuracio.getCodi(), configuracio.getValor()); //$NON-NLS-1$
		reconfigureNAS(configuracio);
		
		getConfigEntityDao().update(configuracioEntity);
		configuracio = getConfigEntityDao().toConfiguracio(configuracioEntity);
		
		if (configuracio.getCodiXarxa() == null)
		{
			System.setProperty(configuracio.getCodi(), configuracio.getValor());
		}
		return configuracio;
	}
	
	private Collection<ConfigEntity> localFindConfiguracioByFiltre(String codi, String valor, String descripcio, String codiXarxa) {
		String query = 
			"select configuracio " + //$NON-NLS-1$
				"from " + //$NON-NLS-1$
				"es.caib.seycon.ng.model.ConfiguracioEntity configuracio " + //$NON-NLS-1$
				"left join configuracio.xarxa xarxa "+ //$NON-NLS-1$
				"where " + //$NON-NLS-1$
				"(:codi is null or configuracio.codi like :codi) and " + //$NON-NLS-1$
				"(:codiXarxa is null or xarxa.codi like :codiXarxa) and " + //$NON-NLS-1$
				"(:valor is null or configuracio.valor like :valor) and " + //$NON-NLS-1$
				"(:descripcio is null or configuracio.descripcio like :descripcio)"; //$NON-NLS-1$
		Parameter codiParameter = new Parameter("codi", codi); //$NON-NLS-1$
		Parameter codiXarxaParameter = new Parameter("codiXarxa", codiXarxa); //$NON-NLS-1$
		Parameter valorParameter = new Parameter("valor", valor); //$NON-NLS-1$
		Parameter descripcioParameter = new Parameter("descripcio", descripcio); //$NON-NLS-1$
		Parameter[] parameters = {codiParameter, codiXarxaParameter, valorParameter, descripcioParameter};
		return getConfigEntityDao().query(query, parameters);		
	}

	protected Collection<Configuracio> handleFindConfiguracioByFiltre(
		String codi, String valor, String descripcio, String codiXarxa)
		throws Exception
	{
		int limitResults = 0;	// Limit of rows to obtain
		
		// Check limit defined
		if (System.getProperty("soffid.ui.maxrows") != null) //$NON-NLS-1$
		{
			limitResults = Integer.parseInt(System.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
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
				return getConfigEntityDao().toConfiguracioList(configuracions).subList(0, limitResults);
			}
			
			return getConfigEntityDao().toConfiguracioList(configuracions);
		}
		
		return new Vector();
	}

	protected Configuracio handleFindParametreByCodiAndCodiXarxa(
			String codiParametre, String codiXarxa) throws Exception {
		ConfigEntity configuracioEntity = getConfigEntityDao().findByCodeAndNetworkCode(codiParametre, codiXarxa);
		if(configuracioEntity != null){
			Configuracio configuracio = getConfigEntityDao().toConfiguracio(configuracioEntity);
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
	
}       
