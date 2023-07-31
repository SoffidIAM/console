package com.soffid.iam.web.issue;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.Issue;
import com.soffid.iam.api.IssueActionDefinition;
import com.soffid.iam.api.IssuePolicy;
import com.soffid.iam.api.IssuePolicyAction;
import com.soffid.iam.api.IssueStatus;
import com.soffid.iam.web.WebDataType;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.SearchBox;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathNotFoundException;


public class IssueHandler extends FrameHandler {

	private List<IssueActionDefinition> actions;
	private ManualActionHandler handler;
	private IssueActionDefinition currentAction;

	public IssueHandler() throws InternalErrorException, NamingException, CreateException {
		super();
		actions = EJBLocator.getIssueService().listManualActions();
	}

	@Override
	public void onChangeForm(Event ev) throws Exception {
		super.onChangeForm(ev);
		try {
			Issue i = (Issue) XPathUtils.eval(getListbox(), "instance");
			Div buttons = (Div) getFellow("buttons");
			for (Iterator iterator = buttons.getChildren().iterator(); iterator.hasNext();) {
				Button b = (Button) iterator.next();
				IssueActionDefinition a = (IssueActionDefinition) b.getAttribute("action");
				if (a != null) {
					b.setVisible(a.getIssueTypes().contains(i.getType()) 
							 && i.getStatus() != IssueStatus.SOLVED_NOTADUPLICATE
							 && i.getStatus() != IssueStatus.SOLVED);
				}
			}
			getFellow("ack_button").setVisible(i.getStatus() == IssueStatus.NEW);
			getFellow("solve_button").setVisible(i.getStatus() == IssueStatus.ACKNOWLEDGED);
		} catch (JXPathNotFoundException e) {
			
		}
	}

	@Override
	public void afterCompose() {
		super.afterCompose();
		Div buttons = (Div) getFellow("buttons");
		for (IssueActionDefinition def: actions) {
			Button b = new Button();
			b.setLabel(Labels.getLabel(def.getLabel()));
			b.setAttribute("action", def);
			b.addEventListener("onClick", (ev) -> {doAction(ev);});
			b.setVisible(false);
			buttons.appendChild(b);
		}
	}
	
	
	private void doAction(Event ev) throws NamingException, CreateException, InternalErrorException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Issue issue = (Issue) XPathUtils.getValue(getForm(), "instance");
		currentAction = (IssueActionDefinition) ev.getTarget().getAttribute("action");
		
		Window w = (Window) getFellow("action_window");
		
		Label action = (Label) w.getFellow("action");
		action.setValue(Labels.getLabel(currentAction.getLabel()));
		
		Div fields = (Div) w.getFellow("fields");
		fields.getChildren().clear();
		
		
		for (DataType dt: currentAction.getParameters()) {
			InputField3 ifield = new InputField3();
			ifield.setId("_"+dt.getName());
			ifield.setDataType( new WebDataType(dt) );
			ifield.setParent(fields);
			ifield.afterCompose();
			ifield.onCreate();
			if (dt.getName().equals("body")) {
				StringBuffer sb = new StringBuffer();
				sb.append("Issue number: "+issue.getNumber()).append("<br />");
				IssueForm issueForm = (IssueForm) getFellow("issueform");
				for (Object field: issueForm.getChildren()) {
					if (field instanceof InputField3) {
						final InputField3 inputField3 = (InputField3) field;
						Object v = inputField3.getValue();
						if (v != null && inputField3.isVisible()) {
							sb  .append("<p>")
								.append(inputField3.getLabel());
							if (v instanceof List) {
								boolean first = true;
								for (Object vv: (List) v) {
									if (!first)
										sb.append("</p><p style='margin-left: 100px'>");
									first = false;
									sb .append(inputField3.translateToUserInterface( toHtml(v)));
								}
							} else {
								
								sb .append(inputField3.translateToUserInterface( toHtml(v)));
							}
							sb.append("</p>");
						}
					}
				}
				ifield.setValue(sb.toString());
			}
		}
		
		w.doHighlighted();
		handler = (ManualActionHandler) Class.forName(currentAction.getHandler()).
				getConstructor().
				newInstance();
		handler.init(w, Arrays.asList(issue));
	}

	private String toHtml(Object v) {
		return v.toString().replace(">", "&gt;")
				.replace("<", "&lt")
				.replace("\n", "</p><p style='margin-left: 100px'>");
	}
	
	public void closeAction(Event ev) {
		Window w = (Window) getFellow("action_window");
		w.setVisible(false);
	}

	public void applyAction(Event ev) throws Exception {
		Issue issue = (Issue) XPathUtils.getValue(getForm(), "instance");
		Window w = (Window) getFellow("action_window");
		Map<String, Object> attributes = new java.util.HashMap<>();
		for (DataType dt: currentAction.getParameters()) {
			InputField3 ifield = (InputField3) w.getFellow("_"+dt.getName());
			attributes.put(dt.getName(), ifield.getValue());
		}
		handler.process(w, Arrays.asList(issue), attributes);
		w.setVisible(false);
		for (Issue issue2: EJBLocator.getIssueService()
				.findIssuesByJsonQuery("id eq "+issue.getId(), null, null)
				.getResources()) {
			XPathUtils.setValue(getForm(), "status", issue2.getStatus());
			XPathUtils.setValue(getForm(), "description", issue2.getDescription());
			XPathUtils.setValue(getForm(), "users", issue2.getUsers());
			XPathUtils.setValue(getForm(), "performedActions", issue2.getPerformedActions());
		}
	}

	public void ack(Event ev) throws Exception {
		XPathUtils.setValue(getListbox(), "status", IssueStatus.ACKNOWLEDGED);
		getModel().commit();
		onChangeForm(ev);
	}

	public void solve(Event ev) throws Exception {
		XPathUtils.setValue(getListbox(), "status", IssueStatus.SOLVED);
		getModel().commit();
		onChangeForm(ev);
	}

	List<Issue> currentIssues;
	public void multiAction(Event ev) throws NamingException, CreateException, InternalErrorException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		LinkedList<IssueActionDefinition> enabledActions = new LinkedList<>(actions);
		DataTable dt = (DataTable) getListbox();
		int[] rows = dt.getSelectedIndexes();
		if (rows.length == 0) return;
		boolean ack = true;
		boolean solve = true;
		currentIssues = new LinkedList<>();
		for (int i = 0; i < rows.length; i++) {
			String path = dt.getItemXPath(rows[i]);
			Issue issue = (Issue) XPathUtils.eval(getModel(), path+"/instance");
			currentIssues.add(issue);
			String type = issue.getType();
			for (Iterator<IssueActionDefinition> iterator = enabledActions.iterator(); 
					iterator.hasNext();) {
				IssueActionDefinition ia = iterator.next();
				if (! ia.getIssueTypes().contains(type)) {
					iterator.remove();
				}
			}
			if (issue.getStatus() == IssueStatus.ACKNOWLEDGED)
				ack = false;
			if (issue.getStatus() == IssueStatus.SOLVED || issue.getStatus() == IssueStatus.SOLVED_NOTADUPLICATE)
				ack = solve = false;
		}
		
		if (enabledActions.isEmpty() && !ack && !solve)
			return;
		
		Window w = (Window) getFellow("multiaction_window");
		w.getFellow("step1").setVisible(true);
		Radiogroup rg = (Radiogroup) w.getFellow("radio");
		rg.getChildren().clear();
		for (IssueActionDefinition action: enabledActions) {
			Radio r = new Radio(Labels.getLabel(action.getLabel()));
			r.setAttribute("action", action);
			rg.appendChild(r);
		}
		if (ack) {
			Radio r = new Radio(Labels.getLabel("com.soffid.iam.api.IssueActionDefinition.auto-ack"));
			r.setAttribute("action", "ack");
			rg.appendChild(r);
		}
		if (solve) {
			Radio r = new Radio(Labels.getLabel("com.soffid.iam.api.IssueActionDefinition.solve"));
			r.setAttribute("action", "solve");
			rg.appendChild(r);
		}
		w.getFellow("step2").setVisible(false);
		w.doHighlighted();
	}
	
	public void closeBulkAction(Event ev) {
		Window w = (Window) getFellow("multiaction_window");
		w.setVisible(false);
	}

	public void nextBulkAction(Event ev) throws NamingException, CreateException, InternalErrorException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		DataTable table = (DataTable) getListbox();
		int[] rows = table.getSelectedIndexes();

		Window w = (Window) getFellow("multiaction_window");
		Radiogroup rg = (Radiogroup) w.getFellow("radio");
		final Radio radio = rg.getSelectedItem();
		if (radio == null)
			return;
		Object actionAtt = radio.getAttribute("action");
		if (actionAtt instanceof IssueActionDefinition)
			currentAction = (IssueActionDefinition) actionAtt;
		else
			currentAction = null;
		
		Label action = (Label) w.getFellow("action");
		action.setValue(radio.getLabel());
		
		Div fields = (Div) w.getFellow("fields");
		fields.getChildren().clear();
		
		if (currentAction != null) {
			for (DataType dt: currentAction.getParameters()) {
				InputField3 ifield = new InputField3();
				ifield.setId("_"+dt.getName());
				ifield.setDataType( new WebDataType(dt) );
				ifield.setParent(fields);
				ifield.afterCompose();
				ifield.onCreate();
				if (dt.getName().equals("body")) {
					StringBuffer sb = new StringBuffer();
					
					sb.append("Issue number: ");
					for (int i = 0; i < rows.length; i++) {
						String path = table.getItemXPath(rows[i]);
						Issue issue = (Issue) XPathUtils.eval(getModel(), path+"/instance");
						sb.append(issue.getNumber()).append("<br />");
					}
					ifield.setValue(sb.toString());
				}
			}
			handler = (ManualActionHandler) Class.forName(currentAction.getHandler()).
					getConstructor().
					newInstance();
			handler.init(w, currentIssues);
		}
		
		w.getFellow("step1").setVisible(false);
		w.getFellow("step2").setVisible(true);
	}

	public void backAction(Event ev) throws NamingException, CreateException, InternalErrorException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Window w = (Window) getFellow("multiaction_window");
		w.getFellow("step1").setVisible(true);
		w.getFellow("step2").setVisible(false);
	}

	public void applyBulkAction(Event ev) throws Exception {
		Window w = (Window) getFellow("multiaction_window");
		Map<String, Object> attributes = new java.util.HashMap<>();
		Radiogroup rg = (Radiogroup) w.getFellow("radio");
		final Radio radio = rg.getSelectedItem();
		Object actionAtt = radio.getAttribute("action");
		if ("ack".equals(actionAtt)) {
			for (Issue issue: currentIssues) {
				if (issue.getStatus() == IssueStatus.NEW) {
					issue.setStatus(IssueStatus.ACKNOWLEDGED);
					EJBLocator.getIssueService().update(issue);
				}
			}
		}
		else if ("solve".equals(actionAtt)) {
			for (Issue issue: currentIssues) {
				if (issue.getStatus() == IssueStatus.NEW || 
						issue.getStatus() == IssueStatus.ACKNOWLEDGED ) {
					issue.setStatus(IssueStatus.SOLVED);
					EJBLocator.getIssueService().update(issue);
				}
			}
		} else {
			for (DataType dt: currentAction.getParameters()) {
				InputField3 ifield = (InputField3) w.getFellow("_"+dt.getName());
				attributes.put(dt.getName(), ifield.getValue());
			}
			handler.process(w, currentIssues, attributes);
		}
		w.setVisible(false);

		SearchBox sb = (SearchBox) getFellow("searchBox");
		sb.search(true);
	}
}
