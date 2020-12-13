package com.soffid.iam.web.custom;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.JSONException;
import org.json.JSONObject;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.Host;
import com.soffid.iam.web.component.DatatypeColumnsDatatable;
import com.soffid.iam.web.component.DynamicColumnsDatatable;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.zkib.datamodel.DataNode;


public class CustomObjectDatatable extends DatatypeColumnsDatatable {
	String type;
	
	static String[] defaultColumns = {
			"name", "description"
	};
	
	public CustomObjectDatatable() throws Exception {
	}
	
	public Collection<DataType> getDataTypes() throws Exception {
		LinkedList<DataType> l = new LinkedList<DataType>( 
				EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(type, null));
		for (Iterator<DataType> it = l.iterator(); it.hasNext();) {
			DataType dt = it.next();
			if (dt.getType() == TypeEnumeration.SEPARATOR)
				it.remove();
		}
		return l;
	}

	@Override
	public String[] getDefaultColumns() throws Exception {
		return defaultColumns;
	}

	
	public String getType() {
		return type;
	}

	
	public void setType(String type) {
		this.type = type;
	}

	
}
