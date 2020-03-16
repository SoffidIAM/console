package com.soffid.iam.web.component;

import org.zkoss.zul.Div;

public class Menu2 extends Div {
	String image = "~./img/Hamburger_icon.svg";
	public Menu2() {
		setSclass("menu2");
	}

	public String getImgTag() {
		if (image == null)
			return null;

		final StringBuffer sb = new StringBuffer(64)
			.append("<img src=\"")
			.append(getDesktop().getExecution().encodeURL(image))
			.append("\" align=\"absmiddle\"/>");

		return sb.toString(); //keep a space
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
