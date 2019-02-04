//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.sync.servei;

import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.sync.engine.intf.DebugTaskResults;
import com.soffid.iam.sync.service.SyncServerStatsService;
import com.soffid.mda.annotation.*;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

@Service(internal = true, serverOnly = true, translatedName = "TaskQueue", translatedPackage = "com.soffid.iam.sync.service")
@Depends({ es.caib.seycon.ng.sync.servei.TaskGenerator.class, es.caib.seycon.ng.servei.InternalPasswordService.class,
		es.caib.seycon.ng.model.UsuariEntity.class, es.caib.seycon.ng.model.DominiContrasenyaEntity.class,
		es.caib.seycon.ng.model.DominiUsuariEntity.class, es.caib.seycon.ng.model.TasqueEntity.class,
		es.caib.seycon.ng.model.TaskLogEntity.class, es.caib.seycon.ng.model.DispatcherEntity.class,
		es.caib.seycon.ng.sync.servei.SecretStoreService.class, es.caib.seycon.ng.model.AccountEntity.class,
		es.caib.seycon.ng.servei.AccountService.class, es.caib.seycon.ng.sync.servei.ServerService.class,
		es.caib.seycon.ng.sync.servei.ChangePasswordNotificationQueue.class,
		es.caib.seycon.ng.sync.servei.TaskQueue.class ,
		SyncServerStatsService.class})
@Description("Cola de tareas pendientes de ejecución\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
public abstract class TaskQueue {

	@Description("Cola de tareas pendientes de ejecución\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRES_NEW, isolation = org.springframework.transaction.annotation.Isolation.READ_COMMITTED, rollbackForClassName = {
			"java.lang.Exception" })
	public void addTask(TaskHandler newTask) throws es.caib.seycon.ng.exception.InternalErrorException,
			es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Description("Cola de tareas pendientes de ejecución\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRES_NEW, isolation = org.springframework.transaction.annotation.Isolation.READ_COMMITTED, rollbackForClassName = {
			"java.lang.Exception" })
	public TaskHandler addTask(es.caib.seycon.ng.model.TasqueEntity newTask)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Description("Cola de tareas pendientes de ejecución\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.NEVER, isolation = org.springframework.transaction.annotation.Isolation.READ_COMMITTED, rollbackForClassName = {
			"java.lang.Exception" })
	public TaskHandler getPendingTask(com.soffid.iam.sync.engine.DispatcherHandler taskDispatcher)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Description("Cola de tareas pendientes de ejecución\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.NEVER, isolation = org.springframework.transaction.annotation.Isolation.READ_COMMITTED, rollbackForClassName = {
			"java.lang.Exception" })
	public TaskHandler getNextPendingTask(com.soffid.iam.sync.engine.DispatcherHandler taskDispatcher,
			TaskHandler previousTask) throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Description("Cola de tareas pendientes de ejecución\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public int countTasks() throws es.caib.seycon.ng.exception.InternalErrorException {
		return 0;
	}

	@Description("Cola de tareas pendientes de ejecución\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public int countTasks(com.soffid.iam.sync.engine.DispatcherHandler taskDispatcher)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return 0;
	}

	@Description("Number of errored tasks")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public int countErrorTasks(com.soffid.iam.sync.engine.DispatcherHandler taskDispatcher)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return 0;
	}

	@Description("Cola de tareas pendientes de ejecución\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS)
	public void expireTasks() throws es.caib.seycon.ng.exception.InternalErrorException,
			es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Description("Cola de tareas pendientes de ejecución\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void notifyTaskStatus(TaskHandler task, com.soffid.iam.sync.engine.DispatcherHandler taskDispatcher,
			boolean bOK, @Nullable java.lang.String sReason, @Nullable java.lang.Throwable t)
			throws es.caib.seycon.ng.exception.InternalErrorException,
			es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Description("Cola de tareas pendientes de ejecución\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void cancelTask(long taskId) throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Description("Cola de tareas pendientes de ejecución\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRES_NEW, isolation = org.springframework.transaction.annotation.Isolation.READ_COMMITTED, rollbackForClassName = {
			"java.lang.Exception" })
	public void updateTask(es.caib.seycon.ng.model.TasqueEntity task)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Description("Cola de tareas pendientes de ejecución\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRES_NEW, isolation = org.springframework.transaction.annotation.Isolation.READ_COMMITTED, rollbackForClassName = {
			"java.lang.Exception" })
	public void removeTask(es.caib.seycon.ng.model.TasqueEntity task)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Description("Cola de tareas pendientes de ejecución\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Map<String,Exception> processOBTask(TaskHandler task) throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Description("Executes a task. Returns a map with two attributes for each dispatcher: log and status")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Map<String,DebugTaskResults> debugTask(TaskHandler task) throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Description("Cola de tareas pendientes de ejecución\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRES_NEW, isolation = org.springframework.transaction.annotation.Isolation.READ_COMMITTED, rollbackForClassName = {
			"java.lang.Exception" })
	public void notifyTaskStatusNewTransaction(TaskHandler task,
			com.soffid.iam.sync.engine.DispatcherHandler taskDispatcher, boolean bOK,
			@Nullable java.lang.String sReason, @Nullable java.lang.Throwable t)
			throws es.caib.seycon.ng.exception.InternalErrorException,
			es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Description("Cola de tareas pendientes de ejecución\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void pushTaskToPersist(TaskHandler newTask) throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Description("Cola de tareas pendientes de ejecución\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public TaskHandler peekTaskToPersist() throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Description("Cola de tareas pendientes de ejecución\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void persistTask(TaskHandler newTask) throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Description("Cola de tareas pendientes de ejecución\n\n@author $Author: u07286 $\n@version $Revision: 1.1 $\n")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public TaskHandler findTaskHandlerById(long taskId) throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}
}
