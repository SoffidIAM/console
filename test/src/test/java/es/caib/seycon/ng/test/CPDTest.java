package es.caib.seycon.ng.test;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Vector;

import javax.naming.Context;
import javax.rmi.PortableRemoteObject;

import junit.framework.Assert;
import junit.framework.TestCase;
import es.caib.seycon.ng.comu.PortaCPD;
import es.caib.seycon.ng.comu.RegistreCPD;
import es.caib.seycon.ng.comu.TarjaCPD;
import es.caib.seycon.ng.servei.ejb.CPDService;
import es.caib.seycon.ng.servei.ejb.CPDServiceHome;

public class CPDTest extends TestCase {

	private CPDService service;

	protected void setUp() throws Exception {
		try {
			super.setUp();
			Context ctx = InitialContextFactory.getInitialContext();
			Object obj = ctx.lookup(CPDServiceHome.JNDI_NAME);
			CPDServiceHome CPDHome = (CPDServiceHome) PortableRemoteObject
					.narrow(obj, CPDServiceHome.class);
			service = CPDHome.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testCreatePorta() throws Exception {
		PortaCPD portaCPD = new PortaCPD();
		portaCPD.setNom("miPuerta"); //$NON-NLS-1$
		portaCPD.setDescripcio("descrip"); //$NON-NLS-1$
		portaCPD = service.create(portaCPD);
		portaCPD = service.findPortaByNomPorta("miPuerta"); //$NON-NLS-1$
		Assert.assertNotNull(portaCPD);
		Assert.assertEquals(portaCPD.getNom(), "miPuerta"); //$NON-NLS-1$
		Assert.assertEquals(portaCPD.getDescripcio(), "descrip"); //$NON-NLS-1$
	}

	public void testUpdatePorta() throws Exception {
		PortaCPD portaCPD = service.findPortaByNomPorta("miPuerta"); //$NON-NLS-1$
		portaCPD.setDescripcio("novaDesc"); //$NON-NLS-1$
		portaCPD = service.update(portaCPD);
		portaCPD = service.findPortaByNomPorta("miPuerta"); //$NON-NLS-1$
		Assert.assertNotNull(portaCPD);
		Assert.assertEquals(portaCPD.getNom(), "miPuerta"); //$NON-NLS-1$
		Assert.assertEquals(portaCPD.getDescripcio(), "novaDesc"); //$NON-NLS-1$
	}

	public void testCreateTarja() throws Exception {
		TarjaCPD tarjaCPD = new TarjaCPD();
		tarjaCPD.setCodiTarja("mita"); //$NON-NLS-1$
		tarjaCPD.setCodiUsuari("u89559"); //$NON-NLS-1$
		tarjaCPD = service.create(tarjaCPD);
		tarjaCPD = service.findTarjaCPDByCodiTarja("mita"); //$NON-NLS-1$
		Assert.assertNotNull(tarjaCPD);
		Assert.assertEquals(tarjaCPD.getCodiTarja(), "mita"); //$NON-NLS-1$

	}

	public void testCreateRegistre() throws Exception {
		RegistreCPD registreCPD = new RegistreCPD();
		registreCPD.setCodiTarja("mita"); //$NON-NLS-1$
		registreCPD.setNomPorta("miPuerta"); //$NON-NLS-1$
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 20);
		calendar.set(Calendar.MONTH, Calendar.AUGUST);
		calendar.set(Calendar.YEAR, 2000);
		registreCPD.setRegistre("20/08/2000"); //$NON-NLS-1$
		registreCPD = service.create(registreCPD);
		registreCPD = service.findRegistreByCodiTarjaAndNomPorta("mita", //$NON-NLS-1$
				"miPuerta"); //$NON-NLS-1$
		Assert.assertNotNull(registreCPD);
		Assert.assertEquals(registreCPD.getCodiTarja(), "mita"); //$NON-NLS-1$
		Assert.assertEquals(registreCPD.getNomPorta(), "miPuerta"); //$NON-NLS-1$
	}

	public void testFindRegistresByFiltre() throws Exception {
		try{
		String codiTarja = "mit%"; //$NON-NLS-1$
		String nomPorta = null;
		String registre = ""; //$NON-NLS-1$
		String codiUsuari = "%"; //$NON-NLS-1$
		Collection registres = service.findRegistresByFiltre(codiTarja,
				nomPorta, registre, codiUsuari);
		assertTrue(registres.size() > 0);
		Iterator iterator = registres.iterator();
		while (iterator.hasNext()) {
			RegistreCPD registreLocal = (RegistreCPD) iterator.next();
			assertTrue(registreLocal.getCodiTarja().startsWith("mit")); //$NON-NLS-1$
		}
		codiTarja = "%"; //$NON-NLS-1$
		nomPorta = "miPu%"; //$NON-NLS-1$
		registre = null;
		codiUsuari = ""; //$NON-NLS-1$
		registres = service.findRegistresByFiltre(codiTarja, nomPorta,
				registre, codiUsuari);
		assertTrue(registres.size() > 0);
		iterator = registres.iterator();
		while (iterator.hasNext()) {
			RegistreCPD registreLocal = (RegistreCPD) iterator.next();
			assertTrue(registreLocal.getNomPorta().startsWith("miP")); //$NON-NLS-1$
		}

		codiTarja = ""; //$NON-NLS-1$
		nomPorta = "%"; //$NON-NLS-1$
		registre = ">20/08/2005"; //$NON-NLS-1$
		codiUsuari = "";; //$NON-NLS-1$
		registres = service.findRegistresByFiltre(codiTarja, nomPorta,
				registre, codiUsuari);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //$NON-NLS-1$
		assertTrue(registres.size() > 0);
		Date rightDate = dateFormat.parse("20/08/2000"); //$NON-NLS-1$
		iterator = registres.iterator();
		while (iterator.hasNext()) {
			RegistreCPD registreLocal = (RegistreCPD) iterator.next();
			try {
				Date currentDate = dateFormat
						.parse(registreLocal.getRegistre());
				assertTrue(currentDate.after(rightDate));
			} catch (Exception e) {
				e.printStackTrace();
				fail();
			}
		}

		codiTarja = ""; //$NON-NLS-1$
		nomPorta = "%"; //$NON-NLS-1$
		registre = "<20/08/2001"; //$NON-NLS-1$
		codiUsuari = null;
		registres = service.findRegistresByFiltre(codiTarja, nomPorta,
				registre, codiUsuari);
		assertTrue(registres.size() > 0);
		rightDate = dateFormat.parse("20/08/2003"); //$NON-NLS-1$
		iterator = registres.iterator();
		while (iterator.hasNext()) {
			RegistreCPD registreLocal = (RegistreCPD) iterator.next();
			try {
				Date currentDate = dateFormat
						.parse(registreLocal.getRegistre());
				assertTrue(currentDate.before(rightDate));
			} catch (Exception e) {
				e.printStackTrace();
				fail();
			}
		}

		codiTarja = ""; //$NON-NLS-1$
		nomPorta = "%"; //$NON-NLS-1$
		registre = "31/05/2003"; //$NON-NLS-1$
		codiUsuari = null;
		registres = service.findRegistresByFiltre(codiTarja, nomPorta,
				registre, codiUsuari);
		assertTrue(registres.size() > 0);
		rightDate = dateFormat.parse("31/05/2003"); //$NON-NLS-1$
		iterator = registres.iterator();
		while (iterator.hasNext()) {
			RegistreCPD registreLocal = (RegistreCPD) iterator.next();
			try {
				Date currentDate = dateFormat
						.parse(registreLocal.getRegistre());
				assertTrue(registreLocal.getRegistre().compareTo("31/05/2003") == 0); //$NON-NLS-1$
			} catch (Exception e) {
				e.printStackTrace();
				fail();
			}
		}

		codiTarja = null;
		nomPorta = ""; //$NON-NLS-1$
		registre = "%"; //$NON-NLS-1$
		codiUsuari = "u895%"; //$NON-NLS-1$
		registres = service.findRegistresByFiltre(codiTarja, nomPorta,
				registre, codiUsuari);
		assertTrue(registres.size() > 0);
		iterator = registres.iterator();
		while (iterator.hasNext()) {
			RegistreCPD registreLocal = (RegistreCPD) iterator.next();
			assertTrue(registreLocal.getCodiUsuari().startsWith("u895")); //$NON-NLS-1$
		}

		boolean error = false;
		try {
			codiTarja = null;
			nomPorta = ""; //$NON-NLS-1$
			registre = "%"; //$NON-NLS-1$
			codiUsuari = "%"; //$NON-NLS-1$
			registres = service.findRegistresByFiltre(codiTarja, nomPorta,
					registre, codiUsuari);
		} catch (Exception e) {
			error = true;
		}
		assertTrue(error);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void testFindPortaByNomPortaAndDescripcio() throws Exception {
		String nomPorta = "%"; //$NON-NLS-1$
		String descripcio = "%"; //$NON-NLS-1$

		nomPorta = "miPuer%"; //$NON-NLS-1$
		descripcio = "%"; //$NON-NLS-1$
		Collection portes = service.findPortesByNomPortaAndDescripcio(nomPorta,
				descripcio);
		Assert.assertTrue(portes.size() > 0);
		Iterator iterator = portes.iterator();
		while (iterator.hasNext()) {
			PortaCPD porta = (PortaCPD) iterator.next();
			String item = porta.getNom();
			Assert.assertTrue(item.startsWith("miPuer")); //$NON-NLS-1$
		}
		nomPorta = "miPuer%"; //$NON-NLS-1$
		descripcio = null;
		portes = service
				.findPortesByNomPortaAndDescripcio(nomPorta, descripcio);
		Assert.assertTrue(portes.size() > 0);
		iterator = portes.iterator();
		while (iterator.hasNext()) {
			PortaCPD porta = (PortaCPD) iterator.next();
			String item = porta.getNom();
			Assert.assertTrue(item.startsWith("miPuer")); //$NON-NLS-1$
		}
		nomPorta = "miPuer%"; //$NON-NLS-1$
		descripcio = ""; //$NON-NLS-1$
		portes = service
				.findPortesByNomPortaAndDescripcio(nomPorta, descripcio);
		Assert.assertTrue(portes.size() > 0);
		iterator = portes.iterator();
		while (iterator.hasNext()) {
			PortaCPD porta = (PortaCPD) iterator.next();
			String item = porta.getNom();
			Assert.assertTrue(item.startsWith("miPuer")); //$NON-NLS-1$
		}
		nomPorta = ""; //$NON-NLS-1$
		descripcio = "novaDe%"; //$NON-NLS-1$
		portes = service
				.findPortesByNomPortaAndDescripcio(nomPorta, descripcio);
		Assert.assertTrue(portes.size() > 0);
		iterator = portes.iterator();
		while (iterator.hasNext()) {
			PortaCPD porta = (PortaCPD) iterator.next();
			String item = porta.getDescripcio();
			Assert.assertTrue(item.startsWith("novaDe")); //$NON-NLS-1$
		}
		nomPorta = null;
		descripcio = "novaDe%"; //$NON-NLS-1$
		portes = service
				.findPortesByNomPortaAndDescripcio(nomPorta, descripcio);
		Assert.assertTrue(portes.size() > 0);
		iterator = portes.iterator();
		while (iterator.hasNext()) {
			PortaCPD porta = (PortaCPD) iterator.next();
			String item = porta.getDescripcio();
			Assert.assertTrue(item.startsWith("novaDe")); //$NON-NLS-1$
		}
		nomPorta = "%"; //$NON-NLS-1$
		descripcio = "novaDe%"; //$NON-NLS-1$
		portes = service
				.findPortesByNomPortaAndDescripcio(nomPorta, descripcio);
		Assert.assertTrue(portes.size() > 0);
		iterator = portes.iterator();
		while (iterator.hasNext()) {
			PortaCPD porta = (PortaCPD) iterator.next();
			String item = porta.getDescripcio();
			Assert.assertTrue(item.startsWith("novaDe")); //$NON-NLS-1$
		}

	}

	public void testFindTargesCPDByCodiTarjaAndCodiUsuari() throws Exception {
		String codiTarja = "%322%"; //$NON-NLS-1$
		String codiUsuari = "%"; //$NON-NLS-1$
		Collection targes = service.findTargesCPDByCodiTarjaAndCodiUsuari(
				codiTarja, codiUsuari);
		Iterator iterator = targes.iterator();
		while (iterator.hasNext()) {
			TarjaCPD tarjaCPD = (TarjaCPD) iterator.next();
		}

	}

	public void testFindPortaByNomPorta() throws Exception {
		String nomPorta = "miPuerta"; //$NON-NLS-1$
		String descripcio = "Mi puerta"; //$NON-NLS-1$
		PortaCPD portaCPD = service.findPortaByNomPorta(nomPorta);
		Assert.assertNotNull(portaCPD);
		Assert.assertEquals(portaCPD.getNom(), "miPuerta"); //$NON-NLS-1$
	}

	public void testFindPortesByNomPortaAndDescripcio() throws Exception {
		String nomPorta = "miPuerta"; //$NON-NLS-1$
		String descripcio = "Mi puerta"; //$NON-NLS-1$
		Collection portes = service.findPortesByNomPortaAndDescripcio(nomPorta,
				descripcio);
		Iterator iterator = portes.iterator();
		while (iterator.hasNext()) {
			PortaCPD portaCPD = (PortaCPD) iterator.next();
			assertTrue(portaCPD.getNom().compareTo(nomPorta) == 0);
			assertTrue(portaCPD.getDescripcio().compareTo(descripcio) == 0);
		}
	}

	public void testFindTarjaCPDByCodiTarja() throws Exception {
		String codiTarja = "mita"; //$NON-NLS-1$
		TarjaCPD tarjaCPD = service.findTarjaCPDByCodiTarja(codiTarja);
		assertNotNull(tarjaCPD);
		assertTrue(tarjaCPD.getCodiTarja().compareTo("mita") == 0); //$NON-NLS-1$
	}

	public void testUpdateTarja() throws Exception {
		TarjaCPD tarjaCPD = service.findTarjaCPDByCodiTarja("mita"); //$NON-NLS-1$
		tarjaCPD.setCodiUsuari("u04697"); //$NON-NLS-1$
		tarjaCPD = service.update(tarjaCPD);
		Assert.assertNotNull(tarjaCPD);
		Assert.assertEquals(tarjaCPD.getCodiUsuari(), "u04697"); //$NON-NLS-1$
	}

	public void testDeleteRegistre() throws Exception {
		RegistreCPD registreCPD = service.findRegistreByCodiTarjaAndNomPorta(
				"mita", "miPuerta"); //$NON-NLS-1$ //$NON-NLS-2$
		service.delete(registreCPD);
		registreCPD = service.findRegistreByCodiTarjaAndNomPorta("mita", //$NON-NLS-1$
				"miPuerta"); //$NON-NLS-1$
		Assert.assertNull(registreCPD);
	}

	public void testDeletePorta() throws Exception {
		PortaCPD portaCPD = service.findPortaByNomPorta("miPuerta"); //$NON-NLS-1$
		service.delete(portaCPD);
		portaCPD = service.findPortaByNomPorta("miPuerta"); //$NON-NLS-1$
		Assert.assertNull(portaCPD);
	}

	public void testDeleteTarja() throws Exception {
		TarjaCPD tarjaCPD = service.findTarjaCPDByCodiTarja("mita"); //$NON-NLS-1$
		service.delete(tarjaCPD);
		tarjaCPD = service.findTarjaCPDByCodiTarja("mita"); //$NON-NLS-1$
		Assert.assertNull(tarjaCPD);
	}

}
