package com.soffid.iam.web.application;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
import com.soffid.iam.api.Domain;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.service.ejb.UserService;
import com.soffid.iam.web.component.BulkAction;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.DomainValueField;
import com.soffid.iam.web.component.DynamicColumnsDatatable;
import com.soffid.iam.web.component.FileDump;
import com.soffid.iam.web.component.ObjectAttributesDiv;
import com.soffid.iam.web.popup.SelectColumnsHandler;

import es.caib.seycon.ng.comu.RolGrant;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.component.Databox;
import es.caib.zkib.component.Wizard;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.events.XPathRerunEvent;
import es.caib.zkib.zkiblaf.Missatgebox;


public class ApplicationRoleHandler extends Div implements AfterCompose {
	private static final long serialVersionUID = 1L;
	private static final int WIZARD_GRANTED = 1;
	private static final int WIZARD_GRANTEE = 2;
	private static final int WIZARD_GROUP = 3;
	private String listboxPath;
	
	private UserService userService;
	private Role currentRole;
	private Window wizardWindow;
	private Wizard wizard;
	private int wizardMode;
	private Role wizardRole;
	private Group wizardGroup;
	
	public ApplicationRoleHandler() throws NamingException, CreateException {
		userService = EJBLocator.getUserService();
	}
	
	public void afterCompose() {
		listboxPath = (String) Executions.getCurrent().getArg().get("listbox");
	}
	
	
	public void addNew (Event event) {
		DataTree2 dt = (DataTree2) Path.getComponent(listboxPath);
		DataSource dataSource = (DataSource) dt;
		DataSource rootDs = (DataSource) XPathUtils.getPath(this, "/model");
		String name = (String) XPathUtils.getValue (dataSource, "/name");
		
		getListbox().addNew();
		
		XPathUtils.setValue(getListbox(), "informationSystemName", name);
		
		Window w = (Window) getFellow("modify-window");
		w.doHighlighted();
	}
	
	public void showDetails(Event event) {
		DataNode dn = (DataNode) getListbox().getJXPathContext().getValue("/");
		getWindowModify().doHighlighted();
		displayRemoveButton(false);
	}
	
	public void closeDetails(Event event) throws Exception {
		Window w = getWindowModify();
		w.setVisible(false);
		if (event != null)
			event.stopPropagation();
		DataSource dt = (DataSource) Path.getComponent(listboxPath);
		DataModelCollection dn = (DataModelCollection) XPathUtils.getValue(dt, "/role");
		dn.refresh();
	}
	
	public void delete(Event event) {
		Missatgebox.confirmaOK_CANCEL(Labels.getLabel("common.delete"), 
				(event2) -> {
					if (event2.getName().equals("onOK")) {
						DataTable dt = getListbox();
						dt.delete();
						dt.commit();
						getWindowModify().setVisible(false);
					}
				});
	}
	
	public void onChange() {
		ObjectAttributesDiv d = (ObjectAttributesDiv) getWindowModify().getFellow("attributes");
		if (d.validate()) {
			getListbox().commit();
			getWindowModify().setVisible(false);
		}
	}

	public Window getWindowModify() {
		return (Window) getFellow("modify-window");
	}
	
	public DynamicColumnsDatatable getListbox() {
		return (DynamicColumnsDatatable) getFellow("listbox");
	}

	public Window getWindowAdd() {
		return (Window) getFellow("add_domain_window");
	}

	public Window getWindowAddValue() {
		return (Window) getFellow("add_value_window");
	}

	public void applyAddDomain(Event event) throws CommitException {
		CustomField3 name = (CustomField3) getWindowAdd().getFellow("name");
		CustomField3 desc = (CustomField3) getWindowAdd().getFellow("description");
		if (name.validate() && desc.validate()) {
			getListbox().commit();
			getWindowAdd().setVisible(false);
			getListbox().setSelectedIndex(new int[0]);
		}
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
	
	public void downloadCsv(Event event) {
		getListbox().download();
	}

	public void addNewGranted (Event event) throws NamingException, CreateException, InternalErrorException, IOException {
		wizardWindow = (Window) getFellow("add-granted-window");
		wizard = (Wizard) wizardWindow.getFellow("wizard");
		wizardMode = WIZARD_GRANTED;

		String currentDomain = (String) getListbox().getJXPathContext().getValue("/@domain");
		
		DomainValueField dvf = (DomainValueField) wizardWindow.getFellow("sourceDomainValues");
		if (currentDomain == null)
		{
			dvf.setVisible(false);
			wizard.setSelected(1);
		} else {
			wizard.setSelected(0);
			dvf.setApplication(currentRole.getInformationSystemName());
			dvf.setDomain(currentRole.getDomain());
			dvf.setValue(new LinkedList<String>());
			dvf.setVisible(true);
			dvf.createField();
		}
			
		CustomField3 role = (CustomField3) wizardWindow.getFellow("role");
		role.setValue("");
		wizardWindow.doHighlighted();
	}
	
	public void addNewGrantee (Event event) throws NamingException, CreateException, InternalErrorException, IOException {
		wizardWindow = (Window) getFellow("add-grantee-window");
		wizard = (Wizard) wizardWindow.getFellow("wizard");
		wizardMode = WIZARD_GRANTEE;

		wizard.setSelected(0);
		
		CustomField3 role = (CustomField3) wizardWindow.getFellow("role");
		role.setValue("");
		wizardWindow.doHighlighted();
	}

	public void addNewGroup (Event event) throws NamingException, CreateException, InternalErrorException, IOException {
		wizardWindow = (Window) getFellow("add-group-window");
		wizard = (Wizard) wizardWindow.getFellow("wizard");
		wizardMode = WIZARD_GROUP;

		wizard.setSelected(0);
		
		CustomField3 group = (CustomField3) wizardWindow.getFellow("group");
		group.setValue("");
		wizardWindow.doHighlighted();
	}

	public void wizardBack(Event event) {
		switch (wizardMode) {
		case WIZARD_GRANTED:
			switch (wizard.getSelected()) {
			case 0:
				wizard.getParent().setVisible(false);
				break;
			case 1:
				String currentDomain = (String) getListbox().getJXPathContext().getValue("/@domain");
				if (currentDomain == null) {
					wizardWindow.setVisible(false);
				} else {
					wizard.previous();
				}
				break;
			case 2:
				wizard.previous();
				break;
			case 3:
				wizard.previous();
				if (wizardRole.getDomain() == null) {
					wizard.previous();
				}
				break;
			}
			break;
		case WIZARD_GRANTEE:
			switch (wizard.getSelected()) {
			case 3:
				wizard.previous();
				String currentDomain = (String) getListbox().getJXPathContext().getValue("/@domain");
				if (currentDomain != null) {
					break;
				}
			case 2:
				wizard.previous();
				if (wizardRole.getDomain() != null) {
					break;
				}
			case 1:
				wizard.previous();
				break;
			case 0:
				wizard.getParent().setVisible(false);
				break;
			}
			break;
		case WIZARD_GROUP:
			switch (wizard.getSelected()) {
			case 2:
				wizard.previous();
				String currentDomain = (String) getListbox().getJXPathContext().getValue("/@domain");
				if (currentDomain != null) {
					break;
				}
			case 1:
				wizard.previous();
				break;
			case 0:
				wizard.getParent().setVisible(false);
				break;
			}
			break;
		}
	}
	
	public void wizardNext(Event event) throws Exception {
		switch (wizardMode) {
		case WIZARD_GRANTED:
			switch (wizard.getSelected()) {
			case 0:
				wizard.next();
				break;
			case 1:
				CustomField3 role = (CustomField3) wizardWindow.getFellow("role");
				if (role.validate()) {
					wizardRole = (Role) role.getValueObject();
					wizard.next();
					DomainValueField dvf = (DomainValueField) wizardWindow.getFellow("domainValues");
					if (wizardRole.getDomain() == null) {
						dvf.setVisible(false);
						// DO NOT BREAK -> Skip next step
					}
					else {
						dvf.setApplication(wizardRole.getInformationSystemName());
						dvf.setDomain(wizardRole.getDomain());
						dvf.setValue(new LinkedList<String>());
						dvf.setVisible(true);
						dvf.createField();
						break;
					}
				} else {
					break;
				}
			case 2:
				{
					wizard.next();
					String currentRole = (String) getListbox().getJXPathContext().getValue("/@name");
					String currentSystem = (String) getListbox().getJXPathContext().getValue("/@system");
					String currentDomain = (String) getListbox().getJXPathContext().getValue("/@domain");
					((Databox)wizardWindow.getFellow("granteeRole")).setValue(currentRole+"@"+currentSystem); 
					
					List<String> sourceValues = (List<String>) ((Databox)wizardWindow.getFellow("sourceDomainValues")).getValue(); 
					((Databox)wizardWindow.getFellow("granteeScope")).setValue(sourceValues);
					((Databox)wizardWindow.getFellow("granteeScope")).setVisible(currentDomain != null);
								
					((Databox)wizardWindow.getFellow("grantedRole")).setValue(wizardRole.getName()+"@"+wizardRole.getSystem());
	
					List<String> values = (List<String>) ((Databox)wizardWindow.getFellow("domainValues")).getValue(); 
					((Databox)wizardWindow.getFellow("grantedScope")).setValue(values);
					((Databox)wizardWindow.getFellow("grantedScope")).setVisible(wizardRole.getDomain() != null);
				}
				break;
			}
			break;
		case WIZARD_GRANTEE:
			switch (wizard.getSelected()) {
			case 0:
				CustomField3 role = (CustomField3) wizardWindow.getFellow("role");
				if (role.validate()) {
					wizardRole = (Role) role.getValueObject();
					wizard.next();
					DomainValueField dvf = (DomainValueField) wizardWindow.getFellow("domainValues");
					if (wizardRole.getDomain() == null) {
						dvf.setVisible(false);
						// DO NOT BREAK -> Skip next step
					}
					else {
						dvf.setApplication(wizardRole.getInformationSystemName());
						dvf.setDomain(wizardRole.getDomain());
						dvf.setValue(new LinkedList<String>());
						dvf.setVisible(true);
						dvf.createField();
						break;
					}
				} else {
					break;
				}
			case 1:
				{
					wizard.next();
					String currentDomain = (String) getListbox().getJXPathContext().getValue("/@domain");
					DomainValueField dvf = (DomainValueField) wizardWindow.getFellow("sourceDomainValues");
					if (currentDomain == null)
					{
						dvf.setVisible(false);
					} else {
						dvf.setApplication(currentRole.getInformationSystemName());
						dvf.setDomain(currentRole.getDomain());
						dvf.setValue(new LinkedList<String>());
						dvf.setVisible(true);
						dvf.createField();
						break;
					}
				}
			case 2: 
				{
					wizard.next();
					String currentRole = (String) getListbox().getJXPathContext().getValue("/@name");
					String currentSystem = (String) getListbox().getJXPathContext().getValue("/@system");
					String currentDomain = (String) getListbox().getJXPathContext().getValue("/@domain");
					((Databox)wizardWindow.getFellow("grantedRole")).setValue(currentRole+"@"+currentSystem); 
					
					List<String> sourceValues = (List<String>) ((Databox)wizardWindow.getFellow("sourceDomainValues")).getValue(); 
					((Databox)wizardWindow.getFellow("grantedScope")).setValue(sourceValues);
					((Databox)wizardWindow.getFellow("grantedScope")).setVisible(currentDomain != null);
								
					((Databox)wizardWindow.getFellow("granteeRole")).setValue(wizardRole.getName()+"@"+wizardRole.getSystem());
	
					List<String> values = (List<String>) ((Databox)wizardWindow.getFellow("domainValues")).getValue(); 
					((Databox)wizardWindow.getFellow("granteeScope")).setValue(values);
					((Databox)wizardWindow.getFellow("granteeScope")).setVisible(wizardRole.getDomain() != null);
	
					break;
				}
			}
			break;
		case WIZARD_GROUP:
			switch (wizard.getSelected()) {
			case 0:
				{
					CustomField3 group = (CustomField3) wizardWindow.getFellow("group");
					if (group.validate()) {
						wizardGroup = (Group) group.getValueObject();
						wizard.next();
						String currentDomain = (String) getListbox().getJXPathContext().getValue("/@domain");
						if (currentDomain != null)
							break;
					} else {
						break;
					}
				}
			case 1:
				{
					wizard.next();
					CustomField3 group = (CustomField3) wizardWindow.getFellow("group");
					String currentRole = (String) getListbox().getJXPathContext().getValue("/@name");
					String currentSystem = (String) getListbox().getJXPathContext().getValue("/@system");
					String currentDomain = (String) getListbox().getJXPathContext().getValue("/@domain");
					((Databox)wizardWindow.getFellow("grantedRole")).setValue(currentRole+"@"+currentSystem); 
					
					List<String> sourceValues = (List<String>) ((Databox)wizardWindow.getFellow("domainValues")).getValue(); 
					((Databox)wizardWindow.getFellow("grantedScope")).setValue(sourceValues);
					((Databox)wizardWindow.getFellow("grantedScope")).setVisible(currentDomain != null);
								
					((Databox)wizardWindow.getFellow("granteeRole")).setValue(group.getValue());
	
					break;
				}
			}
			break;
		}
	}
	
	public void wizardApply (Event event) {
		switch (wizardMode) {
		case WIZARD_GRANTED:
		{
			Long currentRoleId = (Long) getListbox().getJXPathContext().getValue("/@id");
			String currentRole = (String) getListbox().getJXPathContext().getValue("/@name");
			String currentSystem = (String) getListbox().getJXPathContext().getValue("/@system");
			String currentDomain = (String) getListbox().getJXPathContext().getValue("/@domain");
			String currentDescription = (String) getListbox().getJXPathContext().getValue("/@description");
			
			List<String> sourceValues = (List<String>) ((Databox)wizardWindow.getFellow("sourceDomainValues")).getValue(); 

			List<String> values = (List<String>) ((Databox)wizardWindow.getFellow("domainValues")).getValue(); 
			
			List<RoleGrant> ownedRoles = new LinkedList<> ((List<RoleGrant>) getListbox().getJXPathContext().getValue("@ownedRoles"));
			
			for (String sd: normalize(sourceValues)) {
				for (String td: normalize(values) ) {
					RoleGrant rg = new RoleGrant();
					rg.setDomainValue(td);
					rg.setEnabled(true);
					rg.setMandatory(true);
					rg.setOwnerRole(currentRoleId);
					rg.setOwnerRoleName(currentRole);
					rg.setOwnerSystem(currentSystem);
					rg.setOwnerRoleDescription(currentDescription);
					rg.setOwnerRolDomainValue(sd);
					rg.setRoleDescription(this.wizardRole.getDescription());
					rg.setRoleId(wizardRole.getId());
					rg.setRoleName(wizardRole.getName());
					rg.setSystem(wizardRole.getSystem());
					ownedRoles.add(rg);
				}
			}
			getListbox().getJXPathContext().setValue("/ownedRoles", ownedRoles);
			getListbox().sendEvent(new XPathRerunEvent(getListbox(), "/ownedRoles"));
//			getListbox().commit();
			wizardWindow.setVisible(false);
			break;
		}
		case WIZARD_GRANTEE:
		{
			Long currentRoleId = (Long) getListbox().getJXPathContext().getValue("/@id");
			String currentRole = (String) getListbox().getJXPathContext().getValue("/@name");
			String currentSystem = (String) getListbox().getJXPathContext().getValue("/@system");
			String currentDomain = (String) getListbox().getJXPathContext().getValue("/@domain");
			String currentDescription = (String) getListbox().getJXPathContext().getValue("/@description");
			
			List<String> sourceValues = (List<String>) ((Databox)wizardWindow.getFellow("sourceDomainValues")).getValue(); 

			List<String> values = (List<String>) ((Databox)wizardWindow.getFellow("domainValues")).getValue(); 
			
			List<RoleGrant> ownerRoles = new LinkedList<> ((List<RoleGrant>) getListbox().getJXPathContext().getValue("@ownerRoles"));
			
			for (String sd: normalize(sourceValues)) {
				for (String td: normalize(values) ) {
					RoleGrant rg = new RoleGrant();
					rg.setDomainValue(td);
					rg.setEnabled(true);
					rg.setMandatory(true);
					rg.setOwnerRole(wizardRole.getId());
					rg.setOwnerRoleName(wizardRole.getName());
					rg.setOwnerSystem(wizardRole.getSystem());
					rg.setOwnerRoleDescription(wizardRole.getDescription());
					rg.setOwnerRolDomainValue(td);
					rg.setRoleDescription(currentDescription);
					rg.setRoleId(currentRoleId);
					rg.setRoleName(currentRole);
					rg.setSystem(currentSystem);
					ownerRoles.add(rg);
				}
			}
			getListbox().getJXPathContext().setValue("/ownerRoles", ownerRoles);
			getListbox().sendEvent(new XPathRerunEvent(getListbox(), "/ownerRoles"));
//			getListbox().commit();
			wizardWindow.setVisible(false);
			break;
		}
		case WIZARD_GROUP:
		{
			Long currentRoleId = (Long) getListbox().getJXPathContext().getValue("/@id");
			String currentRole = (String) getListbox().getJXPathContext().getValue("/@name");
			String currentSystem = (String) getListbox().getJXPathContext().getValue("/@system");
			String currentDomain = (String) getListbox().getJXPathContext().getValue("/@domain");
			String currentDescription = (String) getListbox().getJXPathContext().getValue("/@description");
			
			List<String> values = (List<String>) ((Databox)wizardWindow.getFellow("domainValues")).getValue(); 
			
			List<RoleGrant> ownerRoles = new LinkedList<> ((List<RoleGrant>) getListbox().getJXPathContext().getValue("/granteeGroups"));
			
			for (String td: normalize(values) ) {
				RoleGrant rg = new RoleGrant();
				rg.setDomainValue(td);
				rg.setEnabled(true);
				rg.setMandatory(true);
				rg.setOwnerGroup(wizardGroup.getName());
				rg.setRoleDescription(currentDescription);
				rg.setRoleId(currentRoleId);
				rg.setRoleName(currentRole);
				rg.setSystem(currentSystem);
				ownerRoles.add(rg);
			}

			getListbox().getJXPathContext().setValue("/granteeGroups", ownerRoles);
			getListbox().sendEvent(new XPathRerunEvent(getListbox(), "/granteeGroups"));
//			getListbox().commit();
			wizardWindow.setVisible(false);
			break;
		}
		}
	}
	
	private List<String> normalize (List<String> s ){
		LinkedList<String> r = new LinkedList<String>();
		if (s == null || s.isEmpty()) r.add(null);
		else r.addAll(s);
		return r;
	}
	
	public void removeGranted(Event event) {
		DataTable ownedRoles = (DataTable) getWindowModify().getFellow("ownedRoles");
		ownedRoles.delete();
	}
	
	public void removeGrantee(Event event) {
		DataTable owneeRoles = (DataTable) getWindowModify().getFellow("ownerRoles");
		owneeRoles.delete();
	}
	
	public void removeGroup(Event event) {
		DataTable ownerGroup = (DataTable) getWindowModify().getFellow("ownerGroups");
		ownerGroup.delete();
	}
	
	public void setMandatoryGranted(Event event) {
		String [] data = (String[]) event.getData();
		DataTable dt = (DataTable) getWindowModify().getFellow("ownedRoles");
		dt.getJXPathContext().setValue("@mandatory", data.length > 0 && "true".equals(data[0]));
	}
	
	public void setMandatoryGrantee(Event event) {
		String [] data = (String[]) event.getData();
		DataTable dt = (DataTable) getWindowModify().getFellow("ownerRoles");
		dt.getJXPathContext().setValue("@mandatory", data.length > 0 && "true".equals(data[0]));
	}
	
	public void setMandatoryGroup(Event event) {
		String [] data = (String[]) event.getData();
		DataTable dt = (DataTable) getWindowModify().getFellow("ownerGroups");
		dt.getJXPathContext().setValue("@mandatory", data.length > 0 && "true".equals(data[0]));
	}

	public void preview(Event event) throws InternalErrorException, NamingException, CreateException {
		Role r = (Role) ((DataNode) getListbox().getJXPathContext().getValue("/")).getInstance();
		ObjectAttributesDiv d = (ObjectAttributesDiv) getWindowModify().getFellow("attributes");
		if (d.validate() && r.getId() != null) {
			List<RoleAccount> ra = new LinkedList<>();
			List<RoleAccount> rr = new LinkedList<>();
			DataNodeCollection dnc = (DataNodeCollection) getListbox().getJXPathContext().getValue("/grant");
			for (int i = 0; i < dnc.size(); i++) {
				DataNode dn = (DataNode) dnc.getDataModel(i);
				if (dn.isDeleted())
					rr.add((RoleAccount) dn.getInstance());
				else if (dn.isNew())
					ra.add((RoleAccount) dn.getInstance());
			}
			String file = EJBLocator.getApplicationService().generateChangesReport(r, ra, rr);
			Window previewWindow = (Window) getFellow("previewWindow");
			((FileDump)previewWindow.getFellow("previewDiv")).setSrc(file);
			previewWindow.doHighlighted();
		}
	}
	
	public void closePreview(Event event) {
		Window previewWindow = (Window) getFellow("previewWindow");
		previewWindow.setVisible(false);
	}

	public void applyPreview(Event event) {
		getListbox().commit();
		Window previewWindow = (Window) getFellow("previewWindow");
		previewWindow.setVisible(false);
		getWindowModify().setVisible(false);
	}

	public void displayRemoveButton(boolean display) {
		HtmlBasedComponent d = (HtmlBasedComponent) getListbox().getNextSibling();
		if (d != null && d instanceof Div) {
			d =  (HtmlBasedComponent) d.getFirstChild();
			if (d != null && "deleteButton".equals(d.getSclass())) {
				d.setVisible(display);
			}
		}
	}
	
	public void multiSelect(Event event) {
		DataTable lb = (DataTable) getListbox();
		displayRemoveButton( lb.getSelectedIndexes() != null && lb.getSelectedIndexes().length > 0);
	}

	public void deleteSelected(Event event0) {
		Component lb = getListbox();
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
							displayRemoveButton(false);
						}
					});
		}
	}

	public void displayRemoveButton2(Component lb, boolean display) {
		HtmlBasedComponent d = (HtmlBasedComponent) lb.getNextSibling();
		if (d != null && d instanceof Div) {
			d =  (HtmlBasedComponent) d.getFirstChild();
			if (d != null && "deleteButton".equals(d.getSclass())) {
				d.setVisible(display);
			}
		}
	}
	
	public void multiSelect2(Event event) {
		DataTable lb = (DataTable) event.getTarget();
		displayRemoveButton2(lb, lb.getSelectedIndexes() != null && lb.getSelectedIndexes().length > 0);
	}

	public void deleteSelected2(Event event0) {
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
							displayRemoveButton2(lb, false);
						}
					});
		}
	}
	
	public void bulkAction(Event event) throws IOException, CommitException, InternalErrorException, NamingException, CreateException {
		DataTable listbox = (DataTable) getListbox();
		if (listbox.getSelectedIndexes() != null && listbox.getSelectedIndexes().length > 0) {
			BulkAction ba = new BulkAction( Role.class.getName() ); 
			ba.start(listbox ) ;
		}
	}

}
