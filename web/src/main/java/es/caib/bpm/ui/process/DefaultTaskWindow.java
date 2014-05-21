package es.caib.bpm.ui.process;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.zkoss.util.Locales;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;


import es.caib.bpm.toolkit.WorkflowWindow;

public class DefaultTaskWindow extends WorkflowWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void load() {
		Map m = getProcessInstance().getVariables();
		for ( Iterator it = m.keySet().iterator(); it.hasNext(); )
		{
			String var = (String) it.next();
			Object value = m.get(var);
			if (value == null) {
				// Nothing to do 
			} else if (value instanceof Integer ||
					value instanceof Long ||
					value instanceof String )  {
				addItem(var).setValue(value.toString());
			} else if (value instanceof Boolean) {
				Checkbox v = new Checkbox(""); //$NON-NLS-1$
				v.setDisabled(true);
				v.setChecked(((Boolean) value).booleanValue());
				addItem(var, v);
			} else if (value instanceof Date) {
				DateFormat f = DateFormat.getDateTimeInstance(DateFormat.SHORT, 
						DateFormat.SHORT,
						Locales.getCurrent());
				addItem(var).setValue(f.format((Date)value));
			} else if (value instanceof Calendar) {
				DateFormat f = DateFormat.getDateTimeInstance(DateFormat.SHORT, 
						DateFormat.SHORT,
						Locales.getCurrent());
				addItem(var).setValue(f.format((Calendar)value));
			}
		}
	}

	private Label addItem(String var) {
		Label l = new Label();
		addItem (var, l);
		return l;
	}

	private void addItem(String var, Component component) {
		Rows rows = (Rows) getFellow("rows"); //$NON-NLS-1$
		Row r = new Row ();
		Label l = new Label(var);
		l.setParent(r);
		component.setParent(r);
		r.setParent(rows);
		
	}

}
