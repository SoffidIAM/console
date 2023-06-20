package com.soffid.iam.web.popup;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
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
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zk.ui.metainfo.ZScript;
import org.zkoss.zk.ui.util.Configuration;
import org.zkoss.zul.Column;
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
import com.soffid.iam.api.Issue;
import com.soffid.iam.api.IssueStatus;
import com.soffid.iam.api.IssueUser;
import com.soffid.iam.api.User;
import com.soffid.iam.common.TransactionalTask;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.web.WebDataType;
import com.soffid.iam.web.component.BulkAction;
import com.soffid.iam.web.component.Columns;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.MergeAction;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SoffidStackTrace;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Databox;
import es.caib.zkib.component.Databox.Type;
import es.caib.zkib.component.Select;
import es.caib.zkib.component.Switch;
import es.caib.zkib.component.Wizard;
import es.caib.zkib.zkiblaf.Missatgebox;


public class MergeActionHandler extends Window implements AfterCompose {
	private MergeAction mergeAction;
	private Component invoker;
	private List<String> names;
	private List<Object> objects;
	private List<DataType> dataTypes;
	Map<String, int[]> actions = new HashMap<>();
	public MergeActionHandler() {
		Map args = Executions.getCurrent().getAttributes();
		if (args != null) {
			mergeAction = (MergeAction) args.get("mergeAction");
			invoker = (Component) args.get("invoker");
			objects = (List<Object>) args.get("objects");
			names = (List<String>) args.get("names");
			dataTypes = (List<DataType>) args.get("dataTypes");
			newIssue = ((Boolean) args.get("newIssue")).booleanValue();
			currentIssue = (Issue) args.get("currentIssue");
		}
	}

	public void start() throws NamingException, CreateException, InternalErrorException, IOException, WrongValueException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		doHighlighted();
		getFellow("step0").setVisible(newIssue);
		getFellow("step1").setVisible(!newIssue);
		getFellow("step2").setVisible(false);
		Grid actions1 = getActions1Grid();
		actions1.getChildren().clear();
		createActions (actions1);
	}
	
	public void solveNow(Event ev) {
		getFellow("step0").setVisible(false);
		getFellow("step1").setVisible(true);
	}

	public void solveLater(Event ev) throws InternalErrorException, NamingException, CreateException {
		Issue i = new Issue();
		i.setType("duplicated-user");
		i.setCreated(new Date());
		i.setStatus(IssueStatus.NEW);
		List<IssueUser> users = new LinkedList<>();
		for (Object o: objects) {
			if (o instanceof User) {
				User user = (User) o;
				IssueUser iu = new IssueUser();
				iu.setUserName(((User) o).getUserName());
				String field = ConfigurationCache.getProperty("soffid.user.externalId");
				if (field != null) {
					final Object externalId = user.getAttributes().get(field);
					iu.setExternalId(externalId == null ? null: externalId.toString());
				}
				users.add(iu);
			}
		}
		i.setUsers(users );
		i = EJBLocator.getIssueService().create(i);
		Missatgebox.avis(Labels.getLabel("merge.newIssueCreated"));
		setVisible(false);
	}
	
	boolean isEnabled(int i) {
		if (objects.size() <= 2) return true;
		Grid actions1 = getActions1Grid();
		Row row = (Row) actions1.getRows().getFirstChild();
		Switch s = (Switch) row.getChildren().get(i+1);
		return s.isChecked();
	}
	
	private void createActions(Grid actions1) throws NamingException, CreateException, InternalErrorException, IOException, WrongValueException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		actions1.appendChild(new Columns());
		actions1.getColumns().appendChild(new Column(""));
		for ( String name: names ) {
			actions1.getColumns().appendChild(new Column(name));
		}
		
		Rows rows = new Rows();
		actions1.appendChild(rows);
		
		Row header = new Row();
		rows.appendChild(header);
		header.appendChild(new Label(Labels.getLabel("common.merge")));
		int pos = 0;
		for ( String name: names ) {
			Switch s = new Switch();
			s.setChecked(true);
			s.addEventListener("onCheck", onUserSwitch);
			header.appendChild(s);
			s.setAttribute("position", pos++);
		}
		if (names.size() > 2) {
			header.setVisible(false);
		}
		
		for (DataType attribute: dataTypes) {
			if (attribute.getType() != TypeEnumeration.SEPARATOR &&
					! attribute.isReadOnly()) {
				attribute = new WebDataType(attribute);
				Row row = new Row();
				row.setAttribute("attribute", attribute);
				rows.appendChild(row);
				Label l = new Label(attribute.getLabel());
				row.appendChild(l);
				boolean allNull = true;
				for (Object object: objects) {
					Div d = new Div();
					d.setStyle("height: 30px");
					d.addEventListener("onClick", onSelectValue);
					row.appendChild(d);
					InputField3 output = new InputField3();
					DataType dt2 = new DataType(attribute);
					dt2.setLabel(null);
					output.setDataType(dt2);
					output.setReadonly(true);
					output.setLabel(null);
					d.appendChild(output);
					output.createField();
					output.afterCompose();
					Object v = getObjectValue(object, attribute);
					if (v != null && ! v.equals("")) {
						output.setValue(v);
						allNull = false;
					}
					else if (attribute.isMultiValued())
						output.setValue(new LinkedList<>());
					else
						output.setValue("");
					if (dt2.isMultiValued() || row.getChildren().size() == 2)
						d.setSclass("merge reverse");
					else
						d.setSclass("merge");
				}
				if (allNull) row.setVisible(false);
			}
		}
	}

	EventListener onUserSwitch = new EventListener() {
		@Override
		public void onEvent(Event arg0) throws Exception {
			Switch s = (Switch) arg0.getTarget();
			Integer pos = (Integer) s.getAttribute("position");
			Grid actions1 = getActions1Grid();
			// Verify at least two are checked
			if (!s.isChecked()) {
				int count = 0;
				for ( Component cell = actions1.getRows().getFirstChild().getFirstChild().getNextSibling(); 
						cell != null;
						cell = cell.getNextSibling()) {
					Switch sw = (Switch) cell;
					if (sw.isChecked()) count++;
				}
				if (count <= 1) {
					s.setChecked(true);
					return;
				}
			}
			for ( Component row = actions1.getRows().getFirstChild().getNextSibling(); 
					row != null;
					row = row.getNextSibling()) {
				Div div = (Div) row.getChildren().get(pos+1);
				if (s.isChecked()) {
					if (div.getSclass().equals("merge hidden"))
						div.setSclass("merge");
				}
				else {
					DataType dt = (DataType) row.getAttribute("attribute");
					String oldClass = div.getSclass();
					div.setClass("merge hidden");
					if (oldClass.equals("merge reverse") && !dt.isMultiValued()) {
						for (Component div2 = row.getFirstChild().getNextSibling(); 
								div2 != null;
								div2 = div2.getNextSibling()) {
							if (((Div)div2).getSclass().equals("merge")) {
								((Div)div2).setSclass("merge reverse");
								break;
							}
						}
					}
				}
			}
		}
	};
	
	EventListener onSelectValue = new EventListener() {
		@Override
		public void onEvent(Event ev) throws Exception {
			Component row = ev.getTarget().getParent();
			Div div = (Div) ev.getTarget();
			DataType dt = (DataType) row.getAttribute("attribute");
			if (dt.isMultiValued()) {
				InputField3 input = (InputField3) ev.getTarget().getFirstChild();
				if (div.getSclass().equals("merge"))
					div.setSclass("merge reverse");
				else
					div.setSclass("merge");
			} else {
				for (Component child = row.getFirstChild().getNextSibling(); child != null; child = child.getNextSibling()) {
					InputField3 input = (InputField3) child.getFirstChild();
					if (child == ev.getTarget()) 
						((Div)child).setSclass("merge reverse");
					else if (((Div)child).getSclass().equals("merge reverse"))
						((Div)child).setSclass("merge");
				}
			}
		}
	};
	private Issue currentIssue;
	private boolean newIssue;
	
	private Object getObjectValue(Object object12, DataType dt) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (Boolean.TRUE.equals(dt.getBuiltin())) {
			return PropertyUtils.getProperty(object12, dt.getName());
		} else {
			return PropertyUtils.getMappedProperty(object12, "attributes", dt.getName());
		}
	}

	public Grid getActions1Grid() {
		return (Grid) getFellow("actions1");
	}
	
	public static void startWizard (Component invoker,
			List<String> xpaths,
			List<Object> objects,
			List<String> names,
			List<DataType> dataTypes,
			MergeAction action,
			boolean newIssue,
			Issue currentIssue) throws IOException, WrongValueException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, NamingException, CreateException, InternalErrorException {
		Page p = invoker.getDesktop().getPageIfAny("mergeAction");
		if ( p == null) {
			Include i = new Include("/popup/mergeAction.zul");
			i.setDynamicProperty("mergeAction", action);
			i.setDynamicProperty("objects", objects);
			i.setDynamicProperty("names", names);
			i.setDynamicProperty("invoker", invoker);
			i.setDynamicProperty("dataTypes", dataTypes);
			i.setDynamicProperty("newIssue", newIssue);
			i.setDynamicProperty("currentIssue", currentIssue);
			i.setPage(invoker.getPage());
		} else {
			MergeActionHandler h = (MergeActionHandler) p.getFellow("window");
			h.invoker = invoker;
			h.objects = objects;
			h.names = names;
			h.dataTypes = dataTypes;
			h.mergeAction = action;
			h.newIssue = newIssue;
			h.currentIssue = currentIssue;
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

	public void step1next (Event event) throws NamingException, CreateException, InternalErrorException, IOException {
		Div actions2 = (Div) getFellow("actions2");
		actions2.getChildren().clear();
		actions = new HashMap<>();
		
		for (Row row = (Row) getActions1Grid().getRows().getFirstChild().getNextSibling();
				row != null;
				row = (Row) row.getNextSibling()) {
			if (row.isVisible()) {
				DataType attribute = (DataType) row.getAttribute("attribute");
				InputField3 field = new InputField3();
				field.setDataType(attribute);
				field.setReadonly(true);
				
				List<Object> values = new LinkedList<>();
				int[] cols = new int[objects.size()];
				int usedcols = 0;
				int col = 0;
				for (Div cell = (Div) row.getFirstChild().getNextSibling(); cell != null; cell = (Div) cell.getNextSibling()) {
					if (cell.getSclass().equals("merge reverse")) {
						cols[usedcols++] = col;
						InputField3 field2 = (InputField3) cell.getFirstChild();
						if (attribute.isMultiValued())  {
							values.addAll((List)field2.getValue());
						}
						else {
							field.setValue(field2.getValue());
							break;
						}
					}
					col ++;
				}
				actions.put(attribute.getName(), Arrays.copyOf(cols, usedcols));
				if (attribute.isMultiValued())
					field.setValue(values);
				actions2.appendChild(field);
				field.createField();
				field.afterCompose();
			}
		}

		getFellow("step1").setVisible(false);
		getFellow("step2").setVisible(true);
		
	}
	
	
	public void step2next ( Event event) throws Exception {
		List<Integer> pos = new LinkedList<>();
		if (objects.size() <= 2) {
			for (int i = 0; i < objects.size(); i++)
				pos.add(i);
		} 
		else {
			for (int i = 0; i < objects.size(); i++) {
				if (isEnabled(i))
					pos.add(i);
			}
		}
		int[] selectedOptions = new int[pos.size()];
		for (int i = 0; i < pos.size(); i++)
			selectedOptions[i] = pos.get(i).intValue();
			
		EJBLocator.getAsyncRunnerService().runTransaction(() -> {
			mergeAction.apply(actions, selectedOptions); return 0;
		}	);
		
		if (mergeAction.getOnApply() != null)
			mergeAction.getOnApply().onEvent(new Event("onApply", this));
		setVisible(false);
	}
	
	public void step2back (Event event) {
		getFellow("step1").setVisible(true);
		getFellow("step2").setVisible(false);
	}
	
	public void close (Event event) {
		setVisible(false);
	}
	
}


