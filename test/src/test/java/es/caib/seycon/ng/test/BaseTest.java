package es.caib.seycon.ng.test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.util.Collection;

import javax.naming.Context;
import javax.rmi.PortableRemoteObject;

import es.caib.signatura.api.Signature;
import junit.framework.TestCase;

public class BaseTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
/*
		Context ctx = InitialContextFactory.getInitialContext();
		Object obj = ctx.lookup(DrugstoreUserEJBHome.JNDI_NAME);
		DrugstoreUserEJBHome home = (DrugstoreUserEJBHome) PortableRemoteObject
				.narrow(obj, DrugstoreUserEJBHome.class);
		service = home.create();
*/
	}

	/*
	 * Test method for
	 * 'es.caib.seycon.ng.servei.LlistesDeCorreuServiceImpl.handleGetLlistesDeCorreu()'
	 */
	public void testRegisterDrugstoreUser() throws RemoteException {
		/*ProvaFirma.txt
		byte[] peticio = { 1, 2, 3 };
		es.caib.signatura.api.Signer signer = null;

		ObjectInputStream signatureStream = null;
		InputStream in = null;
		Signature signatureData = null;
		boolean result = false;
		try {
			signatureStream = new ObjectInputStream(new FileInputStream(
					"c:\\ProvaFirma.fir"));
			signatureData = (Signature) signatureStream.readObject();
			signatureStream.close();		
			service.registerDrugstoreUser(peticio, signatureData);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		// Collection col = service.getLlistesDeCorreu();
	}

}
