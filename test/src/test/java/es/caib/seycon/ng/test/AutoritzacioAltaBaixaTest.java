package es.caib.seycon.ng.test;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.naming.Context;
import javax.rmi.PortableRemoteObject;

import es.caib.seycon.ng.comu.Aplicacio;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.servei.workflow.ejb.AutoritzacioAltaBaixaServiceHome;
import es.caib.seycon.ng.servei.workflow.ejb.AutoritzacioAltaBaixaService;
import junit.framework.Assert;
import junit.framework.TestCase;

public class AutoritzacioAltaBaixaTest extends TestCase {

	private AutoritzacioAltaBaixaService service;
	
	protected void setUp() throws Exception {
		super.setUp();
		Context ctx = InitialContextFactory.getInitialContext();
		Object obj = ctx.lookup(AutoritzacioAltaBaixaServiceHome.JNDI_NAME);
		AutoritzacioAltaBaixaServiceHome home = 
			(AutoritzacioAltaBaixaServiceHome) PortableRemoteObject.narrow(obj, 
					AutoritzacioAltaBaixaServiceHome.class);
		service = home.create();
	}
	/*
	public void testGetRolAdministradorByGrup() throws RemoteException {
		String codiGrup = "SERSEG";
		Rol rol = service.getRolAdministradorByGrup(codiGrup);
		assertNotNull(rol);
	}
	
	public void testGetGrupsGestionablesByIniciador() throws RemoteException {
		String codiUsuari = "u89559";
		Collection grups = service.getGrupsGestionablesByIniciador(codiUsuari);
		Iterator iterator = grups.iterator();
		while(iterator.hasNext()){
			Grup grup = (Grup) iterator.next();
		}
	}
	*/
}