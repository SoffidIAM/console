package es.caib.seycon.ng.test;



import com.soffid.iam.ServiceLocator;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;

public class StatsTest extends AbstractTest
{

	public void testPrugi() throws InternalErrorException, AccountAlreadyExistsException
	{
		com.soffid.iam.ServiceLocator.instance().init("testBeanRefFactory.xml", "beanRefFactory");

		Security.nestedLogin("Test", Security.ALL_PERMISSIONS);
		try {
			ServiceLocator.instance().getStatsService().purge();
		} finally {
			Security.nestedLogoff();
		}
	}


}
