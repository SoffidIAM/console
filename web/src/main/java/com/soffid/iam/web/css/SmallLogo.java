package com.soffid.iam.web.css;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.soffid.iam.ServiceLocator;
import com.soffid.iam.utils.ConfigurationCache;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebServlet(name="small.logo", urlPatterns="/anonymous/small-logo.png")
public class SmallLogo extends HttpServlet {
	byte[] getOriginalImage () throws IOException {
		InputStream in = getServletContext().getResourceAsStream("/img/pixel.png");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (int i = in.read(); i >= 0; i = in.read())
		{
			out.write(i);
		}
		in.close();
		out.close();
		return out.toByteArray();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		byte[] img;
		try {
			img = ServiceLocator.instance().getConfigurationService().getBlob("logo2");
			if (img == null)
			{
				img = getOriginalImage();
			}
			resp.setStatus( HttpServletResponse.SC_OK);
			resp.setContentLength(img.length);
			resp.setContentType("image/png");
			ServletOutputStream outputStream = resp.getOutputStream();
			outputStream.write(img);
			outputStream.close();
		} catch (InternalErrorException e) {
			resp.setStatus( HttpServletResponse.SC_NO_CONTENT);
		}
	}

}
