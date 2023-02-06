package com.soffid.iam.bpm.business;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
import java.util.zip.ZipInputStream;

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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jbpm.JbpmContext;
import org.jbpm.graph.def.Action;
import org.jbpm.graph.def.Event;
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

import com.soffid.iam.bpm.mail.Mail;
import com.soffid.iam.bpm.model.ProcessDefinitionProperty;
import com.soffid.iam.bpm.model.ProcessDefinitionUserRole;
import com.soffid.iam.bpm.model.UserInterface;
import com.soffid.iam.bpm.model.dal.ProcessDefinitionPropertyDal;

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

        // Change notification actions from on-assign to on-create
        
        changeNotifications (processDefinition);
        
        return procesarArchivoXML(documentoXml, processDefinition);
    }

	/**
	 * @param processDefinition
	 */
	private void changeNotifications (ProcessDefinition processDefinition)
	{
		TaskMgmtDefinition taskDef = processDefinition.getTaskMgmtDefinition();
		Map<String, Task> tasks = (Map<String, Task>) taskDef.getTasks();
		if (tasks != null && tasks.values() != null)
		{
			for (Task task : tasks.values())
			{
				Event event = task.getEvent(Event.EVENTTYPE_TASK_ASSIGN);
				if (event != null)
				{
					Iterator it = event.getActions().iterator();
					while (it.hasNext())
					{
						Action action = (Action) it.next();
						if (action.getName().equals(task.getName())
										&& action.getActionDelegation() != null
										&& Mail.class.getName().equals(action //$NON-NLS-1$
														.getActionDelegation()
														.getClassName()))
						{
							it.remove();
							Event event2 = task.getEvent(Event.EVENTTYPE_TASK_CREATE);
							if (event2 == null)
							{
								event2 = new Event(Event.EVENTTYPE_TASK_CREATE);
								task.addEvent(event2);
							}
							event2.addAction(action);
						}
					}
				}
			}
		}
	}

	private Document parseUIDescriptor(ProcessDefinition processDefinition)
			throws ParserConfigurationException, FactoryConfigurationError,
			SAXException, BPMException, DocumentException, IOException {
		
		Document documentoXml = null;
        DocumentBuilder builder = null;

        // turn validation on
        org.dom4j.io.SAXReader reader = new org.dom4j.io.SAXReader(true);
        // set the validation feature to true to report validation errors
        reader.setFeature("http://xml.org/sax/features/validation", true); //$NON-NLS-1$

        // set the validation/schema feature to true to report validation errors
        // against a schema
        reader.setFeature("http://apache.org/xml/features/validation/schema", //$NON-NLS-1$
                true);
        // set the validation/schema-full-checking feature to true to enable
        // full schema, grammar-constraint checking
        reader
                .setFeature(
                        "http://apache.org/xml/features/validation/schema-full-checking", //$NON-NLS-1$
                        true);
        // set the schema
        reader
                .setProperty(
                        "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation", //$NON-NLS-1$
                        "/ui.xsd"); //$NON-NLS-1$
        // set the entity resolver (to load the schema with getResourceAsStream)
        reader.getXMLReader().setEntityResolver(new SchemaLoader());
        reader.setEntityResolver(new SchemaLoader());

        InputStream in = processDefinition.getFileDefinition().getInputStream("ui.xml"); //$NON-NLS-1$
        if (in == null)
        	throw new BPMException(Messages.getString("UserInterfaceBusiness.MissingUIFile"), -1); //$NON-NLS-1$

        reader.read(in);

        builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        return builder.parse(processDefinition.getFileDefinition().getInputStream("ui.xml"));  //$NON-NLS-1$
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
            String tag = (String) xpath.evaluate("/process/tag", documento, //$NON-NLS-1$
                    XPathConstants.STRING);
            if (tag != null) {
                ProcessDefinitionProperty prop = new ProcessDefinitionProperty();
                prop.setProcessDefinitionId(
                		new Long(processDefinition.getId()));
                prop.setName("tag"); //$NON-NLS-1$
                prop.setValue(tag);
                context.getSession().save(prop);
            } else
                tag = ""; //$NON-NLS-1$

            String type = (String) xpath.evaluate("/process/type", documento, //$NON-NLS-1$
                            XPathConstants.STRING);
            if (type != null) {
                ProcessDefinitionProperty prop = new ProcessDefinitionProperty();
                prop.setProcessDefinitionId(
                		new Long(processDefinition.getId()));
                prop.setName("type"); //$NON-NLS-1$
                prop.setValue(type);
                context.getSession().save(prop);
            } else
            	type = ""; //$NON-NLS-1$

            
            String userBased = (String) xpath.evaluate("/process/appliesTo", documento, //$NON-NLS-1$
                            XPathConstants.STRING);
            if (userBased != null) {
                ProcessDefinitionProperty prop = new ProcessDefinitionProperty();
                prop.setProcessDefinitionId(
                		new Long(processDefinition.getId()));
                prop.setName("appliesTo"); //$NON-NLS-1$
                prop.setValue(userBased);
                context.getSession().save(prop);
            }

            NodeList list = (NodeList) xpath.evaluate("/process/task", //$NON-NLS-1$
                    documento, XPathConstants.NODESET);
            for (int index = 0; index < list.getLength(); index++) {
                elemento = list.item(index);

                tarea = (String) xpath.evaluate("@name", elemento, //$NON-NLS-1$
                        XPathConstants.STRING);
                path = (String) xpath.evaluate("file/@path", elemento, //$NON-NLS-1$
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
                    "/process/upgrade/process", documento, //$NON-NLS-1$
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

        String name = (String) xpath.evaluate("name", elemento, //$NON-NLS-1$
                XPathConstants.STRING);
        if (name == null || name.length() == 0)
            name = processDefinition.getName();
        String tagMatch = (String) xpath.evaluate("tag", elemento, //$NON-NLS-1$
                XPathConstants.STRING);
        if (tagMatch == null || tagMatch.length() == 0)
            tagMatch = tag;

        String regex = tagMatch.replaceAll("\\.", "\\\\.").replaceAll("\\?", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ".").replaceAll("\\*", ".*");    //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

        Pattern pattern = Pattern.compile(regex);

        ProcessDefinitionPropertyDal propertyDal = new ProcessDefinitionPropertyDal();
        propertyDal.setContext(context);

        List definitions = context.getGraphSession()
                .findAllProcessDefinitionVersions(name);
        for (Iterator it = definitions.iterator(); it.hasNext();) {
            ProcessDefinition def = (ProcessDefinition) it.next();
            if (def.getId() != processDefinition.getId()) {
                String oldTag = propertyDal.getProcessDefinitionProperty(def
                        .getId(), "tag");  //$NON-NLS-1$
                if (oldTag == null)
                    oldTag = "";  //$NON-NLS-1$

                if (pattern.matcher(oldTag).matches())
                    doDefinitionUpgrade(def, processDefinition, elemento, messages);
            }
            context.getSession().flush();
            context.getSession().clear();
        }
    }

    private void doDefinitionUpgrade(ProcessDefinition source,
            ProcessDefinition target, Node elemento,
            Vector messages)
            throws XPathExpressionException {
        Criteria busqueda = context.getSession().createCriteria(
                ProcessInstance.class);

        busqueda.add(Restrictions.eq("processDefinition", source));  //$NON-NLS-1$
        busqueda.add(Restrictions.isNull("end"));  //$NON-NLS-1$

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
			            "task[@source=$source]/@target", elemento);  //$NON-NLS-1$
		    }
		    if (newTask == null || newTask.length() == 0)
		        newTask = ti.getTask().getName();
		    Task targetTask = target.getTaskMgmtDefinition().getTask(
		            newTask);
		    if (targetTask == null) {
		        String message = String.format(Messages.getString("UserInterfaceBusiness.NotUpgradeTask"), sourceTask, //$NON-NLS-1$
		        		newTask, instance.getId());  
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
		        if (ti.getTask() != null) {
			        boolean exactName = taskName.equals(ti.getTask().getName());
			        Task targetTask = (Task) tasks.get(ti);
			        ti.setTask(targetTask);
			        if (!exactName) {
			            ti.setName(taskName);
			        }
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
		    messages.add (String.format(Messages.getString("UserInterfaceBusiness.UpgradedProcess"), instance.getId()));  //$NON-NLS-1$
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
	        newNodeName = xpath.evaluate("/node[source=$source]/@target",  //$NON-NLS-1$
	                upgradeDefinition);
        }
        if (newNodeName == null || newNodeName.length() == 0)
            newNodeName = nodeName;

        org.jbpm.graph.def.Node newNode = target.getNode(nodeName);
        if (newNode == null) {
            messages.add (String.format(Messages.getString("UserInterfaceBusiness.NotUpgradeNode"), nodeName, newNodeName, //$NON-NLS-1$
            		token.getProcessInstance().getId())); 
            return false;
        }
        translations.put(token, newNode);
        boolean ok = true;
        for (Iterator it = token.getChildren().values().iterator(); ok
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
        log.debug(Messages.getString("UserInterfaceBusiness.ProcessInitiatorRole")); //$NON-NLS-1$
        this.procesarRolDefinicionProceso(documento, "initiator",  //$NON-NLS-1$
        		def);
        log.debug(Messages.getString("UserInterfaceBusiness.ProcessObserverRole")); //$NON-NLS-1$
        this.procesarRolDefinicionProceso(documento, "observer",  //$NON-NLS-1$
        		def);
        log.debug(Messages.getString("UserInterfaceBusiness.ProcessSupervisorRole")); //$NON-NLS-1$
        this.procesarRolDefinicionProceso(documento, "supervisor",  //$NON-NLS-1$
        		def);
    }

    private void procesarRolDefinicionProceso(Document documento,
            String roleName, ProcessDefinition def) throws BPMException {
        Node nodeProceso = null;
        NodeList childs = null;
        Node elementoRolUser = null;
        String userName = null;
        ProcessDefinitionUserRole userRole = null;

        log.debug(Messages.getString("UserInterfaceBusiness.GetRoleElement")); //$NON-NLS-1$
        nodeProceso = documento.getElementsByTagName(roleName).item(0);

        if (nodeProceso != null)
        {
	        childs = nodeProceso.getChildNodes();
	
	        StringBuffer actors = new StringBuffer();
	        log.debug(Messages.getString("UserInterfaceBusiness.ChildView")); //$NON-NLS-1$
	        for (int index = 0; index < childs.getLength(); index++) {
	            elementoRolUser = childs.item(index);
	
	            log.debug(Messages.getString("UserInterfaceBusiness.CheckElementType")); //$NON-NLS-1$
	            if (elementoRolUser.getNodeType() == Node.ELEMENT_NODE
	                    && (elementoRolUser.getNodeName().equals("role") || elementoRolUser  //$NON-NLS-1$
	                            .getNodeName().equals("user"))) {  //$NON-NLS-1$
	                userRole = new ProcessDefinitionUserRole();
	
	                elementoRolUser = childs.item(index);
	
	                userName = elementoRolUser.getAttributes().getNamedItem("name")  //$NON-NLS-1$
	                        .getNodeValue();
	                
	                if (actors.length() >= 0)
	                	actors.append (", ");  //$NON-NLS-1$
	               	actors.append(userName);
	
	                log.debug(Messages.getString("UserInterfaceBusiness.MakeElement")); //$NON-NLS-1$
	                userRole.setProcessDefinitionId(new Long(def.getId()));
	                userRole.setUserRole(userName);
	                userRole.setAppRole(roleName);
	                userRole.setIsUser(new Boolean(!elementoRolUser.getNodeName()
	                        .equals("role")));  //$NON-NLS-1$
	
	                log.debug(Messages.getString("UserInterfaceBusiness.RecordElement")); //$NON-NLS-1$
	                this.context.getSession().save(userRole);
	            }
	        }
	        if (actors.length() > 0)
	        {
	        	TaskMgmtDefinition taskMgmt = def.getTaskMgmtDefinition(); 
	        	Swimlane sl = taskMgmt.getSwimlane(roleName);
	        	if (sl != null)
	        		throw new BPMException(String.format(Messages.getString("UserInterfaceBusiness.AlreadySwimlaneNamed"), roleName), -1);  //$NON-NLS-1$
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
        public static final String FILE_SCHEME = "file://";  //$NON-NLS-1$

        public InputSource resolveEntity(String publicId, String systemId)
                throws SAXException {
            return new InputSource(SchemaLoader.class
                    .getResourceAsStream("/es/caib/bpm/toolkit/xml/ui.xsd"));  //$NON-NLS-1$
        }
    }

    static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";  //$NON-NLS-1$
    static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";  //$NON-NLS-1$
    static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";  //$NON-NLS-1$

    private JbpmContext context = null;
    private static Log log = LogFactory.getLog(UserInterfaceBusiness.class);
	public String[] upgradeProcess(ProcessInstance process) throws XPathExpressionException, ParserConfigurationException, FactoryConfigurationError, SAXException, BPMException, DocumentException, IOException {
		Vector messages = new Vector ();
		ProcessDefinition currentDefinition = process.getProcessDefinition();
		ProcessDefinition newestDefinition = context.getGraphSession().findLatestProcessDefinition(
				currentDefinition.getName());
		if (currentDefinition.getId() == newestDefinition.getId())
			messages.add(new String (Messages.getString("UserInterfaceBusiness.NothingToUpgrade"))); //$NON-NLS-1$
		else
		{
			// Recuperar el tag de origen
	        ProcessDefinitionPropertyDal propertyDal = new ProcessDefinitionPropertyDal();
	        propertyDal.setContext(context);
            String oldTag = propertyDal.getProcessDefinitionProperty(currentDefinition.getId(), "tag");  //$NON-NLS-1$
            String newTag = propertyDal.getProcessDefinitionProperty(newestDefinition.getId(), "tag");  //$NON-NLS-1$
            
            // Recuperar el descriptor xml de migración
			Document d = parseUIDescriptor(newestDefinition);
	        XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			
			// Localizar la ruta de upgrade
		    xpath.setXPathVariableResolver(new DummyVariableResolver(oldTag));
            Node upgrade = (Node) xpath.evaluate(
                    "/process/upgrade/process[tag=$tag]", d,  //$NON-NLS-1$
                    XPathConstants.NODE);
            if (upgrade == null)
            	messages.add (String.format(Messages.getString("UserInterfaceBusiness.ForcingUpgrade"), newTag));  //$NON-NLS-1$
            else
            	messages.add (String.format(Messages.getString("UserInterfaceBusiness.TryingUpgrade"), newTag));  //$NON-NLS-1$
			if (!doProcessUpgrade(process, upgrade, newestDefinition, messages))
			{
				messages.add (Messages.getString("UserInterfaceBusiness.NotUpgradeProcess")); //$NON-NLS-1$
			}
		}
		return (String[]) messages.toArray(new String[messages.size()]);
	}
	
	public String extractVersion (File f) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException
	{
		ZipInputStream zin = new ZipInputStream(new FileInputStream(f));
		ZipEntry zentry = zin.getNextEntry();
		String version = null;
		while (zentry != null)
		{
			if (zentry.getName().equals("ui.xml")) //$NON-NLS-1$
			{
		        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		        Document doc = builder.parse(zin); 
		        XPathFactory factory = XPathFactory.newInstance();
				XPath xpath = factory.newXPath();
				Node versionNode = (Node) xpath.evaluate("/process/tag", doc, XPathConstants.NODE); //$NON-NLS-1$
				if (versionNode != null)
				{
					version = versionNode.getTextContent().trim();
					break;
				}
			}
			zin.closeEntry();
			zentry = zin.getNextEntry();
		}
		return version;
	}

	public String extractName (File f) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException
	{
		ZipInputStream zin = new ZipInputStream(new FileInputStream(f));
		ZipEntry zentry = zin.getNextEntry();
		String version = null;
		while (zentry != null)
		{
			if (zentry.getName().equals("processdefinition.xml")) //$NON-NLS-1$
			{
		        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		        Document doc = builder.parse(zin); 
		        XPathFactory factory = XPathFactory.newInstance();
				XPath xpath = factory.newXPath();
				String name = (String) xpath.evaluate("/process-definition/@name", doc, XPathConstants.STRING); //$NON-NLS-1$
				if (name != null)
				{
					return name;
				}
			}
			zin.closeEntry();
			zentry = zin.getNextEntry();
		}
		return null;
	}
}
