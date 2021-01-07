package com.soffid.iam.web.syncserver;

import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Div;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AgentStatusInfo;
import com.soffid.iam.api.Server;
import com.soffid.iam.api.SyncAgentTaskLog;
import com.soffid.iam.api.SyncServerInfo;
import com.soffid.iam.ui.SeyconTask;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.seycon.ng.comu.SeyconAgentTaskLog;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Databox;
import es.caib.zkib.component.DateFormats;
import es.caib.zkib.component.Switch;
import es.caib.zkib.zkiblaf.Missatgebox;


public class SyncserverHandler extends FrameHandler {
	boolean canRestart = Security.isUserInRole(Security.AUTO_MONITOR_AGENT_RESTART);
	private String serverUrl;
	private Long serverId;
	private List<AgentStatusInfo> agentStatus;
	private List<SyncAgentTaskLog> currentAgentTasks;
	private List<SeyconTask> currentTasks;
	private AgentStatusInfo currentAgent;
	private SyncAgentTaskLog currentTask;
	private List<String> currentsAgents;
	private List<SeyconTask> unscheduledTasks;

	public SyncserverHandler() throws InternalErrorException {
		super();
	}

	@Override
	public void afterCompose() {
		super.afterCompose();
		
		refreshPage();
	}
	
	public void refreshPage() {
		Div div = (Div) getFellow("servers");
		div.getChildren().clear();
		try {
			for ( Server server: EJBLocator.getSyncServerService().getSyncServers()) {
				Map<String, Object> m = new HashMap<>();
				m.put("url", server.getUrl());
				m.put("id", server.getId());
				
				Executions.getCurrent().createComponents("/monitor/syncserver-server.zul", div, m);
			}
		} catch (InternalErrorException | NamingException | CreateException e) {
			throw new UiException (e);
		}
		
	}
	
	public void enableTimer (Event event) {
		Switch sw = (Switch) event.getTarget();
		Timer t = (Timer) getFellow("timer");
		if (sw.isChecked())
			t.start();
		else
			t.stop();
	}
	
	protected String getUrl(Event event) {
		for (Component c = event.getTarget(); c != null; c = c.getParent()) {
			String url = (String) c.getAttribute("serverUrl");
			if (url != null)
				return url;
		}
		return null;
	}
	
	protected Long getServerId(Event event) {
		for (Component c = event.getTarget(); c != null; c = c.getParent()) {
			Long id = (Long) c.getAttribute("serverId");
			if (id != null)
				return id;
		}
		return null;
	}
	
	public void info(Event event) throws InternalErrorException, NamingException, CreateException {
		String url;
		url = getUrl(event);
		hideAllDivs();
		SyncServerInfo ssi = EJBLocator.getSyncServerService().getSyncServerInfo(url);
		
		((Label)getFellow("form-label")).setValue(url);
		((CustomField3) getFellow("ss_versio")).setValue(ssi.getVersion());
		((CustomField3) getFellow("ss_sso")).setValue(ssi.getSso());
		((CustomField3) getFellow("ss_jetty")).setValue(ssi.getJetty());
		((CustomField3) getFellow("ss_ssodaemon")).setValue(ssi.getSsoDaemon());
		((CustomField3) getFellow("ss_taskgenerator")).setValue(ssi.getTaskGenerator());
		((CustomField3) getFellow("ss_cad_root")).setValue(ssi.getExpirationRootCertificate());
		((CustomField3) getFellow("ss_cad_main")).setValue(ssi.getExpirationMainCertificate());
		((CustomField3) getFellow("ss_data_server")).setValue(ssi.getCurrentServerDate());
		((CustomField3) getFellow("ss_database_connections")).setValue(ssi.getDatabaseConnections());
		getFellow("tab_info").setVisible(true);
		showDetails();
	}

	private void hideAllDivs() {
		getFellow("tab_info").setVisible(false);
		getFellow("tab_stats").setVisible(false);
		getFellow("tab_tasks").setVisible(false);
		getFellow("tab_unscheduledtasks").setVisible(false);
		getFellow("tab_agents").setVisible(false);
		getFellow("refreshButton").setVisible(false);
	}
	
	public void restart(Event event) {
		String url = getUrl(event);
		if (canRestart)
		{
			try
			{
				es.caib.zkib.zkiblaf.Missatgebox.confirmaOK_CANCEL(
						String.format(org.zkoss.util.resource.Labels.getLabel("seyconServer.DesitjaReiniciar"),
									new Object [] {url}) ,
						(event2) -> {
							if (event2.getName().equalsIgnoreCase("onOK") ) {
								com.soffid.iam.EJBLocator.getSyncServerService().resetSyncServer(
										url, null);
								
								Missatgebox.avis(
										String.format(org.zkoss.util.resource
												.Labels.getLabel("seyconServer.Restarting"), 
												new Object [] {url}));
							}
						});
			}
			catch (Throwable th)
			{
				String fmt = org.zkoss.util.resource
						.Labels.getLabel("seyconServer.ErrorReinici");
				Missatgebox.error (String.format(fmt, new Object []
						{
							(th.getCause()!=null ? th.getCause() : th.toString())
						}
				));
				
			}
		}

	}

	
	public void getLog(Event event) {
		if (Security.isUserInRole(Security.AUTO_BASE_LOG_QUERY))
		{
			try
			{
				// Get URL server  
				String url = getUrl(event);
				
				java.net.URL urls = new java.net.URL(url);
				String host = urls.getHost();
				// Funciona amb url relativa??
				String url2 = "/SEU/serverlog?urlserver="+ URLEncoder.encode(url);
				
				Iframe logwindow = (Iframe) getFellow("logwindow");
				logwindow.setSrc(url2);
				logwindow.invalidate();
			}
			catch (Throwable th)
			{
				Missatgebox.error (org.zkoss.util.resource.Labels
						.getLabel("seyconServer.ErrorLog"));
			}
		}

	}
	
	public void stats(Event event) throws InternalErrorException, NamingException, CreateException {
		Long serverId = getServerId(event);
		hideAllDivs();
		((Label)getFellow("form-label")).setValue(getUrl(event));
		getFellow("tab_stats").setVisible(true);
		Iframe iframe = (Iframe) getFellow("statsGraph");
		iframe.setSrc("/graph/graph.html?data=syncserver_performance/"+serverId);
		iframe.invalidate();
		showDetails();
	}


	public void viewTasks(Event event) throws InternalErrorException, NamingException, CreateException {
		hideAllDivs();
		serverId = getServerId(event);
		serverUrl = getUrl(event);
		((Label)getFellow("form-label")).setValue(serverUrl);
		getFellow("tab_tasks").setVisible(true);
		getFellow("refreshButton").setVisible(true);
		showDetails();
		
		updateTasksGrid();
	}

	public void updateTasksGrid() throws InternalErrorException, NamingException, CreateException {
		Iterator<Object> it = EJBLocator.getSyncServerService().getServerTasks(serverUrl).iterator();
        currentsAgents = new LinkedList<String> ( (Collection<String>) it.next());
        currentTasks = (List<SeyconTask>) it.next();
        
        DataTable dt = (DataTable) getFellow("tasks_table");
        
        // Create columns
        JSONArray columns = new JSONArray();
        JSONObject h = new JSONObject();
        h.put("name", Labels.getLabel("seyconserver.zul.Tasca"));
        h.put("value", "task");
        columns.put(h);
        int i = 0;
        for ( String s: currentsAgents) {
            h = new JSONObject();
            h.put("name", s);
            h.put("className", "#{col_"+i+"=='DONE'?'green':col_"+i+"=='ERROR'?'red': 'yellow'}");
            h.put("template", "<span onclick=\"zkDatatable.sendClientAction(this, 'onTaskDetails', ["+i+"])\">#{col_"+i+"}</span>");
            h.put("value", "col_"+i);
            columns.put(h);
            i++;
        }
        dt.setColumns(columns.toString());
        // Set value
        StringBuffer values = new StringBuffer();
        values.append("[");
        for ( SeyconTask st: currentTasks ) {
        	JSONObject value = new JSONObject();
        	value.put("task", st.getTaskName());
        	value.put("id", st.getId());
        	for  (i = 0; i < st.getEstatExecucioAgents().length; i++) {
        		value.put("col_"+i, st.getEstatExecucioAgents()[i]);
        	}
        	if (values.length() > 1)
        		values.append(",");
        	values.append(value.toString());
        }
        values.append("]");
        dt.setData(values.toString());
	}
	
	public void cancelGridTask(Event event) {
        DataTable dt = (DataTable) getFellow("tasks_table");
        int pos = dt.getSelectedIndex();
        if (pos < 0) return;
        
        final SeyconTask row = currentTasks.get(pos);
        if (row == null) return;
        
        final Long id  = row.getId();
        
		Missatgebox.confirmaOK_CANCEL(
				String.format(Labels.getLabel("seyconserver.zul.ConfirmCancelTask"), row.getTaskName()),
						(event2) -> {
							if ("onOK".equals( event2.getName())) {
								EJBLocator.getSyncServerService().cancelTask(id);
								dt.delete();
								currentTasks.remove(pos);
							}
						});
	}

	public void cancelTask(Event event) {
        if (currentTask == null) return;
        
        final Long id  = currentTask.getTaskId();
        
		Missatgebox.confirmaOK_CANCEL(
				String.format(Labels.getLabel("seyconserver.zul.ConfirmCancelTask"), currentTask.getTaskDescription()),
						(event2) -> {
							if ("onOK".equals( event2.getName())) {
								EJBLocator.getSyncServerService().cancelTask(id);
								getFellow("taskWindow").setVisible(false);
								if (getFellow("agentWindow").isVisible()) {
									DataTable dt = ((DataTable) getFellow("taskWindow").getFellow("table"));
									currentAgentTasks.remove(dt.getSelectedIndex());
									dt.delete();
								}
								if (getFellow("tab_tasks").isVisible()) {
									DataTable dt = ((DataTable) getFellow("tasks_table"));
									currentTasks.remove(dt.getSelectedIndex());
									dt.delete();
								}
							}
						});
	}

	public void reescheduleTask(Event event) {
        if (currentTask == null) return;
        
        final Long id  = currentTask.getTaskId();
        
		Missatgebox.confirmaOK_CANCEL(
				String.format(Labels.getLabel("seyconserver.zul.ConfirmReleaseTask"), currentTask.getTaskDescription()),
						(event2) -> {
							if ("onOK".equals( event2.getName())) {
								EJBLocator.getSyncServerService().boostTask(id);
								getFellow("taskWindow").setVisible(false);
								if (getFellow("agentWindow").isVisible()) {
									openAgentTasks(null);
								}
							}
						});
	}

	public void refreshTasks (Event event) throws InternalErrorException, NamingException, CreateException {
		if (getFellow("tab_tasks").isVisible())
			updateTasksGrid();
		if (getFellow("tab_unscheduledtasks").isVisible())
			updateUnscheduledTasksGrid();
		if (getFellow("tab_agents").isVisible())
			updateAgentsGrid();
	}
	
	public void viewAgents(Event event) throws InternalErrorException, NamingException, CreateException {
		hideAllDivs();
		serverId = getServerId(event);
		serverUrl = getUrl(event);
		((Label)getFellow("form-label")).setValue(serverUrl);
		getFellow("tab_agents").setVisible(true);
		getFellow("refreshButton").setVisible(true);
		showDetails();
		
		updateAgentsGrid();
	}

	public void updateAgentsGrid() throws InternalErrorException, NamingException, CreateException {
        DataTable dt = (DataTable) getFellow("agents_table");
        
        // Set value
        JSONArray values = new JSONArray();
        agentStatus = new LinkedList<>( EJBLocator.getSyncServerService().getServerAgentStatus(serverUrl));
		for ( AgentStatusInfo st: agentStatus ) {
        	JSONObject value = new JSONObject( st );
        	if (! "Connected".equals( st.getStatus()))
        		value.put("$class", "bold");
        	values.put(value);
        }
        dt.setData(values);
	}

	public void getTaskDetails (Event event) throws InternalErrorException, NamingException, CreateException {
        DataTable dt = (DataTable) getFellow("tasks_table");
        int pos = dt.getSelectedIndex();
        if (pos < 0) return;
        
        SeyconTask task = currentTasks.get(pos);
        if (task == null) return;
        
        final Long id  = task.getId();
        final String taskName = task.getTaskName();
        final String[] messages = task.getMessage();
        final String[] exceptions = task.getException();
        
        int col = Integer.parseInt( ((String[])event.getData())[0] );
        if (! "DONE".equals(task.getEstatExecucioAgents()[col])) {
        	Window w = (Window) getFellow("taskWindow");

        	currentTask = EJBLocator.getSyncServerService().getAgentTasks(serverUrl, currentsAgents.get(col), id );
        	w.setTitle(task.getTaskName());
        	((Databox) w.getFellow("task")).setValue(currentTask.getTaskDescription());
        	((Databox) w.getFellow("message")).setValue(messages[col]);
        	((Databox) w.getFellow("priority")).setValue(currentTask.getPriority());
        	((Databox) w.getFellow("executions")).setValue(currentTask.getExecutionsNumber());
        	((Databox) w.getFellow("lastExecution")).setFormat(DateFormats.getDateTimeFormatString());
        	((Databox) w.getFellow("lastExecution")).setValue(
        			currentTask.getLastExecutionDate() == null ? null:
        			currentTask.getLastExecutionDate().getTime());
        	((Databox) w.getFellow("nextExecution")).setFormat(DateFormats.getDateTimeFormatString());
        	((Databox) w.getFellow("nextExecution")).setValue(
        		currentTask.getNextExecutionDate() == null ? null:
        		currentTask.getNextExecutionDate().getTime());
        	Textbox tb = (Textbox) w.getFellow("tb");
        	tb.setValue(currentTask.getStackTrace());
        	w.doHighlighted();
        	
        }

	}
	
	public void getAgentTaskDetails (Event event) throws InternalErrorException, NamingException, CreateException {
		Window w2 = (Window) getFellow("agentWindow");
        DataTable dt = (DataTable) w2.getFellow("table");
        int pos = dt.getSelectedIndex();
        if (pos < 0) return;
        
        currentTask = currentAgentTasks.get(pos);
        if (currentTask == null) return;
        
    	Window w = (Window) getFellow("taskWindow");

    	w.setTitle(currentTask.getTaskDescription());
    	((Databox) w.getFellow("task")).setValue(currentTask.getTaskDescription());
    	((Databox) w.getFellow("message")).setValue(currentTask.getMessage());
    	((Databox) w.getFellow("priority")).setValue(currentTask.getPriority());
    	((Databox) w.getFellow("executions")).setValue(currentTask.getExecutionsNumber());
    	((Databox) w.getFellow("lastExecution")).setFormat(DateFormats.getDateTimeFormatString());
    	((Databox) w.getFellow("lastExecution")).setValue(
    			currentTask.getLastExecutionDate() == null ? null:
    			currentTask.getLastExecutionDate().getTime());
    	((Databox) w.getFellow("nextExecution")).setFormat(DateFormats.getDateTimeFormatString());
    	((Databox) w.getFellow("nextExecution")).setValue(
    		currentTask.getNextExecutionDate() == null ? null:
    		currentTask.getNextExecutionDate().getTime());
    	Textbox tb = (Textbox) w.getFellow("tb");
    	tb.setValue(currentTask.getStackTrace());
    	w.doHighlighted();
        	
	}

	public void closeTaskWindow(Event event) {
		Window w = (Window) getFellow("taskWindow");
		w.setVisible(false);
	}

	public void closeLogWindow(Event event) {
		Window w = (Window) getFellow("logWindow");
		w.setVisible(false);
	}
	
	public void openAgentTasks(Event event) throws InternalErrorException, NamingException, CreateException {
        DataTable dt = (DataTable) getFellow("agents_table");
        int pos = dt.getSelectedIndex();
        if (pos < 0) return;

        currentAgent = agentStatus.get(pos);
        currentAgentTasks = new LinkedList<> (EJBLocator.getSyncServerService().getAgentTasks(serverUrl, currentAgent.getAgentName()));
        StringBuffer tasksArray = new StringBuffer();
        tasksArray.append("[");
        for (SyncAgentTaskLog task: currentAgentTasks) {
        	JSONObject o = new JSONObject();
        	o.put("task", task.getTaskDescription());
        	o.put("priority", task.getPriority());
        	o.put("executions", task.getExecutionsNumber());
        	if (task.getLastExecutionDate() != null) {
        		o.put("executiontime", task.getLastExecutionDate().getTime());
        		o.put("executiontime_datetime", DateFormats.getDateTimeFormat().format(task.getLastExecutionDate().getTime()));
        		
        	}
        	o.put("message", task.getMessage());
        	if (task.getNextExecutionDate() != null) {
        		o.put("scheduled", task.getNextExecutionDate().getTime());
        		o.put("scheduled_datetime", DateFormats.getDateTimeFormat().format(task.getNextExecutionDate().getTime()));
        	}
        	if (tasksArray.length() > 1)
        		tasksArray.append(",");
        	tasksArray.append(o.toString());
        }
        tasksArray.append("]");
        
        Window w = (Window) getFellow("agentWindow");
        DataTable dt2 = (DataTable) w.getFellow("table");
        w.setTitle(currentAgent.getAgentName());
        dt2.setData(tasksArray.toString());
        w.doHighlighted();
        
        ((Databox)w.getFellow("name")).setValue(currentAgent.getAgentName());
        ((Databox)w.getFellow("url")).setValue(currentAgent.getUrl());
        ((Databox)w.getFellow("version")).setValue(currentAgent.getVersion());
        Databox stbox = (Databox)w.getFellow("stackTrace");
        if (currentAgent.getStackTrace() != null) {
        	stbox.setVisible(true);
			stbox.setValue(currentAgent.getStackTrace());
        } else {
        	stbox.setVisible(false);
        }
        if ("local".equals(currentAgent.getUrl())) {
        	w.getFellow("log_button").setVisible(false);
        	w.getFellow("restart_button").setVisible(false);
        } else {
        	w.getFellow("log_button").setVisible(true);
        	w.getFellow("restart_button").setVisible(true);
        }
	}
	
	public void closeAgentWindow(Event event) {
        Window w = (Window) getFellow("agentWindow");
		w.setVisible(false);
	}
	

	public void downloadLog(Event event) {
		if (Security.isUserInRole(Security.AUTO_BASE_LOG_QUERY))
		{
			try
			{
				// Get URL server  
				String[] rows = com.soffid.iam.EJBLocator.getSyncServerService().tailServerLog(currentAgent.getUrl());
				Window logWindow = (Window) getFellow("logWindow");
				logWindow.setTitle( currentAgent.getUrl() );
				StringBuffer sb = new StringBuffer();
				if (rows != null) {
					for (String row: rows) {
						sb.append(row).append("\r\n");
					}
				}
				((Textbox)logWindow.getFellow("tb")).setValue( sb.toString() );
				logWindow.doHighlighted();
			}
			catch (Throwable th)
			{
				Missatgebox.error (org.zkoss.util.resource.Labels
						.getLabel("seyconServer.ErrorLog"));
			}
		}

	}

	public void restartAgent(Event event) {
		if (canRestart)
		{
			try
			{
				es.caib.zkib.zkiblaf.Missatgebox.
						confirmaOK_CANCEL(String.format(org.zkoss.util.resource
								.Labels.getLabel("seyconServer.DesitjaReiniciar"),
						new Object [] {currentAgent.getUrl()}),
								(event2) -> {
									if (event2.getName().equalsIgnoreCase("onOK")) {
										String agentUrl = currentAgent.getUrl();
										if (currentAgent.getUrl() == null ||
												"local".equals(currentAgent.getUrl())) {
											agentUrl = serverUrl;
										}
										com.soffid.iam.EJBLocator.getSyncServerService().resetSyncServer(
												serverUrl, agentUrl);
										
										Missatgebox.avis (
												String.format(org.zkoss.util.resource
														.Labels.getLabel("seyconServer.Restarting"), 
														new Object [] {currentAgent.getUrl()}));
										
									}
								});
			}
			catch (Throwable th)
			{
				String fmt = org.zkoss.util.resource
						.Labels.getLabel("seyconServer.ErrorReinici");
				Missatgebox.error (String.format(fmt, new Object []
						{
							(th.getCause()!=null ? th.getCause() : th.toString())
						}
				));
				
			}
		}

	}

	public void viewUnscheduledTasks(Event event) throws InternalErrorException, NamingException, CreateException {
		hideAllDivs();
		((Label)getFellow("form-label")).setValue("");
		getFellow("tab_unscheduledtasks").setVisible(true);
		getFellow("refreshButton").setVisible(true);
		showDetails();
		
		updateUnscheduledTasksGrid();
	}

	public void updateUnscheduledTasksGrid() throws InternalErrorException, NamingException, CreateException {
		unscheduledTasks = new LinkedList<>( EJBLocator.getSyncServerService().findUnscheduledTasks() );
        
        DataTable dt = (DataTable) getFellow("unscheduledtasks_table");
        
        // Set value
        StringBuffer values = new StringBuffer();
        values.append("[");
        for ( SeyconTask st: unscheduledTasks ) {
        	JSONObject value = new JSONObject();
        	value.put("task", st.getTaskName());
        	value.put("status", st.getStatus());
        	if (values.length() > 1)
        		values.append(",");
        	values.append(value.toString());
        }
        values.append("]");
        dt.setData(values.toString());
	}
	
	public void cancelUnscheduledTask(Event event) {
        final DataTable dt = (DataTable) getFellow("unscheduledtasks_table");
        
        final int[] selected = dt.getSelectedIndexes();
        
        if (selected != null && selected.length > 0) {
        	Arrays.sort(selected);
        	String n = unscheduledTasks.get(selected[0]).getTaskName();
        	if (selected.length > 1) 
        		n = n + " (+ "+(selected.length-1)+")";
        	Missatgebox.confirmaOK_CANCEL(
        			String.format(Labels.getLabel("seyconserver.zul.ConfirmCancelTask"), n),
        			(event2) -> {
        				if ("onOK".equals( event2.getName())) {
        					for ( int i = selected.length - 1; i >= 0; i--) {
        						SeyconTask task = unscheduledTasks.get(selected[i]);
        						EJBLocator.getSyncServerService().cancelTask(task.getId());
        						dt.setSelectedIndex(selected[i]);
        						dt.delete();
        					}
        				}
        			});
        }
        	
	}

	public void releaseTask(Event event) {
        final DataTable dt = (DataTable) getFellow("unscheduledtasks_table");
        
        final int[] selected = dt.getSelectedIndexes();
        
        if (selected != null && selected.length > 0) {
        	Arrays.sort(selected);
        	String n = unscheduledTasks.get(selected[0]).getTaskName();
        	if (selected.length > 1) 
        		n = n + " (+ "+(selected.length-1)+")";
        	Missatgebox.confirmaOK_CANCEL(
        			String.format(Labels.getLabel("seyconserver.zul.ConfirmReleaseTask"), n),
        			(event2) -> {
        				if ("onOK".equals( event2.getName())) {
        					for ( int i = selected.length - 1; i >= 0; i--) {
        						SeyconTask task = unscheduledTasks.get(selected[i]);
        						EJBLocator.getSyncServerService().releaseTask(task.getId());
        					}
        					updateUnscheduledTasksGrid();
        				}
        			});
        	
        }
	}

}
