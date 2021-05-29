package com.soffid.iam.web.agent;

import java.util.Stack;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import es.caib.zkib.component.DataTree2;

public class LogParser {
	public void parseLog (String txt, DataTree2 tree) {
		JSONObject logRoot = new JSONObject();
		JSONArray children = new JSONArray();
		logRoot.put("children", children);
		if (txt != null) {
			int i = 0;
			int j;
			Stack<JSONObject> stack = new Stack<JSONObject>();
			stack.push(logRoot);
			do {
				JSONObject log = new JSONObject();
				log.put("type", "log");
				
				j = txt.indexOf("\n", i);
				String s;
				if (j < 0) {
					s = txt.substring(i);
				} else {
					s =  txt.substring(i, j);
					i = j+1;
				}
				int n = s.indexOf(" INFO BEGIN ");
				if (n > 0 && !hasChars(s.substring(0, n))) {
					children.put(log);
					children = new JSONArray();
					log.put("header", s.substring(n+12));
					log.put("children", children);
					log.put("type", "header");
					log.put("collapsed", true);
					stack.push(log);
				} else {
					if (s.endsWith(" INFO END") &&
							!hasChars(s.substring(0, s.length()-8)) && 
							stack.size() > 1 ){
						stack.pop();
						JSONObject parent = stack.peek();
						children = parent.getJSONArray("children");
					} else if (!s.trim().isEmpty()) {
						children.put(log);
						log.put("log", StringEscapeUtils.escapeHtml(s));
						if (s.contains(" WARN "))
						{
							log.put("style", "style='color: red'");
							for (JSONObject parents: stack)
								parents.put("collapsed", false);
						}
						else
							log.put("style", "");
					}
				}
			} while (j >= 0);
			
		}
		tree.setData(logRoot);
	}

	private boolean hasChars(String substring) {
		int letters = 0;
		for (int i = 0; i < substring.length(); i++)
			if (Character.isAlphabetic(substring.charAt(i)))
			{
				if (substring.charAt(i) == 'P' ||
						substring.charAt(i) == 'A' ||
						substring.charAt(i) == 'M')
				{
					letters ++;
					if (letters > 2)
						return true;
				}
			}
		return false;
	}

}
