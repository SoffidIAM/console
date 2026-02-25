//
// (C) 2013 Soffid
//
//

package com.soffid.iam.bpm.service.ejb;
/**
 * @see <code>com.soffid.iam.bpm.service.BpmEngine</code>,
 * @see <code>com.soffid.iam.bpm.service.BpmEngine</code>,
 */
@jakarta.ejb.Stateless(name="BpmEngine", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.bpm.service.BpmEngine")
@jakarta.ejb.Local(com.soffid.iam.bpm.service.ejb.BpmEngine.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class BpmEngineBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.bpm.service.ejb.BpmEngine
{
	private com.soffid.iam.bpm.service.BpmEngine bpmEngine;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#void downloadParFile(com.soffid.iam.bpm.api.ProcessDefinition def, java.io.OutputStream stream)
	 */
	@jakarta.annotation.security.PermitAll
	public void downloadParFile(
		final com.soffid.iam.bpm.api.ProcessDefinition def, 
		final java.io.OutputStream stream)
		throws com.soffid.iam.exception.InternalErrorException, java.lang.Exception
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.bpmEngine.downloadParFile(def, stream); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof java.lang.Exception)
				throw (java.lang.Exception) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#boolean canAdmin(com.soffid.iam.bpm.api.ProcessInstance instanceVO)
	 */
	@jakarta.annotation.security.PermitAll
	public boolean canAdmin(
		final com.soffid.iam.bpm.api.ProcessInstance instanceVO)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.canAdmin(instanceVO); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#boolean isUserInRole(java.lang.String role)
	 */
	@jakarta.annotation.security.PermitAll
	public boolean isUserInRole(
		final java.lang.String role)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.isUserInRole(role); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#byte[] getProcessDefinitionIcon(java.lang.Long definitionId)
	 */
	@jakarta.annotation.security.PermitAll
	public byte[] getProcessDefinitionIcon(
		final java.lang.Long definitionId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getProcessDefinitionIcon(definitionId); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#byte[] getProcessDefinitionImage(com.soffid.iam.bpm.api.ProcessDefinition def)
	 */
	@jakarta.annotation.security.PermitAll
	public byte[] getProcessDefinitionImage(
		final com.soffid.iam.bpm.api.ProcessDefinition def)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getProcessDefinitionImage(def); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.DeployToken openDeployParDefinitionTransfer()
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.DeployToken openDeployParDefinitionTransfer()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.openDeployParDefinitionTransfer(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessDefinition disableProcessDefinition(com.soffid.iam.bpm.api.ProcessDefinition defVO)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.ProcessDefinition disableProcessDefinition(
		final com.soffid.iam.bpm.api.ProcessDefinition defVO)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.disableProcessDefinition(defVO); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessDefinition enableProcessDefinition(com.soffid.iam.bpm.api.ProcessDefinition defVO)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.ProcessDefinition enableProcessDefinition(
		final com.soffid.iam.bpm.api.ProcessDefinition defVO)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.enableProcessDefinition(defVO); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessDefinition getDefinition(com.soffid.iam.bpm.api.ProcessInstance process)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.ProcessDefinition getDefinition(
		final com.soffid.iam.bpm.api.ProcessInstance process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getDefinition(process); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessDefinition getProcessDefinition(com.soffid.iam.bpm.api.ProcessInstance process)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.ProcessDefinition getProcessDefinition(
		final com.soffid.iam.bpm.api.ProcessInstance process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getProcessDefinition(process); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessInstance cancel(com.soffid.iam.bpm.api.ProcessInstance process)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.ProcessInstance cancel(
		final com.soffid.iam.bpm.api.ProcessInstance process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.cancel(process); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessInstance getProcess(long id)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.ProcessInstance getProcess(
		final long id)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getProcess(id); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessInstance getProcessInstance(com.soffid.iam.bpm.api.TaskInstance task)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.ProcessInstance getProcessInstance(
		final com.soffid.iam.bpm.api.TaskInstance task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getProcessInstance(task); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessInstance getProcessLightweight(long id)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.ProcessInstance getProcessLightweight(
		final long id)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getProcessLightweight(id); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessInstance newProcess(com.soffid.iam.bpm.api.ProcessDefinition def)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.ProcessInstance newProcess(
		final com.soffid.iam.bpm.api.ProcessDefinition def)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.newProcess(def); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessInstance newProcess(com.soffid.iam.bpm.api.ProcessDefinition def, boolean start)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.ProcessInstance newProcess(
		final com.soffid.iam.bpm.api.ProcessDefinition def, 
		final boolean start)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.newProcess(def, start); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessLog[] getProcessLog(com.soffid.iam.bpm.api.ProcessInstance process)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.ProcessLog[] getProcessLog(
		final com.soffid.iam.bpm.api.ProcessInstance process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getProcessLog(process); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessLog[] getTaskLog(com.soffid.iam.bpm.api.TaskInstance process)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.ProcessLog[] getTaskLog(
		final com.soffid.iam.bpm.api.TaskInstance process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getTaskLog(process); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.TaskDefinition getDefinition(com.soffid.iam.bpm.api.TaskInstance task)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.TaskDefinition getDefinition(
		final com.soffid.iam.bpm.api.TaskInstance task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getDefinition(task); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.TaskInstance addComment(com.soffid.iam.bpm.api.TaskInstance task, java.lang.String comment)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.TaskInstance addComment(
		final com.soffid.iam.bpm.api.TaskInstance task, 
		final java.lang.String comment)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.addComment(task, comment); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.TaskInstance cancel(com.soffid.iam.bpm.api.TaskInstance task)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.TaskInstance cancel(
		final com.soffid.iam.bpm.api.TaskInstance task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.cancel(task); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.TaskInstance createDummyTask(long processDefinitionId)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.TaskInstance createDummyTask(
		final long processDefinitionId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.createDummyTask(processDefinitionId); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.TaskInstance delegateTaskToUser(com.soffid.iam.bpm.api.TaskInstance task, java.lang.String username)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.TaskInstance delegateTaskToUser(
		final com.soffid.iam.bpm.api.TaskInstance task, 
		final java.lang.String username)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.delegateTaskToUser(task, username); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.TaskInstance executeTask(com.soffid.iam.bpm.api.TaskInstance task, java.lang.String transitionName)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.TaskInstance executeTask(
		final com.soffid.iam.bpm.api.TaskInstance task, 
		final java.lang.String transitionName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.executeTask(task, transitionName); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.TaskInstance getTask(long id)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.TaskInstance getTask(
		final long id)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getTask(id); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.TaskInstance reserveTask(com.soffid.iam.bpm.api.TaskInstance task)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.TaskInstance reserveTask(
		final com.soffid.iam.bpm.api.TaskInstance task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.reserveTask(task); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.TaskInstance startTask(com.soffid.iam.bpm.api.TaskInstance task)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.TaskInstance startTask(
		final com.soffid.iam.bpm.api.TaskInstance task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.startTask(task); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.TaskInstance update(com.soffid.iam.bpm.api.TaskInstance task)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.TaskInstance update(
		final com.soffid.iam.bpm.api.TaskInstance task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.update(task); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.Token[] getTokens(long id)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.bpm.api.Token[] getTokens(
		final long id)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getTokens(id); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessDefinition> findProcessDefinitionByTextAndJsonQuery(java.lang.String text, java.lang.String jsonQuery, java.lang.Integer start, java.lang.Integer pageSize)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessDefinition> findProcessDefinitionByTextAndJsonQuery(
		final java.lang.String text, 
		final java.lang.String jsonQuery, 
		final java.lang.Integer start, 
		final java.lang.Integer pageSize)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.findProcessDefinitionByTextAndJsonQuery(text, jsonQuery, start, pageSize); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstance(com.soffid.zkdb.api.Query query)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstance(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.findProcessInstance(query); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstanceByTextAndJsonQuery(java.lang.String text, java.lang.String jsonQuery, java.lang.Integer start, java.lang.Integer pageSize)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstanceByTextAndJsonQuery(
		final java.lang.String text, 
		final java.lang.String jsonQuery, 
		final java.lang.Integer start, 
		final java.lang.Integer pageSize)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.findProcessInstanceByTextAndJsonQuery(text, jsonQuery, start, pageSize); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.TaskInstance> findTasksByTextAndJsonQuery(java.lang.String text, java.lang.String jsonQuery, java.lang.Integer start, java.lang.Integer pageSize)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.TaskInstance> findTasksByTextAndJsonQuery(
		final java.lang.String text, 
		final java.lang.String jsonQuery, 
		final java.lang.Integer start, 
		final java.lang.Integer pageSize)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.findTasksByTextAndJsonQuery(text, jsonQuery, start, pageSize); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#int countMyTasks()
	 */
	@jakarta.annotation.security.PermitAll
	public int countMyTasks()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.countMyTasks(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#int countNewTasks()
	 */
	@jakarta.annotation.security.PermitAll
	public int countNewTasks()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.countNewTasks(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#int[] getCoordinates(com.soffid.iam.bpm.api.ProcessInstance task)
	 */
	@jakarta.annotation.security.PermitAll
	public int[] getCoordinates(
		final com.soffid.iam.bpm.api.ProcessInstance task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getCoordinates(task); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#int[] getCoordinates(com.soffid.iam.bpm.api.TaskInstance task)
	 */
	@jakarta.annotation.security.PermitAll
	public int[] getCoordinates(
		final com.soffid.iam.bpm.api.TaskInstance task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getCoordinates(task); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.io.InputStream getResourceAsStream(com.soffid.iam.bpm.api.ProcessDefinition processdef, java.lang.String resource)
	 */
	@jakarta.annotation.security.PermitAll
	public java.io.InputStream getResourceAsStream(
		final com.soffid.iam.bpm.api.ProcessDefinition processdef, 
		final java.lang.String resource)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getResourceAsStream(processdef, resource); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.io.InputStream getResourceAsStream(com.soffid.iam.bpm.api.ProcessInstance process, java.lang.String resource)
	 */
	@jakarta.annotation.security.PermitAll
	public java.io.InputStream getResourceAsStream(
		final com.soffid.iam.bpm.api.ProcessInstance process, 
		final java.lang.String resource)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getResourceAsStream(process, resource); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.lang.String getUI(com.soffid.iam.bpm.api.ProcessInstance process)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String getUI(
		final com.soffid.iam.bpm.api.ProcessInstance process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getUI(process); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.lang.String getUI(com.soffid.iam.bpm.api.TaskInstance task)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String getUI(
		final com.soffid.iam.bpm.api.TaskInstance task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getUI(task); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.lang.String[] getDeployMessages(com.soffid.iam.bpm.api.DeployToken token)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String[] getDeployMessages(
		final com.soffid.iam.bpm.api.DeployToken token)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getDeployMessages(token); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.lang.String[] upgradeProcess(com.soffid.iam.bpm.api.ProcessInstance instanceVO)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String[] upgradeProcess(
		final com.soffid.iam.bpm.api.ProcessInstance instanceVO)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.upgradeProcess(instanceVO); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.Collection<java.lang.Long> findChildProcesses(java.lang.Long processId)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<java.lang.Long> findChildProcesses(
		final java.lang.Long processId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.findChildProcesses(processId); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.Collection<java.lang.Long> findParentProceeses(java.lang.Long processId)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<java.lang.Long> findParentProceeses(
		final java.lang.Long processId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.findParentProceeses(processId); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.Collection<com.soffid.iam.bpm.api.BPMUser> findUsers(java.lang.String userName, java.lang.String givenName, java.lang.String surName, java.lang.String group)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.bpm.api.BPMUser> findUsers(
		final java.lang.String userName, 
		final java.lang.String givenName, 
		final java.lang.String surName, 
		final java.lang.String group)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.findUsers(userName, givenName, surName, group); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findAllProcessDefinitions(boolean onlyEnabled)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findAllProcessDefinitions(
		final boolean onlyEnabled)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.findAllProcessDefinitions(onlyEnabled); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.TaskInstance> findGroupTasks()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.bpm.api.TaskInstance> findGroupTasks()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.findGroupTasks(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findInitiatorProcessDefinitions()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findInitiatorProcessDefinitions()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.findInitiatorProcessDefinitions(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.ProcessInstance> findMyProcesses()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.bpm.api.ProcessInstance> findMyProcesses()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.findMyProcesses(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.TaskInstance> findMyTasks()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.bpm.api.TaskInstance> findMyTasks()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.findMyTasks(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.TaskInstance> findMyTasksLightweight()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.bpm.api.TaskInstance> findMyTasksLightweight()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.findMyTasksLightweight(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findObserverProcessDefinitions()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findObserverProcessDefinitions()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.findObserverProcessDefinitions(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findProcessDefinitions(java.lang.String name, boolean onlyEnabled)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findProcessDefinitions(
		final java.lang.String name, 
		final boolean onlyEnabled)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.findProcessDefinitions(name, onlyEnabled); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findProcessDefinitions(java.lang.String name, com.soffid.iam.bpm.api.PredefinedProcessType processType)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findProcessDefinitions(
		final java.lang.String name, 
		final com.soffid.iam.bpm.api.PredefinedProcessType processType)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.findProcessDefinitions(name, processType); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstances(com.soffid.iam.bpm.api.ProcessDefinition def)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstances(
		final com.soffid.iam.bpm.api.ProcessDefinition def)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.findProcessInstances(def); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstances(java.util.List definitions, java.lang.String processId, java.lang.String estado, java.lang.String actor, java.util.Date startDate, boolean finalizada)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstances(
		final java.util.List definitions, 
		final java.lang.String processId, 
		final java.lang.String estado, 
		final java.lang.String actor, 
		final java.util.Date startDate, 
		final boolean finalizada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.findProcessInstances(definitions, processId, estado, actor, startDate, finalizada); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findSupervisorProcessDefinitions()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findSupervisorProcessDefinitions()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.findSupervisorProcessDefinitions(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.TaskDefinition> findTaskDefinitions(com.soffid.iam.bpm.api.ProcessDefinition def)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.bpm.api.TaskDefinition> findTaskDefinitions(
		final com.soffid.iam.bpm.api.ProcessDefinition def)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.findTaskDefinitions(def); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.TaskInstance> findTasks(java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, com.soffid.iam.bpm.api.TaskDefinition task, java.lang.String actor, java.util.Date processStartDate, java.util.Date taskCreationDate, boolean finalizada)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.bpm.api.TaskInstance> findTasks(
		final java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, 
		final com.soffid.iam.bpm.api.TaskDefinition task, 
		final java.lang.String actor, 
		final java.util.Date processStartDate, 
		final java.util.Date taskCreationDate, 
		final boolean finalizada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.findTasks(def, task, actor, processStartDate, taskCreationDate, finalizada); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.TaskInstance> findTasks(java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, java.lang.String process, com.soffid.iam.bpm.api.TaskDefinition task, java.lang.String actor, java.util.Date processStartDate, java.util.Date taskCreationDate, boolean finalizada)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.bpm.api.TaskInstance> findTasks(
		final java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, 
		final java.lang.String process, 
		final com.soffid.iam.bpm.api.TaskDefinition task, 
		final java.lang.String actor, 
		final java.util.Date processStartDate, 
		final java.util.Date taskCreationDate, 
		final boolean finalizada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.findTasks(def, process, task, actor, processStartDate, taskCreationDate, finalizada); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.Job> getActiveJobs()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.bpm.api.Job> getActiveJobs()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getActiveJobs(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.Job> getActiveJobs(com.soffid.iam.bpm.api.ProcessInstance process)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.bpm.api.Job> getActiveJobs(
		final com.soffid.iam.bpm.api.ProcessInstance process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getActiveJobs(process); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.TaskInstance> getActiveTasks(com.soffid.iam.bpm.api.ProcessInstance process)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.bpm.api.TaskInstance> getActiveTasks(
		final com.soffid.iam.bpm.api.ProcessInstance process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getActiveTasks(process); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.Job> getAllJobs()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.bpm.api.Job> getAllJobs()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getAllJobs(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.TaskInstance> getPendingTasks(com.soffid.iam.bpm.api.ProcessInstance process)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.bpm.api.TaskInstance> getPendingTasks(
		final com.soffid.iam.bpm.api.ProcessInstance process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getPendingTasks(process); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.ProcessInstance> searchProcessInstances(java.lang.String query, java.lang.String processID, java.util.Date sinceStartDate, java.util.Date untilStartDate, java.util.Date sinceEndDate, java.util.Date untilEndDate, boolean finished)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.bpm.api.ProcessInstance> searchProcessInstances(
		final java.lang.String query, 
		final java.lang.String processID, 
		final java.util.Date sinceStartDate, 
		final java.util.Date untilStartDate, 
		final java.util.Date sinceEndDate, 
		final java.util.Date untilEndDate, 
		final boolean finished)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.searchProcessInstances(query, processID, sinceStartDate, untilStartDate, sinceEndDate, untilEndDate, finished); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.Map getUIClassesForTask(com.soffid.iam.bpm.api.ProcessDefinition def)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Map getUIClassesForTask(
		final com.soffid.iam.bpm.api.ProcessDefinition def)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.sql.SQLException, java.io.IOException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getUIClassesForTask(def); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof java.sql.SQLException)
				throw (java.sql.SQLException) cause;
			if (cause instanceof java.io.IOException)
				throw (java.io.IOException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#org.jbpm.JbpmConfiguration getJBpmConfiguration()
	 */
	@jakarta.annotation.security.PermitAll
	public org.jbpm.JbpmConfiguration getJBpmConfiguration()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getJBpmConfiguration(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#org.jbpm.JbpmContext getContext()
	 */
	@jakarta.annotation.security.PermitAll
	public org.jbpm.JbpmContext getContext()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.bpmEngine.getContext(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#void addComment(com.soffid.iam.bpm.api.ProcessInstance process, java.lang.String comment)
	 */
	@jakarta.annotation.security.PermitAll
	public void addComment(
		final com.soffid.iam.bpm.api.ProcessInstance process, 
		final java.lang.String comment)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.bpmEngine.addComment(process, comment); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#void endDeployParDefinitionTransfer(com.soffid.iam.bpm.api.DeployToken token)
	 */
	@jakarta.annotation.security.PermitAll
	public void endDeployParDefinitionTransfer(
		final com.soffid.iam.bpm.api.DeployToken token)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.bpmEngine.endDeployParDefinitionTransfer(token); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#void linkProcesses(java.lang.Long parentProcess, java.lang.Long childProcess)
	 */
	@jakarta.annotation.security.PermitAll
	public void linkProcesses(
		final java.lang.Long parentProcess, 
		final java.lang.Long childProcess)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.bpmEngine.linkProcesses(parentProcess, childProcess); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#void nextDeployParDefinitionPackage(com.soffid.iam.bpm.api.DeployToken token, byte[] filePackage, int length)
	 */
	@jakarta.annotation.security.PermitAll
	public void nextDeployParDefinitionPackage(
		final com.soffid.iam.bpm.api.DeployToken token, 
		final byte[] filePackage, 
		final int length)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.bpmEngine.nextDeployParDefinitionPackage(token, filePackage, length); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#void pauseJob(com.soffid.iam.bpm.api.Job jobvo)
	 */
	@jakarta.annotation.security.PermitAll
	public void pauseJob(
		final com.soffid.iam.bpm.api.Job jobvo)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.bpmEngine.pauseJob(jobvo); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#void ping()
	 */
	@jakarta.annotation.security.PermitAll
	public void ping()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.bpmEngine.ping(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#void reindex()
	 */
	@jakarta.annotation.security.PermitAll
	public void reindex()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.io.IOException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.bpmEngine.reindex(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof java.io.IOException)
				throw (java.io.IOException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#void resumeJob(com.soffid.iam.bpm.api.Job jobvo)
	 */
	@jakarta.annotation.security.PermitAll
	public void resumeJob(
		final com.soffid.iam.bpm.api.Job jobvo)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.bpmEngine.resumeJob(jobvo); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#void retryJob(com.soffid.iam.bpm.api.Job jobvo)
	 */
	@jakarta.annotation.security.PermitAll
	public void retryJob(
		final com.soffid.iam.bpm.api.Job jobvo)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.bpmEngine.retryJob(jobvo); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#void signal(com.soffid.iam.bpm.api.ProcessInstance instanceVO)
	 */
	@jakarta.annotation.security.PermitAll
	public void signal(
		final com.soffid.iam.bpm.api.ProcessInstance instanceVO)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.bpmEngine.signal(instanceVO); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#void signal(com.soffid.iam.bpm.api.ProcessInstance instanceVO, java.lang.String transitionName)
	 */
	@jakarta.annotation.security.PermitAll
	public void signal(
		final com.soffid.iam.bpm.api.ProcessInstance instanceVO, 
		final java.lang.String transitionName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.bpmEngine.signal(instanceVO, transitionName); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#void startProcess(com.soffid.iam.bpm.api.ProcessInstance process)
	 */
	@jakarta.annotation.security.PermitAll
	public void startProcess(
		final com.soffid.iam.bpm.api.ProcessInstance process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.bpmEngine.startProcess(process); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#void update(com.soffid.iam.bpm.api.ProcessInstance process)
	 */
	@jakarta.annotation.security.PermitAll
	public void update(
		final com.soffid.iam.bpm.api.ProcessInstance process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.bpmEngine.update(process); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#void updateSwimlane(com.soffid.iam.bpm.api.TaskInstance task, java.lang.String swimlane, java.lang.String[] actorIds)
	 */
	@jakarta.annotation.security.PermitAll
	public void updateSwimlane(
		final com.soffid.iam.bpm.api.TaskInstance task, 
		final java.lang.String swimlane, 
		final java.lang.String[] actorIds)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.bpmEngine.updateSwimlane(task, swimlane, actorIds); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.bpm.exception.BPMException)
				throw (com.soffid.iam.bpm.exception.BPMException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#void upgradeParFile(java.io.InputStream stream)
	 */
	@jakarta.annotation.security.PermitAll
	public void upgradeParFile(
		final java.io.InputStream stream)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.bpmEngine.upgradeParFile(stream); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * Initizlizes been
	 *
	 * @see org.springframework.ejb.support.AbstractStatelessSessionBean#onEjbCreate()
	 */

	@Override

	@jakarta.annotation.PostConstruct
	public void createBean() 
	{
		super.createBean();
	}


	protected void onEjbCreate()
	{

		this.bpmEngine = (com.soffid.iam.bpm.service.BpmEngine)
		getBeanFactory().getBean("com.soffid.iam.bpm.service.BpmEngine");
	}

	
	/**
	 * Override default BeanFactoryLocator implementation to
	 * provide singleton loading of the application context Bean factory.
	 *
	 * @see jakarta.ejb.SessionBean#setSessionContext(jakarta.ejb.SessionContext)
	 */
	public void setSessionContext(jakarta.ejb.SessionContext sessionContext)
	{
		super.setSessionContext(sessionContext);
		super.setBeanFactoryLocator(
		org.springframework.context.access.ContextSingletonBeanFactoryLocator.getInstance("beanRefFactory.xml"));
		super.setBeanFactoryLocatorKey("beanRefFactory");
	}

	org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog (getClass());

	/**
	 * Finds the root cause of the parent exception
	 * by traveling up the exception tree.
	 */	private static Throwable getRootCause(Throwable throwable)
	{
		if (throwable != null)
		{
			// Reflectively get any exception causes.
			try
			{
				Throwable targetException = null;
				// java.lang.reflect.InvocationTargetException
				String exceptionProperty = "targetException";
				if (org.apache.commons.beanutils.PropertyUtils.isReadable(throwable, exceptionProperty))
				{
					targetException = (Throwable)org.apache.commons.beanutils.PropertyUtils.getProperty(throwable, exceptionProperty);
				}
				else
				{
					exceptionProperty = "causedByException";
					//jakarta.ejb.EJBException
					if (org.apache.commons.beanutils.PropertyUtils.isReadable(throwable, exceptionProperty))
					{
						targetException = (Throwable)org.apache.commons.beanutils.PropertyUtils.getProperty(throwable, exceptionProperty);
					}
				}
				if (targetException != null)
				{
					throwable = targetException;
				}
			}
			catch (Exception exception)
			{
				// just print the exception and continue
				exception.printStackTrace();
			}
			if (throwable.getCause() != null)
			{
				throwable = throwable.getCause();
				throwable = getRootCause(throwable);
			}
		}
		return throwable;
	}
}
