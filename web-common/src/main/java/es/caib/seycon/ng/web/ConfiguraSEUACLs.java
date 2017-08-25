package es.caib.seycon.ng.web;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.EJBLocator;
import es.caib.seycon.ng.servei.ejb.PuntEntradaService;
import es.caib.seycon.ng.servei.ejb.XarxaService;

/**
 * Obté si certs menús del SEU s'han de mostrar (per ACLs)
 * 
 * By Alejandro Usero Ruiz 10/01/2012
 * 
 * @author u88683
 * 
 */
public class ConfiguraSEUACLs extends ConfiguraSEU {

	private static final long serialVersionUID = 1L;

	public ConfiguraSEUACLs() throws RemoteException, NamingException,
			CreateException, Exception {
		super();
	}

	/**
	 * Obté els menús del SEU per les ACLs de Xarxa de l'usuari actual
	 * 
	 * @return
	 */
	public Boolean teACLXarxes() {
		String codiUsuari = null;
		codiUsuari = Security.getCurrentUser();

		Boolean teACLXarxes = new Boolean(false);

		org.zkoss.zk.ui.Session sessio = org.zkoss.zk.ui.Sessions.getCurrent();

		// just in case of accident
		if (sessio == null || codiUsuari == null) {
			sessio.setAttribute("hasACLXarxes", teACLXarxes); //$NON-NLS-1$
			return teACLXarxes;
		}

		try {
			Object objTeACLXarxes = sessio.getAttribute("hasACLXarxes"); //$NON-NLS-1$
			if (objTeACLXarxes != null && objTeACLXarxes instanceof Boolean) {
				teACLXarxes = (Boolean) objTeACLXarxes;
			}

			if (objTeACLXarxes == null) {// l'obtenim si encara no l'hem fet
				teACLXarxes = getXarxaService().hasAnyACLXarxes(codiUsuari);
			}
		} catch (Throwable th) {
			// Mostrem error per consola
			th.printStackTrace();
		}

		sessio.setAttribute("hasACLXarxes", teACLXarxes); //$NON-NLS-1$

		return teACLXarxes;
	}

	/**
	 * Obté els menús del SEU per les ACLs de PUE de l'usuari actual it can be
	 * really slooooww
	 * 
	 * @return
	 */
	public Boolean teACLPUE() {
		String codiUsuari = Security.getCurrentUser();

		Boolean teACLPUE = new Boolean(false);

		org.zkoss.zk.ui.Session sessio = org.zkoss.zk.ui.Sessions.getCurrent();

		// just in case of accident
		if (sessio == null || codiUsuari == null) {
			sessio.setAttribute("hasACLPUE", teACLPUE); //$NON-NLS-1$
			return teACLPUE;
		}

		try {
			Object objPUE = sessio.getAttribute("hasACLPUE"); //$NON-NLS-1$
			if (objPUE != null && objPUE instanceof Boolean) {
				teACLPUE = (Boolean) objPUE;
			}

			if (objPUE == null) {// l'obtenim si encara no l'hem fet
				teACLPUE = getPUEService().hasAnyACLPUE(codiUsuari);
			}
		} catch (Throwable th) {
			// Mostrem error per consola
			th.printStackTrace();
		}

		sessio.setAttribute("hasACLPUE", teACLPUE); //$NON-NLS-1$
		return teACLPUE;
	}

	private XarxaService xarxa_service = null;

	private XarxaService getXarxaService() throws NamingException, CreateException {
		if (xarxa_service == null) xarxa_service = EJBLocator.getXarxaService();
		return xarxa_service;
	}

	private PuntEntradaService PUE_service = null;

	private PuntEntradaService getPUEService() throws NamingException, CreateException {
		if (PUE_service == null) PUE_service = EJBLocator.getPuntEntradaService();
		return PUE_service;
	}
}
