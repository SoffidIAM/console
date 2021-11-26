package com.soffid.iam.web.account;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.UiException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.web.SearchAttributeDefinition;
import com.soffid.iam.web.component.BulkAction;
import com.soffid.iam.web.component.inputField.RoleDataHandler;
import com.soffid.iam.web.popup.BulkActionAttribute;
import com.soffid.iam.web.popup.BulkActionAttributeAction;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.jxpath.JXPathContext;


public class AccountBulkAction extends BulkAction {

	@Override
	public Collection<DataType> getMetadata() throws InternalErrorException, NamingException, CreateException {
		Collection<DataType> l2 = EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(Account.class.getName(), null) ;;

		Set<String> names = new HashSet<String>();
		
		LinkedList<DataType> l = new LinkedList<DataType>();
		l.addAll(l2);
		DataType dt = new DataType();
		dt.setName("$roles$");
		dt.setLabel(Labels.getLabel("usuaris.zul.Rols"));
		dt.setMultiValued(true);
		dt.setType(TypeEnumeration.ROLE_TYPE);
		l.addFirst(dt);
		for (com.soffid.iam.api.System d: EJBLocator.getDispatcherService().findAllActiveDispatchers())
		{
			for (DataType att: EJBLocator.getAdditionalDataService().findSystemDataTypes(d.getName()))
			{
				if ( ! names.contains(att.getName())) {
					names.add(att.getName());
					l.add(att);
				}
			}
		}
		return l;
	}
	
	@Override
	public void apply(JXPathContext ctx, String xpath, BulkActionAttribute attribute, BulkActionAttributeAction action, Object value) {
		if (attribute.getName().equals("$roles$")) {
			if ( action.getName().equals("addValue")) {
				for (String roleName: (Collection<String>) value) {
					Role role;
					try {
						role = new RoleDataHandler(null).getObject(roleName, null);
					} catch (Exception e) {
						throw new UiException("Error finding role "+value, e);
					}
					if (role != null) {
						DataNodeCollection coll = (DataNodeCollection) ctx.getValue(xpath+"/role");
						RoleAccount ra = new RoleAccount();
						ra.setUserCode((String) ctx.getValue(xpath+"/userName"));
						ra.setRoleName(role.getName());
						ra.setRoleDescription(role.getDescription());
						ra.setSystem(role.getSystem());
						ra.setAccountSystem(role.getSystem());
						ra.setInformationSystemName(role.getInformationSystemName());
						ra.setCertificationDate(new Date());
						ra.setStartDate(new Date());
						coll.add(ra);
					}
				}
			}
			if ( action.getName().equals("removeValue")) {
				for (String roleName: (Collection<String>) value) {
					Role role;
					try {
						role = new RoleDataHandler(null).getObject(roleName, null);
					} catch (Exception e) {
						throw new UiException("Error finding role "+value, e);
					}
					if (role != null) {
						DataNodeCollection coll = (DataNodeCollection) ctx.getValue(xpath+"/role");
						for (int i = 0; i < coll.size(); i++) {
							DataNode dataNode = (DataNode)coll.getDataModel(i);
							RoleAccount ra = (RoleAccount) dataNode.getInstance();
							if (ra.getRoleName().equals(role.getName()) && ra.getSystem().equals(role.getSystem()))
								dataNode.delete();
						}
					}
				}
			}
			if ( action.getName().equals("clearValue")) {
				DataNodeCollection coll = (DataNodeCollection) ctx.getValue(xpath+"/role");
				for (int i = 0; i < coll.size(); i++) {
					DataNode dataNode = (DataNode)coll.getDataModel(i);
					dataNode.delete();
				}
			}
			
		}
		else
			super.apply(ctx, xpath, attribute, action, value);
	}


}
