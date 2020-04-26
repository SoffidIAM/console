package com.soffid.iam.web.css;

import javax.servlet.annotation.WebServlet;

@WebServlet(name="devices.css", urlPatterns="/css/devices.css")
public class DeviceCssServlet extends CSSTranslator {

	@Override
	protected String getResourceName() {
		return "/css/devices.css";
	}

}
