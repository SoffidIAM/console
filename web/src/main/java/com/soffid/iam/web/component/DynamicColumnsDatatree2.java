package com.soffid.iam.web.component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.ext.AfterCompose;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.web.menu.YamlParser;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkdb.yaml.Yaml2Json;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DataTree2;

public class DynamicColumnsDatatree2 extends DataTree2 {
	String preference;
	private boolean initialized;

	public String getCustomColumns() throws Exception {return null;}
	public String[] getMandatoryColumns() throws Exception {return null;}
	public String[] getHiddenColumns() throws Exception {return null;}

	public String getPreference() {
		return preference;
	}

	public void afterCompose() {
		super.afterCompose();
		initialized = true;
		if (preference != null) {
			try {
				setDefaultColumns();
			} catch (Exception e) {
				throw new UiException(e);
			}
		}
	}
	
	public void setPreference(String preference) throws Exception {
		this.preference = preference;
		if (initialized)
			setDefaultColumns();
	}


	private void setDefaultColumns() throws Exception {
		String pref = EJBLocator.getPreferencesService().findMyPreference("cols-"+preference);
		if (pref != null && ! pref.trim().isEmpty()) {
			JSONArray a = new JSONArray();
			
			String customColumns = getCustomColumns();
			if (customColumns != null && !customColumns.trim().isEmpty())
				a = new JSONArray( new Yaml2Json().transform(customColumns));
			
			String[] mandatory = getMandatoryColumns();
			HashSet<String> enabledColumns = new HashSet<>();
			for (String columnName:  pref.split(" ")) {
				if (!columnName.trim().isEmpty() && ! enabledColumns.contains(columnName))
					enabledColumns.add(columnName);
			}
			if (mandatory != null) {
				for (String m: mandatory) {
					if (! enabledColumns.contains(m))
						enabledColumns.add(m);
				}
			}

			String[] hidden = getHiddenColumns();
			if (hidden != null) {
				for (String m: hidden) {
					if (enabledColumns.contains(m))
						enabledColumns.remove(m);
				}
			}

			JSONArray columns = getColumns();
			for (int i = 0; i < columns.length(); i++) {
				JSONObject o = columns.getJSONObject(i);
				String value = o.optString("name");
				if (columns.isEmpty())
				{
					if (o.optBoolean("default"))
						o.remove("hidden");
					else
						o.put("hidden", true);
				}
				else if ( enabledColumns.contains(value))
				{
					o.remove("hidden");
				}
				else {
					o.put("hidden",  true);
				}
			}

			setColumns(columns.toString());
			return;
		}

		// No preference saved yet
		JSONArray columns = getColumns();
		for (int i = 0; i < columns.length(); i++) {
			JSONObject o = columns.getJSONObject(i);
			String value = o.optString("name");
			if (o.optBoolean("default"))
				o.remove("hidden");
			else
				o.put("hidden", true);
		}

		setColumns(columns.toString());
	}
	
	public void storePreferredColumns() throws UnsupportedEncodingException, InternalErrorException, NamingException, CreateException {
		StringBuffer sb = new StringBuffer();
		JSONArray cols = getColumns();
		for (int i = 0; i < cols.length(); i++) {
			JSONObject col = cols.getJSONObject(i);
			if ( ! col.optBoolean("hidden"))
			{
				if (sb.length() > 0) sb.append(" ");
				sb.append( URLEncoder.encode( col.optString("value"), "UTF-8" ) ) ;
			}
		}
		if (preference != null)	
			EJBLocator.getPreferencesService().updateMyPreference("cols-"+preference, sb.toString());
	}
}
