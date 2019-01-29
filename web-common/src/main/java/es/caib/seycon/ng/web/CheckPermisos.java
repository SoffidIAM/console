package es.caib.seycon.ng.web;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

import com.soffid.iam.utils.AutoritzacionsUsuari;
import com.soffid.iam.utils.Security;

/**
 * Classe per fer la comprovació de permisos als ZULS, rep com a paràmetre un
 * identificador per obtindre el tipus de verificar.
 * 
 * Alejandro Usero Ruiz - 10/08/2011
 * 
 * @author u88683
 * 
 */
public class CheckPermisos implements Initiator {

	public void doAfterCompose(Page arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	public boolean doCatch(Throwable arg0) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public void doFinally() throws Exception {
		// TODO Auto-generated method stub

	}

	public void doInit(Page arg0, Object[] args) throws Exception {

		if (args == null || args.length == 0 || args.length > 0
				&& !(args[0] instanceof String))
			return; // No fem res

		String pagina = (String) args[0];
		boolean redirect = false;

		if ("agents".equals(pagina)) { //$NON-NLS-1$
			if (!AutoritzacionsUsuari.hasViewAgentsSEU()) {
				redirect = true;
			}
		} else if ("aplicacions".equals(pagina)) { //$NON-NLS-1$
			if (!AutoritzacionsUsuari.hasViewAplicacionsSEU()) {
				redirect = true;
			}
		} else if ("directory".equals(pagina)) { //$NON-NLS-1$
			if (!Security.isUserInRole("selfservice:directory")) {
				redirect = true;
			}
		} else if ("auditoria".equals(pagina)) { //$NON-NLS-1$
			if (!AutoritzacionsUsuari.hasViewAuditoriaSEU() &&
					!AutoritzacionsUsuari.hasQueryCustomAuditoria()) {
				redirect = true;
			}
		} else if ("autoritzacions".equals(pagina)) { //$NON-NLS-1$
			if (!AutoritzacionsUsuari.hasViewAutoritzacionsSEU()) {
				redirect = true;
			}
		} else if ("correu".equals(pagina)) { //$NON-NLS-1$
			if (!AutoritzacionsUsuari.hasViewCorreuSEU()) {
				redirect = true;
			}
		} else if ("dominiscorreu".equals(pagina)) { //$NON-NLS-1$
			if (!AutoritzacionsUsuari.hasViewDominisCorreuSEU()) {
				redirect = true;
			}			
		} else if ("dadesAddicionals".equals(pagina)) { //$NON-NLS-1$
			if (!AutoritzacionsUsuari.hasViewDadesAddicionalsSEU()) {
				redirect = true;
			}
		} else if ("grups".equals(pagina)) { //$NON-NLS-1$
			if (!AutoritzacionsUsuari.hasViewGrupsSEU()) {
				redirect = true;
			}
		} else if ("impressores".equals(pagina)) { //$NON-NLS-1$
			if (!AutoritzacionsUsuari.hasViewImpressoresSEU()) {
				redirect = true;
			}
		} else if ("lopd".equals(pagina)) { //$NON-NLS-1$
			if (!AutoritzacionsUsuari.hasViewLopdSEU()) {
				redirect = true;
			}
		/*} else if ("maquines".equals(pagina)) {
			if (!AutoritzacionsUsuari.hasViewMaquinesSEU()) {
				redirect = true;
			}*/
		} else if ("parametres".equals(pagina)) { //$NON-NLS-1$
			if (!AutoritzacionsUsuari.hasViewParametresSEU()) {
				redirect = true;
			}
		} else if ("registreAcces".equals(pagina)) { //$NON-NLS-1$
			if (!AutoritzacionsUsuari.hasViewRegistreAccesSEU()) {
				redirect = true;
			}
		} else if ("serveis".equals(pagina)) { //$NON-NLS-1$
			if (!AutoritzacionsUsuari.hasViewServeisSEU()) {
				redirect = true;
			}
		} else if ("tipusUO".equals(pagina)) { //$NON-NLS-1$
			if (!AutoritzacionsUsuari.hasViewTipusUOSEU()) {
				redirect = true;
			}
		} else if ("usuaris".equals(pagina)) { //$NON-NLS-1$
			if (!AutoritzacionsUsuari.hasViewUsuarisSEU()) {
				redirect = true;
			}
		/*} else if ("xarxes".equals(pagina)) {
			if (!AutoritzacionsUsuari.hasViewXarxesSEU()) {
				redirect = true;
			}*/
		/*} else if ("menusIntranet".equals(pagina)) {
			if (!AutoritzacionsUsuari.hasViewMenusIntranetSEU()) {
				redirect = true;
			}*/
		} else if ("seyconserver".equals(pagina)) { //$NON-NLS-1$
			if (!AutoritzacionsUsuari.hasViewSeyconServerSEU()) {
				redirect = true;
			}
		} else if ("federacioIdentitats".equals(pagina)) { //$NON-NLS-1$
			if (!AutoritzacionsUsuari.hasViewFederacioIdentitatsSEU()) {
				redirect = true;
			}
		} else if ("accounts".equals(pagina)) { //$NON-NLS-1$
			if (!AutoritzacionsUsuari.hasViewAccountsSEU()) {
				redirect = true;
			}
		} else if ("dominiUsuaris".equals(pagina)) { //$NON-NLS-1$
			if (!AutoritzacionsUsuari.hasViewDominiUsuaris()) {
				redirect = true;
			}
		}
		
		


		if (redirect) {
			try {
				//Perquè no es quede a la sessió i la torne a recarregar
				Executions.getCurrent().getDesktop().getSession().setAttribute("paginaActual", null); //$NON-NLS-1$
			} catch (Throwable th) {}
			Executions.getCurrent().sendRedirect("/"); //$NON-NLS-1$
		}

	}
}
