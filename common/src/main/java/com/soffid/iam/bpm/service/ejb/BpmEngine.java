//
// (C) 2013 Soffid
//
//

package com.soffid.iam.bpm.service.ejb;
/**
 * EJB BpmEngine
 */
public interface BpmEngine

 {

	void downloadParFile(
		final com.soffid.iam.bpm.api.ProcessDefinition def, 
		final java.io.OutputStream stream)
	throws com.soffid.iam.exception.InternalErrorException, java.lang.Exception;

	boolean canAdmin(
		final com.soffid.iam.bpm.api.ProcessInstance instanceVO)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	boolean isUserInRole(
		final java.lang.String role)
	throws com.soffid.iam.exception.InternalErrorException;

	byte[] getProcessDefinitionIcon(
		final java.lang.Long definitionId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	byte[] getProcessDefinitionImage(
		final com.soffid.iam.bpm.api.ProcessDefinition def)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.bpm.api.DeployToken openDeployParDefinitionTransfer()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	com.soffid.iam.bpm.api.ProcessDefinition disableProcessDefinition(
		final com.soffid.iam.bpm.api.ProcessDefinition defVO)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	com.soffid.iam.bpm.api.ProcessDefinition enableProcessDefinition(
		final com.soffid.iam.bpm.api.ProcessDefinition defVO)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	com.soffid.iam.bpm.api.ProcessDefinition getDefinition(
		final com.soffid.iam.bpm.api.ProcessInstance process)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.bpm.api.ProcessDefinition getProcessDefinition(
		final com.soffid.iam.bpm.api.ProcessInstance process)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.bpm.api.ProcessInstance cancel(
		final com.soffid.iam.bpm.api.ProcessInstance process)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.bpm.api.ProcessInstance getProcess(
		final long id)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	com.soffid.iam.bpm.api.ProcessInstance getProcessInstance(
		final com.soffid.iam.bpm.api.TaskInstance task)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.bpm.api.ProcessInstance getProcessLightweight(
		final long id)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	com.soffid.iam.bpm.api.ProcessInstance newProcess(
		final com.soffid.iam.bpm.api.ProcessDefinition def)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	com.soffid.iam.bpm.api.ProcessInstance newProcess(
		final com.soffid.iam.bpm.api.ProcessDefinition def, 
		final boolean start)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	com.soffid.iam.bpm.api.ProcessLog[] getProcessLog(
		final com.soffid.iam.bpm.api.ProcessInstance process)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	com.soffid.iam.bpm.api.ProcessLog[] getTaskLog(
		final com.soffid.iam.bpm.api.TaskInstance process)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	com.soffid.iam.bpm.api.TaskDefinition getDefinition(
		final com.soffid.iam.bpm.api.TaskInstance task)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.bpm.api.TaskInstance addComment(
		final com.soffid.iam.bpm.api.TaskInstance task, 
		final java.lang.String comment)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.bpm.api.TaskInstance cancel(
		final com.soffid.iam.bpm.api.TaskInstance task)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.bpm.api.TaskInstance createDummyTask(
		final long processDefinitionId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.bpm.api.TaskInstance delegateTaskToUser(
		final com.soffid.iam.bpm.api.TaskInstance task, 
		final java.lang.String username)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.bpm.api.TaskInstance executeTask(
		final com.soffid.iam.bpm.api.TaskInstance task, 
		final java.lang.String transitionName)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	com.soffid.iam.bpm.api.TaskInstance getTask(
		final long id)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	com.soffid.iam.bpm.api.TaskInstance reserveTask(
		final com.soffid.iam.bpm.api.TaskInstance task)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	com.soffid.iam.bpm.api.TaskInstance startTask(
		final com.soffid.iam.bpm.api.TaskInstance task)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	com.soffid.iam.bpm.api.TaskInstance update(
		final com.soffid.iam.bpm.api.TaskInstance task)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	com.soffid.iam.bpm.api.Token[] getTokens(
		final long id)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessDefinition> findProcessDefinitionByTextAndJsonQuery(
		final java.lang.String text, 
		final java.lang.String jsonQuery, 
		final java.lang.Integer start, 
		final java.lang.Integer pageSize)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstance(
		final com.soffid.zkdb.api.Query query)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstanceByTextAndJsonQuery(
		final java.lang.String text, 
		final java.lang.String jsonQuery, 
		final java.lang.Integer start, 
		final java.lang.Integer pageSize)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.TaskInstance> findTasksByTextAndJsonQuery(
		final java.lang.String text, 
		final java.lang.String jsonQuery, 
		final java.lang.Integer start, 
		final java.lang.Integer pageSize)
	throws com.soffid.iam.exception.InternalErrorException;

	int countMyTasks()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	int countNewTasks()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	int[] getCoordinates(
		final com.soffid.iam.bpm.api.ProcessInstance task)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	int[] getCoordinates(
		final com.soffid.iam.bpm.api.TaskInstance task)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.io.InputStream getResourceAsStream(
		final com.soffid.iam.bpm.api.ProcessDefinition processdef, 
		final java.lang.String resource)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.io.InputStream getResourceAsStream(
		final com.soffid.iam.bpm.api.ProcessInstance process, 
		final java.lang.String resource)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String getUI(
		final com.soffid.iam.bpm.api.ProcessInstance process)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String getUI(
		final com.soffid.iam.bpm.api.TaskInstance task)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String[] getDeployMessages(
		final com.soffid.iam.bpm.api.DeployToken token)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String[] upgradeProcess(
		final com.soffid.iam.bpm.api.ProcessInstance instanceVO)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	java.util.Collection<java.lang.Long> findChildProcesses(
		final java.lang.Long processId)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<java.lang.Long> findParentProceeses(
		final java.lang.Long processId)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.bpm.api.BPMUser> findUsers(
		final java.lang.String userName, 
		final java.lang.String givenName, 
		final java.lang.String surName, 
		final java.lang.String group)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findAllProcessDefinitions(
		final boolean onlyEnabled)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.bpm.api.TaskInstance> findGroupTasks()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findInitiatorProcessDefinitions()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	java.util.List<com.soffid.iam.bpm.api.ProcessInstance> findMyProcesses()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.bpm.api.TaskInstance> findMyTasks()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	java.util.List<com.soffid.iam.bpm.api.TaskInstance> findMyTasksLightweight()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findObserverProcessDefinitions()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findProcessDefinitions(
		final java.lang.String name, 
		final boolean onlyEnabled)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findProcessDefinitions(
		final java.lang.String name, 
		final com.soffid.iam.bpm.api.PredefinedProcessType processType)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstances(
		final com.soffid.iam.bpm.api.ProcessDefinition def)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	java.util.List<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstances(
		final java.util.List definitions, 
		final java.lang.String processId, 
		final java.lang.String estado, 
		final java.lang.String actor, 
		final java.util.Date startDate, 
		final boolean finalizada)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findSupervisorProcessDefinitions()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	java.util.List<com.soffid.iam.bpm.api.TaskDefinition> findTaskDefinitions(
		final com.soffid.iam.bpm.api.ProcessDefinition def)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	java.util.List<com.soffid.iam.bpm.api.TaskInstance> findTasks(
		final java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, 
		final com.soffid.iam.bpm.api.TaskDefinition task, 
		final java.lang.String actor, 
		final java.util.Date processStartDate, 
		final java.util.Date taskCreationDate, 
		final boolean finalizada)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	java.util.List<com.soffid.iam.bpm.api.TaskInstance> findTasks(
		final java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, 
		final java.lang.String process, 
		final com.soffid.iam.bpm.api.TaskDefinition task, 
		final java.lang.String actor, 
		final java.util.Date processStartDate, 
		final java.util.Date taskCreationDate, 
		final boolean finalizada)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	java.util.List<com.soffid.iam.bpm.api.Job> getActiveJobs()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	java.util.List<com.soffid.iam.bpm.api.Job> getActiveJobs(
		final com.soffid.iam.bpm.api.ProcessInstance process)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	java.util.List<com.soffid.iam.bpm.api.TaskInstance> getActiveTasks(
		final com.soffid.iam.bpm.api.ProcessInstance process)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	java.util.List<com.soffid.iam.bpm.api.Job> getAllJobs()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	java.util.List<com.soffid.iam.bpm.api.TaskInstance> getPendingTasks(
		final com.soffid.iam.bpm.api.ProcessInstance process)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	java.util.List<com.soffid.iam.bpm.api.ProcessInstance> searchProcessInstances(
		final java.lang.String query, 
		final java.lang.String processID, 
		final java.util.Date sinceStartDate, 
		final java.util.Date untilStartDate, 
		final java.util.Date sinceEndDate, 
		final java.util.Date untilEndDate, 
		final boolean finished)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	java.util.Map getUIClassesForTask(
		final com.soffid.iam.bpm.api.ProcessDefinition def)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.sql.SQLException, java.io.IOException;

	org.jbpm.JbpmConfiguration getJBpmConfiguration()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	org.jbpm.JbpmContext getContext()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void addComment(
		final com.soffid.iam.bpm.api.ProcessInstance process, 
		final java.lang.String comment)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void endDeployParDefinitionTransfer(
		final com.soffid.iam.bpm.api.DeployToken token)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	void linkProcesses(
		final java.lang.Long parentProcess, 
		final java.lang.Long childProcess)
	throws com.soffid.iam.exception.InternalErrorException;

	void nextDeployParDefinitionPackage(
		final com.soffid.iam.bpm.api.DeployToken token, 
		final byte[] filePackage, 
		final int length)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	void pauseJob(
		final com.soffid.iam.bpm.api.Job jobvo)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	void ping()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void reindex()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.io.IOException;

	void resumeJob(
		final com.soffid.iam.bpm.api.Job jobvo)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	void retryJob(
		final com.soffid.iam.bpm.api.Job jobvo)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	void signal(
		final com.soffid.iam.bpm.api.ProcessInstance instanceVO)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	void signal(
		final com.soffid.iam.bpm.api.ProcessInstance instanceVO, 
		final java.lang.String transitionName)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	void startProcess(
		final com.soffid.iam.bpm.api.ProcessInstance process)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	void update(
		final com.soffid.iam.bpm.api.ProcessInstance process)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	void updateSwimlane(
		final com.soffid.iam.bpm.api.TaskInstance task, 
		final java.lang.String swimlane, 
		final java.lang.String[] actorIds)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	void upgradeParFile(
		final java.io.InputStream stream)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
