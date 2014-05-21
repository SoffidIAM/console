package es.caib.seycon.ng.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import junit.framework.TestCase;
import es.caib.bpm.beans.exception.DocumentBeanException;
import es.caib.bpm.beans.home.DocumentHome; //import es.caib.bpm.beans.login.ClientLogin;
import es.caib.bpm.beans.remote.Document;
import es.caib.bpm.nas.exception.NASException;

public class NASTest extends TestCase {

/*	public void testCrearDocumento() throws Exception {
		Document documento = null;
		FileInputStream inputStream = null;
		byte[] buffer = new byte[10240];
		int leidos = 0;

		try {
			inputStream = new FileInputStream("resources/path.txt");

			documento = this.documentHome.createNew("application/text",
					"path.txt", "BPM", 2007);

			
			documento.openUploadTransfer();
			
			while ((leidos = inputStream.read(buffer)) != -1) {
				documento.nextUploadPackage(buffer, leidos);
			}
			
			documento.endUploadTransfer();

			//documento.addRole("Supervisor");

			inputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			inputStream.close();
			throw ex;
		}
	}
*/
	/**
	 * Recupera un archivo del repositorio a partir del hash.
	 * 
	 * @throws Exception
	 */
	/*
	public void testDescargarDocumento() throws Exception {
		Document documento = null;
		FileOutputStream outputStream = null;
		byte[] leidos = null;

		try {
			outputStream = new FileOutputStream(
					"resources/sampleTraido.txt");

			documento = this.documentHome.create("25",
					"685fcaa50e282a91a733aac592f3f480581cb5f4");

			documento.openDownloadTransfer();

			while ((leidos = documento.nextDownloadPackage(10240)) != null) {
				outputStream.write(leidos);
			}

			documento.endDownloadTransfer();
		} catch (Exception ex) {
			throw ex;
		} finally {
			outputStream.close();
		}
	}
*/
	/**
	 * Agrega una firma digital al documento.
	 * 
	 * @throws CreateException
	 * @throws IOException
	 * @throws NASException
	 * 
	 */
	/*
	public void testAgregarFirmaDigital() throws CreateException, IOException,
			NASException {
		Document documento = null;
		ByteArrayOutputStream streamSalida = null;
		FileInputStream stream = null;
		byte[] buffer = new byte[10240];
		int leidos = 0;
		documento = this.documentHome.create("14",
				"685fcaa50e282a91a733aac592f3f480581cb5f4");

		streamSalida = new ByteArrayOutputStream();
		stream = new FileInputStream("resources/path.sign");

		while ((leidos = stream.read(buffer)) != -1) {
			streamSalida.write(buffer, 0, leidos);
		}
		documento.addSign('N', streamSalida.toByteArray());
	}*/

	/**
	 * Consulta las firmas de un documento.
	 * 
	 * @throws CreateException
	 * @throws IOException
	 * @throws NASException
	 */
	/*
	public void testConsultarFirmas() throws CreateException, IOException,
			NASException {
		Document documento = null;
		File firma = new File("resources/firma.sign");
		List firmas = null;

		documento = this.documentHome.create("14",
				"685fcaa50e282a91a733aac592f3f480581cb5f4");

		firmas = documento.getSigns();

		TestCase.assertTrue(firmas.size() == 1);
	}
*/
	/**
	 * Realiza la validacion de la firma contra el documento.
	 * 
	 * @throws CreateException
	 * @throws IOException
	 * @throws NASException
	 * @throws DocumentBeanException
	 * 
	 *//*
	public void testValidarFirma() throws CreateException, IOException,
			NASException, DocumentBeanException {
		Document documento = null;
		List firmas = null;
		documento = this.documentHome.create("14",
				"685fcaa50e282a91a733aac592f3f480581cb5f4");

		firmas = documento.getSigns();

		TestCase.assertTrue(documento.verifySign(((BpmSign) firmas.get(0))
				.getSign()));
	}*/

	/**
	 * Valida que el documento tenga loggeos de acceso.
	 * 
	 * @throws RemoteException
	 * @throws CreateException
	 *//*
	public void testAccessLog() throws RemoteException, CreateException {
		Document documento = null;

		documento = this.documentHome.create("14",
				"685fcaa50e282a91a733aac592f3f480581cb5f4");

		List logs = documento.getAccessLog(null);

		TestCase.assertTrue(logs != null && logs.size() > 0);
	}*/

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	/*
	protected void setUp() throws Exception {
		try{
		Properties properties = null;
		properties = new Properties();
		properties.load(new FileInputStream("jndi.properties"));

		System.getProperties().put("java.security.auth.login.config",
				"security.conf");

		this.context = new InitialContext(properties);

		login = new ClientLogin("u89559", "pass");

		login.login();

		Object o = context.lookup("DocumentBean");

		TestCase.assertTrue(o != null);

		documentHome = (DocumentHome) PortableRemoteObject.narrow(o,
				DocumentHome.class);
		
		TestCase.assertTrue(documentHome != null);
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
*/
	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		this.login.logout();
	}

	public static void main(String[] args) {
/*		NASTest nastest = new NASTest();

		try {
			nastest.setUp();

			nastest.testAgregarFirmaDigital();

			nastest.tearDown();
		} catch (Exception ex) {
			ex.printStackTrace();
		}*/
	}

	/** El modulo de login del cliente */
	private ClientLogin login= null;
	/** La capa de negocio */
	//private DocumentHome documentHome = null;
	/** El contexto */
	private Context context = null;
}
