package com.soffid.iam.web.syncserver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.zk.ui.UiException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Server;
import com.soffid.iam.service.ejb.SyncServerService;
import com.soffid.iam.web.component.Graphjs;
import com.soffid.iam.web.menu.YamlParser;

public class SyncServerPerformanceGraphjs extends Graphjs {
	Log log = LogFactory.getLog(getClass());
	Long serverId;
	
	@Override
	public void updateData()
	{
		try {
			SyncServerService syncServerService  = EJBLocator.getSyncServerService();
			DateFormat df = new SimpleDateFormat("HH:mm:ss");
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
					+ "  plugins:\n"
					+ "    title:\n"
					+ "      text: Syncserver performance (tasks per minute)\n"
					+ "      display: true\n"
					+ "    datalabels:\n"
					+ "      display: false\n"
					+ "refresh: 15000\n"
					+ "data:\n"
					+ "  datasets: xxx\n";
			try {
				Long id = serverId;
				for (Server server: syncServerService.getSyncServers()) {
					if (server.getId().equals(id)) {
						random = new Random(0);
	
						JSONObject base = (JSONObject) new YamlParser().parse(template);
						
						int step = 10;
						long now = System.currentTimeMillis();
						int seconds = 1200;
						Map<String, int[]> stats = EJBLocator.getSyncServerService().getStats(server.getUrl(), "tasks-success", seconds, step);
						Map<String, int[]> statsError = EJBLocator.getSyncServerService().getStats(server.getUrl(), "tasks-error", seconds, step);
	
						LinkedList<String> labelsSet = new LinkedList<>();
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
								final String label = df.format(then);
								if (!labelsSet.contains(label)) {
									labelsSet.add(label);
								}
								item.put("x", label);
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
								final String label = df.format(then);
								if (!labelsSet.contains(label)) {
									labelsSet.add(label);
								}
								item.put("x", label);
								item.put("y", -data[i]);
								if (- data[i] < min) min = -data[i];
								o.put(item);
							}
						}
	
						if (stats.keySet().size() + statsError.keySet().size() > 20)
							base.getJSONObject("options").getJSONObject("plugins").put("legend", new JSONObject("{\"display\":false}"));
						
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
						Collections.sort(labelsSet);
						JSONArray labels = new JSONArray();
						for (String label: labelsSet) labels.put(label);
						data.put("labels",  labels);
	
						data.put ("datasets", datasetArray);
						((JSONObject)base.query("/options/scales/yAxes/0/ticks")).put("min", min);
						setData(base.toString());
						return ;
					}
				}
				throw new ServletException("Wrong server "+id);
			} catch (Exception e) {
				log.warn("Error getting data", e);
				JSONObject r = new JSONObject();
				r.put("type", "bar");
				r.put("data", new HashMap<String,Object>());
				r.put("options", new HashMap<String,Object>());
				setData(r.toString());
			}
		} catch (Exception e1) {
			throw new UiException(e1);
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

	
	public Long getServerId() {
		return serverId;
	}

	
	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}
}
