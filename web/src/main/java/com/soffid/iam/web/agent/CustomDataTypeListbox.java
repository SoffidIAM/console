package com.soffid.iam.web.agent;

import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.zkib.binder.SingletonBinder;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.events.XPathEvent;
import es.caib.zkib.events.XPathSubscriber;

public class CustomDataTypeListbox extends Listbox implements XPathSubscriber {

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
				TypeEnumeration type = null;
				String customObject = null;
				if (i >= 0)
				{
					Listitem item = getItemAtIndex(i);
					String newValue[] = (String[]) item.getValue();
					if ( newValue != null)
					{
						customObject = newValue[1];
						type = TypeEnumeration.fromString(newValue[0]);
					}
				}
				DataSource ds = valueBinder.getDataSource();
				String path = valueBinder.getXPath();
				ds.getJXPathContext().setValue(path+"@type", 
						type);
				ds.getJXPathContext().setValue(path+"@dataObjectType", 
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
		CustomDataTypeListbox clone = (CustomDataTypeListbox) super.clone();
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

	
	public CustomDataTypeListbox() {
		super();

	}

	
	void syncSelectedItem()
	{
		getChildren().clear();

		TypeEnumeration currentType = null;
		String currentCustomType = null;

		try {
			DataNode dn = (DataNode) valueBinder.getValue();
			if (dn == null)
				return;
			currentType = (TypeEnumeration) dn.get("type");
			currentCustomType = (String) dn.get("dataObjectType");
		} catch (Exception e) {
			e.printStackTrace();
		}

		appendChild(new Listitem("- select one -", null));
		for (String sot : (List<String>) TypeEnumeration.literals()) {
			if (!sot.equals(TypeEnumeration.CUSTOM_OBJECT_TYPE.getValue())) {
				Listitem li = new Listitem(
						org.zkoss.util.resource.Labels.getLabel("typeDadaAddicional."+sot),
						new String[] {sot, null});
				appendChild(li);
				if (currentType != null && sot.equals(currentType.getValue())) setSelectedItem(li);
			}
		}
		try {
			for (com.soffid.iam.api.CustomObjectType cot : com.soffid.iam.EJBLocator.getAdditionalDataService()
					.findCustomObjectTypeByJsonQuery(null)) {
				Listitem li = new Listitem(cot.getName(),
						new String[] {TypeEnumeration.CUSTOM_OBJECT_TYPE.getValue(), cot.getName()});
				appendChild(li);
				if (currentType.getValue().equals(TypeEnumeration.CUSTOM_OBJECT_TYPE.getValue())
						&& cot.getName().equals(currentCustomType))
					setSelectedItem(li);
			}
		} catch (Exception e) {

		}

	}

}
