package com.soffid.iam.web.bpm;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.RemoveException;
import javax.imageio.ImageIO;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.impl.AbstractTag;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.metainfo.PageDefinition;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.InputElement;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.bpm.api.ProcessDefinition;
import com.soffid.iam.bpm.api.ProcessInstance;
import com.soffid.iam.bpm.api.TaskInstance;
import com.soffid.iam.bpm.service.ejb.BpmEngine;
import com.soffid.iam.common.TransactionalTask;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.doc.exception.DocumentBeanException;
import com.soffid.iam.doc.exception.NASException;
import com.soffid.iam.doc.service.ejb.DocumentService;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.bpm.attachment.TaskAttachmentManager;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.Menu2item;
import com.soffid.iam.web.inbox.InboxHandler;
import com.soffid.iam.web.popup.FileUpload2;

import es.caib.bpm.classloader.UIClassLoader;
import es.caib.bpm.exception.BPMException;
import es.caib.bpm.toolkit.exception.UserWorkflowException;
import es.caib.bpm.toolkit.exception.WorkflowException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataGrid;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Databox;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.events.SerializableEventListener;
import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.Frame;
import es.caib.zkib.zkiblaf.Missatgebox;

public class TaskUI extends FrameHandler implements EventListener {
	private Long taskId = null;
    Label proceso = null;
    Label tarea = null;
    Label asignadoA = null;
    Datebox fechaInicioProceso = null;
    Datebox fechaCreacionTarea = null;
    Datebox fechaFinalizacionProceso = null;
    Component ventanaDinamica = null;
    Image visorImagenes = null;
    DataTable tablaArchivos = null;
    Label estado = null;
	private DataModel model;
	private Label descripcion;
	private Label idtarea;
	private Label idproceso;
	private DataTable comments;
	private Databox newCommentBox = null;
	TaskInstance currentTask;
    ProcessInstance currentProcess;
    ProcessDefinition currentDefinition;
	private Component tabAnexos;
	private Component anexos;
	private Component uploadButton;
	private Component deleteButton;
    private static Log log = LogFactory.getLog(TaskUI.class);
    private Tabbox tabbox;
    Div botonera = null;
    Button btnTomar = null;
    Button btnDelegar = null;
	private Long definitionId;
	private Map<String,String[]> newTaskParameters = null;
    
	public TaskUI() throws InternalErrorException, NamingException, CreateException {
		super();
		HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		String id = req.getParameter("id"); //$NON-NLS-1$
		if (id != null) {
			taskId  = Long.parseLong(id);
		}
		definitionName = req.getParameter("def");
		if (definitionName != null) {
			try {
				definitionId  = Long.parseLong(definitionName);
			} catch (NumberFormatException e) {
				for (ProcessDefinition d: EJBLocator.getBpmEngine().findProcessDefinitions(definitionName, true)) {
					definitionId = d.getId();
				}
			}
			newTaskParameters = req.getParameterMap();
		}
		
	}

	public void onCreate ()  throws Exception {
	    visorImagenes = (Image) getFellow("visorProcesoWnd").getFellow("visorProceso"); //$NON-NLS-1$ //$NON-NLS-2$

	    fechaInicioProceso = (Datebox) getFellow("txtFechaInicio"); //$NON-NLS-1$
	    fechaFinalizacionProceso = (Datebox) getFellow("txtFechaFinalizacion"); //$NON-NLS-1$
	    fechaCreacionTarea = (Datebox) getFellow("txtFechaCreacion"); //$NON-NLS-1$
	    asignadoA = (Label) getFellow("txtAsignadoA"); //$NON-NLS-1$
	    estado = (Label) getFellow("txtEstado"); //$NON-NLS-1$
        model = (DataModel) getFellow("model"); //$NON-NLS-1$

        proceso = (Label) getFellow("txtProceso"); //$NON-NLS-1$
        tarea = (Label) getFellow("txtTarea"); //$NON-NLS-1$
        idproceso = (Label) getFellow("txtIdProceso"); //$NON-NLS-1$
        idtarea = (Label) getFellow("txtIdTarea"); //$NON-NLS-1$
        descripcion = (Label) getFellow("txtDescripcion"); //$NON-NLS-1$
        fechaInicioProceso = (Datebox) getFellow("txtFechaInicio"); //$NON-NLS-1$
        fechaFinalizacionProceso = (Datebox) getFellow("txtFechaFinalizacion"); //$NON-NLS-1$
        fechaCreacionTarea = (Datebox) getFellow("txtFechaCreacion"); //$NON-NLS-1$
        asignadoA = (Label) getFellow("txtAsignadoA"); //$NON-NLS-1$
        tablaArchivos = getAttachmentsListbox();
        comments = (DataTable) getFellow("comments"); //$NON-NLS-1$
        newCommentBox = (Databox) getFellow("newCommentBox"); //$NON-NLS-1$
        tabAnexos = getFellow("tabAnexos"); //$NON-NLS-1$
        anexos = getFellow("anexos"); //$NON-NLS-1$
        uploadButton = getFellow("uploadButton"); //$NON-NLS-1$
        deleteButton = getFellow("deleteButton"); //$NON-NLS-1$
        tabbox = (Tabbox) getFellow("tabTarea"); //$NON-NLS-1$
        botonera = (Div) getFellow("botonera"); //$NON-NLS-1$
        btnTomar = (Button) botonera.getFellow("btnTomar"); //$NON-NLS-1$
        btnDelegar = (Menu2item) botonera.getFellow("btnDelegar"); //$NON-NLS-1$
        ventanaDinamica = getFellow("datosElementoWorkflow"); //$NON-NLS-1$

        BpmEngine engine = EJBLocator.getBpmEngine();
        
		if (taskId != null) {
			TaskInstance ti = engine.getTask(taskId);
			if (ti != null)
				openTaskInstance(ti);
			else {
				Missatgebox.avis("This task does not exist", 
						(event) -> {
							Application.goBack();
						});
			}
		} else if (definitionId != null)
		{
			TaskInstance ti = engine.createDummyTask(definitionId);
			if (ti == null) {
				for (ProcessDefinition def: engine.findProcessDefinitions(definitionName, true) ) {
					ProcessInstance proc = engine.newProcess(def);
					for (TaskInstance task: engine.getActiveTasks(proc)) {
						ti = task;
						break;
					}
				}
			}
			if (ti == null)
				throw new InternalErrorException("Cannot crate task for process " +definitionId);
			if (ti.getStart() == null) {
				ti = EJBLocator.getBpmEngine().startTask(ti);
			}
			Map vars = ti.getVariables();
			for ( Map.Entry<String,String[]> param: newTaskParameters.entrySet()) {
				if (param.getKey().startsWith("_")) {
					vars.put(param.getKey().substring(1), ((String[])param.getValue())[0]);
				}
			}
			openTaskInstance(ti);
		}
		
	}


    public ProcessInstance getCurrentProcess() {
        return currentProcess;
    }

    public void setCurrentProcess(ProcessInstance currentProcess) {
        this.currentProcess = currentProcess;
        DataModel model = getDataModel();
        if (model != null) {
            BPMDataNode node = (BPMDataNode) model.getDataNode();
            node.setProcessInstance(currentProcess);
            model.refresh();
        } else if (currentTask != null)
            throw new RuntimeException(
                    Messages.getString("TaskUI.NoObjectBPMDataNodeError")); //$NON-NLS-1$
    }

    private DataModel getDataModel() {
        return model;
    }

    public ProcessDefinition getCurrentDefinition() {
        return currentDefinition;
    }

    public void setCurrentDefinition(ProcessDefinition currentDefinition) {
        this.currentDefinition = currentDefinition;
        DataModel model = getDataModel();
        if (model != null) {
            BPMDataNode node = (BPMDataNode) model.getDataNode();
            node.setProcessDefinition(currentDefinition);
            model.refresh();
        } else if (currentTask != null)
            throw new RuntimeException(
                    Messages.getString("TaskUI.NoObjectBPMDataNodeError")); //$NON-NLS-1$
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
	private String definitionName;
	private List<String> attachmentTags;
	private InboxHandler inboxHandler;

    public void openTaskInstance(TaskInstance task) throws IOException,
            Exception, ClassNotFoundException, SQLException,
            NamingException, CreateException, InternalErrorException, BPMException {
        ProcessDefinition definicion;
        BpmEngine engine = getEngine();
        WorkflowWindowInterface componenteGenerado = null;
        ProcessInstance instanciaProceso;
        String ui;
        ClassLoader heavenClassLoader;
        byte[] imagen;
        org.zkoss.image.Image imagenProceso;
        ByteArrayOutputStream streamSalidaImagen;

        String user = Security.getCurrentUser();
        boolean canManage = user != null && user.equals(task.getActorId()) && task.getStart() != null;

        Application.setTitle(task.getName());
        setCurrentTask(task);

        instanciaProceso = engine.getProcessInstance(task);
        setCurrentProcess(instanciaProceso);
        definicion = engine.getProcessDefinition(instanciaProceso);
        setCurrentDefinition(definicion);

        try {
	        generateImage(engine, definicion, task);
        } catch (Exception e) {
        	getFellow("viewProcessDiagramButton").setVisible(false);
        	// log.warn("Error rendering workflow image", e);
        }

        // Establecemos los datos de proceso

        if ( inboxHandler == null)
        	((Label) getFellow("navigatorLabel")).setValue(task.getName());
        else {
        	for (Component c = getFellowIfAny("navigatorLabel"); c != null; c = c.getPreviousSibling())
        		c.setVisible(false);
        }
        proceso.setValue(String.format(Messages.getString("TaskUI.DataProcessInfo"), definicion.getName(), definicion.getTag())); //$NON-NLS-1$
        idproceso.setValue( instanciaProceso.getId() == 0L ? "" : Long.toString(instanciaProceso.getId()));
        idtarea.setValue( task.getId() == 0L ? "" :Long.toString(task.getId()));
        descripcion.setValue(task.getDescription());
        fechaFinalizacionProceso.setValue(instanciaProceso.getEnd());
        tarea.setValue(task.getName());
        fechaInicioProceso.setValue(instanciaProceso.getStart());
        if (task.getActorId() == null) {
            String users = null;
            for (Iterator it = task.getPooledActors().iterator(); it.hasNext();) {
                if (users == null)
                    users = (String) it.next();
                else
                    users = users + ", " + (String) it.next(); //$NON-NLS-1$
            }
            asignadoA.setValue(users);
        } else {
            asignadoA.setValue(task.getActorId());
            if (task.isOpen() && task.getStart() == null && task.getActorId().equals(Security.getCurrentUser()))
            {
            	task = engine.startTask(task);
            	setCurrentTask(task);
            	canManage = true;
            }
        }
        fechaCreacionTarea.setValue(task.getCreate());

        if (task.isOpen()) {
            if (task.getStart() == null) {
                estado.setValue(Labels.getLabel("task.estat.pendent")); //$NON-NLS-1$
            } else {
                estado.setValue(Labels.getLabel("task.estat.encurs")); //$NON-NLS-1$
            }
        } else {
            estado.setValue(Labels.getLabel("task.estat.fi")); //$NON-NLS-1$
        }
        // Cargamos la interfaz dinamica

        heavenClassLoader = this.cargarClasesUI(task);
        try {
            ui = engine.getUI(task);

            ventanaDinamica.getChildren().clear();

            if (ui != null) {
                HashMap map = new HashMap();
                map.put("taskInstance", task); //$NON-NLS-1$
                map.put("processInstance", instanciaProceso); //$NON-NLS-1$
                map.put("engine", engine); //$NON-NLS-1$
                try {
	                componenteGenerado = (WorkflowWindowInterface) Executions.createComponentsDirectly(ui,
	                        "zul", ventanaDinamica, map); //$NON-NLS-1$
	                if (!canManage || task.isCancelled() || task.getEnd()!=null)
	                    disableInputbox((Component) componenteGenerado);
                } catch (Exception e) {
	            	log.warn("Error generating task page", e);
	        		Label l = new Label (e.toString());
	        		l.setStyle("color: red");
	        		l.setMultiline(true);
	                ventanaDinamica.getChildren().clear();
	        		ventanaDinamica.appendChild(l);
	            	PageDefinition def = Executions.getCurrent().getPageDefinition("/wf/process/default.zul"); //$NON-NLS-1$
	            	Executions.createComponents(def, ventanaDinamica, null);
	            	componenteGenerado = getWorkflowWindow();
                }
            }

            this.updateBotonera(componenteGenerado);

            // Actualizar comentarios
            comments.setDataPath("model:/processInstance/comments"); //$NON-NLS-1$
            newCommentBox.setValue(""); //$NON-NLS-1$
            newCommentBox.setVisible(canManage);
        	Tab tb = (Tab) tabbox.getTabs().getChildren().get(3);
            if ( getCurrentProcess().getComments().isEmpty() )
            {
            	tb.setSclass("clean-tab");
            } else {
            	tb.setSclass("redtab clean-tab");
            }
            
            if (componenteGenerado != null
                    && componenteGenerado instanceof WorkflowWindowInterface) {
                WorkflowWindowInterface window = (WorkflowWindowInterface) componenteGenerado;

                // log.debug("Establecemos tarea y variables");
                window.setTask(task);
                window.setProcessInstance(instanciaProceso);

                try {
                	Events.sendEvent(new Event(WorkflowWindowInterface.LOAD_EVENT, window));
                } catch (Exception e) {
                	log.warn("Error loading task", e);
	            	window.detach();

	            	Label l = new Label (e.toString());
	        		l.setStyle("color: red");
	        		l.setMultiline(true);
	                ventanaDinamica.getChildren().clear();
	        		ventanaDinamica.appendChild(l);
	            	PageDefinition def = Executions.getCurrent().getPageDefinition("/wf/process/default.zul"); //$NON-NLS-1$
	            	Executions.createComponents(def, ventanaDinamica, null);
	            	window = getWorkflowWindow();
	            	if (window != null)
	            	{
		            	window.setTask(task);
		                window.setProcessInstance(instanciaProceso);
		                try {
		                	Events.sendEvent(new Event(WorkflowWindowInterface.LOAD_EVENT, window));
		                } catch (Throwable th ) {
		                }
	            	}
                }

                tabAnexos.setVisible(
                        window.isShowAttachments() && canManage);
                anexos.setVisible(
                        window.isShowAttachments() && canManage);

                this.cargarTablaArchivos(task, tablaArchivos);
                uploadButton.setVisible(
                        window.isShowAttachments()
                                && window.isCanAddAttachments() && canManage);
                deleteButton
                        .setVisible(
                                window.isShowAttachments()
                                        && window.isCanDeleteAttachments()
                                        && canManage);                
                tabbox.setSelectedIndex(0);
            } else {
                throw new UiException(
                        Messages.getString("TaskUI.WorkflowWindowError")); //$NON-NLS-1$
            }

        } finally {
            if (heavenClassLoader != null) {
                Thread.currentThread().setContextClassLoader(heavenClassLoader);
            }

        }
    }

	private void generateImage(BpmEngine engine, ProcessDefinition definicion, TaskInstance task)
			throws InternalErrorException, IOException {
		byte[] imagen;
		org.zkoss.image.Image imagenProceso;
		ByteArrayOutputStream streamSalidaImagen;
		imagen = engine.getProcessDefinitionImage(definicion);

		BufferedImage imagenBuffered = ImageIO.read(new ByteArrayInputStream(
		        imagen));
		imagenBuffered.getGraphics().setColor(Color.RED);

		int[] coordinates = engine.getCoordinates(task);

		Graphics2D graph = (Graphics2D) imagenBuffered.getGraphics();
		graph.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
		        1.0F));
		graph.setPaint(new Color(255, 140, 140));
		graph.setStroke(new BasicStroke(2.5f));

		// log.debug("Dibujamos en: X: " + coordinates[0] + " Y: " +
		// coordinates[1] + " Ancho: " + coordinates[2] + " Alto: " +
		// coordinates[3]);
		graph.draw(new Rectangle2D.Double((double) coordinates[0],
		        (double) coordinates[1], (double) coordinates[2],
		        (double) coordinates[3]));

		streamSalidaImagen = new ByteArrayOutputStream();
		ImageIO.write(imagenBuffered, "jpeg", streamSalidaImagen); //$NON-NLS-1$

		imagenProceso = new AImage("image/jpeg", streamSalidaImagen //$NON-NLS-1$
		        .toByteArray());

		visorImagenes.setContent((org.zkoss.image.Image) imagenProceso);
	}


    private void disableInputbox(final Component componente) {
    	if (componente instanceof AbstractTag)
    		return;
    	
    	componente.addEventListener("onSetReadonly", new SerializableEventListener() {
			
			public void onEvent(Event event) throws Exception {
				recursiveDisable (event.getTarget());
			}
		});
    	Events.postEvent("onSetReadonly", componente, null);
    }
    
    private void recursiveDisable (Component component)
    {
        if (component instanceof InputElement)
            ((InputElement) component).setReadonly(true);
        else if (component instanceof Listitem)
            ((Listitem) component).setDisabled(true);
        else if (component instanceof Button)
            ((Button) component).setDisabled(true);
        else if (component instanceof Radio)
            ((Radio) component).setDisabled(true);
        else if (component instanceof Checkbox)
            ((Checkbox) component).setDisabled(true);
        else if (component instanceof Checkbox)
            ((Checkbox) component).setDisabled(true);
        else {
            if (component instanceof Listbox)
                ((Listbox) component).setDisabled(true);

            for (Iterator it = component.getChildren().iterator(); it
                    .hasNext();) {
                Component child = (Component) it.next();
                disableInputbox(child);
            }
        }

    }

    public void limpiarSeleccion() {
        
        tablaArchivos = getAttachmentsListbox ();

        log.debug(Messages.getString("TaskUI.ClearInfo")); //$NON-NLS-1$
        visorImagenes.setContent((org.zkoss.image.Image) null);
        if (fechaInicioProceso != null)
            fechaInicioProceso.setValue(null);
        if (asignadoA != null)
            asignadoA.setValue(""); //$NON-NLS-1$
        if (fechaCreacionTarea != null)
            fechaCreacionTarea.setValue(null);
        if (fechaFinalizacionProceso != null)
            fechaFinalizacionProceso.setValue(null);
        if (tablaArchivos != null)
            tablaArchivos.setData("[]");

        if (ventanaDinamica != null)
            ventanaDinamica.getChildren().clear();
    }

    public void refreshListadoArchivos() throws IOException, NamingException,
            CreateException, InternalErrorException {
        if (getCurrentTask() != null) {
            this.cargarTablaArchivos(getCurrentTask(), tablaArchivos);
        }
    }

    public void cargarTablaArchivos(TaskInstance task, DataTable tablaArchivos)
            throws IOException, NamingException, CreateException, InternalErrorException {
        Listitem item = null;
        String roles = null;

        TaskAttachmentManager business = new TaskAttachmentManager (getCurrentTask());

        StringBuffer sb = new StringBuffer("[");
        attachmentTags = business.getTags();
		for (Iterator it = attachmentTags.iterator(); it.hasNext();) {
            String tag = (String) it.next();
            try {
	            DocumentService document = business.getDocument(tag);
	
	            JSONObject o = new JSONObject();
	            o.put("document", document.getExternalName());
	            o.put("mimeType", document.getMimeType());
	            if (sb.length() > 1) sb.append(",");
	            sb.append(o.toString());
            } catch (Exception e) {
            	
            }
        }
        sb.append("]");
        tablaArchivos.setData(sb.toString());
    }

    public ClassLoader cargarClasesUI(TaskInstance task)
            throws ClassNotFoundException, SQLException, IOException,
            CreateException, NamingException, InternalErrorException {

    	ClassLoader heavenLoader = Thread.currentThread().getContextClassLoader();
    	UIClassLoader cl = task.getProcessClassLoader();

        Thread.currentThread().setContextClassLoader(cl.clone(heavenLoader));

        return heavenLoader;
    }

    public void updateBotonera(WorkflowWindowInterface componenteGenerado) {
        TaskInstance task = getCurrentTask();
        String user = Security.getCurrentUser();
        
        
        btnDelegar.setDisabled(! componenteGenerado .isAllowDelegate());

        botonera.getChildren().clear();

        btnTomar.setVisible(task.getStart() == null
                || !user.equals(task.getActorId()));

        btnDelegar.setVisible(
        		(task.getStart() == null || user.equals(task.getActorId())) 
        		&& !task.isCancelled() && task.getEnd()==null		&&
        		!task.isDummyTask());

        boolean iniciado = user.equals(task.getActorId())
                && task.getStart() != null;
        //btnSalvar.setVisible(iniciado && !task.isCancelled() && task.getEnd()==null && ! task.isDummyTask());
        //btnCerrar.setVisible(true);
        
        //només mostrar botons de transició si la tasca està activa
        if(!task.isCancelled() && task.getEnd()==null){
	        Arrays.sort(task.getTransitions());
	        Button button = null;
	        for (int i = 0; iniciado && i < task.getTransitions().length; i++) {
	            button = new Button();
	            button.setLabel(task.getTransitions()[i]);
	            button.addEventListener("onClick", this); //$NON-NLS-1$
	            botonera.appendChild(button);
	        }
        }
    }

    public TaskInstance getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(TaskInstance currentTask) {
        this.currentTask = currentTask;
        DataModel model = getDataModel();
        if (model != null) {
            BPMDataNode node = (BPMDataNode) model.getDataNode();
            node.setTaskInstance(currentTask);
            model.refresh();
        } else if (currentTask != null)
            throw new RuntimeException(
                    Messages.getString("TaskUI.NoObjectBPMDataNodeError")); //$NON-NLS-1$
    }

    public void onEvent(Event evt) throws InterruptedException, IOException,
            CreateException, NamingException {
        if ("onClick".equals(evt.getName())) { //$NON-NLS-1$
            ejecutarTarea(((Button) evt.getTarget()).getLabel());
        }
    }

    public void ejecutarTarea(final String transicion) throws InterruptedException,
            IOException, CreateException, NamingException {
        final BpmEngine engine = getEngine();

        try {
        	final WorkflowWindowInterface workflowWindow = getWorkflowWindow ();
            if (workflowWindow != null) {
            	try {
            		Events.sendEvent(new Event(WorkflowWindowInterface.SAVE_EVENT,
            				workflowWindow));
            		
            		
            		Events.sendEvent(new Event(
            				WorkflowWindowInterface.PREPARE_TRANSITION_EVENT,
            				workflowWindow, transicion));
                } catch (Exception ex) {
                	if (ex instanceof UiException)
                		throw (UiException) ex;
                    log.error(Messages.getString("TaskUI.TransitionError"), ex); //$NON-NLS-1$
                    // Localizar el mensaje
                    String message = Labels.getLabel("task.msgError") + " " //$NON-NLS-1$ //$NON-NLS-2$
                            + ex.toString();
                    Throwable ex2 = ex;
                    while (ex2 != null) {
                        if (ex2 instanceof WorkflowException)
                        {
                            message = Labels.getLabel("task.msgError") //$NON-NLS-1$
                                    + " " + ex2.getMessage(); //$NON-NLS-1$
                            Missatgebox.error(message);
                            return;
                        }
                        if (ex2 instanceof EJBException)
                        	ex2 = ((EJBException)ex2).getCausedByException();
                        else if (ex2.getCause() == ex2)
                            ex2 = null;
                        else
                            ex2 = ex2.getCause();
                    }
                    throw new UiException(message, ex);
                }
                try {
                    final TaskInstance task = new TaskInstance( currentTask );
                    EJBLocator.getAsyncRunnerService().runTransaction(new TransactionalTask() {
						@Override
						public Object run() throws Exception
						{
							ProcessInstance process = getCurrentProcess();
							TaskInstance task2 = engine.update(task);
							if (process.isDummyProcess())
								process = engine.getProcessInstance(task2);
							
							
							if (newCommentBox.getValue() != null
									&& ! newCommentBox.getValue().toString().trim().isEmpty()) {
								engine.addComment(task2, newCommentBox.getValue().toString());
								// workflowWindow.setTask(task);
							}
							
							engine.executeTask(task2, transicion);
							// workflowWindow.setTask(task);
							
							currentProcess = process;
							return null;
						}
					});

                    Events.sendEvent(new Event(
                    		WorkflowWindowInterface.COMPLETE_TRANSITION_EVENT,
                    		workflowWindow, transicion));
                    
                    // Locate next task from same process
                    List<TaskInstance> tasks = engine.getPendingTasks(currentProcess);

                    getDataModel().commit();
                    if (tasks != null)
                    {
                    	for (TaskInstance ti: tasks)
                    	{
                    		if (ti.getActorId() != null && ti.getActorId().equals (Security.getCurrentUser()))
                    		{
                                ti = engine.startTask(ti);
                                Application.jumpTo(BPMApplication.getTaskURL(ti));
                                return ;
                    			
                    		}
                    	}
                    }
                    cerrarTarea();
                } catch (Exception ex) {
                    log.error(Messages.getString("TaskUI.TransitionError"), ex); //$NON-NLS-1$
                    workflowWindow.refresh();
                    // Localizar el mensaje
                    String message = Labels.getLabel("task.msgError") + " " //$NON-NLS-1$ //$NON-NLS-2$
                            + ex.toString();
                    Throwable ex2 = ex;
                    while (ex2 != null) {
                        if (ex2 instanceof WorkflowException)
                        {
                            message = Labels.getLabel("task.msgError") //$NON-NLS-1$
                                    + " " + ex2.getMessage(); //$NON-NLS-1$
                            Missatgebox.error(message);
                            return;
                        }
                        if (ex2 instanceof EJBException)
                        	ex2 = ((EJBException)ex2).getCausedByException();
                        else if (ex2.getCause() == ex2)
                            ex2 = null;
                        else
                            ex2 = ex2.getCause();
                    }
                    throw new UiException(message, ex);
                }
            } else {
            	Missatgebox.info(Labels.getLabel("task.msgSeleccionTarea"), //$NON-NLS-1$
                        "Workflow BPM"); //$NON-NLS-1$
            }
        } finally {
        }
    }


    private WorkflowWindowInterface getWorkflowWindow() {
    	
        for (Object c: ventanaDinamica.getChildren()) 
        {
            if ( c instanceof WorkflowWindowInterface)
            	return (WorkflowWindowInterface) c;
        }
        return null;
	}

	public void salvarTarea() throws InterruptedException, IOException,
		CreateException, NamingException {
		salvarTarea(true);
	}
	public void salvarTarea(boolean exit) throws InterruptedException, IOException,
            CreateException, NamingException {
        WorkflowWindowInterface workflowWindow = null;
        TaskInstance task = currentTask;
        BpmEngine engine = getEngine();

        try {
        	workflowWindow = getWorkflowWindow();
            if (workflowWindow != null) {

                try {
                    Events.sendEvent(new Event(WorkflowWindowInterface.SAVE_EVENT,
                            workflowWindow));

                    engine.update(task);
                    if (newCommentBox.getValue() != null
                            && ! newCommentBox.getValue().toString().trim().isEmpty())
                    {
                        task = engine.addComment(task, newCommentBox.getValue().toString());
                        workflowWindow.setTask(task);
                    }

                    getDataModel().commit();
                    if (exit) cerrarTarea();
                } catch (UiException ex) {
                    workflowWindow.refresh();
                    if (ex.getCause() != null
                            && ex.getCause() instanceof UserWorkflowException) {
                        if (ex.getCause().getMessage() != null)
                        	Missatgebox.error(ex.getCause().getMessage());
                    } else {
                        log.error(Messages.getString("TaskUI.UnexpectedError"), ex); //$NON-NLS-1$
                        throw ex;
                    }
                } catch (Exception ex) {
                    workflowWindow.refresh();
                    log.error(Messages.getString("TaskUI.UnexpectedError"), ex); //$NON-NLS-1$
                }
            } else {
            	Missatgebox.info(Labels.getLabel("task.msgSeleccionTarea"), //$NON-NLS-1$
                        "Workflow BPM"); //$NON-NLS-1$
            }
        } finally {
        }
    }

    public void cerrarTarea() throws CommitException   {
    	if (inboxHandler == null)
    		Application.goBack();
    	else
    		inboxHandler.hideDetails();
    }

    public void tomarTarea() throws InterruptedException, CreateException,
            NamingException {
        TaskInstance task = null;

        try {
            task = getEngine().startTask(getCurrentTask());
            openTaskInstance(task);
        } catch (Exception ex) {
        	Missatgebox.error(Labels.getLabel("task.msgError") + " " //$NON-NLS-1$ //$NON-NLS-2$
                    + ex.getMessage());
        } finally {
        }
    }

    public void closeDelegate(Event event) {
    	getFellow("delegate-window").setVisible(false);
    }
    
	public void delegarTarea () throws CreateException, NamingException {
		Window w = (Window) getFellow("delegate-window");
		w.doHighlighted();
		((CustomField3) w.getFellow("user")).focus();
	}
	
	public void delegate(Event event) throws CreateException, NamingException, WrongValueException, InternalErrorException, CommitException {
		Window w = (Window) getFellow("delegate-window");
		
		CustomField3 cf = ((CustomField3) w.getFellow("user"));

		if (cf.attributeValidateAll()) {
			w.setVisible(false);
			BpmEngine engine = getEngine();
            if (newCommentBox.getValue() != null
                    && ! newCommentBox.getValue().toString().trim().isEmpty())
                currentTask = engine.addComment(currentTask, newCommentBox.getValue().toString());
            currentTask = engine.delegateTaskToUser(currentTask, (String) cf.getValue());
            cerrarTarea();
		}
	}
	

	private BpmEngine getEngine() throws CreateException, NamingException {
        return BPMApplication.getEngine();
    }

    public void subirArchivo() throws InterruptedException, IOException, NamingException, CreateException, DocumentBeanException, BPMException, InternalErrorException, EJBException, RemoveException
    {

            TaskAttachmentManager business= new TaskAttachmentManager(getCurrentTask());
            Session sesion= this.getDesktop().getSession();
            
            FileUpload2.get((event) -> {
            	org.zkoss.util.media.Media dataSubida = ((UploadEvent)event).getMedia();
            
            	if(dataSubida!= null)
	            {
	                    String tag = ((HttpSession)sesion.getNativeSession()).getId();
	                    List tags = business.getTags();
	                    int counter = 0;
	                    while ( tags.contains(tag) )
	                    {
	                    	counter++;
	                    	tag = ((HttpSession)sesion.getNativeSession()).getId()+"_"+counter;
	                    }
	                    business.uploadFile(dataSubida, tag);
	                    refreshListadoArchivos();
	            }
            });
    }

    public void eliminarArchivo() throws Exception
    {
            TaskAttachmentManager business= new TaskAttachmentManager(getCurrentTask());
            
            if(tablaArchivos.getSelectedIndex() >= 0)
            {
                    String tag = attachmentTags.get(tablaArchivos.getSelectedIndex());
            
                    business.eliminarArchivo(tag);
                    
                    refreshListadoArchivos();
            }
            else
            {
            	Missatgebox.info(Labels.getLabel("contenidoTarea.msgSeleccionArchivo")); //$NON-NLS-1$
            }
    }

    public void descargarArchivo() throws IOException, DocumentBeanException, BPMException, InterruptedException
    {
            TaskAttachmentManager business= new TaskAttachmentManager(getCurrentTask());
            
            //Tomamos la tabla de archivos
            if(tablaArchivos!= null)
            {
                    //tomamos el item seleccionado
                    int i = tablaArchivos.getSelectedIndex();
                    
                    if( i >= 0)
                    {
                            String tag = attachmentTags.get(i);
                            Executions.getCurrent().sendRedirect(business.getDownloadURL(tag), "_new"); //$NON-NLS-1$
                    }
                    else
                    {
                    	Missatgebox.avis(Messages.getString("TaskUI.SelectFileInfo")); //$NON-NLS-1$
                    }
            }
            else
            {
            	Missatgebox.avis(Messages.getString("TaskUI.LoadFileError"));                       //$NON-NLS-1$
            }
    }
    
    private DataTable getAttachmentsListbox() {
        return (DataTable) getFellow("tablaArchivos"); //$NON-NLS-1$
    }
    
    /**
     * Notifica el canvi de selecció entre pestanyes.
     * @param newItem
     */
    public void cambiarPestana(Event event){
        WorkflowWindowInterface workflowWindow = getWorkflowWindow();
    	Events.sendEvent(workflowWindow,new Event(WorkflowWindowInterface.TAB_SELECTED_EVENT,
                workflowWindow,tabbox.getSelectedPanel().getId()));    	
    }

	public void setParentFrame(InboxHandler inboxHandler) {
		this.inboxHandler = inboxHandler;
	}
	
	public void closeProcessViewer (Event event) {
		event.getTarget().getFellow("visorProcesoWnd").setVisible(false);
	}

}

