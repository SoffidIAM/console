package es.caib.seycon.ng.test;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.hibernate.EntityMode;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.type.Type;

import com.soffid.iam.api.Tenant;
import com.soffid.iam.model.UserEntityImpl;
import com.soffid.iam.service.TenantService;
import com.soffid.iam.service.impl.tenant.TenantExporter;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.PoliticaContrasenya;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.AutoritzacioService;

public class TenantTest extends AbstractTest
{

	public void testExport() throws Exception
	{
		com.soffid.iam.ServiceLocator.instance().init("testBeanRefFactory.xml", "beanRefFactory");

		Security.nestedLogin("Test", Security.ALL_PERMISSIONS);
		try {
			TenantService tenantService = com.soffid.iam.ServiceLocator.instance().getTenantService();
			
			Tenant t = tenantService.getMasterTenant();
			File f = File.createTempFile("export", "dump");
			FileOutputStream out = new FileOutputStream(f);
			TenantExporter te = new TenantExporter();
			te.setIgnoreFailures(true);
			te.export(t.getId(), out);
			out.close();
			f.delete();
		} finally {
			Security.nestedLogoff();
		}
	}


}

