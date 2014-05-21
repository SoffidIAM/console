package es.caib.seycon.ng.test;

import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import junit.framework.Assert;
import junit.framework.TestCase;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.RolsUsuaris;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.servei.ejb.LopdServiceHome;
import es.caib.seycon.ng.servei.ejb.UsuariService;
import es.caib.seycon.ng.servei.ejb.UsuariServiceHome;
import es.caib.seycon.ng.servei.ejb.XarxaService;
import es.caib.seycon.ng.servei.ejb.XarxaServiceHome;

public class ProceduresTest extends TestCase {

	private UsuariService usuariService;
	private XarxaService xarxaService;

	protected void setUp() throws Exception {
		super.setUp();
		try {
			Properties properties = null;
			properties = new Properties();
			properties.load(new FileInputStream("jndi.properties")); //$NON-NLS-1$
			System.getProperties().put("java.security.auth.login.config", //$NON-NLS-1$
					"security.conf"); //$NON-NLS-1$
			Context ctx = new InitialContext(properties);
			ClientLogin login = new ClientLogin("u89559", "pass"); //$NON-NLS-1$ //$NON-NLS-2$
			login.login();
			Context context = InitialContextFactory.getInitialContext();
			Object objUsuari = context.lookup(UsuariServiceHome.JNDI_NAME);
			UsuariServiceHome usuariHome = (UsuariServiceHome) PortableRemoteObject
					.narrow(objUsuari, UsuariServiceHome.class);
			usuariService = usuariHome.create();
			
			Object objXarxa = context.lookup(XarxaServiceHome.JNDI_NAME);
			XarxaServiceHome xarxaHome = (XarxaServiceHome) PortableRemoteObject
					.narrow(objXarxa, XarxaServiceHome.class);
			xarxaService = xarxaHome.create();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*
	public void testRenovacioPassword() throws RemoteException {
		String nouPassword = usuariService.canviPassword("u99991");
		System.out.println(nouPassword);
		assertNotNull(nouPassword);
		String nouNouPassword = usuariService.canviPassword("u99991");
		System.out.println(nouNouPassword);
		assertNotNull(nouNouPassword);
		Assert.assertFalse(nouPassword == nouNouPassword);
	}

	public void testTestPropagacioCanvis() throws RemoteException {
		String[] tasques = usuariService.refreshCanvis("u86957");
		for (int i = 0; i < tasques.length; i++) {
			System.out.println("Tasque: " + tasques[i]);
		}
	}

	public void testGetTasques() throws RemoteException {
		String[] tasques = usuariService.getTasques("u86957");
		for (int i = 0; i < tasques.length; i++) {
			System.out.println("Tasque: " + tasques[i]);
		}
	}*/
	/*
	public void testGetPrimeraIPLliure() throws RemoteException{
		String primeraIPLliure = xarxaService.getPrimeraIPLliure("172.18.1.0", "255.255.255.0");
		System.out.println(primeraIPLliure);
	}
	
	public void testGetIPsOcupades() throws RemoteException{
		Long count = xarxaService.getIPsOcupades("172.18.1.0", "255.255.255.0");
		System.out.println(count);
	}
	
	public void testGetIPsBuides() throws RemoteException{
		Long count = xarxaService.getIPsBuides("172.18.1.0", "255.255.255.0");
		System.out.println(count);
	}
	*/
	
	public void testGetSeguentCodi() throws RemoteException{
		String count = usuariService.getSeguentCodi();
		System.out.println(count);
	}
}
