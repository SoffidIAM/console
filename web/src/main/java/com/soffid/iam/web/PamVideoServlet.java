package com.soffid.iam.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.PamSession;
import com.soffid.iam.service.ejb.PamSessionService;

import es.caib.seycon.util.Base64;

@WebServlet("/pam/Video/*")
public class PamVideoServlet extends HttpServlet {
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
			
			resp.addHeader("Accept-Ranges", "bytes");
			boolean found = false;
			PamSessionService ejb = EJBLocator.getPamSessionService();
			long size = ejb.getVideoSize(session, frame);
			long start = 0;
			long end = size;

			String range = req.getHeader("Range");
			List<long[]> ranges = parseRanges (range, size);
			
			if (ranges == null)
			{
				resp.setStatus(HttpServletResponse.SC_OK);
				resp.setContentType("video/ogg");
				resp.setContentLength((int)size);
				ServletOutputStream out = resp.getOutputStream();
				ejb.generateVideo(session, frame, out, 0, size-1);
				out.close();
			}
			else if (ranges.size() == 1)
			{
				long[] r = ranges.get(0);
				resp.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
				resp.setContentType("video/ogg");
				resp.setContentLength((int) (r[1]-r[0]+1));
				resp.addHeader("Content-Range", "bytes "+r[0]+"-"+r[1]+"/"+size);
				ServletOutputStream out = resp.getOutputStream();
				ejb.generateVideo(session, frame, out, r[0], r[1]);
				out.close();
			}
			else
			{
				final String separator = "soffidseparator";
				long contentSize = calculateSizeMultiByteRange(ranges, size, separator);
				resp.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
				resp.setContentType("multipart/byteranges; boundary="+separator);
				resp.setContentLength((int) contentSize);
				ServletOutputStream out = resp.getOutputStream();
				long pos = 0;
				for (long[] r: ranges)
				{
					String header = generatePartHedaer(r, size, separator);
					out.write(header.getBytes("UTF-8"));
					ejb.generateVideo(session, frame, out, r[0], r[1]);
				}
				out.write(("--"+separator+"--\n").getBytes("UTF-8"));
				out.close();
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	
	private long calculateSizeMultiByteRange(List<long[]> ranges, long size, String separator) {
		long l = 1;
		for (long[] range: ranges)
		{
			String partHeader = generatePartHedaer (range, size, separator);
			l += partHeader.length();
			l = l + range[1] - range[0] + 1;
		}
		l += separator.length()+5;
		return l;
	}


	private String generatePartHedaer(long[] range, long size, String separator) {
		StringBuffer sb = new StringBuffer();
		sb.append("--")
			.append(separator)
			.append("\nContent-Type: video/ogg\nContent-Range: bytes")
			.append(range[0])
			.append("-")
			.append(range[1])
			.append("/")
			.append(size)
			.append("\n\n");
		return sb.toString();
	}


	private List<long[]> parseRanges(String range, long size) {
		if ( range == null)
		{
			return null;
		}
		if (!range.startsWith("bytes="))
			return null;
		String tail = range.substring(6);
		List<long[]> l = new LinkedList<long[]>();
		for (String subrange: tail.split(","))
		{
			subrange = subrange.trim();
			if ( !subrange.isEmpty())
			{
				int i = subrange.indexOf("-");
				if (i > 0)
				{
					long first = 
						i == 0 ? 
							0L : 
							Long.parseLong(subrange.substring(0,i).trim());
					if (first >= size) first = size - 1;
					long last = i == subrange.length() - 1 ?
							size - 1:
							Long.parseLong(subrange.substring(i+1).trim()); 
					if (last >= size) last = size - 1;
					l.add(new long[] {first, last});
				}
			}
		}
		Collections.sort(l, new Comparator<long[]>() {
			@Override
			public int compare(long[] o1, long[] o2) {
				return Long.compare(o1[0], o2[0]);
			}
		});
		
		Iterator<long[]> iterator = l.iterator();
		long[] last = iterator.next();
		while ( iterator.hasNext())
		{
			long[] next = iterator.next();
			if (next[0] <= last[1])
			{
				last[1] = next[1];
				iterator.remove();
			}
			else
				last = next;
		}
		return l;
	}

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String pathInfo = req.getPathInfo();
			int i = pathInfo.lastIndexOf('/');
			if (i < 0)
			{
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			long frame = Long.decode(pathInfo.substring(i+1));
			String path = pathInfo.substring(0, i);
			PamSession session = getSessionInfo(req, path);
			if (session == null)
			{
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return;
			}

			PamSessionService ejb = EJBLocator.getPamSessionService();
			long size = ejb.getVideoSize(session, frame);

			resp.setContentType("video/ogg");
			resp.addHeader("Accept-Ranges", "bytes");
			resp.setContentLength((int) size);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
}
