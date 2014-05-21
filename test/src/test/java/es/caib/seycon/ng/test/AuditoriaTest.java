package es.caib.seycon.ng.test;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.naming.Context;
import javax.rmi.PortableRemoteObject;

import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.servei.ejb.AuditoriaService;
import es.caib.seycon.ng.servei.ejb.AuditoriaServiceHome;
import junit.framework.Assert;
import junit.framework.TestCase;

public class AuditoriaTest extends TestCase {

	private AuditoriaService service;
	
	protected void setUp() throws Exception {
		try{
		super.setUp();
		Context ctx = InitialContextFactory.getInitialContext();
		Object obj = ctx.lookup(AuditoriaServiceHome.JNDI_NAME);
		AuditoriaServiceHome home = (AuditoriaServiceHome) PortableRemoteObject.narrow(obj, AuditoriaServiceHome.class);
		service = home.create();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
/*
	public void testFindAuditoriesByCriteri() throws RemoteException {
		
		Collection auditories = service.findAuditoriesByCriteri("=15/11/2007",
				null, "PASSWORD", "");
		String dataString = "15/11/2007";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Assert.assertTrue(auditories.size() > 0);
		Iterator iterator = auditories.iterator();
		while(iterator.hasNext()){
			Auditoria auditoria = (Auditoria) iterator.next();
			String objecte = auditoria.getObjecte();
			assertTrue(objecte.compareTo("PASSWORD") == 0);
			try{
				Date currentDate = dateFormat.parse(auditoria.getData());
				assertTrue(auditoria.getData().compareTo(dataString) == 0);
			}catch(Exception e){
				e.printStackTrace();
				fail();
			}
		}
		
		auditories = service.findAuditoriesByCriteri(">15/11/2007",
				null, "PASSWORD", "");
		dataString = "15/11/2007";
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Assert.assertTrue(auditories.size() > 0);
		iterator = auditories.iterator();
		while(iterator.hasNext()){
			Auditoria auditoria = (Auditoria) iterator.next();
			String objecte = auditoria.getObjecte();
			assertTrue(objecte.compareTo("PASSWORD") == 0);
			try{
				Date currentDate = dateFormat.parse(auditoria.getData());
				Date rightDate = dateFormat.parse(dataString);			
				assertTrue(currentDate.after(rightDate));
			}catch(Exception e){
				e.printStackTrace();
				fail();
			}
		}
		
		auditories = service.findAuditoriesByCriteri("<20/05/2002",
				null, "PASSWORD", "u81%");
		dataString = "20/05/2002";
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Assert.assertTrue(auditories.size() > 0);
		iterator = auditories.iterator();
		while(iterator.hasNext()){
			Auditoria auditoria = (Auditoria) iterator.next();
			String objecte = auditoria.getObjecte();
			assertTrue(objecte.compareTo("PASSWORD") == 0);
			try{
				Date currentDate = dateFormat.parse(auditoria.getData());
				Date rightDate = dateFormat.parse(dataString);			
				assertTrue(currentDate.before(rightDate));
			}catch(Exception e){
				e.printStackTrace();
				fail();
			}
		}
		
		auditories = service.findAuditoriesByCriteri("<20/05/2002",
				"", "SC_USUARI%", "u81%");
		dataString = "20/05/2007";
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Assert.assertTrue(auditories.size() > 0);
		iterator = auditories.iterator();
		while(iterator.hasNext()){
			Auditoria auditoria = (Auditoria) iterator.next();
			String objecte = auditoria.getObjecte();
			assertTrue(objecte.startsWith("SC_USUARI"));
			try{
				Date currentDate = dateFormat.parse(auditoria.getData());
				Date rightDate = dateFormat.parse(dataString);			
				assertTrue(currentDate.before(rightDate));
			}catch(Exception e){
				e.printStackTrace();
				fail();
			}
		}
		
		auditories = service.findAuditoriesByCriteri("<20/05/2002",
				null, "", "u81642");
		dataString = "20/05/2007";
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Assert.assertTrue(auditories.size() > 0);
		iterator = auditories.iterator();
		while(iterator.hasNext()){
			Auditoria auditoria = (Auditoria) iterator.next();
			assertTrue(auditoria.getUsuariAuditat().compareTo("u81642") == 0);
			try{
				Date currentDate = dateFormat.parse(auditoria.getData());
				Date rightDate = dateFormat.parse(dataString);			
				assertTrue(currentDate.before(rightDate));
			}catch(Exception e){
				e.printStackTrace();
				fail();
			}
		}
		
		auditories = service.findAuditoriesByCriteri("<20/05/2002",
				"u101341", null, "u81642");
		dataString = "20/05/2007";
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Assert.assertTrue(auditories.size() > 0);
		iterator = auditories.iterator();
		while(iterator.hasNext()){
			Auditoria auditoria = (Auditoria) iterator.next();
			assertTrue(auditoria.getAutor().compareTo("u101341") == 0);
			try{
				Date currentDate = dateFormat.parse(auditoria.getData());
				Date rightDate = dateFormat.parse(dataString);			
				assertTrue(currentDate.before(rightDate));
			}catch(Exception e){
				e.printStackTrace();
				fail();
			}
		}		
		
	}*/
}