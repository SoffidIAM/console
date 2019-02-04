//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;

import com.soffid.iam.model.TenantEntity;
import com.soffid.iam.ui.SeyconTask;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.comu.SeyconServerInfo;
import es.caib.seycon.ng.comu.Tasca;
import es.caib.seycon.ng.model.ConfiguracioEntity;

import java.util.Collection;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

@Service(translatedName = "SyncServerService", translatedPackage = "com.soffid.iam.service")
@Depends({ es.caib.seycon.ng.model.TasqueEntity.class, es.caib.seycon.ng.model.TaskLogEntity.class,
		es.caib.seycon.ng.model.DispatcherEntity.class, es.caib.seycon.ng.model.ServerEntity.class, TenantEntity.class,
		ConfiguracioService.class, ConfiguracioEntity.class })
public abstract class SeyconServerService {

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

	@Operation(grantees = { roles.base_log_query.class }, translated = "getSeyconServerLog")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.io.InputStream getSeyconServerLog(java.lang.String urlServer)
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

}
