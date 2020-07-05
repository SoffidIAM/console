package com.soffid.iam.web.popup;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.util.resource.Labels;
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

import com.soffid.codemirror.Codemirror;

public class Editor extends Window implements AfterCompose {
	EventListener listener;
	private String vars;
	private Textbox textbox;
	private Codemirror editor;
	
	public static void edit ( Textbox textbox, String vars) {
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
			editorWindow.editor.setValue( textbox.getValue() );
			editorWindow.doHighlighted();
			editorWindow.editor.focus();
		}
	}
	
	@Override
	public void setPage (Page page) {
		super.setPage(page);
		Execution ex = Executions.getCurrent();
		Map args = ex.getArg();
		this.textbox = (Textbox) args.get("textbox");
		this.vars = (String) args.get("vars");
	}
	
	public void cleanWindow(Event event) {
		editor.setValue("");
		setVisible(false);
	}

	public void accept(Event event) {
		textbox.setValue(editor.getValue());
		cleanWindow(event);
	}

	@Override
	public void afterCompose() {
		editor = (Codemirror) getFellow("editor");
		editor.setGlobalVars(vars);
		editor.setValue( textbox.getValue() );
		editor.focus();
	}
}
