package es.caib.bpm.ui.task;

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
import org.dom4j.DocumentException;
import org.zkoss.image.AImage;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.impl.AbstractTag;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.metainfo.PageDefinition;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Iframe;
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
import org.zkoss.zul.Tree;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.impl.InputElement;

import com.soffid.iam.doc.exception.DocumentBeanException;
import com.soffid.iam.doc.exception.NASException;
import com.soffid.iam.doc.service.ejb.DocumentService;
import com.soffid.iam.utils.Security;

import es.caib.bpm.attachment.TaskAttachmentManager;
import es.caib.bpm.classloader.UIClassLoader;
import es.caib.bpm.datamodel.BPMDataNode;
import es.caib.bpm.exception.BPMException;
import es.caib.bpm.servei.ejb.BpmEngine;
import es.caib.bpm.toolkit.BPMApplication;
import es.caib.bpm.toolkit.WorkflowWindow;
import es.caib.bpm.toolkit.exception.UserWorkflowException;
import es.caib.bpm.toolkit.exception.WorkflowException;
import es.caib.bpm.ui.SignatureManager;
import es.caib.bpm.ui.tree.FirmaListitem;
import es.caib.bpm.vo.ProcessDefinition;
import es.caib.bpm.vo.ProcessInstance;
import es.caib.bpm.vo.TaskInstance;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.signatura.api.Signature;
import es.caib.signatura.api.SignatureTimestampException;
import es.caib.zkib.component.DataGrid;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.events.SerializableEventListener;
import es.caib.zkib.util.SynchronizableBoolean;
import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.Frame;
import es.caib.zkib.zkiblaf.Missatgebox;

public class TaskUI extends Frame implements EventListener {
	
	private long taskId = 0;
    Label proceso = null;
    Label tarea = null;
    Label asignadoA = null;
    Datebox fechaInicioProceso = null;
    Datebox fechaCreacionTarea = null;
    Datebox fechaFinalizacionProceso = null;
    Component ventanaDinamica = null;
    Image visorImagenes = null;
    Listbox tablaArchivos = null;
    Label estado = null;
	private DataModel model;
	private Label descripcion;
	private Label idtarea;
	private Label idproceso;
	private DataGrid comments;
	private Component newcommentBox = null;
	TaskInstance currentTask;
    ProcessInstance currentProcess;
    ProcessDefinition currentDefinition;
	private Component tabAnexos;
	private Component anexos;
	private Component uploadButton;
	private Component deleteButton;
    private static Log log = LogFactory.getLog(TaskUI.class);
    private Tabbox tabbox;
    Textbox newComment;
    Hbox botonera = null;
    Button btnTomar = null;
    Button btnDelegar = null;
	private Button btnCerrar;
	private Button btnSalvar;

    
	public boolean canClose() {
        boolean result;

		if (! getDataModel().isCommitPending())
			return true;
		
		result=Missatgebox.confirmaYES_NO(Labels.getLabel("task.msgDeseaSalir"), //$NON-NLS-1$
				Labels.getLabel("task.titleDeseaSalir") //$NON-NLS-1$
				,Messagebox.QUESTION);

        return result;
	}

	public TaskUI() {
		super();
		HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		String id = req.getParameter("id"); //$NON-NLS-1$
		if (id != null) {
			taskId  = Long.parseLong(id);
		}
	}

	public void onCreate ()  throws Exception {
	    visorImagenes = (Image) getFellow("visorProcesoWnd").getFellow("visorProceso"); //$NON-NLS-1$ //$NON-NLS-2$

	    fechaInicioProceso = (Datebox) getFellow("txtFechaInicio"); //$NON-NLS-1$
	    fechaFinalizacionProceso = (Datebox) getFellow("txtFechaFinalizacion"); //$NON-NLS-1$
	    fechaCreacionTarea = (Datebox) getFellow("txtFechaCreacion"); //$NON-NLS-1$
	    asignadoA = (Label) getFellow("txtAsignadoA"); //$NON-NLS-1$
	    estado = (Label) getFellow("txtEstado"); //$NON-NLS-1$
        model = (DataModel) getFellow("BPMdata"); //$NON-NLS-1$

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
        comments = (DataGrid) getFellow("comments"); //$NON-NLS-1$
        newcommentBox = getFellow("newCommentBox"); //$NON-NLS-1$
        tabAnexos = getFellow("tabAnexos"); //$NON-NLS-1$
        anexos = getFellow("anexos"); //$NON-NLS-1$
        uploadButton = getFellow("uploadButton"); //$NON-NLS-1$
        deleteButton = getFellow("deleteButton"); //$NON-NLS-1$
        tabbox = (Tabbox) getFellow("tabTarea"); //$NON-NLS-1$
        newComment = (Textbox) getFellow("newComment"); //$NON-NLS-1$
        botonera = (Hbox) getFellow("botonera"); //$NON-NLS-1$
        btnTomar = (Button) botonera.getFellow("btnTomar"); //$NON-NLS-1$
        btnDelegar = (Button) botonera.getFellow("btnDelegar"); //$NON-NLS-1$
        btnSalvar = (Button) botonera.getFellow("btnSalvar"); //$NON-NLS-1$
        btnCerrar = (Button) botonera.getFellow("btnCerrar"); //$NON-NLS-1$
        ventanaDinamica = getFellow("datosElementoWorkflow"); //$NON-NLS-1$

		if (taskId != 0) {
			TaskInstance ti = BPMApplication.getEngine().getTask(taskId);
			if (ti == null)
				Application.goBack();
			else
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

    public void openTaskInstance(TaskInstance task) throws IOException,
            DocumentException, ClassNotFoundException, SQLException,
            NamingException, CreateException, InternalErrorException, BPMException {
        ProcessDefinition definicion;
        BpmEngine engine = getEngine();
        Component componenteGenerado = null;
        ProcessInstance instanciaProceso;
        String ui;
        ClassLoader heavenClassLoader;
        byte[] imagen;
        org.zkoss.image.Image imagenProceso;
        ByteArrayOutputStream streamSalidaImagen;

        String user = Security.getCurrentUser();
        boolean canManage = user.equals(task.getActorId())
                && task.getStart() != null;

        Application.setTitle(task.getName());
        setCurrentTask(task);

        instanciaProceso = engine.getProcessInstance(task);
        setCurrentProcess(instanciaProceso);
        definicion = engine.getProcessDefinition(instanciaProceso);
        setCurrentDefinition(definicion);

        try {
	        generateImage(engine, definicion, task);
        } catch (Exception e) {
        	log.warn("Error rendering workflow image", e);
        }

        // Establecemos los datos de proceso

        proceso.setValue(String.format(Messages.getString("TaskUI.DataProcessInfo"), definicion.getName(), definicion.getTag())); //$NON-NLS-1$
        idproceso.setValue(Long.toString(instanciaProceso.getId()));
        idtarea.setValue(Long.toString(task.getId()));
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
	                componenteGenerado = Executions.createComponentsDirectly(ui,
	                        "zul", ventanaDinamica, map); //$NON-NLS-1$
	                if (!canManage || task.isCancelled() || task.getEnd()!=null)
	                    disableInputbox(componenteGenerado);
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
            comments.setDataPath("BPMdata:/processInstance/comments"); //$NON-NLS-1$
            newComment.setValue(""); //$NON-NLS-1$
            newcommentBox.setVisible(canManage);
        	Tab tb = (Tab) tabbox.getTabs().getChildren().get(3);
            if ( getCurrentProcess().getComments().isEmpty() )
            {
            	tb.setSclass("tab");
            } else {
            	tb.setSclass("redtab tab");
            }
            
            if (componenteGenerado != null
                    && componenteGenerado instanceof WorkflowWindow) {
                WorkflowWindow window = (WorkflowWindow) componenteGenerado;

                // log.debug("Establecemos tarea y variables");
                window.setTask(task);
                window.setProcessInstance(instanciaProceso);
                window.setEngine(engine);
                window.setSignatureHandler(new SignatureManager(window));

                try {
                	Events.sendEvent(new Event(WorkflowWindow.LOAD_EVENT, window));
                } catch (Exception e) {
	            	window.detach();;                	

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
		                window.setEngine(engine);
		                window.setSignatureHandler(new SignatureManager(window));
		                try {
		                	Events.sendEvent(new Event(WorkflowWindow.LOAD_EVENT, window));
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
        else if (component instanceof Listbox)
            ((Listbox) component).setDisabled(true);
        else if (component instanceof Button)
            ((Button) component).setDisabled(true);
        else if (component instanceof Radio)
            ((Radio) component).setDisabled(true);
        else if (component instanceof Checkbox)
            ((Checkbox) component).setDisabled(true);
        else if (component instanceof Checkbox)
            ((Checkbox) component).setDisabled(true);
        else {
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
            tablaArchivos.getItems().clear();

        if (ventanaDinamica != null)
            ventanaDinamica.getChildren().clear();
    }

    public void refreshListadoArchivos() throws IOException, NamingException,
            CreateException, InternalErrorException {
        Listbox tablaArchivos = null;

        tablaArchivos = getAttachmentsListbox();

        if (getCurrentTask() != null) {
            this.cargarTablaArchivos(getCurrentTask(), tablaArchivos);
        }
    }

    public void cargarTablaArchivos(TaskInstance task, Listbox tablaArchivos)
            throws IOException, NamingException, CreateException, InternalErrorException {
        Listitem item = null;
        String roles = null;

        TaskAttachmentManager business = new TaskAttachmentManager(
                getCurrentTask());

        tablaArchivos.getItems().clear();

        for (Iterator it = business.getTags().iterator(); it.hasNext();) {
            String tag = (String) it.next();
            try {
	            DocumentService document = business.getDocument(tag);
	
	            item = new Listitem();
	            item.appendChild(new Listcell(document.getExternalName()));
	            item.appendChild(new Listcell(document.getMimeType()));
	            item.setValue(tag);
	
	            item.appendChild(new Listcell(Messages.getString("TaskUI.PublicInfo"))); //$NON-NLS-1$
	            item.appendChild(new Listcell("")); //$NON-NLS-1$
	
	            tablaArchivos.getItems().add(item);
            } catch (Exception e) {
            	
            }
        }
    }

    public ClassLoader cargarClasesUI(TaskInstance task)
            throws ClassNotFoundException, SQLException, IOException,
            CreateException, NamingException, InternalErrorException {

    	ClassLoader heavenLoader = Thread.currentThread().getContextClassLoader();
    	UIClassLoader cl = task.getProcessClassLoader();

        Thread.currentThread().setContextClassLoader(cl.clone(heavenLoader));

        return heavenLoader;
    }

    public void updateBotonera(Component componenteGenerado) {
        TaskInstance task = getCurrentTask();
        String user = Security.getCurrentUser();

        if (componenteGenerado instanceof WorkflowWindow)
            btnDelegar.setDisabled(!((WorkflowWindow) componenteGenerado)
                    .isAllowDelegate());
        else
            btnDelegar.setDisabled(true);

        botonera.getChildren().clear();

        btnTomar.setVisible(task.getStart() == null
                || !user.equals(task.getActorId()));

        btnDelegar.setVisible(
        		(task.getStart() == null || user.equals(task.getActorId())) 
        		&& !task.isCancelled() && task.getEnd()==null		);

        boolean iniciado = user.equals(task.getActorId())
                && task.getStart() != null;
        btnSalvar.setVisible(iniciado && !task.isCancelled() && task.getEnd()==null);
        
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

    public void ejecutarTarea(String transicion) throws InterruptedException,
            IOException, CreateException, NamingException {
        TaskInstance task = null;
        BpmEngine engine = getEngine();

        try {
        	WorkflowWindow workflowWindow = getWorkflowWindow ();
            if (workflowWindow != null) {

                task = workflowWindow.getTask();

                try {
                    Events.sendEvent(new Event(WorkflowWindow.SAVE_EVENT,
                            workflowWindow));

                    ProcessInstance process = getCurrentProcess();
                    engine.update(task);

                    Events.sendEvent(new Event(
                            WorkflowWindow.PREPARE_TRANSITION_EVENT,
                            workflowWindow, transicion));


                    if (newComment.getValue() != null
                            && newComment.getValue().length() > 0) {
                        engine.addComment(task, newComment.getValue());
                        // workflowWindow.setTask(task);
                    }

                    engine.executeTask(task, transicion);
                    // workflowWindow.setTask(task);

                    Events.sendEvent(new Event(
                            WorkflowWindow.COMPLETE_TRANSITION_EVENT,
                            workflowWindow, transicion));

                    // Locate next task from same process
                    List<TaskInstance> tasks = engine.getPendingTasks(process);

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
                   	Application.goBack();

                } catch (Exception ex) {
                    log.error(Messages.getString("TaskUI.TransitionError"), ex); //$NON-NLS-1$
                    workflowWindow.refresh();
                    // Localizar el mensaje
                    String message = Labels.getLabel("task.msgError") + " " //$NON-NLS-1$ //$NON-NLS-2$
                            + ex.toString();
                    Throwable ex2 = ex;
                    while (ex2 != null) {
                        if (ex2 instanceof WorkflowException)
                            message = Labels.getLabel("task.msgError") //$NON-NLS-1$
                                    + " " + ex2.getMessage(); //$NON-NLS-1$

                        if (ex2 instanceof EJBException)
                        	ex2 = ((EJBException)ex2).getCausedByException();
                        else if (ex2.getCause() == ex2)
                            ex2 = null;
                        else
                            ex2 = ex2.getCause();
                    }
                    Missatgebox.error(message);
                }
            } else {
            	Missatgebox.info(Labels.getLabel("task.msgSeleccionTarea"), //$NON-NLS-1$
                        "Workflow BPM"); //$NON-NLS-1$
            }
        } finally {
        }
    }


    private WorkflowWindow getWorkflowWindow() {
    	
        for (Object c: ventanaDinamica.getChildren()) 
        {
            if ( c instanceof WorkflowWindow)
            	return (WorkflowWindow) c;
        }
        return null;
	}

	public void salvarTarea() throws InterruptedException, IOException,
            CreateException, NamingException {
        WorkflowWindow workflowWindow = null;
        TaskInstance task = null;
        BpmEngine engine = getEngine();

        try {
        	workflowWindow = getWorkflowWindow();
            if (workflowWindow != null) {

                task = workflowWindow.getTask();

                try {
                    Events.sendEvent(new Event(WorkflowWindow.SAVE_EVENT,
                            workflowWindow));

                    engine.update(task);
                    if (newComment.getValue() != null
                            && newComment.getValue().length() > 0)
                    {
                        task = engine.addComment(task, newComment.getValue());
                        workflowWindow.setTask(task);
                    }

                    getDataModel().commit();
                    Application.goBack();
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

    public void cerrarTarea() throws InterruptedException, IOException,
            CreateException, NamingException, BPMException, ClassNotFoundException, SQLException, DocumentException {
       	Application.goBack();
    }

    public void tomarTarea() throws InterruptedException, CreateException,
            NamingException {
        TaskInstance task = null;

        try {
            task = getEngine().startTask(getCurrentTask());
            Application.jumpTo(BPMApplication.getTaskURL(task));
        } catch (Exception ex) {
        	Missatgebox.error(Labels.getLabel("task.msgError") + " " //$NON-NLS-1$ //$NON-NLS-2$
                    + ex.getMessage());
        } finally {
        }
    }

    public void delegarTarea() throws InterruptedException, CreateException,
            NamingException {
        WorkflowWindow workflowWindow = getWorkflowWindow();
        TaskInstance task = null;
        BpmEngine engine = getEngine();
        SeleccionUsuarioUI window = null;
        String usuarioSeleccionado = null;

        try {
        	
            if (workflowWindow != null) {
                task = workflowWindow.getTask();

                //PJR- afegeixo això per si cal amagar plugins adobe de previsualització de documents, ja que impedeixen que la finestra de selecció d'usuari es vegi
                Events.sendEvent(workflowWindow,new Event(WorkflowWindow.DELEGATION_INIT_EVENT,
                        workflowWindow));
                
                
                window = (SeleccionUsuarioUI) Executions.createComponents(
                        "/wf/task/seleccionUsuario.zul", this, null); //$NON-NLS-1$
                window.doModal();

                usuarioSeleccionado = window.getUsuarioSeleccionado();

                if (usuarioSeleccionado != null) {
                    if (newComment.getValue() != null
                            && newComment.getValue().length() > 0)
                        task = engine.addComment(task, newComment.getValue());
                    task = engine.delegateTaskToUser(task,
                            usuarioSeleccionado);
                    workflowWindow.setTask(task);

                    log.debug(Messages.getString("TaskUI.ClearSelectionTasks")); //$NON-NLS-1$
                    Application.goBack();
                }
            } else {
            	Missatgebox.info(Labels.getLabel("task.msgSeleccionTarea"), //$NON-NLS-1$
                        "Workflow BPM"); //$NON-NLS-1$
            }

        } catch (Exception ex) {
            Missatgebox.error(Labels.getLabel("task.msgError") + " " //$NON-NLS-1$ //$NON-NLS-2$
                    + ex.getMessage());
        } finally {
        }
    }

    private BpmEngine getEngine() throws CreateException, NamingException {
        return BPMApplication.getEngine();
    }

    public void subirArchivo() throws InterruptedException, IOException, NamingException, CreateException, DocumentBeanException, BPMException, InternalErrorException, EJBException, RemoveException
    {

            Media dataSubida= null;
            TaskAttachmentManager business= new TaskAttachmentManager(getCurrentTask());
            Session sesion= this.getDesktop().getSession();
            
            dataSubida = Fileupload.get(true);
            
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
    }

    public void eliminarArchivo() throws Exception
    {
            Listbox listbox= getAttachmentsListbox();

            TaskAttachmentManager business= new TaskAttachmentManager(getCurrentTask());
            
            if(listbox.getSelectedItem()!= null)
            {
                    String tag= (String)listbox.getSelectedItem().getValue();
            
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
            Listbox tablaArchivos= null;
            Listitem item= null;
            TaskAttachmentManager business= new TaskAttachmentManager(getCurrentTask());
            
            //Tomamos la tabla de archivos
            tablaArchivos= getAttachmentsListbox();

            if(tablaArchivos!= null)
            {
                    //tomamos el item seleccionado
                    item= tablaArchivos.getSelectedItem();
                    
                    if(item!= null)
                    {
                            String tag = (String) item.getValue();
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
    
    public void seleccionarDocumento() throws SignatureTimestampException, IOException, ClassNotFoundException, NASException, NamingException, CreateException, InternalErrorException
    {
            Listbox tablaArchivos= null;
            Listitem item= null;
            Listitem roleListItem= null;
            DocumentService document= null;
            Set rolesDocumento= null;
            String role= null;
            Listbox tablaRoles= null;
            List firmas= null;
            Signature sign= null;
            
            Vbox roles= (Vbox)this.getFellow("rolesDocumento"); //$NON-NLS-1$
            TaskAttachmentManager am = new TaskAttachmentManager(getCurrentTask());
            
            roles.setVisible(false);
            
            //Tomamos la tabla de archivos
            tablaArchivos= getAttachmentsListbox();
            tablaRoles= getRolesListbox();
            Listbox tablaFirmas = getSignaturesListbox();
            
            //Limpiamos la tabla de roles
            tablaRoles.getItems().clear();
            
            if(tablaArchivos!= null)
            {
                    //tomamos el item seleccionado
                    item = tablaArchivos.getSelectedItem();
                    
                    if(item!= null)
                    {
                            String tag = (String) item.getValue();
                    
                            document = am.getDocument(tag);
                                                        
                            // Actualizar firmas
                            tablaFirmas.getItems().clear ();
                            firmas= document.getSigns();

                            for(int index= 0; index< firmas.size(); index++)
                            {
                                sign= ((Signature)firmas.get(index));
                                tablaFirmas.getItems().add(new FirmaListitem(sign));
                            }
                    }
            }
    }

    private Listbox getSignaturesListbox() {
        return (Listbox) getFellow ("tablaFirmas"); //$NON-NLS-1$
    }

    private Listbox getRolesListbox() {
        return (Listbox) getFellow ("tablaRoles"); //$NON-NLS-1$
    }

    private Listbox getAttachmentsListbox() {
        return (Listbox) getFellow("tablaArchivos"); //$NON-NLS-1$
    }
    
    public void downloadSign() throws InterruptedException, IOException, NamingException, DocumentBeanException
    {
            Iframe iframe= (Iframe)this.getFellowIfAny("iframe"); //$NON-NLS-1$
            Tree tree= null;
            FirmaListitem row= null;

            tree= (Tree)this.getFellowIfAny("treefirmas"); //$NON-NLS-1$

            if(tree!=null && tree.getSelectedItem()!= null)
            {
                    row= (FirmaListitem)tree.getSelectedItem().getChildren().get(0);
                    
                    iframe.setContent(new AMedia("firma.sign", ".sign", "application/octet-stream", row.getSign().getPkcs7())); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            }
            else
            {
            	Missatgebox.avis(Labels.getLabel("contenidoTarea.msgSeleccionFirma"), "Custodia de Documentos"); //$NON-NLS-1$ //$NON-NLS-2$
            }
    }
    
    /**
     * Notifica el canvi de selecció entre pestanyes.
     * @param newItem
     */
    public void cambiarPestana(Tabpanel newItem){
        WorkflowWindow workflowWindow = getWorkflowWindow();
    	Events.sendEvent(workflowWindow,new Event(WorkflowWindow.TAB_SELECTED_EVENT,
                workflowWindow,newItem.getId()));    	
    }
}

