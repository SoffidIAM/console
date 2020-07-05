package com.soffid.iam.web.popup;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Domain;
import com.soffid.iam.api.DomainType;
import com.soffid.iam.api.Role;
import com.soffid.iam.web.component.Identity;
import com.soffid.iam.web.component.Identity.Type;

import es.caib.seycon.ng.exception.InternalErrorException;


public class IdentityHandler extends Window implements AfterCompose {
	com.soffid.iam.api.AsyncList userList = null;
	int[] userListPosition = new int[1];
	com.soffid.iam.api.AsyncList accountList = null;
	int[] accountListPosition = new int[1];
	com.soffid.iam.api.AsyncList roleList = null;
	int[] roleListPosition = new int[1];
	com.soffid.iam.api.AsyncList groupList = null;
	int[] groupListPosition = new int[1];
	com.soffid.iam.api.AsyncList appList = null;
	int[] appListPosition = new int[1];
	com.soffid.iam.api.AsyncList domainValueList = null;
	int[] domainValueListPosition = new int[1];
	List<com.soffid.iam.web.component.Identity> ids = null;
	List<Identity> currentIdentities = null;
	String searchCriteria = "";
	
	com.soffid.iam.api.Role currentRole = null;
	String currentRolePrefix = null;
	boolean grantMode = false;
	private Timer timer;
	private Textbox textbox;
	private Div selected;
	private Div result;
	private Image searchProgress;

	public IdentityHandler() {
		
	}

	@Override
	public void setPage(Page page) {
		super.setPage(page);
		Map args = Executions.getCurrent().getAttributes();
		if (args != null) {
			page.setVariable("wildcardDomain", args.get("wildcardDomain") );
			page.setVariable("types", args.get("types"));
			page.setVariable("singleIdentity", args.get("singleIdentity") );
			page.setVariable("title", args.get("title"));
			page.setVariable("invoker", args.get("invoker"));
			page.setVariable("backMessage", args.get("backMessage"));
			page.setVariable("visible", args.get("visible"));
		}
	}
	
	
	boolean fetchAsyncData ( com.soffid.iam.api.AsyncList list, int[] position, String tag) throws Throwable {
		boolean any = false;
		if (list != null)
		{
			Iterator it = list.iterator();
			if ( (list.isDone() &&  position[0] == list.size()) || list.isCancelled())
			{
				Throwable th = list.getExceptionToThrow();
				list.clearExceptionToThrow();
				if (th != null)
					throw th; 
				any = false;
			} else if (list.size() > position[0]) {
				int i = 0;
				while (it.hasNext())
				{
				    Object o = it.next();
					if (i++ >= position[0])
					{
						Identity id = 
								currentRole == null ? 
									Identity.fromObject (o):
									Identity.fromObject(currentRole, o);
						ids.add(id);				
						position[0] ++;
					}
				}
				any = true;
			} else {
				any = true;
			}
		}
		return any;

	}
	
	public void start () {
		String title = (String) getPage().getVariable("title");
		Type[] types = (Type[]) getPage().getVariable("types");
		if (title != null )
			setTitle( title );
		doHighlighted();
		textbox.focus();
		selected.getChildren().clear();
		if ( currentIdentities != null)
		{
			for (com.soffid.iam.web.component.Identity id: currentIdentities) {
				selected.appendChild(id.generateTag(true) );
			}
		}
		grantMode = false;
		for (Type type: types)
		{
			if (type == Type.GRANT)
				grantMode = true;
		}
		textbox.setValue("");
		result.getChildren().clear();
		currentRole = null;
	}
	
	public void cancel()
	{
		currentIdentities = null;
		setVisible(false);
	}
	
	public void accept()
	{
		Component invoker = (Component) getPage().getVariable("invoker");
		String message = (String) getPage().getVariable("backMessage");
		List identities = new LinkedList<>();
		for ( Component div: (Collection<Component>) selected.getChildren())
		{
			Identity id = (Identity) div.getAttribute("identity");
			identities.add(id);
		}
		currentIdentities = null;
		setVisible(false);
		Events.sendEvent(new Event(message, invoker, identities));
		
	}

	public void selectCandidate (Event e) throws Exception
	{
		Identity id = (Identity) e.getTarget().getAttribute("identity");
		e.getTarget().detach();
		for (Component child: (Collection<Component>) selected.getChildren())
		{
			Identity id2 = (Identity) child.getAttribute("identity");
			if (id.equals(id2))
				return;
		}
		if (grantMode && id.getObject() instanceof com.soffid.iam.api.Role)
		{
			com.soffid.iam.api.Role role = (Role) id.getObject(); 
			if (role.getDomain() == null ||
					role.getDomain().equals(es.caib.seycon.ng.comu.TipusDomini.SENSE_DOMINI))
				id = new com.soffid.iam.web.component.Identity( role, (com.soffid.iam.api.DomainValue) null);
			else
			{
				currentRolePrefix = role.getName()+" @ "+role.getSystem()+" / ";
				currentRole = role;
				textbox.setValue(currentRolePrefix);
				textbox.focus();
				textbox.setSelectionRange(currentRolePrefix.length(), currentRolePrefix.length());
				result.getChildren().clear();
//				if (role.getDomain().getExternalCode() != null)
				search(currentRolePrefix);
				return;
			}
		}
			
		selected.appendChild(id.generateTag(true) );
		textbox.focus();
		textbox.setSelectionRange(0, textbox.getText().length());
		if ( Boolean.TRUE.equals(getPage().getVariable("singleIdentity")))
		{
			accept();
		}
	}
	
	public void search(InputEvent event) throws Exception
	{
		String t = event.getValue();
		search(t);
	}

	public void search(String t) throws InternalErrorException, NamingException, CreateException {
		ids = new LinkedList();
		searchCriteria = t;
		searchSomething (t, ids);
		
		searchProgress.setVisible(true);
		
		Collections.sort(ids, Identity.getComparator());
		result.getChildren().clear();
		for (com.soffid.iam.web.component.Identity id: ids)
		{
			Div d = id.generateSelector(t);
			result.appendChild(d);
			d.addEventListener("onClick", 
				(e) -> {selectCandidate(e);}
			);
		}
	}
	
	void searchSomething (String t, List ids) throws InternalErrorException, NamingException, CreateException
	{
		if (userList != null) {
			userList.cancel();
		}
		if (accountList != null) accountList.cancel();
		if (roleList != null) roleList.cancel();
		if (groupList != null) groupList.cancel();
		if (domainValueList != null) domainValueList.cancel();
		if (appList != null) appList.cancel();
		
		Type[] types = (Type[]) getPage().getVariable("types");
		
		for (Type type: types)
		{
			if (type == com.soffid.iam.web.component.Identity.Type.USER)
				searchUsers(t, ids);
			
			if (type == com.soffid.iam.web.component.Identity.Type.ACCOUNT)
				searchAccounts(t, ids);

			if (type == com.soffid.iam.web.component.Identity.Type.ROLE)
				searchRoles(t, ids);

			if (type == com.soffid.iam.web.component.Identity.Type.GROUP)
				searchGroups(t, ids);
			
			if (type == com.soffid.iam.web.component.Identity.Type.GRANT)
				searchGrants(t, ids);
		}
	}
	
	void searchUsers (String t, List ids) throws InternalErrorException, NamingException, CreateException {
		userListPosition[0] = 0;
		userList = com.soffid.iam.EJBLocator.getUserService().findUserByTextAsync(t);
		timer.start();
	}
	                  
	void searchAccounts (String t, List ids) throws InternalErrorException, NamingException, CreateException {
		accountListPosition[0] = 0;
		accountList = com.soffid.iam.EJBLocator.getAccountService().findAccountByTextAsync(t);
		timer.start();
	}
	
	void searchRoles (String t, List ids) throws InternalErrorException, NamingException, CreateException {
		roleListPosition[0] = 0;
		roleList = com.soffid.iam.EJBLocator.getApplicationService().findRoleByTextAsync(t);
		timer.start();
	}

	void searchGroups (String t, List ids) throws InternalErrorException, NamingException, CreateException {
		groupListPosition[0] = 0;
		groupList = com.soffid.iam.EJBLocator.getGroupService().findGroupByTextAsync(t);
		timer.start();
	}


	void searchApplications (String t, List ids) throws InternalErrorException, NamingException, CreateException {
		appListPosition[0] = 0;
		appList = com.soffid.iam.EJBLocator.getApplicationService().findApplicationByTextAsync(t);
		timer.start();
	}

	void searchGrants (String t, List ids) throws InternalErrorException, NamingException, CreateException {
		
		if ( currentRole == null || currentRolePrefix == null)
			searchRoles(t, ids);
		else if (t.startsWith(currentRolePrefix) &&
				currentRole.getDomain() != null && 
				!DomainType.SENSE_DOMINI.equals(currentRole.getDomain()))
		{

			String t2 = t.substring(currentRolePrefix.length());
			searchCriteria = t2;
			if ( Boolean.TRUE.equals(getPage().getVariable("wildcardDomain")) && 
					t2.isEmpty() && 
					! currentRole.getDomain().equals(com.soffid.iam.api.DomainType.SENSE_DOMINI))
			{
				com.soffid.iam.web.component.Identity id = new com.soffid.iam.web.component.Identity(currentRole, 
						new com.soffid.iam.api.DomainValue());
				ids.add(id);				
			}
			if (DomainType.APLICACIONS.equals( currentRole.getDomain()) ||
					DomainType.APPLICATIONS.equals( currentRole.getDomain()))
			{
				searchApplications (t2, ids);
			}
			else if (DomainType.GROUPS.equals( currentRole.getDomain()) ||
					DomainType.GRUPS.equals( currentRole.getDomain()))
			{
				searchGroups(t2, ids);
			}
			else if (DomainType.GRUPS_USUARI.equals( currentRole.getDomain()) ||
					DomainType.MEMBERSHIPS.equals( currentRole.getDomain()))
			{
				searchGroups(t2, ids);
			}
			else
			{
				searchDomainValues (currentRole.getDomain(), t2, ids);
			}
		} else {
			if (currentRolePrefix.startsWith(t))
			{
				currentRole = null;
				currentRolePrefix = null;

				textbox.setValue("");

				
				getFellow("result").getChildren().clear();
			} else {
				currentRole = null;
				currentRolePrefix = null;
			}
		}
	}
	                  
	void searchDomainValues (String domain, String t, List ids) throws InternalErrorException, NamingException, CreateException {
		domainValueListPosition[0] = 0;
		domainValueList = com.soffid.iam.EJBLocator.getDomainService()
				.findDomainValueByTextAndFilterAsync(t, 
						"domain.name eq \""+domain.replaceAll("\"", "\\\\\"")+"\" and "
								+ "domain.informationSystem.name eq \""+ currentRole.getInformationSystemName().replaceAll("\"", "\\\\\"")+"\"");
		timer.start();
	}

	
	public void onTimer(Event event) throws Throwable {
	    boolean any = false;
	    any = fetchAsyncData(userList, userListPosition, "user") || any;
	    any = fetchAsyncData(roleList, roleListPosition, "role") || any;
	    any = fetchAsyncData(groupList, groupListPosition, "group") || any;
	    any = fetchAsyncData(appList, appListPosition, "app") || any;
	    any = fetchAsyncData(domainValueList, domainValueListPosition, "domainValue") || any;
	    
		if (!any) {
			timer.stop();
			searchProgress.setVisible(false);
		}
		else
		{
			Collections.sort(ids, com.soffid.iam.web.component.Identity.getComparator());
			result.getChildren().clear();
			for (com.soffid.iam.web.component.Identity id: ids)
			{
				Div d = id.generateSelector(searchCriteria);
				result.appendChild(d);
				d.addEventListener("onClick", 
					(e) -> { selectCandidate(e);}
				);
			}
		}
	}
	
	public void onClose(Event event) {
		event.stopPropagation();		                                 
		cancel();
	}
	
	@Override
	public void afterCompose() {
		timer = (Timer) getFellow("timer");
		textbox = (Textbox) getFellow("textbox");
		selected = (Div) getFellow("selected");
		result = (Div) getFellow("result");
		searchProgress = (Image) getFellow("searchProgress");
		if (Boolean.TRUE.equals(getPage().getVariable("visible"))) {
			doHighlighted();
			textbox.focus();
		}
	}
	
	
	public static void selectIdentity (String title, com.soffid.iam.web.component.Identity.Type[] types, 
			boolean singleIdentity, boolean wildcardDomain,
			Component invoker, String backMessage) throws IOException {
		Page p = invoker.getDesktop().getPageIfAny("identity");
		if ( p == null) {
			Include i = new Include("/popup/identity.zul");
			i.setDynamicProperty("types", types);
			i.setDynamicProperty("invoker", invoker);
			i.setDynamicProperty("backMessage", backMessage);
			i.setDynamicProperty("title", title);
			i.setDynamicProperty("visible", true);
			i.setDynamicProperty("singleIdentity", singleIdentity);
			i.setDynamicProperty("wildcardDomain", wildcardDomain);
			i.setPage(invoker.getPage());
		} else {
			p.setVariable("types", types);
			p.setVariable("title", title);
			p.setVariable("invoker", invoker);
			p.setVariable("backMessage", backMessage);
			p.setVariable("singleIdentity", singleIdentity);
			p.setVariable("wildcardDomain", wildcardDomain);
			Events.sendEvent(new Event("onDisplay", p.getFellow("identityWindow")));
		}
	}
	
	public static void selectIdentity (String title, com.soffid.iam.web.component.Identity.Type[] types, 
			Component invoker, String backMessage) throws IOException {
		selectIdentity(title, types, false, false, invoker, backMessage);
	}

}

