package com.soffid.iam.web.config;

import java.io.IOException;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Page;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Application;
import com.soffid.iam.service.ejb.ConfigurationService;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.seycon.ng.exception.InternalErrorException;

public class IntegrationEngineHandler extends FrameHandler {
	public IntegrationEngineHandler() throws Exception {
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
	}

	ConfigurationService configurationService = EJBLocator.getConfigurationService();
	private Listbox taskMode;
	private Intbox taskLimit;

	@Override
	public void afterCompose() {
		super.afterCompose();
		taskLimit = (Intbox) getFellow("taskLimit");
		taskMode = (Listbox) getFellow("taskMode");
		refreshTaskEngine();
	}

	public void refreshTaskEngine() {
		String mode = com.soffid.iam.utils.ConfigurationCache.getProperty("soffid.task.mode");
		if (mode == null)
			mode = "auto";
		List<Listitem> items = taskMode.getItems();
		for (Listitem item : items)
			if (item.getValue().equals(mode))
				taskMode.setSelectedItem(item);
		String max = com.soffid.iam.utils.ConfigurationCache.getProperty("soffid.task.limit");
		taskLimit.setText(max);
	}

	void updateAttribute(String param, String value, String desc) throws InternalErrorException, NamingException, CreateException {
		com.soffid.iam.service.ejb.ConfigurationService svc = com.soffid.iam.EJBLocator.getConfigurationService();
		com.soffid.iam.api.Configuration cfg = svc.findParameterByNameAndNetworkName(param, null);
		if (value == null || value.trim().isEmpty()) {
			if (cfg != null)
				svc.delete(cfg);
		} else if (cfg == null) {
			cfg = new com.soffid.iam.api.Configuration();
			cfg.setCode(param);
			cfg.setValue(value);
			cfg.setDescription(desc);
			svc.create(cfg);
		} else {
			cfg.setValue(value);
			svc.update(cfg);
		}
	}

	public void updateTaskEngine() throws InternalErrorException, NamingException, CreateException, IOException {

		updateAttribute("soffid.task.mode", (String) taskMode.getSelectedItem().getValue(), "Task operation mode");
		updateAttribute("soffid.task.limit", taskLimit.getText(), "Maximum number of tasks per transaction");
		
		closeFrame();
	}

}
