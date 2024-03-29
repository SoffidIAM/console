package com.soffid.iam.web.bpm;

import java.util.Vector;

import com.soffid.iam.bpm.api.ProcessLog;
import com.soffid.iam.bpm.api.TaskInstance;

import es.caib.zkib.datamodel.DataContext;
import es.caib.zkib.datamodel.Finder;

public class TaskDataNode extends DummyDataNode {

	private TaskInstance ti;

	public TaskDataNode(DataContext ctx) {
		super(ctx);
		ti = (TaskInstance) ctx.getData();
        // Process
        addFinder("log", //$NON-NLS-1$
                new Finder () {
                        public java.util.Collection find() throws Exception {
                            Vector v = new Vector();
                            if (ti != null)
                            {
                            	ProcessLog[] logs = BPMApplication.getEngine().getTaskLog(ti);
                            	if (logs != null)
                            	{
                            		for (int i = 0; i < logs.length; i++)
                            			v.add (logs[i]);
                            	}
                            }
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

}
