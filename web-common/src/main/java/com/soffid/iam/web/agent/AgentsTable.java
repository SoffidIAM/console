package com.soffid.iam.web.agent;

import org.json.JSONException;
import org.json.JSONObject;

import com.soffid.iam.api.System;

import es.caib.zkib.component.DataTable;
import es.caib.zkib.datamodel.DataNode;

public class AgentsTable extends DataTable {

	@Override
	protected JSONObject getClientValue(Object element) throws JSONException {
		System s = (System) ((DataNode)element).getInstance();
		JSONObject o = new JSONObject();
		o.put("name", s.getName());
		o.put("description", s.getDescription());
		o.put("className", s.getClass() == null ? JSONObject.NULL : s.getClassName());
		o.put("url", s.getUrl() == null ? JSONObject.NULL: s.getUrl());
		return o;
	}

}
