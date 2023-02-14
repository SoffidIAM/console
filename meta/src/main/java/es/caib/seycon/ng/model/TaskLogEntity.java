//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;

import java.util.Collection;

import com.soffid.mda.annotation.*;

@Entity(table = "SC_TASKLOG", translatedName = "TaskLogEntity", translatedPackage = "com.soffid.iam.model")
@Depends({ es.caib.seycon.ng.model.TasqueEntity.class,
		es.caib.seycon.ng.model.DispatcherEntity.class })
public abstract class TaskLogEntity {

	@Column(name = "TLO_COMPLETE", length = 1, translated = "completed")
	@Nullable
	public java.lang.String complet;

	@Column(name = "TLO_MISSAT", length = 1024, translated = "message")
	@Nullable
	public java.lang.String missatge;

	@Column(name = "TLO_DATCREA", translated = "creationDate")
	@Nullable
	public java.util.Date dataCreacio;

	@Column(name = "TLO_DADAEX", translated = "lastExecution")
	@Nullable
	public java.lang.Long darreraExecucio;

	@Column(name = "TLO_PRDAEX", translated = "nextExecution")
	@Nullable
	public java.lang.Long proximaExecucio;

	@Column(name = "TLO_NUMEXE", translated = "executionsNumber")
	@Nullable
	public java.lang.Long numExecucions;

	@Column(name = "TLO_STACK", length = 4000)
	@Nullable
	public java.lang.String stackTrace;

	@Column(name = "TLO_IDTASQUE", translated = "task", reverseAttribute = "taskLogs")
	public es.caib.seycon.ng.model.TasqueEntity tasca;

	@Column(name = "TLO_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name = "TLO_DIS_ID", translated = "system")
	public es.caib.seycon.ng.model.DispatcherEntity dispatcher;

	@Operation(translated = "findByTaskID")
	@DaoFinder("from com.soffid.iam.model.TaskLogEntity tlog\nwhere tlog.task.id=:taskId")
	public java.util.List<es.caib.seycon.ng.model.TaskLogEntity> findByIdTasca(
			java.lang.Long taskId) {
		return null;
	}

	@DaoFinder("select tlog from com.soffid.iam.model.TaskLogEntity tlog \n"
			+ "left join tlog.task tasca\n" + "where  \n"
			+ "  (:server is not null and tasca.server=:server) \n"
			+ "  or (:server is null and tasca.server is null)\n"
			+ "order by tlog.task.id, tlog.system.name")
	public java.util.List<es.caib.seycon.ng.model.TaskLogEntity> findAllHavingTasqueByServer(
			java.lang.String server) {
		return null;
	}

	@DaoFinder("select tlog from com.soffid.iam.model.TaskLogEntity tlog \n"
			+ "left join tlog.task tasca\n" 
			+ "where  \n"
			+ "  (:server is not null and tasca.server=:server) or (:server is null and tasca.server is null) and \n"
			+ "  (:serverInstance is not null and tasca.serverInstance=:serverInstance) or (:serverInstance is null and tasca.serverInstance is null)\n"
			+ "order by tlog.task.id, tlog.system.name")
	public java.util.List<es.caib.seycon.ng.model.TaskLogEntity> findAllHavingTasqueByServerAndServerInstance(
			java.lang.String server, String serverInstance) {
		return null;
	}

	@DaoFinder("select tlog from com.soffid.iam.model.TaskLogEntity tlog\n"
			+ "left join tlog.system system\n" + "left join tlog.task task\n"
			+ "where system.name=:system and \n"
			+ "(:status is null or tlog.completed=:status) and " + "( \n"
			+ "   (:server is not null and task.server=:server) or \n"
			+ "   (:server is null and task.server is null) \n"
			+ ") order by task.id, system.name")
	public java.util.List<es.caib.seycon.ng.model.TaskLogEntity> findAllHavingTasqueByAgentAndServer(
			java.lang.String server, java.lang.String system,
			@Nullable java.lang.String status) {
		return null;
	}

	@DaoFinder("select tlog "
			+ "from com.soffid.iam.model.TaskLogEntity tlog "
			+ "where tlog.system.name=:system  " //$NON-NLS-1$
			+ "and (tlog.task.server=:server and :server is not null or tlog.task.server is null and :server is null) "
			+ "order by tlog.task.id, tlog.system.name")
	public java.util.List<es.caib.seycon.ng.model.TaskLogEntity> findByServerAndSystem(
			java.lang.String server, java.lang.String system) {
		return null;
	}
	
	@DaoFinder("select tlog "
			+ "from com.soffid.iam.model.TaskLogEntity tlog "
			+ "where tlog.system.name=:system  " //$NON-NLS-1$
			+ "and (tlog.task.server=:server and :server is not null or tlog.task.server is null and :server is null) and "
			+ "    (tlog.task.serverInstance=:serverInstance and :serverInstance is not null or tlog.task.serverInstance is null and :serverInstance is null)"
			+ "order by tlog.task.id, tlog.system.name")
	public java.util.List<es.caib.seycon.ng.model.TaskLogEntity> findByServerAndSystem(
			java.lang.String server, String serverInstance, java.lang.String system) {
		return null;
	}
	
	@DaoFinder("select system.name, count(distinct task.id) from \n"
			+ "com.soffid.iam.model.TaskLogEntity tlo\n"
			+ "where task.tenant.id = :tenantId and completed='S'  \n"
			+ "group by system.name \n"
			+ "order by system.name")
	public Collection<Object[]> countTasksBySystem() {
		return null;
	}

	@DaoFinder("select system.name, count(distinct task.id) from \n"
			+ "com.soffid.iam.model.TaskLogEntity tlo\n"
			+ "where task.tenant.id = :tenantId and completed='S' and task.server=:server and \n"
			+ "(task.systemName is null or task.systemName = system.name) "
			+ "group by system.name \n"
			+ "order by system.name")
	public Collection<Object[]> countTasksByServerAndSystem(String server) {
		return null;
	}

	@DaoFinder("select system.name, count(distinct task.id) from \n"
			+ "com.soffid.iam.model.TaskLogEntity tlo\n"
			+ "where task.tenant.id = :tenantId and completed='S' and task.server=:server and task.serverInstance=:serverInstance and \n"
			+ "(task.systemName is null or task.systemName = system.name) "
			+ "group by system.name \n"
			+ "order by system.name")
	public Collection<Object[]> countTasksByServerAndSystem(String server, String serverInstance) {
		return null;
	}
}
