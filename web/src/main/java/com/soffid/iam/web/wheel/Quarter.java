package com.soffid.iam.web.wheel;

import org.zkoss.zk.au.out.AuScript;

public class Quarter {
	private String tag;
	private Sector[] sectors;

	public Quarter(String tag, Sector... sectors) {
		this.tag = tag;
		this.sectors = sectors;
	}
	
	public void updateStatus(WheelHandler handler) {
		int done = 0;
		for (Sector sector: sectors) {
			if (sector.isDone()) {
				done ++;
				sector.dim(handler);
			}
		}
		handler.response(null, new AuScript(handler, "document.getElementById('"+tag+"_counter').innerHTML='"+done+"/"+sectors.length+"';"));
	}

	public void onSector(String tag) {
		for (Sector s: sectors)
			s.onClick(tag);
	}

	public void installHandler(WheelHandler wheelHandler) {
		for (Sector s: sectors)
			s.installHandler(wheelHandler);
	}
}
