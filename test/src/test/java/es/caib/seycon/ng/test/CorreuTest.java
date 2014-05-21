package es.caib.seycon.ng.test;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.naming.Context;
import javax.rmi.PortableRemoteObject;

import es.caib.seycon.ng.comu.CorreuExtern;
import es.caib.seycon.ng.comu.DominiCorreu;
import es.caib.seycon.ng.comu.LlistaCorreu;
import es.caib.seycon.ng.comu.LlistaCorreuUsuari;
import es.caib.seycon.ng.comu.RelacioLlistaCorreu;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.servei.ejb.AplicacioServiceHome;
import es.caib.seycon.ng.servei.ejb.LlistesDeCorreuService;
import es.caib.seycon.ng.servei.ejb.LlistesDeCorreuServiceHome;
import es.caib.seycon.ng.servei.ejb.LopdService;
import es.caib.seycon.ng.servei.ejb.LopdServiceHome;
import junit.framework.TestCase;

public class CorreuTest extends TestCase {
	private LlistesDeCorreuService service;
	private static String nomLlistaCorreu = "llisProva"; //$NON-NLS-1$
	private static String codiDominiCorreu = "dom"; //$NON-NLS-1$
	private static String codiUsuari = "u89559"; //$NON-NLS-1$
	private static String adreca = "'prova@prova.pr'"; //$NON-NLS-1$

	protected void setUp() throws Exception {
		try {
			super.setUp();
			Context ctx = InitialContextFactory.getInitialContext();
			Object obj = ctx.lookup(LlistesDeCorreuServiceHome.JNDI_NAME);
			LlistesDeCorreuServiceHome home = (LlistesDeCorreuServiceHome) PortableRemoteObject
					.narrow(obj, LlistesDeCorreuServiceHome.class);
			service = home.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 
	 * public void testGetLlistesDeCorreu() throws RemoteException { Collection
	 * col = service.getLlistesDeCorreu(); }
	 * 
	 * public void testHandleFindByNom() throws RemoteException { Collection col =
	 * service.findLlistesCorreuByNom("cati"); }
	 */
/*
	public void testCreateDominiCorreu() throws Exception {
		String descripcio = "proves";
		DominiCorreu dominiCorreu = new DominiCorreu();
		dominiCorreu.setCodi(codiDominiCorreu);
		dominiCorreu.setDescripcio(descripcio);
		dominiCorreu = service.create(dominiCorreu);
		assertNotNull(dominiCorreu);
		assertEquals(codiDominiCorreu, dominiCorreu.getCodi());
		assertEquals(descripcio, dominiCorreu.getDescripcio());
		dominiCorreu = service.findDominiCorreuByCodi(codiDominiCorreu);
		assertNotNull(dominiCorreu);
		assertEquals(codiDominiCorreu, dominiCorreu.getCodi());
		assertEquals(descripcio, dominiCorreu.getDescripcio());
	}

	public void testCreateLlistaCorreu() throws Exception {
		String descripcio = "prova";
		LlistaCorreu llistaCorreu = new LlistaCorreu();
		llistaCorreu.setCodiDomini(codiDominiCorreu);
		llistaCorreu.setDescripcio(descripcio);
		llistaCorreu.setNom(nomLlistaCorreu);
		llistaCorreu = service.create(llistaCorreu);
		assertNotNull(llistaCorreu);
		assertEquals(nomLlistaCorreu, llistaCorreu.getNom());
		assertEquals(codiDominiCorreu, llistaCorreu.getCodiDomini());
		assertEquals(descripcio, llistaCorreu.getDescripcio());
		llistaCorreu = service.findLlistaCorreuByNomAndCodiDomini(
				nomLlistaCorreu, codiDominiCorreu);
		assertNotNull(llistaCorreu);
		assertEquals(nomLlistaCorreu, llistaCorreu.getNom());
		assertEquals(codiDominiCorreu, llistaCorreu.getCodiDomini());
		assertEquals(descripcio, llistaCorreu.getDescripcio());
	}

	public void testCreateCorreuExtern() throws Exception {
		try {
			CorreuExtern correuExtern = new CorreuExtern();
			correuExtern.setAdreca(adreca);
			correuExtern.setLlistaCorreuNom(nomLlistaCorreu);
			correuExtern.setCodiDomini(codiDominiCorreu);
			correuExtern = service.create(correuExtern);
			assertNotNull(correuExtern);
			assertEquals(adreca, correuExtern.getAdreca());
			assertEquals(nomLlistaCorreu, correuExtern.getLlistaCorreuNom());
			correuExtern = service.findCorreuExternByAdreca(adreca);
			assertEquals(adreca, correuExtern.getAdreca());
			assertEquals(nomLlistaCorreu, correuExtern.getLlistaCorreuNom());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFindDominiCorreuByFiltre() throws Exception {
		Collection dominisDeCorreu = service.findDominisCorreuByFiltre("dom%",
				"pro%");
		assertTrue(dominisDeCorreu.size() > 0);
		Iterator iterator = dominisDeCorreu.iterator();
		while (iterator.hasNext()) {
			DominiCorreu dominiCorreu = (DominiCorreu) iterator.next();
			assertNotNull(dominiCorreu);
			assertTrue(dominiCorreu.getCodi().startsWith("dom"));
			assertTrue(dominiCorreu.getDescripcio().startsWith("pro"));
		}
		dominisDeCorreu = service.findDominisCorreuByFiltre("%", "pro%");
		assertTrue(dominisDeCorreu.size() > 0);
		iterator = dominisDeCorreu.iterator();
		while (iterator.hasNext()) {
			DominiCorreu dominiCorreu = (DominiCorreu) iterator.next();
			assertNotNull(dominiCorreu);
			assertTrue(dominiCorreu.getDescripcio().startsWith("pro"));
		}
		dominisDeCorreu = service.findDominisCorreuByFiltre("dom%", "%");
		assertTrue(dominisDeCorreu.size() > 0);
		iterator = dominisDeCorreu.iterator();
		while (iterator.hasNext()) {
			DominiCorreu dominiCorreu = (DominiCorreu) iterator.next();
			assertNotNull(dominiCorreu);
			assertTrue(dominiCorreu.getCodi().startsWith("dom"));
		}
		dominisDeCorreu = service.findDominisCorreuByFiltre("", "pro%");
		assertTrue(dominisDeCorreu.size() > 0);
		iterator = dominisDeCorreu.iterator();
		while (iterator.hasNext()) {
			DominiCorreu dominiCorreu = (DominiCorreu) iterator.next();
			assertNotNull(dominiCorreu);
			assertTrue(dominiCorreu.getDescripcio().startsWith("pro"));
		}
		dominisDeCorreu = service.findDominisCorreuByFiltre("dom%", "");
		assertTrue(dominisDeCorreu.size() > 0);
		iterator = dominisDeCorreu.iterator();
		while (iterator.hasNext()) {
			DominiCorreu dominiCorreu = (DominiCorreu) iterator.next();
			assertNotNull(dominiCorreu);
			assertTrue(dominiCorreu.getCodi().startsWith("dom"));
		}
		dominisDeCorreu = service.findDominisCorreuByFiltre(null, "pro%");
		assertTrue(dominisDeCorreu.size() > 0);
		iterator = dominisDeCorreu.iterator();
		while (iterator.hasNext()) {
			DominiCorreu dominiCorreu = (DominiCorreu) iterator.next();
			assertNotNull(dominiCorreu);
			assertTrue(dominiCorreu.getDescripcio().startsWith("pro"));
		}
		dominisDeCorreu = service.findDominisCorreuByFiltre("dom%", null);
		assertTrue(dominisDeCorreu.size() > 0);
		iterator = dominisDeCorreu.iterator();
		while (iterator.hasNext()) {
			DominiCorreu dominiCorreu = (DominiCorreu) iterator.next();
			assertNotNull(dominiCorreu);
			assertTrue(dominiCorreu.getCodi().startsWith("dom"));
		}

		boolean error = false;
		try {
			dominisDeCorreu = service.findDominisCorreuByFiltre("%", null);
		} catch (Exception e) {
			error = true;
		}
		assertTrue(error);
	}

	public void testCreateLlistaCorreuUsuari() throws Exception {
		LlistaCorreuUsuari llistaCorreuUsuari = new LlistaCorreuUsuari();
		llistaCorreuUsuari.setCodiUsuari(codiUsuari);
		llistaCorreuUsuari.setNomLlistaCorreu(nomLlistaCorreu);
		llistaCorreuUsuari.setCodiDomini(codiDominiCorreu);
		llistaCorreuUsuari = service.create(llistaCorreuUsuari);
		assertNotNull(llistaCorreuUsuari);
		assertEquals(codiUsuari, llistaCorreuUsuari.getCodiUsuari());
		assertEquals(nomLlistaCorreu, llistaCorreuUsuari.getNomLlistaCorreu());
		llistaCorreuUsuari = service
				.findLlistaCorreuUsuariByNomLlistaCorreuAndCodiDominiAndCodiUsuari(
						nomLlistaCorreu, codiDominiCorreu, codiUsuari);
		assertNotNull(llistaCorreuUsuari);
		assertEquals(codiUsuari, llistaCorreuUsuari.getCodiUsuari());
		assertEquals(nomLlistaCorreu, llistaCorreuUsuari.getNomLlistaCorreu());
	}

	public void testFindCorreusExternsByNomLlistaCorreu() throws Exception {
		Collection correusExterns = service
				.findCorreusExternsByNomLlistaCorreuAndCodiDomini(
						nomLlistaCorreu, codiDominiCorreu);
		assertNotNull(correusExterns);
		boolean trobat = false;
		Iterator iterator = correusExterns.iterator();
		while (iterator.hasNext() && !trobat) {
			CorreuExtern correuExtern = (CorreuExtern) iterator.next();
			trobat = correuExtern.getAdreca().compareTo(adreca) == 0;
		}
		assertTrue(trobat);
	}

	private static boolean cercaLlistesDeCorreu(Collection llistesCorreu) {
		boolean trobat = false;
		Iterator iterator = llistesCorreu.iterator();
		while (iterator.hasNext() && !trobat) {
			LlistaCorreu llistaCorreu = (LlistaCorreu) iterator.next();
			trobat = llistaCorreu.getNom().compareTo(nomLlistaCorreu) == 0;
		}
		return trobat;
	}

	public void testFindLlistesDeCorreuByDades() throws Exception {
		boolean error = false;
		try{
			Collection llistesDeCorreu = service.findLlistesDeCorreuByDades(null,
				null, null, null);
		}catch(Exception e){
			error = true;
		}
		assertTrue(error);
	}

	public void testFindLlistaCorreuUsuariByCodiUsuari() throws Exception {
		Collection llistesDeCorreu = service
				.findLlistaCorreuUsuariByCodiUsuari(codiUsuari);
		assertNotNull(llistesDeCorreu);
		assertFalse(llistesDeCorreu.size() == 0);
		Iterator iterator = llistesDeCorreu.iterator();
		while (iterator.hasNext()) {
			LlistaCorreuUsuari llistaCorreuUsuari = (LlistaCorreuUsuari) iterator
					.next();
			assertNotNull(llistaCorreuUsuari);
			assertEquals(codiUsuari, llistaCorreuUsuari.getCodiUsuari());
		}
	}

	public void testFindUsuarisByNomLlistaCorreu() throws Exception {
		Collection usuaris = service.findUsuarisByNomLlistaCorreuAndCodiDomini(
				nomLlistaCorreu, codiDominiCorreu);
		assertNotNull(usuaris);
		assertEquals(1, usuaris.size());
		Iterator iterator = usuaris.iterator();
		while (iterator.hasNext()) {
			Usuari usuari = (Usuari) iterator.next();
			assertNotNull(usuari);
			assertEquals(codiUsuari, usuari.getCodi());
		}
	}

	public void testUpdateLlistaCorreu() throws RemoteException {
		String novaDescripcio = "nova descripci�";

		LlistaCorreu llistaCorreu = service.findLlistaCorreuByNomAndCodiDomini(
				nomLlistaCorreu, codiDominiCorreu);
		llistaCorreu.setDescripcio(novaDescripcio);
		llistaCorreu = service.update(llistaCorreu);
		assertNotNull(llistaCorreu);
		assertEquals(nomLlistaCorreu, llistaCorreu.getNom());
		assertEquals(novaDescripcio, llistaCorreu.getDescripcio());
		llistaCorreu = service.findLlistaCorreuByNomAndCodiDomini(
				nomLlistaCorreu, codiDominiCorreu);
		assertNotNull(llistaCorreu);
		assertEquals(nomLlistaCorreu, llistaCorreu.getNom());
		assertEquals(novaDescripcio, llistaCorreu.getDescripcio());
	}

	public void testUpdateDominiCorreu() throws Exception {
		String novaDescripcio = "novaDescripcio";
		DominiCorreu dominiCorreu = service
				.findDominiCorreuByCodi(codiDominiCorreu);
		dominiCorreu.setDescripcio(novaDescripcio);
		dominiCorreu = service.update(dominiCorreu);
		assertNotNull(dominiCorreu);
		assertEquals(codiDominiCorreu, dominiCorreu.getCodi());
		assertEquals(novaDescripcio, dominiCorreu.getDescripcio());
		dominiCorreu = service.findDominiCorreuByCodi(codiDominiCorreu);
		assertNotNull(dominiCorreu);
		assertEquals(codiDominiCorreu, dominiCorreu.getCodi());
		assertEquals(novaDescripcio, dominiCorreu.getDescripcio());
	}

	public void testCreateLlistaCorreuNou() throws Exception {
		String nouNomLlistaCorreu = nomLlistaCorreu + "NOU";
		String descripcio = "prova";
		String codiDominiCorreu = null;
		LlistaCorreu llistaCorreu = new LlistaCorreu();
		llistaCorreu.setCodiDomini(codiDominiCorreu);
		llistaCorreu.setDescripcio(descripcio);
		llistaCorreu.setNom(nouNomLlistaCorreu);
		llistaCorreu = service.create(llistaCorreu);
		assertNotNull(llistaCorreu);
		assertEquals(nouNomLlistaCorreu, llistaCorreu.getNom());
		assertEquals(codiDominiCorreu, llistaCorreu.getCodiDomini());
		assertEquals(descripcio, llistaCorreu.getDescripcio());
		llistaCorreu = service.findLlistaCorreuByNomAndCodiDomini(
				nouNomLlistaCorreu, codiDominiCorreu);
		assertNotNull(llistaCorreu);
		assertEquals(nouNomLlistaCorreu, llistaCorreu.getNom());
		assertEquals(codiDominiCorreu, llistaCorreu.getCodiDomini());
		assertEquals(descripcio, llistaCorreu.getDescripcio());

	}

	public void testCreateRelacioLlistaCorreu() throws Exception {
		String nomLlistaCorreuPertany = nomLlistaCorreu + "NOU";
		String nomLlistaCorreuConte = nomLlistaCorreu;
		RelacioLlistaCorreu relacioLlistaCorreu = new RelacioLlistaCorreu();
		relacioLlistaCorreu.setNomLlistaCorreuConte(nomLlistaCorreuConte);
		relacioLlistaCorreu.setCodiDominiCorreuConte(codiDominiCorreu);
		relacioLlistaCorreu.setNomLlistaCorreuPertany(nomLlistaCorreuPertany);
		relacioLlistaCorreu.setCodiDominiCorreuPertany(null);
		relacioLlistaCorreu = service.create(relacioLlistaCorreu);
		assertNotNull(relacioLlistaCorreu);
		assertEquals(nomLlistaCorreuConte, relacioLlistaCorreu
				.getNomLlistaCorreuConte());
		assertEquals(nomLlistaCorreuPertany, relacioLlistaCorreu
				.getNomLlistaCorreuPertany());
		relacioLlistaCorreu = service
				.findRelacioLlistaCorreuByNomAndCodiLlistaCorreuPertanyAndNomAndCodiLlistaCorreuConte(
						nomLlistaCorreuPertany, null, nomLlistaCorreuConte,
						codiDominiCorreu);
		assertNotNull(relacioLlistaCorreu);
		assertEquals(nomLlistaCorreuConte, relacioLlistaCorreu
				.getNomLlistaCorreuConte());
		assertEquals(nomLlistaCorreuPertany, relacioLlistaCorreu
				.getNomLlistaCorreuPertany());
	}

	public void testFindRelacionsLlistaCorreuByNomLlistaCorreuConte()
			throws Exception {
		String nomLlistaCorreuPertany = nomLlistaCorreu + "NOU";
		String nomLlistaCorreuConte = nomLlistaCorreu;
		Collection relacionsLlistaCorreu = service
				.findRelacionsLlistaCorreuByNomLlistaCorreuConteAndCodiDomini(
						nomLlistaCorreuConte, codiDominiCorreu);
		assertNotNull(relacionsLlistaCorreu);
		assertEquals(1, relacionsLlistaCorreu.size());
		RelacioLlistaCorreu relacioLlistaCorreu = (RelacioLlistaCorreu) relacionsLlistaCorreu
				.iterator().next();
		assertNotNull(relacioLlistaCorreu);
		assertEquals(nomLlistaCorreuConte, relacioLlistaCorreu
				.getNomLlistaCorreuConte());
		assertEquals(nomLlistaCorreuPertany, relacioLlistaCorreu
				.getNomLlistaCorreuPertany());
	}

	public void testFindRelacionsLlistaCorreuByNomLlistaCorreuPertanyAndCodiDomini()
			throws Exception {
		String nomLlistaCorreuPertany = nomLlistaCorreu + "NOU";
		String nomLlistaCorreuConte = nomLlistaCorreu;
		Collection relacionsLlistaCorreu = service
				.findRelacionsLlistaCorreuByNomLlistaCorreuPertanyAndCodiDomini(
						nomLlistaCorreuPertany, null);
		assertNotNull(relacionsLlistaCorreu);
		assertEquals(1, relacionsLlistaCorreu.size());
		RelacioLlistaCorreu relacioLlistaCorreu = (RelacioLlistaCorreu) relacionsLlistaCorreu
				.iterator().next();
		assertNotNull(relacioLlistaCorreu);
		assertEquals(nomLlistaCorreuConte, relacioLlistaCorreu
				.getNomLlistaCorreuConte());
		assertEquals(nomLlistaCorreuPertany, relacioLlistaCorreu
				.getNomLlistaCorreuPertany());
	}

	public void testDeleteLlistaCorreuUsuari() throws RemoteException {
		LlistaCorreuUsuari llistaCorreuUsuari = service
				.findLlistaCorreuUsuariByNomLlistaCorreuAndCodiDominiAndCodiUsuari(
						nomLlistaCorreu, codiDominiCorreu, codiUsuari);
		assertNotNull(llistaCorreuUsuari);
		service.delete(llistaCorreuUsuari);
		llistaCorreuUsuari = service
				.findLlistaCorreuUsuariByNomLlistaCorreuAndCodiDominiAndCodiUsuari(
						nomLlistaCorreu, codiDominiCorreu, codiUsuari);
		assertNull(llistaCorreuUsuari);
	}

	public void testDeleteCorreuExtern() throws RemoteException {
		CorreuExtern correuExtern = service.findCorreuExternByAdreca(adreca);
		assertNotNull(correuExtern);
		service.delete(correuExtern);
		correuExtern = service.findCorreuExternByAdreca(adreca);
		assertNull(correuExtern);
	}

	public void testDeteteRelacioLlistaCorreu() throws RemoteException {
		String nomLlistaCorreuPertany = nomLlistaCorreu + "NOU";
		String nomLlistaCorreuConte = nomLlistaCorreu;
		RelacioLlistaCorreu relacioLlistaCorreu = service
				.findRelacioLlistaCorreuByNomAndCodiLlistaCorreuPertanyAndNomAndCodiLlistaCorreuConte(
						nomLlistaCorreuPertany, null, nomLlistaCorreuConte,
						codiDominiCorreu);
		assertNotNull(relacioLlistaCorreu);
		assertEquals(nomLlistaCorreuConte, relacioLlistaCorreu
				.getNomLlistaCorreuConte());
		assertEquals(nomLlistaCorreuPertany, relacioLlistaCorreu
				.getNomLlistaCorreuPertany());
		service.delete(relacioLlistaCorreu);
		relacioLlistaCorreu = service
				.findRelacioLlistaCorreuByNomAndCodiLlistaCorreuPertanyAndNomAndCodiLlistaCorreuConte(
						nomLlistaCorreuPertany, null, nomLlistaCorreuConte,
						codiDominiCorreu);
		assertNull(relacioLlistaCorreu);
	}

	public void testDeteteLlistaCorreu() throws RemoteException {
		LlistaCorreu llistaCorreu = service.findLlistaCorreuByNomAndCodiDomini(
				nomLlistaCorreu, codiDominiCorreu);
		assertNotNull(llistaCorreu);
		service.delete(llistaCorreu);
		llistaCorreu = service.findLlistaCorreuByNomAndCodiDomini(
				nomLlistaCorreu, codiDominiCorreu);
		assertNull(llistaCorreu);

		llistaCorreu = service.findLlistaCorreuByNomAndCodiDomini(
				nomLlistaCorreu + "NOU", null);
		assertNotNull(llistaCorreu);
		service.delete(llistaCorreu);
		llistaCorreu = service.findLlistaCorreuByNomAndCodiDomini(
				nomLlistaCorreu + "NOU", null);
		assertNull(llistaCorreu);

		DominiCorreu dominiCorreu = service
				.findDominiCorreuByCodi(codiDominiCorreu);
		service.delete(dominiCorreu);
		dominiCorreu = service.findDominiCorreuByCodi(codiDominiCorreu);
		assertNull(dominiCorreu);
	}
*/
}
