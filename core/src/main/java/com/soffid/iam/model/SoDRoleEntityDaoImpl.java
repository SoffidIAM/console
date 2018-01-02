//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

import com.soffid.iam.api.SoDRole;

/**
 * DAO SoDRoleEntity implementation
 */
public class SoDRoleEntityDaoImpl extends com.soffid.iam.model.SoDRoleEntityDaoBase
{

	@Override
    public void toSoDRole(com.soffid.iam.model.SoDRoleEntity source, SoDRole target) {
		super.toSoDRole(source, target);
		target.setRole(getRoleEntityDao().toRole(source.getRole()));
		target.setRuleId(source.getRule().getId());
	}

	@Override
    public void soDRoleToEntity(SoDRole source, com.soffid.iam.model.SoDRoleEntity target, boolean copyIfNull) {
		super.soDRoleToEntity(source, target, copyIfNull);
		target.setRole(getRoleEntityDao().load(source.getRole().getId()));
		target.setRule(getSoDRuleEntityDao().load(source.getRuleId()));
	}
}
