package com.soffid.iam.web.agent;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.zkoss.util.logging.Log;
import org.zkoss.xml.HTMLs;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Column;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.event.ListDataListener;
import org.zkoss.zul.impl.InputElement;

import com.soffid.iam.api.ObjectMappingProperty;
import com.soffid.iam.web.popup.Editor;

import es.caib.zkib.binder.CollectionBinder;
import es.caib.zkib.component.DataGrid;
import es.caib.zkib.datamodel.DataModelNode;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.events.XPathEvent;
import es.caib.zkib.events.XPathRerunEvent;
import es.caib.zkib.events.XPathSubscriber;
import es.caib.zkib.jxpath.Pointer;
import es.caib.zkib.zkiblaf.ImageClic;


public class CustomPropertiesGrid extends Grid implements AfterCompose {
	private CollectionBinder binder = new CollectionBinder(this);
	private ListDataListener _dataListener;
	private static final Log log = Log.lookup(DataGrid.class);
	public void setDataPath(String path)
	{
		binder.setDataPath(path);
		generateGrid();
	}
	
	public Object clone() {
		final CustomPropertiesGrid clone = (CustomPropertiesGrid) super.clone();
		clone.binder = new CollectionBinder (clone);
		clone.binder.setDataPath(binder.getDataPath());
		clone._dataListener = null;
		clone.registerListeners();
		Events.postEvent(new Event("onApplyDatapath", clone));
		return clone;
	}

	public void setPage(Page page) {
		super.setPage(page);
		binder.setPage(page);
	}

	public void setParent(Component parent) {
		super.setParent(parent);
		binder.setParent(parent);
		generateGrid();
	}

	private final class OnChangeTextboxListener implements EventListener {
		@Override
		public void onEvent(Event event) throws Exception {
			Textbox tb = (Textbox) event.getTarget();
			onUpdateTextbox(tb);
		}
	}

	public void onUpdateTextbox(Textbox tb) throws Exception {
		if (Boolean.TRUE.equals(tb.getAttribute("methodName")))
		{
			String oldMethod  = (String) tb.getAttribute("method");
			HashMap<String, String> m = methods.get(oldMethod);
			String newMethodName = tb.getValue();
			if ( methods.containsKey(newMethodName))
			{
				tb.setValue(oldMethod);
				throw new WrongValueException("This method already exists");
			}
			methods.remove(oldMethod);
			methods.put(newMethodName, m);
			Div div = (Div) tb.getNextSibling();
			Grid g = (Grid) div.getFirstChild(); 
			createMethodProperties(newMethodName, m, g);
		} else {
			String method = (String) tb.getAttribute("method");
			String property = (String) tb.getAttribute("property");
			HashMap<String, String> m = methods.get(method);
			m.put(property, tb.getValue());
		}

		// Save properties
		saveProperties();
	}
	
	private final class OnRemoveMethodListener implements EventListener {

		@Override
		public void onEvent(Event event) throws Exception {
			String method = (String) event.getTarget().getAttribute("method");
			methods.remove(method);
			event.getTarget().getParent().detach();
			saveProperties();
		}
	}

	private final class OnEditListener implements EventListener {

		@Override
		public void onEvent(Event event) throws Exception {
			Editor.edit( (InputElement) event.getTarget().getPreviousSibling(), new com.soffid.iam.web.agent.ScriptEnviroment().getSystemVars(event.getTarget()));
		}
	}

	private final class OnApplyDatapathListener implements EventListener {
		public void onEvent(Event event) throws Exception {
			generateGrid();
		}
	}


	@Override
	public void afterCompose() {
		generateGrid();
	}

	public void registerListeners() {
		onChangeTextbox = new OnChangeTextboxListener();

		onRemoveMethod = new OnRemoveMethodListener();

		onEditListener = new OnEditListener();

		addEventListener("onApplyDatapath", new OnApplyDatapathListener());
	}

	protected HashMap<String, HashMap<String,String>> methods;
	protected String[][] methodDescriptor;
	public void generateGrid() {
		if (getPage() == null || binder.getDataPath() == null ||
				binder.getDataSource() == null)
			return;
		// Initialize variable
		methods = new HashMap<String, HashMap<String,String>>();
		// Clear data rows
		Rows rows = getRows();
		if (rows == null)
		{
			rows = new Rows();
			appendChild(rows);
		}
		while (rows.getFirstChild() != null)
			rows.getFirstChild().detach();
		// Fetch data
		methodDescriptor = (String[][]) getPage().getAttribute("methodDescriptor");
		if (methodDescriptor == null)
			return;
		DataNodeCollection coll = (DataNodeCollection) binder.getDataSource().getJXPathContext().getValue(binder.getXPath());
		for (DataNode dn: (List<DataNode>)coll)
		{
			ObjectMappingProperty prop = (ObjectMappingProperty) dn.getInstance();
			String name = prop.getProperty();
			String value = prop.getValue();
			String[] p = splitMethodProperty (name);
			if (p != null)
			{
				HashMap<String, String> method = methods.get(p[0]);
				if (method == null)
				{
					method = new HashMap<String,String>();
					methods.put(p[0], method);
				}
				method.put(p[1], value);
			}
		}
		// Create grid
		List<String> names = new LinkedList<String> (methods.keySet());
		Collections.sort(names);
		for (String methodName: names)
		{
			createMethodRow(methodName);
		}
	}

	private String[] splitMethodProperty(String name) {
		int i;
		if (name == null || name.trim().isEmpty()) {
			name="";
			i=0;
		}
		else
		{
			for (i = 1; i < name.length(); i++)
			{
				if (Character.isUpperCase(name.charAt(i)))
					break;
			}
		}
		String methodName = name.substring(0, i);
		String propertyName = name.substring(i);
		boolean found = false;
		for (String[] p: methodDescriptor)
		{
			if (p[0].equals(propertyName))
				found = true;
		}
		if (found == true)
			return new String[] {methodName, propertyName};
		else
			return null;
	}

	public void createMethodRow(String methodName) {
		Row row = new Row();
		getRows().appendChild(row);
		row.setValign("top");
		HashMap<String, String> method = methods.get(methodName);
		PropertyTextbox tb = new PropertyTextbox();
		tb.setSclass("noborder-textbox");
		tb.setAttribute("method", methodName);
		tb.setAttribute("methodName", Boolean.TRUE);
		tb.setValue(methodName);
		tb.setWidth("100%");
		tb.grid = this;
		tb.addEventListener("onChange", onChangeTextbox);
		row.appendChild(tb);
		Div d = new Div();
		if (methodDescriptor.length > 2)
			d.setSclass("method-collapsed-div");
		row.appendChild(d);
		Grid g = new Grid();
		g.setFixedLayout(true);
		Columns columns = new Columns();
		g.appendChild(columns);
		Column column = new Column();
		column.setWidth("200px");
		columns.appendChild(column);
		column = new Column();
		columns.appendChild(column);
		column = new Column();
		column.setWidth("40px");
		columns.appendChild(column);
		createMethodProperties (methodName, method, g);
		d.appendChild(g);
		if (methodDescriptor.length > 2)
		{
			Div d2 = new Div();
			d2.setSclass("method-collapse-button-div");
			Image img = new Image ("/icons/down.png");
			d2.setAction("onClick: collapse('"+d.getUuid()+"')");
			d2.appendChild(img);
			d.appendChild(d2);
		}
		
		ImageClic ic = new ImageClic("~./img/list-remove.gif");
		ic.setAttribute("method", methodName);
		ic.setAlign("right");
		ic.addEventListener("onClick", onRemoveMethod);
		row.appendChild(ic);
	}


	private void createMethodProperties(String methodName, HashMap<String, String> method, Grid g) {
		if (g.getRows() != null) 
			g.getRows().detach();
		g.appendChild(new Rows());
		for (String[] property: methodDescriptor)
		{
			String name = property[0];
			String label = property.length > 1 ? property[1] : name;
			String type = property.length > 2 ? property[2]: "";
			Row row = new Row();
			g.getRows().appendChild(row);
			row.appendChild(new Label(label));
			PropertyTextbox tb = new PropertyTextbox();
			tb.setSclass("noborder-textbox");
			tb.setAttribute("method", methodName);
			tb.setAttribute("methodName", Boolean.FALSE);
			tb.setAttribute("property", name);
			tb.setMultiline(true);
			tb.setStyle("resize: vertical");
			tb.setValue(method.get(name));
			tb.setWidth("100%");
			tb.addEventListener("onChange", onChangeTextbox);
			tb.grid = this;
			if (property.length > 3)
				tb.title = property[3];
			row.appendChild(tb);		
			if ("beanshell".equals(type))
			{
				ImageClic ic = new ImageClic("/img/pencil.svg");
				ic.setWidth("24px");
				ic.addEventListener("onClick", onEditListener);
				row.appendChild(ic);
			}
		}
	}

	private void saveProperties() throws Exception {
		Map<String,String> finalProperties = new HashMap<String,String>();
		for ( String methodName: methods.keySet()) {
			HashMap<String, String> method = methods.get(methodName);
			for ( String property: method.keySet())
			{
				String value = method.get(property);
				if (value != null && !value.trim().isEmpty())
					finalProperties.put(methodName+property, value);
			}
		}
		
		DataNodeCollection dnc = (DataNodeCollection) binder.getJXPathContext().getValue("/");
		for (Iterator<DataNode> it = dnc.iterator(); it.hasNext();)
		{
			DataNode dn = it.next();
			ObjectMappingProperty omp = (ObjectMappingProperty) dn.getInstance();
			if (splitMethodProperty(omp.getProperty()) != null)
			{
				String newValue = finalProperties.get(omp.getProperty());
				if (!dn.isDeleted())
				{
					if (newValue == null)
						dn.delete();
					else if (! newValue.equals(omp.getValue()))
					{
						omp.setValue(newValue);
						dn.update();
					}
					finalProperties.remove(omp.getProperty());
				}
			}
		}
		for (String newProperty: finalProperties.keySet())
		{
			DataNode dn = (DataNode) dnc.newInstance();
			ObjectMappingProperty omp = (ObjectMappingProperty) dn.getInstance();
			omp.setProperty(newProperty);
			omp.setValue(finalProperties.get(newProperty));
		}
		
		try {
			updating = true;
			binder.getDataSource().sendEvent(new XPathRerunEvent(binder.getDataSource(), binder.getXPath()));
		} finally {
			updating = false;
		}
	}
	
	final static String[] stdMethods = new String[] {
			"select", "insert", "update", "delete", "load"
	};
	public void createMethod ()
	{
		String candidateName = null;
		for (String s: stdMethods)
			if (!methods.containsKey(s))
				candidateName = s;
		if (candidateName == null)
		{
			int i = 1;
			do
			{
				candidateName = Integer.toString(i++);
			} while (methods.containsKey(candidateName));
		}
		methods.put(candidateName, new HashMap<String,String>());
		createMethodRow(candidateName);
	}
	

	EventListener onChangeTextbox = new OnChangeTextboxListener();

	EventListener onRemoveMethod = new OnRemoveMethodListener();

	EventListener onEditListener = new OnEditListener();
	
	boolean updating = false;
}


class PropertyTextbox extends Textbox
{
	CustomPropertiesGrid grid;
	String title;
	
	public void setValue(String s){
		super.setValue(s);
		if (grid != null)
		{
			try {
				grid.onUpdateTextbox(this);
			} catch (Exception e) {
			}
		}
			
	}

	public String getInnerAttrs() {
		final StringBuffer sb =
			new StringBuffer(64).append(super.getInnerAttrs());
		if (title != null)
			HTMLs.appendAttribute(sb, "title", title);
		return sb.toString();
	}
}