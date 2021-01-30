package com.soffid.iam.web.agent;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Window;

import com.soffid.iam.api.AttributeMapping;
import com.soffid.iam.api.ObjectMapping;

import es.caib.seycon.ng.comu.AttributeDirection;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;


public class OutputTriggerWindow extends Window implements AfterCompose {
	public OutputTriggerWindow() {
		super();

		org.zkoss.zk.ui.Execution ex = Executions.getCurrent();
		Map args = ex.getArg();
		setVariable("objectType", args.get("objectType"), true);
		setVariable("triggerType", args.get("triggerType"), true);

	}

	public static void open(Component handler, String objectType, String triggerType) {
		Component previous = handler.getFellowIfAny("agentTriggersWindow");
		if (previous != null)
			previous.detach();
		Map arg = new HashMap();
		arg.put("objectType", objectType);
		arg.put("triggerType", triggerType);
		Executions.getCurrent().createComponents("/config/agent/agent-triggers.zul", handler, arg);
	}

	@Override
	public void afterCompose() {
	}

	public void doClose(Event event) throws CommitException, InterruptedException {
		DataModel model = (DataModel) Path.getComponent("/model");
		if (model.isCommitPending()) {
			model.commit();
			Thread.sleep(3000);
		}
	}
}
