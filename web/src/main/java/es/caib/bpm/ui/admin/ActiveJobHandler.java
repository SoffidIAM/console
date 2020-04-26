package es.caib.bpm.ui.admin;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

import es.caib.bpm.exception.BPMException;
import es.caib.bpm.servei.ejb.BpmEngine;
import es.caib.bpm.toolkit.BPMApplication;
import es.caib.bpm.ui.inbox.ListitemCreator;
import es.caib.bpm.vo.Job;
import es.caib.bpm.vo.ProcessInstance;
import es.caib.bpm.vo.TaskInstance;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataListbox;
import es.caib.zkib.events.SerializableEventListener;
import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.Frame;

public class ActiveJobHandler extends Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Listbox listbox;
	private Job currentJob;
	private Window currentJobWindow;


	public ActiveJobHandler () {
	}
	
	
	public void onCreate () throws Exception
	{
		try {
			listbox = (Listbox) getFellow("listadoJobs"); //$NON-NLS-1$
			listbox.addEventListener("onSelect", new SerializableEventListener () { //$NON-NLS-1$

				public void onEvent(Event event) throws Exception {
					onSelectJob();
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
	
    public void onSelectJob ()
    {
    	if ( listbox.getSelectedItem() != null)
    	{
    		if (currentJobWindow != null) {
				currentJobWindow.setVisible(false);
				currentJobWindow.setParent(null);
    		}

			currentJob = (Job) listbox.getSelectedItem().getValue();
	    	Component[] components = Executions.getCurrent().createComponents("/wf/job.zul", new HashMap()); //$NON-NLS-1$
	    	if (components.length != 1)
	    	{
	    		throw new UiException (Messages.getString("ActiveJobHandler.4")); //$NON-NLS-1$
	    	}
	    	currentJobWindow = (Window)components[0];
	    	currentJobWindow.setParent(this);
	    	SimpleDateFormat formatConHora = new SimpleDateFormat("dd/MM/yyyy hh:mm"); //$NON-NLS-1$
	    	((Label)currentJobWindow.getFellow("job.id")).setValue(Long.toString(currentJob.getId())); //$NON-NLS-1$
	    	((Label)currentJobWindow.getFellow("job.process")).setValue(Long.toString(currentJob.getProcessId())); //$NON-NLS-1$
	    	((Label)currentJobWindow.getFellow("job.name")).setValue(currentJob.getName()); //$NON-NLS-1$
	    	((Label)currentJobWindow.getFellow("job.dueDate")).setValue(formatConHora.format(currentJob.getDueDate())); //$NON-NLS-1$
	    	((Label)currentJobWindow.getFellow("job.failures")).setValue(Integer.toString(currentJob.getFailures())); //$NON-NLS-1$
	    	((Label)currentJobWindow.getFellow("job.error")).setValue(currentJob.getErrorMessage()); //$NON-NLS-1$
	    	Label statusLabel =((Label)currentJobWindow.getFellow("job.status")); //$NON-NLS-1$
	    	Button pauseButton = (Button) currentJobWindow.getFellow("pausebutton"); //$NON-NLS-1$
	    	Button resumeButton = (Button) currentJobWindow.getFellow("resumebutton"); //$NON-NLS-1$
	    	Button retryButton = (Button) currentJobWindow.getFellow("retrybutton"); //$NON-NLS-1$
	    	Button closeButton = (Button) currentJobWindow.getFellow("closebutton"); //$NON-NLS-1$
	    	Button processButton = (Button) currentJobWindow.getFellow("openprocess"); //$NON-NLS-1$
	    	processButton.addEventListener("onClick", new SerializableEventListener() { //$NON-NLS-1$
				public void onEvent(Event event) throws Exception {
					ProcessInstance p = BPMApplication.getEngine().getProcess(currentJob.getProcessId());
					String url = BPMApplication.getProcessURL(p);
					currentJobWindow.setVisible(false);
					currentJobWindow.setParent(null);
					currentJobWindow = null;
					currentJob = null;
					Application.call(url);
				}
			});
	    	if (currentJob.isPaused())
	    	{
	    		statusLabel.setValue(Labels.getLabel("job.status.pause")); //$NON-NLS-1$
	    		pauseButton.setVisible(false);
	    		retryButton.setVisible(false);
	    		resumeButton.addEventListener("onClick", new SerializableEventListener() { //$NON-NLS-1$
					public void onEvent(Event event) throws Exception {
						BPMApplication.getEngine().resumeJob(currentJob);
						currentJobWindow.setVisible(false);
						currentJobWindow.setParent(null);
						currentJobWindow = null;
						currentJob = null;
						refresh ();
						
					}
				});
	    	}
	    	else if (currentJob.isError())
	    	{
	    		statusLabel.setValue(Labels.getLabel("job.status.error")); //$NON-NLS-1$
	    		pauseButton.setVisible(false);
	    		resumeButton.setVisible(false);
	    		retryButton.addEventListener("onClick", new SerializableEventListener() { //$NON-NLS-1$
					public void onEvent(Event event) throws Exception {
						BPMApplication.getEngine().retryJob(currentJob);
						currentJobWindow.setVisible(false);
						currentJobWindow.setParent(null);
						currentJobWindow = null;
						currentJob = null;
						refresh ();
					}
						
				});
	    	}
	    	else 
	    	{
	    		if (currentJob.getFailures() > 0 && currentJob.getErrorMessage() != null)
	    			statusLabel.setValue(Labels.getLabel("job.status.warning")); //$NON-NLS-1$
	    		else
	    			statusLabel.setValue(Labels.getLabel("job.status.pending")); //$NON-NLS-1$
	    		retryButton.setVisible(false);
	    		resumeButton.setVisible(false);
	    		pauseButton.addEventListener("onClick", new SerializableEventListener() { //$NON-NLS-1$
					public void onEvent(Event event) throws Exception {
						BPMApplication.getEngine().pauseJob(currentJob);
						currentJobWindow.setVisible(false);
						currentJobWindow.setParent(null);
						currentJobWindow = null;
						currentJob = null;
						refresh ();
					}
						
				});
	    	}
	    		
			closeButton.addEventListener("onClick", new SerializableEventListener() { //$NON-NLS-1$
				public void onEvent(Event event) throws Exception {
					currentJobWindow.setVisible(false);
					currentJobWindow.setParent(null);
					currentJobWindow = null;
					currentJob = null;
				}
			});
	
			currentJobWindow.doOverlapped();
	    	
			listbox.setSelectedItem(null);
    	}
    }

	public void refresh() throws RemoteException,
			InterruptedException, CreateException, NamingException,
			BPMException, InternalErrorException {
		BpmEngine engine = BPMApplication.getEngine();
		List jobs = null;
		Listitem item = null;

		try {
			jobs = engine.getActiveJobs();

			if (listbox.getItems() != null) {
				listbox.getItems().clear();
				listbox.setSelectedItem(null);
			}

			if (jobs != null) {
				ListitemCreator decorator = new ListitemCreator(engine);
				for (Iterator itTask = jobs.iterator(); itTask.hasNext();) {
					Job job = (Job) itTask.next();
					item = decorator.createListitem(job);
					listbox.getItems().add(item);
				}
			}
		} finally {
		}
	}
	
}

