package com.soffid.iam.web.graph;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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

public class PamAccountsGraphjs extends Graphjs {
	Log log = LogFactory.getLog(getClass());
	
	@Override
	public void updateData()
	{
		try {
			StatsService statsService = EJBLocator.getStatsService();
			try {
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DAY_OF_MONTH, - 30);
				
				Stats stats = statsService.findStats("PAM_ACCOUNTS", c.getTime(), new Date(), 0);
	
				HashMap<String, Object> data = new HashMap<>();
				LinkedList<String> labels = new LinkedList<String>();
				LinkedList<HashMap<String, Object>> datasets = new LinkedList<HashMap<String, Object>>();
				HashMap<String, Object> dataset1 = new HashMap<String,Object>();
				LinkedList<Long> dataset1data = new LinkedList<>();
				
				List<String> k = new LinkedList<String>(stats.getSeries().keySet());
				Collections.sort(k);
				for ( String serie: k)
				{
					List<StatsSample> serieData = stats.getSeries().get(serie);
					dataset1data.add(serieData.get(serieData.size()-1).getMax());
					labels.add(serie);
				}
				dataset1.put("data", dataset1data);
				dataset1.put("backgroundColor", new String[] {
						"rgb(235, 235, 18)",
						"rgb(99, 255, 132)",
						"rgb(255, 125, 86)"});
				datasets.add(dataset1);
				data.put("datasets", datasets);
				data.put("labels", labels);
				HashMap<String, Object> r = new HashMap<>();
				r.put("type", "doughnut");
				r.put("data", data);
				HashMap<Object, Object> options = new HashMap<>();
				r.put("options", options);
				
				HashMap<Object, Object> plugins = new HashMap<>();
				options.put("plugins", plugins);
				
				HashMap<Object, Object> title = new HashMap<>();
				plugins.put("title", title);
				title.put("text", "Jump server enabled accounts");
				title.put("display", true);
	
				HashMap<Object, Object> legend = new HashMap<>();
				plugins.put("legend", legend);
				legend.put("display", true);
	
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
}
