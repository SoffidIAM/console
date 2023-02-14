package com.soffid.iam.web.css;

import javax.servlet.annotation.WebServlet;

@WebServlet(name="login.css", urlPatterns="/css/login.css")
public class LoginCssServlet extends CSSTranslator {

	@Override
	protected String getResourceName() {
		return "/css/login.css";
	}

}
