package com.soffid.iam.bpm.business;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.Vector;

import org.jbpm.JbpmContext;
import org.jbpm.bytes.ByteArray;
import org.jbpm.graph.def.Action;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.Token;
import org.jbpm.graph.node.TaskNode;
import org.jbpm.job.Job;
import org.jbpm.taskmgmt.def.Task;
import org.jbpm.taskmgmt.exe.PooledActor;
import org.jbpm.taskmgmt.exe.SwimlaneInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;

import es.caib.bpm.exception.BPMException;
import es.caib.bpm.util.Timer;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.bpm.api.Comment;
import com.soffid.iam.bpm.api.ConfigParameterVO;
import com.soffid.iam.bpm.mail.Mail;
import com.soffid.iam.bpm.model.dal.ProcessDefinitionPropertyDal;

public class VOFactory {
	public static Comment newComment(org.jbpm.graph.exe.Comment instance) {
		Comment c = new Comment();
		c.setActor(instance.getActorId());
		c.setMessage(instance.getMessage());
		c.setTime(instance.getTime());
		return c;
	}

	public static com.soffid.iam.bpm.api.TaskInstance newTaskInstance(
			org.jbpm.taskmgmt.exe.TaskInstance instance) {
/**/	Timer t1=new Timer();
		
		com.soffid.iam.bpm.api.TaskInstance vo = new com.soffid.iam.bpm.api.TaskInstance();
		vo.setProcessName(instance.getProcessInstance().getProcessDefinition().getName());
		vo.setProcessId(instance.getProcessInstance().getId());
		vo.setActorId(instance.getActorId());
		vo.setBlocking(instance.isBlocking());
		vo.setCancelled(instance.isCancelled());
		vo.setCreate(instance.getCreate());
		String d = instance.getDescription();
		if (d == null)
			d = instance.getTask().getDescription();
		if (d == null)
			d = instance.getToken().getNode().getDescription();
		vo.setDescription(d);
		vo.setDueDate(instance.getDueDate());
		vo.setEnd(instance.getEnd());
		vo.setId(instance.getId());
		vo.setName(instance.getName());
		vo.setOpen(instance.isOpen());
		Set actors = new HashSet();
		if (instance.getPooledActors() != null) {
			for (Iterator it = instance.getPooledActors().iterator(); it
					.hasNext();) {
				PooledActor actor = (PooledActor) it.next();
				actors.add(actor.getActorId());
			}
		}
		vo.setPooledActors(actors);
		vo.setPriority(instance.getPriority());
		vo.setSignalling(instance.isSignalling());
		vo.setStart(instance.getStart());
		SwimlaneInstance swimlane = instance.getSwimlaneInstance();
		if (swimlane != null)
			vo.setSwimlane(swimlane.getName());
		if (instance.getAvailableTransitions() == null)
			vo.setTransitions(new String[0]);
		else {
			List transitionList = instance.getAvailableTransitions();
			String transitions[] = new String[transitionList.size()];
			int i = 0;
			for (Iterator it = transitionList.iterator(); it.hasNext();) {
				Transition t = (Transition) it.next();
				transitions[i++] = t.getName();
			}
			vo.setTransitions(transitions);
		}
		Map variables = new HashMap();
		for (Iterator it = instance.getVariables().keySet().iterator(); it
				.hasNext();) {
			String key = (String) it.next();
			Object obj = instance.getVariable(key);
			if (obj instanceof ByteArray) {
				try {
					obj = new ObjectInputStream(new ByteArrayInputStream(
							((ByteArray) obj).getBytes())).readObject();
				} catch (final Exception e) {
					throw new RuntimeException(e);
				}
			}
			variables.put(key, obj);
		}
		vo.setVariables(variables);

		ConfigParameterVO param = null;
        String timeThresold="1000"; //$NON-NLS-1$
        try {
			param = ServiceLocator.instance().getBpmConfigService().findFirstByAppKey("BPM","es.caib.ibkey.timer.databaseTask.milis"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (Exception e) {
		}
        if(param!=null)
        	timeThresold=param.getValue();

/**/	t1.logTime("VOFactory.newTaskInstance",Integer.parseInt(timeThresold)); //$NON-NLS-1$
		
		return vo;
	}

	public static com.soffid.iam.bpm.api.ProcessInstance newProcessInstance(
			org.jbpm.graph.exe.ProcessInstance instance) {
		com.soffid.iam.bpm.api.ProcessInstance process = new com.soffid.iam.bpm.api.ProcessInstance();
		process.setEnd(instance.getEnd());
		process.setId(instance.getId());
		process.setStart(instance.getStart());
		process.setVariables(instance.getContextInstance().getVariables());
		Vector comments = new Vector();
		if (instance.getRootToken() != null &&
				instance.getRootToken().getComments() != null)
		{
			for (Iterator it = instance.getRootToken().getComments().iterator(); it
					.hasNext();) {
				comments.add(newComment((org.jbpm.graph.exe.Comment) it.next()));
			}
		}
		process.setComments(comments);
		Token t = instance.getRootToken();
		Node n = t.getNode();
		if (n instanceof TaskNode)
		{
			StringBuffer tasks = new StringBuffer();
			for( Iterator it = instance.getTaskMgmtInstance().getUnfinishedTasks(t).iterator();
				it.hasNext();) {
				TaskInstance ti = (TaskInstance) it.next();
				tasks.append(ti.getName());
				tasks.append(" "); //$NON-NLS-1$
			}
			process.setCurrentTask(tasks.toString());
		}
		else if (n != null)
		{
			process.setCurrentTask(n.getName());
		}
		return process;
	}

	public static com.soffid.iam.bpm.api.ProcessDefinition newProcessDefinition(
			ProcessDefinition instance, JbpmContext context) {
		com.soffid.iam.bpm.api.ProcessDefinition vo = new com.soffid.iam.bpm.api.ProcessDefinition();
		vo.setName(instance.getName());
		ProcessDefinitionPropertyDal dal = new ProcessDefinitionPropertyDal();
		dal.setContext(context);
		vo.setTag(dal.getProcessDefinitionProperty(instance.getId(), "tag")); //$NON-NLS-1$
		String type = dal.getProcessDefinitionProperty(instance.getId(), "type"); //$NON-NLS-1$ 
		if (type == null || type.isEmpty())
		{
			vo.setType(null);
		}
		else
		{
			vo.setType(es.caib.bpm.vo.PredefinedProcessType.fromString(type));
		}
		String disabled = dal.getProcessDefinitionProperty(instance.getId(), "disabled"); //$NON-NLS-1$
		vo.setEnabled( ! "true".equals(disabled) ); //$NON-NLS-1$
		String userCentric = dal.getProcessDefinitionProperty(instance.getId(), "appliesTo"); //$NON-NLS-1$
		vo.setAppliesTo( userCentric ); //$NON-NLS-1$
		vo.setVersion(instance.getVersion());
		vo.setId(instance.getId());
		return vo;
	}

	public static com.soffid.iam.bpm.api.TaskDefinition newTaskDefinition(Task task) {
		com.soffid.iam.bpm.api.TaskDefinition vo = new com.soffid.iam.bpm.api.TaskDefinition();
		vo.setId(task.getId());
		vo.setBlocking(task.isBlocking());
		vo.setDescription(task.getDescription());
		vo.setName(task.getName());
		vo.setSignalling(task.isSignalling());
		return vo;
	}
	
	public static com.soffid.iam.bpm.api.Job newJob(Job j) {
		com.soffid.iam.bpm.api.Job vo = new com.soffid.iam.bpm.api.Job();
		vo.setDueDate(j.getDueDate());
		vo.setError(j.getRetries() <= 0);
		vo.setErrorMessage(j.getException());
		vo.setFailures(j.getVersion());
		vo.setId(j.getId());
		vo.setLocked(j.getLockOwner() != null);
		vo.setName(j.getTaskInstance() != null ? 
			j.getTaskInstance().getName():
				j.getToken()!= null && j.getToken().getNode() != null ?
						j.getToken().getNode().getName():
						"<unknown>"); //$NON-NLS-1$
		vo.setPaused(j.isSuspended());
		vo.setProcessId(j.getProcessInstance().getId());
		if (j instanceof org.jbpm.job.Timer)
		{
			org.jbpm.job.Timer t = (org.jbpm.job.Timer) j;
			Action action = ((org.jbpm.job.Timer) j).getAction();
			if (action != null && action.getActionDelegation() != null && 
					action.getActionDelegation().getClassName() != null &&
					Mail.class.getName().equals(action.getActionDelegation().getClassName())) 
				vo.setName(t.getName()+" (Mail notification)");
			else
				vo.setName(t.getName()+" (Timer)");
		}
		if (j instanceof org.jbpm.job.CleanUpProcessJob)
		{
			org.jbpm.job.CleanUpProcessJob t = (org.jbpm.job.CleanUpProcessJob) j;
			vo.setName("Process cleanup"); //$NON-NLS-1$
		}
		if (j instanceof org.jbpm.job.ExecuteActionJob)
		{
			org.jbpm.job.ExecuteActionJob t = (org.jbpm.job.ExecuteActionJob) j;
			if (t.getAction() != null)
				vo.setName(t.getAction().getName());
		}
		return vo;
	}
	
	public static com.soffid.iam.bpm.api.Token newToken (Token t) {
		com.soffid.iam.bpm.api.Token vo = new com.soffid.iam.bpm.api.Token();
		
		vo.setProcessId(t.getProcessInstance().getId());
		if (t.getNode() != null)
			vo.setNodeName(t.getNode().getName());
		vo.setTokenName(t.getFullName());
		
		vo.setFinished(t.hasEnded());
		vo.setLocked(t.isLocked());
		vo.setSuspended(t.isSuspended());
		
		return vo;
	}

	public static Object newLightweightTaskInstance(TaskInstance instance) throws BPMException {
/**/	Timer t1=new Timer();
		
		com.soffid.iam.bpm.api.LighweightTaskInstance vo = new com.soffid.iam.bpm.api.LighweightTaskInstance();
		vo.setProcessName(instance.getProcessInstance().getProcessDefinition().getName());
		vo.setProcessId(instance.getProcessInstance().getId());
		vo.setActorId(instance.getActorId());
		vo.setBlocking(instance.isBlocking());
		vo.setCancelled(instance.isCancelled());
		vo.setCreate(instance.getCreate());
		String d = instance.getDescription();
		if (d == null)
			d = instance.getTask().getDescription();
		if (d == null)
			d = instance.getToken().getNode().getDescription();
		vo.setDescription(d);
		vo.setDueDate(instance.getDueDate());
		vo.setEnd(instance.getEnd());
		vo.setId(instance.getId());
		vo.setName(instance.getName());
		vo.setOpen(instance.isOpen());
		Set actors = new HashSet();
		if (instance.getPooledActors() != null) {
			for (Iterator it = instance.getPooledActors().iterator(); it
					.hasNext();) {
				PooledActor actor = (PooledActor) it.next();
				actors.add(actor.getActorId());
			}
		}
		vo.setPooledActors(actors);
		vo.setPriority(instance.getPriority());
		vo.setSignalling(instance.isSignalling());
		vo.setStart(instance.getStart());
		SwimlaneInstance swimlane = instance.getSwimlaneInstance();
		if (swimlane != null)
			vo.setSwimlane(swimlane.getName());
		if (instance.getAvailableTransitions() == null)
			vo.setTransitions(new String[0]);
		else {
			List transitionList = instance.getAvailableTransitions();
			String transitions[] = new String[transitionList.size()];
			int i = 0;
			for (Iterator it = transitionList.iterator(); it.hasNext();) {
				Transition t = (Transition) it.next();
				transitions[i++] = t.getName();
			}
			vo.setTransitions(transitions);
		}
		/** A diferencia del TaskInstance, no le copiamos las variables para incrementat la velocidad a la que se generan **/		

		//		Map variables = new HashMap();
//		for (Iterator it = instance.getVariables().keySet().iterator(); it
//				.hasNext();) {
//			String key = (String) it.next();
//			Object obj = instance.getVariable(key);
//			if (obj instanceof ByteArray) {
//				try {
//					obj = new ObjectInputStream(new ByteArrayInputStream(
//							((ByteArray) obj).getBytes())).readObject();
//				} catch (final Exception e) {
//					throw new RuntimeException(e);
//				}
//			}
//			variables.put(key, obj);
//		}
//		vo.setVariables(variables);
/** **/
		
		ConfigParameterVO param = null;
        String timeThresold="1000"; //$NON-NLS-1$
        try {
			param = ServiceLocator.instance().getBpmConfigService().findFirstByAppKey("BPM","es.caib.ibkey.timer.databaseTask.milis"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (Exception e) {}
        if(param!=null) timeThresold=param.getValue();
        
		/**/	t1.logTime("VOFactory.newLighweightTaskInstance",Integer.parseInt(timeThresold)); //$NON-NLS-1$
		return vo;
	}

}

