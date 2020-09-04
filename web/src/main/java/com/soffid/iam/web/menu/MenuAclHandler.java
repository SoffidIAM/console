package com.soffid.iam.web.menu;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AccessTreeAuthorization;
import com.soffid.iam.api.Role;
import com.soffid.iam.service.ejb.UserService;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.ObjectAttributesDiv;

import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Wizard;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;


public class MenuAclHandler extends Div implements AfterCompose {
	private static final long serialVersionUID = 1L;
	private String listboxPath;
	
	private UserService userService;
	private Role currentRole;
	private CustomField3 account;
	private CustomField3 user;
	private CustomField3 role;
	private CustomField3 group;
	private CustomField3 account2;
	private CustomField3 user2;
	private CustomField3 role2;
	private CustomField3 group2;
	private CustomField3 typeField;
	private CustomField3 levelField;
	private CustomField3 account3;
	private CustomField3 user3;
	private CustomField3 role3;
	private CustomField3 group3;
	
	public MenuAclHandler() throws NamingException, CreateException {
		userService = EJBLocator.getUserService();
	}
	
	public void afterCompose() {
		listboxPath = (String) Executions.getCurrent().getArg().get("listbox");
		Window w = getWindowAdd();
		user = (CustomField3) w.getFellow("user");
		account = (CustomField3) w.getFellow("account");
		role = (CustomField3) w.getFellow("role");
		group = (CustomField3) w.getFellow("group");
		user2 = (CustomField3) w.getFellow("user2");
		role2 = (CustomField3) w.getFellow("role2");
		account2 = (CustomField3) w.getFellow("account2");
		group2 = (CustomField3) w.getFellow("group2");
		typeField = (CustomField3) w.getFellow("type");
		levelField = (CustomField3) w.getFellow("level");
		w = getWindowModify();
		user3 = (CustomField3) w.getFellow("user3");
		account3 = (CustomField3) w.getFellow("account3");
		role3 = (CustomField3) w.getFellow("role3");
		group3 = (CustomField3) w.getFellow("group3");
	}
	
	
	public void addNew (Event event) {
		Window w = (Window) getFellow("add-window");
		getWizard().setSelected(0);
		w.doHighlighted();
	}
	
	public void showDetails(Event event) {
		String o = (String) XPathUtils.getValue((DataSource)getListbox(), "authorizedEntityCode");
		String t = (String) XPathUtils.getValue((DataSource)getListbox(), "authorizationEntityType");
		getWizard().next();
		user3.setVisible( "user".equals(t) );
		role3.setVisible( "role".equals(t) );
		group3.setVisible( "group".equals(t) );
		account3.setVisible( "account".equals(t) );

		Window w = getWindowModify();
		w.doHighlighted();
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
	
	public void onChange() {
		Window w = getWindowModify();
		ObjectAttributesDiv d = (ObjectAttributesDiv) w.getFellow("attributes");
		if (d.validate()) {
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

	public void selectType(Event event) {
		Window w = getWindowAdd();
		CustomField3 cf = (CustomField3) w.getFellow("type");
		if (cf.validate()) {
			String type = (String) cf.getValue();
			getWizard().next();
			user.setVisible("user".equals(type));
			role.setVisible("role".equals(type));
			group.setVisible("group".equals(type));
			account.setVisible("account".equals(type));
			user2.setVisible("user".equals(type));
			role2.setVisible("role".equals(type));
			group2.setVisible("group".equals(type));
			account2.setVisible("account".equals(type));
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
		if ("account".equals(type) && account.validate() ) {
			account2.setValue(account.getValue());
			getWizard().next();
		}
	}
	
	public void applyAdd(Event event) throws Exception {
		if (levelField.validate()) {
			DataSource entryPointDataSource = (DataSource) Path.getComponent(listboxPath);
			AccessTreeAuthorization na = new AccessTreeAuthorization();
			na.setAccessTreeId((Long) XPathUtils.getValue(entryPointDataSource, "id"));
			na.setAuthorizationLevelDescription( levelField.getValue().toString() );
			String type = (String) typeField.getValue();
			na.setAuthorizationEntityType(type);
			if ("user".equals(type) && user.validate() ) {
				na.setAuthorizedEntityCode(user.getValue().toString());
				na.setAuthorizedEntityDescription(user.getDescription(user.getValue()));
			}
			if ("group".equals(type) && group.validate() ) {
				na.setAuthorizedEntityCode(group.getValue().toString());
				na.setAuthorizedEntityDescription(group.getDescription(group.getValue()));
			}
			if ("role".equals(type) && role.validate() ) {
				na.setAuthorizedEntityCode(role.getValue().toString());
				na.setAuthorizedEntityDescription(role.getDescription(role.getValue()));
			}
			if ("account".equals(type) && account.validate() ) {
				na.setAuthorizedEntityCode(account.getValue().toString());
				na.setAuthorizedEntityDescription(account.getDescription(account.getValue()));
			}
			
			XPathUtils.createPath(entryPointDataSource, "/auth", na);
			entryPointDataSource.commit();
			getWindowAdd().setVisible(false);
			getListbox().setSelectedIndex(-1);
		}
	}

	public void previous(Event event) {
		getWizard().previous();
	}
	
}
