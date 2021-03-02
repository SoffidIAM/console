package es.caib.seycon.ng.test;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.soffid.iam.api.Tenant;
import com.soffid.iam.service.TenantService;
import com.soffid.iam.service.impl.tenant.TenantDataManager;
import com.soffid.iam.service.impl.tenant.TenantExporter;
import com.soffid.iam.service.impl.tenant.TenantImporter;
import com.soffid.iam.service.impl.tenant.TenantRemover;
import com.soffid.iam.utils.Security;

public class TenantTest extends AbstractTest
{

	public void testExport() throws Exception
	{
		com.soffid.iam.ServiceLocator.instance().init("testBeanRefFactory.xml", "beanRefFactory");

		TenantDataManager.setDebug(true);
		Security.nestedLogin("Test", Security.ALL_PERMISSIONS);
		try {
			TenantService tenantService = com.soffid.iam.ServiceLocator.instance().getTenantService();
			
			Tenant t = tenantService.getMasterTenant();
			File f = File.createTempFile("export", "dump");
			FileOutputStream out = new FileOutputStream(f);
			TenantExporter te = new TenantExporter();
			te.setIgnoreFailures(true);
			te.export(t, out);
			out.close();
			f.delete();
		} finally {
			Security.nestedLogoff();
		}
	}

	public void testImport() throws Exception
	{
		com.soffid.iam.ServiceLocator.instance().init("testBeanRefFactory.xml", "beanRefFactory");
		TenantDataManager.setDebug(true);

		Security.nestedLogin("Test", Security.ALL_PERMISSIONS);
		try {
			TenantService tenantService = com.soffid.iam.ServiceLocator.instance().getTenantService();
			
			Tenant t = tenantService.getMasterTenant();
			File f = File.createTempFile("export", "dump");
			FileOutputStream out = new FileOutputStream(f);
			TenantExporter te = new TenantExporter();
			te.setIgnoreFailures(true);
			te.export(t, out);
			out.close();
			TenantImporter ti = new TenantImporter();
			FileInputStream in = new FileInputStream(f);
			Tenant t2 = ti.importTenant(in);
			in.close();
			f.delete();
			System.out.println ("Created "+t2);
		} finally {
			Security.nestedLogoff();
		}
	}

	public void testRemove() throws Exception
	{
		com.soffid.iam.ServiceLocator.instance().init("testBeanRefFactory.xml", "beanRefFactory");
		TenantDataManager.setDebug(true);

		Security.nestedLogin("Test", Security.ALL_PERMISSIONS);
		try {
			TenantService tenantService = com.soffid.iam.ServiceLocator.instance().getTenantService();
			
			Tenant t = new Tenant();
			t.setName("test");
			t.setDescription("Test tenant");
			t.setEnabled(true);
			t = tenantService.create(t);
			
			TenantRemover remover = new TenantRemover();
			remover.setIgnoreFailures(true);
			remover.remove(t);
		} finally {
			Security.nestedLogoff();
		}
	}
}

