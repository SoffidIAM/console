package com.soffid.iam.web.obligation;

import java.util.Map;

import org.zkoss.util.resource.Labels;

import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.Missatgebox;

public class WorkflowObligationHandler {

	public void startWorkflow(final Map<String, String> map) {
		String inProcess = map.get("in-progress-process");
		if (inProcess != null) {
			Missatgebox.avis( String.format( Labels.getLabel("accounts.process-pending"), inProcess));
		} else {
			Missatgebox.confirmaOK_CANCEL(Labels.getLabel("accounts.process-new"),
					event -> {
						if (event.getName().equals("onOK")) {
							String process = map.get("process");
							String account = map.get("account");
							String systemName = map.get("systemName");
							String server = map.get("server");
							String loginName = map.get("loginName");
							Application.call("/wf/task.zul?def="+process+
									"&_account="+account+
									"&_systemName="+systemName+
									"&_loginName="+loginName+
									"&_server="+server);
						}						
					}
				);
		}
	}

}
