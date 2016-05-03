package es.caib.seycon.ng.upload;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.mx.util.MBeanProxyExt;
import org.jboss.system.ServiceMBeanSupport;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Configuration;
import com.soffid.tools.db.persistence.XmlReader;
import com.soffid.tools.db.schema.Column;
import com.soffid.tools.db.schema.Database;
import com.soffid.tools.db.schema.Table;
import com.soffid.tools.db.updater.DBUpdater;
import com.soffid.tools.db.updater.MsSqlServerUpdater;
import com.soffid.tools.db.updater.MySqlUpdater;
import com.soffid.tools.db.updater.OracleUpdater;

import es.caib.seycon.ng.comu.Configuracio;
import es.caib.seycon.ng.exception.InternalErrorException;

import com.soffid.iam.model.identity.IdentityGeneratorBean;
import com.soffid.iam.service.ApplicationBootService;
import com.soffid.iam.service.ApplicationShutdownService;
import com.soffid.iam.service.ConfigurationService;

public class UploadService extends ServiceMBeanSupport implements UploadServiceMBean {
    int scheduledInterval = 30000;
    int maxScheduledInterval = 30000;
    int schedulerThreads = 1;
    Log log = LogFactory.getLog(UploadService.class);

    protected void startService() throws Exception {
        try {
            log.info(Messages.getString("UploadService.StartingUploadInfo")); //$NON-NLS-1$
            try {
            	log.info ("Verifying database schema");
            	updateDatabase();
            } catch (Exception e)
            {
            	log.warn("Error updating database schema", e);
            }
            try {
            	com.soffid.iam.ServiceLocator.instance().getUserService();
            } catch (Exception e)  {
            	final DeployerServiceMBean soffidDeployer = (DeployerServiceMBean) MBeanProxyExt.
            			create(DeployerServiceMBean.class, "com.soffid.iam:name=deployer") ;
            	if (! soffidDeployer.isFailSafe())
            	{
            		soffidDeployer.setFailSafe(true);
            		new Thread (new Runnable() {
						
						public void run() {
							try {
								Thread.sleep(5000);
							} catch (InterruptedException e1) {
							}
							log.info("Redeploying in fail-safe mode");
							try {
								soffidDeployer.redeploy();
							} catch (Exception e) {
								log.warn ("Error deploying in fail-safe mode", e);
							}
						}
					}).start();
            		throw new Exception ("Failed to create spring context", e);
            	}
            }
            
            Thread th = new Thread( new Runnable() {
				
				public void run() {
					uploadComponents();
				}
			});
            
            
            Map beans = com.soffid.iam.ServiceLocator.instance().getContext().
            		getBeansOfType(ApplicationBootService.class);
            
            th.start ();

            for ( Object service: beans.keySet())
            {
            	log.info ("Executing startup bean: " + service);
            	
            	((ApplicationBootService) beans.get(service)).consoleBoot();
            }
            
            log.info(Messages.getString("UploadService.StartedUploadInfo")); //$NON-NLS-1$
        } catch (Throwable e) {
            log.warn(Messages.getString("UploadService.UploadFileError"), e); //$NON-NLS-1$
        }
    }

	private void uploadComponents() {
		try {
			uploadComponentCurrent("com.soffid.iam.console", "iam-common", "jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			uploadComponentCurrent("com.soffid.iam.console", "iam-core", "jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			uploadComponentCurrent("com.soffid.iam.console", "dbtools", "jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			uploadComponentCurrent("org.jbpm.jbpm3", "jbpm-jpdl", "jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			uploadComponentCurrent("es.caib.signatura.valcert", "valcert-client-axis", "jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			uploadComponentCurrent("es.caib.signatura.valcert", "valcert-client-axis", "jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			uploadComponentCurrent("jasperreports","jasperreports", "jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			uploadComponentVersion("spring", "1.2.7", "jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			uploadComponentVersion("hibernate", "3.2.6.ga", "jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			uploadComponentVersion("commons-collections", "3.1", "jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			uploadComponentVersion("commons-beanutils", "1.7.0", "jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			uploadComponentVersion("axis", "1.4", "jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			uploadComponentVersion("ehcache", "1.2.3", "jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			uploadComponentVersion("cglib", "2.1_3", "jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			uploadComponentVersion("asm", "1.5.3", "jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			uploadComponentVersion("asm-attrs", "1.5.3", "jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			uploadComponentVersion("antlr", "2.7.6", "jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			uploadComponentVersion("cron4j", "2.2.5", "jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			uploadComponentForResource("javamail", "javax/mail/Message.class"); //$NON-NLS-1$ //$NON-NLS-2$
			uploadComponentForResource("j2ee", "javax/ejb/EJBObject.class"); //$NON-NLS-1$ //$NON-NLS-2$
			uploadComponentForResource("signatura", "es/caib/signatura/api/Signature.class"); //$NON-NLS-1$ //$NON-NLS-2$
			uploadComponentForResource("dom4j", "org/dom4j/Document.class"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (Exception e) {
			log.warn ("Error uploading service", e);
		}
	}


    private void uploadComponentCurrent(String groupId, String artifactId, String packaging)
            throws IOException, InternalErrorException {

        String v = getCurrentVersion(groupId, artifactId);
        if (v == null) {
            log.warn(String.format(Messages.getString("UploadService.GuessComponentVersionError"), groupId, artifactId)); //$NON-NLS-1$
        } else {
            uploadComponentVersion(artifactId, v, packaging);
        }
    }

    private void uploadComponentForResource(String artifactId, String className)
            throws IOException, InternalErrorException {
        ClassLoader cl = this.getClass().getClassLoader();
        URL url = cl.getResource(className);
        if (url == null) {
            log.warn(String.format(Messages.getString("UploadService.LocateResourceError"), className)); //$NON-NLS-1$
            return;
        }

        String s = url.toString();
        if (!s.startsWith("jar:")) { //$NON-NLS-1$
            log.warn(String.format(Messages.getString("UploadService.UnsupportURLFormat"), s, //$NON-NLS-1$
                    className));
            return;
        }

        s = s.substring(4);
        int i = s.lastIndexOf("!"); //$NON-NLS-1$
        if (i >= 0)
            s = s.substring(0, i);
        uploadUrl(artifactId, "Unknnown", new URL(s)); //$NON-NLS-1$
    }

    private void uploadComponentVersion(String artifactId, String version, String packaging)
            throws IOException, InternalErrorException {
        ClassLoader cl = this.getClass().getClassLoader();

        String file = version == null ? artifactId + "." + packaging : artifactId + "-" + version //$NON-NLS-1$ //$NON-NLS-2$
                + "." + packaging; //$NON-NLS-1$

        URL url = cl.getResource(file);
        uploadUrl(artifactId, version, url);

    }

    private void uploadUrl(String artifactId, String version, URL url)
            throws InternalErrorException, IOException {
        if (url == null) {
            log.warn(String.format(Messages.getString("UploadService.UnableUploadFile"), artifactId)); //$NON-NLS-1$
            return;
        }
        log.info(String.format("Uploading '%1$s' from '%2$s'", artifactId, url.toString()));   //$NON-NLS-1$

        ServiceLocator locator = ServiceLocator.instance();
        ConfigurationService service = locator.getConfigurationService();
        
        // Test for already uploaded component
        if (! version.endsWith("-SNAPSHOT"))
        {
        	String currentVersion = service.getBlobVersion("component."+artifactId);
        	if (currentVersion != null && currentVersion.equals(version))
        	{
            	log.warn(String.format("Component '%1$s' is already up to date", artifactId));
        		return ;
        	}
        	
        }

        // First: Calculate resource size
        InputStream in;
        try {
        	in = url.openStream();
        } catch (IOException e) {
        	log.warn(String.format("Unable to read URL '%1$s'", url.toString()));
        	return;
        }
        int size = 0;
        while (in.read() >= 0)
            size++;
        in.close();
        // Second: Read into array
        byte data[] = new byte[size];
        in = url.openStream();
        int offset = 0;
        int read;
        do {
            read = in.read(data, offset, size - offset);
            offset += read;
        } while (read >= 0 && offset < size);

        // Third: Upload data
        String paramName = "component." + artifactId + ".version";   //$NON-NLS-1$ //$NON-NLS-2$
        Configuration config = service.findParameterByNameAndNetworkName(paramName, null);
        service.updateBlob("component." + artifactId, data, version);  //$NON-NLS-1$
        data = null;

        if (config == null) {
            config = new Configuration(paramName, version);
            service.create(config);
        } else {
            config.setValue(version);
            service.update(config);
        }
    }

    private String getCurrentVersion(String groupId, String artifactId) throws IOException {
        ClassLoader cl = this.getClass().getClassLoader();
        Properties prop = new Properties();
        InputStream in = cl.getResourceAsStream("META-INF/maven/" + groupId + "/" + artifactId //$NON-NLS-1$ //$NON-NLS-2$
                + "/pom.properties"); //$NON-NLS-1$
        if (in == null)
            return null;

        prop.load(in);

        return prop.getProperty(Messages.getString("UploadService.192")); //$NON-NLS-1$
    }

    protected void stopService() throws Exception {
        Map beans = ServiceLocator.instance().getContext().
        		getBeansOfType(ApplicationShutdownService.class);
        
        for ( Object service: beans.keySet())
        {
        	log.info ("Executing shtudown bean: " + service);
        	
        	((ApplicationShutdownService) beans.get(service)).consoleShutdown();
        }
        
    }

    protected void updateDatabase () throws Exception
    {
    	PathMatchingResourcePatternResolver rpr = new PathMatchingResourcePatternResolver(UploadService.class.getClassLoader());
//    	ApplicationContext ctx = SeyconServiceLocator.instance().getContext();

    	Database db = new Database();
    	XmlReader reader = new XmlReader();
    	parseResources(rpr, db, reader, "console-ddl.xml");
    	parseResources(rpr, db, reader, "core-ddl.xml");
    	parseResources(rpr, db, reader, "plugin-ddl.xml");
        DataSource ds = (DataSource) new InitialContext().lookup("java:jdbc/seycon");
        Connection conn = ds.getConnection();
        String type = System.getProperty("dbDriverString"); //$NON-NLS-1$
        DBUpdater updater ;
        if ("mysql".equals(type))  //$NON-NLS-1$
        {
        	updater = new MySqlUpdater();
        } else if ("oracle".equals (type)) { //$NON-NLS-1$
        	updater = new OracleUpdater();
        } else if ("sqlserver".equals(type)) {
        	updater = new MsSqlServerUpdater();
        } else {
            throw new RuntimeException("Unable to get dialect for database type ["+type+"]"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        updater.setIgnoreFailures(true);
        updater.setLog(new LoggingStream(log));
        updater.update(conn, db);
        
    	IdentityGeneratorBean identityGenerator = (IdentityGeneratorBean) ServiceLocator.instance().getService("identity-generator");
    	if (! identityGenerator.isSequenceStarted())
    	{
    		long l = getMaxIdentifier(conn, db);
    		identityGenerator.initialize(l, 100, 1);
    	}

        conn.close();
    }

	private long getMaxIdentifier(Connection connection, Database db) throws SQLException {
		long l = 1;
		for (Table t: db.tables)
		{
			for (Column c: t.columns)
			{
				if (c.primaryKey)
				{
					PreparedStatement st = connection.prepareStatement("SELECT MAX("+c.name+") FROM "+t.name);
					try
					{
						ResultSet rs = st.executeQuery();
						try
						{
							if (rs.next())
							{
								long l2 = rs.getLong(1);
								if (l2 >= l)
									l = l2+1;
							}
						}
						finally
						{
							rs.close();
						}
					}
					finally
					{
						st.close();
					}
				}
			}
		}
		return l;
	}

	private void parseResources(ResourcePatternResolver rpr, Database db,
			XmlReader reader, String path) throws IOException, Exception {
		Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);
    	while (resources.hasMoreElements())
    	{
    		reader.parse(db, resources.nextElement().openStream());
    	}
	}
}
