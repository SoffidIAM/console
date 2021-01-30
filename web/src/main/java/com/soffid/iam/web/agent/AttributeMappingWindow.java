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
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;


public class AttributeMappingWindow extends Window implements AfterCompose {
	public AttributeMappingWindow() {
		super();

		org.zkoss.zk.ui.Execution ex = Executions.getCurrent();
		Map args = ex.getArg();
		setVariable("objectType", args.get("objectType"), true);
		setVariable("direction", args.get("direction"), true);

	}

	public static void open(Component handler, String objectType, String direction) {
		Component previous = handler.getFellowIfAny("agentAttributesWindow");
		if (previous != null)
			previous.detach();
		Map arg = new HashMap();
		arg.put("objectType", objectType);
		arg.put("direction", direction);
		if (direction != null) {
			Component listbox = handler.getPage().getFellow("listbox");
			DataNodeCollection coll = (DataNodeCollection) XPathUtils.getValue(listbox, "/objectMapping");
			for ( int i = 0; i < coll.getSize(); i++) {
				DataNode dn = (DataNode) coll.getDataModel(i);
				if (dn != null && !dn.isDeleted()) {
					ObjectMapping om = (ObjectMapping) dn.getInstance();
					if (om.getSoffidObject() != null && om.getSoffidObject().toString().equals(objectType)) {
						DataNodeCollection coll2 = (DataNodeCollection) dn.getListModel("attributeMapping");
						for ( int j = 0; j < coll2.getSize(); j++ ) {
							DataNode dn2 = (DataNode) coll2.getDataModel(j);
							if ( dn2 != null && ! dn2.isDeleted()) {
								AttributeMapping mapping = (AttributeMapping) dn2.getInstance();
								if ( mapping.getDirection() == AttributeDirection.INPUTOUTPUT) {
									mapping.setDirection(AttributeDirection.INPUT);
									dn2.update();
									AttributeMapping mapping2 = new AttributeMapping();
									mapping2.setDirection(AttributeDirection.OUTPUT);
									mapping2.setSoffidAttribute(mapping.getSoffidAttribute());
									mapping2.setSystemAttribute(mapping.getSystemAttribute());
									coll2.add(mapping2);
								}
							}
						}
					}
				}
			}
		}
		Executions.getCurrent().createComponents("/config/agent/agent-attributes.zul", handler, arg);
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
