package es.caib.bpm.ui.main;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Window;

import es.caib.bpm.toolkit.BPMApplication;
import es.caib.bpm.ui.tree.ApplicationTreecell;
import es.caib.bpm.vo.ProcessDefinition;
import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.ApplicationComponent;

public class FavoriteMenuWindow extends Window implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private LinkedList recents = new LinkedList();
	private final int MAX_RECENTS = 5;

	
	private Treeitem getRecentsMenu() {
		return  (Treeitem) getFellow("recents"); //$NON-NLS-1$
	}
	
	private static ApplicationComponent getApplication() {
		Execution ex = Executions.getCurrent();
		ApplicationComponent app = (ApplicationComponent) ex.getDesktop().getAttribute("zkiblaf$application"); //$NON-NLS-1$
		return app;
	}	

	public void onCreate ()
	{ // Establim els recents de l'usuari:
		//Treeitem item = getRecentsMenu();
		//Treechildren children = item.getTreechildren();
		// Refresquem els recents
		//ponRecents();
		try {
			//TODO: Hem de carregar els favorits de UsuariSEU
			
			/*BPMEngineLocal engine = BPMApplication.getEngine();
			List l = engine.findInitiatorProcessDefinitions();
			for ( Iterator it = l.iterator(); it.hasNext(); )
			{
				ProcessDefinition def = (ProcessDefinition) it.next();
				Treecell treecell = new Treecell();
				Treeitem treeitem = new Treeitem ();
				Treerow treerow = new Treerow ();
				treecell.setLabel(def.getName());
				treecell.addEventListener("onClick", new CreateProcessListener(def));
				treecell.setParent(treerow);
				treerow.setParent(treeitem);
				treeitem.setParent(children);
			}*/
			// Hem d'obtindre els recents de l'usuari
			
			// Guardem els favorits com a atribut del desktop
			//Execution ex = Executions.getCurrent();
			//ex.getDesktop().setAttribute("zkiblaf$favorits", this);
			/*Session zksessio = getApplication().getDesktop().getSession();
			if (zksessio!=null && zksessio.getAttribute("zkiblaf$favorits")==null)
				zksessio.setAttribute("zkiblaf$favorits", this);*/

		} catch (Exception e) {
			throw new UiException(e);
		}
		
	}
	
	private ApplicationTreecell creaElementMenu ( String page, String langlabelRef) {
		ApplicationTreecell treecell = new ApplicationTreecell();
		treecell.setPagina(page);
		treecell.setLanglabel(langlabelRef);
		
		return treecell;
	}
	
	private void ponRecents() {
		// Afegim el nou
		Treeitem item = getRecentsMenu();
		
		Treechildren children = item.getTreechildren();
		// Només mostrem els darrers MAX_RECENTS
		if (children.getChildren().size()!=0)
			children.getChildren().clear();
		for (Iterator it = recents.iterator(); it.hasNext();) {
			Treecell treecell = (Treecell) it.next();
			
			Treeitem treeitem = new Treeitem ();
			Treerow treerow = new Treerow ();
			treecell.setParent(treerow);
			treerow.setParent(treeitem);

			treeitem.setParent(children); // l'afegim a l'arbre
		}
	}
	
	public void addRecentPage (String pagina, String langlabelRef) {
		Treeitem pareMenuRecents = getRecentsMenu();
		Treechildren menusRecents = pareMenuRecents.getTreechildren();
		
		
		// Hem d'eliminar el darrer??
		if (recents.size() >= MAX_RECENTS) {
			recents.removeLast(); //eliminem el darrer
			// Esborrem el darrer
			menusRecents.getChildren().remove(recents.size()-1);
		} 

		// Creem la nova entrada:
		ApplicationTreecell nou = creaElementMenu(pagina, langlabelRef);
		
		// Eliminem els repetits
		int pos = 0;
		for (Iterator it = recents.iterator(); it.hasNext(); ) {
			ApplicationTreecell item = (ApplicationTreecell) it.next();
			
			if (item.getPagina().equals(pagina)) {
				it.remove(); //l'eliminem (ja el teniem)
				// L'hem d'eliminar de l'arbre
				menusRecents.getChildren().remove(pos);
			}
			pos++;			
		}
		

		recents.addFirst(nou); // afegim el nou
		
		Treeitem nouTreeitem = new Treeitem ();
		Treerow treerow = new Treerow ();
		nou.setParent(treerow);
		treerow.setParent(nouTreeitem);

		//nouTreeitem.setParent(menusRecents); // l'afegim a l'arbre
		menusRecents.getChildren().add(0,nouTreeitem);

		//ponRecents();

		
	}
}
