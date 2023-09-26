package com.soffid.iam.web.bpm.process;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.imageio.ImageIO;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.image.AImage;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.metainfo.PageDefinition;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.InputElement;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.User;
import com.soffid.iam.bpm.api.Job;
import com.soffid.iam.bpm.api.ProcessDefinition;
import com.soffid.iam.bpm.api.ProcessInstance;
import com.soffid.iam.bpm.api.TaskInstance;
import com.soffid.iam.bpm.api.Token;
import com.soffid.iam.bpm.service.ejb.BpmEngine;
import com.soffid.iam.doc.exception.DocumentBeanException;
import com.soffid.iam.doc.service.ejb.DocumentService;
import com.soffid.iam.web.bpm.BPMApplication;
import com.soffid.iam.web.bpm.BPMDataNode;
import com.soffid.iam.web.bpm.ListitemCreator;
import com.soffid.iam.web.bpm.WorkflowWindowInterface;
import com.soffid.iam.web.bpm.attachment.ProcessAttachmentManager;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.bpm.exception.BPMException;
import es.caib.bpm.toolkit.WorkflowWindow;
import es.caib.bpm.ui.process.Messages;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.component.DateFormats;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.events.SerializableEventListener;
import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.Missatgebox;

public class ProcessUI extends FrameHandler {
	private long processId = 0;

    Label proceso;
    Label asignadoA;
    Datebox fechaInicioProceso;
    Datebox fechaCreacionTarea;
    Datebox fechaFinalizacionProceso;
    DataTable tablaArchivos;
	private DataTree2 tablaTareas;
	private Label estado;
	private Label descripcion;
	private Label idproceso;
    Component ventanaDinamica = null;
    ProcessInstance currentProcess;
    ProcessDefinition currentDefinition;
	private Job currentJob;
	Map<Long,Job> jobs = new HashMap<>();
	private Window currentJobWindow;

	private DataModel model;

	private DataTable comments;

	private Component tabAnexos;

	private Component anexos;

	private Tabbox tabbox;

	private Image visorProceso;

	private Button btnUpgrade;

	private Button btnCancel;

	private static Log log = LogFactory.getLog(ProcessUI.class);


    
	public ProcessUI() throws InternalErrorException {
		super();
		HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		String id = req.getParameter("id"); //$NON-NLS-1$
		if (id != null) {
			processId  = Long.parseLong(id);
		}
	}

	@Override
	public void afterCompose ()   {
        // Establecemos los datos de proceso
        proceso = (Label) getFellow("txtProceso"); //$NON-NLS-1$
        idproceso = (Label) getFellow("txtIdProceso"); //$NON-NLS-1$
        descripcion = (Label) getFellow("txtDescripcion"); //$NON-NLS-1$
        fechaInicioProceso = (Datebox) getFellow("txtFechaInicio"); //$NON-NLS-1$
        fechaFinalizacionProceso = (Datebox) getFellow("txtFechaFinalizacion"); //$NON-NLS-1$
        fechaCreacionTarea = (Datebox) getFellow("txtFechaCreacion"); //$NON-NLS-1$
        asignadoA = (Label) getFellow("txtAsignadoA"); //$NON-NLS-1$
        estado = (Label) getFellow("txtEstado"); //$NON-NLS-1$
        tablaArchivos = getAttachmentsListbox();
        tablaTareas = (DataTree2) getFellow("listadoTareas"); //$NON-NLS-1$
        ventanaDinamica = getFellow("datosElementoWorkflow"); //$NON-NLS-1$
        model = (DataModel) getFellow("model"); //$NON-NLS-1$
        comments = (DataTable) getFellow("comments"); //$NON-NLS-1$
        tabAnexos = getFellow("tabAnexos"); //$NON-NLS-1$
        anexos = getFellow("anexos"); //$NON-NLS-1$
        tabbox = (Tabbox) getFellow("tabbox"); //$NON-NLS-1$
        visorProceso = (Image) getFellow("visorProcesoWnd").getFellow("visorProceso"); //$NON-NLS-1$ //$NON-NLS-2$
        btnCancel = (Button) getFellow("btnCancel"); //$NON-NLS-1$
        btnUpgrade = (Button) getFellow("btnUpgrade"); //$NON-NLS-1$

        if (processId != 0) {
			try {
				ProcessInstance pi = EJBLocator.getBpmEngine().getProcess(processId);
				openProcessInstance(pi, true);
			} catch (Exception e)
			{
				log.warn("Error opening process", e);
			}
		}
        
        addEventListener("onReturn", new SerializableEventListener() { //$NON-NLS-1$
			public void onEvent(Event event) throws Exception {
				ProcessInstance pi = BPMApplication.getEngine().getProcess(processId);
				openProcessInstance(pi, false);
			}
		});
	}

    private WorkflowWindowInterface getWorkflowWindow() {
    	
        for (Object c: ventanaDinamica.getChildren()) 
        {
            if ( c instanceof WorkflowWindowInterface)
            	return (WorkflowWindowInterface) c;
        }
       	return null;
	}

    public ProcessInstance getCurrentProcess() {
        return currentProcess;
    }

    public void setCurrentProcess(ProcessInstance currentProcess) {
        this.currentProcess = currentProcess;
        if (model != null) {
            BPMDataNode node = (BPMDataNode) model.getDataNode();
            node.setProcessInstance(currentProcess);
            model.refresh();
        } else if (currentProcess != null)
            throw new RuntimeException(
                    Messages.getString("ProcessUI.NoObjectBPMDataNodeToTask")); //$NON-NLS-1$
    }


    public ProcessDefinition getCurrentDefinition() {
        return currentDefinition;
    }

    public void setCurrentDefinition(ProcessDefinition currentDefinition) {
        this.currentDefinition = currentDefinition;
        if (model != null) {
            BPMDataNode node = (BPMDataNode) model.getDataNode();
            node.setProcessDefinition(currentDefinition);
            model.refresh();
        } else if (currentProcess != null)
            throw new RuntimeException(
                    Messages.getString("ProcessUI.NoObjectBPMDataNodeToTask")); //$NON-NLS-1$
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public void refresh() throws IOException, DocumentException, ClassNotFoundException, SQLException, NamingException, CreateException, BPMException, InternalErrorException {
    	ProcessInstance proc = getEngine().getProcess(getCurrentProcess().getId());
    	openProcessInstance(proc, false);
		if (currentJobWindow != null)
			currentJobWindow.setParent(null);
		currentJobWindow = null;
		currentJob = null;
    }
    
    public void openProcessInstance(ProcessInstance proc, boolean firstTime) throws IOException,
            DocumentException, ClassNotFoundException, SQLException,
            NamingException, CreateException, BPMException, InternalErrorException {
        ProcessDefinition definicion;
        BpmEngine engine = getEngine();
        String ui;
        ClassLoader heavenClassLoader;
        byte[] imagen;
        org.zkoss.image.Image imagenProceso;
        ByteArrayOutputStream streamSalidaImagen;

        setCurrentProcess(proc);
        definicion = engine.getProcessDefinition(proc);
        setCurrentDefinition(definicion);

        String subject = null;
        try {
        	subject = (String) proc.getVariables().get("Subject"); //$NON-NLS-1$
        } catch (ClassCastException e) {
        	
        }
        Application.setTitle (subject == null? definicion.getName()+" "+proc.getId(): subject); //$NON-NLS-1$


        try {
        	generateImage(engine, proc, definicion);
        } catch (Exception e) {
        	getFellow("viewProcessDiagramButton").setVisible(false);
//        	log.warn("Error generating workflow image", e);
        }


        proceso.setValue(definicion.getName() + " (Ver. " + definicion.getTag() //$NON-NLS-1$
                + ")"); //$NON-NLS-1$
        idproceso.setValue(Long.toString(proc.getId()));
        fechaFinalizacionProceso.setValue(proc.getEnd());
        fechaInicioProceso.setValue(proc.getStart());
        List tasks = engine.getActiveTasks(proc);
        if (tasks.isEmpty())
        {
        	asignadoA.setValue("-"); //$NON-NLS-1$
        }
        else
        {
            StringBuffer users = new StringBuffer();
            for ( Iterator it = tasks.iterator(); it.hasNext(); )
            {
            	TaskInstance task = (TaskInstance) it.next();
            	fechaCreacionTarea.setValue(task.getCreate());
            	if (task.getActorId() != null)
            	{
            		User u = EJBLocator.getUserService().findUserByUserName( task.getActorId() );
            		if (users.length() > 0)
            			users.append (", "); //$NON-NLS-1$
            		users.append(u.getUserName()+" "+u.getFullName());
            	}
            	else
            	{
                    for (Iterator it2 = task.getPooledActors().iterator(); it2.hasNext();) {
                    	User u = EJBLocator.getUserService().findUserByUserName( (String) it2.next());
                		if (users.length() > 0)
                			users.append (", "); //$NON-NLS-1$
                		users.append(u.getUserName()+" "+u.getFullName());
                    }
            	}
            }
            asignadoA.setValue(users.toString());
        }

        describeTokens(proc, engine);
        // Cargamos la interfaz dinamica
        heavenClassLoader = this.cargarClasesUI(proc);
        try {

            ventanaDinamica.getChildren().clear();
            ui = engine.getUI(proc);
            HashMap map = new HashMap();
            map.put("processInstance", proc); //$NON-NLS-1$
            map.put("engine", engine); //$NON-NLS-1$
            
        	try {
	            if (ui == null)
	            {
	            	PageDefinition def = Executions.getCurrent().getPageDefinition("/wf/process/default.zul"); //$NON-NLS-1$
	           		Executions.createComponents(def, ventanaDinamica, map);
	            }
	            else
	            {
            		Executions.createComponentsDirectly(ui,
                        "zul", ventanaDinamica, map); //$NON-NLS-1$
	        	}
        	} catch (Exception e) {
        		Label l = new Label (e.toString());
        		l.setMultiline(true);
        		ventanaDinamica.appendChild(l);
            	PageDefinition def = Executions.getCurrent().getPageDefinition("/wf/process/default.zul"); //$NON-NLS-1$
           		Executions.createComponents(def, ventanaDinamica, map);
            }

            // Actualizar comentarios
            comments.setDataPath("model:/processInstance/comments"); //$NON-NLS-1$
            // Desactivar botones de upgrade
            boolean canAdmin = engine.canAdmin(proc);
            btnCancel.setVisible(canAdmin && !isFinalized());
            btnUpgrade.setVisible(canAdmin && !isFinalized());

            WorkflowWindowInterface window = getWorkflowWindow();
            if (window != null) {
                // log.debug("Establecemos tarea y variables");
                window.setTask(null);
                window.setProcessInstance(proc);

                try {
                	Events.sendEvent(new Event(WorkflowWindow.LOAD_EVENT, window));
                } catch (Exception e) {
                	log.warn("Error opening process "+getCurrentProcess().getId(), e);
                }
                tabAnexos.setVisible(window.isShowAttachments());
                anexos.setVisible(window.isShowAttachments());

                cargarTablaArchivos(proc, tablaArchivos);
                cargarTablaTareas (proc, tablaTareas);
                
                if (firstTime) 
                	tabbox.setSelectedIndex(0);
            } else {
                throw new UiException(
                        Messages.getString("ProcessUI.GeneratedObjectErrorType")); //$NON-NLS-1$
            }

        } finally {
            if (heavenClassLoader != null) {
                Thread.currentThread().setContextClassLoader(heavenClassLoader);
            }

        }
    }

	private void generateImage(BpmEngine engine, ProcessInstance proc, ProcessDefinition definicion)
			throws InternalErrorException, IOException {
		byte[] imagen;
		org.zkoss.image.Image imagenProceso;
		ByteArrayOutputStream streamSalidaImagen;
		imagen = engine.getProcessDefinitionImage(definicion);

        BufferedImage imagenBuffered = ImageIO.read(new ByteArrayInputStream(
                imagen));
        imagenBuffered.getGraphics().setColor(Color.RED);

        int[] coordinates = engine.getCoordinates(proc);

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

        visorProceso.setContent((org.zkoss.image.Image) imagenProceso);
	}

	private void describeTokens(ProcessInstance proc, BpmEngine engine)
			throws BPMException, InternalErrorException {
		Token[] tokens = engine.getTokens(proc.getId());
        if (tokens == null || tokens.length == 0) {
            estado.setValue(Labels.getLabel("token.lblFinished")); //$NON-NLS-1$
        } else {
        	StringBuffer status = new StringBuffer();
        	for (int i = 0; i <tokens.length; i++)
        	{
        		if (! "/".equals (tokens[i].getTokenName())) { //$NON-NLS-1$
            		status.append (tokens[i].getTokenName());
            		status.append (": "); //$NON-NLS-1$
        		}
        		status.append (describeToken(tokens[i]));
        		status.append ("\n"); //$NON-NLS-1$
        	}
        	estado.setValue(status.toString());
        }
	}

    private String describeToken(Token token) {
    	StringBuffer text = new StringBuffer();
    	text.append (token.getNodeName());
    	if (token.isFinished()) {
    		text.append (" ("); //$NON-NLS-1$
    		text.append(Labels.getLabel("token.lblFinished")); //$NON-NLS-1$
    		text.append (")"); //$NON-NLS-1$
    	} else if (token.isSuspended()){
    		text.append (" ("); //$NON-NLS-1$
    		text.append(Labels.getLabel("token.lblSuspended")); //$NON-NLS-1$
    		text.append (")"); //$NON-NLS-1$
    	} else if (token.isLocked()){
    		text.append (" ("); //$NON-NLS-1$
    		text.append(Labels.getLabel("token.lblLocked")); //$NON-NLS-1$
    		text.append (")"); //$NON-NLS-1$
    	} else {
    		text.append (" ("); //$NON-NLS-1$
    		text.append(Labels.getLabel("token.lblActive")); //$NON-NLS-1$
    		text.append (")"); //$NON-NLS-1$
    	}
    	return text.toString();
	}

	private void cargarTablaJobs(ProcessInstance proc, Listbox tablaJobs) throws CreateException, NamingException, BPMException, InternalErrorException {
		List jobs = getEngine().getActiveJobs(proc);
		if (jobs == null || jobs.isEmpty())
			tablaJobs.setVisible(false);
		else
		{
			// Visible
			tablaJobs.setVisible(true);
			// Limpiar
			while (tablaJobs.getItemCount() > 0)
				tablaJobs.getItemAtIndex(0).setParent(null);
			// Crear filas
			ListitemCreator creator = new ListitemCreator(getEngine());
			for (Iterator it = jobs.iterator(); it.hasNext(); )
			{
				Job job = (Job) it.next();
				
				Listitem item = creator.createListitem(job);

				tablaJobs.getItems().add(item);
			}
			
		}
		
	}

    
	public void openTree(Event ev) throws InternalErrorException, BPMException, NamingException, CreateException {
		DataTree2 dt = (DataTree2) ev.getTarget();
		int[] selected = dt.getSelectedItem();
		JSONObject current = currentTasks;
		for (int s: selected) {
			current = current.getJSONArray("children").getJSONObject(s);
		}
		String type = current.getString("type");
		if ("task".equals(type)) {
			openTask(current);
		}
		if ("job".equals(type)) {
			openJob(current.getLong("jobId"));
		}
	}

	public void openTask (JSONObject o) throws InternalErrorException, BPMException, NamingException, CreateException {
		long id = o.getLong("taskId");
		TaskInstance taskInstance = EJBLocator.getBpmEngine().getTask(id);
		if (taskInstance != null)
			Application.call(BPMApplication.getTaskURL(taskInstance));
	}
    
	EventListener onSelectJob = new EventListener() {
		@Override
		public void onEvent(Event event) throws Exception {
	    	currentJob = null;//(Job) tablaTareas.getSelectedItem().getValue();
	    	Component[] components = Executions.getCurrent().createComponents("/wf/job.zul", new HashMap()); //$NON-NLS-1$
	    	if (components.length != 1)
	    	{
	    		throw new UiException (Messages.getString("ProcessUI.OnlyOneComponentExpected")); //$NON-NLS-1$
	    	}
	    	if (currentJobWindow != null)
	    		currentJobWindow.setParent(null);
	    	currentJobWindow = (Window)components[0];
	    	currentJobWindow.setParent(  ProcessUI.this );
	    	SimpleDateFormat formatConHora = new SimpleDateFormat( Labels.getLabel("selfService.Format")); //$NON-NLS-1$
	    	((Label)currentJobWindow.getFellow("job.id")).setValue(Long.toString(currentJob.getId())); //$NON-NLS-1$
	    	((Label)currentJobWindow.getFellow("job.process")).setValue(Long.toString(currentJob.getProcessId())); //$NON-NLS-1$
	    	((Label)currentJobWindow.getFellow("job.name")).setValue(currentJob.getName()); //$NON-NLS-1$
	    	((Label)currentJobWindow.getFellow("job.dueDate")).setValue(formatConHora.format(currentJob.getDueDate())); //$NON-NLS-1$
	    	((Label)currentJobWindow.getFellow("job.failures")).setValue(Integer.toString(currentJob.getFailures())); //$NON-NLS-1$
	    	((Label)currentJobWindow.getFellow("job.error")).setValue(currentJob.getErrorMessage()); //$NON-NLS-1$
	    	Label statusLabel =((Label)currentJobWindow.getFellow("job.status")); //$NON-NLS-1$
	    	Button pauseButton = (Button) currentJobWindow.getFellow("pausebutton"); //$NON-NLS-1$
	    	Button resumeButton = (Button) currentJobWindow.getFellow("resumebutton"); //$NON-NLS-1$
	    	Button retryButton = (Button) currentJobWindow.getFellow("retrybutton"); //$NON-NLS-1$
	    	Button closeButton = (Button) currentJobWindow.getFellow("closebutton"); //$NON-NLS-1$
	    	Button processButton = (Button) currentJobWindow.getFellow("openprocess"); //$NON-NLS-1$
	    	processButton.setVisible(false);
	    	if (currentJob.isPaused())
	    	{
	    		statusLabel.setValue(Labels.getLabel("job.status.pause")); //$NON-NLS-1$
	    		pauseButton.setVisible(false);
	    		retryButton.setVisible(false);
	    		resumeButton.addEventListener("onClick", new SerializableEventListener() { //$NON-NLS-1$
					public void onEvent(Event event) throws Exception {
						getEngine().resumeJob(currentJob);
						currentJobWindow.setVisible(false);
						currentJobWindow.setParent(null);
						currentJobWindow = null;
						currentJob = null;
						refresh ();
					}
				});
	    	}
	    	else if (currentJob.isError())
	    	{
	    		statusLabel.setValue(Labels.getLabel("job.status.error")); //$NON-NLS-1$
	    		pauseButton.setVisible(false);
	    		resumeButton.setVisible(false);
	    		retryButton.addEventListener("onClick", new SerializableEventListener() { //$NON-NLS-1$
					public void onEvent(Event event) throws Exception {
						getEngine().retryJob(currentJob);
						currentJobWindow.setVisible(false);
						currentJobWindow.setParent(null);
						currentJobWindow = null;
						currentJob = null;
						refresh ();
					}
						
				});
	    	}
	    	else 
	    	{
	    		if (currentJob.getFailures() > 0 && currentJob.getErrorMessage() != null)
	    			statusLabel.setValue(Labels.getLabel("job.status.warning")); //$NON-NLS-1$
	    		else
	    			statusLabel.setValue(Labels.getLabel("job.status.pending")); //$NON-NLS-1$
	    		retryButton.setVisible(false);
	    		resumeButton.setVisible(false);
	    		pauseButton.addEventListener("onClick", new SerializableEventListener() { //$NON-NLS-1$
					public void onEvent(Event event) throws Exception {
						getEngine().pauseJob(currentJob);
						currentJobWindow.setVisible(false);
						currentJobWindow.setParent(null);
						currentJobWindow = null;
						currentJob = null;
						refresh ();
					}
						
				});
	    	}
	    		
			closeButton.addEventListener("onClick", new SerializableEventListener() { //$NON-NLS-1$
				public void onEvent(Event event) throws Exception {
					currentJobWindow.setVisible(false);
					currentJobWindow.setParent(null);
					currentJobWindow = null;
					currentJob = null;
				}
			});
	
			currentJobWindow.doOverlapped();
	    	
	    }
	};

	private FrameHandler parentFrame;

	private List<String> attachmentTags;
    
	private void cargarTablaTareas(ProcessInstance proc, DataTree2 tablaTareas) throws CreateException, NamingException, BPMException, InternalErrorException {
		BpmEngine engine = getEngine();
		jobs.clear();
		
		Set <Long> roots = new HashSet<Long>();
		Set <Long> children = new HashSet<Long>();
		Set <Long> current = new HashSet<Long>();
		current.add(proc.getId());
		roots.add(proc.getId());
	
		do
		{
			Set<Long> c = current;
			current = new HashSet<Long>();
			for ( Long id: c) {
				for (Long parent: engine.findParentProceeses(id))
				{
					if ( ! children.contains(parent) && !roots.contains(parent) && ! current.contains(parent))
					{
						roots.add(parent);
						roots.remove(id);
						children.add (id);
						current.add(parent);
					}
				}
			}
			
		} while ( ! current.isEmpty());

		currentTasks = new JSONObject();
		loadProcesses ( engine, roots, new HashSet<Long> (), currentTasks, true);
		tablaTareas.setData(currentTasks);
	}


    private void loadProcesses(BpmEngine engine, Set<Long> roots, HashSet<Long> processed, JSONObject data, boolean expandir) throws InternalErrorException, BPMException {
    	SimpleDateFormat df = new SimpleDateFormat( Labels.getLabel("selfService.Format")); //$NON-NLS-1$
    	for ( Long id: roots )
    	{
    		if ( ! processed.contains(id))
    		{
    			ProcessInstance proc = engine.getProcess(id);
    			if (proc != null)
    			{
    				processed.add(id);

    				JSONObject o = new JSONObject();
    				o.put("type", "process");
    				o.put("processId", id);
    				o.put("id", "Process "+ id);
    				o.put("description", proc.getDescription());
    				o.put("startDate", DateFormats.getDateTimeFormat().format(proc.getStart()));
    				o.put("currentTask", proc.getCurrentTask());
    				o.put("children", new JSONArray());
    				if (data.optJSONArray("children") == null)
    					data.put("children", new JSONArray());
    				data.getJSONArray("children").put(o);

    				loadProcessTasks ( engine, proc, o );
	    			loadProcessJobs ( engine, proc, o );
	    			
	    			HashSet<Long> childProcs = new HashSet<Long>(engine.findChildProcesses(proc.getId()));
	    			loadProcesses(engine, childProcs, processed, o, false);
    			}
    		}
    	}
	}

	private void loadProcessTasks(BpmEngine engine, ProcessInstance proc, JSONObject parent) throws InternalErrorException, BPMException {
    	SimpleDateFormat df = new SimpleDateFormat( Labels.getLabel("selfService.Format")); //$NON-NLS-1$
		List<TaskInstance> tasks = engine.getActiveTasks(proc);
		for (Iterator<TaskInstance> it = tasks.iterator(); it.hasNext(); )
		{
			TaskInstance task = (TaskInstance) it.next();

			JSONObject o = new JSONObject();
			o.put("type", "task");
			o.put("taskId", task.getId());
			o.put("id", "Task "+ task.getId());
			o.put("description", task.getName());
			o.put("startDate", DateFormats.getDateTimeFormat().format(task.getStart() == null? task.getCreate(): task.getStart()));
			o.put("status", task.getStart() == null ? 
					Labels.getLabel("job.status.started") :  
						Labels.getLabel("job.status.pending") );
			if (task.getActorId() != null) {
				o.put("actor", task.getActorId());
			} else {
				String actors = null;
				for (Iterator it2 = task.getPooledActors().iterator(); it2.hasNext();) {
					String actor = (String) it2.next();
					if (actors == null)
						actors = actor;
					else
						actors = actors + "; " + actor; //$NON-NLS-1$
				}
				if (actors == null)
					actors = "-"; //$NON-NLS-1$
				o.put("actor", actors);
			}
			o.put("children", new JSONArray());
			if (parent.optJSONArray("children") == null)
				parent.put("children", new JSONArray());
			parent.getJSONArray("children").put(o);
		}
	}

	private void loadProcessJobs(BpmEngine engine, ProcessInstance proc, JSONObject parent) throws InternalErrorException, BPMException {
    	SimpleDateFormat df = new SimpleDateFormat( Labels.getLabel("selfService.Format")); //$NON-NLS-1$
		List<Job> tasks = engine.getActiveJobs(proc);
		if (tasks != null)
		{
			for (Iterator<Job> it = tasks.iterator(); it.hasNext(); )
			{
				Job job = it.next();
				
				jobs.put(job.getId(), job);
	
				JSONObject o = new JSONObject();
				o.put("type", "job");
				o.put("jobId", job.getId());
				o.put("id", "Job "+ job.getId());
				o.put("description", job.getName());
				o.put("startDate", DateFormats.getDateTimeFormat().format(job.getDueDate()));
				if (job.isPaused())
				{
					o.put("status", Labels.getLabel ("job.status.pause" ));
				} else if (job.isError())
				{
					o.put("status",Labels.getLabel("job.status.error" ));
				} else if (job.getFailures() > 0 && job.getErrorMessage() != null) {
					o.put("status", Labels.getLabel("job.status.warning" ));
				} else {
					o.put("status",Labels.getLabel("job.status.pending" ));
				}
				if (parent.optJSONArray("children") == null)
					parent.put("children", new JSONArray());
				parent.getJSONArray("children").put(o);
			}
		}
	}

	private void disableInputbox(Component componente) {
        if (componente instanceof InputElement)
            ((InputElement) componente).setReadonly(true);
        else if (componente instanceof Listbox)
            ((Listbox) componente).setDisabled(true);
        else if (componente instanceof Button)
            ((Button) componente).setDisabled(true);
        else {
            for (Iterator it = componente.getChildren().iterator(); it
                    .hasNext();) {
                Component child = (Component) it.next();
                disableInputbox(child);
            }
        }

    }

    public void refreshListadoArchivos() throws IOException, NamingException,
            CreateException, InternalErrorException {
        this.getDesktop().getSession();
        DataTable tablaArchivos = null;

        tablaArchivos = getAttachmentsListbox();

        if (getCurrentProcess() != null) {
            this.cargarTablaArchivos(getCurrentProcess(), tablaArchivos);
        }
    }

    public void cargarTablaArchivos(ProcessInstance proc, DataTable tablaArchivos)
            throws IOException, NamingException, CreateException, InternalErrorException {
        Listitem item = null;
        String roles = null;

        ProcessAttachmentManager business = new ProcessAttachmentManager(proc);

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

    private static java.util.Hashtable classLoaders = new java.util.Hashtable();

	private JSONObject currentTasks;

	private File tempFile;

    public ClassLoader cargarClasesUI(ProcessInstance proc)
            throws ClassNotFoundException, SQLException, IOException,
            CreateException, NamingException, InternalErrorException {

    	ClassLoader heavenLoader = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(proc.getProcessClassLoader().clone(heavenLoader));

        return heavenLoader;
    }



    public void cerrar() throws InterruptedException, IOException,
            CreateException, NamingException, BPMException, ClassNotFoundException, SQLException, DocumentException, CommitException {
    	if (parentFrame == null)
    		Application.goBack();
    	else
    		parentFrame.hideDetails();
    }


    private BpmEngine getEngine() throws CreateException, NamingException {
        return EJBLocator.getBpmEngine();
    }

    public void descargarArchivo() throws IOException, DocumentBeanException, BPMException, InterruptedException, NamingException, CreateException, InternalErrorException
    {
            ProcessAttachmentManager business= new ProcessAttachmentManager(getCurrentProcess());
            
            //Tomamos la tabla de archivos
            if(tablaArchivos!= null)
            {
                    //tomamos el item seleccionado
                    int i = tablaArchivos.getSelectedIndex();
                    
                    if( i >= 0)
                    {
                        String tag = attachmentTags.get(i);
            			DocumentService d = business.getDocument(tag);
            			if (tempFile != null)
            				tempFile.delete();
            			tempFile = File.createTempFile("soffid", "-"+d.getExternalName());
            			FileOutputStream out = new FileOutputStream(tempFile);
            			d.openDownloadTransfer();
            			byte[] b;
            			b = d.nextDownloadPackage(8192);
            			while (b != null) {
            				out.write(b);
            				b = d.nextDownloadPackage(8192);
            			}
            			d.endDownloadTransfer();
            			out.close();
            			
            			Filedownload.save( new AMedia(d.getExternalName(), null, "application/octet-stream", tempFile, true));
            			d.closeDocument();
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
    
    public void cancel( ) {
    	Window w = (Window) getFellow("cancelprocess");
    	w.doHighlighted();
    	CustomField3 cf = (CustomField3) w.getFellow("comment");
    	cf.focus();
    }

	public void closeCancel( ) {
    	Window w = (Window) getFellow("cancelprocess");
    	w.setVisible(false);
	}

	public void cancelProcess2( ) throws IOException, DocumentException, ClassNotFoundException, SQLException, NamingException, CreateException, BPMException, InternalErrorException {
    	Window w = (Window) getFellow("cancelprocess");
    	w.setVisible(false);
    	CustomField3 cf = (CustomField3) w.getFellow("comment");
    	String comment = (String) cf.getValue();
    	if (comment != null && ! comment.trim().isEmpty())
    		getEngine().addComment(getCurrentProcess(), comment);
		setCurrentProcess(getEngine().cancel(getCurrentProcess()));
		refresh();
    }

    public void upgrade( ) throws IOException, DocumentException, ClassNotFoundException, SQLException, NamingException, CreateException, BPMException, InternalErrorException {
		getEngine().upgradeProcess(getCurrentProcess());
		String result []= getEngine().getDeployMessages();
		StringBuffer b = new StringBuffer();
		for (int i = 0; i < result.length; i++)
		{
			b.append (result[i]);
			b.append ('\n');
		}
		Missatgebox.info(b.toString(),
				Labels.getLabel("process.upgraderesult")); //$NON-NLS-1$
		refresh();
    }
    
    /**
     * returns a boolean indicating if the process has ended. Used to enable/disable buttons
     */
    public boolean isFinalized(){
    	return (getCurrentProcess().getEnd()!=null);   
    }

	public void setParentFrame(FrameHandler parentFrame) {
		this.parentFrame = parentFrame;
	}
	
	public void openJob(Long id) {
		currentJob = jobs.get(id);

		Component w = getFellow("job");

		((CustomField3)w.getFellow("id")).setValue(currentJob.getId());
		((CustomField3)w.getFellow("name")).setValue(currentJob.getName());
		((CustomField3)w.getFellow("dueDate")).setValue(currentJob.getDueDate());
		((CustomField3)w.getFellow("failures")).setValue(currentJob.getFailures());
		((CustomField3)w.getFellow("errorLog")).setValue(currentJob.getErrorMessage());
		
		String errorMessage = currentJob.getErrorMessage();
		w.getFellow("errorLog").setVisible(errorMessage != null && ! errorMessage.trim().isEmpty());
		Button pauseButton = (Button) w.getFellow("pausebutton"); //$NON-NLS-1$
		Button resumeButton = (Button) w.getFellow("resumebutton"); //$NON-NLS-1$
		Button retryButton = (Button) w.getFellow("retrybutton"); //$NON-NLS-1$
		Button closeButton = (Button) w.getFellow("closebutton"); //$NON-NLS-1$
		
		CustomField3 statusLabel = (CustomField3) w.getFellow("status");
		if (currentJob.isPaused())
		{
			statusLabel.setValue(Labels.getLabel("job.status.pause")); //$NON-NLS-1$
			resumeButton.setVisible(true);
			pauseButton.setVisible(false);
			retryButton.setVisible(false);
		}
		else if (currentJob.isError())
		{
			statusLabel.setValue(Labels.getLabel("job.status.error")); //$NON-NLS-1$
			retryButton.setVisible(true);
			pauseButton.setVisible(false);
			resumeButton.setVisible(false);
		}
		else 
		{
			Integer failures = currentJob.getFailures();
			if (failures != null && failures.intValue() > 0 && errorMessage != null && ! errorMessage.trim().isEmpty())
				statusLabel.setValue(Labels.getLabel("job.status.warning")); //$NON-NLS-1$
			else
				statusLabel.setValue(Labels.getLabel("job.status.pending")); //$NON-NLS-1$
			retryButton.setVisible(false);
			resumeButton.setVisible(false);
			pauseButton.setVisible(true);
		}

		((Window)getFellow("job")).doHighlighted();
	}
	
	public void pause (Event event) throws InternalErrorException, BPMException, CreateException, NamingException, CommitException, RemoteException, InterruptedException {
		BPMApplication.getEngine().pauseJob(currentJob);
		hideJob();
		cargarTablaTareas (currentProcess, tablaTareas);
	}

	public void resume (Event event) throws InternalErrorException, BPMException, CreateException, NamingException, CommitException, RemoteException, InterruptedException {
		BPMApplication.getEngine().resumeJob(currentJob);
		hideJob();
		cargarTablaTareas (currentProcess, tablaTareas);
	}
	
	public void retry (Event event) throws InternalErrorException, BPMException, CreateException, NamingException, CommitException, RemoteException, InterruptedException {
		BPMApplication.getEngine().retryJob(currentJob);
		hideJob();
        cargarTablaTareas (currentProcess, tablaTareas);
	}

	public void hideJob() {
		getFellow("job").setVisible(false);
		DataTree2 dt = (DataTree2) getFellow("listadoTareas");
		dt.setSelectedIndex(new int[0]);
	}

	@Override
	protected void finalize() throws Throwable {
		if (tempFile != null)
			tempFile.delete();
	}
}
