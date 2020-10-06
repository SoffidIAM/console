package com.soffid.iam.web.syncserver;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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
import org.zkoss.zul.Listitem;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AgentStatusInfo;
import com.soffid.iam.api.Server;
import com.soffid.iam.api.SyncServerInfo;
import com.soffid.iam.service.ejb.SyncServerService;
import com.soffid.iam.web.menu.YamlParser;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebServlet(urlPatterns="/graph/tasks")
public class SyncServerTasksGraph extends HttpServlet {
	@EJB SyncServerService syncServerService;
	Log log = LogFactory.getLog(getClass());
	
	protected JSONObject getStats() throws ServletException, IOException
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String template = "" 
				+ "type: horizontalBar\n" 
				+ "options:\n"
				+ "  scales:\n"
				+ "    xAxes:\n"
				+ "    - stacked: true\n"
				+ "      ticks:\n"
				+ "        suggestedMax: 100\n"
				+ "        min: 0\n"
				+ "    yAxes:\n"
				+ "    - display: true\n"
				+ "      stacked: true\n"
				+ "  title:\n"
				+ "    text: Active tasks\n"
				+ "    display: true\n"
				+ "  plugins:\n"
				+ "    datalabels:\n"
				+ "      display: false\n"
				+ "  legend: false\n"
				+ "refresh: 10000\n"
				+ "data:\n"
				+ "  datasets:\n"
				+ "  - label: tasks";
		try {
			JSONObject base = (JSONObject) new YamlParser().parse(template);
			random = new Random(10);
			Collection<AgentStatusInfo> status = EJBLocator.getSyncServerService().getServerAgentStatus();

			JSONArray data = new JSONArray();
			JSONArray labels = new JSONArray();
			JSONArray colors = new JSONArray();

			int i = 0;
			for (AgentStatusInfo agent: status ) {
				String c = getColor(i, null);
				colors.put(c);
				labels.put(agent.getAgentName());
				data.put(agent.getPendingTasks());
			}
			((JSONObject)base.query("/data")).put("labels", labels);
			((JSONObject)base.query("/data/datasets/0")).put("data", data);
			((JSONObject)base.query("/data/datasets/0")).put("backgroundColor", colors);
			((JSONObject)base.query("/data/datasets/0")).put("borderColor", colors);
			return base;
		} catch (Exception e) {
			log.warn("Error getting data", e);
			JSONObject r = new JSONObject();
			r.put("type", "bar");
			r.put("data", new HashMap<String,Object>());
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
		JSONObject r = getStats( );
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("application/json");
		out.write( r.toString().getBytes("UTF-8") );
		out.close();
		
	}


	static final String[] defaultColors = {
			"210, 86, 132",
			"190, 180, 25",
			"43, 205, 64",
			"235, 235, 18",
			"99, 255, 132",
			"255, 125, 8"};

	Random random = new Random(0);

	String getColor (int dataset, String alpha)
	{
		if (dataset < 0 * defaultColors.length)
			return alpha==null? 
				"rgb("+defaultColors[dataset]+")" :
				"rgba("+defaultColors[dataset]+","+alpha+")";
		else
		{
			int i = random.nextInt(256);
			int j = random.nextInt(256);
			int k = random.nextInt(256);
			int t = 0;
				
			while (i + j + k < 512)
			{
				t ++;
				if ( t % 3 == 0 && i < 256) i++;
				if ( t % 3 == 1 && j < 256) j++;
				if ( t % 3 == 2 && k < 256) k++;
			}

			while (i + j + k > 512)
			{
				t ++;
				if ( t % 3 == 0 && i > 0) i--;
				if ( t % 3 == 1 && j > 0) j--;
				if ( t % 3 == 2 && k > 0) k--;
			}

			return alpha==null? 
					"rgb("+i +", "+ j +", "+ k +")" :
					"rgba("+i +", "+ j +", "+ k +","+alpha+")";
		}
	}
}
