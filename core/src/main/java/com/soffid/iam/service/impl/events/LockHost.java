package com.soffid.iam.service.impl.events;

import java.util.HashMap;
import java.util.Map;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.AccountStatus;
import com.soffid.iam.api.Host;
import com.soffid.iam.api.Issue;
import com.soffid.iam.interp.Evaluator;
import com.soffid.iam.model.IssueEntity;
import com.soffid.iam.model.IssueHostEntity;
import com.soffid.iam.model.IssuePolicyActionEntity;
import com.soffid.iam.service.NetworkService;
import com.soffid.iam.service.impl.issues.AutomaticActionHandler;

import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;

public class LockHost implements AutomaticActionHandler {

	@Override
	public void process(Issue event, IssueEntity entity, IssuePolicyActionEntity actionEntity) throws InternalErrorException {
		NetworkService svc = ServiceLocator.instance().getNetworkService();
		for (IssueHostEntity issueHostEntity: entity.getHosts()) {
			if (issueHostEntity.getHost() != null)
			{
				Host h = svc.findHostById(issueHostEntity.getHost().getId());
				if (h != null) {
					h.setLocked(true);
					svc.update(h);
				}
			}
		}
	}
}
