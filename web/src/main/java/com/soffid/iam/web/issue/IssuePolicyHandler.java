package com.soffid.iam.web.issue;

import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.IssueActionDefinition;
import com.soffid.iam.api.IssuePolicy;
import com.soffid.iam.api.IssuePolicyAction;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.SearchBox;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;


public class IssuePolicyHandler extends FrameHandler {

	public IssuePolicyHandler() throws InternalErrorException {
		super();
	}

	@Override
	public void onChangeForm(Event ev) throws Exception {
		super.onChangeForm(ev);
	}

	public void addAction(Event ev) throws Exception {
		IssuePolicy p = (IssuePolicy) XPathUtils.eval(getListbox(), "instance");
		
		XPathUtils.createPath((DataSource) getListbox(), "/actions", new IssuePolicyAction());
		DataTable table = (DataTable) getFellow("actions");
		table.setSelectedIndex(p.getActions().size()-1);
		
		org.zkoss.zul.Window w = (Window) getFellow("action_window");
		w.doHighlighted();
		changeAction(ev);
	}

	@Override
	public void afterCompose() {
		super.afterCompose();
		SearchBox sb = (SearchBox) getFellow("searchBox");
		sb.search();
		
		try {
			List<String> v = new LinkedList<>();
			for (IssueActionDefinition dt: EJBLocator.getIssuePolicyService().listAutomaticActions()) {
				String t = URLEncoder.encode(dt.getName(), "UTF-8")+": "+Labels.getLabel(dt.getLabel());
				v.add(t);
			}
			org.zkoss.zul.Window w = (Window) getFellow("action_window");
			CustomField3 f = (CustomField3) w.getFellow("action");
			f.setListOfValues(v.toArray(new String[v.size()]));
			f.createField();
			f.invalidate();
		} catch (Exception e) {
			throw new UiException(e);
		}
	}
	
	public void closeDetails() throws CommitException {
		org.zkoss.zul.Window w = (Window) getFellow("action_window");
		w.setVisible(false);
		
		DataTable actions = (DataTable) getFellow("actions");
		actions.updateClientRow(actions.getSelectedIndex());
	}

	public void changeAction(Event event) throws InternalErrorException, NamingException, CreateException {
		org.zkoss.zul.Window w = (Window) getFellow("action_window");

		IssuePolicyAction action = (IssuePolicyAction) XPathUtils.eval(getFellow("actions"), "/.");
		
		for (String s: new String[] {"body", "subject", "emailAddress", "script", "processDefinition"})
			w.getFellow(s).setVisible(false);
		
		for (IssueActionDefinition dt: EJBLocator.getIssuePolicyService().listAutomaticActions()) {
			if (dt.getName().equals(action.getAction())) {
				for (DataType parameter: dt.getParameters()) {
					w.getFellow(parameter.getName()).setVisible(true);
				}
			}
		}
		
	}
	
	public void selectAction(Event event) throws InternalErrorException, NamingException, CreateException {
		org.zkoss.zul.Window w = (Window) getFellow("action_window");
		w.doHighlighted();
		changeAction(event);
	}
}
