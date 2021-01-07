package com.soffid.iam.model;

import com.soffid.iam.api.SoDRuleMatrix;

public class SoDRuleMatrixEntityDaoImpl extends SoDRuleMatrixEntityDaoBase {

	@Override
	public void toSoDRuleMatrix(SoDRuleMatrixEntity source, SoDRuleMatrix target) {
		super.toSoDRuleMatrix(source, target);
		target.setRuleId(source.getRule().getId());
		target.setRow(source.getRow().getId());
		target.setColumn(source.getColumn().getId());
	}

	@Override
	public void soDRuleMatrixToEntity(SoDRuleMatrix source, SoDRuleMatrixEntity target, boolean copyIfNull) {
		super.soDRuleMatrixToEntity(source, target, copyIfNull);
		target.setRule(getSoDRuleEntityDao().load(source.getRuleId()));
		target.setRow(getSoDRoleEntityDao().load(source.getRow()));
		target.setColumn(getSoDRoleEntityDao().load(source.getColumn()));
	}

}
