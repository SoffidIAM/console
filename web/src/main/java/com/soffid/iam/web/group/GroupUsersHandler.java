package com.soffid.iam.web.group;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.User;
import com.soffid.iam.service.ejb.UserService;
import com.soffid.iam.web.component.DynamicColumnsDatatable;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.ObjectAttributesDiv;
import com.soffid.iam.web.popup.SelectColumnsHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.component.Wizard;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;


public class GroupUsersHandler extends Div implements AfterCompose {
	private static final long serialVersionUID = 1L;
	private String listboxPath;
	
	private UserService userService;
	
	public GroupUsersHandler() throws NamingException, CreateException {
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
		boolean isPrimaryGroup = Boolean.TRUE.equals(XPathUtils.getValue((DataSource) getListbox(), "primaryGroup"));
		Window w = getWindowModify();
		w.doHighlighted();
		ObjectAttributesDiv oad = (ObjectAttributesDiv) w.getFellow("attributes");
		List<DataType> dts = oad.getDataTypes();
		for (DataType dt: dts)
			dt.setVisibilityExpression(isPrimaryGroup? "false": null);
		oad.getDataType("group").setVisibilityExpression(null);
		oad.getDataType("group").setReadOnly(!isPrimaryGroup);
		oad.getDataType("user").setVisibilityExpression(null);
		oad.getDataType("user").setReadOnly(true);
		oad.refresh();
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
		InputField3 input = (InputField3) w.getFellow("user");
		String v = (String) input.getValue();
		if (input.attributeValidateAll()) {
			DataTree2 groupsListbox = (DataTree2) Path.getComponent(listboxPath);
			String groupName = (String) XPathUtils.getValue((DataSource) groupsListbox, "@name");
			String groupDescription = (String) XPathUtils.getValue((DataSource) groupsListbox, "@description");

			User user = EJBLocator.getUserService().findUserByUserName(v);
			if (user != null) {
				GroupUser gu = new GroupUser();
				gu.setDisabled(false);
				gu.setStart(new Date());
				gu.setGroupDescription(groupDescription);
				gu.setGroup(groupName);
				gu.setUser(v);
				gu.setFullName(user.getFullName());
				gu.setInfo(Labels.getLabel("user-groups.sg-type"));
				
				DataNodeCollection coll = (DataNodeCollection) XPathUtils.getValue((DataSource)groupsListbox, "/user");
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
}
