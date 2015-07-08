package es.caib.seycon.ng.test;

import com.soffid.iam.doc.exception.DocumentBeanException;
import com.soffid.iam.doc.service.DocumentService;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.utils.Security;

public class DocumentServiceTest extends AbstractTest
{

	public void testDocumentService () throws InternalErrorException, DocumentBeanException
	{
		Security.nestedLogin("Test", new String[] {Security.AUTO_AUTHORIZATION_ALL});
		try {
			DocumentService ds1 = (DocumentService) context.getBean(DocumentService.SERVICE_NAME);
			DocumentService ds2 = (DocumentService) context.getBean(DocumentService.SERVICE_NAME);
			ds1.createDocument("text/plain", "test1", "soffid");
			ds2.createDocument("text/plain", "test1", "soffid");
			ds1.openUploadTransfer();
			ds2.openUploadTransfer();
			ds1.endUploadTransfer();
			ds2.endUploadTransfer();
			ds1.closeDocument();
			ds2.closeDocument();
		} finally {
			Security.nestedLogoff();
		}
	}

}
