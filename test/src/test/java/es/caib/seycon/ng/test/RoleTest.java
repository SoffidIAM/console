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
import es.caib.seycon.ng.comu.AdministracioAplicacio;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.RolsUsuaris;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.servei.ejb.UsuariService;
import es.caib.seycon.ng.servei.ejb.UsuariServiceHome;
import es.caib.seycon.ng.servei.ejb.AplicacioService;
import es.caib.seycon.ng.servei.ejb.AplicacioServiceHome;

public class RoleTest extends TestCase {

	private UsuariService usuariService;
	private AplicacioService aplicacioService;

	protected void setUp() throws Exception {
		super.setUp();

		Properties properties = null;
		properties = new Properties();
		properties.load(new FileInputStream("jndi.properties")); //$NON-NLS-1$
		System.getProperties().put("java.security.auth.login.config", //$NON-NLS-1$
				"security.conf"); //$NON-NLS-1$
		Context context = new InitialContext(properties);
		ClientLogin login = new ClientLogin("u89559", "pass"); //$NON-NLS-1$ //$NON-NLS-2$
		login.login();


		Object objUsuari = context.lookup(UsuariServiceHome.JNDI_NAME);
		UsuariServiceHome usuariHome = (UsuariServiceHome) PortableRemoteObject
				.narrow(objUsuari, UsuariServiceHome.class);
		usuariService = usuariHome.create();

		Object objAplicacio = context.lookup(AplicacioServiceHome.JNDI_NAME);
		AplicacioServiceHome aplicacioHome = (AplicacioServiceHome) PortableRemoteObject
				.narrow(objAplicacio, AplicacioServiceHome.class);
		aplicacioService = aplicacioHome.create();

	}
/*
	public void testGetRoles() throws RemoteException {
		Collection impressoras = aplicacioService.getRols();
	}

	public void testCreateRol() throws RemoteException {
		String nom = "testRol";
		String descripcio = "una descripcio";
		String aplicacio = "SEYCON";
		String baseDeDades = "SAPAgent";
		Boolean defecte = new Boolean(true);
		Boolean contrasenya = new Boolean(true);
		Boolean superRole = new Boolean(true);
		Rol rol = new Rol();
		rol.setNom(nom);
		rol.setDescripcio(descripcio);
		rol.setDefecte(defecte);
		//rol.setSuperRole(superRole);
		rol.setCodiAplicacio(aplicacio);
		rol.setBaseDeDades(baseDeDades);
		rol.setContrasenya(contrasenya);
		rol = aplicacioService.create(rol);
		Assert.assertNotNull(rol);
		Assert.assertEquals(nom, rol.getNom());
		Assert.assertEquals(descripcio, rol.getDescripcio());
		Assert.assertEquals(aplicacio, rol.getCodiAplicacio());
		Assert.assertEquals(baseDeDades, rol.getBaseDeDades());
		Assert.assertEquals(defecte, rol.getDefecte());
		Assert.assertEquals(contrasenya, rol.getContrasenya());
		//Assert.assertEquals(superRole, rol.getSuperRole());
		rol = aplicacioService.findRolByNomRol(nom);
		Assert.assertNotNull(rol);
		Assert.assertEquals(nom, rol.getNom());
		Assert.assertEquals(descripcio, rol.getDescripcio());
		Assert.assertEquals(aplicacio, rol.getCodiAplicacio());
		Assert.assertEquals(baseDeDades, rol.getBaseDeDades());
		Assert.assertEquals(defecte, rol.getDefecte());
		Assert.assertEquals(contrasenya, rol.getContrasenya());
//		Assert.assertEquals(superRole, rol.getSuperRole());
	}

	public void testFindRolByFiltre() throws RemoteException {

		String nom = "testR%";
		String descripcio = null;
		String defecte = "";
		String baseDeDades = "%";
		String contrasenya = null;
		String superRole = "";
		String codiAplicacio = null;
		Collection rols = aplicacioService.findRolsByFiltre(nom, descripcio, defecte,
				baseDeDades, contrasenya, superRole, codiAplicacio);
		assertTrue(rols.size() > 0);
		Iterator iterator = rols.iterator();
		while (iterator.hasNext()) {
			Rol rol = (Rol) iterator.next();
			assertTrue(rol.getNom().startsWith("testR"));
		}

		nom = null;
		descripcio = "una %";
		defecte = null;
		baseDeDades = "";
		contrasenya = "%";
		superRole = null;
		codiAplicacio = "%";
		rols = aplicacioService.findRolsByFiltre(nom, descripcio, defecte, baseDeDades,
				contrasenya, superRole, codiAplicacio);
		assertTrue(rols.size() > 0);
		iterator = rols.iterator();
		while (iterator.hasNext()) {
			Rol rol = (Rol) iterator.next();
			assertTrue(rol.getDescripcio().startsWith("una "));
		}

		nom = "";
		descripcio = "una %";
		defecte = "S";
		baseDeDades = null;
		contrasenya = "";
		superRole = "%";
		codiAplicacio = null;
		rols = aplicacioService.findRolsByFiltre(nom, descripcio, defecte, baseDeDades,
				contrasenya, superRole, codiAplicacio);
		assertTrue(rols.size() > 0);
		iterator = rols.iterator();
		while (iterator.hasNext()) {
			Rol rol = (Rol) iterator.next();
			assertTrue(rol.getDefecte().booleanValue());
		}

		nom = "";
		descripcio = "una %";
		defecte = null;
		baseDeDades = "SAPAg%";
		contrasenya = null;
		superRole = "";
		codiAplicacio = null;
		rols = aplicacioService.findRolsByFiltre(nom, descripcio, defecte, baseDeDades,
				contrasenya, superRole, codiAplicacio);
		assertTrue(rols.size() > 0);
		iterator = rols.iterator();
		while (iterator.hasNext()) {
			Rol rol = (Rol) iterator.next();
			assertTrue(rol.getBaseDeDades().startsWith("SAPAg"));
		}

		nom = null;
		descripcio = "una %";
		defecte = "%";
		baseDeDades = null;
		contrasenya = "S";
		superRole = null;
		codiAplicacio = "%";
		rols = aplicacioService.findRolsByFiltre(nom, descripcio, defecte, baseDeDades,
				contrasenya, superRole, codiAplicacio);
		assertTrue(rols.size() > 0);
		iterator = rols.iterator();
		while (iterator.hasNext()) {
			Rol rol = (Rol) iterator.next();
			assertTrue(rol.getContrasenya().booleanValue());
		}

		nom = "%";
		descripcio = "una %";
		defecte = null;
		baseDeDades = "";
		contrasenya = "%";
		superRole = "S";
		codiAplicacio = "";
		rols = aplicacioService.findRolsByFiltre(nom, descripcio, defecte, baseDeDades,
				contrasenya, superRole, codiAplicacio);
		assertTrue(rols.size() > 0);
		iterator = rols.iterator();
		while (iterator.hasNext()) {
			Rol rol = (Rol) iterator.next();
			//assertTrue(rol.getSuperRole().booleanValue());
		}

		nom = null;
		descripcio = "";
		defecte = "%";
		baseDeDades = null;
		contrasenya = "";
		superRole = "%";
		codiAplicacio = "SEY%";
		rols = aplicacioService.findRolsByFiltre(nom, descripcio, defecte, baseDeDades,
				contrasenya, superRole, codiAplicacio);
		assertTrue(rols.size() > 0);
		iterator = rols.iterator();
		while (iterator.hasNext()) {
			Rol rol = (Rol) iterator.next();
			assertTrue(rol.getCodiAplicacio().startsWith("SEY"));
		}

		boolean error = false;
		try {
			nom = null;
			descripcio = "";
			defecte = "%";
			baseDeDades = null;
			contrasenya = "";
			superRole = "%";
			codiAplicacio = "%";
			rols = aplicacioService.findRolsByFiltre(nom, descripcio, defecte,
					baseDeDades, contrasenya, superRole, codiAplicacio);
		} catch (Exception e) {
			error = true;
		}
		assertTrue(error);
	}

	public void testCreateRolsUsuaris() throws RemoteException {
		String nomRol = "testRol";
		String codiUsuari = "u89559";
		String codiGrup = "DGTIC";
		RolsUsuaris rolsUsuaris = new RolsUsuaris();
		rolsUsuaris.setCodiUsuari(codiUsuari);
		rolsUsuaris.setNomRol(nomRol);
		rolsUsuaris.setCodiGrup(codiGrup);
		rolsUsuaris = aplicacioService.create(rolsUsuaris);
		assertNotNull(rolsUsuaris);
		assertTrue(rolsUsuaris.getCodiUsuari().compareTo(codiUsuari) == 0);
		assertTrue(rolsUsuaris.getNomRol().compareTo(nomRol) == 0);
		assertTrue(rolsUsuaris.getCodiGrup().compareTo(codiGrup) == 0);
		rolsUsuaris = aplicacioService.findRolsUsuarisByCodiUsuariAndNomRol(codiUsuari,
				nomRol);
		assertNotNull(rolsUsuaris);
		assertTrue(rolsUsuaris.getCodiUsuari().compareTo(codiUsuari) == 0);
		assertTrue(rolsUsuaris.getNomRol().compareTo(nomRol) == 0);
		assertTrue(rolsUsuaris.getCodiGrup().compareTo(codiGrup) == 0);
	}

	public void testAddPrincipalToUsuari() throws RemoteException {
		String nom = "SC_RESPONSABLE";
		String codiUsuari = "u89559";
		String aplicacio = "SEYCON";
		AdministracioAplicacio administracioAplicacio = new AdministracioAplicacio();
		administracioAplicacio.setCodiAplicacio(aplicacio);
		administracioAplicacio.setCodiUsuari(codiUsuari);
		administracioAplicacio.setNomRol(nom);
		administracioAplicacio = aplicacioService
				.create(administracioAplicacio);
		assertNotNull(administracioAplicacio);
		assertEquals(nom, administracioAplicacio.getNomRol());
		assertEquals(codiUsuari, administracioAplicacio.getCodiUsuari());
		assertEquals(aplicacio, administracioAplicacio.getCodiAplicacio());
		administracioAplicacio = aplicacioService
				.findAdministracioAplicacioByNomRolAndCodiAplicacioAndCodiUsuar(
						nom, aplicacio, codiUsuari);
		assertNotNull(administracioAplicacio);
		assertEquals(nom, administracioAplicacio.getNomRol());
		assertEquals(codiUsuari, administracioAplicacio.getCodiUsuari());
		assertEquals(aplicacio, administracioAplicacio.getCodiAplicacio());
	}

	public void testUpdateRolsUsuaris() throws Exception {
		String nomRol = "testRol";
		String codiUsuari = "u89559";
		String codiAplicacio = "SEYCON";
		RolsUsuaris rolusu = aplicacioService.findRolsUsuarisByCodiUsuariAndNomRol(
				codiUsuari, nomRol);
		assertNotNull(rolusu);
		rolusu.setCodiAplicacio(codiAplicacio);
		rolusu = aplicacioService.update(rolusu);
		assertNotNull(rolusu);
		assertTrue(rolusu.getCodiAplicacio().compareTo(codiAplicacio) == 0);
		rolusu = aplicacioService.findRolsUsuarisByCodiUsuariAndNomRol(codiUsuari,
				nomRol);
		assertNotNull(rolusu);
		assertTrue(rolusu.getCodiAplicacio().compareTo(codiAplicacio) == 0);
	}
*/
	public void testFindRolsUsuarisByCodiUsuari() throws Exception {
		String codiUsuari = "u89559"; //$NON-NLS-1$
		Collection rolsUsuaris = aplicacioService
				.findRolsUsuarisByCodiUsuari(codiUsuari);
		assertTrue(rolsUsuaris.size() > 0);
		Iterator iterator = rolsUsuaris.iterator();
		while (iterator.hasNext()) {
			RolsUsuaris rolusu = (RolsUsuaris) iterator.next();
			assertNotNull(rolusu);
			assertTrue(rolusu.getCodiUsuari().compareTo(codiUsuari) == 0);
		}
	}
/*
	public void testFindRolsUsuarisByNomRol() throws Exception {
		String nomRol = "testRol";
		Collection rolsUsuaris = aplicacioService.findRolsUsuarisByNomRol(nomRol);
		assertTrue(rolsUsuaris.size() > 0);
		Iterator iterator = rolsUsuaris.iterator();
		while (iterator.hasNext()) {
			RolsUsuaris rolusu = (RolsUsuaris) iterator.next();
			assertNotNull(rolusu);
			assertTrue(rolusu.getNomRol().compareTo(nomRol) == 0);
		}
	}

	public void testFindUsuarisByNomRolApplicacio() throws RemoteException {
		String nomRol = "testRol";
		String codiUsuari = "u89559";
		Collection usuaris = aplicacioService.findUsuarisByNomRol(nomRol);
		assertTrue(usuaris.size() > 0);
		Iterator iterator = usuaris.iterator();
		boolean trobat = false;
		while (iterator.hasNext() && !trobat) {
			Usuari usuari = (Usuari) iterator.next();
			trobat = usuari.getCodi().compareTo(codiUsuari) == 0;
		}
		assertTrue(trobat);
	}

	public void testFindRolsByFiltreAndUpdate() throws RemoteException {
		Collection rols = aplicacioService.findRolsByFiltre("SC_RRHH", null, null, null,
				null, null, null);
		Iterator iterator = rols.iterator();
		while (iterator.hasNext()) {
			Rol rol = (Rol) iterator.next();
			rol.setCodiAplicacio("SEYCON");
			aplicacioService.update(rol);
		}
		Assert.assertNotNull(rols);
	}

	public void testFindRolsByCodiUsuariANDFindUsuarisByNomRol()
			throws RemoteException {
		String codiUsuari = "u89559";
		String nomRol = "testRol";
		Collection rols = aplicacioService.findRolsByCodiUsuari(codiUsuari);
		assertTrue(rols.size() > 0);
		Iterator iterator = rols.iterator();
		boolean trobat = false;
		while (iterator.hasNext() && !trobat) {
			Rol rol = (Rol) iterator.next();
			String nom = rol.getNom();
			trobat = nom.compareTo(nomRol) == 0;
		}
		assertTrue(trobat);

		trobat = false;
		Collection usuaris = aplicacioService.findUsuarisByNomRol(nomRol);
		assertTrue(usuaris.size() > 0);
		iterator = usuaris.iterator();
		while (iterator.hasNext() && !trobat) {
			Usuari usuari = (Usuari) iterator.next();
			trobat = usuari.getCodi().equals(codiUsuari);
		}
		assertTrue(trobat);
	}

	public void testDeleteRolsUsuaris() throws RemoteException {
		String nomRol = "testRol";
		String codiUsuari = "u89559";
		RolsUsuaris rolsUsuaris = aplicacioService.findRolsUsuarisByCodiUsuariAndNomRol(
				codiUsuari, nomRol);
		assertNotNull(rolsUsuaris);
		aplicacioService.delete(rolsUsuaris);
		rolsUsuaris = aplicacioService.findRolsUsuarisByCodiUsuariAndNomRol(codiUsuari,
				nomRol);
		assertNull(rolsUsuaris);
	}

	public void testUpdateRol() throws RemoteException {
		String nom = "testRol";
		String baseDeDades = "sofiwnt3";
		String codiAplicacio = "SAP";
		Boolean contrasenya = new Boolean(false);
		Boolean defecte = new Boolean(false);
		String descripcio = "nova descripcio";
		Boolean superRole = new Boolean(false);
		Rol rol = aplicacioService.findRolByNomRol(nom);
		rol.setBaseDeDades(baseDeDades);
		rol.setCodiAplicacio(codiAplicacio);
		rol.setContrasenya(contrasenya);
		rol.setDefecte(defecte);
		rol.setDescripcio(descripcio);
		//rol.setSuperRole(superRole);
		rol = aplicacioService.update(rol);
		Assert.assertNotNull(rol);
		Assert.assertEquals(baseDeDades, rol.getBaseDeDades());
		Assert.assertEquals(codiAplicacio, rol.getCodiAplicacio());
		Assert.assertEquals(contrasenya, rol.getContrasenya());
		Assert.assertEquals(defecte, rol.getDefecte());
		Assert.assertEquals(descripcio, rol.getDescripcio());
		//Assert.assertEquals(superRole, rol.getSuperRole());
		rol = aplicacioService.findRolByNomRol(nom);
		Assert.assertNotNull(rol);
		Assert.assertEquals(baseDeDades, rol.getBaseDeDades());
		Assert.assertEquals(codiAplicacio, rol.getCodiAplicacio());
		Assert.assertEquals(contrasenya, rol.getContrasenya());
		Assert.assertEquals(defecte, rol.getDefecte());
		Assert.assertEquals(descripcio, rol.getDescripcio());
		//Assert.assertEquals(superRole, rol.getSuperRole());
	}

	public void testDeleteRol() throws RemoteException {
		String nom = "testRol";
		Rol rol = aplicacioService.findRolByNomRol(nom);
		assertNotNull(rol);
		aplicacioService.delete(rol);
		rol = aplicacioService.findRolByNomRol(nom);
		Assert.assertNull(rol);
	}

	public void testDeletePrincipalToUsuari() throws RemoteException {
		String nom = "SC_RESPONSABLE";
		String codiUsuari = "u89559";
		String aplicacio = "SEYCON";
		AdministracioAplicacio administracioAplicacio = aplicacioService
				.findAdministracioAplicacioByNomRolAndCodiAplicacioAndCodiUsuar(
						nom, aplicacio, codiUsuari);
		aplicacioService.delete(administracioAplicacio);
		administracioAplicacio = aplicacioService
				.findAdministracioAplicacioByNomRolAndCodiAplicacioAndCodiUsuar(
						nom, aplicacio, codiUsuari);
		assertNull(administracioAplicacio);
	}
*/
}
