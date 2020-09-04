package com.soffid.iam.web.application;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;

import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.DynamicColumnsDatatable;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.SearchBox;
import com.soffid.iam.web.popup.SelectColumnsHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.Variables;


public class ApplicationHandler extends FrameHandler {

	public ApplicationHandler() throws InternalErrorException {
		super();
	}

	@Override
	public void afterCompose() {
		super.afterCompose();

		DataModel model = getModel();
		Variables vars = model.getJXPathContext().getVariables();


		HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		String name = req.getParameter("name");
		SearchBox sb = (SearchBox) getFellow("searchBox");
		if (name != null) {
			sb.addAttribute("name").setSearchFilter(name);
			sb.search();
		}
		sb.search();
	}
	
	public void onChangeForm(Event event) {
		
	}
	
	public void addApplication(Event event) throws Exception {
		DataTree2 tree = (DataTree2) getListbox();
		String parent = (String) XPathUtils.getValue(  (DataSource) tree, "name");
		if (parent != null) {
			tree.addNew("/application");
			XPathUtils.setValue( (DataSource) getListbox(), "parent", parent);
			showDetails();
		}
	}

	public void addNew() throws Exception {
		DataTree2 tree = (DataTree2) getListbox();
		tree.setSelectedIndex(new int[0]);
		tree.addNew("/application");
		showDetails();
	}

	public void changeColumns(Event event) throws IOException {
		SelectColumnsHandler.startWizard((DynamicColumnsDatatable) getListbox());
	}



	@Override
	public void setPage(Page p) {
		super.setPage(p);
	}
	
}
