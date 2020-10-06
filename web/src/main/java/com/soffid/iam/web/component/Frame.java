package com.soffid.iam.web.component;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.au.out.AuScript;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.ext.AfterCompose;

import com.soffid.iam.utils.Security;
import com.soffid.iam.web.menu.MenuOption;
import com.soffid.iam.web.menu.MenuParser;

import es.caib.zkib.zkiblaf.Application;

public class Frame extends es.caib.zkib.zkiblaf.Frame implements AfterCompose {
	String permissions[];

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
}
