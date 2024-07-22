package com.soffid.iam.webservice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Base64;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.authenticator.BasicAuthenticator.BasicCredentials;
import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.buf.ByteChunk;
import org.apache.tomcat.util.buf.MessageBytes;
import org.jfree.util.Log;

import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.tomcat.LoginService;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

@WebFilter(filterName = "securityFilter", urlPatterns = {"/*"})
public class SecurityFilter extends TenantFilter {
	static org.apache.commons.logging.Log log = LogFactory.getLog(SecurityFilter.class);
	static LRUMap map = new LRUMap(1500);
	private LoginService svc;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		try {
			svc = (LoginService) new InitialContext().lookup("openejb:/local/soffid.ejb.com.soffid.iam.tomcat.service.LoginService");
		} catch (NamingException e) {
			throw new ServletException("Cannot locate LoginService");
		}

	}

	public void nextStep(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		
        String authorization =
            httpReq.getHeader("authorization");

        if (authorization != null) {
        	try {
	        	if (authorization.toLowerCase().startsWith("bearer "))
	        		checkBearerAuthorization(httpReq, httpResp, chain, authorization);
	        	else if (authorization.toLowerCase().startsWith("basic "))
	        		checkBasicAuthorization(httpReq, httpResp, chain, authorization);
	        	else
	            	addAuthHeader(httpResp);
        	} catch (Exception e) {
        		log.warn("Error authenticating user", e);
            	addAuthHeader(httpResp);
        	}
        }
        else
        	addAuthHeader(httpResp);
	}

	private boolean checkForCachedAuthentication(HttpServletRequest httpReq, 
			HttpServletResponse httpResp, 
			FilterChain chain, 
			String authorization) throws IOException, ServletException {
		SoffidPrincipal p = (SoffidPrincipal) map.get(authorization);
		if (p == null) return false;
		if (svc.hasPasswordChange(p))
			return false;
		proceed(httpReq, httpResp, chain, p);
		return true;
	}

	protected void addAuthHeader(HttpServletResponse httpResp) {
		String cfg = ConfigurationCache.getProperty("soffid.webservice.auth.password");
		if ("false".equals(cfg))
		{
			httpResp.setStatus(HttpServletResponse.SC_FORBIDDEN);
		} else {
			httpResp.setHeader("WWW-Authenticate",
					"Basic realm=\"Soffid\", charset=UTF_8");
			httpResp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	private void checkBasicAuthorization(HttpServletRequest httpReq, HttpServletResponse httpResp, FilterChain chain,
			String auth) throws IOException, ServletException, NamingException {
		String cfg = ConfigurationCache.getProperty("soffid.webservice.auth.password");
		if ("false".equals(cfg))
		{
			httpResp.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
    	if (checkForCachedAuthentication(httpReq, httpResp, chain, auth)) {
    		return;
    	}
		String s = new String(
				Base64.getDecoder().decode(
					auth.substring(6).getBytes(StandardCharsets.UTF_8)),
					StandardCharsets.UTF_8);
		int i = s.indexOf(":");
		if (i >= 0) {
			SoffidPrincipal p = (SoffidPrincipal) svc.authenticate (s.substring(0, i), s.substring(i+1));
			if (p != null && !p.hasRole("PASSWORD:EXPIRED")) {
				registerCache ( auth, p);
				proceed(httpReq, httpResp, chain, p);
			} else {
		        addAuthHeader(httpResp);
			}
		}
		else
			addAuthHeader(httpResp);
	}

	private void checkBearerAuthorization(HttpServletRequest httpReq, HttpServletResponse httpResp, FilterChain chain,
			String auth) throws IOException, ServletException, NamingException {
		String cfg = ConfigurationCache.getProperty("soffid.webservice.auth.jwt");
		if (!"true".equals(cfg))
		{
			httpResp.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
    	if (checkForCachedAuthentication(httpReq, httpResp, chain, auth)) {
    		proceed(httpReq, httpResp, chain, null);
    		return;
    	}

		String s = auth.substring(7);
		Principal p = svc.authenticateJWT(s);
		if (p != null) {
			registerCache ( auth, p);
			proceed(httpReq, httpResp, chain, p);
		} else {
			httpResp.setHeader("WWW-Authenticate",
	        		"Bearer realm=\"Soffid\", charset=UTF_8");
			httpResp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	private void registerCache(String auth, Principal p) {
		map.put(auth, p);
	}

	protected void proceed(HttpServletRequest httpReq, HttpServletResponse httpResp, FilterChain chain, Principal p)
			throws IOException, ServletException {
		Security.nestedLogin((SoffidPrincipal) p);
		try {
			
			HttpServletRequestWrapper req = new AuthenticatedRequestWrapper(httpReq, p);
	        addCorsHeader(req, httpResp, chain);

			chain.doFilter(req, httpResp);
		} finally {
			Security.nestedLogoff();
		}
	}

	protected void addCorsHeader(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String origin = ConfigurationCache.getProperty("soffid.scim.cors.origin");
		if (origin != null) {
			HttpServletResponse resp = (HttpServletResponse) response;
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
	}

	public void destroy() {
	}

}
