package com.soffid.iam.web.popup;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONObject;
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
import es.caib.zkib.component.ReorderEvent;


public class SelectColumnsHandler extends Window implements AfterCompose {
	private DataTable listbox;
	private JSONArray cols;
	private int positions[];

	public SelectColumnsHandler() {
		
	}

	public void start() {
		DynamicColumnsDatatable src = (DynamicColumnsDatatable) getPage().getVariable("src");
		cols = src.getAllColumns();
		positions = new int[cols.length()];
		for (int i = 0; i < positions.length; i++) positions[i] = i;

		JSONArray data = new JSONArray();
		List<Integer> selected = new LinkedList<>();
		for (int i = 0; i < cols.length(); i++) 
		{
			JSONObject o = new JSONObject();
			JSONObject colDef = cols.getJSONObject(i);
			o.put("name", colDef.getString("name"));
			o.put("sort", false);
			if (colDef.optBoolean("enabled"))
				selected.add(new Integer(i));
			data.put(o);
		}
		listbox.setData(data);
		
		int [] selectedArray = new int[selected.size()];
		int selectedPos = 0;
		for (Integer k: selected) selectedArray[selectedPos++] = k.intValue();
		
		listbox.setSelectedIndex(selectedArray);
		doHighlighted();
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
		DynamicColumnsDatatable src = (DynamicColumnsDatatable) getPage().getVariable("src");
		int[] selected = listbox.getSelectedIndexes();
		Arrays.sort(selected);
		JSONArray allCols = new JSONArray();
		JSONArray cols = new JSONArray();
		for (int i = 0; i < positions.length; i++) 
		{
			JSONObject colDef = this.cols.getJSONObject( positions[i] );
			allCols.put(colDef);
			if (Arrays.binarySearch(selected, positions[i]) >= 0) {
				colDef.put("enabled", true);
				cols.put(colDef);
			} else {
				colDef.put("enabled", false);
			}
		}
		src.setColumns(cols.toString());
		src.storePreferredColumns(allCols);
		setVisible(false);
	}

	public void reorder (ReorderEvent event) {
		int pos = event.getSrcPosition();
		int target = event.getInsertBeforePosition();
		int[] positions = new int[cols.length()];
		
		JSONObject srcObject = cols.getJSONObject(pos);
		JSONArray cols2 = new JSONArray();
		for (int i = 0, j = 0; i <= cols.length(); i++) {
			if ( i == target )
				positions[j++] = this.positions[pos];
				cols2.put(srcObject);
			if ( i != pos && i < cols.length())
				positions[j++] = this.positions[i];
		}
		this.positions = positions;
	}


}

