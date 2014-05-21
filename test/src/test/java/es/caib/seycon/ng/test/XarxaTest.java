package es.caib.seycon.ng.test;

import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import es.caib.seycon.ng.comu.Aplicacio;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Identitat;
import es.caib.seycon.ng.comu.Maquina;
import es.caib.seycon.ng.comu.NetworkAuthorization;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.Xarxa;
import es.caib.seycon.ng.servei.UnknowRoleException;
import es.caib.seycon.ng.servei.UnknownGroupException;
import es.caib.seycon.ng.servei.UnknownUserException;
import es.caib.seycon.ng.servei.UnknownXarxaException;
import es.caib.seycon.ng.servei.ejb.AplicacioServiceHome;
import es.caib.seycon.ng.servei.ejb.XarxaServiceHome;
import es.caib.seycon.ng.servei.ejb.UsuariServiceHome;
import es.caib.seycon.ng.servei.ejb.GrupServiceHome;

import junit.framework.TestCase;

public class XarxaTest extends TestCase {

	private static String codiXarxa = "test"; //$NON-NLS-1$
	private static String nomMaquina = "maqTest"; //$NON-NLS-1$

	private es.caib.seycon.ng.servei.ejb.XarxaService service;
	private es.caib.seycon.ng.servei.ejb.AplicacioService aplicacioService;
	private es.caib.seycon.ng.servei.ejb.UsuariService usuariService;
	private es.caib.seycon.ng.servei.ejb.GrupService grupService;

	protected void setUp() throws Exception {
		super.setUp();
		try {
			/*
			 * Properties properties = null; properties = new Properties();
			 * properties.load(new FileInputStream("jndi.properties"));
			 * System.getProperties().put("java.security.auth.login.config",
			 * "security.conf"); Context context = new
			 * InitialContext(properties); ClientLogin login = new
			 * ClientLogin("u89559", "pass"); login.login();
			 */
			Properties properties = null;
			properties = new Properties();
			properties.load(new FileInputStream("jndi.properties")); //$NON-NLS-1$
			System.getProperties().put("java.security.auth.login.config", //$NON-NLS-1$
					"security.conf"); //$NON-NLS-1$
			Context context = new InitialContext(properties);
			ClientLogin login = new ClientLogin("u89559", "papua23"); //$NON-NLS-1$ //$NON-NLS-2$
			login.login();

			Object obj = context.lookup(XarxaServiceHome.JNDI_NAME);
			XarxaServiceHome home = (XarxaServiceHome) PortableRemoteObject
					.narrow(obj, XarxaServiceHome.class);
			service = home.create();

			Object obj2 = context.lookup(AplicacioServiceHome.JNDI_NAME);
			AplicacioServiceHome home2 = (AplicacioServiceHome) PortableRemoteObject
					.narrow(obj2, AplicacioServiceHome.class);
			aplicacioService = home2.create();

			Object obj3 = context.lookup(UsuariServiceHome.JNDI_NAME);
			UsuariServiceHome home3 = (UsuariServiceHome) PortableRemoteObject
					.narrow(obj3, UsuariServiceHome.class);
			usuariService = home3.create();

			Object obj5 = context.lookup(GrupServiceHome.JNDI_NAME);
			GrupServiceHome home5 = (GrupServiceHome) PortableRemoteObject
					.narrow(obj5, GrupServiceHome.class);
			grupService = home5.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFindSessionsByNomMaquina() throws RemoteException {
		Collection sessions = service.findSessionsByNomMaquina("aca"); //$NON-NLS-1$
		System.out.println(Messages.getString("XarxaTest.8") + sessions); //$NON-NLS-1$
	}
/*
	public void testFindUsuariByIdSessio() throws RemoteException {
		System.out.println("service: " + service);
		try {
			Usuari usuari = service.findUsuariByIdSessio(new Long(10));
			if (usuari != null) {
				System.out.println("usuari amb codi: " + usuari.getCodi());
			} else {
				System.out.println("usuari no trobat");
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public void testFindNivellAccesByNomMaquina() throws RemoteException {
		Long nivellAcces = service.findNivellAccesByNomMaquina("aca1");
		System.out.println("nivell acces: " + nivellAcces.longValue());
	}
	*/

	/*
	 * public void testCreateXarxa() throws RemoteException {
	 * 
	 * Xarxa xarxa = new Xarxa(); xarxa.setCodi(codiXarxa);
	 * xarxa.setDescripcio("Xarxa Test AndroMda");
	 * xarxa.setMascara("255.255.255.0"); xarxa.setNormalitzada(new
	 * Boolean(true)); xarxa.setAdreca("192.178.3.5"); xarxa =
	 * service.create(xarxa); assertNotNull(xarxa); assertEquals(codiXarxa,
	 * xarxa.getCodi()); xarxa = service.findXarxaByCodi(codiXarxa);
	 * assertNotNull(xarxa); assertEquals(codiXarxa, xarxa.getCodi());
	 * assertEquals("192.178.3.5", xarxa.getAdreca()); assertEquals("Xarxa Test
	 * AndroMda", xarxa.getDescripcio()); assertEquals("255.255.255.0",
	 * xarxa.getMascara()); assertEquals(new Boolean(true),
	 * xarxa.getNormalitzada()); }
	 * 
	 * public void testCreateMaquina() throws RemoteException { Maquina maquina =
	 * new Maquina(); maquina.setNom(nomMaquina);
	 * maquina.setSistemaOperatiu("LIN"); maquina.setCorreu(new Boolean(false));
	 * maquina.setOfimatica(new Boolean(false));
	 * maquina.setCodiXarxa(codiXarxa); maquina.setAdreca("1.1.1.1");
	 * maquina.setAlias("un alias"); maquina.setDhcp("puede");
	 * maquina.setMac("FF:FF:FF:FF:FF"); maquina.setDescripcio("una
	 * descripcio"); maquina = service.create(maquina); assertNotNull(maquina);
	 * assertEquals(nomMaquina, maquina.getNom()); assertEquals("LIN",
	 * maquina.getSistemaOperatiu()); assertEquals(new Boolean(false),
	 * maquina.getCorreu()); assertEquals(new Boolean(false),
	 * maquina.getOfimatica()); assertEquals(codiXarxa, maquina.getCodiXarxa());
	 * assertEquals("1.1.1.1", maquina.getAdreca()); assertEquals("un alias",
	 * maquina.getAlias()); assertEquals("puede", maquina.getDhcp());
	 * assertEquals("FF:FF:FF:FF:FF", maquina.getMac()); assertEquals("una
	 * descripcio", maquina.getDescripcio()); maquina =
	 * service.findMaquinaByNom(nomMaquina); assertNotNull(maquina);
	 * assertEquals(nomMaquina, maquina.getNom()); assertEquals("LIN",
	 * maquina.getSistemaOperatiu()); assertEquals(new Boolean(false),
	 * maquina.getCorreu()); assertEquals(new Boolean(false),
	 * maquina.getOfimatica()); assertEquals(codiXarxa, maquina.getCodiXarxa());
	 * assertEquals("1.1.1.1", maquina.getAdreca()); assertEquals("un alias",
	 * maquina.getAlias()); assertEquals("puede", maquina.getDhcp());
	 * assertEquals("FF:FF:FF:FF:FF", maquina.getMac()); assertEquals("una
	 * descripcio", maquina.getDescripcio()); }
	 * 
	 * 
	 * 
	 * public void testFindXarxa() throws RemoteException { String codi = "";
	 * String adreca = null; String descripcio = "%"; String mascara = "";
	 * String normalitzada = null; String dhcp = ""; boolean error = true; try{
	 * service.findXarxaByFiltre(codi, adreca, descripcio, mascara,
	 * normalitzada, dhcp); }catch(Exception e){ error = true; }
	 * assertTrue(error); }
	 */
	/*
	 * public void testFindMaquina() throws RemoteException {
	 * 
	 * Maquina maquina = null; Collection maquines = null; Iterator iterator =
	 * null; maquines = service.findMaquinaByFiltre(nomMaquina + "%", null, "",
	 * null, "%", null, "%", null, "%", null); assertTrue(maquines.size() > 0);
	 * iterator = maquines.iterator(); while (iterator.hasNext()) { maquina =
	 * (Maquina) iterator.next(); assertNotNull(maquina); String item =
	 * maquina.getNom(); assertTrue(item.startsWith(nomMaquina)); }
	 * 
	 * 
	 * maquines = service.findMaquinaByFiltre(nomMaquina + "%", "LIN%", null,
	 * "", "%", null, "%", null, null, null); assertNotNull(maquines);
	 * assertTrue(maquines.size() > 0); iterator = maquines.iterator(); while
	 * (iterator.hasNext()) { maquina = (Maquina) iterator.next();
	 * assertNotNull(maquina); String item = maquina.getSistemaOperatiu();
	 * assertTrue(item.startsWith("LIN")); }
	 * 
	 * maquines = service.findMaquinaByFiltre(nomMaquina + "%", "%", "1.1.1%",
	 * "", "%", null, "%", null, null, null); assertNotNull(maquines);
	 * assertTrue(maquines.size() > 0); iterator = maquines.iterator(); while
	 * (iterator.hasNext()) { maquina = (Maquina) iterator.next();
	 * assertNotNull(maquina); String item = maquina.getAdreca();
	 * assertTrue(item.startsWith("1.1.1")); }
	 * 
	 * maquines = service.findMaquinaByFiltre(nomMaquina + "%", "%", "", "pue%",
	 * "%", null, "%", null, null, null); assertNotNull(maquines);
	 * assertTrue(maquines.size() > 0); iterator = maquines.iterator(); while
	 * (iterator.hasNext()) { maquina = (Maquina) iterator.next();
	 * assertNotNull(maquina); String item = maquina.getDhcp();
	 * assertTrue(item.startsWith("pue")); }
	 * 
	 * 
	 * maquines = service.findMaquinaByFiltre(nomMaquina + "%", "%", null, "",
	 * "N", null, "%", null, null, null); assertNotNull(maquines);
	 * assertTrue(maquines.size() > 0); iterator = maquines.iterator(); while
	 * (iterator.hasNext()) { maquina = (Maquina) iterator.next();
	 * assertNotNull(maquina); Boolean item = maquina.getCorreu();
	 * assertFalse(item.booleanValue()); }
	 * 
	 * maquines = service.findMaquinaByFiltre(nomMaquina + "%", "%", "%", "",
	 * "%", "N", "%", null, null, null); assertNotNull(maquines);
	 * assertTrue(maquines.size() > 0); iterator = maquines.iterator(); while
	 * (iterator.hasNext()) { maquina = (Maquina) iterator.next();
	 * assertNotNull(maquina);
	 * assertFalse(maquina.getOfimatica().booleanValue()); }
	 * 
	 * maquines = service.findMaquinaByFiltre(nomMaquina + "%", "%", "%", "",
	 * "%", null, "un%", null, null, null); assertNotNull(maquines);
	 * assertTrue(maquines.size() > 0); iterator = maquines.iterator(); while
	 * (iterator.hasNext()) { maquina = (Maquina) iterator.next();
	 * assertNotNull(maquina); String item = maquina.getAlias();
	 * assertTrue(item.startsWith("un")); }
	 * 
	 * maquines = service.findMaquinaByFiltre(nomMaquina + "%", "%", "%", "",
	 * "%", null, "", "FF%", null, null); assertNotNull(maquines);
	 * assertTrue(maquines.size() > 0); iterator = maquines.iterator(); while
	 * (iterator.hasNext()) { maquina = (Maquina) iterator.next();
	 * assertNotNull(maquina); String item = maquina.getMac();
	 * assertTrue(item.startsWith("FF")); }
	 * 
	 * maquines = service.findMaquinaByFiltre(nomMaquina + "%", "%", "%", "",
	 * "%", null, "%", "", "una de%", null); assertNotNull(maquines);
	 * assertTrue(maquines.size() > 0); iterator = maquines.iterator(); while
	 * (iterator.hasNext()) { maquina = (Maquina) iterator.next();
	 * assertNotNull(maquina); String item = maquina.getDescripcio();
	 * assertTrue(item.startsWith("una de")); }
	 * 
	 * maquines = service.findMaquinaByFiltre(nomMaquina + "%", "%", "%", "",
	 * "%", null, "", null, null, codiXarxa + "%"); assertNotNull(maquines);
	 * assertTrue(maquines.size() > 0); iterator = maquines.iterator(); while
	 * (iterator.hasNext()) { maquina = (Maquina) iterator.next();
	 * assertNotNull(maquina); String item = maquina.getCodiXarxa();
	 * assertTrue(item.startsWith(codiXarxa)); }
	 * 
	 * boolean error = false; try{ maquines = service.findMaquinaByFiltre("%",
	 * "%", "%", "", "%", null, "", null, null, "%"); }catch(Exception e){ error =
	 * true; } assertTrue(error); }
	 */
	/*
	 * public void testHandleGetXarxes() throws RemoteException { Collection col =
	 * service.getXarxes(); }
	 */

	/*
	 * public void testDobleCreateXarxa() throws RemoteException { try { Xarxa
	 * xarxa = new Xarxa(); xarxa.setCodi(codiXarxa); xarxa.setDescripcio("Xarxa
	 * Test AndroMda"); xarxa.setMascara("255.255.255.0");
	 * xarxa.setNormalitzada(new Boolean(true)); xarxa.setAdreca("192.178.3.5");
	 * xarxa = service.create(xarxa); } catch (Exception e) { return; } fail(); }
	 * 
	 * public void testUpdateXarxa() throws RemoteException { Xarxa xarxa =
	 * service.findXarxaByCodi(codiXarxa); xarxa.setDescripcio("Nova
	 * descripcio."); service.update(xarxa); xarxa =
	 * service.findXarxaByCodi(codiXarxa); assertNotNull(xarxa);
	 * assertEquals(codiXarxa, xarxa.getCodi()); assertEquals("192.178.3.5",
	 * xarxa.getAdreca()); assertEquals("Nova descripcio.",
	 * xarxa.getDescripcio()); assertEquals("255.255.255.0",
	 * xarxa.getMascara()); assertEquals(new Boolean(true),
	 * xarxa.getNormalitzada()); }
	 * 
	 * 
	 * 
	 * public void testCreateNetworkAuthorization() throws RemoteException,
	 * UnknowRoleException, UnknownGroupException, UnknownXarxaException,
	 * UnknownUserException { String codiIdentitat = "u86500"; Identitat
	 * identitat = service.findIdentitatByCodi(codiIdentitat);
	 * NetworkAuthorization networkAuthorization = new NetworkAuthorization();
	 * networkAuthorization.setIdentitat(identitat);
	 * networkAuthorization.setNivell(2);
	 * networkAuthorization.setMascara("epreinf149");
	 * networkAuthorization.setCodiXarxa(codiXarxa);
	 * service.create(networkAuthorization); networkAuthorization = service
	 * .findNetworkAuthorizationByCodiXarxaAndCodiIdentitat(codiXarxa,
	 * codiIdentitat); assertNotNull(networkAuthorization);
	 * assertEquals(codiXarxa, networkAuthorization.getCodiXarxa());
	 * assertEquals(codiIdentitat, networkAuthorization.getIdentitat()
	 * .getCodiUsuari());
	 * 
	 * Collection col = service.findIdentitatsByCodi("SC_%"); Iterator i =
	 * col.iterator(); while (i.hasNext()) { Identitat identitatRol =
	 * (Identitat) i.next(); codiIdentitat = identitatRol.getNomRol();
	 * networkAuthorization = new NetworkAuthorization();
	 * networkAuthorization.setIdentitat(identitatRol);
	 * networkAuthorization.setNivell(1);
	 * networkAuthorization.setMascara("epreinf149");
	 * networkAuthorization.setCodiXarxa(codiXarxa);
	 * service.create(networkAuthorization); networkAuthorization = service
	 * .findNetworkAuthorizationByCodiXarxaAndCodiIdentitat( codiXarxa,
	 * codiIdentitat); assertNotNull(networkAuthorization);
	 * assertEquals(codiXarxa, networkAuthorization.getCodiXarxa());
	 * assertEquals(codiIdentitat, networkAuthorization.getIdentitat()
	 * .getNomRol()); }
	 * 
	 * codiIdentitat = "DGTIC"; Identitat grupIdentitat =
	 * service.findIdentitatByCodi(codiIdentitat); networkAuthorization = new
	 * NetworkAuthorization(); networkAuthorization.setIdentitat(grupIdentitat);
	 * networkAuthorization.setNivell(2);
	 * networkAuthorization.setMascara("epreinf149");
	 * networkAuthorization.setCodiXarxa(codiXarxa);
	 * service.create(networkAuthorization); networkAuthorization = service
	 * .findNetworkAuthorizationByCodiXarxaAndCodiIdentitat(codiXarxa,
	 * codiIdentitat); assertNotNull(networkAuthorization);
	 * assertEquals(codiXarxa, networkAuthorization.getCodiXarxa());
	 * assertEquals(codiIdentitat, networkAuthorization.getIdentitat()
	 * .getCodiGrup()); }
	 * 
	 * public void testTeAccesAXarxa() throws RemoteException { String
	 * codiUsuari = "u86500"; Boolean teAcces =
	 * service.teAccesAXarxa(codiUsuari, codiXarxa); if(teAcces.booleanValue()){
	 * System.out.println("L'usuari '"+codiUsuari +"' t� acc�s a la xarxa amb
	 * codi '" +codiXarxa+"'."); } }
	 * 
	 * public void testDobleCreateNetworkAuthorization() throws RemoteException,
	 * UnknowRoleException, UnknownGroupException, UnknownXarxaException,
	 * UnknownUserException { try { String codiIdentitat = "u86500"; Identitat
	 * identitat = service.findIdentitatByCodi(codiIdentitat);
	 * NetworkAuthorization networkAuthorization = new NetworkAuthorization();
	 * networkAuthorization.setIdentitat(identitat);
	 * networkAuthorization.setNivell(2);
	 * networkAuthorization.setMascara("epreinf149");
	 * networkAuthorization.setCodiXarxa(codiXarxa);
	 * service.create(networkAuthorization); } catch (Exception e) { return; }
	 * fail(); }
	 * 
	 * public void testDeleteNetworkAuthorization() throws RemoteException,
	 * UnknowRoleException, UnknownGroupException, UnknownXarxaException,
	 * UnknownUserException { String codiIdentitat = "u86500"; Identitat
	 * identitat = service.findIdentitatByCodi(codiIdentitat);
	 * NetworkAuthorization networkAuthorization = service
	 * .findNetworkAuthorizationByCodiXarxaAndCodiIdentitat(codiXarxa,
	 * codiIdentitat); assertNotNull(networkAuthorization);
	 * assertEquals(codiXarxa, networkAuthorization.getCodiXarxa());
	 * assertEquals(codiIdentitat, networkAuthorization.getIdentitat()
	 * .getCodiUsuari()); service.delete(networkAuthorization);
	 * networkAuthorization = service
	 * .findNetworkAuthorizationByCodiXarxaAndCodiIdentitat(codiXarxa,
	 * codiIdentitat); assertNull(networkAuthorization);
	 * 
	 * Collection col = service.findIdentitatsByCodi("SC_%"); Iterator i =
	 * col.iterator(); while (i.hasNext()) { Identitat identitatRol =
	 * (Identitat) i.next(); codiIdentitat = identitatRol.getNomRol();
	 * networkAuthorization = service
	 * .findNetworkAuthorizationByCodiXarxaAndCodiIdentitat( codiXarxa,
	 * codiIdentitat); assertNotNull(networkAuthorization);
	 * assertEquals(codiXarxa, networkAuthorization.getCodiXarxa());
	 * assertEquals(codiIdentitat, networkAuthorization.getIdentitat()
	 * .getNomRol()); service.delete(networkAuthorization); networkAuthorization =
	 * service .findNetworkAuthorizationByCodiXarxaAndCodiIdentitat( codiXarxa,
	 * codiIdentitat); assertNull(networkAuthorization); }
	 * 
	 * codiIdentitat = "DGTIC"; Identitat grupIdentitat =
	 * service.findIdentitatByCodi(codiIdentitat); networkAuthorization =
	 * service .findNetworkAuthorizationByCodiXarxaAndCodiIdentitat(codiXarxa,
	 * codiIdentitat); assertNotNull(networkAuthorization);
	 * assertEquals(codiXarxa, networkAuthorization.getCodiXarxa());
	 * assertEquals(codiIdentitat, networkAuthorization.getIdentitat()
	 * .getCodiGrup()); service.delete(networkAuthorization);
	 * networkAuthorization = service
	 * .findNetworkAuthorizationByCodiXarxaAndCodiIdentitat(codiXarxa,
	 * codiIdentitat); assertNull(networkAuthorization); }
	 * 
	 * public void testHandleGetACL() throws RemoteException { Xarxa xarxa =
	 * service.findXarxaByCodi(codiXarxa); Collection col =
	 * service.getACL(xarxa); }
	 * 
	 * public void testGetMaquines() throws RemoteException { Collection
	 * maquines = service.getMaquines(); }
	 * 
	 * public void testDobleCreateMaquina() throws RemoteException { try {
	 * Maquina maquina = new Maquina(); maquina.setNom(nomMaquina);
	 * maquina.setSistemaOperatiu("LIN"); maquina.setCorreu(new Boolean(false));
	 * maquina.setOfimatica(new Boolean(false));
	 * maquina.setCodiXarxa(codiXarxa); maquina.setAdreca("1.1.1.1");
	 * maquina.setAlias("un alias"); maquina.setDhcp("puede");
	 * maquina.setMac("FF:FF:FF:FF:FF"); maquina.setDescripcio("una
	 * descripcio"); maquina = service.create(maquina);
	 * System.out.println(maquina); } catch (Exception e) { return; } fail(); }
	 * 
	 * public void testUpdateMaquina() throws RemoteException { Maquina maquina =
	 * service.findMaquinaByNom(nomMaquina); maquina.setSistemaOperatiu("W95");
	 * maquina.setCorreu(new Boolean(true)); maquina.setOfimatica(new
	 * Boolean(true)); maquina.setAdreca("1.1.1.2"); maquina.setAlias("un
	 * alias2"); maquina.setDhcp("puede2"); maquina.setMac("FF:FF:FF:FF:22");
	 * maquina.setDescripcio("una descripcio2"); maquina =
	 * service.create(maquina); assertNotNull(maquina); assertEquals(nomMaquina,
	 * maquina.getNom()); assertEquals("W95", maquina.getSistemaOperatiu());
	 * assertEquals(new Boolean(true), maquina.getCorreu()); assertEquals(new
	 * Boolean(true), maquina.getOfimatica()); assertEquals(codiXarxa,
	 * maquina.getCodiXarxa()); assertEquals("1.1.1.2", maquina.getAdreca());
	 * assertEquals("un alias2", maquina.getAlias()); assertEquals("puede2",
	 * maquina.getDhcp()); assertEquals("FF:FF:FF:FF:22", maquina.getMac());
	 * assertEquals("una descripcio2", maquina.getDescripcio()); maquina =
	 * service.findMaquinaByNom(nomMaquina); assertNotNull(maquina);
	 * assertEquals(nomMaquina, maquina.getNom()); assertEquals("W95",
	 * maquina.getSistemaOperatiu()); assertEquals(new Boolean(true),
	 * maquina.getCorreu()); assertEquals(new Boolean(true),
	 * maquina.getOfimatica()); assertEquals(codiXarxa, maquina.getCodiXarxa());
	 * assertEquals("1.1.1.2", maquina.getAdreca()); assertEquals("un alias2",
	 * maquina.getAlias()); assertEquals("puede2", maquina.getDhcp());
	 * assertEquals("FF:FF:FF:FF:22", maquina.getMac()); assertEquals("una
	 * descripcio2", maquina.getDescripcio()); }
	 * 
	 * public void testFindMaquinesByXarxa() throws RemoteException { Xarxa
	 * xarxa = service.findXarxaByCodi(codiXarxa); Collection maquines =
	 * service.findMaquinesByXarxa(xarxa); boolean trobat = false; Iterator
	 * iterator = maquines.iterator(); while (iterator.hasNext() && !trobat) {
	 * Maquina maquina = (Maquina) iterator.next(); trobat =
	 * maquina.getNom().compareTo(nomMaquina) == 0; } assertEquals(true,
	 * trobat); }
	 * 
	 * public void testDeleteMaquina() throws RemoteException { Maquina maquina =
	 * service.findMaquinaByNom(nomMaquina); service.delete(maquina); maquina =
	 * service.findMaquinaByNom(nomMaquina); assertNull(maquina); }
	 * 
	 * public void testFindIdentitatsByCodi() throws RemoteException { String
	 * codiIdentitats = "a%"; Collection identitats =
	 * service.findIdentitatsByCodi(codiIdentitats);
	 * assertTrue(identitats.size() > 0); Iterator iterator =
	 * identitats.iterator(); while (iterator.hasNext()) { Identitat identitat =
	 * (Identitat) iterator.next(); String codiIdentitat =
	 * (identitat.getCodiGrup() == null ? "" : identitat.getCodiGrup()) +
	 * (identitat.getCodiUsuari() == null ? "" : identitat .getCodiUsuari()) +
	 * (identitat.getNomRol() == null ? "" : identitat .getNomRol());
	 * assertTrue(codiIdentitat.startsWith("a")); } }
	 * 
	 * public void testHandleDeleteXarxa() throws RemoteException { Xarxa xarxa =
	 * service.findXarxaByCodi(codiXarxa); assertNotNull(xarxa);
	 * service.delete(xarxa); xarxa = service.findXarxaByCodi(codiXarxa);
	 * assertNull(xarxa); }
	 */
}
