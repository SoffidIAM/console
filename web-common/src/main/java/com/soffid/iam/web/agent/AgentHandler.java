package com.soffid.iam.web.agent;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.jfree.util.Log;
import org.zkoss.idom.Document;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Fileupload;
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
import com.soffid.iam.api.AgentDescriptor;
import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.api.System;
import com.soffid.iam.api.UserType;
import com.soffid.iam.doc.exception.DocumentBeanException;
import com.soffid.iam.service.ejb.DispatcherService;
import com.soffid.iam.utils.AutoritzacionsUsuari;
import com.soffid.iam.web.component.FileDump;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.SearchBox;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataDatebox;
import es.caib.zkib.component.DataListbox;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Select;
import es.caib.zkib.component.Switch;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datamodel.xml.XmlDataNode;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathContext;
import es.caib.zkib.zkiblaf.Missatgebox;

public class AgentHandler extends FrameHandler {

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
		getModel().addEventListener("onCommit", 
				(evt) -> onChangeClass());
		
		((SearchBox) getFellow("searchBox")).search();
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

	@Override
	public void onChangeForm(Event evt)
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
				statusComponent.setSrc ("/img/semafor-vermell.png");
				lastComponent.setVisible(true);
				lastComponent.setValue(last1.getTime());
				labelComponent.setVisible(true);
				separator.setVisible(true);
				lastEndComponent.setVisible(true);
				lastEndComponent.setValue(last2.getTime());
			}
			else if ( error != null && last2 != null)
			{
				statusComponent.setSrc ("/img/semafor-verd.png");
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
		((Row)getFellow("row_authoritative")).setVisible(false); 
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
				Boolean auth = (Boolean) ctx.getValue("/plugin[className='"+value+"']/@authoritativeSource");
				((Row)getFellow("row_authoritative")).setVisible( auth != null && auth.booleanValue() ); 
			}
			catch (Exception e) { 
				((Row)getFellow("row_authoritative")).setVisible( false ); 
			}
			
			try {
				byte[] data = (byte[]) ctx.getValue("/plugin[className='"+value+"']/userInterface");

				// Clear methodDescriptor variable
				getPage().setVariable("methodDescriptor", null);

				//System.out.println("AgentDescriptor = "+data.getClass().getName());
				java.io.ByteArrayInputStream in = new java.io.ByteArrayInputStream(data);
				Document xmlDoc = new org.zkoss.idom.input.SAXBuilder(false, false).build(in);
				Executions.getCurrent().createComponentsDirectly(new String(data, "UTF-8"), "zul", customAgentProperties, new java.util.HashMap());
				customAgentProperties.setVisible(true);
				Boolean enable = (Boolean) ctx.getValue("/plugin[className='"+value+"']/@enableAttributeMapping");
				Long agentId = (Long) XPathUtils.getValue(form, "@id");
				setVisibleAttributeMapping (agentId != null && enable);
			}
			
			catch (Exception e) {
				Log.warn("Error generating custom properties page", e);
				customAgentProperties.setVisible(true);
				setVisibleAttributeMapping (false);
			}
			setVisibleMetadata (true);
			
		}
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
		startTask("reconcileTask");
	}
	
	public void startImportTask() throws Exception {
		startTask("importTask");
	}

	public void startImpactTask() throws Exception {
		startTask("impactTask");
	}

	void startTask(String type) throws Exception
	{
		com.soffid.iam.api.ScheduledTask task = (ScheduledTask) XPathUtils.getValue(form, "tasks[1]/"+type);
		
		com.soffid.iam.EJBLocator.getScheduledTaskService().startNow (task);
		
		Thread.currentThread().sleep(2000);
		
		((DataNodeCollection) XPathUtils.getValue(form, "tasks")).refresh();

		onChangeForm(null);
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
		List<Integer> elements = userTypeTable.getSelectedIndexes();
		String tipusUsuariSeleccionats = "";
		ListModel model = userTypeTable.getModel();
		int numSel = 0;
		for ( Integer pos: elements)
		{
			DataNode dn = (DataNode) model.getElementAt(pos.intValue());
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
		org.zkoss.util.media.Media m = Fileupload.get();
		es.caib.zkib.datasource.DataSource listbox = (DataSource) getListbox();
		new com.soffid.iam.web.agent.Importer().doImport(m, listbox);
		listbox.sendEvent( new es.caib.zkib.events.XPathRerunEvent( listbox, "/" ) );
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


}
