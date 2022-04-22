package com.soffid.iam.web.menu;

import java.util.List;

import org.json.JSONArray;

public interface DynamicLauncher {
	void launch(MenuOption option, boolean directLink) throws Exception;
}
