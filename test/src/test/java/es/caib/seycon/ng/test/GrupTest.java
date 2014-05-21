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
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.RolsUsuaris;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.UsuariGrup;
import es.caib.seycon.ng.servei.ejb.AplicacioService;
import es.caib.seycon.ng.servei.ejb.AplicacioServiceHome;
import es.caib.seycon.ng.servei.ejb.DominiServiceHome;
import es.caib.seycon.ng.servei.ejb.GrupService;
import es.caib.seycon.ng.servei.ejb.GrupServiceHome;
import es.caib.seycon.ng.servei.ejb.UsuariService;
import es.caib.seycon.ng.servei.ejb.UsuariServiceHome;

public class GrupTest extends TestCase {

	private GrupService service;
	private UsuariService serviceUsuari;
	private AplicacioService aplicacioService;
	private static String codiGrup = "gru_prova"; //$NON-NLS-1$

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

			// Context ctx = InitialContextFactory.getInitialContext();
			Object obj = ctx.lookup(GrupServiceHome.JNDI_NAME);
			GrupServiceHome grupHome = (GrupServiceHome) PortableRemoteObject
					.narrow(obj, GrupServiceHome.class);
			service = grupHome.create();

			Object obj2 = ctx.lookup(AplicacioServiceHome.JNDI_NAME);
			AplicacioServiceHome home2 = (AplicacioServiceHome) PortableRemoteObject
					.narrow(obj2, AplicacioServiceHome.class);
			aplicacioService = home2.create();
			
			Object objUsuari = ctx.lookup(UsuariServiceHome.JNDI_NAME);
			UsuariServiceHome usuariHome = (UsuariServiceHome) PortableRemoteObject
					.narrow(objUsuari, UsuariServiceHome.class);
			serviceUsuari = usuariHome.create();
			
			obj = ctx.lookup(DominiServiceHome.JNDI_NAME);
			DominiServiceHome dominiHome = (DominiServiceHome) PortableRemoteObject
					.narrow(obj, DominiServiceHome.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*
	public void testCreate() throws RemoteException {
		Grup grup = new Grup();
		grup.setCodi(codiGrup);
		grup.setQuota("100");
		grup.setUnitatOfimatica("W:");
		grup.setDescripcio("proves");
		grup.setTipus("CONSELLERIA");
		grup = service.create(grup);
		Assert.assertNotNull(grup);
		Assert.assertEquals(codiGrup, grup.getCodi());

		grup = service.findGrupByCodiGrup(codiGrup);
		Assert.assertNotNull(grup);
		Assert.assertEquals(codiGrup, grup.getCodi());

		grup = new Grup();
		grup.setCodi(codiGrup + "II");
		grup.setQuota("100");
		grup.setUnitatOfimatica("W:");
		grup.setDescripcio("proves");
		grup.setTipus("CONSELLERIA");
		grup.setCodiPare(codiGrup);
		grup = service.create(grup);
		Assert.assertNotNull(grup);
		Assert.assertEquals(codiGrup + "II", grup.getCodi());

		grup = service.findGrupByCodiGrup(codiGrup + "II");
		Assert.assertNotNull(grup);
		Assert.assertEquals(codiGrup + "II", grup.getCodi());

	}

	public void testFindGrupsByFiltre() throws RemoteException {
		boolean error = false;
		try {
			Collection collection = service.findGrupsByFiltre("%", null, "",
					"%", null);
			assertTrue(collection.size() > 0);
			Iterator iterator = collection.iterator();
			while (iterator.hasNext()) {
				Grup grup = (Grup) iterator.next();
				assertTrue(grup.getCodi().startsWith("gru"));
			}
		} catch (Exception e) {
			error = true;
		}
		assertTrue(error);

		Collection collection = service.findGrupsByFiltre("gru%", null, "",
				"%", null);
		assertTrue(collection.size() > 0);
		Iterator iterator = collection.iterator();
		while (iterator.hasNext()) {
			Grup grup = (Grup) iterator.next();
			assertTrue(grup.getCodi().startsWith("gru"));
		}

		collection = service.findGrupsByFiltre(null, codiGrup, null, "", "%");
		assertTrue(collection.size() > 0);
		iterator = collection.iterator();
		while (iterator.hasNext()) {
			Grup grup = (Grup) iterator.next();
			assertTrue(grup.getCodiPare().startsWith(codiGrup));
		}

		collection = service.findGrupsByFiltre("%", null, "W%", null, "");
		assertTrue(collection.size() > 0);
		iterator = collection.iterator();
		while (iterator.hasNext()) {
			Grup grup = (Grup) iterator.next();
			assertTrue(grup.getUnitatOfimatica().startsWith("W"));
		}
		collection = service.findGrupsByFiltre("", "%", null, "pro%", null);
		assertTrue(collection.size() > 0);
		iterator = collection.iterator();
		while (iterator.hasNext()) {
			Grup grup = (Grup) iterator.next();
			assertTrue(grup.getDescripcio().startsWith("pro"));
		}
		collection = service.findGrupsByFiltre(null, "", "%", null, "CONSE%");
		assertTrue(collection.size() > 0);
		iterator = collection.iterator();
		while (iterator.hasNext()) {
			Grup grup = (Grup) iterator.next();
			assertTrue(grup.getTipus().startsWith("CONSE"));
		}

	}

	public void testFindRolAdministradorByCodiGrup() throws RemoteException {
		String codiUsuari = "u89559";
		RolsUsuaris rolsUsuaris = new RolsUsuaris();
		rolsUsuaris.setCodiAplicacio("SEYCON");
		//rolsUsuaris.setCodiGrup(codiGrup);
		rolsUsuaris.setCodiUsuari(codiUsuari);
		rolsUsuaris.setNomRol("SC_CAP_RRHH");
		aplicacioService.create(rolsUsuaris);

		//Rol rol = service.findRolAdministradorByCodiGrup(codiGrup);
		//assertNotNull(rol);
	}


	public void testFindUsuariGrupsByCodiUsuari() throws RemoteException {
		String codiUsuari = "u89559";
		Collection collection = service.findUsuariGrupsByCodiUsuari(codiUsuari);
		Iterator iterator = collection.iterator();
		boolean trobat = false;
		while (iterator.hasNext() && !trobat) {
			UsuariGrup usugrup = (UsuariGrup) iterator.next();
			trobat = usugrup.getCodiGrup().compareTo(codiGrup) == 0;
		}
		assertTrue(trobat);
	}*/
/*
	public void testFindRolsUsuarisAmbGrupByCodiUsuari() throws RemoteException {
		String codiUsuari = "u89559";
		Collection collection = service
				.findRolsUsuarisAmbGrupByCodiUsuari(codiUsuari);
		Iterator iterator = collection.iterator();
		boolean trobat = false;
		while (iterator.hasNext() && !trobat) {
			RolsUsuaris rolusu = (RolsUsuaris) iterator.next();
			trobat = rolusu.getCodiGrup().compareTo(codiGrup) == 0
					&& rolusu.getCodiAplicacio() != null;
		}
		assertTrue(trobat);
	}
*//*
	public void testGetUnitatsOrganitzativesDependents() throws RemoteException {
		Collection conselleries = service.findGrupsByTipusGrup("CONSELLERIA");
		Iterator iterator = conselleries.iterator();
		while (iterator.hasNext()) {
			Grup grup = (Grup) iterator.next();
		}

		Collection direccionsGenerals = service
				.findGrupsByTipusGrup("DIRECCIO_GENERAL");
		iterator = direccionsGenerals.iterator();
		while (iterator.hasNext()) {
			Grup grup = (Grup) iterator.next();
		}

		Collection grups = service.findSubGrupsByCodiGrup("ecohinova");
		assertTrue(grups.size() > 0);
		iterator = grups.iterator();
		while (iterator.hasNext()) {
			Grup grup = (Grup) iterator.next();
			System.out.println("codi del grup: " + grup.getCodi());
		}
		Collection impressoras = service.getGrups();
	}*/
/*
	public void testGetGrupPrimari() throws Exception {
		String codiUsuari = "u89559";
		Grup grupPrimari = service.findGrupPrimariByCodiUsuari(codiUsuari);
		Assert.assertNotNull(grupPrimari);
	}
*//*
	public void testGetGrupsFromUsuaris() throws Exception {
		String codiUsuari = "u89559";
		Collection grups = service.findGrupsFromUsuarisByCodiUsuari(codiUsuari);
		Assert.assertNotNull(grups);
		Iterator iterator = grups.iterator();
		boolean trobat = false;
		while (iterator.hasNext() && !trobat) {
			Grup grup = (Grup) iterator.next();
			trobat = grup.getCodi().compareTo(codiGrup) == 0;
		}
		assertTrue(trobat);
	}
*/
	/*
	public void testGetGrupsFromRols() throws Exception {
		String codiUsuari = "u89559";
		Collection grups = service.findGrupsFromRolsByCodiUsuari(codiUsuari);
		Assert.assertNotNull(grups);
		Iterator iterator = grups.iterator();
		boolean trobat = false;
		while (iterator.hasNext() && !trobat) {
			Grup grup = (Grup) iterator.next();
			trobat = grup.getCodi().compareTo(codiGrup) == 0;
		}
		assertTrue(trobat);
	}*/
	/*
	public void testCreateUsuariGrup() throws Exception {
		String codiUsuari = "u89559";
		UsuariGrup usuariGrup = new UsuariGrup();
		usuariGrup.setCodiGrup(codiGrup);
		usuariGrup.setCodiUsuari(codiUsuari);
		//usuariGrup.setCarreg("director");
		usuariGrup = service.create(usuariGrup);
		assertNotNull(usuariGrup);
		assertEquals(codiGrup, usuariGrup.getCodiGrup());
		assertEquals(codiUsuari, usuariGrup.getCodiUsuari());
		usuariGrup = service.findUsuariGrupByCodiUsuariAndCodiGrup(codiUsuari,
				codiGrup);
		assertNotNull(usuariGrup);
		assertEquals(codiGrup, usuariGrup.getCodiGrup());
		assertEquals(codiUsuari, usuariGrup.getCodiUsuari());
		//assertEquals("director", usuariGrup.getCarreg());
		Usuari usuari = serviceUsuari.findUsuariByCodiUsuari(codiUsuari);
		assertNotNull(usuari);
	}

	public void testDeleteUsuariGrup() throws Exception {
		String codiUsuari = "u89559";
		UsuariGrup usuariGrup = service.findUsuariGrupByCodiUsuariAndCodiGrup(
				codiUsuari, codiGrup);
		assertNotNull(usuariGrup);
		assertEquals(codiGrup, usuariGrup.getCodiGrup());
		assertEquals(codiUsuari, usuariGrup.getCodiUsuari());
		service.delete(usuariGrup);
		usuariGrup = service.findUsuariGrupByCodiUsuariAndCodiGrup(codiUsuari,
				codiGrup);
		assertNull(usuariGrup);
	}

	public void testUndoFindRolAdministradorByCodiGrup() throws RemoteException {
		RolsUsuaris rolUsuari = aplicacioService
				.findRolsUsuarisByCodiUsuariAndNomRol("u89559", "SC_CAP_RRHH");
		aplicacioService.delete(rolUsuari);
		Rol rol = service.findRolAdministradorByCodiGrup(codiGrup);
		assertNull(rol);
	}*/
/*
	public void testRemoveGrupFromUsuari() throws Exception {
		String codiUsuari = "u89559";
		UsuariGrup usuariGrups = service.findUsuariGrupByCodiUsuariAndCodiGrup(
				codiUsuari, codiGrup);
		assertNotNull(usuariGrups);
		assertTrue(usuariGrups.getCodiUsuari().compareTo(codiUsuari) == 0);
		assertTrue(usuariGrups.getCodiGrup().compareTo(codiGrup) == 0);
		service.removeGrupFromUsuari(codiUsuari, codiGrup);
		usuariGrups = service.findUsuariGrupByCodiUsuariAndCodiGrup(codiUsuari,
				codiGrup);
		assertNull(usuariGrups);
	}
*//*
	public void testGetLlistaPares() throws Exception {
		Collection pares = service.getLlistaDePares("dgtic");
		Iterator paresIterator = pares.iterator();
		while(paresIterator.hasNext()){
			Grup grup = (Grup) paresIterator.next();
			System.out.println(grup.getCodi());
		}		
	}
	
	public void testDeleteGrup() throws Exception {
		Grup grup = service.findGrupByCodiGrup(codiGrup + "II");
		service.delete(grup);
		grup = service.findGrupByCodiGrup(codiGrup + "II");
		Assert.assertNull(grup);

		grup = service.findGrupByCodiGrup(codiGrup);
		service.delete(grup);
		grup = service.findGrupByCodiGrup(codiGrup);
		Assert.assertNull(grup);

	}
*/
}
