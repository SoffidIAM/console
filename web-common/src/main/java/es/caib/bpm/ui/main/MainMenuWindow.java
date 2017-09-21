package es.caib.bpm.ui.main;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Window;

import com.soffid.iam.api.CustomObjectType;

import es.caib.bpm.servei.ejb.BpmEngine;
import es.caib.bpm.toolkit.BPMApplication;
import es.caib.bpm.ui.tree.ApplicationTreecell;
import es.caib.bpm.vo.ProcessDefinition;

public class MainMenuWindow extends Window implements AfterCompose {

	private static final long serialVersionUID = 1L;

	private void createCustomObjects() {
		Treeitem item = (Treeitem) getFellowIfAny("customObjectTypes"); //$NON-NLS-1$
		if (null == item) // In selfserver "customObjectTypes" doesn't exist
			return;
		Treechildren children = item.getTreechildren();
		boolean visible = false;

		children.getChildren().clear();
		try {
			Collection<CustomObjectType> types = com.soffid.iam.EJBLocator.getAdditionalDataService()
					.findCustomObjectTypeByJsonQuery(null);
			BpmEngine engine = BPMApplication.getEngine();

			for (com.soffid.iam.api.CustomObjectType type : types) {
				Treeitem ti = new Treeitem();
				ti.setLabel(type.getDescription());

				ApplicationTreecell treecell = new ApplicationTreecell();
				Treeitem treeitem = new Treeitem();
				Treerow treerow = new Treerow();
				treecell.setLabel(type.getDescription());
				treecell.setPagina("/customObjects.zul?type="+type.getName());
				treecell.setParent(treerow);
				treerow.setParent(treeitem);
				treeitem.setParent(children);
			}

			// Check no available process
			if (!item.isEmpty()) {
				visible = true;
			}

			item.setVisible(visible);

		}

		catch (Exception e) {
			throw new UiException(e);
		}
	}

	private void createProcesses() {
		Treeitem item = (Treeitem) getFellow("availableprocesses"); //$NON-NLS-1$
		Treechildren children = item.getTreechildren();
		boolean visible = false;

		children.getChildren().clear();
		try {
			BpmEngine engine = BPMApplication.getEngine();
			List l = engine.findInitiatorProcessDefinitions();

			for (Iterator it = l.iterator(); it.hasNext();) {
				ProcessDefinition def = (ProcessDefinition) it.next();
				Treecell treecell = new Treecell();
				Treeitem treeitem = new Treeitem();
				Treerow treerow = new Treerow();
				treecell.setLabel(def.getName());
				treecell.addEventListener("onClick", new CreateProcessListener(def)); // $NON-NLS-1$
				treecell.setParent(treerow);
				treerow.setParent(treeitem);
				treeitem.setParent(children);
			}

			// Check no available process
			if (!item.isEmpty()) {
				visible = true;
			}

			item.setVisible(visible);

		}

		catch (Exception e) {
			throw new UiException(e);
		}
	}

	public boolean setVisible(boolean visible) {
		if (visible) {
			afterCompose();
		}

		return super.setVisible(visible);
	}

	@Override
	public void afterCompose() {
		createProcesses();
		createCustomObjects();
	}
}
