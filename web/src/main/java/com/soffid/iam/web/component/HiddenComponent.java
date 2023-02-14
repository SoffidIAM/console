package com.soffid.iam.web.component;

import java.io.IOException;
import java.io.Writer;

import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.render.SmartWriter;


public class HiddenComponent extends AbstractComponent {
	boolean enabled = false;

	@Override
	public void redraw(Writer out) throws IOException {
		final SmartWriter wh = new SmartWriter(out);
		final String uuid = getUuid();		
		if (enabled) {
			wh.write("<div id=\"").write(uuid).write("\">");
			for (Component first = getFirstChild(); first != null; first = first.getNextSibling())
				first.redraw(out);
			wh.write("</div>");
		} else {
			wh.write("<div id=\"").write(uuid).write("\"/>");
		}
	}

	
	public boolean isEnabled() {
		return enabled;
	}

	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
