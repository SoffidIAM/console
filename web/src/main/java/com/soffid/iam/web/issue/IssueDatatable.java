package com.soffid.iam.web.issue;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.JSONException;
import org.json.JSONObject;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.Issue;
import com.soffid.iam.api.IssueHost;
import com.soffid.iam.api.IssueStatus;
import com.soffid.iam.api.IssueUser;
import com.soffid.iam.api.System;
import com.soffid.iam.api.User;
import com.soffid.iam.web.component.DatatypeColumnsDatatable;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.zkib.datamodel.DataNode;


public class IssueDatatable extends DatatypeColumnsDatatable {
	HashMap<String, System> systems = new HashMap<>();

	static String[] defaultColumns = {
			"type", "description", "created", "status"
	};
	
	public IssueDatatable() throws Exception {
	}
	
	public Collection<DataType> getDataTypes() throws Exception {
		HashSet<String> names = new HashSet<>();
		LinkedList<DataType> l = new LinkedList<DataType>( 
				EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(Issue.class.getName(), null));
		for (Iterator<DataType> it = l.iterator(); it.hasNext();) {
			DataType dt = it.next();
			names.add(dt.getName());
			if (dt.getType() == TypeEnumeration.SEPARATOR)
				it.remove();
		}
		return l;
	}

	@Override
	public String[] getDefaultColumns() throws Exception {
		return defaultColumns;
	}

	@Override
	protected JSONObject getClientValue(Object element) throws JSONException {
		JSONObject s = super.getClientValue(element);
		Issue a = (Issue) ((DataNode)element).getInstance();
		if (a.getStatus() == IssueStatus.NEW)
			s.put("$class", "bold");
		else if (a.getStatus() == IssueStatus.SOLVED)
			s.put("$class", "grayed");
		
		StringBuffer sb = new StringBuffer();
		if (a.getUsers() != null)
			for (IssueUser user: a.getUsers()) {
				if (sb.length() > 0) sb.append(" ");
				sb.append(user.getUserName());
			}
		s.put("hosts", sb.toString());

		sb = new StringBuffer();
		if (a.getHosts() != null)
			for (IssueHost host: a.getHosts()) {
				if (sb.length() > 0) sb.append(" ");
				sb.append(host.getHostName());
			}
		s.put("hosts", sb.toString());
		return s;
	}

}
