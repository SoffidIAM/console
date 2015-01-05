package es.caib.bpm.servei;

import org.jbpm.svc.Service;
import org.jbpm.svc.ServiceFactory;

public class EventServiceFactory implements ServiceFactory {
	EventServiceImpl esi = new EventServiceImpl();
	
	public Service openService() {
		return esi;
	}

	public void close() {
	}

}
