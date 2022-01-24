package com.soffid.iam.web.graph;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.zkoss.zk.ui.UiException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Stats;
import com.soffid.iam.api.StatsSample;
import com.soffid.iam.service.ejb.StatsService;
import com.soffid.iam.web.component.Graphjs;

import es.caib.seycon.ng.exception.InternalErrorException;

public class PamUsersGraphjs extends Graphjs {
	Log log = LogFactory.getLog(getClass());
	
	@Override
	public void updateData()
	{
		try {
			StatsService statsService = EJBLocator.getStatsService();
			try {
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DAY_OF_MONTH, -365);
				
				Stats stats = statsService.findStats("PAM_USERS", c.getTime(), new Date(), 60*24);
	
				HashMap<String, Object> data = new HashMap<>();
				LinkedList<String> labels = new LinkedList<String>();
				LinkedList<HashMap<String, Object>> datasets = new LinkedList<HashMap<String, Object>>();
				
				List<String> k = new LinkedList<String>(stats.getSeries().keySet());
				Collections.sort(k);
				int num = 0;
				for ( String serie: k)
				{
					HashMap<String, Object> dataset = new HashMap<String,Object>();
					LinkedList<Long> datasetdata = new LinkedList<>();
					for ( StatsSample value: stats.getSeries().get(serie))
					{
						datasetdata.add(value.getMax());
					}
					dataset.put("data", datasetdata);
					dataset.put("label", serie);
					dataset.put("borderColor", getColor(num, null));
					dataset.put("backgroundColor", getColor(num++, null));
					datasets.add(dataset);
					labels.add(serie);
				}
				data.put("datasets", datasets);
				data.put("labels", stats.getTags());
				HashMap<String, Object> r = new HashMap<>();
				r.put("type", "bar");
				r.put("data", data);
				
				HashMap<String,Object> options = new HashMap<>();
				r.put("options", options);
				HashMap<String,Object> scales = new HashMap<>();
				options.put("scales", scales);
				HashMap<String, Object> axe = new HashMap<>();
				scales.put("xAxes", new Object[] {axe});
				axe.put("type", "time");
				HashMap<Object, Object> axeTime = new HashMap<>();
				axe.put("time", axeTime);
				axeTime.put("unit", "day");
	
				HashMap<String, Object> axe2 = new HashMap<>();
				scales.put("yAxes", new Object[] {axe2});
				HashMap<Object, Object> axe2ticks = new HashMap<>();
				axe2.put("ticks", axe2ticks);
				axe2ticks.put("min", 0);
				
				HashMap<Object, Object> plugins = new HashMap<>();
				options.put("plugins", plugins);
				
				HashMap<Object, Object> title = new HashMap<>();
				plugins.put("title", title);
				title.put("text", "Users with access to PAM jump servers");
				title.put("display", true);
	
				HashMap<Object, Object> legend = new HashMap<>();
				plugins.put("legend", legend);
				legend.put("display", false);
	
				setData(new JSONObject(r).toString());
			} catch (InternalErrorException e) {
				log.warn("Error getting data", e);
				HashMap<String, Object> r = new HashMap<>();
				r.put("type", "doughnut");
				r.put("data", new HashMap<String,Object>());
				r.put("options", new HashMap<String,Object>());
				setData(new JSONObject(r).toString());
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
			
	String getColor (int dataset, String alpha)
	{
		if (dataset < 0* defaultColors.length)
			return alpha==null? 
				"rgb("+defaultColors[dataset]+")" :
				"rgba("+defaultColors[dataset]+","+alpha+")";
		else
		{
			Random r = new Random();
			int i = r.nextInt(256);
			int j = r.nextInt(256);
			int k = r.nextInt(256);
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
