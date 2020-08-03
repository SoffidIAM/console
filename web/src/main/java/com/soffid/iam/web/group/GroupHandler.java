package com.soffid.iam.web.group;

import java.io.IOException;

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


public class GroupHandler extends FrameHandler {

	public GroupHandler() throws InternalErrorException {
		super();
	}

	
	
	@Override
	public void afterCompose() {
		super.afterCompose();

		DataModel model = getModel();
		Variables vars = model.getJXPathContext().getVariables();
		vars.declareVariable("canQueryGroupRoles", Security.isUserInRole("group:role:query"));

		SearchBox sb = (SearchBox) getFellow("searchBox");
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
		SelectColumnsHandler.startWizard((DynamicColumnsDatatable) getListbox());
	}



	@Override
	public void setPage(Page p) {
		super.setPage(p);
	}
	
}
