package com.soffid.iam.deployer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
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
import org.apache.openejb.NoSuchApplicationException;
import org.apache.openejb.UndeployException;
import org.apache.openejb.assembler.Deployer;
import org.apache.openejb.assembler.DeployerEjb;
import org.apache.openejb.assembler.classic.AppInfo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@Singleton(name="SoffidDeployerBean")
@Local({DeployerService.class})
@Startup
@javax.ejb.TransactionManagement(value=javax.ejb.TransactionManagementType.CONTAINER)
@javax.ejb.TransactionAttribute(value=javax.ejb.TransactionAttributeType.SUPPORTS)
public class DeployerBean implements DeployerService {
	Log log = LogFactory.getLog(DeployerBean.class);
	private File mainWarFile;
	private File webserviceWarFile;
	List<String> initClasses = new LinkedList<String>();
	List<String> coreModules = new LinkedList<String>();
	List<String> javaModules = new LinkedList<String>();
	boolean failSafe;
	private File selfServiceWarFile;

	@Resource(name="soffidDataSource")
	DataSource ds ;
	
	Deployer deployer = new DeployerEjb();
	
	@Resource
	private SessionContext context;

	public DeployerBean() {
		super();
	}

	@PostConstruct
	public void init() throws Exception {
		log.info("Started deployer bean");
		AccessController.doPrivileged(new PrivilegedAction<Void>() {
			public Void run() {
				try {
					deploy(true);
					context.getTimerService().createTimer(10, 5000, "Scan Timer");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}

	public List<String> getInitClasses() {
		return initClasses;
	}

	public List<String> getCoreModules() {
		return coreModules;
	}

	private void recursivelyDelete(File deployDir) {
		if (deployDir.isDirectory()) {
			for (File child : deployDir.listFiles()) {
				recursivelyDelete(child);
			}
		}
		deployDir.delete();
	}

	private void generateEar(QueryHelper qh) throws Exception {
		uncompressEar();
		log.info("Deploying plugins");
		extractPlugins(qh);
		log.info("Setting application up");
		updateApplicationXml();
		new FileOutputStream(getTimestampFile()).close();
	}

	private void updateApplicationXml() throws SAXException, IOException,
			ParserConfigurationException, XPathExpressionException,
			TransformerException {
		File appXml = new File(
				new File(deployDir(), "META-INF"), "application.xml"); //$NON-NLS-1$ //$NON-NLS-2$

		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		f.setValidating(false);

		// f.setFeature("http://xml.org/sax/features/namespaces", false);
		f.setFeature("http://xml.org/sax/features/validation", false);
		f.setFeature(
				"http://apache.org/xml/features/nonvalidating/load-dtd-grammar",
				false);
		f.setFeature(
				"http://apache.org/xml/features/nonvalidating/load-external-dtd",
				false);

		DocumentBuilder builder = f.newDocumentBuilder();
		builder.setEntityResolver(new EntityResolver() {

			public InputSource resolveEntity(String publicId, String systemId)
					throws SAXException, IOException {
				return new InputSource();
			}
		});
		FileInputStream in = new FileInputStream(appXml);
		Document doc = builder.parse(in);
		in.close();

		XPath xpath = XPathFactory.newInstance().newXPath();

		Node node = (Node) xpath.evaluate(
				"/application", doc, XPathConstants.NODE); //$NON-NLS-1$
		NodeList webmodules = (NodeList) xpath.evaluate("module/web", node, XPathConstants.NODESET);
		for (int i = 0; i < webmodules.getLength(); i++) {
			Node webmodule = webmodules.item(i);
			Node webUri = (Node) xpath.evaluate("web-uri", webmodule, XPathConstants.NODE);
			String path = webUri.getTextContent();
			webUri.setTextContent(removeExtension(path));
		}

		for (String modulePath : coreModules) {
			File moduleFile = new File(modulePath);
			if (isEjbModule(moduleFile)) {
				Element me = doc.createElement("module"); //$NON-NLS-1$
				Element ejb = doc.createElement("ejb"); //$NON-NLS-1$
				ejb.appendChild(doc.createTextNode(moduleFile.getName()));
				me.appendChild(ejb);
				node.appendChild(me);
				log.info("Registering ejb module " + moduleFile.getName());
			}
		}

		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		appXml.delete();
		FileOutputStream out = new FileOutputStream(appXml);
		StreamResult result = new StreamResult(out);
		transformer.transform(new DOMSource(doc), result);
		out.close();
	}

	private boolean isEjbModule(File moduleFile) throws IOException {
		ZipEntry entry;
		ZipInputStream zin = new ZipInputStream(new FileInputStream(moduleFile));
		boolean isEJB = false;
		while (!isEJB && (entry = zin.getNextEntry()) != null) {
			if (entry.getName().equals("META-INF/ejb-jar.xml") || //$NON-NLS-1$
					entry.getName().equals("META-INF\\ejb-jar.xml") ||
					entry.getName().equals("META-INF/openejb-jar.xml") || //$NON-NLS-1$
					entry.getName().equals("META-INF\\openejb-jar.xml")) //$NON-NLS-1$
			{
				isEJB = true;
			}
		}
		zin.close();
		return isEJB;
	}

	private void extractPlugins(QueryHelper qh) throws Exception {
		qh.select("SELECT SPM_ID, SPL_NAME, SPM_TYPE, SPM_CLASS, SPM_DATA " + //$NON-NLS-1$
				"FROM SC_SERPLU P, SC_SEPLMO M " + //$NON-NLS-1$
				"WHERE P.SPL_ENABLE=1 AND P.SPL_ID=M.SPM_SPL_ID",
				new Object[0], //$NON-NLS-1$
				new QueryAction() {
					public void perform(ResultSet rset) throws SQLException,
							IOException {
						long id = rset.getLong(1);
						String name = rset.getString(2);
						String type = rset.getString(3);
						log.info("Parsing database addon " + name);
						if (type.equals("W")) //$NON-NLS-1$
						{
							extractWarAddon(removeFileExtension(mainWarFile), name,
									rset.getBinaryStream(5));
						}
						if (type.equals("E")) //$NON-NLS-1$
						{
							extractWarAddon(removeFileExtension (selfServiceWarFile), name,
									rset.getBinaryStream(5));
						}
						if (type.equals("C") || type.equals("V")) //$NON-NLS-1$ //$NON-NLS-2$
						{
							String clazzname = rset.getString(4);
							if (!rset.wasNull())
								initClasses.add(clazzname);
							try {
								extractCoreAddon(Long.toString(id),
										rset.getBinaryStream(5));
							} catch (Exception e) {
								throw new IOException(e);
							}
						}
						if (type.equals("X")) //$NON-NLS-1$ //Web service
						{
							try {
								extractWebServiceAddon(Long.toString(id), name,
										rset.getBinaryStream(5));
							} catch (Exception e) {
								throw new IOException(e);
							}
						}
					}
				});
		File addonsDir = new File(
				new File(getJbossHomeDir(), "soffid"), "addons"); //$NON-NLS-1$ //$NON-NLS-2$
		if (addonsDir.isDirectory()) {
			for (File f : addonsDir.listFiles()) {
				String simpleName = f.getName();
				log.info("Parsing file addon " + simpleName);
				int lastDot = simpleName.lastIndexOf(".");
				if (lastDot > 0)
					simpleName = simpleName.substring(0, lastDot);

				if (f.getName().endsWith(".war"))
					extractWarAddon(mainWarFile, simpleName,
							new FileInputStream(f));
				else if (f.getName().endsWith(".jar"))
					extractCoreAddon(simpleName, new FileInputStream(f));
			}
		}
	}

	private void pauseConnector() throws MalformedObjectNameException,
			InstanceNotFoundException, ReflectionException, MBeanException, IntrospectionException, AttributeNotFoundException {
		final ObjectName objectNameQuery = new ObjectName("*:type=Connector,*");
		MBeanServer mbeanServer = null;
		for (final MBeanServer server : (List<MBeanServer>) MBeanServerFactory
				.findMBeanServer(null)) {
			for (ObjectName objectName: server.queryNames(objectNameQuery, null)) {
				mbeanServer = server;
				Object v = mbeanServer.getAttribute(objectName, "stateName");
				log.info("MBEAN "+objectName.getCanonicalName()+ "STATUS: "+v);
				if ("STARTED".equals (v))
				{
					log.info("Stopping");
					mbeanServer.invoke(objectName, "pause", null, null);
				}
			}
		}
	}

	private void resumeConnector() throws InstanceNotFoundException,
			ReflectionException, MBeanException, MalformedObjectNameException, AttributeNotFoundException {
		final ObjectName objectNameQuery = new ObjectName("*:type=Connector,*");
		MBeanServer mbeanServer = null;
		for (final MBeanServer server : (List<MBeanServer>) MBeanServerFactory
				.findMBeanServer(null)) {
			for (ObjectName objectName: server.queryNames(objectNameQuery, null)) {
				mbeanServer = server;
				Object v = mbeanServer.getAttribute(objectName, "stateName");
				log.info("MBEAN "+objectName.getCanonicalName()+ "STATUS: "+v);
				log.info("Resuming");
				mbeanServer.invoke(objectName, "resume", null, null);
			}
		}
	}

	protected void extractWarAddon(File warFile, String name,
			InputStream binaryStream) throws IOException {
		log.info("Generating web addon " + name);
		ZipEntry entry;
		ZipInputStream zin = new ZipInputStream(binaryStream);
		while ((entry = zin.getNextEntry()) != null) {
			if (entry.getName().equals("META-INF/web.xml")
					|| entry.getName().equals("META-INF\\web.xml")) {
				// Nothing to do with web.xml
			} else {
				File f = new File(warFile, entry.getName());
				if (entry.isDirectory()) {
					f.mkdirs();
				} else {
					if (f.getName().startsWith("replace-")) {
						f = new File(f.getParentFile(), f.getName()
								.substring(8));
						log.info("Replacing file " + f.getPath());
						f.getParentFile().mkdirs();
						extractFile(zin, f);
					} else if (isXslPath(warFile, entry)) {
						log.info("Applying XSL transformation to "
								+ f.getPath());
						File patchedFile = getPatchedFile(warFile, entry);
						File resultFile = new File(f.getPath()
								+ ".xslt-tmpfile");

						extractFile(zin, f);

						Source src = new StreamSource(patchedFile);
						Source xslt = new StreamSource(f);
						StreamResult result = new StreamResult();
						TransformerFactory factory = TransformerFactory
								.newInstance();
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
							log.warn(
									"Error transforming applying "
											+ entry.getName(), e);
						} catch (TransformerException e) {
							log.warn(
									"Error transforming applying "
											+ entry.getName(), e);
						}
					} else if (entry.getName().matches(
							".*iam-label.*\\.properties")) {
						log.info("Appending messages to " + f.getPath());
						FileOutputStream out = new FileOutputStream(f, true);
						out.write('\n');
						byte b[] = new byte[4096];
						int read;
						while ((read = zin.read(b)) > 0) {
							out.write(b, 0, read);
						}
						out.close();
					} else if (f.canRead()) {
						log.warn("Module " + name + ". Ignoring file "
								+ f.getPath());
					} else {
						f.getParentFile().mkdirs();
						extractFile(zin, f);
					}
				}
			}
		}
	}

	private void extractFile(ZipInputStream zin, File f)
			throws FileNotFoundException, IOException {
		FileOutputStream out = new FileOutputStream(f);
		byte b[] = new byte[4096];
		int read;
		while ((read = zin.read(b)) > 0) {
			out.write(b, 0, read);
		}
		out.close();
	}

	private boolean isXslPath(File warFile, ZipEntry entry) {
		if (!entry.getName().endsWith(".xsl"))
			return false;
		File f = getPatchedFile(warFile, entry);
		if (f.canRead())
			return true;
		else
			return false;
	}

	private File getPatchedFile(File warFile, ZipEntry entry) {
		String base = entry.getName().substring(0,
				entry.getName().lastIndexOf('.'));
		File f = new File(warFile, base);
		return f;
	}

	protected void extractCoreAddon(String name, InputStream binaryStream)
			throws Exception {
		File coreFile = new File(deployDir(), "plugin-" + name + ".jar"); //$NON-NLS-1$ //$NON-NLS-2$
		log.info("Generating addon file " + coreFile);
		FileOutputStream out = new FileOutputStream(coreFile);
		byte b[] = new byte[4096];
		int read;
		while ((read = binaryStream.read(b)) > 0) {
			out.write(b, 0, read);
		}
		out.close();
		if (isEjbModule(coreFile)) {
			coreModules.add(coreFile.getPath());
		} else {
			File commonFile = new File(deployDir(), "lib/plugin-" + name + ".jar"); //$NON-NLS-1$ //$NON-NLS-2$
			InputStream in = new FileInputStream(coreFile);
			out = new FileOutputStream(commonFile);
			while ((read = in.read(b)) > 0) {
				out.write(b, 0, read);
			}
			in.close();
			out.close();
			coreFile.delete();
		}
	}

	protected void extractWebServiceAddon(String name, String originalName, InputStream binaryStream)
			throws Exception {
		File coreFile = new File(deployDir(), "plugin-" + name + ".jar"); //$NON-NLS-1$ //$NON-NLS-2$
		log.info("Generating web service file " + coreFile);
		FileOutputStream out = new FileOutputStream(coreFile);
		boolean ejb = false;
		byte b[] = new byte[4096];
		int read;
		while ((read = binaryStream.read(b)) > 0) {
			out.write(b, 0, read);
		}
		out.close();

		// Search for services.xml file
		boolean axisService = false;
		ZipInputStream zin = new ZipInputStream( new FileInputStream(coreFile));
		ZipEntry entry;
		while ((entry = zin.getNextEntry()) != null) {
			if (entry.getName().equals("META-INF/services.xml") ||
					entry.getName().equals("META-INF\\services.xml") )
				axisService = true;
		}
		zin.close();
		
		if ( axisService)
		{
			File wsFile = new File(new File (removeExtension(webserviceWarFile.getPath())),
					"WEB-INF/services/plugin-" + name + ".aar"); //$NON-NLS-1$ //$NON-NLS-2$
	
			log.info("Moving to " + wsFile);
			FileOutputStream out2 = new FileOutputStream(wsFile);
			FileInputStream in2 = new FileInputStream (coreFile);
			while ((read = in2.read(b)) > 0) {
				out2.write(b, 0, read);
			}
			out2.close();
			in2.close();
			
			coreFile.delete();
		}
		else
		{
			File classesDir = new File(new File (removeExtension(webserviceWarFile.getPath())),
					"WEB-INF/classes"); //$NON-NLS-1$ //$NON-NLS-2$
			log.info("Extracting to " + classesDir);
			zin = new ZipInputStream( new FileInputStream(coreFile));
			while ((entry = zin.getNextEntry()) != null) {
				File f = new File(classesDir, entry.getName());
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

	private void uncompressEar() throws Exception {
		File target = deployDir();
		log.info("Exploding " + initialEarFile().getPath() + " into "
				+ target.getPath());
		target.mkdirs();
		ZipInputStream zin = new ZipInputStream(new FileInputStream(
				initialEarFile()));
		ZipEntry entry;
		while ((entry = zin.getNextEntry()) != null) {
			File f = new File(target, entry.getName());
			if (entry.isDirectory()) {
				f.mkdirs();
			} else {
				if (entry.getName().endsWith(".war")) //$NON-NLS-1$
					extractWar(zin, f);
				else {
					f.getParentFile().mkdirs();
					extractFile(zin, f);
				}
			}
		}
	}

	private String removeExtension (String warFile) {
		if (warFile.toUpperCase().endsWith(".WAR"))
		{
			int i = warFile.lastIndexOf('.');
			if ( i >= 0)
				return warFile.substring(0,i);
			else
				return warFile;
		}
		else
			return warFile;
	}

	private File removeFileExtension (File warFile) {
		String s = warFile.getPath();
		if (s.toUpperCase().endsWith(".WAR"))
		{
			int i = s.lastIndexOf('.');
			if ( i >= 0)
				return new File(s.substring(0,i));
			else
				return warFile;
		}
		else
			return warFile;
	}

	private void extractWar(InputStream in, File warFile)
			throws FileNotFoundException, IOException {
		log.info("Exploding war " + warFile.getPath());
		if (warFile.getName().startsWith("iam-web-")) //$NON-NLS-1$
			mainWarFile = warFile;
		if (warFile.getName().startsWith("selfservice-")) //$NON-NLS-1$
			selfServiceWarFile = warFile;
		if (warFile.getName().startsWith("iam-webservice-")) //$NON-NLS-1$
			webserviceWarFile = warFile;
		warFile = new File (removeExtension(warFile.getPath()));
		warFile.mkdirs();
		ZipEntry entry;
		ZipInputStream zin = new ZipInputStream(in);
		while ((entry = zin.getNextEntry()) != null) {
			File f = new File(warFile, entry.getName());
			if (entry.isDirectory()) {
				f.mkdirs();
			} else {
				f.getParentFile().mkdirs();
				extractFile(zin, f);
			}
		}
	}

	private File tmpDir() {
		File home = getJbossHomeDir();
		File deploy = new File(new File(home, "work"), "soffid"); //$NON-NLS-1$ //$NON-NLS-2$
		deploy.mkdirs();
		return deploy;

	}

	private File deployDir() {
		return new File(tmpDir(), "iam-ear.ear"); //$NON-NLS-1$
	}

	private File initialEarFile() {
		File home = getJbossHomeDir();
		File ear = new File(new File(home, "soffid"), "iam-ear.ear"); //$NON-NLS-1$ //$NON-NLS-2$
		return ear;

	}

	private File getJbossHomeDir() {
		return new File(System.getProperty("catalina.home")); //$NON-NLS-1$
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
		AccessController.doPrivileged(new PrivilegedAction<Void>() {
			public Void run() {
				try {
					undeploy();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}

	private void undeploy() throws MalformedURLException, UndeployException,
			NoSuchApplicationException {
		try {
			if (failedWarInfo != null)
			{
				log.info("Undeploying "+failedWarInfo.path);
				deployer.undeploy(failedWarInfo.path);
				failedWarInfo = null;
			}
		} catch (Throwable e2) {
			log.warn(e2);
		}

		if (appInfo != null)
		{
			deployer.undeploy(appInfo.path);
			appInfo = null;
		}
		initClasses = new LinkedList<String>();
		coreModules = new LinkedList<String>();
		javaModules = new LinkedList<String>();
	}

	public boolean isFailSafe() {
		if (getFailSafeFile().exists())
			return true;
		else
			return failSafe;
	}

	private File getFailSafeFile() {
		return new File(
				new File(getJbossHomeDir(), "soffid"), "fail-safe"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public void setFailSafe(boolean failSafe) {
		this.failSafe = failSafe;

	}

	public void redeploy() throws Exception {
		AccessController.doPrivileged(new PrivilegedAction<Void>() {
			public Void run() {
				try {
					log.info("Redeploying SOFFID CONSOLE");
					pauseConnector();
					Thread.sleep(2000);
					undeploy();
					deploy(false);
					resumeConnector();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}

	AppInfo failedWarInfo = null;
	private AppInfo appInfo = null;
	private static boolean ongoing = false;
	private void deploy(boolean firstTime) throws Exception {

		if (ongoing)
			return;

		ongoing = true;
		File home = getJbossHomeDir();
		File failedWar = new File(new File(home, "soffid"), "failed.ear");

		lastModified = calculateLastModified();

		try {
			coreModules = new LinkedList<String>();
			javaModules = new LinkedList<String>();

			if (isFailSafe()) {
				System.setProperty("soffid.fail-safe", "true");
				log.info("Deploying on fail-safe mode");
				deleteCacheProperties ();
				recursivelyDelete(tmpDir());
				getTimestampFile().delete();
				uncompressEar();
				updateApplicationXml();
				getFailSafeFile().delete();
			} else {
				System.setProperty("soffid.fail-safe", "false");
				log.info(Messages.getString("UploadService.StartedUploadInfo")); //$NON-NLS-1$
				Connection conn = ds.getConnection();
				QueryHelper qh = new QueryHelper(conn);
				updateCacheProperties (qh);
				try {
					List<Object[]> result = null;
					try {
						 result = qh.select("SELECT CON_VALOR FROM " + //$NON-NLS-1$
							"SC_CONFIG WHERE CON_CODI='plugin.timestamp'"); //$NON-NLS-1$
					} catch (SQLException e) {
						// Maybe first execution
					}
					if (result == null)
					{
						// First time
						System.setProperty("soffid.fail-safe", "true");
						log.info("Deploying on fail-safe mode");
						recursivelyDelete(tmpDir());
						getTimestampFile().delete();
						uncompressEar();
						updateApplicationXml();
					}
					else if ( !result.isEmpty()) 
					{
						Long ts = Long.decode(result.iterator().next()[0]
								.toString());
						if (mustUpdate(ts)) {
							recursivelyDelete(tmpDir());
							generateEar(qh);
						} else {
							log.info(Messages.getString("DeployerService.3")); //$NON-NLS-1$
						}
					} else
						generateEar(qh);
				} finally {
					conn.close();
				}
			}

			try {
				log.info("Deploying "+deployDir().getPath());
				appInfo = deployer.deploy(deployDir().getPath());
			} catch (Exception e) {
				coreModules = new LinkedList<String>();
				javaModules = new LinkedList<String>();
				log.warn(
						"Error generating Soffid IAM ear. Generating fail-safe console",
						e);
				System.setProperty("soffid.fail-safe", "true");
				log.info("Deploying on fail-safe mode");
				recursivelyDelete(tmpDir());
				getTimestampFile().delete();
				uncompressEar();
				updateApplicationXml();
				log.info("Deploying "+deployDir().getPath());
				appInfo = deployer.deploy(deployDir().getPath());
			} finally {
				Thread.sleep(1000);
			}

		} catch (Throwable e) {
			log.warn(Messages.getString("UploadService.UploadFileError"), e); //$NON-NLS-1$
			try {
				failedWarInfo = deployer.deploy(failedWar.getPath());
			} catch (Exception e2) {
				log.warn(e2);
			}
		} finally {
			ongoing = false;
		}
	}


	private void updateCacheProperties(QueryHelper qh) throws SQLException, IOException {
		deleteCacheProperties();
		try {
			for ( Object[] data: qh.select(
					  "SELECT CON_CODI, CON_VALOR "
					+ "FROM   SC_TENANT, SC_CONFIG "
					+ "WHERE  CON_TEN_ID=TEN_ID AND CON_IDXAR IS NULL AND TEN_NAME='master' "
					+ "AND    CON_CODI = 'soffid.cache.enable'", new Object [0]))
			{
				System.setProperty  ((String) data[0], (String) data[1]);
			}
			
			File f = new File ( new File (getJbossHomeDir(), "conf"), "jcs.properties");
			if (f.canRead())
			{
				System.setProperty("soffid.cache.configFile", f.getAbsolutePath());
			}
			else
			{
				System.getProperties().remove("soffid.cache.configFile");
				for ( Object[] data: qh.select(
						  "SELECT BCO_NAME, BCO_VALUE "
						+ "FROM   SC_BLOCON "
						+ "WHERE  BCO_NAME = 'soffid.cache.config'", new Object [0]))
				{
					System.setProperty  ((String) data[0], new String((byte[]) data[1], "UTF-8"));
				}
			}
		} catch (Exception e) {
			log.info("Error getting cache configuration", e);
		}

	}

	private void deleteCacheProperties() {
		System.setProperty("soffid.cache.enable", "false");
	}

	private void doDeploy() throws Exception {
		AccessController.doPrivileged(new PrivilegedAction<Void>() {
			public Void run() {
				try {
					log.info("Deploying by first time");
					pauseConnector();
					undeploy();
					deploy(true);
					resumeConnector();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}

	private long lastModified = 0;

	@Timeout
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public void timeOutHandler(Timer timer) throws Exception {
		try {
			if (lastModified  == 0)
				doDeploy ();
			else if (!ongoing)
			{
				failSafe = false;
				long last = calculateLastModified();
		
				if (last > lastModified) {
					lastModified = last;
					redeploy();
				}
			}
		} catch (Exception e) {
			log.info("Error on deployer timer", e);
		}
	}

	private long calculateLastModified() {
		long last = initialEarFile().lastModified();
		File addonsDir = new File(
				new File(getJbossHomeDir(), "soffid"), "addons"); //$NON-NLS-1$ //$NON-NLS-2$

		File failSafeFile = getFailSafeFile();
		if (failSafeFile.exists() && failSafeFile.lastModified() > last)
			last = failSafeFile.lastModified();
		
		if (addonsDir.isDirectory()) {
			for (File f : addonsDir.listFiles()) {
				if (f.lastModified() > last)
				{
					last = f.lastModified();
				}
			}
		}
		return last;
	}

}
