package com.soffid.iam.web.self;

import org.json.JSONException;
import org.json.JSONObject;

import com.soffid.iam.api.User;

import es.caib.zkib.component.DataTable;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datamodel.DataNode;


public class AccountsTable extends DataTable {

	@Override
	protected JSONObject getClientValue(Object element) throws JSONException {
		DataNode sdn = (DataNode)element;
		JSONObject s = super.getClientValue(element);
		DataModelCollection dnc = sdn.getListModel("dispatcherInformation");
		if (dnc.getSize() > 0) {
			DataNode dn = (DataNode) dnc.getDataModel(0);
			s.put("systemDescription",  dn.get("description"));
		}
		if (Boolean.TRUE.equals( sdn.get("disabled")))
			s.put("$class", "dashed");
		else
			s.put("$class", "std");
		return s;

	}

	
}
