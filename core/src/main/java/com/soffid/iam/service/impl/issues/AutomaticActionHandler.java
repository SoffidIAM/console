package com.soffid.iam.service.impl.issues;

import com.soffid.iam.api.Issue;
import com.soffid.iam.api.IssueActionDefinition;
import com.soffid.iam.model.IssueEntity;
import com.soffid.iam.model.IssuePolicyActionEntity;

import es.caib.seycon.ng.exception.InternalErrorException;

public interface AutomaticActionHandler {

	void process(Issue event, IssueEntity entity, IssuePolicyActionEntity actionEntity) throws InternalErrorException;
}
