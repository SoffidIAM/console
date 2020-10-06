package com.soffid.iam.web.user;

import org.json.JSONException;
import org.json.JSONObject;

import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.web.component.DynamicColumnsDatatable;

import es.caib.zkib.datamodel.DataNode;

public class RoleAccountDatatable extends DynamicColumnsDatatable {

	@Override
	protected JSONObject getClientValue(Object element) throws JSONException {
		JSONObject o = super.getClientValue(element);
		RoleAccount ra = (RoleAccount) ((DataNode)element).getInstance();
		o.put("$class", "normal");
		
		if (ra.isApprovalPending())
			o.put("$class", "approval-pending");
		if (! ra.isEnabled())
			o.put("$class", "dashed");
		if (ra.getRuleId() != null)
			o.put("$class", "rule");
		return o;
	}

}
