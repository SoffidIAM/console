package com.soffid.iam.web.syncserver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.zk.ui.UiException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.web.component.Graphjs;
import com.soffid.iam.web.menu.YamlParser;

public class SyncServerQueueGraphjs extends Graphjs {
	Log log = LogFactory.getLog(getClass());
	
	@Override
	public void updateData()
	{
		try {
			DateFormat df = new SimpleDateFormat("HH:mm");
			String template = "" 
					+ "type: line\n" 
					+ "options:\n"
					+ "  scales:\n"
					+ "    y:\n"
					+ "      ticks:\n"
					+ "        min:0\n"
					+ "        suggestedMax: 100\n"
					+ "      stacked: true\n"
					+ "    x:\n"
	//				+ "      time:\n"
	//				+ "        unit: minute\n"
	//				+ "      type: time\n"
					+ "      stacked: true\n" 
					+ "  plugins:\n"
					+ "    title:\n"
					+ "      text: Tasks by server\n"
					+ "      display: true\n"
					+ "    legend:\n"
					+ "      display: false\n"
					+ "    datalabels:\n"
					+ "      display: false\n"
					+ "refresh: 60000\n"
					+ "data:\n"
					+ "  datasets: xx\n";
			try {
				random = new Random(25);
	
				JSONObject base = (JSONObject) new YamlParser().parse(template);
				
				Map<String, Vector<Object[]>> stats = EJBLocator.getSyncServerService().getPendingTasksStats();
				int num = 0;
				
				JSONArray datasets = new JSONArray();
				base.getJSONObject("data").put("datasets", datasets);
				base.put("refresh", 2000);
	
				LinkedList<String> labelsSet = new LinkedList<>();
				for ( String s: stats.keySet()) {
					JSONArray dataArray = new JSONArray();
					for (Object[] row: stats.get(s)) {
						Date data = (Date) row[0];
						Long n = (Long) row[1];
						JSONObject o = new JSONObject();
						final String label = df.format(data);
						o.put("x", label);
						o.put("y", n);
						dataArray.put(o);
						if (!labelsSet.contains(label)) {
							labelsSet.add(label);
						}
					}
					JSONObject dataset = new JSONObject();
					dataset.put("data", dataArray);
					dataset.put("fill", "origin");
					String color = getColor(num ++, "0.6");
					String colorNoAlpha = color.substring(0, color.lastIndexOf(',')) + ")";
					dataset.put("borderColor", colorNoAlpha);
					dataset.put("backgroundColor", color);
					dataset.put("label", s);
					dataset.put("cubicInterpolation", "monotone");
					
					datasets.put(dataset);
				}
				Collections.sort(labelsSet);
				JSONArray labels = new JSONArray();
				for (String label: labelsSet) labels.put(label);
				base.getJSONObject("data").put("labels",  labels);
				setData(base.toString());
				return;
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
}
