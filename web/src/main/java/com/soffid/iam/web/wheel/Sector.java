package com.soffid.iam.web.wheel;

import org.zkoss.zk.au.out.AuScript;

public class Sector {
	private String tag;
	private WheelHandler handler;

	public Sector(String tag) {
		this.tag = tag;
	}
	public boolean isDone() {
		return tag.equals("am03");
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
			dim(handler);
		}
	}
	
	public String getTag() {
		return tag;
	}
	
	public WheelHandler getHandler() {
		return handler;
	}

}
