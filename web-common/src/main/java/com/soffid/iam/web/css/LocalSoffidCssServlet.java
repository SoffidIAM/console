package com.soffid.iam.web.css;

import javax.servlet.annotation.WebServlet;

@WebServlet(name="localSEU.css", urlPatterns="/css/localSEU.css")
public class LocalSoffidCssServlet extends CSSTranslator {

	@Override
	protected String getResourceName() {
		return "/css/localSEU.css";
	}

}
