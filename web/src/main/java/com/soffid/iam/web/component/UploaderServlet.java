package com.soffid.iam.web.component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.util.media.AMedia;

public class UploaderServlet extends HttpServlet {
	Log log = LogFactory.getLog(getClass());
	FileCleaningTracker tracker  = new FileCleaningTracker();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		final File f = File.createTempFile("upload", "");
		final File f2 = File.createTempFile("upload", "data");
		HttpSession session = request.getSession(false);
		if (session == null)
		{
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		else
		{
			try {
				ServletInputStream in = request.getInputStream();
				FileOutputStream out = new FileOutputStream(f);
				String boundary = null;
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				for (int read = in.read(); read >= 0; read = in.read()) {
					if (boundary == null) {
						if (read == 10) boundary = buffer.toString("UTF-8").substring(2);
						else buffer.write(read);
					}
					out.write(read);
				}
				buffer.close();
				out.close();
				in.close();
	
				File target = new File(f.getPath()+".dir");
				DiskFileItemFactory dif = new DiskFileItemFactory(256000, target);
				dif.setFileCleaningTracker(tracker);
				ServletFileUpload uf = new ServletFileUpload( dif );
				InputStream fileIn = null;
				String uuid = null;
				String name = null;
				String ctype = null;
				RequestContext ctx = new UploadRequestContext(request, boundary, f);
				for (FileItemIterator it = uf.getItemIterator(ctx); it.hasNext();) 
				{
					FileItemStream file = it.next();
					String attName = file.getFieldName();
					if (attName.equals("uuid"))
						uuid = readFile(file);
					if (attName.equals("name"))
						name = readFile(file);
					if (attName.equals("file"))
					{
						byte buf2[] = new byte[64000];
						name = file.getName();
						ctype = file.getContentType();
						InputStream in2 = file.openStream();
						FileOutputStream out2 = new FileOutputStream(f2);
						for (int read = in2.read(buf2); read > 0; read = in2.read(buf2)) {
							out2.write(buf2, 0, read);
						}
						in2.close();
						out2.close();
					}
					
				}
				AMedia media = new AMedia(name, null, ctype, f2, true);
				tracker.track(f2, media);
				Uploader.register(session, uuid, media);
				resp.sendError(HttpServletResponse.SC_OK);
			} catch (Exception e) {
				log.warn("Error putting file", e);
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} finally {
				f.delete();
			}
		}
	}
	
	private String readFile(FileItemStream file) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		InputStream in = file.openStream();
		for (int read = in.read(); read >= 0; read = in.read()) {
			out.write(read);
		}
		in.close();
		out.close();
		return out.toString("UTF-8");
	}
}
