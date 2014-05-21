package es.caib.bpm.business;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.security.auth.login.LoginException;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathVariableResolver;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jboss.proxy.ejb.RetryInterceptor;
import org.jbpm.JbpmContext;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.module.def.ModuleDefinition;
import org.jbpm.taskmgmt.def.Swimlane;
import org.jbpm.taskmgmt.def.Task;
import org.jbpm.taskmgmt.def.TaskMgmtDefinition;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import es.caib.bpm.dal.ProcessDefinitionPropertyDal;
import es.caib.bpm.entity.ProcessDefinitionProperty;
import es.caib.bpm.entity.ProcessDefinitionUserRole;
import es.caib.bpm.entity.UserInterface;
import es.caib.bpm.exception.BPMException;

public class UserInterfaceBusiness {

    public UserInterfaceBusiness(JbpmContext context) {
        super();
        this.context = context;
    }

    public String[] procesarDefinicionUI(File file,
            ProcessDefinition processDefinition) throws ZipException,
            IOException, ParserConfigurationException,
            FactoryConfigurationError, SAXException, LoginException,
            NamingException, CreateException,
            XPathExpressionException, BPMException, DocumentException {
        Document documentoXml = parseUIDescriptor(processDefinition);

        return procesarArchivoXML(documentoXml, processDefinition);
    }

	private Document parseUIDescriptor(ProcessDefinition processDefinition)
			throws ParserConfigurationException, FactoryConfigurationError,
			SAXException, BPMException, DocumentException, IOException {
		Document documentoXml = null;
        DocumentBuilder builder = null;

        // turn validation on
        org.dom4j.io.SAXReader reader = new org.dom4j.io.SAXReader(true);
        // set the validation feature to true to report validation errors
        reader.setFeature("http://xml.org/sax/features/validation", true);

        // set the validation/schema feature to true to report validation errors
        // against a schema
        reader.setFeature("http://apache.org/xml/features/validation/schema",
                true);
        // set the validation/schema-full-checking feature to true to enable
        // full schema, grammar-constraint checking
        reader
                .setFeature(
                        "http://apache.org/xml/features/validation/schema-full-checking",
                        true);
        // set the schema
        reader
                .setProperty(
                        "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation",
                        "/ui.xsd");
        // set the entity resolver (to load the schema with getResourceAsStream)
        reader.getXMLReader().setEntityResolver(new SchemaLoader());
        reader.setEntityResolver(new SchemaLoader());

        InputStream in = processDefinition.getFileDefinition().getInputStream("ui.xml");
        if (in == null)
        	throw new BPMException("Missing ui.xml file", -1);

        reader.read(in);

        builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        return builder.parse(processDefinition.getFileDefinition().getInputStream("ui.xml")); 
	}

    private String[] procesarArchivoXML(Document documento,
            ProcessDefinition processDefinition) throws IOException,
            LoginException, NamingException, CreateException,
            XPathExpressionException, BPMException {
        Node elemento = null;
        Node elementoAux = null;
        String path = null;
        String tarea = null;
        ByteArrayOutputStream outputStream = null;
        InputStream streamLectura = null;
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();

        try {
            String tag = (String) xpath.evaluate("/process/tag", documento,
                    XPathConstants.STRING);
            if (tag != null) {
                ProcessDefinitionProperty prop = new ProcessDefinitionProperty();
                prop.setProcessDefinitionId(
                		new Long(processDefinition.getId()));
                prop.setName("tag");
                prop.setValue(tag);
                context.getSession().save(prop);
            } else
                tag = "";
            NodeList list = (NodeList) xpath.evaluate("/process/task",
                    documento, XPathConstants.NODESET);
            for (int index = 0; index < list.getLength(); index++) {
                elemento = list.item(index);

                tarea = (String) xpath.evaluate("@name", elemento,
                        XPathConstants.STRING);
                path = (String) xpath.evaluate("file/@path", elemento,
                        XPathConstants.STRING);

                UserInterface ui = new UserInterface();
                ui.setProcessDefinitionId(new Long(processDefinition.getId()));
                ui.setFileName(path);
                ui.setTarea(tarea);
                context.getSession().save(ui);
            }

            // Ajustar los roles
            this.procesarRolesDefinicionProceso(documento, processDefinition);
            
            // Realizar upgrade
            NodeList upgrades = (NodeList) xpath.evaluate(
                    "/process/upgrade/process", documento,
                    XPathConstants.NODESET);
            Vector messages = new Vector();
            for (int index = 0; index < upgrades.getLength(); index++) {
                elemento = upgrades.item(index);
                doUpgrade(processDefinition, tag, elemento, messages);
            }
            return (String[]) messages.toArray(new String[0]);

        } finally {
            if (streamLectura != null) {
                streamLectura.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    private void doUpgrade(ProcessDefinition processDefinition, String tag,
            Node elemento, Vector messages) throws XPathExpressionException {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();

        String name = (String) xpath.evaluate("name", elemento,
                XPathConstants.STRING);
        if (name == null || name.length() == 0)
            name = processDefinition.getName();
        String tagMatch = (String) xpath.evaluate("tag", elemento,
                XPathConstants.STRING);
        if (tagMatch == null || tagMatch.length() == 0)
            tagMatch = tag;

        String regex = tagMatch.replaceAll("\\.", "\\\\.").replaceAll("\\?",
                ".").replaceAll("\\*", ".*");
        Pattern pattern = Pattern.compile(regex);

        ProcessDefinitionPropertyDal propertyDal = new ProcessDefinitionPropertyDal();
        propertyDal.setContext(context);

        List definitions = context.getGraphSession()
                .findAllProcessDefinitionVersions(name);
        for (Iterator it = definitions.iterator(); it.hasNext();) {
            ProcessDefinition def = (ProcessDefinition) it.next();
            if (def.getId() != processDefinition.getId()) {
                String oldTag = propertyDal.getProcessDefinitionProperty(def
                        .getId(), "tag");
                if (oldTag == null)
                    oldTag = "";
                if (pattern.matcher(oldTag).matches())
                    doDefinitionUpgrade(def, processDefinition, elemento, messages);
            }
        }
    }

    private void doDefinitionUpgrade(ProcessDefinition source,
            ProcessDefinition target, Node elemento,
            Vector messages)
            throws XPathExpressionException {
        Criteria busqueda = context.getSession().createCriteria(
                ProcessInstance.class);

        busqueda.add(Restrictions.eq("processDefinition", source));
        busqueda.add(Restrictions.isNull("end"));

        List resultado = busqueda.list();
        for (Iterator it = resultado.iterator(); it.hasNext();) {
            ProcessInstance instance = (ProcessInstance) it.next();
            doProcessUpgrade(instance, elemento, target,
					messages);
        }
    }

	private boolean doProcessUpgrade(ProcessInstance instance, Node elemento,
			ProcessDefinition target, Vector messages)
			throws XPathExpressionException {
		boolean ok = true;

        Collection taskInstances = instance.getTaskMgmtInstance().getTaskInstances();

        XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();

		Hashtable tasks = new Hashtable();
		Hashtable tokens = new Hashtable();
		// Actualizar tareas
		for (Iterator taskIterator = taskInstances.iterator(); taskIterator
		        .hasNext();) {
		    TaskInstance ti = (TaskInstance) taskIterator.next();
		    String sourceTask = ti.getTask().getName();
		    String newTask = null;
		    if (elemento != null) {
			    xpath.setXPathVariableResolver(new DummyVariableResolver(
			            sourceTask));
			    newTask = xpath.evaluate(
			            "task[@source=$source]/@target", elemento);
		    }
		    if (newTask == null || newTask.length() == 0)
		        newTask = ti.getTask().getName();
		    Task targetTask = target.getTaskMgmtDefinition().getTask(
		            newTask);
		    if (targetTask == null) {
		        String message = "Cannot upgrade task " + sourceTask + " to "
		        + newTask + " for process " + instance.getId(); 
		        messages.add (message);
		        ok = false;
		    } else
		        tasks.put(ti, targetTask);
		}
		// Actualizar tokens
		if (ok) {
		    Token token = instance.getRootToken();

		    ok = upgradeToken(token, target, elemento, tokens, messages);
		}
		if (ok) {
		    for (Iterator taskIterator = tasks.keySet().iterator(); taskIterator
		            .hasNext();) {

		        TaskInstance ti = (TaskInstance) taskIterator.next();
		        String taskName = ti.getName();
		        boolean exactName = taskName.equals(ti.getTask().getName());
		        Task targetTask = (Task) tasks.get(ti);
		        ti.setTask(targetTask);
		        if (!exactName) {
		            ti.setName(taskName);
		        }
		    }
		    for (Iterator tokenIterator = tokens.keySet().iterator(); tokenIterator
		            .hasNext();) {
		        Token token = (Token) tokenIterator.next();
		        org.jbpm.graph.def.Node targetNode = (org.jbpm.graph.def.Node) tokens
		                .get(token);
		        token.setNode(targetNode);
		    }
		    instance.setProcessDefinition(target);
		    messages.add ("Upgraded process " + instance.getId());
		}
		return ok;
	}

    private boolean upgradeToken(Token token, ProcessDefinition target,
            Node upgradeDefinition, Hashtable translations, Vector messages)
            throws XPathExpressionException {
        // Buscar el nuevo estado
        if (token.getNode() == null)
            return false;
        String newNodeName = null;
        
        String nodeName = token.getNode().getName();
        if (upgradeDefinition != null) {
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();
	        xpath.setXPathVariableResolver(new DummyVariableResolver(nodeName));
	        newNodeName = xpath.evaluate("/node[source=$source]/@target",
	                upgradeDefinition);
        }
        if (newNodeName == null || newNodeName.length() == 0)
            newNodeName = nodeName;

        org.jbpm.graph.def.Node newNode = target.getNode(nodeName);
        if (newNode == null) {
            messages.add ("Cannot upgrade node " + nodeName + " to " + newNodeName
                    + " for process " + token.getProcessInstance().getId());
            return false;
        }
        translations.put(token, newNode);
        boolean ok = true;
        for (Iterator it = token.getChildren().entrySet().iterator(); ok
                && it.hasNext();) {
            Token child = (Token) it.next();
            ok = upgradeToken(child, target, upgradeDefinition, translations, messages);
        }
        return ok;
    }

    class DummyVariableResolver implements XPathVariableResolver {
        String sourceTask;

        public DummyVariableResolver(String sourceTask) {
            super();
            this.sourceTask = sourceTask;
        }

        public Object resolveVariable(QName variableName) {
            return sourceTask;
        }

    }

    private void procesarRolesDefinicionProceso(Document documento,
            ProcessDefinition def) throws BPMException {
        log.debug("Procesamos el rol initiator");
        this.procesarRolDefinicionProceso(documento, "initiator",
        		def);
        log.debug("Procesamos el rol observer");
        this.procesarRolDefinicionProceso(documento, "observer",
        		def);
        log.debug("Procesamos el rol supervisor");
        this.procesarRolDefinicionProceso(documento, "supervisor",
        		def);
    }

    private void procesarRolDefinicionProceso(Document documento,
            String roleName, ProcessDefinition def) throws BPMException {
        Node nodeProceso = null;
        NodeList childs = null;
        Node elementoRolUser = null;
        String userName = null;
        ProcessDefinitionUserRole userRole = null;

        log.debug("Traemos el elemento del rol");
        nodeProceso = documento.getElementsByTagName(roleName).item(0);

        if (nodeProceso != null)
        {
	        childs = nodeProceso.getChildNodes();
	
	        StringBuffer actors = new StringBuffer();
	        log.debug("Recorremos los hijos");
	        for (int index = 0; index < childs.getLength(); index++) {
	            elementoRolUser = childs.item(index);
	
	            log.debug("Verificamos que sea un elemento tipo user o role");
	            if (elementoRolUser.getNodeType() == Node.ELEMENT_NODE
	                    && (elementoRolUser.getNodeName().equals("role") || elementoRolUser
	                            .getNodeName().equals("user"))) {
	                userRole = new ProcessDefinitionUserRole();
	
	                elementoRolUser = childs.item(index);
	
	                userName = elementoRolUser.getAttributes().getNamedItem("name")
	                        .getNodeValue();
	                
	                if (actors.length() >= 0)
	                	actors.append (", ");
	               	actors.append(userName);
	
	                log.debug("Generamos el elemento");
	                userRole.setProcessDefinitionId(new Long(def.getId()));
	                userRole.setUserRole(userName);
	                userRole.setAppRole(roleName);
	                userRole.setIsUser(new Boolean(!elementoRolUser.getNodeName()
	                        .equals("role")));
	
	                log.debug("Grabamos");
	                this.context.getSession().save(userRole);
	            }
	        }
	        if (actors.length() > 0)
	        {
	        	TaskMgmtDefinition taskMgmt = def.getTaskMgmtDefinition(); 
	        	Swimlane sl = taskMgmt.getSwimlane(roleName);
	        	if (sl != null)
	        		throw new BPMException("There already is a swimlane named "+roleName, -1);
	        	sl = new Swimlane (roleName);
	        	taskMgmt.addSwimlane(sl);
	        	sl.setPooledActorsExpression(actors.toString());
	        }
        }
        

    }

    public JbpmContext getContext() {
        return context;
    }

    public void setContext(JbpmContext context) {
        this.context = context;
    }

    public class SchemaLoader implements EntityResolver {
        public static final String FILE_SCHEME = "file://";

        public InputSource resolveEntity(String publicId, String systemId)
                throws SAXException {
            return new InputSource(SchemaLoader.class
                    .getResourceAsStream("/es/caib/bpm/toolkit/xml/ui.xsd"));
        }
    }

    static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
    static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

    private JbpmContext context = null;
    private static Logger log = Logger.getLogger(UserInterfaceBusiness.class);
	public String[] upgradeProcess(ProcessInstance process) throws XPathExpressionException, ParserConfigurationException, FactoryConfigurationError, SAXException, BPMException, DocumentException, IOException {
		Vector messages = new Vector ();
		ProcessDefinition currentDefinition = process.getProcessDefinition();
		ProcessDefinition newestDefinition = context.getGraphSession().findLatestProcessDefinition(
				currentDefinition.getName());
		if (currentDefinition.getId() == newestDefinition.getId())
			messages.add(new String ("There is nothing to upgrade"));
		else
		{
			// Recuperar el tag de origen
	        ProcessDefinitionPropertyDal propertyDal = new ProcessDefinitionPropertyDal();
	        propertyDal.setContext(context);
            String oldTag = propertyDal.getProcessDefinitionProperty(currentDefinition.getId(), "tag");
            String newTag = propertyDal.getProcessDefinitionProperty(newestDefinition.getId(), "tag");
            
            // Recuperar el descriptor xml de migración
			Document d = parseUIDescriptor(newestDefinition);
	        XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			
			// Localizar la ruta de upgrade
		    xpath.setXPathVariableResolver(new DummyVariableResolver(oldTag));
            Node upgrade = (Node) xpath.evaluate(
                    "/process/upgrade/process[tag=$tag]", d,
                    XPathConstants.NODE);
            if (upgrade == null)
            	messages.add ("Forcing upgrade to "+newTag);
            else
            	messages.add ("Trying upgrade to "+newTag);
			if (!doProcessUpgrade(process, upgrade, newestDefinition, messages))
			{
				messages.add ("Process not upgraded");
			}
		}
		return (String[]) messages.toArray(new String[messages.size()]);
	}
}
