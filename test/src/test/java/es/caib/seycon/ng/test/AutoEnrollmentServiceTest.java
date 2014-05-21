package es.caib.seycon.ng.test;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.xml.rpc.ServiceException;

import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.servei.ejb.UsuariService;
import es.caib.seycon.ng.servei.ejb.UsuariServiceHome;
import es.caib.seycon.ng.webservice.client.AutoEnrollmentServiceWSDelegator;
import es.caib.seycon.ng.webservice.client.AutoEnrollmentServiceWSDelegatorServiceLocator;
import junit.framework.TestCase;

public class AutoEnrollmentServiceTest extends TestCase {

	protected String actualPassword="password1"; //$NON-NLS-1$
	protected String oldPassword="password2"; //$NON-NLS-1$
	
	protected void setUp() throws Exception {
		super.setUp();
		
		try{
			//Properties properties = null;
			//properties = new Properties();
			//properties.load(new FileInputStream("jndi.properties"));
			//System.getProperties().put("java.security.auth.login.config","security.conf");
			//Context ctx = new InitialContext(properties);
			//ClientLogin login = new ClientLogin("u91940", "kgptmv2");
			//login.login();
			
			/**Object obj = ctx.lookup(UsuariServiceHome.JNDI_NAME);
			UsuariServiceHome usuariHome = (UsuariServiceHome) PortableRemoteObject
					.narrow(obj, UsuariServiceHome.class);
			service = usuariHome.create();
			**/
		}catch(Exception e){
			e.printStackTrace();
		}	
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Testeja la funció donar d'alta un usuari anònim
	 *
	 */
	public void testAlta() {
		//Matriu de paràmetres a combinar per a la prova
		
		String params [][] = new String [][] {
				{null,"","Pere","Joseph"},										//nom //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				{null,"","Joseph","Rodríguez"},					//llinatges //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				{null,"","Rodriguez","Pere"},					//llinatges //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				{null,"","seguretat4@dgtic.caib.es","seguretat4@dgtic.caib.es"},	//correuElectronic //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				{null,"","http://tticlin2.testl.ab:18080/seycon-web","http://tticlin2.testl.ab:18080/seycon-web"}	//correuElectronic //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		};

		//Anem a buscar el webservice a la URL especificada
		AutoEnrollmentServiceWSDelegatorServiceLocator locator=new AutoEnrollmentServiceWSDelegatorServiceLocator();
		AutoEnrollmentServiceWSDelegator service=null;
		try {
			service = locator.getAutoEnrollmentService(new URL("http://tticlin2.test.lab:18080/seycon-webservice/services/AutoEnrollmentService")); //$NON-NLS-1$
		} catch (Throwable e1) {
			e1.printStackTrace();
			fail(e1.getMessage());
		}
		
		//Provem amb els paràmetres
		for(int i=2;i<params.length;i++){
				boolean result=true;
				Throwable ex=null;
				
				//crida al mètode alta
				try {
					service.alta(params[0][i],params[1][i],params[2][i],params[3][i],params[4][i]);
				} catch (Throwable e) {
					ex=e;
					result=false;
					
				}
				
				if(result){
					//si s'ha generat resultat per als casos on havia d'haber error, sortim amb error
					if(i==1 || i==0 || i==4){
						//ex.printStackTrace();
						fail("Resultat no esperat amb les dades correctes: "+params[0][i]+" , "+params[1][i]+" , "+params[2][i]); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						continue;
					}
					
					assertTrue((i==2 || i==3));
				}else{
					//si no s'ha generat resultat per als casos on no havia d'aber error, sortim amb error

					if( i==2  || i==3){
						ex.printStackTrace();
						//fail("Resultat no esperat amb les dades correctes: "+params[0][i]+" , "+params[1][i]+" , "+params[2][i]);
					}
					assertTrue((i==0 || i==1 || i==4));
					continue;	

				}
		}
		
		
	}
/**
	public void testAssignarPassword(){
		
		String params [][] = new String [][] {
				{null,"","Pere"},						//nom
				{null,"","Joseph Rodriguez"},			//llinatges
				{null,"",actualPassword},					//password1
				{null,"","1234"},							//PIN
				{null,"","seguretat4@dgtic.caib.es"}	//correuElectronic
		};

		AutoEnrollmentService_ServiceLocator locator=new AutoEnrollmentService_ServiceLocator();
		AutoEnrollmentService_PortType service=null;
		try {
			service = locator.getAutoEnrollmentService(new URL("http://epreinf41.caib.es/seycon-ws/services/AutoEnrollmentService?wsdl"));
		} catch (Exception e1) {
			e1.printStackTrace();
			fail(e1.getMessage());
		}
		
		
		for(int i=0;i<params.length;i++){
			boolean result=false;
			Exception ex=null;
			try{
					result=service.assignarPassword(params[4][i],params[3][i],params[2][i]);
				}catch(Exception e){
					ex=e;
				}

				if(result){
					if(i==1 || i==0){
						ex.printStackTrace();
						fail("Resultat no esperat amb les dades correctes: "+params[4][i]+" , "+params[3][i]+" , "+params[2][i]);
						continue;
					}
					
					assertTrue((i==2 || i==3));
				}else{
					if( i==2  || i==3){
						ex.printStackTrace();
						fail("Resultat no esperat amb les dades correctes: "+params[4][i]+" , "+params[3][i]+" , "+params[2][i]);
					}
					assertTrue((i==0 || i==1));
					continue;	

				}
		}	
	}


	public void testResetejarPassword() {


		
		AutoEnrollmentService_ServiceLocator locator=new AutoEnrollmentService_ServiceLocator();
		AutoEnrollmentService_PortType service=null;
		try {
			service = locator.getAutoEnrollmentService(new URL("http://epreinf41.caib.es/seycon-ws/services/AutoEnrollmentService?wsdl"));
		} catch (Exception e1) {
			e1.printStackTrace();
			fail(e1.getMessage());
		}
		
		String params [][] = new String [5][3];
		
		for(int i=0;i<params.length;i++){

			params = new String [][] {
					{null,"","Pere"},						//nom
					{null,"","Joseph Rodriguez"},			//llinatges
					{null,"",actualPassword},					//oldPassword
					{null,"",oldPassword},					//newPassword
					{null,"","seguretat4@dgtic.caib.es"}	//correuElectronic
			};
			
			boolean result=false;
			Exception ex=null;
			
			try{
					result=service.resetejarPassword(params[4][i], params[2][i], params[3][i]);
					String tempPass=oldPassword;
					oldPassword=actualPassword;
					actualPassword=tempPass;
										
				}catch(Exception e){
					ex=e;
				}
				
				if(result){
					if(i==1 || i==0){
						ex.printStackTrace();
						fail("Resultat no esperat amb les dades correctes: "+params[4][i]+" , "+params[2][i]+" , "+params[3][i]);
						continue;
					}
					
					assertTrue((i==2 || i==3));
				}else{
					if( i==2  || i==3){
						ex.printStackTrace();
						fail("Resultat no esperat amb les dades correctes: "+params[4][i]+" , "+params[2][i]+" , "+params[3][i]);
					}
					assertTrue((i==0 || i==1));
					continue;	

				}			
		}	
		
	}

	
	private UsuariService service;
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */




	/**
	 * Test method for {@link es.caib.seycon.ng.webservice.client.AutoEnrollmentServiceProxy#AutoEnrollmentServiceProxy()}.
	 */
/**
	public void testAutoEnrollmentServiceProxy() {
		


	}
**/	

	
	
}
