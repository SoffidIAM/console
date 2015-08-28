package es.caib.seycon.ng.web;

import java.rmi.RemoteException;
import java.util.HashMap;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Executions;

import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.UsuariSEU;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.servei.ejb.UsuariService;

/*
 * Classe per customitzar la presentació del SEU web
 * i les preferències de l'usuari
 * 
 * Alejandro Usero Ruiz - 29/07/2011
 * 
 */

public class Custom {
	
	// Constants de fileres als listbox,gris
	public static final int FILERES = 14; //Defecte
	public static final int FILERES_ESQUEMA = 20;
	public static final int FILERES_GRUPS = 18;
	public static final int FILERES_OBRIR = 20;
	
	public static int getRows(String tipo) {
		/* G = grups
		 * E = esquema
		 * O = obrir tras setRows(5) 
		 * altres = defecte
		 */
		return "G".equals(tipo) ? 18 : "E".equals(tipo) ? 20 : "O".equals(tipo)? 20:  14; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
	public static int getRows(){
		return getRows("D"); //defecto //$NON-NLS-1$
	}	
	
	public static UsuariService getUsuariService() throws NamingException, CreateException, RemoteException {
		javax.naming.Context context = new javax.naming.InitialContext();
		Object objUsuari = context.lookup(es.caib.seycon.ng.servei.ejb.UsuariServiceHome.JNDI_NAME);
		es.caib.seycon.ng.servei.ejb.UsuariServiceHome usuariHome = (es.caib.seycon.ng.servei.ejb.UsuariServiceHome) javax.rmi.PortableRemoteObject
					.narrow(objUsuari, es.caib.seycon.ng.servei.ejb.UsuariServiceHome.class);
		es.caib.seycon.ng.servei.ejb.UsuariService usuariService = usuariHome.create();
		return usuariService;
	}
	
	/**
	 * Establix la preferència de l'usuari a la base de dades
	 * @param codiUsuari
	 * @param clau
	 * @param valor
	 */
	public static void actualitzaPreferenciaUsuari(String clau, String valor) {
		try {
			String codiUsuari = Security.getCurrentUser();
			UsuariSEU usuariSEU = getUsuariService().findUsuariSEUByCodiUsuari(
					codiUsuari);
			if (usuariSEU != null) {
				// comprovació de seguretat:
				if (usuariSEU.getPreferenciesSEU() == null)
					usuariSEU.setPreferenciesSEU(new HashMap());

				if (valor != null && !"".equals(valor.trim())) { //$NON-NLS-1$
					usuariSEU.getPreferenciesSEU().put(clau, valor.trim());
				} else {
					// Si la clau és nul·la o buida, l'eliminem
					usuariSEU.getPreferenciesSEU().remove(clau);
				}

				// Actualitzem les preferencies de l'usuari
				getUsuariService().update(usuariSEU);
			}
		} catch (Throwable th) {
			throw new SeyconException(
					Messages.getString("Custom.UpdatePreferencesError") //$NON-NLS-1$
							+ th.getMessage());
		}

	}	
	
	

}
