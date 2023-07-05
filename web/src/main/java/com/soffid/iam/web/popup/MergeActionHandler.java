package com.soffid.iam.web.popup;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.commons.beanutils.PropertyUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zk.ui.metainfo.ZScript;
import org.zkoss.zk.ui.util.Configuration;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Progressmeter;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.DataType;
import com.soffid.iam.common.TransactionalTask;
import com.soffid.iam.web.WebDataType;
import com.soffid.iam.web.component.BulkAction;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.MergeAction;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SoffidStackTrace;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Databox;
import es.caib.zkib.component.Databox.Type;
import es.caib.zkib.component.Select;
import es.caib.zkib.component.Wizard;


public class MergeActionHandler extends Window implements AfterCompose {
	List<Integer> selectedActions; 
	List<Object> selectedValues;
	private MergeAction mergeAction;
	private DataTable invoker;
	private String name2;
	private String name1;
	private Object object1;
	private Object object2;
	private List<DataType> dataTypes;
	Map<String, String> values;
	public MergeActionHandler() {
		Map args = Executions.getCurrent().getAttributes();
		if (args != null) {
			mergeAction = (MergeAction) args.get("mergeAction");
			invoker = (DataTable) args.get("invoker");
			object1 = args.get("object1");
			object2 = args.get("object2");
			name1 = (String) args.get("name1");
			name2 = (String) args.get("name2");
			dataTypes = (List<DataType>) args.get("dataTypes");
		}
	}

	public void start() throws NamingException, CreateException, InternalErrorException, IOException, WrongValueException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		doHighlighted();
		Div actions1 = getActions1Grid();
		actions1.getChildren().clear();
		createActions (actions1);
	}

	private void createActions(Div actions1) throws NamingException, CreateException, InternalErrorException, IOException, WrongValueException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		values = new HashMap<>();
		String msgSelect = Labels.getLabel("merge.action.select");
		String msgMerge = Labels.getLabel("merge.action.merge");
		List<String> listSelect = Arrays.asList(
				"1: "+String.format(msgSelect, name1),
				"2: "+String.format(msgSelect, name2));
		List<String> listMerge = Arrays.asList(
				"1: "+String.format(msgSelect, name1),
				"2: "+String.format(msgSelect, name2),
				"3: "+String.format(msgMerge));
		for (DataType attribute: dataTypes) {
			if (attribute.getType() != TypeEnumeration.SEPARATOR &&
					! attribute.isReadOnly()) {
				Div d = new Div();
				d.setStyle("width: 100%; vertical-align: top; border-bottom: solid 1px #808080; margin-top: 3px; margin-bottom: 3px; overflow: hidden");
				actions1.appendChild(d);
				Div d1 = new Div();
				d.appendChild(d1);
				d1.setStyle("display: inline-block; width: 50%; vertical-align: top; overflow: hidden");
				Databox input = new Databox();
				input.setRequired(true);
				input.setLabel(attribute.getLabel());
				if (! attribute.isMultiValued()) {
					input.setValues(listSelect);
					input.setValue("1");
				} else {
					input.setValues(listMerge);
					input.setValue("3");
				}
				input.setType(Type.LIST);
				input.setAttribute("dataType", attribute);
				input.addEventHandler("onChange", new EventHandler(ZScript.parseContent("ref:window.onChangeAttribute"), null));
				input.afterCompose();
				d1.appendChild(input);
				Div d2 = new Div();
				d2.setStyle("display: inline-block; width: 50%; vertical-align: top; overflow: hidden");
				d.appendChild(d2);
				InputField3 output = new InputField3();
				DataType dt2 = new DataType(attribute);
				dt2.setLabel(null);
				output.setDataType(dt2);
				output.setReadonly(true);
				output.setLabel(null);
				d2.appendChild(output);
				output.createField();
				output.afterCompose();
				refreshValue(input, output);
			}
		}
	}

	private void refreshValue(Databox input, InputField3 output) throws WrongValueException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String value = (String) input.getValue();
		DataType dt = output.getDataType();
		values.put(dt.getName(), value);
		if ("1".equals(value)) {
			Object v = getObjectValue(object1, dt);
			output.setValue(v);
		}
		else if ("2".equals(value)) {
			Object v = getObjectValue(object2, dt);
			output.setValue(getObjectValue(object2, dt));
		}
		else if ("3".equals(value)) {
			List<Object> l1 = new LinkedList<>();
			Collection<Object> c1 = (Collection<Object>) getObjectValue(object1, dt);
			if (c1 != null)
				l1.addAll(c1);
			Collection<Object> c2 = (Collection<Object>) getObjectValue(object2, dt);
			if (c2 != null)
				l1.addAll(c2);
			output.setValue(l1);
		}
	}

	private Object getObjectValue(Object object12, DataType dt) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (Boolean.TRUE.equals(dt.getBuiltin())) {
			return PropertyUtils.getProperty(object12, dt.getName());
		} else {
			return PropertyUtils.getMappedProperty(object12, "attributes", dt.getName());
		}
	}

	public void onChangeAttribute(Event event) throws WrongValueException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Databox src = (Databox) event.getTarget();
		InputField3 target = (InputField3) src.getParent().getNextSibling().getFirstChild();
		refreshValue(src, target);
	}

	public Div getActions1Grid() {
		return (Div) getFellow("actions1");
	}
	
	public static void startWizard (DataTable invoker,
			Object object1,
			Object object2,
			String name1,
			String name2, 
			List<DataType> dataTypes,
			MergeAction action) throws IOException, WrongValueException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, NamingException, CreateException, InternalErrorException {
		Page p = invoker.getDesktop().getPageIfAny("mergeAction");
		if ( p == null) {
			Include i = new Include("/popup/mergeAction.zul");
			i.setDynamicProperty("mergeAction", action);
			i.setDynamicProperty("object1", object1);
			i.setDynamicProperty("object2", object2);
			i.setDynamicProperty("name1", name1);
			i.setDynamicProperty("name2", name2);
			i.setDynamicProperty("invoker", invoker);
			i.setDynamicProperty("dataTypes", dataTypes);
		i.setPage(invoker.getPage());
		} else {
			MergeActionHandler h = (MergeActionHandler) p.getFellow("window");
			h.invoker = invoker;
			h.object1 = object1;
			h.object2 = object2;
			h.name1 = name1;
			h.name2 = name2;
			h.dataTypes = dataTypes;
			h.mergeAction = action;
			h.start();
		}
	}

	Wizard getWizard() {
		return (Wizard) getFellow("wizard");
	}

	@Override
	public void afterCompose() {
		try {
			start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void step1back ( Event event) {
		setVisible(false);
	}

	public void step1next ( Event event) throws InternalErrorException, NamingException, CreateException {
		EJBLocator.getAsyncRunnerService().runTransaction(() -> {mergeAction.apply(values); return 0;}	);
		setVisible(false);
	}
	
	public void step2back (Event event) {
		getWizard().previous();
	}
	
	public void close (Event event) {
		setVisible(false);
	}
	
}


