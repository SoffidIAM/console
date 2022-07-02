package com.soffid.iam.web.popup;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Include;
import org.zkoss.zul.Window;

import com.soffid.iam.web.component.DynamicColumnsDatatable;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.component.ReorderEvent;


public class SelectColumnsHandler extends Window implements AfterCompose {
	private DataTable listbox;
	private JSONArray cols;
	private int positions[];
	private boolean table;

	public SelectColumnsHandler() {
		
	}

	public void start() {
		Component c = (Component) getPage().getVariable("src");
		JSONArray data = new JSONArray();
		List<Integer> selected = new LinkedList<>();
		if (c instanceof DynamicColumnsDatatable) {
			DynamicColumnsDatatable src = (DynamicColumnsDatatable) c;
			table = true;
			JSONArray srccols = src.getAllColumns();
			cols = new JSONArray();
			String colsString = src.getColumns();
			if (colsString == null) {
				JSONArray ar = new JSONArray();
				for (int i = 0; i < srccols.length() && i < 6; i++)
					ar.put(srccols.get(i));
				colsString = ar.toString();
			}
				
			JSONArray cols2 = new JSONArray( colsString );
			
			String[] mandatory = null; 
			try {
				mandatory = ((DynamicColumnsDatatable) c).getMandatoryColumns();
			} catch (Exception e) {
			}
			HashSet<String> colNames = new HashSet();
			for (int i = 0; i < cols2.length(); i++) {
				JSONObject o = new JSONObject();
				JSONObject colDef = cols2.getJSONObject(i);
				String value = colDef.optString("value", "");
				if (colDef.optString("name", null) != null) {
					o.put("name", colDef.getString("name"));
					o.put("sort", false);
					o.put("mandatory", isMandatory(mandatory, value));
					selected.add(new Integer(data.length()));
					data.put(o);
					colNames.add(colDef.getString("name"));
					cols.put(colDef);
				}				
			}
	
			
			for (int i = 0; i < srccols.length(); i++) 
			{
				JSONObject o = new JSONObject();
				JSONObject colDef = srccols.getJSONObject(i);
				String value = colDef.optString("value", "");
				if (colDef.optString("name", null) != null &&
						!colNames.contains(colDef.getString("name"))) {
					o.put("name", colDef.getString("name"));
					o.put("sort", false);
					o.put("mandatory", isMandatory(mandatory, value));
					data.put(o);
					cols.put(colDef);
				}
			}
			
			positions = new int[cols.length()];
			for (int i = 0; i < positions.length; i++) positions[i] = i;
			
		} else {
			table = false;
			DataTree2 src = (DataTree2) c;
			cols = src.getColumns();
			positions = new int[cols.length()];
			for (int i = 0; i < positions.length; i++) positions[i] = i;
	
			for (int i = 0; i < cols.length(); i++) 
			{
				JSONObject o = new JSONObject();
				JSONObject colDef = cols.getJSONObject(i);
				if (colDef.optString("name", null) != null) {
					o.put("name", colDef.getString("name"));
					o.put("sort", false);
					if (!colDef.optBoolean("hidden"))
						selected.add(new Integer(i));
					data.put(o);
				}
			}
		}
		listbox.setData(data);
		
		int [] selectedArray = new int[selected.size()];
		int selectedPos = 0;
		for (Integer k: selected) selectedArray[selectedPos++] = k.intValue();
		
		listbox.setSelectedIndex(selectedArray);
		doHighlighted();
	}
	
	private boolean isMandatory(String[] mandatory, String value) {
		if (mandatory == null)
			return false;
		for (String m: mandatory)
			if (m.equals(value)) return true;
		return false;
	}

	@Override
	public void setPage(Page page) {
		super.setPage(page);
		Map args = Executions.getCurrent().getAttributes();
		if (args != null) {
			page.setVariable("src", args.get("src") );
		}
	}
	
		
	public void onClose(Event event) {
		event.stopPropagation();		                                 
		close (event);
	}
	
	public void close(Event event) {
		setVisible(false);
	}
	
	@Override
	public void afterCompose() {
		listbox = (DataTable) getFellow("listbox");
		start();
	}
	
	
	public static void startWizard (DynamicColumnsDatatable src) throws IOException {
		Page p = src.getDesktop().getPageIfAny("selectColumns");
		if ( p == null) {
			Include i = new Include("/popup/select-columns.zul");
			i.setDynamicProperty("src", src);
			i.setPage(src.getPage());
		} else {
			p.setVariable("src", src);
			Events.sendEvent(new Event("onDisplay", p.getFellow("window")));
		}
	}
	
	public void end() throws UnsupportedEncodingException, InternalErrorException, NamingException, CreateException {
		Component c = (Component) getPage().getVariable("src");
		if (c instanceof DynamicColumnsDatatable) {
			DynamicColumnsDatatable src = (DynamicColumnsDatatable) getPage().getVariable("src");
			int[] selected = listbox.getSelectedIndexes();
			Arrays.sort(selected);
			JSONArray allCols = new JSONArray();
			JSONArray cols = new JSONArray();
			String[] mandatory = null; 
			try {
				if (c instanceof DynamicColumnsDatatable)
					mandatory = ((DynamicColumnsDatatable) c).getMandatoryColumns();
			} catch (Exception e) {
			}
			for (int i = 0; i < positions.length; i++) 
			{
				JSONObject colDef = this.cols.getJSONObject( positions[i] );
				String value = colDef.optString("value", "");
				allCols.put(colDef);
				if (isMandatory(mandatory, value)) {
					colDef.put("enabled", true);
					cols.put(colDef);
				} else if (selected.length == 0) {
					colDef.put("enabled", colDef.optBoolean("default"));
					cols.put(colDef);
				} else if (Arrays.binarySearch(selected, positions[i]) >= 0) {
					colDef.put("enabled", true);
					cols.put(colDef);
				} else {
					colDef.put("enabled", false);
				}
			}
			
			src.setColumns(cols.toString());
			src.storePreferredColumns(allCols);
		}
		if (c instanceof DataTree2) {
			DataTree2 src = (DataTree2) c;
			int[] selected = listbox.getSelectedIndexes();
			for (int i = 0; i < positions.length; i++) 
			{
				JSONObject colDef = this.cols.getJSONObject( positions[i] );
				if (selected.length == 0) {
					colDef.put("hidden", true);
				} else if (Arrays.binarySearch(selected, positions[i]) >= 0) {
					colDef.put("hidden", false);
				} else {
					colDef.put("hidden", true);
				}
			}
			src.setColumns(cols.toString());
		}
		setVisible(false);
	}

	public void reorder (ReorderEvent event) {
		int pos = event.getSrcPosition();
		Integer before = event.getInsertBeforePosition();
		int[] positions = new int[this.positions.length];
		
		JSONObject srcObject = cols.getJSONObject(pos);
		JSONArray cols2 = new JSONArray();
		int j = 0;
		for (int i = 0; i < cols.length(); i++) {
			if ( before != null && before.intValue() == this.positions[i])
				positions[j++] = pos;
			if ( this.positions[i] != pos )
				positions[j++] = this.positions[i];
		}
		if (before == null)
			positions[j++] = pos;
		this.positions = positions;
	}

	public static void startWizard(DataTree2 src) {
		Page p = src.getDesktop().getPageIfAny("selectColumns");
		if ( p == null) {
			Include i = new Include("/popup/select-columns.zul");
			i.setDynamicProperty("src", src);
			i.setPage(src.getPage());
		} else {
			p.setVariable("src", src);
			Events.sendEvent(new Event("onDisplay", p.getFellow("window")));
		}
	}


}

