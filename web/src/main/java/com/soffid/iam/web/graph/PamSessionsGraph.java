package com.soffid.iam.web.graph;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.icu.text.SimpleDateFormat;
import com.soffid.iam.api.Stats;
import com.soffid.iam.api.StatsSample;
import com.soffid.iam.service.ejb.StatsService;

import es.caib.seycon.ng.exception.InternalErrorException;

@WebServlet(urlPatterns="/graph/pam_sessions")
public class PamSessionsGraph extends HttpServlet {
	@EJB StatsService statsService;
	Log log = LogFactory.getLog(getClass());
	
	protected HashMap<String, Object> getStats()
	{
		try {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -14);
			
			Stats stats = statsService.findStats("PAM_SESSIONS", c.getTime(), new Date(), 0);

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
					datasetdata.add(value.getAverage());
				}
				dataset.put("data", datasetdata);
				dataset.put("label", serie);
				dataset.put("lineTension", 0);
				dataset.put("borderColor", getColor(num++, null));
				dataset.put("backgroundColor", getColor(num++, "0.2"));
				datasets.add(dataset);
				labels.add(serie);
			}
			data.put("datasets", datasets);
			data.put("labels", stats.getTags());
			HashMap<String, Object> r = new HashMap<>();
			r.put("type", "line");
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
			
			HashMap<Object, Object> title = new HashMap<>();
			options.put("title", title);
			title.put("text", "Jump server sessions");
			title.put("display", true);

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

	static final String[] defaultColors = {
			"210, 86, 132",
			"190, 180, 25",
			"43, 205, 64",
			"235, 235, 18",
			"99, 255, 132",
			"255, 125, 8"};
			
	String getColor (int dataset, String alpha)
	{
		if (dataset < 0*defaultColors.length)
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
