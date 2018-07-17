package es.caib.bpm.ui.process;

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
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
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
import org.zkoss.image.AImage;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.metainfo.PageDefinition;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.InputElement;

import com.soffid.iam.doc.exception.DocumentBeanException;
import com.soffid.iam.doc.exception.NASException;
import com.soffid.iam.doc.service.ejb.DocumentService;

import es.caib.bpm.attachment.ProcessAttachmentManager;
import es.caib.bpm.classloader.UIClassLoader;
import es.caib.bpm.datamodel.BPMDataNode;
import es.caib.bpm.exception.BPMException;
import es.caib.bpm.servei.ejb.BpmEngine;
import es.caib.bpm.toolkit.BPMApplication;
import es.caib.bpm.toolkit.WorkflowWindow;
import es.caib.bpm.ui.SignatureManager;
import es.caib.bpm.ui.inbox.ListitemCreator;
import es.caib.bpm.ui.tree.FirmaListitem;
import es.caib.bpm.vo.Job;
import es.caib.bpm.vo.ProcessDefinition;
import es.caib.bpm.vo.ProcessInstance;
import es.caib.bpm.vo.TaskInstance;
import es.caib.bpm.vo.Token;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.signatura.api.Signature;
import es.caib.signatura.api.SignatureTimestampException;
import es.caib.zkib.component.DataGrid;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.events.SerializableEventListener;
import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.Frame;
import es.caib.zkib.zkiblaf.Missatgebox;

public class ProcessUI extends Frame {
	
	private long processId = 0;

    Label proceso;
    Label asignadoA;
    Datebox fechaInicioProceso;
    Datebox fechaCreacionTarea;
    Datebox fechaFinalizacionProceso;
    Listbox tablaArchivos;
	private Listbox tablaJobs;
	private Listbox tablaTareas;
	private Label estado;
	private Label descripcion;
	private Label idproceso;
    Component ventanaDinamica = null;
    ProcessInstance currentProcess;
    ProcessDefinition currentDefinition;
	private Job currentJob;
	private Window currentJobWindow;

	private DataModel model;

	private DataGrid comments;

	private Component tabAnexos;

	private Component anexos;

	private Tabbox tabbox;

	private Image visorProceso;

	private Button btnUpgrade;

	private Button btnCancel;

	private static Log log = LogFactory.getLog(ProcessUI.class);


    
	public ProcessUI() {
		super();
		HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		String id = req.getParameter("id"); //$NON-NLS-1$
		if (id != null) {
			processId  = Long.parseLong(id);
		}
	}

	public void onCreate ()  throws Exception  {
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
        tablaTareas = (Listbox) getFellow("listadoTareas"); //$NON-NLS-1$
        tablaJobs = (Listbox) getFellow("listadoJobs"); //$NON-NLS-1$
        ventanaDinamica = getFellow("datosElementoWorkflow"); //$NON-NLS-1$
        model = (DataModel) getFellow("BPMdata"); //$NON-NLS-1$
        comments = (DataGrid) getFellow("comments"); //$NON-NLS-1$
        tabAnexos = getFellow("tabAnexos"); //$NON-NLS-1$
        anexos = getFellow("anexos"); //$NON-NLS-1$
        tabbox = (Tabbox) getFellow("tabbox"); //$NON-NLS-1$
        visorProceso = (Image) getFellow("visorProcesoWnd").getFellow("visorProceso"); //$NON-NLS-1$ //$NON-NLS-2$
        btnCancel = (Button) getFellow("btnCancel"); //$NON-NLS-1$
        btnUpgrade = (Button) getFellow("btnUpgrade"); //$NON-NLS-1$

        if (processId > 0) {
			ProcessInstance pi = BPMApplication.getEngine().getProcess(processId);
			try {
				openProcessInstance(pi);
			} catch (Exception e)
			{
				log.warn("Error opening process", e);
			}
		}
        
        addEventListener("onReturn", new SerializableEventListener() { //$NON-NLS-1$
			public void onEvent(Event event) throws Exception {
				ProcessInstance pi = BPMApplication.getEngine().getProcess(processId);
				openProcessInstance(pi);
			}
		});
	}

    private WorkflowWindow getWorkflowWindow() {
    	
        for (Object c: ventanaDinamica.getChildren()) 
        {
            if ( c instanceof WorkflowWindow)
            	return (WorkflowWindow) c;
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
    	openProcessInstance(getCurrentProcess());
		if (currentJobWindow != null)
			currentJobWindow.setParent(null);
		currentJobWindow = null;
		currentJob = null;
    }
    
    public void openProcessInstance(ProcessInstance proc) throws IOException,
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
            		if (users.length() > 0)
            			users.append (", "); //$NON-NLS-1$
            		users.append(task.getActorId());
            	}
            	else
            	{
                    for (Iterator it2 = task.getPooledActors().iterator(); it2.hasNext();) {
                		if (users.length() > 0)
                			users.append (", "); //$NON-NLS-1$
                         users.append(it2.next());
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
            comments.setDataPath("BPMdata:/processInstance/comments"); //$NON-NLS-1$
            // Desactivar botones de upgrade
            boolean canAdmin = engine.canAdmin(proc);
            btnCancel.setVisible(canAdmin && !isFinalized());
            btnUpgrade.setVisible(canAdmin && !isFinalized());

            WorkflowWindow window = getWorkflowWindow();
            if (window != null) {
                // log.debug("Establecemos tarea y variables");
                window.setTask(null);
                window.setProcessInstance(proc);
                window.setEngine(engine);
                window.setSignatureHandler(new SignatureManager(window));

                Events.sendEvent(new Event(WorkflowWindow.LOAD_EVENT, window));

                tabAnexos.setVisible(window.isShowAttachments());
                anexos.setVisible(window.isShowAttachments());

                cargarTablaArchivos(proc, tablaArchivos);
                cargarTablaTareas (proc, tablaTareas);
                cargarTablaJobs (proc, tablaJobs);
                
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

    

	public void onSelectTask ()
    {
    	TaskInstance ti = (TaskInstance) tablaTareas.getSelectedItem().getValue();
    	Application.call(BPMApplication.getTaskURL(ti));
    }
    
    public void onSelectJob ()
    {
    	currentJob = (Job) tablaJobs.getSelectedItem().getValue();
    	Component[] components = Executions.getCurrent().createComponents("/wf/job.zul", new HashMap()); //$NON-NLS-1$
    	if (components.length != 1)
    	{
    		throw new UiException (Messages.getString("ProcessUI.OnlyOneComponentExpected")); //$NON-NLS-1$
    	}
    	if (currentJobWindow != null)
    		currentJobWindow.setParent(null);
    	currentJobWindow = (Window)components[0];
    	currentJobWindow.setParent(this);
    	SimpleDateFormat formatConHora = new SimpleDateFormat("dd/MM/yyyy hh:mm"); //$NON-NLS-1$
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
    	
		tablaJobs.setSelectedItem(null);
    }
    
	private void cargarTablaTareas(ProcessInstance proc, Listbox tablaTareas) throws CreateException, NamingException, BPMException, InternalErrorException {
		BpmEngine engine = getEngine();
		List tasks = engine.getActiveTasks(proc);
		if (tasks == null || tasks.isEmpty())
			tablaTareas.setVisible(false);
		else
		{
			// Visible
			tablaTareas.setVisible(true);
			// Limpiar
			while (tablaTareas.getItemCount() > 0)
				tablaTareas.getItemAtIndex(0).setParent(null);
			// Crear filas
			ListitemCreator creator = new ListitemCreator(engine);
			for (Iterator it = tasks.iterator(); it.hasNext(); )
			{
				TaskInstance task = (TaskInstance) it.next();

				
				Listitem item = creator.createListitem(task);
					tablaTareas.getItems().add(item);
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
        Listbox tablaArchivos = null;

        tablaArchivos = getAttachmentsListbox();

        if (getCurrentProcess() != null) {
            this.cargarTablaArchivos(getCurrentProcess(), tablaArchivos);
        }
    }

    public void cargarTablaArchivos(ProcessInstance process, Listbox tablaArchivos)
            throws IOException, NamingException, CreateException, InternalErrorException {
        Listitem item = null;
        String roles = null;

        ProcessAttachmentManager business = new ProcessAttachmentManager(process);

        tablaArchivos.getItems().clear();

        for (Iterator it = business.getTags().iterator(); it.hasNext();) {
            String tag = (String) it.next();
            DocumentService document = business.getDocument(tag);

            item = new Listitem();
            item.appendChild(new Listcell(document.getExternalName()));
            item.appendChild(new Listcell(document.getMimeType()));
            item.setValue(tag);

            item.appendChild(new Listcell(Messages.getString("ProcessUI.PublicInfo"))); //$NON-NLS-1$
            item.appendChild(new Listcell("")); //$NON-NLS-1$
            tablaArchivos.getItems().add(item);
        }
    }

    private static java.util.Hashtable classLoaders = new java.util.Hashtable();

    public ClassLoader cargarClasesUI(ProcessInstance proc)
            throws ClassNotFoundException, SQLException, IOException,
            CreateException, NamingException, InternalErrorException {

    	ClassLoader heavenLoader = Thread.currentThread().getContextClassLoader();
    	UIClassLoader cl = proc.getProcessClassLoader();
    	cl.setParentClassLoader(heavenLoader);
        Thread.currentThread().setContextClassLoader(cl);

        return heavenLoader;
    }



    public void cerrar() throws InterruptedException, IOException,
            CreateException, NamingException, BPMException, ClassNotFoundException, SQLException, DocumentException {
       	Application.goBack();
    }


    private BpmEngine getEngine() throws CreateException, NamingException {
        return BPMApplication.getEngine();
    }


    public void descargarArchivo() throws IOException, DocumentBeanException, BPMException, InterruptedException
    {
            Listbox tablaArchivos= null;
            Listitem item= null;
            ProcessAttachmentManager business= new ProcessAttachmentManager(getCurrentProcess());
            
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
                    		Missatgebox.info(Messages.getString("ProcessUI.SelectArchiveAlert")); //$NON-NLS-1$
                    }
            }
            else
            {
            	Missatgebox.info(Messages.getString("ProcessUI.LoadArchivesAlert"));                       //$NON-NLS-1$
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
            ProcessAttachmentManager am = new ProcessAttachmentManager(getCurrentProcess());
            
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
            	Missatgebox.avis(Labels.getLabel("contenidoTarea.msgSeleccionFirma"), Messages.getString("ProcessUI.CustodyDocs")); //$NON-NLS-1$ //$NON-NLS-2$
            }
    }
    


    public void cancel( ) throws IOException, DocumentException, ClassNotFoundException, SQLException, NamingException, CreateException, BPMException, InternalErrorException {
			boolean result = Missatgebox.confirmaYES_NO(Labels.getLabel("process.confirmCancel"), //$NON-NLS-1$
					Labels.getLabel("process.warning") //$NON-NLS-1$
					,Messagebox.EXCLAMATION);
			if (result)
			{
				setCurrentProcess(getEngine().cancel(getCurrentProcess()));
				refresh();
			}

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
}
