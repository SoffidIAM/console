package com.soffid.iam.web.config;

import java.io.IOException;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.json.JSONException;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Application;
import com.soffid.iam.service.ejb.ConfigurationService;
import com.soffid.iam.utils.ConfigurationCache;
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
	private Listbox lang;

	@Override
	public void afterCompose() {
		super.afterCompose();
		taskLimit = (Intbox) getFellow("taskLimit");
		taskMode = (Listbox) getFellow("taskMode");
		lang = (Listbox) getFellow("language");
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
		
		
		String interpreter = ConfigurationCache.getProperty("soffid.interpreter");
		if ("javascript".equals(interpreter))
			lang.setSelectedIndex(1);
		else
			lang.setSelectedIndex(0);

		String max = com.soffid.iam.utils.ConfigurationCache.getProperty("soffid.task.limit");
		taskLimit.setText(max);
		
		
		Button b = (Button) getFellow("undoButton");
		b.setDisabled(true);
		b = (Button) getFellow("commitButton");
		b.setDisabled(true);

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

	public void updateTaskEngine() throws InternalErrorException, NamingException, CreateException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, JSONException {

		updateAttribute("soffid.task.mode", (String) taskMode.getSelectedItem().getValue(), "Task operation mode");
		updateAttribute("soffid.task.limit", taskLimit.getText(), "Maximum number of tasks per transaction");
		updateAttribute("soffid.interpreter", (String) lang.getSelectedItem().getValue(), "Script language");
		
		closeFrame();
	}

	public void enableButtons(Event ev) {
		Button b = (Button) getFellow("undoButton");
		b.setDisabled(false);
		b = (Button) getFellow("commitButton");
		b.setDisabled(false);
	}
}
