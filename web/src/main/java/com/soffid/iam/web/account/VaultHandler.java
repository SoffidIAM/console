package com.soffid.iam.web.account;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

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
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;

public class VaultHandler extends FrameHandler {
	private Account account;

	public VaultHandler() throws InternalErrorException {
		com.soffid.iam.api.Tenant masterTenant = com.soffid.iam.ServiceLocator.instance().getTenantService().getMasterTenant();
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
	}
		

	public void onChangeForm(Event ev) throws Exception {
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
			} else {
				updateAccountIcons();
			}
		}
		updateStatus();
	}

	public void updateAccountIcons() {
		Long id = (Long) XPathUtils.eval(getForm(), "id");
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
		} else {
			getFellow("set_password_button").setVisible(accessLevel == AccountAccessLevelEnum.ACCESS_MANAGER || accessLevel == AccountAccessLevelEnum.ACCESS_OWNER);
			getFellow("unlock_button").setVisible(false);
			getFellow("view_password_button").setVisible(
					type != AccountType.PRIVILEGED && ( accessLevel == AccountAccessLevelEnum.ACCESS_MANAGER || accessLevel == AccountAccessLevelEnum.ACCESS_OWNER) );
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
	}

	public void setPassword(Event event) throws InternalErrorException, NamingException, CreateException, CommitException {
		getForm().getDataSource().commit();
		Account acc = (Account) XPathUtils.eval(getForm(), "instance");
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
		updateAccountIcons();
	}

	public void unlockAccount() throws WrongValueException, InternalErrorException, NamingException, CreateException {
		final Account account = (Account) XPathUtils.getValue(getForm(), "/.");

		Missatgebox.confirmaOK_CANCEL("Please, confirm you want to return this account",
				(Event evt) ->  {
						if ("onOK".equals(evt.getName())) {
							SelfService ejb = EJBLocator.getSelfService();
							ejb.checkinHPAccount(account);
							XPathUtils.setValue(getForm(), "lockedBy", null);
							updateAccountIcons();
						}
					} );

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
		}
		else
		{
			com.soffid.iam.api.Password pawd = EJBLocator.getSelfService().queryAccountPassword(account);
			if(pawd!=null){
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
					showPassword.setVisible(true);
					showPassword.setMode("highlighted");
			}
			else{
				Missatgebox.avis(org.zkoss.util.resource.Labels.getLabel("selfService.EmptyField"));
			}
		}	
	}
	
	public void closeShowPassword() throws NamingException, CreateException, WrongValueException, InternalErrorException, InterruptedException {
		Window showPassword = (Window) getFellow("showPassword");
		showPassword.setVisible(false);
	}
	
	public void launch() throws NamingException, CreateException, WrongValueException, InternalErrorException, InterruptedException, UnsupportedEncodingException {
		Account account = (Account) XPathUtils.getValue(getForm(), "/.");
		new LaunchHelper().launchAccount(account);
	}

	@Override
	public boolean insertBefore(Component newChild, Component refChild) {
		boolean b = super.insertBefore(newChild, refChild);
		if ( newChild instanceof DataModel) {
			HttpServletRequest request = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
			String requestedAccount = request.getParameter("account");
			String requestedSystem = request.getParameter("system");
			if (requestedAccount != null && requestedSystem != null)
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
	
}
