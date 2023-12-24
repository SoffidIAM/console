package com.soffid.iam.web.application;

import org.json.JSONException;
import org.json.JSONObject;

import com.soffid.iam.api.RoleDependencyStatus;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.User;
import com.soffid.iam.web.component.DynamicColumnsDatatable;

import es.caib.zkib.component.DataTable;
import es.caib.zkib.datamodel.DataNode;


public class GrantDatatable extends DynamicColumnsDatatable {
	@Override
	protected JSONObject getClientValue(Object element) throws JSONException {
		JSONObject s = super.getClientValue(element);
		RoleGrant u = (RoleGrant) element;
		if (u.getStatus() == RoleDependencyStatus.STATUS_ACTIVE)
			s.put("$class", "std");
		else if (u.getStatus() == RoleDependencyStatus.STATUS_TOAPPROVE)
			s.put("$class", "approval-pending");
		else if (u.getStatus() == RoleDependencyStatus.STATUS_TOREMOVE)
			s.put("$class", "dashed");
		else
			s.put("$class", "approval-pending");
		return s;
	}

}
