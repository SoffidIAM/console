package com.soffid.iam.web.bpm;

import java.util.Vector;

import com.soffid.iam.bpm.api.ProcessInstance;
import com.soffid.iam.bpm.api.ProcessLog;
import es.caib.zkib.datamodel.DataContext;
import es.caib.zkib.datamodel.Finder;

public class ProcessDataNode extends DummyDataNode {

	private ProcessInstance pi;

	public ProcessDataNode(DataContext ctx) {
		super(ctx);
		pi = (ProcessInstance) ctx.getData();
        // Process
        addFinder("log", //$NON-NLS-1$
                new Finder () {
                        public java.util.Collection find() throws Exception {
                            Vector v = new Vector();
                            if (pi != null)
                            {
                            	ProcessLog[] logs = BPMApplication.getEngine().getProcessLog(pi);
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
