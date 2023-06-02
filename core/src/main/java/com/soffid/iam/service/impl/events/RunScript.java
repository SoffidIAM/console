package com.soffid.iam.service.impl.events;

import java.util.HashMap;
import java.util.Map;

import com.soffid.iam.api.Issue;
import com.soffid.iam.interp.Evaluator;
import com.soffid.iam.model.IssueEntity;
import com.soffid.iam.model.IssuePolicyActionEntity;
import com.soffid.iam.service.impl.issues.AutomaticActionHandler;

import es.caib.seycon.ng.exception.InternalErrorException;

public class RunScript implements AutomaticActionHandler {

	@Override
	public void process(Issue event, IssueEntity entity, IssuePolicyActionEntity actionEntity) throws InternalErrorException {
		try {
			Evaluator.instance().evaluate(actionEntity.getScript(), getVars(event), "Action "+actionEntity.getDescription());
		} catch (InternalErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalErrorException("Error evaluating action "+actionEntity.getDescription(), e);
		}
	}

	private Map<String, Object> getVars(Issue event) {
		HashMap m = new HashMap<>();
		m.put("event", event);
		return m;
	}
}
