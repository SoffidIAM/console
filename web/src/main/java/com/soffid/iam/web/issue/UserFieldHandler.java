package com.soffid.iam.web.issue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;

import com.soffid.iam.api.DataType;
import com.soffid.iam.api.EventUserAction;
import com.soffid.iam.api.IssueUser;
import com.soffid.iam.web.WebDataType;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldUIHandler;
import com.soffid.iam.web.datarender.DefaultRenderer;

import es.caib.zkib.component.DataTable;
import es.caib.zkib.datasource.XPathUtils;


public class UserFieldHandler extends InputFieldUIHandler {
	@Override
	public void afterCreate(InputField3 field) throws Exception {
		field.setVisible(false);
	}

	@Override
	public void beforeCreate(InputField3 field) throws Exception {
		field.setDataHandler(new IssueUserDataHandler(field.getDataType()));
		Div d = new Div();
		d.setId("_usersTable");
		d.setSclass("databox");
		
		Div d0 = new Div();
		d.appendChild(d0);
		d0.setSclass("label");
		
		Label l = new Label(Labels.getLabel("com.soffid.iam.api.Issue.users")+" :");
		d0.appendChild(l);
		
		Div d2 = new Div();
		d.appendChild(d2);
		d2.setSclass("container");
		DataTable table = new DataTable();
		d2.appendChild(table);
		
		
		table.setDataPath("/users");
		JSONArray a = new JSONArray();
		a.put( new JSONObject("{\"name\":\""+Labels.getLabel("com.soffid.iam.api.User.userName")+"\","
				+ "\"value\":\"userName\"}"));
		
		a.put(new JSONObject("{\"name\":\""+Labels.getLabel("merge.externalId")+"\","
				+ "\"value\":\"externalId\"}"));
		
		JSONObject o = new JSONObject();
		o.put("name", Labels.getLabel("auditoria.zul.Accia-2"));

		WebDataType dt = new WebDataType();
		dt.setBuiltin(true);
		dt.setNlsLabel("auditoria.zul.Accia-2");
		dt.setName("action");
		dt.setEnumeration(EventUserAction.class.getName());
		a.put(new DefaultRenderer().renderColumn(dt));
		table.setColumns(a.toString());
		table.afterCompose();
		field.getParent().appendChild(d);
		
		table.addEventListener("onSelect", (ev) -> {
			Long userId = (Long) XPathUtils.eval (table, "userId");
			if (userId != null) {
				Executions.getCurrent().sendRedirect("/resource/user/user.zul?filter=id eq "+userId, "_blank");
			}
			table.setSelectedIndex(-1);
		});
	}

	@Override
	public Object translateToUserInterface(InputField3 field, Object o) throws Exception {
		if (o == null) return null;
		else return ((IssueUser)o).getUserName();
	}

	@Override
	public boolean isVisible(InputField3 field) throws Exception {
		return false;
	}
}
