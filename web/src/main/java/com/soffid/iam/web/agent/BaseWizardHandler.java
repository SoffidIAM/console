package com.soffid.iam.web.agent;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.ejb.CreateException;
import javax.naming.InvalidNameException;
import javax.naming.NamingException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

import org.apache.commons.beanutils.PropertyUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Label;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.AsyncProcessTracker;
import com.soffid.iam.api.AttributeMapping;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.ObjectMapping;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.PasswordDomain;
import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.api.Server;
import com.soffid.iam.api.SoffidObjectType;
import com.soffid.iam.api.System;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserDomain;
import com.soffid.iam.api.UserType;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.service.AccountService;
import com.soffid.iam.service.SystemScheduledTasks;
import com.soffid.iam.service.UserService;
import com.soffid.iam.service.ejb.DispatcherService;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.WebDataType;
import com.soffid.iam.web.component.AttributeSearchBox;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.SearchBox;
import com.soffid.iam.web.popup.Editor;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.AttributeDirection;
import es.caib.seycon.ng.comu.ServerType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SoffidStackTrace;
import es.caib.zkib.component.Div;
import es.caib.zkib.component.Select;
import es.caib.zkib.component.Wizard;
import es.caib.zkib.zkiblaf.Missatgebox;

public abstract class BaseWizardHandler extends Window implements AfterCompose{

	private Wizard wizard;
	private ScheduledTask currentTask;
	private Timer timer;
	private Label explanation2;
	private System currentSystem;
	private Select attributeSelector;
	private Radiogroup strategyRadio;
	private AsyncProcessTracker currentProcess;
	private Textbox script;

	@Override
	public void afterCompose() {
		wizard = (Wizard) getFellow("wizard");
		timer = (Timer) getFellow("timer");
		script = (Textbox) getFellow("script");
		explanation2 = (Label) getFellow("explanation2");
		attributeSelector = (Select) getFellow("attributeSelector");
		strategyRadio = (Radiogroup) getFellow("radio");
		doHighlighted();
		JSONArray a = new JSONArray();
		try {
			List<String> candidates = Arrays.asList("userName", "email", "firstName", "lastName", "shortName", "emailAddress");
			for (DataType md: EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(User.class.getName(), null)) {
				if (! Boolean.TRUE.equals(md.getBuiltin()) || candidates.contains(md.getName())) {
					JSONObject o = new JSONObject();
					o.put("label", new WebDataType(md).getLabel());
					o.put("value", Boolean.TRUE.equals(md.getBuiltin()) ? md.getName() : "attributes."+md.getName());
					a.put(o);
				}
			}
			attributeSelector.setOptions(a.toString());
			attributeSelector.setSelectedValue("userName");
		} catch (Exception e) {
			throw new UiException(e);
		}
		
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
			if (validateConnectionAttributes()) {
				if (createAgent())
					wizard.next();
			}
			break;
		case 1:
			if (strategyRadio.getSelectedItem() == null) {
				Missatgebox.avis(Labels.getLabel("wizard-ad.warning"));
			}
			else {
				if (strategyRadio.getSelectedItem().getValue().equals("script")) {
					Editor.edit(script, "{\"serviceLocator\":\"com.soffid.iam.ServiceLocator\"}",
							"<b>serviceLocator</b>: The service locator<br>"+
							"Any column or attribute from the target system is available as a standard variable<br>"+
							"return the user name of the account's owner. Return null in case no owner can be found",
							(ev2) -> {
								wizard.next();
								startTask(currentSystem);
							});
				} else {
					wizard.next();
					startTask(currentSystem);
				}
			}
			break;
		}
	}

	
	protected abstract boolean validateConnectionAttributes();

	private void doBinding() throws InternalErrorException, NamingException, CreateException {
		final AsyncProcessTracker t = new AsyncProcessTracker();
		currentProcess = t;
		getFellow("reconcilediv").setVisible(false);
		getFellow("progressdiv").setVisible(true);
		
		final String query = null;
		currentProcess.setCurrent("");
		currentProcess.setStart(new Date());
		currentProcess.setProgress((float)0.0);
		final SoffidPrincipal principal = Security.getSoffidPrincipal();
		final String attribute = (String) attributeSelector.getSelectedValue();
		new Thread( () -> {
			int first = 0;
			int step = 100;
			Security.nestedLogin(principal);
			try {
				PagedResult<User> list;
				do {
					final AccountService accountService = ServiceLocator.instance().getAccountService();
					final UserService userService = ServiceLocator.instance().getUserService();
					list = userService.findUserByTextAndFilter(null, query, first, step);
					for (User user: list.getResources() ) {
						t.setCurrent(user.getUserName());
						t.setProgress((float) first / (float) list.getTotalResults());
						String s = (String) (attribute.startsWith("attributes.") ? user.getAttributes().get(attribute.substring(11)) :
							PropertyUtils.getProperty(user, attribute));
						if ( s != null && ! s.trim().isEmpty()) {
							Account account = accountService.findAccount(s, currentSystem.getName());
							if (account != null && account.getType() == AccountType.IGNORED) {
								account.setType(AccountType.USER);
								account.getOwnerUsers().add(user.getUserName());
								accountService.updateAccount2(account);
							}
						}
						first ++;
					}
				} while (first + list.getResources().size() < list.getTotalResults().intValue());
			} catch (Exception e) {
				currentProcess.setErrorMessage(SoffidStackTrace.generateShortDescription(e));
			} finally {
				Security.nestedLogoff();
			}
			currentProcess.setFinished(true);
			
		}).start();
		timer.start();
	}

	private boolean createAgent() throws Exception {
		// --------------- Create the agent
		System s = new System();
		configureAgent(s);
		
		System s2 = EJBLocator.getDispatcherService().findDispatcherByName(s.getName());
		if (s2 == null) {
			s = EJBLocator.getDispatcherService().create(s);
			loadMappings(s);
			checkConnectivity(s, s2);
			return true;
		} else {
			String msg = String.format(Labels.getLabel("wizard-ad.confirmReplace"), s.getName());
			final System ss = s;
			Missatgebox.confirmaOK_CANCEL(msg, (ev) -> {
				if (ev.getName().equals("onOK")) {
					Window w = (Window) ev.getTarget().getSpaceOwner();
					w.detach();
					ss.setId(s2.getId());
					System s3 = EJBLocator.getDispatcherService().update(ss);
					checkConnectivity(s3, null);
					wizard.next();
				}				
			});
			return false;
		}
	}

	protected void loadMappings(System s) throws InternalErrorException, NamingException, CreateException, Exception {
		EJBLocator.getDispatcherService().setDefaultMappingsByDispatcher(s.getId());
	}

	private void checkConnectivity(System s, System s2) throws InternalErrorException, NamingException, CreateException, Exception {
		// --------------------- Check connectivity
		currentSystem = s;
		try {
			EJBLocator.getDispatcherService().checkConnectivity(s.getName());
			Thread.sleep(2000);
		} catch (Exception e) {
			if (s2 != null)
				EJBLocator.getDispatcherService().delete(s);
			throw e;
		}
	}

	protected void configureAgent(System s) throws InternalErrorException, NamingException, CreateException, UnsupportedEncodingException {
		s.setAuthoritative(false);
		s.setAccessControl(false);
		s.setClassName("com.soffid.iam.sync.agent2.CustomizableActiveDirectoryAgent");
		s.setFullReconciliation(false);
		s.setGenerateTasksOnLoad(false);
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
	}
	

	public void startTask(System system) throws Exception {
		final DispatcherService dispatcherService = EJBLocator.getDispatcherService();
		for (ObjectMapping mapping: dispatcherService.findObjectMappingsByDispatcher(currentSystem.getId())) {
			if (mapping.getSoffidObject() == SoffidObjectType.OBJECT_ACCOUNT) {
				for (AttributeMapping att: dispatcherService.findAttributeMappingsByObject(mapping.getId())) {
					if (att.getDirection() == AttributeDirection.INPUT && 
							(att.getSoffidAttribute().equals("ownerUsers") || 
									att.getSoffidAttribute().equals("type"))) {
						dispatcherService.delete(att);
					}
				}
				if (strategyRadio.getSelectedItem().getValue().equals("script")) {
					String scriptValue = script.getValue().trim();
					if (!scriptValue.endsWith(";")) scriptValue += ";";
					AttributeMapping att = new AttributeMapping();
					att.setDirection(AttributeDirection.INPUT);
					att.setObjectId(mapping.getId());
					att.setSoffidAttribute("ownerUsers");
					att.setSystemAttribute("String findOwner() {\n"+scriptValue+"\n"
							+ "}\n"
							+ "s = findOwner();\n"
							+ "if (s == null) return null;\n"
							+ "l = new java.util.LinkedList();\n"
							+ "l.add(s);\n"
							+ "return l;\n");
					dispatcherService.create(att);

					att = new AttributeMapping();
					att.setDirection(AttributeDirection.INPUT);
					att.setObjectId(mapping.getId());
					att.setSoffidAttribute("type");
					att.setSystemAttribute("String findOwner() {\n"+scriptValue+"\n"
							+ "}\n"
							+ "s = findOwner();\n"
							+ "if (s == null) return \"I\";\n"
							+ "else return \"U\";\n");
					dispatcherService.create(att);
				}
			}
		}
		try {
			EJBLocator.getDispatcherService().checkConnectivity(currentSystem.getName());
			Thread.sleep(3000);
		} catch (Exception e) {
		}
		for (ScheduledTask task: EJBLocator.getScheduledTaskService().listTasks())
		{
			if ( system.getId().toString().equals(task.getParams()))
			{
				if (task.getHandlerName().equals(SystemScheduledTasks.RECONCILE_DISPATCHER)) {
					currentTask = task;
				}
			}
			
		}

		currentProcess = null;
		com.soffid.iam.EJBLocator.getScheduledTaskService().startNow (currentTask);
		explanation2.setValue(currentTask.getName());
		timer.start();
		getFellow("reconcilediv").setVisible(true);
		getFellow("progressdiv").setVisible(false);
	}
	
	public void onTimer(Event event) throws Exception {
		switch (wizard.getSelected()) {
		case 2:
			currentTask = EJBLocator.getScheduledTaskService().load(currentTask.getId());
			if (! currentTask.isActive()) {
				if (currentProcess == null) {
					if (strategyRadio.getSelectedItem().getValue().equals("script")) {
						wizard.next();
					} else {
						doBinding();
						updateProgress();
					}
				}
				else if (currentProcess.isFinished()) {
					timer.stop();
					if (currentProcess.getErrorMessage() != null) {
						Missatgebox.avis(currentProcess.getErrorMessage());
						wizard.setSelected(1);
					} else {
						wizard.next();
					}
				} else {
					updateProgress();
				}
			}
			break;
		case 3: 
		}
	}
	
	final TimeZone utcTimeZone = TimeZone.getTimeZone("GMT");
	private void updateProgress() {
		AsyncProcessTracker p = currentProcess;
		String s = p.getCurrent() == null ? "": p.getCurrent();
		((Label)getFellow("progress")).setValue(s);
		((Div)getFellow("progressbar")).setWidth(""+((int) (p.getProgress() * 100))+"%");
		if (p.getProgress() == 0.0) 
			s = "-";
		else {
			int etf =(int) ( (1.0 - p.getProgress()) / p.getProgress() * ( java.lang.System.currentTimeMillis() - p.getStart().getTime() ) );
			if (etf < 60_000) 
				s = "" + (etf / 1000) + " s";
			else if (etf < 60 * 60_000) {
				SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
				sdf.setTimeZone(utcTimeZone);
				s = sdf.format(new Date(etf));
			}
			else if (etf < 24 * 60 * 60_000) {
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				sdf.setTimeZone(utcTimeZone);
				s = sdf.format(new Date(etf));
			}
			else {
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				sdf.setTimeZone(utcTimeZone);
				s = ""+(etf / (24 * 60 * 60_000)) + " days " + sdf.format(new Date(etf));
			}
		}
		((Label)getFellow("etf")).setValue(s);
	}

	public void end(Event e) {
		SearchBox sb = (SearchBox) getParent().getFellow("searchBox");
		sb.setBasicMode();
		AttributeSearchBox att = sb.addAttribute("name");
		att.setSearchFilter(currentSystem.getName());
		sb.search();
		detach();
	}

	public void onRadio(Event e) {
		
	}
	
}
