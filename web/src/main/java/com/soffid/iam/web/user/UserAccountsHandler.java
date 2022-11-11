package com.soffid.iam.web.user;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Executions;
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
import com.soffid.iam.api.Password;
import com.soffid.iam.service.ejb.UserService;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.component.Switch;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;


public class UserAccountsHandler extends Div implements AfterCompose {
	private static final long serialVersionUID = 1L;
	private String listboxPath;
	private DataTree2 tree;
	
	private UserService userService;
	
	public void updateStatus(Event event) {
		UserAccountsTree tree = (UserAccountsTree) getFellow("tree");
		tree.updateClient();
	}
	
	public UserAccountsHandler() throws NamingException, CreateException {
		userService = EJBLocator.getUserService();
	}
	
	public void afterCompose() {
		listboxPath = (String) Executions.getCurrent().getArg().get("listbox");
		tree = (DataTree2) getFellow("tree");
	}
	
	public void openPasswordWindow(Event event) {
		DataSource listbox = (DataSource) XPathUtils.getPath(getPage(), listboxPath);
		Boolean active = (Boolean) listbox.getJXPathContext().getValue("active");
		if (Boolean.TRUE.equals(active)) {
			Window w = (Window) getFellow("newPassword2");
			Radiogroup gt = (Radiogroup) w.getFellow("generationType");
			Radio radioRandom = (Radio) w.getFellow("generationRandom");
			gt.setSelectedItem(radioRandom);
			Switch s = (Switch) w.getFellow("temporary");
			s.setChecked(true);
			onChangeSelectedGeneration(null);
			w.doHighlighted();
		} else {
			Missatgebox.avis("The user is disabled. The password cannot be changed yet");
		}
	}
	
	public void onCancelPassword(Event event) {
		Window w = (Window) getFellow("newPassword2");
		Textbox p = (Textbox) w.getFellow("password");
		p.setValue("");
		w.setVisible(false);
		if (event != null) event.stopPropagation();
	}
	
	public void onSetPassword(Event event) throws CommitException, WrongValueException, InternalErrorException, BadPasswordException
	{
		DataSource listbox = (DataSource) XPathUtils.getPath(getPage(), listboxPath);
		listbox.commit();

		Window w = (Window) getFellow("newPassword2");
		Radiogroup gt = (Radiogroup) w.getFellow("generationType");
		Radio radioRandom = (Radio) w.getFellow("generationRandom");

		String user = (String) listbox.getJXPathContext().getValue("userName");
		String domain = (String) tree.getJXPathContext().getValue("name");
		
		Switch s = (Switch) w.getFellow("temporary");
		if (gt.getSelectedItem() != radioRandom)
		{
			Textbox password = (Textbox) w.getFellow("password");
			if (s.isChecked())
				userService.setTemporaryPassword(user, domain, new Password(password.getValue()));
			else
				userService.setPassword(user, domain, new Password(password.getValue()));
			es.caib.zkib.zkiblaf.Missatgebox
					.avis(org.zkoss.util.resource.Labels
							.getLabel("accounts.setPassword.msg"));
		}
		else
		{
			String nouPassword = userService.setTemporaryPassword(user, domain);
			showPasswordAssist(domain, nouPassword);
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
		Switch s = (Switch) w.getFellow("temporary");
		
		if (generationType.getSelectedItem() != radioRandom)
		{
			s.getParent().setVisible(Security.isUserInRole("user:password:set-no-temporary"));
			password.setDisabled (false);
			password.setFocus(true);
			passworddiv.setStyle("visibility: visible");
		} else {
			s.getParent().setVisible(false);
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
	
	void showPasswordAssist (String domain, String pwd) {
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
		((Label)wnd.getFellow("lbInfoNouPass")).setValue( String.format(org.zkoss
				.util.resource.Labels.getLabel("canviPassword.CanviPasswordDomini"),
				new Object [] {domain}) );
		wnd.setTitle(String.format(org.zkoss.util.resource.Labels
				.getLabel("canviPassword.NouPasswordDomini"),
				new Object [] {domain}));
		wnd.doHighlighted();
	}
	
	public void openAccount(Event event) {
		Object item = ((DataNode) tree.getJXPathContext().getValue("/")).getInstance();
		if (item instanceof Account) {
			Window w = 
					(Window) getDesktop()
					.getPage("user-account-details")
					.getFellow("window");
			Events.postEvent(new Event("onStart", w));
		}
	}
	
	public void addNew (Event event) {
		Window w = (Window) getDesktop()
				.getPage("user-account-add")
				.getFellow("window");
		Events.postEvent(new Event("onStart", w));
	}
}
