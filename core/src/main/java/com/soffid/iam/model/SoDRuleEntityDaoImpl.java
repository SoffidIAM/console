//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

import com.soffid.iam.api.SoDRule;
import com.soffid.iam.api.SodRuleType;

/**
 * DAO SoDRuleEntity implementation
 */
public class SoDRuleEntityDaoImpl extends com.soffid.iam.model.SoDRuleEntityDaoBase
{

	@Override
    public void toSoDRule(com.soffid.iam.model.SoDRuleEntity source, SoDRule target) {
		super.toSoDRule(source, target);
		target.setApplication(source.getApplication().getName());
		if ( source.getType() == null) {
			target.setType(source.getNumber() == null ? SodRuleType.MATCH_ALL : SodRuleType.MATCH_SOME);
		}
	}

	@Override
    public void soDRuleToEntity(SoDRule source, com.soffid.iam.model.SoDRuleEntity target, boolean copyIfNull) {
		super.soDRuleToEntity(source, target, copyIfNull);
		target.setApplication(getInformationSystemEntityDao().findByCode(source.getApplication()));
	}
	
	
}
