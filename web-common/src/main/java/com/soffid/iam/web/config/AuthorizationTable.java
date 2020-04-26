package com.soffid.iam.web.config;

import org.json.JSONException;
import org.json.JSONObject;

import com.soffid.iam.api.AuthorizationRole;

import es.caib.zkib.component.DataTable;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datamodel.DataModelNode;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;

public class AuthorizationTable extends DataTable {

	@Override
	protected JSONObject getClientValue(Object element) throws JSONException {
		DataNode n = (DataNode) element;
		JSONObject o = new JSONObject(n.getInstance());
		DataModelCollection coll = n.getListModel("autoritzacioRol");
		StringBuffer sb = new StringBuffer();
		for ( int i = 0; i < coll.getSize(); i++)
		{
			DataNode child = (DataNode) coll.getDataModel(i);
			if (child != null) {
				AuthorizationRole au = (AuthorizationRole) child.getInstance();
				if ( sb.length() > 0) sb.append("; ");
				sb.append( au.getRole().getName()+"@"+au.getRole().getSystem() );
			}
		}
		o.put("roles",  sb.toString());
		return o;
	}

}
