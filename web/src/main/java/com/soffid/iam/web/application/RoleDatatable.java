package com.soffid.iam.web.application;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.zkoss.zk.ui.event.Event;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.User;
import com.soffid.iam.web.component.DatatypeColumnsDatatable;
import com.soffid.iam.web.component.DynamicColumnsDatatable;
import com.soffid.iam.web.popup.SelectColumnsHandler;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datamodel.DataNode;


public class RoleDatatable extends DatatypeColumnsDatatable {
	static String[] defaultColumns = {
			"name", "description", "system", "domain"
	};
	
	public RoleDatatable() throws Exception {
	}
	
	public Collection<DataType> getDataTypes() throws Exception {
		LinkedList<DataType> l = new LinkedList<DataType>( 
				EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(Role.class.getName(), null));
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
//		User u = (User) ((DataNode)element).getInstance();
//		if (Boolean.FALSE.equals(u.getActive()))
//			s.put("$class", "dashed");
		return s;
	}

}
