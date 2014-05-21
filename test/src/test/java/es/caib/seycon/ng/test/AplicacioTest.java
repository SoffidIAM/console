package es.caib.seycon.ng.test;

import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.security.Principal;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import es.caib.seycon.ng.comu.AdministracioAplicacio;
import es.caib.seycon.ng.comu.Aplicacio;
import es.caib.seycon.ng.comu.ContenidorRol;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.RolAssociacioRol;
import es.caib.seycon.ng.comu.RolsUsuaris;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.servei.ejb.AplicacioService;
import es.caib.seycon.ng.servei.ejb.AplicacioServiceHome;
import es.caib.seycon.ng.servei.ejb.GrupService;
import es.caib.seycon.ng.servei.ejb.GrupServiceHome;
import es.caib.seycon.ng.servei.ejb.UsuariService;
import es.caib.seycon.ng.servei.ejb.UsuariServiceHome;
import junit.framework.Assert;
import junit.framework.TestCase;

public class AplicacioTest extends TestCase {

	private AplicacioService service;
	private UsuariService usuariService;
	private GrupService grupService;

	protected void setUp() throws Exception {
		super.setUp();

		Properties properties = null;
		properties = new Properties();

		properties.load(new FileInputStream("jndi.properties")); //$NON-NLS-1$
		System.getProperties().put("java.security.auth.login.config", //$NON-NLS-1$
				"security.conf"); //$NON-NLS-1$

		Context context = new InitialContext(properties);
		ClientLogin login = new ClientLogin("u89559", "papua23"); //$NON-NLS-1$ //$NON-NLS-2$
		login.login();

		try {

			Object obj = context.lookup(AplicacioServiceHome.JNDI_NAME);
			AplicacioServiceHome home = (AplicacioServiceHome) PortableRemoteObject
					.narrow(obj, AplicacioServiceHome.class);
			service = home.create();

			Object objUsuari = context.lookup(UsuariServiceHome.JNDI_NAME);
			UsuariServiceHome homeUsuari = (UsuariServiceHome) PortableRemoteObject
					.narrow(objUsuari, UsuariServiceHome.class);
			usuariService = homeUsuari.create();

			Object objGrup = context.lookup(GrupServiceHome.JNDI_NAME);
			GrupServiceHome homeGrup = (GrupServiceHome) PortableRemoteObject
					.narrow(objGrup, GrupServiceHome.class);
			grupService = homeGrup.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public void testGetAplicacions() throws RemoteException { try {
	 * Collection apls = service.getAplicacions(); } catch (Exception e) {
	 * e.printStackTrace(); } }
	 */


 	public void testFindContenidorAplicacioRol() throws RemoteException {
		Rol rol = service.findRolById(new Long(5742));
		ContenidorRol contenidorRol = service.toContenidorRol(rol);
		Collection contenidorsRol = service.findRolsContinguts(contenidorRol);
		for (Iterator iterator = contenidorsRol.iterator(); iterator.hasNext();) {
			contenidorRol = (ContenidorRol) iterator.next();
			System.out.println(contenidorRol.getCodi() + ", " //$NON-NLS-1$
					+ contenidorRol.getTipus());
		}
	}

	public void testFindContenidorAplicacioUsuari() throws RemoteException {
		Usuari usuari = usuariService.findUsuariByCodiUsuari("u89559"); //$NON-NLS-1$
		ContenidorRol contenidorRol = service.toContenidorRol(usuari);
		Collection contenidorsRol = service.findRolsContinguts(contenidorRol);
		for (Iterator iterator = contenidorsRol.iterator(); iterator.hasNext();) {
			contenidorRol = (ContenidorRol) iterator.next();
			System.out.println(contenidorRol.getCodi() + ", " //$NON-NLS-1$
					+ contenidorRol.getTipus());
		}
	}

	public void testFindContenidorAplicacioGrup() throws RemoteException {
		Grup grup = grupService.findGrupByCodiGrup("dgtic"); //$NON-NLS-1$
		ContenidorRol contenidorRol = service.toContenidorRol(grup);
		Collection contenidorsRol = service.findRolsContinguts(contenidorRol);
		for (Iterator iterator = contenidorsRol.iterator(); iterator.hasNext();) {
			contenidorRol = (ContenidorRol) iterator.next();
			System.out.println(contenidorRol.getCodi() + ", " //$NON-NLS-1$
					+ contenidorRol.getTipus());
		}
	}

	public void testFindRolsByCodiUsuari() throws RemoteException {
		try {
			Usuari usuari = usuariService.findUsuariByCodiUsuari("u89559"); //$NON-NLS-1$
			Collection rols = service.findRolsByCodiUsuari("u89559"); //$NON-NLS-1$
			for (Iterator iterator = rols.iterator(); iterator.hasNext();) {
				Rol rol = (Rol) iterator.next();
				System.out.println("Nom del rol: " + rol.getNom()); //$NON-NLS-1$
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public void testFindUsuarisByNomRolAndCodiAplicacioRolAndCodiDispatcher()
			throws RemoteException {
		Collection usuaris = service.findUsuarisByNomRolAndCodiAplicacioRolAndCodiDispatcher(
				"SC_CAP_RRHH", "SEYCON", "PasswordBank"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		for(Iterator iterator = usuaris.iterator();iterator.hasNext();){
			Usuari usuari = (Usuari) iterator.next();
			System.out.println("Usuari: "+usuari.getCodi()); //$NON-NLS-1$
		}
	}
	
	public void testFindRolAssociacioRol()
		throws RemoteException {
		Rol rolRRHH = service.findRolByNomRolAndCodiAplicacioAndCodiDispatcher(
				"SC_CAP_RRHH", "SEYCON", "PasswordBank"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		Rol rol = service.findRolByNomRolAndCodiAplicacioAndCodiDispatcher(
				"SC_CAP", "SEYCON", "PasswordBank"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		RolAssociacioRol rolAssociacioRol = new RolAssociacioRol();
		rolAssociacioRol.setContenidor(rol);
		rolAssociacioRol.setContingut(rolRRHH);
		rolAssociacioRol = service.create(rolAssociacioRol);
		rolAssociacioRol = service.findRolAssociacioRol(rol, rolRRHH);
		Assert.assertNotNull(rolAssociacioRol);
		Collection rolContinguts = service.findRolsContinguts(rol);
		Assert.assertNotNull(rolContinguts);
		Assert.assertTrue(rolContinguts.size() == 1);
		Rol contingut = (Rol) rolContinguts.iterator().next();
		Assert.assertEquals(contingut.getNom(), rolRRHH.getNom()); 
		Assert.assertEquals(contingut.getCodiAplicacio(), rolRRHH.getCodiAplicacio()); 
		Assert.assertEquals(contingut.getBaseDeDades(), rolRRHH.getBaseDeDades()); 
		service.delete(rolAssociacioRol);
		rolAssociacioRol = service.findRolAssociacioRol(rol, rolRRHH);
		Assert.assertNull(rolAssociacioRol);
		rolContinguts = service.findRolsContinguts(rol);
		Assert.assertNotNull(rolContinguts);
		Assert.assertTrue(rolContinguts.size() == 0);
	}

	/*
	 * public void testCreateAplicacio() throws RemoteException { String
	 * codiAplicacio = "app_prova"; String nom = "prova"; String responsable =
	 * "responsable prova"; String directoriFonts = "fonts dir"; String
	 * directoriExecutable = "exec dir"; String baseDades = "bbdd"; Aplicacio
	 * aplicacio = new Aplicacio(); aplicacio.setNom(nom);
	 * aplicacio.setCodi(codiAplicacio);
	 * //aplicacio.setResponsable(responsable);
	 * aplicacio.setDirectoriExecutable(directoriExecutable);
	 * aplicacio.setDirectoriFonts(directoriFonts); aplicacio.setBd(baseDades);
	 * aplicacio = service.create(aplicacio); Assert.assertNotNull(aplicacio);
	 * Assert.assertEquals(nom, aplicacio.getNom());
	 * //Assert.assertEquals(responsable, aplicacio.getResponsable());
	 * Assert.assertEquals(codiAplicacio, aplicacio.getCodi());
	 * Assert.assertEquals(directoriFonts, aplicacio.getDirectoriFonts());
	 * Assert.assertEquals(directoriExecutable, aplicacio
	 * .getDirectoriExecutable()); }
	 * 
	 * public void testCreateAdministracioAplicacio() throws Exception { String
	 * codiAplicacio = "app_prova"; String nomRol = "SC_RESPONSABLE"; String
	 * codiUsuari = "u89559"; AdministracioAplicacio administracioAplicacio =
	 * new AdministracioAplicacio();
	 * administracioAplicacio.setCodiAplicacio(codiAplicacio);
	 * administracioAplicacio.setCodiUsuari(codiUsuari);
	 * administracioAplicacio.setNomRol(nomRol); administracioAplicacio =
	 * service.create(administracioAplicacio);
	 * assertNotNull(administracioAplicacio); assertEquals(codiAplicacio,
	 * administracioAplicacio.getCodiAplicacio()); assertEquals(nomRol,
	 * administracioAplicacio.getNomRol()); assertEquals(codiUsuari,
	 * administracioAplicacio.getCodiUsuari()); administracioAplicacio = service
	 * .findAdministracioAplicacioByNomRolAndCodiAplicacioAndCodiUsuar( nomRol,
	 * codiAplicacio, codiUsuari); assertNotNull(administracioAplicacio);
	 * assertEquals(codiAplicacio, administracioAplicacio.getCodiAplicacio());
	 * assertEquals(nomRol, administracioAplicacio.getNomRol());
	 * assertEquals(codiUsuari, administracioAplicacio.getCodiUsuari()); }
	 * 
	 * public void testCreateAdministracioAplicacioII() throws Exception {
	 * String codiAplicacio = "app_prova"; String nomRol =
	 * "SC_RESPONSABLE_SEGURETAT"; String codiUsuari = "u89559";
	 * AdministracioAplicacio administracioAplicacio = new
	 * AdministracioAplicacio();
	 * administracioAplicacio.setCodiAplicacio(codiAplicacio);
	 * administracioAplicacio.setCodiUsuari(codiUsuari);
	 * administracioAplicacio.setNomRol(nomRol); administracioAplicacio =
	 * service.create(administracioAplicacio);
	 * assertNotNull(administracioAplicacio); assertEquals(codiAplicacio,
	 * administracioAplicacio.getCodiAplicacio()); assertEquals(nomRol,
	 * administracioAplicacio.getNomRol()); assertEquals(codiUsuari,
	 * administracioAplicacio.getCodiUsuari()); administracioAplicacio = service
	 * .findAdministracioAplicacioByNomRolAndCodiAplicacioAndCodiUsuar( nomRol,
	 * codiAplicacio, codiUsuari); assertNotNull(administracioAplicacio);
	 * assertEquals(codiAplicacio, administracioAplicacio.getCodiAplicacio());
	 * assertEquals(nomRol, administracioAplicacio.getNomRol());
	 * assertEquals(codiUsuari, administracioAplicacio.getCodiUsuari()); }
	 * 
	 * public void testCreateAplicacioII() throws RemoteException { String
	 * codiAplicacio = "app_prova"; String nom = "prova"; String responsable =
	 * "responsable prova"; String directoriFonts = "fonts dir"; String
	 * directoriExecutable = "exec dir"; String baseDades = "bbdd"; Aplicacio
	 * aplicacio = service.findAplicacioByCodiAplicacio(codiAplicacio);
	 * Assert.assertNotNull(aplicacio); Assert.assertEquals(nom,
	 * aplicacio.getNom()); //Assert.assertEquals(responsable,
	 * aplicacio.getResponsable()); Assert.assertEquals(codiAplicacio,
	 * aplicacio.getCodi()); Assert.assertEquals(directoriFonts,
	 * aplicacio.getDirectoriFonts()); Assert.assertEquals(directoriExecutable,
	 * aplicacio .getDirectoriExecutable()); Assert.assertEquals(baseDades,
	 * aplicacio.getBd()); aplicacio =
	 * service.findAplicacioByCodiAplicacio(aplicacio.getCodi()); }
	 * 
	 * 
	 * 
	 * public void testUpdateAplicacio() throws RemoteException { String
	 * codiApliacio = "app_prova"; String nouResponsable = "nou_responsable";
	 * Aplicacio aplicacio = new Aplicacio(); aplicacio =
	 * service.findAplicacioByCodiAplicacio(codiApliacio);
	 * //aplicacio.setResponsable(nouResponsable); service.update(aplicacio);
	 * aplicacio = service.findAplicacioByCodiAplicacio(codiApliacio);
	 * Assert.assertNotNull(aplicacio); //Assert.assertEquals(nouResponsable,
	 * aplicacio.getResponsable()); }
	 * 
	 * public void testCreateRol() throws RemoteException { String codiAplicacio =
	 * "app_prova"; String nomRol = "rol_pr"; String descripcio = "descripcio";
	 * String baseDeDades = "seycon"; Boolean defecte = new Boolean(true);
	 * Boolean superRole = new Boolean(true); Boolean contrasenya = new
	 * Boolean(true); Rol rol = new Rol(); rol.setCodiAplicacio(codiAplicacio);
	 * rol.setDescripcio(descripcio); rol.setNom(nomRol);
	 * rol.setBaseDeDades(baseDeDades); rol.setDefecte(defecte);
	 * //rol.setSuperRole(superRole); rol.setContrasenya(contrasenya); rol =
	 * service.create(rol); Assert.assertNotNull(rol);
	 * Assert.assertEquals(nomRol, rol.getNom());
	 * Assert.assertEquals(descripcio, rol.getDescripcio());
	 * Assert.assertEquals(baseDeDades, rol.getBaseDeDades());
	 * Assert.assertEquals(defecte, rol.getDefecte()); //
	 * Assert.assertEquals(superRole, rol.getSuperRole());
	 * 
	 * rol = service.findRolByNomRol(nomRol); Assert.assertNotNull(rol);
	 * Assert.assertEquals(nomRol, rol.getNom());
	 * Assert.assertEquals(descripcio, rol.getDescripcio());
	 * Assert.assertEquals(baseDeDades, rol.getBaseDeDades());
	 * Assert.assertEquals(defecte, rol.getDefecte());
	 * //Assert.assertEquals(superRole, rol.getSuperRole()); }
	 * 
	 * public void testFindRolsByCodiAplicacio() throws Exception { String
	 * codiAplicacio = "app_prova"; String nomRol = "rol_pr"; Collection rols =
	 * service.findRolsByCodiAplicacio(codiAplicacio); assertTrue(rols.size() >
	 * 0); Iterator iterator = rols.iterator(); while (iterator.hasNext()) { Rol
	 * rol = (Rol) iterator.next();
	 * assertTrue(rol.getCodiAplicacio().compareTo(codiAplicacio) == 0); } }
	 * 
	 * public void testFindAplicacioByCriteri() throws Exception { String codi =
	 * "app%"; String nom = null; String directoriFonts = ""; String responsable =
	 * "%"; String directoriExecutable = ""; String bd = null; Collection
	 * aplicacions = service.findAplicacioByCriteri(codi, nom, directoriFonts,
	 * responsable, directoriExecutable, bd); assertTrue(aplicacions.size() >
	 * 0); Iterator iterator = aplicacions.iterator(); while
	 * (iterator.hasNext()) { Aplicacio aplicacio = (Aplicacio) iterator.next();
	 * assertTrue(aplicacio.getCodi().startsWith("app")); }
	 * 
	 * codi = null; nom = "pro%"; directoriFonts = null; responsable = "";
	 * directoriExecutable = "%"; bd = ""; aplicacions =
	 * service.findAplicacioByCriteri(codi, nom, directoriFonts, responsable,
	 * directoriExecutable, bd); assertTrue(aplicacions.size() > 0); iterator =
	 * aplicacions.iterator(); while (iterator.hasNext()) { Aplicacio aplicacio =
	 * (Aplicacio) iterator.next();
	 * assertTrue(aplicacio.getNom().startsWith("pro")); }
	 * 
	 * codi = ""; nom = null; directoriFonts = "fon%"; responsable = null;
	 * directoriExecutable = ""; bd = "%"; aplicacions =
	 * service.findAplicacioByCriteri(codi, nom, directoriFonts, responsable,
	 * directoriExecutable, bd); assertTrue(aplicacions.size() > 0); iterator =
	 * aplicacions.iterator(); while (iterator.hasNext()) { Aplicacio aplicacio =
	 * (Aplicacio) iterator.next();
	 * assertTrue(aplicacio.getDirectoriFonts().startsWith("fon")); }
	 * 
	 * codi = ""; nom = "%"; directoriFonts = ""; responsable = null;
	 * directoriExecutable = "exe%"; bd = null; aplicacions =
	 * service.findAplicacioByCriteri(codi, nom, directoriFonts, responsable,
	 * directoriExecutable, bd); assertTrue(aplicacions.size() > 0); iterator =
	 * aplicacions.iterator(); while (iterator.hasNext()) { Aplicacio aplicacio =
	 * (Aplicacio) iterator.next();
	 * assertTrue(aplicacio.getDirectoriExecutable().startsWith("exe")); }
	 * 
	 * codi = null; nom = ""; directoriFonts = "%"; responsable = "";
	 * directoriExecutable = null; bd = "bbdd"; aplicacions =
	 * service.findAplicacioByCriteri(codi, nom, directoriFonts, responsable,
	 * directoriExecutable, bd); assertTrue(aplicacions.size() > 0); iterator =
	 * aplicacions.iterator(); while (iterator.hasNext()) { Aplicacio aplicacio =
	 * (Aplicacio) iterator.next();
	 * assertTrue(aplicacio.getBd().startsWith("bbdd")); }
	 * 
	 * boolean error = false; try { codi = null; nom = ""; directoriFonts = "%";
	 * responsable = ""; directoriExecutable = null; bd = ""; aplicacions =
	 * service.findAplicacioByCriteri(codi, nom, directoriFonts, responsable,
	 * directoriExecutable, bd); } catch (Exception e) { error = true; }
	 * assertTrue(error);
	 *  }
	 * 
	 * 
	 * 
	 * public void testFindAdministracioAplicacioByCodiAplicacio() throws
	 * Exception { String codiAplicacio = "app_prova"; Collection
	 * administracionsAplicacio = service
	 * .findAdministracioAplicacioByCodiAplicacio(codiAplicacio);
	 * assertTrue(administracionsAplicacio.size() > 0); Iterator iterator =
	 * administracionsAplicacio.iterator(); while (iterator.hasNext()) {
	 * AdministracioAplicacio administracioAplicacio = (AdministracioAplicacio)
	 * iterator .next();
	 * assertTrue(administracioAplicacio.getCodiAplicacio().compareTo(
	 * codiAplicacio) == 0); } }
	 */
	/*
	 * public void testFindUsuarisAdministrenAplicacioByNomRolAndCodiApliacio()
	 * throws Exception { String codiUsuari = "u89559"; String codiAplicacio =
	 * "app_prova"; String nomRol = "SC_RESPONSABLE_SEGURETAT"; Collection
	 * usuaris = service
	 * .findUsuarisAdministrenAplicacioByNomRolAndCodiApliacio(nomRol,
	 * codiAplicacio); assertTrue(usuaris.size() > 0); Iterator iterator =
	 * usuaris.iterator(); boolean trobat = false; while (iterator.hasNext() &&
	 * !trobat) { Usuari usuari = (Usuari) iterator.next(); trobat =
	 * usuari.getCodi().compareTo(codiUsuari) == 0; } assertTrue(trobat); }
	 */
	/*
	 * public void testFindAplicacionsAccessiblesByCodiUsuari() throws Exception {
	 * String codiUsuari = "u89559"; String codiAplicacio = "app_prova";
	 * Collection aplicacions = service.
	 * .findAplicacionsAccessiblesByCodiUsuari(codiUsuari);
	 * assertTrue(aplicacions.size() > 0); Iterator iterator =
	 * aplicacions.iterator(); boolean trobat = false; while (iterator.hasNext() &&
	 * !trobat) { Aplicacio aplicacio = (Aplicacio) iterator.next(); trobat =
	 * aplicacio.getCodi().compareTo(codiAplicacio) == 0; } assertTrue(trobat); }
	 */
	/*
	 * public void testFindAplicacionsActualitzablesByCodiUsuari() throws
	 * Exception { String codiUsuari = "u89559"; String codiAplicacio =
	 * "app_prova"; Collection aplicacions = service
	 * .findAplicacionsActualitzablesByCodiUsuari(codiUsuari);
	 * assertTrue(aplicacions.size() > 0); Iterator iterator =
	 * aplicacions.iterator(); boolean trobat = false; while (iterator.hasNext() &&
	 * !trobat) { Aplicacio aplicacio = (Aplicacio) iterator.next(); trobat =
	 * aplicacio.getCodi().compareTo(codiAplicacio) == 0; } assertTrue(trobat); }
	 * 
	 * public void testFindUsuarisAmbPermisosActualitzacioByCodiAplicacio()
	 * throws Exception { String codiUsuari = "u89559"; String codiAplicacio =
	 * "app_prova"; Collection aplicacions = service
	 * .findUsuarisAmbPermisosActualitzacioByCodiAplicacio(codiAplicacio);
	 * assertTrue(aplicacions.size() > 0); Iterator iterator =
	 * aplicacions.iterator(); boolean trobat = false; while (iterator.hasNext() &&
	 * !trobat) { Usuari usuari = (Usuari) iterator.next(); trobat =
	 * usuari.getCodi().compareTo(codiUsuari) == 0; } assertTrue(trobat); }
	 * 
	 * public void testDeleteRol() throws Exception { String nomRol = "rol_pr";
	 * Rol rol = service.findRolByNomRol(nomRol); Assert.assertNotNull(rol);
	 * service.delete(rol); rol = service.findRolByNomRol(nomRol);
	 * Assert.assertNull(rol); }
	 * 
	 * public void testDeleteAplicacio() throws Exception { String codiAplicacio =
	 * "app_prova"; Aplicacio aplicacio = service
	 * .findAplicacioByCodiAplicacio(codiAplicacio);
	 * Assert.assertNotNull(aplicacio); Assert.assertEquals(codiAplicacio,
	 * aplicacio.getCodi()); service.delete(aplicacio); aplicacio =
	 * service.findAplicacioByCodiAplicacio(codiAplicacio);
	 * Assert.assertNull(aplicacio); }
	 */
}
