package com.soffid.iam.service.impl.issues;

import java.util.Map;

import com.soffid.iam.model.IssueEntity;

public interface ManualActionHandler {

	void process(IssueEntity entity, String action, Map<String, Object> parameters);

}
