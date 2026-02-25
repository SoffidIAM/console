//
// (C) 2013 Soffid
//
//

package com.soffid.iam.bpm.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.bpm.service.BpmEngine</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.bpm.service.BpmEngine
 */
public abstract class BpmEngineBase
	implements com.soffid.iam.bpm.service.BpmEngine
 {
	private com.soffid.iam.iga.service.ApplicationService applicationService;

	/**
	 * Sets reference to <code>applicationService</code>.
	 */
	public void setApplicationService (com.soffid.iam.iga.service.ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	/**
	 * Gets reference to <code>applicationService</code>.
	 */
	public com.soffid.iam.iga.service.ApplicationService getApplicationService () {
		return applicationService;
	}

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

	private com.soffid.iam.bpm.service.BpmConfigService bpmConfigService;

	/**
	 * Sets reference to <code>bpmConfigService</code>.
	 */
	public void setBpmConfigService (com.soffid.iam.bpm.service.BpmConfigService bpmConfigService) {
		this.bpmConfigService = bpmConfigService;
	}

	/**
	 * Gets reference to <code>bpmConfigService</code>.
	 */
	public com.soffid.iam.bpm.service.BpmConfigService getBpmConfigService () {
		return bpmConfigService;
	}

	private com.soffid.iam.impl.service.LuceneIndexService luceneIndexService;

	/**
	 * Sets reference to <code>luceneIndexService</code>.
	 */
	public void setLuceneIndexService (com.soffid.iam.impl.service.LuceneIndexService luceneIndexService) {
		this.luceneIndexService = luceneIndexService;
	}

	/**
	 * Gets reference to <code>luceneIndexService</code>.
	 */
	public com.soffid.iam.impl.service.LuceneIndexService getLuceneIndexService () {
		return luceneIndexService;
	}

	private com.soffid.iam.iga.model.ProcessHierarchyEntityDao processHierarchyEntityDao;

	/**
	 * Sets reference to <code>processHierarchyEntityDao</code>.
	 */
	public void setProcessHierarchyEntityDao (com.soffid.iam.iga.model.ProcessHierarchyEntityDao processHierarchyEntityDao) {
		this.processHierarchyEntityDao = processHierarchyEntityDao;
	}

	/**
	 * Gets reference to <code>processHierarchyEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.ProcessHierarchyEntityDao getProcessHierarchyEntityDao () {
		return processHierarchyEntityDao;
	}

	private com.soffid.iam.iga.model.SystemEntityDao systemEntityDao;

	/**
	 * Sets reference to <code>systemEntityDao</code>.
	 */
	public void setSystemEntityDao (com.soffid.iam.iga.model.SystemEntityDao systemEntityDao) {
		this.systemEntityDao = systemEntityDao;
	}

	/**
	 * Gets reference to <code>systemEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.SystemEntityDao getSystemEntityDao () {
		return systemEntityDao;
	}

	private com.soffid.iam.base.model.UserEntityDao userEntityDao;

	/**
	 * Sets reference to <code>userEntityDao</code>.
	 */
	public void setUserEntityDao (com.soffid.iam.base.model.UserEntityDao userEntityDao) {
		this.userEntityDao = userEntityDao;
	}

	/**
	 * Gets reference to <code>userEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserEntityDao getUserEntityDao () {
		return userEntityDao;
	}

	private com.soffid.iam.base.service.UserService userService;

	/**
	 * Sets reference to <code>userService</code>.
	 */
	public void setUserService (com.soffid.iam.base.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Gets reference to <code>userService</code>.
	 */
	public com.soffid.iam.base.service.UserService getUserService () {
		return userService;
	}


	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#void downloadParFile(com.soffid.iam.bpm.api.ProcessDefinition def, java.io.OutputStream stream)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void downloadParFile(
		final com.soffid.iam.bpm.api.ProcessDefinition def, 
		final java.io.OutputStream stream)
		throws com.soffid.iam.exception.InternalErrorException, java.lang.Exception
	{
		if (def == null) {
			throw new IllegalArgumentException("void com.soffid.iam.bpm.service.BpmEngine.downloadParFile(com.soffid.iam.bpm.api.ProcessDefinition def, java.io.OutputStream stream) - def cannot be null");
		}
		if (stream == null) {
			throw new IllegalArgumentException("void com.soffid.iam.bpm.service.BpmEngine.downloadParFile(com.soffid.iam.bpm.api.ProcessDefinition def, java.io.OutputStream stream) - stream cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDownloadParFile(def, stream);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof java.lang.Exception) 
			throw (java.lang.Exception) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.downloadParFile", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.downloadParFile", (Throwable) __r[1]);
	}

	protected abstract void handleDownloadParFile(com.soffid.iam.bpm.api.ProcessDefinition def, java.io.OutputStream stream) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#boolean canAdmin(com.soffid.iam.bpm.api.ProcessInstance instanceVO)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean canAdmin(
		final com.soffid.iam.bpm.api.ProcessInstance instanceVO)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (instanceVO == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.bpm.service.BpmEngine.canAdmin(com.soffid.iam.bpm.api.ProcessInstance instanceVO) - instanceVO cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCanAdmin(instanceVO)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.canAdmin", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.canAdmin", (Throwable) __r[1]);
	}

	protected abstract boolean handleCanAdmin(com.soffid.iam.bpm.api.ProcessInstance instanceVO) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#boolean isUserInRole(java.lang.String role)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class}, readOnly=true)
	public boolean isUserInRole(
		final java.lang.String role)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (role == null || role.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.bpm.service.BpmEngine.isUserInRole(java.lang.String role) - role cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsUserInRole(role)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.isUserInRole", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.isUserInRole", (Throwable) __r[1]);
	}

	protected abstract boolean handleIsUserInRole(java.lang.String role) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#byte[] getProcessDefinitionIcon(java.lang.Long definitionId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public byte[] getProcessDefinitionIcon(
		final java.lang.Long definitionId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (definitionId == null) {
			throw new IllegalArgumentException("byte[] com.soffid.iam.bpm.service.BpmEngine.getProcessDefinitionIcon(java.lang.Long definitionId) - definitionId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetProcessDefinitionIcon(definitionId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (byte[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getProcessDefinitionIcon", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getProcessDefinitionIcon", (Throwable) __r[1]);
	}

	protected abstract byte[] handleGetProcessDefinitionIcon(java.lang.Long definitionId) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#byte[] getProcessDefinitionImage(com.soffid.iam.bpm.api.ProcessDefinition def)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public byte[] getProcessDefinitionImage(
		final com.soffid.iam.bpm.api.ProcessDefinition def)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (def == null) {
			throw new IllegalArgumentException("byte[] com.soffid.iam.bpm.service.BpmEngine.getProcessDefinitionImage(com.soffid.iam.bpm.api.ProcessDefinition def) - def cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetProcessDefinitionImage(def)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (byte[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getProcessDefinitionImage", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getProcessDefinitionImage", (Throwable) __r[1]);
	}

	protected abstract byte[] handleGetProcessDefinitionImage(com.soffid.iam.bpm.api.ProcessDefinition def) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.DeployToken openDeployParDefinitionTransfer()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.bpm.api.DeployToken openDeployParDefinitionTransfer()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleOpenDeployParDefinitionTransfer()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.bpm.api.DeployToken) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.openDeployParDefinitionTransfer", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.openDeployParDefinitionTransfer", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.DeployToken handleOpenDeployParDefinitionTransfer() throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessDefinition disableProcessDefinition(com.soffid.iam.bpm.api.ProcessDefinition defVO)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.bpm.api.ProcessDefinition disableProcessDefinition(
		final com.soffid.iam.bpm.api.ProcessDefinition defVO)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (defVO == null) {
			throw new IllegalArgumentException("com.soffid.iam.bpm.api.ProcessDefinition com.soffid.iam.bpm.service.BpmEngine.disableProcessDefinition(com.soffid.iam.bpm.api.ProcessDefinition defVO) - defVO cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleDisableProcessDefinition(defVO)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.bpm.api.ProcessDefinition) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.disableProcessDefinition", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.disableProcessDefinition", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.ProcessDefinition handleDisableProcessDefinition(com.soffid.iam.bpm.api.ProcessDefinition defVO) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessDefinition enableProcessDefinition(com.soffid.iam.bpm.api.ProcessDefinition defVO)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.bpm.api.ProcessDefinition enableProcessDefinition(
		final com.soffid.iam.bpm.api.ProcessDefinition defVO)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (defVO == null) {
			throw new IllegalArgumentException("com.soffid.iam.bpm.api.ProcessDefinition com.soffid.iam.bpm.service.BpmEngine.enableProcessDefinition(com.soffid.iam.bpm.api.ProcessDefinition defVO) - defVO cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleEnableProcessDefinition(defVO)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.bpm.api.ProcessDefinition) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.enableProcessDefinition", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.enableProcessDefinition", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.ProcessDefinition handleEnableProcessDefinition(com.soffid.iam.bpm.api.ProcessDefinition defVO) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessDefinition getDefinition(com.soffid.iam.bpm.api.ProcessInstance process)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.bpm.api.ProcessDefinition getDefinition(
		final com.soffid.iam.bpm.api.ProcessInstance process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (process == null) {
			throw new IllegalArgumentException("com.soffid.iam.bpm.api.ProcessDefinition com.soffid.iam.bpm.service.BpmEngine.getDefinition(com.soffid.iam.bpm.api.ProcessInstance process) - process cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetDefinition(process)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.bpm.api.ProcessDefinition) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getDefinition", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getDefinition", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.ProcessDefinition handleGetDefinition(com.soffid.iam.bpm.api.ProcessInstance process) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessDefinition getProcessDefinition(com.soffid.iam.bpm.api.ProcessInstance process)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.bpm.api.ProcessDefinition getProcessDefinition(
		final com.soffid.iam.bpm.api.ProcessInstance process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (process == null) {
			throw new IllegalArgumentException("com.soffid.iam.bpm.api.ProcessDefinition com.soffid.iam.bpm.service.BpmEngine.getProcessDefinition(com.soffid.iam.bpm.api.ProcessInstance process) - process cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetProcessDefinition(process)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.bpm.api.ProcessDefinition) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getProcessDefinition", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getProcessDefinition", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.ProcessDefinition handleGetProcessDefinition(com.soffid.iam.bpm.api.ProcessInstance process) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessInstance cancel(com.soffid.iam.bpm.api.ProcessInstance process)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.bpm.api.ProcessInstance cancel(
		final com.soffid.iam.bpm.api.ProcessInstance process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (process == null) {
			throw new IllegalArgumentException("com.soffid.iam.bpm.api.ProcessInstance com.soffid.iam.bpm.service.BpmEngine.cancel(com.soffid.iam.bpm.api.ProcessInstance process) - process cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCancel(process)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.bpm.api.ProcessInstance) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.cancel", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.cancel", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.ProcessInstance handleCancel(com.soffid.iam.bpm.api.ProcessInstance process) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessInstance getProcess(long id)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class}, readOnly=true)
	public com.soffid.iam.bpm.api.ProcessInstance getProcess(
		final long id)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetProcess(id)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.bpm.api.ProcessInstance) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getProcess", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getProcess", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.ProcessInstance handleGetProcess(long id) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessInstance getProcessInstance(com.soffid.iam.bpm.api.TaskInstance task)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class}, readOnly=true)
	public com.soffid.iam.bpm.api.ProcessInstance getProcessInstance(
		final com.soffid.iam.bpm.api.TaskInstance task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (task == null) {
			throw new IllegalArgumentException("com.soffid.iam.bpm.api.ProcessInstance com.soffid.iam.bpm.service.BpmEngine.getProcessInstance(com.soffid.iam.bpm.api.TaskInstance task) - task cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetProcessInstance(task)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.bpm.api.ProcessInstance) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getProcessInstance", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getProcessInstance", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.ProcessInstance handleGetProcessInstance(com.soffid.iam.bpm.api.TaskInstance task) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessInstance getProcessLightweight(long id)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.bpm.api.ProcessInstance getProcessLightweight(
		final long id)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetProcessLightweight(id)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.bpm.api.ProcessInstance) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getProcessLightweight", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getProcessLightweight", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.ProcessInstance handleGetProcessLightweight(long id) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessInstance newProcess(com.soffid.iam.bpm.api.ProcessDefinition def)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.bpm.api.ProcessInstance newProcess(
		final com.soffid.iam.bpm.api.ProcessDefinition def)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (def == null) {
			throw new IllegalArgumentException("com.soffid.iam.bpm.api.ProcessInstance com.soffid.iam.bpm.service.BpmEngine.newProcess(com.soffid.iam.bpm.api.ProcessDefinition def) - def cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleNewProcess(def)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.bpm.api.ProcessInstance) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.newProcess", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.newProcess", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.ProcessInstance handleNewProcess(com.soffid.iam.bpm.api.ProcessDefinition def) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessInstance newProcess(com.soffid.iam.bpm.api.ProcessDefinition def, boolean start)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.bpm.api.ProcessInstance newProcess(
		final com.soffid.iam.bpm.api.ProcessDefinition def, 
		final boolean start)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (def == null) {
			throw new IllegalArgumentException("com.soffid.iam.bpm.api.ProcessInstance com.soffid.iam.bpm.service.BpmEngine.newProcess(com.soffid.iam.bpm.api.ProcessDefinition def, boolean start) - def cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleNewProcess(def, start)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.bpm.api.ProcessInstance) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.newProcess", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.newProcess", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.ProcessInstance handleNewProcess(com.soffid.iam.bpm.api.ProcessDefinition def, boolean start) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessLog[] getProcessLog(com.soffid.iam.bpm.api.ProcessInstance process)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.bpm.api.ProcessLog[] getProcessLog(
		final com.soffid.iam.bpm.api.ProcessInstance process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (process == null) {
			throw new IllegalArgumentException("com.soffid.iam.bpm.api.ProcessLog[] com.soffid.iam.bpm.service.BpmEngine.getProcessLog(com.soffid.iam.bpm.api.ProcessInstance process) - process cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetProcessLog(process)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.bpm.api.ProcessLog[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getProcessLog", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getProcessLog", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.ProcessLog[] handleGetProcessLog(com.soffid.iam.bpm.api.ProcessInstance process) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.ProcessLog[] getTaskLog(com.soffid.iam.bpm.api.TaskInstance process)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.bpm.api.ProcessLog[] getTaskLog(
		final com.soffid.iam.bpm.api.TaskInstance process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (process == null) {
			throw new IllegalArgumentException("com.soffid.iam.bpm.api.ProcessLog[] com.soffid.iam.bpm.service.BpmEngine.getTaskLog(com.soffid.iam.bpm.api.TaskInstance process) - process cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetTaskLog(process)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.bpm.api.ProcessLog[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getTaskLog", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getTaskLog", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.ProcessLog[] handleGetTaskLog(com.soffid.iam.bpm.api.TaskInstance process) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.TaskDefinition getDefinition(com.soffid.iam.bpm.api.TaskInstance task)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.bpm.api.TaskDefinition getDefinition(
		final com.soffid.iam.bpm.api.TaskInstance task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (task == null) {
			throw new IllegalArgumentException("com.soffid.iam.bpm.api.TaskDefinition com.soffid.iam.bpm.service.BpmEngine.getDefinition(com.soffid.iam.bpm.api.TaskInstance task) - task cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetDefinition(task)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.bpm.api.TaskDefinition) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getDefinition", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getDefinition", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.TaskDefinition handleGetDefinition(com.soffid.iam.bpm.api.TaskInstance task) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.TaskInstance addComment(com.soffid.iam.bpm.api.TaskInstance task, java.lang.String comment)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.bpm.api.TaskInstance addComment(
		final com.soffid.iam.bpm.api.TaskInstance task, 
		final java.lang.String comment)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (task == null) {
			throw new IllegalArgumentException("com.soffid.iam.bpm.api.TaskInstance com.soffid.iam.bpm.service.BpmEngine.addComment(com.soffid.iam.bpm.api.TaskInstance task, java.lang.String comment) - task cannot be null");
		}
		if (comment == null || comment.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.bpm.api.TaskInstance com.soffid.iam.bpm.service.BpmEngine.addComment(com.soffid.iam.bpm.api.TaskInstance task, java.lang.String comment) - comment cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleAddComment(task, comment)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.bpm.api.TaskInstance) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.addComment", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.addComment", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.TaskInstance handleAddComment(com.soffid.iam.bpm.api.TaskInstance task, java.lang.String comment) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.TaskInstance cancel(com.soffid.iam.bpm.api.TaskInstance task)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.bpm.api.TaskInstance cancel(
		final com.soffid.iam.bpm.api.TaskInstance task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (task == null) {
			throw new IllegalArgumentException("com.soffid.iam.bpm.api.TaskInstance com.soffid.iam.bpm.service.BpmEngine.cancel(com.soffid.iam.bpm.api.TaskInstance task) - task cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCancel(task)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.bpm.api.TaskInstance) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.cancel", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.cancel", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.TaskInstance handleCancel(com.soffid.iam.bpm.api.TaskInstance task) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.TaskInstance createDummyTask(long processDefinitionId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.bpm.api.TaskInstance createDummyTask(
		final long processDefinitionId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreateDummyTask(processDefinitionId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.bpm.api.TaskInstance) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.createDummyTask", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.createDummyTask", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.TaskInstance handleCreateDummyTask(long processDefinitionId) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.TaskInstance delegateTaskToUser(com.soffid.iam.bpm.api.TaskInstance task, java.lang.String username)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.bpm.api.TaskInstance delegateTaskToUser(
		final com.soffid.iam.bpm.api.TaskInstance task, 
		final java.lang.String username)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (task == null) {
			throw new IllegalArgumentException("com.soffid.iam.bpm.api.TaskInstance com.soffid.iam.bpm.service.BpmEngine.delegateTaskToUser(com.soffid.iam.bpm.api.TaskInstance task, java.lang.String username) - task cannot be null");
		}
		if (username == null || username.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.bpm.api.TaskInstance com.soffid.iam.bpm.service.BpmEngine.delegateTaskToUser(com.soffid.iam.bpm.api.TaskInstance task, java.lang.String username) - username cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleDelegateTaskToUser(task, username)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.bpm.api.TaskInstance) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.delegateTaskToUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.delegateTaskToUser", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.TaskInstance handleDelegateTaskToUser(com.soffid.iam.bpm.api.TaskInstance task, java.lang.String username) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.TaskInstance executeTask(com.soffid.iam.bpm.api.TaskInstance task, java.lang.String transitionName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.bpm.api.TaskInstance executeTask(
		final com.soffid.iam.bpm.api.TaskInstance task, 
		final java.lang.String transitionName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (task == null) {
			throw new IllegalArgumentException("com.soffid.iam.bpm.api.TaskInstance com.soffid.iam.bpm.service.BpmEngine.executeTask(com.soffid.iam.bpm.api.TaskInstance task, java.lang.String transitionName) - task cannot be null");
		}
		if (transitionName == null || transitionName.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.bpm.api.TaskInstance com.soffid.iam.bpm.service.BpmEngine.executeTask(com.soffid.iam.bpm.api.TaskInstance task, java.lang.String transitionName) - transitionName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleExecuteTask(task, transitionName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.bpm.api.TaskInstance) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.executeTask", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.executeTask", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.TaskInstance handleExecuteTask(com.soffid.iam.bpm.api.TaskInstance task, java.lang.String transitionName) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.TaskInstance getTask(long id)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class}, readOnly=true)
	public com.soffid.iam.bpm.api.TaskInstance getTask(
		final long id)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetTask(id)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.bpm.api.TaskInstance) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getTask", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getTask", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.TaskInstance handleGetTask(long id) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.TaskInstance reserveTask(com.soffid.iam.bpm.api.TaskInstance task)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.bpm.api.TaskInstance reserveTask(
		final com.soffid.iam.bpm.api.TaskInstance task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (task == null) {
			throw new IllegalArgumentException("com.soffid.iam.bpm.api.TaskInstance com.soffid.iam.bpm.service.BpmEngine.reserveTask(com.soffid.iam.bpm.api.TaskInstance task) - task cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleReserveTask(task)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.bpm.api.TaskInstance) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.reserveTask", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.reserveTask", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.TaskInstance handleReserveTask(com.soffid.iam.bpm.api.TaskInstance task) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.TaskInstance startTask(com.soffid.iam.bpm.api.TaskInstance task)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.bpm.api.TaskInstance startTask(
		final com.soffid.iam.bpm.api.TaskInstance task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (task == null) {
			throw new IllegalArgumentException("com.soffid.iam.bpm.api.TaskInstance com.soffid.iam.bpm.service.BpmEngine.startTask(com.soffid.iam.bpm.api.TaskInstance task) - task cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleStartTask(task)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.bpm.api.TaskInstance) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.startTask", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.startTask", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.TaskInstance handleStartTask(com.soffid.iam.bpm.api.TaskInstance task) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.TaskInstance update(com.soffid.iam.bpm.api.TaskInstance task)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.bpm.api.TaskInstance update(
		final com.soffid.iam.bpm.api.TaskInstance task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (task == null) {
			throw new IllegalArgumentException("com.soffid.iam.bpm.api.TaskInstance com.soffid.iam.bpm.service.BpmEngine.update(com.soffid.iam.bpm.api.TaskInstance task) - task cannot be null");
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
			return (com.soffid.iam.bpm.api.TaskInstance) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.TaskInstance handleUpdate(com.soffid.iam.bpm.api.TaskInstance task) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.iam.bpm.api.Token[] getTokens(long id)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.bpm.api.Token[] getTokens(
		final long id)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetTokens(id)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.bpm.api.Token[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getTokens", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getTokens", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.bpm.api.Token[] handleGetTokens(long id) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessDefinition> findProcessDefinitionByTextAndJsonQuery(java.lang.String text, java.lang.String jsonQuery, java.lang.Integer start, java.lang.Integer pageSize)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessDefinition> findProcessDefinitionByTextAndJsonQuery(
		final java.lang.String text, 
		final java.lang.String jsonQuery, 
		final java.lang.Integer start, 
		final java.lang.Integer pageSize)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindProcessDefinitionByTextAndJsonQuery(text, jsonQuery, start, pageSize)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessDefinition>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.findProcessDefinitionByTextAndJsonQuery", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.findProcessDefinitionByTextAndJsonQuery", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessDefinition> handleFindProcessDefinitionByTextAndJsonQuery(java.lang.String text, java.lang.String jsonQuery, java.lang.Integer start, java.lang.Integer pageSize) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstance(com.soffid.zkdb.api.Query query)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstance(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (query == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessInstance> com.soffid.iam.bpm.service.BpmEngine.findProcessInstance(com.soffid.zkdb.api.Query query) - query cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindProcessInstance(query)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessInstance>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.findProcessInstance", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.findProcessInstance", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessInstance> handleFindProcessInstance(com.soffid.zkdb.api.Query query) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstanceByTextAndJsonQuery(java.lang.String text, java.lang.String jsonQuery, java.lang.Integer start, java.lang.Integer pageSize)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstanceByTextAndJsonQuery(
		final java.lang.String text, 
		final java.lang.String jsonQuery, 
		final java.lang.Integer start, 
		final java.lang.Integer pageSize)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindProcessInstanceByTextAndJsonQuery(text, jsonQuery, start, pageSize)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessInstance>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.findProcessInstanceByTextAndJsonQuery", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.findProcessInstanceByTextAndJsonQuery", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.ProcessInstance> handleFindProcessInstanceByTextAndJsonQuery(java.lang.String text, java.lang.String jsonQuery, java.lang.Integer start, java.lang.Integer pageSize) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.TaskInstance> findTasksByTextAndJsonQuery(java.lang.String text, java.lang.String jsonQuery, java.lang.Integer start, java.lang.Integer pageSize)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.TaskInstance> findTasksByTextAndJsonQuery(
		final java.lang.String text, 
		final java.lang.String jsonQuery, 
		final java.lang.Integer start, 
		final java.lang.Integer pageSize)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindTasksByTextAndJsonQuery(text, jsonQuery, start, pageSize)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.TaskInstance>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.findTasksByTextAndJsonQuery", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.findTasksByTextAndJsonQuery", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.bpm.api.TaskInstance> handleFindTasksByTextAndJsonQuery(java.lang.String text, java.lang.String jsonQuery, java.lang.Integer start, java.lang.Integer pageSize) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#int countMyTasks()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public int countMyTasks()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCountMyTasks()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Integer) __r[0]).intValue();
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.countMyTasks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.countMyTasks", (Throwable) __r[1]);
	}

	protected abstract int handleCountMyTasks() throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#int countNewTasks()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public int countNewTasks()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCountNewTasks()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Integer) __r[0]).intValue();
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.countNewTasks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.countNewTasks", (Throwable) __r[1]);
	}

	protected abstract int handleCountNewTasks() throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#int[] getCoordinates(com.soffid.iam.bpm.api.ProcessInstance task)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public int[] getCoordinates(
		final com.soffid.iam.bpm.api.ProcessInstance task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (task == null) {
			throw new IllegalArgumentException("int[] com.soffid.iam.bpm.service.BpmEngine.getCoordinates(com.soffid.iam.bpm.api.ProcessInstance task) - task cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetCoordinates(task)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (int[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getCoordinates", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getCoordinates", (Throwable) __r[1]);
	}

	protected abstract int[] handleGetCoordinates(com.soffid.iam.bpm.api.ProcessInstance task) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#int[] getCoordinates(com.soffid.iam.bpm.api.TaskInstance task)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public int[] getCoordinates(
		final com.soffid.iam.bpm.api.TaskInstance task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (task == null) {
			throw new IllegalArgumentException("int[] com.soffid.iam.bpm.service.BpmEngine.getCoordinates(com.soffid.iam.bpm.api.TaskInstance task) - task cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetCoordinates(task)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (int[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getCoordinates", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getCoordinates", (Throwable) __r[1]);
	}

	protected abstract int[] handleGetCoordinates(com.soffid.iam.bpm.api.TaskInstance task) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.io.InputStream getResourceAsStream(com.soffid.iam.bpm.api.ProcessDefinition processdef, java.lang.String resource)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.io.InputStream getResourceAsStream(
		final com.soffid.iam.bpm.api.ProcessDefinition processdef, 
		final java.lang.String resource)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (processdef == null) {
			throw new IllegalArgumentException("java.io.InputStream com.soffid.iam.bpm.service.BpmEngine.getResourceAsStream(com.soffid.iam.bpm.api.ProcessDefinition processdef, java.lang.String resource) - processdef cannot be null");
		}
		if (resource == null || resource.trim().length() == 0) {
			throw new IllegalArgumentException("java.io.InputStream com.soffid.iam.bpm.service.BpmEngine.getResourceAsStream(com.soffid.iam.bpm.api.ProcessDefinition processdef, java.lang.String resource) - resource cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetResourceAsStream(processdef, resource)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.io.InputStream) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getResourceAsStream", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getResourceAsStream", (Throwable) __r[1]);
	}

	protected abstract java.io.InputStream handleGetResourceAsStream(com.soffid.iam.bpm.api.ProcessDefinition processdef, java.lang.String resource) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.io.InputStream getResourceAsStream(com.soffid.iam.bpm.api.ProcessInstance process, java.lang.String resource)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.io.InputStream getResourceAsStream(
		final com.soffid.iam.bpm.api.ProcessInstance process, 
		final java.lang.String resource)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (process == null) {
			throw new IllegalArgumentException("java.io.InputStream com.soffid.iam.bpm.service.BpmEngine.getResourceAsStream(com.soffid.iam.bpm.api.ProcessInstance process, java.lang.String resource) - process cannot be null");
		}
		if (resource == null || resource.trim().length() == 0) {
			throw new IllegalArgumentException("java.io.InputStream com.soffid.iam.bpm.service.BpmEngine.getResourceAsStream(com.soffid.iam.bpm.api.ProcessInstance process, java.lang.String resource) - resource cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetResourceAsStream(process, resource)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.io.InputStream) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getResourceAsStream", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getResourceAsStream", (Throwable) __r[1]);
	}

	protected abstract java.io.InputStream handleGetResourceAsStream(com.soffid.iam.bpm.api.ProcessInstance process, java.lang.String resource) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.lang.String getUI(com.soffid.iam.bpm.api.ProcessInstance process)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String getUI(
		final com.soffid.iam.bpm.api.ProcessInstance process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (process == null) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.bpm.service.BpmEngine.getUI(com.soffid.iam.bpm.api.ProcessInstance process) - process cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUI(process)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getUI", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getUI", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGetUI(com.soffid.iam.bpm.api.ProcessInstance process) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.lang.String getUI(com.soffid.iam.bpm.api.TaskInstance task)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String getUI(
		final com.soffid.iam.bpm.api.TaskInstance task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (task == null) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.bpm.service.BpmEngine.getUI(com.soffid.iam.bpm.api.TaskInstance task) - task cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUI(task)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getUI", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getUI", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGetUI(com.soffid.iam.bpm.api.TaskInstance task) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.lang.String[] getDeployMessages(com.soffid.iam.bpm.api.DeployToken token)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String[] getDeployMessages(
		final com.soffid.iam.bpm.api.DeployToken token)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (token == null) {
			throw new IllegalArgumentException("java.lang.String[] com.soffid.iam.bpm.service.BpmEngine.getDeployMessages(com.soffid.iam.bpm.api.DeployToken token) - token cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetDeployMessages(token)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getDeployMessages", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getDeployMessages", (Throwable) __r[1]);
	}

	protected abstract java.lang.String[] handleGetDeployMessages(com.soffid.iam.bpm.api.DeployToken token) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.lang.String[] upgradeProcess(com.soffid.iam.bpm.api.ProcessInstance instanceVO)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String[] upgradeProcess(
		final com.soffid.iam.bpm.api.ProcessInstance instanceVO)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (instanceVO == null) {
			throw new IllegalArgumentException("java.lang.String[] com.soffid.iam.bpm.service.BpmEngine.upgradeProcess(com.soffid.iam.bpm.api.ProcessInstance instanceVO) - instanceVO cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpgradeProcess(instanceVO)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.upgradeProcess", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.upgradeProcess", (Throwable) __r[1]);
	}

	protected abstract java.lang.String[] handleUpgradeProcess(com.soffid.iam.bpm.api.ProcessInstance instanceVO) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.Collection<java.lang.Long> findChildProcesses(java.lang.Long processId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class}, readOnly=true)
	public java.util.Collection<java.lang.Long> findChildProcesses(
		final java.lang.Long processId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (processId == null) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.Long> com.soffid.iam.bpm.service.BpmEngine.findChildProcesses(java.lang.Long processId) - processId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindChildProcesses(processId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<java.lang.Long>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.findChildProcesses", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.findChildProcesses", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<java.lang.Long> handleFindChildProcesses(java.lang.Long processId) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.Collection<java.lang.Long> findParentProceeses(java.lang.Long processId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class}, readOnly=true)
	public java.util.Collection<java.lang.Long> findParentProceeses(
		final java.lang.Long processId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (processId == null) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.Long> com.soffid.iam.bpm.service.BpmEngine.findParentProceeses(java.lang.Long processId) - processId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindParentProceeses(processId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<java.lang.Long>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.findParentProceeses", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.findParentProceeses", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<java.lang.Long> handleFindParentProceeses(java.lang.Long processId) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.Collection<com.soffid.iam.bpm.api.BPMUser> findUsers(java.lang.String userName, java.lang.String givenName, java.lang.String surName, java.lang.String group)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.bpm.api.BPMUser> findUsers(
		final java.lang.String userName, 
		final java.lang.String givenName, 
		final java.lang.String surName, 
		final java.lang.String group)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUsers(userName, givenName, surName, group)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.bpm.api.BPMUser>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.findUsers", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.findUsers", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.bpm.api.BPMUser> handleFindUsers(java.lang.String userName, java.lang.String givenName, java.lang.String surName, java.lang.String group) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findAllProcessDefinitions(boolean onlyEnabled)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findAllProcessDefinitions(
		final boolean onlyEnabled)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAllProcessDefinitions(onlyEnabled)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.bpm.api.ProcessDefinition>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.findAllProcessDefinitions", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.findAllProcessDefinitions", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> handleFindAllProcessDefinitions(boolean onlyEnabled) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.TaskInstance> findGroupTasks()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.bpm.api.TaskInstance> findGroupTasks()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindGroupTasks()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.bpm.api.TaskInstance>) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.findGroupTasks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.findGroupTasks", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.bpm.api.TaskInstance> handleFindGroupTasks() throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findInitiatorProcessDefinitions()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findInitiatorProcessDefinitions()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindInitiatorProcessDefinitions()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.bpm.api.ProcessDefinition>) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.findInitiatorProcessDefinitions", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.findInitiatorProcessDefinitions", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> handleFindInitiatorProcessDefinitions() throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.ProcessInstance> findMyProcesses()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.bpm.api.ProcessInstance> findMyProcesses()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindMyProcesses()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.bpm.api.ProcessInstance>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.findMyProcesses", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.findMyProcesses", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.bpm.api.ProcessInstance> handleFindMyProcesses() throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.TaskInstance> findMyTasks()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.bpm.api.TaskInstance> findMyTasks()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindMyTasks()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.bpm.api.TaskInstance>) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.findMyTasks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.findMyTasks", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.bpm.api.TaskInstance> handleFindMyTasks() throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.TaskInstance> findMyTasksLightweight()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.bpm.api.TaskInstance> findMyTasksLightweight()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindMyTasksLightweight()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.bpm.api.TaskInstance>) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.findMyTasksLightweight", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.findMyTasksLightweight", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.bpm.api.TaskInstance> handleFindMyTasksLightweight() throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findObserverProcessDefinitions()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findObserverProcessDefinitions()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindObserverProcessDefinitions()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.bpm.api.ProcessDefinition>) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.findObserverProcessDefinitions", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.findObserverProcessDefinitions", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> handleFindObserverProcessDefinitions() throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findProcessDefinitions(java.lang.String name, boolean onlyEnabled)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findProcessDefinitions(
		final java.lang.String name, 
		final boolean onlyEnabled)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindProcessDefinitions(name, onlyEnabled)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.bpm.api.ProcessDefinition>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.findProcessDefinitions", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.findProcessDefinitions", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> handleFindProcessDefinitions(java.lang.String name, boolean onlyEnabled) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findProcessDefinitions(java.lang.String name, com.soffid.iam.bpm.api.PredefinedProcessType processType)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findProcessDefinitions(
		final java.lang.String name, 
		final com.soffid.iam.bpm.api.PredefinedProcessType processType)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (processType == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> com.soffid.iam.bpm.service.BpmEngine.findProcessDefinitions(java.lang.String name, com.soffid.iam.bpm.api.PredefinedProcessType processType) - processType cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindProcessDefinitions(name, processType)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.bpm.api.ProcessDefinition>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.findProcessDefinitions", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.findProcessDefinitions", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> handleFindProcessDefinitions(java.lang.String name, com.soffid.iam.bpm.api.PredefinedProcessType processType) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstances(com.soffid.iam.bpm.api.ProcessDefinition def)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstances(
		final com.soffid.iam.bpm.api.ProcessDefinition def)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (def == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.bpm.api.ProcessInstance> com.soffid.iam.bpm.service.BpmEngine.findProcessInstances(com.soffid.iam.bpm.api.ProcessDefinition def) - def cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindProcessInstances(def)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.bpm.api.ProcessInstance>) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.findProcessInstances", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.findProcessInstances", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.bpm.api.ProcessInstance> handleFindProcessInstances(com.soffid.iam.bpm.api.ProcessDefinition def) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstances(java.util.List definitions, java.lang.String processId, java.lang.String estado, java.lang.String actor, java.util.Date startDate, boolean finalizada)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.bpm.api.ProcessInstance> findProcessInstances(
		final java.util.List definitions, 
		final java.lang.String processId, 
		final java.lang.String estado, 
		final java.lang.String actor, 
		final java.util.Date startDate, 
		final boolean finalizada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (definitions == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.bpm.api.ProcessInstance> com.soffid.iam.bpm.service.BpmEngine.findProcessInstances(java.util.List definitions, java.lang.String processId, java.lang.String estado, java.lang.String actor, java.util.Date startDate, boolean finalizada) - definitions cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindProcessInstances(definitions, processId, estado, actor, startDate, finalizada)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.bpm.api.ProcessInstance>) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.findProcessInstances", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.findProcessInstances", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.bpm.api.ProcessInstance> handleFindProcessInstances(java.util.List definitions, java.lang.String processId, java.lang.String estado, java.lang.String actor, java.util.Date startDate, boolean finalizada) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findSupervisorProcessDefinitions()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> findSupervisorProcessDefinitions()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindSupervisorProcessDefinitions()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.bpm.api.ProcessDefinition>) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.findSupervisorProcessDefinitions", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.findSupervisorProcessDefinitions", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.bpm.api.ProcessDefinition> handleFindSupervisorProcessDefinitions() throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.TaskDefinition> findTaskDefinitions(com.soffid.iam.bpm.api.ProcessDefinition def)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.bpm.api.TaskDefinition> findTaskDefinitions(
		final com.soffid.iam.bpm.api.ProcessDefinition def)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (def == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.bpm.api.TaskDefinition> com.soffid.iam.bpm.service.BpmEngine.findTaskDefinitions(com.soffid.iam.bpm.api.ProcessDefinition def) - def cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindTaskDefinitions(def)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.bpm.api.TaskDefinition>) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.findTaskDefinitions", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.findTaskDefinitions", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.bpm.api.TaskDefinition> handleFindTaskDefinitions(com.soffid.iam.bpm.api.ProcessDefinition def) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.TaskInstance> findTasks(java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, com.soffid.iam.bpm.api.TaskDefinition task, java.lang.String actor, java.util.Date processStartDate, java.util.Date taskCreationDate, boolean finalizada)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.bpm.api.TaskInstance> findTasks(
		final java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, 
		final com.soffid.iam.bpm.api.TaskDefinition task, 
		final java.lang.String actor, 
		final java.util.Date processStartDate, 
		final java.util.Date taskCreationDate, 
		final boolean finalizada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (def == null ) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.bpm.api.TaskInstance> com.soffid.iam.bpm.service.BpmEngine.findTasks(java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, com.soffid.iam.bpm.api.TaskDefinition task, java.lang.String actor, java.util.Date processStartDate, java.util.Date taskCreationDate, boolean finalizada) - def cannot be empty");
		}
		if (task == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.bpm.api.TaskInstance> com.soffid.iam.bpm.service.BpmEngine.findTasks(java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, com.soffid.iam.bpm.api.TaskDefinition task, java.lang.String actor, java.util.Date processStartDate, java.util.Date taskCreationDate, boolean finalizada) - task cannot be null");
		}
		if (actor == null || actor.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.bpm.api.TaskInstance> com.soffid.iam.bpm.service.BpmEngine.findTasks(java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, com.soffid.iam.bpm.api.TaskDefinition task, java.lang.String actor, java.util.Date processStartDate, java.util.Date taskCreationDate, boolean finalizada) - actor cannot be null");
		}
		if (processStartDate == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.bpm.api.TaskInstance> com.soffid.iam.bpm.service.BpmEngine.findTasks(java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, com.soffid.iam.bpm.api.TaskDefinition task, java.lang.String actor, java.util.Date processStartDate, java.util.Date taskCreationDate, boolean finalizada) - processStartDate cannot be null");
		}
		if (taskCreationDate == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.bpm.api.TaskInstance> com.soffid.iam.bpm.service.BpmEngine.findTasks(java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, com.soffid.iam.bpm.api.TaskDefinition task, java.lang.String actor, java.util.Date processStartDate, java.util.Date taskCreationDate, boolean finalizada) - taskCreationDate cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindTasks(def, task, actor, processStartDate, taskCreationDate, finalizada)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.bpm.api.TaskInstance>) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.findTasks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.findTasks", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.bpm.api.TaskInstance> handleFindTasks(java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, com.soffid.iam.bpm.api.TaskDefinition task, java.lang.String actor, java.util.Date processStartDate, java.util.Date taskCreationDate, boolean finalizada) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.TaskInstance> findTasks(java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, java.lang.String process, com.soffid.iam.bpm.api.TaskDefinition task, java.lang.String actor, java.util.Date processStartDate, java.util.Date taskCreationDate, boolean finalizada)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.bpm.api.TaskInstance> findTasks(
		final java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, 
		final java.lang.String process, 
		final com.soffid.iam.bpm.api.TaskDefinition task, 
		final java.lang.String actor, 
		final java.util.Date processStartDate, 
		final java.util.Date taskCreationDate, 
		final boolean finalizada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (def == null ) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.bpm.api.TaskInstance> com.soffid.iam.bpm.service.BpmEngine.findTasks(java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, java.lang.String process, com.soffid.iam.bpm.api.TaskDefinition task, java.lang.String actor, java.util.Date processStartDate, java.util.Date taskCreationDate, boolean finalizada) - def cannot be empty");
		}
		if (process == null || process.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.bpm.api.TaskInstance> com.soffid.iam.bpm.service.BpmEngine.findTasks(java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, java.lang.String process, com.soffid.iam.bpm.api.TaskDefinition task, java.lang.String actor, java.util.Date processStartDate, java.util.Date taskCreationDate, boolean finalizada) - process cannot be null");
		}
		if (task == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.bpm.api.TaskInstance> com.soffid.iam.bpm.service.BpmEngine.findTasks(java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, java.lang.String process, com.soffid.iam.bpm.api.TaskDefinition task, java.lang.String actor, java.util.Date processStartDate, java.util.Date taskCreationDate, boolean finalizada) - task cannot be null");
		}
		if (actor == null || actor.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.bpm.api.TaskInstance> com.soffid.iam.bpm.service.BpmEngine.findTasks(java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, java.lang.String process, com.soffid.iam.bpm.api.TaskDefinition task, java.lang.String actor, java.util.Date processStartDate, java.util.Date taskCreationDate, boolean finalizada) - actor cannot be null");
		}
		if (processStartDate == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.bpm.api.TaskInstance> com.soffid.iam.bpm.service.BpmEngine.findTasks(java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, java.lang.String process, com.soffid.iam.bpm.api.TaskDefinition task, java.lang.String actor, java.util.Date processStartDate, java.util.Date taskCreationDate, boolean finalizada) - processStartDate cannot be null");
		}
		if (taskCreationDate == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.bpm.api.TaskInstance> com.soffid.iam.bpm.service.BpmEngine.findTasks(java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, java.lang.String process, com.soffid.iam.bpm.api.TaskDefinition task, java.lang.String actor, java.util.Date processStartDate, java.util.Date taskCreationDate, boolean finalizada) - taskCreationDate cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindTasks(def, process, task, actor, processStartDate, taskCreationDate, finalizada)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.bpm.api.TaskInstance>) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.findTasks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.findTasks", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.bpm.api.TaskInstance> handleFindTasks(java.util.List<com.soffid.iam.bpm.api.ProcessInstance> def, java.lang.String process, com.soffid.iam.bpm.api.TaskDefinition task, java.lang.String actor, java.util.Date processStartDate, java.util.Date taskCreationDate, boolean finalizada) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.Job> getActiveJobs()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.bpm.api.Job> getActiveJobs()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetActiveJobs()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.bpm.api.Job>) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getActiveJobs", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getActiveJobs", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.bpm.api.Job> handleGetActiveJobs() throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.Job> getActiveJobs(com.soffid.iam.bpm.api.ProcessInstance process)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.bpm.api.Job> getActiveJobs(
		final com.soffid.iam.bpm.api.ProcessInstance process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (process == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.bpm.api.Job> com.soffid.iam.bpm.service.BpmEngine.getActiveJobs(com.soffid.iam.bpm.api.ProcessInstance process) - process cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetActiveJobs(process)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.bpm.api.Job>) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getActiveJobs", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getActiveJobs", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.bpm.api.Job> handleGetActiveJobs(com.soffid.iam.bpm.api.ProcessInstance process) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.TaskInstance> getActiveTasks(com.soffid.iam.bpm.api.ProcessInstance process)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.bpm.api.TaskInstance> getActiveTasks(
		final com.soffid.iam.bpm.api.ProcessInstance process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (process == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.bpm.api.TaskInstance> com.soffid.iam.bpm.service.BpmEngine.getActiveTasks(com.soffid.iam.bpm.api.ProcessInstance process) - process cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetActiveTasks(process)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.bpm.api.TaskInstance>) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getActiveTasks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getActiveTasks", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.bpm.api.TaskInstance> handleGetActiveTasks(com.soffid.iam.bpm.api.ProcessInstance process) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.Job> getAllJobs()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.bpm.api.Job> getAllJobs()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetAllJobs()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.bpm.api.Job>) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getAllJobs", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getAllJobs", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.bpm.api.Job> handleGetAllJobs() throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.TaskInstance> getPendingTasks(com.soffid.iam.bpm.api.ProcessInstance process)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.bpm.api.TaskInstance> getPendingTasks(
		final com.soffid.iam.bpm.api.ProcessInstance process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (process == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.bpm.api.TaskInstance> com.soffid.iam.bpm.service.BpmEngine.getPendingTasks(com.soffid.iam.bpm.api.ProcessInstance process) - process cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetPendingTasks(process)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.bpm.api.TaskInstance>) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getPendingTasks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getPendingTasks", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.bpm.api.TaskInstance> handleGetPendingTasks(com.soffid.iam.bpm.api.ProcessInstance process) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.List<com.soffid.iam.bpm.api.ProcessInstance> searchProcessInstances(java.lang.String query, java.lang.String processID, java.util.Date sinceStartDate, java.util.Date untilStartDate, java.util.Date sinceEndDate, java.util.Date untilEndDate, boolean finished)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.bpm.api.ProcessInstance> searchProcessInstances(
		final java.lang.String query, 
		final java.lang.String processID, 
		final java.util.Date sinceStartDate, 
		final java.util.Date untilStartDate, 
		final java.util.Date sinceEndDate, 
		final java.util.Date untilEndDate, 
		final boolean finished)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleSearchProcessInstances(query, processID, sinceStartDate, untilStartDate, sinceEndDate, untilEndDate, finished)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.bpm.api.ProcessInstance>) __r[0];
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.searchProcessInstances", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.searchProcessInstances", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.bpm.api.ProcessInstance> handleSearchProcessInstances(java.lang.String query, java.lang.String processID, java.util.Date sinceStartDate, java.util.Date untilStartDate, java.util.Date sinceEndDate, java.util.Date untilEndDate, boolean finished) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#java.util.Map getUIClassesForTask(com.soffid.iam.bpm.api.ProcessDefinition def)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Map getUIClassesForTask(
		final com.soffid.iam.bpm.api.ProcessDefinition def)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.sql.SQLException, java.io.IOException
	{
		if (def == null) {
			throw new IllegalArgumentException("java.util.Map com.soffid.iam.bpm.service.BpmEngine.getUIClassesForTask(com.soffid.iam.bpm.api.ProcessDefinition def) - def cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUIClassesForTask(def)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Map) __r[0];
		if (__r[1] instanceof java.sql.SQLException) 
			throw (java.sql.SQLException) __r[1];
		if (__r[1] instanceof java.io.IOException) 
			throw (java.io.IOException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getUIClassesForTask", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getUIClassesForTask", (Throwable) __r[1]);
	}

	protected abstract java.util.Map handleGetUIClassesForTask(com.soffid.iam.bpm.api.ProcessDefinition def) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#org.jbpm.JbpmConfiguration getJBpmConfiguration()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public org.jbpm.JbpmConfiguration getJBpmConfiguration()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetJBpmConfiguration()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (org.jbpm.JbpmConfiguration) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getJBpmConfiguration", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getJBpmConfiguration", (Throwable) __r[1]);
	}

	protected abstract org.jbpm.JbpmConfiguration handleGetJBpmConfiguration() throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#org.jbpm.JbpmContext getContext()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public org.jbpm.JbpmContext getContext()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetContext()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (org.jbpm.JbpmContext) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.getContext", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.getContext", (Throwable) __r[1]);
	}

	protected abstract org.jbpm.JbpmContext handleGetContext() throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#void addComment(com.soffid.iam.bpm.api.ProcessInstance process, java.lang.String comment)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void addComment(
		final com.soffid.iam.bpm.api.ProcessInstance process, 
		final java.lang.String comment)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (process == null) {
			throw new IllegalArgumentException("void com.soffid.iam.bpm.service.BpmEngine.addComment(com.soffid.iam.bpm.api.ProcessInstance process, java.lang.String comment) - process cannot be null");
		}
		if (comment == null || comment.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.bpm.service.BpmEngine.addComment(com.soffid.iam.bpm.api.ProcessInstance process, java.lang.String comment) - comment cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleAddComment(process, comment);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.addComment", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.addComment", (Throwable) __r[1]);
	}

	protected abstract void handleAddComment(com.soffid.iam.bpm.api.ProcessInstance process, java.lang.String comment) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#void endDeployParDefinitionTransfer(com.soffid.iam.bpm.api.DeployToken token)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void endDeployParDefinitionTransfer(
		final com.soffid.iam.bpm.api.DeployToken token)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (token == null) {
			throw new IllegalArgumentException("void com.soffid.iam.bpm.service.BpmEngine.endDeployParDefinitionTransfer(com.soffid.iam.bpm.api.DeployToken token) - token cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleEndDeployParDefinitionTransfer(token);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.endDeployParDefinitionTransfer", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.endDeployParDefinitionTransfer", (Throwable) __r[1]);
	}

	protected abstract void handleEndDeployParDefinitionTransfer(com.soffid.iam.bpm.api.DeployToken token) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#void linkProcesses(java.lang.Long parentProcess, java.lang.Long childProcess)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void linkProcesses(
		final java.lang.Long parentProcess, 
		final java.lang.Long childProcess)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (parentProcess == null) {
			throw new IllegalArgumentException("void com.soffid.iam.bpm.service.BpmEngine.linkProcesses(java.lang.Long parentProcess, java.lang.Long childProcess) - parentProcess cannot be null");
		}
		if (childProcess == null) {
			throw new IllegalArgumentException("void com.soffid.iam.bpm.service.BpmEngine.linkProcesses(java.lang.Long parentProcess, java.lang.Long childProcess) - childProcess cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleLinkProcesses(parentProcess, childProcess);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.linkProcesses", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.linkProcesses", (Throwable) __r[1]);
	}

	protected abstract void handleLinkProcesses(java.lang.Long parentProcess, java.lang.Long childProcess) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#void nextDeployParDefinitionPackage(com.soffid.iam.bpm.api.DeployToken token, byte[] filePackage, int length)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void nextDeployParDefinitionPackage(
		final com.soffid.iam.bpm.api.DeployToken token, 
		final byte[] filePackage, 
		final int length)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (token == null) {
			throw new IllegalArgumentException("void com.soffid.iam.bpm.service.BpmEngine.nextDeployParDefinitionPackage(com.soffid.iam.bpm.api.DeployToken token, byte[] filePackage, int length) - token cannot be null");
		}
		if (filePackage == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.bpm.service.BpmEngine.nextDeployParDefinitionPackage(com.soffid.iam.bpm.api.DeployToken token, byte[] filePackage, int length) - filePackage cannot be empty");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleNextDeployParDefinitionPackage(token, filePackage, length);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.nextDeployParDefinitionPackage", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.nextDeployParDefinitionPackage", (Throwable) __r[1]);
	}

	protected abstract void handleNextDeployParDefinitionPackage(com.soffid.iam.bpm.api.DeployToken token, byte[] filePackage, int length) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#void pauseJob(com.soffid.iam.bpm.api.Job jobvo)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void pauseJob(
		final com.soffid.iam.bpm.api.Job jobvo)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (jobvo == null) {
			throw new IllegalArgumentException("void com.soffid.iam.bpm.service.BpmEngine.pauseJob(com.soffid.iam.bpm.api.Job jobvo) - jobvo cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handlePauseJob(jobvo);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.pauseJob", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.pauseJob", (Throwable) __r[1]);
	}

	protected abstract void handlePauseJob(com.soffid.iam.bpm.api.Job jobvo) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#void ping()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void ping()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handlePing();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.ping", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.ping", (Throwable) __r[1]);
	}

	protected abstract void handlePing() throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#void reindex()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void reindex()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.io.IOException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleReindex();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof java.io.IOException) 
			throw (java.io.IOException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.reindex", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.reindex", (Throwable) __r[1]);
	}

	protected abstract void handleReindex() throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#void resumeJob(com.soffid.iam.bpm.api.Job jobvo)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void resumeJob(
		final com.soffid.iam.bpm.api.Job jobvo)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (jobvo == null) {
			throw new IllegalArgumentException("void com.soffid.iam.bpm.service.BpmEngine.resumeJob(com.soffid.iam.bpm.api.Job jobvo) - jobvo cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleResumeJob(jobvo);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.resumeJob", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.resumeJob", (Throwable) __r[1]);
	}

	protected abstract void handleResumeJob(com.soffid.iam.bpm.api.Job jobvo) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#void retryJob(com.soffid.iam.bpm.api.Job jobvo)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void retryJob(
		final com.soffid.iam.bpm.api.Job jobvo)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (jobvo == null) {
			throw new IllegalArgumentException("void com.soffid.iam.bpm.service.BpmEngine.retryJob(com.soffid.iam.bpm.api.Job jobvo) - jobvo cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRetryJob(jobvo);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.retryJob", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.retryJob", (Throwable) __r[1]);
	}

	protected abstract void handleRetryJob(com.soffid.iam.bpm.api.Job jobvo) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#void signal(com.soffid.iam.bpm.api.ProcessInstance instanceVO)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void signal(
		final com.soffid.iam.bpm.api.ProcessInstance instanceVO)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (instanceVO == null) {
			throw new IllegalArgumentException("void com.soffid.iam.bpm.service.BpmEngine.signal(com.soffid.iam.bpm.api.ProcessInstance instanceVO) - instanceVO cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSignal(instanceVO);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.signal", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.signal", (Throwable) __r[1]);
	}

	protected abstract void handleSignal(com.soffid.iam.bpm.api.ProcessInstance instanceVO) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#void signal(com.soffid.iam.bpm.api.ProcessInstance instanceVO, java.lang.String transitionName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void signal(
		final com.soffid.iam.bpm.api.ProcessInstance instanceVO, 
		final java.lang.String transitionName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (instanceVO == null) {
			throw new IllegalArgumentException("void com.soffid.iam.bpm.service.BpmEngine.signal(com.soffid.iam.bpm.api.ProcessInstance instanceVO, java.lang.String transitionName) - instanceVO cannot be null");
		}
		if (transitionName == null || transitionName.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.bpm.service.BpmEngine.signal(com.soffid.iam.bpm.api.ProcessInstance instanceVO, java.lang.String transitionName) - transitionName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSignal(instanceVO, transitionName);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.signal", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.signal", (Throwable) __r[1]);
	}

	protected abstract void handleSignal(com.soffid.iam.bpm.api.ProcessInstance instanceVO, java.lang.String transitionName) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#void startProcess(com.soffid.iam.bpm.api.ProcessInstance process)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void startProcess(
		final com.soffid.iam.bpm.api.ProcessInstance process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (process == null) {
			throw new IllegalArgumentException("void com.soffid.iam.bpm.service.BpmEngine.startProcess(com.soffid.iam.bpm.api.ProcessInstance process) - process cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleStartProcess(process);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.startProcess", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.startProcess", (Throwable) __r[1]);
	}

	protected abstract void handleStartProcess(com.soffid.iam.bpm.api.ProcessInstance process) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#void update(com.soffid.iam.bpm.api.ProcessInstance process)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void update(
		final com.soffid.iam.bpm.api.ProcessInstance process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (process == null) {
			throw new IllegalArgumentException("void com.soffid.iam.bpm.service.BpmEngine.update(com.soffid.iam.bpm.api.ProcessInstance process) - process cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpdate(process);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.update", (Throwable) __r[1]);
	}

	protected abstract void handleUpdate(com.soffid.iam.bpm.api.ProcessInstance process) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#void updateSwimlane(com.soffid.iam.bpm.api.TaskInstance task, java.lang.String swimlane, java.lang.String[] actorIds)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void updateSwimlane(
		final com.soffid.iam.bpm.api.TaskInstance task, 
		final java.lang.String swimlane, 
		final java.lang.String[] actorIds)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.bpm.exception.BPMException
	{
		if (task == null) {
			throw new IllegalArgumentException("void com.soffid.iam.bpm.service.BpmEngine.updateSwimlane(com.soffid.iam.bpm.api.TaskInstance task, java.lang.String swimlane, java.lang.String[] actorIds) - task cannot be null");
		}
		if (swimlane == null || swimlane.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.bpm.service.BpmEngine.updateSwimlane(com.soffid.iam.bpm.api.TaskInstance task, java.lang.String swimlane, java.lang.String[] actorIds) - swimlane cannot be null");
		}
		if (actorIds == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.bpm.service.BpmEngine.updateSwimlane(com.soffid.iam.bpm.api.TaskInstance task, java.lang.String swimlane, java.lang.String[] actorIds) - actorIds cannot be empty");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpdateSwimlane(task, swimlane, actorIds);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.bpm.exception.BPMException) 
			throw (com.soffid.iam.bpm.exception.BPMException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.updateSwimlane", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.updateSwimlane", (Throwable) __r[1]);
	}

	protected abstract void handleUpdateSwimlane(com.soffid.iam.bpm.api.TaskInstance task, java.lang.String swimlane, java.lang.String[] actorIds) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmEngine#	 * @see com.soffid.iam.bpm.service.BpmEngine#void upgradeParFile(java.io.InputStream stream)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void upgradeParFile(
		final java.io.InputStream stream)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (stream == null) {
			throw new IllegalArgumentException("void com.soffid.iam.bpm.service.BpmEngine.upgradeParFile(java.io.InputStream stream) - stream cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpgradeParFile(stream);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmEngine.class).
			warn ("Error on BpmEngine.upgradeParFile", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmEngine.upgradeParFile", (Throwable) __r[1]);
	}

	protected abstract void handleUpgradeParFile(java.io.InputStream stream) throws Exception;

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
