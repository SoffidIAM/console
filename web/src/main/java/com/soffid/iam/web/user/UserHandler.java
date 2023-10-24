package com.soffid.iam.web.user;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.util.TimeZones;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Window;

import java.text.SimpleDateFormat;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.SyncAgentTaskLog;
import com.soffid.iam.service.ejb.ApplicationService;
import com.soffid.iam.service.ejb.AuthorizationService;
import com.soffid.iam.service.ejb.ConfigurationService;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.ObjectAttributesDiv;
import com.soffid.iam.web.component.SearchBox;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.ImportCsvHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathException;
import es.caib.zkib.zkiblaf.Missatgebox;

public class UserHandler extends FrameHandler {
	private boolean isMaster;
	private boolean canCreateUser;
	private boolean canUpdateUser;
	private boolean canDeleteUser;
	private boolean canQueryUser;
	private ConfigurationService configSvc;
	private String wizard;
	private List<SyncAgentTaskLog> activeTasks;
	private String activeTaskId;
	
	public UserHandler() throws InternalErrorException {
		com.soffid.iam.api.Tenant masterTenant = com.soffid.iam.ServiceLocator.instance().getTenantService().getMasterTenant();

		isMaster = com.soffid.iam.utils.Security.getCurrentTenantName().equals ( masterTenant.getName() );
		canCreateUser = Security.isUserInRole("user:create");
		canUpdateUser = Security.isUserInRole("user:update");
		canDeleteUser = Security.isUserInRole("user:delete");
		canQueryUser = Security.isUserInRole("user:query");;
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
		getNamespace().setVariable("isMaster", isMaster, true);
		getNamespace().setVariable("canCreateUser", canCreateUser, true);
		getNamespace().setVariable("canUpdateUser", canUpdateUser, true);
		getNamespace().setVariable("canDeleteUser", canDeleteUser, true);
		getNamespace().setVariable("canQueryUser", canQueryUser, true);
		getNamespace().setVariable("canQueryUserRole", Security.isUserInRole("user:role:query"), true);
	}
		

	public void onChangeDades() {
		updateStatus();
		try {
			ObjectAttributesDiv oa = (ObjectAttributesDiv) getFellow("userAttributes");
			boolean ro = oa.isReadonly();
			boolean ro2;
			Long id = (Long) XPathUtils.eval(getForm(), "id");
			String group = (String) XPathUtils.eval(getForm(), "primaryGroup");
			if (id == null)
				ro2 = ! Security.isUserInRole(Security.AUTO_USER_CREATE);
			else 
				ro2 = ! Security.isUserInRole(Security.AUTO_USER_UPDATE+"/"+group);
			if (ro != ro2) {
				oa.setReadonly(ro2);
				oa.updateMetadata();
			}
		} catch (JXPathException e) {}
	}
	
	public void importCsv () throws IOException, CommitException, InternalErrorException, NamingException, CreateException {
		new UserImporter().importCsv(this);
	}
	
	public void bulkAction(Event event) throws IOException, CommitException, InternalErrorException, NamingException, CreateException {
		DataTable listbox = (DataTable) getListbox();
		if (listbox.getSelectedIndexes() != null && listbox.getSelectedIndexes().length > 0) {
			UserBulkAction ba = new UserBulkAction( ); 
			ba.start(listbox ) ;
		} else {
			Missatgebox.avis("bulk.selectOneObject");
		}
	}

	
	public void mergeAction(Event event) throws IOException, CommitException, InternalErrorException, NamingException, CreateException, WrongValueException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DataTable listbox = (DataTable) getListbox();
		if (listbox.getSelectedIndexes() != null && listbox.getSelectedIndexes().length >= 2) {
			new UserMergeAction().start((DataTable) getListbox(), true, null);
		} else {
			Missatgebox.avis(Labels.getLabel("merge.warning"));
		}
	}

	public void addNew() throws Exception {
		super.addNew();
	}

	@Override
	public void afterCompose() {
		super.afterCompose();
		HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		wizard = req.getParameter("wizard");
		if ("csv".equals(wizard) ) {
			try {
				importCsv();
			} catch (Exception e) {
				throw new UiException(e);
			}
		}
		if ("form".equals(wizard) ) {
			try {
				addNew();
			} catch (Exception e) {
				throw new UiException(e);
			}
		}
		String user = req.getParameter("userName");
		if (user != null) {
			SearchBox sb = (SearchBox) getFellow("searchBox");
			sb.setBasicMode();
			sb.addAttribute("userName").setSearchFilter(user);
			sb.search();
		}
	}
	
	public void updateStatus() {
		Button b = (Button) getFellow("pendingTasksButton");
		try {
			Long id = (Long) XPathUtils.eval(getForm(), "id");
			if (id == null)
				b.setVisible(false);
			else {
				String userName = (String) XPathUtils.getValue(getForm(), "userName");
				int i = EJBLocator.getUserService().isUpdatePendingExtended(userName);
				if ( i == 0 ) b.setVisible( false );
				else {
					b.setVisible(true);
					b.setImage(i == 1 ? "/img/held.svg" :
						i == 2 ? "/img/sync.svg" :
							"/img/warning.svg");
				}
			}
			if (getFellow("tasksWindow").isVisible()) {
				viewTasks(null);
			}
		} catch (Exception e) {
			b.setVisible(false);
		}
	}

	@Override
	public boolean applyNoClose(Event event) throws CommitException {
		boolean b = super.applyNoClose(event);
		updateStatus();
		return b;
	}
	
	public void viewTasks (Event event) throws InternalErrorException, NamingException, CreateException {
		String userName = (String) XPathUtils.getValue(getForm(), "userName");

		boolean show = false;
		Window w = (Window) getFellow("tasksWindow");
		DataTable dt = (DataTable) w.getFellow("tasks");
		JSONArray array = new JSONArray();
		activeTasks = new java.util.LinkedList<>(EJBLocator.getUserService().getActiveTasks(userName));
		final CustomField3 errorField = (CustomField3)w.getFellow("taskError");
		for (SyncAgentTaskLog tl: activeTasks) {
			JSONObject j = dt.wrap(tl);
			array.put(j);
			String s = tl.getAgentCode()+ " - " + tl.getTaskId();
			if (s.equals(activeTaskId) &&
					tl.getMessage() != null && 
					!tl.getMessage().trim().isEmpty()) {
				if (! tl.getMessage().equals(errorField.getValue()))
					errorField.setValue(tl.getMessage());
				show = true;
			}
		}
		if (errorField.isVisible() != show)
			errorField.setVisible(show);
		dt.setData(array);
		w.doHighlighted();
	}

	public void selectTaskDetails(Event event) {
		Window w = (Window) getFellow("tasksWindow");
		DataTable dt = (DataTable) w.getFellow("tasks");
		int selected = dt.getSelectedIndex();
		CustomField3 cf = ((CustomField3)w.getFellow("taskError"));
		if (selected >= 0 && selected < activeTasks.size()) {
			SyncAgentTaskLog task = activeTasks.get(selected);
			activeTaskId = task.getAgentCode()+ " - " + task.getTaskId();
			if (task.getMessage() != null && !task.getMessage().trim().isEmpty()) {
				cf.setVisible(true);
				cf.setValue(task.getMessage());
			} else {
				cf.setVisible(false);
			}
		} else {
			cf.setVisible(false);
		}
	}
	
	public void closeTaskWindow(Event event) {
		Window w = (Window) getFellow("tasksWindow");
		w.setVisible(false);
	}
	
	public void audit (Event ev) {
		String user = (String) XPathUtils.eval(getForm(), "userName");
		Execution exec = Executions.getCurrent();
		Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZones.getCurrent());
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.AM_PM, Calendar.AM);
		String d = ISODateTimeFormat.dateTime().print(c.getTimeInMillis()); 
		exec.sendRedirect("/monitor/audit.zul?filter=user eq \""+ user.replace("\\", "\\\\").replace("\"","\\\"")+ "\" and calendar gt \""+d+"\"",
				"_blank");
	}

	public void accessLog (Event ev) {
		String user = (String) XPathUtils.eval(getForm(), "userName");
		Execution exec = Executions.getCurrent();
		Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZones.getCurrent());
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.AM_PM, Calendar.AM);
		String d = ISODateTimeFormat.dateTime().print(c.getTimeInMillis()); 
		exec.sendRedirect("/monitor/access.zul?filter=userName eq \""+ user.replace("\\", "\\\\").replace("\"","\\\"")+ "\" and startDate gt \""+d+"\"",
				"_blank");
	}
	
	public void refresh(Event ev) throws InternalErrorException, NamingException, CreateException {
		String user = (String) XPathUtils.eval(getForm(), "userName");
		EJBLocator.getUserService().refreshChanges(user);
		
	}
	
	public void userPrinters(Event ev) {
		Window w = (Window) getFellow("printersWindow");
		w.doHighlighted();
	}
}
