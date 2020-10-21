package com.soffid.iam.web.bpm;

import org.json.JSONException;
import org.json.JSONObject;

import com.soffid.iam.api.User;
import com.soffid.iam.bpm.api.Job;
import com.soffid.iam.web.component.DynamicColumnsDatatable;

import es.caib.zkib.datamodel.DataNode;


public class JobDatatable extends DynamicColumnsDatatable {

	@Override
	protected JSONObject getClientValue(Object element) throws JSONException {
		JSONObject o = super.getClientValue(element);
		Job job = (Job) ((DataNode)element).getInstance();

		if (job.getFailures() > 0 &&
				job.getErrorMessage() != null &&
				! job.getErrorMessage().trim().isEmpty()) {
			o.put("$class", "red");
		} else if (job.getDueDate() != null
				&& job.getDueDate().getTime() < System
						.currentTimeMillis()) {
			o.put("$class", "bold");
		} else
			o.put("$class", "std");
		return o;
	}

}
