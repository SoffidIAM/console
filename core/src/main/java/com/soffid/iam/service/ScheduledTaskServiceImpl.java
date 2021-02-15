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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.api.ScheduledTaskHandler;
import com.soffid.iam.api.ScheduledTaskLog;
import com.soffid.iam.doc.api.DocumentInputStream;
import com.soffid.iam.doc.api.DocumentReference;
import com.soffid.iam.doc.exception.DocumentBeanException;
import com.soffid.iam.doc.service.DocumentService;
import com.soffid.iam.model.ConfigEntity;
import com.soffid.iam.model.ScheduledTaskEntity;
import com.soffid.iam.model.ScheduledTaskHandlerEntity;
import com.soffid.iam.model.ScheduledTaskLogEntity;
import com.soffid.iam.model.ServerEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.remote.RemoteServiceLocator;
import com.soffid.iam.remote.URLManager;
import com.soffid.iam.sync.service.SyncStatusService;
import com.soffid.iam.utils.ConfigurationCache;
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
		entity.setActive(false);
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
		if (entity.getLastExecution() != null) {
			int max = 10;
			try {
				max = Integer.parseInt( ConfigurationCache.getProperty("soffid.scheduledTask.maxLog") );
			} catch (NumberFormatException e) {}
			LinkedList<ScheduledTaskLogEntity> l = new LinkedList<ScheduledTaskLogEntity> ( entity.getLogs());
			Collections.sort(l, new Comparator<ScheduledTaskLogEntity>() {
				@Override
				public int compare(ScheduledTaskLogEntity o1, ScheduledTaskLogEntity o2) {
					return o1.getTime().compareTo(o2.getTime());
				}
			});
			while ( l.size() >= max) {
				ScheduledTaskLogEntity last = l.pollLast();
				getScheduledTaskLogEntityDao().remove(last);
				entity.getLogs().remove(last);
			}
			ScheduledTaskLogEntity taskLog = getScheduledTaskLogEntityDao().newScheduledTaskLogEntity();
			taskLog.setError(entity.isError());
			taskLog.setLogReferenceID(entity.getLogReferenceID());
			taskLog.setTask(entity);
			taskLog.setTime(entity.getLastExecution());
			getScheduledTaskLogEntityDao().create(taskLog);
			entity.getLogs().add(taskLog);
			entity.setLogReferenceID(null);
		}
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
		if (entity.getServer() == null) {
			try {
				entity.setServer( getServerEntityDao().findByName( Config.getConfig().getHostName() ) );
			} catch (IOException e) {
			}
		}
		entity.setLogReferenceID(null);
		entity.setError(false);
		getScheduledTaskEntityDao().update(entity);
		getScheduledTaskEntityDao().toScheduledTask(entity, task);
		if ( ! task.getHandlerName().equals(SystemScheduledTasks.UPDATE_STATS))
			audit (task.getName(), "S"); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.ScheduledTaskService#registerEndTask(com.soffid.iam.api.ScheduledTask)
	 */
	public void handleRegisterEndTask (ScheduledTask task) throws InternalErrorException, DocumentBeanException
	{
		ScheduledTaskEntity entity = getScheduledTaskEntityDao().load(task.getId());
		if (entity.getLogReferenceID() != null && entity.getLogReferenceID().contains(":"))
		{
			DocumentService ds = getDocumentService();
			try {
				ds.deleteDocument(new DocumentReference(entity.getLogReferenceID()));
			} catch (Throwable th ) {
				// Ignore deletion problem
			}
		}
		entity.setLogReferenceID(task.getLogReferenceID());
		entity.setActive(false);
		entity.setLastEnd(new Date());
		entity.setError(task.isError());
		try {
			ServerEntity s = getServerEntityDao().findByName(Config.getConfig().getHostName());
			entity.setServer(s);
		} catch (IOException e) {
		}
		getScheduledTaskEntityDao().update(entity);
		if ( ! task.getHandlerName().equals(SystemScheduledTasks.UPDATE_STATS))
			audit (task.getName(), "F"); //$NON-NLS-1$
		
		if (task.isError())
		{
			String mailNotification = System.getProperty("soffid.scheduler.error.notify"); //$NON-NLS-1$
			if (mailNotification != null)
			{
				StringBuffer body = new StringBuffer();
				body.append(String.format(Messages.getString("ScheduledTaskServiceImpl.2"), //$NON-NLS-1$
						task.getName()))
					.append("\n"); //$NON-NLS-1$
				
				if (task.getLogReferenceID() != null)
				{
					StringBuffer tail = new StringBuffer();
					DocumentService ds = getDocumentService();
					try {
						boolean truncated = false;
						ds.openDocument( new DocumentReference (task.getLogReferenceID()));
						DocumentInputStream in  = new DocumentInputStream( ds );
						InputStreamReader reader = new InputStreamReader(in);
						for (int ch = 0; ( ch = reader.read()) != -1; )
						{
							if (tail.length() > 32000)
							{
								truncated = true;
								tail.delete(0, 4000);
							}
							tail.append((char) ch);
						}
						if (truncated)
						{
							body.append("... TRUNCATED TEXT ... ");
						}
						body.append(tail);
					} catch ( Exception e ) {
						log.warn("Error getting task log", e);
					}
				
				}
				getMailService().sendTextMailToActors(mailNotification.split("\\s*,\\s*"), //$NON-NLS-1$
						String.format(Messages.getString("ScheduledTaskServiceImpl.5"), task.getName()), //$NON-NLS-1$
						body.toString());
			}

		}
	}

	private void reconfigureTasks () throws InternalErrorException
	{
		// Updates master configuration property
		String timeStamp = Long.toString(System.currentTimeMillis());
		ConfigEntity c = getConfigEntityDao().findByTenantNameAndNetwork(Security.getMasterTenantName(), "soffid.schedule.timeStamp", null);
		if (c == null)
		{
			c = getConfigEntityDao().newConfigEntity();
			c.setName("soffid.schedule.timeStamp"); //$NON-NLS-1$
			c.setDescription("Task scheduler update time stamp"); //$NON-NLS-1$
			c.setValue(timeStamp);
			getConfigEntityDao().createMasterConfig(c);
			
		}
		else
		{
			c.setValue(timeStamp);
			getConfigEntityDao().update(c);
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
        RemoteServiceLocator rsl = createRemoteServiceLocator(task.getServerName());
        if (rsl == null)
        	throw new InternalErrorException("Not allowed to execute task on "+task.getServerName());
        SyncStatusService status = rsl.getSyncStatusService();
        status.startScheduledTask(task);
	}

    private RemoteServiceLocator createRemoteServiceLocator(String serverName) throws IOException, InternalErrorException {
        for (ServerEntity server:  getServerEntityDao().findByTenant(Security.getCurrentTenantName()))
        {
        	if ( (server.getType() == ServerType.MASTERSERVER && "*".equals(serverName) )
        			|| server.getName().equals(serverName))
        	{
                RemoteServiceLocator rsl = new RemoteServiceLocator( server.getUrl() );
            	rsl.setTenant(Security.getCurrentTenantName()+"\\"+Security.getCurrentAccount());
                rsl.setAuthToken(server.getAuth());
                return rsl;
        	}
        }
        return null;
    }

	@Override
	protected ScheduledTask handleLoad(Long taskId) throws Exception {
		ScheduledTaskEntity entity = getScheduledTaskEntityDao().load(taskId);
		if (entity == null) return null;
		else return getScheduledTaskEntityDao().toScheduledTask(entity);
	}
}
