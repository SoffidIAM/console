package com.soffid.iam.web.account;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.Host;
import com.soffid.iam.api.HostService;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.SoffidObjectType;
import com.soffid.iam.api.System;
import com.soffid.iam.api.UserType;
import com.soffid.iam.api.VaultElement;
import com.soffid.iam.service.ejb.ApplicationService;
import com.soffid.iam.service.ejb.AuthorizationService;
import com.soffid.iam.service.ejb.ConfigurationService;
import com.soffid.iam.service.ejb.DispatcherService;
import com.soffid.iam.sync.engine.intf.GetObjectResults;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.BulkAction;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.DynamicColumnsDatatable;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.ObjectAttributesDiv;
import com.soffid.iam.web.component.SearchBox;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.ImportCsvHandler;
import com.soffid.iam.web.popup.SelectColumnsHandler;
import com.soffid.iam.web.user.UserBulkAction;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;

public class AccountHandler extends FrameHandler {
	public AccountHandler() throws InternalErrorException {
		com.soffid.iam.api.Tenant masterTenant = com.soffid.iam.ServiceLocator.instance().getTenantService().getMasterTenant();

	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
	}
		

	public void onChangeForm(Event ev) throws Exception {
		super.onChangeForm(ev);
		updateStatus();
		try {
			DataNodeCollection coll = (DataNodeCollection) XPathUtils.eval(getForm(), "/services");
			getFellow("servicesSection").setVisible(!coll.isEmpty());
		} catch (Exception e) {}
	}
	
	public void importCsv () throws IOException, CommitException {
		getModel().commit();
		
		String[][] data = { 
				{"name", Labels.getLabel("parametres.zul.Parametre-2")},
				{"networkName", Labels.getLabel("parametres.zul.Xarxa-2")},
				{"value", Labels.getLabel("parametres.zul.Valor-2")},
				{"description", Labels.getLabel("parametres.zul.Descripcia-2")}
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
/*			for ( Iterator<Map<String, String>> iterator = parser.iterator(); iterator.hasNext(); )
			{
				m = iterator.next();
				String name = m.get("name");
				String network = m.get("networkName");
				String description = m.get("description");
				String value = m.get("value");
				if (network != null && network.isEmpty()) network = null;

				if (name != null && !name.trim().isEmpty() && m.containsKey("value"))
				{
					Configuration cfg = configSvc.findParameterByNameAndNetworkName(name, network);
					if (cfg != null)
					{
						if (value == null) {
							configSvc.delete(cfg);
							removed ++;
						}
						else if (cfg.getValue() != null && cfg.getValue().equals(value))
						{
							unchanged ++;
						} else {
							cfg.setValue(value);
							if (m.containsKey("description"))
								cfg.setDescription(description);
							configSvc.update(cfg);
							updates ++;
						}
					} else if (value != null) {
						inserts ++;
						cfg = new Configuration();
						cfg.setValue(value);
						cfg.setDescription(description);
						cfg.setName(name);
						cfg.setNetworkCode(network);
						configSvc.create(cfg);
					}
				}
			}
*/		} catch (UiException e) {
			throw e;
		} catch (Exception e) {
			if (m == null)
				throw new UiException(e);
			else
				throw new UiException("Error loading parameter "+m.get("name"), e);
		}
		
		getModel().refresh();
		Missatgebox.avis(Labels.getLabel("parametres.zul.import", new Object[] { updates, inserts, removed, unchanged }));
	}
	
	public void changeColumns(Event event) throws IOException {
		SelectColumnsHandler.startWizard((DynamicColumnsDatatable) getListbox());
	}
	
	public void addNew() throws Exception {
		super.addNew();
	}

	@Override
	public void afterCompose() {
		super.afterCompose();
		SearchBox sb = (SearchBox) getFellow("searchBox");
		HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		String user = req.getParameter("name");
		String system = req.getParameter("system");
		String id = req.getParameter("id");
		if (user != null && system != null) {
			sb.setBasicMode();
			sb.addAttribute("name").setSearchFilter(user);
			sb.addAttribute("system").setSearchFilter(system);
			sb.search();
		} else if (id != null) {
			sb.setBasicMode();
			sb.addAttribute("id").setSearchFilter(id);
			sb.search();
		} else {
			HashSet<String> s = new HashSet<String>();
			s.add("P");
			s.add("S");
			s.add("I");
			
			sb.addAttribute("type").setSelectedValues(s);
		}

	}

	public void setPassword(Event event) {
		DataSource listbox = (DataSource) getListbox();
		Account acc = (Account) listbox.getJXPathContext().getValue("instance");
		try {
			for (UserType ut: EJBLocator.getUserDomainService().findAllUserType()) {
				if (ut.getName().equals(acc.getPasswordPolicy())) {
					if (ut.isUnmanaged())
					{
						Missatgebox.avis( String.format("Warning. The account policy [%s] is marked as unmanaged. The password will not be sent to the target system", 
								ut.getDescription()),
								(ev2) -> doPasswordChange() );
						return;
					}
				}
			}
		} catch (Exception e) {
			// Ignore errors due to lack of permissions
		}
		try {
			System system = EJBLocator.getDispatcherService().findDispatcherByName(acc.getSystem());
			System soffid = EJBLocator.getDispatcherService().findSoffidDispatcher();
			if (system == null || ! system.getName().equals(soffid.getName())) {
				
				if (system == null ||
						system.getUrl() == null ||
						system.getUrl().trim().isEmpty())
				{
					Missatgebox.avis( 
							String.format("Warning: The system [%s] is disconnected. The password will not be sent to the target system",system.getDescription()),
							(ev2) -> doPasswordChange() );
					return;
				}
				else if ( system.isReadOnly() ) {
					Missatgebox.avis( String.format("Warning: The system [%s] is in read-only mode. The password will not be sent to the target system",system.getDescription()),
							(ev2) -> doPasswordChange() );
					return;
				}
			}
		} catch (Exception e) {
			// Ignore errors due to lack of permissions
		}
		if (acc.isDisabled()) {
			Missatgebox.avis("The account is disabled. The password cannot be changed yet");
		} else if ( acc.getType() == AccountType.IGNORED ) {
			Missatgebox.avis("Warning: The account is unmanaged. The password will not be sent to the target system",
					(ev2) -> doPasswordChange() );
			return;
		} else {
			doPasswordChange();
		}
	}

	public void doPasswordChange() {
		Window w = (Window) getFellow("newPassword2");
		Radiogroup gt = (Radiogroup) w.getFellow("generationType");
		Radio radioRandom = (Radio) w.getFellow("generationRandom");
		gt.setSelectedItem(radioRandom);
		onChangeSelectedGeneration(null);
		w.doHighlighted();
	}
	
	public void onCancelPassword(Event event) {
		Window w = (Window) getFellow("newPassword2");
		Textbox p = (Textbox) w.getFellow("password");
		p.setValue("");
		w.setVisible(false);
		if (event != null) event.stopPropagation();
	}
	
	public void onSetPassword(Event event) throws CommitException, WrongValueException, InternalErrorException, BadPasswordException, NamingException, CreateException
	{
		DataTable listbox = (DataTable) getListbox();
		listbox.commit();

		Window w = (Window) getFellow("newPassword2");
		Radiogroup gt = (Radiogroup) w.getFellow("generationType");
		Radio radioRandom = (Radio) w.getFellow("generationRandom");

		Account account = (Account) ((DataNode)XPathUtils.getValue(getListbox(), "/")).getInstance();
		
		if (gt.getSelectedItem() != radioRandom)
		{
			Textbox password = (Textbox) w.getFellow("password");
			EJBLocator.getAccountService().setAccountPassword(account, new Password( password.getValue()) );
			es.caib.zkib.zkiblaf.Missatgebox
					.avis(org.zkoss.util.resource.Labels
							.getLabel("accounts.setPassword.msg"));
		}
		else
		{
			Password nouPassword =  EJBLocator.getAccountService().generateAccountPassword(account);
			showPasswordAssist(nouPassword.getPassword());
		}

		onCancelPassword(event );
	}

	public void onChangeSelectedGeneration (Event event)
	{
		Window w = (Window) getFellow("newPassword2");
		Radiogroup generationType = (Radiogroup) w.getFellow("generationType");
		Radio radioRandom = (Radio) w.getFellow("generationRandom");
		Textbox password = (Textbox) w.getFellow("password");
		Div passworddiv = (Div) w.getFellow("passworddiv");
		Button setButton = (Button) w.getFellow("setButton");
		
		if (generationType.getSelectedItem() != radioRandom)
		{
			password.setDisabled (false);
			password.setFocus(true);
			passworddiv.setStyle("visibility: visible");
		} else {
			password.setSclass("text");
			password.setDisabled (true);
			password.invalidate();
			setButton.setFocus(true);
			passworddiv.setStyle("visibility: hidden");
		}
	}
	
	public void onCancelDisplayPassword(Event event)
	{
		Window wnd = (Window) getFellow("displayNewPassword");
		Label popupPwd = (Label) wnd.getFellow("popupPwd");
		Label passwordValue = (Label) wnd.getFellow("passwordValue");
		passwordValue.setValue("");
		popupPwd.setValue("");
		wnd.setVisible(false);
		if (event != null)
			event.stopPropagation();
	}
	
	void showPasswordAssist (String pwd) {
		String pwdMsg = "";
		
		Window wnd = (Window) getFellow("displayNewPassword");
		Label popupPwd = (Label) wnd.getFellow("popupPwd");
		Label passwordValue = (Label) wnd.getFellow("passwordValue");
		
		// Check valid password
		if ((pwd != null) && (pwd.length() != 0)) 
		{
			for (int i = 0; i < pwd.length(); i++) 
			{
				pwdMsg = pwdMsg + pwd.charAt(i) + "\t";
				Character c = pwd.charAt(i);
				if (c.isUpperCase(c))
				{
					pwdMsg = pwdMsg + org.zkoss.util.resource.Labels
							.getLabel("selfService.Majuscula");
				}
				
				if (c.isLowerCase(c))
				{
					pwdMsg = pwdMsg + org.zkoss.util.resource.Labels
							.getLabel("selfService.Minuscula");
				}
				
				if (c.isDigit(c))
				{
					pwdMsg = pwdMsg + org.zkoss.util.resource.Labels
							.getLabel("selfService.Number");
				}
				
				if (!c.isLetter(c) && !c.isDigit(c))
				{
					pwdMsg = pwdMsg + org.zkoss.util.resource.Labels
							.getLabel("selfService.Symbol");
				}
				
				pwdMsg = pwdMsg + "\n";
			}
			
			popupPwd.setValue(pwdMsg);
		}
		
		else
		{
			popupPwd.setValue("");
		}
		passwordValue.setValue( pwd );
		
		Account account = (Account) ((DataNode)XPathUtils.getValue(getListbox(), "/")).getInstance();

		((Label)wnd.getFellow("lbInfoNouPass")).setValue( String.format(org.zkoss
				.util.resource.Labels.getLabel("canviPassword.CanviPasswordDomini"),
				new Object [] {account.getSystem()}) );
		wnd.setTitle(String.format(org.zkoss.util.resource.Labels
				.getLabel("canviPassword.NouPasswordDomini"),
				new Object [] {account.getSystem()}));
		wnd.doHighlighted();
	}
	
	public void updateStatus() {
		Image i = (Image) getFellow("pendingChanges");
		Component lb = getListbox();
		if ( lb instanceof DataTable && ((DataTable) lb).getSelectedIndex() >= 0) {
			Long id = (Long) XPathUtils.eval(getForm(), "id");
			getFellow("tabroles").setVisible(id != null);
			getFellow("tabeffectiveroles").setVisible(id != null);

			Object instance = ((DataNode)XPathUtils.getValue(getForm(), "/")).getInstance();
			Account account = (Account) instance;
			try {
				i.setVisible(EJBLocator.getAccountService().isUpdatePending(account));
			} catch (Exception e) {
				i.setVisible(false);
			}
		} else if ( lb instanceof DataTree2 && ((DataTree2) lb).getSelectedItemXPath() != null ) {
			Long id = (Long) XPathUtils.eval(getForm(), "id");
			getFellow("tabroles").setVisible(id != null);
			getFellow("tabeffectiveroles").setVisible(id != null);
			Object instance = ((DataNode)XPathUtils.getValue(lb, "/.")).getInstance();
			if (instance instanceof VaultElement &&
					"account".equals(((VaultElement) instance).getType())) {
				Account account = (Account) ((VaultElement)instance).getAccount();
				try {
					i.setVisible(EJBLocator.getAccountService().isUpdatePending(account));
				} catch (Exception e) {
					i.setVisible(false);
				}
			} else {
				i.setVisible(false);
			}
		} else {
			i.setVisible(false);
		}
	}

	@Override
	public boolean applyNoClose(Event event) throws CommitException {
		boolean b = super.applyNoClose(event);
		updateStatus();
		return b;
	}

	public void bulkAction(Event event) throws IOException, CommitException, InternalErrorException, NamingException, CreateException {
		DataTable listbox = (DataTable) getListbox();
		if (listbox.getSelectedIndexes() != null && listbox.getSelectedIndexes().length > 0) {
			BulkAction ba = new AccountBulkAction( ) ; 
			ba.setSearchBox ( (SearchBox) getFellow("searchBox") );
			ba.start(listbox ) ;
		}
	}

	public String stringify (Object obj, String indent)
	{
		if (obj == null) return "";
		
		if (obj instanceof java.util.Calendar)
		{
			return new java.text.SimpleDateFormat(org.zkoss.util.resource.Labels.getLabel("selfService.Format"))
					.format(((Calendar)obj).getTime());
		}
		if (obj instanceof java.util.Date)
		{
			return new java.text.SimpleDateFormat(org.zkoss.util.resource.Labels.getLabel("selfService.Format"))
					.format((Date)obj);
		}
		if (obj instanceof java.util.Collection)
		{
			String r = "[";
			for ( Object obj2 : (Collection)obj) {
				if (!r.isEmpty()) r = r + ",\n"+indent;
				r = r + stringify(obj2, indent+"  ");
			}
			return r + "]";
		}
		if (obj instanceof java.util.Map)
		{
			String r = "{";
			Map map = (Map)obj;
			for ( Object k : map.keySet()) {
				if (!r.isEmpty()) r = r + ",\n";
				r = r + stringify(k, indent+" ") + ": "+stringify (map.get(k), indent+"  ");
			}
			return r + "}";
		}
		if (obj.getClass().isArray())
		{
			String r = "";
			Object[] array = (Object[]) obj; 
			for ( Object obj2 : array) {
				if (r.isEmpty()) r = "[";
				else r = r + ",\n"+indent;
				r = r + stringify(obj2, indent+"  ");
			}
			return r + "]";
		}
		return obj.toString();
	}

	public void doQuery () throws NamingException, CreateException, InternalErrorException
	{
		DispatcherService svc = EJBLocator.getDispatcherService();
		
		String name = (String) XPathUtils.eval(getForm(), "/@name");
		String system = (String) XPathUtils.eval(getForm(), "/@system");

		GetObjectResults result = svc.getNativeObject(
				system,
				SoffidObjectType.OBJECT_ACCOUNT,
				name, system);
		Map map = result.getObject();
		if (map == null)
		{
			es.caib.zkib.zkiblaf.Missatgebox.avis(org.zkoss.util.resource.Labels.getLabel("agents.zul.notFound"));
			return;
		}
		Component c = Path.getComponent("//objectAttributes/window");
		for (Object key: map.keySet())
		{
			map.put(key, stringify(map.get(key), ""));
		}
		Events.postEvent("onStart", c, new Object[]{
				name,
				result
		});
	}
	
	public void addNewService(Event event) throws CommitException {
		getModel().commit();
		DataTable dt = (DataTable) getFellow("servicesList");
		dt.addNew();
		
		Account account = (Account) XPathUtils.eval(getListbox(), "instance");
		XPathUtils.setValue(dt, "accountId", account.getId());
		XPathUtils.setValue(dt, "accountName", account.getName());
		XPathUtils.setValue(dt, "accountSystem", account.getSystem());
		XPathUtils.setValue(dt, "manual", Boolean.TRUE);
		
		openService(event);
	}
	
	public void openService(Event event) throws CommitException {
		Window w = (Window) getFellow("serviceWindow");
		Component form = w.getFellow("form");
		HostService current = (HostService) XPathUtils.eval(form, "instance");
		((CustomField3)w.getFellow("service")).setReadonly(! current.isManual());
		((CustomField3)w.getFellow("hostName")).setReadonly(! current.isManual());
		w.doHighlighted();
		if (current.getId() == null)
			((CustomField3)w.getFellow("service")).focus();
		else
			((CustomField3)w.getFellow("command")).focus();
	}
	
	public void undoService(Event event) throws Exception {
		Window w = (Window) getFellow("serviceWindow");
		DataNodeCollection coll = (DataNodeCollection) XPathUtils.eval(getListbox(), "services");
		coll.refresh();
		w.setVisible(false);
	}

	public void applyService(Event event) throws Exception {
		Window w = (Window) getFellow("serviceWindow");

		if ( ((CustomField3)w.getFellow("service")).attributeValidateAll() &&
			((CustomField3)w.getFellow("hostName")).attributeValidateAll() &&
			((CustomField3)w.getFellow("command")).attributeValidateAll() ) {
			
			Component form = w.getFellow("form");
			
			CustomField3 cf = (CustomField3)w.getFellow("hostName");
			Host host = (Host) cf.getValueObject();
			XPathUtils.setValue(form, "hostId", host.getId());
			
			getModel().commit();
			w.setVisible(false);
		}
	}

	public void deleteService(Event event) throws Exception {
		Missatgebox.confirmaOK_CANCEL(Labels.getLabel("common.delete"), 
				(event2) -> {
					if (event2.getName().equals("onOK")) {
						Window w = (Window) getFellow("serviceWindow");
						DataTable dt = (DataTable) getFellow("servicesList");
						dt.delete();
						getModel().commit();
						w.setVisible(false);
					}
				});

	}
}
