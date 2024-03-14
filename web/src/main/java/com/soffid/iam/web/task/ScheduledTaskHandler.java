package com.soffid.iam.web.task;

import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Filedownload;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.api.ScheduledTaskLog;
import com.soffid.iam.doc.exception.DocumentBeanException;
import com.soffid.iam.doc.service.ejb.DocumentService;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.bpm.exception.BPMException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.binder.BindContext;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Databox;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.events.XPathRerunEvent;

public class ScheduledTaskHandler extends FrameHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean discovery;

	public ScheduledTaskHandler () throws InternalErrorException {
		super();
	}
	
	
	public void refresh() throws RemoteException,
			InterruptedException, CreateException, NamingException,
			BPMException, InternalErrorException {
		getModel().refresh();
	}

	public void onChangeForm(Event event) throws Exception {
		BindContext ctx = getForm();
		try {
			XPathUtils.eval(ctx, "instance");
		} catch (Exception e) {
			return;
		}
		Calendar last = (Calendar) XPathUtils.eval(getForm(), "lastEnd");
		Boolean active = (Boolean) XPathUtils.eval(getForm(), "active");
		Boolean error = (Boolean) XPathUtils.eval(getForm(), "error");
		String ref = (String) XPathUtils.eval(getForm(), "logReferenceID");
		List<ScheduledTaskLog> logs = (List<ScheduledTaskLog>) XPathUtils.eval(getForm(), "logs");
		
		getFellow("status-running").setVisible(Boolean.TRUE.equals(active));
		getFellow("status-stopped").setVisible(!Boolean.TRUE.equals(active));
		getFellow("warning").setVisible(Boolean.TRUE.equals(error));

		getFellow("logsSection").setVisible(logs != null && !logs.isEmpty());
		
		ctx.getDataSource().sendEvent(new XPathRerunEvent(ctx.getDataSource(), ctx.getXPath()+"/logs"));
		
		if (last == null || Boolean.TRUE.equals(active))
			getFellow("logSection").setVisible(false);
		else {
			getFellow("logSection").setVisible(true);
			Databox logField = (Databox) getFellow("log");
			if (last == null || ref == null)
			{
				logField.setVisible(false);
			} else {
				logField.setVisible(true);
				int ch = -1;
				StringBuffer sb = new StringBuffer();
				try {
					com.soffid.iam.doc.service.ejb.DocumentService doc = es.caib.seycon.ng.EJBLocator.getDocumentService();
					doc.openDocument(new com.soffid.iam.doc.api.DocumentReference(ref));
					java.io.InputStream in = new com.soffid.iam.doc.api.DocumentInputStream(doc);
					InputStreamReader reader = new InputStreamReader(in, "UTF-8");
					int lines = 0;
					for (ch = reader.read(); ch >= 0 && lines < 10; ch = reader.read()) {
						sb.append((char) ch);
						if (ch == '\n') lines ++;
					}
					reader.close();
					in.close();
				} catch (Exception e) {
					// Document has been erased
				}
				if (ch >= 0) {
					logField.setSelectIcon("/img/download.svg");
					logField.setSelectIcon2("/img/download-white.svg");
					logField.setForceSelectIcon(true);
					sb.append("...");
				} else {
					logField.setSelectIcon(null);
					logField.setSelectIcon2(null);
					logField.setForceSelectIcon(false);
				}
				logField.setValue(sb.toString());
				logField.invalidate();
			}
		}
		updateStatus(event);
		if (isDiscovery())
			getModel().commit();
	}
	
	public void downloadLog(Event event) throws IllegalArgumentException, InternalErrorException, DocumentBeanException, NamingException, CreateException {
		String ref = (String) XPathUtils.eval(getForm(), "logReferenceID");
		String name = (String) XPathUtils.eval(getForm(), "name");
		DocumentService doc = EJBLocator.getDocumentService();
		doc.openDocument(new com.soffid.iam.doc.api.DocumentReference(ref));

		Filedownload.save(new com.soffid.iam.doc.api.DocumentInputStream(doc),
				"text/plain; charset=utf-8",
				name+".txt");
	}

	public void updateStatus(Event event) {
		BindContext ctx = getForm();
		try {
			XPathUtils.eval(ctx, "instance");
		} catch (Exception e) {
			return;
		}
		
		Long taskId = (Long) XPathUtils.eval(ctx, "@id");
		if (taskId != null) {
			ScheduledTask task;
			try {
				task = EJBLocator.getScheduledTaskService().load(taskId);
				Object lastExecution = XPathUtils.eval(ctx, "lastExecution");
				Object lastEnd = XPathUtils.eval(ctx, "lastEnd");
				if ((lastExecution == null ? 
						task.getLastExecution() != null :
						!lastExecution.equals(task.getLastExecution())) ||
					(lastEnd == null ?
						task.getLastEnd() != null:
						!lastEnd.equals(task.getLastEnd())))
				{
					XPathUtils.setValue(ctx, "lastEnd", task.getLastEnd());
					XPathUtils.setValue(ctx, "error", task.isError());
					XPathUtils.setValue(ctx, "active", task.isActive());
					XPathUtils.setValue(ctx, "lastExecution", task.getLastExecution());
					XPathUtils.setValue(ctx, "logReferenceID", task.getLogReferenceID());
					XPathUtils.setValue(ctx, "logs", task.getLogs());
					ctx.getDataSource().sendEvent(new XPathRerunEvent(ctx.getDataSource(), "/logs"));
					onChangeForm(event);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void start(Event event) throws InternalErrorException, NamingException, CreateException, InterruptedException, CommitException {
		ScheduledTask task = (ScheduledTask) XPathUtils.eval(getForm(), "instance");
		EJBLocator.getScheduledTaskService().startNow(task );
		Thread.sleep(2000);
		if (isDiscovery())
			getModel().commit();
		updateStatus(event);
	}
	
	public void downloadOtherLog() throws IllegalArgumentException, InternalErrorException, NamingException, CreateException, DocumentBeanException {
		DataTable dt = (DataTable) getFellow("logs");
		String reference = (String) dt.getJXPathContext().getValue("logReferenceID");
		if (reference != null) {
			String name = (String) XPathUtils.eval(getForm(), "name");
			DocumentService doc = EJBLocator.getDocumentService();
			doc.openDocument(new com.soffid.iam.doc.api.DocumentReference(reference));

			Filedownload.save(new com.soffid.iam.doc.api.DocumentInputStream(doc),
					"text/plain; charset=utf-8",
					name+".txt");			
		}
	}

	
	public boolean isDiscovery() {
		return discovery;
	}


	
	public void setDiscovery(boolean discovery) {
		this.discovery = discovery;
	}
}

