package es.caib.seycon.ng.test;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.dialect.HSQLDialect;
import org.springframework.beans.factory.BeanFactory;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Challenge;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.User;
import com.soffid.iam.model.CustomDialect;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;

public class LinOTPTest {
	public static void main (String [] args) throws InternalErrorException
	{
		System.out.println(System.getProperty("java.home"));
		ConfigurationCache.setProperty("soffid.linotp.enabled", "true");
		ConfigurationCache.setProperty("soffid.linotp.user", "admin");
		String pass = new Password("changeit").toString();
		ConfigurationCache.setProperty("soffid.linotp.password", pass);
		ConfigurationCache.setProperty("soffid.linotp.server", "https://localhost:1443");
		
		System.setProperty("catalina.home", "target/server");

		CustomDialect.dialectClass = HSQLDialect.class;
		
		BeanFactory ctx = org.springframework.context.access.ContextSingletonBeanFactoryLocator.getInstance("testBeanRefFactory.xml")
				.useBeanFactory("beanRefFactory")
				.getFactory();
		
		
		SessionFactory sf = (SessionFactory) ctx.getBean("sessionFactory");
		Session session = sf.openSession();

		ServiceLocator serviceLocator = com.soffid.iam.ServiceLocator.instance();
		serviceLocator.init("testBeanRefFactory.xml", "beanRefFactory");

		ServiceLocator.instance().getTenantService().getMasterTenant();
		Security.nestedLogin("Test", Security.ALL_PERMISSIONS);
		try {

			serviceLocator.getApplicationBootService().consoleBoot();
			
			User u = new User ();
			u.setUserName("ppig");
			u.setFirstName("Peppa");
			u.setLastName("Pig");
			u.setActive(true);
			u.setPrimaryGroup("enterprise");
			u.setHomeServer("null");
			u.setMailServer("null");
			u.setUserType("I");
			u.setProfileServer("null");
			u = serviceLocator.getUserService().create(u);
			
			Challenge challenge = new Challenge ();
			challenge.setUser( u );
			serviceLocator.getOTPValidationService().selectToken(challenge);
			
			session.close();
		} finally {
			Security.nestedLogoff();
		}
	}
}

