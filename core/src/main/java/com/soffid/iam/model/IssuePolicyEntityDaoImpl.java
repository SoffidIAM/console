package com.soffid.iam.model;

import java.util.LinkedList;

import com.soffid.iam.api.IssuePolicy;

public class IssuePolicyEntityDaoImpl extends IssuePolicyEntityDaoBase {

	@Override
	public void toIssuePolicy(IssuePolicyEntity source, IssuePolicy target) {
		super.toIssuePolicy(source, target);
		target.setActions(getIssuePolicyActionEntityDao().toIssuePolicyActionList(source.getActions()));
	}

	@Override
	public void issuePolicyToEntity(IssuePolicy source, IssuePolicyEntity target, boolean copyIfNull) {
		super.issuePolicyToEntity(source, target, copyIfNull);
	}

}
