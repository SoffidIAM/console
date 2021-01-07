package com.soffid.iam.web.agent;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.zkoss.idom.Document;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.Command;
import org.zkoss.zk.au.ComponentCommand;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Html;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Row;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.AccountStatus;
import com.soffid.iam.api.AgentDescriptor;
import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.api.Server;
import com.soffid.iam.api.SoffidObjectType;
import com.soffid.iam.api.System;
import com.soffid.iam.api.UserAccount;
import com.soffid.iam.api.UserType;
import com.soffid.iam.doc.exception.DocumentBeanException;
import com.soffid.iam.service.TaskHandler;
import com.soffid.iam.service.ejb.DispatcherService;
import com.soffid.iam.sync.engine.intf.DebugTaskResults;
import com.soffid.iam.utils.AutoritzacionsUsuari;
import com.soffid.iam.web.SearchAttributeDefinition;
import com.soffid.iam.web.SearchDictionary;
import com.soffid.iam.web.component.AttributeSearchBox;
import com.soffid.iam.web.component.FileDump;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.SearchBox;
import com.soffid.iam.web.popup.FileUpload2;

import es.caib.seycon.ng.comu.ServerType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataDatebox;
import es.caib.zkib.component.DataListbox;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.component.Select;
import es.caib.zkib.component.Switch;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datamodel.DataModelNode;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datamodel.xml.XmlDataNode;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathContext;
import es.caib.zkib.zkiblaf.Missatgebox;

public class AgentHandler extends FrameHandler {
	Log log = LogFactory.getLog(getClass());
	
	private boolean canCreateAccessControlAgent;
	private boolean canQueryAccessControlAgent;
	private Tab accessControlTab;
	private Tab basicTab;
	private Tab attributeMappingTab;
	private Tab metadataTab;
	private DataTable gridControlAccess;
	private DataTable listbox;
	private DataTable userTypeTable;
	private Component form;
	private Timer refreshTasksTimer;
	private Component missatge;
	private Window startTaskWindow;
	private ScheduledTask currentTask;
	private Tab workflowTab;
	private String selectedProcess;
	private DebugTaskResults propagateLog;

	public AgentHandler() throws InternalErrorException {
		super();
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
		getNamespace().setVariable("canCreateAgent", AutoritzacionsUsuari.hasCreateAgent(), true);
		getNamespace().setVariable("canDeleteAgent", AutoritzacionsUsuari.hasDeleteAgent(), true);
		getNamespace().setVariable("canUpdateAgent", AutoritzacionsUsuari.hasUpdateAgent(), true);
		getNamespace().setVariable("canQueryAgent", AutoritzacionsUsuari.hasQueryAgent(), true);
		getNamespace().setVariable("canPropagateAgentUsers", AutoritzacionsUsuari.hasPropagateAgentUsers(), true);
		getNamespace().setVariable("canPropagateAgentUsers", AutoritzacionsUsuari.hasPropagateAgentUsers(), true);
		getNamespace().setVariable("canPropagateAgentGroups", AutoritzacionsUsuari.hasPropagateAgentGroups(), true);
		getNamespace().setVariable("canModifyAgent",
				AutoritzacionsUsuari.hasCreateAgent() || AutoritzacionsUsuari.hasUpdateAgent(), true);
		canCreateAccessControlAgent = AutoritzacionsUsuari.hasCreateAccessControlAgent();
		getNamespace().setVariable("canCreateAccessControlAgent", canCreateAccessControlAgent, true);
		getNamespace().setVariable("canDeleteAccessControlAgent", AutoritzacionsUsuari.hasDeleteAccessControlAgent(),
				true);
		getNamespace().setVariable("canUpdateAccessControlAgent", AutoritzacionsUsuari.hasUpdateAccessControlAgent(),
				true);
		canQueryAccessControlAgent = AutoritzacionsUsuari.hasQueryAccessControlAgent();
		getNamespace().setVariable("canQueryAccessControlAgent", canQueryAccessControlAgent, true);
		getNamespace().setVariable("canModifyAccessControl",
				canCreateAccessControlAgent || AutoritzacionsUsuari.hasUpdateAccessControlAgent(), true);
		getNamespace().setVariable("canSetAccessControl", AutoritzacionsUsuari.hasSetAccessControlAgent(), true);
		
	}

	public void addNew() throws Exception {
		super.addNew();
		setVisibleControlAcces(false);
		setVisibleAttributeMapping(false);
		setVisibleMetadata(false);
		verificaControlAcces();
	}

	public void verificaControlAcces() {
		// l'amaguem per defecte
		setVisibleControlAcces(false);

		// si no pot veure el control d'accés sortim
		if (!canQueryAccessControlAgent)
			return;

		// Ací obtenim el nom de l'agent per veure la seua classe
		Object elem = XPathUtils.getValue(getListbox(), ".");
		if (elem instanceof es.caib.zkib.datamodel.xml.XmlDataNode) {
			Object obj = ((DataNode) elem).getInstance();
			if (obj instanceof System) {
				System agent = (System) obj;
				String nomClass = agent.getClassName();
				String codiAgent = agent.getName();
				getModel().getVariables().declareVariable("codiAgent", codiAgent);
				try {
					// Mostramos o no el control d'accés segons el plugin
					Boolean cacActiu = (Boolean) getModel().getJXPathContext()
							.getValue("/plugin[className='" + nomClass + "']/enableAccessControl");
					// Missatgebox.info ("cacActiu= "+cacActiu);
					if (cacActiu != null && cacActiu) { // es boolea
						setVisibleControlAcces(true);
						carregaControlAcces();
					}
				}

				catch (Throwable th) {
					// Missatgebox.error ("error "+th.getMessage());
					setVisibleControlAcces(false);
				}

			}
		}
	}

	public void setVisibleControlAcces(boolean visible) {
		if (visible == false && accessControlTab.isSelected())
			basicTab.setSelected(true);
		accessControlTab.setVisible(visible);
	}

	public void setVisibleAttributeMapping(boolean visible) {
		if (visible == false && attributeMappingTab.isSelected())
			basicTab.setSelected(true);
		attributeMappingTab.setVisible(visible);
	}

	public void setVisibleMetadata(boolean visible) {
		if (visible == false && metadataTab.isSelected())
			basicTab.setSelected(true);
		metadataTab.setVisible(visible);
	}

	public void carregaControlAcces() throws Exception {
		((DataNodeCollection) getModel().getValue("/agent/controlAcces")).refresh();
		gridControlAccess.setDataPath("listbox:/controlAcces");
	}

	public void propagaCanvisAgent_Usuaris() {
		Object elem = listbox.getJXPathContext().getValue(".");
		if (elem instanceof es.caib.zkib.datamodel.xml.XmlDataNode) {
			final XmlDataNode node = (XmlDataNode) elem;
			final com.soffid.iam.api.System system = (com.soffid.iam.api.System) node.getInstance();
			Missatgebox.confirmaOK_CANCEL(org.zkoss.util.resource.Labels.getLabel("agents.Usuaris"),
					org.zkoss.util.resource.Labels.getLabel("agents.PropagarUsuaris"), (evt) -> {
						if ("onOK".equals(evt.getName())) {
							EJBLocator.getDispatcherService().porpagateUsersDispatcher(system.getName());
						}
					});
		} else {
			Missatgebox.avis(org.zkoss.util.resource.Labels.getLabel("agents.Select"));
		}
	}

	public void propagaCanvisAgent_Rols() {
		Object elem = listbox.getJXPathContext().getValue(".");
		if (elem instanceof es.caib.zkib.datamodel.xml.XmlDataNode) {
			final XmlDataNode node = (XmlDataNode) elem;
			final com.soffid.iam.api.System system = (com.soffid.iam.api.System) node.getInstance();
			Missatgebox.confirmaOK_CANCEL(org.zkoss.util.resource.Labels.getLabel("agents.Usuaris"),
					org.zkoss.util.resource.Labels.getLabel("agents.PropagarUsuaris"), (evt) -> {
						if ("onOK".equals(evt.getName())) {
							EJBLocator.getDispatcherService().propagateDispatcherRoles(system.getName());
						}
					});
		} else {
			Missatgebox.avis(org.zkoss.util.resource.Labels.getLabel("agents.Select"));
		}
	}

	public void propagaCanvisAgent_Groups() {
		Object elem = listbox.getJXPathContext().getValue(".");
		if (elem instanceof es.caib.zkib.datamodel.xml.XmlDataNode) {
			final XmlDataNode node = (XmlDataNode) elem;
			final com.soffid.iam.api.System system = (com.soffid.iam.api.System) node.getInstance();
			Missatgebox.confirmaOK_CANCEL(org.zkoss.util.resource.Labels.getLabel("agents.Usuaris"),
					org.zkoss.util.resource.Labels.getLabel("agents.PropagarUsuaris"), (evt) -> {
						if ("onOK".equals(evt.getName())) {
							EJBLocator.getDispatcherService().propagateDispatcherGroups(system.getName());
						}
					});
		} else {
			Missatgebox.avis(org.zkoss.util.resource.Labels.getLabel("agents.Select"));
		}
	}

	public void seleccionaTipusUsuari(String relacioLaboral) {
		if (relacioLaboral != null)
		{
			List<String> tipusUsuari = Arrays.asList(relacioLaboral.split(","));
			ListModel model = userTypeTable.getModel();
			List<Integer> selected = new LinkedList<Integer>();
			for ( int i = 0; i < model.getSize(); i++)
			{
				DataNode dn = (DataNode) model.getElementAt(i);
				UserType ut = (UserType) dn.getInstance();
				if ( tipusUsuari.contains(ut.getCode()))
				{
					selected.add(new Integer(i));
				}
			}
			int[] selectedArray = new int[selected.size()];
			for (int i = 0; i < selected.size(); i++) 
				selectedArray[i] = selected.get(i).intValue();
			userTypeTable.setSelectedIndex(selectedArray);
		}
	}


	public void delete() {
		Missatgebox.confirmaOK_CANCEL(org.zkoss.util.resource.Labels.getLabel("agents.DeleteAgentAccounts"),
			org.zkoss.util.resource.Labels.getLabel("process.warning"),
				(evt) -> {
					if ("onOK".equals(evt.getName())) {
						Missatgebox.confirmaOK_CANCEL(org.zkoss.util.resource.Labels.getLabel("agents.DeleteAgentRoles"),
								org.zkoss.util.resource.Labels.getLabel("process.warning"),
								(evt2) -> {
									if ("onOK".equals(evt2.getName())) 
										listbox.delete();
								});
					}
				});
	}

	public void afterCompose() {
		super.afterCompose();
		accessControlTab = (Tab) getFellow("r_controlAcces");
		basicTab = (Tab) getFellow("r_basica");
		attributeMappingTab = (Tab) getFellow("r_attributeMapping");
		metadataTab = (Tab) getFellow("r_metadata");
		gridControlAccess = (DataTable) getFellow("gridControlAccess");
		listbox = (DataTable) getFellow("listbox");
		userTypeTable = (DataTable) getFellow("lbTipusUsuari");
		form = getFellow("form");
		refreshTasksTimer = (Timer) getFellow("refreshTasksTimer");
		missatge = getFellow("missatge");
		startTaskWindow = (Window) getFellow("startTaskWindow");
		workflowTab = (Tab) getFellow("r_workflow");

		getModel().addEventListener("onCommit", 
				(evt) -> onChangeClass());
		
		
		SearchBox searchBox = (SearchBox) getFellow("searchBox");
		SearchDictionary dictionary = searchBox.getDictionary();
		SearchAttributeDefinition att = findAttribute(dictionary, "url");
		if (att != null) {
			
			AttributeSearchBox box = searchBox.addAttribute("url");
			
			Set<String> values = new HashSet<>();
			Iterator<String> iterator = att.getValues().iterator();
			iterator.next();
			while (iterator.hasNext())
				values.add (iterator.next());
			box.setSelectedValues(values);
		}			
		searchBox.search();
		
		DataNodeCollection servers = (DataNodeCollection) getModel().getValue("/server");
		if (servers.isEmpty())
		{
			Missatgebox.avis("Warning: There is no sync server configured yet. Please download the sync-server software and execute the configure command on it");
		} else {
			DataNodeCollection plugins = (DataNodeCollection) getModel().getValue("/plugin");
			if (plugins.getSize() <= 1)
			{
				Missatgebox.avis("Warning: There is no connector loaded. Please load them from the plugins management page");
			}

		}
	}

	private SearchAttributeDefinition findAttribute(SearchDictionary dictionary, String name) {
		for (SearchAttributeDefinition att: dictionary.getAttributes()) {
			if (att.getName().equals(name))
				return att;
		}
		return null;
	}

	@Override
	public void onChangeForm(Event evt) throws Exception
	{
		super.onChangeForm(evt);
		onEnableManualAccount();
		try {
			JXPathContext ctx = listbox.getJXPathContext();
			DataNode dn = (DataNode) ctx.getValue("/");
			
			System system = (System) dn.getInstance();
			((Textbox)getFellow("agentName")).setDisabled(!dn.isNew());

			userTypeTable.setSelectedIndex(new int[0]);
			
			// Agent existent:
			if (!dn.isNew()) 
			{
				// Cargamos los checkboxes del tipo de usuario
				String relacioLaboral = (String) ctx.getValue("@userTypes");
				seleccionaTipusUsuari(relacioLaboral);
				
				String url = (String) ((Select) getFellow("url")).getSelectedValue();
				if(url == null || url.trim().length() == 0)
				{
					( (Button) getFellow("imgPropaga")).setDisabled(true);
				}
				else
				{
					( (Button) getFellow("imgPropaga")).setDisabled(false);
				}
			} 
			((Intbox) getFellow("agentThreadsBox")).setDisabled(Boolean.TRUE.equals ( system.getSharedDispatcher() ) );
			
			String taskMode = com.soffid.iam.utils.ConfigurationCache.getProperty("soffid.task.mode");
			if (taskMode == null || "auto".equals(taskMode))
			{
				((Row)getFellow("serverStatusRow")).setVisible(true);
				((Label)getFellow("serverStatusMessage")).setValue( org.zkoss.util.resource.Labels.getLabel("parametres.zul.mode.auto") );
			}
			else
				((Row)getFellow("serverStatusRow")).setVisible(false);
			// Disable when the class is not defined
			String javaClass = system.getClassName();
			Select select = (Select) getFellow("cbClassDescription");
			if (javaClass != null) {
				select.setDisabled(true);
				for ( DataNode serverPlugin : (Collection<DataNode>) getModel().getJXPathContext().getValue("/plugin"))
				{
					AgentDescriptor ad = (AgentDescriptor) serverPlugin.getInstance();
					if (ad.getClassName().equals(javaClass))
					{
						select.setDisabled(false);
						onChangeClass();
					}
				}
			} else {
				setVisibleWorkflows(false);
				setVisibleAttributeMapping(false);
				select.setDisabled(false);
			}
			// Task status
			displayTasksStatus ();
			// Access control
			verificaControlAcces();
			missatge.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void displayTasksStatus () {
		for (String type: new String[] { "reconcile", "import", "impact"} ) {
			Boolean active = false;
			Boolean error = false;
			java.util.Calendar last1 = null;
			java.util.Calendar last2 = null;
			
			try {
				active = (Boolean) XPathUtils.getValue(form, "tasks[1]/"+type+"Task/active");
				error =  (Boolean) XPathUtils.getValue(form, "tasks[1]/"+type+"Task/error");
				last1 = (Calendar) XPathUtils.getValue(form, "tasks[1]/"+type+"Task/lastExecution");
				last2 = (Calendar) XPathUtils.getValue(form, "tasks[1]/"+type+"Task/lastEnd");
				form.getFellow (type+"Div"). setVisible(true);
			} catch (Exception e) {
				form.getFellow (type+"Div"). setVisible(false);
				// Ignore
			}
			Image statusComponent = (Image) form.getFellow (type+"Status");
			DataDatebox lastComponent = (DataDatebox) form.getFellow (type+"Last");
			Label labelComponent = (Label) form.getFellow (type+"Label");
			DataDatebox lastEndComponent = (DataDatebox) form.getFellow (type+"LastEnd");
			Label separator = (Label) form.getFellow (type+"Separator");
			
			if ( active != null && active.booleanValue())
			{
				statusComponent.setVisible(true);
				statusComponent.setSrc("/img/wait.gif");
				lastComponent.setVisible(true);
				lastComponent.setValue(last1.getTime());
				labelComponent.setVisible(true);
				separator.setVisible(false);
				lastEndComponent.setVisible(false);
			}
			else if ( error != null && error.booleanValue())
			{
				statusComponent.setVisible(true);
				statusComponent.setSrc ("/img/warning.svg");
				lastComponent.setVisible(true);
				lastComponent.setValue(last1.getTime());
				labelComponent.setVisible(true);
				separator.setVisible(true);
				lastEndComponent.setVisible(true);
				lastEndComponent.setValue(last2.getTime());
			}
			else if ( error != null && last2 != null)
			{
				statusComponent.setSrc ("/img/ok.svg");
				statusComponent.setVisible(true);
				lastComponent.setVisible(true);
				lastComponent.setValue(last1.getTime());
				labelComponent.setVisible(true);
				separator.setVisible(true);
				lastEndComponent.setVisible(true);
				lastEndComponent.setValue(last2.getTime());
			}
			else 
			{
				statusComponent.setVisible(false);
				lastComponent.setVisible(false);
				labelComponent.setVisible(false);
				separator.setVisible(false);
				lastEndComponent.setVisible(false);
			}
		}
	}
	
	public void refreshTasks () {
		try {
			es.caib.zkib.datamodel.DataNodeCollection coll = (DataNodeCollection) XPathUtils.getValue(form, "tasks");
			if (coll != null)
			{
				coll .refresh();
				displayTasksStatus();
			}
		} catch (Exception e) {
			refreshTasksTimer.stop();
		}
	}
	
	public void onEnableManualAccount ()
	{
		if (((Switch)getFellow("detall_manual")).isChecked())
		{
			((Row)getFellow("rols_row")).setVisible(false);
			((Row)getFellow("r_tipusUsuari")).setVisible(false);
			((Row)getFellow("groups_row")).setVisible(false);
		}
		else
		{
			((Row)getFellow("rols_row")).setVisible(true);
			((Row)getFellow("r_tipusUsuari")).setVisible(true);
			((Row)getFellow("groups_row")).setVisible(true);
		}
		missatge.setVisible(true);
	}
	
	
	public void onChangeClass() {
		//System.out.println("On change class");
		Select cb = (Select) getFellow("cbClassDescription");
		String value = (String) cb.getSelectedValue();
		es.caib.zkib.jxpath.JXPathContext ctx = getModel().getJXPathContext ();
		Row authoritativeRow = (Row)getFellow("row_authoritative");
		authoritativeRow.setVisible(false); 
		boolean isReplica = false;
		Div customAgentProperties = (Div) getFellow("customAgentProperties");
		if (value==null || "".equals(value)) 
		{
			customAgentProperties.setVisible(false);
		}
		else
		{
			customAgentProperties.getChildren().clear ();
			try {
				DataNode dn = (DataNode) ctx.getValue("/plugin[className='"+value+"']");
				if (dn == null) {
					customAgentProperties.setVisible(true);
					setVisibleAttributeMapping (false);
					setVisibleWorkflows(false);
					authoritativeRow.setVisible( true ); 
				} else {
					AgentDescriptor ad = (AgentDescriptor) dn.getInstance();
					
					authoritativeRow.setVisible( ad.isAuthoritativeSource() ); 

					byte[] data = ad.getUserInterface();
	
					// Clear methodDescriptor variable
					getPage().setVariable("methodDescriptor", null);
	
					//System.out.println("AgentDescriptor = "+data.getClass().getName());
					java.io.ByteArrayInputStream in = new java.io.ByteArrayInputStream(data);
					Document xmlDoc = new org.zkoss.idom.input.SAXBuilder(false, false).build(in);
					Executions.getCurrent().createComponentsDirectly(new String(data, "UTF-8"), "zul", customAgentProperties, new java.util.HashMap());
					customAgentProperties.setVisible(true);
					Long agentId = (Long) XPathUtils.getValue(form, "@id");
					setVisibleAttributeMapping (agentId != null && ad.isEnableAttributeMapping());
					DataModelCollection coll = dn.getListModel("pluginWorkflow");
					if (coll == null || coll.getSize() == 0) {
						setVisibleWorkflows(false);
					} else {
						setVisibleWorkflows(true);
						fillWorkflowsTable (coll);
					}
				}
			}
			catch (Exception e) {
				log.warn("Error generating custom properties page", e);
				customAgentProperties.setVisible(true);
				setVisibleAttributeMapping (false);
				setVisibleWorkflows(false);
			}
			setVisibleMetadata (true);
			
		}
	}
	
	private void fillWorkflowsTable(DataModelCollection coll) {
		JSONArray data = new JSONArray();
		for ( int i = 0; i < coll.getSize(); i++) {
			DataNode node = (DataNode) coll.getDataModel(i);
			String name = (String) node.get("name");
			JSONObject o = new JSONObject();
			o.put("name", name);
			data.put(o);
		}
		DataTable workflows = (DataTable) getFellow("workflowsGrid");
		workflows.setData(data);
	}

	private void setVisibleWorkflows(boolean visible) {
		if (visible == false && workflowTab.isSelected())
			basicTab.setSelected(true);
		workflowTab.setVisible(visible);
	}
	
	
	public void closeWorkflowWindow(Event event) {
		Window w = (Window) getFellow("workflowWindow");
		w.setVisible(false);
	}
	
	public void editWorkflow(Event event) throws UnsupportedEncodingException {
		DataTable dt = (DataTable) event.getTarget();
		int pos = dt.getSelectedIndex();
		String className = (String) XPathUtils.getValue(getForm(), "className");
		es.caib.zkib.jxpath.JXPathContext ctx = getModel().getJXPathContext ();
		byte[] image = (byte[]) ctx.getValue("/plugin[className='"+className+"']/pluginWorkflow["+(pos+1)+"]/image");
		selectedProcess = (String) ctx.getValue("/plugin[className='"+className+"']/pluginWorkflow["+(pos+1)+"]/name");
		
		Window workflowWindow = (Window) getFellow("workflowWindow");
		workflowWindow.setTitle(Labels.getLabel("agents.zul.edit-workflow", new Object[] {selectedProcess}));
		Html html = (Html) workflowWindow.getFellow("content");
		html.setContent(new String(image,"UTF-8"));
		
		// Set test button
		workflowWindow.getFellow("testRowLabel1").setVisible(true);
		workflowWindow.getFellow("testRowTextbox1").setVisible(true);						
		workflowWindow.getFellow("testButton").setVisible(true);
		((Textbox)workflowWindow.getFellow("testRowTextbox1")).setValue("");						
		((Textbox)workflowWindow.getFellow("testRowTextbox2")).setValue("");						
		if (selectedProcess.equals( "UpdateAccount"))
		{
			((Label)workflowWindow.getFellow("testRowLabel1")).setValue("Account: ");
			workflowWindow.getFellow("testRowLabel2").setVisible(false);						
			workflowWindow.getFellow("testRowTextbox2").setVisible(false);						
		}
		else if (selectedProcess.equals("UpdateUser"))
		{
			((Label)workflowWindow.getFellow("testRowLabel1")).setValue("User: ");
		}
		else
		{
			workflowWindow.getFellow("testRowLabel1").setVisible(false);
			workflowWindow.getFellow("testRowTextbox1").setVisible(false);						
			workflowWindow.getFellow("testRowLabel2").setVisible(false);
			workflowWindow.getFellow("testRowTextbox2").setVisible(false);						
			workflowWindow.getFellow("testButton").setVisible(false);
		}

		workflowWindow.doHighlighted();
	}

	public void doWorkflowTest(Event ev) throws InternalErrorException, NamingException, CreateException, CommitException {
		getModel().commit();
		DebugTaskResults r = null;
		if (selectedProcess.equals( "UpdateAccount"))
		{
			Window workflowWindow = (Window) getFellow("workflowWindow");
			String user = ((Textbox) workflowWindow.getFellow("testRowTextbox1")).getValue();						
			String system = (String) XPathUtils.getValue(getForm(), "name");

			boolean warnDelete = false;
			Account acc = EJBLocator.getAccountService().findAccount(user, system);
			if (acc != null)
			{
				if (acc.getStatus() == AccountStatus.REMOVED)
					warnDelete = true;
			} else {
				warnDelete = true;
			}
			if (warnDelete)
				Missatgebox.confirmaYES_NO(Labels.getLabel("agents.zul.warnDelete"), 
						(event) -> {
							if (event.getName().equals("onYes")) {
								testPropagateObject(system, SoffidObjectType.OBJECT_ACCOUNT, user, null);
							}
						});
			else
				testPropagateObject(system, SoffidObjectType.OBJECT_ACCOUNT, user, null);
		} else if (selectedProcess.equals( "UpdateUser"))
		{
			Window workflowWindow = (Window) getFellow("workflowWindow");
			String user = ((Textbox) workflowWindow.getFellow("testRowTextbox1")).getValue();						
			String system = (String) XPathUtils.getValue(getForm(), "name");

			boolean warnDelete = false;
			Account acc = EJBLocator.getAccountService().findAccount(user, system);
			if (acc != null)
			{
				if (acc.getStatus() == AccountStatus.REMOVED)
					warnDelete = true;
			} else {
				for (UserAccount uacc: EJBLocator.getAccountService().findUsersAccounts(user, system)) {
					if (uacc.getStatus() == AccountStatus.REMOVED)
						warnDelete = true;
				}
			}
			if (warnDelete)
				Missatgebox.confirmaYES_NO(Labels.getLabel("agents.zul.warnDelete"), 
						(event) -> {
							if (event.getName().equals("onYes")) {
								testPropagateObject (system, SoffidObjectType.OBJECT_USER, user, null);
							}
						});
			else
				testPropagateObject (system, SoffidObjectType.OBJECT_USER, user, null);
		}
		
	}
	
	public void downloadPropagateLog (Event event) {
		AMedia media = new AMedia("Synchronize-log.txt", null, "text/plain", propagateLog.getLog());
		Filedownload.save(media);
	}
	
	private void testPropagateObject(String system, SoffidObjectType objectType, String object1, String object2) throws InternalErrorException, NamingException, CreateException {
		propagateLog = EJBLocator.getDispatcherService().testPropagateObject(system, objectType, object1, object2);
		Window testWindow = (Window) getFellow("testWindow");
		((Label)testWindow.getFellow("status")).setValue(propagateLog.getStatus());
		DataTree2 tree = (DataTree2)testWindow.getFellow("log");
		JSONObject logRoot = new JSONObject();
		JSONArray children = new JSONArray();
		logRoot.put("children", children);
		if (propagateLog.getLog() != null) {
			int i = 0;
			int j;
			Stack<JSONObject> stack = new Stack<JSONObject>();
			stack.push(logRoot);
			do {
				JSONObject log = new JSONObject();
				log.put("type", "log");
				
				j = propagateLog.getLog().indexOf("\n", i);
				String s;
				if (j < 0) {
					s = propagateLog.getLog().substring(i);
				} else {
					s =  propagateLog.getLog().substring(i, j);
					i = j+1;
				}
				int n = s.indexOf(" INFO BEGIN ");
				if (n > 0 && !hasChars(s.substring(0, n))) {
					children.put(log);
					children = new JSONArray();
					log.put("header", s.substring(n+12));
					log.put("children", children);
					log.put("type", "header");
					log.put("collapsed", true);
					stack.push(log);
				} else {
					if (s.endsWith(" INFO END") &&
							!hasChars(s.substring(0, s.length()-8)) && 
							stack.size() > 1 ){
						stack.pop();
						JSONObject parent = stack.peek();
						children = parent.getJSONArray("children");
					} else if (!s.trim().isEmpty()) {
						children.put(log);
						log.put("log", s);
						if (s.contains(" WARN "))
						{
							log.put("style", "style='color: red'");
							for (JSONObject parents: stack)
								parents.put("collapsed", false);
						}
						else
							log.put("style", "");
					}
				}
			} while (j >= 0);
			
		}
		tree.setData(logRoot);
		testWindow.doHighlighted();
	}

	private boolean hasChars(String substring) {
		int letters = 0;
		for (int i = 0; i < substring.length(); i++)
			if (Character.isAlphabetic(substring.charAt(i)))
			{
				if (substring.charAt(i) == 'P' ||
						substring.charAt(i) == 'A' ||
						substring.charAt(i) == 'M')
				{
					letters ++;
					if (letters > 2)
						return true;
				}
			}
		return false;
	}

	public void hideTestRow (Event event)
	{
		Component c = event.getTarget();
		c.getFellow("testRowLabel1").setVisible(false);						
		c.getFellow("testRowLabel2").setVisible(false);						
		c.getFellow("testRowTextbox1").setVisible(false);						
		c.getFellow("testRowTextbox2").setVisible(false);						
		c.getFellow("testRowButton1").setVisible(true);
		c.getFellow("testRowButton2").setVisible(false);						
		c.getFellow("testRowButton3").setVisible(false);						
		c.getFellow("testRowButton4").setVisible(false);						
		c.getFellow("testRowButton5").setVisible(false);						
	}
	
	public void selectObjectType (Event event)
	{
		
	}
	
	public void createObjectTypes (Event event)
	{
		
	}
	
	public void showReconcileTaskMessage() throws DocumentBeanException, InternalErrorException, NamingException, CreateException, IOException {
		showTaskMessage("reconcileTask");
	}

	public void showImportTaskMessage() throws DocumentBeanException, InternalErrorException, NamingException, CreateException, IOException {
		showTaskMessage("importTask");
	}

	public void showImpactTaskMessage() throws DocumentBeanException, InternalErrorException, NamingException, CreateException, IOException {
		showTaskMessage("impactTask");
	}

	void showTaskMessage (String type) throws DocumentBeanException, InternalErrorException, NamingException, CreateException, IOException
	{
		String ref = (String) XPathUtils.getValue(form, "tasks[1]/"+type+"/@logReferenceID");
		Long id = (Long) XPathUtils.getValue(form, "tasks[1]/"+type+"/@id");
		for ( com.soffid.iam.api.ScheduledTask t: com.soffid.iam.EJBLocator.getScheduledTaskService().listTasks())
		{
			if (t.getId().equals ( id ))
			{
				ref = t.getLogReferenceID();
			}
		}
		if (ref == null)
			return;
		String name = (String) XPathUtils.getValue(form, "tasks[1]/"+type+"/@name");
		com.soffid.iam.doc.service.ejb.DocumentService doc = es.caib.seycon.ng.EJBLocator.getDocumentService();
		doc.openDocument(new com.soffid.iam.doc.api.DocumentReference(ref));
		java.io.InputStream in = new com.soffid.iam.doc.api.DocumentInputStream(doc);
		byte[] b = new byte[8000];
		int read = in.read(b);
		in.close();
		
		Window logWindow = (Window) getFellow("logWindow");
		if (read == b.length)
		{                              
			Filedownload.save(new com.soffid.iam.doc.api.DocumentInputStream(doc),
					"text/plain; charset=utf-8",
					name+".txt");
		}
		else if (read <= 0)
		{
			logWindow.setTitle(name);
			((Textbox) logWindow.getFellow("tb")).setValue("");
			logWindow.doHighlighted();
		}
		else
		{
			logWindow.setTitle(name);
			((Textbox)logWindow.getFellow("tb")).setValue( new String(b, 0, read) );
			logWindow.doHighlighted();
		}
		
		
	}

	public void startReconcile() throws Exception {
		prepareStartTask("reconcileTask");
	}
	
	public void startImportTask() throws Exception {
		prepareStartTask("importTask");
	}

	public void startImpactTask() throws Exception {
		prepareStartTask("impactTask");
	}

	void prepareStartTask(String type) throws Exception
	{
		currentTask = (ScheduledTask) XPathUtils.getValue(form, "tasks[1]/"+type);
		if (currentTask == null)
		{
			throw new InternalErrorException("Cannot find the scheduled task");
		}
		startTaskWindow.setTitle(currentTask.getName());
		
		Select select = (Select) startTaskWindow.getFellow("servers");
		JSONArray options = new JSONArray();
		for (Server server: EJBLocator.getDispatcherService().findTenantServers()) {
			if (server.getType() == ServerType.MASTERSERVER) {
				JSONObject o = new JSONObject();
				o.put("key", server.getName());
				o.put("label", server.getName());
				options.put(o);
			}
		}
		select.setOptions(options.toString());
		select.setSelectedValue(currentTask.getServerName());
		startTaskWindow.doHighlighted();
	}
	
	public void startTask(Event event) throws Exception {
		Select select = (Select) startTaskWindow.getFellow("servers");
		currentTask.setServerName((String) select.getSelectedValue());
		
		com.soffid.iam.EJBLocator.getScheduledTaskService().update(currentTask);
		com.soffid.iam.EJBLocator.getScheduledTaskService().startNow (currentTask);
		
		Thread.currentThread().sleep(2000);
		
		((DataNodeCollection) XPathUtils.getValue(form, "tasks")).refresh();

		onChangeForm(null);
		startTaskWindow.setVisible(false);
	}
	
	public void cancelStartTask(Event event) {
		startTaskWindow.setVisible(false);
	}

	public void onChangeTab(Event evt) {
		Tab tab = (Tab) evt.getTarget();
     	if (tab.getId().equals("r_tasksTab"))
     	{
     		refreshTasks();
     		refreshTasksTimer.start();
     	}
     	else
     		refreshTasksTimer.stop();
	}
	
	public void showMessage() {
		missatge.setVisible(true);
	}

	public void onSelectUserType (Event evt) {
		missatge.setVisible(true);
		// no es pot saber quin és el darrer que ha seleccionat... els agafem tots
		int[] elements = userTypeTable.getSelectedIndexes();
		String tipusUsuariSeleccionats = "";
		ListModel model = userTypeTable.getModel();
		int numSel = 0;
		for ( int pos: elements)
		{
			DataNode dn = (DataNode) model.getElementAt(pos);
			UserType ut = (UserType) dn.getInstance();
			tipusUsuariSeleccionats += ut.getCode()+",";
			numSel++;
		}

		//Missatgebox.info ("tipus d'usuaris seleccionats "+tipusUsuariSeleccionats);
		// Ho guardem
		// Obtenim el valor anterior
		final String tipusAnterior = (String) XPathUtils.getValue(getForm(), "/@userTypes");
		boolean confirma = false;
		String[] tu = null;
		if (tipusAnterior!=null) {
			tu = tipusAnterior.split(",");
			// només quan es canvien de més de 2 a 1
			if (tu!=null && tu.length > 2 && numSel == 1) {
				confirma = true;
			}
		}
		if (confirma && tu!=null) {
			final String s = tipusUsuariSeleccionats;
			Missatgebox.confirmaOK_CANCEL(String.format(org.zkoss.util.resource.Labels.getLabel("agents.CanviarTipusUsuari"), new Object [] {tu.length,numSel}),
				org.zkoss.util.resource.Labels.getLabel("agents.VerificarCanviarTipusUsuari"),
				(evt2) -> {
					if ("onOK".equals(evt2.getName())) {
						XPathUtils.setValue(getForm(), "/@userTypes", s);		
					} else {
						seleccionaTipusUsuari(tipusAnterior);
					}
				});
		} else {
			XPathUtils.setValue(getForm(), "/@userTypes", tipusUsuariSeleccionats);
		}
		
	}
	
	public void preview(Event event) throws InternalErrorException, NamingException, CreateException {
		System d = (System)((XmlDataNode) XPathUtils.getValue(form, "/")).getInstance();
		DispatcherService svc = EJBLocator.getDispatcherService();
		String file = svc.generateChangesReport(d);
		Window previewWindow = (Window) getFellow("previewWindow");
		((FileDump)previewWindow.getFellow("previewDiv")).setSrc(file);
		previewWindow.doHighlighted();

	}
	
	public void applyChanges(Event event) throws InternalErrorException, NamingException, CreateException, CommitException {
		getModel().commit();
		System d = (System)((XmlDataNode) XPathUtils.getValue(form, "/")).getInstance();
		EJBLocator.getDispatcherService().applyConfiguration(d) ;
		Window previewWindow = (Window) getFellow("previewWindow");
		previewWindow.setVisible(false);
		
	}
	
	public void defaultMapping() {
		if (AutoritzacionsUsuari.hasUpdateAgent())
		{
			Missatgebox.confirmaOK_CANCEL(
					org.zkoss.util.resource.Labels.getLabel("agents.resetMappings"),
						org.zkoss.util.resource.Labels.getLabel("agents.reset"),
						(evt) -> {
							if ("onOK".equals(evt.getName())) {
								getModel().commit();
								Long id = (Long) XPathUtils.getValue( getListbox(), "/@id");
								DispatcherService ejb = EJBLocator.getDispatcherService();
								ejb.setDefaultMappingsByDispatcher(id);
								((DataModelCollection) XPathUtils.getValue((Component)getForm(), "/objectMapping")).refresh();
							}														
						}
	   			);
		}

	}
	
	public void exportMapping() throws UnsupportedEncodingException, ParserConfigurationException, TransformerException {
		String t = new com.soffid.iam.web.agent.Exporter().export ((DataSource) getListbox());
		org.zkoss.util.media.AMedia m = new org.zkoss.util.media.AMedia("agent-config.xml",
				null, "text/xml", t.getBytes("UTF-8"));
		Filedownload.save(m);
	}
	
	public void importMapping() throws Exception {
        FileUpload2.get((event) -> {
        	org.zkoss.util.media.Media m = ((UploadEvent)event).getMedia();
			es.caib.zkib.datasource.DataSource listbox = (DataSource) getListbox();
			new com.soffid.iam.web.agent.Importer().doImport(m, listbox);
			listbox.sendEvent( new es.caib.zkib.events.XPathRerunEvent( listbox, "/" ) );
        });
	}
	
	public void test() throws Exception {
		Object elem = listbox.getJXPathContext().getValue(".");
		if (elem instanceof es.caib.zkib.datamodel.xml.XmlDataNode) {
			final XmlDataNode node = (XmlDataNode) elem;
			final com.soffid.iam.api.System system = (com.soffid.iam.api.System) node.getInstance();
			EJBLocator.getDispatcherService().checkConnectivity(system.getName());
			Missatgebox.avis(org.zkoss.util.resource.Labels.getLabel("agents.zul.success"));
		} else {
			Missatgebox.avis(org.zkoss.util.resource.Labels.getLabel("agents.Select"));
		}
	}

	public Command getCommand(String cmdId) {
		if ("diagramAction".equals(cmdId))
			return _digramActionCommand ;
		return super.getCommand(cmdId);
	}
	
	private Command _digramActionCommand = new ComponentCommand("diagramAction", 0) {
		protected void process(AuRequest request) {
			Component handler = request.getComponent();
			Component wf = handler.getFellow("workflowWindow");
			if ("editMappings".equals(request.getData()[0]))
			{
				String type = (String) new JSONTokener(request.getData()[1]).nextValue();
				String direction = (String) new JSONTokener(request.getData()[2]).nextValue();
				AttributeMappingWindow.open(wf, type, direction);
			}
			if ("editTriggers".equals(request.getData()[0]))
			{
				String type = (String) new JSONTokener(request.getData()[1]).nextValue();
				String event = (String) new JSONTokener(request.getData()[2]).nextValue();
				OutputTriggerWindow.open(wf, type, event);
			}
			if ("editProperties".equals(request.getData()[0]))
			{
				String type = (String) new JSONTokener(request.getData()[1]).nextValue();
				JSONArray array = new JSONArray(request.getData()[2]);
				String[] props = new String[array.length()];
				for (int i = 0; i < array.length(); i++)
					props[i] = array.getString(i); 
				PropertiesWindow.open(wf, type,  props);
			}
		}
	};

}
