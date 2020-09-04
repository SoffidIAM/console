package com.soffid.iam.web.group;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.service.ejb.UserService;
import com.soffid.iam.web.component.DomainValueField;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.ObjectAttributesDiv;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.component.Wizard;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;


public class GroupRolesHandler extends Div implements AfterCompose {
	private static final long serialVersionUID = 1L;
	private String listboxPath;
	
	private UserService userService;
	private Role currentRole;
	
	public GroupRolesHandler() throws NamingException, CreateException {
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
	
	public void selectDomain(Event ev) throws InternalErrorException, NamingException, CreateException, IOException {
		Window w = getWindowAdd();
		InputField3 input = (InputField3) w.getFellow("role");
		String v = (String) input.getValue();
		if (input.attributeValidateAll()) {
			int i = v.lastIndexOf("@");
			String roleName = i > 0 ? v.substring(0, i): v;
			String systemName = i > 0 ? v.substring(i+1) : EJBLocator.getDispatcherService().findSoffidDispatcher().getName();
			currentRole = EJBLocator.getApplicationService().findRoleByNameAndSystem(roleName, systemName);
			DataTree2 groupsListbox = (DataTree2) Path.getComponent(listboxPath);
			String groupName = (String) XPathUtils.getValue((DataSource) groupsListbox, "@name");
			String groupDescription = (String) XPathUtils.getValue((DataSource) groupsListbox, "@description");

			if (currentRole != null) {
				RoleGrant ra = new RoleGrant();
				ra.setOwnerGroup(groupName);
				ra.setEnabled(true);
				ra.setRoleId(currentRole.getId());
				ra.setInformationSystem(currentRole.getInformationSystemName());
				ra.setRoleDescription(currentRole.getDescription());
				ra.setRoleName(currentRole.getName());
				ra.setSystem(currentRole.getSystem());
				ra.setMandatory(true);
				
				DataNodeCollection coll = (DataNodeCollection) XPathUtils.getValue((DataSource)groupsListbox, "/grantedRoles");
				coll.add(ra);
				coll.setActiveNode(coll.getDataModel(coll.getSize()-1));
				getWizard().next();
				
				DomainValueField domains = (DomainValueField) w.getFellow("domainValues");
				domains.setValue(new LinkedList<>());
				if (currentRole.getDomain() == null) {
					setProperties(null);
					getWizard().next();
				} else {
					DomainValueField dvf = (DomainValueField) getWindowAdd().getFellow("domainValues");
					dvf.setApplication(currentRole.getInformationSystemName());
					dvf.setDomain(currentRole.getDomain());
					dvf.setValue(new LinkedList<String>());
					dvf.createField();
				}
			}
		}
	}

	public void setProperties(Event ev) throws InternalErrorException, NamingException, CreateException {
		Window w = getWindowAdd();
		DomainValueField input = (DomainValueField) w.getFellow("domainValues");
		List<String> domains = (List<String>) input.getValue();
		DomainValueField input2 = (DomainValueField) w.getFellow("domainValues2");
		input2.setValue(domains);
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
		DataSource groupsDataSource = (DataSource) Path.getComponent(listboxPath);

		DomainValueField input = (DomainValueField) getWindowAdd().getFellow("domainValues");
		List<String> domains = (List<String>) input.getValue();
		DataTable lb = getListbox();
		if ( currentRole.getDomain() !=  null && domains != null && ! domains.isEmpty()) {
			lb.delete();
			DataTree2 groupsListbox = (DataTree2) Path.getComponent(listboxPath);
			String groupName = (String) XPathUtils.getValue((DataSource) groupsListbox, "@name");
			String groupDescription = (String) XPathUtils.getValue((DataSource) groupsListbox, "@description");
			
			for (String domain: domains) {
				RoleGrant ra = new RoleGrant();
				ra.setOwnerGroup(groupName);
				ra.setEnabled(true);
				ra.setInformationSystem(currentRole.getInformationSystemName());
				ra.setRoleDescription(currentRole.getDescription());
				ra.setRoleName(currentRole.getName());
				ra.setSystem(currentRole.getSystem());
				ra.setRoleId(currentRole.getId());
				ra.setDomainValue(domain);
				ra.setMandatory(true);
				DataNodeCollection coll = (DataNodeCollection) XPathUtils.getValue((DataSource)groupsListbox, "/grantedRoles");				
			}
		}
		groupsDataSource.commit();
		getWindowAdd().setVisible(false);
	}

	public void backAndRollback(Event ev) {
		DataTable dt = getListbox();
		dt.delete();
		getWizard().previous();
		if (currentRole.getDomain() == null) 
			getWizard().previous();
	}

	public void back(Event ev) {
		getWizard().previous();
	}

	
	public String getListboxPath() {
		return listboxPath;
	}

	
	public void setListboxPath(String listboxPath) {
		this.listboxPath = listboxPath;
	}
}
