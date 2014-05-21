package es.caib.seycon.ng.test;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;

import javax.naming.Context;
import javax.rmi.PortableRemoteObject;

import junit.framework.TestCase;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.servei.ejb.DadesAddicionalsService;
import es.caib.seycon.ng.servei.ejb.DadesAddicionalsServiceHome;

public class DadesAddicionalsTest extends TestCase {

	private DadesAddicionalsService service;
	private static String codi = "HHHHRRRRRR"; //$NON-NLS-1$
		
	protected void setUp() throws Exception {
		try{
			super.setUp();
			Context ctx = InitialContextFactory.getInitialContext();
			Object obj = ctx.lookup(DadesAddicionalsServiceHome.JNDI_NAME);
			DadesAddicionalsServiceHome CPDHome = (DadesAddicionalsServiceHome) PortableRemoteObject.narrow(
				obj, DadesAddicionalsServiceHome.class);
			service = CPDHome.create();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void testGetTipusDades()	throws java.lang.Exception {
		Collection tipusDades = service.getTipusDades();
		assertTrue(tipusDades.size() > 0);
	}

	public void testCreate()throws java.lang.Exception {
		Long ordre = new Long(33);
		TipusDada tipusDada = new TipusDada();
		tipusDada.setCodi(codi);
		tipusDada.setOrdre(ordre);
		tipusDada = service.create(tipusDada);
		assertNotNull(tipusDada);
		assertTrue(tipusDada.getCodi().compareTo(codi) == 0);
		assertTrue(tipusDada.getOrdre().compareTo(ordre) == 0);
		tipusDada = service.findTipusDadaByCodi(codi);
		assertNotNull(tipusDada);
		assertTrue(tipusDada.getCodi().compareTo(codi) == 0);
		assertTrue(tipusDada.getOrdre().compareTo(ordre) == 0);
	}

	public void testUpdate() throws java.lang.Exception {
		Long ordre = new Long(1000);
		TipusDada tipusDada = service.findTipusDadaByCodi(codi);
		tipusDada.setOrdre(ordre);
		tipusDada = service.update(tipusDada);
		assertNotNull(tipusDada);
		assertTrue(tipusDada.getCodi().compareTo(codi) == 0);
		assertTrue(tipusDada.getOrdre().compareTo(ordre) == 0);
		tipusDada = service.findTipusDadaByCodi(codi);
		assertNotNull(tipusDada);
		assertTrue(tipusDada.getCodi().compareTo(codi) == 0);
		assertTrue(tipusDada.getOrdre().compareTo(ordre) == 0);		
	}

	public void testFindTipusDadesByCodi() throws java.lang.Exception {
		Collection tipusDades = service.findTipusDadesByCodi("T%"); //$NON-NLS-1$
		assertTrue(tipusDades.size() > 0);
		Iterator iterator = tipusDades.iterator();
		while(iterator.hasNext()){
			TipusDada tipusDada = (TipusDada) iterator.next();
			assertTrue(tipusDada.getCodi().startsWith("T")); //$NON-NLS-1$
		}
	}
	
	public void testDelete()	throws java.lang.Exception {		
		TipusDada tipusDada = service.findTipusDadaByCodi(codi);
		assertNotNull(tipusDada);
		service.delete(tipusDada);
		tipusDada = service.findTipusDadaByCodi(codi);
		assertNull(tipusDada);
	}
}
