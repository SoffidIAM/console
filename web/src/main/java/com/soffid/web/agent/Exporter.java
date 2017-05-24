package com.soffid.web.agent;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import es.caib.seycon.ng.comu.AttributeDirection;
import es.caib.seycon.ng.comu.AttributeMapping;
import es.caib.seycon.ng.comu.ObjectMapping;
import es.caib.seycon.ng.comu.ObjectMappingProperty;
import es.caib.seycon.ng.comu.ObjectMappingTrigger;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.jxpath.JXPathContext;
import es.caib.zkib.jxpath.Pointer;

public class Exporter {
	public String export (DataSource ds) throws ParserConfigurationException, TransformerException, UnsupportedEncodingException
	{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("agent-config");
		doc.appendChild(rootElement);
		
		JXPathContext ctx = ds.getJXPathContext();
		for ( Iterator it = ctx.iteratePointers("/objectMapping");
				it.hasNext();)
		{
			Pointer p = (Pointer) it.next();
			serializeObjectMapping (doc, rootElement, ctx, p);
		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(out);
 
		transformer.transform(source, result);	
		
		return out.toString("UTF-8");
	}

	private void serializeObjectMapping(Document doc, Element rootElement, JXPathContext ctx, Pointer p) {
		DataNode dn = (DataNode) p.getValue();
		ObjectMapping instance = (ObjectMapping) dn.getInstance();
		
		Element e = doc.createElement("objectMapping");
		e.setAttribute("systemObject", instance.getSystemObject());
		e.setAttribute("soffidObject", instance.getSoffidObject().getValue());
		e.setAttribute("condition", instance.getCondition());
		rootElement.appendChild(e);
		
		for ( Iterator it = ctx.iteratePointers(p.asPath()+"/property");
				it.hasNext();)
		{
			Pointer p2 = (Pointer) it.next();
			serializeProperty (doc, e, ctx, p2);
		}
		
		for ( Iterator it = ctx.iteratePointers(p.asPath()+"/attributeMapping");
				it.hasNext();)
		{
			Pointer p2 = (Pointer) it.next();
			serializeAttribute (doc, e, ctx, p2);
		}
		
		for ( Iterator it = ctx.iteratePointers(p.asPath()+"/objectMappingTrigger");
				it.hasNext();)
		{
			Pointer p2 = (Pointer) it.next();
			serializeTrigger (doc, e, ctx, p2);
		}
	}

	private void serializeProperty(Document doc, Element parent, JXPathContext ctx,
			Pointer p) {
		DataNode dn = (DataNode) p.getValue();
		ObjectMappingProperty instance = (ObjectMappingProperty) dn.getInstance();

		Element e = doc.createElement("property");
		e.setAttribute("name", instance.getProperty());
		e.setAttribute("value", instance.getValue());
		parent.appendChild(e);
	}

	private void serializeTrigger(Document doc, Element parent, JXPathContext ctx,
			Pointer p) {
		DataNode dn = (DataNode) p.getValue();
		ObjectMappingTrigger instance = (ObjectMappingTrigger) dn.getInstance();

		Element e = doc.createElement("trigger");
		e.setAttribute("type", instance.getTrigger().toString());
		e.setAttribute("script", instance.getScript());
		parent.appendChild(e);
	}


	private void serializeAttribute(Document doc, Element parent, JXPathContext ctx,
			Pointer p) {
		DataNode dn = (DataNode) p.getValue();
		AttributeMapping instance = (AttributeMapping) dn.getInstance();

		Element e = doc.createElement("attributeMapping");
		e.setAttribute("soffidAttribute", instance.getSoffidAttribute());
		e.setAttribute("systemAttribute", instance.getSystemAttribute());
		AttributeDirection direction = instance.getDirection();
		
		int i = AttributeDirection.literals().indexOf(direction.getValue());
		String name = (String) AttributeDirection.names().get(i);
		e.setAttribute("direction", name);
		parent.appendChild(e);
	}

}
