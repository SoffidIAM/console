package com.soffid.iam.web.host;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.openejb.jee.jba.cmp.Datasource;
import org.zkoss.util.Dates;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.UiException;
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
import com.soffid.iam.api.AdministratorAuthorizationToAccessHost;
import com.soffid.iam.api.DomainValue;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.OUType;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.UserAccount;
import com.soffid.iam.service.ejb.ApplicationService;
import com.soffid.iam.service.ejb.ConfigurationService;
import com.soffid.iam.service.ejb.NetworkService;
import com.soffid.iam.service.ejb.UserService;
import com.soffid.iam.utils.DateUtils;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.DomainValueField;
import com.soffid.iam.web.component.DynamicColumnsDatatable;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.ObjectAttributesDiv;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.ImportCsvHandler;
import com.soffid.iam.web.popup.SelectColumnsHandler;

import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.component.DateFormats;
import es.caib.zkib.component.Wizard;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;


public class HostAclHandler extends Div {
	private static final long serialVersionUID = 1L;
	private String listboxPath;
	
	private UserService userService;
	private Role currentRole;
	private RoleAccount currentRoleAccount;
	
	public HostAclHandler() throws NamingException, CreateException {
		userService = EJBLocator.getUserService();
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
	

	public Window getWindowModify() {
		return (Window) getFellow("modify-window");
	}
	
	public void undoAdd(Event ev) {
		Window w = getWindowAdd();
		w.setVisible(false);
	}
	
	public DataTable getHostsListbox() {
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

	public void applyAdd(Event event) throws Exception {
		CustomField3 cf = (CustomField3) getWindowAdd().getFellow("newUser");
		CustomField3 cf2  = (CustomField3) getWindowAdd().getFellow("newExpirationDate");
		if (cf.validate() && cf2.validate()) {
			String userName = (String) cf.getValue();
			String fullName = (String) cf.getDescription(userName);
			String hostName = (String) XPathUtils.getValue((DataSource) getHostsListbox(), "name");
			
			AdministratorAuthorizationToAccessHost a = new AdministratorAuthorizationToAccessHost();
			a.setUserCode(userName);
			a.setUserName(fullName);
			a.setAuthorizationAccessExpirationDate( Calendar.getInstance());
			a.getAuthorizationAccessExpirationDate().setTime((Date)cf2.getValue());
			a.setRequestDate(Calendar.getInstance());
			a.setHostName(hostName);
			XPathUtils.createPath(getHostsListbox(), "/manager", a);
			try {
				getListbox().commit();
				getWindowAdd().setVisible(false);
			} catch (Exception e) {
				getListbox().delete();
				throw e;
			}
		}
	}

	public void backAndRollback(Event ev) {
		getWindowAdd().setVisible(false);
	}

	public void next(Event ev) {
		getWizard().next();
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
				{"userCode", Labels.getLabel("maquines.zul.Usuari-2")},
				{"authorizationAccessExpirationDate", Labels.getLabel("maquines.zul.DataCaducitat")}
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
			DataTable usersListbox = getHostsListbox();
			String userName = (String) XPathUtils.getValue((DataSource) usersListbox, "@name");

			String hostName = (String) XPathUtils.getValue((DataSource) getHostsListbox(), "name");

			NetworkService svc = EJBLocator.getNetworkService();
			for ( Iterator<Map<String, String>> iterator = parser.iterator(); iterator.hasNext(); )
			{
				m = iterator.next();
				String user = m.get("userCode");
				String authDate = m.get("authorizationAccessExpirationDate");

				if (user != null && !user.trim().isEmpty() && authDate != null && !authDate.trim().isEmpty())
				{
					AdministratorAuthorizationToAccessHost a = new AdministratorAuthorizationToAccessHost();
					a.setUserCode(user);
					a.setAuthorizationAccessExpirationDate( Calendar.getInstance());
					Date d = null;
					try {
						d = DateFormats.getDateTimeFormat().parse(authDate);
						a.getAuthorizationAccessExpirationDate().setTime(d);
					} catch (Exception e) {
						throw new UiException ("Unable to parse date "+authDate);
					}
					a.setRequestDate(Calendar.getInstance());
					a.setHostName(hostName);
					svc.create(a);
					inserts ++;
				}
			}
			DataNodeCollection coll = (DataNodeCollection) XPathUtils.getValue((DataSource)usersListbox, "/manager");
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

	public void setPermissions(Event event) {
		boolean support = false;
		try {
			String hostName = (String) XPathUtils.getValue((DataSource)getHostsListbox(), "name");
			String network = (String) XPathUtils.getValue((DataSource)getHostsListbox(), "networkCode");
			Long level = EJBLocator.getNetworkService().findAccessLevelByHostNameAndNetworkName(hostName, network);
			if (level != null && level.intValue() >= 1 /* support */)
				support = true;
		} catch (Exception e) {
		}
		getFellow("addbutton").setVisible(support);
		getFellow("addbutton2").setVisible(support);
		getFellow("importbutton").setVisible(support);
		getFellow("getPassword").setVisible(support);
	}
}
