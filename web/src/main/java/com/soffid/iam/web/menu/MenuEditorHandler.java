package com.soffid.iam.web.menu;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AccessTree;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathNotFoundException;
import es.caib.zkib.zkiblaf.Missatgebox;

public class MenuEditorHandler extends FrameHandler {
	public MenuEditorHandler() throws InternalErrorException {
		com.soffid.iam.api.Tenant masterTenant = com.soffid.iam.ServiceLocator.instance().getTenantService().getMasterTenant();

	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
	}
		

	public void onChangeForm(Event ev) throws Exception {
		super.onChangeForm(ev);
		try {
			Long id = (Long) XPathUtils.getValue(getForm(), "id");
			CustomField3 cf = (CustomField3) getFellow("menu");
			cf.setReadonly(id != null);
			
			Boolean menu = (Boolean) XPathUtils.getValue(getForm(), "menu");
			getFellow("p_execucions").setVisible( ! Boolean.TRUE.equals(menu)); 
			getFellow("p_xml").setVisible( ! Boolean.TRUE.equals(menu)); 
			getFellow("tp_executions").setVisible( ! Boolean.TRUE.equals(menu)); 
			getFellow("tp_xml").setVisible( ! Boolean.TRUE.equals(menu)); 
			onChangeMenu(ev);
		} catch (JXPathNotFoundException e) {
			
		}
	}
	
	public void onChangeMenu(Event ev) {
		Boolean menu = (Boolean) XPathUtils.getValue(getForm(), "menu");
		((CustomField3) getFellow("menuType")).setVisible(Boolean.TRUE.equals(menu));
		((CustomField3) getFellow("system")).setVisible(! Boolean.TRUE.equals(menu));
	}

	public void addNew(Event ev) {
		DataTree2 dt = (DataTree2) getListbox();
		dt.commit();
		Long id = (Long) XPathUtils.getValue(getForm(), "id");
		AccessTree at = new AccessTree();
		at.setParentId(id);
		dt.addNew("/app", at);
	}
	
	public void reorder(Event event) {
		int[][] data = (int[][]) event.getData();
		int[] srcPos = data[0];
		int[] targetPos = data[1];
		DataTree2 tree = (DataTree2) getListbox();
		
		DataNode src = (DataNode) tree.getElementAt(srcPos);
		DataNode target = (DataNode) tree.getElementAt(targetPos);
		if (src != null) {
			Missatgebox.confirmaOK_CANCEL(
				String.format(Labels.getLabel("application.zul.confirmMove"), target.get("name"), src.get("name") ),
				(ev) -> {
					if (ev.getName().equals("onOK")) {
						moveTree(srcPos, targetPos, tree, src, target);
						
					}
				});
		}
	}

	private void moveTree(int[] srcPos, int[] targetPos, DataTree2 tree, DataNode src, DataNode target)
			throws InternalErrorException, NamingException, CreateException, CommitException {
		tree.setSelectedIndex(srcPos);
		final AccessTree srcTree = (AccessTree) src.getInstance();
		Long previousParent =  srcTree.getParentId();
		final AccessTree targetTree = (AccessTree)target.getInstance();
		// Apply change in user interface tree
		XPathUtils.setValue(tree, "/parentId", targetTree.getId());
		getModel().commit();
		// Undo and redo in the database
		srcTree.setParentId(previousParent);
		EJBLocator.getEntryPointService().moveApplicationAccessTreeMenu(srcTree, targetTree);
		srcTree.setParentId(targetTree.getId());
	}

}
