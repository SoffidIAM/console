package com.soffid.iam.web.common;

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
import org.zkoss.zul.Include;
import org.zkoss.zul.Window;

public class FileUpload2 extends Window {
	EventListener listener;
	
	public static void get ( EventListener listener) {
		get ( Labels.getLabel("fileupload.upload"), listener);
	}
	public static void get (String title, EventListener listener) {
		Map args = new HashMap();
		args.put("listener", listener);
		args.put("title",  title);
		
		Executions.createComponents("/popup/upload.zul", null, args );
	}
	
	@Override
	public void setPage (Page page) {
		super.setPage(page);
		Execution ex = Executions.getCurrent();
		Map args = ex.getArg();
		setTitle((String) args.get("title"));
		this.listener = (EventListener) args.get("listener");
	}
	
	public void onUpload (UploadEvent event) throws Exception {
		this.listener.onEvent(event);
		detach();
	}

	public void cancelUpload (Event event) throws Exception {
		detach();
	}
}
