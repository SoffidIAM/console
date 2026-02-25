//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.base.service.ServerPluginService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.base.service.ServerPluginService
 */
public abstract class ServerPluginServiceBase
	implements com.soffid.iam.base.service.ServerPluginService
 {
	private com.soffid.iam.base.model.AgentDescriptorEntityDao agentDescriptorEntityDao;

	/**
	 * Sets reference to <code>agentDescriptorEntityDao</code>.
	 */
	public void setAgentDescriptorEntityDao (com.soffid.iam.base.model.AgentDescriptorEntityDao agentDescriptorEntityDao) {
		this.agentDescriptorEntityDao = agentDescriptorEntityDao;
	}

	/**
	 * Gets reference to <code>agentDescriptorEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AgentDescriptorEntityDao getAgentDescriptorEntityDao () {
		return agentDescriptorEntityDao;
	}

	private com.soffid.iam.base.model.AgentPropertyEntityDao agentPropertyEntityDao;

	/**
	 * Sets reference to <code>agentPropertyEntityDao</code>.
	 */
	public void setAgentPropertyEntityDao (com.soffid.iam.base.model.AgentPropertyEntityDao agentPropertyEntityDao) {
		this.agentPropertyEntityDao = agentPropertyEntityDao;
	}

	/**
	 * Gets reference to <code>agentPropertyEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AgentPropertyEntityDao getAgentPropertyEntityDao () {
		return agentPropertyEntityDao;
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

	private com.soffid.iam.base.model.DefaultAttributeMappingEntityDao defaultAttributeMappingEntityDao;

	/**
	 * Sets reference to <code>defaultAttributeMappingEntityDao</code>.
	 */
	public void setDefaultAttributeMappingEntityDao (com.soffid.iam.base.model.DefaultAttributeMappingEntityDao defaultAttributeMappingEntityDao) {
		this.defaultAttributeMappingEntityDao = defaultAttributeMappingEntityDao;
	}

	/**
	 * Gets reference to <code>defaultAttributeMappingEntityDao</code>.
	 */
	public com.soffid.iam.base.model.DefaultAttributeMappingEntityDao getDefaultAttributeMappingEntityDao () {
		return defaultAttributeMappingEntityDao;
	}

	private com.soffid.iam.base.model.DefaultObjectMappingEntityDao defaultObjectMappingEntityDao;

	/**
	 * Sets reference to <code>defaultObjectMappingEntityDao</code>.
	 */
	public void setDefaultObjectMappingEntityDao (com.soffid.iam.base.model.DefaultObjectMappingEntityDao defaultObjectMappingEntityDao) {
		this.defaultObjectMappingEntityDao = defaultObjectMappingEntityDao;
	}

	/**
	 * Gets reference to <code>defaultObjectMappingEntityDao</code>.
	 */
	public com.soffid.iam.base.model.DefaultObjectMappingEntityDao getDefaultObjectMappingEntityDao () {
		return defaultObjectMappingEntityDao;
	}

	private com.soffid.iam.base.model.DefaultObjectMappingPropertyEntityDao defaultObjectMappingPropertyEntityDao;

	/**
	 * Sets reference to <code>defaultObjectMappingPropertyEntityDao</code>.
	 */
	public void setDefaultObjectMappingPropertyEntityDao (com.soffid.iam.base.model.DefaultObjectMappingPropertyEntityDao defaultObjectMappingPropertyEntityDao) {
		this.defaultObjectMappingPropertyEntityDao = defaultObjectMappingPropertyEntityDao;
	}

	/**
	 * Gets reference to <code>defaultObjectMappingPropertyEntityDao</code>.
	 */
	public com.soffid.iam.base.model.DefaultObjectMappingPropertyEntityDao getDefaultObjectMappingPropertyEntityDao () {
		return defaultObjectMappingPropertyEntityDao;
	}

	private com.soffid.iam.base.model.ServerPluginEntityDao serverPluginEntityDao;

	/**
	 * Sets reference to <code>serverPluginEntityDao</code>.
	 */
	public void setServerPluginEntityDao (com.soffid.iam.base.model.ServerPluginEntityDao serverPluginEntityDao) {
		this.serverPluginEntityDao = serverPluginEntityDao;
	}

	/**
	 * Gets reference to <code>serverPluginEntityDao</code>.
	 */
	public com.soffid.iam.base.model.ServerPluginEntityDao getServerPluginEntityDao () {
		return serverPluginEntityDao;
	}

	private com.soffid.iam.base.model.ServerPluginModuleEntityDao serverPluginModuleEntityDao;

	/**
	 * Sets reference to <code>serverPluginModuleEntityDao</code>.
	 */
	public void setServerPluginModuleEntityDao (com.soffid.iam.base.model.ServerPluginModuleEntityDao serverPluginModuleEntityDao) {
		this.serverPluginModuleEntityDao = serverPluginModuleEntityDao;
	}

	/**
	 * Gets reference to <code>serverPluginModuleEntityDao</code>.
	 */
	public com.soffid.iam.base.model.ServerPluginModuleEntityDao getServerPluginModuleEntityDao () {
		return serverPluginModuleEntityDao;
	}


	/**
	 * @see com.soffid.iam.base.service.ServerPluginService#	 * @see com.soffid.iam.base.service.ServerPluginService#boolean deployPlugin(byte[] i)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean deployPlugin(
		final byte[] i)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.DuplicatedClassException
	{
		if (i == null ) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.ServerPluginService.deployPlugin(byte[] i) - i cannot be empty");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleDeployPlugin(i)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.DuplicatedClassException) 
			throw (com.soffid.iam.exception.DuplicatedClassException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ServerPluginService.class).
			warn ("Error on ServerPluginService.deployPlugin", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ServerPluginService.deployPlugin", (Throwable) __r[1]);
	}

	protected abstract boolean handleDeployPlugin(byte[] i) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ServerPluginService#	 * @see com.soffid.iam.base.service.ServerPluginService#com.soffid.iam.base.api.AgentDescriptor getAgentDescriptor(java.lang.String className)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.AgentDescriptor getAgentDescriptor(
		final java.lang.String className)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (className == null || className.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AgentDescriptor com.soffid.iam.base.service.ServerPluginService.getAgentDescriptor(java.lang.String className) - className cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetAgentDescriptor(className)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.AgentDescriptor) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ServerPluginService.class).
			warn ("Error on ServerPluginService.getAgentDescriptor", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ServerPluginService.getAgentDescriptor", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.AgentDescriptor handleGetAgentDescriptor(java.lang.String className) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ServerPluginService#	 * @see com.soffid.iam.base.service.ServerPluginService#java.lang.String getServerVersion()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String getServerVersion()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetServerVersion()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ServerPluginService.class).
			warn ("Error on ServerPluginService.getServerVersion", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ServerPluginService.getServerVersion", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGetServerVersion() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ServerPluginService#	 * @see com.soffid.iam.base.service.ServerPluginService#java.util.Collection<com.soffid.iam.base.api.AgentDescriptorWorkflow> findAgentDescriptorWorkflows(com.soffid.iam.base.api.AgentDescriptor agent)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.AgentDescriptorWorkflow> findAgentDescriptorWorkflows(
		final com.soffid.iam.base.api.AgentDescriptor agent)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (agent == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.AgentDescriptorWorkflow> com.soffid.iam.base.service.ServerPluginService.findAgentDescriptorWorkflows(com.soffid.iam.base.api.AgentDescriptor agent) - agent cannot be null");
		}
		if (agent.getId() == null ) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.AgentDescriptorWorkflow> com.soffid.iam.base.service.ServerPluginService.findAgentDescriptorWorkflows(com.soffid.iam.base.api.AgentDescriptor agent) - agent.id cannot be null");
		}
		if (agent.getDescription() == null || agent.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.AgentDescriptorWorkflow> com.soffid.iam.base.service.ServerPluginService.findAgentDescriptorWorkflows(com.soffid.iam.base.api.AgentDescriptor agent) - agent.description cannot be null");
		}
		if (agent.getClassName() == null || agent.getClassName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.AgentDescriptorWorkflow> com.soffid.iam.base.service.ServerPluginService.findAgentDescriptorWorkflows(com.soffid.iam.base.api.AgentDescriptor agent) - agent.className cannot be null");
		}
		if (agent.getUserInterface() == null ) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.AgentDescriptorWorkflow> com.soffid.iam.base.service.ServerPluginService.findAgentDescriptorWorkflows(com.soffid.iam.base.api.AgentDescriptor agent) - agent.userInterface cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAgentDescriptorWorkflows(agent)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.AgentDescriptorWorkflow>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ServerPluginService.class).
			warn ("Error on ServerPluginService.findAgentDescriptorWorkflows", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ServerPluginService.findAgentDescriptorWorkflows", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.AgentDescriptorWorkflow> handleFindAgentDescriptorWorkflows(com.soffid.iam.base.api.AgentDescriptor agent) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ServerPluginService#	 * @see com.soffid.iam.base.service.ServerPluginService#java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> getAgentDescriptors()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> getAgentDescriptors()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetAgentDescriptors()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.AgentDescriptor>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ServerPluginService.class).
			warn ("Error on ServerPluginService.getAgentDescriptors", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ServerPluginService.getAgentDescriptors", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> handleGetAgentDescriptors() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ServerPluginService#	 * @see com.soffid.iam.base.service.ServerPluginService#java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> getAllAgentDescriptorsInfo()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> getAllAgentDescriptorsInfo()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetAllAgentDescriptorsInfo()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.AgentDescriptor>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ServerPluginService.class).
			warn ("Error on ServerPluginService.getAllAgentDescriptorsInfo", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ServerPluginService.getAllAgentDescriptorsInfo", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> handleGetAllAgentDescriptorsInfo() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ServerPluginService#	 * @see com.soffid.iam.base.service.ServerPluginService#java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> getPluginAgentDescriptors(com.soffid.iam.base.api.ServerPlugin plugin)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> getPluginAgentDescriptors(
		final com.soffid.iam.base.api.ServerPlugin plugin)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (plugin == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> com.soffid.iam.base.service.ServerPluginService.getPluginAgentDescriptors(com.soffid.iam.base.api.ServerPlugin plugin) - plugin cannot be null");
		}
		if (plugin.getId() == null ) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> com.soffid.iam.base.service.ServerPluginService.getPluginAgentDescriptors(com.soffid.iam.base.api.ServerPlugin plugin) - plugin.id cannot be null");
		}
		if (plugin.getVersion() == null || plugin.getVersion().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> com.soffid.iam.base.service.ServerPluginService.getPluginAgentDescriptors(com.soffid.iam.base.api.ServerPlugin plugin) - plugin.version cannot be null");
		}
		if (plugin.getName() == null || plugin.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> com.soffid.iam.base.service.ServerPluginService.getPluginAgentDescriptors(com.soffid.iam.base.api.ServerPlugin plugin) - plugin.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetPluginAgentDescriptors(plugin)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.AgentDescriptor>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ServerPluginService.class).
			warn ("Error on ServerPluginService.getPluginAgentDescriptors", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ServerPluginService.getPluginAgentDescriptors", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> handleGetPluginAgentDescriptors(com.soffid.iam.base.api.ServerPlugin plugin) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ServerPluginService#	 * @see com.soffid.iam.base.service.ServerPluginService#java.util.Collection<com.soffid.iam.base.api.ServerPlugin> listServerPlugins()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.ServerPlugin> listServerPlugins()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleListServerPlugins()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.ServerPlugin>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ServerPluginService.class).
			warn ("Error on ServerPluginService.listServerPlugins", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ServerPluginService.listServerPlugins", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.ServerPlugin> handleListServerPlugins() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ServerPluginService#	 * @see com.soffid.iam.base.service.ServerPluginService#void deletePlugin(com.soffid.iam.base.api.ServerPlugin plugin)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void deletePlugin(
		final com.soffid.iam.base.api.ServerPlugin plugin)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (plugin == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.ServerPluginService.deletePlugin(com.soffid.iam.base.api.ServerPlugin plugin) - plugin cannot be null");
		}
		if (plugin.getId() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.ServerPluginService.deletePlugin(com.soffid.iam.base.api.ServerPlugin plugin) - plugin.id cannot be null");
		}
		if (plugin.getVersion() == null || plugin.getVersion().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.ServerPluginService.deletePlugin(com.soffid.iam.base.api.ServerPlugin plugin) - plugin.version cannot be null");
		}
		if (plugin.getName() == null || plugin.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.ServerPluginService.deletePlugin(com.soffid.iam.base.api.ServerPlugin plugin) - plugin.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDeletePlugin(plugin);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ServerPluginService.class).
			warn ("Error on ServerPluginService.deletePlugin", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ServerPluginService.deletePlugin", (Throwable) __r[1]);
	}

	protected abstract void handleDeletePlugin(com.soffid.iam.base.api.ServerPlugin plugin) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ServerPluginService#	 * @see com.soffid.iam.base.service.ServerPluginService#void enablePlugin(com.soffid.iam.base.api.ServerPlugin plugin, boolean status)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void enablePlugin(
		final com.soffid.iam.base.api.ServerPlugin plugin, 
		final boolean status)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (plugin == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.ServerPluginService.enablePlugin(com.soffid.iam.base.api.ServerPlugin plugin, boolean status) - plugin cannot be null");
		}
		if (plugin.getId() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.ServerPluginService.enablePlugin(com.soffid.iam.base.api.ServerPlugin plugin, boolean status) - plugin.id cannot be null");
		}
		if (plugin.getVersion() == null || plugin.getVersion().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.ServerPluginService.enablePlugin(com.soffid.iam.base.api.ServerPlugin plugin, boolean status) - plugin.version cannot be null");
		}
		if (plugin.getName() == null || plugin.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.ServerPluginService.enablePlugin(com.soffid.iam.base.api.ServerPlugin plugin, boolean status) - plugin.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleEnablePlugin(plugin, status);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ServerPluginService.class).
			warn ("Error on ServerPluginService.enablePlugin", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ServerPluginService.enablePlugin", (Throwable) __r[1]);
	}

	protected abstract void handleEnablePlugin(com.soffid.iam.base.api.ServerPlugin plugin, boolean status) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ServerPluginService#	 * @see com.soffid.iam.base.service.ServerPluginService#void updatePlugin(byte[] i)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void updatePlugin(
		final byte[] i)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.DuplicatedClassException
	{
		if (i == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.ServerPluginService.updatePlugin(byte[] i) - i cannot be empty");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpdatePlugin(i);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.DuplicatedClassException) 
			throw (com.soffid.iam.exception.DuplicatedClassException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ServerPluginService.class).
			warn ("Error on ServerPluginService.updatePlugin", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ServerPluginService.updatePlugin", (Throwable) __r[1]);
	}

	protected abstract void handleUpdatePlugin(byte[] i) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ServerPluginService#	 * @see com.soffid.iam.base.service.ServerPluginService#void updatePlugin(com.soffid.iam.base.api.ServerPlugin plugin)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void updatePlugin(
		final com.soffid.iam.base.api.ServerPlugin plugin)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (plugin == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.ServerPluginService.updatePlugin(com.soffid.iam.base.api.ServerPlugin plugin) - plugin cannot be null");
		}
		if (plugin.getId() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.ServerPluginService.updatePlugin(com.soffid.iam.base.api.ServerPlugin plugin) - plugin.id cannot be null");
		}
		if (plugin.getVersion() == null || plugin.getVersion().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.ServerPluginService.updatePlugin(com.soffid.iam.base.api.ServerPlugin plugin) - plugin.version cannot be null");
		}
		if (plugin.getName() == null || plugin.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.ServerPluginService.updatePlugin(com.soffid.iam.base.api.ServerPlugin plugin) - plugin.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpdatePlugin(plugin);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ServerPluginService.class).
			warn ("Error on ServerPluginService.updatePlugin", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ServerPluginService.updatePlugin", (Throwable) __r[1]);
	}

	protected abstract void handleUpdatePlugin(com.soffid.iam.base.api.ServerPlugin plugin) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ServerPluginService#	 * @see com.soffid.iam.base.service.ServerPluginService#void updatePlugin(java.io.File f)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void updatePlugin(
		final java.io.File f)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.DuplicatedClassException
	{
		if (f == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.ServerPluginService.updatePlugin(java.io.File f) - f cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpdatePlugin(f);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.DuplicatedClassException) 
			throw (com.soffid.iam.exception.DuplicatedClassException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ServerPluginService.class).
			warn ("Error on ServerPluginService.updatePlugin", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ServerPluginService.updatePlugin", (Throwable) __r[1]);
	}

	protected abstract void handleUpdatePlugin(java.io.File f) throws Exception;

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
