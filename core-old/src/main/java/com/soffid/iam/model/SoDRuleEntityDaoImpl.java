//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

import es.caib.seycon.ng.comu.SoDRule;

/**
 * DAO SoDRuleEntity implementation
 */
public class SoDRuleEntityDaoImpl extends com.soffid.iam.model.SoDRuleEntityDaoBase
{

	@Override
    public void toSoDRule(com.soffid.iam.model.SoDRuleEntity source, SoDRule target) {
		super.toSoDRule(source, target);
		target.setApplicationId(source.getApplication().getId());

	}

	@Override
    public void soDRuleToEntity(SoDRule source, com.soffid.iam.model.SoDRuleEntity target, boolean copyIfNull) {
		super.soDRuleToEntity(source, target, copyIfNull);
		target.setApplication(getInformationSystemEntityDao().load(source.getApplicationId()));
	}
	
	
}
