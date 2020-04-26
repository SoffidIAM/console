package com.soffid.iam.web.vault;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.VaultFolder;
import com.soffid.iam.service.ejb.SelfService;
import com.soffid.iam.web.component.Frame;

import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.binder.BindContext;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.Embed;
import es.caib.zkib.component.Form;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Esquema;
import es.caib.zkib.zkiblaf.EsquemaVertical;
import es.caib.zkib.zkiblaf.ImageClic;
import es.caib.zkib.zkiblaf.Missatgebox;

public class VaultFrame extends Frame {
	BindContext currentContext;
	private boolean canCreateRootFolders;
	
	public VaultFrame() {
		super();
		try
		{
			es.caib.zkib.zkiblaf.Application.setTitle(org.zkoss.util.resource
				.Labels.getLabel("vault.title"));
		}
		catch (Exception ex){}
	}
	

	public void init()
	{
		getTreebox().addEventListener("onNewRow", onNewTreeRow);
		getModel().getVariables().declareVariable("filter", "");
		HttpServletRequest request = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		String requestedAccount = request.getParameter("account");
		String requestedSystem = request.getParameter("system");
		getModel().getVariables().declareVariable("account", requestedAccount);
		getModel().getVariables().declareVariable("system", requestedSystem);
		getModel().getVariables().declareVariable("enabled", true);
		getFellow("esquema").getFellow("lista").getFellow("b_inserir").addEventListener("onClick", addRootFolder);
		if (requestedAccount != null && requestedSystem != null)
		{
			getModel().getVariables().declareVariable("directFilter", true);
			addEventListener("onPerformQuery", onPerformQuery);
			Events.postEvent(-100, "onPerformQuery", this, null);
		} else {
			getModel().getVariables().declareVariable("directFilter", false);
		}
		getModel().refresh();
	}
	
	@Override
	public void afterCompose ()
	{
		super.afterCompose();
		init();
	}
	
	protected Component getEsquema ()  {
		return getFellow("esquema");
		
	}

	protected Component getAccountWindow ()  {
		return getFellow("accountWindow");
		
	}

	protected DataModel getModel ()  {
		return (DataModel) getFellow("model");
		
	}

	
	EventListener onPerformQuery = new EventListener() {
		public void onEvent(Event event) throws Exception
		{
			Tree treebox = getTreebox();	                                          
			Treeitem firstRow = (Treeitem) treebox.getTreechildren().getFirstChild();
			if (firstRow != null)
			{
				treebox.setSelectedItem(firstRow);
				es.caib.zkib.binder.BindContext ctx = XPathUtils.getComponentContext(firstRow);
				currentContext = ctx;
				String path = ctx.getXPath();
				es.caib.seycon.ng.comu.AccountType t = (AccountType) XPathUtils.getValue(ctx, "/@type");
				if (t.equals (es.caib.seycon.ng.comu.AccountType.SHARED))
				{
					es.caib.seycon.ng.comu.AccountAccessLevelEnum level = (AccountAccessLevelEnum) XPathUtils.getValue(ctx, "/@accessLevel");
					if (level.equals(es.caib.seycon.ng.comu.AccountAccessLevelEnum.ACCESS_MANAGER) ||
							level.equals(es.caib.seycon.ng.comu.AccountAccessLevelEnum.ACCESS_OWNER))
						Events.sendEvent (new Event("onOpenAccount", getAccountWindow(), path));
					getModel().getVariables().declareVariable("directFilter", false);
				}
				else
				{
					getModel().getVariables().declareVariable("directFilter", false);
					getModel().refresh();
				}
				
			}
		}
	};
	
	public BindContext getCurrentContext() {
		return currentContext;
	}
	public void setCurrentContext(BindContext currentContext) {
		this.currentContext = currentContext;
	}
	
	public EventListener addRootFolder = new EventListener() {
		@Override
		public void onEvent(Event event) throws Exception {
			String path = XPathUtils.createPath(getModel(), "/folder");
			Events.sendEvent (new Event("onNewFolder", getFolderWindow(), path));
		}
	};
	
	
	private Component getFolderWindow() {
		return getFellow("folderWindow");
	}

	private Tree getTreebox() {
		return (Tree) getFellow("esquema").getFellow("lista"). getFellow("treebox");
	}

	void openFolder (Component component)
	{
		selectItem(component);
		es.caib.zkib.binder.BindContext ctx = currentContext;
		String path = ctx.getXPath();
		Events.sendEvent (new Event("onOpenFolder", getFolderWindow(), path));
	}

	void openAccount (Component component)
	{
		selectItem(component);
		es.caib.zkib.binder.BindContext ctx = currentContext;
		String path = ctx.getXPath();
		Events.sendEvent (new Event("onOpenAccount", getAccountWindow(), path));
	}


	void addSubFolder (Component component) throws Exception
	{
		selectItem(component);
		es.caib.zkib.binder.BindContext ctx = currentContext;
		String path = ctx.getXPath();
		String path2 = XPathUtils.createPath(getModel(), path+"/folder");
		XPathUtils.setValue(ctx.getDataSource(), path2+"/@parentId", XPathUtils.getValue(ctx, "@id"));
		XPathUtils.setValue(ctx.getDataSource(), path2+"/@parentFolder", XPathUtils.getValue(ctx, "@name"));
		XPathUtils.setValue(ctx.getDataSource(), path2+"/@personal", XPathUtils.getValue(ctx, "@personal"));
		Events.sendEvent (new Event("onNewFolder", getFolderWindow(), path2));
	}
	
	void addAccount (Component component) throws Exception
	{
		selectItem(component);
		es.caib.zkib.binder.BindContext ctx = currentContext;
		String path = ctx.getXPath();
		String path2 = XPathUtils.createPath(getModel(), path+"/account");
		XPathUtils.setValue(ctx.getDataSource(), path2+"/@vaultFolderId", XPathUtils.getValue(ctx, "@id"));
		XPathUtils.setValue(ctx.getDataSource(), path2+"/@vaultFolder", XPathUtils.getValue(ctx, "@name"));
		XPathUtils.setValue(ctx.getDataSource(), path2+"/@inheritNewPermissions", true);
		XPathUtils.setValue(ctx.getDataSource(), path2+"/@accessLevel", es.caib.seycon.ng.comu.AccountAccessLevelEnum.ACCESS_OWNER );
		Events.sendEvent (new Event("onNewAccount", getAccountWindow(), path2));
	}
	
	void removeAccount (Component component) 
	{
		selectItem(component);
		es.caib.zkib.binder.BindContext ctx = currentContext;
		String accountName = (String) XPathUtils.getValue(ctx, "@description");
		Missatgebox.confirmaOK_CANCEL(
				String.format( org.zkoss.util.resource.Labels
						.getLabel("vault.account.remove"), new Object[] { accountName }), 
				org.zkoss.util.resource.Labels.getLabel("vault.confirm"), 
				new EventListener() {
			public void onEvent(Event evt) throws CommitException {
				if ("onOK".equals(evt.getName())) {
					es.caib.zkib.binder.BindContext ctx = currentContext;
					XPathUtils.removePath(ctx.getDataSource(), ctx.getXPath());
					getModel().commit();
				}
			}
		});

	}

	void removeFolder (Component component) 
	{
		selectItem(component);
		es.caib.zkib.binder.BindContext ctx = currentContext;
		String accountName = (String) XPathUtils.getValue(ctx, "@name");
		Missatgebox.confirmaOK_CANCEL(
				String.format(org.zkoss.util.resource.Labels.getLabel("vault.folder.remove"), accountName), 
				org.zkoss.util.resource.Labels.getLabel("vault.confirm"), 
				new EventListener() {
			public void onEvent(Event evt) throws CommitException {
				if ("onOK".equals(evt.getName())) {
					es.caib.zkib.binder.BindContext ctx = currentContext;
					XPathUtils.removePath(ctx.getDataSource(), ctx.getXPath());
					getModel().commit();
				}
			}
		});

	}
	
	public void search ()
	{
		String value = ((Textbox) getEsquema().getFellow("queryWindow").getFellow("queryName").getFellow("textbox")).getValue();
		if (value == null || value.trim().length() == 0)
			getModel().getVariables().declareVariable("filter", "");
		else
			getModel().getVariables().declareVariable("filter", value);
		
		getModel().refresh();
	}
	
	void setPassword (Component button) throws InternalErrorException, NamingException, CreateException, CommitException
	{
		selectItem(button);
		getModel().commit ();
		try{
			final Window newPasswordS = (Window) getAccountWindow().getFellow("newPasswordS");
			final Window newPassword = (Window) getAccountWindow().getFellow("newPassword");
			es.caib.zkib.binder.BindContext ctx = XPathUtils.getComponentContext(button);
			com.soffid.iam.api.Account account = (Account) ((DataNode) XPathUtils.getValue(ctx, ".")).getInstance();
			if (account.getType().equals(es.caib.seycon.ng.comu.AccountType.USER)){
				SelfService service = EJBLocator.getSelfService();
				newPasswordS.setAttribute("acco", account);
				String afectats = service.queryOtherAffectedAccounts(account);
				if(afectats != null){
					Missatgebox.confirmaOK_CANCEL(String.format(org.zkoss.util.resource.Labels.getLabel("selfService.Segur"),
							new Object[] {afectats}), 
							org.zkoss.util.resource.Labels.getLabel("selfService.Segur2") , 
							new org.zkoss.zk.ui.event.EventListener(){
								public void onEvent(Event evt) throws InterruptedException{
									if("onOK".equals(evt.getName())){
										newPasswordS.setVisible(true);
										newPasswordS.setMode("highlighted");
										((Textbox)newPasswordS.getFellow("p1")).focus();
									}
								}
							});
				}
			}else if (account.getType().equals(es.caib.seycon.ng.comu.AccountType.SHARED)){
				newPasswordS.setAttribute("acco", account);
				newPasswordS.setVisible(true);
				newPasswordS.setMode("highlighted");
				((Textbox)newPasswordS.getFellow("p1")).focus();
			}else if (account.getType().equals(es.caib.seycon.ng.comu.AccountType.PRIVILEGED)){
				newPassword.setAttribute("acco", account);
				ctx = XPathUtils.getComponentContext(button);
				String path = ctx.getXPath();
				newPassword.setAttribute("path", path);
				String parent = path.substring(0, path.lastIndexOf('['));
				newPassword.setAttribute("parentPath", parent);
				java.util.Calendar dia = java.util.Calendar.getInstance();
				dia.add(java.util.Calendar.DAY_OF_MONTH, 1);
				((Datebox)newPassword.getFellow("timepwd")).setValue(dia.getTime());
				dia.clear();
				newPassword.setVisible(true);
				newPassword.setMode("highlighted");
				((Textbox)newPassword.getFellow("password")).focus();
			}
		} catch ( InterruptedException e) {	}
	}


	void queryPassword2(Component button) throws WrongValueException, InternalErrorException, NamingException, CreateException{
		try{
			selectItem (button);
			Window showPassword = (Window) getAccountWindow().getFellow("showPassword");
			
			((Textbox)showPassword.getFellow("qpassword")).setValue("");
			((Label)showPassword.getFellow("popupPwd")).setValue("");
			es.caib.zkib.binder.BindContext ctx = XPathUtils.getComponentContext(button);
			com.soffid.iam.api.Account account = (Account) ((DataNode)XPathUtils.getValue(ctx, ".")).getInstance();
			com.soffid.iam.service.ejb.AccountService service = com.soffid.iam.EJBLocator.getAccountService();
			if(service.isUpdatePending(account) && account.getType().equals(AccountType.PRIVILEGED))
			{
				Missatgebox.avis(org.zkoss.util.resource.Labels.getLabel("selfService.UpdatePending"));
			}
			else
			{
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
		} catch ( InterruptedException e){}
	}
	
	private void selectItem(Component c) {
		currentContext = XPathUtils.getComponentContext(c);
		while ( c != null && ! (c instanceof Treeitem))
		{
			c = c.getParent();
		}
		if (c != null)
			getTreebox().setSelectedItem((Treeitem) c);
	}

	private EventListener openFolderListener = new EventListener() {
		@Override
		public void onEvent(Event event) throws Exception {
			openFolder(event.getTarget());
		}
	};
	private EventListener addSubFolderListener = new EventListener() {
		public void onEvent(Event event) throws Exception {
			addSubFolder(event.getTarget());
		}
	};
	private EventListener addAccountListener = new EventListener() {
		public void onEvent(Event event) throws Exception {
			addAccount(event.getTarget());
		}
	};
	private EventListener removeFolderListener = new EventListener() {
		public void onEvent(Event event) throws Exception {
			removeFolder(event.getTarget());
		}
	};
	private EventListener queryPasswordListener = new EventListener() {
		@Override
		public void onEvent(Event event) throws Exception {
			queryPassword2(event.getTarget());
		}
	};
	private EventListener setPasswordListener = new EventListener() {
		public void onEvent(Event event) throws Exception {
			setPassword(event.getTarget());
		}
	};
	private EventListener openAccountListener = new EventListener() {
		public void onEvent(Event event) throws Exception {
			openAccount(event.getTarget());
		}
	};
	private EventListener removeAccountListener = new EventListener() {
		public void onEvent(Event event) throws Exception {
			removeAccount(event.getTarget());
		}
	};

	private EventListener checkinHPAccount = new EventListener() {
		@Override
		public void onEvent(final Event event) throws Exception {
			es.caib.zkib.binder.BindContext ctx = XPathUtils.getComponentContext(event.getTarget());
			final Account acc = (Account) ((DataNode) XPathUtils.getValue(ctx, ".")).getInstance();
			es.caib.zkib.zkiblaf.Missatgebox.confirmaOK_CANCEL("Please, confirm you want to return this account",
					new org.zkoss.zk.ui.event.EventListener() {
						public void onEvent(Event evt) throws NamingException, CreateException, InternalErrorException {
							if ("onOK".equals(evt.getName())) {
								com.soffid.iam.service.ejb.AccountService ejb = com.soffid.iam.EJBLocator
										.getAccountService();
								ejb.checkinHPAccount(acc);
								event.getTarget().getParent().setVisible(false);
							}
						}
					});
		}
	};

	EventListener onNewTreeRow = new EventListener() {		
		@Override
		public void onEvent(Event event) throws Exception {
			try {
			    Treeitem item = (Treeitem) event.getData();
			    if (item == null || item.getPage() == null)
			    	return ;
			    es.caib.zkib.datamodel.DataNode dn = (DataNode) XPathUtils.getValue(item, ".");
			    if (dn == null)
			    	return;
			    Object obj = dn.getInstance();
			    if (obj != null && obj instanceof Account)
			    {
			    	Account acc = (Account) obj;
		    		Div owner = (Div) item.getTreerow().getFirstChild().getChildren().get(3);
			    	if (! acc.getType().equals( AccountType.PRIVILEGED))
			    	{
			    		owner.setVisible(false);
			    	} else {
			    		String ownerUser = null;
			    		
			    		try { 
			    			ownerUser = (String) XPathUtils.getValue(item, "/owner/@userName");	
			    		} catch (Exception e) {}
			    		if ( ownerUser == null || ownerUser.isEmpty())
				    		owner.setVisible(false);
			    		else
			    			((Component)owner.getChildren().get(2)).addEventListener("onClick", checkinHPAccount );
			    	}
				    Div actions = (Div) item.getTreerow().getFirstChild().getChildren().get(4);
		    		((ImageClic)actions.getChildren().get(0)).addEventListener("onClick", queryPasswordListener);
		    		((ImageClic)actions.getChildren().get(1)).addEventListener("onClick", setPasswordListener);
		    		((ImageClic)actions.getChildren().get(2)).addEventListener("onClick", openAccountListener);
		    		((ImageClic)actions.getChildren().get(3)).addEventListener("onClick", removeAccountListener);
			    	if (acc.getAccessLevel().equals (es.caib.seycon.ng.comu.AccountAccessLevelEnum.ACCESS_MANAGER) )
			    	{
			    		actions.getChildren().remove(2); // Edit
			    		actions.getChildren().remove(2); // Remove
			    	}
			    	else if (acc.getAccessLevel().equals (es.caib.seycon.ng.comu.AccountAccessLevelEnum.ACCESS_OWNER))
			    	{
			    	}
			    	else
			    	{
			    		actions.getChildren().clear(); 
			    		
			    	}
			    }
			    if (obj != null && obj instanceof com.soffid.iam.api.VaultFolder)
			    {
				    Div actions = (Div) item.getTreerow().getFirstChild().getChildren().get(2);
			    	VaultFolder f = (VaultFolder) obj;
		    		((ImageClic)actions.getChildren().get(0)).addEventListener("onClick", openFolderListener);
		    		((ImageClic)actions.getChildren().get(1)).addEventListener("onClick", addSubFolderListener);
		    		((ImageClic)actions.getChildren().get(2)).addEventListener("onClick", addAccountListener);
		    		((ImageClic)actions.getChildren().get(3)).addEventListener("onClick", removeFolderListener);
			    	if (!f.getAccessLevel().equals (es.caib.seycon.ng.comu.AccountAccessLevelEnum.ACCESS_OWNER))
			    	{
			    		actions.getChildren().clear();
			    	}
			    }
			} catch (Exception e) {}		
			}
	};

	@Override
	public void onChildAdded(Component child) {

		canCreateRootFolders = es.caib.seycon.ng.utils.Security.isUserInRole("sso:createSharedFolders");
		getPage().setVariable("canCreateRootFolders", canCreateRootFolders);
		super.onChildAdded(child);
	}
	

	public void checkIsUpdatePending ()
	{
		Treeitem item = getTreebox().getSelectedItem();
		if (item != null)
		{
		    es.caib.zkib.datamodel.DataNode dn = (DataNode) XPathUtils.getValue(item, ".");
		    if (dn == null || ! (dn.getInstance() instanceof Account))
		    	return;
		    final Account account = (Account) dn.getInstance();
	    	Treerow row = item.getTreerow();
			Treecell cell = (Treecell) row.getFirstChild();
			Component link = (Component) cell.getChildren().get(3);
			if (link.getAttribute("warning") == null )
			{
				final Image image = new Image("~./img/exclamacio.gif");
				image.setStyle("margin-left: 24px");
				image.setAttribute("warning", "true");
				cell.insertBefore(image, link);
				final Timer timer = (Timer) getPage().getFellow("timer");
				timer.addEventListener("onTimer", new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {
						if ( ! ServiceLocator.instance().getAccountService().isUpdatePending(account))
						{
							image.detach();
							timer.removeEventListener("onTimer", this);
						}
					}
				});
			}
    		Div owner = (Div) item.getTreerow().getFirstChild().getChildren().get(4);
	    	if (! account.getType().equals( AccountType.PRIVILEGED))
	    	{
	    		owner.setVisible(false);
	    	} else {
	    		String ownerUser = null;
	    		
	    		try { 
	    			dn.getListModel("owner").refresh();
	    			ownerUser = (String) XPathUtils.getValue(item, "/owner/@userName");	
	    		} catch (Exception e) {}
	    		if ( ownerUser == null || ownerUser.isEmpty())
		    		owner.setVisible(false);
	    		else
	    		{
		    		owner.setVisible(true);
	    			((Component)owner.getChildren().get(2)).addEventListener("onClick", checkinHPAccount );
	    		}
	    	}
		}
	}
}
