package com.soffid.iam.web.user;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zul.Progressmeter;

import com.soffid.iam.api.DataType;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.User;
import com.soffid.iam.web.component.BulkAction;
import com.soffid.iam.web.component.inputField.GroupDataHandler;
import com.soffid.iam.web.component.inputField.RoleDataHandler;
import com.soffid.iam.web.popup.BulkActionAttribute;
import com.soffid.iam.web.popup.BulkActionAttributeAction;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.jxpath.JXPathContext;


public class UserBulkAction extends BulkAction {
	public UserBulkAction() {
		super(User.class.getName());
	}

	@Override
	public Collection<DataType> getMetadata() throws InternalErrorException, NamingException, CreateException {
		Collection<DataType> md = super.getMetadata();
		DataType dt = new DataType();
		dt.setName("$roles$");
		dt.setLabel(Labels.getLabel("usuaris.zul.Rols"));
		dt.setMultiValued(true);
		dt.setType(TypeEnumeration.ROLE_TYPE);
		md.add(dt);

		dt = new DataType();
		dt.setName("$groups$");
		dt.setLabel(Labels.getLabel("agents.zul.Grups"));
		dt.setMultiValued(true);
		dt.setType(TypeEnumeration.GROUP_TYPE);
		md.add(dt);

		return md;
	}

	@Override
	public void apply(JXPathContext ctx, String xpath, BulkActionAttribute attribute, BulkActionAttributeAction action, Object value) {
		if (attribute.getName().equals("$roles$")) {
			applyRoles(ctx, xpath, action, value);			
		}
		else if (attribute.getName().equals("$groups$")) {
			applyGroups(ctx, xpath, action, value);
		}
		else
			super.apply(ctx, xpath, attribute, action, value);
	}

	public void applyRoles(JXPathContext ctx, String xpath, BulkActionAttributeAction action, Object value) {
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

	public void applyGroups(JXPathContext ctx, String xpath, BulkActionAttributeAction action, Object value) {
		if ( action.getName().equals("addValue")) {
			for (String groupName: (Collection<String>) value) {
				Group group;
				try {
					group = new GroupDataHandler(null).getObject(groupName, null);
				} catch (Exception e) {
					throw new UiException("Error finding role "+value, e);
				}
				if (group != null) {
					DataNodeCollection coll = (DataNodeCollection) ctx.getValue(xpath+"/group");
					GroupUser ra = new GroupUser();
					ra.setUser((String) ctx.getValue(xpath+"/userName"));
					ra.setGroup(group.getName());
					ra.setGroupDescription(group.getDescription());
					ra.setFullName((String) ctx.getValue(xpath+"/fullName"));
					coll.add(ra);
				}
			}
		}
		if ( action.getName().equals("removeValue")) {
			for (String groupName: (Collection<String>) value) {
				Group group;
				try {
					group = new GroupDataHandler(null).getObject(groupName, null);
				} catch (Exception e) {
					throw new UiException("Error finding role "+value, e);
				}
				if (group != null) {
					DataNodeCollection coll = (DataNodeCollection) ctx.getValue(xpath+"/group");
					for (int i = 0; i < coll.size(); i++) {
						DataNode dataNode = (DataNode)coll.getDataModel(i);
						GroupUser ra = (GroupUser) dataNode.getInstance();
						if (ra.getGroup().equals(group.getName()))
							dataNode.delete();
					}
				}
			}
		}
		if ( action.getName().equals("clearValue")) {
			DataNodeCollection coll = (DataNodeCollection) ctx.getValue(xpath+"/group");
			for (int i = 0; i < coll.size(); i++) {
				DataNode dataNode = (DataNode)coll.getDataModel(i);
				dataNode.delete();
			}
		}
	}
}
