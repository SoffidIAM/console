package com.soffid.iam.web;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.PamSession;
import com.soffid.iam.service.ejb.PamSessionService;

@WebServlet("/pam/Subtitles/*")
public class PamKeyboardServlet extends HttpServlet {
	PamSession getSessionInfo (HttpServletRequest req, String id)
	{
		PamSession r = (PamSession) req.getSession().getAttribute("pam-session-"+id);
		return r;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String pathInfo = req.getPathInfo();
			int i = pathInfo.lastIndexOf('/');
			if (i < 0)
			{
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			long frame = Long.decode(pathInfo.substring(i+1));
			String path = pathInfo.substring(1, i);

			PamSession session = getSessionInfo(req, path);
			if (session == null)
			{
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			boolean found = false;
			PamSessionService ejb = EJBLocator.getPamSessionService();
			KeystrokesToVtt out = new KeystrokesToVtt(resp.getOutputStream(), frame);
			ejb.generateKeystrokes(session, out);
			resp.setContentType("text/vtt; charset=UTF-8");
			out.close();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	
}
