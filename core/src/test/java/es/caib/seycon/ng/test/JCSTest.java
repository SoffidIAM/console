package es.caib.seycon.ng.test;



import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;
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

public class JCSTest extends AbstractTest
{

	public void testJCS() throws InternalErrorException, IOException
	{
		Properties p = new Properties ();
		p.load( getClass().getResourceAsStream("jcs.cfg"));
		JCS.setConfigProperties(p);
		
		CacheAccess<Object, Object> cache = JCS.getInstance("default");
		
		cache.put("test", "sample");
		
		System.out.println("test = "+cache.get("test"));
	}
	

}
