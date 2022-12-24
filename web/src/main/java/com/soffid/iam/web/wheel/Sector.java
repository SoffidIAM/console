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
		String parentClassName = "g_"+tag.substring(0, tag.length()-2);
		handler.response(null, new AuScript(handler, "document.getElementById('g_"+tag+"').classList.add('dim');"));
//				+ "document.getElementById('g_"+tag+"').classList.remove('"+parentClassName+"');"));
	}

	public void onClick(String tag2) {
		if (tag2.equals(tag)) {
			activate();
			if (isDone())
				dim(handler);
		}
	}

	protected void activate() {
		Window w = (Window) getHandler().getFellowIfAny(getTag()+"_w");
		if (w == null)
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
