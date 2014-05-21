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
import es.caib.seycon.ng.comu.GrupImpressora;
import es.caib.seycon.ng.comu.Impressora;
import es.caib.seycon.ng.comu.UsuariImpressora;
import es.caib.seycon.ng.servei.ejb.ImpressoraService;
import es.caib.seycon.ng.servei.ejb.ImpressoraServiceHome;

public class ImpressoraTest extends TestCase {

	private ImpressoraService service;
	private String codiImpressora = "prueba"; //$NON-NLS-1$

	protected void setUp() throws Exception {
		try {

			super.setUp();
			Properties properties = null;
			properties = new Properties();
			properties.load(new FileInputStream("jndi.properties")); //$NON-NLS-1$
			System.getProperties().put("java.security.auth.login.config", //$NON-NLS-1$
					"security.conf"); //$NON-NLS-1$
			Context context = new InitialContext(properties);
			ClientLogin login = new ClientLogin("u89559", "pass"); //$NON-NLS-1$ //$NON-NLS-2$
			login.login();

			Object obj = context
					.lookup("seycon-3.0-SNAPSHOT/ejb/es.caib.seycon.ng.servei.ImpressoraService"); //$NON-NLS-1$
			ImpressoraServiceHome home = (ImpressoraServiceHome) PortableRemoteObject
					.narrow(obj, ImpressoraServiceHome.class);
			service = home.create();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testCreate() throws RemoteException {
		Impressora impressora = new Impressora();
		impressora.setCodi(codiImpressora);
		impressora.setModel("Pruebas"); //$NON-NLS-1$
		impressora.setNomMaquina("lofiprn1"); //$NON-NLS-1$
		impressora.setLocal(new Boolean(false));
		impressora = service.create(impressora);
		Assert.assertNotNull(impressora);
		Assert.assertEquals(codiImpressora, impressora.getCodi());
		impressora = service.findImpressoraByCodiImpressora(codiImpressora);
		Assert.assertNotNull(impressora);
		Assert.assertEquals(codiImpressora, impressora.getCodi());
	}

	public void testDobleCreate() throws RemoteException {
		try {
			Impressora impressora = new Impressora();
			impressora.setCodi(codiImpressora);
			impressora.setModel("Pruebas"); //$NON-NLS-1$
			impressora.setNomMaquina("lofiprn1"); //$NON-NLS-1$
			impressora = service.create(impressora);
			System.out.println(impressora);
		} catch (Exception e) {
			return;
		}
		fail();
	}

	public void testFindByCriteri() throws RemoteException {

		Collection impressores = service.findImpressoresByCriteri(
				codiImpressora, null, "", "%"); //$NON-NLS-1$ //$NON-NLS-2$
		Iterator iterator = impressores.iterator();
		Assert.assertTrue(impressores.size() > 0);
		while (iterator.hasNext()) {
			Impressora impressora = (Impressora) iterator.next();
			Assert.assertEquals(codiImpressora, impressora.getCodi());
		}
		impressores = service.findImpressoresByCriteri("", "Prueb%", null, ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		Assert.assertTrue(impressores.size() > 0);
		iterator = impressores.iterator();
		while (iterator.hasNext()) {
			Impressora impressora = (Impressora) iterator.next();
			assertTrue(impressora.getModel().startsWith("Prueb")); //$NON-NLS-1$
		}
		try {
			impressores = service.findImpressoresByCriteri("%", "Prueb%", "N", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					null);
			Assert.assertTrue(impressores.size() > 0);
			iterator = impressores.iterator();
			while (iterator.hasNext()) {
				Impressora impressora = (Impressora) iterator.next();
				assertTrue(!impressora.getLocal().booleanValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean error = false;
		try {
			impressores = service.findImpressoresByCriteri("%", "", "%", null); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			Assert.assertTrue(impressores.size() > 0);
		} catch (Exception e) {
			error = true;
		}
		assertTrue(error);

		impressores = service.findImpressoresByCriteri(null, "Prueb%", "%", //$NON-NLS-1$ //$NON-NLS-2$
				"lofi%"); //$NON-NLS-1$
		Assert.assertTrue(impressores.size() > 0);
		iterator = impressores.iterator();
		while (iterator.hasNext()) {
			Impressora impressora = (Impressora) iterator.next();
			assertTrue(impressora.getNomMaquina().startsWith("lofi")); //$NON-NLS-1$
		}
	}

	public void testUpdate() throws RemoteException {
		Impressora impressora = service
				.findImpressoraByCodiImpressora(codiImpressora);
		Assert.assertNotNull(impressora);
		Assert.assertEquals(codiImpressora, impressora.getCodi());
		impressora.setModel("Pruebas2"); //$NON-NLS-1$
		impressora.setNomMaquina("ebendon10"); //$NON-NLS-1$
		service.update(impressora);
		impressora = service.findImpressoraByCodiImpressora(codiImpressora);
		Assert.assertNotNull(impressora);
		Assert.assertEquals(codiImpressora, impressora.getCodi());
		Assert.assertEquals("Pruebas2", impressora.getModel()); //$NON-NLS-1$
		Assert.assertEquals("ebendon10", impressora.getNomMaquina()); //$NON-NLS-1$
	}

	/*
	 * public void testGetImpressores() throws RemoteException { Collection
	 * impressoras = service.getImpressores(); }
	 */

	public void testUsuariImpressoraCreate() throws RemoteException {
			String codiUsuari = "u89559"; //$NON-NLS-1$
			UsuariImpressora usuariImpressora = new UsuariImpressora();
			usuariImpressora.setCodiImpressora(codiImpressora);
			usuariImpressora.setCodiUsuari(codiUsuari);
			usuariImpressora.setPerDefecte(new Boolean(true));
			usuariImpressora = service.create(usuariImpressora);
			Assert.assertNotNull(usuariImpressora);
			Assert.assertEquals(codiUsuari, usuariImpressora.getCodiUsuari());
			Assert.assertEquals(codiImpressora, usuariImpressora
					.getCodiImpressora());
			usuariImpressora = service
					.findUsuariImpressoraByCodiUsuariAndCodiImpressora(
							codiUsuari, codiImpressora);
			Assert.assertNotNull(usuariImpressora);
			Assert.assertEquals(codiUsuari, usuariImpressora.getCodiUsuari());
			Assert.assertEquals(codiImpressora, usuariImpressora
					.getCodiImpressora());
	}

	public void testUsuariImpressoraGet() throws RemoteException {
		String codiUsuari = "u89559"; //$NON-NLS-1$
		Collection usuariImpressores = service
				.getUsuariImpressoresByCodiImpressora(codiImpressora);
		Assert.assertEquals(1, usuariImpressores.size());
		UsuariImpressora usuariImpressora = (UsuariImpressora) usuariImpressores
				.iterator().next();
		Assert.assertNotNull(usuariImpressora);
		Assert.assertEquals(codiImpressora, usuariImpressora
				.getCodiImpressora());
		Assert.assertEquals(codiUsuari, usuariImpressora.getCodiUsuari());
	}

	public void testUsuariImpressoraDelete() throws RemoteException {
		String codiUsuari = "u89559"; //$NON-NLS-1$
		UsuariImpressora usuariImpressora = service
				.findUsuariImpressoraByCodiUsuariAndCodiImpressora(codiUsuari,
						codiImpressora);
		Assert.assertNotNull(usuariImpressora);
		Assert.assertEquals(codiImpressora, usuariImpressora
				.getCodiImpressora());
		Assert.assertEquals(codiUsuari, usuariImpressora.getCodiUsuari());
		service.delete(usuariImpressora);
		usuariImpressora = service
				.findUsuariImpressoraByCodiUsuariAndCodiImpressora(codiUsuari,
						codiImpressora);
		Assert.assertNull(usuariImpressora);
	}

	public void testGrupImpressoraCreate() throws RemoteException {
		String codiGrup = "DGECO"; //$NON-NLS-1$
		GrupImpressora grupImpressora = new GrupImpressora();
		grupImpressora.setCodiImpressora(codiImpressora);
		grupImpressora.setCodiGrup(codiGrup);
		grupImpressora.setPerDefecte(new Boolean(true));
		grupImpressora = service.create(grupImpressora);
		Assert.assertNotNull(grupImpressora);
		Assert.assertEquals(codiGrup, grupImpressora.getCodiGrup());
		Assert.assertEquals(codiImpressora, grupImpressora.getCodiImpressora());
		grupImpressora = service.findGrupImpressoraByCodiGrupAndCodiImpressora(
				codiGrup, codiImpressora);
		Assert.assertNotNull(grupImpressora);
		Assert.assertEquals(codiGrup, grupImpressora.getCodiGrup());
		Assert.assertEquals(codiImpressora, grupImpressora.getCodiImpressora());
	}

	public void testGrupImpressoraGet() throws RemoteException {
		String codiGrup = "DGECO"; //$NON-NLS-1$
		Collection grupImpressores = service
				.getGrupImpressoresByCodiImpressora(codiImpressora);
		Assert.assertEquals(1, grupImpressores.size());
		GrupImpressora grupImpressora = (GrupImpressora) grupImpressores
				.iterator().next();
		Assert.assertNotNull(grupImpressora);
		Assert.assertEquals(codiImpressora, grupImpressora.getCodiImpressora());
		Assert.assertEquals(codiGrup, grupImpressora.getCodiGrup());
	}
	public void testFindGrupImpressoresByCodiGrup() throws RemoteException{
		String codiGrup = "DGECO"; //$NON-NLS-1$
		Collection grupImpressores = service.findGrupImpressoresByCodiGrup(codiGrup);
		Assert.assertEquals(1, grupImpressores.size());
		GrupImpressora grupImpressora = (GrupImpressora) grupImpressores
				.iterator().next();
		Assert.assertNotNull(grupImpressora);
		Assert.assertEquals(codiImpressora, grupImpressora.getCodiImpressora());
		Assert.assertEquals(codiGrup, grupImpressora.getCodiGrup());
	}
	
	public void testUpdateGrupImpressora() throws RemoteException {
		String codiGrup = "DGECO"; //$NON-NLS-1$
		GrupImpressora grupImpressora = 
			service.findGrupImpressoraByCodiGrupAndCodiImpressora(codiGrup, codiImpressora);
		grupImpressora.setPerDefecte(new Boolean(false));
		service.update(grupImpressora);
		Assert.assertNotNull(grupImpressora);
		Assert.assertFalse(grupImpressora.getPerDefecte().booleanValue());
		Assert.assertEquals(codiImpressora, grupImpressora.getCodiImpressora());
		Assert.assertEquals(codiGrup, grupImpressora.getCodiGrup());		
	}
	public void testGrupImpressoraDelete() throws RemoteException {
		String codiGrup = "DGECO"; //$NON-NLS-1$
		GrupImpressora grupImpressora = service
				.findGrupImpressoraByCodiGrupAndCodiImpressora(codiGrup,
						codiImpressora);
		Assert.assertNotNull(grupImpressora);
		Assert.assertEquals(codiImpressora, grupImpressora.getCodiImpressora());
		Assert.assertEquals(codiGrup, grupImpressora.getCodiGrup());
		service.delete(grupImpressora);
		grupImpressora = service.findGrupImpressoraByCodiGrupAndCodiImpressora(
				codiGrup, codiImpressora);
		Assert.assertNull(grupImpressora);
	}

	public void testDelete() throws RemoteException {
		Impressora impressora = service
				.findImpressoraByCodiImpressora(codiImpressora);
		Assert.assertNotNull(impressora);
		Assert.assertEquals(codiImpressora, impressora.getCodi());
		impressora.setModel("Pruebas2"); //$NON-NLS-1$
		service.delete(impressora);
		impressora = service.findImpressoraByCodiImpressora(codiImpressora);
		Assert.assertNull(impressora);
	}

}
