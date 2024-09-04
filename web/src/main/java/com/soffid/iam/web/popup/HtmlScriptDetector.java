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
				if (tag.equalsIgnoreCase("applet"))
					return false;
				if (tag.equalsIgnoreCase("iframe"))
					return false;
				if (tag.equalsIgnoreCase("object"))
					return false;
			}
			for (Attribute att: element.attributes()) {
				if (att.getKey().toLowerCase().startsWith("on"))
					return false;
				if (att.getKey().equalsIgnoreCase("href") && 
						att.getValue().toLowerCase().contains("script:"))
					return false;
			}
		}
		return true;
	}
}
