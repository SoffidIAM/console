//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.sync.service.ScheduledTaskService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.sync.service.ScheduledTaskService
 */
public abstract class ScheduledTaskServiceBase
	implements com.soffid.iam.sync.service.ScheduledTaskService
 {
	private com.soffid.iam.rc.model.AuditEntityDao auditEntityDao;

	/**
	 * Sets reference to <code>auditEntityDao</code>.
	 */
	public void setAuditEntityDao (com.soffid.iam.rc.model.AuditEntityDao auditEntityDao) {
		this.auditEntityDao = auditEntityDao;
	}

	/**
	 * Gets reference to <code>auditEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.AuditEntityDao getAuditEntityDao () {
		return auditEntityDao;
	}

	private com.soffid.iam.base.model.ConfigEntityDao configEntityDao;

	/**
	 * Sets reference to <code>configEntityDao</code>.
	 */
	public void setConfigEntityDao (com.soffid.iam.base.model.ConfigEntityDao configEntityDao) {
		this.configEntityDao = configEntityDao;
	}

	/**
	 * Gets reference to <code>configEntityDao</code>.
	 */
	public com.soffid.iam.base.model.ConfigEntityDao getConfigEntityDao () {
		return configEntityDao;
	}

	private com.soffid.iam.base.service.ConfigurationService configurationService;

	/**
	 * Sets reference to <code>configurationService</code>.
	 */
	public void setConfigurationService (com.soffid.iam.base.service.ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	/**
	 * Gets reference to <code>configurationService</code>.
	 */
	public com.soffid.iam.base.service.ConfigurationService getConfigurationService () {
		return configurationService;
	}

	private com.soffid.iam.doc.service.DocumentService documentService;

	/**
	 * Sets reference to <code>documentService</code>.
	 */
	public void setDocumentService (com.soffid.iam.doc.service.DocumentService documentService) {
		this.documentService = documentService;
	}

	/**
	 * Gets reference to <code>documentService</code>.
	 */
	public com.soffid.iam.doc.service.DocumentService getDocumentService () {
		return documentService;
	}

	private com.soffid.iam.iga.service.MailService mailService;

	/**
	 * Sets reference to <code>mailService</code>.
	 */
	public void setMailService (com.soffid.iam.iga.service.MailService mailService) {
		this.mailService = mailService;
	}

	/**
	 * Gets reference to <code>mailService</code>.
	 */
	public com.soffid.iam.iga.service.MailService getMailService () {
		return mailService;
	}

	private com.soffid.iam.sync.model.ScheduledTaskEntityDao scheduledTaskEntityDao;

	/**
	 * Sets reference to <code>scheduledTaskEntityDao</code>.
	 */
	public void setScheduledTaskEntityDao (com.soffid.iam.sync.model.ScheduledTaskEntityDao scheduledTaskEntityDao) {
		this.scheduledTaskEntityDao = scheduledTaskEntityDao;
	}

	/**
	 * Gets reference to <code>scheduledTaskEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.ScheduledTaskEntityDao getScheduledTaskEntityDao () {
		return scheduledTaskEntityDao;
	}

	private com.soffid.iam.sync.model.ScheduledTaskHandlerEntityDao scheduledTaskHandlerEntityDao;

	/**
	 * Sets reference to <code>scheduledTaskHandlerEntityDao</code>.
	 */
	public void setScheduledTaskHandlerEntityDao (com.soffid.iam.sync.model.ScheduledTaskHandlerEntityDao scheduledTaskHandlerEntityDao) {
		this.scheduledTaskHandlerEntityDao = scheduledTaskHandlerEntityDao;
	}

	/**
	 * Gets reference to <code>scheduledTaskHandlerEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.ScheduledTaskHandlerEntityDao getScheduledTaskHandlerEntityDao () {
		return scheduledTaskHandlerEntityDao;
	}

	private com.soffid.iam.sync.model.ScheduledTaskLogEntityDao scheduledTaskLogEntityDao;

	/**
	 * Sets reference to <code>scheduledTaskLogEntityDao</code>.
	 */
	public void setScheduledTaskLogEntityDao (com.soffid.iam.sync.model.ScheduledTaskLogEntityDao scheduledTaskLogEntityDao) {
		this.scheduledTaskLogEntityDao = scheduledTaskLogEntityDao;
	}

	/**
	 * Gets reference to <code>scheduledTaskLogEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.ScheduledTaskLogEntityDao getScheduledTaskLogEntityDao () {
		return scheduledTaskLogEntityDao;
	}

	private com.soffid.iam.sync.model.ServerEntityDao serverEntityDao;

	/**
	 * Sets reference to <code>serverEntityDao</code>.
	 */
	public void setServerEntityDao (com.soffid.iam.sync.model.ServerEntityDao serverEntityDao) {
		this.serverEntityDao = serverEntityDao;
	}

	/**
	 * Gets reference to <code>serverEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.ServerEntityDao getServerEntityDao () {
		return serverEntityDao;
	}

	private com.soffid.iam.sync.model.TaskEntityDao taskEntityDao;

	/**
	 * Sets reference to <code>taskEntityDao</code>.
	 */
	public void setTaskEntityDao (com.soffid.iam.sync.model.TaskEntityDao taskEntityDao) {
		this.taskEntityDao = taskEntityDao;
	}

	/**
	 * Gets reference to <code>taskEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.TaskEntityDao getTaskEntityDao () {
		return taskEntityDao;
	}


	/**
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#	 * @see com.soffid.iam.sync.service.ScheduledTaskService#boolean isStopping(com.soffid.iam.sync.api.ScheduledTask task)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean isStopping(
		final com.soffid.iam.sync.api.ScheduledTask task)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (task == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.sync.service.ScheduledTaskService.isStopping(com.soffid.iam.sync.api.ScheduledTask task) - task cannot be null");
		}
		if (task.getName() == null || task.getName().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.sync.service.ScheduledTaskService.isStopping(com.soffid.iam.sync.api.ScheduledTask task) - task.name cannot be null");
		}
		if (task.getHandlerName() == null || task.getHandlerName().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.sync.service.ScheduledTaskService.isStopping(com.soffid.iam.sync.api.ScheduledTask task) - task.handlerName cannot be null");
		}
		if (task.getDayPattern() == null || task.getDayPattern().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.sync.service.ScheduledTaskService.isStopping(com.soffid.iam.sync.api.ScheduledTask task) - task.dayPattern cannot be null");
		}
		if (task.getHoursPattern() == null || task.getHoursPattern().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.sync.service.ScheduledTaskService.isStopping(com.soffid.iam.sync.api.ScheduledTask task) - task.hoursPattern cannot be null");
		}
		if (task.getMonthsPattern() == null || task.getMonthsPattern().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.sync.service.ScheduledTaskService.isStopping(com.soffid.iam.sync.api.ScheduledTask task) - task.monthsPattern cannot be null");
		}
		if (task.getDayOfWeekPattern() == null || task.getDayOfWeekPattern().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.sync.service.ScheduledTaskService.isStopping(com.soffid.iam.sync.api.ScheduledTask task) - task.dayOfWeekPattern cannot be null");
		}
		if (task.getMinutesPattern() == null || task.getMinutesPattern().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.sync.service.ScheduledTaskService.isStopping(com.soffid.iam.sync.api.ScheduledTask task) - task.minutesPattern cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsStopping(task)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.ScheduledTaskService.class).
			warn ("Error on ScheduledTaskService.isStopping", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ScheduledTaskService.isStopping", (Throwable) __r[1]);
	}

	protected abstract boolean handleIsStopping(com.soffid.iam.sync.api.ScheduledTask task) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#	 * @see com.soffid.iam.sync.service.ScheduledTaskService#com.soffid.iam.sync.api.ScheduledTask create(com.soffid.iam.sync.api.ScheduledTask task)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.sync.api.ScheduledTask create(
		final com.soffid.iam.sync.api.ScheduledTask task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (task == null) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTask com.soffid.iam.sync.service.ScheduledTaskService.create(com.soffid.iam.sync.api.ScheduledTask task) - task cannot be null");
		}
		if (task.getName() == null || task.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTask com.soffid.iam.sync.service.ScheduledTaskService.create(com.soffid.iam.sync.api.ScheduledTask task) - task.name cannot be null");
		}
		if (task.getHandlerName() == null || task.getHandlerName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTask com.soffid.iam.sync.service.ScheduledTaskService.create(com.soffid.iam.sync.api.ScheduledTask task) - task.handlerName cannot be null");
		}
		if (task.getDayPattern() == null || task.getDayPattern().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTask com.soffid.iam.sync.service.ScheduledTaskService.create(com.soffid.iam.sync.api.ScheduledTask task) - task.dayPattern cannot be null");
		}
		if (task.getHoursPattern() == null || task.getHoursPattern().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTask com.soffid.iam.sync.service.ScheduledTaskService.create(com.soffid.iam.sync.api.ScheduledTask task) - task.hoursPattern cannot be null");
		}
		if (task.getMonthsPattern() == null || task.getMonthsPattern().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTask com.soffid.iam.sync.service.ScheduledTaskService.create(com.soffid.iam.sync.api.ScheduledTask task) - task.monthsPattern cannot be null");
		}
		if (task.getDayOfWeekPattern() == null || task.getDayOfWeekPattern().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTask com.soffid.iam.sync.service.ScheduledTaskService.create(com.soffid.iam.sync.api.ScheduledTask task) - task.dayOfWeekPattern cannot be null");
		}
		if (task.getMinutesPattern() == null || task.getMinutesPattern().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTask com.soffid.iam.sync.service.ScheduledTaskService.create(com.soffid.iam.sync.api.ScheduledTask task) - task.minutesPattern cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(task)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.sync.api.ScheduledTask) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.ScheduledTaskService.class).
			warn ("Error on ScheduledTaskService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ScheduledTaskService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.sync.api.ScheduledTask handleCreate(com.soffid.iam.sync.api.ScheduledTask task) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#	 * @see com.soffid.iam.sync.service.ScheduledTaskService#com.soffid.iam.sync.api.ScheduledTask findById(java.lang.Long id)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.sync.api.ScheduledTask findById(
		final java.lang.Long id)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (id == null) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTask com.soffid.iam.sync.service.ScheduledTaskService.findById(java.lang.Long id) - id cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindById(id)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.sync.api.ScheduledTask) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.ScheduledTaskService.class).
			warn ("Error on ScheduledTaskService.findById", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ScheduledTaskService.findById", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.sync.api.ScheduledTask handleFindById(java.lang.Long id) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#	 * @see com.soffid.iam.sync.service.ScheduledTaskService#com.soffid.iam.sync.api.ScheduledTask findScheduledTaskByHandlerAndParams(java.lang.String handler, java.lang.String params)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.sync.api.ScheduledTask findScheduledTaskByHandlerAndParams(
		final java.lang.String handler, 
		final java.lang.String params)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (handler == null || handler.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTask com.soffid.iam.sync.service.ScheduledTaskService.findScheduledTaskByHandlerAndParams(java.lang.String handler, java.lang.String params) - handler cannot be null");
		}
		if (params == null || params.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTask com.soffid.iam.sync.service.ScheduledTaskService.findScheduledTaskByHandlerAndParams(java.lang.String handler, java.lang.String params) - params cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindScheduledTaskByHandlerAndParams(handler, params)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.sync.api.ScheduledTask) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.ScheduledTaskService.class).
			warn ("Error on ScheduledTaskService.findScheduledTaskByHandlerAndParams", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ScheduledTaskService.findScheduledTaskByHandlerAndParams", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.sync.api.ScheduledTask handleFindScheduledTaskByHandlerAndParams(java.lang.String handler, java.lang.String params) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#	 * @see com.soffid.iam.sync.service.ScheduledTaskService#com.soffid.iam.sync.api.ScheduledTask load(java.lang.Long taskId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.sync.api.ScheduledTask load(
		final java.lang.Long taskId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (taskId == null) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTask com.soffid.iam.sync.service.ScheduledTaskService.load(java.lang.Long taskId) - taskId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleLoad(taskId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.sync.api.ScheduledTask) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.ScheduledTaskService.class).
			warn ("Error on ScheduledTaskService.load", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ScheduledTaskService.load", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.sync.api.ScheduledTask handleLoad(java.lang.Long taskId) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#	 * @see com.soffid.iam.sync.service.ScheduledTaskService#com.soffid.iam.sync.api.ScheduledTask update(com.soffid.iam.sync.api.ScheduledTask task)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.sync.api.ScheduledTask update(
		final com.soffid.iam.sync.api.ScheduledTask task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (task == null) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTask com.soffid.iam.sync.service.ScheduledTaskService.update(com.soffid.iam.sync.api.ScheduledTask task) - task cannot be null");
		}
		if (task.getName() == null || task.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTask com.soffid.iam.sync.service.ScheduledTaskService.update(com.soffid.iam.sync.api.ScheduledTask task) - task.name cannot be null");
		}
		if (task.getHandlerName() == null || task.getHandlerName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTask com.soffid.iam.sync.service.ScheduledTaskService.update(com.soffid.iam.sync.api.ScheduledTask task) - task.handlerName cannot be null");
		}
		if (task.getDayPattern() == null || task.getDayPattern().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTask com.soffid.iam.sync.service.ScheduledTaskService.update(com.soffid.iam.sync.api.ScheduledTask task) - task.dayPattern cannot be null");
		}
		if (task.getHoursPattern() == null || task.getHoursPattern().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTask com.soffid.iam.sync.service.ScheduledTaskService.update(com.soffid.iam.sync.api.ScheduledTask task) - task.hoursPattern cannot be null");
		}
		if (task.getMonthsPattern() == null || task.getMonthsPattern().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTask com.soffid.iam.sync.service.ScheduledTaskService.update(com.soffid.iam.sync.api.ScheduledTask task) - task.monthsPattern cannot be null");
		}
		if (task.getDayOfWeekPattern() == null || task.getDayOfWeekPattern().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTask com.soffid.iam.sync.service.ScheduledTaskService.update(com.soffid.iam.sync.api.ScheduledTask task) - task.dayOfWeekPattern cannot be null");
		}
		if (task.getMinutesPattern() == null || task.getMinutesPattern().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTask com.soffid.iam.sync.service.ScheduledTaskService.update(com.soffid.iam.sync.api.ScheduledTask task) - task.minutesPattern cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(task)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.sync.api.ScheduledTask) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.ScheduledTaskService.class).
			warn ("Error on ScheduledTaskService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ScheduledTaskService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.sync.api.ScheduledTask handleUpdate(com.soffid.iam.sync.api.ScheduledTask task) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#	 * @see com.soffid.iam.sync.service.ScheduledTaskService#com.soffid.iam.sync.api.ScheduledTaskHandler create(com.soffid.iam.sync.api.ScheduledTaskHandler handler)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.sync.api.ScheduledTaskHandler create(
		final com.soffid.iam.sync.api.ScheduledTaskHandler handler)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (handler == null) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTaskHandler com.soffid.iam.sync.service.ScheduledTaskService.create(com.soffid.iam.sync.api.ScheduledTaskHandler handler) - handler cannot be null");
		}
		if (handler.getName() == null || handler.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTaskHandler com.soffid.iam.sync.service.ScheduledTaskService.create(com.soffid.iam.sync.api.ScheduledTaskHandler handler) - handler.name cannot be null");
		}
		if (handler.getClassName() == null || handler.getClassName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTaskHandler com.soffid.iam.sync.service.ScheduledTaskService.create(com.soffid.iam.sync.api.ScheduledTaskHandler handler) - handler.className cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(handler)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.sync.api.ScheduledTaskHandler) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.ScheduledTaskService.class).
			warn ("Error on ScheduledTaskService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ScheduledTaskService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.sync.api.ScheduledTaskHandler handleCreate(com.soffid.iam.sync.api.ScheduledTaskHandler handler) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#	 * @see com.soffid.iam.sync.service.ScheduledTaskService#com.soffid.iam.sync.api.ScheduledTaskHandler update(com.soffid.iam.sync.api.ScheduledTaskHandler handler)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.sync.api.ScheduledTaskHandler update(
		final com.soffid.iam.sync.api.ScheduledTaskHandler handler)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (handler == null) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTaskHandler com.soffid.iam.sync.service.ScheduledTaskService.update(com.soffid.iam.sync.api.ScheduledTaskHandler handler) - handler cannot be null");
		}
		if (handler.getName() == null || handler.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTaskHandler com.soffid.iam.sync.service.ScheduledTaskService.update(com.soffid.iam.sync.api.ScheduledTaskHandler handler) - handler.name cannot be null");
		}
		if (handler.getClassName() == null || handler.getClassName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ScheduledTaskHandler com.soffid.iam.sync.service.ScheduledTaskService.update(com.soffid.iam.sync.api.ScheduledTaskHandler handler) - handler.className cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(handler)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.sync.api.ScheduledTaskHandler) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.ScheduledTaskService.class).
			warn ("Error on ScheduledTaskService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ScheduledTaskService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.sync.api.ScheduledTaskHandler handleUpdate(com.soffid.iam.sync.api.ScheduledTaskHandler handler) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#	 * @see com.soffid.iam.sync.service.ScheduledTaskService#java.util.List<com.soffid.iam.sync.api.ScheduledTask> listEnabledTasks()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.sync.api.ScheduledTask> listEnabledTasks()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleListEnabledTasks()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.sync.api.ScheduledTask>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.ScheduledTaskService.class).
			warn ("Error on ScheduledTaskService.listEnabledTasks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ScheduledTaskService.listEnabledTasks", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.sync.api.ScheduledTask> handleListEnabledTasks() throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#	 * @see com.soffid.iam.sync.service.ScheduledTaskService#java.util.List<com.soffid.iam.sync.api.ScheduledTaskHandler> listHandlers()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.sync.api.ScheduledTaskHandler> listHandlers()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleListHandlers()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.sync.api.ScheduledTaskHandler>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.ScheduledTaskService.class).
			warn ("Error on ScheduledTaskService.listHandlers", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ScheduledTaskService.listHandlers", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.sync.api.ScheduledTaskHandler> handleListHandlers() throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#	 * @see com.soffid.iam.sync.service.ScheduledTaskService#java.util.List<com.soffid.iam.sync.api.ScheduledTask> listServerTasks(java.lang.String server)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.sync.api.ScheduledTask> listServerTasks(
		final java.lang.String server)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (server == null || server.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.sync.api.ScheduledTask> com.soffid.iam.sync.service.ScheduledTaskService.listServerTasks(java.lang.String server) - server cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleListServerTasks(server)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.sync.api.ScheduledTask>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.ScheduledTaskService.class).
			warn ("Error on ScheduledTaskService.listServerTasks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ScheduledTaskService.listServerTasks", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.sync.api.ScheduledTask> handleListServerTasks(java.lang.String server) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#	 * @see com.soffid.iam.sync.service.ScheduledTaskService#java.util.List<com.soffid.iam.sync.api.ScheduledTask> listTasks()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.sync.api.ScheduledTask> listTasks()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleListTasks()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.sync.api.ScheduledTask>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.ScheduledTaskService.class).
			warn ("Error on ScheduledTaskService.listTasks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ScheduledTaskService.listTasks", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.sync.api.ScheduledTask> handleListTasks() throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#	 * @see com.soffid.iam.sync.service.ScheduledTaskService#void registerEndTask(com.soffid.iam.sync.api.ScheduledTask task)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void registerEndTask(
		final com.soffid.iam.sync.api.ScheduledTask task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (task == null) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.registerEndTask(com.soffid.iam.sync.api.ScheduledTask task) - task cannot be null");
		}
		if (task.getName() == null || task.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.registerEndTask(com.soffid.iam.sync.api.ScheduledTask task) - task.name cannot be null");
		}
		if (task.getHandlerName() == null || task.getHandlerName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.registerEndTask(com.soffid.iam.sync.api.ScheduledTask task) - task.handlerName cannot be null");
		}
		if (task.getDayPattern() == null || task.getDayPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.registerEndTask(com.soffid.iam.sync.api.ScheduledTask task) - task.dayPattern cannot be null");
		}
		if (task.getHoursPattern() == null || task.getHoursPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.registerEndTask(com.soffid.iam.sync.api.ScheduledTask task) - task.hoursPattern cannot be null");
		}
		if (task.getMonthsPattern() == null || task.getMonthsPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.registerEndTask(com.soffid.iam.sync.api.ScheduledTask task) - task.monthsPattern cannot be null");
		}
		if (task.getDayOfWeekPattern() == null || task.getDayOfWeekPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.registerEndTask(com.soffid.iam.sync.api.ScheduledTask task) - task.dayOfWeekPattern cannot be null");
		}
		if (task.getMinutesPattern() == null || task.getMinutesPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.registerEndTask(com.soffid.iam.sync.api.ScheduledTask task) - task.minutesPattern cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRegisterEndTask(task);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.ScheduledTaskService.class).
			warn ("Error on ScheduledTaskService.registerEndTask", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ScheduledTaskService.registerEndTask", (Throwable) __r[1]);
	}

	protected abstract void handleRegisterEndTask(com.soffid.iam.sync.api.ScheduledTask task) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#	 * @see com.soffid.iam.sync.service.ScheduledTaskService#void registerStartTask(com.soffid.iam.sync.api.ScheduledTask task)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void registerStartTask(
		final com.soffid.iam.sync.api.ScheduledTask task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (task == null) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.registerStartTask(com.soffid.iam.sync.api.ScheduledTask task) - task cannot be null");
		}
		if (task.getName() == null || task.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.registerStartTask(com.soffid.iam.sync.api.ScheduledTask task) - task.name cannot be null");
		}
		if (task.getHandlerName() == null || task.getHandlerName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.registerStartTask(com.soffid.iam.sync.api.ScheduledTask task) - task.handlerName cannot be null");
		}
		if (task.getDayPattern() == null || task.getDayPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.registerStartTask(com.soffid.iam.sync.api.ScheduledTask task) - task.dayPattern cannot be null");
		}
		if (task.getHoursPattern() == null || task.getHoursPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.registerStartTask(com.soffid.iam.sync.api.ScheduledTask task) - task.hoursPattern cannot be null");
		}
		if (task.getMonthsPattern() == null || task.getMonthsPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.registerStartTask(com.soffid.iam.sync.api.ScheduledTask task) - task.monthsPattern cannot be null");
		}
		if (task.getDayOfWeekPattern() == null || task.getDayOfWeekPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.registerStartTask(com.soffid.iam.sync.api.ScheduledTask task) - task.dayOfWeekPattern cannot be null");
		}
		if (task.getMinutesPattern() == null || task.getMinutesPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.registerStartTask(com.soffid.iam.sync.api.ScheduledTask task) - task.minutesPattern cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRegisterStartTask(task);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.ScheduledTaskService.class).
			warn ("Error on ScheduledTaskService.registerStartTask", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ScheduledTaskService.registerStartTask", (Throwable) __r[1]);
	}

	protected abstract void handleRegisterStartTask(com.soffid.iam.sync.api.ScheduledTask task) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#	 * @see com.soffid.iam.sync.service.ScheduledTaskService#void remove(com.soffid.iam.sync.api.ScheduledTask task)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void remove(
		final com.soffid.iam.sync.api.ScheduledTask task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (task == null) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.remove(com.soffid.iam.sync.api.ScheduledTask task) - task cannot be null");
		}
		if (task.getName() == null || task.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.remove(com.soffid.iam.sync.api.ScheduledTask task) - task.name cannot be null");
		}
		if (task.getHandlerName() == null || task.getHandlerName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.remove(com.soffid.iam.sync.api.ScheduledTask task) - task.handlerName cannot be null");
		}
		if (task.getDayPattern() == null || task.getDayPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.remove(com.soffid.iam.sync.api.ScheduledTask task) - task.dayPattern cannot be null");
		}
		if (task.getHoursPattern() == null || task.getHoursPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.remove(com.soffid.iam.sync.api.ScheduledTask task) - task.hoursPattern cannot be null");
		}
		if (task.getMonthsPattern() == null || task.getMonthsPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.remove(com.soffid.iam.sync.api.ScheduledTask task) - task.monthsPattern cannot be null");
		}
		if (task.getDayOfWeekPattern() == null || task.getDayOfWeekPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.remove(com.soffid.iam.sync.api.ScheduledTask task) - task.dayOfWeekPattern cannot be null");
		}
		if (task.getMinutesPattern() == null || task.getMinutesPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.remove(com.soffid.iam.sync.api.ScheduledTask task) - task.minutesPattern cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRemove(task);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.ScheduledTaskService.class).
			warn ("Error on ScheduledTaskService.remove", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ScheduledTaskService.remove", (Throwable) __r[1]);
	}

	protected abstract void handleRemove(com.soffid.iam.sync.api.ScheduledTask task) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#	 * @see com.soffid.iam.sync.service.ScheduledTaskService#void remove(com.soffid.iam.sync.api.ScheduledTaskHandler handler)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void remove(
		final com.soffid.iam.sync.api.ScheduledTaskHandler handler)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (handler == null) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.remove(com.soffid.iam.sync.api.ScheduledTaskHandler handler) - handler cannot be null");
		}
		if (handler.getName() == null || handler.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.remove(com.soffid.iam.sync.api.ScheduledTaskHandler handler) - handler.name cannot be null");
		}
		if (handler.getClassName() == null || handler.getClassName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.remove(com.soffid.iam.sync.api.ScheduledTaskHandler handler) - handler.className cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRemove(handler);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.ScheduledTaskService.class).
			warn ("Error on ScheduledTaskService.remove", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ScheduledTaskService.remove", (Throwable) __r[1]);
	}

	protected abstract void handleRemove(com.soffid.iam.sync.api.ScheduledTaskHandler handler) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#	 * @see com.soffid.iam.sync.service.ScheduledTaskService#void startNow(com.soffid.iam.sync.api.ScheduledTask task)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void startNow(
		final com.soffid.iam.sync.api.ScheduledTask task)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (task == null) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.startNow(com.soffid.iam.sync.api.ScheduledTask task) - task cannot be null");
		}
		if (task.getName() == null || task.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.startNow(com.soffid.iam.sync.api.ScheduledTask task) - task.name cannot be null");
		}
		if (task.getHandlerName() == null || task.getHandlerName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.startNow(com.soffid.iam.sync.api.ScheduledTask task) - task.handlerName cannot be null");
		}
		if (task.getDayPattern() == null || task.getDayPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.startNow(com.soffid.iam.sync.api.ScheduledTask task) - task.dayPattern cannot be null");
		}
		if (task.getHoursPattern() == null || task.getHoursPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.startNow(com.soffid.iam.sync.api.ScheduledTask task) - task.hoursPattern cannot be null");
		}
		if (task.getMonthsPattern() == null || task.getMonthsPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.startNow(com.soffid.iam.sync.api.ScheduledTask task) - task.monthsPattern cannot be null");
		}
		if (task.getDayOfWeekPattern() == null || task.getDayOfWeekPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.startNow(com.soffid.iam.sync.api.ScheduledTask task) - task.dayOfWeekPattern cannot be null");
		}
		if (task.getMinutesPattern() == null || task.getMinutesPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.startNow(com.soffid.iam.sync.api.ScheduledTask task) - task.minutesPattern cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleStartNow(task);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.ScheduledTaskService.class).
			warn ("Error on ScheduledTaskService.startNow", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ScheduledTaskService.startNow", (Throwable) __r[1]);
	}

	protected abstract void handleStartNow(com.soffid.iam.sync.api.ScheduledTask task) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.ScheduledTaskService#	 * @see com.soffid.iam.sync.service.ScheduledTaskService#void stop(com.soffid.iam.sync.api.ScheduledTask task)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void stop(
		final com.soffid.iam.sync.api.ScheduledTask task)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (task == null) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.stop(com.soffid.iam.sync.api.ScheduledTask task) - task cannot be null");
		}
		if (task.getName() == null || task.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.stop(com.soffid.iam.sync.api.ScheduledTask task) - task.name cannot be null");
		}
		if (task.getHandlerName() == null || task.getHandlerName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.stop(com.soffid.iam.sync.api.ScheduledTask task) - task.handlerName cannot be null");
		}
		if (task.getDayPattern() == null || task.getDayPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.stop(com.soffid.iam.sync.api.ScheduledTask task) - task.dayPattern cannot be null");
		}
		if (task.getHoursPattern() == null || task.getHoursPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.stop(com.soffid.iam.sync.api.ScheduledTask task) - task.hoursPattern cannot be null");
		}
		if (task.getMonthsPattern() == null || task.getMonthsPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.stop(com.soffid.iam.sync.api.ScheduledTask task) - task.monthsPattern cannot be null");
		}
		if (task.getDayOfWeekPattern() == null || task.getDayOfWeekPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.stop(com.soffid.iam.sync.api.ScheduledTask task) - task.dayOfWeekPattern cannot be null");
		}
		if (task.getMinutesPattern() == null || task.getMinutesPattern().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.ScheduledTaskService.stop(com.soffid.iam.sync.api.ScheduledTask task) - task.minutesPattern cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleStop(task);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.ScheduledTaskService.class).
			warn ("Error on ScheduledTaskService.stop", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ScheduledTaskService.stop", (Throwable) __r[1]);
	}

	protected abstract void handleStop(com.soffid.iam.sync.api.ScheduledTask task) throws Exception;

	/**
	 * Gets the current <code>principal</code> if one has been set,
	 * otherwise returns <code>null</code>.
	 *
	 * @return the current principal
	 */
	protected java.security.Principal getPrincipal()
	{
		return com.soffid.iam.PrincipalStore.get();
	}

}
