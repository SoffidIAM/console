//
// (C) 2013 Soffid
//
//

package com.soffid.iam.bpm.service;
/**
 * Service BpmEngine
 */
public interface BpmEngine {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.bpm.service.BpmEngine";

	public final static String SERVICE_NAME = "com.soffid.iam.bpm.service.BpmEngine";

	/**
	 * Operation downloadParFile

	 * @param def 
	 * @param stream 
	 */
	void downloadParFile(
		final com.soffid.iam.bpm.api.ProcessDefinition def, 
		final java.io.OutputStream stream)
			throws com.soffid.iam.exception.InternalErrorException, java.lang.Exception;

	/**
	 * Operation canAdmin

	 * @param instanceVO 
	 * @return 
	 */
	boolean canAdmin(
		final com.soffid.iam.bpm.api.ProcessInstance instanceVO)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation isUserInRole

	 * @param role 
	 * @return 
	 */
	boolean isUserInRole(
		final java.lang.String role)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getProcessDefinitionIcon

	 * @param definitionId 
	 * @return 
	 */
	byte[] getProcessDefinitionIcon(
		final java.lang.Long definitionId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getProcessDefinitionImage

	 * @param def 
	 * @return 
	 */
	byte[] getProcessDefinitionImage(
		final com.soffid.iam.bpm.api.ProcessDefinition def)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation openDeployParDefinitionTransfer

	 * @return 
	 */
	com.soffid.iam.bpm.api.DeployToken openDeployParDefinitionTransfer()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation disableProcessDefinition

	 * @param defVO 
	 * @return 
	 */
	com.soffid.iam.bpm.api.ProcessDefinition disableProcessDefinition(
		final com.soffid.iam.bpm.api.ProcessDefinition defVO)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation enableProcessDefinition

	 * @param defVO 
	 * @return 
	 */
	com.soffid.iam.bpm.api.ProcessDefinition enableProcessDefinition(
		final com.soffid.iam.bpm.api.ProcessDefinition defVO)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation getDefinition

	 * @param process 
	 * @return 
	 */
	com.soffid.iam.bpm.api.ProcessDefinition getDefinition(
		final com.soffid.iam.bpm.api.ProcessInstance process)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getProcessDefinition

	 * @param process 
	 * @return 
	 */
	com.soffid.iam.bpm.api.ProcessDefinition getProcessDefinition(
		final com.soffid.iam.bpm.api.ProcessInstance process)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation cancel

	 * @param process 
	 * @return 
	 */
	com.soffid.iam.bpm.api.ProcessInstance cancel(
		final com.soffid.iam.bpm.api.ProcessInstance process)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getProcess

	 * @param id 
	 * @return 
	 */
	com.soffid.iam.bpm.api.ProcessInstance getProcess(
		final long id)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation getProcessInstance

	 * @param task 
	 * @return 
	 */
	com.soffid.iam.bpm.api.ProcessInstance getProcessInstance(
		final com.soffid.iam.bpm.api.TaskInstance task)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getProcessLightweight

	 * @param id 
	 * @return 
	 */
	com.soffid.iam.bpm.api.ProcessInstance getProcessLightweight(
		final long id)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation newProcess

	 * @param def 
	 * @return 
	 */
	com.soffid.iam.bpm.api.ProcessInstance newProcess(
		final com.soffid.iam.bpm.api.ProcessDefinition def)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation newProcess

	 * @param def 
	 * @param start 
	 * @return 
	 */
	com.soffid.iam.bpm.api.ProcessInstance newProcess(
		final com.soffid.iam.bpm.api.ProcessDefinition def, 
		final boolean start)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation getProcessLog

	 * @param process 
	 * @return 
	 */
	com.soffid.iam.bpm.api.ProcessLog[] getProcessLog(
		final com.soffid.iam.bpm.api.ProcessInstance process)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation getTaskLog

	 * @param process 
	 * @return 
	 */
	com.soffid.iam.bpm.api.ProcessLog[] getTaskLog(
		final com.soffid.iam.bpm.api.TaskInstance process)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation getDefinition

	 * @param task 
	 * @return 
	 */
	com.soffid.iam.bpm.api.TaskDefinition getDefinition(
		final com.soffid.iam.bpm.api.TaskInstance task)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation addComment

	 * @param task 
	 * @param comment 
	 * @return 
	 */
	com.soffid.iam.bpm.api.TaskInstance addComment(
		final com.soffid.iam.bpm.api.TaskInstance task, 
		final java.lang.String comment)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation cancel

	 * @param task 
	 * @return 
	 */
	com.soffid.iam.bpm.api.TaskInstance cancel(
		final com.soffid.iam.bpm.api.TaskInstance task)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createDummyTask

	 * @param processDefinitionId 
	 * @return 
	 */
	com.soffid.iam.bpm.api.TaskInstance createDummyTask(
		final long processDefinitionId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delegateTaskToUser

	 * @param task 
	 * @param username 
	 * @return 
	 */
	com.soffid.iam.bpm.api.TaskInstance delegateTaskToUser(
		final com.soffid.iam.bpm.api.TaskInstance task, 
		final java.lang.String username)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation executeTask

	 * @param task 
	 * @param transitionName 
	 * @return 
	 */
	com.soffid.iam.bpm.api.TaskInstance executeTask(
		final com.soffid.iam.bpm.api.TaskInstance task, 
		final java.lang.String transitionName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation getTask

	 * @param id 
	 * @return 
	 */
	com.soffid.iam.bpm.api.TaskInstance getTask(
		final long id)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation reserveTask

	 * @param task 
	 * @return 
	 */
	com.soffid.iam.bpm.api.TaskInstance reserveTask(
		final com.soffid.iam.bpm.api.TaskInstance task)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation startTask

	 * @param task 
	 * @return 
	 */
	com.soffid.iam.bpm.api.TaskInstance startTask(
		final com.soffid.iam.bpm.api.TaskInstance task)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation update

	 * @param task 
	 * @return 
	 */
	com.soffid.iam.bpm.api.TaskInstance update(
		final com.soffid.iam.bpm.api.TaskInstance task)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation getTokens

	 * @param id 
	 * @return 
	 */
	com.soffid.iam.bpm.api.Token[] getTokens(
		final long id)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation findProcessDefinitionByTextAndJsonQuery

	 * @param text 
	 * @param jsonQuery 
	 * @param start 
	 * @param pageSize 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessDefinition> findProcessDefinitionByTextAndJsonQuery(
		final java.lang.String text, 
		final java.lang.String jsonQuery, 
		final java.lang.Integer start, 
		final java.lang.Integer pageSize)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findProcessInstance

	 * @param query 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstance(
		final com.soffid.zkdb.api.Query query)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findProcessInstanceByTextAndJsonQuery

	 * @param text 
	 * @param jsonQuery 
	 * @param start 
	 * @param pageSize 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstanceByTextAndJsonQuery(
		final java.lang.String text, 
		final java.lang.String jsonQuery, 
		final java.lang.Integer start, 
		final java.lang.Integer pageSize)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findTasksByTextAndJsonQuery

	 * @param text 
	 * @param jsonQuery 
	 * @param start 
	 * @param pageSize 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.TaskInstance> findTasksByTextAndJsonQuery(
		final java.lang.String text, 
		final java.lang.String jsonQuery, 
		final java.lang.Integer start, 
		final java.lang.Integer pageSize)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation countMyTasks

	 * @return 
	 */
	int countMyTasks()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation countNewTasks

	 * @return 
	 */
	int countNewTasks()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation getCoordinates

	 * @param task 
	 * @return 
	 */
	int[] getCoordinates(
		final com.soffid.iam.bpm.api.ProcessInstance task)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getCoordinates

	 * @param task 
	 * @return 
	 */
	int[] getCoordinates(
		final com.soffid.iam.bpm.api.TaskInstance task)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getResourceAsStream

	 * @param processdef 
	 * @param resource 
	 * @return 
	 */
	java.io.InputStream getResourceAsStream(
		final com.soffid.iam.bpm.api.ProcessDefinition processdef, 
		final java.lang.String resource)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getResourceAsStream

	 * @param process 
	 * @param resource 
	 * @return 
	 */
	java.io.InputStream getResourceAsStream(
		final com.soffid.iam.bpm.api.ProcessInstance process, 
		final java.lang.String resource)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUI

	 * @param process 
	 * @return 
	 */
	java.lang.String getUI(
		final com.soffid.iam.bpm.api.ProcessInstance process)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUI

	 * @param task 
	 * @return 
	 */
	java.lang.String getUI(
		final com.soffid.iam.bpm.api.TaskInstance task)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getDeployMessages

	 * @param token 
	 * @return 
	 */
	java.lang.String[] getDeployMessages(
		final com.soffid.iam.bpm.api.DeployToken token)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation upgradeProcess

	 * @param instanceVO 
	 * @return 
	 */
	java.lang.String[] upgradeProcess(
		final com.soffid.iam.bpm.api.ProcessInstance instanceVO)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation findChildProcesses

	 * @param processId 
	 * @return 
	 */
	java.util.Collection<java.lang.Long> findChildProcesses(
		final java.lang.Long processId)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findParentProceeses

	 * @param processId 
	 * @return 
	 */
	java.util.Collection<java.lang.Long> findParentProceeses(
		final java.lang.Long processId)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUsers

	 * @param userName 
	 * @param givenName 
	 * @param surName 
	 * @param group 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.bpm.api.BPMUser> findUsers(
		final java.lang.String userName, 
		final java.lang.String givenName, 
		final java.lang.String surName, 
		final java.lang.String group)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAllProcessDefinitions

	 * @param onlyEnabled 
	 * @return 
	 */
	java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findAllProcessDefinitions(
		final boolean onlyEnabled)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findGroupTasks

	 * @return 
	 */
	java.util.List<com.soffid.iam.bpm.api.TaskInstance> findGroupTasks()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation findInitiatorProcessDefinitions

	 * @return 
	 */
	java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findInitiatorProcessDefinitions()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation findMyProcesses

	 * @return 
	 */
	java.util.List<com.soffid.iam.bpm.api.ProcessInstance> findMyProcesses()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findMyTasks

	 * @return 
	 */
	java.util.List<com.soffid.iam.bpm.api.TaskInstance> findMyTasks()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation findMyTasksLightweight

	 * @return 
	 */
	java.util.List<com.soffid.iam.bpm.api.TaskInstance> findMyTasksLightweight()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation findObserverProcessDefinitions

	 * @return 
	 */
	java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findObserverProcessDefinitions()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation findProcessDefinitions

	 * @param name 
	 * @param onlyEnabled 
	 * @return 
	 */
	java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findProcessDefinitions(
		final java.lang.String name, 
		final boolean onlyEnabled)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findProcessDefinitions
	 * Searches for process definition of a predefined type

	 * @param name 
	 * @param processType 
	 * @return 
	 */
	java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findProcessDefinitions(
		final java.lang.String name, 
		final com.soffid.iam.bpm.api.PredefinedProcessType processType)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findProcessInstances

	 * @param def 
	 * @return 
	 */
	java.util.List<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstances(
		final com.soffid.iam.bpm.api.ProcessDefinition def)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation findProcessInstances

	 * @param definitions 
	 * @param processId 
	 * @param estado 
	 * @param actor 
	 * @param startDate 
	 * @param finalizada 
	 * @return 
	 */
	java.util.List<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstances(
		final java.util.List definitions, 
		final java.lang.String processId, 
		final java.lang.String estado, 
		final java.lang.String actor, 
		final java.util.Date startDate, 
		final boolean finalizada)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation findSupervisorProcessDefinitions

	 * @return 
	 */
	java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findSupervisorProcessDefinitions()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation findTaskDefinitions

	 * @param def 
	 * @return 
	 */
	java.util.List<com.soffid.iam.bpm.api.TaskDefinition> findTaskDefinitions(
		final com.soffid.iam.bpm.api.ProcessDefinition def)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation findTasks

	 * @param def 
	 * @param task 
	 * @param actor 
	 * @param processStartDate 
	 * @param taskCreationDate 
	 * @param finalizada 
	 * @return 
	 */
	java.util.List<com.soffid.iam.bpm.api.TaskInstance> findTasks(
		final java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, 
		final com.soffid.iam.bpm.api.TaskDefinition task, 
		final java.lang.String actor, 
		final java.util.Date processStartDate, 
		final java.util.Date taskCreationDate, 
		final boolean finalizada)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation findTasks

	 * @param def 
	 * @param process 
	 * @param task 
	 * @param actor 
	 * @param processStartDate 
	 * @param taskCreationDate 
	 * @param finalizada 
	 * @return 
	 */
	java.util.List<com.soffid.iam.bpm.api.TaskInstance> findTasks(
		final java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, 
		final java.lang.String process, 
		final com.soffid.iam.bpm.api.TaskDefinition task, 
		final java.lang.String actor, 
		final java.util.Date processStartDate, 
		final java.util.Date taskCreationDate, 
		final boolean finalizada)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation getActiveJobs

	 * @return 
	 */
	java.util.List<com.soffid.iam.bpm.api.Job> getActiveJobs()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation getActiveJobs

	 * @param process 
	 * @return 
	 */
	java.util.List<com.soffid.iam.bpm.api.Job> getActiveJobs(
		final com.soffid.iam.bpm.api.ProcessInstance process)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation getActiveTasks

	 * @param process 
	 * @return 
	 */
	java.util.List<com.soffid.iam.bpm.api.TaskInstance> getActiveTasks(
		final com.soffid.iam.bpm.api.ProcessInstance process)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation getAllJobs

	 * @return 
	 */
	java.util.List<com.soffid.iam.bpm.api.Job> getAllJobs()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation getPendingTasks

	 * @param process 
	 * @return 
	 */
	java.util.List<com.soffid.iam.bpm.api.TaskInstance> getPendingTasks(
		final com.soffid.iam.bpm.api.ProcessInstance process)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation searchProcessInstances

	 * @param query 
	 * @param processID 
	 * @param sinceStartDate 
	 * @param untilStartDate 
	 * @param sinceEndDate 
	 * @param untilEndDate 
	 * @param finished 
	 * @return 
	 */
	java.util.List<com.soffid.iam.bpm.api.ProcessInstance> searchProcessInstances(
		final java.lang.String query, 
		final java.lang.String processID, 
		final java.util.Date sinceStartDate, 
		final java.util.Date untilStartDate, 
		final java.util.Date sinceEndDate, 
		final java.util.Date untilEndDate, 
		final boolean finished)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation getUIClassesForTask

	 * @param def 
	 * @return 
	 */
	java.util.Map getUIClassesForTask(
		final com.soffid.iam.bpm.api.ProcessDefinition def)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.sql.SQLException, java.io.IOException;

	/**
	 * Operation getJBpmConfiguration

	 * @return 
	 */
	org.jbpm.JbpmConfiguration getJBpmConfiguration()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getContext

	 * @return 
	 */
	org.jbpm.JbpmContext getContext()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation addComment

	 * @param process 
	 * @param comment 
	 */
	void addComment(
		final com.soffid.iam.bpm.api.ProcessInstance process, 
		final java.lang.String comment)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation endDeployParDefinitionTransfer

	 * @param token 
	 */
	void endDeployParDefinitionTransfer(
		final com.soffid.iam.bpm.api.DeployToken token)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation linkProcesses

	 * @param parentProcess 
	 * @param childProcess 
	 */
	void linkProcesses(
		final java.lang.Long parentProcess, 
		final java.lang.Long childProcess)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation nextDeployParDefinitionPackage

	 * @param token 
	 * @param filePackage 
	 * @param length 
	 */
	void nextDeployParDefinitionPackage(
		final com.soffid.iam.bpm.api.DeployToken token, 
		final byte[] filePackage, 
		final int length)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation pauseJob

	 * @param jobvo 
	 */
	void pauseJob(
		final com.soffid.iam.bpm.api.Job jobvo)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation ping

	 */
	void ping()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation reindex

	 */
	void reindex()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.io.IOException;

	/**
	 * Operation resumeJob

	 * @param jobvo 
	 */
	void resumeJob(
		final com.soffid.iam.bpm.api.Job jobvo)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation retryJob

	 * @param jobvo 
	 */
	void retryJob(
		final com.soffid.iam.bpm.api.Job jobvo)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation signal

	 * @param instanceVO 
	 */
	void signal(
		final com.soffid.iam.bpm.api.ProcessInstance instanceVO)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation signal

	 * @param instanceVO 
	 * @param transitionName 
	 */
	void signal(
		final com.soffid.iam.bpm.api.ProcessInstance instanceVO, 
		final java.lang.String transitionName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation startProcess

	 * @param process 
	 */
	void startProcess(
		final com.soffid.iam.bpm.api.ProcessInstance process)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation update

	 * @param process 
	 */
	void update(
		final com.soffid.iam.bpm.api.ProcessInstance process)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation updateSwimlane

	 * @param task 
	 * @param swimlane 
	 * @param actorIds 
	 */
	void updateSwimlane(
		final com.soffid.iam.bpm.api.TaskInstance task, 
		final java.lang.String swimlane, 
		final java.lang.String[] actorIds)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException;

	/**
	 * Operation upgradeParFile

	 * @param stream 
	 */
	void upgradeParFile(
		final java.io.InputStream stream)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
