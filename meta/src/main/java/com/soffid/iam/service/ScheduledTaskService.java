//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.service;
import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.doc.service.DocumentService;
import com.soffid.iam.model.ScheduledTaskLogEntity;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.ConfiguracioEntity;
import es.caib.seycon.ng.model.ServerEntity;

import org.springframework.transaction.annotation.Transactional;

@Service ( translatedName="ScheduledTaskService",
	 translatedPackage="com.soffid.iam.service")
@Depends ({com.soffid.iam.model.ScheduledTaskHandlerEntity.class, ScheduledTaskLogEntity.class,
	com.soffid.iam.model.ScheduledTaskEntity.class,
	es.caib.seycon.ng.model.TasqueEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.servei.ConfiguracioService.class,
	MailService.class,
	ConfiguracioEntity.class,
	DocumentService.class,
	ServerEntity.class})
public abstract class ScheduledTaskService {

	@Description("Finds a scheduled task by handler and params")
	public ScheduledTask findScheduledTaskByHandlerAndParams (String handler, String params)
	{
		return null;
	}
	
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.api.ScheduledTaskHandler create(
		com.soffid.iam.api.ScheduledTaskHandler handler)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.api.ScheduledTaskHandler update(
		com.soffid.iam.api.ScheduledTaskHandler handler)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void remove(
		com.soffid.iam.api.ScheduledTaskHandler handler)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.api.ScheduledTaskHandler> listHandlers()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={com.soffid.iam.roles.schedule_admin.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.api.ScheduledTask create(
		com.soffid.iam.api.ScheduledTask task)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={com.soffid.iam.roles.schedule_admin.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.api.ScheduledTask update(
		com.soffid.iam.api.ScheduledTask task)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={com.soffid.iam.roles.schedule_admin.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void remove(
		com.soffid.iam.api.ScheduledTask task)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Operation ( grantees={com.soffid.iam.roles.schedule_query.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.api.ScheduledTask> listTasks()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	@Operation ( grantees={com.soffid.iam.roles.schedule_query.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.api.ScheduledTask load(Long taskId) {
		return null;
	}

	@Operation ( grantees={com.soffid.iam.roles.schedule_query.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.api.ScheduledTask> listServerTasks(String server)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Transactional(rollbackFor={java.lang.Exception.class})
	public void registerStartTask(
		com.soffid.iam.api.ScheduledTask task)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void registerEndTask(
		com.soffid.iam.api.ScheduledTask task)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	
	@Operation ( grantees={com.soffid.iam.roles.schedule_query.class})
	public void startNow(ScheduledTask task) {}
}
