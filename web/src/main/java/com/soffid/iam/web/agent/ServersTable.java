package com.soffid.iam.web.agent;

import org.json.JSONException;
import org.json.JSONObject;
import org.zkoss.util.resource.Labels;

import com.soffid.iam.api.Server;

import es.caib.zkib.component.DataTable;
import es.caib.zkib.datamodel.DataNode;


public class ServersTable extends DataTable {
	protected JSONObject getClientValue(Object element) throws JSONException {
   		DataNode dataNode = (DataNode) element;
		Server server = (Server) dataNode.getInstance();
    	JSONObject o = new JSONObject(server);
    	o.remove("auth");
    	o.remove("pk");
    	if (server.getType() == null)
    		o.put("typeString",  "");
    	else
    		o.put("typeString",  Labels.getLabel("servers.zul."+server.getType().toString()));
    	return o;
	}

}
