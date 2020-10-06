package com.soffid.iam.web.bpm;

import org.zkoss.zk.ui.Component;

import com.soffid.iam.bpm.api.ProcessInstance;
import com.soffid.iam.bpm.api.TaskInstance;

public interface WorkflowWindowInterface extends Component {
    public static final String LOAD_EVENT = "onLoad"; //$NON-NLS-1$

    public static final String SAVE_EVENT = "onSave"; //$NON-NLS-1$

    public static final String COMPLETE_TRANSITION_EVENT = "onCompleteTransition"; //$NON-NLS-1$

    public static final String PREPARE_TRANSITION_EVENT = "onPrepareTransition"; //$NON-NLS-1$

	public static final String DELEGATION_INIT_EVENT = "onInitDelegation"; //$NON-NLS-1$

	public static final String TAB_SELECTED_EVENT = "onTabSelected"; //$NON-NLS-1$


	boolean isCanAddAttachments();

	void setCanAddAttachments(boolean canAddAttachments);

	boolean isCanDeleteAttachments();

	void setCanDeleteAttachments(boolean canDeleteAttachments);

	void setTask(TaskInstance task);

	void setProcessInstance(ProcessInstance instance);

	boolean isAllowDelegate();

	void setAllowDelegate(boolean allowDelegate);

	boolean isShowAttachments();

	void setShowAttachments(boolean showAttachments);
	
    void refresh ();

}