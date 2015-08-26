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

import com.soffid.iam.model.ServiceEntity;
import es.caib.seycon.ng.comu.Servei;
import es.caib.seycon.ng.comu.ServeiSearchCriteria;
import es.caib.seycon.ng.exception.SeyconException;
import com.soffid.iam.model.Parameter;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

/**
 * @see es.caib.seycon.ng.servei.ServeiService
 */
public class ServeiServiceImpl extends
		es.caib.seycon.ng.servei.ServeiServiceBase {

	/**
	 * @see es.caib.seycon.ng.servei.ServeiService#create(es.caib.seycon.ng.comu.Servei)
	 */
	protected es.caib.seycon.ng.comu.Servei handleCreate(
			es.caib.seycon.ng.comu.Servei servei) throws java.lang.Exception {
		ServiceEntity servicesSameCode = getServiceEntityDao().findByName(servei.getCodi());
		if(servicesSameCode != null)
			throw new SeyconException(String.format(Messages.getString("ServeiServiceImpl.CodeServiceExists"),  //$NON-NLS-1$
							servei.getCodi())); 
		ServiceEntity serveiEntity = getServiceEntityDao().serveiToEntity(servei);
		getServiceEntityDao().create(serveiEntity);
		servei.setId(serveiEntity.getId());
		return getServiceEntityDao().toServei(serveiEntity);
	}

	/**
	 * @see es.caib.seycon.ng.servei.ServeiService#delete(es.caib.seycon.ng.comu.Servei)
	 */
	protected void handleDelete(es.caib.seycon.ng.comu.Servei servei)
			throws java.lang.Exception {
		ServiceEntity serveiEntity = getServiceEntityDao().findByName(servei.getCodi());
		if(serveiEntity != null){
			getServiceEntityDao().remove(serveiEntity);
		}
	}

	/**
	 * @see es.caib.seycon.ng.servei.ServeiService#update(es.caib.seycon.ng.comu.Servei)
	 */
	protected es.caib.seycon.ng.comu.Servei handleUpdate(
			es.caib.seycon.ng.comu.Servei servei) throws java.lang.Exception {
		ServiceEntity entity = getServiceEntityDao().serveiToEntity(servei);
		getServiceEntityDao().update(entity);
		return getServiceEntityDao().toServei(entity);
	}

	protected Collection<Servei> handleFindServeisByCriteri(String codi,
		String descripcio) throws Exception
	{
		ServeiSearchCriteria criteri = new ServeiSearchCriteria();
		int limitResults = Integer.parseInt(System.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
		
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
				return getServiceEntityDao().toServeiList(serveis).subList(0, limitResults);
			}
			
			return getServiceEntityDao().toServeiList(serveis);
		}
		
		return new Vector();
	}

	protected Servei handleFindServeiByCodi(String codi) throws Exception {
		ServiceEntity serveiEntity = getServiceEntityDao().findByName(codi);
		if(serveiEntity != null){
			Servei servei = getServiceEntityDao().toServei(serveiEntity);
			return servei;
		}
		return null;
	}

	protected Collection<Servei> handleGetServeis() throws Exception {
		List<ServiceEntity> col = getServiceEntityDao().loadAll();
		return getServiceEntityDao().toServeiList(col);
	}
}