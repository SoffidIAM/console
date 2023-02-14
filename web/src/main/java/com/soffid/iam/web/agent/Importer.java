package com.soffid.iam.web.agent;

import java.io.ByteArrayInputStream;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.zkoss.util.media.Media;

import com.soffid.iam.api.AttributeMapping;
import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.LetterCaseEnum;
import com.soffid.iam.api.ObjectMapping;
import com.soffid.iam.api.ObjectMappingProperty;
import com.soffid.iam.api.ObjectMappingTrigger;
import com.soffid.iam.api.ReconcileTrigger;
import com.soffid.iam.api.SoffidObjectType;

import es.caib.seycon.ng.comu.AttributeDirection;
import es.caib.seycon.ng.comu.SoffidObjectTrigger;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datamodel.DataModelNode;
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
		
		clearMappings(ds);

		NodeList nodes = doc.getElementsByTagName("objectMapping");
		for (int i = 0; i < nodes.getLength(); i++)
		{
			Element n = (Element) nodes.item(i);
			ObjectMapping om = new ObjectMapping();
			
			om.setCondition(n.getAttribute("condition"));
			om.setSystemObject(n.getAttribute("systemObject"));
			om.setSoffidObject(SoffidObjectType.fromString(n.getAttribute("soffidObject")));
			
			// Add object to path
			String path = createObject(ds, om);
			
			addProperties (n, ds, path, om);
			addAttributes(n, ds, path, om);
			addTriggers(n, ds, path, om);
		}
				
		nodes = doc.getElementsByTagName("loadTrigger");
		for (int i = 0; i < nodes.getLength(); i++)
		{
			Element n = (Element) nodes.item(i);
			ReconcileTrigger om = new ReconcileTrigger();
			
			om.setObjectType( SoffidObjectType.fromString( n.getAttribute("objectType")));
			om.setTrigger( SoffidObjectTrigger.fromString( n.getAttribute("trigger")));
			om.setScript(n.getAttribute("script"));

			createReconcileTrigger(ds, om);
			
		}
				
		nodes = doc.getElementsByTagName("metadata");
		for (int i = 0; i < nodes.getLength(); i++)
		{
			Element n = (Element) nodes.item(i);
			DataType dt = new DataType();
			
			dt.setName(n.getAttribute("name"));
			dt.setCustomObjectType( n.getAttribute("customObjectType") );
			dt.setDataObjectType( n.getAttribute("dataObjectType") );
			dt.setFilterExpression( n.getAttribute("filterExpression") );
			dt.setLabel( n.getAttribute("label") );
			dt.setOnChangeTrigger( n.getAttribute("onChangeTrigger") );
			dt.setOnFocusTrigger( n.getAttribute("onFocusTrigger") );
			dt.setOnLoadTrigger( n.getAttribute("onLoadTrigger") );
			dt.setValidationExpression( n.getAttribute("validationExpression") );
			dt.setVisibilityExpression( n.getAttribute("visibilityExpression") );
			if (n.hasAttribute("adminVisibility"))
				dt.setAdminVisibility(AttributeVisibilityEnum.fromString(n.getAttribute("adminVisibility")));
			if (n.hasAttribute("operatorVisibility"))
				dt.setOperatorVisibility(AttributeVisibilityEnum.fromString(n.getAttribute("operatorVisibility")));
			if (n.hasAttribute("userVisibility"))
				dt.setUserVisibility(AttributeVisibilityEnum.fromString(n.getAttribute("userVisibility")));
			if (n.hasAttribute("letterCase"))
				dt.setLetterCase(LetterCaseEnum.fromString(n.getAttribute("letterCase")));
			if (n.hasAttribute("multiValuedRows"))
				dt.setMultiValuedRows( Integer.parseInt(n.getAttribute("multiValuedRows")));
			dt.setMultiValued(Boolean.getBoolean(n.getAttribute("multiValued")));
			dt.setRequired(Boolean.getBoolean(n.getAttribute("required")));
			dt.setOrder(Long.parseLong(n.getAttribute("order")));
			if (n.hasAttribute("size"))
				dt.setSize( Integer.parseInt(n.getAttribute("size")));
			if (n.hasAttribute("type"))
				dt.setType( TypeEnumeration.fromString(n.getAttribute("type")));
			if (n.hasAttribute("unique"))
				dt.setUnique( "true".equals(n.getAttribute("multiValuedRows")));
			
			List<String> l = null;
			NodeList nodes2 = n.getElementsByTagName("value");
			for (int j = 0; j < nodes2.getLength(); j++)
			{
				if (l == null) l = new LinkedList<String>();
				l.add( nodes2.item(i).getTextContent() );
			}
			dt.setValues(l);

			createMetadata(ds, dt);
			
		}
	}

	protected void createMetadata(DataSource ds, DataType dt) throws Exception {
		XPathUtils.createPath(ds, "/metadata", dt);
	}

	protected void createReconcileTrigger(DataSource ds, ReconcileTrigger om) throws Exception {
		XPathUtils.createPath(ds, "/reconcileTrigger", om);
	}

	protected String createObject(DataSource ds, ObjectMapping om) throws Exception {
		String path = XPathUtils.createPath(ds, "/objectMapping", om);
		return path;
	}

	protected void clearMappings(DataSource ds) throws InternalErrorException {
		DataNodeCollection coll = (DataNodeCollection) XPathUtils.getValue(ds, "/objectMapping");
		for (int i = 0; i < coll.getSize(); i++)
		{
			DataModelNode dn = coll.getDataModel(i);
			if (dn != null && ! dn.isDeleted())
				dn.delete();
		}
		
		coll = (DataNodeCollection) XPathUtils.getValue(ds, "/reconcileTrigger");
		for (int i = 0; i < coll.getSize(); i++)
		{
			DataModelNode dn = coll.getDataModel(i);
			if (dn != null && ! dn.isDeleted())
				dn.delete();
		}
	}

	private void addProperties(Element mappingElement, DataSource ds, String path, ObjectMapping om) throws Exception {
		NodeList nodes = mappingElement.getElementsByTagName("property");
		for (int i = 0; i < nodes.getLength(); i++)
		{
			Element n = (Element) nodes.item(i);
			ObjectMappingProperty omp = new ObjectMappingProperty();
			
			omp.setProperty(n.getAttribute("name"));
			omp.setValue(n.getAttribute("value"));
			
			// Add object to path
			createProperty(ds, path, omp, om);
		}
	}

	protected void createProperty(DataSource ds, String path, ObjectMappingProperty omp, ObjectMapping om) throws Exception {
		XPathUtils.createPath(ds, path+"/property", omp);
	}

	private void addTriggers(Element mappingElement, DataSource ds, String path, ObjectMapping om) throws Exception {
		NodeList nodes = mappingElement.getElementsByTagName("trigger");
		for (int i = 0; i < nodes.getLength(); i++)
		{
			Element n = (Element) nodes.item(i);
			ObjectMappingTrigger omp = new ObjectMappingTrigger();
			
			omp.setTrigger(SoffidObjectTrigger.fromString(n.getAttribute("type")));
			omp.setScript(n.getAttribute("script"));
			
			// Add object to path
			createObjectMappingTrigger(ds, path, omp, om);
		}
	}

	protected void createObjectMappingTrigger(DataSource ds, String path, ObjectMappingTrigger omp, ObjectMapping om) throws Exception {
		XPathUtils.createPath(ds, path+"/objectMappingTrigger", omp);
	}

	private void addAttributes(Element mappingElement, DataSource ds, String path, ObjectMapping om) throws Exception {
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
			createAttribute(ds, path, am, om);
		}
	}

	protected void createAttribute(DataSource ds, String path, AttributeMapping am, ObjectMapping om) throws Exception {
		XPathUtils.createPath(ds, path+"/attributeMapping", am);
	}
}
