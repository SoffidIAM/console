/**
 * 
 */
/**
 * 
 */
/**
 * 
 */
package com.soffid.iam.service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.api.ScheduledTaskHandler;
import com.soffid.iam.doc.api.DocumentReference;
import com.soffid.iam.doc.service.DocumentService;
import com.soffid.iam.model.ScheduledTaskEntity;
import com.soffid.iam.model.ScheduledTaskHandlerEntity;
import com.soffid.iam.model.ServerEntity;
import com.soffid.iam.remote.RemoteServiceLocator;
import com.soffid.iam.remote.URLManager;
import com.soffid.iam.sync.service.SyncStatusService;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.ServerType;
import es.caib.seycon.ng.config.Config;
import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * @author bubu
 *
 */
public class ScheduledTaskServiceImpl extends ScheduledTaskServiceBase
{
	Log log = LogFactory.getLog(getClass());
	/* (non-Javadoc)
	 * @see com.soffid.iam.service.ScheduledTaskServiceBase#handleCreate(com.soffid.iam.api.ScheduledTaskHandler)
	 */
	@Override
	protected ScheduledTaskHandler handleCreate (ScheduledTaskHandler handler)
					throws Exception
	{
		ScheduledTaskHandlerEntity entity = getScheduledTaskHandlerEntityDao().scheduledTaskHandlerToEntity(handler);
		getScheduledTaskHandlerEntityDao().create(entity);
		return getScheduledTaskHandlerEntityDao().toScheduledTaskHandler(entity);
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.ScheduledTaskServiceBase#handleUpdate(com.soffid.iam.api.ScheduledTaskHandler)
	 */
	@Override
	protected ScheduledTaskHandler handleUpdate (ScheduledTaskHandler handler)
					throws Exception
	{
		ScheduledTaskHandlerEntity entity = getScheduledTaskHandlerEntityDao().scheduledTaskHandlerToEntity(handler);
		getScheduledTaskHandlerEntityDao().update(entity);
		return getScheduledTaskHandlerEntityDao().toScheduledTaskHandler(entity);
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.ScheduledTaskServiceBase#handleRemove(com.soffid.iam.api.ScheduledTaskHandler)
	 */
	@Override
	protected void handleRemove (ScheduledTaskHandler handler) throws Exception
	{
		ScheduledTaskHandlerEntity entity = getScheduledTaskHandlerEntityDao().scheduledTaskHandlerToEntity(handler);
		getScheduledTaskHandlerEntityDao().remove(entity);
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.ScheduledTaskServiceBase#handleListHandlers()
	 */
	@Override
	protected List<ScheduledTaskHandler> handleListHandlers () throws Exception
	{
		return getScheduledTaskHandlerEntityDao().toScheduledTaskHandlerList(getScheduledTaskHandlerEntityDao().loadAll());
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.ScheduledTaskServiceBase#handleCreate(com.soffid.iam.api.ScheduledTask)
	 */
	@Override
	protected ScheduledTask handleCreate (ScheduledTask task) throws Exception
	{
		reconfigureTasks();
		ScheduledTaskEntity entity = getScheduledTaskEntityDao().scheduledTaskToEntity(task);
		getScheduledTaskEntityDao().create(entity);
		audit (task.getName(), "C"); //$NON-NLS-1$
		return getScheduledTaskEntityDao().toScheduledTask(entity);
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.ScheduledTaskServiceBase#handleUpdate(com.soffid.iam.api.ScheduledTask)
	 */
	@Override
	protected ScheduledTask handleUpdate (ScheduledTask task) throws Exception
	{
		reconfigureTasks();
		ScheduledTaskEntity entity = getScheduledTaskEntityDao().load(task.getId());
		ScheduledTask oldtask = getScheduledTaskEntityDao().toScheduledTask(entity);
		task.setError(oldtask.isError());
		task.setActive(oldtask.isActive());
		task.setLastEnd(oldtask.getLastEnd());
		task.setLastExecution(oldtask.getLastExecution());
		task.setNextExecution(oldtask.getNextExecution());
		getScheduledTaskEntityDao().scheduledTaskToEntity(task, entity, true);
		getScheduledTaskEntityDao().update(entity);
		audit (task.getName(), "U"); //$NON-NLS-1$
		return getScheduledTaskEntityDao().toScheduledTask(entity);
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.ScheduledTaskServiceBase#handleRemove(com.soffid.iam.api.ScheduledTask)
	 */
	@Override
	protected void handleRemove (ScheduledTask task) throws Exception
	{
		ScheduledTaskEntity entity = getScheduledTaskEntityDao().scheduledTaskToEntity(task);
		getScheduledTaskEntityDao().remove(entity);
		audit (task.getName(), "R"); //$NON-NLS-1$
		reconfigureTasks();
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.ScheduledTaskServiceBase#handleListTasks()
	 */
	@Override
	protected List<ScheduledTask> handleListTasks () throws Exception
	{
		List<ScheduledTask> tasks = getScheduledTaskEntityDao().toScheduledTaskList(getScheduledTaskEntityDao().loadAll());
		Collections.sort(tasks, new Comparator<ScheduledTask>() {
			public int compare(ScheduledTask o1, ScheduledTask o2) {
				if (o1.getLastEnd() == null)
				{
					if (o2.getLastEnd() != null)
						return +1;
					else
					{
						if (o1.getLastExecution() == null)
						{
							if (o2.getLastExecution() == null)
							{
								return o1.getName().compareTo(o2.getName());
							}
							else
								return +1;
						}
						else if (o2.getLastExecution() == null)
							return -1;
						else
							return -o1.getLastExecution().compareTo(o2.getLastExecution());
							
					}
				}
				else
				{
					if (o2.getLastEnd() == null)
						return -1;
					else
						return -o1.getLastEnd().compareTo(o2.getLastEnd());
				}
			}
			
		});
		return tasks;
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.ScheduledTaskService#registerStartTask(com.soffid.iam.api.ScheduledTask)
	 */
	public void handleRegisterStartTask (ScheduledTask task) throws InternalErrorException
	{
		ScheduledTaskEntity entity = getScheduledTaskEntityDao().load(task.getId());
		entity.setActive(true);
		entity.setLastExecution(new Date());
		if (entity.getLogReferenceID() != null)
		{
			try {
				DocumentService ds = ServiceLocator.instance().getDocumentService();
				ds.deleteDocument(new DocumentReference( entity.getLogReferenceID()) );
			} catch (Exception e) {
				
			}
		}
		entity.setLogReferenceID(null);
		entity.setError(false);
		getScheduledTaskEntityDao().update(entity);
		getScheduledTaskEntityDao().toScheduledTask(entity, task);
		audit (task.getName(), "S"); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.ScheduledTaskService#registerEndTask(com.soffid.iam.api.ScheduledTask)
	 */
	public void handleRegisterEndTask (ScheduledTask task) throws InternalErrorException
	{
		task.setActive(false);
		task.setLogReferenceID(task.getLogReferenceID());
		task.setLastEnd(Calendar.getInstance());
		try {
			task.setServerName(Config.getConfig().getHostName());
		} catch (IOException e) {
		}
		ScheduledTaskEntity entity = getScheduledTaskEntityDao().scheduledTaskToEntity(task);
		getScheduledTaskEntityDao().update(entity);
		audit (task.getName(), "F"); //$NON-NLS-1$
	}

	private void reconfigureTasks () throws InternalErrorException
	{
		// Updates master configuration property
		Security.nestedLogin("master", Security.getCurrentAccount(), Security.ALL_PERMISSIONS);
		try
		{
			String timeStamp = Long.toString(System.currentTimeMillis());
			Configuration config = getConfigurationService().findParameterByNameAndNetworkName("soffid.schedule.timeStamp", null); //$NON-NLS-1$
			if (config == null)
			{
				config = new Configuration();
				config.setCode("soffid.schedule.timeStamp"); //$NON-NLS-1$
				config.setDescription("Task scheduler update time stamp"); //$NON-NLS-1$
				config.setValue(timeStamp);
				getConfigurationService().create(config);
			}
			else
			{
				config.setValue(timeStamp);
				getConfigurationService().update(config);
			}
		} finally {
			Security.nestedLogoff();
		}
	}
	
	private void audit (String task, String action) throws InternalErrorException
	{
		Audit aud = new Audit();
		aud.setAction(action);
		aud.setObject("SC_SCHTAS"); //$NON-NLS-1$
		aud.setScheduledTask(task);
		aud.setAuthor(Security.getCurrentAccount());
		aud.setCalendar(Calendar.getInstance());
		getAuditEntityDao().create(getAuditEntityDao().auditToEntity(aud));
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.ScheduledTaskServiceBase#handleFindScheduledTaskByHandlerAndParams(java.lang.String, java.lang.String)
	 */
	@Override
	protected ScheduledTask handleFindScheduledTaskByHandlerAndParams (String handler,
					String params) throws Exception
	{
		ScheduledTaskEntity st = 
			getScheduledTaskEntityDao().findByHandlerParams(handler, params);
		if (st == null)
			return null;
		else
			return getScheduledTaskEntityDao().toScheduledTask(st);
	}

	@Override
	protected List<ScheduledTask> handleListServerTasks(String server) throws Exception {
		Collection<ScheduledTaskEntity> entities = getScheduledTaskEntityDao().findAllByServer(server);
		List<ScheduledTask> tasks = getScheduledTaskEntityDao().toScheduledTaskList(entities);
		return tasks;
	}

	@Override
	protected void handleStartNow(ScheduledTask task) throws Exception {
		if (task.getServerName().equals("*"))
		{
			
		}
        RemoteServiceLocator rsl = createRemoteServiceLocator(task.getServerName());
        if (rsl == null)
        	throw new InternalErrorException("Not allowed to execute task on "+task.getServerName());
        SyncStatusService status = rsl.getSyncStatusService();
        status.startScheduledTask(task);
	}

    private RemoteServiceLocator createRemoteServiceLocator(String serverName) throws IOException, InternalErrorException {
        for (ServerEntity server:  getServerEntityDao().findByTenant(Security.getCurrentTenantName()))
        {
        	if (server.getType() == ServerType.MASTERSERVER && 
        			("*".equals(serverName) || server.getName().equals(serverName)))
        	{
                RemoteServiceLocator rsl = new RemoteServiceLocator(serverName);
                URLManager um = new URLManager(serverName);
            	rsl.setTenant(Security.getCurrentTenantName()+"\\"+Security.getCurrentAccount());
                rsl.setAuthToken(server.getAuth());
                return rsl;
        	}
        }
        return null;
    }
}
