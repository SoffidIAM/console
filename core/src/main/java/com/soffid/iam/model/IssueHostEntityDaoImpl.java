package com.soffid.iam.model;

import com.soffid.iam.api.IssueHost;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;

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
		CriteriaSearchConfiguration criteria = new CriteriaSearchConfiguration();
		criteria.setMaximumResultSize(1);
		target.setHost(source.getHostId() != null ? getHostEntityDao().load(source.getHostId()) :
				source.getHostName() != null ? getHostEntityDao().findByName(criteria , source.getHostName()) :
				null);
	}

}
