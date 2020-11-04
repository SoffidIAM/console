package com.soffid.iam.web.audit;

import org.json.JSONException;
import org.json.JSONObject;

import com.soffid.iam.web.component.DynamicColumnsDatatable;


public class SessionDatatable extends DynamicColumnsDatatable {

	@Override
	protected JSONObject getClientValue(Object element) throws JSONException {
		JSONObject o = super.getClientValue(element);
		o.remove("monitorUrl");
		return o;
	}

}
