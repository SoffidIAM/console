package com.soffid.iam.web.discovery;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.S;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.ComponentNotFoundException;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AccessTree;
import com.soffid.iam.api.AccessTreeExecution;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.Host;
import com.soffid.iam.api.HostPort;
import com.soffid.iam.api.JumpServerGroup;
import com.soffid.iam.api.Network;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.PasswordDomain;
import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.api.Server;
import com.soffid.iam.api.System;
import com.soffid.iam.api.UserDomain;
import com.soffid.iam.service.ejb.AccountService;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.SearchBox;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DataTree;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.component.Wizard;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathNotFoundException;
import es.caib.zkib.zkiblaf.Missatgebox;


public class DiscoveryHandler extends FrameHandler {

	public DiscoveryHandler() throws InternalErrorException {
		super();
	}

	public void updateTree(Event event) throws Exception {
		DataNodeCollection modelCollection = (DataNodeCollection) getModel().getValue("/network");
		boolean end = !modelCollection.isInProgress();
		Timer timer = (Timer) event.getTarget();
		timer.setDelay(1000);
		try {
			modelCollection.updateProgressStatus();
		} finally {
			if (end)
			{
				timer.stop();
			}
		}
	}

	@Override
	public void onChangeForm(Event ev) throws Exception {
		super.onChangeForm(ev);
		DataTree2 dt = (DataTree2) getListbox();
		try {
			Object o = XPathUtils.eval(getForm(), "instance");
			getFellow("networkDiv").setVisible(false);
			getFellow("hostDiv").setVisible(false);
			if (o instanceof Network ) {
				getFellow("networkDiv").setVisible(true);
				Boolean enabled = (Boolean) XPathUtils.eval(getForm(), "discovery");
				getFellow("notaskdiv").setVisible( ! Boolean.TRUE.equals(enabled));
				getFellow("taskdiv").setVisible( Boolean.TRUE.equals(enabled));
				getFellow("deleteAccountButton").setVisible(false);
			} else if (o instanceof Host){
				getFellow("hostDiv").setVisible(true);
			} else {
				if (getModel() != null)
					getModel().commit();
				
				try {
					displayRemoveButton(getListbox(), false);
				} catch (ComponentNotFoundException e) {} // Ignore
				
				getCard().setSclass ( "card" );
			}
		} catch (JXPathNotFoundException e) {}
	}

	public void onChangeServer(Event ev) throws Exception {
		Network n = (Network) XPathUtils.eval(getForm(), "instance"); 
		EJBLocator.getNetworkService().update(n);
		getModel().commit();
		onChangeForm(ev);
	}
	
	public void enableNetwork(Event ev) throws Exception {
		Network n = (Network) XPathUtils.eval(getForm(), "instance"); 
		EJBLocator.getNetworkDiscoveryService().enableNetworkDiscoveryScheduledTask(n);
		XPathUtils.setValue(getForm(), "discovery", true);
		getModel().commit();
		onChangeForm(ev);
	}

	@Override
	public void showDetails() {
		Object o = XPathUtils.eval(getForm(), "instance");
		if (o instanceof Network ) {
			super.showDetails();
		} else if (o instanceof Host){
			super.showDetails();
		} else if (o instanceof String[]) {
		}
	}
	
	public void addSystem(Event event) throws NamingException, CreateException, InternalErrorException, IOException {
		Window w = (Window) getFellow("add_system");
		Wizard wizard = (Wizard) w.getFellow("wizard");
		wizard.disableStep(1);
		wizard.disableStep(2);
		wizard.setSelected(0);
		w.doHighlighted();
		DataNode current = (DataNode) XPathUtils.eval(getForm(), "/.");
		Host host = (Host) ((DataNode) current.getParent()).getInstance();
		List<String> values = new LinkedList<String>();
		if ("NTS".equals( host.getOs())) {
			if (hasService("389/tcp") && hasService("88/tcp"))
				values.add("AD: Active directory");
			else {
				if (hasService("445/tcp"))
					values.add("Windows: Windows local accounts");
				if (hasService("389/tcp") || hasService("636/tcp"))
					values.add("LDAP: LDAP Server");
			}
		}
		else if ("LIN".equals( host.getOs()))  {
			if (hasService("22/tcp"))
				values.add("Linux: Linux local accounts");
			if (hasService("389/tcp") || hasService("636/tcp"))
				values.add("LDAP: LDAP Server");
		}
		else 
		{
			if (hasService("445/tcp"))
				values.add("Windows: Windows local accounts");
			if (hasService("389/tcp") || hasService("636/tcp"))
				values.add("LDAP: LDAP Server");
			if (hasService("22/tcp"))
				values.add("Linux: Linux local accounts");
		}
		if (hasService("3306/tcp"))
			values.add("MariaDB: MySql or MariaDB database");
		if (hasService("5432/tcp"))
			values.add("PostgreSQL: PostgreSQL database");
		if (hasService("1433/tcp"))
			values.add("SQLServer: SQL Server database");
		if (hasServiceDescription("Oracle TNS Listener"))
			values.add("Oracle: Oracle database");
		
		values.add("Other: Other");
		CustomField3 cf = (CustomField3) w.getFellow("type");
		cf.setListOfValues(values.toArray(new String[values.size()]));
		cf.setValue(null);
		cf.updateMetadata();
	}
	
	private boolean hasService(String port) {
		DataNode current = (DataNode) XPathUtils.eval(getForm(), "/.");
		DataNode host =  (DataNode) current.getParent();
		DataNodeCollection coll = (DataNodeCollection) host.getListModel("port");
		for (int i = 0; i < coll.size(); i++) {
			DataNode dn = (DataNode) coll.get(i);
			if (dn != null &&  !dn.isDeleted()) {
				HostPort hp = (HostPort) dn.getInstance();
				if (hp.getPort().equals(port))
					return true;
			}
		}
		return false;
	}

	private boolean hasServiceDescription(String port) {
		DataNode current = (DataNode) XPathUtils.eval(getForm(), "/.");
		DataNode host =  (DataNode) current.getParent();
		DataNodeCollection coll = (DataNodeCollection) host.getListModel("port");
		for (int i = 0; i < coll.size(); i++) {
			DataNode dn = (DataNode) coll.get(i);
			if (dn != null &&  !dn.isDeleted()) {
				HostPort hp = (HostPort) dn.getInstance();
				if (hp.getDescription().toLowerCase().contains(port.toLowerCase()))
					return true;
			}
		}
		return false;
	}

	public void undoAdd(Event event) {
		getFellow("add_system").setVisible(false);
	}
	
	public void addAccount(Event event) {
		Window w = (Window) getFellow("add_account");
		w.doHighlighted();
		Radio r = (Radio) w.getFellow("radio_new_account");
		r.setChecked(true);
		w.getFellow("div_new_account").setVisible(true);
		w.getFellow("div_existing_account").setVisible(false);
		((InputField3) w.getFellow("new_account")).setValue(null);
		((InputField3) w.getFellow("new_password")).setValue(null);
		((InputField3) w.getFellow("existing_account")).setValue(null);
	}

	public void setNewAccount(Event event) {
		Window w = (Window) getFellow("add_account");
		w.getFellow("div_new_account").setVisible(true);
		w.getFellow("div_existing_account").setVisible(false);
	}

	public void setExistingAccount(Event event) {
		Window w = (Window) getFellow("add_account");
		w.getFellow("div_new_account").setVisible(false);
		w.getFellow("div_existing_account").setVisible(true);
	}

	public void closeWindow(Event event) {
		Window w = (Window) event.getTarget().getSpaceOwner();
		w.setVisible(false);
	}

	public void applyAddAccount(Event event) throws Exception {
		Window w = (Window) getFellow("add_account");
		Radiogroup r = (Radiogroup) w.getFellow("radio");
		if (r.getSelectedIndex() == 0) // New account
		{
			InputField3 u = (InputField3) w.getFellow("new_account");
			InputField3 p = (InputField3) w.getFellow("new_password");
			if (u.attributeValidateAll() && p.attributeValidateAll()) {
				Account acc  = new Account();
				String ssoSystem = ConfigurationCache.getProperty("AutoSSOSystem"); //$NON-NLS-1$
				acc.setName("?");
				acc.setSystem(ssoSystem);
				acc.setDescription(u.getValue().toString());
				acc.setLoginName(u.getValue().toString());
				acc.setPasswordPolicy(ConfigurationCache.getProperty("AutoSSOPolicy"));
				acc.setType(AccountType.IGNORED);
				AccountService accountService = EJBLocator.getAccountService();
				acc = accountService.createAccount(acc);
				accountService.setAccountPassword(acc,(Password) p.getValue());
				XPathUtils.createPath(getForm().getDataSource(), "/account", acc);
				getModel().commit();
				w.setVisible(false);
			}
		} else {
			InputField3 accfield = (InputField3) w.getFellow("existing_account");
			if (accfield.attributeValidateAll()) {
				Account acc = (Account) accfield.getValueObject();
				if (acc != null) {
					XPathUtils.createPath(getForm().getDataSource(), "/account", acc);
					getModel().commit();
					w.setVisible(false);
				}
			}
		}
	}

	public void deleteAccount(Event event) throws Exception {
		DataTable dt = (DataTable) getFellow("networkAccounts");
		dt.deleteSelectedItem();
		getModel().commit();
		getFellow("deleteAccountButton").setVisible(false);
	}
	
	public void openAccounts(Event ev) {
		String system = (String) XPathUtils.eval(getListbox(), "name");
		Executions.getCurrent().sendRedirect("/resource/account/account.zul?filter=system eq \""+quote(system)+"\"", "_blank");
	}

	public void openSystem(Event ev) {
		String system = (String) XPathUtils.eval(getListbox(), "name");
		Executions.getCurrent().sendRedirect("/config/agents.zul?filter=name eq \""+quote(system)+"\"", "_blank");
	}


	private String quote(String system) {
		return system.replace("\\", "\\\\").replace("\"", "\\\"").replace("\'", "\\\'");
	}
	
	public void setSystemCredentials(Event event) {
		Window w = (Window) getFellow("add_system");
		CustomField3 cf = (CustomField3) w.getFellow("type");
		Wizard wizard = (Wizard) w.getFellow("wizard");
		final String type = (String) cf.getValue();
		if (type.equals("Other")) {
			DataModel model = (DataModel) w.getFellow("model");
			model.getVariables().declareVariable("className", System.class.getName());
			wizard.enableStep(1);
		} else {
			wizard.enableStep(2);
		}
		wizard.next();
		CustomField3 instance = (CustomField3) w.getFellow("instance");
		CustomField3 instanceUrl = (CustomField3) w.getFellow("instanceUrl");
		if ("Oracle".equalsIgnoreCase(type)) {
			DataNode dn = (DataNode) XPathUtils.eval(getForm(), "/");;
			DataNode parentNode = (DataNode) dn.getParent();
			Host h = (Host) parentNode.getInstance();

			instance.setVisible(false);
			instanceUrl.setVisible(true);
			instanceUrl.setValue("jdbc:oracle:thin:@"+ h.getName()+":1521:SID" );
			
			final DataModelCollection ports = parentNode.getListModel("port");
			for (int i = 0; i < ports.getSize(); i++) {
				DataNode portNode = (DataNode) ports.getDataModel(i);
				if (portNode != null && !portNode.isDeleted()) {
					String desc = (String) portNode.get("description");
					if (desc.toLowerCase().contains("oracle")) {
						String portNumber = (String) portNode.get("port");
						if (portNumber.contains("/"))
							portNumber = portNumber.substring(0, portNumber.indexOf("/"));
						instanceUrl.setValue("jdbc:oracle:thin:@"+ h.getName()+":"+portNumber+":SID" );
					}
				}
			}
		}
		else if ("Postgresql".equalsIgnoreCase(type) ||
				"SqlServer".equalsIgnoreCase(type)) {
			instance.setVisible(true);
			instanceUrl.setVisible(false);			
		} else {
			instance.setVisible(false);
			instanceUrl.setVisible(false);
		}
	}
	
	public void back(Event event) {
		Window w = (Window) getFellow("add_system");
		Wizard wizard = (Wizard) w.getFellow("wizard");
		wizard.previous();
		if (wizard.getSelected() == 0) {
			wizard.disableStep(1);
			wizard.disableStep(2);
		}
	}

	public void linkSystem(Event event) throws Exception {
		Window w = (Window) getFellow("add_system");
		Wizard wizard = (Wizard) w.getFellow("wizard");
		DataTable dt = (DataTable) w.getFellow("listbox");
		if (dt.getSelectedIndex() < 0) {
			Missatgebox.avis("Please, select a target system to link");
		}
		System s = (System) XPathUtils.eval(dt, "instance");
		DataNode dn = (DataNode) XPathUtils.eval(getForm(), "/");;
		DataNode parentNode = (DataNode) dn.getParent();
		Host h = (Host) parentNode.getInstance();
		EJBLocator.getNetworkDiscoveryService().registerHostSystem(h, s);
		w.setVisible(false);
		dn.getListModel("system").refresh();
		parentNode.getListModel("dispatcherHolder").refresh();
	}
	
	public void applyAddSystem(Event event) throws Exception {
		Window w = (Window) getFellow("add_system");
		Wizard wizard = (Wizard) w.getFellow("wizard");
		
		CustomField3 cfUser = (CustomField3) w.getFellow("loginName");
		final String userName = (String) cfUser.getValue();
		CustomField3 cfPassword = (CustomField3) w.getFellow("password");
		Password password = (Password) cfPassword.getValue();
		if (!cfUser.attributeValidateAll() || !cfPassword.attributeValidateAll())
			return;
		
		CustomField3 cf = (CustomField3) w.getFellow("type");
		CustomField3 instance = (CustomField3) w.getFellow("instance");
		CustomField3 instanceUrl = (CustomField3) w.getFellow("instanceUrl");

		String type = (String) cf.getValue();
		String instanceName = null;
		if (instance.isVisible()) instanceName = (String) instance.getValue();
		if (instanceUrl.isVisible()) instanceName = (String) instanceUrl.getValue();
		
		DataNode dn = (DataNode) XPathUtils.eval(getForm(), "/");;
		DataNode parentNode = (DataNode) dn.getParent();
		Host h = (Host) parentNode.getInstance();

		System system = EJBLocator.getNetworkDiscoveryService().createSystemCandidate(h, type, userName, password, instanceName);
		
		Thread.sleep(100);
		
		try {
			EJBLocator.getDispatcherService().checkConnectivity( system.getName() );
		} catch (Exception e) {
			EJBLocator.getDispatcherService().delete(system);
			throw e;
		}
		w.setVisible(false);
		dn.getListModel("system").refresh();
		parentNode.getListModel("dispatcherHolder").refresh();
		
		for (ScheduledTask task: EJBLocator.getScheduledTaskService().listTasks()) {
			if (task.getHandlerName().equals("system:reconcile-dispatcher") &&
					task.getParams().equals(system.getId().toString())) {
				EJBLocator.getScheduledTaskService().startNow(task);
			}
		}
	}
	
	public void addEntryPoint(Event event) throws Exception {
		List<String> types = new LinkedList();
		DataNode entryPointHolder = (DataNode) XPathUtils.eval(getListbox(), "/.");
		DataNodeCollection ports = (DataNodeCollection) entryPointHolder.getParent().getListModel("port"); 
		for (int i = 0; i < ports.size(); i++) {
			DataNode node = (DataNode) ports.get(i);
			if (node != null) {
				HostPort port = (HostPort) node.getInstance();
				if (port.getPort().equals("22/tcp"))
					types.add("ssh: Secure shell (ssh)");
				else if (port.getPort().equals("80/tcp")) 
					types.add("http: Web application (http)");
				else if (port.getPort().equals("443/tcp")) 
					types.add("https: Secure web application (https)");
				else if (port.getPort().equals("3389/tcp")) 
					types.add("rdp: Remote Desktop Protocol (rdp)");
			}
		}
		if (types.isEmpty()) {
			Missatgebox.avis(Labels.getLabel("discovery.noService"));
		} else {
			Window w = (Window) getFellow("add_entrypoint");
			w.doHighlighted();
			DataNodeCollection tree = (DataNodeCollection) getModel().getValue("/app");
			tree.refresh();
			CustomField3 type = (CustomField3) w.getFellow("type");
			type.setListOfValues(types.toArray(new String[types.size()]));
			type.setValue(null);
			if (types.size() == 1) {
				String t = types.get(0);
				if (t.contains(":"))
					t = t.substring(0, t.indexOf(":")).trim();
				type.setValue(t);
			}
			type.updateMetadata();
		}
	}

	public void applyAddEntryPoint(Event event) throws Exception {
		Window w = (Window) getFellow("add_entrypoint");
		CustomField3 type = (CustomField3) w.getFellow("type");
		if (type.attributeValidateAll() ) {
			DataTree2 tree = (DataTree2) w.getFellow("listbox");
			if (tree.getSelectedItem() == null || tree.getSelectedItem().length == 0) {
				type.setWarning(0, "Please, select a menu item");
			} else {
				String typeValue = (String) type.getValue();
				DataNode entryPointHolder = (DataNode) XPathUtils.eval(getListbox(), "/.");
				Host host = (Host) ((DataNode) entryPointHolder.getParent()).getInstance(); 
				AccessTree parent = (AccessTree) XPathUtils.eval(tree, "instance");
				AccessTree newAccessTree = new AccessTree();
				newAccessTree.setParentId(parent.getId());
				newAccessTree.setName(host.getDescription()+" ("+typeValue+")");
				newAccessTree.setMenu(false);
				newAccessTree.setSystem(guessSystem(host, typeValue));
				newAccessTree.setPublicAccess(false);
				newAccessTree.setExecutions(new LinkedList<AccessTreeExecution>());
				newAccessTree = EJBLocator.getEntryPointService().create(newAccessTree);
				String url ;
				url = typeValue+"://"+host.getName();
				for (String scope: new String[] {"L", "W", "I"}) {
					AccessTreeExecution exec = new AccessTreeExecution();
					exec.setExecutionTypeCode("PAM");
					exec.setContent("url="+url+"\nserverGroup="+getDefaultJumpServer()); //$NON-NLS-1$
					exec.setScope(scope);
					exec.setAccessTreeId(newAccessTree.getId());
					EJBLocator.getEntryPointService().createExecution(newAccessTree, exec);
					newAccessTree.getExecutions().add(exec);
				}
				EJBLocator.getNetworkDiscoveryService().registerHostEntryPoint(host, newAccessTree);
				Executions.getCurrent().sendRedirect("/resource/menu/menu.zul?filter=id eq "+newAccessTree.getId(), "_blank");
				w.setVisible(false);
				entryPointHolder.getListModel("entryPoint").refresh();
			}
		}
	}

	private String getDefaultJumpServer() {
		try {
			for (JumpServerGroup sg: EJBLocator.getPamSessionService().findJumpServerGroups())
				return sg.getName();
		} catch (Exception e) {
		}
		return "default";
	}

	private String guessSystem(Host host, String typeValue) throws InternalErrorException, NamingException, CreateException {
		List<System> systems = EJBLocator.getNetworkDiscoveryService().findHostSystems(host);
		if (systems.isEmpty()) return null;
		if (systems.size() == 1)
			return systems.get(0).getName();
		for (System system: systems) {
			if (system.getClassName().toLowerCase().contains("windows") &&
					typeValue.equals("rdp"))
				return system.getName();
			if (system.getClassName().toLowerCase().contains("windows") &&
					typeValue.equals("rdp"))
				return system.getName();
		}
		return systems.get(0).getName();
	}

	@Override
	public void afterCompose() {
		super.afterCompose();
		HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		String wizard = req.getParameter("wizard");
		if ("new".equals(wizard)) 
			Executions.createComponents("/resource/network/wizard-network.zul", this, new HashMap<>());
	}
}
