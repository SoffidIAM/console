package com.soffid.iam.web.group;

import java.io.IOException;
import java.util.Date;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.DynamicColumnsDatatable;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.NavigationBar;
import com.soffid.iam.web.component.ObjectAttributesDiv;
import com.soffid.iam.web.component.SearchBox;
import com.soffid.iam.web.popup.SelectColumnsHandler;
import com.soffid.iam.web.user.UserImporter;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.component.DateFormats;
import es.caib.zkib.component.Form;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathException;
import es.caib.zkib.jxpath.Variables;
import es.caib.zkib.zkiblaf.Missatgebox;


public class GroupHandler extends FrameHandler {
	Date currentDate;
	
	public GroupHandler() throws InternalErrorException {
		super();
	}

	
	
	@Override
	public void afterCompose() {
		super.afterCompose();

		DataModel model = getModel();
		Variables vars = model.getJXPathContext().getVariables();
		vars.declareVariable("canQueryGroupRoles", Security.isUserInRole("group:role:query"));

		HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		String name = req.getParameter("name");
		SearchBox sb = (SearchBox) getFellow("searchBox");
		if (name != null) {
			sb.addAttribute("name").setSearchFilter(name);
		}
		sb.search();
	}
	
	public void onChangeForm(Event event) {
		
	}
	
	public void addGroup(Event event) throws Exception {
		DataTree2 tree = (DataTree2) getListbox();
		String parent = (String) XPathUtils.getValue(  (DataSource) tree, "name");
		if (parent != null) {
			tree.addNew("/group");
			XPathUtils.setValue( (DataSource) getListbox(), "parentGroup", parent);
			showDetails();
		}
	}

	public void addNew() throws Exception {
		DataTree2 tree = (DataTree2) getListbox();
		tree.setSelectedIndex(new int[0]);
		tree.addNew("/group");
		showDetails();
	}

	public void changeColumns(Event event) throws IOException {
		SelectColumnsHandler.startWizard((DataTree2) getListbox());
	}



	@Override
	public void setPage(Page p) {
		super.setPage(p);
	}
	
	public void importCsv () throws IOException, CommitException, InternalErrorException, NamingException, CreateException {
		new GroupImporter().importCsv(this);
	}
	
	public void reorderGroup(Event event) {
		int[][] data = (int[][]) event.getData();
		int[] srcPos = data[0];
		int[] targetPos = data[1];
		DataTree2 tree = (DataTree2) getListbox();
		
		DataNode src = (DataNode) tree.getElementAt(srcPos);
		DataNode target = (DataNode) tree.getElementAt(targetPos);
		if (src != null) {
			Missatgebox.confirmaOK_CANCEL(
				String.format(Labels.getLabel("grups.zul.confirmMove"), target.get("name"), src.get("name") ),
				(ev) -> {
					if (ev.getName().equals("onOK")) {
						tree.setSelectedIndex(srcPos);
						XPathUtils.setValue(tree, "/parentGroup", target.get("name"));
						tree.setSelectedIndex(targetPos);
						getModel().commit();
					}
				});
		}
	}
	
	
	public void changeDate(Event ev) {
		Window w = (Window) getFellow("changeDate");
		CustomField3 cf = (CustomField3) w.getFellow("date");
		if (currentDate == null)
			cf.setValue(new Date());
		else
			cf.setValue(currentDate);
		w.doHighlighted();
	}
	
	public void applyDate(Event ev) {
		Window w = (Window) getFellow("changeDate");
		CustomField3 cf = (CustomField3) w.getFellow("date");
		Date date = (Date) cf.getValue();
		getModel().getVariables().declareVariable("date", date);
		w.setVisible(false);
		SearchBox sb = (SearchBox) getFellow("searchBox");
		sb.search();
		
		String s = DateFormats.getDateTimeFormat().format(date);
		NavigationBar n = (NavigationBar) getFellow("navigator1");
		Label l = (Label) getFellow("dateNavigator1");
		l.setVisible(true);
		l.setValue(" > "+ s);
		n.setLastAction("ref:frame.dateToday");
		n.refresh();

		n = (NavigationBar) getFellow("navigator2");
		n.setLastAction("ref:frame.dateToday");
		getFellow("dateNavigator2b").setVisible(true);
		l = (Label) getFellow("dateNavigator2");
		l.setVisible(true);
		l.setValue(s);
		n.refresh();

		ObjectAttributesDiv o = ((ObjectAttributesDiv)getFellow("groupAttributes"));
		o.setReadonly(true);
	}
	
	public void closeDate(Event ev) {
		Window w = (Window) getFellow("changeDate");
		w.setVisible(false);
	}

	public void dateToday(Event ev) throws CommitException {
		getModel().commit();
		
		Window w = (Window) getFellow("changeDate");
		currentDate = null;
		w.setVisible(false);
		getModel().getVariables().declareVariable("date", null);
		SearchBox sb = (SearchBox) getFellow("searchBox");
		sb.search();
		
		NavigationBar n = (NavigationBar) getFellow("navigator1");
		Label l = (Label) getFellow("dateNavigator1");
		l.setVisible(false);
		n.setLastAction(null);
		n.refresh();

		n = (NavigationBar) getFellow("navigator2");
		n.setLastAction("ref:frame.hideDetails");
		getFellow("dateNavigator2b").setVisible(false);
		l = (Label) getFellow("dateNavigator2");
		l.setVisible(false);
		n.refresh();
		
		ObjectAttributesDiv o = ((ObjectAttributesDiv)getFellow("groupAttributes"));
		o.setReadonly(false);
		hideDetails();
	}
}
