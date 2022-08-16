package com.soffid.iam.web.wheel;

import java.util.List;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.web.menu.DynamicMenuHandler;
import com.soffid.iam.web.menu.MenuOption;


public class WheelMenuHandler implements DynamicMenuHandler {
	@Override
	public List<MenuOption> getOptions(MenuOption option) {
		return null;
	}

	@Override
	public String getTip(MenuOption option) {
		return null;
	}

	@Override
	public boolean isVisible(MenuOption option) {
		return true;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

}
