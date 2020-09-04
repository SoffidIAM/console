package com.soffid.iam.web.account;

import java.io.IOException;
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
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
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
import com.soffid.iam.api.Password;
import com.soffid.iam.api.VaultElement;
import com.soffid.iam.service.ejb.ApplicationService;
import com.soffid.iam.service.ejb.AuthorizationService;
import com.soffid.iam.service.ejb.ConfigurationService;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.DynamicColumnsDatatable;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.ObjectAttributesDiv;
import com.soffid.iam.web.component.SearchBox;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.ImportCsvHandler;
import com.soffid.iam.web.popup.SelectColumnsHandler;

import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.datamodel.DataNode;
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
		

	public void onChangeForm(Event ev) {
		super.onChangeForm(ev);
		updateStatus();
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
		if (user != null && system != null) {
			sb.setBasicMode();
			sb.addAttribute("name").setSearchFilter(user);
			sb.addAttribute("system").setSearchFilter(system);
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
		Boolean disabled = (Boolean) listbox.getJXPathContext().getValue("disabled");
		if (Boolean.FALSE.equals(disabled)) {
			Window w = (Window) getFellow("newPassword2");
			Radiogroup gt = (Radiogroup) w.getFellow("generationType");
			Radio radioRandom = (Radio) w.getFellow("generationRandom");
			gt.setSelectedItem(radioRandom);
			onChangeSelectedGeneration(null);
			w.doHighlighted();
		} else {
			Missatgebox.avis("The account is disabled. The password cannot be changed yet");
		}
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
					.confirmaOK(org.zkoss.util.resource.Labels
							.getLabel("accounts.setPassword.msg"));
		}
		else
		{
			Password nouPassword =  EJBLocator.getAccountService().generateAccountTemporaryPassword(account);
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
			Object instance = ((DataNode)XPathUtils.getValue(getForm(), "/")).getInstance();
			Account account = (Account) instance;
			try {
				i.setVisible(EJBLocator.getAccountService().isUpdatePending(account));
			} catch (Exception e) {
				i.setVisible(false);
			}
		} else if ( lb instanceof DataTree2 && ((DataTree2) lb).getSelectedItemXPath() != null ) {
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

}
