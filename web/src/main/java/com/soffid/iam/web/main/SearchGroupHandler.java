package com.soffid.iam.web.main;

import java.io.IOException;
import java.util.List;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zk.ui.metainfo.ZScript;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.CustomObjectType;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.User;
import com.soffid.iam.web.menu.MenuOption;
import com.soffid.iam.web.menu.MenuParser;

public class SearchGroupHandler extends SearchHandler<Group> {

	public void startSearch(String term) throws Exception {
		super.startSearch(term);
		try {
			CustomObjectType cot = EJBLocator.getAdditionalDataService().findCustomObjectTypeByName(Group.class.getName());
			if (cot != null && cot.isTextIndex()) {
				StringBuffer sb = new StringBuffer();
				final String[] parts = term.split(" +");
				for (int i = 0; i < parts.length; i++) {
					if (parts[i].length() > 0) {
						if (sb.length() > 0)
							sb.append("AND ");
						if ( i == parts.length - 1)
							sb.append("(")
								.append(parts[i])
								.append("* OR ")
								.append(parts[i])
								.append("~ ) ");
						else
							sb.append(parts[i])
								.append("~ ");
					}
				}
				list = EJBLocator.getGroupService().findGroupByTextAsync(sb.toString());
			}
			else
				list = EJBLocator.getGroupService().findGroupByTextAsync(term);
		} catch (Exception e) {}
	}
	
		
	@Override
	protected void addElement(Group next) {
		if (parentDiv.getChildren().isEmpty())
		{
			Label l = new Label( Labels.getLabel("aplicacions.zul.Grup"));
			l.setSclass("search-header");
			parentDiv.appendChild(l);
		}
		String s = next.getName()+" - "+next.getDescription();
		Div d = new Div();
		d.appendChild(new Label(s));
		d.setSclass("search-option");
		d.setAttribute("url", "/resource/group/group.zul?name="+next.getName());
		d.addEventHandler("onClick",
			new EventHandler(ZScript.parseContent("ref:searchBox.onNavigate"), null));
		parentDiv.appendChild(d);
	}


	@Override
	protected void addMore() {
		Div d = new Div();
		d.setSclass("search-more");
		d.appendChild(new Label("..."));
		parentDiv.appendChild(d);
	}

}
