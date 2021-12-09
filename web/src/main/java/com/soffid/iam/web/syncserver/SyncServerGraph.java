package com.soffid.iam.web.syncserver;

import java.io.IOException;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import com.soffid.iam.api.Server;
import com.soffid.iam.api.SyncServerInfo;
import com.soffid.iam.service.ejb.SyncServerService;
import com.soffid.iam.web.menu.YamlParser;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebServlet(urlPatterns="/graph/syncserver_status/*")
public class SyncServerGraph extends HttpServlet {
	@EJB SyncServerService syncServerService;
	Log log = LogFactory.getLog(getClass());
	
	protected JSONObject getStats(Long id) throws ServletException, IOException
	{
		String template = "" 
				+ "type: doughnut\n" 
				+ "options:\n" 
				+ "  plugins:\n"
				+ "    title:\n"
				+ "      text: Agents status\n"
				+ "      display: true\n"
				+ "    legend:\n"
				+ "      display: false\n"
				+ "      align: start\n"
				+ "      position: bottom\n"
				+ "      labels:\n"
				+ "        boxWidth: 12\n"
				+ "    datalabels:\n"
				+ "      display: false\n"
				+ "      backgroundColor: rgba(128,128,128,0.7)\n"
				+ "      align: start\n"
				+ "      anchor: start\n"
				+ "      offset: -40\n"
				+ "      borderRadius: 4\n"
				+ "      borderWidth: 1\n"
				+ "      color: white\n"
				+ "  animation:\n"
				+ "    animateRotate: false\n"
				+ "    animateScale: true\n"
				+ "  elements:\n"
				+ "    center: \n"
				+ "      text: OK\n"
				+ "      color: rgb(99, 255, 99)\n"
				+ "      maxFontSize: 20\n"
				+ "      minFontSize: 15\n"
				+ "      sizePadding: 20\n"
				+ "refresh: 10000\n"
				+ "data:\n"
				+ "  datasets:\n"
				+ "  - data: xxx \n"
				+ "    borderColor: rgb(128,128,128)\n"
				+ "    borderWidth: 2\n"
				+ "    backgroundColor:\n"
				+ "    - rgb(199, 255, 199)\n"
				+ "    - rgb(255, 25, 18)\n"
				+ "  labels:\n"
				+ "  - Connected\n"
				+ "  - Disconnected\n";  
		try {
			for (Server server: syncServerService.getSyncServerInstances()) {
				if (server.getId().equals(id)) {
					JSONObject base = (JSONObject) new YamlParser().parse(template);
					SyncServerInfo status = syncServerService.getSyncServerInfo(server.getUrl());
					JSONArray data = new JSONArray();
					data.put (status.getConnectedAgents());
					data.put (status.getNumberOfAgents() -
							status.getConnectedAgents());
					((JSONObject) base.query("/data/datasets/0")).put("data",  data);
					if ("OFFLINE".equals(status.getStatus())) {
						JSONObject center = base.getJSONObject("options").getJSONObject("elements").getJSONObject("center");
						center.put("text", "Off");
						center.put("color", "rgb(0,0,0)");
					} 
					else if (! status.getConnectedAgents().equals(status.getNumberOfAgents())) {
						JSONObject center = base.getJSONObject("options").getJSONObject("elements").getJSONObject("center");
						center.put("text", status.getNumberOfAgents().intValue() - status.getConnectedAgents().intValue());
						center.put("color", "rgb(255, 25, 18)");
					}
//					base.getJSONObject("options").put("circumference", Math.PI/2);
					return base;
				}
			}
			throw new ServletException("Wrong server "+id);
		} catch (Exception e) {
			log.warn("Error getting data", e);
			JSONObject r = new JSONObject();
			r.put("type", "doughnut");
			r.put("data", new HashMap<String,Object>());
			r.put("refresh", 5000);
			r.put("options", new HashMap<String,Object>());
			return r;
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String referer = req.getHeader("Referer");
		if (referer == null || ! referer.contains("graph.html"))
		{
			resp.sendRedirect("/");
			return;
		}
		if (req.getPathInfo() == null)
			resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Missing server id");
		else {
			JSONObject r = getStats( Long.decode( req.getPathInfo().substring(1)) );
			ServletOutputStream out = resp.getOutputStream();
			resp.setContentType("application/json");
			out.write( r.toString().getBytes("UTF-8") );
			out.close();
		}
		
	}


}
