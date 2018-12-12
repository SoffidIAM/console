package es.caib.seycon.ng.test;



import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.UserData;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;

public class AccountAttributeTest extends AbstractTest
{

	public void testAccountAttribute() throws InternalErrorException, AccountAlreadyExistsException
	{
		com.soffid.iam.ServiceLocator.instance().init("testBeanRefFactory.xml", "beanRefFactory");

		Security.nestedLogin("Test", Security.ALL_PERMISSIONS);
		try {
			com.soffid.iam.api.System s = new com.soffid.iam.api.System();
			s.setName("AccountTest");
			s.setClassName("-");
			s.setPasswordsDomain("DEFAULT");
			s.setUsersDomain("DEFAULT");
			s = ServiceLocator.instance().getDispatcherService().create(s);
			
			DataType dt = new DataType();
			dt.setSystemName(s.getName());
			dt.setCode("data");
			dt.setOrder(1L);
			dt.setLabel("Data");
			dt.setType(TypeEnumeration.STRING_TYPE);
			dt = ServiceLocator.instance().getAdditionalDataService().create (dt);
			
			Account acc = new Account();
			acc.setName("test");
			acc.setSystem(s.getName());
			acc.setDescription("Test account");
			acc.setType(AccountType.SHARED);
			acc.setPasswordPolicy("I");
			acc = ServiceLocator.instance().getAccountService().createAccount(acc);
			
			UserData attribute = new UserData();
			attribute.setAccountName(acc.getName());
			attribute.setSystemName(acc.getSystem());
			attribute.setValue("****");
			attribute.setAttribute(dt.getCode());
			attribute = ServiceLocator.instance().getAccountService().createAccountAttribute(attribute );
			
			ServiceLocator.instance().getAccountService().removeAccountAttribute(attribute );
			
		} finally {
			Security.nestedLogoff();
		}
	}


}
