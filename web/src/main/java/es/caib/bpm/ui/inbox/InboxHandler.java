package es.caib.bpm.ui.inbox;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

import es.caib.bpm.exception.BPMException;
import es.caib.bpm.servei.ejb.BpmEngine;
import es.caib.bpm.toolkit.BPMApplication;
import es.caib.bpm.util.Timer;
import es.caib.bpm.vo.TaskInstance;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.events.SerializableEventListener;
import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.Frame;

import javax.servlet.http.HttpServletRequest;

public class InboxHandler extends com.soffid.iam.web.component.Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Listbox listbox;


	public InboxHandler () {
	}
	
	
	public void onCreate () throws Exception
	{
		try {
			listbox = (Listbox) getFellow("listadoTareas"); //$NON-NLS-1$
			listbox.addEventListener("onSelect", new SerializableEventListener () { //$NON-NLS-1$

				public void onEvent(Event event) throws Exception {
			        Listitem item = listbox.getSelectedItem();
			        TaskInstance task = (TaskInstance) item.getValue();

			        Application.call(BPMApplication.getTaskURL(task));
				}
				
			});
			this.addEventListener("onOpenTask", new SerializableEventListener () { //$NON-NLS-1$

				public void onEvent(Event event) throws Exception {
			        TaskInstance task = (TaskInstance) event.getData();

			        Application.call(BPMApplication.getTaskURL(task));
				}
				
			});
			addEventListener("onReturn", new SerializableEventListener () { //$NON-NLS-1$
				public void onEvent(Event event) throws Exception {
					refresh ();
				}
				
			});
			refresh ();
			HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
			String taskId = req.getParameter("taskId");
			if (taskId != null)
			{
				BpmEngine engine = BPMApplication.getEngine();
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
	
	public void refresh() throws RemoteException,
			InterruptedException, CreateException, NamingException,
			BPMException, InternalErrorException {
		BpmEngine engine = BPMApplication.getEngine();
		List tareas = null;
		Listitem item = null;
		TaskInstance task = null;

		try {
			tareas = getTasks(engine);

			if (listbox.getItems() != null) {
				listbox.getItems().clear();
				listbox.setSelectedItem(null);
			}

			if (tareas != null) {
				Collections.sort(tareas, new Comparator() {
					public int compare(Object arg0, Object arg1) {
						TaskInstance t0 = (TaskInstance) arg0;
						TaskInstance t1 = (TaskInstance) arg1;
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

				//creem dos timers, un per al temps a calcular i l'altre per a l'acumulat
				Timer t2=new Timer(new Timer[]{new Timer()});

				ListitemCreator decorator = new ListitemCreator(engine);
				for (Iterator itTask = tareas.iterator(); itTask.hasNext();) {
					t2.reset();
						task = (TaskInstance) itTask.next();
						item = decorator.createListitem(task);
						listbox.getItems().add(item);
					t2.logTime("AbstractInboxHandler.createListitem", 100); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					
				}
			}
		} finally {
		}
	}


	protected List getTasks(BpmEngine engine) throws InternalErrorException,
			BPMException {
		return engine.findMyTasks();
	}

	
}
