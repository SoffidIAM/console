package com.soffid.selfservice.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.json.JSONException;
import org.json.JSONObject;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.ComponentNotFoundException;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.ClientInfoEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Box;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AccessTree;
import com.soffid.iam.api.AccessTreeExecution;
import com.soffid.iam.api.AccessTreeExecutionType;
import com.soffid.iam.api.VaultFolder;
import com.soffid.iam.service.ejb.SelfService;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;
import com.soffid.iam.utils.TipusAutoritzacioPuntEntrada;
import com.soffid.iam.web.launcher.ApplicationLauncher;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.ExecucioPuntEntrada;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.comu.PuntEntrada;
import es.caib.seycon.ng.comu.UserAccount;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.ejb.AccountService;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.events.SerializableEventListener;
import es.caib.zkib.zkiblaf.Missatgebox;

public class SelfServiceHandler extends com.soffid.iam.web.component.Frame 
{
	private String ambit;

	public SelfServiceHandler () throws CreateException, NamingException, InternalErrorException
	{
		SelfService servei = EJBLocator.getSelfService();
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

	void onClientInfo (Event ev) {
		if (ev instanceof ClientInfoEvent)
		{
			ClientInfoEvent event = (ClientInfoEvent) ev;
			try 
			{
				Box form = (Box) Path.getComponent("//selfservice/fusuaris");
				if (form != null){
					int heigthPantalla = event.getDesktopHeight();
					int hPantalla2 = (int) ((heigthPantalla)*0.90);
					form.setHeight(""+hPantalla2+"px");
				}
				
			} catch (ComponentNotFoundException e) {}
			int ample = event.getDesktopWidth();
			if(ample <568){
				Listheader headQ = (Listheader) getFellowIfAny("headQuery");
				if (headQ != null)
					headQ.setLabel(org.zkoss.util.resource.Labels.getLabel("selfService.QueryPasswords2"));
				Listheader headP = (Listheader) getFellowIfAny("headChange");
				if (headP != null)
					headP.setLabel(org.zkoss.util.resource.Labels.getLabel("selfService.ChangePassword2"));
			}else{
				Listheader headQ = (Listheader) getFellowIfAny("headQuery");
				if (headQ != null)
					headQ.setLabel(org.zkoss.util.resource.Labels.getLabel("selfService.QueryPasswords"));
				Listheader headP = (Listheader) getFellowIfAny("headChange");
				if (headP != null)
					headP.setLabel(org.zkoss.util.resource.Labels.getLabel("selfService.ChangePassword"));
			}
		}
	}

	public void onCreate() {
		model = (DataModel) getFellowIfAny("model");
		newPasswordS = (Window) getFellowIfAny("newPasswordS");
		newPassword = (Window) getFellowIfAny("newPassword");
		showPassword = (Window) getFellowIfAny("showPassword");

		addEventListener("onClientInfo", new SerializableEventListener() {
			
			public void onEvent(Event event) throws Exception {
				onClientInfo(event);
			}
		});
		
		addEventListener("onReturn", new SerializableEventListener() {
			
			public void onEvent(Event event) throws Exception {
				Events.postEvent("onReturn", getFellow("inboxhandler"), null); 
			}
		});
		
		
		Component l = getFellowIfAny("listadoAccounts");
		if (l !=null)
		{
			l.addEventListener("onNewRow", new SerializableEventListener() {
				
				public void onEvent(Event event) throws Exception {
					onNewAccount((Listitem) event.getData());
				}
			});
		}
		
		try {
			final Textbox appfinder = (Textbox) getFellow("appfinder");
			appfinder.addEventListener("onChanging", new SerializableEventListener() {
				public void onEvent(Event event) throws Exception {
					if (event instanceof InputEvent)
						search(((InputEvent) event).getValue());
				}
			});
	
			appfinder.addEventListener("onOK", new SerializableEventListener() {
				public void onEvent(Event event) throws Exception {
					search(appfinder.getValue());
				}
			});
	
			appfinder.addEventListener("onCancel", new SerializableEventListener() {
				public void onEvent(Event event) throws Exception {
					appfinder.setValue("");
					search(null);
				}
			});
			try {
				getFellow("appcancelbutton").addEventListener("onClick", new SerializableEventListener() {
					
					public void onEvent(Event event) throws Exception {
						appfinder.setValue("");
						search(null);
					}
				});
			} catch (ComponentNotFoundException e) {}
		} catch (ComponentNotFoundException e) {}
	
		if (getTreebox() != null)
		{
			getTreebox().addEventListener("onNewRow", new SerializableEventListener() {
				public void onEvent(Event event) throws Exception {
					carregaIcona((Component) event.getData());
				}
			});
			getTreebox().addEventListener("onSelect", new SerializableEventListener() {
				public void onEvent(Event event) throws Exception {
					select();
				}
			});
		}



				
		if (model != null)
			model.getVariables().declareVariable("query", false);
		
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
				es.caib.seycon.ng.servei.ejb.SelfService service = es.caib.seycon.ng.EJBLocator.getSelfService();
				String afectats = service.queryOtherAffectedAccounts(account);
				if(afectats != null){
					Missatgebox.confirmaOK_CANCEL(String.format(org.zkoss.util.resource.Labels.getLabel("selfService.Segur"),
							new Object[] {afectats}), 
							org.zkoss.util.resource.Labels.getLabel("selfService.Segur2") , 
							new SerializableEventListener(){
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
			es.caib.seycon.ng.servei.ejb.AccountService service = es.caib.seycon.ng.EJBLocator.getAccountService();
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
		return (Tree) getFellowIfAny("treebox");
	}
	
	void select() throws Exception{
		openTree(getTreebox().getSelectedItem());
	}
	
	void carregaIcona(Component r) throws IOException{
		es.caib.zkib.binder.BindContext ctx = es.caib.zkib.datasource.XPathUtils.getComponentContext(r);
		try {
			org.zkoss.zul.Image img = null ;
			Treeitem item = (Treeitem) r;
			Treerow row = item.getTreerow();
			if ( row != null && row.getChildren().size() > 0)
			{
				Treecell c = (Treecell) row.getChildren().get(0);
				if ( c.getChildren().size() > 0)
				{
					img = (Image) c.getChildren().get(0);
				}
			}
			Object value = ((DataNode)XPathUtils.getValue(ctx, ".")).getInstance();
			if (value != null && value instanceof PuntEntrada)
			{
				PuntEntrada pe = (PuntEntrada) value;
				byte [] imgIcona1 = pe.getImgIcona1();
				String tm = pe.getMenu();
				if (tm.equals("N"))
					item.getTreechildren().setParent(null);
				if (img != null)
				{
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
				}
			} else if (value instanceof VaultFolder && img != null) {
				img.setSrc("/img/root.gif");
			} else if (value instanceof Account && img != null){
				String url = (String) ((Account)value).getAttributes().get("SSO:URL");
				if (url == null || url.trim().length() == 0)
					((HtmlBasedComponent)
						((Component)
							((Component)r.getChildren().get(0))
							.getChildren().get(0))
						.getChildren().get(2))
					.setStyle("color: #EEEEEE; ");
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
	
	public void openTree(Treeitem selected) throws Exception{
		es.caib.zkib.binder.BindContext ctx = XPathUtils.getComponentContext(selected);
		DataNode obj2 = (DataNode) XPathUtils.getValue(ctx, ".");
		Object obj = obj2.getInstance();
		if(obj instanceof PuntEntrada){
			openEntryPoint(selected, obj);
				
		} else if (obj instanceof com.soffid.iam.api.Account) {
			openAccount(selected, obj);
		}
	}
	private void openAccount(Treeitem selected, Object obj)
			throws InternalErrorException, NamingException, CreateException {
		selected.setSelected(false);
		com.soffid.iam.api.Account account = (com.soffid.iam.api.Account) obj;
		String url = account.getLoginUrl();
		if ( url == null || url.trim().isEmpty())
			url = (String) account.getAttributes().get("SSO:URL");
		if (url != null)
		{
			url.replaceAll("'", "\\'");
			String name = account.getName();
			if (account.getLoginName() != null && ! account.getLoginName().isEmpty())
				name = account.getLoginName();
			com.soffid.iam.api.Password password = EJBLocator.getSelfService().queryAccountPasswordBypassPolicy(account);
			boolean ssokm = "true".equals(ConfigurationCache.getProperty("soffid.ssokm.enable"));
			if (password == null || ! ssokm)
				Clients.evalJavaScript("window.open('"+url+"', '_blank');");
			else
			{
				JSONObject j = new JSONObject();
				for (String att: account.getAttributes().keySet())
				{
					if (att.startsWith("SSO:") &&
							!att.equals("SSO:URL") && 
							!att.equals("SSO:Server"))
					{
						try {
							String value = (String) account.getAttributes().get(att);
							String[] values = value.split("=");
							if (values.length == 2)
							{
								j.put( URLDecoder.decode(values[0], "UTF-8"), 
										URLDecoder.decode(values[1], "UTF-8"));
							}
						} catch (Exception e) {}
					}
				}
				try {
					j.put("url", url);
					j.put("account", name);
					j.put("password", password.getPassword());
					Clients.evalJavaScript("launchSsoUrl("+j.toString()+");");
				} catch (JSONException e) {
					Clients.evalJavaScript("window.open('"+url+"', '_blank');");
				}
			}
		}
	}

	private void openEntryPoint(Treeitem selected, Object obj)
			throws Exception {
		PuntEntrada instance = (PuntEntrada) obj;
		if(!selected.isOpen() && instance.getMenu().equals("S")){
			selected.setOpen(true);
		}else if (instance.getMenu().equals("S"))
			selected.setOpen(false);
		else{
			Long name = instance.getId();
			ExecucioPuntEntrada exe = findExecucio ( instance.getExecucions() );
			if (exe != null)
			{
				Collection<ExecucioPuntEntrada> punt = instance.getExecucions();
				if(!punt.isEmpty())
				{
					boolean ssokm = "true".equals(ConfigurationCache.getProperty("soffid.ssokm.enable"));
					String system = instance.getSystem();
					String type = exe.getCodiTipusExecucio();
					List<com.soffid.iam.api.Account> accounts = fetchAccounts(instance);
					Class executor = findExecutionType( exe );
					if (executor != null)
					{
						ApplicationLauncher l = (ApplicationLauncher) executor.newInstance();
						l.open(AccessTree.toAccessTree(instance),
								AccessTreeExecution.toAccessTreeExecution(exe),
								accounts);
					}
	 				else
						Clients.evalJavaScript("window.open('execucions?id="+id+"', '_blank');");
  	                       }
        	               else
                	                Messagebox.show("Cannot execute");
 
			}
			else
				Messagebox.show("Cannot execute");
		}
	}
	
	private Class findExecutionType(ExecucioPuntEntrada exe) throws InternalErrorException, NamingException, CreateException {
		for (AccessTreeExecutionType et: EJBLocator.getEntryPointService().getAllMimeTypeExecution())
		{
			if (et.getCode().equals(exe.getCodiTipusExecucio()) && et.getJavaClass() != null)
			{
				Class c;
				try {
					c = Class.forName(et.getJavaClass());
					if (c != null && ApplicationLauncher.class.isAssignableFrom(c))
					{
						return c;
					}
				} catch (ClassNotFoundException e) {
				}
			}
		}
		return null;
	}
	
	public String encodeJS(String url) {
		return url.replaceAll("\\\\","\\\\\\\\").replaceAll("'", "\\'");
	}

	public void openWssoEntryPoint(PuntEntrada instance, ExecucioPuntEntrada exe, List<com.soffid.iam.api.Account> accounts)
			throws InternalErrorException, NamingException, CreateException {
		// Get passwords
		List<Object[]> r = new LinkedList<Object[]>();
		SelfService sss = EJBLocator.getSelfService();
		com.soffid.iam.service.ejb.AccountService accountService = EJBLocator.getAccountService();
		for (com.soffid.iam.api.Account account: accounts)
		{
			if (accountService.isAccountPasswordAvailable(account.getId()))
				r.add(new Object [] { account.getLoginName(), account.getDescription(), account });
		}
		if (r.size() == 0)
		{
			throw new UiException("Sorry. There is no account available to open this service");
		}
		else if (r.size() == 1)
		{
			try {
				Object[] row = r.get(0);
				com.soffid.iam.api.Account account = accountService.findAccountById((Long)row[2]);
				com.soffid.iam.api.Password password = EJBLocator.getSelfService().queryAccountPasswordBypassPolicy(account);
				if (password == null)
					throw new InternalErrorException(
							String.format("Unable to fetch password for account %s at %s",
									account.getName(),
									account.getSystem()));
				
				JSONObject j = new JSONObject();
				j.put("url", exe.getContingut());
				j.put("account", row[0]);
				j.put("password", password.getPassword());
				Clients.evalJavaScript("launchSsoUrl("+j.toString()+");");
			} catch (JSONException e) {
				Clients.evalJavaScript("window.open('"+exe.getContingut()+"', '_blank');");
			}
		} else {
			currentEntryPoint = instance;
			currentExecutionMethod = exe;
			Window w = (Window) getFellow ("selectAccount");
			Listbox lb = (Listbox) w.getFellow("accountsListbox");
			lb.getItems().clear();
			Collections.sort(r, new Comparator<Object[]>() {
				public int compare(Object[] o1, Object[] o2) {
					return o1[0].toString().compareTo(o2[0].toString());
				}
				
			});
			for ( Object[] row: r)
			{
				Listitem item = new Listitem();
				item.setValue(row);
				item.appendChild(new Listcell(row[0].toString()));
				item.appendChild(new Listcell(row[1].toString()));
				lb.appendChild(item);
			}
			w.doHighlighted();
		}
	}

	private ExecucioPuntEntrada findExecucio(Collection<ExecucioPuntEntrada> execucions) throws InternalErrorException {
		String scope = com.soffid.iam.ServiceLocator.instance()
				.getEntryPointService()
				.getScopeForAddress(Security.getClientIp());
		ExecucioPuntEntrada selected = null;
		if ("L".equals(scope))
			selected = findExecucio (execucions, "L");
		if ("W".equals(scope) || "L".equals(scope) && selected == null)
			selected = findExecucio (execucions, "W");
		if (selected == null)
			selected = findExecucio (execucions, "I");
		return selected;
	}
	
	private ExecucioPuntEntrada findExecucio(Collection<ExecucioPuntEntrada> execucions, String scope) {
		for (ExecucioPuntEntrada exe: execucions)
			if (scope.equals(exe.getAmbit()))
				return exe;
		return null;
	}
	public List<com.soffid.iam.api.Account> fetchAccounts(PuntEntrada instance) throws InternalErrorException, NamingException, CreateException {
		String user = Security.getCurrentUser();
		String system = instance.getSystem();
		List<com.soffid.iam.api.Account> accounts = new LinkedList<com.soffid.iam.api.Account>();
		// Get accounts list
		Security.nestedLogin(Security.getCurrentAccount(), Security.ALL_PERMISSIONS);
		try {
			com.soffid.iam.service.ejb.AccountService accountService = EJBLocator.getAccountService();
			if (system != null &&  !system.trim().isEmpty())
			{
				accounts.addAll(accountService.findUsersAccounts(user, system));
				for (com.soffid.iam.api.Account acc: accountService.findSharedAccountsByUser(user))
				{
					if (acc.getSystem().equals(system))
						accounts.add(acc);
				}
			}
			
			for ( AutoritzacioPuntEntrada auth: instance.getAutoritzacions())
			{
				if (auth.getTipusEntitatAutoritzada().equals(TipusAutoritzacioPuntEntrada.ACCOUNT) &&
						auth.getDescripcioNivellAutoritzacio().equals(TipusAutoritzacioPuntEntrada.NIVELL_QUERY_DESCRIPCIO))
				{
					accounts.add(accountService.findAccountById(auth.getIdEntitatAutoritzada()));
				}
			}
			
		} finally {
			Security.nestedLogoff();
		}
		return accounts;
	}
	
	protected void launchAccount () throws InternalErrorException, NamingException, CreateException
	{
		Window w = (Window) getFellow ("selectAccount");
		Listbox lb = (Listbox) w.getFellow("accountsListbox");
		Listitem selected = lb.getSelectedItem();
		if (selected != null)
		{
			String[] values = (String[]) selected.getValue();
			try {
				JSONObject j = new JSONObject();
				j.put("url", values[3]);
				j.put("account", values[0]);
				j.put("password", Password.decode(values[2]).getPassword());
				Clients.evalJavaScript("launchSsoUrl("+j.toString()+");");
			} catch (JSONException e) {
				Clients.evalJavaScript("window.open('"+values[3]+"', '_blank');");
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
				new SerializableEventListener() {
					public void onEvent(Event evt) throws CreateException, NamingException, InternalErrorException {
						if ("onOK".equals(evt.getName())) {
							AccountService ejb = es.caib.seycon.ng.EJBLocator.getAccountService();
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
			v.declareVariable("queryName", null);
		} else {
			v.declareVariable("query", true);
			v.declareVariable("queryName", s);
		}
		((DataNodeCollection)model.getValue("/moure")).refresh();
		((DataNodeCollection)model.getValue("/folder")).refresh();
		((DataNodeCollection)model.getValue("/folderAccount")).refresh();
	}
}
