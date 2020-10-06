package com.soffid.iam.web.main;

import java.io.IOException;
import java.util.List;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zk.ui.metainfo.ZScript;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;

import com.soffid.iam.web.menu.MenuOption;
import com.soffid.iam.web.menu.MenuParser;

public class SearchMenuHandler extends SearchHandler<MenuOption> {

	public void startSearch(String term) throws Exception {
		super.startSearch(term);
		MenuParser menuParser = new MenuParser();
		List<MenuOption> options = menuParser.parse("console.yaml");
		findMenu (options, term.split(" ,+"));
	}
	
		
	@Override
	protected void addElement(MenuOption next) {
		String s = next.getLiteral() == null ? Labels.getLabel(next.getLabel()) : next.getLiteral();
		if (parentDiv.getChildren().isEmpty())
		{
			Label l = new Label( Labels.getLabel("seu.menu-options"));
			l.setSclass("search-header");
			parentDiv.appendChild(l);
		}
		if (parentDiv.getChildren().size() > 5)
			return;
		if (parentDiv.getChildren().size() == 5)
		{
			Div d = new Div();
			d.setSclass("search-more");
			d.appendChild(new Label("..."));
			parentDiv.appendChild(d);
		} else {
			Div d = new Div();
			d.appendChild(new Label(s));
			d.setSclass("search-option");
			if (next.getUrl() != null)
				d.setAttribute("url", next.getUrl());
			else
				d.setAttribute("url", "/main/menu.zul?option="+next.getLabel());
			d.addEventHandler("onClick",
				new EventHandler(ZScript.parseContent("ref:searchBox.onNavigate"), null));
			parentDiv.appendChild(d);
		}
	}

	
	public MenuOption findMenu(List<MenuOption> options, String terms[]) {
		if (options == null || terms == null || terms.length == 0)
			return null;
		for ( MenuOption option: options)
		{
			String s = option.getLiteral() == null ? Labels.getLabel(option.getLabel()) : option.getLiteral();
			if (matches(terms, s)) 
			{
				addElement (option);
			}
			List<MenuOption> options2 = option.getOptions();
			if (option.getHandler() != null) {
				options2 = option.getHandler().getOptions();
			}
			findMenu (options2, terms);
		}
		return null;
	}


	private boolean matches(String[] terms, String s) {
		if (s == null)
			return false;
		for (String term: terms)
			if ( ! s.toLowerCase().contains(term.toLowerCase()))
				return false;
		return true;
	}


	@Override
	protected void addMore() {
		// TODO Auto-generated method stub
		
	}


}
