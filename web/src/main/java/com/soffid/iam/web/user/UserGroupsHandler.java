package com.soffid.iam.web.user;

import java.io.IOException;
import java.util.Date;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.Password;
import com.soffid.iam.service.ejb.UserService;
import com.soffid.iam.web.component.DynamicColumnsDatatable;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.ObjectAttributesDiv;
import com.soffid.iam.web.popup.SelectColumnsHandler;

import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.component.Wizard;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;


public class UserGroupsHandler extends Div implements AfterCompose {
	private static final long serialVersionUID = 1L;
	private String listboxPath;
	
	private UserService userService;
	
	public UserGroupsHandler() throws NamingException, CreateException {
		userService = EJBLocator.getUserService();
	}
	
	public void afterCompose() {
		listboxPath = (String) Executions.getCurrent().getArg().get("listbox");
	}
	
	
	public void addNew (Event event) {
		Window w = (Window) getFellow("add-window");
		getWizard().setSelected(0);
		w.doHighlighted();
	}
	
	public void showDetails(Event event) {
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
	
	public void setProperties(Event ev) throws InternalErrorException, NamingException, CreateException {
		Window w = getWindowAdd();
		InputField3 input = (InputField3) w.getFellow("group");
		String v = (String) input.getValue();
		if (input.attributeValidateAll()) {
			DataTable usersListbox = (DataTable) Path.getComponent(listboxPath);
			String userName = (String) XPathUtils.getValue((DataSource) usersListbox, "@userName");
			String fullName = (String) XPathUtils.getValue((DataSource) usersListbox, "@fullName");

			Group group = EJBLocator.getGroupService().findGroupByGroupName(v);
			if (group != null) {
				GroupUser gu = new GroupUser();
				gu.setDisabled(false);
				gu.setStart(new Date());
				gu.setFullName(fullName);
				gu.setUser(userName);
				gu.setGroup(v);
				gu.setGroupDescription(group.getDescription());
				
				DataNodeCollection coll = (DataNodeCollection) XPathUtils.getValue((DataSource)usersListbox, "/group");
				coll.add(gu);
				coll.setActiveNode(coll.getDataModel(coll.getSize()-1));
				getWizard().next();
				ObjectAttributesDiv uga = (ObjectAttributesDiv) getWindowAdd().getFellow("userGroupAttributes");
				uga.getInputFieldsMap().get("user").setReadonly(true);
				uga.getInputFieldsMap().get("group").setReadonly(true);
			}
		}
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

	public void applyAdd(Event event) throws CommitException {
		DataSource usersDataSource = (DataSource) Path.getComponent(listboxPath);
		usersDataSource.commit();
		getWindowAdd().setVisible(false);
		getListbox().setSelectedIndex(-1);
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
}
