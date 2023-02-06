package com.soffid.iam.web.component;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Img;
import org.zkoss.zk.au.out.AuScript;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zk.ui.metainfo.ZScript;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

import com.soffid.iam.utils.Security;
import com.soffid.iam.web.menu.MenuOption;
import com.soffid.iam.web.menu.MenuParser;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.zkiblaf.Application;


public class MainMenu extends FrameHandler implements AfterCompose {
	public MainMenu() throws InternalErrorException {
		super();
	}

	String menu;
	LinkedList<MenuOption> stack = new LinkedList<>();
	private List<MenuOption> options;
	private List<MenuOption> currentOptions;
	private Div navigator;
	private Div optionsDiv;
	boolean small;
	
	@Override
	public void afterCompose() {
		super.afterCompose();
		HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		String option = (String) req.getParameter("option");
		Application.setTitle(Labels.getLabel("menu.title"));
		small = false;
		try {
			options = new MenuParser().getMenus(menu);
			currentOptions = options;
			if (option != null) 
				searchOption(options, option);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		while (currentOptions == null && ! stack.isEmpty()) {
			MenuOption prev = stack.removeLast();
			if ( ! stack.isEmpty())
				currentOptions = stack.getLast().getOptions();
			if (prev.getExecHandler() != null) {
				try {
					prev.getExecHandler().launch(prev, false);
				} catch (Exception e) {
					throw new UiException(e);
				}
			}
			else if (prev.getUrl() != null) {
				Application.setPage(prev.getUrl());
				return;
			}
		}
		if (currentOptions == null)
			currentOptions = options;
		navigator = (Div) getFirstChild();
		optionsDiv = (Div) getLastChild();
		loadMenus();
		focusSearchbox();
	}


	public void focusSearchbox() {
		Page p = getDesktop().getPageIfAny("index");
		if (p != null) {
			Component w = p.getFellowIfAny("appWindow");
			if (w != null) {
				Textbox input = (Textbox) w.getFellowIfAny("searchTextbox");
				if (input != null)
					input.focus();
			}
		}
	}

	
	private boolean searchOption(List<MenuOption> options, String optionName) throws Exception {
		if (searchOption(options, optionName, false))
			return true;
		else
			return searchOption(options, optionName, true);
	}
	
	private boolean searchOption(List<MenuOption> options, String optionName, boolean dynamic) throws Exception {
		if (options == null)
			return false;
		for (MenuOption option: options)
		{
			if (option.getLabel() != null && option.getLabel().equals(optionName))
			{
				stack.add(option);
				if (option.getExecHandler() != null) {
					option.getExecHandler().launch(option, true);
				} else {
					if ( option.getHandler() != null)
						currentOptions = option.getHandler().getOptions(option);
					else 
						currentOptions = option.getOptions();
					small = option.isSmall();
					setUrl("/main/menu.zul?option="+optionName);
				}
				return true;
			}
			else if (option.getOptions() != null && ! option.getOptions().isEmpty())
			{
				stack.add(option);
				if ( searchOption(option.getOptions(), optionName))
					return true;
				stack.removeLast();
			}
			else if (dynamic && option.getHandler() != null) {
				stack.add(option);
				if (searchOption (option.getHandler().getOptions(option), optionName, dynamic))
					return true;
				stack.removeLast();
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
			optionName = menuOption.getLiteral() != null ? menuOption.getLiteral(): Labels.getLabel( menuOption.getLabel() );
		} while (true);
		
		if (small) optionsDiv.setSclass("options options-small");
		else optionsDiv.setSclass("options");
		
		optionsDiv.getChildren().clear();
		for ( MenuOption option: currentOptions) {
			String tip = null;

			if (option.getHandler() == null || option.getHandler().isVisible(option)) {
				Div d = new Div();
				d.setSclass("menuoption");
				d.addEventHandler("onClick", new EventHandler(ZScript.parseContent("ref:"+getId()+".openMenu"), null));
				d.setAttribute("menuOption", option);
				optionsDiv.appendChild(d);
				if (option.getHandler() != null) {
					tip = option.getHandler().getTip(option);
					if (tip != null) {
						Div dd = new Div();
						dd.setSclass("menuoption-tip-shadow");
						d.appendChild(dd);
						Label d3 = new Label();
						d3.setSclass("menuoption-tip");
						d3.setValue(tip);
						dd.appendChild(d3);
					}
				}
				if (option.getImg() != null)
				{
					Img img = new Img();
					if (option.getImg().startsWith("data:"))
						img.setDynamicProperty("src", option.getImg());
					else
						img.setDynamicProperty("src", getDesktop().getExecution().getContextPath() + option.getImg());
					d.appendChild(img);
				}
				if (option.getLiteral() != null) {
					Label l = new Label( option.getLiteral());
					l.setSclass("menuoption-title");
					d.appendChild(l);
				} else if (option.getLabel() != null) {
					Label l = new Label( Labels.getLabel(option.getLabel()));
					l.setSclass("menuoption-title");
					d.appendChild(l);
				}
				if (option.getOptions() != null && !option.getOptions().isEmpty()) {
					Div d2 = new Div ();
					d2.setSclass("menuoption-suboptions");
					d.appendChild(d2);
					boolean first = true;
					for (MenuOption suboption: option.getOptions())
					{
						if (!first)
							d2.appendChild(new Label(", "));
						first = false;
						Label l = new Label( suboption.getLiteral() != null ? suboption.getLiteral(): Labels.getLabel( suboption.getLabel()));
						l.setSclass("menusuboption-title");
						d2.appendChild(l);
					}
				}
			}
		}
	}


	public void onNavigate (Event event) {
		MenuOption s = (MenuOption) event.getTarget().getAttribute("menuOption");
		if (event instanceof MouseEvent &&
				((MouseEvent) event).getKeys() == MouseEvent.CTRL_KEY ) {
			if ( s == null)
				Executions.getCurrent().sendRedirect("/main/menu.zul", "_blank");
			else
				Executions.getCurrent().sendRedirect("/main/menu.zul?option="+s.getLabel(), "_blank");
		} else {
			stack.removeLast();
			while ( ! stack.isEmpty()) {
				MenuOption o = stack.getLast();
				if ( s != null &&  
						(s.getLiteral() == null || s.getLiteral().equals(o.getLiteral())) &&
						(s.getLabel() == null || s.getLabel().equals(o.getLabel())) ) break;
				stack.removeLast();
			} 
			if (stack.isEmpty()) {
				currentOptions = options;
				small = false;
			}
			else  {
				small = stack.getLast().isSmall();
				currentOptions = stack.getLast().getOptions();
			}
			
			loadMenus();
			focusSearchbox();
			if (s != null)
				setUrl("/main/menu.zul?option="+s.getLabel());
		}
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

	
	public void openMenu(Event event) throws Exception {
		MenuOption option = (MenuOption) event.getTarget().getAttribute("menuOption");
		if ( option.getHandler() != null)
			option.setOptions(option.getHandler().getOptions(option));
		if ((option.getOptions() == null || option.getOptions().isEmpty()) && option.getUrl() != null )
		{
			if (event instanceof MouseEvent &&
					(((MouseEvent) event).getKeys() & MouseEvent.CTRL_KEY) != 0)
				getDesktop().getExecution().sendRedirect(option.getUrl(), "_blank");
			else
				Application.call(option.getUrl());
		} else if ((option.getOptions() == null || option.getOptions().isEmpty()) && option.getExecHandler() != null) {
			option.getExecHandler().launch(option, false);
		} else {
			stack.add(option);
			small = option.isSmall();
			currentOptions = option.getOptions();
			loadMenus();
			setUrl("/main/menu.zul?option="+option.getLabel());
			focusSearchbox();
		}
	}
}

