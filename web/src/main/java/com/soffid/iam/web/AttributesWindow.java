package com.soffid.iam.web;

import java.util.Comparator;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Row;
import org.zkoss.zul.Window;

import es.caib.seycon.ng.comu.TipusDada;
import es.caib.zkib.component.DataGrid;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.events.XPathRerunEvent;


public class AttributesWindow extends Window {
	public void renumAttributes (DropEvent event)
	{
		Listbox grid = (Listbox) getFellow ("listbox");
		Listitem srcRow = (Listitem) event.getDragged().getParent().getParent();
		Listitem targetRow = (Listitem) event.getTarget();
		if (srcRow != targetRow)
		{
			TipusDada srcField = (TipusDada) ((DataNode) XPathUtils.getValue(srcRow, ".")).getInstance();
			
			int order = 1;
			for ( Listitem item : (List<Listitem>) grid.getItems())
			{
				if (item == srcRow) {
					// Skip
				}
				else
				{
					if (item == targetRow) 
						srcField.setOrdre( new Long ( order ++ ));
					DataNode dataNode = (DataNode) XPathUtils.getValue(item, ".");
					TipusDada targetField = (TipusDada) dataNode.getInstance();
					targetField.setOrdre( new Long ( order ++ ));
					dataNode.update();
				}
			}
			DataNodeCollection collection = (DataNodeCollection) XPathUtils.getValue(grid.getParent(), "/dadaAddicional");
			collection.sort(new OrderComparator());
		}
	}
	
	public void onNewRow(Event event) {
		Listitem item = (Listitem) event.getData();
		item.addEventListener("onDrop", onDrop);
		item.setDroppable("true");
	}
	
	EventListener onDrop = new EventListener() {
		@Override
		public void onEvent(Event arg0) throws Exception {
			renumAttributes ((DropEvent) arg0);
		}
	};
}

class OrderComparator implements Comparator
{

	@Override
	public int compare(Object o1, Object o2) {
		return ((TipusDada)o1).getOrdre().compareTo(((TipusDada) o2).getOrdre());
	}
	
}
