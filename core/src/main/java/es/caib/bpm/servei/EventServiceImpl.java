package es.caib.bpm.servei;

import java.util.Collection;

import org.jbpm.graph.def.Event;
import org.jbpm.graph.def.GraphElement;
import org.jbpm.graph.exe.ExecutionContext;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.UsuariWFProcess;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.UsuariService;
import es.caib.seycon.ng.utils.Security;


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
				UsuariService usvc = ServiceLocator.instance().getUsuariService();
				Collection<UsuariWFProcess> proc = usvc.findProcessosWFUsuariByIdProces(executionContext.getProcessInstance().getId());
				for (UsuariWFProcess pu: proc)
				{
					if (pu.getFinalitzat() == null || ! pu.getFinalitzat())
					{
						pu.setFinalitzat(true);
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
