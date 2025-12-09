package com.soffid.iam.web.self;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.json.JSONException;
import org.json.JSONObject;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.PasswordPolicy;
import com.soffid.iam.api.User;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datamodel.DataNode;


public class AccountsTable extends DataTable {

	@Override
	protected JSONObject getClientValue(Object element) throws JSONException {
		DataNode sdn = (DataNode)element;
		JSONObject s = super.getClientValue(element);
		DataModelCollection dnc = sdn.getListModel("dispatcherInformation");
		if (dnc.getSize() > 0) {
			DataNode dn = (DataNode) dnc.getDataModel(0);
			s.put("systemDescription",  dn.get("description"));
		}
		if (Boolean.TRUE.equals( sdn.get("disabled")))
			s.put("$class", "dashed");
		else
			s.put("$class", "std");
		
		PasswordPolicy pp;
		try {
			pp = EJBLocator.getSelfService().getPasswordPolicy((Account) sdn.getInstance());
			s.put("canView", pp != null && pp.isAllowPasswordQuery() && 
					Boolean.TRUE.equals(pp.getStoreUserPasswords()));
			s.put("canChange", pp != null && pp.isAllowPasswordChange());
		} catch (InternalErrorException | NamingException | CreateException e) {
		}
		return s;

	}

	
}
