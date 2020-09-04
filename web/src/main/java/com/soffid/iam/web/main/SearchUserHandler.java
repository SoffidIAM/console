package com.soffid.iam.web.main;

import java.io.IOException;
import java.util.List;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zk.ui.metainfo.ZScript;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.User;
import com.soffid.iam.web.menu.MenuOption;
import com.soffid.iam.web.menu.MenuParser;

public class SearchUserHandler extends SearchHandler<User> {

	public void startSearch(String term) throws Exception {
		super.startSearch(term);
		try {
			list = EJBLocator.getUserService().findUserByTextAsync(term);
		} catch (Exception e) {}
	}
	
		
	@Override
	protected void addElement(User next) {
		if (parentDiv.getChildren().isEmpty())
		{
			Label l = new Label( Labels.getLabel("aplicacions.zul.Usuaris"));
			l.setSclass("search-header");
			parentDiv.appendChild(l);
		}
		String s = next.getUserName()+" - "+next.getFullName();
		Div d = new Div();
		d.appendChild(new Label(s));
		d.setSclass("search-option");
		d.setAttribute("url", "/resource/user/user.zul?userName="+next.getUserName());
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
