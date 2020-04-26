package es.caib.bpm.toolkit;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;

import com.soffid.iam.web.users.additionalData.CustomField;

import es.caib.bpm.servei.ejb.BpmEngine;
import es.caib.bpm.toolkit.exception.UserWorkflowException;
import es.caib.bpm.toolkit.exception.WorkflowException;
import es.caib.bpm.vo.ProcessInstance;
import es.caib.bpm.vo.TaskInstance;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.events.SerializableEventListener;

/**
 * Interfaz de usuario generica para elementos de workflow.
 * 
 * @author Pablo Hern�n Gim�nez.
 */
public class WorkflowWindow extends Window {
    public static final String LOAD_EVENT = "onLoad"; //$NON-NLS-1$

    public static final String SAVE_EVENT = "onSave"; //$NON-NLS-1$

    public static final String COMPLETE_TRANSITION_EVENT = "onCompleteTransition"; //$NON-NLS-1$

    public static final String PREPARE_TRANSITION_EVENT = "onPrepareTransition"; //$NON-NLS-1$

	public static final String DELEGATION_INIT_EVENT = "onInitDelegation"; //$NON-NLS-1$

	public static final String TAB_SELECTED_EVENT = "onTabSelected"; //$NON-NLS-1$

    private SignaturaHandler signatureHandler;

    private boolean allowDelegate = true;
    private boolean showAttachments = true;
    private boolean canAddAttachments = false;
    private boolean canDeleteAttachments = false;

    public boolean isCanAddAttachments() {
        return canAddAttachments;
    }

    public void setCanAddAttachments(boolean canAddAttachments) {
        this.canAddAttachments = canAddAttachments;
    }

    public boolean isCanDeleteAttachments() {
        return canDeleteAttachments;
    }

    public void setCanDeleteAttachments(boolean canDeleteAttachments) {
        this.canDeleteAttachments = canDeleteAttachments;
    }

    public WorkflowWindow() {
    	Execution exe = Executions.getCurrent();
    	if (exe == null)
    		throw new UiException("Cannot get current execution");
		Map arguments = exe.getArg();
		processInstance = (ProcessInstance) arguments.get("processInstance"); //$NON-NLS-1$
		if (processInstance == null) {
			processInstance = new ProcessInstance();
			processInstance.setVariables(new HashMap());
		}

		taskInstance = (TaskInstance) arguments.get("taskInstance"); //$NON-NLS-1$
		if (taskInstance == null) {
			taskInstance = new TaskInstance();
			taskInstance.setVariables(processInstance.getVariables());
		}

        engine = (BpmEngine) arguments.get("engine"); //$NON-NLS-1$
        this.addEventListener(PREPARE_TRANSITION_EVENT, new SerializableEventListener() {
            public void onEvent(org.zkoss.zk.ui.event.Event event)
                    throws Exception {
                prepareTransition((String) event.getData());
            };
        });

        this.addEventListener(COMPLETE_TRANSITION_EVENT, new SerializableEventListener() {
            public void onEvent(org.zkoss.zk.ui.event.Event event)
                    throws Exception {
                completeTransition((String) event.getData());
            };
        });

        this.addEventListener(SAVE_EVENT, new SerializableEventListener() {
            public void onEvent(org.zkoss.zk.ui.event.Event event)
                    throws Exception {
                save();
            };
        });
        this.addEventListener(LOAD_EVENT, new SerializableEventListener() {
            public void onEvent(org.zkoss.zk.ui.event.Event event)
                    throws Exception {
            	load();
           };
        });
        
        this.addEventListener(DELEGATION_INIT_EVENT, new SerializableEventListener() {
            public void onEvent(org.zkoss.zk.ui.event.Event event)
                    throws Exception {
                onDelegationInit();
            };
        });

        this.addEventListener(TAB_SELECTED_EVENT, new SerializableEventListener() {
            public void onEvent(org.zkoss.zk.ui.event.Event event)
                    throws Exception {
                onTaskTabSelected((String) event.getData());
            };
        });
        
    }

    protected void load() {
    	
    }

    public TaskInstance getTask() {
        return taskInstance;
    }

    public void setTask(TaskInstance task) {
        this.taskInstance = task;
    }

	protected void prepareTransition(String trasition) throws WorkflowException {
		validateCustomFields (this);
	}


	private void validateCustomFields(Component c) {
		if (c instanceof CustomField)
		{
			CustomField customField = (CustomField) c;
			customField.validate();
		}
		else
		{
			for (Component c2 = c.getFirstChild(); c2 != null; c2 = c2.getNextSibling())
				validateCustomFields(c2);
		}
	}

    protected void completeTransition(String trasition)
            throws WorkflowException {
    }

    public BpmEngine getEngine() {
        return this.engine;
    }

    public void setEngine(BpmEngine engine) {
        this.engine = engine;
    }

    public ProcessInstance getProcessInstance() {
        return this.processInstance;
    }

    public void setProcessInstance(ProcessInstance instance) {
        this.processInstance = instance;
    }

    private ProcessInstance processInstance = null;
    private BpmEngine engine = null;
    private TaskInstance taskInstance = null;
    protected static Log log = LogFactory.getLog(WorkflowWindow.class);

    protected void save() throws WorkflowException {
    }

    public SignaturaHandler getSignatureHandler() {
        return signatureHandler;
    }

    public void setSignatureHandler(SignaturaHandler signatureHandler) {
        this.signatureHandler = signatureHandler;
    }

    public boolean isAllowDelegate() {
        return allowDelegate;
    }

    public void setAllowDelegate(boolean allowDelegate) {
        this.allowDelegate = allowDelegate;
    }

    public boolean isShowAttachments() {
        return showAttachments;
    }

    public void setShowAttachments(boolean showAttachments) {
        this.showAttachments = showAttachments;
    }

    public void refresh ()
    {
        DataModel dm = (DataModel) getParent().getFellowIfAny("BPMdata"); //$NON-NLS-1$
        if (dm != null)
            dm.refresh();
    }

    public void onDelegationInit(){
    	
    }
    
    public void onTaskTabSelected(String selectedId){
    	
    }
    
}
