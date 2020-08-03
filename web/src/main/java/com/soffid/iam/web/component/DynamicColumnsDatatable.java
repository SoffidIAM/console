package com.soffid.iam.web.component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.zk.ui.ext.AfterCompose;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.web.menu.YamlParser;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;

public class DynamicColumnsDatatable extends DataTable {
	JSONArray allColumns;
	String preference;

	public String getCustomColumns() throws Exception {return null;}
	public String[] getMandatoryColumns() throws Exception {return null;}
	public String[] getHiddenColumns() throws Exception {return null;}

	public JSONArray getAllColumns() {
		return allColumns;
	}

	
	public void setAllColumns(JSONArray allColumns) throws Exception {
		this.allColumns = allColumns;
		if (this.preference != null)
			setDefaultColumns();
	}
	
	public void setAllColumns(String allColumns) throws Exception {
		JSONArray array = (JSONArray) new YamlParser().parse(allColumns);
		setAllColumns(array);
	}
	
	public String getPreference() {
		return preference;
	}

	
	public void setPreference(String preference) throws Exception {
		this.preference = preference;
		if (this.allColumns != null)
			setDefaultColumns();
	}


	private void setDefaultColumns() throws Exception {
		String pref = EJBLocator.getPreferencesService().findMyPreference("cols-"+preference);
		if (pref != null && ! pref.trim().isEmpty()) {
			Set<String> cols = new java.util.HashSet<String>();
			JSONArray a = new JSONArray();
			
			
			String customColumns = getCustomColumns();
			if (customColumns != null && !customColumns.trim().isEmpty())
				a = (JSONArray) new YamlParser().parse(customColumns);
			
			String[] mandatory = getMandatoryColumns();
			List<String> enabledColumns = Arrays.asList(pref.split(" "));
			if (mandatory != null) {
				for (String m: mandatory) {
					if (! enabledColumns.contains(m))
						enabledColumns.add(0, m);
				}
			}

			String[] hidden = getHiddenColumns();
			if (hidden != null) {
				for (String m: hidden) {
					if (enabledColumns.contains(m))
						enabledColumns.remove(m);
				}
			}

			for (String s: enabledColumns) {
				String col = URLDecoder.decode(s, "UTF-8");
				for (int i = 0; i < allColumns.length(); i++) {
					JSONObject o = allColumns.getJSONObject(i);
					String value = o.optString("value");
					if ( value.equals(col))
					{
						o.put("enabled", true);
						a.put(o);
					}
				}
			}
			if (!a.isEmpty()) {
				setColumns(a.toString());
				return;
			}
		}

		// No preference saved yet
		JSONArray a = new JSONArray();
		String customColumns = getCustomColumns();
		if (customColumns != null && !customColumns.trim().isEmpty())
			a = (JSONArray) new YamlParser().parse(customColumns);
		
		String[] mandatory = getMandatoryColumns();
		if (mandatory == null) mandatory = new String[0];

		String[] hidden = getHiddenColumns();
		if (hidden == null) hidden = new String[0];

		for (int i = 0; i < allColumns.length(); i++) {
			JSONObject o = allColumns.getJSONObject(i);
			if ( Arrays.binarySearch(hidden, o.optString("value")) >= 0) {
				// Skip
			} else if ( Arrays.binarySearch(mandatory, o.optString("value")) >= 0 || o.optBoolean("default")) {
				o.put("enabled", true);
				a.put(o);
			}
		}
		setColumns(a.toString());
	}
	
	public void storePreferredColumns(JSONArray allCols) throws UnsupportedEncodingException, InternalErrorException, NamingException, CreateException {
		allColumns = allCols;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < allColumns.length(); i++) {
			JSONObject col = allColumns.getJSONObject(i);
			if ( col.optBoolean("enabled"))
			{
				if (sb.length() > 0) sb.append(" ");
				sb.append( URLEncoder.encode( col.optString("value"), "UTF-8" ) ) ;
			}
		}
		if (preference != null)	
			EJBLocator.getPreferencesService().updateMyPreference("cols-"+preference, sb.toString());
	}
}
