package com.soffid.iam.model;

import com.soffid.iam.api.IssueHost;

public class IssueHostEntityDaoImpl extends IssueHostEntityDaoBase {

	@Override
	public IssueHostEntity issueHostToEntity(IssueHost instance) {
		IssueHostEntity eh = newIssueHostEntity();
		issueHostToEntity(instance, eh, true);
		return eh;
	}

	@Override
	public void toIssueHost(IssueHostEntity source, IssueHost target) {
		super.toIssueHost(source, target);
		target.setHostId(source.getHost() == null? null: source.getHost().getId());
	}

	@Override
	public void issueHostToEntity(IssueHost source, IssueHostEntity target, boolean copyIfNull) {
		super.issueHostToEntity(source, target, copyIfNull);
		target.setHost(source.getHostId() == null ? null: getHostEntityDao().load(source.getHostId()));
	}

}
