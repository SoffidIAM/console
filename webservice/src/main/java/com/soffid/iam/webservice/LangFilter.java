package com.soffid.iam.webservice;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.soffid.iam.lang.MessageFactory;

@WebFilter(filterName = "Language", urlPatterns = { "/*" })
public class LangFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		Locale idioma = null;
		Enumeration<?> e = req.getLocales();
		while (e.hasMoreElements()) {
			Locale l = (Locale) e.nextElement();
			if (l.getLanguage().equalsIgnoreCase("ca")) {
				idioma = l;
				break;
			} else if (l.getLanguage().equalsIgnoreCase("es")) {
				idioma = l;
				break;
			} else if (l.getLanguage().equalsIgnoreCase("en")) {
				idioma = l;
				break;
			} else if (l.getLanguage().equalsIgnoreCase("nl")) {
				idioma = l;
				break;
			}
		}
		if (idioma == null) {
			if (System.getProperty("soffid.language.default") == null)
				idioma = new Locale("en", "US"); //$NON-NLS-1$ //$NON-NLS-2$
			else
				idioma = new Locale(System.getProperty("soffid.language.default"));

		}
		MessageFactory.setThreadLocale(idioma);
		chain.doFilter(req, res);
	}

	public void init(FilterConfig arg0) throws ServletException {
	}
}
