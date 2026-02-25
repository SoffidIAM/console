//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.base.service.ConfigurationService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.base.service.ConfigurationService
 */
public abstract class ConfigurationServiceBase
	implements com.soffid.iam.base.service.ConfigurationService
 {
	private com.soffid.iam.base.model.BlobConfigurationEntityDao blobConfigurationEntityDao;

	/**
	 * Sets reference to <code>blobConfigurationEntityDao</code>.
	 */
	public void setBlobConfigurationEntityDao (com.soffid.iam.base.model.BlobConfigurationEntityDao blobConfigurationEntityDao) {
		this.blobConfigurationEntityDao = blobConfigurationEntityDao;
	}

	/**
	 * Gets reference to <code>blobConfigurationEntityDao</code>.
	 */
	public com.soffid.iam.base.model.BlobConfigurationEntityDao getBlobConfigurationEntityDao () {
		return blobConfigurationEntityDao;
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

	private com.soffid.iam.sync.service.SyncServerService syncServerService;

	/**
	 * Sets reference to <code>syncServerService</code>.
	 */
	public void setSyncServerService (com.soffid.iam.sync.service.SyncServerService syncServerService) {
		this.syncServerService = syncServerService;
	}

	/**
	 * Gets reference to <code>syncServerService</code>.
	 */
	public com.soffid.iam.sync.service.SyncServerService getSyncServerService () {
		return syncServerService;
	}


	/**
	 * @see com.soffid.iam.base.service.ConfigurationService#	 * @see com.soffid.iam.base.service.ConfigurationService#byte[] getBlob(java.lang.String name)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public byte[] getBlob(
		final java.lang.String name)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("byte[] com.soffid.iam.base.service.ConfigurationService.getBlob(java.lang.String name) - name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetBlob(name)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (byte[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ConfigurationService.class).
			warn ("Error on ConfigurationService.getBlob", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ConfigurationService.getBlob", (Throwable) __r[1]);
	}

	protected abstract byte[] handleGetBlob(java.lang.String name) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ConfigurationService#	 * @see com.soffid.iam.base.service.ConfigurationService#com.soffid.iam.base.api.Configuration create(com.soffid.iam.base.api.Configuration configuracio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Configuration create(
		final com.soffid.iam.base.api.Configuration configuracio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (configuracio == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Configuration com.soffid.iam.base.service.ConfigurationService.create(com.soffid.iam.base.api.Configuration configuracio) - configuracio cannot be null");
		}
		if (configuracio.getName() == null || configuracio.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Configuration com.soffid.iam.base.service.ConfigurationService.create(com.soffid.iam.base.api.Configuration configuracio) - configuracio.name cannot be null");
		}
		if (configuracio.getValue() == null || configuracio.getValue().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Configuration com.soffid.iam.base.service.ConfigurationService.create(com.soffid.iam.base.api.Configuration configuracio) - configuracio.value cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(configuracio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Configuration) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ConfigurationService.class).
			warn ("Error on ConfigurationService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ConfigurationService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Configuration handleCreate(com.soffid.iam.base.api.Configuration configuracio) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ConfigurationService#	 * @see com.soffid.iam.base.service.ConfigurationService#com.soffid.iam.base.api.Configuration findMasterParameterByNameAndNetwork(java.lang.String paramter, java.lang.String networkName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Configuration findMasterParameterByNameAndNetwork(
		final java.lang.String paramter, 
		final java.lang.String networkName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindMasterParameterByNameAndNetwork(paramter, networkName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Configuration) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ConfigurationService.class).
			warn ("Error on ConfigurationService.findMasterParameterByNameAndNetwork", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ConfigurationService.findMasterParameterByNameAndNetwork", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Configuration handleFindMasterParameterByNameAndNetwork(java.lang.String paramter, java.lang.String networkName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ConfigurationService#	 * @see com.soffid.iam.base.service.ConfigurationService#com.soffid.iam.base.api.Configuration findParameterByNameAndNetworkName(java.lang.String codiParametre, java.lang.String codiXarxa)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Configuration findParameterByNameAndNetworkName(
		final java.lang.String codiParametre, 
		final java.lang.String codiXarxa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindParameterByNameAndNetworkName(codiParametre, codiXarxa)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Configuration) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ConfigurationService.class).
			warn ("Error on ConfigurationService.findParameterByNameAndNetworkName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ConfigurationService.findParameterByNameAndNetworkName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Configuration handleFindParameterByNameAndNetworkName(java.lang.String codiParametre, java.lang.String codiXarxa) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ConfigurationService#	 * @see com.soffid.iam.base.service.ConfigurationService#com.soffid.iam.base.api.Configuration update(com.soffid.iam.base.api.Configuration configuracio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Configuration update(
		final com.soffid.iam.base.api.Configuration configuracio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (configuracio == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Configuration com.soffid.iam.base.service.ConfigurationService.update(com.soffid.iam.base.api.Configuration configuracio) - configuracio cannot be null");
		}
		if (configuracio.getName() == null || configuracio.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Configuration com.soffid.iam.base.service.ConfigurationService.update(com.soffid.iam.base.api.Configuration configuracio) - configuracio.name cannot be null");
		}
		if (configuracio.getValue() == null || configuracio.getValue().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Configuration com.soffid.iam.base.service.ConfigurationService.update(com.soffid.iam.base.api.Configuration configuracio) - configuracio.value cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(configuracio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Configuration) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ConfigurationService.class).
			warn ("Error on ConfigurationService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ConfigurationService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Configuration handleUpdate(com.soffid.iam.base.api.Configuration configuracio) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ConfigurationService#	 * @see com.soffid.iam.base.service.ConfigurationService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.Configuration> findConfigurations(com.soffid.zkdb.api.Query query)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.Configuration> findConfigurations(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindConfigurations(query)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.Configuration>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ConfigurationService.class).
			warn ("Error on ConfigurationService.findConfigurations", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ConfigurationService.findConfigurations", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.Configuration> handleFindConfigurations(com.soffid.zkdb.api.Query query) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ConfigurationService#	 * @see com.soffid.iam.base.service.ConfigurationService#java.lang.String findTenantParameter(java.lang.String tenant, java.lang.String parameter)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		readOnly=true)
	public java.lang.String findTenantParameter(
		final java.lang.String tenant, 
		final java.lang.String parameter)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (tenant == null || tenant.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.base.service.ConfigurationService.findTenantParameter(java.lang.String tenant, java.lang.String parameter) - tenant cannot be null");
		}
		if (parameter == null || parameter.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.base.service.ConfigurationService.findTenantParameter(java.lang.String tenant, java.lang.String parameter) - parameter cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindTenantParameter(tenant, parameter)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ConfigurationService.class).
			warn ("Error on ConfigurationService.findTenantParameter", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ConfigurationService.findTenantParameter", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleFindTenantParameter(java.lang.String tenant, java.lang.String parameter) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ConfigurationService#	 * @see com.soffid.iam.base.service.ConfigurationService#java.lang.String getBlobVersion(java.lang.String name)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String getBlobVersion(
		final java.lang.String name)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.base.service.ConfigurationService.getBlobVersion(java.lang.String name) - name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetBlobVersion(name)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ConfigurationService.class).
			warn ("Error on ConfigurationService.getBlobVersion", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ConfigurationService.getBlobVersion", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGetBlobVersion(java.lang.String name) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ConfigurationService#	 * @see com.soffid.iam.base.service.ConfigurationService#java.util.Collection<com.soffid.iam.base.api.Configuration> getParameters()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.Configuration> getParameters()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetParameters()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.Configuration>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ConfigurationService.class).
			warn ("Error on ConfigurationService.getParameters", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ConfigurationService.getParameters", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.Configuration> handleGetParameters() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ConfigurationService#	 * @see com.soffid.iam.base.service.ConfigurationService#void delete(com.soffid.iam.base.api.Configuration configuracio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.base.api.Configuration configuracio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (configuracio == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.ConfigurationService.delete(com.soffid.iam.base.api.Configuration configuracio) - configuracio cannot be null");
		}
		if (configuracio.getName() == null || configuracio.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.ConfigurationService.delete(com.soffid.iam.base.api.Configuration configuracio) - configuracio.name cannot be null");
		}
		if (configuracio.getValue() == null || configuracio.getValue().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.ConfigurationService.delete(com.soffid.iam.base.api.Configuration configuracio) - configuracio.value cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(configuracio);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ConfigurationService.class).
			warn ("Error on ConfigurationService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ConfigurationService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.base.api.Configuration configuracio) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ConfigurationService#	 * @see com.soffid.iam.base.service.ConfigurationService#void deleteBlob(java.lang.String name)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void deleteBlob(
		final java.lang.String name)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.ConfigurationService.deleteBlob(java.lang.String name) - name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDeleteBlob(name);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ConfigurationService.class).
			warn ("Error on ConfigurationService.deleteBlob", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ConfigurationService.deleteBlob", (Throwable) __r[1]);
	}

	protected abstract void handleDeleteBlob(java.lang.String name) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ConfigurationService#	 * @see com.soffid.iam.base.service.ConfigurationService#void updateBlob(java.lang.String name, byte[] data)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void updateBlob(
		final java.lang.String name, 
		final byte[] data)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.ConfigurationService.updateBlob(java.lang.String name, byte[] data) - name cannot be null");
		}
		if (data == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.ConfigurationService.updateBlob(java.lang.String name, byte[] data) - data cannot be empty");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpdateBlob(name, data);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ConfigurationService.class).
			warn ("Error on ConfigurationService.updateBlob", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ConfigurationService.updateBlob", (Throwable) __r[1]);
	}

	protected abstract void handleUpdateBlob(java.lang.String name, byte[] data) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.ConfigurationService#	 * @see com.soffid.iam.base.service.ConfigurationService#void updateBlob(java.lang.String name, byte[] data, java.lang.String version)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void updateBlob(
		final java.lang.String name, 
		final byte[] data, 
		final java.lang.String version)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.ConfigurationService.updateBlob(java.lang.String name, byte[] data, java.lang.String version) - name cannot be null");
		}
		if (data == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.ConfigurationService.updateBlob(java.lang.String name, byte[] data, java.lang.String version) - data cannot be empty");
		}
		if (version == null || version.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.ConfigurationService.updateBlob(java.lang.String name, byte[] data, java.lang.String version) - version cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpdateBlob(name, data, version);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.ConfigurationService.class).
			warn ("Error on ConfigurationService.updateBlob", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ConfigurationService.updateBlob", (Throwable) __r[1]);
	}

	protected abstract void handleUpdateBlob(java.lang.String name, byte[] data, java.lang.String version) throws Exception;

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
