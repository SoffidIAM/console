package es.caib.seycon.ng.test;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Vector;

import javax.naming.Context;
import javax.rmi.PortableRemoteObject;

import es.caib.seycon.ng.comu.AdministradorSeguretatOrganitzatiuFitxer;
import es.caib.seycon.ng.comu.Fitxer;
import es.caib.seycon.ng.comu.ResponsableSeguretatOrganitzatiuFitxer;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.RolFitxer;
import es.caib.seycon.ng.servei.ejb.AplicacioService;
import es.caib.seycon.ng.servei.ejb.AplicacioServiceHome;
import es.caib.seycon.ng.servei.ejb.DominiServiceHome;
import es.caib.seycon.ng.servei.ejb.LopdService;
import es.caib.seycon.ng.servei.ejb.LopdServiceHome;
import junit.framework.TestCase;

public class LopdTest extends TestCase {

	private LopdService service;
	private AplicacioService aplicacioService;

	protected void setUp() throws Exception {
		super.setUp();
		try {
			Context ctx = InitialContextFactory.getInitialContext();

			Object obj = ctx.lookup(LopdServiceHome.JNDI_NAME);
			LopdServiceHome home = (LopdServiceHome) PortableRemoteObject
					.narrow(obj, LopdServiceHome.class);
			service = home.create();

			Object obj2 = ctx.lookup(AplicacioServiceHome.JNDI_NAME);
			AplicacioServiceHome home2 = (AplicacioServiceHome) PortableRemoteObject
					.narrow(obj2, AplicacioServiceHome.class);
			aplicacioService = home2.create();
			
			obj = ctx.lookup(DominiServiceHome.JNDI_NAME);
			DominiServiceHome dominiHome = (DominiServiceHome) PortableRemoteObject
					.narrow(obj, DominiServiceHome.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
/*
	public void testCreate() throws RemoteException {
		String nom = "Prova";
		String responsable = "u89559";
		String responsableTecnic = "u89559";
		String nivell = "ALT";
		String registrat = "06/07/2006";
		Boolean regapd = new Boolean(false);
		Fitxer fitxer = new Fitxer();
		fitxer.setNom(nom);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(dateFormat.parse(registrat));
			fitxer.setDataRegistreAPD(calendar);
		} catch (Exception e) {
			fail();
		}
		fitxer.setResponsable(responsable);
		fitxer.setResponsableSeguretatTecnic(responsableTecnic);
		fitxer.setNivell(nivell);
		fitxer.setRegistratAPD(regapd);

		fitxer.setDireccioGeneral("DGTIC");
		fitxer = service.create(fitxer);

		fitxer = service.findFitxerById(fitxer.getId());
		assertNotNull(fitxer);
		assertEquals(nom, fitxer.getNom());
		assertEquals(responsable, fitxer.getResponsable());
		assertEquals(nivell, fitxer.getNivell());
		assertEquals(regapd, fitxer.getRegistratAPD());
	}

	public void testFindFitxersByFiltre() throws Exception {
		String id = null;
		String nom = null;
		String responsable = null;
		String conselleria = null;
		String dg = null;
		String nivell = null;
		String registrat = "06/07/2006";
		Collection fitxers = service.findFitxersByFiltre(id, nom, responsable,
				conselleria, dg, nivell, registrat);
		assertTrue(fitxers.size() > 0);

		Iterator iterator = null;

		id = null;
		nom = "Pro%";
		responsable = null;
		conselleria = null;
		dg = null;
		nivell = null;
		registrat = null;
		fitxers = service.findFitxersByFiltre(id, nom, responsable,
				conselleria, dg, nivell, registrat);
		assertTrue(fitxers.size() > 0);
		iterator = fitxers.iterator();
		while (iterator.hasNext()) {
			Fitxer fitxer = (Fitxer) iterator.next();
			assertTrue(fitxer.getNom().startsWith("Pro"));
		}

		id = null;
		nom = "";
		responsable = null;
		conselleria = null;
		dg = "DGTIC%";
		nivell = "%";
		registrat = "06/07/2006";
		fitxers = service.findFitxersByFiltre(id, nom, responsable,
				conselleria, dg, nivell, registrat);
		assertTrue(fitxers.size() > 0);
		iterator = fitxers.iterator();
		while (iterator.hasNext()) {
			Fitxer fitxer = (Fitxer) iterator.next();
			assertTrue(fitxer.getDireccioGeneral().startsWith("DGTIC"));
		}

		id = null;
		nom = "";
		responsable = "%";
		conselleria = null;
		dg = "";
		nivell = "ALT%";
		registrat = null;
		fitxers = service.findFitxersByFiltre(id, nom, responsable,
				conselleria, dg, nivell, registrat);
		assertTrue(fitxers.size() > 0);
		iterator = fitxers.iterator();
		while (iterator.hasNext()) {
			Fitxer fitxer = (Fitxer) iterator.next();
			assertTrue(fitxer.getNivell().startsWith("ALT"));
		}

		id = null;
		nom = "";
		responsable = "u895%";
		conselleria = null;
		dg = "";
		nivell = "%";
		registrat = "06/07/2006";
		fitxers = service.findFitxersByFiltre(id, nom, responsable,
				conselleria, dg, nivell, registrat);
		assertTrue(fitxers.size() > 0);
		iterator = fitxers.iterator();
		while (iterator.hasNext()) {
			Fitxer fitxer = (Fitxer) iterator.next();
			assertTrue(fitxer.getResponsable().startsWith("u895"));
		}

		id = null;
		nom = "";
		responsable = "%";
		conselleria = "ecohino%";
		dg = "";
		nivell = "%";
		registrat = "";
		fitxers = service.findFitxersByFiltre(id, nom, responsable,
				conselleria, dg, nivell, registrat);
		assertTrue(fitxers.size() > 0);
		iterator = fitxers.iterator();
		while (iterator.hasNext()) {
			Fitxer fitxer = (Fitxer) iterator.next();
			assertTrue(fitxer.getConselleria().startsWith("ecohino"));
		}

		fitxers = service.findFitxersByNomFitxer("Prova");
		assertTrue(fitxers.size() == 1);
		Fitxer fitxer = (Fitxer) fitxers.iterator().next();
		id = fitxer.getId().toString();
		nom = "%";
		responsable = "%";
		conselleria = null;
		dg = "";
		nivell = "%";
		registrat = "06/07/2006";
		fitxers = service.findFitxersByFiltre(id, nom, responsable,
				conselleria, dg, nivell, registrat);
		assertTrue(fitxers.size() > 0);
		iterator = fitxers.iterator();
		while (iterator.hasNext()) {
			fitxer = (Fitxer) iterator.next();
			assertTrue(fitxer.getId().compareTo(new Long(id)) == 0);
		}

		boolean error = false;
		try {			
			id = null;
			nom = "%";
			responsable = "%";
			conselleria = null;
			dg = "";
			nivell = "%";
			registrat = null;
			fitxers = service.findFitxersByFiltre(id, nom, responsable,
					conselleria, dg, nivell, registrat);
		} catch (Exception e) {
			error = true;
		}
		assertTrue(error);
	}

	public void testUpdate() throws RemoteException {
		Collection collection = service.findFitxersByFiltre(null, "Prova", "%",
				"%", "%", "%", "%");
		assertNotNull(collection);
		assertTrue(collection.size() == 1);
		Fitxer fitxer = (Fitxer) collection.iterator().next();
		fitxer.setFinalitat("finalitat");
		fitxer.setDireccioGeneral("DGECO");
		fitxer.setCodiRegistreAPD("codiRegistreAPD");
		fitxer
				.setDadesAcademiquesProfessionals("dadesAcademiquesProfessionals");
		fitxer.setDadesComercials("dadesComercials");
		fitxer.setDadesIdentificatives("dadesIdentificatives");
		fitxer.setDadesInfraccions("dadesInfraccions");
		fitxer.setDadesLaborals("dadesLaborals");
		fitxer.setDadesPersonals("dadesPersonals");
		fitxer.setDadesSocials("dadesSocials");
		fitxer.setDadesTransaccionals("dadesTransaccionals");
		fitxer.setSistema("sistema");
		fitxer.setResponsableSeguretatTecnic("u89559");
		fitxer.setResponsable("u89559");
		fitxer.setRegistratAPD(new Boolean(true));
		fitxer.setObservacions("observaciones");
		fitxer.setNomRegistreAPD("nomRegistreAPD");
		fitxer.setNivell("ALT");
		fitxer.setMotiuBaixa("motiuBaixa");
		service.update(fitxer);

		collection = service.findFitxersByFiltre(null, "Prova", "%", "%", "%",
				"%", "%");
		assertNotNull(collection);
		assertTrue(collection.size() == 1);
		fitxer = (Fitxer) collection.iterator().next();
		assertEquals("DGECO", fitxer.getDireccioGeneral());
		assertEquals("ecohinova", fitxer.getConselleria());
		assertEquals("finalitat", fitxer.getFinalitat());
		assertEquals("DGECO", fitxer.getDireccioGeneral());
		assertEquals("codiRegistreAPD", fitxer.getCodiRegistreAPD());
		assertEquals("dadesAcademiquesProfessionals", fitxer
				.getDadesAcademiquesProfessionals());
		assertEquals("dadesComercials", fitxer.getDadesComercials());
		assertEquals("dadesIdentificatives", fitxer.getDadesIdentificatives());
		assertEquals("dadesInfraccions", fitxer.getDadesInfraccions());
		assertEquals("dadesLaborals", fitxer.getDadesLaborals());
		assertEquals("dadesPersonals", fitxer.getDadesPersonals());
		assertEquals("dadesSocials", fitxer.getDadesSocials());
		assertEquals("dadesTransaccionals", fitxer.getDadesTransaccionals());
		assertEquals("sistema", fitxer.getSistema());
		assertEquals("u89559", fitxer.getResponsableSeguretatTecnic());
		assertEquals("u89559", fitxer.getResponsable());
		assertEquals(new Boolean(true), fitxer.getRegistratAPD());
		assertEquals("observaciones", fitxer.getObservacions());
		assertEquals("nomRegistreAPD", fitxer.getNomRegistreAPD());
		assertEquals("ALT", fitxer.getNivell());
		assertEquals("motiuBaixa", fitxer.getMotiuBaixa());
	}

	public void testAddRole() throws RemoteException {
		RolFitxer rolFitxer = new RolFitxer();
		Collection fitxers = service.findFitxersByNomFitxer("Prova");
		assertTrue(fitxers.size() == 1);
		Fitxer fitxer = (Fitxer) fitxers.iterator().next();
		Long identifier = fitxer.getId();
		rolFitxer.setIdFitxer(identifier);
		rolFitxer.setNomRol("SC_GESTOR");
		service.create(rolFitxer);
		Collection roles = service.findRolesByIdFitxer(identifier);
		assertTrue(roles.size() > 0);
		Iterator rolIterator = roles.iterator();
		while (rolIterator.hasNext()) {
			Rol rol = (Rol) rolIterator.next();
			assertEquals(rol.getNom(), "SC_GESTOR");
		}
	}

	public void testFindRolesByIdFitxer() throws RemoteException {
		Collection fitxers = service.findFitxersByNomFitxer("Prova");
		assertTrue(fitxers.size() == 1);
		Fitxer fitxer = (Fitxer) fitxers.iterator().next();
		Long identifier = fitxer.getId();
		Collection roles = service.findRolesByIdFitxer(identifier);
		assertNotNull(roles);
		assertEquals(roles.size(), 1);
		Iterator iterator = roles.iterator();
		while (iterator.hasNext()) {
			Rol rol = (Rol) iterator.next();
			assertEquals(rol.getNom(), "SC_GESTOR");
		}
	}

	public void testCreateResponsableSeguretatOrganitzatiuFitxer()
			throws Exception {
		String codiUsuari = "u89559";
		ResponsableSeguretatOrganitzatiuFitxer responsableSeguretatOrganitzatiuFitxer = new ResponsableSeguretatOrganitzatiuFitxer();
		responsableSeguretatOrganitzatiuFitxer.setCodiUsuari(codiUsuari);
		Collection fitxers = service.findFitxersByNomFitxer("Prova");
		assertTrue(fitxers.size() == 1);
		Fitxer fitxer = (Fitxer) fitxers.iterator().next();
		Long identifier = fitxer.getId();
		responsableSeguretatOrganitzatiuFitxer.setIdFitxer(identifier);
		responsableSeguretatOrganitzatiuFitxer = service
				.create(responsableSeguretatOrganitzatiuFitxer);
		assertNotNull(responsableSeguretatOrganitzatiuFitxer);
		assertEquals(codiUsuari, responsableSeguretatOrganitzatiuFitxer
				.getCodiUsuari());
		assertEquals(identifier, responsableSeguretatOrganitzatiuFitxer
				.getIdFitxer());
		responsableSeguretatOrganitzatiuFitxer = service
				.findResponsableSeguretatOrganitzatiuFitxerByCodiUsuariAndIdFitxer(
						codiUsuari, identifier);
		assertNotNull(responsableSeguretatOrganitzatiuFitxer);
		assertEquals(codiUsuari, responsableSeguretatOrganitzatiuFitxer
				.getCodiUsuari());
		assertEquals(identifier, responsableSeguretatOrganitzatiuFitxer
				.getIdFitxer());

		Collection responsablesFitxer = service
				.findResponsablesSeguretatOrganitzatiusFitxerByIdFitxer(identifier);
		assertTrue(responsablesFitxer.size() > 0);
		Iterator iterator = responsablesFitxer.iterator();
		while (iterator.hasNext()) {
			ResponsableSeguretatOrganitzatiuFitxer responsableFitxer = (ResponsableSeguretatOrganitzatiuFitxer) iterator
					.next();
			assertEquals(identifier, responsableFitxer.getIdFitxer());
		}
	}

	public void testCreateAdministradorSeguretatOrganitzatiuFitxer()
			throws Exception {
		String codiUsuari = "u89559";
		AdministradorSeguretatOrganitzatiuFitxer administradorSeguretatOrganitzatiuFitxer = new AdministradorSeguretatOrganitzatiuFitxer();
		administradorSeguretatOrganitzatiuFitxer.setCodiUsuari(codiUsuari);
		Collection fitxers = service.findFitxersByNomFitxer("Prova");
		assertTrue(fitxers.size() == 1);
		Fitxer fitxer = (Fitxer) fitxers.iterator().next();
		Long identifier = fitxer.getId();
		administradorSeguretatOrganitzatiuFitxer.setIdFitxer(identifier);
		administradorSeguretatOrganitzatiuFitxer = service
				.create(administradorSeguretatOrganitzatiuFitxer);
		assertNotNull(administradorSeguretatOrganitzatiuFitxer);
		assertEquals(codiUsuari, administradorSeguretatOrganitzatiuFitxer
				.getCodiUsuari());
		assertEquals(identifier, administradorSeguretatOrganitzatiuFitxer
				.getIdFitxer());
		administradorSeguretatOrganitzatiuFitxer = service
				.findAdministradorSeguretatOrganitzatiuFitxerByCodiUsuariAndIdFitxer(
						codiUsuari, identifier);
		assertNotNull(administradorSeguretatOrganitzatiuFitxer);
		assertEquals(codiUsuari, administradorSeguretatOrganitzatiuFitxer
				.getCodiUsuari());
		assertEquals(identifier, administradorSeguretatOrganitzatiuFitxer
				.getIdFitxer());

		Collection administradorsFitxer = service
				.findAdministradorsSeguretatOrganitzatiusFitxerByIdFitxer(identifier);
		assertTrue(administradorsFitxer.size() > 0);
		Iterator iterator = administradorsFitxer.iterator();
		while (iterator.hasNext()) {
			AdministradorSeguretatOrganitzatiuFitxer administradorFitxer = (AdministradorSeguretatOrganitzatiuFitxer) iterator
					.next();
			assertEquals(identifier, administradorFitxer.getIdFitxer());
		}

	}

	public void testDeleteResponsableSeguretatOrganitzatiuFitxer()
			throws Exception {
		String codiUsuari = "u89559";
		Collection fitxers = service.findFitxersByNomFitxer("Prova");
		assertTrue(fitxers.size() == 1);
		Fitxer fitxer = (Fitxer) fitxers.iterator().next();
		Long identifier = fitxer.getId();
		ResponsableSeguretatOrganitzatiuFitxer responsableSeguretatOrganitzatiuFitxer = service
				.findResponsableSeguretatOrganitzatiuFitxerByCodiUsuariAndIdFitxer(
						codiUsuari, identifier);
		service.delete(responsableSeguretatOrganitzatiuFitxer);
		responsableSeguretatOrganitzatiuFitxer = service
				.findResponsableSeguretatOrganitzatiuFitxerByCodiUsuariAndIdFitxer(
						codiUsuari, identifier);
		assertNull(responsableSeguretatOrganitzatiuFitxer);
	}

	public void testDeleteAdministradorSeguretatOrganitzatiuFitxer()
			throws Exception {
		String codiUsuari = "u89559";
		Collection fitxers = service.findFitxersByNomFitxer("Prova");
		assertTrue(fitxers.size() == 1);
		Fitxer fitxer = (Fitxer) fitxers.iterator().next();
		Long identifier = fitxer.getId();
		AdministradorSeguretatOrganitzatiuFitxer administradorSeguretatOrganitzatiuFitxer = service
				.findAdministradorSeguretatOrganitzatiuFitxerByCodiUsuariAndIdFitxer(
						codiUsuari, identifier);
		service.delete(administradorSeguretatOrganitzatiuFitxer);
		administradorSeguretatOrganitzatiuFitxer = service
				.findAdministradorSeguretatOrganitzatiuFitxerByCodiUsuariAndIdFitxer(
						codiUsuari, identifier);
		assertNull(administradorSeguretatOrganitzatiuFitxer);
	}

	public void testRemoveRoleFromFitxer() throws Exception {
		Collection fitxers = service.findFitxersByNomFitxer("Prova");
		assertTrue(fitxers.size() == 1);
		Fitxer fitxer = (Fitxer) fitxers.iterator().next();
		Long identifier = fitxer.getId();
		String nomRol = "SC_GESTOR";
		RolFitxer rolFitxer = service.findRolFitxerByNomRolAndIdFitxer(nomRol,
				identifier);
		assertNotNull(rolFitxer);
		service.delete(rolFitxer);
		rolFitxer = service
				.findRolFitxerByNomRolAndIdFitxer(nomRol, identifier);
		assertNull(rolFitxer);
	}

	public void testFindFitxerById() throws Exception {
		Collection fitxers = service.findFitxersByNomFitxer("Prova");
		assertTrue(fitxers.size() == 1);
		Fitxer fitxer = (Fitxer) fitxers.iterator().next();
		Long identifier = fitxer.getId();
		fitxer = service.findFitxerById(identifier);
		assertNotNull(fitxer);
		assertEquals("DGECO", fitxer.getDireccioGeneral());
		assertEquals("ecohinova", fitxer.getConselleria());
		assertEquals("finalitat", fitxer.getFinalitat());
		assertEquals("DGECO", fitxer.getDireccioGeneral());
		assertEquals("codiRegistreAPD", fitxer.getCodiRegistreAPD());
		assertEquals("dadesAcademiquesProfessionals", fitxer
				.getDadesAcademiquesProfessionals());
		assertEquals("dadesComercials", fitxer.getDadesComercials());
		assertEquals("dadesIdentificatives", fitxer.getDadesIdentificatives());
		assertEquals("dadesInfraccions", fitxer.getDadesInfraccions());
		assertEquals("dadesLaborals", fitxer.getDadesLaborals());
		assertEquals("dadesPersonals", fitxer.getDadesPersonals());
		assertEquals("dadesSocials", fitxer.getDadesSocials());
		assertEquals("dadesTransaccionals", fitxer.getDadesTransaccionals());
		assertEquals("sistema", fitxer.getSistema());
		assertEquals("u89559", fitxer.getResponsableSeguretatTecnic());
		assertEquals("u89559", fitxer.getResponsable());
		assertEquals(new Boolean(true), fitxer.getRegistratAPD());
		assertEquals("observaciones", fitxer.getObservacions());
		assertEquals("nomRegistreAPD", fitxer.getNomRegistreAPD());
		assertEquals("ALT", fitxer.getNivell());
		assertEquals("motiuBaixa", fitxer.getMotiuBaixa());
	}

	public void testRemoveFitxer() throws Exception {
		Collection fitxers = service.findFitxersByNomFitxer("Prova");
		assertTrue(fitxers.size() == 1);
		Fitxer fitxer = (Fitxer) fitxers.iterator().next();
		Long identifier = fitxer.getId();
		service.delete(fitxer);
		fitxer = service.findFitxerById(identifier);
		assertNull(fitxer);
	}*/

}
