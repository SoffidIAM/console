package es.caib.bpm.business;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.WeakHashMap;

import org.jbpm.JbpmConfiguration;
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

import es.caib.bpm.classloader.UIClassLoader;
import es.caib.bpm.dal.ProcessDefinitionPropertyDal;
import es.caib.bpm.exception.BPMException;
import es.caib.bpm.mail.Mail;
import es.caib.bpm.toolkit.EJBContainer;
import es.caib.bpm.util.Timer;
import es.caib.bpm.vo.Comment;
import es.caib.bpm.vo.ConfigParameterVO;
import es.caib.bpm.vo.PredefinedProcessType;
import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.utils.Security;

public class VOFactory {
	public static Comment newComment(org.jbpm.graph.exe.Comment instance) {
		Comment c = new Comment();
		String actorId = instance.getActorId();
		Security.nestedLogin(Security.getCurrentAccount(), new String[] {Security.AUTO_USER_QUERY+Security.AUTO_ALL});
		try {
			actorId = actorId+" "+ServiceLocator.instance().getUsuariService().findUsuariByCodiUsuari(actorId).getFullName();
		} catch (Exception e) {
		} finally {
			Security.nestedLogoff();
		}
		c.setActor(actorId);
		c.setMessage(instance.getMessage());
		c.setTime(instance.getTime());
		return c;
	}

	public static es.caib.bpm.vo.TaskInstance newTaskInstance(
			org.jbpm.taskmgmt.exe.TaskInstance instance) throws InternalErrorException {
/**/	Timer t1=new Timer();
		
		es.caib.bpm.vo.TaskInstance vo = new es.caib.bpm.vo.TaskInstance();
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
			List transitionList = instance.getToken().getNode().getLeavingTransitions();
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

		
		ConfigParameterVO param = null;
        String timeThresold="1000"; //$NON-NLS-1$
        try {
			param = EJBContainer.getBPMConfigBean().findFirstByAppKey("BPM","es.caib.ibkey.timer.databaseTask.milis"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (Exception e) {
		}
        if(param!=null)
        	timeThresold=param.getValue();

/**/	t1.logTime("VOFactory.newTaskInstance",Integer.parseInt(timeThresold)); //$NON-NLS-1$
		
		return vo;
	}

	public static es.caib.bpm.vo.ProcessInstance newProcessInstance(
			org.jbpm.graph.exe.ProcessInstance instance) throws InternalErrorException {
		es.caib.bpm.vo.ProcessInstance process = new es.caib.bpm.vo.ProcessInstance();
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
		} finally {
			Thread.currentThread().setContextClassLoader(oldcl);
		}
		Vector<Comment> comments = new Vector<Comment>();
		populateTokenComments(instance.getRootToken(), comments);
		Collections.sort(comments, new Comparator<Comment>() {
			public int compare(Comment o1, Comment o2) {
				return o1.getTime().compareTo(o2.getTime());
			}
		});
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

	public static es.caib.bpm.vo.ProcessDefinition newProcessDefinition(
			ProcessDefinition instance, JbpmContext context) {
		es.caib.bpm.vo.ProcessDefinition vo = new es.caib.bpm.vo.ProcessDefinition();
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
			vo.setType(PredefinedProcessType.fromString(type));
		}
		String disabled = dal.getProcessDefinitionProperty(instance.getId(), "disabled"); //$NON-NLS-1$
		vo.setEnabled( ! "true".equals(disabled) ); //$NON-NLS-1$
		String userCentric = dal.getProcessDefinitionProperty(instance.getId(), "appliesTo"); //$NON-NLS-1$
		vo.setAppliesTo( userCentric ); //$NON-NLS-1$
		vo.setVersion(instance.getVersion());
		vo.setId(instance.getId());
		return vo;
	}

	public static es.caib.bpm.vo.TaskDefinition newTaskDefinition(Task task) {
		es.caib.bpm.vo.TaskDefinition vo = new es.caib.bpm.vo.TaskDefinition();
		vo.setId(task.getId());
		vo.setBlocking(task.isBlocking());
		vo.setDescription(task.getDescription());
		vo.setName(task.getName());
		vo.setSignalling(task.isSignalling());
		return vo;
	}
	
	public static es.caib.bpm.vo.Job newJob(Job j) {
		es.caib.bpm.vo.Job vo = new es.caib.bpm.vo.Job();
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
	
	public static es.caib.bpm.vo.Token newToken (Token t) {
		es.caib.bpm.vo.Token vo = new es.caib.bpm.vo.Token();
		
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
/**/	Timer t1=new Timer();
		
		es.caib.bpm.vo.LighweightTaskInstance vo = new es.caib.bpm.vo.LighweightTaskInstance();
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
			param = EJBContainer.getBPMConfigBean().findFirstByAppKey("BPM","es.caib.ibkey.timer.databaseTask.milis"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (Exception e) {}
        if(param!=null) timeThresold=param.getValue();
        
		/**/	t1.logTime("VOFactory.newLighweightTaskInstance",Integer.parseInt(timeThresold)); //$NON-NLS-1$
		return vo;
	}
	
	
	static WeakHashMap<Long, ClassLoader> classesMap = new WeakHashMap<Long,ClassLoader> ();
	static private ClassLoader getClassLoader(ProcessDefinition def) throws InternalErrorException
	{
		return JbpmConfiguration.getProcessClassLoader(def);
	}

}

