package com.soffid.iam.web.agent;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listitem;

import es.caib.seycon.ng.comu.SoffidObjectType;
import es.caib.zkib.binder.BindContext;
import es.caib.zkib.binder.CollectionBinder;
import es.caib.zkib.binder.SingletonBinder;
import es.caib.zkib.binder.list.DataListItemRenderer;
import es.caib.zkib.binder.list.ModelProxy;
import es.caib.zkib.component.DataListbox;
import es.caib.zkib.component.HeaderFilter;
import es.caib.zkib.component.ListboxFilter;
import es.caib.zkib.component.MasterListItem;
import es.caib.zkib.datamodel.DataModelNode;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.ChildDataSourceImpl;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.events.XPathCollectionEvent;
import es.caib.zkib.events.XPathEvent;
import es.caib.zkib.events.XPathRerunEvent;
import es.caib.zkib.events.XPathSubscriber;
import es.caib.zkib.events.XPathValueEvent;
import es.caib.zkib.jxpath.JXPathContext;
import es.caib.zkib.jxpath.JXPathNotFoundException;
import es.caib.zkib.jxpath.Pointer;
import es.caib.zkib.jxpath.Variables;

public class CustomTypeListbox extends Listbox implements XPathSubscriber {

	private static final long serialVersionUID = 7972552691186027886L;
	SingletonBinder valueBinder = new SingletonBinder(this);

	public void onCreate() {
		syncSelectedItem();
	}

	public String getBind() {
		return valueBinder.getDataPath();
	}

	public void setBind(String bind) {
		valueBinder.setDataPath(bind);
		if (bind != null) enableOnSelectListener();
	}

	/** Esto recibe los eventos asociados al valor resultado, no al modelo */
	public void onUpdate(XPathEvent event) {
		boolean old = _updateValueBinder;
		_updateValueBinder = false;
		syncSelectedItem();
		_updateValueBinder = old;
	}

	EventListener onSelectListener = null;
	private boolean _updateValueBinder  = true;

	private void enableOnSelectListener() {
		if (onSelectListener == null) {
			onSelectListener = new EventListener() {

				public boolean isAsap() {
					return true;
				};

				public void onEvent(org.zkoss.zk.ui.event.Event arg0) {// NOTHING
																		// TO DO
				};
			};

			this.addEventListener("onSelect", onSelectListener);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zkoss.zul.Listbox#smartUpdate(java.lang.String, java.lang.String)
	 */
	public void smartUpdate(String attr, String value) {
		if ("selectedIndex".equals(attr) || "select".equals(attr)) {
			int i = getSelectedIndex();

			if (_updateValueBinder) {
				SoffidObjectType type = null;
				String customObject = null;
				if (i >= 0)
				{
					Listitem item = getItemAtIndex(i);
					String newValue[] = (String[]) item.getValue();
					if ( newValue != null)
					{
						customObject = newValue[1];
						type = SoffidObjectType.fromString(newValue[0]);
					}
				}
				DataSource ds = valueBinder.getDataSource();
				String path = valueBinder.getXPath();
				ds.getJXPathContext().setValue(path+"/@soffidObject", 
						type);
				ds.getJXPathContext().setValue(path+"/@soffidCustomObject", 
						customObject);
			}
		}
		super.smartUpdate(attr, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zkoss.zul.Listbox#onInitRender()
	 */
	public void onInitRender() {
		super.onInitRender();
		syncSelectedItem();
	}

	public void setPage(Page page) {
		super.setPage(page);
		valueBinder.setPage(page);
	}

	public void setParent(Component parent) {
		super.setParent(parent);
		valueBinder.setParent(parent);
	}

	public Object clone() {
		CustomTypeListbox clone = (CustomTypeListbox) super.clone();
		clone.valueBinder = new SingletonBinder(clone);
		clone.setBind(valueBinder.getDataPath());
		return clone;
	}

	protected class ExtraCtrl extends Listbox.ExtraCtrl {

		// -- Selectable --//
		public void selectItemsByClient(Set selItems) {
			super.selectItemsByClient(selItems);
		}

	}

	
	public CustomTypeListbox() {
		super();

	}

	
	void syncSelectedItem()
	{
		getChildren().clear();

		SoffidObjectType currentType = null;
		String currentCustomType = null;

		try {
			DataNode dn = (DataNode) valueBinder.getValue();
			currentType = (SoffidObjectType) dn.get("soffidObject");
			currentCustomType = (String) dn.get("soffidCustomObject");
		} catch (Exception e) {
			e.printStackTrace();
		}

		appendChild(new Listitem("- select one -", null));
		for (String sot : (List<String>) com.soffid.iam.api.SoffidObjectType.literals()) {
			if (!sot.equals(es.caib.seycon.ng.comu.SoffidObjectType.OBJECT_CUSTOM.getValue())) {
				Listitem li = new Listitem(sot, new String[] {sot, null});
				appendChild(li);
				if (currentType != null && sot.equals(currentType.getValue())) setSelectedItem(li);
			}
		}
		try {
			for (com.soffid.iam.api.CustomObjectType cot : com.soffid.iam.EJBLocator.getAdditionalDataService()
					.findCustomObjectTypeByJsonQuery(null)) {
				Listitem li = new Listitem(cot.getName(),
						new String[] {es.caib.seycon.ng.comu.SoffidObjectType.OBJECT_CUSTOM.getValue(), cot.getName()});
				appendChild(li);
				if (currentType.getValue().equals(es.caib.seycon.ng.comu.SoffidObjectType.OBJECT_CUSTOM.getValue())
						&& cot.getName().equals(currentCustomType))
					setSelectedItem(li);
			}
		} catch (Exception e) {

		}

	}

}
