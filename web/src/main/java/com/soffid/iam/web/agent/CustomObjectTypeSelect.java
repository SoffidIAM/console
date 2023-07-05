package com.soffid.iam.web.agent;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;

import com.soffid.iam.api.ExtensibleObjectRegister;
import com.soffid.iam.api.SoffidObjectType;

import es.caib.zkib.binder.SingletonBinder;
import es.caib.zkib.component.Select;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.events.XPathEvent;
import es.caib.zkib.events.XPathSubscriber;

public class CustomObjectTypeSelect extends Select implements XPathSubscriber {
	boolean accountMetadata = false;

	private static final long serialVersionUID = 7972552691186027886L;

	public void onCreate() {
		valueBinder.setDataPath("/");
		syncSelectedItem();
	}

	/** Esto recibe los eventos asociados al valor resultado, no al modelo */
	public void onUpdate(XPathEvent event) {
		syncSelectedItem();
	}

    protected void clientSelect(String value ) {
    	String prefix = SoffidObjectType.OBJECT_CUSTOM.getValue()+":";
    	SoffidObjectType type;
    	String customObjectType;
		if (value.startsWith( prefix))
    	{
			type = SoffidObjectType.OBJECT_CUSTOM;
    		customObjectType = value.substring( prefix.length() );
    	} else {
    		type = SoffidObjectType.fromString(value);
    		customObjectType = null;
    	}
		DataSource ds = valueBinder.getDataSource();
		String path = valueBinder.getXPath();
		if (!path.endsWith("/"))
			path = path + "/";
		ds.getJXPathContext().setValue(path+"@soffidObject", 
				type);
		ds.getJXPathContext().setValue(path+"@soffidCustomObject", 
				customObjectType);
		
		selectedValue = value;
		Events.postEvent(new Event("onSelect", this, selectedValue));
	}

	public void setPage(Page page) {
		super.setPage(page);
		valueBinder.setPage(page);
	}

	public void setParent(Component parent) {
		super.setParent(parent);
		valueBinder.setParent(parent);
	}

	public Object clone() {
		CustomObjectTypeSelect clone = (CustomObjectTypeSelect) super.clone();
		clone.valueBinder = new SingletonBinder(clone);
		clone.setBind(valueBinder.getDataPath());
		return clone;
	}

	public CustomObjectTypeSelect() {
		super();

	}
	
	void syncSelectedItem()
	{
		getChildren().clear();

		SoffidObjectType currentType = null;
		String currentCustomType = null;

		try {
			Object value = valueBinder.getValue();
			if (value == null)
				return;
			DataNode dn = (DataNode) value;
			currentType = (SoffidObjectType) dn.get("soffidObject");
			currentCustomType = (String) dn.get("soffidCustomObject");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			JSONArray array = new JSONArray();
			JSONObject o = new JSONObject();
			o.put("label", "- select one -");
			o.put("value", "");
			array.put(o);
			String selected = null;
			for (String sot : (List<String>) SoffidObjectType.literals()) {
				if (!sot.equals(SoffidObjectType.OBJECT_CUSTOM.getValue())) {
					o = new JSONObject();
					o.put("label", org.zkoss.util.resource.Labels.getLabel("typeDadaAddicional."+sot));
					o.put("value", sot);
					array.put(o);
					if (currentType != null && sot.equals(currentType.getValue())) selected = sot;
				}
			}
			for (com.soffid.iam.api.CustomObjectType cot : com.soffid.iam.EJBLocator.getAdditionalDataService()
					.findCustomObjectTypeByJsonQuery(null)) {
				if (!cot.isBuiltin() ||
						cot.getExtensibleObjectClass() != null && !cot.getExtensibleObjectClass().trim().isEmpty()) {
					o = new JSONObject();
					o.put("label", cot.getName());
					String value = SoffidObjectType.OBJECT_CUSTOM.getValue()+":"+cot.getName();
					o.put("value", value);
					array.put(o);
					if (currentType != null &&
							currentType.getValue().equals(SoffidObjectType.OBJECT_CUSTOM.getValue())
							&& cot.getName().equals(currentCustomType))
						selected = value;
				}
			}
			for (ExtensibleObjectRegister cot : com.soffid.iam.EJBLocator.getAdditionalDataService()
					.findExtensibleObjectRegisters()) {
				o = new JSONObject();
				o.put("label", cot.getName());
				String value = SoffidObjectType.OBJECT_CUSTOM.getValue()+":"+cot.getName();
				o.put("value", value);
				array.put(o);
				if (currentType != null &&
						currentType.getValue().equals(SoffidObjectType.OBJECT_CUSTOM.getValue())
						&& cot.getName().equals(currentCustomType))
					selected = value;
			}
			setOptions(array.toString());
			setSelectedValue(selected);
		} catch (Exception e) {

		}
		
	}

}
