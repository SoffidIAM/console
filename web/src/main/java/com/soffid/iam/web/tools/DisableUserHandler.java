package com.soffid.iam.web.tools;

import java.util.LinkedList;

import javax.json.JsonArray;
import javax.json.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.au.out.AuInvoke;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Progressmeter;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timer;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncProcessTracker;
import com.soffid.iam.api.DisableObjectRule;
import com.soffid.iam.api.exception.ListFullException;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.SearchBox;
import com.soffid.iam.web.popup.EmailEditor;
import com.soffid.scimquery.parser.ParseException;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataGrid;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Div;
import es.caib.zkib.component.Select;
import es.caib.zkib.component.Wizard;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datamodel.RowsLimitExceededException;
import es.caib.zkib.zkiblaf.ImageClic;
import es.caib.zkib.zkiblaf.Missatgebox;


public class DisableUserHandler extends FrameHandler {

	private Wizard wizard;
	private Timer timer;
	private Button step3next;
	private Div progressImage2;
	private Div progressImage3;
	private Progressmeter progressPct;
	private DataGrid rulesGrid;
	private LinkedList<DisableObjectRule> rules = new LinkedList<>();
	private LinkedList<Object[]> result;
	private AsyncProcessTracker currentProcess;
	private DataTable previewlistbox;
	int rows;
	private Div progressImage4;
	
	public DisableUserHandler() throws InternalErrorException {
		super();
	}

	@Override
	public void afterCompose() {
		super.afterCompose();
		wizard = (Wizard) getFellow("wizard");
		timer = (Timer) getFellow("timer");
		progressImage2 = (Div) getFellow("progressImage2");
		progressImage3 = (Div) getFellow("progressImage3");
		progressImage4 = (Div) getFellow("progressImage4");
		progressPct = (Progressmeter) getFellow("progressPct");
		step3next = (Button) getFellow("step3next");
		rulesGrid = (DataGrid) getFellow("rulesGrid");
		previewlistbox = (DataTable) getFellow("previewlistbox");
	}

	public void back(Event event) {
		wizard.previous();
		if (wizard.getSelected() == 1)
		{
			timer.stop();
		}
	}
	
	
	public void next(Event event) throws Exception {
		if (wizard.getSelected() == 2) {
			if (! checkValidRules())
				return;
		}
		wizard.next();
		if (wizard.getSelected() == 2) {
			rulesGrid.getRows().getChildren().clear();
			rules.clear();
			addNewRow();
		}
		if (wizard.getSelected() == 3) {
			String query = (String) getModel().getJXPathContext().getVariables().getVariable("query");
			result = new LinkedList<>();
			currentProcess = EJBLocator.getUserService().disableUsersPreview(query, rules, result);
			progressImage2.setVisible(true);
			step3next.setVisible(false);
			previewlistbox.setData("[]");
			rows = 0;
			timer.setRepeats(true);
			timer.setDelay(1000);
			timer.start();
		}
		if (wizard.getSelected() == 4) {
			String query = (String) getModel().getJXPathContext().getVariables().getVariable("query");
			result = new LinkedList<>();
			currentProcess = EJBLocator.getUserService().disableUsers(query, rules);
			progressImage3.setVisible(true);
			progressImage4.setVisible(false);
			timer.setRepeats(true);
			timer.setDelay(1000);
			timer.start();
		}
	}

	private boolean checkValidRules() {
		boolean anyOK = false;
		for (DisableObjectRule rule: rules) {
			if (rule.getCriteria() != null && ! rule.getCriteria().equals("-")) {
				if (rule.getAction() != null && ! rule.getAction().equals("-")) {
					anyOK = true;
				}
			}
			if ("P".equals(rule.getCriteria()) &&
					("D".equals(rule.getAction()) || "R".equals(rule.getAction()))) {
				if (rule.getParameter().intValue() < 0) {
					Missatgebox.avis(Labels.getLabel("tools.disable-users.warning.1"));
				}
			}
			if ("L".equals(rule.getCriteria())) {
				if (rule.getParameter().intValue() < 1) {
					Missatgebox.avis(Labels.getLabel("tools.disable-users.warning.2"));
					return false;
				}
			}
		}
		if (!anyOK) {
			Missatgebox.avis(Labels.getLabel("tools.disable-users.selectaction"));
		}
		return anyOK;
	}

	private void addNewRow() {
		final DisableObjectRule rule = new DisableObjectRule();
		rule.setParameter(30);
		rules.add(rule);
		
		Row r = new Row();
		rulesGrid.getRows().appendChild(r);
		final Select s = new Select();
		String[] criteria = new String[] {
				"-", Labels.getLabel("descriptorAgent.Select"),
				"L", Labels.getLabel("tools.disable-users.criteria0"),
				"P", Labels.getLabel("tools.disable-users.criteria1")
		};
		s.setOptions(optionsToJson(criteria).toString());
		s.setSelectedValue("-");
		s.addEventListener("onSelect", (ev) -> {
			rule.setCriteria((String) s.getSelectedValue());
			checkNewRowNeeded(rule);
		});
		r.appendChild(s);
		
		
		final Intbox i = new Intbox();
		i.setValue(rule.getParameter().intValue());
		i.addEventListener("onChange", (ev) -> {
			rule.setParameter(i.getValue());
			checkNewRowNeeded(rule);
		});
		r.appendChild(i);
		
		Div d = new Div();
		final Select s2 = new Select();
		final ImageClic pencil = new ImageClic("/img/pencil.svg");
		final Textbox msg = new Textbox();
		msg.setVisible(false);
		msg.setText(Labels.getLabel("tools.disable-users.message"));
		final Textbox sub = new Textbox();
		sub.setVisible(false);
		sub.setText(Labels.getLabel("tools.disable-users.defaultSubject"));
		final Textbox to = new Textbox();
		to.setVisible(false);
		to.setText("#{userName}");
		pencil.addEventListener("onClick", (ev) -> {
			EmailEditor.edit(to, sub, msg, (ev2) -> {
				rule.setEmailBody(msg.getValue());
				rule.setEmailSubject(msg.getValue());
				rule.setEmailCopy(to.getValue());
			});
		});
		pencil.setVisible(false);
		String[] actions = new String[] {
				"-", Labels.getLabel("tools.disable-users.action0"),
				"E", Labels.getLabel("tools.disable-users.action1"),
				"D", Labels.getLabel("tools.disable-users.action2"),
				"R", Labels.getLabel("tools.disable-users.action3")
		};
		s2.setOptions(optionsToJson(actions).toString());
		s2.setSelectedValue("-");
		s2.addEventListener("onSelect", (ev) -> {
			rule.setAction((String) s2.getSelectedValue());
			pencil.setVisible("E".equals(rule.getAction()));
			checkNewRowNeeded(rule);
			if ("E".equals(rule.getAction()))
				EmailEditor.edit(to, sub, msg, (ev2) -> {
					rule.setEmailBody(msg.getValue());
					rule.setEmailSubject(msg.getValue());
					rule.setEmailCopy(to.getValue());
				});
		});
		d.appendChild(s2);
		d.appendChild(pencil);
		d.appendChild(msg);
		r.appendChild(d);
	}

	public void checkNewRowNeeded(DisableObjectRule rule) {
		if (rules.getLast() == rule &&
				rule.getCriteria() != null &&
				!rule.getCriteria().equals("-"))
			addNewRow();
	}
	
	private JSONArray optionsToJson(String[] criteria) {
		JSONArray a = new JSONArray();
		for (int i = 0; i < criteria.length; i+=2) {
			JSONObject o = new JSONObject();
			o.put("value", criteria[i]);
			o.put("label", criteria[i+1]);
			a.put(o);
		}
		return a;
	}

	public void onTimer(Event event) throws Exception {
		if (wizard.getSelected() == 3 && currentProcess != null) {
			final boolean finished = currentProcess.isFinished();
			synchronized ( result ) {
				for (Object action[]: result) {
					JSONObject o = new JSONObject();
					o.put("userName", action[0]);
					o.put("fullName", action[1]);
					o.put("action", "E".equals(action[2]) ? Labels.getLabel("tools.disable-users.action1"):
						"D".equals(action[2]) ? Labels.getLabel("tools.disable-users.action2"):
						"R".equals(action[2]) ? Labels.getLabel("tools.disable-users.action3"): "");
					previewlistbox.response("add_"+rows, new AuInvoke(previewlistbox, "addRow", Integer.toString(rows),o.toString()));
					rows ++;
				}
				result.clear();
			}
			if (finished) {
				progressImage2.setVisible(false);
				timer.stop();
				if (currentProcess.getErrorMessage() != null) {
					Missatgebox.avis(currentProcess.getErrorMessage());
				}
				else
				{
					step3next.setVisible(true);
				}
				currentProcess  = null;
			}
		}
		if (wizard.getSelected() == 4 && currentProcess != null) {
			if (currentProcess.isFinished()) {
				progressImage3.setVisible(false);
				progressImage4.setVisible(true);
				timer.stop();
				if (currentProcess.getErrorMessage() != null) {
					((Label) progressImage4.getFirstChild()).setValue(currentProcess.getErrorMessage());
				}
			} else {
				progressPct.setValue((int)(100.0 * currentProcess.getProgress()));
			}
		}
	}
}
