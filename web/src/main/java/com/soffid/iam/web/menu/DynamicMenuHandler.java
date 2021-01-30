package com.soffid.iam.web.menu;

import java.util.List;

public interface DynamicMenuHandler {
	List<MenuOption> getOptions(MenuOption option);
	String getTip(MenuOption option);
	boolean isVisible(MenuOption option);
	boolean isLeaf();
}
