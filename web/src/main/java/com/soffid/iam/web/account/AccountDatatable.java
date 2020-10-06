package com.soffid.iam.web.account;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.JSONException;
import org.json.JSONObject;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.System;
import com.soffid.iam.api.User;
import com.soffid.iam.web.component.DatatypeColumnsDatatable;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.zkib.datamodel.DataNode;


public class AccountDatatable extends DatatypeColumnsDatatable {
	static String[] defaultColumns = {
			"name", "system", "description", "disabled"
	};
	
	public AccountDatatable() throws Exception {
	}
	
	public Collection<DataType> getDataTypes() throws Exception {
		HashSet<String> names = new HashSet<>();
		LinkedList<DataType> l = new LinkedList<DataType>( 
				EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(Account.class.getName(), null));
		for (Iterator<DataType> it = l.iterator(); it.hasNext();) {
			DataType dt = it.next();
			names.add(dt.getName());
			if (dt.getType() == TypeEnumeration.SEPARATOR)
				it.remove();
		}
		
		for (System system: EJBLocator.getDispatcherService().findAllActiveDispatchers()) {
			for ( DataType dt: EJBLocator.getAdditionalDataService().findSystemDataTypes(system.getName())) {
				if (!names.contains(dt.getName())) {
					names.add(dt.getName());
					if (dt.getType() != TypeEnumeration.SEPARATOR)
						l.add(dt);
				}
			}
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
		Account a = (Account) ((DataNode)element).getInstance();
		if (a.isDisabled())
			s.put("$class", "dashed");
		else
			s.put("$class", "std");
		return s;
	}

}
