package es.caib.seycon.ng.test;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import junit.framework.Assert;
import junit.framework.TestCase;
import es.caib.seycon.ng.comu.Configuracio;
import es.caib.seycon.ng.servei.ejb.ConfiguracioService;
import es.caib.seycon.ng.servei.ejb.ConfiguracioServiceHome;

public class ConfiguracioTest extends TestCase {

	private ConfiguracioService service;

	protected void setUp() throws Exception {
		
			super.setUp();			
			Properties properties = new Properties();
			properties.setProperty( "java.naming.factory.initial",	"org.jboss.naming.HttpNamingContextFactory" ); //$NON-NLS-1$ //$NON-NLS-2$
			properties.setProperty( "java.naming.provider.url", "http://localhost:8080/invoker/ReadOnlyJNDIFactory" ); //$NON-NLS-1$ //$NON-NLS-2$
			properties.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming" ); //$NON-NLS-1$ //$NON-NLS-2$
			Context ctx = new InitialContext( properties );
			Object obj = ctx.lookup(ConfiguracioServiceHome.JNDI_NAME);
			ConfiguracioServiceHome ConfiguracioHome = (ConfiguracioServiceHome) PortableRemoteObject
					.narrow(obj, ConfiguracioServiceHome.class);
			service = ConfiguracioHome.create();
		
	}

	public void testGetParametres() throws Exception {
		Collection parametres = service.getParametres();
		assertTrue(parametres.size() > 0);
	}

	public void testCreate() throws Exception {
		String codi = "BASE_DE_DADES_PROVES"; //$NON-NLS-1$
		String codiXarxa = "adsl1"; //$NON-NLS-1$
		String valor = "jdni/labasededades"; //$NON-NLS-1$
		String descripcio = "una descripcio"; //$NON-NLS-1$
		Configuracio configuracio = new Configuracio();
		configuracio.setCodi(codi);
		configuracio.setValor(valor);
		configuracio.setCodiXarxa(codiXarxa);
		configuracio.setDescripcio(descripcio);
		configuracio = service.create(configuracio);
		Assert.assertNotNull(configuracio);
		Assert.assertEquals(codi, configuracio.getCodi());
		Assert.assertEquals(valor, configuracio.getValor());
		Assert.assertEquals(codiXarxa, configuracio.getCodiXarxa());
		Assert.assertEquals(descripcio, configuracio.getDescripcio());
		configuracio = service.findParametreByCodiAndCodiXarxa(codi, codiXarxa);
		Assert.assertNotNull(configuracio);
		Assert.assertEquals(codi, configuracio.getCodi());
		Assert.assertEquals(valor, configuracio.getValor());
		Assert.assertEquals(codiXarxa, configuracio.getCodiXarxa());
		Assert.assertEquals(descripcio, configuracio.getDescripcio());
	}
	
	public void testFindParametresByFiltre() throws Exception {
		
		String codiParametre = "BASE_DE%"; //$NON-NLS-1$
		String codiXarxa = "%"; //$NON-NLS-1$
		String valor = ""; //$NON-NLS-1$
		String descripcio = null;
		Collection parametres = service.findConfiguracioByFiltre(codiParametre, codiXarxa, valor, descripcio);
		Iterator iterator = parametres.iterator();
		while (iterator.hasNext()) {
			Configuracio configuracio = (Configuracio) iterator.next();
			assertTrue(configuracio.getCodi().startsWith("BASE_DE")); //$NON-NLS-1$
		}
		
		codiParametre = null;
		codiXarxa = "ad%"; //$NON-NLS-1$
		valor = "%"; //$NON-NLS-1$
		descripcio = ""; //$NON-NLS-1$
		parametres = service.findConfiguracioByFiltre(codiParametre, codiXarxa, valor, descripcio);
		iterator = parametres.iterator();
		while (iterator.hasNext()) {
			Configuracio configuracio = (Configuracio) iterator.next();
			assertTrue(configuracio.getCodiXarxa().startsWith("ad")); //$NON-NLS-1$
		}
		
		codiParametre = ""; //$NON-NLS-1$
		codiXarxa = null;
		valor = "jnd%"; //$NON-NLS-1$
		descripcio = "%"; //$NON-NLS-1$
		parametres = service.findConfiguracioByFiltre(codiParametre, codiXarxa, valor, descripcio);
		iterator = parametres.iterator();
		while (iterator.hasNext()) {
			Configuracio configuracio = (Configuracio) iterator.next();
			assertTrue(configuracio.getValor().startsWith("jnd")); //$NON-NLS-1$
		}
		
		codiParametre = "%"; //$NON-NLS-1$
		codiXarxa = ""; //$NON-NLS-1$
		valor = null;
		descripcio = "una%"; //$NON-NLS-1$
		parametres = service.findConfiguracioByFiltre(codiParametre, codiXarxa, valor, descripcio);
		iterator = parametres.iterator();
		while (iterator.hasNext()) {
			Configuracio configuracio = (Configuracio) iterator.next();
			assertTrue(configuracio.getDescripcio().startsWith("una")); //$NON-NLS-1$
		}
		
		
	}
	
	
	public void testUpdate() throws Exception {
		String codi = "BASE_DE_DADES_PROVES"; //$NON-NLS-1$
		String codiXarxa = "adsl1"; //$NON-NLS-1$
		String nouValor = "jdni/la_nova_basededades"; //$NON-NLS-1$
		Configuracio parametre = service.findParametreByCodiAndCodiXarxa(codi, codiXarxa);
		Assert.assertNotNull(parametre);
		parametre.setValor(nouValor);
		parametre = service.update(parametre);
		Assert.assertNotNull(parametre);
		Assert.assertEquals(codi, parametre.getCodi());
		Assert.assertEquals(nouValor, parametre.getValor());
		parametre = service.findParametreByCodiAndCodiXarxa(codi, codiXarxa);
		Assert.assertNotNull(parametre);
		Assert.assertEquals(codi, parametre.getCodi());
		Assert.assertEquals(nouValor, parametre.getValor());
	}
	
	public void testDelete() throws Exception {
		String codiXarxa = "adsl1"; //$NON-NLS-1$
		String codi = "BASE_DE_DADES_PROVES"; //$NON-NLS-1$
		Configuracio parametre = service.findParametreByCodiAndCodiXarxa(codi, codiXarxa);
		assertNotNull(parametre);
		service.delete(parametre);
		parametre = service.findParametreByCodiAndCodiXarxa(codi, codiXarxa);
		Assert.assertNull(parametre);
	}
}