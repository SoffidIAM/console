package com.soffid.iam.web.wheel;

import java.util.HashMap;

import org.zkoss.zk.au.out.AuScript;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

public class Sector {
	private String tag;
	private WheelHandler handler;

	public Sector(String tag) {
		this.tag = tag;
	}
	public boolean isDone() {
		return false;
	}

	public void installHandler(WheelHandler handler) {
		this.handler = handler;
		handler.response(null, new AuScript(handler, "zk.listen(document.getElementById('g_"+tag+"'), 'click', () => {"
				+ "zkau.send({\n"
				+ "            uuid: '"+ handler.getUuid()+"', "
				+ "            cmd: \"onSector\", "
				+ "            data: ['"+tag+"'] "
				+ "        }, 100)"
				+ "});"));		
	}
	
	public void dim(WheelHandler handler) {
		handler.response(null, new AuScript(handler, "{var e=document.getElementById('g_"+tag+"'); if (e) {e.classList.add('dim');}}"));
	}

	public void done(WheelHandler handler) {
		handler.response(null, new AuScript(handler, "{var e=document.getElementById('g_"+tag+"'); if (e) {e.classList.remove('dim');e.classList.add('done');}}"));
	}

	public void onClick(String tag2) {
		if (tag2.equals(tag)) {
			activate();
			if (isDone())
				done(handler);
			else
				dim(handler);
		}
	}

	protected void activate() {
		Window w = (Window) getHandler().getFellowIfAny(getTag()+"_w");
		if (w != null)
			w.detach();
		w = (Window) Executions.getCurrent().createComponents("/config/wheel/"+getTag()+".zul", getHandler(), new HashMap<>());
		w.doHighlighted();
	}
	
	public String getTag() {
		return tag;
	}
	
	public WheelHandler getHandler() {
		return handler;
	}

}
