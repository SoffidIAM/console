package es.caib.bpm.toolkit.xml;

import java.io.FileInputStream;
import java.io.IOException;

import junit.framework.TestCase;

import org.dom4j.DocumentException;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class TestParserJAXP extends TestCase
{
    static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
    static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

    public void testValidarXML() throws SAXException, IOException, DocumentException
	{
//		 turn validation on
		org.dom4j.io.SAXReader reader = new org.dom4j.io.SAXReader(true);
//		 set the validation feature to true to report validation errors
		reader.setFeature("http://xml.org/sax/features/validation",true);
		
		reader.setProperty(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
//		 set the validation/schema feature to true to report validation errors against a schema
		reader.setFeature("http://apache.org/xml/features/validation/schema", true);
//		 set the validation/schema-full-checking feature to true to enable full schema, grammar-constraint checking
		reader.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);
//		 set the schema
		reader.setProperty("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation", "/ui.xsd");
//		 set the entity resolver (to load the schema with getResourceAsStream)
		reader.getXMLReader().setEntityResolver(new SchemaLoader());
		reader.setEntityResolver(new SchemaLoader());
		
//		reader.read(new FileInputStream("src/test/resources/ui.xml"));
	}
	
	public class SchemaLoader implements EntityResolver 
	{
		public static final String FILE_SCHEME = "file://";

		public InputSource resolveEntity(String publicId, String systemId) throws SAXException 
		{
			return new InputSource(SchemaLoader.class.getResourceAsStream("/es/caib/bpm/toolkit/xml/ui.xsd"));
		}
	}
}
