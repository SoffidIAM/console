package com.soffid.iam.web.agent;

import org.json.JSONException;
import org.json.JSONObject;
import org.zkoss.util.resource.Labels;

import com.soffid.iam.api.DataType;
import com.soffid.iam.api.System;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.datamodel.DataNode;

public class MetadataTable extends DataTable {

	@Override
	protected JSONObject getClientValue(Object element) throws JSONException {
		JSONObject o = super.getClientValue(element);

		DataNode dataNode = (DataNode)element;
		DataType dt = (DataType) dataNode.getInstance();
		TypeEnumeration type = dt.getType();
	 	String s = type == null ? "":
	 		Labels.getLabel("typeDadaAddicional."+dt.getType().getValue());
		o.put("typeDescription", s);
		
		return o;
	}

}
