//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.bpm.servei;
import com.soffid.iam.model.ProcessHierarchyEntity;
import com.soffid.mda.annotation.*;

import es.caib.bpm.vo.Job;
import es.caib.bpm.vo.PredefinedProcessType;
import es.caib.bpm.vo.ProcessDefinition;
import es.caib.bpm.vo.ProcessInstance;
import es.caib.bpm.vo.TaskDefinition;
import es.caib.bpm.vo.TaskInstance;
import es.caib.seycon.ng.model.AuditoriaEntity;
import es.caib.seycon.ng.servei.AplicacioService;

import java.util.Collection;
import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

@Service ( grantees={roles.Tothom.class}, translatedName="BpmEngine", translatedPackage="com.soffid.iam.bpm.service")
@Depends ({es.caib.bpm.servei.BpmConfigService.class,
	es.caib.seycon.ng.servei.UsuariService.class, AplicacioService.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.DispatcherEntity.class,
	es.caib.seycon.ng.servei.SessionCacheService.class,
	ProcessHierarchyEntity.class, 
	AuditoriaEntity.class})
public abstract class BpmEngine {

	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<ProcessInstance> findMyProcesses()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.bpm.vo.ProcessDefinition getProcessDefinition(
		es.caib.bpm.vo.ProcessInstance process)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.bpm.vo.TaskInstance createDummyTask (
		long processDefinitionId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.bpm.vo.ProcessInstance cancel(
		es.caib.bpm.vo.ProcessInstance process)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<ProcessInstance> searchProcessInstances(
		@Nullable java.lang.String query, 
		@Nullable java.lang.String processID, 
		@Nullable Date sinceStartDate, @Nullable Date untilStartDate, 
		@Nullable Date sinceEndDate, @Nullable Date untilEndDate,
		boolean finished)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<ProcessInstance> findProcessInstances(
		java.util.List definitions, 
		@Nullable java.lang.String processId, 
		@Nullable java.lang.String estado, 
		@Nullable java.lang.String actor, 
		@Nullable java.util.Date startDate, 
		boolean finalizada)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<ProcessInstance> findProcessInstances(
		es.caib.bpm.vo.ProcessDefinition def)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.bpm.vo.ProcessDefinition getDefinition(
		es.caib.bpm.vo.ProcessInstance process)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void update(
		es.caib.bpm.vo.ProcessInstance process)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void startProcess(
		es.caib.bpm.vo.ProcessInstance process)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	}
	@Transactional(rollbackFor={}, noRollbackFor={java.lang.Exception.class},readOnly=true)
	public es.caib.bpm.vo.ProcessInstance getProcess(
		long id)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.bpm.vo.Token[] getTokens(
		long id)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.bpm.vo.ProcessLog[] getProcessLog(
		es.caib.bpm.vo.ProcessInstance process)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.bpm.vo.ProcessLog[] getTaskLog(
		es.caib.bpm.vo.TaskInstance process)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void upgradeProcess(
		es.caib.bpm.vo.ProcessInstance instanceVO)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<TaskInstance> findMyTasks()
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<TaskInstance> findMyTasksLightweight()
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<TaskInstance> findGroupTasks()
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.bpm.vo.TaskInstance startTask(
		es.caib.bpm.vo.TaskInstance task)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.bpm.vo.TaskInstance addComment(
		es.caib.bpm.vo.TaskInstance task, 
		java.lang.String comment)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.bpm.vo.TaskInstance executeTask(
		es.caib.bpm.vo.TaskInstance task, 
		java.lang.String transitionName)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.bpm.vo.TaskInstance reserveTask(
		es.caib.bpm.vo.TaskInstance task)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.bpm.vo.TaskInstance delegateTaskToUser(
		es.caib.bpm.vo.TaskInstance task, 
		java.lang.String username)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public TaskInstance update(
		es.caib.bpm.vo.TaskInstance task)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException { 
		return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void updateSwimlane(
		es.caib.bpm.vo.TaskInstance task, 
		java.lang.String swimlane, 
		java.lang.String[] actorIds)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	}
	@Transactional(noRollbackFor={java.lang.Exception.class}, readOnly=true)
	public es.caib.bpm.vo.ProcessInstance getProcessInstance(
		es.caib.bpm.vo.TaskInstance task)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.bpm.vo.TaskInstance cancel(
		es.caib.bpm.vo.TaskInstance task)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<TaskInstance> findTasks(
		java.util.List<ProcessInstance> def, 
		es.caib.bpm.vo.TaskDefinition task, 
		java.lang.String actor, 
		java.util.Date processStartDate, 
		java.util.Date taskCreationDate, 
		boolean finalizada)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<TaskInstance> findTasks(
		java.util.List<ProcessInstance> def, 
		java.lang.String process, 
		es.caib.bpm.vo.TaskDefinition task, 
		java.lang.String actor, 
		java.util.Date processStartDate, 
		java.util.Date taskCreationDate, 
		boolean finalizada)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String getUI(
		es.caib.bpm.vo.TaskInstance task)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String getUI(
		es.caib.bpm.vo.ProcessInstance process)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.bpm.vo.TaskDefinition getDefinition(
		es.caib.bpm.vo.TaskInstance task)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<TaskInstance> getPendingTasks(
		es.caib.bpm.vo.ProcessInstance process)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}

	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<ProcessDefinition> findProcessDefinitions(
		@Nullable java.lang.String name, 
		boolean onlyEnabled)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Description("Searches for process definition of a predefined type")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<ProcessDefinition> findProcessDefinitions(
		@Nullable java.lang.String name, 
		PredefinedProcessType processType)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.bpm.vo.ProcessInstance newProcess(
		es.caib.bpm.vo.ProcessDefinition def)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.bpm.vo.ProcessInstance newProcess(
		es.caib.bpm.vo.ProcessDefinition def, 
		boolean start)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public byte[] getProcessDefinitionImage(
			es.caib.bpm.vo.ProcessDefinition def)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public byte[] getProcessDefinitionIcon(
			Long definitionId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public int[] getCoordinates(
		es.caib.bpm.vo.TaskInstance task)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public int[] getCoordinates(
		es.caib.bpm.vo.ProcessInstance task)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<ProcessDefinition> findInitiatorProcessDefinitions()
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<ProcessDefinition> findObserverProcessDefinitions()
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<ProcessDefinition> findSupervisorProcessDefinitions()
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Map getUIClassesForTask(
		es.caib.bpm.vo.ProcessDefinition def)
		throws es.caib.seycon.ng.exception.InternalErrorException, java.sql.SQLException, java.io.IOException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<TaskDefinition> findTaskDefinitions(
		es.caib.bpm.vo.ProcessDefinition def)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.bpm.vo.ProcessDefinition enableProcessDefinition(
		es.caib.bpm.vo.ProcessDefinition defVO)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.bpm.vo.ProcessDefinition disableProcessDefinition(
		es.caib.bpm.vo.ProcessDefinition defVO)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void openDeployParDefinitionTransfer()
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void nextDeployParDefinitionPackage(
		byte[] filePackage, 
		int length)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void endDeployParDefinitionTransfer()
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String[] getDeployMessages()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={},noRollbackFor={java.lang.Exception.class}, readOnly=true)
	public es.caib.bpm.vo.TaskInstance getTask(
		long id)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}

	@Transactional(rollbackFor={java.lang.Exception.class})
	public org.jbpm.JbpmConfiguration getJBpmConfiguration()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public org.jbpm.JbpmContext getContext()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void signal(
		es.caib.bpm.vo.ProcessInstance instanceVO)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void signal(
		es.caib.bpm.vo.ProcessInstance instanceVO, 
		java.lang.String transitionName)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean canAdmin(
		es.caib.bpm.vo.ProcessInstance instanceVO)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return false;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<Job> getActiveJobs(
		es.caib.bpm.vo.ProcessInstance process)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<TaskInstance> getActiveTasks(
		es.caib.bpm.vo.ProcessInstance process)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<Job> getActiveJobs()
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void resumeJob(
		es.caib.bpm.vo.Job jobvo)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void pauseJob(
		es.caib.bpm.vo.Job jobvo)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void retryJob(
		es.caib.bpm.vo.Job jobvo)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.bpm.exception.BPMException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void changeConfiguration(
		java.util.Map<String,String> m)
		throws es.caib.seycon.ng.exception.InternalErrorException, java.io.IOException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Map<String,String> getConfiguration()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void reindex()
		throws es.caib.seycon.ng.exception.InternalErrorException, java.io.IOException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void ping()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.io.InputStream getResourceAsStream(
		es.caib.bpm.vo.ProcessInstance process, 
		java.lang.String resource)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.io.InputStream getResourceAsStream(
		es.caib.bpm.vo.ProcessDefinition processdef, 
		java.lang.String resource)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.bpm.vo.BPMUser> findUsers(
		@Nullable java.lang.String userName, 
		@Nullable java.lang.String givenName, 
		@Nullable java.lang.String surName, 
		@Nullable java.lang.String group)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void upgradeParFile(
		java.io.InputStream stream)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Transactional(rollbackFor={java.lang.Exception.class},readOnly=true)
	public boolean isUserInRole (String role)
	{
		return false;
	}

	@Transactional(rollbackFor={java.lang.Exception.class},readOnly=true)
	public Collection<Long> findChildProcesses (Long processId)
	{
		return null;
	}

	@Transactional(rollbackFor={java.lang.Exception.class},readOnly=true)
	public Collection<Long> findParentProceeses (Long processId)
	{
		return null;
	}
	
	public void linkProcesses (Long parentProcess, Long childProcess) { }
}
