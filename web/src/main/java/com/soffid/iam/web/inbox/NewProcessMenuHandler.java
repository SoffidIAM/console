package com.soffid.iam.web.inbox;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Executions;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.bpm.api.ProcessDefinition;
import com.soffid.iam.web.menu.DynamicMenuHandler;
import com.soffid.iam.web.menu.MenuOption;

import es.caib.bpm.exception.BPMException;
import es.caib.seycon.ng.exception.InternalErrorException;


public class NewProcessMenuHandler implements DynamicMenuHandler {
	Log log = LogFactory.getLog(getClass());
	
	@Override
	public List<MenuOption> getOptions(MenuOption option) {
		List<MenuOption> list = new LinkedList<MenuOption>();
		try {
			MenuOption o = new MenuOption();
			o.setImg( "/img/menu/requests.svg" );
			o.setLabel("selfService.MyProcess");
			o.setUrl("/wf/my-requests.zul");
			list.add(o);
			
			for (ProcessDefinition p: EJBLocator.getBpmEngine().findInitiatorProcessDefinitions()) {
				String[] split = p.getName().split(" */ *");
				int i;
				List<MenuOption> parentList = list;
				String path = null;
				for (i = 0; i < split.length - 1; i++) {
					if (path == null) path = split[i];
					else path += "/"+split[i];
					String menuName = split[i];
					boolean found = false;
					for (MenuOption parentOption: parentList) {
						if (parentOption.getLiteral() != null && parentOption.getLiteral().equals(menuName)) {
							parentList = parentOption.getOptions();
							found = true;
							break;
						}
					}
					if (!found) {
						MenuOption parentOption = new MenuOption();
						parentOption.setImg("/img/menu/container.svg");
						parentOption.setLiteral(menuName);
						parentOption.setLabel("new-process."+path);
						parentOption.setOptions(new LinkedList<>());
						parentList.add(parentOption);
						parentList = parentOption.getOptions();
					}
				}
				o = new MenuOption();
				o.setImg( "/img/wf/"+p.getId() );
				o.setLabel("new-process."+p.getName());
				o.setLiteral(split[split.length - 1]);
				o.setUrl("/wf/task.zul?def="+URLEncoder.encode(p.getName(), "UTF-8"));
				parentList.add(o);
			}
		} catch (InternalErrorException | BPMException | NamingException | CreateException | UnsupportedEncodingException e) {
			log.warn("Error fetching process to start", e);
		}
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
