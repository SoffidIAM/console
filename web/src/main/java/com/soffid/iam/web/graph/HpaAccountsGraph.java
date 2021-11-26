package com.soffid.iam.web.graph;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soffid.iam.api.Stats;
import com.soffid.iam.api.StatsSample;
import com.soffid.iam.service.ejb.StatsService;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebServlet(urlPatterns="/graph/hpa_accounts")
public class HpaAccountsGraph extends HttpServlet {
	@EJB StatsService statsService;
	Log log = LogFactory.getLog(getClass());
	
	protected HashMap<String, Object> getStats()
	{
		try {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, - 30);
			
			Stats stats = statsService.findStats("HPA_ACCOUNTS", c.getTime(), new Date(), 0);

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
			dataset1.put("label", "Privilegd accounts");
			dataset1.put("backgroundColor", new String[] {
					"rgb(99, 255, 132)",
					"rgb(255, 125, 86)",
					"rgb(235, 235, 18)"});
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
			title.put("text", "High-privilege accounts");
			title.put("display", true);

			HashMap<Object, Object> legend = new HashMap<>();
			plugins.put("legend", legend);
			legend.put("display", true);
			return r;
		} catch (InternalErrorException e) {
			log.warn("Error getting data", e);
			HashMap<String, Object> r = new HashMap<>();
			r.put("type", "doughnut");
			r.put("data", new HashMap<String,Object>());
			r.put("options", new HashMap<String,Object>());
			return r;
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HashMap<String, Object> r = getStats();
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("application/json");
		ObjectMapper m = new ObjectMapper();
		JsonFactory factory = m.getFactory();
		JsonGenerator generator = factory.createGenerator(out);
		generator.writeObject(r);
		generator.close();
		out.close();
		
	}


}
