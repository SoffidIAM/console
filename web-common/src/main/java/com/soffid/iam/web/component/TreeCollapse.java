package com.soffid.iam.web.component;

import java.io.IOException;
import java.io.Writer;

import org.zkoss.xml.HTMLs;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;

public class TreeCollapse extends Div {
	boolean open = true;
	
	public TreeCollapse() {
		setSclass("tree-collapse open");
	}
	

	@Override
	public void redraw(Writer out) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append ("<div ");
		HTMLs.appendAttribute(sb, "id", getUuid());
		HTMLs.appendAttribute(sb, "class", getSclass());
		HTMLs.appendAttribute(sb, "style", getStyle());
		sb.append("><svg");
		HTMLs.appendAttribute(sb, "width", "16");
		HTMLs.appendAttribute(sb, "height", "16");
		HTMLs.appendAttribute(sb, "version", "1.1");
		sb = appendAsapAttr(sb, Events.ON_CLICK);
		sb.append("><g id=\"layer1\" transform=\"translate(0,-281)\">\n" + 
				"    <path style=\"opacity:1;fill:currentColor;fill-opacity:1;fill-rule:nonzero;stroke:currentColor;stroke-width:0.39421901;stroke-miterlimit:4;stroke-dasharray:none\" id=\"path815\" d=\"m 0.61876638,291.44886 a 7.7849007,7.7845583 0 0 1 4.96050982,-9.80929 7.7849007,7.7845583 0 0 1 9.8249028,4.93016 7.7849007,7.7845583 0 0 1 -4.900205,9.83956 7.7849007,7.7845583 0 0 1 -9.85498012,-4.86977 L 8.01896,289.03198 Z\">\n" + 
				"    </path><path style=\"fill:none;stroke:#ffffff;stroke-width:1.41703427;stroke-linecap:round;stroke-linejoin:round;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\" d=\"M 6.3314345,293.6398 11.302735,288.98191 6.3314345,284.2312\" id=\"path817\"></path>\n" + 
				"</g></svg></div>");
		
		out.write(sb.toString());
	}


	public boolean isOpen() {
		return open;
	}


	public void setOpen(boolean open) {
		this.open = open;
		setSclass(open? "tree-collapse open": "tree-collapse");
	}

}
