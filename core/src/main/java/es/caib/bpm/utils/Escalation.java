/**
 * 
 */
package es.caib.bpm.utils;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.exe.PooledActor;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.jbpm.taskmgmt.exe.TaskMgmtInstance;


/**
 * @author bubu
 *
 */
public class Escalation
{
	public static void escale (ExecutionContext ctx, String entities[])
	{
		if (ctx.getTaskInstance() != null)
			escalate (ctx.getTaskInstance(), entities);
		else
		{
			TaskMgmtInstance tmi = ctx.getTaskMgmtInstance();
			for (TaskInstance ti: tmi.getTaskInstances())
			{
				escalate (ti, entities);
			}
		}
	}

	public static void escale (ExecutionContext ctx, String entity)
	{
		escale (ctx, new String[]{entity});
	}
	
	/**
	 * @param taskInstance
	 * @param entities
	 */
	private static void escalate (TaskInstance taskInstance, String[] entities)
	{
		if (taskInstance.getStart() == null) 
		{
			for (String entity: entities)
				taskInstance.getPooledActors().add(new PooledActor(entity));
		}	
	}
}
