package es.caib.bpm.business;

import com.soffid.iam.bpm.BpmEngineImpl;
import es.caib.bpm.dal.ProcessDefinitionRolesDal;
import es.caib.bpm.entity.ProcessDefinitionUserRole;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.ejb.SessionContext;
import org.jbpm.JbpmContext;
import org.jbpm.JbpmException;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.jpdl.el.impl.JbpmExpressionEvaluator;
import org.jbpm.taskmgmt.def.Swimlane;
import org.jbpm.taskmgmt.exe.PooledActor;
import org.jbpm.taskmgmt.exe.SwimlaneInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;

public class ProcessDefinitionRolesBusiness {

	public boolean isUserAuthorized(String appRole, String [] userRoles,
			ProcessDefinition definition) {
		
		Swimlane swim = null;
		
		if (definition != null && definition.getTaskMgmtDefinition() != null)
			swim = definition.getTaskMgmtDefinition().getSwimlane(appRole);
		
		if (swim == null)
			return checkOldAuthorization(appRole, userRoles, definition);
		else {
			String members = swim.getActorIdExpression();
			if (members == null)
				members = swim.getPooledActorsExpression();
			if (members == null)
				return checkOldAuthorization(appRole, userRoles, definition);
			else
			{
				String parsedMembers[] = getDefinitionActors(members);
				for (int i = 0; i < parsedMembers.length; i++) {
					for (int j = 0; j < userRoles.length; j++)
						if (userRoles[j].equals(parsedMembers[i]))
							return true;
				}
				return false;
			}
		}
	}

	private String[] getDefinitionActors(String pooledActorsExpression) {
		ExecutionContext executionContext = ExecutionContext.currentExecutionContext();
		if (executionContext == null)
			executionContext = new ExecutionContext ( (Token) null);
		String[] pooledActors = null;
		Object result = JbpmExpressionEvaluator.evaluate(
				pooledActorsExpression, executionContext);
		if (result == null) {
			throw new JbpmException(String.format(Messages.getString("ProcessDefinitionRolesBusiness.PooledActorsNullError"), //$NON-NLS-1$
					pooledActorsExpression)); 
		}

		if (result instanceof String[]) {
			pooledActors = (String[]) result;

		} else if (result instanceof Collection) {
			Collection collection = (Collection) result;
			pooledActors = (String[]) collection.toArray(new String[collection
					.size()]);

		} else if (result instanceof String) {
			List pooledActorList = new ArrayList();
			StringTokenizer tokenizer = new StringTokenizer((String) result,
					","); //$NON-NLS-1$
			while (tokenizer.hasMoreTokens()) {
				pooledActorList.add(tokenizer.nextToken().trim());
			}
			pooledActors = (String[]) pooledActorList
					.toArray(new String[pooledActorList.size()]);
		} else {
			throw new JbpmException(String.format(Messages.getString("ProcessDefinitionRolesBusiness.PooledActorsException"), //$NON-NLS-1$
					pooledActorsExpression, result, result.getClass().getName())); //$NON-NLS-1$
		}
		return pooledActors;
	}

	public boolean isUserAuthorized(String appRole, String[] userRoles,
			ProcessInstance pi) {
		if (pi == null)
			return false;
		ExecutionContext ctx = new ExecutionContext(pi.getRootToken());
		ExecutionContext.pushCurrentContext(ctx);
		try {
			Swimlane swim = pi.getProcessDefinition().getTaskMgmtDefinition().getSwimlane(appRole);
			if (swim == null)
				return checkOldAuthorization(appRole, userRoles, pi.getProcessDefinition());
			else {
				SwimlaneInstance swimInstance = pi.getTaskMgmtInstance().
					getInitializedSwimlaneInstance(ctx, swim);
				if (swimInstance.getActorId() != null) {
					for (int j = 0; j < userRoles.length; j++)
						if (userRoles[j].equals(swimInstance.getActorId()))
							return true;
				}
				if (swimInstance.getPooledActors() != null) {
					Iterator it = swimInstance.getPooledActors().iterator();
					while (it.hasNext()) {
						PooledActor actor = (PooledActor) it.next();
						for (int j = 0; j < userRoles.length; j++)
							if (userRoles[j].equals(actor.getActorId()))
								return true;
					}
				}
				return false;
			}
		} finally {
			ExecutionContext.popCurrentContext(ctx);
		}
	}


	private boolean checkOldAuthorization(String appRole, String[] userRoles, 
			ProcessDefinition roleDefinition) {

		ProcessDefinitionRolesDal dal = new ProcessDefinitionRolesDal();
		dal.setContext(this.context);

		List resultado = dal
				.findProcessDefinitionRoles(roleDefinition, appRole);

		if (resultado.isEmpty())
			return true;
		
		for (Iterator it = resultado.iterator(); it.hasNext();) {
			ProcessDefinitionUserRole role = (ProcessDefinitionUserRole) it
					.next();
			if ("*".equals(role.getUserRole())) //$NON-NLS-1$
			{
				return true;
			}
			for (int j = 0; j < userRoles.length; j++)
				if (userRoles[j].equals(role.getUserRole()))
					return true;

		}

		return false;
	}

	public void setContext(JbpmContext context) {
		this.context = context;
	}

	public JbpmContext getContext() {
		return context;
	}

	private JbpmContext context = null;

	public boolean canAccess(String[] userGroups, TaskInstance ti) {
		if (ti.getActorId() != null) {
			for (String userGroup: userGroups) {
				if (userGroup.equals(ti.getActorId()))
					return true;
			}
		}
		
		if (ti.getProcessInstance() == null)
			return false;
		
		if (isUserAuthorized(BpmEngineImpl.SUPERVISOR_ROLE, userGroups, ti.getProcessInstance()))
			return true;
		if (ti.getSwimlaneInstance() != null) {
			SwimlaneInstance swim = ti.getSwimlaneInstance();
			String actorId = swim.getActorId();
			if (actorId != null) {
				for (String userGroup: userGroups) {
					if (userGroup.equals(actorId))
						return true;
				}
			}
			if (swim.getPooledActors() != null) {
				for (String group: userGroups) {
					for (Iterator it2 = swim.getPooledActors().iterator(); it2
							.hasNext();) {
						PooledActor actor = (PooledActor) it2.next();
						if (group.equals(actor.getActorId()))
							return true;
					}
				}
			}
		}
		if (ti.getPooledActors() != null) {
			for (String group: userGroups) {
				for (Iterator it2 = ti.getPooledActors().iterator(); it2
						.hasNext();) {
					PooledActor actor = (PooledActor) it2.next();
					if (group.equals(actor.getActorId()))
						return true;
				}
			}
		}
		return false;
	}
}
