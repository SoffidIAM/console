package es.caib.seycon.ng.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import com.soffid.iam.doc.exception.DocumentBeanException;
import com.soffid.iam.doc.service.DocumentService;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;

public class DocumentServiceTest extends AbstractTest
{

	public void testDocumentService () throws InternalErrorException, DocumentBeanException, IOException
	{
		byte [] sampleData = new byte[32000];
		new Random().nextBytes(sampleData);
		com.soffid.iam.ServiceLocator.instance().init("testBeanRefFactory.xml", "beanRefFactory");
		Security.nestedLogin("Test", new String[] {Security.AUTO_AUTHORIZATION_ALL});
		try {
			DocumentService ds1 = (DocumentService) context.getBean(DocumentService.SERVICE_NAME);
			DocumentService ds2 = (DocumentService) context.getBean(DocumentService.SERVICE_NAME);
			ds1.createDocument("text/plain", "test1", "soffid");
			ds2.createDocument("text/plain", "test1", "soffid");
			ds1.openUploadTransfer();
			ds2.openUploadTransfer();
			ds1.nextUploadPackage(sampleData, sampleData.length);
			ds2.nextUploadPackage(sampleData, sampleData.length);
			ds1.endUploadTransfer();
			ds2.endUploadTransfer();
			ds1.closeDocument();
			ds2.closeDocument();
			
			File f = File.createTempFile("doc-dump-", ".zip");
			System.out.println("Dumping to "+f.getPath());
			DocumentService ds3 = (DocumentService) context.getBean(DocumentService.SERVICE_NAME);
			FileOutputStream out = new FileOutputStream(f);
			ds3.exportDocuments(out);
			out.close();
			System.out.println("Restoring from "+f.getPath());
			ds3.importDocuments(new FileInputStream(f));
		} finally {
			Security.nestedLogoff();
		}
	}

}
