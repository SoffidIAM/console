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

import com.soffid.iam.api.OUType;
import com.soffid.iam.model.GroupTypeEntity;
import com.soffid.iam.model.Parameter;
import es.caib.seycon.ng.exception.SeyconException;
import java.util.Collection;

/**
 * Servei per a accedir a TipusUnitatOrganitzativa Created on 01/06/2009
 * 
 * @author u91940
 * @see es.caib.seycon.ng.servei.TipusUnitatOrganitzativaService
 */
public class OrganizationalUnitTypeServiceImpl extends com.soffid.iam.service.OrganizationalUnitTypeServiceBase {

	/**
	 * @see es.caib.seycon.ng.servei.TipusUnitatOrganitzativaService#create(es.caib.seycon.ng.comu.TipusUnitatOrganitzativa)
	 */
	protected com.soffid.iam.api.OUType handleCreate(com.soffid.iam.api.OUType tipus) throws java.lang.Exception {
		GroupTypeEntity tipusSameCode = getGroupTypeEntityDao().findByName(tipus.getCode());
		if(tipusSameCode != null)
			throw new SeyconException(String.format(Messages.getString("OrganizationalUnitTypeServiceImpl.CodeTypeExists"), tipus.getCode())); 
		GroupTypeEntity entity = getGroupTypeEntityDao().oUTypeToEntity(tipus);
		getGroupTypeEntityDao().create(entity);
		tipus.setId(entity.getId());
		tipus = getGroupTypeEntityDao().toOUType(entity);
		return tipus;
	}

	/**
	 * @see es.caib.seycon.ng.servei.TipusUnitatOrganitzativaService#delete(es.caib.seycon.ng.comu.TipusUnitatOrganitzativa)
	 */
	protected void handleDelete(com.soffid.iam.api.OUType tipus) throws java.lang.Exception {
		GroupTypeEntity entity = getGroupTypeEntityDao().oUTypeToEntity(tipus);
		getGroupTypeEntityDao().remove(entity);
	}

	/**
	 * @see es.caib.seycon.ng.servei.TipusUnitatOrganitzativaService#update(es.caib.seycon.ng.comu.TipusUnitatOrganitzativa)
	 */
	protected com.soffid.iam.api.OUType handleUpdate(com.soffid.iam.api.OUType tipus) throws java.lang.Exception {
		GroupTypeEntity entity = getGroupTypeEntityDao().oUTypeToEntity(tipus);
		getGroupTypeEntityDao().update(entity);
		tipus = getGroupTypeEntityDao().toOUType(entity);
		return tipus;
	}

	/**
	 * @see es.caib.seycon.ng.servei.TipusUnitatOrganitzativaService#findTipusUnitatOrganitzativaByCodi(java.lang.String)
	 */
	protected OUType handleFindOUTypeByName(String CodiTipusUnitatOrganitzativa) throws Exception {
		GroupTypeEntity entity = getGroupTypeEntityDao().findByName(CodiTipusUnitatOrganitzativa);
		return getGroupTypeEntityDao().toOUType(entity);
	}

	/**
	 * @see es.caib.seycon.ng.servei.TipusUnitatOrganitzativaService#findTipusUnitatOrganitzativaByFiltre(java.lang.String,
	 *      java.lang.String)
	 */
	protected Collection<OUType> handleFindOUTypeByFilter(String codi, String descripcio) throws Exception {
		Collection<GroupTypeEntity> entities = getGroupTypeEntityDao().findByFilter(codi, descripcio);
		int limitResults = Integer.parseInt(System.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
		
		// Check maximum number of results
		if (entities.size() > limitResults)
		{
			return getGroupTypeEntityDao().toOUTypeList(entities).subList(0, limitResults);
		}
		
		return getGroupTypeEntityDao().toOUTypeList(entities);
	}
}