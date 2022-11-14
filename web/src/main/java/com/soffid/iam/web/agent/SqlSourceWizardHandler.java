package com.soffid.iam.web.agent;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.InvalidNameException;
import javax.naming.NamingException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

import org.apache.openjpa.jdbc.sql.StoredProcedure.SQL;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.PasswordDomain;
import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.api.Server;
import com.soffid.iam.api.System;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserDomain;
import com.soffid.iam.api.UserType;
import com.soffid.iam.service.SystemScheduledTasks;
import com.soffid.iam.web.WebDataType;
import com.soffid.iam.web.component.CustomField3;

import es.caib.seycon.ng.comu.ServerType;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.Select;
import es.caib.zkib.component.Wizard;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.XPathUtils;

public class SqlSourceWizardHandler extends Window implements AfterCompose{

	private Wizard wizard;
	private CustomField3 user;
	private CustomField3 pass;
	private ScheduledTask currentTask;
	private Timer timer;
	private Label explanation2;
	private System currentSystem;
	private Label explanation3;
	private CustomField3 type;
	private CustomField3 url;
	private CustomField3 sql;
	private LinkedList<String> columns;
	private int rows;
	private Div form;

	@Override
	public void afterCompose() {
		wizard = (Wizard) getFellow("wizard");
		type = (CustomField3) getFellow("type");
		url = (CustomField3) getFellow("url");
		user = (CustomField3) getFellow("user");
		pass = (CustomField3) getFellow("pass");
		sql = (CustomField3) getFellow("sql");
		timer = (Timer) getFellow("timer");
		explanation2 = (Label) getFellow("explanation2");
		explanation3 = (Label) getFellow("explanation3");
		form = (Div) getFellow("form");
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
			if (type.attributeValidateAll() && user.attributeValidateAll() && pass.attributeValidateAll() && url.attributeValidateAll() &&
					sql.attributeValidateAll()) {
				final String userName = (String) user.getValue();
				createAgent((String)type.getValue(),(String) url.getValue(), userName, (Password) pass.getValue());
				wizard.next();
			}
		}
	}

	private void createAgent(String type, String url, String user, Password pass) throws Exception {
		// --------------- Create the agent
		String database;
		int slash = url.lastIndexOf("/");
		int colon = url.lastIndexOf(":");
		database = slash > colon ? url.substring(slash+1): url.substring(colon+1);
		if (database.contains(";"))
			database = database.substring(0, database.indexOf(";"));
		System s = new System();
		s.setName("Source database: "+database);
		s.setAuthoritative(true);
		s.setAccessControl(false);
		s.setClassName("com.soffid.iam.sync.agent.SQLAgent2");
		s.setDescription("Authoritative data source "+database);
		s.setFullReconciliation(false);
		s.setGenerateTasksOnLoad(true);
		s.setParam0(user);
		s.setParam1(pass.toString());
		s.setParam2(url);
		s.setParam6(type);
		s.setParam5("false"); // Debug
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
			if (s2 != null)
				EJBLocator.getDispatcherService().delete(s2);
			throw e;
		}
		loadColumns();
	}
	

	private void loadColumns() throws InternalErrorException, NamingException, CreateException, UnsupportedEncodingException {
		Collection<Map<String, Object>> result = EJBLocator.getDispatcherService().invoke(currentSystem.getName(), (String) sql.getValue(), "", new HashMap<>());
		if (! result.iterator().hasNext()) {
			throw new UiException(Labels.getLabel("wizard-sql-source.emptySet"));
		}
		rows = result.size();
		columns = new LinkedList<>();
		Map<String, Object> firstRow = result.iterator().next();
		columns.addAll(firstRow.keySet());
		while (form.getFirstChild() != null)
			form.getFirstChild().detach();
		
		Collection<DataType> dataTypes = EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(User.class.getName(), null);
		List<String> values = new LinkedList<>();
		for (DataType datatype: dataTypes) {
			if (datatype.getType() != TypeEnumeration.SEPARATOR && ! datatype.isReadOnly()) {
				WebDataType wdt = new WebDataType(datatype);
				String name = wdt.getName();
				if (! Boolean.TRUE.equals(wdt.getBuiltin())) name = "attributes."+name;
				values.add(URLEncoder.encode(name, "UTF-8")+":"+wdt.getLabel());
			}
		}
		String[] valuesArray = values.toArray(new String[values.size()]);
		for (String column: columns) {
			CustomField3 cf = new CustomField3();
			cf.setLabel(column);
			cf.setDataType("STRING");
			cf.setListOfValues(valuesArray);
			form.appendChild(cf);
			cf.afterCompose();
		}
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
