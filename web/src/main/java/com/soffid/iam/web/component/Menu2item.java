package com.soffid.iam.web.component;

import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.impl.LabelImageElement;

public class Menu2item extends Button {
	//-- super --//
	public String getOuterAttrs() {
		final StringBuffer sb =
			new StringBuffer(64).append(super.getOuterAttrs());

		appendAsapAttr(sb, Events.ON_CLICK);
		return sb.toString();
	}

	@Override
	public String getImgTag() {
		String image = getImage();
		String image2 = null;
		if (image != null && image.endsWith(".svg")) {
			image2 = image.substring(0, image.length() - 4)+"-white.svg";
		
			final StringBuffer sb = new StringBuffer(64)
				.append("<img class='menu2std' src=\"")
				.append(getDesktop().getExecution().encodeURL(image))
				.append("\" align=\"absmiddle\"/>")
				.append("<img class='menu2rev' src=\"")
				.append(getDesktop().getExecution().encodeURL(image2))
				.append("\" align=\"absmiddle\"/>");
	
			final String label = getLabel();
			if (label != null && label.length() > 0) sb.append(' ');
	
			return sb.toString(); //keep a space
		} else {
			return super.getImgTag();
		}
	}


}
