package com.soffid.iam.web.css;

import javax.servlet.annotation.WebServlet;

@WebServlet(name="localSEU.css", urlPatterns="/css/standard.css")
public class StandardCssServlet extends CSSTranslator {

	@Override
	protected String getResourceName() {
		return "/css/standard.css";
	}

}
