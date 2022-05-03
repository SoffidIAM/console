package com.soffid.iam.web.user;

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
import com.soffid.iam.api.Printer;
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


public class UserPrintersHandler extends Div implements AfterCompose {
	private static final long serialVersionUID = 1L;
	private String listboxPath;
	
	private UserService userService;
	private boolean newPrinter;
	
	public UserPrintersHandler() throws NamingException, CreateException {
		userService = EJBLocator.getUserService();
	}
	
	public void afterCompose() {
		listboxPath = (String) Executions.getCurrent().getArg().get("listbox");
	}
	
	
	public void addNew (Event event) {
		
		Window w = getWindowModify();
		w.doHighlighted();
		getListbox().addNew();
		
		Component userListBox = Path.getComponent(getSpaceOwner() , listboxPath);
		XPathUtils.setValue(getListbox(), "user", XPathUtils.eval(userListBox, "userName"));
		XPathUtils.setValue(getListbox(), "fullName", XPathUtils.eval(userListBox, "fullName"));
		XPathUtils.setValue(getListbox(), "enabledByDefault", false);
		displayRemoveButton(getListbox(), false);
		newPrinter = true;
	}
	
	public void showDetails(Event event) {
		Window w = getWindowModify();
		w.doHighlighted();
		displayRemoveButton(getListbox(), false);
		newPrinter = false;
	}
	
	public void closeDetails(Event event) {
		if (newPrinter)
			getListbox().delete();
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
	
	public void apply() {
		Window w = getWindowModify();
		CustomField3 cf = (CustomField3) w.getFellow("printer");
		if (cf.attributeValidateAll()) {
			DataTable dt = getListbox();
			dt.commit();
			closeDetails(null);
			w.setVisible(false);
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
	
	public void changePrinter(Event ev) throws Exception {
		CustomField3 cf = (CustomField3) ev.getTarget();
		Printer printer = (Printer) cf.getValueObject();
		XPathUtils.setValue(getListbox(), "printerServerName", printer.getHostName());
	}
	
	public void closePrinters(Event ev) {
		Component w = (Component) getSpaceOwner();
		w.getParent().setVisible(false);
	}
}
