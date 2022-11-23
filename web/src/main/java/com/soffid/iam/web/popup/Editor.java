package com.soffid.iam.web.popup;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Html;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.InputElement;

import com.soffid.codemirror.Codemirror;

public class Editor extends Window implements AfterCompose {
	EventListener listener;
	private String vars;
	private InputElement textbox;
	private Codemirror editor;
	private String env;
	
	public static void edit ( InputElement textbox, String vars) {
		edit (textbox, vars, null);
	}
	
	public static void edit ( InputElement textbox, String vars, String env) {
		edit(textbox, vars, env, null);
	}
	public static void edit ( InputElement textbox, String vars, String env, EventListener listener) {
		Page p = textbox.getPage();
		Editor editorWindow = (Editor) p.getFellowIfAny("editorWindow");
		if (editorWindow == null) {
			Map args = new HashMap();
			args.put("textbox", textbox);
			args.put("vars",  vars);
			args.put("env", env);
			args.put("listener", listener);
			Executions.createComponents("/popup/editor.zul", null, args );
		} else {
			editorWindow.textbox = textbox;
			editorWindow.vars = vars;
			editorWindow.env = env;
			editorWindow.editor.setGlobalVars(vars);
			editorWindow.doHighlighted();
			editorWindow.editor.setValue( textbox.getText() );
			editorWindow.editor.focus();
			editorWindow.listener = listener;
			editorWindow.updateEnv();
		}
	}
	
	private void updateEnv() {
		if (env == null) {
			getFellow("env").setVisible(false);
			getFellow("envtext").setVisible(false);
		} else {
			getFellow("env").setVisible(true);
			Html h = (Html) getFellow("envtext");
			h.setVisible(true);
			h.setContent(env);
		}
	}

	@Override
	public void setPage (Page page) {
		super.setPage(page);
		Execution ex = Executions.getCurrent();
		Map args = ex.getArg();
		this.textbox = (InputElement) args.get("textbox");
		this.vars = (String) args.get("vars");
		this.env = (String) args.get("env");
		this.listener = (EventListener) args.get("listener");
	}
	
	public void cleanWindow(Event event) throws WrongValueException, Exception {
		editor.setValue("");
		setVisible(false);
		if (listener != null)
			listener.onEvent(new Event("onChange", textbox, textbox.getText()));
	}

	public void accept(Event event) throws WrongValueException, Exception {
		textbox.setText(editor.getValue());
		cleanWindow(event);
	}

	@Override
	public void afterCompose() {
		editor = (Codemirror) getFellow("editor");
		editor.setGlobalVars(vars);
		editor.setValue( textbox.getText() );
		editor.focus();
		updateEnv();
	}

}
