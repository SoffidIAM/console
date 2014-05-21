package es.caib.seycon.ng.test;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;

import javax.naming.Context;
import javax.rmi.PortableRemoteObject;

import junit.framework.Assert;
import junit.framework.TestCase;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.Servei;
import es.caib.seycon.ng.servei.ejb.ServeiService;
import es.caib.seycon.ng.servei.ejb.ServeiServiceHome;

public class ServeiTest extends TestCase {

	private ServeiService service;

	protected void setUp() throws Exception {
		super.setUp();
		Context ctx = InitialContextFactory.getInitialContext();
		Object obj = ctx.lookup(ServeiServiceHome.JNDI_NAME);
		ServeiServiceHome serveiHome = (ServeiServiceHome) PortableRemoteObject
				.narrow(obj, ServeiServiceHome.class);
		service = serveiHome.create();
	}

	public void testCreate() throws java.lang.Exception {
		Servei servei = new Servei();
		servei.setCodi("hola"); //$NON-NLS-1$
		servei.setDescripcio("servei de salutacio"); //$NON-NLS-1$
		servei = service.create(servei);
		assertNotNull(servei);
		assertEquals("hola", servei.getCodi()); //$NON-NLS-1$
		assertEquals("servei de salutacio", servei.getDescripcio()); //$NON-NLS-1$
		servei = service.findServeiByCodi("hola"); //$NON-NLS-1$
		assertNotNull(servei);
		assertEquals("hola", servei.getCodi()); //$NON-NLS-1$
		assertEquals("servei de salutacio", servei.getDescripcio());	 //$NON-NLS-1$
	}
	
	
	
	public void testUpdate() throws java.lang.Exception {
		Servei servei =  service.findServeiByCodi("hola"); //$NON-NLS-1$
		assertNotNull(servei);
		servei.setDescripcio("servei de salutacio actualitzat"); //$NON-NLS-1$
		servei = service.update(servei);
		servei = service.findServeiByCodi("hola"); //$NON-NLS-1$
		assertNotNull(servei);
		assertEquals("hola", servei.getCodi()); //$NON-NLS-1$
		assertEquals("servei de salutacio actualitzat", servei.getDescripcio()); //$NON-NLS-1$
	}
	
	public void testFindServeisByCriteri() throws Exception {
		String codi = "hola"; //$NON-NLS-1$
		String descripcio = "%"; //$NON-NLS-1$
		Collection serveis = service.findServeisByCriteri(codi, descripcio);
		Iterator iterator = serveis.iterator();
		while(iterator.hasNext()){
			Servei servei = (Servei) iterator.next();
			assertEquals("hola", servei.getCodi()); //$NON-NLS-1$
		}
		
		codi = ""; //$NON-NLS-1$
		descripcio = "servei de sa%"; //$NON-NLS-1$
		serveis = service.findServeisByCriteri(codi, descripcio);
		iterator = serveis.iterator();
		while(iterator.hasNext()){
			Servei servei = (Servei) iterator.next();
			assertTrue(servei.getDescripcio().startsWith("servei de sa")); //$NON-NLS-1$
		}	

	}
	
	
	public void testDelete() throws java.lang.Exception {
		Servei servei = service.findServeiByCodi("hola"); //$NON-NLS-1$
		Assert.assertNotNull(servei);
		servei.setDescripcio("servei de salutacio actualitzat"); //$NON-NLS-1$
		service.delete(servei);
		servei = service.findServeiByCodi("hola"); //$NON-NLS-1$
		Assert.assertNull(servei);
	}

	
}