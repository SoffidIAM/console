package com.soffid.iam.web.pam;

import org.json.JSONException;
import org.json.JSONObject;

import com.soffid.iam.api.JumpServerGroup;

import es.caib.zkib.component.DataTable;
import es.caib.zkib.datamodel.DataNode;


public class JumpserverTable extends DataTable {

	@Override
	protected JSONObject getClientValue(Object element) throws JSONException {
   		DataNode dataNode = (DataNode) element;
		JumpServerGroup server = (JumpServerGroup) dataNode.getInstance();
    	JSONObject o = new JSONObject(server);
    	o.remove("password");
    	o.remove("storeUserName");
    	return o;
	}

}
