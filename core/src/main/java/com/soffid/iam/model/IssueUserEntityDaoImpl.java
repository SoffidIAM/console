package com.soffid.iam.model;

import com.soffid.iam.api.IssueUser;

public class IssueUserEntityDaoImpl extends IssueUserEntityDaoBase {

	@Override
	public IssueUserEntity issueUserToEntity(IssueUser instance) {
		IssueUserEntity eu = newIssueUserEntity();
		issueUserToEntity(instance, eu, true);
		return eu;
	}

	@Override
	public void toIssueUser(IssueUserEntity source, IssueUser target) {
		super.toIssueUser(source, target);
		target.setUserId(source.getUser() == null? null: source.getUser().getId());
	}

	@Override
	public void issueUserToEntity(IssueUser source, IssueUserEntity target, boolean copyIfNull) {
		super.issueUserToEntity(source, target, copyIfNull);
		if (source.getUserId() == null)
			target.setUser(getUserEntityDao().load(source.getUserId()));
		else
			target.setUser(null);
	}

}
