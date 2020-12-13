package com.soffid.iam.service.impl;

import com.soffid.iam.api.AgentDescriptor;
import com.soffid.iam.api.ServerPlugin;
import com.soffid.iam.api.ServerPluginModule;
import es.caib.seycon.ng.comu.AttributeDirection;
import com.soffid.iam.api.AttributeMapping;
import com.soffid.iam.api.ObjectMappingProperty;
import es.caib.seycon.ng.comu.ServerPluginModuleType;
import com.soffid.iam.api.SoffidObjectType;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ServerPluginParser {
	File f;
	
	ServerPlugin plugin = null;

	public ServerPlugin getPlugin()
	{
		return plugin;
	}

	public void setPlugin(ServerPlugin plugin)
	{
		this.plugin = plugin;
	}

	public ServerPluginParser() throws IOException {
		f = createTempFile();
	}

	private File createTempFile() throws IOException
	{
		return File.createTempFile("plugin", "zip");  //$NON-NLS-1$ //$NON-NLS-2$
	}

	protected void finalize() throws Throwable {
		// Invoke the finalizer of our superclass
		// We haven't discussed superclasses or this syntax yet
		super.finalize();

		// Delete a temporary file we were using
		// If the file doesn't exist or tempfile is null, this can throw
		// an exception, but that exception is ignored.
		f.delete();
	}

	public void parse(byte b[]) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
		copyContent(f, b);		
		ZipFile zf = new ZipFile(f);
		Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zf.entries();
		while (entries.hasMoreElements()) {
			ZipEntry entry = entries.nextElement();
			if (!entry.isDirectory() && (
					entry.getName().equals("META-INF\\soffid-plugin.xml") || //$NON-NLS-1$
					entry.getName().equals("META-INF/soffid-plugin.xml") || //$NON-NLS-1$
					entry.getName().equals("META-INF\\seu-plugin.xml") || //$NON-NLS-1$
					entry.getName().equals("META-INF/seu-plugin.xml"))) { //$NON-NLS-1$
				parseDescriptorEntry (zf.getInputStream(entry), b);
				return;
			}
		}
		throw new IOException ("File META-INF/seu-plugin.xml not found"); //$NON-NLS-1$
	}

	private void parseDescriptorEntry(InputStream in, byte[] b) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document xmlDoc = db.parse(in);
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String name = xpath.evaluate("/seuServerPlugin/name", xmlDoc); //$NON-NLS-1$
		if (name != null && ! name.isEmpty())
		{
			parseDescriptorV1 (xmlDoc, b);
		} else {
			name = xpath.evaluate("/SoffidIAMPlugin/name", xmlDoc); //$NON-NLS-1$
			if (name != null && ! name.isEmpty())
				parseDescriptorV2(xmlDoc,b );
			else
				throw new SAXException(Messages.getString("ServerPluginParser.6")); //$NON-NLS-1$
		}
	}
	private void parseDescriptorV1 (Document xmlDoc, byte[] b) throws XPathExpressionException, SAXException, IOException
	{
		plugin = new ServerPlugin();
		
		XPath xpath = XPathFactory.newInstance().newXPath();

		plugin.setName(xpath.evaluate("/seuServerPlugin/name", xmlDoc));  //$NON-NLS-1$
		String version = (xpath.evaluate("/seuServerPlugin/version", xmlDoc)); //$NON-NLS-1$
		if (version == null)
		{
			throw new SAXException(Messages.getString("ServerPluginParser.8")); //$NON-NLS-1$
		}
		plugin.setVersion(version);
		List<ServerPluginModule> modules = new Vector<ServerPluginModule> (1);
		plugin.setModules(modules);
		
		InternalServerPluginModule module = new InternalServerPluginModule();
		module.setName(plugin.getName());
		module.setType(ServerPluginModuleType.MODULE_AGENT);
		module.setData(b);
		Vector<AgentDescriptor> descriptors = new Vector<AgentDescriptor> ();
		module.setAgents(descriptors);
		
		NodeList nodes = (NodeList) xpath.evaluate("/seuServerPlugin/agent", xmlDoc, XPathConstants.NODESET); //$NON-NLS-1$
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			InternalAgentDescriptor ad = new InternalAgentDescriptor();
			String classV2 = xpath.evaluate("javaClassV2", n);
			if ("-".equals(classV2))
				continue;
			if (classV2 == null || classV2.isEmpty())
				classV2 = xpath.evaluate("javaClass", n);
			if (classV2 == null || classV2.isEmpty())
				continue;
			ad.setClassName(classV2); //$NON-NLS-1$
			ad.setDescription(xpath.evaluate( "name", n)); //$NON-NLS-1$
			String resource = xpath.evaluate("userInterface", n); //$NON-NLS-1$
			if (resource == null || "".equals(resource)) //$NON-NLS-1$
				throw new SAXException(Messages.getString("ServerPluginParser.14")+ad.getDescription()+Messages.getString("ServerPluginParser.15")); //$NON-NLS-1$ //$NON-NLS-2$
			ad.setUserInterface(populateResource(f, resource));
			String enableAccessControl = xpath.evaluate("enableAccessControl", n); //$NON-NLS-1$
			if (enableAccessControl == null || "".equals(enableAccessControl)) //$NON-NLS-1$
				enableAccessControl = "false";//Posem N si és nul //$NON-NLS-1$
			ad.setEnableAccessControl("true".equals(enableAccessControl)?true:false); //$NON-NLS-1$

			String authoritativeSource = xpath.evaluate("authoritativeSource", n); //$NON-NLS-1$
			if (authoritativeSource == null || "".equals(authoritativeSource)) //$NON-NLS-1$
				authoritativeSource = "false";//Posem N si és nul //$NON-NLS-1$
			ad.setAuthoritativeSource("true".equals(authoritativeSource)); //$NON-NLS-1$

			String attributeMapping = xpath.evaluate("enableAttributeMapping", n); //$NON-NLS-1$
			if (attributeMapping == null || "".equals(attributeMapping)) //$NON-NLS-1$
				attributeMapping = "false";//Posem N si és nul //$NON-NLS-1$
			ad.setEnableAttributeMapping("true".equals(attributeMapping)); //$NON-NLS-1$

			String objectTriggers = xpath.evaluate("enableObjectTriggers", n); //$NON-NLS-1$
			if (objectTriggers == null || "".equals(objectTriggers)) //$NON-NLS-1$
				objectTriggers = "false";//Posem N si és nul //$NON-NLS-1$
			ad.setEnableObjectTriggers("true".equals(objectTriggers)); //$NON-NLS-1$

			String service = xpath.evaluate("service", n); //$NON-NLS-1$
			if (service == null || "".equals(service)) //$NON-NLS-1$
				service = "false";//Posem N si és nul //$NON-NLS-1$
			ad.setService("true".equals(service)); //$NON-NLS-1$

			loadDefaultAttributeMappings (ad, n);
			

			descriptors.add(ad);
		}

		modules.add(module);		
	}

	/**
	 * @param ad
	 * @param n
	 * @throws XPathExpressionException 
	 */
	private void loadDefaultAttributeMappings (InternalAgentDescriptor ad, Node n) throws XPathExpressionException
	{
		if (ad.isEnableAttributeMapping())
		{
    		XPath xpath = XPathFactory.newInstance().newXPath();

    		NodeList nodes = (NodeList) xpath.evaluate("object", n, XPathConstants.NODESET); //$NON-NLS-1$
    		List<InternalObjectMapping> objects = new LinkedList<InternalObjectMapping>();
    		for (int i = 0; i < nodes.getLength(); i++) {
    			Element child = (Element) nodes.item(i);
    			String condition = child.getAttribute("condition"); //$NON-NLS-1$
    			String soffidObject = child.getAttribute("soffidObject"); //$NON-NLS-1$
    			String systemObject = child.getAttribute("systemObject"); //$NON-NLS-1$
    			InternalObjectMapping object = new InternalObjectMapping ();
    			object.setCondition(condition);
    			object.setSoffidObject(SoffidObjectType.fromString(soffidObject));
    			object.setSystemObject(systemObject);
    			objects.add(object);
    			
        		NodeList nodes2 = (NodeList) xpath.evaluate("property", child, XPathConstants.NODESET); //$NON-NLS-1$
        		for (int j = 0; j < nodes2.getLength(); j++)
        		{
        			Element child2 = (Element) nodes2.item(j);
        			String name = child2.getAttribute("name"); //$NON-NLS-1$
        			String value = child2.getAttribute("value"); //$NON-NLS-1$
        			ObjectMappingProperty prop = new ObjectMappingProperty();
        			prop.setProperty(name);
        			prop.setValue(value);
        			object.getProperties().add(prop);
        		}
    
        		NodeList nodes3 = (NodeList) xpath.evaluate("mapping", child, XPathConstants.NODESET); //$NON-NLS-1$
        		List<AttributeMapping> mappings = new LinkedList<AttributeMapping>();
        		for (int j = 0; j < nodes3.getLength(); j++) {
        			Element child2 = (Element) nodes3.item(j);
        			String soffidAttribute = child2.getAttribute("soffidAttribute"); //$NON-NLS-1$
        			String systemAttribute = child2.getAttribute("systemAttribute"); //$NON-NLS-1$
        			String direction = child2.getAttribute("direction"); //$NON-NLS-1$
        			AttributeMapping mapping = new AttributeMapping ();
        			mapping.setSoffidAttribute(soffidAttribute);
        			mapping.setSystemAttribute(systemAttribute);
        			mapping.setDirection("in".equalsIgnoreCase(direction) ? AttributeDirection.INPUT : //$NON-NLS-1$
        								 "out".equalsIgnoreCase(direction) ? AttributeDirection.OUTPUT: //$NON-NLS-1$
        									 AttributeDirection.INPUTOUTPUT);
            		object.getAttributes().add(mapping);
        
        		}
    		}
    		ad.setObjects(objects);

		}
	}

	private void parseDescriptorV2 (Document xmlDoc, byte[] b) throws XPathExpressionException, SAXException, IOException
	{
		plugin = new ServerPlugin();
		
		XPath xpath = XPathFactory.newInstance().newXPath();

		plugin.setName(xpath.evaluate("/SoffidIAMPlugin/name", xmlDoc));  //$NON-NLS-1$
		String version = (xpath.evaluate("/SoffidIAMPlugin/version", xmlDoc)); //$NON-NLS-1$
		if (version == null)
		{
			throw new SAXException(Messages.getString("ServerPluginParser.8")); //$NON-NLS-1$
		}
		plugin.setVersion(version);
		List<ServerPluginModule> modules = new Vector<ServerPluginModule> (1);
		plugin.setModules(modules);
		
		NodeList nodes = (NodeList) xpath.evaluate("/SoffidIAMPlugin/core", xmlDoc, XPathConstants.NODESET); //$NON-NLS-1$
		for (int i = 0; i < nodes.getLength(); i++) {
			Element n = (Element) nodes.item(i);

			InternalServerPluginModule module = new InternalServerPluginModule();
			
			module.setName(n.getAttribute("name"));//$NON-NLS-1$
			module.setType(ServerPluginModuleType.MODULE_CORE);
			module.setInitClass(n.getAttribute( "initClass"));//$NON-NLS-1$
			String resource = n.getAttribute("resource"); //$NON-NLS-1$
			if (resource == null || resource.isEmpty()) {
				module.setData(b);
			}
			else
			{
				byte newData[] = populateResource(f, resource);
				module.setData(newData);
			}
			modules.add(module);
		}
			
		nodes = (NodeList) xpath.evaluate("/SoffidIAMPlugin/web", xmlDoc, XPathConstants.NODESET); //$NON-NLS-1$
		for (int i = 0; i < nodes.getLength(); i++) {
			Element n = (Element) nodes.item(i);

			InternalServerPluginModule module = new InternalServerPluginModule();
			
			module.setName(n.getAttribute("name"));//$NON-NLS-1$
			module.setType(ServerPluginModuleType.MODULE_WEB);
			String resource = n.getAttribute("resource"); //$NON-NLS-1$
			if (resource == null || resource.isEmpty()) {
				module.setData(b);
			}
			else
			{
				byte newData[] = populateResource(f, resource);
				module.setData(newData);
			}
			modules.add(module);
		}

		nodes = (NodeList) xpath.evaluate("/SoffidIAMPlugin/selfService", xmlDoc, XPathConstants.NODESET); //$NON-NLS-1$
		for (int i = 0; i < nodes.getLength(); i++) {
			Element n = (Element) nodes.item(i);

			InternalServerPluginModule module = new InternalServerPluginModule();
			
			module.setName(n.getAttribute("name"));//$NON-NLS-1$
			module.setType(ServerPluginModuleType.MODULE_SELFSERVICE);
			String resource = n.getAttribute("resource"); //$NON-NLS-1$
			if (resource == null || resource.isEmpty()) {
				module.setData(b);
			}
			else
			{
				byte newData[] = populateResource(f, resource);
				module.setData(newData);
			}
			modules.add(module);
		}

		nodes = (NodeList) xpath.evaluate("/SoffidIAMPlugin/common", xmlDoc, XPathConstants.NODESET); //$NON-NLS-1$
		for (int i = 0; i < nodes.getLength(); i++) {
			Element n = (Element) nodes.item(i);

			InternalServerPluginModule module = new InternalServerPluginModule();
			
			module.setName(n.getAttribute("name"));//$NON-NLS-1$
			module.setType(ServerPluginModuleType.MODULE_COMMON);
			String resource = n.getAttribute("resource"); //$NON-NLS-1$
			if (resource == null || resource.isEmpty()) {
				module.setData(b);
			}
			else
			{
				byte newData[] = populateResource(f, resource);
				module.setData(newData);
			}
			modules.add(module);
		}

		nodes = (NodeList) xpath.evaluate("/SoffidIAMPlugin/syncserver",  //$NON-NLS-1$
				xmlDoc, XPathConstants.NODESET); //$NON-NLS-1$
		for (int i = 0; i < nodes.getLength(); i++) {
			Element n = (Element) nodes.item(i);

			InternalServerPluginModule module = new InternalServerPluginModule();
			
			module.setName(n.getAttribute("name"));//$NON-NLS-1$
			module.setType(ServerPluginModuleType.MODULE_SYNCSERVER);
			String resource = n.getAttribute("resource"); //$NON-NLS-1$
			if (resource == null || resource.isEmpty()) {
				module.setData(b);
			}
			else
			{
				byte newData[] = populateResource(f, resource);
				module.setData(newData);
			}
			modules.add(module);
		}

		nodes = (NodeList) xpath.evaluate("/SoffidIAMPlugin/agents", xmlDoc, XPathConstants.NODESET); //$NON-NLS-1$
		for (int i = 0; i < nodes.getLength(); i++) {
			Element n = (Element) nodes.item(i);

			InternalServerPluginModule module = new InternalServerPluginModule();
			
			module.setName(n.getAttribute("name"));//$NON-NLS-1$
			module.setType(ServerPluginModuleType.MODULE_AGENT);
			String resource = n.getAttribute("resource"); //$NON-NLS-1$
			if (resource == null || resource.isEmpty()) {
				module.setData(b);
				module.setAgents(parseAgents (f, n));
			}
			else
			{
				byte newData[] = populateResource(f, resource);
				File moduleFile = createTempFile();
				copyContent(moduleFile, newData);
				module.setData(newData);
				module.setAgents(parseAgents (moduleFile, n));
				moduleFile.delete();
			}
			modules.add(module);
		}
		nodes = (NodeList) xpath.evaluate("/SoffidIAMPlugin/webService", xmlDoc, XPathConstants.NODESET); //$NON-NLS-1$
		for (int i = 0; i < nodes.getLength(); i++) {
			Element n = (Element) nodes.item(i);

			InternalServerPluginModule module = new InternalServerPluginModule();
			
			module.setName(n.getAttribute("name"));//$NON-NLS-1$
			module.setType(ServerPluginModuleType.MODULE_WEBSERVICE);
			String resource = n.getAttribute("resource"); //$NON-NLS-1$
			if (resource == null || resource.isEmpty()) {
				module.setData(b);
			}
			else
			{
				byte newData[] = populateResource(f, resource);
				module.setData(newData);
			}
			modules.add(module);
		}

	}
	
	Collection<AgentDescriptor> parseAgents (File f, Node root) throws IOException, XPathExpressionException, SAXException
	{
		Collection<AgentDescriptor> agents = new LinkedList<AgentDescriptor>();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		
		NodeList nodes = (NodeList) xpath.evaluate("agent", root, XPathConstants.NODESET); //$NON-NLS-1$
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			InternalAgentDescriptor ad = new InternalAgentDescriptor();
			String classV2 = xpath.evaluate("javaClassV2", n);
			if ("-".equals(classV2))
				continue;
			if (classV2 == null || classV2.isEmpty())
				classV2 = xpath.evaluate("javaClass", n);
			if (classV2 == null || classV2.isEmpty())
				continue;
			ad.setClassName(classV2); //$NON-NLS-1$
			ad.setDescription(xpath.evaluate( "name", n)); //$NON-NLS-1$
			String resource = xpath.evaluate("userInterface", n); //$NON-NLS-1$
			if (resource == null || "".equals(resource)) //$NON-NLS-1$
				throw new SAXException(Messages.getString("ServerPluginParser.14")+ad.getDescription()+Messages.getString("ServerPluginParser.15")); //$NON-NLS-1$ //$NON-NLS-2$
			ad.setUserInterface(populateResource(f, resource));
			String enableAccessControl = xpath.evaluate("enableAccessControl", n); //$NON-NLS-1$
			if (enableAccessControl == null || "".equals(enableAccessControl)) //$NON-NLS-1$
				enableAccessControl = "false";//Posem N si és nul //$NON-NLS-1$
			ad.setEnableAccessControl("true".equals(enableAccessControl)); //$NON-NLS-1$

			String authoritativeSource = xpath.evaluate("authoritativeSource", n); //$NON-NLS-1$
			if (authoritativeSource == null || "".equals(authoritativeSource)) //$NON-NLS-1$
				authoritativeSource = "false";//Posem N si és nul //$NON-NLS-1$
			ad.setAuthoritativeSource("true".equals(authoritativeSource)); //$NON-NLS-1$

			String attributeMapping = xpath.evaluate("enableAttributeMapping", n); //$NON-NLS-1$
			if (attributeMapping == null || "".equals(attributeMapping)) //$NON-NLS-1$
				attributeMapping = "false";//Posem N si és nul //$NON-NLS-1$
			ad.setEnableAttributeMapping("true".equals(attributeMapping)); //$NON-NLS-1$

			loadDefaultAttributeMappings (ad, n);

			agents.add(ad);
		}

		return agents;		
	}


	private byte[] populateResource(File f, String resource) throws IOException {
		String resource2 = resource.replace("\\", "/"); //$NON-NLS-1$ //$NON-NLS-2$
		ZipFile zf = new ZipFile(f);
		Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zf.entries();
		while (entries.hasMoreElements()) {
			ZipEntry entry = entries.nextElement();
			if (!entry.isDirectory() && 
					entry.getName().replace("\\", "/").equals(resource2)) { //$NON-NLS-1$ //$NON-NLS-2$
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				InputStream in = zf.getInputStream(entry);
				int read = in.read();
				while (read >= 0) {
					out.write(read);
					read = in.read ();
				}
				return out.toByteArray();
			}
		}
		throw new IOException (String.format (Messages.getString("ServerPluginParser.UnableFIndResource"), resource)); //$NON-NLS-1$
	}

	public void copyContent(File f, byte b[]) throws FileNotFoundException,
			IOException {
		OutputStream out = new FileOutputStream(f);
		out.write(b);
		out.close ();
	}

}

