package com.soffid.iam.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

import org.json.JSONArray;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class IssueDataParser {
	private JSONArray actions = null;
	private JSONArray manualActions;
	private JSONArray events;
	private static IssueDataParser instance = null;
	
	public static IssueDataParser instance() throws IOException {
		if (instance == null) {
			JSONArray actions = loadResource("com/soffid/iam/service/impl/event-actions.yaml");
			JSONArray events = loadResource("com/soffid/iam/service/impl/events.yaml");
			JSONArray manualActions = loadResource("com/soffid/iam/service/impl/event-manual-actions.yaml");
			instance = new IssueDataParser(actions, events, manualActions);
		}
		return instance;
	}
	
	protected IssueDataParser(JSONArray actions, JSONArray events, JSONArray manualActions) {
		this.actions = actions;
		this.manualActions = manualActions;
		this.events = events;
	}
	
	private static JSONArray loadResource(String resource) throws IOException {
		JSONArray actions = new JSONArray();
		PathMatchingResourcePatternResolver rpr = new PathMatchingResourcePatternResolver(IssueDataParser.class.getClassLoader());
		Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(resource);
		while (resources.hasMoreElements())
		{
			URL url = resources.nextElement();
			InputStream in = url.openStream();
			String json = new Yaml2Json().transform(new InputStreamReader(in, StandardCharsets.UTF_8));
			in.close();
			JSONArray a2 = new JSONArray(json);
			for (int i = 0; i < a2.length(); i++)
				actions.put(a2.get(i));
		}
		return actions;
	}

	public JSONArray getActions() {
		return actions;
	}

	public JSONArray getManualActions() {
		return manualActions;
	}

	public JSONArray getIssues() {
		return events;
	}
}
