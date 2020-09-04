package com.soffid.iam.web.host;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.JSONException;
import org.json.JSONObject;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.Host;
import com.soffid.iam.web.component.DatatypeColumnsDatatable;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.zkib.datamodel.DataNode;


public class HostDatatable extends DatatypeColumnsDatatable {
	static String[] defaultColumns = {
			"name", "description", "ip"
	};
	
	public HostDatatable() throws Exception {
	}
	
	public Collection<DataType> getDataTypes() throws Exception {
		LinkedList<DataType> l = new LinkedList<DataType>( 
				EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(Host.class.getName(), null));
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

	@Override
	protected JSONObject getClientValue(Object element) throws JSONException {
		JSONObject s = super.getClientValue(element);
		Host u = (Host) ((DataNode)element).getInstance();
		return s;
	}

}
