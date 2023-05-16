package com.soffid.iam.service.impl.issues;

import com.soffid.iam.api.IssueActionDefinition;
import com.soffid.iam.model.IssueEntity;
import com.soffid.iam.model.IssuePolicyActionEntity;

public interface AutomaticActionHandler {

	void process(IssueEntity event, IssuePolicyActionEntity actionEntity);
}
