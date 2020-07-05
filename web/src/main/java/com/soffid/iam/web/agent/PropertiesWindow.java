package com.soffid.iam.web.agent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Window;

import com.soffid.iam.api.AttributeMapping;
import com.soffid.iam.api.ObjectMapping;
import com.soffid.iam.api.ObjectMappingProperty;

import es.caib.seycon.ng.comu.AttributeDirection;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.XPathUtils;


public class PropertiesWindow extends Window implements AfterCompose {
	public PropertiesWindow() {
		super();

		org.zkoss.zk.ui.Execution ex = Executions.getCurrent();
		Map args = ex.getArg();
		setVariable("objectType", args.get("objectType"), true);
		setVariable("properties", args.get("properties"), true);

	}

	public static void open(Component handler, String objectType, String[] properties) {
		Component previous = handler.getFellowIfAny("agentPropertiesWindow");
		if (previous != null)
			previous.detach();
		Map arg = new HashMap();
		arg.put("objectType", objectType);
		
		if (properties != null) {
			HashMap<String,String> m = new HashMap<String,String>();
			for (int i = 0; i+1 < properties.length; i++)
				m.put(properties[i], properties[i+1]);
			arg.put("properties", new HashSet<String>(m.keySet()));
			
			Component listbox = handler.getPage().getFellow("listbox");
			DataNodeCollection coll = (DataNodeCollection) XPathUtils.getValue(listbox, "/objectMapping");
			for ( int i = 0; i < coll.getSize(); i++) {
				DataNode dn = (DataNode) coll.getDataModel(i);
				if (dn != null && !dn.isDeleted()) {
					ObjectMapping om = (ObjectMapping) dn.getInstance();
					if (om.getSoffidObject() != null && om.getSoffidObject().toString().equals(objectType)) {
						DataNodeCollection coll2 = (DataNodeCollection) dn.getListModel("property");
						HashMap<String,String> m2 = new HashMap<>(m); 
						for ( int j = 0; j < coll2.getSize(); j++ ) {
							DataNode dn2 = (DataNode) coll2.getDataModel(j);
							if ( dn2 != null && ! dn2.isDeleted()) {
								ObjectMappingProperty mapping = (ObjectMappingProperty) dn2.getInstance();
								
								if ( m2.containsKey(mapping.getProperty())) {
									m2.remove(mapping);
								}
							}
						}
						for ( String np: m2.keySet()) {
							ObjectMappingProperty mapping2 = new ObjectMappingProperty();
							mapping2.setProperty(np);
							mapping2.setValue(m2.get(np));
							coll2.add(mapping2);

						}
					}
				}
			}
		}

		Executions.getCurrent().createComponents("/config/agent/agent-properties.zul", handler, arg);
	}

	@Override
	public void afterCompose() {
	}

}
