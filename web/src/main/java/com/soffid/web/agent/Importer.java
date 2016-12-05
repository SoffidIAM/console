package com.soffid.web.agent;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.zkoss.util.media.Media;

import es.caib.seycon.ng.comu.AttributeDirection;
import es.caib.seycon.ng.comu.AttributeMapping;
import es.caib.seycon.ng.comu.ObjectMapping;
import es.caib.seycon.ng.comu.ObjectMappingProperty;
import es.caib.seycon.ng.comu.ObjectMappingTrigger;
import es.caib.seycon.ng.comu.SoffidObjectTrigger;
import es.caib.seycon.ng.comu.SoffidObjectType;
import es.caib.zkib.datamodel.DataModelNode;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;

public class Importer {
	public void doImport (Media m, DataSource ds) throws Exception
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc;
		if (m.isBinary() && m.inMemory())
			doc = dBuilder.parse(new ByteArrayInputStream(m.getByteData()));
		else if (m.isBinary())
			doc = dBuilder.parse(m.getStreamData());
		else if (m.inMemory())
			doc = dBuilder.parse(new ByteArrayInputStream(m.getStringData().getBytes("UTF-8")));
		else
			doc = dBuilder.parse(new InputSource(m.getReaderData()));
		
		DataNodeCollection coll = (DataNodeCollection) XPathUtils.getValue(ds, "/objectMapping");
		for (int i = 0; i < coll.getSize(); i++)
		{
			DataModelNode dn = coll.getDataModel(i);
			if (dn != null && ! dn.isDeleted())
				dn.delete();
		}
		
		NodeList nodes = doc.getElementsByTagName("objectMapping");
		for (int i = 0; i < nodes.getLength(); i++)
		{
			Element n = (Element) nodes.item(i);
			ObjectMapping om = new ObjectMapping();
			
			om.setCondition(n.getAttribute("condition"));
			om.setSystemObject(n.getAttribute("systemObject"));
			om.setSoffidObject(SoffidObjectType.fromString(n.getAttribute("soffidObject")));
			
			// Add object to path
			String path = XPathUtils.createPath(ds, "/objectMapping", om);
			
			addProperties (n, ds, path);
			addAttributes(n, ds, path);
			addTriggers(n, ds, path);
		}
				
	}

	private void addProperties(Element mappingElement, DataSource ds, String path) throws Exception {
		NodeList nodes = mappingElement.getElementsByTagName("property");
		for (int i = 0; i < nodes.getLength(); i++)
		{
			Element n = (Element) nodes.item(i);
			ObjectMappingProperty omp = new ObjectMappingProperty();
			
			omp.setProperty(n.getAttribute("name"));
			omp.setValue(n.getAttribute("value"));
			
			// Add object to path
			XPathUtils.createPath(ds, path+"/property", omp);
		}
	}

	private void addTriggers(Element mappingElement, DataSource ds, String path) throws Exception {
		NodeList nodes = mappingElement.getElementsByTagName("trigger");
		for (int i = 0; i < nodes.getLength(); i++)
		{
			Element n = (Element) nodes.item(i);
			ObjectMappingTrigger omp = new ObjectMappingTrigger();
			
			omp.setTrigger(SoffidObjectTrigger.fromString(n.getAttribute("type")));
			omp.setScript(n.getAttribute("script"));
			
			// Add object to path
			XPathUtils.createPath(ds, path+"/objectMappingTrigger", omp);
		}
	}

	private void addAttributes(Element mappingElement, DataSource ds, String path) throws Exception {
		NodeList nodes = mappingElement.getElementsByTagName("attributeMapping");
		for (int i = 0; i < nodes.getLength(); i++)
		{
			Element n = (Element) nodes.item(i);
			AttributeMapping am = new AttributeMapping();
			
			am.setSoffidAttribute(n.getAttribute("soffidAttribute"));
			am.setSystemAttribute(n.getAttribute("systemAttribute"));
			String direction = n.getAttribute("direction");
			
			int index = AttributeDirection.names().indexOf(direction);
			if ( index >= 0)
				am.setDirection(AttributeDirection.fromString(
						(String)AttributeDirection.literals().get(index)));
			// Add object to path
			XPathUtils.createPath(ds, path+"/attributeMapping", am);
		}
	}
}
