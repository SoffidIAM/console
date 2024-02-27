package com.soffid.iam.web.account;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.AccountStatus;
import com.soffid.iam.api.Host;
import com.soffid.iam.api.HostService;
import com.soffid.iam.api.LaunchType;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.PasswordValidation;
import com.soffid.iam.api.System;
import com.soffid.iam.api.UserType;
import com.soffid.iam.api.VaultElement;
import com.soffid.iam.api.VaultFolder;
import com.soffid.iam.service.ejb.AccountService;
import com.soffid.iam.service.ejb.SelfService;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.ObjectAttributesDiv;
import com.soffid.iam.web.component.SearchBox;
import com.soffid.iam.web.vault.LaunchHelper;

import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.binder.BindContext;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.component.Form2;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;

public class VaultHandler extends FrameHandler {
	private Account account;
	private boolean unselectAfterPassword;

	public VaultHandler() throws InternalErrorException {
		com.soffid.iam.api.Tenant masterTenant = com.soffid.iam.ServiceLocator.instance().getTenantService().getMasterTenant();
		boolean full = Security.isUserInRole("account:update");
				
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
	}
		

	public void onChangeForm2(Event ev) throws Exception {
		changeVisibility(ev, false);
	}
	public void onChangeForm(Event ev) throws Exception {
		changeVisibility(ev, true);
	}
	
	public void changeVisibility(Event ev, boolean changeTab) throws Exception {
		super.onChangeForm(ev);
		DataTree2 lb = (DataTree2) getListbox();
		if ( lb.getSelectedItemXPath() != null ) {
			VaultElement instance = (VaultElement) ((DataNode)XPathUtils.eval(lb, "/.")).getInstance();
			getFellow("accountProperties").setVisible("account".equals(instance.getType()));
			getFellow("folderProperties").setVisible("folder".equals(instance.getType()));
			if ("folder".equals(instance.getType())) {
				AccountAccessLevelEnum level = instance.getFolder().getAccessLevel();
				boolean owner = (level == AccountAccessLevelEnum.ACCESS_OWNER) || instance.getFolder().getId() == null;
				((CustomField3) getFellow("folder_name")).setReadonly(! owner);
				((CustomField3) getFellow("folder_description")).setReadonly(! owner);
				getFellow("folder_acl1").setVisible(owner && !instance.getFolder().isPersonal());
				getFellow("folder_acl2").setVisible(owner && !instance.getFolder().isPersonal());
				getFellow("folder_acl3").setVisible(owner && !instance.getFolder().isPersonal());
				getFellow("folder_acl4").setVisible(owner && !instance.getFolder().isPersonal());
				getFellow("deleteAccountButton").setVisible(true);
				getFellow("updatePasswordButton").setVisible(false);
				getFellow("updateSshButton").setVisible(false);
			} else {
				boolean personal = isPersonal();
				updateAccountIcons(changeTab);
				ObjectAttributesDiv att = (ObjectAttributesDiv) getFellow("userAttributes");
		
				AccountAccessLevelEnum level = instance.getAccount().getAccessLevel();
				boolean owner = (level == AccountAccessLevelEnum.ACCESS_OWNER) || instance.getAccount().getId() == null;
				if (att.isReadonly() == owner ) {
					att.setReadonly(!owner);
					att.refresh();
				}
				
				getFellow("accountBasics").setVisible(owner && !personal);
				if (personal || !owner) {
					((Tabbox)getFellow("accountTabbox")).setSelectedIndex(0);
				}
				((CustomField3)getFellow("description")).setReadonly(!personal);
				((CustomField3)getFellow("loginName")).setReadonly(!personal);
				((CustomField3)getFellow("loginUrl")).setReadonly(!personal);
				getFellow("commitbar").setVisible(personal);
				if (getFellowIfAny("name") != null)
					getFellow("name").setVisible(!personal);
				getFellow("system").setVisible(!personal);
				getFellow("lockedBy").setVisible(!personal);
				try {
					DataNodeCollection coll = (DataNodeCollection) XPathUtils.eval(getForm(), "../services");
					boolean display = owner && instance.getAccount().getType() == AccountType.SHARED;
					getFellow("servicesSection").setVisible(display);
				} catch (Exception e) {}
			}
		}
		updateStatus();
	}

	protected boolean isPersonal() {
		DataTree2 lb = (DataTree2) getListbox();
		boolean personal = false;
		int pos[] = lb.getSelectedItem();
		if (pos.length > 1) {
			pos = Arrays.copyOf(pos, pos.length-1);
			String path = (String) lb.getXpathAt(pos);
			VaultElement o = (VaultElement) XPathUtils.eval(getModel(), path+"/instance");
			if (o.getType().equals("folder"))
				personal = o.getFolder().isPersonal();
		}
		return personal;
	}

	public void updateAccountIcons(boolean changeTab) {
		Long id = (Long) XPathUtils.eval(getForm(), "id");
		if (changeTab)
			((Tabbox)getFellow("accountTabbox")).setSelectedIndex(id == null ? 1 : 0);
		String url = (String) XPathUtils.getValue(getForm(), "loginUrl");
		AccountAccessLevelEnum accessLevel = (AccountAccessLevelEnum) XPathUtils.getValue(getForm(), "accessLevel"); 
		AccountType type = (AccountType) XPathUtils.getValue(getForm(), "type");
		String lockedBy = (String) XPathUtils.getValue(getForm(), "lockedBy");
		getFellow("launch_button").setVisible(url != null && ! url.trim().isEmpty());
		if (lockedBy != null && lockedBy.equals(Security.getCurrentUser())) {
			getFellow("set_password_button").setVisible(false);
			getFellow("unlock_button").setVisible(true);
			getFellow("view_password_button").setVisible(true);
			getFellow("deleteAccountButton").setVisible(accessLevel == AccountAccessLevelEnum.ACCESS_OWNER);
			getFellow("updatePasswordButton").setVisible(accessLevel == AccountAccessLevelEnum.ACCESS_OWNER || accessLevel == AccountAccessLevelEnum.ACCESS_MANAGER);
		} else {
			getFellow("deleteAccountButton").setVisible(accessLevel == AccountAccessLevelEnum.ACCESS_OWNER);
			getFellow("updatePasswordButton").setVisible(accessLevel == AccountAccessLevelEnum.ACCESS_OWNER || accessLevel == AccountAccessLevelEnum.ACCESS_MANAGER);
			getFellow("set_password_button").setVisible(accessLevel == AccountAccessLevelEnum.ACCESS_MANAGER || accessLevel == AccountAccessLevelEnum.ACCESS_OWNER);
			getFellow("unlock_button").setVisible(false);
			getFellow("view_password_button").setVisible(
					type != AccountType.PRIVILEGED && ( accessLevel == AccountAccessLevelEnum.ACCESS_MANAGER || accessLevel == AccountAccessLevelEnum.ACCESS_OWNER) );
		}
		final Component updateSshButton = getFellow("updateSshButton");
		try {
			Account acc = (Account) XPathUtils.eval(getForm(), ".");
			String ssoSystem = com.soffid.iam.utils.ConfigurationCache.getProperty("AutoSSOSystem"); //$NON-NLS-1$
			if (acc.getSystem().equals(ssoSystem) && "Linux".equals(acc.getServerType()) ||
				acc.getLaunchType() == LaunchType.LAUNCH_TYPE_PAM && 
					acc.getLoginUrl() != null && 
					acc.getLoginUrl().startsWith("ssh:")){
				updateSshButton.setVisible(true);
			} else {
				updateSshButton.setVisible(false);
			}
		} catch (Exception e) {
			updateSshButton.setVisible(false);
		}
	}
	
	
	public void addNew() throws Exception {
		DataTree2 tree = (DataTree2) getListbox();
		tree.setSelectedIndex(new int[0]);
		VaultElement e = new VaultElement();
		e.setType("folder");
		e.setFolder(new VaultFolder());
		e.getFolder().setOwnerUsers(new LinkedList<>());
		e.getFolder().getOwnerUsers().add(Security.getCurrentUser());
		tree.addNew("/vault", e);
		showDetails();
	}

	public void addFolder(Event ev) throws Exception {
		DataTree2 tree = (DataTree2) getListbox();
		Long parentId = (Long) tree.getJXPathContext().getValue("/folder/id");
		String parent = (String) tree.getJXPathContext().getValue("/folder/name");
		Boolean personal = (Boolean) tree.getJXPathContext().getValue("/folder/personal");
		VaultElement e = new VaultElement();
		e.setType("folder");
		e.setFolder(new VaultFolder());
		e.getFolder().setOwnerUsers(new LinkedList<>());
		e.getFolder().getOwnerUsers().add(Security.getCurrentUser());
		e.getFolder().setParentId(parentId);
		e.getFolder().setParentFolder(parent);
		e.getFolder().setPersonal(personal);
		tree.addNew("/vault", e);
		showDetails();
	}

	public void addAccount(Event ev) throws Exception {
		String ssoSystem = com.soffid.iam.utils.ConfigurationCache.getProperty("AutoSSOSystem"); //$NON-NLS-1$
		String ssoPolicy = com.soffid.iam.utils.ConfigurationCache.getProperty("AutoSSOPolicy"); //$NON-NLS-1$

		DataTree2 tree = (DataTree2) getListbox();
		Long parentId = (Long) tree.getJXPathContext().getValue("/folder/id");
		String parent = (String) tree.getJXPathContext().getValue("/folder/name");
		Boolean personal = (Boolean) tree.getJXPathContext().getValue("/folder/personal");
		VaultElement e = new VaultElement();
		e.setType("account");
		e.setAccount(new Account());
		e.getAccount().setOwnerUsers(new LinkedList<>());
		e.getAccount().getOwnerUsers().add(Security.getCurrentUser());
		e.getAccount().setVaultFolderId(parentId);
		e.getAccount().setVaultFolder(parent);
		e.getAccount().setSystem(ssoSystem);
		e.getAccount().setPasswordPolicy(ssoPolicy);
		e.getAccount().setType(AccountType.IGNORED);
		e.getAccount().setStatus(AccountStatus.ACTIVE);
		if (personal)
			e.getAccount().setName("?");
		tree.addNew("/vault", e);
		showDetails();
	}


	@Override
	public void afterCompose() {
		super.afterCompose();
		if (account != null) {
			DataTree2 tree = (DataTree2) getListbox();
			int l = 0;
			TreeModel treeModel = tree.getModel();
			Object node = treeModel.getRoot();
			while (node != null) {
				if (treeModel.isLeaf(node) || treeModel.getChildCount(node) <= 0)
					node = null;
				else {
					l ++;
					node = treeModel.getChild(node, 0);
				}
			}
			tree.setSelectedIndex(new int[l]);
			showDetails();
			getModel().getVariables().declareVariable("id", null);
		}
		try {
			String pref = EJBLocator.getPreferencesService().findMyPreference("passwordManagerInstalled");
			if (pref == null && Security.isUserInRole("sso:passwordManager")) {
				EJBLocator.getPreferencesService().updateMyPreference("passwordManagerInstalled", "true");
				response(null, new org.zkoss.zk.au.out.AuScript(this, "setTimeout(doRegisterVault, 1000);"));
			}
		} catch (Exception e) {
		}
	}

	public void setPassword(Event event) throws InternalErrorException, NamingException, CreateException, CommitException {
		Form2 form = (Form2) getFellow("accountProperties");
		form.getDataSource().commit();
		Account acc = (Account) XPathUtils.eval(form, "/.");
		if (! isPersonal() ) {
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


	public void doPasswordChange() throws InternalErrorException, NamingException, CreateException {
		AccountType type = (AccountType) XPathUtils.eval(getForm(), "type");
		String name = (String) XPathUtils.eval(getForm(), "name");
		String system = (String) XPathUtils.eval(getForm(), "system");
		if (type == AccountType.PRIVILEGED) {
			Window w = (Window) getFellow("newPasswordPriv");
			((CustomField3)w.getFellow("checkpwd")).setValue(false);
			((CustomField3)w.getFellow("password")).setValue("");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, 1);
			((CustomField3)w.getFellow("timepwd")).setValue(c.getTime());
			
			String p = EJBLocator.getPasswordService().getPolicyDescription(name, system);
			((Label)w.getFellow("policy_text")).setValue(p);
			
			w.doHighlighted();
		} else {
			Window w = (Window) getFellow("newPassword2");
			Radiogroup gt = (Radiogroup) w.getFellow("generationType");
			Radio radioRandom = (Radio) w.getFellow("generationRandom");
			gt.setSelectedItem(radioRandom);
			onChangeSelectedGeneration(null);
			w.doHighlighted();
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
		DataTree2 listbox = (DataTree2) getListbox();
		listbox.commit();

		Window w = (Window) getFellow("newPassword2");
		Radiogroup gt = (Radiogroup) w.getFellow("generationType");
		Radio radioRandom = (Radio) w.getFellow("generationRandom");

		VaultElement element = (VaultElement) ((DataNode)XPathUtils.getValue(getListbox(), "/")).getInstance();
		
		if (gt.getSelectedItem() != radioRandom)
		{
			Textbox password = (Textbox) w.getFellow("password");
			EJBLocator.getSelfService().setAccountPassword(element.getAccount(), new Password( password.getValue()) );
			es.caib.zkib.zkiblaf.Missatgebox
					.avis(org.zkoss.util.resource.Labels
							.getLabel("accounts.setPassword.msg"));
		}
		else
		{
			Password nouPassword =  EJBLocator.getSelfService().generateAccountTemporaryPassword(element.getAccount());
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
		
		VaultElement element = (VaultElement) ((DataNode)XPathUtils.getValue(getListbox(), "/")).getInstance();

		((Label)wnd.getFellow("lbInfoNouPass")).setValue( String.format(org.zkoss
				.util.resource.Labels.getLabel("canviPassword.CanviPasswordDomini"),
				new Object [] {element.getAccount().getSystem()}) );
		wnd.setTitle(String.format(org.zkoss.util.resource.Labels
				.getLabel("canviPassword.NouPasswordDomini"),
				new Object [] {element.getAccount().getSystem()}));
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

	@Override
	protected BindContext getForm() {
		DataTree2 lb = (DataTree2) getListbox();
		if ( lb.getSelectedItemXPath() == null ) {
			return super.getForm();
		}
		else if ( "account".equals( lb.getJXPathContext().getValue("type"))) {
			return (BindContext) getFellow("accountProperties");
		}
		else
		{
			return (BindContext) getFellow("folderProperties");
		}
	}

	public void onPrivCancelPassword() {
		Window w = (Window) getFellow("newPasswordPriv");
		((CustomField3)w.getFellow("password")).setValue("");
		w.setVisible(false);
	}
	
	public void onPrivSetPassword() throws WrongValueException, InternalErrorException, NamingException, CreateException {
		Window w = (Window) getFellow("newPasswordPriv");
		com.soffid.iam.service.ejb.SelfService service = EJBLocator.getSelfService();
		Account account = (Account) XPathUtils.getValue(getForm(), "/.");
		
		CustomField3 d = (CustomField3) w.getFellow("timepwd");
		CustomField3 p = (CustomField3) w.getFellow("password");
		CustomField3 x = (CustomField3) w.getFellow("checkpwd");

		boolean done = service.setHPAccountPassword(account,
				(Password) p.getValue(), (Date) d.getValue(),
					Boolean.TRUE.equals( x.getValue()) );
		if (done)
		{
			es.caib.zkib.zkiblaf.Missatgebox
				.avis(org.zkoss.util.resource.Labels
				.getLabel("accounts.setPassword.msg"));
			try {
				XPathUtils.setValue(getForm(), "lockedBy", Security.getCurrentUser());
				XPathUtils.setValue(getForm(), "lastPasswordSet", Calendar.getInstance() );
				XPathUtils.setValue(getForm(), "passwordStatus", PasswordValidation.PASSWORD_GOOD);
			} catch (Exception e) {}
			updateStatus();
		}
		else
		{
			es.caib.zkib.zkiblaf.Missatgebox
			.avis(org.zkoss.util.resource.Labels
			.getLabel("accounts.setPassword.delayed.msg"));
		}
		w.setVisible(false);
		updateAccountIcons(false);
	}

	public void unlockAccount() throws WrongValueException, InternalErrorException, NamingException, CreateException {
		final Account account = (Account) XPathUtils.getValue(getForm(), "/.");

		Missatgebox.confirmaOK_CANCEL("Please, confirm you want to return this account",
				(Event evt) ->  {
						if ("onOK".equals(evt.getName())) {
							SelfService ejb = EJBLocator.getSelfService();
							ejb.checkinHPAccount(account);
							XPathUtils.setValue(getForm(), "lockedBy", null);
							updateAccountIcons(false);
						}
					} );

	}
	
	public void viewPassword2() throws NamingException, CreateException, WrongValueException, InternalErrorException, InterruptedException {
		getCard().setSclass ( "card" );
		unselectAfterPassword = true;
		try {
			viewPassword();
		} catch (Exception e) {
			unselect();
			throw e;
		}
	}
	
	public void viewPassword() throws NamingException, CreateException, WrongValueException, InternalErrorException, InterruptedException {
		Window showPassword = (Window) getFellow("showPassword");
		((Textbox)showPassword.getFellow("qpassword")).setValue("");
		((Label)showPassword.getFellow("popupPwd")).setValue("");

		Account account = (Account) XPathUtils.getValue(getForm(), "/.");
		AccountService service = com.soffid.iam.EJBLocator.getAccountService();
		if(service.isUpdatePending(account) && account.getType().equals(AccountType.PRIVILEGED))
		{
			Missatgebox.avis(org.zkoss.util.resource.Labels.getLabel("selfService.UpdatePending"));
			unselect();
		}
		else
		{
			com.soffid.iam.api.Password pawd = EJBLocator.getSelfService().queryAccountPassword(account);
			com.soffid.iam.api.Password sshkey = EJBLocator.getSelfService().queryAccountSshKey(account);
			if (pawd!=null && !pawd.getPassword().isEmpty() ||
				sshkey != null && ! sshkey.getPassword().isEmpty()) {
				if (pawd == null || pawd.getPassword().isEmpty()) {
					showPassword.getFellow("divPassword").setVisible(false);
				} else {
					showPassword.getFellow("divPassword").setVisible(true);
					String cadena = pawd.getPassword();
					String cadenaResultant = "";
					((Textbox)showPassword.getFellow("qpassword")).setValue(cadena);
					int i = cadena.length();
					for(int j=0; j<i; j++){
						cadenaResultant = cadenaResultant + cadena.charAt(j) + "\t";
						Character c = cadena.charAt(j);
						if(Character.isUpperCase(c)){
							cadenaResultant = cadenaResultant + org.zkoss.util.resource.Labels.getLabel("selfService.Majuscula");
						}
						if(Character.isLowerCase(c)){
							cadenaResultant = cadenaResultant + org.zkoss.util.resource.Labels.getLabel("selfService.Minuscula");
						}
						if(Character.isDigit(c)){
							cadenaResultant = cadenaResultant + org.zkoss.util.resource.Labels.getLabel("selfService.Number");
						}
						if(!Character.isLetter(c) && !Character.isDigit(c)){
							cadenaResultant = cadenaResultant + org.zkoss.util.resource.Labels.getLabel("selfService.Symbol");
						}
						cadenaResultant = cadenaResultant + "\n";
					}
					((Label)showPassword.getFellow("popupPwd")).setValue(cadenaResultant);
					((Label)showPassword.getFellow("labelPWDis")).setVisible(true);
				}
				if (sshkey == null || sshkey.getPassword().isEmpty()) {
					showPassword.getFellow("divSshKey").setVisible(false);
				} else {
					showPassword.getFellow("divSshKey").setVisible(true);
					((Textbox)showPassword.getFellow("qsshkey")).setValue(sshkey.getPassword());
				}
				showPassword.setVisible(true);
				showPassword.setMode("highlighted");
			}
			else{
				Missatgebox.avis(org.zkoss.util.resource.Labels.getLabel("selfService.EmptyField"));
				unselect();
			}
		}	
	}
	
	public void closeShowPassword() throws NamingException, CreateException, WrongValueException, InternalErrorException, InterruptedException {
		Window showPassword = (Window) getFellow("showPassword");
		showPassword.setVisible(false);
		unselect();
	}

	protected void unselect() {
		if (unselectAfterPassword) {
			unselectAfterPassword = false;
			DataTree2 dt = (DataTree2) getListbox();
			dt.setSelectedIndex(new int[0]);
		}
	}
	
	public void launch() throws NamingException, CreateException, WrongValueException, InternalErrorException, InterruptedException, UnsupportedEncodingException {
		Account account = (Account) XPathUtils.getValue(getForm(), "/.");
		new LaunchHelper().launchAccount(account, false);
	}

	@Override
	public boolean insertBefore(Component newChild, Component refChild) {
		boolean b = super.insertBefore(newChild, refChild);
		if ( newChild instanceof DataModel) {
			HttpServletRequest request = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
			String requestedId = request.getParameter("accountId");
			String requestedAccount = request.getParameter("account");
			String requestedSystem = request.getParameter("system");
			if (requestedId != null) {
				try {
					account = EJBLocator.getAccountService().findAccountById(Long.parseLong(requestedId));
					if (account != null)
						((DataModel)newChild).getVariables().declareVariable("id", account.getId());
				} catch (InternalErrorException | NamingException | CreateException e) {
					e.printStackTrace();
				}
			}
			else if (requestedAccount != null && requestedSystem != null)
			{
				try {
					account = EJBLocator.getAccountService().findAccount(requestedAccount, requestedSystem);
					if (account != null)
						((DataModel)newChild).getVariables().declareVariable("id", account.getId());
				} catch (InternalErrorException | NamingException | CreateException e) {
					e.printStackTrace();
				}
			}
		}
		return b;
	}

	public void reorder(Event event) {
		int[][] data = (int[][]) event.getData();
		int[] srcPos = data[0];
		int[] targetPos = data[1];
		DataTree2 tree = (DataTree2) getListbox();
		
		DataNode src = (DataNode) tree.getElementAt(srcPos);
		DataNode target = (DataNode) tree.getElementAt(targetPos);
		VaultElement ob = (VaultElement) target.getInstance();
		if (ob.getAccount() != null)
			target = (DataNode) target.getParent();
		final DataNode target2 = target;
		if (src != null) {
			VaultElement ob2 = (VaultElement) src.getInstance();
			Missatgebox.confirmaOK_CANCEL(
				String.format(Labels.getLabel("application.zul.confirmMove"), ob.getFolder().getName(), ob2.getAccount().getLoginName() ),
				(ev) -> {
					if (ev.getName().equals("onOK")) {
						tree.setSelectedIndex(srcPos);
						XPathUtils.setValue(tree, "parentId", target2.get("id"));
						if (ob2.getAccount() != null) {
							XPathUtils.setValue(tree, "/account/vaultFolderId", target2.get("id"));
						} else {
							XPathUtils.setValue(tree, "/folder/parentId", target2.get("id"));
						}
						tree.setSelectedIndex(targetPos);
						getModel().commit();
					}
				});
		}
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
	
	public void setSshKey(Event event) throws InternalErrorException, NamingException, CreateException, CommitException {
		getModel().commit();
		Form2 form = (Form2) getFellow("accountProperties");
		form.getDataSource().commit();
		Window w = (Window) getFellow("sshWindow");
		Radiogroup rg = (Radiogroup) w.getFellow("generationType");
		rg.setSelectedIndex(0);
		CustomField3 key = (CustomField3) w.getFellow("newKey");
		key.setVisible(false);
		key.setValue("");
		w.doHighlighted();
		
	}

	public void onChangeSelectedSshGeneration(Event ev) {
		Component w = getFellow("sshWindow");
		Radiogroup rg = (Radiogroup) w.getFellow("generationType");
		CustomField3 cf = (CustomField3) w.getFellow("newKey");
		cf.setVisible(rg.getSelectedIndex() == 1);
	}
	
	public void undoSsh(Event ev) {
		Component w = getFellow("sshWindow");
		w.setVisible(false);
	}
	
	public void applySsh(Event ev) throws InternalErrorException, NamingException, CreateException {
		Component w = getFellow("sshWindow");
		Radiogroup rg = (Radiogroup) w.getFellow("generationType");
		CustomField3 cf = (CustomField3) w.getFellow("newKey");
		Account acc = (Account) XPathUtils.eval(getForm(), ".");
		if (rg.getSelectedIndex() == 1) {
			if (!cf.attributeValidateAll())
				return;
			String key = (String) cf.getValue();
			acc = EJBLocator.getAccountService().setAccountSshPrivateKey(acc, key);
		}
		else {
			acc = EJBLocator.getAccountService().generateAccountSshPrivateKey(acc);
		}
		XPathUtils.setValue(getForm(), "sshPublicKey", acc.getSshPublicKey());
		w.setVisible(false);
	}
	
	public void configurePasswordManager() {
		if (Security.isUserInRole("sso:passwordManager"))
			response(null, new org.zkoss.zk.au.out.AuScript(this, "doRegisterVault()"));
	}
}
