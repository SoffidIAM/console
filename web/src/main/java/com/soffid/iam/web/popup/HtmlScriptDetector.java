package com.soffid.iam.web.popup;

import java.util.regex.Pattern;

public class HtmlScriptDetector {
	public boolean validate(String s) {
		if (Pattern.compile("\\<\\s*script", Pattern.CASE_INSENSITIVE+Pattern.MULTILINE)
				.matcher(s)
				.find()) 
			return false;
		
		if (Pattern.compile("<[^>]*[\\s/]on", Pattern.CASE_INSENSITIVE+Pattern.MULTILINE)
				.matcher(s)
				.find())
			return false;
		
		if (Pattern.compile("[^a-zA-Z0-9]on[a-zA-Z0-9]*\\s*=", Pattern.CASE_INSENSITIVE+Pattern.MULTILINE)
				.matcher(s)
				.find())
			return false;

		return true;
	}
}
