//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.base.service.TenantService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.base.service.TenantService
 */
public abstract class TenantServiceBase
	implements com.soffid.iam.base.service.TenantService
 {
	private com.soffid.iam.impl.service.ApplicationBootService applicationBootService;

	/**
	 * Sets reference to <code>applicationBootService</code>.
	 */
	public void setApplicationBootService (com.soffid.iam.impl.service.ApplicationBootService applicationBootService) {
		this.applicationBootService = applicationBootService;
	}

	/**
	 * Gets reference to <code>applicationBootService</code>.
	 */
	public com.soffid.iam.impl.service.ApplicationBootService getApplicationBootService () {
		return applicationBootService;
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

	private com.soffid.iam.base.model.SoffidLicenseEntityDao soffidLicenseEntityDao;

	/**
	 * Sets reference to <code>soffidLicenseEntityDao</code>.
	 */
	public void setSoffidLicenseEntityDao (com.soffid.iam.base.model.SoffidLicenseEntityDao soffidLicenseEntityDao) {
		this.soffidLicenseEntityDao = soffidLicenseEntityDao;
	}

	/**
	 * Gets reference to <code>soffidLicenseEntityDao</code>.
	 */
	public com.soffid.iam.base.model.SoffidLicenseEntityDao getSoffidLicenseEntityDao () {
		return soffidLicenseEntityDao;
	}

	private com.soffid.iam.base.model.TenantDisabledPermissionEntityDao tenantDisabledPermissionEntityDao;

	/**
	 * Sets reference to <code>tenantDisabledPermissionEntityDao</code>.
	 */
	public void setTenantDisabledPermissionEntityDao (com.soffid.iam.base.model.TenantDisabledPermissionEntityDao tenantDisabledPermissionEntityDao) {
		this.tenantDisabledPermissionEntityDao = tenantDisabledPermissionEntityDao;
	}

	/**
	 * Gets reference to <code>tenantDisabledPermissionEntityDao</code>.
	 */
	public com.soffid.iam.base.model.TenantDisabledPermissionEntityDao getTenantDisabledPermissionEntityDao () {
		return tenantDisabledPermissionEntityDao;
	}

	private com.soffid.iam.base.model.TenantEntityDao tenantEntityDao;

	/**
	 * Sets reference to <code>tenantEntityDao</code>.
	 */
	public void setTenantEntityDao (com.soffid.iam.base.model.TenantEntityDao tenantEntityDao) {
		this.tenantEntityDao = tenantEntityDao;
	}

	/**
	 * Gets reference to <code>tenantEntityDao</code>.
	 */
	public com.soffid.iam.base.model.TenantEntityDao getTenantEntityDao () {
		return tenantEntityDao;
	}

	private com.soffid.iam.base.model.TenantServerEntityDao tenantServerEntityDao;

	/**
	 * Sets reference to <code>tenantServerEntityDao</code>.
	 */
	public void setTenantServerEntityDao (com.soffid.iam.base.model.TenantServerEntityDao tenantServerEntityDao) {
		this.tenantServerEntityDao = tenantServerEntityDao;
	}

	/**
	 * Gets reference to <code>tenantServerEntityDao</code>.
	 */
	public com.soffid.iam.base.model.TenantServerEntityDao getTenantServerEntityDao () {
		return tenantServerEntityDao;
	}


	/**
	 * @see com.soffid.iam.base.service.TenantService#	 * @see com.soffid.iam.base.service.TenantService#com.soffid.iam.base.api.Tenant importTenant(java.io.InputStream in)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Tenant importTenant(
		final java.io.InputStream in)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (in == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Tenant com.soffid.iam.base.service.TenantService.importTenant(java.io.InputStream in) - in cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleImportTenant(in)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Tenant) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.TenantService.class).
			warn ("Error on TenantService.importTenant", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on TenantService.importTenant", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Tenant handleImportTenant(java.io.InputStream in) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.TenantService#	 * @see com.soffid.iam.base.service.TenantService#java.lang.String getTenantToken(java.lang.String[] purpose)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String getTenantToken(
		final java.lang.String[] purpose)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (purpose == null ) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.base.service.TenantService.getTenantToken(java.lang.String[] purpose) - purpose cannot be empty");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetTenantToken(purpose)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.TenantService.class).
			warn ("Error on TenantService.getTenantToken", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on TenantService.getTenantToken", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGetTenantToken(java.lang.String[] purpose) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.TenantService#	 * @see com.soffid.iam.base.service.TenantService#com.soffid.iam.base.api.Tenant create(com.soffid.iam.base.api.Tenant tenant)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Tenant create(
		final com.soffid.iam.base.api.Tenant tenant)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (tenant == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Tenant com.soffid.iam.base.service.TenantService.create(com.soffid.iam.base.api.Tenant tenant) - tenant cannot be null");
		}
		if (tenant.getName() == null || tenant.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Tenant com.soffid.iam.base.service.TenantService.create(com.soffid.iam.base.api.Tenant tenant) - tenant.name cannot be null");
		}
		if (tenant.getDescription() == null || tenant.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Tenant com.soffid.iam.base.service.TenantService.create(com.soffid.iam.base.api.Tenant tenant) - tenant.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(tenant)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Tenant) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.TenantService.class).
			warn ("Error on TenantService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on TenantService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Tenant handleCreate(com.soffid.iam.base.api.Tenant tenant) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.TenantService#	 * @see com.soffid.iam.base.service.TenantService#com.soffid.iam.base.api.Tenant getMasterTenant()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Tenant getMasterTenant()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetMasterTenant()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Tenant) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.TenantService.class).
			warn ("Error on TenantService.getMasterTenant", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on TenantService.getMasterTenant", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Tenant handleGetMasterTenant() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.TenantService#	 * @see com.soffid.iam.base.service.TenantService#com.soffid.iam.base.api.Tenant getTenant(java.lang.Long id)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Tenant getTenant(
		final java.lang.Long id)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (id == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Tenant com.soffid.iam.base.service.TenantService.getTenant(java.lang.Long id) - id cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetTenant(id)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Tenant) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.TenantService.class).
			warn ("Error on TenantService.getTenant", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on TenantService.getTenant", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Tenant handleGetTenant(java.lang.Long id) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.TenantService#	 * @see com.soffid.iam.base.service.TenantService#com.soffid.iam.base.api.Tenant getTenant(java.lang.String name)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Tenant getTenant(
		final java.lang.String name)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Tenant com.soffid.iam.base.service.TenantService.getTenant(java.lang.String name) - name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetTenant(name)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Tenant) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.TenantService.class).
			warn ("Error on TenantService.getTenant", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on TenantService.getTenant", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Tenant handleGetTenant(java.lang.String name) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.TenantService#	 * @see com.soffid.iam.base.service.TenantService#com.soffid.iam.base.api.Tenant update(com.soffid.iam.base.api.Tenant tenant)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Tenant update(
		final com.soffid.iam.base.api.Tenant tenant)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (tenant == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Tenant com.soffid.iam.base.service.TenantService.update(com.soffid.iam.base.api.Tenant tenant) - tenant cannot be null");
		}
		if (tenant.getName() == null || tenant.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Tenant com.soffid.iam.base.service.TenantService.update(com.soffid.iam.base.api.Tenant tenant) - tenant.name cannot be null");
		}
		if (tenant.getDescription() == null || tenant.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Tenant com.soffid.iam.base.service.TenantService.update(com.soffid.iam.base.api.Tenant tenant) - tenant.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(tenant)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Tenant) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.TenantService.class).
			warn ("Error on TenantService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on TenantService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Tenant handleUpdate(com.soffid.iam.base.api.Tenant tenant) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.TenantService#	 * @see com.soffid.iam.base.service.TenantService#java.util.Collection<com.soffid.iam.base.api.Tenant> listTenants()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.Tenant> listTenants()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleListTenants()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.Tenant>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.TenantService.class).
			warn ("Error on TenantService.listTenants", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on TenantService.listTenants", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.Tenant> handleListTenants() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.TenantService#	 * @see com.soffid.iam.base.service.TenantService#java.util.List<java.lang.String> getDisabledPermissions(com.soffid.iam.base.api.Tenant tenant)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<java.lang.String> getDisabledPermissions(
		final com.soffid.iam.base.api.Tenant tenant)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (tenant == null) {
			throw new IllegalArgumentException("java.util.List<java.lang.String> com.soffid.iam.base.service.TenantService.getDisabledPermissions(com.soffid.iam.base.api.Tenant tenant) - tenant cannot be null");
		}
		if (tenant.getName() == null || tenant.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<java.lang.String> com.soffid.iam.base.service.TenantService.getDisabledPermissions(com.soffid.iam.base.api.Tenant tenant) - tenant.name cannot be null");
		}
		if (tenant.getDescription() == null || tenant.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<java.lang.String> com.soffid.iam.base.service.TenantService.getDisabledPermissions(com.soffid.iam.base.api.Tenant tenant) - tenant.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetDisabledPermissions(tenant)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.TenantService.class).
			warn ("Error on TenantService.getDisabledPermissions", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on TenantService.getDisabledPermissions", (Throwable) __r[1]);
	}

	protected abstract java.util.List<java.lang.String> handleGetDisabledPermissions(com.soffid.iam.base.api.Tenant tenant) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.TenantService#	 * @see com.soffid.iam.base.service.TenantService#java.util.List<java.lang.String> getTenantServers(com.soffid.iam.base.api.Tenant tenant)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<java.lang.String> getTenantServers(
		final com.soffid.iam.base.api.Tenant tenant)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (tenant == null) {
			throw new IllegalArgumentException("java.util.List<java.lang.String> com.soffid.iam.base.service.TenantService.getTenantServers(com.soffid.iam.base.api.Tenant tenant) - tenant cannot be null");
		}
		if (tenant.getName() == null || tenant.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<java.lang.String> com.soffid.iam.base.service.TenantService.getTenantServers(com.soffid.iam.base.api.Tenant tenant) - tenant.name cannot be null");
		}
		if (tenant.getDescription() == null || tenant.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<java.lang.String> com.soffid.iam.base.service.TenantService.getTenantServers(com.soffid.iam.base.api.Tenant tenant) - tenant.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetTenantServers(tenant)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.TenantService.class).
			warn ("Error on TenantService.getTenantServers", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on TenantService.getTenantServers", (Throwable) __r[1]);
	}

	protected abstract java.util.List<java.lang.String> handleGetTenantServers(com.soffid.iam.base.api.Tenant tenant) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.TenantService#	 * @see com.soffid.iam.base.service.TenantService#void addTenantServer(com.soffid.iam.base.api.Tenant tenant, java.lang.String server)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void addTenantServer(
		final com.soffid.iam.base.api.Tenant tenant, 
		final java.lang.String server)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (tenant == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.TenantService.addTenantServer(com.soffid.iam.base.api.Tenant tenant, java.lang.String server) - tenant cannot be null");
		}
		if (tenant.getName() == null || tenant.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.TenantService.addTenantServer(com.soffid.iam.base.api.Tenant tenant, java.lang.String server) - tenant.name cannot be null");
		}
		if (tenant.getDescription() == null || tenant.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.TenantService.addTenantServer(com.soffid.iam.base.api.Tenant tenant, java.lang.String server) - tenant.description cannot be null");
		}
		if (server == null || server.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.TenantService.addTenantServer(com.soffid.iam.base.api.Tenant tenant, java.lang.String server) - server cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleAddTenantServer(tenant, server);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.TenantService.class).
			warn ("Error on TenantService.addTenantServer", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on TenantService.addTenantServer", (Throwable) __r[1]);
	}

	protected abstract void handleAddTenantServer(com.soffid.iam.base.api.Tenant tenant, java.lang.String server) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.TenantService#	 * @see com.soffid.iam.base.service.TenantService#void disablePermission(com.soffid.iam.base.api.Tenant tenant, java.lang.String permission)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void disablePermission(
		final com.soffid.iam.base.api.Tenant tenant, 
		final java.lang.String permission)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (tenant == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.TenantService.disablePermission(com.soffid.iam.base.api.Tenant tenant, java.lang.String permission) - tenant cannot be null");
		}
		if (tenant.getName() == null || tenant.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.TenantService.disablePermission(com.soffid.iam.base.api.Tenant tenant, java.lang.String permission) - tenant.name cannot be null");
		}
		if (tenant.getDescription() == null || tenant.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.TenantService.disablePermission(com.soffid.iam.base.api.Tenant tenant, java.lang.String permission) - tenant.description cannot be null");
		}
		if (permission == null || permission.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.TenantService.disablePermission(com.soffid.iam.base.api.Tenant tenant, java.lang.String permission) - permission cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDisablePermission(tenant, permission);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.TenantService.class).
			warn ("Error on TenantService.disablePermission", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on TenantService.disablePermission", (Throwable) __r[1]);
	}

	protected abstract void handleDisablePermission(com.soffid.iam.base.api.Tenant tenant, java.lang.String permission) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.TenantService#	 * @see com.soffid.iam.base.service.TenantService#void enablePermission(com.soffid.iam.base.api.Tenant tenant, java.lang.String permission)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void enablePermission(
		final com.soffid.iam.base.api.Tenant tenant, 
		final java.lang.String permission)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (tenant == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.TenantService.enablePermission(com.soffid.iam.base.api.Tenant tenant, java.lang.String permission) - tenant cannot be null");
		}
		if (tenant.getName() == null || tenant.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.TenantService.enablePermission(com.soffid.iam.base.api.Tenant tenant, java.lang.String permission) - tenant.name cannot be null");
		}
		if (tenant.getDescription() == null || tenant.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.TenantService.enablePermission(com.soffid.iam.base.api.Tenant tenant, java.lang.String permission) - tenant.description cannot be null");
		}
		if (permission == null || permission.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.TenantService.enablePermission(com.soffid.iam.base.api.Tenant tenant, java.lang.String permission) - permission cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleEnablePermission(tenant, permission);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.TenantService.class).
			warn ("Error on TenantService.enablePermission", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on TenantService.enablePermission", (Throwable) __r[1]);
	}

	protected abstract void handleEnablePermission(com.soffid.iam.base.api.Tenant tenant, java.lang.String permission) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.TenantService#	 * @see com.soffid.iam.base.service.TenantService#void remove(com.soffid.iam.base.api.Tenant tenant)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void remove(
		final com.soffid.iam.base.api.Tenant tenant)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (tenant == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.TenantService.remove(com.soffid.iam.base.api.Tenant tenant) - tenant cannot be null");
		}
		if (tenant.getName() == null || tenant.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.TenantService.remove(com.soffid.iam.base.api.Tenant tenant) - tenant.name cannot be null");
		}
		if (tenant.getDescription() == null || tenant.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.TenantService.remove(com.soffid.iam.base.api.Tenant tenant) - tenant.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRemove(tenant);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.TenantService.class).
			warn ("Error on TenantService.remove", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on TenantService.remove", (Throwable) __r[1]);
	}

	protected abstract void handleRemove(com.soffid.iam.base.api.Tenant tenant) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.TenantService#	 * @see com.soffid.iam.base.service.TenantService#void removeTenantServer(com.soffid.iam.base.api.Tenant tenant, java.lang.String server)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void removeTenantServer(
		final com.soffid.iam.base.api.Tenant tenant, 
		final java.lang.String server)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (tenant == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.TenantService.removeTenantServer(com.soffid.iam.base.api.Tenant tenant, java.lang.String server) - tenant cannot be null");
		}
		if (tenant.getName() == null || tenant.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.TenantService.removeTenantServer(com.soffid.iam.base.api.Tenant tenant, java.lang.String server) - tenant.name cannot be null");
		}
		if (tenant.getDescription() == null || tenant.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.TenantService.removeTenantServer(com.soffid.iam.base.api.Tenant tenant, java.lang.String server) - tenant.description cannot be null");
		}
		if (server == null || server.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.TenantService.removeTenantServer(com.soffid.iam.base.api.Tenant tenant, java.lang.String server) - server cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRemoveTenantServer(tenant, server);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.TenantService.class).
			warn ("Error on TenantService.removeTenantServer", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on TenantService.removeTenantServer", (Throwable) __r[1]);
	}

	protected abstract void handleRemoveTenantServer(com.soffid.iam.base.api.Tenant tenant, java.lang.String server) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.TenantService#	 * @see com.soffid.iam.base.service.TenantService#void exportTenant(com.soffid.iam.base.api.Tenant tenant, java.io.OutputStream out)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void exportTenant(
		final com.soffid.iam.base.api.Tenant tenant, 
		final java.io.OutputStream out)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (tenant == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.TenantService.exportTenant(com.soffid.iam.base.api.Tenant tenant, java.io.OutputStream out) - tenant cannot be null");
		}
		if (tenant.getName() == null || tenant.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.TenantService.exportTenant(com.soffid.iam.base.api.Tenant tenant, java.io.OutputStream out) - tenant.name cannot be null");
		}
		if (tenant.getDescription() == null || tenant.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.TenantService.exportTenant(com.soffid.iam.base.api.Tenant tenant, java.io.OutputStream out) - tenant.description cannot be null");
		}
		if (out == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.TenantService.exportTenant(com.soffid.iam.base.api.Tenant tenant, java.io.OutputStream out) - out cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleExportTenant(tenant, out);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.TenantService.class).
			warn ("Error on TenantService.exportTenant", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on TenantService.exportTenant", (Throwable) __r[1]);
	}

	protected abstract void handleExportTenant(com.soffid.iam.base.api.Tenant tenant, java.io.OutputStream out) throws Exception;

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
