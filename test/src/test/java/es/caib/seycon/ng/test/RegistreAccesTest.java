package es.caib.seycon.ng.test;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.naming.Context;
import javax.rmi.PortableRemoteObject;

import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.RegistreAcces;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.servei.ejb.RegistreAccesService;
import es.caib.seycon.ng.servei.ejb.RegistreAccesServiceHome;
import junit.framework.Assert;
import junit.framework.TestCase;

public class RegistreAccesTest extends TestCase {

	private RegistreAccesService service;

	protected void setUp() throws Exception {
		try {
			super.setUp();
			Context ctx = InitialContextFactory.getInitialContext();
			Object obj = ctx.lookup(RegistreAccesServiceHome.JNDI_NAME);
			RegistreAccesServiceHome home = (RegistreAccesServiceHome) PortableRemoteObject
					.narrow(obj, RegistreAccesServiceHome.class);
			service = home.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFindRegistresAccesByCriteri() throws RemoteException {

		Collection registresAcces = service.findRegistresAccesByFiltre("=17/03/2000", //$NON-NLS-1$
				null, "", ""); //$NON-NLS-1$ //$NON-NLS-2$
		String dataString = "17/03/2000"; //$NON-NLS-1$
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //$NON-NLS-1$
		SimpleDateFormat completeDateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
		Assert.assertTrue(registresAcces.size() > 0);
		Iterator iterator = registresAcces.iterator();
		while (iterator.hasNext()) {
			RegistreAcces registreAcces = (RegistreAcces) iterator.next();
			try {
				Date currentDate = registreAcces.getDataInici().getTime();
				String currentDateString = dateFormat.format(currentDate);				
				assertTrue(currentDateString.compareTo(dataString) == 0);
			} catch (Exception e) {
				e.printStackTrace();
				fail();
			}
		}
		
		
		registresAcces = service.findRegistresAccesByFiltre(">18/08/2004 21:30:57", //$NON-NLS-1$
				null, "", ""); //$NON-NLS-1$ //$NON-NLS-2$
		dataString = "18/08/2004"; //$NON-NLS-1$
		Assert.assertTrue(registresAcces.size() > 0);
		iterator = registresAcces.iterator();
		while (iterator.hasNext()) {
			RegistreAcces registreAcces = (RegistreAcces) iterator.next();
			try {
				Date currentDate = registreAcces.getDataInici().getTime();
				String currentDateString = dateFormat.format(currentDate);				
				assertTrue(currentDate.after(dateFormat.parse(dataString)));
			} catch (Exception e) {
				e.printStackTrace();
				fail();
			}
		}

		registresAcces = service.findRegistresAccesByFiltre("<17/03/2000 10:56:00", //$NON-NLS-1$
				null, "", ""); //$NON-NLS-1$ //$NON-NLS-2$
		dataString = "17/03/2000 10:56:00"; //$NON-NLS-1$
		Assert.assertTrue(registresAcces.size() > 0);
		iterator = registresAcces.iterator();
		while (iterator.hasNext()) {
			RegistreAcces registreAcces = (RegistreAcces) iterator.next();
			try {
				Date currentDate = registreAcces.getDataInici().getTime();
				assertTrue(currentDate.before(completeDateFormat.parse(dataString)));
			} catch (Exception e) {
				e.printStackTrace();
				fail();
			}
		}
		
		registresAcces = service.findRegistresAccesByFiltre("=17/03/2000", //$NON-NLS-1$
				null, "", "u80487"); //$NON-NLS-1$ //$NON-NLS-2$
		dataString = "17/03/2000"; //$NON-NLS-1$
		Assert.assertTrue(registresAcces.size() > 0);
		iterator = registresAcces.iterator();
		while (iterator.hasNext()) {
			RegistreAcces registreAcces = (RegistreAcces) iterator.next();
			try {
				Date currentDate = registreAcces.getDataInici().getTime();
				String currentDateString = dateFormat.format(currentDate);
				assertTrue(currentDateString.compareTo(dataString) == 0);
			} catch (Exception e) {
				e.printStackTrace();
				fail();
			}
			assertTrue(registreAcces.getCodiUsuari().compareTo("u80487") == 0); //$NON-NLS-1$
		}
		
		registresAcces = service.findRegistresAccesByFiltre("=17/03/2000", //$NON-NLS-1$
				null, "sprewts4", "%"); //$NON-NLS-1$ //$NON-NLS-2$
		dataString = "17/03/2000"; //$NON-NLS-1$
		Assert.assertTrue(registresAcces.size() > 0);
		iterator = registresAcces.iterator();
		while (iterator.hasNext()) {
			RegistreAcces registreAcces = (RegistreAcces) iterator.next();
			try {
				Date currentDate = registreAcces.getDataInici().getTime();
				String currentDateString = dateFormat.format(currentDate);
				assertTrue(currentDateString.compareTo(dataString) == 0);
			} catch (Exception e) {
				e.printStackTrace();
				fail();
			}
			assertTrue(registreAcces.getNomClinet().compareTo("sprewts4") == 0); //$NON-NLS-1$
		}		
		
		
		registresAcces = service.findRegistresAccesByFiltre("=17/03/2000", //$NON-NLS-1$
				"sintwts1", "", null); //$NON-NLS-1$ //$NON-NLS-2$
		dataString = "17/03/2000"; //$NON-NLS-1$
		Assert.assertTrue(registresAcces.size() > 0);
		iterator = registresAcces.iterator();
		while (iterator.hasNext()) {
			RegistreAcces registreAcces = (RegistreAcces) iterator.next();
			try {
				Date currentDate = registreAcces.getDataInici().getTime();
				String currentDateString = dateFormat.format(currentDate);
				assertTrue(currentDateString.compareTo(dataString) == 0);
			} catch (Exception e) {
				e.printStackTrace();
				fail();
			}
			assertTrue(registreAcces.getNomServidor().compareTo("sintwts1") == 0); //$NON-NLS-1$
		}
	}
}