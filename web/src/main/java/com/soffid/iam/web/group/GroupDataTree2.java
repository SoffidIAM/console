package com.soffid.iam.web.group;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.DataType;
import com.soffid.iam.web.WebDataType;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.binder.tree.TreeModelProxyNode;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.datamodel.DataNode;


public class GroupDataTree2 extends DataTree2 {
	public GroupDataTree2() throws InternalErrorException, NamingException, CreateException {
		
	}
	
	@Override
	public void afterCompose() {
		try {
			JSONArray columns = new JSONArray();
			
			JSONArray finders = getFinders();
			JSONArray finderColumns = new JSONArray();
			finders.getJSONObject(0).put("columns", finderColumns);
			for (DataType dt: EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2("com.soffid.iam.api.Group", null)) {
				if (!dt.getName().equals("name")) {
					WebDataType dt2 = new WebDataType(dt);
					JSONObject c = new JSONObject();
					c.put("name", dt2.getLabel());
					c.put("hidden", !dt.getName().equals("description"));
					columns.put(c);
					JSONObject c2 = new JSONObject();
					c2.put("value", Boolean.TRUE.equals(dt2.getBuiltin()) ? dt.getName() :
						"attributes[@name='"+dt.getName()+"']");
					finderColumns.put(c2);
				}
			}
			setColumns(columns.toString());
		} catch (Exception e) {}
		super.afterCompose();
	}

	@Override
	protected String getRowClass(TreeModelProxyNode node) {
		DataNode dataNode = (DataNode) node.getValue();
		if (Boolean.TRUE.equals(dataNode.get("obsolete")))
			return "dashed";
		else
			return null;
	}
	
	
}
