package com.soffid.iam.web.menu;

import java.util.List;

import org.json.JSONArray;

public interface DynamicMenuHandler {
	List<MenuOption> getOptions(MenuOption option);
	String getTip(MenuOption option);
}