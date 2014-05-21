package es.caib.seycon.ng.test;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import es.caib.seycon.ng.comu.Aplicacio;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.servei.ejb.GrupService;
import es.caib.seycon.ng.servei.ejb.GrupServiceHome;
import es.caib.seycon.ng.servei.workflow.ejb.AltaBaixaUsuariService;
import es.caib.seycon.ng.servei.workflow.ejb.AltaBaixaUsuariServiceHome;
import es.caib.seycon.ng.servei.workflow.ejb.AutoritzacionsServiceHome;
import es.caib.seycon.ng.servei.workflow.ejb.InformacioAutoritzacioService;
import es.caib.seycon.ng.servei.workflow.ejb.InformacioAutoritzacioServiceHome;
import junit.framework.Assert;
import junit.framework.TestCase;
//import es.caib.signatura.api.Signature;
//import es.caib.signatura.impl.CMSSignature;

public class AltaBaixaUsuariTest extends TestCase {

	private AltaBaixaUsuariService service;
	private GrupService grupService;
	private InformacioAutoritzacioService informacioService;

	String codiUsuariCreat = null;

	/*protected void setUp() throws Exception {
		try {
			super.setUp();

			Properties properties = new Properties();
			properties.setProperty("java.naming.factory.initial",
					"org.jboss.naming.NamingContextFactory");
			properties.setProperty("java.naming.provider.url",
					"jnp://epreinf41.caib.es:1099/invoker/ReadOnlyJNDIFactory");
			properties.setProperty("java.naming.factory.url.pkgs",
					"org.jboss.naming:org.jnp.interfaces");
			Context ctx = new InitialContext(properties);

			
			 * Properties properties = null; properties = new Properties();
			 * properties.load(new FileInputStream("jndi.properties"));
			 * System.getProperties().put("java.security.auth.login.config",
			 * "security.conf"); Context ctx = new InitialContext(properties);
			 * ClientLogin login = new ClientLogin("u89559", "pass");
			 * login.login();
			 

			Object obj = ctx
					.lookup("seycon-3.0-SNAPSHOT/ejb/es.caib.seycon.ng.servei.workflow.AltaBaixaUsuariService");// AltaBaixaUsuariServiceHome.JNDI_NAME);
			AltaBaixaUsuariServiceHome home = (AltaBaixaUsuariServiceHome) PortableRemoteObject
					.narrow(obj, AltaBaixaUsuariServiceHome.class);
			service = home.create();

			Object obj2 = ctx
					.lookup(InformacioAutoritzacioServiceHome.JNDI_NAME);
			InformacioAutoritzacioServiceHome autoritzacionsServiceHome = (InformacioAutoritzacioServiceHome) PortableRemoteObject
					.narrow(obj2, InformacioAutoritzacioServiceHome.class);
			informacioService = autoritzacionsServiceHome.create();

			Object obj3 = ctx.lookup(GrupServiceHome.JNDI_NAME);
			GrupServiceHome grupServiceHome = (GrupServiceHome) PortableRemoteObject
					.narrow(obj3, GrupServiceHome.class);
			grupService = grupServiceHome.create();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/

	protected void setUp() throws Exception {
		super.setUp();
		
		try{
			Properties properties = null;
			properties = new Properties();
			properties.load(new FileInputStream("jndi.properties")); //$NON-NLS-1$
			System.getProperties().put("java.security.auth.login.config", //$NON-NLS-1$
					"security.conf"); //$NON-NLS-1$
			Context ctx = new InitialContext(properties);
			/*ClientLogin login = new ClientLogin("u91940", "kgptmv5");
			login.login();*/
			
			properties.list(System.out);
			Object obj = ctx
			.lookup("seycon-3.0.8-SNAPSHOT/ejb/es.caib.seycon.ng.servei.workflow.AltaBaixaUsuariService");// AltaBaixaUsuariServiceHome.JNDI_NAME); //$NON-NLS-1$
			AltaBaixaUsuariServiceHome home = (AltaBaixaUsuariServiceHome) PortableRemoteObject
			.narrow(obj, AltaBaixaUsuariServiceHome.class);
			service = home.create();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}	
		
	}
	
	public void testBaixaUsuari()
	{
		try{
			Usuari usuari = service.baixaUsuari("u81501"); //$NON-NLS-1$
		}catch(RemoteException e)
		{ 
			System.out.println("Error en la baja");  //$NON-NLS-1$
		}
	}
	
	
	/*
	public void testAltaBaixaUsuari() throws java.lang.Exception {
		try{
		String peticio = "";
			FileInputStream peticioXML = new FileInputStream("peticio.xml");

			int data = 0;
			while ((data = peticioXML.read()) > -1) {
				peticio = peticio + ((char) data);
			}
		ObjectInputStream signatureStream = null;
		Signature signatureData = null;
			signatureStream = new ObjectInputStream(new FileInputStream(
					"peticio.xml.firma"));
			signatureData = (Signature) signatureStream.readObject();
			signatureStream.close();

		Usuari usuari = service.altaUsuari(peticio.getBytes(), signatureData);
		codiUsuariCreat = usuari.getCodi();
		Collection usuaris = service.findUsuariByDadesUsuari(codiUsuariCreat,
				"%", "%", "%", "%");
		usuari = (Usuari) usuaris.iterator().next();
		String password = service.setPasswordInicialToUsuari(usuari.getCodi());
		String dni = usuari.getNIF();
		String codiUsuari = usuari.getCodi();

		usuaris = service.findUsuariByDadesUsuari(codiUsuari, dni, "%", "%",
				"%");
		assertTrue(usuaris.size() == 1);
		Iterator iterator = usuaris.iterator();
		usuari = (Usuari) iterator.next();
		Assert.assertNotNull(usuari);

		service.baixaUsuari(codiUsuari);

		usuaris = service.findUsuariByDadesUsuari(dni, "%", "%", "%");
		Assert.assertTrue(usuaris.size() == 1);
		iterator = usuaris.iterator();
		usuari = (Usuari) iterator.next();
		Assert.assertNotNull(usuari);
		Collection rols = informacioService.findRolsByCodiUsuari(codiUsuari);
		Assert.assertEquals(1, rols.size());
		iterator = rols.iterator();
		Rol rol = (Rol) iterator.next();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
*/
}
