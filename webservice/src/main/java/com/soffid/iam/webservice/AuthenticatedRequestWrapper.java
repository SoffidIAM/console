package com.soffid.iam.webservice;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class AuthenticatedRequestWrapper extends HttpServletRequestWrapper {

	private Principal principal;

	public AuthenticatedRequestWrapper(HttpServletRequest request, Principal p) {
		super(request);
		this.principal = p;
	}

	@Override
	public Principal getUserPrincipal() {
		return principal;
	}

}
