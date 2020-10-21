package com.soffid.iam.web.inbox;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.web.menu.DynamicMenuHandler;
import com.soffid.iam.web.menu.MenuOption;

import es.caib.bpm.exception.BPMException;
import es.caib.seycon.ng.exception.InternalErrorException;


public class InboxMenuHandler implements DynamicMenuHandler {
	Log log = LogFactory.getLog(getClass());
	
	@Override
	public List<MenuOption> getOptions(MenuOption option) {
		return null;
	}

	@Override
	public String getTip(MenuOption option) {
		int c;
		try {
			c = EJBLocator.getBpmEngine().findMyTasks().size();
		} catch (InternalErrorException | BPMException | NamingException | CreateException e) {
			return "";
		}
		if (c == 0)
			return "";
		else
			return Integer.toString(c);
	}

}
