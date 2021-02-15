package com.soffid.iam.web.custom;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.CustomObjectType;
import com.soffid.iam.web.menu.DynamicMenuHandler;
import com.soffid.iam.web.menu.MenuOption;


public class CustomObjectMenuHandler implements DynamicMenuHandler {
	Log log = LogFactory.getLog(getClass());
	
	@Override
	public List<MenuOption> getOptions(MenuOption option) {
		if (option.getOptions() != null && !option.getOptions().isEmpty())
			return option.getOptions();
		List<MenuOption> list = new LinkedList<MenuOption>();
		try {
			for (CustomObjectType cu: EJBLocator.getAdditionalDataService().findCustomObjectTypeByJsonQuery("builtin eq \"false\"")) {
				MenuOption o = new MenuOption();
				o.setImg("/img/menu/custom-object.svg");
				o.setLiteral(cu.getDescription());
				o.setUrl("/custom/custom.zul?type="+cu.getName());
				list.add(o);				
			}
		} catch ( Exception e) {
			log.warn("Error fetching process to start", e);
		}
		option.setSmall(list.size() > 6);
		return list;
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
		return false;
	}
}
