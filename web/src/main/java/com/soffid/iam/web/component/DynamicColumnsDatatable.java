package com.soffid.iam.web.component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
	
	public JSONArray getAllColumns() {
		return allColumns;
	}

	
	public void setAllColumns(JSONArray allColumns) throws InternalErrorException, NamingException, CreateException, UnsupportedEncodingException {
		this.allColumns = allColumns;
		if (this.preference != null)
			setDefaultColumns();
	}
	
	public void setAllColumns(String allColumns) throws IOException, InternalErrorException, NamingException, CreateException {
		JSONArray array = (JSONArray) new YamlParser().parse(allColumns);
		setAllColumns(array);
	}
	
	public String getPreference() {
		return preference;
	}

	
	public void setPreference(String preference) throws InternalErrorException, NamingException, CreateException, UnsupportedEncodingException {
		this.preference = preference;
		if (this.allColumns != null)
			setDefaultColumns();
	}


	private void setDefaultColumns() throws InternalErrorException, NamingException, CreateException, UnsupportedEncodingException {
		String pref = EJBLocator.getPreferencesService().findMyPreference("cols-"+preference);
		if (pref != null && ! pref.trim().isEmpty()) {
			Set<String> cols = new java.util.HashSet<String>();
			JSONArray a = new JSONArray();
			for (String s: pref.split(" ")) {
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
			
		JSONArray a = new JSONArray();
		for (int i = 0; i < allColumns.length(); i++) {
			JSONObject o = allColumns.getJSONObject(i);
			if ( o.optBoolean("default")) {
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
