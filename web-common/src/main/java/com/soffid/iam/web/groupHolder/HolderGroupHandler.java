package com.soffid.iam.web.groupHolder;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.Command;
import org.zkoss.zk.au.ComponentCommand;
import org.zkoss.zk.au.out.AuScript;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.RawId;
import org.zkoss.zul.Div;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.OUType;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.User;
import com.soffid.iam.service.AccountService;
import com.soffid.iam.service.ApplicationService;
import com.soffid.iam.service.AuthorizationService;
import com.soffid.iam.service.DispatcherService;
import com.soffid.iam.service.GroupService;
import com.soffid.iam.service.OrganizationalUnitTypeService;
import com.soffid.iam.service.UserService;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.zkiblaf.SignApplet;

public class HolderGroupHandler extends Div implements RawId {
	UserService userService = ServiceLocator.instance().getUserService();
	GroupService groupService = ServiceLocator.instance().getGroupService();
	OrganizationalUnitTypeService ouService = ServiceLocator.instance().getOrganizationalUnitTypeService();
	ApplicationService appService = ServiceLocator.instance().getApplicationService();
	AuthorizationService authService = ServiceLocator.instance().getAuthorizationService();
	DispatcherService dispatcherService = ServiceLocator.instance().getDispatcherService();
	AccountService accountService = ServiceLocator.instance().getAccountService();
	
	boolean askForHolderGroup (String tenant, String account) throws InternalErrorException
	{
		if ( "true".equals( ConfigurationCache.getProperty("soffid.selfservice.groupHolderFilter") ) &&
				getHolderGroups(tenant, account).size() > 1)
			return true;
		else
			return false;
	}
	
	List<Group> getHolderGroups (String tenant, String account) throws InternalErrorException
	{
		List<Group> r = new LinkedList<Group>();
		Security.nestedLogin(tenant, account, Security.ALL_PERMISSIONS);
		try {
			com.soffid.iam.api.System soffid = dispatcherService.findSoffidDispatcher();
			
			Account acc = ServiceLocator.instance().getAccountService().findAccount(account, soffid.getName());
			if (acc != null && acc.getType().equals(AccountType.USER) && acc.getOwnerUsers().size() == 1)
			{
				User user = acc.getOwnerUsers().iterator().next();
				if (user != null)
				{
					addGroup (user.getPrimaryGroup(), r);
					for (GroupUser sg: groupService.findUsersGroupByUserName(user.getUserName()))
					{
						addGroup (sg.getGroup(), r);
					}
				}
			}
		} finally {
			Security.nestedLogoff();
		}
		return r;
	}

	private void addGroup(String groupName, List<Group> r) throws InternalErrorException {
		for ( Group g: r)
		{
			if (g.getName().equals(groupName))
				return;
		}
		Group g = groupService.findGroupByGroupName(groupName);
		String gt = g.getType();
		if (gt != null && !gt.isEmpty())
		{
			OUType ou = ouService.findOUTypeByName(gt);
			if (ou != null && ou.isRoleHolder())
				r.add(g);
		}
	}


	public void selectGroupHolder ( String groupName ) throws InternalErrorException
	{
		Desktop desktop = Executions.getCurrent().getDesktop();
		desktop.setAttribute("holderGroup", groupName);
	}
	
	public Command getCommand(String cmdId) {
		if ("onSelectUser".equals(cmdId))
			return _onSelectUserCommand;
		return super.getCommand(cmdId);
	}
	
	private static Command _onSelectUserCommand  = new ComponentCommand ("onSelectUser", 0) {
		protected void process(AuRequest request) {
			try {
				final HolderGroupHandler applet = (HolderGroupHandler) request.getComponent();
				String tenant = request.getData()[0];
				String account = request.getData()[1];
				if (account != null && account.length() > 0  && 
					applet.askForHolderGroup(tenant, account))
				{
					List<Group> groups = applet.getHolderGroups(tenant, account);
					JSONArray array = new JSONArray();
					for ( Group group: groups)
					{
						JSONObject o = new JSONObject();
						o.put("name", group.getName());
						o.put("description", group.getDescription());
						array.put(o);
					}
					
					applet.response ( "update", new AuScript(applet, "displayHolderGroup("+array.toString()+");"));
				}
				else
					applet.response ( "update", new AuScript(applet, "hideHolderGroup();"));
			} catch (Exception e) {
				throw new UiException(e);
			}
		}
		
	};



}
