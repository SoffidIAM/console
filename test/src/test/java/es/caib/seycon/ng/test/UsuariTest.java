package es.caib.seycon.ng.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import junit.framework.Assert;
import junit.framework.TestCase;
import es.caib.seycon.ng.comu.Aplicacio;
import es.caib.seycon.ng.comu.DadaUsuari;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Impressora;
import es.caib.seycon.ng.comu.Maquina;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.RolsUsuaris;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.UsuariGrup;
import es.caib.seycon.ng.comu.UsuariImpressora;
import es.caib.seycon.ng.servei.ejb.AplicacioService;
import es.caib.seycon.ng.servei.ejb.AplicacioServiceHome;
import es.caib.seycon.ng.servei.ejb.GrupService;
import es.caib.seycon.ng.servei.ejb.GrupServiceHome;
import es.caib.seycon.ng.servei.ejb.ImpressoraService;
import es.caib.seycon.ng.servei.ejb.ImpressoraServiceHome;
import es.caib.seycon.ng.servei.ejb.UsuariService;
import es.caib.seycon.ng.servei.ejb.UsuariServiceHome;
//import es.caib.signatura.api.Signature;
//import es.caib.signatura.impl.CMSSignature;

public class UsuariTest extends TestCase {

	private UsuariService service;
	private GrupService grupService;
	private ImpressoraService impressoraService;
	private AplicacioService aplicacioService;
	
	private static String codiUsuari = "proves"; //$NON-NLS-1$

	protected void setUp() throws Exception {
		super.setUp();
		try{
		Properties properties = null;
		properties = new Properties();
		properties.load(new FileInputStream("jndi.properties")); //$NON-NLS-1$
		System.getProperties().put("java.security.auth.login.config", //$NON-NLS-1$
				"security.conf"); //$NON-NLS-1$
		Context ctx = new InitialContext(properties);
		ClientLogin login = new ClientLogin("u89559", "pass"); //$NON-NLS-1$ //$NON-NLS-2$
		login.login();
		
		Object obj = ctx.lookup(UsuariServiceHome.JNDI_NAME);
		UsuariServiceHome usuariHome = (UsuariServiceHome) PortableRemoteObject
				.narrow(obj, UsuariServiceHome.class);
		service = usuariHome.create();

		Object objectGrup = ctx.lookup(GrupServiceHome.JNDI_NAME);
		GrupServiceHome grupHome = (GrupServiceHome) PortableRemoteObject
				.narrow(objectGrup, GrupServiceHome.class);
		grupService = grupHome.create();

		/*
		Object obj2 = ctx.lookup(AplicacioServiceHome.JNDI_NAME);
		AplicacioServiceHome home2 = (AplicacioServiceHome) PortableRemoteObject
				.narrow(obj2, AplicacioServiceHome.class);
		aplicacioService = home2.create();

		
		Object objectImpressora = ctx.lookup(ImpressoraServiceHome.JNDI_NAME);
		ImpressoraServiceHome impressoraHome = (ImpressoraServiceHome) PortableRemoteObject
				.narrow(objectImpressora, ImpressoraServiceHome.class);
		impressoraService = impressoraHome.create();
		*/
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	public void testBaixaUsuari()  throws RemoteException {
		service.baixaUsuari("u86130"); //$NON-NLS-1$
	}
/*
	public void testExisteixNomCurt() throws RemoteException {
		String nomCurt = "lalal2";
		Boolean existeix = service.existeixNomCurt(nomCurt);
		Assert.assertFalse(existeix.booleanValue());
		nomCurt = "jdpto8";
		existeix = service.existeixNomCurt(nomCurt);
		Assert.assertTrue(existeix.booleanValue());
	}

	public void testCreate() throws RemoteException {
		Usuari usuari = new Usuari();
		usuari.setCodi(codiUsuari);
		usuari.setNom("joan3");
		usuari.setPrimerLlinatge("mateu3");
		GregorianCalendar gregorianCalendar = GregorianCalendar.getInstance();
		Calendar currentCalendar = gregorianCalendar.getInstance();
		Date currentDate = new Date();
		currentCalendar.setTime(currentDate);
		usuari.setDataCreacio(currentCalendar);
		usuari.setUsuariCreacio("u89559");
		usuari.setActiu(new Boolean(true));
		usuari.setPasswordMaxAge(new Long(10));
		usuari.setCodiGrupPrimari("");
		usuari.setNIF("43152443Q");
		usuari.setMultiSessio(new Boolean(true));
		usuari.setUsuariCreacio("u89559");
		usuari.setTipusUsuari("E");
		usuari = service.create(usuari);
		assertNotNull(usuari);
		assertTrue(usuari.getCodi().compareTo(codiUsuari) == 0);
		assertTrue(usuari.getNom().compareTo("joan3") == 0);
		assertTrue(usuari.getPrimerLlinatge().compareTo("mateu3") == 0);
		assertTrue(usuari.getUsuariCreacio().compareTo("u89559") == 0);
		assertTrue(usuari.getActiu().booleanValue());
		assertTrue(usuari.getNIF().compareTo("43152443Q") == 0);
		assertTrue(usuari.getMultiSessio().booleanValue());
		assertTrue(usuari.getTipusUsuari().compareTo("E") == 0);
		usuari = service.findUsuariByCodiUsuari(codiUsuari);
		assertNotNull(usuari);
		assertTrue(usuari.getCodi().compareTo(codiUsuari) == 0);
		assertTrue(usuari.getNom().compareTo("joan3") == 0);
		assertTrue(usuari.getPrimerLlinatge().compareTo("mateu3") == 0);
		assertTrue(usuari.getUsuariCreacio().compareTo("u89559") == 0);
		assertTrue(usuari.getActiu().booleanValue());
		assertTrue(usuari.getNIF().compareTo("43152443Q") == 0);
		assertTrue(usuari.getMultiSessio().booleanValue());
		assertTrue(usuari.getTipusUsuari().compareTo("E") == 0);
	}

	public void testGetDireccionsGeneralsByCodiUsuari() throws RemoteException {
		String codiGrup = "DGTIC";
		UsuariGrup grupUsuari = new UsuariGrup();
		grupUsuari.setCodiGrup(codiGrup);
		grupUsuari.setCodiUsuari("u89559");
		grupService.create(grupUsuari);

		Collection grups = service
				.getDireccionsGeneralsByCodiUsuari(codiUsuari);
		assertTrue(grups.size() > 0);
		boolean trobat = false;
		Iterator iterator = grups.iterator();
		while (iterator.hasNext() && !trobat) {
			Grup grup = (Grup) iterator.next();
			trobat = grup.getCodi().compareTo("DGTIC") == 0;
		}
		assertTrue(trobat);
	}

	public void testGetConselleriesByCodiUsuari() throws RemoteException {
		Collection grups = service.getConselleriesByCodiUsuari(codiUsuari);
		assertTrue(grups.size() > 0);
		boolean trobat = false;
		Iterator iterator = grups.iterator();
		while (iterator.hasNext() && !trobat) {
			Grup grup = (Grup) iterator.next();
			trobat = grup.getCodi().compareTo("ecohinova") == 0;
		}
		assertTrue(trobat);
	}
	public void testFindTotsUsuari() throws RemoteException {
		boolean error = false;
		try{
		String codi = null;
		String nom = "%";
		String primerLlinatge = null;
		String nomCurt = "";
		String dataCreacio = null;
		String actiu = "";
		String segonLlinatge = null;
		String multiSessio = "";
		String comentari = null;
		String tipusUsuari = "";
		String servidorPerfil = null;
		String servidorHome = "";
		String servidorCorreu = null;
		String codiGrupPrimari = "";
		String usuariCreacio = null;
		String dni = "";
		Collection usuaris = service.findUsuariByCriteri(codi, nom,
				primerLlinatge, nomCurt, dataCreacio, usuariCreacio, actiu,
				segonLlinatge, multiSessio, comentari, tipusUsuari,
				servidorPerfil, servidorHome, servidorCorreu, codiGrupPrimari,
				dni);
		}catch(Exception e){
			e.printStackTrace();
			error = true;
		}
		assertTrue(error);

	}

	public void testFindUsuari() throws RemoteException {
		String codi = null;
		String nom = "Maria Espera%";
		String primerLlinatge = null;
		String nomCurt = "";
		String dataCreacio = null;
		String actiu = "";
		String segonLlinatge = null;
		String multiSessio = "";
		String comentari = null;
		String tipusUsuari = "";
		String servidorPerfil = null;
		String servidorHome = "";
		String servidorCorreu = null;
		String codiGrupPrimari = "";
		String usuariCreacio = null;
		String dni = "";
		Collection usuaris = service.findUsuariByCriteri(codi, nom,
				primerLlinatge, nomCurt, dataCreacio, usuariCreacio, actiu,
				segonLlinatge, multiSessio, comentari, tipusUsuari,
				servidorPerfil, servidorHome, servidorCorreu, codiGrupPrimari,
				dni);
		assertTrue(usuaris.size() > 0);
		Iterator iterator = usuaris.iterator();
		while (iterator.hasNext()) {
			Usuari usuari = (Usuari) iterator.next();
			System.out.println("Usuari: " + usuari.getNom());
			assertTrue(usuari.getNom().startsWith("Maria Espera"));
		}

		codi = null;
		nom = "%";
		primerLlinatge = "Gelabert Gira%";
		nomCurt = "";
		dataCreacio = null;
		actiu = "";
		segonLlinatge = null;
		multiSessio = "";
		comentari = null;
		tipusUsuari = "";
		servidorPerfil = null;
		servidorHome = "";
		servidorCorreu = null;
		codiGrupPrimari = "";
		usuariCreacio = null;
		dni = null;
		usuaris = service.findUsuariByCriteri(codi, nom, primerLlinatge,
				nomCurt, dataCreacio, usuariCreacio, actiu, segonLlinatge,
				multiSessio, comentari, tipusUsuari, servidorPerfil,
				servidorHome, servidorCorreu, codiGrupPrimari, dni);
		assertTrue(usuaris.size() > 0);
		iterator = usuaris.iterator();
		while (iterator.hasNext()) {
			Usuari usuari = (Usuari) iterator.next();
			assertTrue(usuari.getPrimerLlinatge().startsWith("Gelabert Gira"));
		}

		codi = null;
		nom = "%";
		primerLlinatge = "%";
		nomCurt = "";
		dataCreacio = null;
		actiu = "";
		segonLlinatge = "Molina-Prad%";
		multiSessio = "";
		comentari = null;
		tipusUsuari = "";
		servidorPerfil = null;
		servidorHome = "";
		servidorCorreu = null;
		codiGrupPrimari = "";
		usuariCreacio = null;
		dni = "%";
		usuaris = service.findUsuariByCriteri(codi, nom, primerLlinatge,
				nomCurt, dataCreacio, usuariCreacio, actiu, segonLlinatge,
				multiSessio, comentari, tipusUsuari, servidorPerfil,
				servidorHome, servidorCorreu, codiGrupPrimari, dni);
		assertTrue(usuaris.size() > 0);
		iterator = usuaris.iterator();
		while (iterator.hasNext()) {
			Usuari usuari = (Usuari) iterator.next();
			assertTrue(usuari.getSegonLlinatge().startsWith("Molina-Prad"));
		}

		codi = null;
		nom = "%";
		primerLlinatge = "%";
		nomCurt = "mfuentesa%";
		dataCreacio = null;
		actiu = "";
		segonLlinatge = "%";
		multiSessio = "";
		comentari = null;
		tipusUsuari = "";
		servidorPerfil = null;
		servidorHome = "";
		servidorCorreu = null;
		codiGrupPrimari = "";
		usuariCreacio = null;
		dni = "%";
		usuaris = service.findUsuariByCriteri(codi, nom, primerLlinatge,
				nomCurt, dataCreacio, usuariCreacio, actiu, segonLlinatge,
				multiSessio, comentari, tipusUsuari, servidorPerfil,
				servidorHome, servidorCorreu, codiGrupPrimari, dni);
		assertTrue(usuaris.size() > 0);
		iterator = usuaris.iterator();
		while (iterator.hasNext()) {
			Usuari usuari = (Usuari) iterator.next();
			assertTrue(usuari.getNomCurt().startsWith("mfuentesa"));
		}

		codi = null;
		nom = "Oscar";
		primerLlinatge = "%";
		nomCurt = "%";
		dataCreacio = null;
		actiu = "S";
		segonLlinatge = "%";
		multiSessio = "";
		comentari = null;
		tipusUsuari = "";
		servidorPerfil = null;
		servidorHome = "";
		servidorCorreu = null;
		codiGrupPrimari = "";
		usuariCreacio = null;
		dni = "%";
		usuaris = service.findUsuariByCriteri(codi, nom, primerLlinatge,
				nomCurt, dataCreacio, usuariCreacio, actiu, segonLlinatge,
				multiSessio, comentari, tipusUsuari, servidorPerfil,
				servidorHome, servidorCorreu, codiGrupPrimari, dni);
		assertTrue(usuaris.size() > 0);
		iterator = usuaris.iterator();
		while (iterator.hasNext()) {
			Usuari usuari = (Usuari) iterator.next();
			assertTrue(usuari.getActiu().booleanValue());
		}

		codi = null;
		nom = "Ajuntament%";
		primerLlinatge = "%";
		nomCurt = "%";
		dataCreacio = null;
		actiu = "%";
		segonLlinatge = "%";
		multiSessio = "";
		comentari = null;
		tipusUsuari = "E";
		servidorPerfil = null;
		servidorHome = "";
		servidorCorreu = null;
		codiGrupPrimari = "";
		usuariCreacio = null;
		dni = "%";
		usuaris = service.findUsuariByCriteri(codi, nom, primerLlinatge,
				nomCurt, dataCreacio, usuariCreacio, actiu, segonLlinatge,
				multiSessio, comentari, tipusUsuari, servidorPerfil,
				servidorHome, servidorCorreu, codiGrupPrimari, dni);
		assertTrue(usuaris.size() > 0);
		iterator = usuaris.iterator();
		while (iterator.hasNext()) {
			Usuari usuari = (Usuari) iterator.next();
			assertTrue(usuari.getTipusUsuari().compareTo("E") == 0);
		}

		codi = null;
		nom = "Juan";
		primerLlinatge = "%";
		nomCurt = "%";
		dataCreacio = null;
		actiu = "S";
		segonLlinatge = "%";
		multiSessio = "";
		comentari = null;
		tipusUsuari = "";
		servidorPerfil = null;
		servidorHome = "lofihom1";
		servidorCorreu = null;
		codiGrupPrimari = "";
		usuariCreacio = null;
		dni = "%";
		usuaris = service.findUsuariByCriteri(codi, nom, primerLlinatge,
				nomCurt, dataCreacio, usuariCreacio, actiu, segonLlinatge,
				multiSessio, comentari, tipusUsuari, servidorPerfil,
				servidorHome, servidorCorreu, codiGrupPrimari, dni);
		assertTrue(usuaris.size() > 0);
		iterator = usuaris.iterator();
		while (iterator.hasNext()) {
			Usuari usuari = (Usuari) iterator.next();
			assertTrue(usuari.getServidorHome().compareTo("lofihom1") == 0);
		}

		codi = null;
		nom = "Felix";
		primerLlinatge = "%";
		nomCurt = "%";
		dataCreacio = null;
		actiu = "S";
		segonLlinatge = "%";
		multiSessio = "";
		comentari = null;
		tipusUsuari = "";
		servidorPerfil = "lofipro1";
		servidorHome = "";
		servidorCorreu = null;
		codiGrupPrimari = "";
		usuariCreacio = null;
		dni = "%";
		usuaris = service.findUsuariByCriteri(codi, nom, primerLlinatge,
				nomCurt, dataCreacio, usuariCreacio, actiu, segonLlinatge,
				multiSessio, comentari, tipusUsuari, servidorPerfil,
				servidorHome, servidorCorreu, codiGrupPrimari, dni);
		assertTrue(usuaris.size() > 0);
		iterator = usuaris.iterator();
		while (iterator.hasNext()) {
			Usuari usuari = (Usuari) iterator.next();
			assertTrue(usuari.getServidorPerfil().compareTo("lofipro1") == 0);
		}

		codi = null;
		nom = "Felix";
		primerLlinatge = "%";
		nomCurt = "%";
		dataCreacio = null;
		actiu = "S";
		segonLlinatge = "%";
		multiSessio = "";
		comentari = null;
		tipusUsuari = "";
		servidorPerfil = null;
		servidorHome = "%";
		servidorCorreu = "scorlin1";
		codiGrupPrimari = "";
		usuariCreacio = null;
		dni = "%";
		usuaris = service.findUsuariByCriteri(codi, nom, primerLlinatge,
				nomCurt, dataCreacio, usuariCreacio, actiu, segonLlinatge,
				multiSessio, comentari, tipusUsuari, servidorPerfil,
				servidorHome, servidorCorreu, codiGrupPrimari, dni);
		assertTrue(usuaris.size() > 0);
		iterator = usuaris.iterator();
		while (iterator.hasNext()) {
			Usuari usuari = (Usuari) iterator.next();
			assertTrue(usuari.getServidorCorreu().compareTo("scorlin1") == 0);
		}

		codi = null;
		nom = "";
		primerLlinatge = "%";
		nomCurt = "%";
		dataCreacio = ">31/01/2007 01:01:01";
		actiu = "S";
		segonLlinatge = "%";
		multiSessio = "";
		comentari = null;
		tipusUsuari = "";
		servidorPerfil = null;
		servidorHome = "%";
		servidorCorreu = "%";
		codiGrupPrimari = "";
		usuariCreacio = null;
		dni = "%";
		usuaris = service.findUsuariByCriteri(codi, nom, primerLlinatge,
				nomCurt, dataCreacio, usuariCreacio, actiu, segonLlinatge,
				multiSessio, comentari, tipusUsuari, servidorPerfil,
				servidorHome, servidorCorreu, codiGrupPrimari, dni);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy kk:mm:ss");
		assertTrue(usuaris.size() > 0);
		iterator = usuaris.iterator();
		while (iterator.hasNext()) {
			Usuari usuari = (Usuari) iterator.next();
			try {
				Date currentDate = usuari.getDataCreacio().getTime();
				Date rightDate = dateFormat.parse("31/01/2007 01:01:01");
				assertTrue(currentDate.after(rightDate));
			} catch (Exception e) {
				e.printStackTrace();
				fail();
			}
		}

		codi = null;
		nom = "";
		primerLlinatge = "%";
		nomCurt = "%";
		dataCreacio = "<15/11/2000 08:59:44";
		actiu = "S";
		segonLlinatge = "%";
		multiSessio = "";
		comentari = null;
		tipusUsuari = "";
		servidorPerfil = null;
		servidorHome = "%";
		servidorCorreu = "%";
		codiGrupPrimari = "";
		usuariCreacio = null;
		dni = "%";
		usuaris = service.findUsuariByCriteri(codi, nom, primerLlinatge,
				nomCurt, dataCreacio, usuariCreacio, actiu, segonLlinatge,
				multiSessio, comentari, tipusUsuari, servidorPerfil,
				servidorHome, servidorCorreu, codiGrupPrimari, dni);
		dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
		assertTrue(usuaris.size() > 0);
		int count = 0;
		iterator = usuaris.iterator();
		while (iterator.hasNext() && count < 30) {
			Usuari usuari = (Usuari) iterator.next();
			try {
				Date currentDate = usuari.getDataCreacio().getTime();
				Date rightDate = dateFormat.parse("15/11/2000 08:59:44");
				assertTrue(currentDate.before(rightDate));
			} catch (Exception e) {
				e.printStackTrace();
				fail();
			}
			count++;
		}

		codi = null;
		nom = "";
		primerLlinatge = "%";
		nomCurt = "%";
		dataCreacio = "=31/01/2007";
		actiu = "S";
		segonLlinatge = "%";
		multiSessio = "";
		comentari = null;
		tipusUsuari = "";
		servidorPerfil = null;
		servidorHome = "%";
		servidorCorreu = "%";
		codiGrupPrimari = "";
		usuariCreacio = null;
		dni = "%";
		usuaris = service.findUsuariByCriteri(codi, nom, primerLlinatge,
				nomCurt, dataCreacio, usuariCreacio, actiu, segonLlinatge,
				multiSessio, comentari, tipusUsuari, servidorPerfil,
				servidorHome, servidorCorreu, codiGrupPrimari, dni);
		assertTrue(usuaris.size() > 0);
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		iterator = usuaris.iterator();
		while (iterator.hasNext()) {
			Usuari usuari = (Usuari) iterator.next();
			try {
				Date currentDate = usuari.getDataCreacio().getTime();
				String currentDateString = dateFormat.format(currentDate);
				String rightDate = "31/01/2007";
				assertTrue(currentDateString.compareTo(rightDate) == 0);
			} catch (Exception e) {
				e.printStackTrace();
				fail();
			}
		}

		codi = null;
		nom = "";
		primerLlinatge = "%";
		nomCurt = "%";
		dataCreacio = "=15/11/2000 09:00:07";
		actiu = "S";
		segonLlinatge = "%";
		multiSessio = "";
		comentari = null;
		tipusUsuari = "";
		servidorPerfil = null;
		servidorHome = "%";
		servidorCorreu = "%";
		codiGrupPrimari = "";
		usuariCreacio = null;
		dni = "%";
		usuaris = service.findUsuariByCriteri(codi, nom, primerLlinatge,
				nomCurt, dataCreacio, usuariCreacio, actiu, segonLlinatge,
				multiSessio, comentari, tipusUsuari, servidorPerfil,
				servidorHome, servidorCorreu, codiGrupPrimari, dni);
		assertTrue(usuaris.size() > 0);
		dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
		iterator = usuaris.iterator();
		while (iterator.hasNext()) {
			Usuari usuari = (Usuari) iterator.next();
			try {
				Date currentDate = usuari.getDataCreacio().getTime();
				String currentDateString = dateFormat.format(currentDate);
				String rightDate = "15/11/2000 09:00:07";
				assertTrue(currentDateString.compareTo(rightDate) == 0);
			} catch (Exception e) {
				e.printStackTrace();
				fail();
			}
		}
		
	}

	public void testFindImpressoresByCodiUsuari() throws Exception {
		UsuariImpressora usuariImpressora = new UsuariImpressora();
		usuariImpressora.setCodiImpressora("iprepnc15");
		usuariImpressora.setCodiUsuari(codiUsuari);
		usuariImpressora.setPerDefecte(new Boolean(false));
		impressoraService.create(usuariImpressora);

		Collection impressores = service
				.findImpressoresByCodiUsuari(codiUsuari);
		assertTrue(impressores.size() > 0);
		boolean trobat = false;
		Iterator iterator = impressores.iterator();
		while (iterator.hasNext() && !trobat) {
			Impressora impressora = (Impressora) iterator.next();
			trobat = impressora.getCodi().compareTo("iprepnc15") == 0;
		}
		assertTrue(trobat);

		Collection usuariImpressores = service.
			findUsuariImpressoresByCodiUsuari(codiUsuari);
		assertTrue(impressores.size() > 0);
		trobat = false;
		iterator = usuariImpressores.iterator();
		while (iterator.hasNext() && !trobat) {
			usuariImpressora = (UsuariImpressora) iterator.next();
			trobat = usuariImpressora.getCodiImpressora().compareTo("iprepnc15") == 0;
			trobat = usuariImpressora.getCodiUsuari().compareTo(codiUsuari) == 0;
		}
		assertTrue(trobat);

		impressoraService.delete(usuariImpressora);

		impressores = service.findImpressoresByCodiUsuari(codiUsuari);
		trobat = false;
		iterator = impressores.iterator();
		while (iterator.hasNext() && !trobat) {
			Impressora impressora = (Impressora) iterator.next();
			trobat = impressora.getCodi().compareTo("iprepnc15") == 0;
		}
		assertFalse(trobat);
	}

	public void testFindUsuariByNIFUsuari() throws java.lang.Exception {
		Usuari usuari = service.findUsuariByNIFUsuari("43152443Q");
		assertNotNull(usuari);
		assertTrue(usuari.getCodi().compareTo(codiUsuari) == 0);
		assertTrue(usuari.getNom().compareTo("joan3") == 0);
		assertTrue(usuari.getPrimerLlinatge().compareTo("mateu3") == 0);
		assertTrue(usuari.getUsuariCreacio().compareTo("u89559") == 0);
		assertTrue(usuari.getActiu().booleanValue());
		assertTrue(usuari.getNIF().compareTo("43152443Q") == 0);
		assertTrue(usuari.getMultiSessio().booleanValue());
		assertTrue(usuari.getTipusUsuari().compareTo("E") == 0);
	}

	public void testFindUsuariByCodiUsuari() throws java.lang.Exception {
		Usuari usuari = service.findUsuariByCodiUsuari(codiUsuari);
		assertNotNull(usuari);
		assertTrue(usuari.getCodi().compareTo(codiUsuari) == 0);
		assertTrue(usuari.getNom().compareTo("joan3") == 0);
		assertTrue(usuari.getPrimerLlinatge().compareTo("mateu3") == 0);
		assertTrue(usuari.getUsuariCreacio().compareTo("u89559") == 0);
		assertTrue(usuari.getActiu().booleanValue());
		assertTrue(usuari.getNIF().compareTo("43152443Q") == 0);
		assertTrue(usuari.getMultiSessio().booleanValue());
		assertTrue(usuari.getTipusUsuari().compareTo("E") == 0);
	}

	public void testFindDadesUsuariByCodiUsuari() throws Exception {
		Collection dades = service.findDadesUsuariByCodiUsuari(codiUsuari);
		boolean trobat = false;
		Iterator iterator = dades.iterator();
		while (iterator.hasNext() && !trobat) {
			DadaUsuari dadaUsuari = (DadaUsuari) iterator.next();
			trobat = dadaUsuari.getCodiDada().compareTo("NIF") == 0
					&& dadaUsuari.getValorDada().compareTo("43152443Q") == 0;
		}
		assertTrue(trobat);

	}
	public void testGetRolsByCodiUsuari() throws Exception {
		RolsUsuaris rolUsuari = new RolsUsuaris();
		rolUsuari.setCodiAplicacio("SAP");
		//rolUsuari.setCodiGrup(null);
		rolUsuari.setCodiUsuari(codiUsuari);
		rolUsuari.setNomRol("SAP_PROVA");
		rolUsuari = aplicacioService.create(rolUsuari);

		Collection rols = service.getRolsByCodiUsuari(codiUsuari);
		assertTrue(rols.size() > 0);
		boolean trobat = false;
		Iterator iterator = rols.iterator();
		while (iterator.hasNext()) {
			Rol rol = (Rol) iterator.next();
			trobat = rol.getNom().compareTo("SAP_PROVA") == 0;
		}
		assertTrue(trobat);
		
		aplicacioService.delete(rolUsuari);
		
		rols = service.getRolsByCodiUsuari(codiUsuari);
		assertTrue(rols.size() > 0);
		trobat = false;
		iterator = rols.iterator();
		while (iterator.hasNext()) {
			Rol rol = (Rol) iterator.next();
			trobat = rol.getNom().compareTo("SAP_PROVA") == 0;
		}
		assertFalse(trobat);
				
	}
	public void testGetRolsAplicacioByCodiUsuariAndCodiAplicacio()
			throws Exception {
		Collection rols = service.getRolsAplicacioByCodiUsuariAndCodiAplicacio(
				codiUsuari, "SEYCON");
		assertTrue(rols.size() > 0);
		boolean trobat = false;
		Iterator iterator = rols.iterator();
		while (iterator.hasNext() && !trobat) {
			Rol rol = (Rol) iterator.next();
			trobat = rol.getCodiAplicacio().compareTo("SEYCON") == 0;
		}
		assertTrue(trobat);
	}

	public void testGetAplicacionsByCodiUsuari() throws Exception {
		Collection aplicacions = service.getAplicacionsByCodiUsuari(codiUsuari);
		assertTrue(aplicacions.size() > 0);
		boolean trobat = false;
		Iterator iterator = aplicacions.iterator();
		while (iterator.hasNext() && !trobat) {
			Aplicacio aplicacio = (Aplicacio) iterator.next();
			trobat = aplicacio.getCodi().compareTo("SEYCON") == 0;
		}
		assertTrue(trobat);
	}

	public void testFindUsuarisByDadesBasiques() throws Exception {
		String nom = "Oscar";
		String codi = "%";
		String primerLlinatge = null;
		String segonLlinatge = null;
		String dni = null;
		Collection usuaris = service.findUsuarisByDadesBasiques(codi, nom,
				primerLlinatge, segonLlinatge, dni);
		assertTrue(usuaris.size() > 0);
		Iterator iterator = usuaris.iterator();
		while (iterator.hasNext()) {
			Usuari usuari = (Usuari) iterator.next();
			assertTrue(usuari.getNom().startsWith("Oscar"));
		}

		codi = null;
		nom = "%";
		primerLlinatge = "Gelabert Gira%";
		segonLlinatge = "%";
		dni = "";
		usuaris = service.findUsuarisByDadesBasiques(codi, nom, primerLlinatge,
				segonLlinatge, dni);
		assertTrue(usuaris.size() > 0);
		iterator = usuaris.iterator();
		while (iterator.hasNext()) {
			Usuari usuari = (Usuari) iterator.next();
			assertTrue(usuari.getPrimerLlinatge().startsWith("Gelabert Gira"));
		}

		codi = "";
		nom = "%";
		primerLlinatge = null;
		segonLlinatge = "Molina-Prad%";
		dni = "%";
		usuaris = service.findUsuarisByDadesBasiques(codi, nom, primerLlinatge,
				segonLlinatge, dni);
		assertTrue(usuaris.size() > 0);
		iterator = usuaris.iterator();
		while (iterator.hasNext()) {
			Usuari usuari = (Usuari) iterator.next();
			assertTrue(usuari.getSegonLlinatge().startsWith("Molina-Prad"));
		}

		nom = "%";
		codi = "u8955%";
		primerLlinatge = null;
		segonLlinatge = "";
		dni = null;
		usuaris = service.findUsuarisByDadesBasiques(codi, nom, primerLlinatge,
				segonLlinatge, dni);
		assertTrue(usuaris.size() > 0);
		iterator = usuaris.iterator();
		while (iterator.hasNext()) {
			Usuari usuari = (Usuari) iterator.next();
			assertTrue(usuari.getCodi().startsWith("u8955"));
		}
	}

	public void testSetServidorsToUsuari() throws java.lang.Exception {
		service.setServidorsToUsuari(codiUsuari, "lofipro1", "scorlin1",
				"lofihom1");
		Usuari usuari = service.findUsuariByCodiUsuari(codiUsuari);
		assertNotNull(usuari);
		assertTrue(usuari.getServidorCorreu().compareTo("scorlin1") == 0);
		assertTrue(usuari.getServidorHome().compareTo("lofihom1") == 0);
		assertTrue(usuari.getServidorPerfil().compareTo("lofipro1") == 0);

	}
	
	public void testFindUsuarisAmbRolsDAplicacioByCodiAplicacio() throws Exception{
		System.out.println("testFindUsuarisAmbRolsDAplicacioByCodiAplicacio");
		Collection usuaris = service.findUsuarisAmbRolsDAplicacioByCodiAplicacio("SEYCON");		
		Iterator usuariIterator = usuaris.iterator();
		while(usuariIterator.hasNext()){
			Usuari usuari = (Usuari) usuariIterator.next();
			System.out.println("Codi d'usuari: " + usuari.getCodi());
		}
	}
	*/
}
