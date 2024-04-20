package com.soffid.iam.web.syncserver;

import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.AgentStatusInfo;
import com.soffid.iam.api.Server;
import com.soffid.iam.api.SyncServerInfo;
import com.soffid.iam.service.SyncServerService;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.menu.YamlParser;

import es.caib.seycon.ng.exception.InternalErrorException;

public class SyncServerMonitor {
	private static Hashtable<String, SyncServerMonitor> monitors = new Hashtable<>();
	Log log = LogFactory.getLog(getClass());
	private long lastUse;
	private Thread thread;
	private Map<String,Thread> serverThreads = new HashMap<>(); 
	private String tenant;
	private Map<String,Object> data = new HashMap<>();
	private List<String> servers = new LinkedList<String>();
	SyncServerService syncServerService  = ServiceLocator.instance().getSyncServerService();
	
	public SyncServerMonitor(String tenant) {
		this.tenant = tenant;
		wakeup();
	}
	
	public static SyncServerMonitor getMonitor() throws InternalErrorException {
		String tenant = Security.getCurrentTenantName();
		SyncServerMonitor monitor = monitors.get(tenant);
		if (monitor == null) {
			monitor = new SyncServerMonitor(tenant);
			monitors.put(tenant, monitor);
		}
		monitor.wakeup();
		return monitor;
	}

	private void wakeup() {
		if (thread == null || !thread.isAlive()) {
			thread = new Thread(() -> {
				Security.nestedLogin(tenant, "anonymous", Security.ALL_PERMISSIONS);
				try {
					readLoop();
				} catch (Exception e) {
					log.warn("Error fetching sync server status", e);
				} finally {
					Security.nestedLogoff();
				}
			});
			thread.start();
		}
	}

	private void readLoop() throws InternalErrorException {
		lastUse = System.currentTimeMillis();
		while (lastUse > System.currentTimeMillis() - 10*60*1000L) { // 10 minutes
			final Collection<Server> newServers = syncServerService.getSyncServers();
			List<String> newServerUrls = new LinkedList<>();
			for (Server server: newServers) {
				Thread t = serverThreads.get(server.getUrl());
				newServerUrls.add(server.getUrl());
				if (t == null) {
					servers.add(server.getUrl());
				}
				if (t == null || !t.isAlive()) {
					t = new Thread( () -> {
						Security.nestedLogin(tenant, "anonymous", Security.ALL_PERMISSIONS);
						try {
							readLoop(server);
						} catch (Exception e) {
						} finally {
							Security.nestedLogoff();
						}
						
					});
					serverThreads.put(server.getUrl(), t);
					t.start();
				}
			}
			for (Iterator<String> it = servers.iterator(); it.hasNext();) {
				String url = it.next();
				if (!newServerUrls.contains(url))
					it.remove();
			}
			readAgentStatus();
			readStats();
			try {
				Thread.sleep(10000);
			} catch (Exception exception ) {}
		}
	}
	
	private void readLoop(Server server) {
		while (lastUse > System.currentTimeMillis() - 10*60*1000L &&
				servers.contains(server.getUrl())) { // 10 minutes
			readStatus(server);
			readSuccessError(server);
			try {
				Thread.sleep(10000);
			} catch (Exception exception ) {}
		}
	}

	private void readSuccessError(Server server) {
		try {
			int step = 10;
			int seconds = 1200;
			Map<String, int[]> stats = syncServerService.getStats(server.getUrl(), "tasks-success", seconds, step);
			Map<String, int[]> statsError = syncServerService.getStats(server.getUrl(), "tasks-error", seconds, step);
			data .put(server.getUrl()+"#success", stats);
			data.put(server.getUrl()+"#failure", statsError);
		} catch (Exception e) {
			data .put(server.getUrl()+"#success", new HashMap<>());
			data.put(server.getUrl()+"#failure", new HashMap<>());
			log.warn("Error fetching stats");
		}
	}

	public Map<String, int[]> getSuccessStats(String serverUrl) {
		lastUse = System.currentTimeMillis();
		Map<String, int[]> s = (Map<String, int[]>) data.get(serverUrl+"#success");
		if (s == null) return new HashMap<>();
		else return s;
	}

	public Map<String, int[]> getFailureStats(String serverUrl) {
		lastUse = System.currentTimeMillis();
		Map<String, int[]> s = (Map<String, int[]>) data.get(serverUrl+"#failure");
		if (s == null) return new HashMap<>();
		else return s;
	}

	private void readStatus(Server server) {
		try {
			SyncServerInfo status = syncServerService.getSyncServerInfo(server.getUrl());
			data.put(server.getUrl()+"#status", status);
		} catch (Exception e) {
			log.warn("Error fetching stats");
		}
	}

	public SyncServerInfo getSyncServerInfo(String url) {
		lastUse = System.currentTimeMillis();
		SyncServerInfo s = (SyncServerInfo) data.get(url+"#status");
		if (s == null) return new SyncServerInfo();
		else return s;
	}


	private void readStats() {
		try {
			Map<String, Vector<Object[]>>  stats = syncServerService.getPendingTasksStats();
			data.put("#stats", stats);
		} catch (Exception e) {
			log.warn("Error fetching stats");
		}
	}

	public Map<String, Vector<Object[]>> getStats() {
		lastUse = System.currentTimeMillis();
		Map<String, Vector<Object[]>>  s = (Map<String, Vector<Object[]>> ) data.get("#stats");
		if (s == null) return new HashMap<>();
		else return s;
	}

	private void readAgentStatus() {
		try {
			Collection<AgentStatusInfo>  status = syncServerService.getServerAgentStatus();
			data.put("#agentstatus", status);
		} catch (Exception e) {
			log.warn("Error fetching stats");
		}
	}

	public Collection<AgentStatusInfo> getAgentStatus() {
		lastUse = System.currentTimeMillis();
		Collection<AgentStatusInfo>  s = (Collection<AgentStatusInfo> ) data.get("#agentstatus");
		if (s == null) return new LinkedList<>();
		else return s;
	}

}
