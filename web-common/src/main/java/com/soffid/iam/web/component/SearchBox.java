package com.soffid.iam.web.component;

import java.util.Set;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.HtmlBasedComponent;

import com.soffid.iam.web.SearchAttributeDefinition;
import com.soffid.iam.web.SearchDictionary;

public class SearchBox extends HtmlBasedComponent {
	SearchDictionary dictionary;

	public SearchBox() {
		setSclass("search-box");
	}
	
	public void setDefaultAttributes (String s)
	{
		for (String att: s.split("[ ,]+"))
		{
			addAttribute(att);
		}
	}

	private void addAttribute(String att) {
		for (SearchAttributeDefinition def: dictionary.getAttributes())
		{
			if (def.getName().equals(att))
				addAttribute (def);
		}
	}

	public SearchDictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(SearchDictionary dictionary) {
		this.dictionary = dictionary;
	}

	private void addAttribute(SearchAttributeDefinition def) {
		AttributeSearchBox asb = new AttributeSearchBox();
		asb.setAttributeDef(def);
		appendChild(asb);
	}


	public String getQueryString ()
	{
		StringBuffer qs = new StringBuffer();
		for (Object child: getChildren())
		{
			if (child instanceof AttributeSearchBox)
			{
				String q = ((AttributeSearchBox) child).getQueryExpression();
				if ( q != null && ! q.isEmpty())
				{
					if (qs.length() > 0)
						qs.append(" and ");
					qs.append("(")
						.append(q)
						.append(")");
				}
			}
		}
		return qs.toString();
	}
}
