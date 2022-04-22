package com.soffid.iam.web.component;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.zkoss.util.resource.Labels;
import org.zkoss.xml.HTMLs;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.Command;
import org.zkoss.zk.au.ComponentCommand;
import org.zkoss.zk.au.out.AuInvoke;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;

import com.soffid.iam.web.menu.MenuOption;
import com.soffid.iam.web.menu.MenuParser;

import es.caib.zkib.zkiblaf.Application;

public class Menu3 extends Div implements AfterCompose {
	private static final String SELECT_EVENT = "onSelect";
	private static final String POPULATE_EVENT = "onPopulate";
	Vector<MenuOption> optionsArray = new Vector<MenuOption>();
	List<MenuOption> options;
	public Menu3() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, JSONException {
		options = new MenuParser().getMenus("console.yaml");
	}

	public void open() {
		response("open", new AuInvoke(this, "openRef"));
	}
	
	public JSONArray generateJsonMenu(List<MenuOption> options) {
		JSONArray array = new JSONArray();
		for (MenuOption option: options)
		{
			JSONObject o = new JSONObject();
			if (option.getLiteral() != null)
				o.put("label", option.getLiteral());
			else
				o.put("label", Labels.getLabel(option.getLabel()));
			if (option.getImg() == null) {
				
			}
			else if (option.getImg().startsWith("data:"))
				o.put("img", option.getImg());
			else
				o.put("img", getDesktop().getExecution().getContextPath()+option.getImg());
			o.put("url", option.getUrl());
			if (option.getUrl() != null)
				o.put("full_url", getDesktop().getExecution().getContextPath()+option.getUrl());
			else if (option.getOptions() != null &&
					! option.getOptions().isEmpty() && option.getLabel() != null) 
				o.put("full_url", getDesktop().getExecution().getContextPath()+"/main/menu.zul?option="+option.getLabel());
			int size = optionsArray.size();
			o.put("url", size);
			optionsArray.add(option);
			if (option.getOptions() != null && !option.getOptions().isEmpty()) 
				o.put("options", generateJsonMenu(option.getOptions()));
			if (option.getHandler() != null && !option.getHandler().isLeaf())
				o.put("dynamic", true);
			array.put(o);
		}
		return array;
	}
	
	public String getOuterAttrs() {
		final StringBuffer sb =
			new StringBuffer(64).append(super.getOuterAttrs());

		HTMLs.appendAttribute(sb, "options", generateJsonMenu(options).toString() );
		return sb.toString();
	}

	public Command getCommand(String cmdId) {
		if (SELECT_EVENT.equals(cmdId))
			return _onSelectCommand;
		if (POPULATE_EVENT.equals(cmdId))
			return _onPopulateCommand;
		return super.getCommand(cmdId);
	}

	private static Command _onSelectCommand  = new ComponentCommand (SELECT_EVENT, 0) {
		protected void process(AuRequest request) {
			final Menu3 menu3 = (Menu3) request.getComponent();
			Events.postEvent("onSelect", menu3, request.getData());
		}
		
	};
	
	private static Command _onPopulateCommand  = new ComponentCommand (POPULATE_EVENT, 0) {
		protected void process(AuRequest request) {
			final Menu3 menu3 = (Menu3) request.getComponent();
			menu3.populate(request.getData()[0]);
		}
		
	};

	@Override
	public void afterCompose() {
		addEventListener("onSelect", (evt) -> {
			String[] data = (String[]) evt.getData();
			MenuOption o = optionsArray.get(Integer.parseInt(data[0]));
			if (o != null)	
			{
				if ( o.getExecHandler() != null)
					o.getExecHandler().launch(o, false);
				else if ("true".equals(data[1]))
					Executions.getCurrent().sendRedirect(o.getUrl(), "_blank");
				else if (o.getUrl() == null)
					Application.setPage("/main/menu.zul?option="+o.getLabel());					
				else
					Application.setPage(o.getUrl());
			}
		});
	}

	protected void populate(String position) {
		List<MenuOption> o = options;
		MenuOption parent = null;
		for (String p: position.split(" ")) {
			parent = o.get(Integer.parseInt(p));
			o = parent.getOptions();
		}
		if (parent.getHandler() != null) {
			parent.setOptions(parent.getHandler().getOptions(parent));
		}
		if (parent.getOptions() != null) {
			JSONArray array = generateJsonMenu(parent.getOptions());
			response("add_menu_"+position, new AuInvoke(this, "populate", position, array.toString()));
		}
	}

}
