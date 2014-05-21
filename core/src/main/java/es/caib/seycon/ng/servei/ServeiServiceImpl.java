// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import es.caib.seycon.ng.comu.Servei;
import es.caib.seycon.ng.comu.ServeiSearchCriteria;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.Parameter;
import es.caib.seycon.ng.model.ServeiEntity;
import es.caib.seycon.ng.model.ServeiEntityDao;

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
		ServeiEntity servicesSameCode = getServeiEntityDao().findByCodi(servei.getCodi());
		if(servicesSameCode != null)
			throw new SeyconException(String.format(Messages.getString("ServeiServiceImpl.CodeServiceExists"),  //$NON-NLS-1$
							servei.getCodi())); 
		ServeiEntity serveiEntity = getServeiEntityDao().serveiToEntity(servei);
		getServeiEntityDao().create(serveiEntity);
		servei.setId(serveiEntity.getId());
		return getServeiEntityDao().toServei(serveiEntity);
	}

	/**
	 * @see es.caib.seycon.ng.servei.ServeiService#delete(es.caib.seycon.ng.comu.Servei)
	 */
	protected void handleDelete(es.caib.seycon.ng.comu.Servei servei)
			throws java.lang.Exception {
		ServeiEntity serveiEntity = getServeiEntityDao().findByCodi(
				servei.getCodi());
		if(serveiEntity != null){
			getServeiEntityDao().remove(serveiEntity);
		}
	}

	/**
	 * @see es.caib.seycon.ng.servei.ServeiService#update(es.caib.seycon.ng.comu.Servei)
	 */
	protected es.caib.seycon.ng.comu.Servei handleUpdate(
			es.caib.seycon.ng.comu.Servei servei) throws java.lang.Exception {
		ServeiEntity entity = getServeiEntityDao().serveiToEntity(servei);
		getServeiEntityDao().update(entity);
		return getServeiEntityDao().toServei(entity);
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
		
		Collection<ServeiEntity> serveis = getServeiEntityDao().findByCriteri(criteri);
		if(serveis != null)
		{
			// Check maximum number of results
			if (serveis.size() > limitResults)
			{
				return getServeiEntityDao().toServeiList(serveis)
					.subList(0, limitResults);
			}
			
			return getServeiEntityDao().toServeiList(serveis);
		}
		
		return new Vector();
	}

	protected Servei handleFindServeiByCodi(String codi) throws Exception {
		ServeiEntity serveiEntity = getServeiEntityDao().findByCodi(codi);
		if(serveiEntity != null){
			Servei servei = getServeiEntityDao().toServei(serveiEntity);
			return servei;
		}
		return null;
	}

	protected Collection<Servei> handleGetServeis() throws Exception {
		List<ServeiEntity> col = getServeiEntityDao().loadAll();
		return getServeiEntityDao().toServeiList(col);
	}
}