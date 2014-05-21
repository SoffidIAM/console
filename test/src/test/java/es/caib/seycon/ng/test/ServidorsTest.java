package es.caib.seycon.ng.test;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import junit.framework.Assert;
import junit.framework.TestCase;
import es.caib.seycon.ng.comu.Maquina;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.servei.XarxaService;
import es.caib.seycon.ng.servei.workflow.ejb.ServidorsService;
import es.caib.seycon.ng.servei.workflow.ejb.ServidorsServiceHome;

public class ServidorsTest extends TestCase {

	private ServidorsService service;

	protected void setUp() throws Exception {
		super.setUp();
		Properties properties = new Properties();
		properties.setProperty( "java.naming.factory.initial",	"org.jboss.naming.HttpNamingContextFactory" ); //$NON-NLS-1$ //$NON-NLS-2$
		properties.setProperty( "java.naming.provider.url", "http://localhost:8080/invoker/ReadOnlyJNDIFactory" ); //$NON-NLS-1$ //$NON-NLS-2$
		properties.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming" ); //$NON-NLS-1$ //$NON-NLS-2$
		Context ctx = new InitialContext( properties );
		
		Object obj = ctx.lookup("seycon-3.0-SNAPSHOT/ejb/es.caib.seycon.ng.servei.workflow.ServidorsService"); //$NON-NLS-1$

		ServidorsServiceHome servidorsServiceHome = (ServidorsServiceHome) 
				PortableRemoteObject.narrow(obj, 
				ServidorsServiceHome.class);
		service = servidorsServiceHome.create();
	}
	public void testGetServidorsCorreu()
			throws java.lang.Exception {
		Collection maquines = service.getServidorsCorreu();
		assertTrue(maquines.size() > 0);
	}

	public void testGetServidorsPerfil()
			throws java.lang.Exception {
		Collection maquines = service.getServidorsPerfil();
		assertTrue(maquines.size() > 0);
	}

	public void testGetServidorsHome()
			throws java.lang.Exception {
		Collection maquines = service.getServidorsHome();
		assertTrue(maquines.size() > 0);
	}
	
	public void testSendMail() throws java.lang.Exception {
		try{
		Collection maquines = service.getServidorsHome();
		service.sendMail("u89559", "cap�alera", "el contingut"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
