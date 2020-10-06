package com.soffid.iam.web.component;

import java.io.IOException;
import java.util.List;

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
	List<MenuOption> options;
	public Menu3() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, JSONException {
		options = new MenuParser().parse("console.yaml");
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
			o.put("img", getDesktop().getExecution().getContextPath()+option.getImg());
			o.put("url", option.getUrl());
			o.put("full_url", getDesktop().getExecution().getContextPath()+option.getUrl());
			if (option.getHandler() != null) {
				List<MenuOption> options2 = option.getHandler().getOptions();
				if (options2 != null && options2.isEmpty()) {
					continue; // Skip empty menu
				} else {
					option.setOptions(options2);
				}
				String tip = option.getHandler().getTip();
				if (tip != null)
					o.put("tip", tip);
			}
			if (option.getOptions() != null && !option.getOptions().isEmpty()) 
				o.put("options", generateJsonMenu(option.getOptions()));
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
		return super.getCommand(cmdId);
	}

	private static Command _onSelectCommand  = new ComponentCommand (SELECT_EVENT, 0) {
		protected void process(AuRequest request) {
			final Menu3 menu3 = (Menu3) request.getComponent();
			Events.postEvent("onSelect", menu3, request.getData());
		}
		
	};
	
	@Override
	public void afterCompose() {
		addEventListener("onSelect", (evt) -> {
			String[] data = (String[]) evt.getData();
			MenuOption o = new MenuParser().findMenu(options, data[0]);
			if (o != null)	
			{
				if ("true".equals(data[1]))
					Executions.getCurrent().sendRedirect(o.getUrl(), "_blank");
				else
					Application.setPage(o.getUrl());
			}
		});
	}

}
