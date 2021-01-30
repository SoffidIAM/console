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

public class HtmlEditor extends Window implements AfterCompose {
	EventListener listener;
	private InputElement textbox;
	private Ckeditor editor;
	
	public static void edit ( InputElement textbox) {
		Page p = textbox.getPage();
		HtmlEditor editorWindow = (HtmlEditor) p.getFellowIfAny("htmlEditorWindow");
		if (editorWindow == null) {
			Map args = new HashMap();
			args.put("textbox", textbox);
			Executions.createComponents("/popup/htmlEditor.zul", null, args );
		} else {
			editorWindow.textbox = textbox;
			editorWindow.doHighlighted();
			editorWindow.editor.setValue( textbox.getText() );
		}
	}
	
	@Override
	public void setPage (Page page) {
		super.setPage(page);
		Execution ex = Executions.getCurrent();
		Map args = ex.getArg();
		this.textbox = (InputElement) args.get("textbox");
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
		editor = (Ckeditor) getFellow("editor");
		editor.setValue( textbox.getText() );
	}

}
