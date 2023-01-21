package com.soffid.iam.web.bpm.process;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

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
import com.soffid.iam.bpm.api.ProcessInstance;
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

public class MyRequestsHandler extends com.soffid.iam.web.component.FrameHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataTable listbox;
	private List<ProcessInstance> currentProcess;


	public MyRequestsHandler () throws InternalErrorException {
		super();
	}
	
	
	public void afterCompose () 
	{
		super.afterCompose();
		try {
			listbox = (DataTable) getFellow("listbox"); //$NON-NLS-1$

			refresh ();
		} catch (Exception e) {
			throw new UiException(e);
		}
	}
	
	public void openProcess(Event event) throws ClassNotFoundException, IOException, SQLException, NamingException, CreateException, InternalErrorException, BPMException, Exception {
		int pos = listbox.getSelectedIndex();
        ProcessInstance process = currentProcess.get(pos);
        ProcessUI processUi = (ProcessUI) getFellow("process").getFellow("frame");

        showDetails();
        processUi.openProcessInstance(process, true);
        processUi.setParentFrame(this);
	}
	
	public void refresh() throws RemoteException,
			InterruptedException, CreateException, NamingException,
			BPMException, InternalErrorException {
		currentProcess = null;

		try {
			currentProcess = EJBLocator.getBpmEngine().findMyProcesses();

			if (currentProcess != null) {
				StringBuffer sb = new StringBuffer();
				sb.append("[");

				for (Iterator<ProcessInstance> itTask = currentProcess.iterator(); itTask.hasNext();) {
					ProcessInstance process = itTask.next();
					if (sb.length() > 1) sb.append(",");
					JSONObject o = new JSONObject();
					o.put("id", process.getId());
					o.put("description", process.getDescription() );
					o.put("currentTask", process.getCurrentTask());
					o.put("start_datetime", DateFormats.getDateTimeFormat().format(process.getStart()));
					o.put("start", process.getStart().getTime());
					if (process.getEnd() != null) {
						o.put("end_datetime", DateFormats.getDateTimeFormat().format(process.getEnd()));
						o.put("end", process.getEnd().getTime());
					}
					JSONObject atts = new JSONObject();
					for (Entry<String, Object> att: process.getVariables().entrySet()) {
						Object value = att.getValue();
						if (value != null) {
							listbox.wrapClientValue(atts, att.getKey(), value);
						}
					}
					o.put("attributes", atts);
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
		super.hideDetails();
	}

	
}
