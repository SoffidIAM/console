package com.soffid.iam.web.user;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.DomainValue;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.OUType;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.UserAccount;
import com.soffid.iam.service.ejb.ApplicationService;
import com.soffid.iam.service.ejb.UserService;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.DomainValueField;
import com.soffid.iam.web.component.DynamicColumnsDatatable;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.ObjectAttributesDiv;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.ImportCsvHandler;
import com.soffid.iam.web.popup.SelectColumnsHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DateFormats;
import es.caib.zkib.component.Wizard;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;


public class UserRolesHandler extends Div implements AfterCompose {
	private static final long serialVersionUID = 1L;
	private String listboxPath;
	
	private UserService userService;
	private Role currentRole;
	private RoleAccount currentRoleAccount;
	
	public UserRolesHandler() throws NamingException, CreateException {
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
			DataTable usersListbox = getUsersListbox();
			String userName = (String) XPathUtils.getValue((DataSource) usersListbox, "@userName");
			String fullName = (String) XPathUtils.getValue((DataSource) usersListbox, "@fullName");

			if (currentRole != null) {
				currentRoleAccount = new RoleAccount();
				currentRoleAccount.setUserCode(userName);
				currentRoleAccount.setUserFullName(fullName);
				currentRoleAccount.setCertificationDate(new Date());
				currentRoleAccount.setStartDate(new Date());
				currentRoleAccount.setEnabled(true);
				currentRoleAccount.setInformationSystemName(currentRole.getInformationSystemName());
				currentRoleAccount.setRoleDescription(currentRole.getDescription());
				currentRoleAccount.setRoleName(currentRole.getName());
				currentRoleAccount.setSystem(currentRole.getSystem());
				
				DataNodeCollection coll = (DataNodeCollection) XPathUtils.getValue((DataSource)usersListbox, "/role");
				coll.add(currentRoleAccount);
				coll.setActiveNode(coll.getDataModel(coll.getSize()-1));
				getWizard().next();
				if (currentRole.getDomain() == null) {
					getWizard().next();
					setProperties(ev);
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
		guessAccountName();
		guessHolderGroups();
	}


	private void guessHolderGroups() throws InternalErrorException, NamingException, CreateException {
		List<String> groups = new LinkedList<>();
		DataSource usersListbox = getUsersListbox();
		String primaryGroup = (String) XPathUtils.getValue(usersListbox, "@primaryGroup");
		String userName = (String) XPathUtils.getValue(usersListbox, "@primaryGroup");
		
		if (isHolderGroup(primaryGroup))
		{
			Group gr = EJBLocator.getGroupService().findGroupByGroupName(primaryGroup);
			if (gr != null)
				groups.add(gr.getName()+" : " +gr.getName()+" - " + gr.getDescription());
		}
		
		for (GroupUser og: EJBLocator.getGroupService().findUsersGroupByUserName(userName))
		{
			if (isHolderGroup(og.getGroup()))
				groups.add(og.getGroup()+" : " +og.getGroup()+" - " + og.getGroupDescription());
		}
		
		CustomField3 field = (CustomField3) getWindowAdd().getFellow("holderGroup");
		if (groups.isEmpty()) {
			field.setVisible(false);
		} else {
			field.setValues(groups);
			field.setVisible(true);
		}
		field.invalidate();
	}

	private boolean isHolderGroup(String groupName) throws InternalErrorException, NamingException, CreateException {
		Group gr = EJBLocator.getGroupService().findGroupByGroupName(groupName);
		if (gr.getType() != null) {
			for (OUType type: EJBLocator.getOrganizationalUnitTypeService().findOUTypeByFilter(gr.getType(), null)) {
				if (type.isRoleHolder())
					return true;
			}
		}
		return false;
	}

	private void guessAccountName() throws InternalErrorException, NamingException, CreateException {
		DataTable usersListbox = getUsersListbox();
		String userName = (String) XPathUtils.getValue((DataSource) usersListbox, "@userName");

		List<String> accounts = new LinkedList<>();
		for (UserAccount account: EJBLocator.getAccountService().findUsersAccounts(userName, currentRole.getSystem()))
			accounts.add(account.getName());
		
		CustomField3 field = (CustomField3) getWindowAdd().getFellow("account");
		if (accounts.isEmpty())
		{
			String accountName = EJBLocator.getAccountService().guessAccountName(userName, currentRole.getSystem());
			field.setListOfValues((String[])null);
			field.setValue(accountName);
			field.setReadonly(true);
			field.updateMetadata();
		} else {
			field.setListOfValues(accounts.toArray(new String[0]));
			field.setValue(accounts.get(0));
			field.setReadonly(false);
			field.updateMetadata();
		}
	}

	public DataTable getUsersListbox() {
		return (DataTable) Path.getComponent(listboxPath);
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
			DataTable usersListbox = getUsersListbox();
			String userName = (String) XPathUtils.getValue((DataSource) usersListbox, "@userName");
			String fullName = (String) XPathUtils.getValue((DataSource) usersListbox, "@fullName");

			for (String domain: domains) {
				RoleAccount ra = new RoleAccount(currentRoleAccount);
				DomainValue dv = new DomainValue();
				dv.setDomainName(currentRole.getDomain());
				dv.setExternalCodeDomain(currentRole.getInformationSystemName());
				dv.setValue(domain);
				ra.setDomainValue(dv);
				DataNodeCollection coll = (DataNodeCollection) XPathUtils.getValue((DataSource)usersListbox, "/role");				
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
	
	public void changeColumns(Event event) throws IOException {
		SelectColumnsHandler.startWizard((DynamicColumnsDatatable) getListbox());
	}

	public void downloadCsv(Event event) {
		Component lb = getListbox();
		if (lb instanceof DataTable)
			((DataTable) lb).download();
	}
	
	public void importCsv () throws IOException, CommitException {
		DataSource model = (DataSource) Path.getComponent(listboxPath);
		model.commit();
		
		String[][] data = { 
				{"system", Labels.getLabel("usuaris.zul.Bbdd")},
				{"roleName", Labels.getLabel("usuaris.zul.Rol")},
				{"domainValue/value", Labels.getLabel("aplica_usuarisRolllista.zul.DescripciadeDomini")},
				{"accountName", Labels.getLabel("usuaris.zul.Account")},
				{"startDate", Labels.getLabel("usuaris.zul.startDate")},
				{"endDate", Labels.getLabel("usuaris.zul.endDate")},
				{"holderGroup", Labels.getLabel("usuaris.zul.holderGroup")}
		};
		
		String title = Labels.getLabel("tenant.zul.import");
		ImportCsvHandler.startWizard(title, data, this, 
				parser -> importCsv(parser));
	}

	private void importCsv(CsvParser parser) {
		Map<String,String> m = null;
		int updates = 0;
		int inserts = 0;
		int unchanged = 0;
		int removed = 0;
		try {
			DataTable usersListbox = getUsersListbox();
			String userName = (String) XPathUtils.getValue((DataSource) usersListbox, "@userName");

			ApplicationService appSvc = EJBLocator.getApplicationService();
			for ( Iterator<Map<String, String>> iterator = parser.iterator(); iterator.hasNext(); )
			{
				m = iterator.next();
				String system = m.get("system");
				String roleName = m.get("roleName");
				String domainValue = m.get("domainValue/value");
				String accountName = m.get("accountName");
				String startDate = m.get("startDate");
				String endDate = m.get("endDate");
				String holderGroup = m.get("holderGroup");

				if (roleName != null && !roleName.trim().isEmpty())
				{
					currentRole = EJBLocator.getApplicationService().findRoleByNameAndSystem(roleName, system);

					currentRoleAccount = new RoleAccount();
					currentRoleAccount.setUserCode(userName);
					currentRoleAccount.setCertificationDate(new Date());
					if (startDate == null || startDate.trim().isEmpty())
						currentRoleAccount.setStartDate(new Date());
					else {
						try {
							Date start = DateFormats.getDateFormat().parse(startDate);
							currentRoleAccount.setStartDate(start);
						} catch (ParseException e) {
							throw new InternalErrorException("Wrong date "+startDate, e);
						}
					}
					if (endDate != null && !endDate.trim().isEmpty()) {
						try {
							Date end = DateFormats.getDateFormat().parse(endDate);
							currentRoleAccount.setEndDate(end);
						} catch (ParseException e) {
							throw new InternalErrorException("Wrong date "+endDate, e);
						}
					}
					currentRoleAccount.setEnabled(true);
					currentRoleAccount.setRoleName(currentRole.getName());
					currentRoleAccount.setSystem(currentRole.getSystem());
					if (accountName != null && ! accountName.trim().isEmpty())
						currentRoleAccount.setAccountName(accountName);
					DomainValue dv = new DomainValue();
					dv.setDomainName(currentRole.getDomain());
					dv.setExternalCodeDomain(currentRole.getInformationSystemName());
					dv.setValue(domainValue);
					currentRoleAccount.setDomainValue(dv);

					appSvc.create(currentRoleAccount);
					inserts ++;
				}
			}
			DataNodeCollection coll = (DataNodeCollection) XPathUtils.getValue((DataSource)usersListbox, "/role");
			coll.refresh();
		} catch (UiException e) {
			throw e;
		} catch (Exception e) {
			if (m == null)
				throw new UiException(e);
			else
				throw new UiException("Error loading parameter "+m.get("name"), e);
		}
		
		
		Missatgebox.avis(Labels.getLabel("parametres.zul.import", new Object[] { updates, inserts, removed, unchanged }));
	}

}
