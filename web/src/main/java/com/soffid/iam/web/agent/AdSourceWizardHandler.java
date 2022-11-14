package com.soffid.iam.web.agent;

import java.nio.charset.StandardCharsets;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.naming.InvalidNameException;
import javax.naming.NamingException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Label;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.PasswordDomain;
import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.api.Server;
import com.soffid.iam.api.System;
import com.soffid.iam.api.UserDomain;
import com.soffid.iam.api.UserType;
import com.soffid.iam.service.SystemScheduledTasks;
import com.soffid.iam.web.component.CustomField3;

import es.caib.seycon.ng.comu.ServerType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.Select;
import es.caib.zkib.component.Wizard;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.XPathUtils;

public class AdSourceWizardHandler extends Window implements AfterCompose{

	private Wizard wizard;
	private CustomField3 name;
	private CustomField3 user;
	private CustomField3 pass;
	private ScheduledTask currentTask;
	private Timer timer;
	private Label explanation2;
	private System currentSystem;
	private Label explanation3;

	@Override
	public void afterCompose() {
		wizard = (Wizard) getFellow("wizard");
		name = (CustomField3) getFellow("name");
		user = (CustomField3) getFellow("user");
		pass = (CustomField3) getFellow("pass");
		timer = (Timer) getFellow("timer");
		explanation2 = (Label) getFellow("explanation2");
		explanation3 = (Label) getFellow("explanation3");
		doHighlighted();
	}
	
	public void back(Event ev) {
		if (wizard.getSelected() == 0)
			detach();
		else
			wizard.previous();
	}
	
	public void next(Event ev) throws Exception {
		switch (wizard.getSelected()) {
		case 0:
			if (name.attributeValidateAll() && user.attributeValidateAll() && pass.attributeValidateAll()) {
				LdapName ldapName;
				try {
					ldapName = new LdapName((String) name.getValue());
				} catch (InvalidNameException e) {
					name.setWarning(0, "Invalid fully-qualyfied domain name");
					return;
				}
				String hostName = null;
				for (Rdn part: ldapName.getRdns()) {
					if (! part.getType().equalsIgnoreCase("dc")) {
						name.setWarning(0, part.getType()+"="+part.getValue().toString()+" should be dc="+part.getValue());
						return;
					}
					if (hostName == null) hostName = part.getValue().toString();
					else hostName = part.getValue().toString()+"."+hostName;
				}
				final String userName = (String) user.getValue();
				createAgent(hostName, ldapName, userName, (Password) pass.getValue());
				wizard.next();
			}
		}
	}

	private void createAgent(String hostName, LdapName ldapName, String user, Password pass) throws Exception {
		// --------------- Create the agent
		System s = new System();
		s.setName("Source AD: "+hostName);
		s.setAuthoritative(true);
		s.setAccessControl(false);
		s.setClassName("com.soffid.iam.sync.agent2.CustomizableActiveDirectoryAgent");
		s.setDescription("Authoritative data source "+ldapName.toString());
		s.setFullReconciliation(false);
		s.setGenerateTasksOnLoad(true);
		s.setParam0(hostName);
		s.setParam1(ldapName.toString());
		s.setParam2(user);
		s.setParam3(pass.toString());
		s.setParam7("false");
		s.setParam8("true"); // Insecure connection
		s.setParam9("false");
		s.setParam4("false");
		s.setParam5("false");
		s.setBlobParam("flatGroups=false&realTimeLogin=false&realTimeSource=true".getBytes(StandardCharsets.UTF_8));
		PasswordDomain pd = EJBLocator.getUserDomainService().findAllPasswordDomain().iterator().next();
		s.setPasswordsDomain(pd.getName());
		s.setPasswordsDomainId(pd.getId());
		UserDomain ud = EJBLocator.getUserDomainService().findAllUserDomain().iterator().next();
		s.setUsersDomain(ud.getName());
		s.setReadOnly(true);
		s.setRolebased(false);
		s.setSharedDispatcher(false);
		s.setTrusted(true);
		Collection<Server> servers = EJBLocator.getDispatcherService().findAllServers();
		s.setUrl("local");
		for (Server server: servers)
			if (server.getType() == ServerType.PROXYSERVER)
				s.setUrl(server.getPublicUrl());
		Collection<UserType> userTypes = EJBLocator.getUserDomainService().findAllUserType();
		String userType = userTypes.iterator().next().getName();
		for (UserType ut: userTypes) {
			if (ut.getName().equals("I")) userType = "I";
		}
		s.setUserTypes(userType);
		
		System s2 = EJBLocator.getDispatcherService().findDispatcherByName(s.getName());
		if (s2 == null) {
			s = EJBLocator.getDispatcherService().create(s);
			EJBLocator.getDispatcherService().setDefaultMappingsByDispatcher(s.getId());
		} else {
			s.setId(s2.getId());
			s = EJBLocator.getDispatcherService().update(s);
		}
		// --------------------- Check connectivity
		currentSystem = s;
		try {
			EJBLocator.getDispatcherService().checkConnectivity(s.getName());
		} catch (Exception e) {
			if (s2 == null)
				EJBLocator.getDispatcherService().delete(s2);
			throw e;
		}
		startTask(s);
	}
	

	public void startTask(System system) throws Exception {
		for (ScheduledTask task: EJBLocator.getScheduledTaskService().listTasks())
		{
			if ( system.getId().toString().equals(task.getParams()))
			{
				if (task.getHandlerName().equals(SystemScheduledTasks.AUTHORITATIVE_DATA_IMPORT)) {
					currentTask = task;
				}
			}
			
		}

		com.soffid.iam.EJBLocator.getScheduledTaskService().startNow (currentTask);
		explanation2.setValue(currentTask.getName());
		timer.start();
	}
	
	public void onTimer(Event event) throws Exception {
		switch (wizard.getSelected()) {
		case 1:
			currentTask = EJBLocator.getScheduledTaskService().load(currentTask.getId());
			if (! currentTask.isActive()) {
				wizard.next();
				startReconcileTask(currentSystem);
				explanation3.setValue(currentTask.getName());
			}
			break;
		case 2:
			currentTask = EJBLocator.getScheduledTaskService().load(currentTask.getId());
			if (! currentTask.isActive()) {
				wizard.next();
				timer.stop();
			}
			break;
			
		}
		
	}
	
	public void startReconcileTask(System system) throws Exception {
		for (ScheduledTask task: EJBLocator.getScheduledTaskService().listTasks())
		{
			if ( system.getId().toString().equals(task.getParams()))
			{
				if (task.getHandlerName().equals(SystemScheduledTasks.RECONCILE_DISPATCHER)) {
					currentTask = task;
				}
			}
			
		}

		com.soffid.iam.EJBLocator.getScheduledTaskService().startNow (currentTask);
		timer.start();
	}
	
	public void end(Event e) {
		detach();
		Executions.sendRedirect("/resource/user/user.zul?filter=");
	}

}
