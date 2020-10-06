package com.soffid.iam.web.mail;

import org.json.JSONException;
import org.json.JSONObject;

import com.soffid.iam.api.MailDomain;

import es.caib.zkib.component.DataTable;
import es.caib.zkib.datamodel.DataNode;


public class MailDomainDatatable extends DataTable {
	public MailDomainDatatable() throws Exception {
	}
	
	@Override
	protected JSONObject getClientValue(Object element) throws JSONException {
		JSONObject row = super.getClientValue(element);
		MailDomain a = (MailDomain) ((DataNode)element).getInstance();
		if (Boolean.TRUE.equals(a.getObsolete()))
			row.put("$class", "dashed");
		else
			row.put("$class", "std");
		return row;
	}

}
