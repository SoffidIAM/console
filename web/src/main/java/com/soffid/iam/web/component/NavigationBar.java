package com.soffid.iam.web.component;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zk.ui.metainfo.ZScript;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;

import com.soffid.iam.web.menu.MenuOption;
import com.soffid.iam.web.menu.MenuParser;

public class NavigationBar extends Div implements AfterCompose {
	String frame = "frame";
	String menu = "console.yaml";
	String lastAction = null;

	public NavigationBar () {
		setSclass("navigation-bar");
	}
	
	@Override
	public void afterCompose() {
		Page p = getPage();
		String path = p.getRequestPath();
		
		List<MenuOption> options;
		try {
			options = new MenuParser().parse(menu);
			if (fillMenu ( options, path, false ) || fillMenu(options, path, true))
			{
				String name = Labels.getLabel("menu.title");				
				insertBefore(new Label(" > "), getFirstChild());
				Label label = new Label(name);
				
				label.addEventHandler("onClick",  new EventHandler(ZScript.parseContent("ref:"+frame+".menu"), null));
				label.setAttribute("target", "");
				label.setSclass("link");
				insertBefore(label, getFirstChild());
			}
			else 
			{
				String name = Labels.getLabel("menu.title");				
				Label label = new Label(name);
				label.addEventHandler("onClick",  new EventHandler(ZScript.parseContent("ref:"+frame+".menu"), null));
				label.setAttribute("target", "");
				label.setSclass("link");
				insertBefore(label, getFirstChild());				
			}
		} catch (IOException | InstantiationException | IllegalAccessException | ClassNotFoundException | JSONException e) {
			throw new UiException(e);
		}
	}

	
	private boolean fillMenu(List<MenuOption> options, String path, boolean dynamic) {
		if (options == null || path == null)
			return false;
		for ( MenuOption option: options)
		{
			if ( dynamic && option.getOptions() == null && option.getHandler() != null) {
				option.setOptions( option.getHandler().getOptions(option));
			}
			if (sameUrl(path, option.getUrl()) || fillMenu(option.getOptions(), path, dynamic)) 
			{
				String name =  option.getLiteral() != null ? option.getLiteral() : Labels.getLabel(option.getLabel());
				Label label = new Label(name);
				
				if ( option.getOptions() == null || option.getOptions().isEmpty())
				{
					if (lastAction != null)
					{
						label.addEventHandler("onClick",  new EventHandler(ZScript.parseContent(lastAction), null));
						label.setAttribute("target", option.getLabel());
						label.setSclass("link");
					}
				}
				else
				{
					insertBefore(new Label(" > "), getFirstChild());
					label.addEventHandler("onClick",  new EventHandler(ZScript.parseContent("ref:"+frame+".menu"), null));
					label.setAttribute("target", option.getLabel());
					label.setSclass("link");
				}
				insertBefore(label, getFirstChild());
				return true;
			}
		}
		return false;
	}


	public boolean sameUrl(String path, String path2) {
		if (path == null || path2 == null)
			return false;
		
		if (path.startsWith("/"))
			path = path.substring(1);
		if (path2.startsWith("/"))
			path2 = path2.substring(1);
		
		return path.equals(path2);
	}


	public String getFrame() {
		return frame;
	}

	
	public void setFrame(String frame) {
		this.frame = frame;
	}

	
	public String getMenu() {
		return menu;
	}

	
	public void setMenu(String menu) {
		this.menu = menu;
	}


	
	public String getLastAction() {
		return lastAction;
	}


	
	public void setLastAction(String lastAction) {
		this.lastAction = lastAction;
	}

}
