package com.soffid.iam.web.component;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.UiException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.web.WebDataType;
import com.soffid.iam.web.datarender.DataTypeRenderer;

import es.caib.seycon.ng.exception.InternalErrorException;

public abstract class DatatypeColumnsDatatable extends DynamicColumnsDatatable {
	public abstract Collection<DataType> getDataTypes() throws Exception;
	public abstract String[] getDefaultColumns() throws Exception;
	
	@Override
	public void afterCompose() {
		invalidate();
		super.afterCompose();
	}
	
	public void invalidate() {
		try {
			String[] cols = getDefaultColumns();
			Arrays.sort(cols);
			JSONArray array = new JSONArray();
			for (DataType dt: getDataTypes()) {
				WebDataType wdt = new WebDataType(dt);
				JSONObject o = new JSONObject();
				o = DataTypeRenderer.getRenderer(wdt).renderColumn(wdt);
				if (Arrays.binarySearch(cols, dt.getName()) >= 0)
					o.put("default", true);
				array.put(o);
			}
			setAllColumns(array);
		} catch (Exception e) {
			throw new UiException(e);
		}
		super.invalidate();
	}
}
