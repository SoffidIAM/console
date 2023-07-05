package com.soffid.iam.model;

import com.soffid.iam.api.IssuePolicyAction;
import com.soffid.iam.api.IssueStatus;

public class IssuePolicyActionEntityDaoImpl extends IssuePolicyActionEntityDaoBase {

	@Override
	public void toIssuePolicyAction(IssuePolicyActionEntity source, IssuePolicyAction target) {
		super.toIssuePolicyAction(source, target);
		if (target.getStatus() == null)
			target.setStatus(IssueStatus.NEW);
	}

	@Override
	public void issuePolicyActionToEntity(IssuePolicyAction source, IssuePolicyActionEntity target,
			boolean copyIfNull) {
		super.issuePolicyActionToEntity(source, target, copyIfNull);
	}

}
