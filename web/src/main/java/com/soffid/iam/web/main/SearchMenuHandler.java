package com.soffid.iam.web.main;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.menu.MenuOption;
import com.soffid.iam.web.menu.MenuParser;

public class SearchMenuHandler extends SearchHandler<MenuOption> {
	public void startSearch(String term) throws Exception {
		super.startSearch(term);
		final AsyncList<MenuOption> l = new AsyncList<>();
		list = l;
		final SoffidPrincipal principal = Security.getSoffidPrincipal();
		List<MenuOption> currentOptions = (List<MenuOption>) Sessions.getCurrent().getAttribute("current_menu");
		new Thread (  ) {
			public void run () {
				try {
					Security.nestedLogin(principal);
					List<MenuOption> options = currentOptions;
					if (options == null) {
						MenuParser menuParser = new MenuParser();
						options = menuParser.getMenus("console.yaml");
					}
					final String[] terms = term.split(" ,+");
					for (int i = 0; i < terms.length; i++) {
						terms[i] = StringUtils.stripAccents(terms[i]).toLowerCase();
					}
					findMenu (options, terms, l, false);
					findMenu (options, terms, l, true);
				} catch (Throwable e) {
					l.cancel(e);
				} finally {
					Security.nestedLogoff();
					l.done();
				}
			}
		}.start();
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

	
	public void findMenu(List<MenuOption> options, String terms[], AsyncList<MenuOption> l, Boolean dynamic) {
		if (options == null || terms == null || terms.length == 0)
			return;
		for ( MenuOption option: options)
		{
			if (l.isCancelled())
				return;
			String s = option.getLiteral() == null ? Labels.getLabel(option.getLabel()) : option.getLiteral();
			if (matches(terms, s)) 
			{
				if (! l.contains(option))
					l.add(option);
			}
			if ( loaded >= max) return;
			List<MenuOption> options2 = null;
			if (dynamic == null) {
				options2 = option.getOptions();
				if (option.getHandler() != null) {
					options2 = option.getHandler().getOptions(option);
				}
				findMenu (options2, terms, l, null);				
			}
			else if (dynamic) {
				if (option.getHandler() != null) {
					options2 = option.getHandler().getOptions(option);
					findMenu (options2, terms, l, null);
				}
			}
			else {
				options2 = option.getOptions();
				findMenu (options2, terms, l, dynamic);
			}
			if ( loaded >= max) return;
		}
	}


	private boolean matches(String[] terms, String s) {
		if (s == null)
			return false;
		final String lowerCase = StringUtils.stripAccents(s).toLowerCase();
		for (String term: terms) {
			if ( ! lowerCase.contains(term))
				return false;
		}
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
