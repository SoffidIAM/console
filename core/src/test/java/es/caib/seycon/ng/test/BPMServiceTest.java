package es.caib.seycon.ng.test;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.bpm.service.BpmEngine;
import com.soffid.iam.doc.exception.DocumentBeanException;
import com.soffid.iam.doc.service.DocumentService;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.UserEntityDao;
import com.soffid.iam.utils.Security;

import es.caib.bpm.exception.BPMException;
import es.caib.seycon.ng.exception.InternalErrorException;

public class BPMServiceTest extends AbstractTest
{

	public void testBPMService () throws InternalErrorException, DocumentBeanException, BPMException
	{
		com.soffid.iam.ServiceLocator.instance().init("testBeanRefFactory.xml", "beanRefFactory");
		Security.nestedLogin("Test", new String[] {Security.AUTO_AUTHORIZATION_ALL});
		try {
			UserEntityDao dao = (UserEntityDao) ServiceLocator.instance().getService("userEntityDao");
			dao.query("select u from com.soffid.iam.model.UserEntity as u where u.id = currentTenant()", new Parameter[] {});
		} finally {
			Security.nestedLogoff();
		}
	}

}
