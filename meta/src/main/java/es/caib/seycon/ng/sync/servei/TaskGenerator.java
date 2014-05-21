//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.sync.servei;
import com.soffid.mda.annotation.*;

import org.springframework.transaction.annotation.Transactional;

@Service ( internal=true,
	 serverOnly=true,
	 translatedName="TaskGenerator",
	 translatedPackage="es.caib.seycon.ng.sync.servei")
@Depends ({es.caib.seycon.ng.sync.servei.TaskQueue.class,
	es.caib.seycon.ng.servei.InternalPasswordService.class,
	es.caib.seycon.ng.sync.servei.ChangePasswordNotificationQueue.class,
	es.caib.seycon.ng.sync.servei.SecretStoreService.class,
	es.caib.seycon.ng.model.TasqueEntity.class,
	es.caib.seycon.ng.model.DispatcherEntity.class})
@Description("Generador de tareas. Recupera las tareas a realizar de la tabla SC_TASQUE y\nlas encola en TaskQueue. Adicionalmente coordina tareas de sincronización\nentre los dispatchers y otros threads relacionados con el SSO tales como el\nacceso a base de datos<BR>\nConsulta las siguientes propiedades del sistema:<BR>\n<li>server.getlogs: true permite la lectura de logs</li>\n<li>server.debuglevel: nivel de información de debug 0 = mínimo 10 = máximo</li>\n<li>server.dispatcher.enabled: true si el dispatcher debe recuperar las\ntareas de la tabla SC_TASQUE.</li>\n<li>server.dispatcher.delay: pausa (en ms.) que realizará el task dispatcher\ntras procesar la cola de tareas pendientes antes de volver a comenzar</li>\n<li>server.dispatcher.timeout: pausa (en ms.) que realizará el task\ndispatcher cuando se produzca en error de comunicaciones</li>\n<li>server.privatekey: archivo donde se encuentra la clave privada DSA que\nse utilizará para contactar con los agentes</li>\n<li>server.query.connections: Número maximo de conexiones a abrir contra la\nbase de datos (por defecto 5)</li>\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
public abstract class TaskGenerator {

	@Description("Generador de tareas. Recupera las tareas a realizar de la tabla SC_TASQUE y\nlas encola en TaskQueue. Adicionalmente coordina tareas de sincronización\nentre los dispatchers y otros threads relacionados con el SSO tales como el\nacceso a base de datos<BR>\nConsulta las siguientes propiedades del sistema:<BR>\n<li>server.getlogs: true permite la lectura de logs</li>\n<li>server.debuglevel: nivel de información de debug 0 = mínimo 10 = máximo</li>\n<li>server.dispatcher.enabled: true si el dispatcher debe recuperar las\ntareas de la tabla SC_TASQUE.</li>\n<li>server.dispatcher.delay: pausa (en ms.) que realizará el task dispatcher\ntras procesar la cola de tareas pendientes antes de volver a comenzar</li>\n<li>server.dispatcher.timeout: pausa (en ms.) que realizará el task\ndispatcher cuando se produzca en error de comunicaciones</li>\n<li>server.privatekey: archivo donde se encuentra la clave privada DSA que\nse utilizará para contactar con los agentes</li>\n<li>server.query.connections: Número maximo de conexiones a abrir contra la\nbase de datos (por defecto 5)</li>\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String getStatus()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Description("Generador de tareas. Recupera las tareas a realizar de la tabla SC_TASQUE y\nlas encola en TaskQueue. Adicionalmente coordina tareas de sincronización\nentre los dispatchers y otros threads relacionados con el SSO tales como el\nacceso a base de datos<BR>\nConsulta las siguientes propiedades del sistema:<BR>\n<li>server.getlogs: true permite la lectura de logs</li>\n<li>server.debuglevel: nivel de información de debug 0 = mínimo 10 = máximo</li>\n<li>server.dispatcher.enabled: true si el dispatcher debe recuperar las\ntareas de la tabla SC_TASQUE.</li>\n<li>server.dispatcher.delay: pausa (en ms.) que realizará el task dispatcher\ntras procesar la cola de tareas pendientes antes de volver a comenzar</li>\n<li>server.dispatcher.timeout: pausa (en ms.) que realizará el task\ndispatcher cuando se produzca en error de comunicaciones</li>\n<li>server.privatekey: archivo donde se encuentra la clave privada DSA que\nse utilizará para contactar con los agentes</li>\n<li>server.query.connections: Número maximo de conexiones a abrir contra la\nbase de datos (por defecto 5)</li>\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.NEVER ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"Exception"})
	public void loadTasks()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Description("Generador de tareas. Recupera las tareas a realizar de la tabla SC_TASQUE y\nlas encola en TaskQueue. Adicionalmente coordina tareas de sincronización\nentre los dispatchers y otros threads relacionados con el SSO tales como el\nacceso a base de datos<BR>\nConsulta las siguientes propiedades del sistema:<BR>\n<li>server.getlogs: true permite la lectura de logs</li>\n<li>server.debuglevel: nivel de información de debug 0 = mínimo 10 = máximo</li>\n<li>server.dispatcher.enabled: true si el dispatcher debe recuperar las\ntareas de la tabla SC_TASQUE.</li>\n<li>server.dispatcher.delay: pausa (en ms.) que realizará el task dispatcher\ntras procesar la cola de tareas pendientes antes de volver a comenzar</li>\n<li>server.dispatcher.timeout: pausa (en ms.) que realizará el task\ndispatcher cuando se produzca en error de comunicaciones</li>\n<li>server.privatekey: archivo donde se encuentra la clave privada DSA que\nse utilizará para contactar con los agentes</li>\n<li>server.query.connections: Número maximo de conexiones a abrir contra la\nbase de datos (por defecto 5)</li>\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean canGetLog(
		es.caib.seycon.ng.sync.engine.DispatcherHandler td)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
	@Description("Generador de tareas. Recupera las tareas a realizar de la tabla SC_TASQUE y\nlas encola en TaskQueue. Adicionalmente coordina tareas de sincronización\nentre los dispatchers y otros threads relacionados con el SSO tales como el\nacceso a base de datos<BR>\nConsulta las siguientes propiedades del sistema:<BR>\n<li>server.getlogs: true permite la lectura de logs</li>\n<li>server.debuglevel: nivel de información de debug 0 = mínimo 10 = máximo</li>\n<li>server.dispatcher.enabled: true si el dispatcher debe recuperar las\ntareas de la tabla SC_TASQUE.</li>\n<li>server.dispatcher.delay: pausa (en ms.) que realizará el task dispatcher\ntras procesar la cola de tareas pendientes antes de volver a comenzar</li>\n<li>server.dispatcher.timeout: pausa (en ms.) que realizará el task\ndispatcher cuando se produzca en error de comunicaciones</li>\n<li>server.privatekey: archivo donde se encuentra la clave privada DSA que\nse utilizará para contactar con los agentes</li>\n<li>server.query.connections: Número maximo de conexiones a abrir contra la\nbase de datos (por defecto 5)</li>\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void finishGetLog(
		es.caib.seycon.ng.sync.engine.DispatcherHandler td)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Description("Generador de tareas. Recupera las tareas a realizar de la tabla SC_TASQUE y\nlas encola en TaskQueue. Adicionalmente coordina tareas de sincronización\nentre los dispatchers y otros threads relacionados con el SSO tales como el\nacceso a base de datos<BR>\nConsulta las siguientes propiedades del sistema:<BR>\n<li>server.getlogs: true permite la lectura de logs</li>\n<li>server.debuglevel: nivel de información de debug 0 = mínimo 10 = máximo</li>\n<li>server.dispatcher.enabled: true si el dispatcher debe recuperar las\ntareas de la tabla SC_TASQUE.</li>\n<li>server.dispatcher.delay: pausa (en ms.) que realizará el task dispatcher\ntras procesar la cola de tareas pendientes antes de volver a comenzar</li>\n<li>server.dispatcher.timeout: pausa (en ms.) que realizará el task\ndispatcher cuando se produzca en error de comunicaciones</li>\n<li>server.privatekey: archivo donde se encuentra la clave privada DSA que\nse utilizará para contactar con los agentes</li>\n<li>server.query.connections: Número maximo de conexiones a abrir contra la\nbase de datos (por defecto 5)</li>\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void shutDown()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Description("Generador de tareas. Recupera las tareas a realizar de la tabla SC_TASQUE y\nlas encola en TaskQueue. Adicionalmente coordina tareas de sincronización\nentre los dispatchers y otros threads relacionados con el SSO tales como el\nacceso a base de datos<BR>\nConsulta las siguientes propiedades del sistema:<BR>\n<li>server.getlogs: true permite la lectura de logs</li>\n<li>server.debuglevel: nivel de información de debug 0 = mínimo 10 = máximo</li>\n<li>server.dispatcher.enabled: true si el dispatcher debe recuperar las\ntareas de la tabla SC_TASQUE.</li>\n<li>server.dispatcher.delay: pausa (en ms.) que realizará el task dispatcher\ntras procesar la cola de tareas pendientes antes de volver a comenzar</li>\n<li>server.dispatcher.timeout: pausa (en ms.) que realizará el task\ndispatcher cuando se produzca en error de comunicaciones</li>\n<li>server.privatekey: archivo donde se encuentra la clave privada DSA que\nse utilizará para contactar con los agentes</li>\n<li>server.query.connections: Número maximo de conexiones a abrir contra la\nbase de datos (por defecto 5)</li>\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void updateAgents()
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Description("Generador de tareas. Recupera las tareas a realizar de la tabla SC_TASQUE y\nlas encola en TaskQueue. Adicionalmente coordina tareas de sincronización\nentre los dispatchers y otros threads relacionados con el SSO tales como el\nacceso a base de datos<BR>\nConsulta las siguientes propiedades del sistema:<BR>\n<li>server.getlogs: true permite la lectura de logs</li>\n<li>server.debuglevel: nivel de información de debug 0 = mínimo 10 = máximo</li>\n<li>server.dispatcher.enabled: true si el dispatcher debe recuperar las\ntareas de la tabla SC_TASQUE.</li>\n<li>server.dispatcher.delay: pausa (en ms.) que realizará el task dispatcher\ntras procesar la cola de tareas pendientes antes de volver a comenzar</li>\n<li>server.dispatcher.timeout: pausa (en ms.) que realizará el task\ndispatcher cuando se produzca en error de comunicaciones</li>\n<li>server.privatekey: archivo donde se encuentra la clave privada DSA que\nse utilizará para contactar con los agentes</li>\n<li>server.query.connections: Número maximo de conexiones a abrir contra la\nbase de datos (por defecto 5)</li>\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean isEnabled()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
	@Description("Generador de tareas. Recupera las tareas a realizar de la tabla SC_TASQUE y\nlas encola en TaskQueue. Adicionalmente coordina tareas de sincronización\nentre los dispatchers y otros threads relacionados con el SSO tales como el\nacceso a base de datos<BR>\nConsulta las siguientes propiedades del sistema:<BR>\n<li>server.getlogs: true permite la lectura de logs</li>\n<li>server.debuglevel: nivel de información de debug 0 = mínimo 10 = máximo</li>\n<li>server.dispatcher.enabled: true si el dispatcher debe recuperar las\ntareas de la tabla SC_TASQUE.</li>\n<li>server.dispatcher.delay: pausa (en ms.) que realizará el task dispatcher\ntras procesar la cola de tareas pendientes antes de volver a comenzar</li>\n<li>server.dispatcher.timeout: pausa (en ms.) que realizará el task\ndispatcher cuando se produzca en error de comunicaciones</li>\n<li>server.privatekey: archivo donde se encuentra la clave privada DSA que\nse utilizará para contactar con los agentes</li>\n<li>server.query.connections: Número maximo de conexiones a abrir contra la\nbase de datos (por defecto 5)</li>\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.sync.engine.DispatcherHandler> getDispatchers()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Description("Generador de tareas. Recupera las tareas a realizar de la tabla SC_TASQUE y\nlas encola en TaskQueue. Adicionalmente coordina tareas de sincronización\nentre los dispatchers y otros threads relacionados con el SSO tales como el\nacceso a base de datos<BR>\nConsulta las siguientes propiedades del sistema:<BR>\n<li>server.getlogs: true permite la lectura de logs</li>\n<li>server.debuglevel: nivel de información de debug 0 = mínimo 10 = máximo</li>\n<li>server.dispatcher.enabled: true si el dispatcher debe recuperar las\ntareas de la tabla SC_TASQUE.</li>\n<li>server.dispatcher.delay: pausa (en ms.) que realizará el task dispatcher\ntras procesar la cola de tareas pendientes antes de volver a comenzar</li>\n<li>server.dispatcher.timeout: pausa (en ms.) que realizará el task\ndispatcher cuando se produzca en error de comunicaciones</li>\n<li>server.privatekey: archivo donde se encuentra la clave privada DSA que\nse utilizará para contactar con los agentes</li>\n<li>server.query.connections: Número maximo de conexiones a abrir contra la\nbase de datos (por defecto 5)</li>\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void setEnabled(
		boolean enabled)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Description("Generador de tareas. Recupera las tareas a realizar de la tabla SC_TASQUE y\nlas encola en TaskQueue. Adicionalmente coordina tareas de sincronización\nentre los dispatchers y otros threads relacionados con el SSO tales como el\nacceso a base de datos<BR>\nConsulta las siguientes propiedades del sistema:<BR>\n<li>server.getlogs: true permite la lectura de logs</li>\n<li>server.debuglevel: nivel de información de debug 0 = mínimo 10 = máximo</li>\n<li>server.dispatcher.enabled: true si el dispatcher debe recuperar las\ntareas de la tabla SC_TASQUE.</li>\n<li>server.dispatcher.delay: pausa (en ms.) que realizará el task dispatcher\ntras procesar la cola de tareas pendientes antes de volver a comenzar</li>\n<li>server.dispatcher.timeout: pausa (en ms.) que realizará el task\ndispatcher cuando se produzca en error de comunicaciones</li>\n<li>server.privatekey: archivo donde se encuentra la clave privada DSA que\nse utilizará para contactar con los agentes</li>\n<li>server.query.connections: Número maximo de conexiones a abrir contra la\nbase de datos (por defecto 5)</li>\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.sync.engine.DispatcherHandler getDispatcher(
		java.lang.String id)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
}
