package com.soffid.iam.web.popup;

import java.util.HashMap;
import java.util.Map;

import org.zkforge.fckez.FCKeditor;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.InputElement;

import com.soffid.ckeditor.Ckeditor;
import com.soffid.iam.web.component.CustomField3;

public class EmailEditor extends Window implements AfterCompose {
	EventListener listener;
	private Ckeditor actualBody;
	private CustomField3 actualSubject;
	private CustomField3 actualSendTo;
	private InputElement body;
	private InputElement subject;
	private InputElement sendTo;
	
	public static void edit ( InputElement sendTo, InputElement subject, InputElement body, EventListener listener) {
		Page p = body.getPage();
		EmailEditor editorWindow = (EmailEditor) p.getFellowIfAny("emailEditorWindow");
		if (editorWindow == null) {
			Map args = new HashMap();
			args.put("body", body);
			args.put("sendTo", sendTo);
			args.put("subject", subject);
			args.put("listener", listener);
			Executions.createComponents("/popup/emailEditor.zul", null, args );
		} else {
			editorWindow.body = body;
			editorWindow.subject=subject;
			editorWindow.sendTo = sendTo;
			editorWindow.listener = listener;
			editorWindow.doHighlighted();
			editorWindow.actualBody.setValue( body.getText() );
			editorWindow.actualSendTo.setValue( sendTo.getText());
			editorWindow.actualSubject.setAction( subject.getText() );
		}
	}
	
	public static void edit ( InputElement sendTo, InputElement subject, InputElement body) {
		edit (sendTo, subject, body, null);
	}
	@Override
	public void setPage (Page page) {
		super.setPage(page);
		Execution ex = Executions.getCurrent();
		Map args = ex.getArg();
		this.body = (InputElement) args.get("body");
		this.sendTo = (InputElement) args.get("sendTo");
		this.subject = (InputElement) args.get("subject");
		listener = (EventListener) args.get("listener");
	}
	
	public void cleanWindow(Event event) {
		actualBody.setValue("");
		actualSendTo.setValue("");
		actualSubject.setValue("");
		setVisible(false);
	}

	public void accept(Event event) throws Exception {
		body.setText(actualBody.getValue());
		sendTo.setText(actualSendTo.getText());
		subject.setText(actualSubject.getText());
		cleanWindow(event);
		if (listener != null) {
			listener.onEvent(event);
		}
	}

	@Override
	public void afterCompose() {
		actualBody = (Ckeditor) getFellow("body");
		actualSendTo = (CustomField3) getFellow("sendTo");
		actualSubject = (CustomField3) getFellow("subject");
		actualBody.setValue( body.getText() );
		actualSendTo.setValue( sendTo.getText());
		actualSubject.setValue( subject.getText() );
	}

}
