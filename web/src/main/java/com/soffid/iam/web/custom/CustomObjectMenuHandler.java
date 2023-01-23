package com.soffid.iam.web.custom;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.CustomObjectType;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.utils.Security;
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
				if (allowed(cu)) {
					MenuOption o = new MenuOption();
					o.setImg("/img/menu/custom-object.svg");
					o.setLiteral(cu.getDescription());
					o.setUrl("/custom/custom.zul?type="+cu.getName());
					list.add(o);				
				}
			}
		} catch ( Exception e) {
			log.warn("Error fetching process to start", e);
		}
		option.setSmall(list.size() > 6);
		return list;
	}


	private boolean allowed(CustomObjectType cu) {
		if (Boolean.TRUE.equals(cu.getPublicAccess()))
			return true;
		if (cu.getManagerRoles() == null) return false;
		SoffidPrincipal principal = Security.getSoffidPrincipal();
		for (String role: cu.getManagerRoles())
			if (Arrays.binarySearch(principal.getSoffidRoles(), role) >= 0)
				return true;
		return false;
	}


	@Override
	public String getTip(MenuOption option) {
		return null;
	}

	@Override
	public boolean isVisible(MenuOption option) {
		try {
			for (CustomObjectType cu: EJBLocator.getAdditionalDataService().findCustomObjectTypeByJsonQuery("builtin eq \"false\"")) {
				if (allowed(cu)) {
					return true;
				}
			}
		} catch ( Exception e) {
			log.warn("Error fetching process to start", e);
		}
		return false;
	}


	@Override
	public boolean isLeaf() {
		return false;
	}
}
