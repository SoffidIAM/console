package com.soffid.iam.web.inbox;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zul.Listitem;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.bpm.api.TaskInstance;
import com.soffid.iam.bpm.service.ejb.BpmEngine;
import com.soffid.iam.web.bpm.BPMApplication;
import com.soffid.iam.web.bpm.TaskUI;

import es.caib.bpm.exception.BPMException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DateFormats;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.events.SerializableEventListener;
import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.Frame;

import javax.servlet.http.HttpServletRequest;

public class InboxHandler extends com.soffid.iam.web.component.FrameHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataTable listbox;
	private List<TaskInstance> currentTasks;


	public InboxHandler () throws InternalErrorException {
		super();
	}
	
	
	public void afterCompose () 
	{
		super.afterCompose();
		try {
			listbox = (DataTable) getFellow("listbox"); //$NON-NLS-1$

			refresh ();
			HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
			String taskId = req.getParameter("taskId");
			if (taskId != null)
			{
				BpmEngine engine = EJBLocator.getBpmEngine();
				TaskInstance task;
				try {
					task = engine.getTask(Long.decode(taskId));
					if (task != null)
					{
						Events.postEvent(-1000, new Event("onOpenTask", this, task)); 
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			throw new UiException(e);
		}
	}
	
	public void openTask(Event event) throws ClassNotFoundException, IOException, SQLException, NamingException, CreateException, InternalErrorException, BPMException, Exception {
		int pos = listbox.getSelectedIndex();
        TaskInstance task = currentTasks.get(pos);
        TaskUI taskUi = (TaskUI) getFellow("task").getFellow("frame");

        if (taskUi.getCurrentTask() != null)
        	taskUi.salvarTarea(false);
//        Application.call(BPMApplication.getTaskURL(task));

        showDetails();
        taskUi.setParentFrame(this);
        taskUi.openTaskInstance(task);
	}
	
	public void refresh() throws RemoteException,
			InterruptedException, CreateException, NamingException,
			BPMException, InternalErrorException {
		BpmEngine engine = EJBLocator.getBpmEngine();
		currentTasks = null;
		TaskInstance task = null;

		try {
			currentTasks = getTasks(engine);

			if (currentTasks != null) {
				Collections.sort(currentTasks, new Comparator<TaskInstance>() {
					public int compare(TaskInstance t0, TaskInstance t1) {
						// 1 = hightest 5 = lowest
						if (t0.getPriority() < t1.getPriority())
							return -1;
						else if (t0.getPriority() > t1.getPriority())
							return 1;
						else if (t0.getCreate().getTime() < t1.getCreate()
								.getTime())
							return +1;
						else if (t0.getCreate().getTime() > t1.getCreate()
								.getTime())
							return -1;
						else
							return 0;
					}
				});


				StringBuffer sb = new StringBuffer();
				sb.append("[");

				for (Iterator itTask = currentTasks.iterator(); itTask.hasNext();) {
					task = (TaskInstance) itTask.next();
					if (sb.length() > 1) sb.append(",");
					JSONObject o = new JSONObject();
					o.put("task", task.getName());
					if (task.getStart() != null) {
						o.put("date_datetime", DateFormats.getDateTimeFormat().format(task.getStart()));
						o.put("date", task.getStart().getTime());
					}
					if (task.getDueDate() != null) {
						o.put("duedate_datetime", DateFormats.getDateTimeFormat().format(task.getDueDate()));
						o.put("duedate", task.getDueDate().getTime());
					}
					if (task.getActorId() != null)
						o.put("actor", task.getActorId());
					else {
						String actors = "";
						for (String actor: task.getPooledActors() ) {
							if (! actors.isEmpty()) actors += ", ";
							actors += actor;
						}
						o.put("actor", actors.toString());
					}
					o.put("id", task.getId());
					sb.append(o.toString());
				}
				sb.append("]");
				listbox.setData(sb.toString());
			}
		} finally {
		}
	}


	protected List getTasks(BpmEngine engine) throws InternalErrorException,
			BPMException {
		return engine.findMyTasks();
	}


	@Override
	public void hideDetails() throws CommitException {
		int pos = listbox.getSelectedIndex();
        TaskInstance task = currentTasks.get(pos);
        try {
			task = EJBLocator.getBpmEngine().getTask(task.getId());
		} catch (Exception e) {
			throw new RuntimeException (e);
		}
        if (task.getEnd() != null)
        {
        	currentTasks.remove(pos);
        	listbox.delete();
        }
        else
        {
        	try {
				refresh();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
        }
        super.hideDetails();
	}

	
}
