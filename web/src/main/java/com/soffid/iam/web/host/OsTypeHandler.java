package com.soffid.iam.web.host;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;

import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;

public class OsTypeHandler extends Window {
	DataModel model;
	
	@Override
	public void onPageAttached(Page newpage, Page oldpage) {
		super.onPageAttached(newpage, oldpage);
		model = (DataModel) newpage.getFellow("model");
		newpage.getNamespace().setVariable("model", model, false);
	}
	

	public void onEditValue(Event event) {
		String[] data = (String[]) event.getData();
		String column = data[0];
		String value = data[1];
		XPathUtils.setValue(getListbox(), "/"+column, value);
		return;
	}


	private Component getListbox() {
		return getFellow("listbox");
	}

	public void multiSelect(Event event) {
		DataTable lb = (DataTable) event.getTarget();
		displayRemoveButton( lb, lb.getSelectedIndexes() != null && lb.getSelectedIndexes().length > 0);
	}

	public void displayRemoveButton(Component lb, boolean display) {
		HtmlBasedComponent d = (HtmlBasedComponent) lb.getNextSibling();
		if (d != null && d instanceof Div) {
			d =  (HtmlBasedComponent) d.getFirstChild();
			if (d != null && "deleteButton".equals(d.getSclass())) {
				d.setVisible(display);
			}
		}
	}

	public void deleteSelected(Event event0) {
		Component b = event0.getTarget();
		final Component lb = b.getParent().getPreviousSibling();
		if (lb instanceof DataTable) {
			final DataTable dt = (DataTable) lb;
			if (dt.getSelectedIndexes() == null || dt.getSelectedIndexes().length == 0) return;
			String msg = dt.getSelectedIndexes().length == 1 ? 
					Labels.getLabel("common.delete") :
					String.format(Labels.getLabel("common.deleteMulti"), dt.getSelectedIndexes().length);
				
			Missatgebox.confirmaOK_CANCEL(msg, 
					(event) -> {
						if (event.getName().equals("onOK")) {
							dt.delete();
							displayRemoveButton(lb, false);
						}
					});
		}
	}
	
	public void undo(Event ev) {
		model.refresh();
		setVisible(false);
	}
	
	public void apply(Event ev) throws CommitException {
		model.commit();
		setVisible(false);
	}

	public void addNew() throws Exception {
		Component lb = getListbox();
		if (lb instanceof DataTable)
		{
			((DataTable) lb).addNew();
		}
	}
	
	public void downloadCsv(Event event) {
		Component lb = getListbox();
		((DataTable) lb).download();
	}

}
