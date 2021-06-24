package com.soffid.iam.bpm.business;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.Vector;
import java.util.WeakHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.bytes.ByteArray;
import org.jbpm.graph.def.Action;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.graph.node.TaskNode;
import org.jbpm.job.Job;
import org.jbpm.jpdl.el.VariableResolver;
import org.jbpm.jpdl.el.impl.JbpmExpressionEvaluator;
import org.jbpm.taskmgmt.def.Task;
import org.jbpm.taskmgmt.exe.PooledActor;
import org.jbpm.taskmgmt.exe.SwimlaneInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.bpm.api.Comment;
import com.soffid.iam.bpm.api.ConfigParameterVO;
import com.soffid.iam.bpm.mail.Mail;
import com.soffid.iam.bpm.model.dal.ProcessDefinitionPropertyDal;
import com.soffid.iam.model.ProcessHierarchyEntity;
import com.soffid.iam.model.ProcessHierarchyEntityDao;

import es.caib.bpm.classloader.UIClassLoader;
import es.caib.bpm.exception.BPMException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.utils.Security;

public class VOFactory {
	
	static Log log = LogFactory.getLog(VOFactory.class);
	
	public static Comment newComment(org.jbpm.graph.exe.Comment instance) {
		Comment c = new Comment();
		String actorId = instance.getActorId();
		Security.nestedLogin(Security.getCurrentAccount(), new String[] {Security.AUTO_USER_QUERY+Security.AUTO_ALL});
		try {
			if (actorId == null)
				actorId = "-";
			else
				actorId = actorId+" "+ServiceLocator.instance().getUserService().findUserByUserName(actorId).getFullName();
		} catch (Exception e) {
			log.info("Error getting user "+actorId, e);
		} finally {
			Security.nestedLogoff();
		}
		c.setActor(actorId);
		c.setMessage(instance.getMessage());
		c.setTime(instance.getTime());
		return c;
	}

	public static com.soffid.iam.bpm.api.TaskInstance newTaskInstance(
			org.jbpm.taskmgmt.exe.TaskInstance instance) throws InternalErrorException {
		com.soffid.iam.bpm.api.TaskInstance vo = new com.soffid.iam.bpm.api.TaskInstance();
		vo.setProcessName(instance.getProcessInstance().getProcessDefinition().getName());
		vo.setProcessId(instance.getProcessInstance().getId());
		vo.setActorId(instance.getActorId());
		vo.setBlocking(instance.isBlocking());
		vo.setCancelled(instance.isCancelled());
		vo.setCreate(instance.getCreate());
		String d = instance.getDescription();
		if (d == null)
			d = instance.getDescription();
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
		vo.setProcessClassLoader(getClassLoader(instance.getProcessInstance().getProcessDefinition()));

		SwimlaneInstance swimlane = instance.getSwimlaneInstance();
		if (swimlane != null)
			vo.setSwimlane(swimlane.getName());
		if (instance.getAvailableTransitions() == null)
			vo.setTransitions(new String[0]);
		else {
			List transitionList = instance.getTask().getTaskNode().getLeavingTransitions();
			String transitions[] = new String[transitionList.size()];
			int i = 0;
			for (Iterator it = transitionList.iterator(); it.hasNext();) {
				Transition t = (Transition) it.next();
				transitions[i++] = t.getName();
			}
			vo.setTransitions(transitions);
		}
		
		ClassLoader oldcl = Thread.currentThread().getContextClassLoader();
		Thread.currentThread().setContextClassLoader(vo.getProcessClassLoader());
		try {
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
		} finally {
			Thread.currentThread().setContextClassLoader(oldcl);
		}

		
		com.soffid.iam.bpm.api.ConfigParameterVO param = null;
        String timeThresold="1000"; //$NON-NLS-1$
        try {
			param = ServiceLocator.instance().getBpmConfigService().findFirstByAppKey("BPM","es.caib.ibkey.timer.databaseTask.milis"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (Exception e) {
		}
        if(param!=null)
        	timeThresold=param.getValue();

        vo.setProcessDefinition(instance.getProcessInstance().getProcessDefinition().getId());
        vo.setDummyTask(false);

        return vo;
	}

	public static com.soffid.iam.bpm.api.ProcessInstance newProcessInstance(
			JbpmContext context,
			ProcessHierarchyEntityDao hDao,
			org.jbpm.graph.exe.ProcessInstance instance) throws InternalErrorException {
		try {
			com.soffid.iam.bpm.api.ProcessInstance process = new com.soffid.iam.bpm.api.ProcessInstance();
			process.setEnd(instance.getEnd());
			process.setId(instance.getId());
			process.setStart(instance.getStart());
			process.setProcessClassLoader(getClassLoader(instance.getProcessDefinition()));
	
			ClassLoader oldcl = Thread.currentThread().getContextClassLoader();
			Thread.currentThread().setContextClassLoader(process.getProcessClassLoader());
			try {
				process.setVariables(instance.getContextInstance().getVariables());
				if (process.getVariables() == null)
					process.setVariables(new HashMap<String, Object>());
			} catch (Throwable th) {
				log.warn("Error deserializing process",th);
				process.setVariables(new HashMap<String, Object>());
			} finally {
				Thread.currentThread().setContextClassLoader(oldcl);
			}
			Vector<Comment> comments = new Vector<Comment>();
			populateTokenComments(context, hDao, instance, comments);
			Collections.sort(comments, new Comparator<Comment>() {
				public int compare(Comment o1, Comment o2) {
					return o1.getTime().compareTo(o2.getTime());
				}
			});
			process.setComments(comments);
			Token t = instance.getRootToken();
			if (t != null) {
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
			}
			
			if (process.getVariables().containsKey("$title"))
				process.setDescription((String) process.getVariables().get("$title"));
			else
			{
				String def = instance.getProcessDefinition().getDescription();
				if (def != null && !def.trim().isEmpty()) {
					VariableResolver variableResolver = JbpmExpressionEvaluator
							.getUsedVariableResolver();
					try {
						process.setDescription( (String) JbpmExpressionEvaluator.evaluate(def,
								new ExecutionContext( instance.getRootToken()),
								variableResolver,
								JbpmExpressionEvaluator.getUsedFunctionMapper()) );
					} catch (Throwable th) {
						process.setDescription(instance.getProcessDefinition().getName());
					}
				}
				else
					process.setDescription(instance.getProcessDefinition().getName());
			}
	
	        process.setProcessDefinition(instance.getProcessDefinition().getId());
	        process.setDummyProcess(false);

	        return process;
		} catch (Throwable th) {
			log.info("Error generating ProcessInstance", th);
			throw new InternalErrorException("Error generating process instance", th);
		}
	}

	private static void populateTokenComments(JbpmContext context, ProcessHierarchyEntityDao hDao,
			ProcessInstance instance, Vector<Comment> comments) {
		for (ProcessHierarchyEntity parent: hDao.findByChildren(instance.getId()))
		{
			ProcessInstance parentProc = context.getProcessInstance(parent.getParentProcess().longValue());
			if (parent != null)
			{
				populateTokenComments(context, hDao, parentProc, comments);
			}
		}

		Token t = instance.getRootToken();
		while (t != null)
		{
			populateTokenComments(t, comments);
			ProcessInstance proc = t.getProcessInstance();
			t = proc == null ? null : proc.getSuperProcessToken();
		} 
	}

	private static void populateTokenComments(Token token, Vector<Comment> comments) {
		if (token != null && token.getComments() != null)
		{
			for (Iterator it = token.getComments().iterator(); it
					.hasNext();) {
				comments.add(newComment((org.jbpm.graph.exe.Comment) it.next()));
			}
		}
		if (token != null && token.getChildren() != null)
		{
			for (Token child: token.getChildren().values())
			{
				populateTokenComments(child, comments);
			}
		}
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
		vo.setAuthor(dal.getProcessDefinitionProperty(instance.getId(), "author")); //$NON-NLS-1$ 
		String deployed = dal.getProcessDefinitionProperty(instance.getId(), "deployed"); //$NON-NLS-1$
		if (deployed != null)
			vo.setDeployed(new Date(Long.parseLong(deployed)));
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

	public static Object newLightweightTaskInstance(TaskInstance instance) throws BPMException, InternalErrorException {
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
		
		vo.setProcessClassLoader(getClassLoader(instance.getProcessInstance().getProcessDefinition()));

		ConfigParameterVO param = null;
        String timeThresold="1000"; //$NON-NLS-1$
        try {
			param = ServiceLocator.instance().getBpmConfigService().findFirstByAppKey("BPM","es.caib.ibkey.timer.databaseTask.milis"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (Exception e) {}
        if(param!=null) timeThresold=param.getValue();
        
		return vo;
	}
	
	
	static WeakHashMap<Long, ClassLoader> classesMap = new WeakHashMap<Long,ClassLoader> ();
	static public UIClassLoader getClassLoader(ProcessDefinition def) throws InternalErrorException
	{
		return (UIClassLoader) JbpmConfiguration.getProcessClassLoader(def);
	}

}

