package com.soffid.iam.web.self;

import java.util.Iterator;
import java.util.Set;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.Password;
import com.soffid.iam.service.ejb.AccountService;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.Databox;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;


public class MyAccountsHandler extends FrameHandler {

	public MyAccountsHandler() throws InternalErrorException {
		super();
	}
	
	public void setPassword(Event event) {
		Account account = (Account) ((DataNode)XPathUtils.getValue(getListbox(), "/.")).getInstance();
		Window wnd = (Window) getFellow("newPassword");
		Databox p1 = (Databox) wnd.getFellow("p1");
		Databox p2 = (Databox) wnd.getFellow("p2");
		p1.setValue("");
		p2.setValue("");
		p1.setWarning(null, "");
		p2.setWarning(null, "");
		
		Databox l = (Databox) wnd.getFellow("policy");
		String policy = "";
		try {
			policy = EJBLocator.getPasswordService().getPolicyDescription(account.getName(), account.getSystem());
		} catch (Exception e) {
			policy = "";
		}
		l.setValue(policy);
		
		Set<String> systems = new java.util.HashSet<>();
		for (Iterator it = getModel().getJXPathContext().iterate("/account/dispatcherInformation/description");
				it.hasNext();)
		{
			systems.add((String) it.next());
		}
		StringBuffer sb = new StringBuffer();
		for ( String system: systems) {
			sb.append(system).append("\n");
		}
		((Databox)wnd.getFellow("notice")).setValue(sb.toString());
		wnd.doHighlighted();
	}
	
	public void onCancelPassword() {
		Window wnd = (Window) getFellow("newPassword");
		Databox p1 = (Databox) wnd.getFellow("p1");
		Databox p2 = (Databox) wnd.getFellow("p2");
		p1.setValue("");
		p2.setValue("");
		wnd.setVisible(false);
		getModel().refresh();
	}
	
	public void onSetPassword() throws InternalErrorException, NamingException, CreateException {
		Window wnd = (Window) getFellow("newPassword");
		Databox p1 = (Databox) wnd.getFellow("p1");
		Databox p2 = (Databox) wnd.getFellow("p2");
		String pp1 = (String) p1.getValue();
		String pp2 = (String) p2.getValue();
		if (pp1 == null || pp1.trim().isEmpty())
		{
			p1.setWarning(null, "Please, enter a password");
		}
		else if (pp2 == null || pp2.trim().isEmpty())
		{
			p1.setWarning(null,  "");
			p2.setWarning(null, "Please, enter the password twice");
		}
		else if ( ! pp2.equals(pp1)) 
		{
			p1.setWarning(null,  "");
			p2.setWarning(null, "Please, enter the password twice");
		}
		else
		{
			p1.setWarning(null,  "");
			p2.setWarning(null,  "");
			Account account = (Account) ((DataNode)XPathUtils.getValue(getListbox(), "/.")).getInstance();
			EJBLocator.getSelfService().setAccountPassword(account, new Password(pp1));
			getModel().refresh();
			es.caib.zkib.zkiblaf.Missatgebox
			.avis(org.zkoss.util.resource.Labels.getLabel("accounts.setPassword.msg"));
			onCancelPassword();
		}
	}


	public void nextPassword() {
		Window wnd = (Window) getFellow("newPassword");
		Databox p2 = (Databox) wnd.getFellow("p2");
		p2.focus();
	}

	public void viewPassword() throws NamingException, CreateException, WrongValueException, InternalErrorException, InterruptedException {
		Window showPassword = (Window) getFellow("showPassword");
		((Textbox)showPassword.getFellow("qpassword")).setValue("");
		((Label)showPassword.getFellow("popupPwd")).setValue("");

		Account account = (Account) ((DataNode)XPathUtils.getValue(getListbox(), "/.")).getInstance();
		AccountService service = com.soffid.iam.EJBLocator.getAccountService();

		com.soffid.iam.api.Password pawd = service.queryAccountPassword(account);
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
	
	public void closeShowPassword() throws NamingException, CreateException, WrongValueException, InternalErrorException, InterruptedException {
		Window showPassword = (Window) getFellow("showPassword");
		showPassword.setVisible(false);
	}
	
}
