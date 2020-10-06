package com.soffid.iam.web.syncserver;

import java.io.IOException;
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
import com.soffid.iam.api.Server;
import com.soffid.iam.api.SyncServerInfo;
import com.soffid.iam.service.ejb.SyncServerService;
import com.soffid.iam.web.menu.YamlParser;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebServlet(urlPatterns="/graph/syncserver_performance/*")
public class SyncServerPerformanceGraph extends HttpServlet {
	@EJB SyncServerService syncServerService;
	Log log = LogFactory.getLog(getClass());
	
	protected JSONObject getStats(Long id) throws ServletException, IOException
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String template = "" 
				+ "type: line\n" 
				+ "options:\n"
				+ "  scales:\n"
				+ "    yAxes:\n"
				+ "    - ticks:\n"
				+ "        min:0\n"
				+ "    xAxes:\n"
				+ "    - time:\n"
				+ "        unit: minute\n"
				+ "      type: time\n" 
				+ "  title:\n"
				+ "    text: Syncserver performance (tasks per minute)\n"
				+ "    display: true\n"
				+ "  plugins:\n"
				+ "    datalabels:\n"
				+ "      display: false\n"
				+ "refresh: 15000\n"
				+ "data:\n"
				+ "  datasets: xxx\n";
		try {
			for (Server server: syncServerService.getSyncServers()) {
				if (server.getId().equals(id)) {
					random = new Random(0);

					JSONObject base = (JSONObject) new YamlParser().parse(template);
					
					int step = 10;
					long now = System.currentTimeMillis();
					int seconds = 1200;
					Map<String, int[]> stats = EJBLocator.getSyncServerService().getStats(server.getUrl(), "tasks-success", seconds, step);
					Map<String, int[]> statsError = EJBLocator.getSyncServerService().getStats(server.getUrl(), "tasks-error", seconds, step);
					
					Map<String, JSONArray> datasets = new HashMap<>();
					
					int min = 0;
					for (String s: stats.keySet())
					{
						JSONArray o = datasets.get(s);
						if (o == null) {
							o = new JSONArray();
							datasets.put(s,  o);
						}
						int[] data = stats.get(s);
						for ( int i = 0; i < data.length; i++) {
							Date then = new Date ( now - 1000L * ( seconds - (long) i * (long)step ));
							JSONObject item = new JSONObject();
							item.put("x", df.format(then));
							item.put("y", data[i]);
							o.put(item);
						}
					}
					for (String s: statsError.keySet())
					{
						JSONArray o = datasets.get("ERROR "+s);
						if (o == null) {
							o = new JSONArray();
							datasets.put("ERROR "+s,  o);
						}
						int[] data = statsError.get(s);
						for ( int i = 0; i < data.length; i++) {
							Date then = new Date ( now - 1000L * ( seconds - (long) i * (long)step ));
							JSONObject item = new JSONObject();
							item.put("x", df.format(then));
							item.put("y", -data[i]);
							if (- data[i] < min) min = -data[i];
							o.put(item);
						}
					}

					JSONArray datasetArray = new JSONArray();
					JSONArray labelsArray  = new JSONArray();
					List<String> keys = new LinkedList<>( datasets.keySet() );
					Collections.sort(keys);
					
					int num = 0;
					for ( String key: keys) {
						JSONObject o = new JSONObject();
						o.put("label", key);
						o.put("data", datasets.get(key));
						o.put("borderColor", getColor(num, null));
						o.put("backgroundColor", getColor(num++, null));
						JSONObject oo = new JSONObject();
						o.put("datalabels", oo);
						oo.put("display", false);
						datasetArray.put(o);
						num ++;
					}
					JSONObject data = base.getJSONObject("data");
					data.put ("datasets", datasetArray);
					((JSONObject)base.query("/options/scales/yAxes/0/ticks")).put("min", min);
					return base;
				}
			}
			throw new ServletException("Wrong server "+id);
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
