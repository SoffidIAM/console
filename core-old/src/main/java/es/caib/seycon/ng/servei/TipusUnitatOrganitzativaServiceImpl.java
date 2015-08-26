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

import com.soffid.iam.model.GroupTypeEntity;
import es.caib.seycon.ng.comu.TipusUnitatOrganitzativa;
import es.caib.seycon.ng.exception.SeyconException;
import com.soffid.iam.model.Parameter;
import java.util.Collection;

/**
 * Servei per a accedir a TipusUnitatOrganitzativa Created on 01/06/2009
 * 
 * @author u91940
 * @see es.caib.seycon.ng.servei.TipusUnitatOrganitzativaService
 */
public class TipusUnitatOrganitzativaServiceImpl extends es.caib.seycon.ng.servei.TipusUnitatOrganitzativaServiceBase {

	/**
	 * @see es.caib.seycon.ng.servei.TipusUnitatOrganitzativaService#create(es.caib.seycon.ng.comu.TipusUnitatOrganitzativa)
	 */
	protected es.caib.seycon.ng.comu.TipusUnitatOrganitzativa handleCreate(es.caib.seycon.ng.comu.TipusUnitatOrganitzativa tipus)
			throws java.lang.Exception {
		GroupTypeEntity tipusSameCode = getGroupTypeEntityDao().findByName(tipus.getCodi());
		if(tipusSameCode != null)
			throw new SeyconException(String.format(Messages.getString("TipusUnitatOrganitzativaServiceImpl.CodeTypeExists"),  //$NON-NLS-1$
							tipus.getCodi())); 
		GroupTypeEntity entity = getGroupTypeEntityDao().tipusUnitatOrganitzativaToEntity(tipus);
		getGroupTypeEntityDao().create(entity);
		tipus.setId(entity.getId());
		tipus = getGroupTypeEntityDao().toTipusUnitatOrganitzativa(entity);
		return tipus;
	}

	/**
	 * @see es.caib.seycon.ng.servei.TipusUnitatOrganitzativaService#delete(es.caib.seycon.ng.comu.TipusUnitatOrganitzativa)
	 */
	protected void handleDelete(es.caib.seycon.ng.comu.TipusUnitatOrganitzativa tipus) throws java.lang.Exception {
		GroupTypeEntity entity = getGroupTypeEntityDao().tipusUnitatOrganitzativaToEntity(tipus);
		getGroupTypeEntityDao().remove(entity);
	}

	/**
	 * @see es.caib.seycon.ng.servei.TipusUnitatOrganitzativaService#update(es.caib.seycon.ng.comu.TipusUnitatOrganitzativa)
	 */
	protected es.caib.seycon.ng.comu.TipusUnitatOrganitzativa handleUpdate(es.caib.seycon.ng.comu.TipusUnitatOrganitzativa tipus)
			throws java.lang.Exception {
		GroupTypeEntity entity = getGroupTypeEntityDao().tipusUnitatOrganitzativaToEntity(tipus);
		getGroupTypeEntityDao().update(entity);
		tipus = getGroupTypeEntityDao().toTipusUnitatOrganitzativa(entity);
		return tipus;
	}

	/**
	 * @see es.caib.seycon.ng.servei.TipusUnitatOrganitzativaService#findTipusUnitatOrganitzativaByCodi(java.lang.String)
	 */
	protected TipusUnitatOrganitzativa handleFindTipusUnitatOrganitzativaByCodi(String CodiTipusUnitatOrganitzativa) throws Exception {
		GroupTypeEntity entity = getGroupTypeEntityDao().findByName(CodiTipusUnitatOrganitzativa);
		return getGroupTypeEntityDao().toTipusUnitatOrganitzativa(entity);
	}

	/**
	 * @see es.caib.seycon.ng.servei.TipusUnitatOrganitzativaService#findTipusUnitatOrganitzativaByFiltre(java.lang.String,
	 *      java.lang.String)
	 */
	protected Collection<TipusUnitatOrganitzativa> handleFindTipusUnitatOrganitzativaByFiltre(String codi, String descripcio)
			throws Exception {
		Collection<GroupTypeEntity> entities = getGroupTypeEntityDao().findByFilter(codi, descripcio);
		int limitResults = Integer.parseInt(System.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
		
		// Check maximum number of results
		if (entities.size() > limitResults)
		{
			return getGroupTypeEntityDao().toTipusUnitatOrganitzativaList(entities).subList(0, limitResults);
		}
		
		return getGroupTypeEntityDao().toTipusUnitatOrganitzativaList(entities);
	}
}