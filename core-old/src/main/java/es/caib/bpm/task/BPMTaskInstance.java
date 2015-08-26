package es.caib.bpm.task;

import java.util.Iterator;
import java.util.Set;

import org.jbpm.taskmgmt.def.Swimlane;
import org.jbpm.taskmgmt.def.TaskMgmtDefinition;
import org.jbpm.taskmgmt.exe.PooledActor;
import org.jbpm.taskmgmt.exe.SwimlaneInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;


public class BPMTaskInstance extends TaskInstance {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BPMTaskInstance() {
		super();
	}

	public BPMTaskInstance(String taskName, String actorId) {
		super(taskName, actorId);
	}

	public BPMTaskInstance(String taskName) {
		super(taskName);
	}

	public void setActorId(String actorId, boolean overwriteSwimlane) {
		super.setActorId(actorId, overwriteSwimlane);
		updateObserverSwimlane ();
	}

	private void updateObserverSwimlane() {
		SwimlaneInstance sl = getTaskMgmtInstance().getSwimlaneInstance("observer"); //$NON-NLS-1$
		if (sl == null)
		{
			TaskMgmtDefinition def = getTaskMgmtInstance().getTaskMgmtDefinition();
			Swimlane sld = def.getSwimlane("observer"); //$NON-NLS-1$
			if (sld != null)
			{
				sl = getTaskMgmtInstance().createSwimlaneInstance(sld);
			}
		}
		if (sl != null)
		{
			if (getActorId () != null)
				addMemberToSwimlane (sl, getActorId());
			if (getPooledActors() != null)
			{
				for (Iterator it =getPooledActors().iterator(); it.hasNext(); )
				{
					PooledActor actor = (PooledActor) it.next();
					addMemberToSwimlane (sl, actor.getActorId());
				}
			}
			
		}
	}

	private void addMemberToSwimlane(SwimlaneInstance sl, String actorId) {
		if (sl.getPooledActors() == null)
			sl.setPooledActors(new String[] {actorId});
		else
		{
			for (Iterator it = sl.getPooledActors().iterator(); it.hasNext(); )
			{
				PooledActor actor = (PooledActor) it.next();
				if (actorId.equals(actor.getActorId()))
					return;
			}
			sl.getPooledActors().add(new PooledActor (actorId));
		}
	}

	public void setPooledActors(Set pooledActors) {
		super.setPooledActors(pooledActors);
		updateObserverSwimlane ();

	}


	public void setPooledActors(String[] actorIds) {
		super.setPooledActors(actorIds);
		updateObserverSwimlane ();

	}
	
	


}
