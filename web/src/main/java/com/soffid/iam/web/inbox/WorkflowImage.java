package com.soffid.iam.web.inbox;

import java.io.IOException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zkoss.web.servlet.dsp.action.Out;

import com.soffid.iam.EJBLocator;

import es.caib.seycon.ng.exception.InternalErrorException;


@WebServlet(name = "Workflow image", urlPatterns = {"/img/wf/*"})
public class WorkflowImage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String defaultImage = "../menu/start-wf.svg";
		String wfid = req.getPathInfo();
		if (wfid == null) {
			resp.sendRedirect(defaultImage);
		} else {
			wfid = wfid.substring(1);
			byte[] img;
			try {
				img = EJBLocator.getBpmEngine().getProcessDefinitionIcon (Long.decode(wfid));
				if (img == null)
				{
					resp.sendRedirect(defaultImage);
				} else {
					if (img[0] == '<' && img[1] == '?')
						resp.setContentType("image/svg+xml");
					else if (img[0] == 0x77 && img[1] == 0xd8 && img[2] == 0xff)
						resp.setContentType("image/jpeg");
					else if (img[0] == 0x89 && img[1] == 0x50 && img[2] == 0x4e)
						resp.setContentType("image/png");
					else 
						resp.setContentType("image/gif");
					resp.setIntHeader("Content-Lenght", img.length);
					ServletOutputStream out = resp.getOutputStream();
					out.write(img);
					out.close();
				}
			} catch (Exception e) {
				resp.sendRedirect(defaultImage);
			}
			
		}
	}
	
}
