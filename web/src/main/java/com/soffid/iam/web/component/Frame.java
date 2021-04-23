package com.soffid.iam.web.component;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.ext.AfterCompose;

import com.soffid.iam.utils.Security;
import com.soffid.iam.web.menu.MenuOption;
import com.soffid.iam.web.menu.MenuParser;

import es.caib.zkib.component.Div;
import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.Frameable;

public class Frame extends Div implements AfterCompose, Frameable {
	String permissions[];
	public boolean saveContent;
	public boolean canClose = true;
	
	public Frame() {
	}

	@Override
	public void afterCompose() {
		MenuParser mp = new MenuParser();
		List<MenuOption> menu;
		try {
			menu = mp.getMenus("console.yaml");
		} catch (Exception e) {
			throw new UiException(e);
		}
		MenuOption option = mp.findMenuOption(menu, getPage());
		if (option != null)
		{
			if (permissions == null)
			{
				permissions = option.getPermissions();
			}
		}
		if (permissions != null && permissions.length > 0)
		{
			boolean valid = false;
			for (String s: permissions) if (Security.isUserInRole(s)) valid = true;
			if ( ! valid )
				setVisible(false);
		}
		if (new OtpPageHandler().needsOtp(this))
			setVisible(false);
	}
	

	public String[] getPermissions() {
		return permissions;
	}

	public void setPermissions(String[] permissions) {
		this.permissions = permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions.split(" +");
	}

	public boolean canClose(EventListener action) {
		return canClose;
	}
	public void setCanClose(boolean canClose) {
		this.canClose = canClose;
	}
	public boolean isSaveContent() {
		return saveContent;
	}
	public void setSaveContent(boolean saveContent) {
		this.saveContent = saveContent;
	}

}
