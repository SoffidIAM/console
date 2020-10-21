package com.soffid.iam.web.bpm;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.bpm.api.Job;
import com.soffid.iam.bpm.api.ProcessInstance;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.bpm.exception.BPMException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;

public class ActiveJobHandler extends FrameHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public ActiveJobHandler () throws InternalErrorException {
		super();
	}
	
	
    public void onSelectJob ()
    {
    }

	public void refresh() throws RemoteException,
			InterruptedException, CreateException, NamingException,
			BPMException, InternalErrorException {
		getModel().refresh();
	}

	public void onChangeForm(Event event) {
		DataTable dt = (DataTable) getListbox();
		if (dt.getSelectedIndex() >= 0) {
			String errorMessage = (String) XPathUtils.eval(getForm(), "errorMessage");
			getFellow("errorLog").setVisible(errorMessage != null && ! errorMessage.trim().isEmpty());
			Button pauseButton = (Button) getFellow("pausebutton"); //$NON-NLS-1$
			Button resumeButton = (Button) getFellow("resumebutton"); //$NON-NLS-1$
			Button retryButton = (Button) getFellow("retrybutton"); //$NON-NLS-1$
			Button closeButton = (Button) getFellow("closebutton"); //$NON-NLS-1$
			
			CustomField3 statusLabel = (CustomField3) getFellow("status");
			if (Boolean.TRUE.equals(XPathUtils.eval(getForm(), "paused")))
			{
				statusLabel.setValue(Labels.getLabel("job.status.pause")); //$NON-NLS-1$
				resumeButton.setVisible(true);
				pauseButton.setVisible(false);
				retryButton.setVisible(false);
			}
			else if (Boolean.TRUE.equals(XPathUtils.eval(getForm(), "error")))
			{
				statusLabel.setValue(Labels.getLabel("job.status.error")); //$NON-NLS-1$
				retryButton.setVisible(true);
				pauseButton.setVisible(false);
				resumeButton.setVisible(false);
			}
			else 
			{
				Integer failures = (Integer) XPathUtils.eval(getForm(), "failures");
				if (failures != null && failures.intValue() > 0 && errorMessage != null && ! errorMessage.trim().isEmpty())
					statusLabel.setValue(Labels.getLabel("job.status.warning")); //$NON-NLS-1$
				else
					statusLabel.setValue(Labels.getLabel("job.status.pending")); //$NON-NLS-1$
				retryButton.setVisible(false);
				resumeButton.setVisible(false);
				pauseButton.setVisible(true);
			}

			CustomField3 procLabel = (CustomField3) getFellow("procLabel");
			Long procId = (Long) XPathUtils.eval(getForm(), "processId");
			try {
				Execution exec = getDesktop().getExecution();
				ProcessInstance proc = EJBLocator.getBpmEngine().getProcess(procId);
				StringBuffer sb = new StringBuffer();
				sb.append("<A target='_blank' href='" + exec.getContextPath()+"/wf/process.zul?id=") .append(procId).append("'>")
					.append(procId)
					.append(" - ")
					.append(proc.getDescription().replaceAll("<", "&lt;").replaceAll(">", "&gt;"))
					.append("</A>");
				procLabel.setValue(sb.toString());
			} catch (Exception e) {
				procLabel.setValue(procId);
			}
		}
	}
	
	public void pause (Event event) throws InternalErrorException, BPMException, CreateException, NamingException, CommitException, RemoteException, InterruptedException {
		Job currentJob = (Job) ((DataNode)XPathUtils.eval(getForm(), ".")).getInstance();
		BPMApplication.getEngine().pauseJob(currentJob);
		hideDetails();
		refresh();
	}

	public void resume (Event event) throws InternalErrorException, BPMException, CreateException, NamingException, CommitException, RemoteException, InterruptedException {
		Job currentJob = (Job) ((DataNode)XPathUtils.eval(getForm(), ".")).getInstance();
		BPMApplication.getEngine().resumeJob(currentJob);
		hideDetails();
		refresh();
	}
	
	public void retry (Event event) throws InternalErrorException, BPMException, CreateException, NamingException, CommitException, RemoteException, InterruptedException {
		Job currentJob = (Job) ((DataNode)XPathUtils.eval(getForm(), ".")).getInstance();
		BPMApplication.getEngine().retryJob(currentJob);
		hideDetails();
		refresh();
	}

}

