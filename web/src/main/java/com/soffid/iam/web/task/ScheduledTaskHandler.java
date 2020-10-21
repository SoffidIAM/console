package com.soffid.iam.web.task;

import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.bpm.api.Job;
import com.soffid.iam.bpm.api.ProcessInstance;
import com.soffid.iam.doc.exception.DocumentBeanException;
import com.soffid.iam.doc.service.ejb.DocumentService;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.bpm.exception.BPMException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Databox;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;

public class ScheduledTaskHandler extends FrameHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public ScheduledTaskHandler () throws InternalErrorException {
		super();
	}
	
	
	public void refresh() throws RemoteException,
			InterruptedException, CreateException, NamingException,
			BPMException, InternalErrorException {
		getModel().refresh();
	}

	public void onChangeForm(Event event) throws Exception {
		DataTable dt = (DataTable) getListbox();
		if (dt.getSelectedIndex() >= 0) {
			Calendar last = (Calendar) XPathUtils.eval(getForm(), "lastEnd");
			Boolean active = (Boolean) XPathUtils.eval(getForm(), "active");
			String ref = (String) XPathUtils.eval(getForm(), "logReferenceID");
			if (last == null)
				getFellow("logSection").setVisible(false);
			else {
				getFellow("logSection").setVisible(true);
				Databox logField = (Databox) getFellow("log");
				if (last == null || ref == null)
				{
					logField.setVisible(false);
				} else {
					logField.setVisible(true);
					com.soffid.iam.doc.service.ejb.DocumentService doc = es.caib.seycon.ng.EJBLocator.getDocumentService();
					doc.openDocument(new com.soffid.iam.doc.api.DocumentReference(ref));
					java.io.InputStream in = new com.soffid.iam.doc.api.DocumentInputStream(doc);
					InputStreamReader reader = new InputStreamReader(in, "UTF-8");
					int lines = 0;
					int ch;
					StringBuffer sb = new StringBuffer();
					for (ch = reader.read(); ch >= 0 && lines < 10; ch = reader.read()) {
						sb.append((char) ch);
						if (ch == '\n') lines ++;
					}
					reader.close();
					in.close();
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
		}
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
	
}

