package com.soffid.iam.service.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.soffid.iam.model.JumpServerEntity;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;

public class JumpServerMonitor extends Thread {
	static Hashtable<String,JumpServerMonitor> monitors = new Hashtable<>();
	
	public static JumpServerMonitor getMonitor(String group) throws InternalErrorException {
		String tag = Security.getCurrentTenantName()+"\n"+group;
		JumpServerMonitor monitor = monitors.get(tag);
		if (monitor == null) {
			monitor = new JumpServerMonitor();
			monitor.start();
			monitors.put(tag, monitor);
		}
		return monitor;
	}
	
	List<Status> status;
	Log log = LogFactory.getLog(getClass());
	
	@Override 
	public void run() {
		while (true) {
			if (status != null) {
				LinkedList<Status> l = new LinkedList<>(status);
				for (Status s: l) {
					updateStatus(s);
				}
			}
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
			}
		}
	}

	private void updateStatus(Status s) {
		try {
			URL url2 = new URL(s.url);
			String base = url2.getProtocol()+"://"+url2.getHost()+
					(url2.getPort() == -1 ? "": ":"+url2.getPort());
			Response response = 
					WebClient
					.create(base+"/launch/status")
					.accept(MediaType.APPLICATION_JSON)
					.get();
			if (response.getStatus() != 200)
				log.info("Error connecting to "+base+": "
						+ "HTTP/"+response.getStatusInfo().getStatusCode()+" "+response.getStatusInfo().getReasonPhrase());
			JSONObject result  = new JSONObject( new JSONTokener( response.readEntity( String.class   ) ) );
			s.number = result.optInt("sessions", 999);
			s.up = true;
		} catch (Exception e) {
			s.up = false;
		}
	}

	public void configure (Collection<JumpServerEntity> entities) {
		List<Status> status = null;
		if (this.status != null) status = new LinkedList<Status>(this.status);
		else status = new LinkedList<Status>();
		
		HashSet<String> urls = new HashSet<String>();
		for (JumpServerEntity entity: entities) {
			urls.add(entity.getUrl());
		}
		
		for (Iterator<Status> it = status.iterator(); it.hasNext();) {
			Status s = it.next();
			if (! urls.contains(s.url)) {
				s.up = false;
				it.remove();
			}
			else
			{
				urls.remove(s.url);
			}
		}
		
		for (String url: urls) {
			Status s = new Status();
			s.url = url;
			s.up = false;
			s.number = 0;
			updateStatus(s);
			status.add(s);
		}
		
		this.status = status;
	}
	
	public String select() {
		if (status == null)
			return null;
		Status best = null;
		for (Status s: status) {
			if (s.up) {
				if (best == null || best.number > s.number)
					best = s;
			}
		}
		
		if (best == null)
			return null;
		else {
			best.number ++;
			return best.url;
		}
	}
}

class Status {
	String url;
	boolean up;
	int number;
}