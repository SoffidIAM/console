package es.caib.seycon.ng.test;



import java.util.List;

import org.hibernate.EntityMode;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.type.Type;

import com.soffid.iam.model.UserEntityImpl;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.PoliticaContrasenya;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.AutoritzacioService;

public class PasswordTest extends AbstractTest
{

	public void testGeneratePassword() throws InternalErrorException
	{

		Security.nestedLogin("Test", new String[] { 
				Security.AUTO_AUTHORIZATION_ALL });
		try {
			PoliticaContrasenya pc = dominiSvc.findPoliticaByTipusAndDominiContrasenyas("I", "DEFAULT");
			pc.setMinMajuscules(1L);
			pc.setMinMinuscules(1L);
			pc.setMaxLongitud(10L);
			pc.setMinLongitud(6L);
			pc.setDuradaMaxima(30L);
			pc.setMaxHistoric(5L);
			pc.setExpressioRegular(null);
			pc.setMinNumeros(null);
			pc.setMinSignesPuntuacio(null);
			dominiSvc.update(pc);
			
			System.out.println("Simple password=" + usuariSvc.canviPassword("admin", "DEFAULT"));
		} finally {
			Security.nestedLogoff();
		}
	}

	public void testGenerateComplexPassword() throws InternalErrorException
	{

		Security.nestedLogin("Test", new String[] { 
				Security.AUTO_AUTHORIZATION_ALL });
		try {	
			PoliticaContrasenya pc = dominiSvc.findPoliticaByTipusAndDominiContrasenyas("I", "DEFAULT");
			pc.setMinMajuscules(1L);
			pc.setMinMinuscules(1L);
			pc.setMinNumeros(2L);
			pc.setMinSignesPuntuacio(1L);
			pc.setExpressioRegular("[A-Z].*");
			pc.setMaxLongitud(10L);
			pc.setMinLongitud(10L);
			pc.setDuradaMaxima(30L);
			pc.setMaxHistoric(5L);
			dominiSvc.update(pc);
			
			System.out.println("Complex password=" + usuariSvc.canviPassword("admin", "DEFAULT"));
		} finally {
			Security.nestedLogoff();
		}
	}
	
	public void testAdminPermissions () throws InternalErrorException
	{
		AutoritzacioService autSvc = (AutoritzacioService) context.getBean("autoritzacioService");
		String[] auts = autSvc.getUserAuthorizationsString("admin");
		System.out.println ("Authorizations");
		for (int i = 0; i < auts.length; i++)
		{
			System.out.println (">>" + auts[i]);
		}
		
	}
	
	SessionFactory sessionFactory;
	private SessionFactory getSessionFactory ()
	{
		if (sessionFactory == null)
			sessionFactory = (SessionFactory) ServiceLocator.instance().getService("sessionFactory");
		return sessionFactory;
	}

	public void testMetadata() throws InternalErrorException 
	{
		Session session = getSessionFactory().openSession();
		String entity = "com.soffid.iam.model.UserEntityImpl";
		Long id = new Long(1);
		try {
			ClassMetadata md = getSessionFactory().getClassMetadata(entity);
			if (md == null)
				throw new InternalErrorException (String.format("Unable to get metadata for entity %s",entity));
			AbstractEntityPersister aep = (AbstractEntityPersister) md;
			String[] cn = aep.getIdentifierColumnNames();
			if (cn.length != 1)
				throw new InternalErrorException (String.format("Entity %s should have one and only one primary key column", entity));
			String tableName = aep.getTableName();

			List list = session.createCriteria(UserEntityImpl.class)
							.add(Restrictions.eq("userName", "admin"))
							.list();
			Object obj = list.get(0);
			
			if (obj == null)
			{
				System.out.println ("NO EXISTE");
			}
			else
			{
				String props [] = aep.getPropertyNames();
				for (int i = 0;i < props.length; i++)
				{
					String prop = props[i];
					String col[] = aep.getPropertyColumnNames(i);
					Type type = aep.getPropertyType(prop);
					if (type.isCollectionType())
					{
						System.out.printf ("%s [COLLECTION]\n", prop);
					} 
					else if (col.length == 0)
					{
						System.out.printf ("%s  IGNORED\n", 
										prop);
					}
					else
					{
						Object val = aep.getPropertyValue(obj, i, EntityMode.POJO);
						if (type.isEntityType() && val != null)
						{
							val = session.getIdentifier(val);
						}
						System.out.printf ("%s[%s]=%s\n", 
										prop, 
										col[0], 
										val == null ? "<null>" : val.toString(),
										type.toString());
					}
				}
				System.out.println (obj);
			}
		} finally {
			session.close();
		}
		
	}

}
