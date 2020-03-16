package com.soffid.iam.web.component;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zk.ui.metainfo.ZScript;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;

import com.soffid.iam.utils.Security;
import com.soffid.iam.web.menu.MenuOption;
import com.soffid.iam.web.menu.MenuParser;

import es.caib.zkib.zkiblaf.Application;


public class MainMenu extends Div implements AfterCompose {
	String menu;
	LinkedList<MenuOption> stack = new LinkedList<>();
	private List<MenuOption> options;
	private List<MenuOption> currentOptions;
	private Div navigator;
	private Div optionsDiv;
	
	@Override
	public void afterCompose() {
		HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		String option = (String) req.getParameter("option");
		Application.setTitle(Labels.getLabel("menu.title"));
		try {
			options = new MenuParser().getMenus(menu);
			currentOptions = options;
			if (option != null)
				searchOption(options, option);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		navigator = (Div) getFirstChild();
		optionsDiv = (Div) getLastChild();
		loadMenus();
	}

	
	private boolean searchOption(List<MenuOption> options, String optionName) {
		for (MenuOption option: options)
		{
			if (option.getOptions() != null && ! option.getOptions().isEmpty())
			{
				if (option.getLabel().equals(optionName))
				{
					stack.push(option);
					currentOptions = option.getOptions();
					return true;
				}
				else
				{
					stack.add(option);
					if ( searchOption(option.getOptions(), optionName))
						return true;
					stack.removeLast();
				}
			}
		}
		return false;
	}


	private void loadMenus() {
		navigator.getChildren().clear();
		String optionName = Labels.getLabel("menu.title");
		MenuOption menuOption = null;
		Iterator<MenuOption> it = stack.iterator();
		do
		{
			addNavigatorItem (menuOption, optionName, it.hasNext());
			if ( ! it.hasNext() ) break;
			menuOption = it.next();
			optionName = Labels.getLabel( menuOption.getLabel() );
		} while (true);
		
		optionsDiv.getChildren().clear();
		for ( MenuOption option: currentOptions) {
			if (isAllowed (option))
			{
				Div d = new Div();
				d.setSclass("menuoption");
				d.addEventHandler("onClick", new EventHandler(ZScript.parseContent("ref:"+getId()+".openMenu"), null));
				d.setAttribute("menuOption", option);
				optionsDiv.appendChild(d);
				if (option.getImg() != null)
				{
					Image img = new Image(option.getImg());
					d.appendChild(img);
				}
				if (option.getLabel() != null) {
					Label l = new Label( Labels.getLabel(option.getLabel()));
					l.setSclass("menuoption-title");
					d.appendChild(l);
				}
				if (!option.getOptions().isEmpty()) {
					Div d2 = new Div ();
					d2.setSclass("menuoption-suboptions");
					d.appendChild(d2);
					boolean first = true;
					for (MenuOption suboption: option.getOptions())
					{
						if ( isAllowed(suboption))
						{
							if (!first)
								d2.appendChild(new Label(", "));
							first = false;
							Label l = new Label( Labels.getLabel(suboption.getLabel()));
							l.setSclass("menusuboption-title");
							d2.appendChild(l);
						}
					}
				}
			}
		}
	}


	private boolean isAllowed(MenuOption option) {
		if (option.getPermissions() == null)
			option.setPermissions( new String[0] );
		if (option.getOptions() == null)
			option.setOptions(new LinkedList<MenuOption>());
		if ( option.getOptions().isEmpty() && option.getPermissions().length == 0)
		{
			return true;
		}
		for (String p: option.getPermissions())
			if (Security.isUserInRole(p))
				return true;
		for (MenuOption child: option.getOptions())
			if (isAllowed(child))
				return true;
		return false;
	}


	public void onNavigate (Event event) {
		MenuOption s = (MenuOption) event.getTarget().getAttribute("menuOption");
		do {
			MenuOption o = stack.getLast();
			if ( s == o ) break;
			stack.removeLast();
		} while ( ! stack.isEmpty());

		if (stack.isEmpty())
			currentOptions = options;
		else
			currentOptions = stack.getLast().getOptions();
		
		loadMenus();
	}
	
	private void addNavigatorItem(MenuOption menuOption, String optionName, boolean hasNext) {
		Label l = new Label(optionName);
		navigator.appendChild(l);
		if (hasNext)
		{
			l.setSclass("link");
			l.addEventHandler("onClick", new EventHandler(ZScript.parseContent("ref:"+getId()+".onNavigate"), null));
			l.setAttribute("menuOption", menuOption);
			navigator.appendChild( new Label(" > "));
		}
	}


	public String getMenu() {
		return menu;
	}

	
	public void setMenu(String menu) {
		this.menu = menu;
	}

	
	public void openMenu(Event event) {
		MenuOption option = (MenuOption) event.getTarget().getAttribute("menuOption");
		if (option.getOptions() == null || option.getOptions().isEmpty())
		{
			Application.setPage(option.getUrl());
		} else {
			stack.add(option);
			currentOptions = option.getOptions();
			loadMenus();
		}
	}
}

