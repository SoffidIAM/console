package es.caib.bpm.ui.main;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Tree;
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
import es.caib.zkib.events.SerializableEventListener;

public class MainMenuWindow extends Window implements AfterCompose {
	private boolean delayProcesses = false;
	
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
				String[] split = def.getName().split("/");
				Treechildren currentChildren = children;
				for (int i = 0; i < split.length && currentChildren != null; i++)
				{
					Treecell treecell = null;
					Treeitem treeitem = null;
					Treerow treerow = null;
					for ( Treeitem sibling: (Collection<Treeitem>)currentChildren.getChildren())
					{
						if (split[i].equals(sibling.getAttribute("path")))
						{
							treeitem = sibling;
							treecell = (Treecell) treeitem.getFirstChild().getFirstChild();
							currentChildren = treeitem.getTreechildren();
							break;
						}
					}
					if (treeitem == null)
					{
						treecell = new Treecell();
						treeitem = new Treeitem();
						treerow = new Treerow();
						treecell.setLabel(split[i]);
						treecell.setParent(treerow);
						treerow.setParent(treeitem);
						treeitem.setParent(currentChildren);
						treeitem.setAttribute("path", split[i]);
						if (i == split.length -1)
						{
							treecell.addEventListener("onClick", new CreateProcessListener(def)); // $NON-NLS-1$
						} else {
							currentChildren = new Treechildren();
							currentChildren.setParent(treeitem);
							treeitem.setOpen(false);
							treecell.addEventListener("onClick", treeEventListener); // $NON-NLS-1$
						}
					}
				}
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

	EventListener treeEventListener = new EventListener() {
		
		@Override
		public void onEvent(Event event) throws Exception {
			Treecell item = (Treecell) event.getTarget();
			item.getTreeitem().setOpen( ! item.getTreeitem().isOpen());
		}
	};
	
	public boolean setVisible(boolean visible) {
		if (visible) {
			afterCompose();
		}

		return super.setVisible(visible);
	}

	@Override
	public void afterCompose() {
		if (delayProcesses)
		{
			getFellow("availableprocesses").addEventListener("onOpen", new SerializableEventListener() {
				@Override
				public void onEvent(Event event) throws Exception {
					createProcesses();
				}
			} );
		}
		else
		{
			createProcesses();
		}
		createCustomObjects();
		registerListener ( );
	}

	private void registerListener() {
		Tree tree = (Tree) getFellowIfAny("menutree");
		if (tree != null)
		{
			for (Treeitem item: (Collection<Treeitem>) tree.getItems())
			{
				registerListener (item);
			}
		}
	}

	private void registerListener(Treeitem item) {
		if (item.getTreechildren() != null )
		{
			item.addEventListener("onOpen", onOpenEventListener);
			for (Treeitem child: (Collection<Treeitem>) item.getTreechildren().getChildren())
				registerListener(child);
		}
	}

	public boolean isDelayProcesses() {
		return delayProcesses;
	}

	public void setDelayProcesses(boolean delayProcesses) {
		this.delayProcesses = delayProcesses;
	}

	private void closeSiblings(Treeitem menuTreeitem) {
		Treechildren treechildren = (Treechildren) menuTreeitem.getParent();
		for ( Treeitem sibling: (Collection<Treeitem>) treechildren.getChildren())
		{
			if (sibling != menuTreeitem)
			{
				closeChildren (sibling);
			}
		}
		
		Component parent = treechildren.getParent();
		if (parent instanceof Treeitem)
			closeSiblings((Treeitem) parent);
		
	}

	private void closeChildren(Treeitem parent) {
		if (parent.getTreechildren() != null && parent.isOpen())
		{
			parent.setOpen(false);
			for ( Treeitem child: (Collection<Treeitem>) parent.getTreechildren().getChildren())
			{
				closeChildren(child);
			}
		}
	}

	private org.zkoss.zk.ui.event.EventListener onOpenEventListener = new org.zkoss.zk.ui.event.EventListener() {
		@Override
		public void onEvent(Event event) throws Exception {
			
			Treeitem treeitem = (Treeitem) event.getTarget();
			if (treeitem.isOpen())
				closeSiblings (treeitem);
		}
	};
}
