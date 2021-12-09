package com.soffid.iam.deployer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.deployment.DeploymentException;
import org.jboss.deployment.scanner.AbstractDeploymentScanner;
import org.jboss.deployment.scanner.DeploymentScanner;
import org.jboss.mx.util.MBeanProxyCreationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DeployerService extends AbstractDeploymentScanner 
	implements DeploymentScanner, DeployerServiceMBean {
    int scheduledInterval = 30000;
    int maxScheduledInterval = 30000;
    int schedulerThreads = 1;
    Log log = LogFactory.getLog(DeployerService.class);
	private File mainWarFile;
	private File webserviceWarFile;
	List<String> initClasses = new LinkedList<String>();
	List<String> coreModules = new LinkedList<String>();
	List<String> javaModules = new LinkedList<String>();
	boolean failSafe;
	private File selfServiceWarFile;
	

	public DeployerService() throws InstanceNotFoundException, MalformedObjectNameException, NullPointerException, MBeanProxyCreationException {
		super();
	}



	public List<String> getInitClasses() {
		return initClasses;
	}



	public List<String> getCoreModules() {
		return coreModules;
	}



    private void recursivelyDelete(File deployDir) {
    	if (deployDir.isDirectory())
    	{
			for (File child: deployDir.listFiles())
			{
				recursivelyDelete(child);
			}
    	}
		deployDir.delete();
	}



	private void generateEar(QueryHelper qh) throws Exception {
    	uncompressEar ();
    	log.info ("Deploying plugins");
    	extractPlugins (qh);
    	log.info ("Setting application up");
    	updateApplicationXml ();
    	updateJBossAppXml();
    	new FileOutputStream(getTimestampFile()).close();
	}



	private void updateApplicationXml() throws SAXException, IOException, ParserConfigurationException, XPathExpressionException, TransformerException {
		File appXml = new File (new File(deployDir(),"META-INF"), "application.xml"); //$NON-NLS-1$ //$NON-NLS-2$
		
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		f.setValidating(false);

//		f.setFeature("http://xml.org/sax/features/namespaces", false);
		f.setFeature("http://xml.org/sax/features/validation", false);
		f.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
		f.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

		DocumentBuilder builder = f.newDocumentBuilder();
		builder.setEntityResolver(new EntityResolver() {
			
            public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException
            {
                return new InputSource();
            }
        });
		FileInputStream in = new FileInputStream(appXml);
		Document doc = builder.parse(in);
		in.close();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		
		Node node = (Node) xpath.evaluate("/application", doc, XPathConstants.NODE); //$NON-NLS-1$
		NodeList modules = node.getChildNodes();
		Node firstModule = null;
		for (int i = 0; i < modules.getLength(); i++)
		{
			Node m = modules.item(i);
			if ( m instanceof Element && ((Element) m).getTagName().equals ("module"))
			{
				firstModule = m;
				break;
			}
		}
		
		for (String modulePath: coreModules)
		{
			File moduleFile = new File (modulePath);
			if (isEjbModule (moduleFile))
			{
				Element me = doc.createElement("module"); //$NON-NLS-1$
				Element ejb = doc.createElement("ejb"); //$NON-NLS-1$
				ejb.appendChild(doc.createTextNode(moduleFile.getName()));
				me.appendChild(ejb);
				node.appendChild(me);
				log.info ("Registering ejb module "+moduleFile.getName());
			}
		}
		
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		appXml.delete();
		FileOutputStream out = new FileOutputStream(appXml);
		StreamResult result = new StreamResult(out);
		transformer.transform(new DOMSource(doc), result);
		out.close ();
	}

	private void updateJBossAppXml() throws SAXException, IOException, ParserConfigurationException, XPathExpressionException, TransformerException {
		File appXml = new File (new File(deployDir(),"META-INF"), "jboss-app.xml"); //$NON-NLS-1$ //$NON-NLS-2$
		
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		f.setValidating(false);
		f.setFeature("http://xml.org/sax/features/validation", false);
		f.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
		f.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

		DocumentBuilder builder = f.newDocumentBuilder();
		builder.setEntityResolver(new EntityResolver() {
			
            public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException
            {
                return new InputSource();
            }
        });
		FileInputStream in = new FileInputStream(appXml);
		Document doc = builder.parse(in);
		in.close();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		
		Node node = (Node) xpath.evaluate("/jboss-app/loader-repository", doc, XPathConstants.NODE); //$NON-NLS-1$
		NodeList modules = node.getChildNodes();
		Node firstModule = null;
		for (int i = 0; i < modules.getLength(); i++)
		{
			Node m = modules.item(i);
			if ( m instanceof Text)
			{
				m.setTextContent("jboss.loader:loader=soffid-"+System.currentTimeMillis()+"\n");
				break;
			}
		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		appXml.delete();
		FileOutputStream out = new FileOutputStream(appXml);
		StreamResult result = new StreamResult(out);
		transformer.transform(new DOMSource(doc), result);
		out.close ();
	}


	private boolean isEjbModule(File moduleFile) throws IOException {
        ZipEntry entry;
        ZipInputStream zin = new ZipInputStream(new FileInputStream(moduleFile));
        boolean isEJB = false;
        while ( !isEJB &&  (entry = zin.getNextEntry()) != null ) {
            if (entry.getName().equals ("META-INF/ejb-jar.xml") || //$NON-NLS-1$
            		entry.getName().equals ("META-INF\\ejb-jar.xml")) //$NON-NLS-1$
            {
            	isEJB = true;
            }
        }
        zin.close();
        return isEJB;
	}



	private void extractPlugins(QueryHelper qh) throws Exception {
		qh.select("SELECT SPM_ID, SPL_NAME, SPM_TYPE, SPM_CLASS, SPM_DATA "+ //$NON-NLS-1$
            			"FROM SC_SERPLU P, SC_SEPLMO M "+ //$NON-NLS-1$
            			"WHERE P.SPL_ENABLE=1 AND P.SPL_ID=M.SPM_SPL_ID", new Object[0], //$NON-NLS-1$
            new QueryAction() {
				public void perform(ResultSet rset) throws SQLException, IOException {
					long id = rset.getLong(1);
					String name = rset.getString(2);
					String type = rset.getString(3);
					log.info("Parsing database addon "+name);
					if (type.equals("W")) //$NON-NLS-1$
					{
						extractWarAddon (mainWarFile, name, rset.getBinaryStream(5));
					}
					if (type.equals("E")) //$NON-NLS-1$
					{
						extractWarAddon (selfServiceWarFile, name, rset.getBinaryStream(5));
					}
					if (type.equals("C") || type.equals("V")) //$NON-NLS-1$ //$NON-NLS-2$
					{
						String clazzname = rset.getString(4);
						if (!rset.wasNull())
							initClasses.add(clazzname);
						try {
							extractCoreAddon (Long.toString(id), rset.getBinaryStream(5));
						} catch (Exception e) {
							throw new IOException(e);
						}
					}
					if (type.equals("X") ) //$NON-NLS-1$ //Web service
					{
						try {
							extractWebServiceAddon (Long.toString(id), rset.getBinaryStream(5));
						} catch (Exception e) {
							throw new IOException(e);
						}
					}
				}
			});
    	File addonsDir = new File (new File(getJbossHomeDir(), "soffid"), "addons"); //$NON-NLS-1$ //$NON-NLS-2$
    	if (addonsDir.isDirectory())
    	{
	    	for (File f: addonsDir.listFiles())
	    	{
	    		String simpleName = f.getName();
				log.info("Parsing file addon "+simpleName);
	    		int lastDot = simpleName.lastIndexOf(".");
	    		if (lastDot > 0)
	    			simpleName = simpleName.substring(0, lastDot);
	    		
	    		if (f.getName().endsWith(".war"))
					extractWarAddon (mainWarFile, simpleName, new FileInputStream(f));
	    		else
	    			extractCoreAddon (simpleName, new FileInputStream(f));
	    	}
    	}
	}


	private void pauseConnector () throws MalformedObjectNameException, NullPointerException, InstanceNotFoundException, ReflectionException, MBeanException
	{
		for (ObjectName name : getServer().queryNames(new ObjectName("jboss.web:type=Connector,*"), null)) 
		{
			getServer().invoke(name, "pause", new Object[0], new String[0]);
		}
	}
	
	private void resumeConnector () throws MalformedObjectNameException, NullPointerException, InstanceNotFoundException, ReflectionException, MBeanException
	{
		for (ObjectName name : getServer().queryNames(new ObjectName("jboss.web:type=Connector,*"), null)) 
		{
			getServer().invoke(name, "resume", new Object[0], new String[0]);
		}
	}
	
	
	protected void extractWarAddon(File warFile, String name, InputStream binaryStream) throws IOException {
		log.info("Generating web addon "+name);
        ZipEntry entry;
        ZipInputStream zin = new ZipInputStream(binaryStream);
        while ( (entry = zin.getNextEntry()) != null ) {
        	if (entry.getName().equals ("META-INF/web.xml") ||
        			entry.getName().equals ("META-INF\\web.xml"))
        	{
        		// Nothing to do with web.xml
        	}
        	else
        	{
        		String entryName = entry.getName();
	            File f = new File(warFile, entry.getName());
	            if (entry.isDirectory()) {
	                f.mkdirs();
	            } else {
	        		if (f.getName().startsWith("replace-"))
	        		{
	        			f = new File (f.getParentFile(), f.getName().substring(8));
	            		log.info("Replacing file "+f.getPath());
		                f.getParentFile().mkdirs();
		                extractFile(zin, f);
	        		} 
	        		else if (isXslPath(warFile, entry))
	            	{
	            		log.info("Applying XSL transformation to "+f.getPath());
	            		File patchedFile = getPatchedFile(warFile, entry);
	            		File resultFile = new File (f.getPath()+".xslt-tmpfile");
	            		
	            		extractFile(zin, f);

	            		Source src = new StreamSource(patchedFile);
	            		Source xslt = new StreamSource(f);
	            		StreamResult result = new StreamResult();
	            		TransformerFactory factory = TransformerFactory.newInstance();
	            		try {
		            		OutputStream out = new FileOutputStream(resultFile);
		            		result.setOutputStream(out);
							Transformer trans = factory.newTransformer(xslt);
							trans.transform(src, result);
							out.close();
							patchedFile.delete();
							resultFile.renameTo(patchedFile);
							f.delete();
						} catch (TransformerConfigurationException e) {
							log.warn("Error transforming applying "+entry.getName(),
									e);
						} catch (TransformerException e) {
							log.warn("Error transforming applying "+entry.getName(),
									e);
						}
	            	}
	            	else if (entry.getName().matches(".*iam-label.*\\.properties"))
	            	{
	            		log.info("Appending messages to "+f.getPath());
	            		FileOutputStream out = new FileOutputStream(f, true);
	            		out.write('\n');
	            		byte b[] = new byte[4096];
	            		int read;
	            		while ( (read = zin.read(b)) > 0)
	            		{
	            			out.write(b, 0, read);
	            		}
	            		out.close();
	            	}
	            	else if (f.canRead())
	            	{
            			log.warn("Module "+name+". Ignoring file "+f.getPath());
	            	}
	            	else
	            	{
		                f.getParentFile().mkdirs();
		                extractFile(zin, f);
	            	}
	            }
        	}
        }
	}



	private void extractFile(ZipInputStream zin, File f)
			throws FileNotFoundException, IOException {
		FileOutputStream out = new FileOutputStream (f);
		byte b[] = new byte [4096];
		int read;
		while ( (read = zin.read(b)) > 0 )
		{
		    out.write(b, 0, read);
		}
		out.close();
	}



	private boolean isXslPath(File warFile, ZipEntry entry) {
		if (! entry.getName().endsWith(".xsl"))
			return false;
		File f = getPatchedFile(warFile, entry);
		if (f.canRead())
			return true;
		else
			return false;
	}



	private File getPatchedFile(File warFile, ZipEntry entry) {
		String base = entry.getName().substring(0, entry.getName().lastIndexOf('.'));
		File f = new File (warFile, base);
		return f;
	}



	protected void extractCoreAddon(String name, InputStream binaryStream) throws Exception {
		File coreFile = new File (deployDir(), "plugin-"+name+".jar"); //$NON-NLS-1$ //$NON-NLS-2$
		log.info("Generating addon file "+coreFile);
		FileOutputStream out = new FileOutputStream(coreFile);
		boolean ejb = false;
        byte b[] = new byte [4096];
        int read;
        while ( (read = binaryStream.read(b)) > 0 )
        {
            out.write(b, 0, read);
        }
		out.close ();
		if (isEjbModule(coreFile))
		{
			coreModules.add(coreFile.getPath());
		}
		else
		{
	        ZipInputStream zin = new ZipInputStream(
	        		new FileInputStream(coreFile)
	        		);
	        ZipEntry entry;
	        while ( (entry = zin.getNextEntry()) != null ) {
	            File f = new File(deployDir(), entry.getName());
	            if (entry.isDirectory()) {
	                f.mkdirs();
	            } else {
	                f.getParentFile().mkdirs();
	                extractFile(zin, f);
	            }
	        }
			coreFile.delete();
		}
	}


	protected void extractWebServiceAddon(String name, InputStream binaryStream) throws Exception 
	{
		
		File wsFile = new File (webserviceWarFile, "WEB-INF/services/plugin-"+name+".aar"); //$NON-NLS-1$ //$NON-NLS-2$
		
		
		log.info("Generating webservice file "+wsFile);
		FileOutputStream out = new FileOutputStream(wsFile);
        byte b[] = new byte [4096];
        int read;
        while ( (read = binaryStream.read(b)) > 0 )
        {
            out.write(b, 0, read);
        }
		out.close ();
	}



	private void uncompressEar() throws Exception {
		File target = deployDir();
		log.info("Exploding "+initialEarFile().getPath()+" into "+target.getPath());
		target.mkdirs();
        ZipInputStream zin = new ZipInputStream(
        		new FileInputStream(initialEarFile())
        		);
        ZipEntry entry;
        while ( (entry = zin.getNextEntry()) != null ) {
            File f = new File(target, entry.getName());
            if (entry.isDirectory()) {
                f.mkdirs();
            } else {
            	if (entry.getName().endsWith(".war")) //$NON-NLS-1$
            		extractWar (zin, f);
            	else
            	{
	                f.getParentFile().mkdirs();
	                extractFile(zin, f);
            	}
            }
        }
	}


	private void extractWar(InputStream in, File warFile) throws FileNotFoundException, IOException 
	{
		log.info("Exploding war "+warFile.getPath());
		if (warFile.getName().startsWith("iam-web-")) //$NON-NLS-1$
			mainWarFile = warFile;
		if (warFile.getName().startsWith("selfservice-")) //$NON-NLS-1$
			selfServiceWarFile = warFile;
		if (warFile.getName().startsWith("iam-webservice-")) //$NON-NLS-1$
			webserviceWarFile = warFile;
		warFile.mkdirs();
        ZipEntry entry;
        ZipInputStream zin = new ZipInputStream(in);
        while ( (entry = zin.getNextEntry()) != null ) {
            File f = new File(warFile, entry.getName());
            if (entry.isDirectory()) {
                f.mkdirs();
            } else {
                f.getParentFile().mkdirs();
                extractFile(zin, f);
            }
        }
	}


	private File tmpDir () {
    	File home = getJbossHomeDir();
    	File deploy = new File (new File(home, "tmp"), "soffid"); //$NON-NLS-1$ //$NON-NLS-2$
    	deploy.mkdirs();
    	return deploy;
    	
    }
	
	private File deployDir () {
		return new File (tmpDir(), "iam-ear.ear"); //$NON-NLS-1$
	}
	
	private File initialEarFile () {
    	File home = getJbossHomeDir();
    	File ear = new File (new File(home, "soffid"), "iam-ear.ear"); //$NON-NLS-1$ //$NON-NLS-2$
    	return ear;
    	
    }


	private File getJbossHomeDir() {
		return new File(System.getProperty ("jboss.server.home.dir")); //$NON-NLS-1$
	}
	
    private boolean mustUpdate(Long ts) {
    	File testFile = getTimestampFile();
    	if (!testFile.canRead())
    		return true;
    	
    	if (testFile.lastModified() < ts.longValue())
    		return true;
    	
    	if (initialEarFile().lastModified() > ts.longValue())
    		return true;
    	
    	return false;
	}


	private File getTimestampFile() {
		return new File(tmpDir(), "timestamp.properties"); //$NON-NLS-1$
	}


	protected void stopService() throws Exception {
        undeploy();
    }



	private void undeploy() throws MalformedURLException, DeploymentException {
		deployer.undeploy(deployDir().toURL());
		initClasses = new LinkedList<String>();
		coreModules = new LinkedList<String>();
		javaModules = new LinkedList<String>();
	}



	public boolean isFailSafe() {
		return failSafe;
	}



	public void setFailSafe(boolean failSafe) {
		this.failSafe = failSafe;
		
	}


	public void redeploy() throws Exception {
		undeploy();
		deploy();
	}

	private void deploy() throws Exception {
        	
    	File home = getJbossHomeDir();
    	File startingWar = new File (new File(home, "soffid"), "starting.war"); 
    	File failedWar = new File (new File(home, "soffid"), "failed.war"); 
            
        try {
        	if (failedWar.isFile() || failedWar.isDirectory())	
        	{
        		try {
        			deployer.undeploy(startingWar.toURL());
        		} catch (Exception e2) {
        		}
        	}

        	if (startingWar.isFile() || startingWar.isDirectory())	
        	{
        		try {
        			deployer.undeploy(startingWar.toURL());
        		} catch (Exception e2) {
        		}
        		try {
        			deployer.deploy(startingWar.toURL());
        		} catch (Exception e2) {
        			log.warn (e2);
        		}
        	}
        	
            if (failSafe)
            {
            	System.setProperty("soffid.fail-safe", "true");
            	log.info("Deploying on fail-safe mode");
                recursivelyDelete(tmpDir());
            	getTimestampFile().delete();
            	uncompressEar ();
            }
            else
            {
            	System.setProperty("soffid.fail-safe", "false");
	            log.info(Messages.getString("UploadService.StartedUploadInfo")); //$NON-NLS-1$
	            DataSource ds = (DataSource) new InitialContext().lookup ("java:jdbc/seycon"); //$NON-NLS-1$
	            Connection conn = ds.getConnection();
	        	QueryHelper qh = new QueryHelper(conn);
	            try {
	            	List<Object[]> result = qh.select("SELECT CON_VALOR FROM " + //$NON-NLS-1$
	            			"SC_CONFIG WHERE CON_CODI='plugin.timestamp'"); //$NON-NLS-1$
	            	if (!result.isEmpty())
	            	{
	            		Long ts = Long.decode(result.iterator().next()[0].toString());
	            		if (mustUpdate(ts))
	            		{
	    	            	recursivelyDelete(tmpDir());
	            			generateEar (qh);
	            		} 
	            		else
	            		{
	            			log.info(Messages.getString("DeployerService.3")); //$NON-NLS-1$
	            		}
	            	}
	            	else
	            		generateEar(qh);
	            } catch (Exception e) {
	            	log.warn("Error generating Soffid IAM ear. Generating fail-safe console", e);
	            	recursivelyDelete(tmpDir());
	            	uncompressEar();
	            	getTimestampFile().delete();
	            } finally {
	            	conn.close();
	            }
            }
            
            
            try {
            	pauseConnector();
            	deployer.deploy(deployDir().toURL());
            } finally {
            	Thread.sleep(2000);
            	resumeConnector();
            }

        } catch (Exception e) {
            log.warn(Messages.getString("UploadService.UploadFileError"), e); //$NON-NLS-1$
        	if (failedWar.isFile() || failedWar.isDirectory())	
        	{
        		try {
        			deployer.deploy(failedWar.toURL());
        		} catch (Exception e2) {
        			log.warn (e2);
        		}
        	}
        	throw e;
        }
	}



	private long lastModified = 0;
	@Override
	public void scan() throws Exception {
		long last = initialEarFile().lastModified();
    	File addonsDir = new File (new File(getJbossHomeDir(), "soffid"), "addons"); //$NON-NLS-1$ //$NON-NLS-2$
    	if (addonsDir.isDirectory())
    	{
	    	for (File f: addonsDir.listFiles())
	    	{
	    		if ( f.lastModified() > last)
	    			last = f.lastModified();
	    	}
    	}
	    	
    	if (last > lastModified)
    	{
    		if (lastModified == 0)
    		{
        		lastModified = last;
    			deploy();
    		}
    		else
    		{
    			lastModified = last;
    			failSafe = false;
    			redeploy();
    		}
    	}
	}

	
}