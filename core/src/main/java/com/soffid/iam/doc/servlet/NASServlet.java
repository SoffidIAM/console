package com.soffid.iam.doc.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.caib.seycon.util.Base64;

public class NASServlet extends HttpServlet {

	boolean validateRequest (HttpServletRequest req, HttpServletResponse resp) throws ServletException
	{
		String auth = req.getHeader("Authorization");
		if (auth == null)
		{
			resp.setHeader("WWW-Authenticate", "BASIC realm=\"Soffid document manager\"");
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		else
		{
			String user = System.getProperty("soffid.ui.docUsername").trim(); //$NON-NLS-1$
			String password = System.getProperty("soffid.ui.docUserPassword").trim(); //$NON-NLS-1$
			String s = user+":"+password;
			String encoded;
			try {
				encoded = "Basic "+Base64.encodeBytes(s.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				throw new ServletException(e);
			}
			if (auth.equals(encoded))
				return true;
			else
			{
				resp.setHeader("WWW-Authenticate", "BASIC realm=\"Soffid document manager\"");
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return false;
			}
		}
	}
	
	File getFile (HttpServletRequest req) throws ServletException, IOException
	{
		String base = System.getProperty("soffid.ui.docPath");
		if (base == null)
		{
			throw new ServletException("Missing configuration parameter soffid.ui.docLocalPath ");
		}
		File baseFile = new File (base);
		File f = new File (base, req.getPathInfo());
		if (  ! f.getCanonicalPath().startsWith(base))
		{
			throw new ServletException("Bad path "+req.getPathInfo());
		}
		else
			return f;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (validateRequest(req, resp))
		{
			File f = getFile(req);
			if (! f.canRead())
			{
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else {
				FileInputStream in = new FileInputStream(f);
				resp.setContentType("binary/octet-stream");
				resp.setContentLength((int) f.length());
				ServletOutputStream out = resp.getOutputStream();
				byte data [] = new byte[8192];
				int read;
				while ( (read = in.read(data)) >= 0)
				{
					out.write (data,0, read);
				}
				in.close();
				out.close ();
			}
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (validateRequest(req, resp))
		{
			File f = getFile(req);
			f.getParentFile().mkdirs();
			if (f.canRead())
			{
				resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
			} else {
				InputStream in = req.getInputStream();
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				OutputStream out = new FileOutputStream(f);
				byte data [] = new byte[8192];
				int read;
				while ( (read = in.read(data)) > 0)
				{
					out.write (data,0, read);
				}
				in.close();
				out.close ();
			}
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

}
