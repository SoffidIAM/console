package com.soffid.iam.web.custom;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.image.AImage;
import org.zkoss.image.Image;
import org.zkoss.zk.ui.Executions;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AccessTree;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.CustomObjectType;
import com.soffid.iam.api.VaultFolder;
import com.soffid.iam.bpm.api.ProcessDefinition;
import com.soffid.iam.web.menu.DynamicMenuHandler;
import com.soffid.iam.web.menu.MenuOption;

import es.caib.bpm.exception.BPMException;
import es.caib.seycon.ng.exception.InternalErrorException;


public class CustomObjectMenuHandler implements DynamicMenuHandler {
	Log log = LogFactory.getLog(getClass());
	
	@Override
	public List<MenuOption> getOptions(MenuOption option) {
		if (option.getOptions() != null && !option.getOptions().isEmpty())
			return option.getOptions();
		log.info("Fetching menus for "+option.getMenuType()+": "+option.getMenuId());
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

}
