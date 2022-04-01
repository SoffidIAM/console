package es.caib.bpm.ui.admin;

import org.json.JSONException;
import org.json.JSONObject;

import es.caib.zkib.component.DataTable;


public class ProcessDefinitionTable extends DataTable {

	@Override
	protected JSONObject getClientValue(Object element) throws JSONException {
		JSONObject o = super.getClientValue(element);
		if (! o.optBoolean("enabled"))
			o.put("$class", "dashed");
		return o;
			
	}

}
