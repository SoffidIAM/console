package com.soffid.iam.web.datarender;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.zkoss.zk.ui.Component;

import com.soffid.iam.api.DataType;
import com.soffid.iam.api.MetadataScope;

public abstract class DataTypeRenderer {
	static Map<String, DataTypeRenderer> renderers ;
	static {
		renderers = new HashMap<String,DataTypeRenderer>();
		register(MetadataScope.USER, "fullName", new ReadOnlyRenderer());
	}
	static DataTypeRenderer defaultRenderer = new DefaultRenderer();
	
	static public void register (MetadataScope scope, String attribute, DataTypeRenderer renderer) {
		renderers.put(scope.getValue()+"."+attribute, renderer);
	}

	static public void register (String customObject, String attribute, DataTypeRenderer renderer) {
		renderers.put(MetadataScope.CUSTOM.getValue()+"."+customObject+"."+attribute, renderer);
	}
	
	static public DataTypeRenderer getRenderer (DataType dt) {
		DataTypeRenderer renderer = null;
		if (Boolean.TRUE.equals(dt.getBuiltin())) {
			if (dt.getScope() == MetadataScope.CUSTOM) {
				renderer = renderers.get(dt.getScope().getValue()+"."+dt.getName());
			} else {
				renderer = renderers.get(MetadataScope.CUSTOM.getValue()+"."+dt.getCustomObjectType()+"."+dt.getName());
			}
		}
		if (renderer == null)
			return defaultRenderer;
		else
			return renderer;
	}
	
	public abstract JSONObject renderColumn(DataType dt);
	public abstract Component renderInputField(DataType dt, Component parent, boolean readonly,
			Object ownerObject, String ownerContext,
			String bind) throws Exception;
}
