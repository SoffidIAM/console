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


public class GroupUserDatatable extends DatatypeColumnsDatatable {
	static String[] mandatoryColumns = {
			"user", "user_.fullName", "info"
	};
	
	static String[] hiddenColumns = {
			"group"
	};
	
	public GroupUserDatatable() throws Exception {
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
			}
		}
		
		DataType dt2 = new DataType();
		dt2.setName("info");
		dt2.setBuiltin(true);
		dt2.setNlsLabel("grups.zul.Tipus");
		dt2.setType(TypeEnumeration.STRING_TYPE);
		l.add(dt2);

		LinkedList<DataType> ll = new LinkedList<DataType>( 
				EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(User.class.getName(), null));
		for (DataType dt: ll) {
			DataType dt3 = new DataType(dt);
			if (Boolean.TRUE.equals(dt3.getBuiltin()))
				dt3.setName("user_."+dt3.getName());
			else
				dt3.setName("user_.attributes."+dt3.getName());
			dt3.setBuiltin(true);
			l.add(dt3);
		}
		return l;
	}

	@Override
	public String[] getDefaultColumns() throws Exception {
		return mandatoryColumns;
	}

	@Override
	protected JSONObject getClientValue(Object element) throws JSONException {
		JSONObject s = super.getClientValue(element);
		GroupUser gu = (GroupUser) ((DataNode)element).getInstance();
		try {
			User u = EJBLocator.getUserService().findUserByUserName(gu.getUser());
			if (u != null)
				s.put("user_", wrap(u));
			if (u == null || Boolean.FALSE.equals(u.getActive()))
				s.put("$class", "dashed");
		} catch (Exception e) {
			
		}
		return s;
	}

}
