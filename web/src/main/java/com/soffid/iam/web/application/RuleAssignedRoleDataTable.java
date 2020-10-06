package com.soffid.iam.web.application;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.json.JSONException;
import org.json.JSONObject;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RuleAssignedRole;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.datamodel.DataNode;


public class RuleAssignedRoleDataTable extends DataTable {

	@Override
	protected JSONObject getClientValue(Object element) throws JSONException {
		JSONObject s = new JSONObject();
		RuleAssignedRole rar = (RuleAssignedRole) ((DataNode)element).getInstance();
		if (rar.getRoleId() != null) {
			Role role;
			try {
				role = EJBLocator.getApplicationService().findRoleById(rar.getRoleId());
				s.put("name", role.getName());
				s.put("system", role.getSystem());
				s.put("description", role.getDescription());
			} catch (InternalErrorException | NamingException | CreateException e) {
			}
		}
		s.put("domainValue", rar.getDomainValue());
		return s;
	}

}
