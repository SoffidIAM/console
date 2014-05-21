package es.caib.bpm.ui.tree;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.au.out.AuInvoke;
import org.zkoss.zk.au.out.AuScript;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Window;

import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.ApplicationComponent;

/**
 * Permite configurar l'accés a les pàgines implementant el 
 * event listener correponets i l'etiqueta corresponent a 
 * l'idioma actual
 * 
 * Alejandro Usero Ruiz - 30 d'agost de 2011
 * 
 * @author u88683
 *
 */
public class ApplicationTreecell extends Treecell {
	
	private static final long serialVersionUID = 1L;
	//referència a la traducció en el properties corresponent
	String langlabel = null;
	// Pàgina a la que fem referència
	String pagina = null;
	
	
	/* Sobreescribim els mètodes pare */

	/*
	public String getColumnHtmlPostfix() {

		return super.getColumnHtmlPostfix();
	}

	public String getColumnHtmlPrefix() {
		// TODO Auto-generated method stub
		return super.getColumnHtmlPrefix();
	}

	public int getColumnIndex() {
		// TODO Auto-generated method stub
		return super.getColumnIndex();
	}

	public String getLabelAttrs() {
		// TODO Auto-generated method stub
		return super.getLabelAttrs();
	}

	public int getLevel() {
		// TODO Auto-generated method stub
		return super.getLevel();
	}

	public int getMaxlength() {
		// TODO Auto-generated method stub
		return super.getMaxlength();
	}

	public String getOuterAttrs() {
		// TODO Auto-generated method stub
		return super.getOuterAttrs();
	}

	protected String getRealStyle() {
		// TODO Auto-generated method stub
		return super.getRealStyle();
	}

	public int getSpan() {
		// TODO Auto-generated method stub
		return super.getSpan();
	}

	public Tree getTree() {
		// TODO Auto-generated method stub
		return super.getTree();
	}

	public Treecol getTreecol() {
		// TODO Auto-generated method stub
		return super.getTreecol();
	}

	public Treeitem getTreeitem() {
		// TODO Auto-generated method stub
		return super.getTreeitem();
	}

	public String getWidth() {
		// TODO Auto-generated method stub
		return super.getWidth();
	}

	public void setParent(Component parent) {
		// TODO Auto-generated method stub
		super.setParent(parent);
	}

	public void setSpan(int span) {
		// TODO Auto-generated method stub
		super.setSpan(span);
	}

	public void setWidth(String width) {
		// TODO Auto-generated method stub
		super.setWidth(width);
	}
	*/

	/* mètodes específics */
	
	public String getLanglabel() {
		return langlabel;
	}

	public String getPagina() {
		return pagina;
	}

	public void setLanglabel(String langlabel) {
		this.langlabel = langlabel;
		super.setLabel(Labels.getLabel(langlabel));
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}
	
	public void onClick(Event evento) throws Exception {
		
		ApplicationComponent app = Application.getApplication();
		
		// Tanquem el menu
		Window menu = (Window) Path.getComponent(app.getSpaceOwner(), app.getMenu());
		if (menu!=null) menu.setVisible(false);
				
		if (!(evento instanceof org.zkoss.zk.ui.event.MouseEvent)) return;

		org.zkoss.zk.ui.event.MouseEvent event = (org.zkoss.zk.ui.event.MouseEvent) evento;
		
		// Detectem si ha polsat CTRL
		int ctrlPulsada = event.getKeys() &  org.zkoss.zk.ui.event.MouseEvent.CTRL_KEY;

		// Mostrem la pàgina
		if (getPagina() != null) {
			String path = Executions.getCurrent().getDesktop().getRequestPath();
			int i = path.indexOf('?');
			if (i >= 0)
				path = path.substring(0, i);
			HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();;
			path = req.getContextPath()+path;
			path = path + "?target="+getPagina();
			if (app.isAllowOpenMultipleTabs() &&  ctrlPulsada != 0) {
				org.zkoss.zk.ui.Desktop desk = app.getDesktop();
				if ( desk != null && desk.getSession() != null) {
					// el guardem per al possible reload
					org.zkoss.zk.ui.Executions.getCurrent().sendRedirect(path, "_blank"); //$NON-NLS-1$ //$NON-NLS-2$
				}
			} else {
				
				Executions.getCurrent().addAuResponse("setUrl", 
						new AuScript(this, 
								String.format("window.history.pushState(\"%s\", \"%s\", window.location.protocol+\"//\"+window.location.host+\"%s\");",
										getPagina(), getLabel(), path)));
				
				es.caib.zkib.zkiblaf.Application.setPage(pagina);
			}
		}
		
		// Guardem l'arxiu a recents
		/*Window favsmenu = Application.getFavoritesMenu();
		if (favsmenu != null) {
			if (favsmenu instanceof FavoriteMenuWindow) {
				FavoriteMenuWindow fv = (FavoriteMenuWindow) favsmenu;
				fv.addRecentPage(pagina, langlabel);
			}
		}*/
				
						
	}
	
}
