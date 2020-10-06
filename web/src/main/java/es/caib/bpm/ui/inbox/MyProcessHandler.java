package es.caib.bpm.ui.inbox;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

import com.soffid.iam.web.bpm.BPMApplication;
import com.soffid.iam.web.bpm.ListitemCreator;

import es.caib.bpm.exception.BPMException;
import com.soffid.iam.bpm.service.ejb.BpmEngine;

import es.caib.bpm.vo.ProcessInstance;
import es.caib.seycon.ng.EJBLocator;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DateFormats;
import es.caib.zkib.events.SerializableEventListener;
import es.caib.zkib.zkiblaf.Application;

public class MyProcessHandler extends com.soffid.iam.web.component.Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Listbox listbox;


	public MyProcessHandler () {
	}
	
	
	public void onCreate () throws Exception
	{
		try {
			listbox = (Listbox) getFellow("listadoProcesos"); //$NON-NLS-1$
			listbox.addEventListener("onSelect", new SerializableEventListener () { //$NON-NLS-1$

				public void onEvent(Event event) throws Exception {
			        Listitem item = listbox.getSelectedItem();
			        ProcessInstance proc = (ProcessInstance) item.getValue();

			        Application.call(BPMApplication.getProcessURL(proc));
			        listbox.setSelectedItem(null);
				}
				
			});
			addEventListener("onReturn", new SerializableEventListener () { //$NON-NLS-1$
				public void onEvent(Event event) throws Exception {
					refresh ();
				}
				
			});
			refresh ();
		} catch (Exception e) {
			throw new UiException(e);
		}
	}
	
	public void refresh() throws RemoteException,
			InterruptedException, CreateException, NamingException,
			BPMException, InternalErrorException {
		BpmEngine engine = BPMApplication.getEngine();
		Listitem item = null;

		try {
			List<ProcessInstance> procs = EJBLocator.getBpmEngine().findMyProcesses();

			if (listbox.getItems() != null) {
				listbox.getItems().clear();
				listbox.setSelectedItem(null);
			}

			if (procs != null) {
				Collections.sort(procs, new Comparator<ProcessInstance>() {
					public int compare(ProcessInstance t0, ProcessInstance t1) {
						return - t0.getStart().compareTo(t1.getStart());
					}
				});

				ListitemCreator decorator = new ListitemCreator(engine);
				for (ProcessInstance proc: procs) {
					item = new Listitem();
					item.appendChild( new Listcell( Long.toString(proc.getId()) ));
					item.appendChild( new Listcell( proc.getDescription() ));
					item.appendChild( new Listcell( DateFormats.getDateTimeFormat().format(proc.getStart()) ));
					item.appendChild( new Listcell( proc.getCurrentTask() ));
					item.setValue(proc);
					listbox.appendChild(item);
				}
			}
		} finally {
		}
	}


}
