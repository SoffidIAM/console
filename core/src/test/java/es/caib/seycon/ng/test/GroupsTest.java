package es.caib.seycon.ng.test;



import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.UserData;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;

public class GroupsTest extends AbstractTest
{

	public void testAccountAttribute() throws InternalErrorException, AccountAlreadyExistsException
	{
		com.soffid.iam.ServiceLocator.instance().init("testBeanRefFactory.xml", "beanRefFactory");

		Security.nestedLogin("Test", Security.ALL_PERMISSIONS);
		try {
			for (GroupUser ug: ServiceLocator.instance().getGroupService().findUsersBelongtoGroupByGroupName("admingroup"))
			{
				System.out.println (ug.toString());
			}
		} finally {
			Security.nestedLogoff();
		}
	}


}
