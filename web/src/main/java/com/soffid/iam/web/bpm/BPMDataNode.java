package com.soffid.iam.web.bpm;

import java.util.Vector;

import org.zkoss.zk.ui.ext.AfterCompose;

import com.soffid.iam.bpm.api.ProcessDefinition;
import com.soffid.iam.bpm.api.ProcessInstance;
import com.soffid.iam.bpm.api.TaskInstance;

import es.caib.zkib.datamodel.DataContext;
import es.caib.zkib.datamodel.Finder;

public class BPMDataNode extends DummyDataNode implements AfterCompose {
	private TaskInstance taskInstance;
	private ProcessInstance processInstance;
	private ProcessDefinition processDefinition;
	
	public BPMDataNode(DataContext ctx) {
		super(ctx);
        // Task
        addFinder("taskInstance", //$NON-NLS-1$
                        new Finder () {
                                public java.util.Collection find() throws Exception {
                                    Vector v = new Vector();
                                    if (taskInstance != null)
                                    	v.add (taskInstance);
                                    return v;
                                };
                                public Object newInstance() throws Exception {
                                        throw new UnsupportedOperationException();
                                }
								public boolean refreshAfterCommit() {
									return false;
								}
                        },
                        TaskDataNode.class);
        // Process
        addFinder("processInstance", //$NON-NLS-1$
                new Finder () {
                        public java.util.Collection find() throws Exception {
                            Vector v = new Vector();
                            if (processInstance != null)
                            	v.add (processInstance);
                            return v;
                        };
                        public Object newInstance() throws Exception {
                                throw new UnsupportedOperationException();
                        }
						public boolean refreshAfterCommit() {
							return false;
						}
                },
                ProcessDataNode.class);
        
        // Process
        addFinder("processDefinition", //$NON-NLS-1$
                new Finder () {
                        public java.util.Collection find() throws Exception {
                            Vector v = new Vector();
                            if (processDefinition != null)
                            	v.add (processDefinition);
                            return v;
                        };
                        public Object newInstance() throws Exception {
                                throw new UnsupportedOperationException();
                        }
						public boolean refreshAfterCommit() {
							return false;
						}
                },
                DummyDataNode.class);
	}

	public ProcessDefinition getProcessDefinition() {
		return processDefinition;
	}

	public void setProcessDefinition(ProcessDefinition processDefinition) {
		this.processDefinition = processDefinition;
	}

	public TaskInstance getTaskInstance() {
		return taskInstance;
	}

	public void setTaskInstance(TaskInstance taskInstance) {
		this.taskInstance = taskInstance;
	}

	public ProcessInstance getProcessInstance() {
		return processInstance;
	}

	public void setProcessInstance(ProcessInstance processInstance) {
		this.processInstance = processInstance;
	}

	@Override
	public void afterCompose() {
		// TODO Auto-generated method stub
		
	}
}
