package com.soffid.iam.web.popup;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zk.ui.metainfo.ZScript;
import org.zkoss.zk.ui.util.Configuration;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Progressmeter;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

import com.soffid.iam.api.DataType;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.BulkAction;
import com.soffid.iam.web.component.InputField3;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.SoffidStackTrace;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Select;
import es.caib.zkib.component.Wizard;


public class BulkActionHandler extends Window implements AfterCompose {
	List<BulkActionAttribute> selectedAttributes;
	List<BulkActionAttributeAction> selectedActions;
	List<Object> selectedValues;
	private BulkAction bulkAction;
	private BulkActionAttribute[] actions;
	private DataTable invoker;

	public BulkActionHandler() {
		Map args = Executions.getCurrent().getAttributes();
		if (args != null) {
			bulkAction = (BulkAction) args.get("bulkAction");
			actions = (BulkActionAttribute[]) args.get("actions");
			invoker = (DataTable) args.get("invoker");
		}
	}

	public void start() {
		doHighlighted();
		Grid actions1 = getActions1Grid();
		actions1.getRows().getChildren().clear();
		createEmptyRow (actions1);
	}

	private void createEmptyRow(Grid actions1) {
		Row r = new Row();
		actions1.getRows().appendChild(r);
		Select s = new Select();
		s.setStyle("width: 180px");
		JSONArray options = new JSONArray();
		JSONObject option = new JSONObject();
		option.put("label", Labels.getLabel("bulk.attribute.select"));
		option.put("value", "-");
		options.put(option);
		for ( BulkActionAttribute action: actions) {
			option = new JSONObject();
			option.put("label", action.getDataType().getLabel());
			option.put("value", action.name);
			options.put(option);
		}
		s.addEventHandler("onSelect", new EventHandler(ZScript.parseContent("ref:window.onChangeAttribute"), null));
		s.setSelectedValue("-");
		s.setOptions(options.toString());
		r.appendChild(s);
		r.appendChild(new Label());
		r.appendChild(new Label());
	}

	BulkActionAttribute findAttribute(String name) {
		for ( BulkActionAttribute attribute: actions) {
			if (attribute.name.equals(name)) {
				return attribute;
			}
		}
		return null;
	}
	
	BulkActionAttributeAction findAction(BulkActionAttribute att, String name) {
		for (BulkActionAttributeAction action: att.actions) {
			if (action.getName().endsWith(name))
				return action;
		}
		return null;
	}
	
	
	public void onChangeAttribute(Event event) {
		Select s = (Select) event.getTarget();
		String att = (String) s.getSelectedValue();
		boolean found = false;
		while (s.getNextSibling() != null)
			s.getNextSibling().detach();
		BulkActionAttribute attribute = findAttribute((String) s.getSelectedValue());
		if (attribute != null) {
			Select s2 = new Select();
			s2.setStyle("width: 180px");
			JSONArray options = new JSONArray();
			for ( BulkActionAttributeAction action: attribute.actions) {
				JSONObject option = new JSONObject();
				option.put("label", Labels.getLabel("bulk.action."+action.name));
				option.put("value", action.name);
				options.put(option);
			}
			s2.setOptions(options.toString());
			s2.setSelectedValue(attribute.actions[0].name);
			s2.addEventHandler("onSelect", new EventHandler(ZScript.parseContent("ref:window.onChangeAttributeAction"), null));
			s.getParent().appendChild(s2);
			createCustomField(s, s2);
		}
		if (s.getParent().getNextSibling() == null)
			createEmptyRow(getActions1Grid());
	}
	
	public void onChangeAttributeAction(Event event) {
		Select s2 = (Select) event.getTarget();
		Select s = (Select) s2.getPreviousSibling();
		while (s2.getNextSibling() != null)
			s2.getNextSibling().detach();
		
		createCustomField(s, s2);
	}

	public void createCustomField(Select s, Select s2) {
		BulkActionAttribute attribute = findAttribute((String) s.getSelectedValue());
		if (attribute != null) {
			BulkActionAttributeAction action = findAction(attribute, (String) s2.getSelectedValue());
			if (action != null) {
				TypeEnumeration type = action.getType();
				if (type != null)
				{
					DataType dataType = attribute.getDataType();
					InputField3 cf = new InputField3();
					if (type == dataType.getType()) {
						dataType = new DataType(dataType);
						dataType.setValidationExpression(null);
						dataType.setVisibilityExpression(null);
						dataType.setLabel(null);
						dataType.setNlsLabel(null);
						cf.setDataType(dataType);
					} else {
						DataType dt = new DataType();
						dt.setType(type);
						dt.setLabel(null);
						cf.setDataType(dt);
					}
					s2.getParent().appendChild(cf);
					try {
						cf.createField();
					} catch (Exception e) {
						throw new UiException(e);
					}
					cf.afterCompose();
				}
			}
		}
	}
	
	public Grid getActions1Grid() {
		return (Grid) getFellow("actions1");
	}
	
	public Grid getActions2Grid() {
		return (Grid) getFellow("actions2");
	}

	public static void startWizard (BulkActionAttribute[] actions,
			DataTable invoker,
			BulkAction bulkAction) throws IOException {
		Page p = invoker.getDesktop().getPageIfAny("bulkAction");
		if ( p == null) {
			Include i = new Include("/popup/bulkAction.zul");
			i.setDynamicProperty("actions", actions);
			i.setDynamicProperty("bulkAction", bulkAction);
			i.setDynamicProperty("invoker", invoker);
			i.setPage(invoker.getPage());
		} else {
			BulkActionHandler h = (BulkActionHandler) p.getFellow("window");
			h.actions = actions;
			h.bulkAction = bulkAction;
			h.invoker = invoker;
			h.start();
		}
	}

	Wizard getWizard() {
		return (Wizard) getFellow("wizard");
	}

	@Override
	public void afterCompose() {
		start();
	}
	
	public void step1back ( Event event) {
		setVisible(false);
	}

	public void step1next ( Event event) {
		selectedAttributes = new LinkedList<>();
		selectedActions = new LinkedList<>();
		selectedValues = new LinkedList<>();
		Rows rows = getActions1Grid().getRows();
		for (Row row: (List<Row>) rows.getChildren()) {
			Select s = (Select) row.getFirstChild();
			String attName = (String) s.getSelectedValue();
			BulkActionAttribute att = findAttribute(attName);
			if (att != null && s.getNextSibling() != null) {
				Select s2 = (Select) s.getNextSibling();
				String actionName = (String) s2.getSelectedValue();
				BulkActionAttributeAction action = findAction(att, actionName);
				if (action != null) {
					InputField3 field = (InputField3) s2.getNextSibling();
					selectedAttributes.add(att);
					selectedActions.add(action);
					if (field == null) 
						selectedValues.add(null);
					else
						selectedValues.add(field.getValue());
				}
			}
			
		}
		getWizard().next();
		
		Grid g2 = getActions2Grid();
		Rows rows2 = g2.getRows();
		rows2.getChildren().clear();
		for (int i = 0; i < selectedAttributes.size(); i++) {
			Row r = new Row();
			rows2.appendChild(r);
			BulkActionAttribute attribute = selectedAttributes.get(i);
			BulkActionAttributeAction action = selectedActions.get(i);
			Label l1 = new Label(attribute.getDataType().getLabel());
			r.appendChild(l1);
			l1.setSclass("label");
			Label l2 = new Label(Labels.getLabel( "bulk.action."+action.getName()));
			l2.setSclass("label");
			r.appendChild(l2 );
			TypeEnumeration type = action.getType();
			if (type != null)
			{
				DataType dataType = attribute.getDataType();
				InputField3 cf = new InputField3();
				if (type == dataType.getType()) {
					dataType = new DataType(dataType);
					dataType.setValidationExpression(null);
					dataType.setVisibilityExpression(null);
					dataType.setLabel(null);
					dataType.setNlsLabel(null);
					dataType.setReadOnly(true);
					cf.setDataType(dataType);
				} else {
					DataType dt = new DataType();
					dt.setType(type);
					dt.setLabel(null);
					dt.setReadOnly(true);
					cf.setDataType(dt);
				}
				r.appendChild(cf);
				cf.setValue(selectedValues.get(i));
				try {
					cf.createField();
				} catch (Exception e) {
					throw new UiException(e);
				}
				cf.afterCompose();
			}
		}
		
		int size = invoker.getSelectedIndexes().length;
		String msg = String.format(Labels.getLabel("bulk.message"), selectedActions.size(), size);
		((Label)getFellow("actionMsg")).setValue(msg);
	}
	
	boolean finished;
	String endMessage;
	
	public void step2next (Event event) {
		finished  = false;
		getFellow("closeButton").setVisible(false);
		bulkAction.resetCounter();
		final Progressmeter p = (Progressmeter) getFellow("progress");
		p.setVisible(true);
		p.setValue(0);
		final Label l = (Label) getFellow("results");
		l.setVisible(false);
		final Desktop desktop = getDesktop();
		getWizard().next();
		getFellow("closeButton").setVisible(false);
		getFellow("cancelButton").setVisible(true);
		final Configuration config = desktop.getWebApp().getConfiguration();
		Event dummyEvent = new Event("onProcess", BulkActionHandler.this);
		final List inits = config.newEventThreadInits(BulkActionHandler.this, event);
		Timer timer = (Timer) getFellow("timer");
		timer.start();
		desktop.enableServerPush(true);
		final SoffidPrincipal principal = Security.getSoffidPrincipal();
		new Thread(new Runnable() {
			@Override
			public void run() {
				config.invokeEventThreadInits(inits, BulkActionHandler.this, event);
				Security.nestedLogin(principal);
				try {
					bulkAction.apply(selectedAttributes, selectedActions, selectedValues, true,
							desktop, p);
					endMessage = Labels.getLabel("bulk.success");
				} catch (Exception e) {
					endMessage = SoffidStackTrace.generateShortDescription(e);
				} finally {
					Security.nestedLogoff();
				}
				try {
					Executions.activate(desktop);
					p.setVisible(false);
					l.setVisible(true);
					l.setValue(endMessage);
					getFellow("closeButton").setVisible(true);
					getFellow("cancelButton").setVisible(false);
				} catch (Exception e) {
					throw new UiException(e);
				} finally {
					timer.stop();
					Executions.deactivate(desktop);
					final List errs = new LinkedList();
					List cleanups = config.newEventThreadCleanups(BulkActionHandler.this, event, errs);
					config.invokeEventThreadCompletes(cleanups, BulkActionHandler.this, event, null);
					getDesktop().enableServerPush(false);
				}
			}
			
		}).start();
	}
	
	public void step2back (Event event) {
		getWizard().previous();
	}
	
	public void close (Event event) {
		setVisible(false);
		getDesktop().enableServerPush(false);
	}
	
	public void cancel(Event event) {
		bulkAction.cancel();
	}
}


