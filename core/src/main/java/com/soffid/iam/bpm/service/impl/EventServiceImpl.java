package com.soffid.iam.bpm.service.impl;

import com.soffid.iam.api.BpmUserProcess;
import com.soffid.iam.service.UserService;
import com.soffid.iam.utils.Security;
import com.soffid.iam.ServiceLocator;

import es.caib.seycon.ng.exception.InternalErrorException;

import java.util.Collection;

import org.jbpm.graph.def.Event;
import org.jbpm.graph.def.GraphElement;
import org.jbpm.graph.exe.ExecutionContext;

public class EventServiceImpl implements org.jbpm.signal.EventService {

	public void close() {
	}

	public void fireEvent(String eventType, GraphElement graphElement,
			ExecutionContext executionContext) {
		if (eventType.equals(Event.EVENTTYPE_PROCESS_END) &&
				executionContext.getProcessInstance() != null)
		{
			Security.nestedLogin("bpm", new String [] {});
			try {
				UserService usvc = ServiceLocator.instance().getUserService();
				Collection<BpmUserProcess> proc = usvc.findBpmUserProcessByProcessId(executionContext.getProcessInstance().getId());
				for (BpmUserProcess pu : proc) {
                    if (pu.getTerminated() == null || !pu.getTerminated()) {
                        pu.setTerminated(true);
                        usvc.update(pu);
                    }
                }
			} catch (InternalErrorException e) {
				throw new RuntimeException("Unable to bind process to user", e);
			} finally {
				Security.nestedLogoff();
			}
		}
	}

}
