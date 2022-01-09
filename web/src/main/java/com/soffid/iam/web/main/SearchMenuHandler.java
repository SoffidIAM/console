package com.soffid.iam.web.main;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zk.ui.metainfo.ZScript;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.common.TransactionalTask;
import com.soffid.iam.web.menu.MenuOption;
import com.soffid.iam.web.menu.MenuParser;

public class SearchMenuHandler extends SearchHandler<MenuOption> {
	public void startSearch(String term) throws Exception {
		super.startSearch(term);
		final AsyncList<MenuOption> l = new AsyncList<>();
		list = l;
		EJBLocator.getAsyncRunnerService().runTransaction( new TransactionalTask() {
			@Override
			public Object run() throws Exception {
				try {
					List<MenuOption> options = (List<MenuOption>) Sessions.getCurrent().getAttribute("current_menu");
					if (options == null) {
						MenuParser menuParser = new MenuParser();
						options = menuParser.parse("console.yaml");
						Sessions.getCurrent().setAttribute("current_menu", options);
					}
					findMenu (options, term.split(" ,+"), l);
				} catch (Exception e) {
					l.cancel(e);
				} finally {
					l.done();
				}
				return null;
			}
		});
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

	
	public void findMenu(List<MenuOption> options, String terms[], AsyncList<MenuOption> l) {
		if (options == null || terms == null || terms.length == 0)
			return;
		for ( MenuOption option: options)
		{
			if (l.isCancelled())
				return;
			String s = option.getLiteral() == null ? Labels.getLabel(option.getLabel()) : option.getLiteral();
			if (matches(terms, s)) 
			{
				l.add(option);
			}
			if ( loaded >= max) return;
			List<MenuOption> options2 = option.getOptions();
			if (option.getHandler() != null) {
				options2 = option.getHandler().getOptions(option);
			}
			if ( loaded >= max) return;
			findMenu (options2, terms, l);
		}
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
		Div d = new Div();
		d.setSclass("search-more");
		d.appendChild(new Label("..."));
		parentDiv.appendChild(d);
	}
}
