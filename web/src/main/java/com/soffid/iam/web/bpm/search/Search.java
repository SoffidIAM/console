package com.soffid.iam.web.bpm.search;


import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;
import org.json.JSONObject;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.au.out.AuSendRedirect;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import es.caib.bpm.exception.BPMException;
import es.caib.bpm.ui.inbox.Messages;

import com.soffid.iam.bpm.service.ejb.BpmEngine;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.bpm.BPMApplication;
import com.soffid.iam.web.bpm.process.ProcessUI;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.EJBLocator;
import com.soffid.iam.bpm.api.ProcessDefinition;
import com.soffid.iam.bpm.api.ProcessInstance;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Databox;
import es.caib.zkib.component.DateFormats;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.Frame;
import es.caib.zkib.zkiblaf.Missatgebox;

public class Search extends FrameHandler 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Log log = LogFactory.getLog(Search.class);
	
	private Databox txtProcessID;

	private Databox txtTarea;
	
	private Databox txtStartDate, txtEndDate;

	private Databox chkFinalizada;

	private Databox txtStartDate2;

	private Databox txtEndDate2;

	private List<ProcessInstance> currentProcess;

	public Search() throws InternalErrorException {
		super();
	}
	
	public void afterCompose ()
	{
		super.afterCompose();
		txtProcessID = (Databox) getFellow ("txtProcessID"); //$NON-NLS-1$
		txtTarea = (Databox) getFellow ("txt"); //$NON-NLS-1$
		txtStartDate = (Databox) getFellow ("startDate"); //$NON-NLS-1$
		txtEndDate = (Databox) getFellow ("endDate"); //$NON-NLS-1$
		txtStartDate2 = (Databox) getFellow ("startDate2"); //$NON-NLS-1$
		txtEndDate2 = (Databox) getFellow ("endDate2"); //$NON-NLS-1$
		chkFinalizada = (Databox) getFellow("txtFinalizada"); //$NON-NLS-1$
	}
	
	private BpmEngine getEngine(Session sesion) throws CreateException, NamingException {
		return BPMApplication.getEngine();
	}
	
	public void buscar() throws RemoteException, InterruptedException,
		CreateException, NamingException, WrongValueException, BPMException,
		InternalErrorException
	{
		Session sesion= this.getDesktop().getSession();
		BpmEngine engine = getEngine(sesion);
		Listitem item= null;
		DataTable resultadoBusqueda= null;
		
		try 
		{
			txtStartDate.setWarning(null, "");
			txtStartDate2.setWarning(null, "");
			txtEndDate.setWarning(null, "");
			txtEndDate2.setWarning(null, "");
			Date start0 = (Date) txtStartDate.getValue();
			Date start1 = (Date) txtStartDate2.getValue();
			Date end0 = (Date) txtEndDate.getValue();
			Date end1 = (Date) txtEndDate2.getValue();
			if (start0 != null && start0.after(new Date())) {
				txtStartDate.setWarning(null, "Date after today");
			}
			if (start1 != null && start1.after(new Date())) {
				txtStartDate2.setWarning(null, "Date after today");
			}
			if (start0 != null && start1 != null && start0.after(start1)) {
				txtStartDate.setWarning(null, "Date range not valid");
			}
			if (end0 != null && end0.after(new Date())) {
				txtEndDate.setWarning(null, "Date after today");
			}
			if (end1 != null && end1.after(new Date())) {
				txtEndDate2.setWarning(null, "Date after today");
			}
			if (end0 != null && end1 != null && end0.after(end1)) {
				txtEndDate.setWarning(null, "Date range not valid");
			}
			
			currentProcess = engine.searchProcessInstances(
				(String) txtTarea.getValue(),
				(String) txtProcessID.getValue(), 
				start0, start1,
				end0, end1,
				Boolean.TRUE.equals( chkFinalizada.getValue()));

			Collections.sort(currentProcess, new Comparator<ProcessInstance>() {
				@Override
				public int compare(ProcessInstance o1, ProcessInstance o2) {
					return - o1.getStart().compareTo(o2.getStart());
				}
			});

			
			if(currentProcess.size() == 0)
			{
				Missatgebox.avis(Labels.getLabel("observacion.resultadoVacio")); //$NON-NLS-1$
			}
			
			//Limpiamos el resultado y la imagen
			resultadoBusqueda= (DataTable)this.getFellow("listbox"); //$NON-NLS-1$
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			DateFormat df = DateFormats.getDateTimeFormat();
			for(Iterator<ProcessInstance> it = currentProcess.iterator(); it.hasNext();)
			{
				ProcessInstance proc = it.next();
				if (Boolean.TRUE.equals( chkFinalizada.getValue()) || proc.getEnd() == null)
				{
					JSONObject o = new JSONObject();
					o.put("id", proc.getId());
					o.put("description", proc.getDescription() );
					o.put("currentTask", proc.getCurrentTask());
					if (proc.getStart() != null) {
						o.put("start", proc.getStart().getTime());
						o.put("start_datetime", df.format(proc.getStart()));
					}
					if (proc.getEnd() != null) {
						o.put("end", proc.getEnd().getTime());
						o.put("end_datetime", df.format(proc.getEnd()));
					}
					
					JSONObject atts = new JSONObject();
					for (Entry<String, Object> att: proc.getVariables().entrySet()) {
						Object value = att.getValue();
						if (value != null) {
							resultadoBusqueda.wrapClientValue(atts, att.getKey(), value);
						}
					}
					o.put("attributes", atts);
					
					if (sb.length() > 1) sb.append(",");
					sb.append(o.toString());
				}
				else
					it.remove();
			}
			sb.append("]");
			resultadoBusqueda.setData(sb.toString());
		} 
		catch (BPMException e) 
		{
			String msgError = Labels.getLabel("error.code." + e.getErrorCode()); //$NON-NLS-1$
			if (msgError==null)
				msgError = e.getMessage();
			Missatgebox.error(msgError);
		}
	}
	
	public void activaFi (Event ev)
	{
		boolean disabled = Boolean.FALSE.equals( chkFinalizada.getValue());
		getFellow("enddateblock").setVisible( ! disabled );
		if (disabled) {
			txtEndDate.setValue(null);
			txtEndDate2.setValue(null);
		}
	}

	public void onOpenHelp (Event ev) {
		response("redirect", new AuSendRedirect("/queryparsersyntax.html", "_blank"));
	}
	
	public void openProcess(Event event) throws ClassNotFoundException, IOException, SQLException, NamingException, CreateException, InternalErrorException, BPMException, Exception {
		DataTable listbox = (DataTable) getFellow("listbox"); //$NON-NLS-1$
		int pos = listbox.getSelectedIndex();
        ProcessInstance process = currentProcess.get(pos);
        ProcessUI processUi = (ProcessUI) getFellow("process").getFellow("frame");

        getFellow("query-box").setVisible(false);
        showDetails();
        Security.nestedLogin(new String[] {"BPM_INTERNAL"});
        try {
        	process = EJBLocator.getBpmEngine().getProcess(process.getId()); // Load full process variables
        	if (process == null)
        		throw new UiException("Access denied");
        } finally {
        	Security.nestedLogoff();
        }
        processUi.openProcessInstance(process, false);
        processUi.setParentFrame(this);
//        getFellow("query-box").setVisible(true);
	}

	@Override
	public void hideDetails() throws CommitException {
        getFellow("query-box").setVisible(true);
		super.hideDetails();
	}
	

}
