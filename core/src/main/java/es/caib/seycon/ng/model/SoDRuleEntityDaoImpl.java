//
// (C) 2013 Soffid
//
//

package es.caib.seycon.ng.model;

import es.caib.seycon.ng.comu.SoDRule;

/**
 * DAO SoDRuleEntity implementation
 */
public class SoDRuleEntityDaoImpl extends SoDRuleEntityDaoBase
{

	@Override
	public void toSoDRule (SoDRuleEntity source, SoDRule target)
	{
		super.toSoDRule(source, target);
		target.setApplicationId(source.getApplication().getId());

	}

	@Override
	public void soDRuleToEntity (SoDRule source, SoDRuleEntity target, boolean copyIfNull)
	{
		super.soDRuleToEntity(source, target, copyIfNull);
		target.setApplication( getAplicacioEntityDao().load(source.getApplicationId()));
	}
	
	
}
