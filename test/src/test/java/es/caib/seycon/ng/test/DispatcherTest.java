package es.caib.seycon.ng.test;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.naming.Context;
import javax.rmi.PortableRemoteObject;

import junit.framework.Assert;
import junit.framework.TestCase;
import es.caib.seycon.ng.comu.Dispatcher;
import es.caib.seycon.ng.servei.ejb.DispatcherService;
import es.caib.seycon.ng.servei.ejb.DispatcherServiceHome;

public class DispatcherTest extends TestCase {

	private DispatcherService service;

	protected void setUp() throws Exception {
		try{
			super.setUp();
			Context ctx = InitialContextFactory.getInitialContext();
			Object obj = ctx.lookup(DispatcherServiceHome.JNDI_NAME);
			DispatcherServiceHome DispatcherHome = (DispatcherServiceHome) PortableRemoteObject.narrow(
				obj, DispatcherServiceHome.class);
			service = DispatcherHome.create();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	public void testCreate()
			throws java.lang.Exception {
		Dispatcher dispatcher = new Dispatcher();
		dispatcher.setBasRol(new Boolean(true));
		dispatcher.setCodi("codi"); //$NON-NLS-1$
		dispatcher.setNomCla("nomCla"); //$NON-NLS-1$
		dispatcher.setParam0("param0"); //$NON-NLS-1$
		dispatcher.setParam1("param1"); //$NON-NLS-1$
		dispatcher.setParam2("param2"); //$NON-NLS-1$
		dispatcher.setParam3("param3"); //$NON-NLS-1$
		dispatcher.setParam4("param4"); //$NON-NLS-1$
		dispatcher.setParam5("param5"); //$NON-NLS-1$
		dispatcher.setParam6("param6"); //$NON-NLS-1$
		dispatcher.setParam7("param7"); //$NON-NLS-1$
		dispatcher.setParam8("param8"); //$NON-NLS-1$
		dispatcher.setParam9("param9"); //$NON-NLS-1$
		dispatcher.setUrl("http://localhost"); //$NON-NLS-1$
		dispatcher.setSegur(new Boolean(true));
		dispatcher = service.create(dispatcher);
		Assert.assertNotNull(dispatcher);
	}

	public void testFindDispatcherByCodi()
	throws Exception {
		String codi = "codi"; //$NON-NLS-1$
		Dispatcher dispatcher = service.findDispatcherByCodi(codi);
		Assert.assertNotNull(dispatcher);
		Assert.assertEquals("param6", dispatcher.getParam6()); //$NON-NLS-1$
		Assert.assertEquals("param0", dispatcher.getParam0()); //$NON-NLS-1$
		Assert.assertEquals(new Boolean(true), dispatcher.getBasRol());
		Assert.assertEquals("codi", dispatcher.getCodi()); //$NON-NLS-1$
		Assert.assertEquals("nomCla", dispatcher.getNomCla()); //$NON-NLS-1$
	}
	
	public void testUpdate()
			throws java.lang.Exception {
		Dispatcher dispatcher = service.findDispatcherByCodi("codi"); //$NON-NLS-1$
		dispatcher.setParam6("nouParam6"); //$NON-NLS-1$
		dispatcher.setBasRol(new Boolean(true));
		dispatcher.setNomCla("nouNomCla"); //$NON-NLS-1$
		dispatcher.setParam0("nouParam0");		 //$NON-NLS-1$
		dispatcher = service.update(dispatcher);
		dispatcher = service.findDispatcherByCodi("codi"); //$NON-NLS-1$
		Assert.assertNotNull(dispatcher);
		Assert.assertEquals("nouParam6", dispatcher.getParam6()); //$NON-NLS-1$
		Assert.assertEquals("nouParam0", dispatcher.getParam0()); //$NON-NLS-1$
		Assert.assertEquals(new Boolean(true), dispatcher.getBasRol());
		Assert.assertEquals("nouNomCla", dispatcher.getNomCla()); //$NON-NLS-1$
	}

	public void testFindDispatchersByFiltre()
			throws Exception {
		String codi = "codi"; //$NON-NLS-1$
		String nomCla = "%";  //$NON-NLS-1$
		String url = "%";  //$NON-NLS-1$
		String basRol = "%";  //$NON-NLS-1$
		String segur = "%"; //$NON-NLS-1$
		Collection dispatchers = service.findDispatchersByFiltre(codi, nomCla, url, basRol, segur);
		Iterator iterator = dispatchers.iterator();
		while(iterator.hasNext()){
			Dispatcher dispatcher = (Dispatcher) iterator.next();
			Assert.assertEquals("codi", dispatcher.getCodi()); //$NON-NLS-1$
		}		
	}
	
	public void testDelete()
			throws java.lang.Exception {
		Dispatcher dispatcher = service.findDispatcherByCodi("codi"); //$NON-NLS-1$
		service.delete(dispatcher);
		dispatcher = service.findDispatcherByCodi("codi"); //$NON-NLS-1$
		Assert.assertNull(dispatcher);
	}

	

}