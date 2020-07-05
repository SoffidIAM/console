package com.soffid.iam.web.agent;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.zkib.binder.SingletonBinder;
import es.caib.zkib.component.Select;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.events.XPathEvent;
import es.caib.zkib.events.XPathSubscriber;

public class CustomDataTypeSelect extends Select implements XPathSubscriber {
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
    	String prefix = TypeEnumeration.CUSTOM_OBJECT_TYPE.getValue()+":";
    	TypeEnumeration type;
    	String customObjectType;
    	if (value == null || value.equals("")) {
			type = null;
    		customObjectType = null;
    	}
    	else if (value.startsWith( prefix))
    	{
			type = TypeEnumeration.CUSTOM_OBJECT_TYPE;
    		customObjectType = value.substring( prefix.length() );
    	} else {
    		type = TypeEnumeration.fromString(value);
    		customObjectType = null;
    	}
		DataSource ds = valueBinder.getDataSource();
		String path = valueBinder.getXPath();
		if (!path.endsWith("/"))
			path = path + "/";
		ds.getJXPathContext().setValue(path+"@type", 
				type);
		ds.getJXPathContext().setValue(path+"@dataObjectType", 
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
		CustomDataTypeSelect clone = (CustomDataTypeSelect) super.clone();
		clone.valueBinder = new SingletonBinder(clone);
		clone.setBind(valueBinder.getDataPath());
		return clone;
	}

	public CustomDataTypeSelect() {
		super();

	}
	
	void syncSelectedItem()
	{
		getChildren().clear();

		TypeEnumeration currentType = null;
		String currentCustomType = null;

		try {
			Object value = valueBinder.getValue();
			if (value == null)
				return;
			DataNode dn = (DataNode) value;
			currentType = (TypeEnumeration) dn.get("type");
			currentCustomType = (String) dn.get("dataObjectType");
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
			for (String sot : (List<String>) TypeEnumeration.literals()) {
				if (!sot.equals(TypeEnumeration.CUSTOM_OBJECT_TYPE.getValue())) {
					o = new JSONObject();
					o.put("label", org.zkoss.util.resource.Labels.getLabel("typeDadaAddicional."+sot));
					o.put("value", sot);
					array.put(o);
					if (currentType != null && sot.equals(currentType.getValue())) selected = sot;
				}
			}
			for (com.soffid.iam.api.CustomObjectType cot : com.soffid.iam.EJBLocator.getAdditionalDataService()
					.findCustomObjectTypeByJsonQuery(null)) {
				o = new JSONObject();
				o.put("label", cot.getName());
				String value = TypeEnumeration.CUSTOM_OBJECT_TYPE.getValue()+":"+cot.getName();
				o.put("value", value);
				array.put(o);
				if (currentType != null &&
						currentType.getValue().equals(TypeEnumeration.CUSTOM_OBJECT_TYPE.getValue())
						&& cot.getName().equals(currentCustomType))
					selected = value;
			}
			setOptions(array.toString());
			setSelectedValue(selected);
		} catch (Exception e) {

		}
		
	}

}
