package com.soffid.selfservice.web;

import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.event.ClientInfoEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.*;
import org.zkoss.zul.Image;

import es.caib.zkib.zkiblaf.Frame;
import es.caib.zkib.zkiblaf.Missatgebox;

import org.zkoss.image.*;
import org.zkoss.zul.*;

import com.soffid.iam.api.VaultFolder;

import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.Form;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datamodel.DataModelNode;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.seycon.ng.comu.*;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.ejb.AccountServiceHome;
import es.caib.seycon.ng.servei.ejb.SelfServiceHome;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;


public class SelfServiceHandler extends Frame 
{
	private String ambit;

	public SelfServiceHandler () throws CreateException, NamingException, InternalErrorException
	{
		es.caib.seycon.ng.servei.ejb.SelfService servei = ((SelfServiceHome)new javax.naming.InitialContext()
			.lookup(es.caib.seycon.ng.servei.ejb.SelfServiceHome.JNDI_NAME))
			.create();
		ambit = (servei.getClientHost());
	}
	public String getAmbit() {
		return ambit;
	}

	public void setAmbit(String ambit) {
		this.ambit = ambit;
	}
	private DataModel model;

	private Window newPassword;

	private Window showPassword;

	private Form fusuaris;

	void onClientInfo (Event ev) {
		if (ev instanceof ClientInfoEvent)
		{
			ClientInfoEvent event = (ClientInfoEvent) ev;
			Box form = (Box) Path.getComponent("//selfservice/fusuaris");
			if (form != null){
				int heigthPantalla = event.getDesktopHeight();
				int hPantalla2 = (int) ((heigthPantalla)*0.90);
				form.setHeight(""+hPantalla2+"px");
				int ample = event.getDesktopWidth();
				if(ample <568){
					Listheader headQ = (Listheader) getFellow("headQuery");
					headQ.setLabel(org.zkoss.util.resource.Labels.getLabel("selfService.QueryPasswords2"));
					Listheader headP = (Listheader) getFellow("headChange");
					headP.setLabel(org.zkoss.util.resource.Labels.getLabel("selfService.ChangePassword2"));
				}else{
					Listheader headQ = (Listheader) getFellow("headQuery");
					headQ.setLabel(org.zkoss.util.resource.Labels.getLabel("selfService.QueryPasswords"));
					Listheader headP = (Listheader) getFellow("headChange");
					headP.setLabel(org.zkoss.util.resource.Labels.getLabel("selfService.ChangePassword"));
				}
			}
		}
	}

	public void onCreate() {
		model = (DataModel) getFellow("model");
		newPasswordS = (Window) getFellow("newPasswordS");
		newPassword = (Window) getFellow("newPassword");
		showPassword = (Window) getFellow("showPassword");
		fusuaris = (Form) getFellow("fusuaris");

		addEventListener("onClientInfo", new EventListener() {
			
			public void onEvent(Event event) throws Exception {
				onClientInfo(event);
			}
		});
		
		addEventListener("onReturn", new EventListener() {
			
			public void onEvent(Event event) throws Exception {
				Events.postEvent("onReturn", getFellow("inboxhandler"), null); 
			}
		});
		
		getFellow("listadoAccounts").addEventListener("onNewRow", new EventListener() {
			
			public void onEvent(Event event) throws Exception {
				onNewAccount((Listitem) event.getData());
			}
		});
		
		final Textbox appfinder = (Textbox) getFellow("appfinder");
		appfinder.addEventListener("onChanging", new EventListener() {
			public void onEvent(Event event) throws Exception {
				if (event instanceof InputEvent)
					search(((InputEvent) event).getValue());
			}
		});

		appfinder.addEventListener("onOK", new EventListener() {
			public void onEvent(Event event) throws Exception {
				search(appfinder.getValue());
			}
		});

		appfinder.addEventListener("onCancel", new EventListener() {
			public void onEvent(Event event) throws Exception {
				appfinder.setValue("");
				search(null);
			}
		});

		getTreebox().addEventListener("onNewRow", new EventListener() {
			public void onEvent(Event event) throws Exception {
				carregaIcona((Component) event.getData());
			}
		});

		getTreebox().addEventListener("onSelect", new EventListener() {
			public void onEvent(Event event) throws Exception {
				select();
			}
		});

		getFellow("appcancelbutton").addEventListener("onClick", new EventListener() {
			
			public void onEvent(Event event) throws Exception {
				appfinder.setValue("");
				search(null);
			}
		});
				
		model.getVariables().declareVariable("query", false);
		
/*		enableTimers = false;
		
		es.caib.zkib.datamodel.DataModelCollection lm = (DataModelCollection) model.getJXPathContext().getValue("/usuari/account");
		if (lm.getSize() > 10)
		{
			enableTimers = false;
		}
	*/	
		try
		{
			es.caib.zkib.zkiblaf.Application.setTitle(org.zkoss.util.resource
					.Labels.getLabel("selfService.Titol"));
			
		}
		catch (Exception ex) {}
		

	}

	boolean canUpdateUserMetadata = true;
	
	long timerDelay = 10000;

	private Window newPasswordS;
	
	public void setPassword (Component button) throws NamingException, InternalErrorException, CreateException
	{
		try{
			es.caib.zkib.binder.BindContext ctx = XPathUtils.getComponentContext(button);
			Account account = (Account) ((DataNode)XPathUtils.getValue(ctx, ".")).getInstance();
			if (account.getType().equals(AccountType.USER)){
				newPasswordS.setAttribute("acco", account);
				SelfServiceHome home = (SelfServiceHome) new javax.naming.InitialContext()
					.lookup(es.caib.seycon.ng.servei.ejb.SelfServiceHome.JNDI_NAME);
				es.caib.seycon.ng.servei.ejb.SelfService service = home.create();
				String afectats = service.queryOtherAffectedAccounts(account);
				if(afectats != null){
					Missatgebox.confirmaOK_CANCEL(String.format(org.zkoss.util.resource.Labels.getLabel("selfService.Segur"),
							new Object[] {afectats}), 
							org.zkoss.util.resource.Labels.getLabel("selfService.Segur2") , 
							new EventListener(){
								public void onEvent(Event evt) throws InterruptedException{
									if("onOK".equals(evt.getName())){
										newPasswordS.setVisible(true);
										newPasswordS.setMode("highlighted");
									}
								}
							});
				}
			}else if (account.getType().equals(AccountType.SHARED)){
				newPasswordS.setAttribute("acco", account);
				newPasswordS.setVisible(true);
				newPasswordS.setMode("highlighted");
			}else if (account.getType().equals(AccountType.PRIVILEGED)){
				newPassword.setAttribute("acco", account);
				Calendar dia = Calendar.getInstance();
				dia.add(Calendar.DAY_OF_MONTH, 1);
				((Datebox)newPassword.getFellow("timepwd")).setValue(dia.getTime());
				dia.clear();
				newPassword.setVisible(true);
				newPassword.setMode("highlighted");
			}
		} catch ( InterruptedException e) {	}
	}
	
	public void queryPassword(Component button) throws CreateException, NamingException, InternalErrorException{
		try{
			((Label)showPassword.getFellow("qpassword")).setValue("");
			((Label)showPassword.getFellow("popupPwd")).setValue("");
			es.caib.zkib.binder.BindContext ctx = XPathUtils.getComponentContext(button);
			Account account = (Account) ((DataNode)XPathUtils.getValue(ctx, ".")).getInstance();
			es.caib.seycon.ng.servei.ejb.AccountService service = ((AccountServiceHome) new javax.naming.InitialContext()
					.lookup(es.caib.seycon.ng.servei.ejb.AccountServiceHome.JNDI_NAME))
					.create();
			if(service.isUpdatePending(account) && account.getType().equals(AccountType.PRIVILEGED))
			{
				((Label)showPassword.getFellow("qpassword")).setValue(org.zkoss.util.resource.Labels.getLabel("selfService.UpdatePending"));
				((Label)showPassword.getFellow("labelPWDis")).setVisible(false);
				((Label)showPassword.getFellow("qpassword")).setMultiline(true);
				showPassword.setVisible(true);
				showPassword.setMode("highlighted");
			}
			else
			{
				Password pawd = service.queryAccountPassword(account);
				if(pawd!=null){
						String cadena = pawd.getPassword();
						String cadenaResultant = "";
						((Label)showPassword.getFellow("qpassword")).setValue(cadena);
						int i = cadena.length();
						for(int j=0; j<i; j++){
							cadenaResultant = cadenaResultant + cadena.charAt(j) + "\t";
							Character c = cadena.charAt(j);
							if(c.isUpperCase(c)){
								cadenaResultant = cadenaResultant + org.zkoss.util.resource.Labels.getLabel("selfService.Majuscula");
							}
							if(c.isLowerCase(c)){
								cadenaResultant = cadenaResultant + org.zkoss.util.resource.Labels.getLabel("selfService.Minuscula");
							}
							if(c.isDigit(c)){
								cadenaResultant = cadenaResultant + org.zkoss.util.resource.Labels.getLabel("selfService.Number");
							}
							if(!c.isLetter(c) && !c.isDigit(c)){
								cadenaResultant = cadenaResultant + org.zkoss.util.resource.Labels.getLabel("selfService.Symbol");
							}
							cadenaResultant = cadenaResultant + "\n";
						}
						((Label)showPassword.getFellow("popupPwd")).setValue(cadenaResultant);
						showPassword.getFellow("labelPWDis").setVisible(true);
				}
				else{
					((Label)showPassword.getFellow("qpassword")).setValue(org.zkoss.util.resource.Labels.getLabel("selfService.EmptyField"));
					((Label)showPassword.getFellow("popupPwd")).setValue("");
					showPassword.getFellow("labelPWDis").setVisible(true);
				}
				showPassword.setVisible(true);
				showPassword.setMode("highlighted");
			}	
		} catch ( InterruptedException e){}
	}
	
	Tree getTreebox() {
		return (Tree) fusuaris.getFellow("treebox");
	}
	
	void select() throws InterruptedException{
		openTree(getTreebox().getSelectedItem());
	}
	
	void carregaIcona(Component r) throws IOException{
		es.caib.zkib.binder.BindContext ctx = es.caib.zkib.datasource.XPathUtils.getComponentContext(r);
		try {
			Object value = ((DataNode)XPathUtils.getValue(ctx, ".")).getInstance();
			if (value != null && value instanceof PuntEntrada)
			{
				PuntEntrada pe = (PuntEntrada) value;
				byte [] imgIcona1 = pe.getImgIcona1();
				String tm = pe.getMenu();
				org.zkoss.zul.Image img = (Image) ((Component)
							((Component)r.getChildren().get(0))
						.getChildren().get(0)).getChildren().get(0);
				Treeitem item = (Treeitem) r;
				if (tm.equals("N"))
					item.getTreechildren().setParent(null);
				if (imgIcona1 != null)
				{
					img.setContent(new AImage(pe.getId().toString(), imgIcona1));
				} else if (imgIcona1 == null && tm != null && tm.equals("S")){
					img.setSrc("/img/root.gif");
				} else if (imgIcona1 == null && tm != null && tm.equals("N")){
					img.setSrc("/img/punt-verd.gif");
				}
				if (tm.equals("N") && pe.getExecucions().isEmpty())
					((HtmlBasedComponent)
						((Component)
							((Component)r.getChildren().get(0))
							.getChildren().get(0))
						.getChildren().get(2))
					.setStyle("color: #EEEEEE; ");
			} else if (value instanceof VaultFolder) {
				org.zkoss.zul.Image img = (Image) ((Component)
						((Component)r.getChildren().get(0))
					.getChildren().get(0)).getChildren().get(0);
				img.setSrc("/img/root.gif");
			} else if (value instanceof Account){
				String url = (String) ((Account)value).getAttributes().get("SSO:URL");
				if (url == null || url.trim().length() == 0)
					((HtmlBasedComponent)
						((Component)
							((Component)r.getChildren().get(0))
							.getChildren().get(0))
						.getChildren().get(2))
					.setStyle("color: #EEEEEE; ");

				org.zkoss.zul.Image img = (Image) ((Component)
						((Component)r.getChildren().get(0))
					.getChildren().get(0)).getChildren().get(0);
				img.setSrc("/img/punt-verd.gif");
				
			}
		} catch (NullPointerException e) {
		} catch (es.caib.zkib.jxpath.JXPathNotFoundException e) {}
	}
	
	void checkIsUpdatePending(Component item) throws InternalErrorException
	{
		es.caib.zkib.binder.BindContext ctx = XPathUtils
				.getComponentContext(item);
		es.caib.zkib.datamodel.DataNode dn = (DataNode) XPathUtils.getValue(ctx, ".");
		
		if (dn != null)
		{
			Account acc = (Account) dn.getInstance();
			if (acc != null && acc.getId() != null)
			{
				es.caib.seycon.ng.servei.AccountService accountService =
						es.caib.seycon.ng.ServiceLocator
							.instance().getAccountService();
				
				Component img = ((Component)
							((Component)
									((Component)item.getChildren().get(2))
									.getChildren().get(0))
							.getChildren().get(2));
				img.setVisible(accountService.isUpdatePending(acc));
				
				es.caib.zkib.datasource.DataSource ds = ctx.getDataSource();
			}
		}
	}
	
	public void openTree(Treeitem selected) throws InterruptedException{
		es.caib.zkib.binder.BindContext ctx = XPathUtils.getComponentContext(selected);
		DataNode obj2 = (DataNode) XPathUtils.getValue(ctx, ".");
		Object obj = obj2.getInstance();
		if(obj instanceof PuntEntrada){
			PuntEntrada instance = (PuntEntrada) obj;
			if(!selected.isOpen() && instance.getMenu().equals("S")){
				selected.setOpen(true);
			}else if (instance.getMenu().equals("S"))
				selected.setOpen(false);
			else{
				Long name = instance.getId();
				Collection<ExecucioPuntEntrada> punt = instance.getExecucions();
				if(!punt.isEmpty())
					Clients.evalJavaScript("window.open('execucions?id="+name+"', '_blank');");
				else
					Messagebox.show("Cannot execute");
			}
				
		} else if (obj instanceof Account) {
			Account account = (Account) obj;
			String url = (String) account.getAttributes().get("SSO:URL");
			if (url != null)
			{
				url.replaceAll("'", "\\'");
				Clients.evalJavaScript("window.open('"+url+"', '_blank');");
			}
		}
	}
	
	void onNewAccount (Listitem row)
	{
		es.caib.zkib.binder.BindContext ctx  = XPathUtils.getComponentContext(row);
		try {
			String owner = (String) XPathUtils.getValue(ctx, "/owner[1]/codi");
			if (owner != null)
			{
				((Component)((Component)row.getChildren().get(2)).getChildren().get(1)).setVisible(true);
				if (owner.equals ( Executions.getCurrent().getUserPrincipal().getName()))
					((Component)
							((Component)
									((Component)row.getChildren().get(2))
									.getChildren().get(1))
							.getChildren().get(2))
					.setVisible(true);
			}
		} catch (es.caib.zkib.jxpath.JXPathNotFoundException e) 
		{
			
		}
			
	}
	
	public void checkinAccount (Component c)
	{
		es.caib.zkib.binder.BindContext ctx = XPathUtils.getComponentContext(c);
		final Account acc = (Account) ((DataNode)XPathUtils.getValue(ctx, ".")).getInstance();
		es.caib.zkib.zkiblaf.Missatgebox.confirmaOK_CANCEL("Please, confirm you want to return this account", 
				new org.zkoss.zk.ui.event.EventListener() {
					public void onEvent(Event evt) throws CreateException, NamingException, InternalErrorException {
						if ("onOK".equals(evt.getName()))
						{
							javax.naming.Context ctx = new javax.naming.InitialContext();
							es.caib.seycon.ng.servei.ejb.AccountService ejb =
									((AccountServiceHome)
									 ctx.lookup( es.caib.seycon.ng.servei.ejb.AccountServiceHome.JNDI_NAME ))
								.create();
							ejb.checkinHPAccount(acc);
							model.refresh();
						}
					}
			}
		);
	}
	
	void search (String s) throws Exception 
	{
		es.caib.zkib.jxpath.JXPathContext ctx = model.getJXPathContext();
		es.caib.zkib.jxpath.Variables v = ctx.getVariables();
		if (s == null || s.trim().isEmpty())
		{
			v.declareVariable("query", false);
		} else {
			v.declareVariable("query", true);
			v.declareVariable("queryName", s);
		}
		((DataNodeCollection)model.getValue("/moure")).refresh();
	}
}
