//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.transaction.annotation.Transactional;

import com.soffid.iam.api.StatsSample;
import com.soffid.iam.model.StatsEntity;
import com.soffid.iam.model.TenantEntity;
import com.soffid.iam.service.StatsService;
import com.soffid.iam.ui.SeyconTask;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.comu.SeyconServerInfo;
import es.caib.seycon.ng.model.ConfiguracioEntity;
import es.caib.seycon.ng.model.ServerInstanceEntity;

@Service(translatedName = "SyncServerService", translatedPackage = "com.soffid.iam.service")
@Depends({ es.caib.seycon.ng.model.TasqueEntity.class, es.caib.seycon.ng.model.TaskLogEntity.class,
		es.caib.seycon.ng.model.DispatcherEntity.class, es.caib.seycon.ng.model.ServerEntity.class, TenantEntity.class,
		ServerInstanceEntity.class,
		ConfiguracioService.class, ConfiguracioEntity.class ,
		DispatcherService.class,
		StatsService.class,
		StatsEntity.class})
public abstract class SeyconServerService {

	@Operation(grantees = { roles.monitor_server_list.class }, translated = "getSyncServers")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Server> getSyncServers()
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.monitor_server_list.class }, translated = "getSyncServerInstances")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Server> getSyncServerInstances()
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.monitor_server_list.class }, translated = "getSyncServersStatus")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.SeyconServerInfo> getSeyconServersStatus()
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.monitor_agent_list.class }, translated = "getServerAgentStatus")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.AgentStatusInfo> getServerAgentStatus(java.lang.String url)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.monitor_agent_list.class }, translated = "getServerAgentStatus")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.AgentStatusInfo> getServerAgentStatus()
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.monitor_agent_list.class }, translated = "getPendingTasksStats")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	@Description ("Returns a map with a list of pairs date - long for each agent. The key is the agent name. An special agent named 'Unscheduled' holds the not scheduled tasks")
	public Map<String, Vector<Object[]>> getPendingTasksStats()
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}
	
	@Operation(grantees = { roles.monitor_server_list.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public Collection<SeyconTask> findUnscheduledTasks() {
		return null;
	}

	@Operation(grantees = { roles.monitor_server_list.class }, translated = "getServerTasks")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<Object> getServerTasks(java.lang.String url)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.monitor_agent_list.class }, translated = "getAgentTasks")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.SeyconAgentTaskLog> getAgentTasks(java.lang.String url,
			java.lang.String agentCodi) throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.monitor_agent_list.class }, translated = "getAgentTasks")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.SeyconAgentTaskLog getAgentTask(java.lang.String url,
			java.lang.String agentName, Long taskId) throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.base_log_query.class }, translated = "getSeyconServerLog")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.io.InputStream getSeyconServerLog(java.lang.String urlServer)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.base_log_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public String[] tailServerLog(java.lang.String urlServer)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.monitor_server_list.class }, translated = "getPendingTasksInfo")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.SeyconServerInfo> getPendingTasksInfo()
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.monitor_server_list.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public SeyconServerInfo getSyncServerInfo(java.lang.String url)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.monitor_server_list.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void resetSyncServer(java.lang.String url, @Nullable String server)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Operation
	@Description("Gets a remote interface for any server, using the console specific authorization key")
	public Object getServerService(String servicePath) {
		return null;
	}

	@Operation
	@Description("Calls synchronization servers to notify dispatcher configuration changes")
	public void updateDispatcherConfiguration() {
	}

	@Operation(grantees = { roles.monitor_server_list.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void cancelTask(long taskId) {

	}

	@Operation(grantees = { roles.monitor_server_list.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void boostTask(long taskId) {

	}

	@Operation(grantees = { roles.monitor_server_list.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void releaseTask(long taskId) {

	}

	@Operation(grantees = { roles.monitor_server_list.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void releaseAllTasks() {

	}

	@Operation(grantees = { roles.monitor_server_list.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void cancelUnscheduledTasks() {

	}
	
	@Operation(grantees = { roles.monitor_server_list.class })
	public Map<String,int[]> getStats ( String server, String metric, int seconds, int step ) { return null;}

	public void updatePendingTasks() {};
}
