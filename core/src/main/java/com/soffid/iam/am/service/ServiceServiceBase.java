//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.am.service.ServiceService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.am.service.ServiceService
 */
public abstract class ServiceServiceBase
	implements com.soffid.iam.am.service.ServiceService
 {
	private com.soffid.iam.am.model.ServiceEntityDao serviceEntityDao;

	/**
	 * Sets reference to <code>serviceEntityDao</code>.
	 */
	public void setServiceEntityDao (com.soffid.iam.am.model.ServiceEntityDao serviceEntityDao) {
		this.serviceEntityDao = serviceEntityDao;
	}

	/**
	 * Gets reference to <code>serviceEntityDao</code>.
	 */
	public com.soffid.iam.am.model.ServiceEntityDao getServiceEntityDao () {
		return serviceEntityDao;
	}


	/**
	 * @see com.soffid.iam.am.service.ServiceService#	 * @see com.soffid.iam.am.service.ServiceService#com.soffid.iam.am.api.Service create(com.soffid.iam.am.api.Service servei)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Service create(
		final com.soffid.iam.am.api.Service servei)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (servei == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Service com.soffid.iam.am.service.ServiceService.create(com.soffid.iam.am.api.Service servei) - servei cannot be null");
		}
		if (servei.getCode() == null || servei.getCode().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Service com.soffid.iam.am.service.ServiceService.create(com.soffid.iam.am.api.Service servei) - servei.code cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(servei)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Service) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.ServiceService.class).
			warn ("Error on ServiceService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ServiceService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Service handleCreate(com.soffid.iam.am.api.Service servei) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.ServiceService#	 * @see com.soffid.iam.am.service.ServiceService#com.soffid.iam.am.api.Service findServiceByName(java.lang.String codi)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Service findServiceByName(
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codi == null || codi.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Service com.soffid.iam.am.service.ServiceService.findServiceByName(java.lang.String codi) - codi cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindServiceByName(codi)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Service) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.ServiceService.class).
			warn ("Error on ServiceService.findServiceByName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ServiceService.findServiceByName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Service handleFindServiceByName(java.lang.String codi) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.ServiceService#	 * @see com.soffid.iam.am.service.ServiceService#com.soffid.iam.am.api.Service update(com.soffid.iam.am.api.Service servei)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Service update(
		final com.soffid.iam.am.api.Service servei)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (servei == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Service com.soffid.iam.am.service.ServiceService.update(com.soffid.iam.am.api.Service servei) - servei cannot be null");
		}
		if (servei.getCode() == null || servei.getCode().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Service com.soffid.iam.am.service.ServiceService.update(com.soffid.iam.am.api.Service servei) - servei.code cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(servei)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Service) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.ServiceService.class).
			warn ("Error on ServiceService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ServiceService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Service handleUpdate(com.soffid.iam.am.api.Service servei) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.ServiceService#	 * @see com.soffid.iam.am.service.ServiceService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Service> findServices(com.soffid.zkdb.api.Query q)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Service> findServices(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (q == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Service> com.soffid.iam.am.service.ServiceService.findServices(com.soffid.zkdb.api.Query q) - q cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindServices(q)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Service>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.ServiceService.class).
			warn ("Error on ServiceService.findServices", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ServiceService.findServices", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Service> handleFindServices(com.soffid.zkdb.api.Query q) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.ServiceService#	 * @see com.soffid.iam.am.service.ServiceService#java.util.Collection<com.soffid.iam.am.api.Service> getServices()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.api.Service> getServices()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetServices()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.api.Service>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.ServiceService.class).
			warn ("Error on ServiceService.getServices", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ServiceService.getServices", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.api.Service> handleGetServices() throws Exception;

	/**
	 * @see com.soffid.iam.am.service.ServiceService#	 * @see com.soffid.iam.am.service.ServiceService#void delete(com.soffid.iam.am.api.Service servei)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.am.api.Service servei)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (servei == null) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.ServiceService.delete(com.soffid.iam.am.api.Service servei) - servei cannot be null");
		}
		if (servei.getCode() == null || servei.getCode().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.ServiceService.delete(com.soffid.iam.am.api.Service servei) - servei.code cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(servei);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.ServiceService.class).
			warn ("Error on ServiceService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ServiceService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.am.api.Service servei) throws Exception;

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
