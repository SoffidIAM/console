package com.soffid.iam.web.group;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.api.User;
import com.soffid.iam.web.component.DatatypeColumnsDatatable;
import com.soffid.iam.web.component.DynamicColumnsDatatable;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datamodel.DataNode;


public class GroupRoleDatatable extends DatatypeColumnsDatatable {
	static String[] mandatoryColumns = {
			"user", "fullName", "info"
	};
	
	static String[] hiddenColumns = {
			"group"
	};
	
	public GroupRoleDatatable() throws Exception {
	}
	
	public Collection<DataType> getDataTypes() throws Exception {
		LinkedList<DataType> l = new LinkedList<DataType>( 
				EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(GroupUser.class.getName(), null));
		for (int pos = 0; pos < l.size(); ) {
			DataType dt = l.get(pos);
			if (dt.getType() == TypeEnumeration.SEPARATOR || Arrays.binarySearch(hiddenColumns, dt.getName()) >= 0)
				l.remove(pos);
			else {
				pos ++;
				if (dt.getName().equals("user")) 
				{
					DataType dt2 = new DataType();
					dt2.setName("fullName");
					dt2.setBuiltin(true);
					dt2.setNlsLabel("com.soffid.iam.api.User.fullName");
					dt2.setType(TypeEnumeration.STRING_TYPE);
					l.add(pos++, dt2);
				}
			}
		}
		
		DataType dt2 = new DataType();
		dt2.setName("info");
		dt2.setBuiltin(true);
		dt2.setNlsLabel("grups.zul.Tipus");
		dt2.setType(TypeEnumeration.STRING_TYPE);
		l.add(dt2);

		return l;
	}

	@Override
	public String[] getDefaultColumns() throws Exception {
		return mandatoryColumns;
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
