package com.soffid.iam.web.vault;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Account;

import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTextbox;
import es.caib.zkib.component.DataTree;
import es.caib.zkib.component.Form;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.events.XPathRerunEvent;
import es.caib.zkib.jxpath.Pointer;
import es.caib.zkib.zkiblaf.Missatgebox;

import com.soffid.iam.api.Password;
import com.soffid.iam.api.VaultFolder;
import com.soffid.iam.service.ejb.SelfService;
import com.soffid.iam.web.users.additionalData.AttributesDiv;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Window;

public class AccountWindow extends Window implements AfterCompose {

	private String ssoPolicy;
	private String ssoSystem;
	private boolean canUpdateUserMetadata;
	private String accountPath;
	private String accountNameOnAttribute;
	private boolean newAccount;
	private DataModel model;
	private String newAccountType;
	
	public void afterCompose() {
		boolean canUpdateUserMetadata = false;
		Component macro = getParent();
		ssoSystem = com.soffid.iam.utils.ConfigurationCache.getProperty("AutoSSOSystem"); //$NON-NLS-1$
		ssoPolicy = com.soffid.iam.utils.ConfigurationCache.getProperty("AutoSSOPolicy"); //$NON-NLS-1$

		macro.addEventListener("onNewAccount", new org.zkoss.zk.ui.event.EventListener() {
			public void onEvent(Event evt) throws Exception {
				onNewAccount((String) evt.getData());
			}
		});
		macro.addEventListener("onOpenAccount", new org.zkoss.zk.ui.event.EventListener() {
			public void onEvent(Event evt) throws UnsupportedEncodingException {
				onOpenAccount((String) evt.getData());
			}
		});
		getPage().getFellow("timer").addEventListener("onTimer", new org.zkoss.zk.ui.event.EventListener() {
			public void onEvent(Event evt) throws UnsupportedEncodingException {
				checkIsUpdatePending();
			}
		});
		model = (DataModel) getPage().getFellow("model");
	}

	void onNewAccount(String path) throws Exception {
		accountPath = path;
		accountNameOnAttribute = null;
		newAccount = true;
		
		getFellow("accountTypeSelector").setVisible(true);
		getFellow("panels").setVisible(false);
		newAccountType = null;

		if (ssoSystem == null)
			throw new UiException("Configuration parameter AutoSSOSystem is not defined. Please, configure it");
		if (ssoPolicy == null)
			throw new UiException("Configuration parameter AutoSSOPolicy is not defined. Please, configure it");

		canUpdateUserMetadata = true;

		es.caib.zkib.component.Form form = (Form) getFellow("form");
		getFellow("serverRow").setVisible(false);
		getFellow("urlRow").setVisible(true);
		((DataTextbox) getFellow("serverTextbox").getFellow("dada")).setBind(null);
//		((DataTextbox) getFellow("urlTextbox")).setBind(null);

		form.setDataPath("/model:" + path);

		setTitle(org.zkoss.util.resource.Labels.getLabel("vault.account.new"));

		displayAccountAcl();

		// Add folder users
		String parentPath = path.substring(0, path.lastIndexOf('/'));
		for (String pathToCopy : new String[] { "grantedUsers", "grantedGroups", "grantedRoles", "managerUsers",
				"managerGroups", "managerRoles", "ownerUsers", "ownerGroups", "ownerRoles" }) {
			for (Iterator it = model.getJXPathContext().iterate(parentPath + "/" + pathToCopy); it.hasNext();) {
				Object obj = it.next();
				es.caib.zkib.datasource.XPathUtils.createPath(model, path + "/" + pathToCopy, obj);
			}
		}
		System.out.println("TTT" + path);

		es.caib.zkib.datasource.XPathUtils.setValue((DataSource) model, path + "/@system", ssoSystem);
		es.caib.zkib.datasource.XPathUtils.setValue((DataSource) model, path + "/@passwordPolicy", ssoPolicy);
		es.caib.zkib.datasource.XPathUtils.setValue((DataSource) model, path + "/@type",
				es.caib.seycon.ng.comu.AccountType.SHARED);
		es.caib.zkib.datasource.XPathUtils.setValue((DataSource) model, path + "/attributes", new HashMap<String, Object>());
		((AttributesDiv) getFellow("attributesDiv")).updateMetadata();

		displayFormatStandard(true);
		doHighlighted();
	}

	boolean displayAccountAcl() {
		es.caib.zkib.component.Form form = (Form) getFellow("form");
		es.caib.zkib.binder.BindContext bindCtx = es.caib.zkib.datasource.XPathUtils.getComponentContext(form);
		String path = bindCtx.getXPath();
		String parentPath = path.substring(0, path.lastIndexOf('/'));

		es.caib.seycon.ng.comu.AccountAccessLevelEnum level = (AccountAccessLevelEnum) es.caib.zkib.datasource.XPathUtils
				.getValue(bindCtx.getDataSource(), path + "/@accessLevel");

		boolean personal = false;
		boolean isAdmin = level.equals(es.caib.seycon.ng.comu.AccountAccessLevelEnum.ACCESS_OWNER);

		try {
			personal = (Boolean) es.caib.zkib.datasource.XPathUtils.getValue(bindCtx.getDataSource(),
					parentPath + "/@personal");
		} catch (Exception e) {

		}

		return displayAccountAcl(isAdmin, personal);
	}

	boolean displayAccountAcl(boolean isAdmin, boolean personal) {
		Events.sendEvent(new Event("onEnable", getFellow("ownerAcl"), !personal && isAdmin));
		Events.sendEvent(new Event("onEnable", getFellow("managerAcl"), !personal && isAdmin));
		Events.sendEvent(new Event("onEnable", getFellow("userAcl"), !personal && isAdmin));

		getFellow("inheritNewPermissionsCheckbox").setVisible(!personal && isAdmin);
		getFellow("permissionsBox").setVisible(!personal && isAdmin);

		return isAdmin;
	}

	void onOpenAccount(String path) throws UnsupportedEncodingException {

		accountNameOnAttribute = null;
		newAccount = false;
		accountPath = path;

		getFellow("accountTypeSelector").setVisible(false);
		getFellow("panels").setVisible(true);

		canUpdateUserMetadata = true;

		getFellow("serverRow").setVisible(false);
		getFellow("urlRow").setVisible(false);
		((DataTextbox) getFellow("serverTextbox").getFellow("dada")).setBind(null);
//		((DataTextbox) getFellow("urlTextbox")).setBind(null);

		DataNode account = (DataNode) XPathUtils.getValue((DataSource)model, path);
		
		es.caib.zkib.component.Form form = (Form) getFellow("form");
		form.setDataPath("/model:" + path);

		setTitle(org.zkoss.util.resource.Labels.getLabel("vault.details"));

		String parentPath = path.substring(0, path.lastIndexOf('/'));

		boolean isAdmin = displayAccountAcl();

		if (ssoSystem.equals(es.caib.zkib.datasource.XPathUtils.getValue((DataSource) model, path + "/@system"))) {
			String loginName = (String) XPathUtils.getValue((DataSource)model, path+"/@loginName");
			if (loginName == null)
			{
				String accountAttributeXPath = findNameAttribute();
				if (accountAttributeXPath != null) {
					Map<String, Object> attributes = 
							(Map<String, Object>) XPathUtils.getValue((DataSource) model, path + "/attributes");
					String value = (String) attributes.get(accountAttributeXPath);
					if (value != null) {
						String[] result = splitValues(value);
						((Textbox) getFellow("txtAccountName2")).setValue(result[1]);
						XPathUtils.setValue((DataSource)model, path+"/@loginName", result[1]);
					}
				}
			}
			displayFormatSSO(isAdmin);
		} else {
			displayFormatStandard(isAdmin);

			try {
				Object ownerName = XPathUtils.getValue((DataSource) model, path + "/owner[1]/userName");
				getFellow("ownerRow").setVisible(ownerName != null);
			} catch (es.caib.zkib.jxpath.JXPathNotFoundException e) {
				getFellow("ownerRow").setVisible(false);
			}
		}

		doHighlighted();
	}

	void displayFormatSSO(boolean isAdmin) {
		getFellow("txtAccountName").setVisible(false);
		getFellow("dispatcherRow").setVisible(false);
		getFellow("typeRow").setVisible(false);
		getFellow("dispatcherRow").setVisible(false);
		getFellow("enabledRow").setVisible(false);
		getFellow("passwordPolicyRow").setVisible(false);

		getFellow("txtAccountName2").setVisible(true);
		getFellow("urlRow").setVisible(true);
		
		getFellow("changeFolderButton").setVisible(isAdmin);
		((Textbox) getFellow("txtAccountName2")).setReadonly(!isAdmin);
		((Textbox) getFellow("serverTextbox").getFellow("dada")).setReadonly(!isAdmin);
		((DataTextbox) getFellow("serverTextbox").getFellow("dada")).setBind("/attributes[@name='SSO:Server']");
		((Textbox) getFellow("urlTextbox")).setReadonly(!isAdmin);
		((Textbox) getFellow("txtAccountDescription").getFellow("dada")).setReadonly(!isAdmin);

		((Textbox) getFellow("txtAccountName2")).focus();
		getFellow("ownerRow").setVisible(false);
	}

	void displayFormatStandard(boolean isAdmin) {
		getFellow("txtAccountName").setVisible(true);
		getFellow("dispatcherRow").setVisible(true);
		getFellow("typeRow").setVisible(true);
		getFellow("dispatcherRow").setVisible(true);
		getFellow("enabledRow").setVisible(true);
		getFellow("passwordPolicyRow").setVisible(true);

		getFellow("changeFolderButton").setVisible(isAdmin);
		((Textbox) getFellow("txtAccountName").getFellow("dada")).setReadonly(!isAdmin);
		((Listbox) getFellow("lbAccountDisabled")).setDisabled(!isAdmin);
		((Textbox) getFellow("txtAccountDescription").getFellow("dada")).setReadonly(!isAdmin);

		getFellow("txtAccountName2").setVisible(false);
		getFellow("urlRow").setVisible(false);
		((Textbox) getFellow("txtAccountName").getFellow("dada")).focus();
	}

	private Long findLastAccount(String system) throws InternalErrorException {
		long top = 0;
		es.caib.seycon.ng.utils.Security.nestedLogin(es.caib.seycon.ng.utils.Security.getCurrentAccount(),
				new String[] { es.caib.seycon.ng.utils.Security.AUTO_ACCOUNT_QUERY
						+ es.caib.seycon.ng.utils.Security.AUTO_ALL });
		try {
			com.soffid.iam.service.AccountService accountService = com.soffid.iam.ServiceLocator.instance()
					.getAccountService();
			long bits = 0;
			long attempt = 1;
			/**
			 * Find radix the first account with number = 2 ^ radix
			 */
			do {
				Account acc = accountService.findAccount("" + attempt, system);
				if (acc == null)
					break;
				top = attempt;
				attempt = attempt + attempt;
				bits++;
			} while (true);
			/**
			 * Now look for the other bits top exists attempt does not exist
			 */
			long step = top;
			while (bits > 1) {
				step = step / 2;
				attempt = top + step;
				Account acc = accountService.findAccount("" + attempt, system);
				if (acc != null)
					top = attempt;
				bits--;
			}
		} finally {
			es.caib.seycon.ng.utils.Security.nestedLogoff();
		}
		return top;
	}

	public void onOpenFolder(String path) {
		es.caib.zkib.component.Form form = (Form) getFellow("form");
		form.setDataPath("/model:" + path);
		Events.sendEvent(new Event("onEnable", getFellow("ownerAcl"), true));
		Events.sendEvent(new Event("onEnable", getFellow("managerAcl"), true));
		Events.sendEvent(new Event("onEnable", getFellow("userAcl"), true));
		setTitle(org.zkoss.util.resource.Labels.getLabel("vault.folder.open"));
		doHighlighted();
	}

	public void onCancel() throws Exception {
		es.caib.zkib.datamodel.DataNode dn = (DataNode) es.caib.zkib.datasource.XPathUtils.getValue((DataSource) model,
				accountPath);
		dn.getContainer().refresh();
		setVisible(false);
	}

	private String[] splitValues(String v) throws UnsupportedEncodingException {
		String[] result = new String[] { "", "" };
		if (v != null) {
			String[] split = v.split("=");
			if (split.length > 0)
				result[0] = java.net.URLDecoder.decode(split[0], "UTF-8");
			if (split.length > 1)
				result[1] = java.net.URLDecoder.decode(split[1], "UTF-8");
		}
		return result;
	}

	String findNameAttribute() {

		es.caib.zkib.binder.BindContext ctx = es.caib.zkib.datasource.XPathUtils.getComponentContext(getFellow("form"));

		accountNameOnAttribute = null;
		Map<String, Object> attributes = (Map<String, Object>) ctx.getDataSource().getJXPathContext()
				.getValue(ctx.getXPath() + "/attributes");
		List<String> attNames = new LinkedList<String>(attributes.keySet());
		Collections.sort(attNames);
		for (String key : attNames) {
			if (key.startsWith("SSO:") && Character.isDigit(key.charAt(4))) {
				accountNameOnAttribute = key;
				break;
			}
		}
		return accountNameOnAttribute;
	}

	public void onChangeLoginUrl (Textbox txtBox)
	{
		txtBox.setStyle("");
	    String value = txtBox.getValue();
	    es.caib.zkib.binder.BindContext ctx = es.caib.zkib.datasource.XPathUtils.getComponentContext(txtBox);
	    if (ssoSystem.equals(es.caib.zkib.datasource.XPathUtils.getValue((DataSource) model, 
	    		ctx.getXPath() + "/@system"))) {
		    Textbox serverTextbox = (Textbox) getFellow("serverTextbox").getFellow("dada");
		    if (value == null || value.trim().length () == 0)
		    {
		    	serverTextbox.setValue("");
		    }
		    else
		    {
		    	try {
			    	java.net.URI url = new java.net.URI(value);
		    		txtBox.setStyle("");
		    		String h = url.getHost();
		    		if (h.startsWith("www."))
		    			h = h.substring(4);
		    		serverTextbox.setValue( h );
		    	} catch (Exception e) {
		    		e.printStackTrace();
		    		txtBox.setStyle("background-color: pink");
		    	}
	
		    }

			ctx.getDataSource().getJXPathContext()
					.setValue(ctx.getXPath() + "/attributes[@name='SSO:URL']", value);
			ctx.getDataSource().sendEvent(new XPathRerunEvent(ctx.getDataSource(), ctx.getXPath()+"/attributes"));
		}
		
	}
	
	public void onChangeAccountName(Textbox txtBox) throws UnsupportedEncodingException {
		es.caib.zkib.binder.BindContext ctx = es.caib.zkib.datasource.XPathUtils.getComponentContext(txtBox);
		String accountAttributeXPath = accountNameOnAttribute;
		if (accountAttributeXPath == null) {
			accountAttributeXPath = findNameAttribute();
		}
		if (accountAttributeXPath == null)
			return;

		Map<String, Object> attributes = (Map<String, Object>) ctx.getDataSource().getJXPathContext()
				.getValue(ctx.getXPath() + "/attributes");
		String value = (String) attributes.get(accountAttributeXPath);
		String[] result = splitValues(value);
		if (result[0].trim().length() == 0)
			result[0] = "user";
		result[1] = txtBox.getValue();
		String str = java.net.URLEncoder.encode(result[0], "UTF-8") + "="
				+ java.net.URLEncoder.encode(result[1], "UTF-8");
		attributes.put(accountAttributeXPath, str);
		((AttributesDiv) getFellow("attributesDiv")).updateMetadata();
	}

	public void onApply() throws InternalErrorException, CommitException {
		if ( newAccount && newAccountType == null)
		{
			Listbox lb = (Listbox) getFellow("accountTypeSelectorListbox");
			if (lb.getSelectedItem() == null)
			{
				Missatgebox.avis(Labels.getLabel("accounts.selectAccountTypeWarning"));
			}
			else
			{
				newAccountType = (String) lb.getSelectedItem().getValue();
				getFellow("accountTypeSelector").setVisible(false);
				getFellow("panels").setVisible(true);
				if (newAccountType.equals("m"))
				{
					displayFormatStandard(true);
				}
				else 
				{
					displayFormatSSO(true);
				}
			}
		}
		else
		{
			
			es.caib.zkib.binder.BindContext ctx = es.caib.zkib.datasource.XPathUtils.getComponentContext(getFellow("form"));
			String system = (String) es.caib.zkib.datasource.XPathUtils.getValue(ctx, "/@system");
			if (newAccount)
			{
				String currentName = (String) es.caib.zkib.datasource.XPathUtils.getValue(ctx, "/@name");
				long l = findLastAccount (ssoSystem) + 1;
				es.caib.zkib.datasource.XPathUtils.setValue(ctx, "/@name", ""+l);	
			}
			Long folderId = (Long) es.caib.zkib.datasource.XPathUtils.getValue(ctx, "/@vaultFolderId");
			
			model.commit();
			setVisible(false);
			
			Tree treebox = (Tree) getPage().getFellow("esquema").getFellow("lista").getFellow("treebox");
			
			String xPath = null;
			
			if (treebox.getSelectedItem() != null)
				xPath = ((DataNode)treebox.getSelectedItem().getValue()).getXPath();
			
			model.getVariables().declareVariable("directFilter", false);
			
			try {
				es.caib.zkib.datamodel.DataNode dn =  (DataNode) es.caib.zkib.datasource.XPathUtils.getValue( (DataSource) model, accountPath);
				dn.getContainer().refresh();
				// Refresh previous parent folder
				es.caib.zkib.datamodel.DataNodeCollection c = (DataNodeCollection) es.caib.zkib.datasource.XPathUtils.getValue((DataSource)model, "/folder");
				searchAndRefresh (c, folderId);
			} catch (Throwable e) {
				
			}
		}

	}

	void searchAndRefresh(es.caib.zkib.datamodel.DataNodeCollection coll, Long folderId) {
		try {
			if (!coll.isDirty()) {
				for (es.caib.zkib.datamodel.DataNode dn : (Collection<DataNode>) coll) {
					com.soffid.iam.api.VaultFolder folder = (VaultFolder) dn.getInstance();
					if (folder != null) {
						if (folder.getId().equals(folderId)) {
							es.caib.zkib.datamodel.DataNodeCollection lm = (DataNodeCollection) dn.getListModel("account");
							lm.refresh();
						} else {
							es.caib.zkib.datamodel.DataNodeCollection lm = (DataNodeCollection) dn.getListModel("folder");
							if (lm != null)
								searchAndRefresh(lm, folderId);
						}
					}
				}
			}
		} catch (Exception e) {

		}
	}

	public void selectParent() {
		Window selectFolderWnd = getSelectFolderWindow();
		es.caib.zkib.component.DataTree tree = (DataTree) selectFolderWnd.getFellow("folderTree");
		tree.setSelectedItem(null);
		selectFolderWnd.doHighlighted();
	}

	private Window getSelectFolderWindow() {
		return (Window) getParent().getFellow("selectFolderWnd");
	}

	public void selectedParentFolder() {
		Window selectFolderWnd = getSelectFolderWindow();
		es.caib.zkib.component.DataTree tree = (DataTree) selectFolderWnd.getFellow("folderTree");
		Treeitem item = tree.getSelectedItem();
		if (item != null) {
			es.caib.zkib.binder.BindContext ctx1 = es.caib.zkib.datasource.XPathUtils.getComponentContext(item);
			es.caib.zkib.binder.BindContext ctx2 = es.caib.zkib.datasource.XPathUtils
					.getComponentContext(getFellow("form"));
			es.caib.zkib.datasource.XPathUtils.setValue(ctx2, "@vaultFolder",
					es.caib.zkib.datasource.XPathUtils.getValue(ctx1, "@name"));
			es.caib.zkib.datasource.XPathUtils.setValue(ctx2, "@vaultFolderId",
					es.caib.zkib.datasource.XPathUtils.getValue(ctx1, "@id"));

			AccountAccessLevelEnum level = (AccountAccessLevelEnum) es.caib.zkib.datasource.XPathUtils
					.getValue(ctx2.getDataSource(), ctx2.getXPath() + "/@accessLevel");
			boolean isAdmin = level.equals(es.caib.seycon.ng.comu.AccountAccessLevelEnum.ACCESS_OWNER);

			boolean personal = false;

			try {
				String path = ctx1.getXPath();
				personal = (Boolean) es.caib.zkib.datasource.XPathUtils.getValue(ctx1.getDataSource(), path + "/@personal");
			} catch (Exception e) {

			}

			displayAccountAcl(isAdmin, personal);
		}
		selectFolderWnd.setVisible(false);
	}

	public void checkIsUpdatePending() {
		try {
			es.caib.zkib.component.Form form = (Form) getFellow("form");
			es.caib.zkib.binder.BindContext ctx = es.caib.zkib.datasource.XPathUtils.getComponentContext(form);
			es.caib.zkib.datamodel.DataNode dn = (DataNode) es.caib.zkib.datasource.XPathUtils.getValue(ctx, ".");
			if (dn != null) {
				Account acc = (Account) dn.getInstance();
				if (acc != null)

				{
					com.soffid.iam.service.AccountService accountService = com.soffid.iam.ServiceLocator.instance()
							.getAccountService();
					form.getFellow("updateIndicator").setVisible(accountService.isUpdatePending(acc));
					es.caib.zkib.datasource.DataSource ds = ctx.getDataSource();

					String prefix = ctx.getXPath();
					ds.sendEvent(
							new es.caib.zkib.events.XPathRerunEvent(ds, XPathUtils.concat(prefix, "/@lastUpdated")));
					ds.sendEvent(new es.caib.zkib.events.XPathRerunEvent(ds,
							XPathUtils.concat(prefix, "/@lastPasswordSet")));
					ds.sendEvent(new es.caib.zkib.events.XPathRerunEvent(ds,
							XPathUtils.concat(prefix, "/@passwordExpiration")));
				}
			}
		} catch (Exception e) {
			// Ignore error when no account is selected
		}
	}

	public void queryPassword(Component button) throws WrongValueException, InternalErrorException, NamingException, CreateException {
		try {
			Component queryImg = getFellow("queryImg");
			Window showPassword = (Window) getParent().getFellow ("showPassword");
			if (!queryImg.isVisible())
				return;

			Textbox qpassword = (Textbox)showPassword.getFellow("qpassword");
			qpassword.setValue("");
			Label popupPwd = (Label)showPassword.getFellow("popupPwd");
			popupPwd.setValue("");
			es.caib.zkib.binder.BindContext ctx = XPathUtils.getComponentContext(button);
			Account account = (Account) ((DataNode) XPathUtils.getValue(ctx, ".")).getInstance();
			com.soffid.iam.service.ejb.AccountService service = com.soffid.iam.EJBLocator.getAccountService();
			if (service.isUpdatePending(account) && account.getType().equals(AccountType.PRIVILEGED)) {
				qpassword.setValue(org.zkoss.util.resource.Labels.getLabel("selfService.UpdatePending"));
				showPassword.getFellow("labelPWDis").setVisible(false);
				qpassword.setMultiline(true);
				showPassword.setVisible(true);
				showPassword.setMode("highlighted");
			} else {
				Password pawd = service.queryAccountPassword(account);
				if (pawd != null) {
					String cadena = pawd.getPassword();
					String cadenaResultant = "";
					qpassword.setValue(cadena);
					int i = cadena.length();
					for (int j = 0; j < i; j++) {
						cadenaResultant = cadenaResultant + cadena.charAt(j) + "\t";
						Character c = cadena.charAt(j);
						if (c.isUpperCase(c)) {
							cadenaResultant = cadenaResultant
									+ org.zkoss.util.resource.Labels.getLabel("selfService.Majuscula");
						}
						if (c.isLowerCase(c)) {
							cadenaResultant = cadenaResultant
									+ org.zkoss.util.resource.Labels.getLabel("selfService.Minuscula");
						}
						if (c.isDigit(c)) {
							cadenaResultant = cadenaResultant
									+ org.zkoss.util.resource.Labels.getLabel("selfService.Number");
						}
						if (!c.isLetter(c) && !c.isDigit(c)) {
							cadenaResultant = cadenaResultant
									+ org.zkoss.util.resource.Labels.getLabel("selfService.Symbol");
						}
						cadenaResultant = cadenaResultant + "\n";
					}
					popupPwd.setValue(cadenaResultant);
					showPassword.getFellow("labelPWDis").setVisible(true);
				} else {
					qpassword.setValue(org.zkoss.util.resource.Labels.getLabel("selfService.EmptyField"));
					popupPwd.setValue("");
					showPassword.getFellow("labelPWDis").setVisible(true);
				}
				showPassword.setVisible(true);
				showPassword.setMode("highlighted");
			}
		} catch (InterruptedException e) {
		}
	}

	public void setPassword(Component button) throws InternalErrorException, CommitException, NamingException, CreateException {
		model.commit();
		try {
			Component passwordImg = getFellow("passwordImg");
			final Window newPasswordS = (Window) getParent().getFellow("newPasswordS");
			final Window newPassword = (Window) getParent().getFellow("newPassword");
			
			if (!passwordImg.isVisible())
				return;
			es.caib.zkib.binder.BindContext ctx = XPathUtils.getComponentContext(button);
			Account account = (Account) ((DataNode) XPathUtils.getValue(ctx, ".")).getInstance();
			if (account.getType().equals(AccountType.USER)) {
				newPasswordS.setAttribute("acco", account);
				newPasswordS.setAttribute("path", accountPath);
				SelfService service = EJBLocator.getSelfService();
				String afectats = service.queryOtherAffectedAccounts(account);
				if (afectats != null) {
					Missatgebox.confirmaOK_CANCEL(
							String.format(org.zkoss.util.resource.Labels.getLabel("selfService.Segur"),
									new Object[] { afectats }),
							org.zkoss.util.resource.Labels.getLabel("selfService.Segur2"), new EventListener() {
								@Override
								public void onEvent(Event evt) throws InterruptedException {
									if ("onOK".equals(evt.getName())) {
										newPasswordS.setVisible(true);
										newPasswordS.doHighlighted();
										((Textbox)newPasswordS.getFellow("p1")).focus();
									}
								}
							});
				}
			} else if (account.getType().equals(AccountType.SHARED)) {
				newPasswordS.setAttribute("acco", account);
				newPasswordS.setVisible(true);
				newPasswordS.setMode("highlighted");
				((Textbox)newPasswordS.getFellow("p1")).focus();
			} else if (account.getType().equals(AccountType.PRIVILEGED)) {
				newPassword.setAttribute("acco", account);
				java.util.Calendar dia = java.util.Calendar.getInstance();
				dia.add(java.util.Calendar.DAY_OF_MONTH, 1);
				((Datebox)newPassword.getFellow("timepwd")).setValue(dia.getTime());
				dia.clear();
				newPassword.setVisible(true);
				newPassword.setMode("highlighted");
				((Textbox)newPassword.getFellow("password")).focus();
			}
		} catch (InterruptedException e) {
		}
	}

	public void checkinAccount(Component c) {
		es.caib.zkib.binder.BindContext ctx = XPathUtils.getComponentContext(c);
		final Account acc = (Account) ((DataNode) XPathUtils.getValue(ctx, ".")).getInstance();
		es.caib.zkib.zkiblaf.Missatgebox.confirmaOK_CANCEL("Please, confirm you want to return this account",
				new org.zkoss.zk.ui.event.EventListener() {
					public void onEvent(Event evt) throws NamingException, CreateException, InternalErrorException {
						if ("onOK".equals(evt.getName())) {
							com.soffid.iam.service.ejb.AccountService ejb = com.soffid.iam.EJBLocator
									.getAccountService();
							ejb.checkinHPAccount(acc);
							getFellow("ownerRow").setVisible(false);
						}
					}
				});
	}

}
