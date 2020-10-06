package com.soffid.iam.web.popup;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Include;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.InputElement;

import com.soffid.codemirror.Codemirror;
import com.soffid.iam.web.component.InputField3;

public class Editor extends Window implements AfterCompose {
	EventListener listener;
	private String vars;
	private InputElement textbox;
	private Codemirror editor;
	
	public static void edit ( InputElement textbox, String vars) {
		Page p = textbox.getPage();
		Editor editorWindow = (Editor) p.getFellowIfAny("editorWindow");
		if (editorWindow == null) {
			Map args = new HashMap();
			args.put("textbox", textbox);
			args.put("vars",  vars);
			
			Executions.createComponents("/popup/editor.zul", null, args );
		} else {
			editorWindow.textbox = textbox;
			editorWindow.vars = vars;
			editorWindow.editor.setGlobalVars(vars);
			editorWindow.doHighlighted();
			editorWindow.editor.setValue( textbox.getText() );
			editorWindow.editor.focus();
		}
	}
	
	@Override
	public void setPage (Page page) {
		super.setPage(page);
		Execution ex = Executions.getCurrent();
		Map args = ex.getArg();
		this.textbox = (InputElement) args.get("textbox");
		this.vars = (String) args.get("vars");
	}
	
	public void cleanWindow(Event event) {
		editor.setValue("");
		setVisible(false);
	}

	public void accept(Event event) {
		textbox.setText(editor.getValue());
		cleanWindow(event);
	}

	@Override
	public void afterCompose() {
		editor = (Codemirror) getFellow("editor");
		editor.setGlobalVars(vars);
		editor.setValue( textbox.getText() );
		editor.focus();
	}

}
