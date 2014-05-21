package es.caib.seycon.ng.test;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;

import javax.naming.Context;
import javax.rmi.PortableRemoteObject;

import junit.framework.Assert;
import junit.framework.TestCase;
import es.caib.seycon.ng.comu.Aplicacio;
import es.caib.seycon.ng.comu.Maquina;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.servei.AplicacioService;
import es.caib.seycon.ng.servei.XarxaService;
import es.caib.seycon.ng.servei.workflow.ejb.InformacioAutoritzacioService;
import es.caib.seycon.ng.servei.workflow.ejb.InformacioAutoritzacioServiceHome;

public class InformacioAutoritzacioTest  extends TestCase{

	private InformacioAutoritzacioService service;

	protected void setUp() throws Exception {
		super.setUp();
		Context ctx = InitialContextFactory.getInitialContext();
		Object obj = ctx.lookup(InformacioAutoritzacioServiceHome.JNDI_NAME);
		InformacioAutoritzacioServiceHome autoritzacionsServiceHome = (InformacioAutoritzacioServiceHome) PortableRemoteObject
				.narrow(obj, InformacioAutoritzacioServiceHome.class);
		service = autoritzacionsServiceHome.create();
	}

	/*
	 * public java.util.Collection testGetAplicacions() throws
	 * java.lang.Exception { Collection aplicacions = service.getAplicacions();
	 * return aplicacions; }
	 */
	
	public void testFindRolsByCodiAplicacio()
			throws java.lang.Exception {
		String codiAplicacio = "CATHOS"; //$NON-NLS-1$
		Collection rols = service.findRolsByCodiAplicacio(codiAplicacio);
		Iterator iterator = rols.iterator();
		while(iterator.hasNext()){
			Rol rol = (Rol) iterator.next();
		}
	}

	public void testFindRolsByCodiUsuari()
			throws java.lang.Exception {
		String codiUsuari = "u89559"; //$NON-NLS-1$
		Collection rols = service.findRolsByCodiUsuari(codiUsuari);
		Iterator iterator = rols.iterator();
		while(iterator.hasNext()){
			Rol rol = (Rol) iterator.next();
		}
	}

	public void testFindUsuariByDadesUsuari()
			throws java.lang.Exception {
		Collection usuaris = service.findUsuariByDadesUsuari("", "Pa%", //$NON-NLS-1$ //$NON-NLS-2$
				"Car%", "%dona"); //$NON-NLS-1$ //$NON-NLS-2$
		Iterator iterator = usuaris.iterator();
		while(iterator.hasNext()){
			Usuari usuari = (Usuari) iterator.next();
		}
	}

	public void testFindAdministradorsAplicacioByCodiAplicacio()
			throws Exception {
		String codiAplicacio = "EDU-EST00"; //$NON-NLS-1$
		Collection administradors = service
				.findAdministradorsAplicacioByCodiAplicacio(codiAplicacio);
		Iterator iterator = administradors.iterator();
		while(iterator.hasNext()){
			Usuari usuari = (Usuari) iterator.next();
		}

	}
	

	public void testGetAplicacionsByCodiUsuari() throws Exception {
		String codiUsuari = "u89559"; //$NON-NLS-1$

		Collection aplicacions = service.getAplicacionsByCodiUsuari(codiUsuari);
		Iterator iterator = aplicacions.iterator();
		while(iterator.hasNext()){
			Aplicacio aplicacio = (Aplicacio) iterator.next();
		}
	}


	public void testGetRolsByCodiUsuari() throws Exception {
		String codiUsuari = "u89559"; //$NON-NLS-1$

		Collection aplicacions = service.getRolsByCodiUsuari(codiUsuari);
		Iterator iterator = aplicacions.iterator();
		while(iterator.hasNext()){
			Rol rol = (Rol) iterator.next();
		}
	}
	

	public void testGetRolsAplicacioByCodiUsuariAndCodiAplicacio()
			throws Exception {
		String codiUsuari = "u89559"; //$NON-NLS-1$
		String codiAplicacio = "CATHOS"; //$NON-NLS-1$
		Collection aplicacions = service
				.getRolsAplicacioByCodiUsuariAndCodiAplicacio(codiUsuari,
						codiAplicacio);
		Iterator iterator = aplicacions.iterator();
		while(iterator.hasNext()){
			Rol rol = (Rol) iterator.next();
		}
	}
	

	public  void testIsAdministradorAplicacio() throws Exception {
		String codiUsuari = "u04697"; //$NON-NLS-1$
		String codiAplicacio = "EDU-EST00"; //$NON-NLS-1$
		Boolean hoEs = service.isAdministradorAplicacio(codiUsuari,
				codiAplicacio);
		codiAplicacio = "SEINCO"; //$NON-NLS-1$
		hoEs = service.isAdministradorAplicacio(codiUsuari,
				codiAplicacio);
	}
	
	

	public void testFindAplicacioByCriteri() throws Exception {
		String codi = "%"; //$NON-NLS-1$
		String nom = "S%"; //$NON-NLS-1$
		String directoriFonts = "%"; //$NON-NLS-1$
		String responsable = "%"; //$NON-NLS-1$
		String directoriExecutable = "%"; //$NON-NLS-1$
		String bd = "%"; //$NON-NLS-1$
		Collection aplicacions = service.findAplicacioByCriteri(codi, nom, 
				directoriFonts, responsable, directoriExecutable, bd);				
		Iterator iterator = aplicacions.iterator();
		while(iterator.hasNext()){
			Aplicacio aplicacio = (Aplicacio) iterator.next();
		}
	}

	public void testFindAplicacionsAdministradesByCodiUsuari()
			throws Exception {
		String codiUsuari = "u89559"; //$NON-NLS-1$
		Collection aplicacions = service
				.findAplicacionsAdministradesByCodiUsuari(codiUsuari);
		Iterator iterator = aplicacions.iterator();
		while(iterator.hasNext()){
			Aplicacio aplicacio = (Aplicacio) iterator.next();
		}
	}

	public void testGetRolSistemes() throws Exception {
		String codiAplicacio = "myApplication"; //$NON-NLS-1$
		Rol rol = service.getRolSistemes(codiAplicacio);
	}

	public void testNecessitaIntervencioSistemes() throws Exception {
		String codiAplicacio = "myApplication"; //$NON-NLS-1$
		String[] codisRols = { "myRol" }; //$NON-NLS-1$
		boolean enNecessita = service.necessitaIntervencioSistemes(
				codiAplicacio, codisRols);
	}
}
