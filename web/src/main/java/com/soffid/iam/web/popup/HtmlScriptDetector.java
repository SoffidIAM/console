package com.soffid.iam.web.popup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;



public class HtmlScriptDetector {
	public boolean validate(String s) {
		Document doc = Jsoup.parse(s);
		for (Element element: doc.getAllElements()) {
			String tag = element.tagName();
			if (tag != null) {
				if (tag.equalsIgnoreCase("script"))
					return false;
			}
			for (Attribute att: element.attributes()) {
				if (att.getKey().toLowerCase().startsWith("on"))
					return false;
			}
		}
		return true;
	}
}
