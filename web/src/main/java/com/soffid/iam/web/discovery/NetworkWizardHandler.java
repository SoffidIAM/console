package com.soffid.iam.web.discovery;

import java.net.InetAddress;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TimeZone;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.Network;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.api.Server;
import com.soffid.iam.service.ejb.AccountService;
import com.soffid.iam.service.ejb.NetworkDiscoveryService;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.web.component.AttributeSearchBox;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.SearchBox;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.ServerType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.component.Wizard;
import es.caib.zkib.datamodel.DataModelNode;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.zkiblaf.Missatgebox;

public class NetworkWizardHandler extends Window implements AfterCompose{

	private Wizard wizard;
	private CustomField3 description;
	private CustomField3 ip;
	private CustomField3 mask;
	DiscoveryHandler frame;
	LinkedList<Account> accounts = new LinkedList<>();
	private DataTable networkAccounts;

	@Override
	public void afterCompose() {
		frame = (DiscoveryHandler) getParent().getFellow("frame");
		wizard = (Wizard) getFellow("wizard");
		description = (CustomField3) getFellow("description");
		ip = (CustomField3) getFellow("ip");
		mask = (CustomField3) getFellow("mask");
		networkAccounts = (DataTable) getFellow("networkAccounts");
		doHighlighted();
	}
	
	public void back(Event ev) {
		if (wizard.getSelected() == 0)
			detach();
		else
			wizard.previous();
	}
	
	public void next(Event ev) throws Exception {
		switch (wizard.getSelected()) {
		case 0:
			if (validateNetwork())
				wizard.next();
			break;
		case 1:
			startDiscovery();
			break;
		case 2:
			detach();
		}
	}

	private void startDiscovery() throws Exception {
		Network n = createNetwork();

		final NetworkDiscoveryService networkDiscoveryService = EJBLocator.getNetworkDiscoveryService();
		if ( Boolean.FALSE.equals(n.getDiscovery()))
			networkDiscoveryService.enableNetworkDiscoveryScheduledTask(n);

		for (Account account: accounts) {
			networkDiscoveryService.createNetworkAccount(n, account);
		}
		ScheduledTask st = networkDiscoveryService.findNetworkDiscoveryScheduledTask(n);
		EJBLocator.getScheduledTaskService().startNow(st);

		DataModel dm = (DataModel) frame.getFellow("model");
		DataTree2 tree = (DataTree2) frame.getFellow("listbox");
		final DataNodeCollection list = (DataNodeCollection)dm.getJXPathContext().getValue("/network");
		list.refresh();
		for (int i = 0; i < list.size(); i++) {
			DataNode node = (DataNode) list.getDataModel(i);
			if (node != null) {
				Network n2 = (Network) node.getInstance();
				if (n2.getName().equals(n.getName())) {
					tree.setSelectedIndex(new int[] {i});
					frame.showDetails();
				}
			}
		}
		wizard.next();
	}

	private Network createNetwork() throws InternalErrorException, NamingException, CreateException {
		Network n = new Network();
		n.setDescription(description.getValue().toString());
		n.setDhcpSupport(true);
		n.setDiscovery(false);
		n.setDiscoveryServer(null);
		for (Server server: EJBLocator.getDispatcherService().findAllServers()) {
			if (server.getType() == ServerType.PROXYSERVER)
				n.setDiscoveryServer(server.getName());
		}
		n.setIp(ip.getValue().toString());
		int number = 1;
		for (Network n2: EJBLocator.getNetworkService().findNetworkByTextAndJsonQuery(null, null, null, null).getResources()) {
			if (n2.getName().startsWith("net")) {
				try {
					int number2 = Integer.parseInt(n2.getName().substring(3));
					if (number2 >= number) number = number2 + 1;
				} catch (NumberFormatException e) {}
			}
			if (n2.getIp().equals(n.getIp())) {
				n = n2;
			}
		}
		n.setMask(mask.getValue().toString());
		if (n.getName() == null) {
			n.setName("net"+number);
			n = EJBLocator.getNetworkService().create(n);
		} else {
			EJBLocator.getNetworkService().update(n);
		}
		return n;
	}

	private boolean validateNetwork() throws Exception {
		if (description.attributeValidateAll() && ip.attributeValidateAll() && mask.attributeValidateAll()) {
			InetAddress address = InetAddress.getByName(mask.getValue().toString());
			byte[] b = address.getAddress();
			if (b[b.length-2] != -1) {
				mask.setWarning(0, Labels.getLabel("wizard-network.alert0"));
				Missatgebox.avis(Labels.getLabel("wizard-network.alert1"));
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	final TimeZone utcTimeZone = TimeZone.getTimeZone("GMT");

	public void end(Event e) {
		SearchBox sb = (SearchBox) getParent().getFellow("searchBox");
		sb.setBasicMode();
		AttributeSearchBox att = sb.addAttribute("name");
		sb.search();
		detach();
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
	
	public void deleteAccount(Event event) throws Exception {
		DataTable dt = (DataTable) getFellow("networkAccounts");
		for (int i: dt.getSelectedIndexes()) {
			accounts.set(i, null);
		}
		for (Iterator<Account> it = accounts.iterator(); it.hasNext();) 
			if (it.next() == null)
				it.remove();
		getFellow("deleteAccountButton").setVisible(false);
		updateNetworkAccounts();
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
				accounts.add(acc);
				updateNetworkAccounts();
				w.setVisible(false);
			}
		} else {
			InputField3 accfield = (InputField3) w.getFellow("existing_account");
			if (accfield.attributeValidateAll()) {
				Account acc = (Account) accfield.getValueObject();
				if (acc != null) {
					accounts.add(acc);
					updateNetworkAccounts();
					w.setVisible(false);
				}
			}
		}
	}

	private void updateNetworkAccounts() {
		JSONArray array = new JSONArray();
		for (Account account: accounts) {
			JSONObject o = new JSONObject();
			o.put("loginName", account.getLoginName());
			array.put(o);
		}
		networkAccounts.setData(array);
	}


}
