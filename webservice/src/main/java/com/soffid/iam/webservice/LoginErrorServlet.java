package com.soffid.iam.webservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soffid.iam.utils.ConfigurationCache;

public class LoginErrorServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String origin = ConfigurationCache.getProperty("soffid.scim.cors.origin");
		if (origin != null) {
			resp.addHeader(
                "Access-Control-Allow-Origin", origin);
			resp.addHeader(
                "Access-Control-Allow-Credentials", "true");
			resp.addHeader(
               "Access-Control-Allow-Headers",
               "origin, content-type, accept, authorization");
    		String methods = ConfigurationCache.getProperty("soffid.scim.cors.methods");
    		resp.addHeader(
                "Access-Control-Allow-Methods", 
        		methods == null ? "GET, OPTIONS, HEAD": methods);
		}
		resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		resp.setContentType("text/plain");
		ServletOutputStream out = resp.getOutputStream();
		out.println("Unauthorized");
		out.close();
	}

}
