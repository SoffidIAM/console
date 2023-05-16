package com.soffid.iam.model;

import com.soffid.iam.api.Issue;

public class IssueEntityDaoImpl extends IssueEntityDaoBase {

	@Override
	public void issueToEntity(Issue source, IssueEntity target, boolean copyIfNull) {
		super.issueToEntity(source, target, copyIfNull);
		target.setSystem(source.getSystem() == null ? null: getSystemEntityDao().findByName(source.getSystem()));
		target.setRoleAccount(source.getRoleAccount() == null ? null: getRoleAccountEntityDao().load(source.getRoleAccount().getId()));
		target.setRule(source.getRule() == null ? null: getPamRuleEntityDao().load(source.getRule().getId()));
		target.setAccount(source.getAccount() == null? null: getAccountEntityDao().load(source.getAccount().getId()));
	}

	@Override
	public void toIssue(IssueEntity source, Issue target) {
		super.toIssue(source, target);
		target.setSystem( source.getSystem() == null ? null: source.getSystem().getName());
		target.setRoleAccount(source.getRoleAccount() == null? null: getRoleAccountEntityDao().toRoleAccount(source.getRoleAccount()));
		target.setRule(source.getRule() == null ? null: getPamRuleEntityDao().toPamRule(source.getRule()));
		target.setAccount(source.getAccount() == null ? null: getAccountEntityDao().toAccount(source.getAccount()));
	}

}
