package com.soffid.iam.web.group;

import java.io.IOException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;

import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.DynamicColumnsDatatable;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.SearchBox;
import com.soffid.iam.web.popup.SelectColumnsHandler;
import com.soffid.iam.web.user.UserImporter;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.Variables;
import es.caib.zkib.zkiblaf.Missatgebox;


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
}
