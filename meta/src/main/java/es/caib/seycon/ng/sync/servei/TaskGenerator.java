//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.sync.servei;
import com.soffid.iam.model.TenantEntity;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.ConfiguracioEntity;
import es.caib.seycon.ng.model.ServerEntity;
import es.caib.seycon.ng.model.ServerInstanceEntity;
import es.caib.seycon.ng.servei.ConfiguracioService;
import es.caib.seycon.ng.servei.SeyconServerService;

import java.util.Set;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service ( internal=true,
	 serverOnly=true,
	 translatedName="TaskGenerator",
	 translatedPackage="com.soffid.iam.sync.service")
@Depends ({es.caib.seycon.ng.sync.servei.TaskQueue.class,
	es.caib.seycon.ng.servei.InternalPasswordService.class,
	es.caib.seycon.ng.sync.servei.ChangePasswordNotificationQueue.class,
	es.caib.seycon.ng.sync.servei.SecretStoreService.class,
	es.caib.seycon.ng.model.TasqueEntity.class,
	ServerEntity.class,
	TenantEntity.class,
	ConfiguracioService.class,
	es.caib.seycon.ng.model.DispatcherEntity.class,
	ServerInstanceEntity.class,
	ConfiguracioEntity.class,
	SeyconServerService.class})
@Description("Generador de tareas. Recupera las tareas a realizar de la tabla SC_TASQUE y\nlas encola en TaskQueue. Adicionalmente coordina tareas de sincronización\nentre los dispatchers y otros threads relacionados con el SSO tales como el\nacceso a base de datos<BR>\nConsulta las siguientes propiedades del sistema:<BR>\n<li>server.getlogs: true permite la lectura de logs</li>\n<li>server.debuglevel: nivel de información de debug 0 = mínimo 10 = máximo</li>\n<li>server.dispatcher.enabled: true si el dispatcher debe recuperar las\ntareas de la tabla SC_TASQUE.</li>\n<li>server.dispatcher.delay: pausa (en ms.) que realizará el task dispatcher\ntras procesar la cola de tareas pendientes antes de volver a comenzar</li>\n<li>server.dispatcher.timeout: pausa (en ms.) que realizará el task\ndispatcher cuando se produzca en error de comunicaciones</li>\n<li>server.privatekey: archivo donde se encuentra la clave privada DSA que\nse utilizará para contactar con los agentes</li>\n<li>server.query.connections: Número maximo de conexiones a abrir contra la\nbase de datos (por defecto 5)</li>\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
public abstract class TaskGenerator {

	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String getStatus()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.NEVER ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"Exception"})
	public void loadTasks()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean canGetLog(
		com.soffid.iam.sync.engine.DispatcherHandler td)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void finishGetLog(
		com.soffid.iam.sync.engine.DispatcherHandler td)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void shutDown()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void updateAgents()
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean isEnabled()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
	
	public boolean isMainServer() {return false;}
	
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.sync.engine.DispatcherHandler> getDispatchers()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.sync.engine.DispatcherHandler> getAllTenantsDispatchers()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void setEnabled(
		boolean enabled)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.sync.engine.DispatcherHandler getDispatcher(
		java.lang.String id)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	@Transactional(readOnly=true)
	public Set<Long> getActiveTenants() { return null; }
	
	@Transactional(readOnly=true)
	public String startVirtualSourceTransaction()
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Transactional(readOnly=true)
	public String startVirtualSourceTransaction(boolean readonly)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Transactional(readOnly=true)
	public void finishVirtualSourceTransaction(String virtualTransactionId)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateClusterStatus() {
	}
	
	public void purgeServerInstances() {
	}
}
