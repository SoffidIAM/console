package es.caib.bpm.datamodel;

import java.util.Vector;

import es.caib.bpm.vo.ProcessDefinition;
import es.caib.bpm.vo.ProcessInstance;
import es.caib.bpm.vo.TaskInstance;
import es.caib.zkib.datamodel.DataContext;
import es.caib.zkib.datamodel.Finder;

public class BPMDataNode extends DummyDataNode {
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
                        DummyDataNode.class);
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
}
