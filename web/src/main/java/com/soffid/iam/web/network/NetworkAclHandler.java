package com.soffid.iam.web.network;

import java.io.IOException;
import java.util.Date;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.Identity;
import com.soffid.iam.api.NetworkAuthorization;
import com.soffid.iam.service.ejb.UserService;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.DynamicColumnsDatatable;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.ObjectAttributesDiv;
import com.soffid.iam.web.popup.SelectColumnsHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Wizard;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;


public class NetworkAclHandler extends Div implements AfterCompose {
	private static final long serialVersionUID = 1L;
	private String listboxPath;
	CustomField3 user;
	CustomField3 role;
	CustomField3 group;
	CustomField3 user2;
	CustomField3 role2;
	CustomField3 group2;
	CustomField3 typeField;
	CustomField3 levelField;
	CustomField3 patternField;
	private CustomField3 user3;
	private CustomField3 role3;
	private CustomField3 group3;
	
	public NetworkAclHandler() throws NamingException, CreateException {
	}
	
	public void afterCompose() {
		listboxPath = (String) Executions.getCurrent().getArg().get("listbox");
		Window w = getWindowAdd();
		user = (CustomField3) w.getFellow("user");
		role = (CustomField3) w.getFellow("role");
		group = (CustomField3) w.getFellow("group");
		user2 = (CustomField3) w.getFellow("user2");
		role2 = (CustomField3) w.getFellow("role2");
		group2 = (CustomField3) w.getFellow("group2");
		typeField = (CustomField3) w.getFellow("type");
		levelField = (CustomField3) w.getFellow("level");
		patternField = (CustomField3) w.getFellow("pattern");
		w = getWindowModify();
		user3 = (CustomField3) w.getFellow("user3");
		role3 = (CustomField3) w.getFellow("role3");
		group3 = (CustomField3) w.getFellow("group3");
	}
	
	
	public void addNew (Event event) {
		Window w = (Window) getFellow("add-window");
		getWizard().setSelected(0);
		w.doHighlighted();
	}
	
	public void showDetails(Event event) {
		String u = (String) XPathUtils.getValue((DataSource)getListbox(), "identity/userCode");
		String g = (String) XPathUtils.getValue((DataSource)getListbox(), "identity/groupCode");
		String r = (String) XPathUtils.getValue((DataSource)getListbox(), "identity/roleName");
		getWizard().next();
		user3.setVisible( u != null );
		role3.setVisible( r != null );
		group3.setVisible( g != null);

		Window w = getWindowModify();
		w.doHighlighted();
		
		displayRemoveButton(getListbox(), false);
	}
	
	public void closeDetails(Event event) {
		Window w = getWindowModify();
		w.setVisible(false);
		if (event != null)
			event.stopPropagation();
	}
	
	public void delete(Event event) {
		Missatgebox.confirmaOK_CANCEL(Labels.getLabel("common.delete"), 
				(event2) -> {
					if (event2.getName().equals("onOK")) {
						DataTable dt = getListbox();
						dt.delete();
						closeDetails(null);
						
					}
				});
	}
	
	public boolean validateAttributes(Component form) {
		if (form == null || !form.isVisible()) return true;
		if (form instanceof ObjectAttributesDiv) {
			return ((ObjectAttributesDiv) form).validate();
		}
		if (form instanceof InputField3) {
			InputField3 inputField = (InputField3)form;
			if (inputField.isReadonly() || inputField.isDisabled())
				return true;
			else
				return inputField.attributeValidateAll();
		}
		boolean ok = true;
		for (Component child = form.getFirstChild(); child != null; child = child.getNextSibling())
			if (! validateAttributes(child))
				ok = false;
		return ok;
	}


	public void onChange() {
		Window w = getWindowModify();
		if (validateAttributes(w.getFellow("form"))) {
			DataTable dt = getListbox();
			dt.commit();
			closeDetails(null);
		}
	}

	public Window getWindowModify() {
		return (Window) getFellow("modify-window");
	}
	
	public void undoAdd(Event ev) {
		Window w = getWindowAdd();
		w.setVisible(false);
	}
	
	public DataTable getListbox() {
		return (DataTable) getFellow("listbox");
	}

	public Window getWindowAdd() {
		return (Window) getFellow("add-window");
	}

	public Wizard getWizard() {
		return (Wizard) getWindowAdd().getFellow("wizard");
	}

	public void backAndRollback(Event ev) {
		DataTable dt = getListbox();
		dt.delete();
		getWizard().previous();
	}

	
	public String getListboxPath() {
		return listboxPath;
	}

	
	public void setListboxPath(String listboxPath) {
		this.listboxPath = listboxPath;
	}

	public void changeColumns(Event event) throws IOException {
		SelectColumnsHandler.startWizard((DynamicColumnsDatatable) getListbox());
	}
	
	public void selectType(Event event) {
		Window w = getWindowAdd();
		CustomField3 cf = (CustomField3) w.getFellow("type");
		if (cf.validate()) {
			String type = (String) cf.getValue();
			getWizard().next();
			user.setVisible("user".equals(type));
			role.setVisible("role".equals(type));
			group.setVisible("group".equals(type));
			user2.setVisible("user".equals(type));
			role2.setVisible("role".equals(type));
			group2.setVisible("group".equals(type));
		}
		
	}
	
	public void selectObject(Event event) {
		Window w = getWindowAdd();
		String type = (String) typeField.getValue();
		if ("user".equals(type) && user.validate() ) {
			user2.setValue(user.getValue());
			getWizard().next();
		}
		if ("group".equals(type) && group.validate() ) {
			group2.setValue(group.getValue());
			getWizard().next();
		}
		if ("role".equals(type) && role.validate() ) {
			role2.setValue(role.getValue());
			getWizard().next();
		}
	}
	
	public void applyAdd(Event event) throws Exception {
		if (levelField.validate()) {
			DataSource networkDataSource = (DataSource) Path.getComponent(listboxPath);
			NetworkAuthorization na = new NetworkAuthorization();
			na.setNetworkCode((String) XPathUtils.getValue(networkDataSource, "name"));
			na.setMask( (String) patternField.getValue() );
			na.setLevel( Integer.parseInt(levelField.getValue().toString()) );
			Identity i = new Identity();
			na.setIdentity(i);
			String type = (String) typeField.getValue();
			if ("user".equals(type) && user.validate() ) {
				i.setIdentityCode(user.getValue().toString());
				i.setUserCode(user.getValue().toString());
				i.setDescription(user.getDescription(user.getValue()));
			}
			if ("group".equals(type) && group.validate() ) {
				i.setIdentityCode(group.getValue().toString());
				i.setGroupCode(group.getValue().toString());
				i.setDescription(group.getDescription(group.getValue()));
			}
			if ("role".equals(type) && role.validate() ) {
				i.setIdentityCode(role.getValue().toString());
				i.setRoleName(role.getValue().toString());
				i.setDescription(role.getDescription(role.getValue()));
			}
			
			XPathUtils.createPath(networkDataSource, "/acl", na);
			networkDataSource.commit();
			getWindowAdd().setVisible(false);
			getListbox().setSelectedIndex(-1);
		}
	}

	public void previous(Event event) {
		getWizard().previous();
	}

	public void displayRemoveButton(Component lb, boolean display) {
		HtmlBasedComponent d = (HtmlBasedComponent) lb.getNextSibling();
		if (d != null && d instanceof Div) {
			d =  (HtmlBasedComponent) d.getFirstChild();
			if (d != null && "deleteButton".equals(d.getSclass())) {
				d.setVisible(display);
			}
		}
	}
	
	public void multiSelect(Event event) {
		DataTable lb = (DataTable) event.getTarget();
		displayRemoveButton( lb, lb.getSelectedIndexes() != null && lb.getSelectedIndexes().length > 0);
	}

	public void deleteSelected(Event event0) {
		Component b = event0.getTarget();
		final Component lb = b.getParent().getPreviousSibling();
		if (lb instanceof DataTable) {
			final DataTable dt = (DataTable) lb;
			if (dt.getSelectedIndexes() == null || dt.getSelectedIndexes().length == 0) return;
			String msg = dt.getSelectedIndexes().length == 1 ? 
					Labels.getLabel("common.delete") :
					String.format(Labels.getLabel("common.deleteMulti"), dt.getSelectedIndexes().length);
				
			Missatgebox.confirmaOK_CANCEL(msg, 
					(event) -> {
						if (event.getName().equals("onOK")) {
							dt.delete();
							displayRemoveButton(lb, false);
						}
					});
		}
	}
}
