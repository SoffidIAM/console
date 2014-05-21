package es.caib.seycon.ng.test;

import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import junit.framework.Assert;
import junit.framework.TestCase;
import es.caib.seycon.ng.comu.Domini;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.RolsUsuaris;
import es.caib.seycon.ng.comu.ValorDomini;
import es.caib.seycon.ng.servei.ejb.AplicacioServiceHome;
import es.caib.seycon.ng.servei.ejb.AplicacioService;
import es.caib.seycon.ng.servei.ejb.DominiService;
import es.caib.seycon.ng.servei.ejb.DominiServiceHome;

public class DominiTest extends TestCase {

	private DominiService service;
	private AplicacioService aplicacioService;

	protected void setUp() throws Exception {
		try {
			super.setUp();

			Properties properties = null;
			properties = new Properties();
			properties.load(new FileInputStream("jndi.properties")); //$NON-NLS-1$
			System.getProperties().put("java.security.auth.login.config", //$NON-NLS-1$
					"security.conf"); //$NON-NLS-1$
			Context ctx = new InitialContext(properties);
			ClientLogin login = new ClientLogin("u89559", "pass"); //$NON-NLS-1$ //$NON-NLS-2$
			login.login();

			Object obj = ctx.lookup(DominiServiceHome.JNDI_NAME);
			DominiServiceHome dominiHome = (DominiServiceHome) PortableRemoteObject
					.narrow(obj, DominiServiceHome.class);
			service = dominiHome.create();

			Object objAplicacio = ctx.lookup(AplicacioServiceHome.JNDI_NAME);
			AplicacioServiceHome aplicacioHome = (AplicacioServiceHome) PortableRemoteObject
					.narrow(objAplicacio, AplicacioServiceHome.class);
			aplicacioService = aplicacioHome.create();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
/*
	public void testCreateDomini() throws Exception {
		Domini domini = new Domini();
		domini.setNom("dom sapo");
		domini.setCodiExtern("SAP");
		domini = service.create(domini);
		// domini.setNomRol("ZIBS:MM:CO:GAPM");
		// domini = service.update(domini);
		assertNotNull(domini);
		domini = service.findDominiAplicacioByNomDominiAndCodiAplicacio(
				"dom sapo", "SAP");
		assertNotNull(domini);
		assertEquals("SAP", domini.getCodiExtern());
		assertEquals("dom sapo", domini.getNom());

		Rol rol = aplicacioService.findRolByNomRol("ZIBS:MM:CO:GAPM");
		rol.setDomini(domini);
		aplicacioService.update(rol);

	}

	public void testCreateValorDomini() throws Exception {
		ValorDomini valorDomini = new ValorDomini();
		valorDomini.setDescripcio("una descripcio");
		valorDomini.setNomDomini("dom sapo");
		valorDomini.setCodiExternDomini("SAP");
		valorDomini.setValor("un valor");
		valorDomini = service.create(valorDomini);
		valorDomini = service
				.findValorDominiAplicacioByNomDominiAndCodiAplicacioDominiAndValor(
						"dom sapo", "SAP", "un valor");
		assertNotNull(valorDomini);
		assertEquals("SAP", valorDomini.getCodiExternDomini());
		assertEquals("una descripcio", valorDomini.getDescripcio());
		assertEquals("dom sapo", valorDomini.getNomDomini());
		assertEquals("un valor", valorDomini.getValor());
	}

	public void testFindDominiGrupsUsuari() throws Exception {
		Domini domini = service.findDominiGrupsUsuari();
		assertNotNull(domini);
		assertEquals("GRUPS_USUARI", domini.getNom());
	}

	public void testFindDominiGrups() throws Exception {
		Domini domini = service.findDominiGrups();
		assertNotNull(domini);
		assertEquals("GRUPS", domini.getNom());
		assertEquals(null, domini.getCodiExtern());
	}

	public void testFindDominisAplicacioByCodiAplicacio() throws Exception {
		Collection dominis = service.findDominisAplicacioByCodiAplicacio("SAP");
		assertNotNull(dominis);
		assertTrue(dominis.size() > 0);
		Iterator iterator = dominis.iterator();
		while (iterator.hasNext()) {
			Domini domini = (Domini) iterator.next();
			assertEquals("SAP", domini.getCodiExtern());
		}
	}

	public void testFindDominisAplicacioByNomRol() throws Exception {
		Domini domini = service.findDominiAplicacioByNomRol("ZIBS:MM:CO:GAPM");
		assertNotNull(domini);
		assertEquals("SAP", domini.getCodiExtern());
		assertEquals("dom sapo", domini.getNom());
	}

	public void testFindValorsDominiByFiltre_Aplicacio() throws Exception {
		Domini domini = service.findDominiAplicacioByNomDominiAndCodiAplicacio(
				"dom sapo", "SAP");
		Collection valorsDomini = service.findValorsDominiByFiltre(domini, null, null, null);
		assertNotNull(valorsDomini);
		assertTrue(valorsDomini.size() > 0);
		Iterator iterator = valorsDomini.iterator();
		while (iterator.hasNext()) {
			ValorDomini valorDomini = (ValorDomini) iterator.next();
			assertEquals("SAP", valorDomini.getCodiExternDomini());
			assertEquals("una descripcio", valorDomini.getDescripcio());
			assertEquals("dom sapo", valorDomini.getNomDomini());
			assertEquals("un valor", valorDomini.getValor());
		}
	}

	public void testFindValorsDomini_Grups() throws Exception {
		Domini domini = service.findDominiGrups();
		Collection valorsDomini = service.findValorsDominiByFiltre(domini, null, null, null);
		assertNotNull(valorsDomini);
		assertTrue(valorsDomini.size() > 0);
	}
*/
	/*
	 * public void testFindValorsDomini_GrupsUsuari() throws Exception { Domini
	 * domini = service.findDominiGrupsUsuariByCodiUsuari("u89559"); Collection
	 * valorsDomini = service.findValorsDominiByDomini(domini);
	 * assertNotNull(valorsDomini); assertTrue(valorsDomini.size() > 0);
	 * Iterator iterator = valorsDomini.iterator(); while(iterator.hasNext()){
	 * ValorDomini valorDomini = (ValorDomini) iterator.next();
	 * assertEquals("u89559", valorDomini.getCodiExternDomini()); } }
	 *//*
	public void testAssignaValorDominiAplicacio() throws Exception {
		RolsUsuaris rolsUsuaris = new RolsUsuaris();
		ValorDomini valorDomini = service
				.findValorDominiAplicacioByNomDominiAndCodiAplicacioDominiAndValor(
						"dom sapo", "SAP", "un valor");
		rolsUsuaris.setValorDomini(valorDomini);
		rolsUsuaris.setCodiUsuari("u89559");
		rolsUsuaris.setNomRol("ZIBS:MM:CO:GAPM");
		aplicacioService.create(rolsUsuaris);

		rolsUsuaris = aplicacioService.findRolsUsuarisByCodiUsuariAndNomRol(
				"u89559", "ZIBS:MM:CO:GAPM");

		aplicacioService.delete(rolsUsuaris);

	}

	public void testAssignaValorDominiGrups() throws Exception {
		Domini domini = service.findDominiGrups();
		Collection valorsDomini = service.findValorsDominiByFiltre(domini, null, null, null);
		ValorDomini valorDomini = (ValorDomini) valorsDomini.iterator().next();
		RolsUsuaris rolsUsuaris = new RolsUsuaris();
		rolsUsuaris.setValorDomini(valorDomini);
		rolsUsuaris.setCodiUsuari("u89559");
		rolsUsuaris.setNomRol("ZIBS:MM:CO:GAPM");
		aplicacioService.create(rolsUsuaris);

		rolsUsuaris = aplicacioService.findRolsUsuarisByCodiUsuariAndNomRol(
				"u89559", "ZIBS:MM:CO:GAPM");
		aplicacioService.delete(rolsUsuaris);
	}

	public void testDeleteValorDomini() throws Exception {
		ValorDomini valorDomini = service
				.findValorDominiAplicacioByNomDominiAndCodiAplicacioDominiAndValor(
						"dom sapo", "SAP", "un valor");
		service.delete(valorDomini);
		valorDomini = service
				.findValorDominiAplicacioByNomDominiAndCodiAplicacioDominiAndValor(
						"dom sapo", "SAP", "un valor");
		assertNull(valorDomini);
	}

	public void testDeleteDomini() throws Exception {
		Rol rol = aplicacioService.findRolByNomRol("ZIBS:MM:CO:GAPM");
		rol.setDomini(null);
		aplicacioService.update(rol);

		Domini domini = service.findDominiAplicacioByNomDominiAndCodiAplicacio(
				"dom sapo", "SAP");
		assertNotNull(domini);
		// assertEquals("ZIBS:MM:CO:GAPM", domini.getNomRol());
		assertEquals("dom sapo", domini.getNom());
		service.delete(domini);
	}

	public void testFindDominisByCodiAplicacio() throws Exception {
		Collection dominis = service.findDominisByCodiAplicacio("SAP%");
		Iterator dominiIterator = dominis.iterator();
		while (dominiIterator.hasNext()) {
			Domini domini = (Domini) dominiIterator.next();
			System.out.println("Nom domini: " + domini.getNom());
		}
	}*/
}
