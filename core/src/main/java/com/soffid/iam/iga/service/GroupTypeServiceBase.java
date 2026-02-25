//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.iga.service.GroupTypeService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.iga.service.GroupTypeService
 */
public abstract class GroupTypeServiceBase
	implements com.soffid.iam.iga.service.GroupTypeService
 {
	private com.soffid.iam.impl.service.AsyncRunnerService asyncRunnerService;

	/**
	 * Sets reference to <code>asyncRunnerService</code>.
	 */
	public void setAsyncRunnerService (com.soffid.iam.impl.service.AsyncRunnerService asyncRunnerService) {
		this.asyncRunnerService = asyncRunnerService;
	}

	/**
	 * Gets reference to <code>asyncRunnerService</code>.
	 */
	public com.soffid.iam.impl.service.AsyncRunnerService getAsyncRunnerService () {
		return asyncRunnerService;
	}

	private com.soffid.iam.iga.api.GroupType groupType;

	/**
	 * Sets reference to <code>groupType</code>.
	 */
	public void setGroupType (com.soffid.iam.iga.api.GroupType groupType) {
		this.groupType = groupType;
	}

	/**
	 * Gets reference to <code>groupType</code>.
	 */
	public com.soffid.iam.iga.api.GroupType getGroupType () {
		return groupType;
	}

	private com.soffid.iam.iga.model.GroupTypeEntityDao groupTypeEntityDao;

	/**
	 * Sets reference to <code>groupTypeEntityDao</code>.
	 */
	public void setGroupTypeEntityDao (com.soffid.iam.iga.model.GroupTypeEntityDao groupTypeEntityDao) {
		this.groupTypeEntityDao = groupTypeEntityDao;
	}

	/**
	 * Gets reference to <code>groupTypeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.GroupTypeEntityDao getGroupTypeEntityDao () {
		return groupTypeEntityDao;
	}


	/**
	 * @see com.soffid.iam.iga.service.GroupTypeService#	 * @see com.soffid.iam.iga.service.GroupTypeService#com.soffid.iam.iga.api.GroupType create(com.soffid.iam.iga.api.GroupType tipus)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.GroupType create(
		final com.soffid.iam.iga.api.GroupType tipus)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (tipus == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.GroupType com.soffid.iam.iga.service.GroupTypeService.create(com.soffid.iam.iga.api.GroupType tipus) - tipus cannot be null");
		}
		if (tipus.getName() == null || tipus.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.GroupType com.soffid.iam.iga.service.GroupTypeService.create(com.soffid.iam.iga.api.GroupType tipus) - tipus.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(tipus)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.GroupType) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupTypeService.class).
			warn ("Error on GroupTypeService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupTypeService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.GroupType handleCreate(com.soffid.iam.iga.api.GroupType tipus) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupTypeService#	 * @see com.soffid.iam.iga.service.GroupTypeService#com.soffid.iam.iga.api.GroupType findGroupTypeByName(java.lang.String CodiTipusUnitatOrganitzativa)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.GroupType findGroupTypeByName(
		final java.lang.String CodiTipusUnitatOrganitzativa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (CodiTipusUnitatOrganitzativa == null || CodiTipusUnitatOrganitzativa.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.GroupType com.soffid.iam.iga.service.GroupTypeService.findGroupTypeByName(java.lang.String CodiTipusUnitatOrganitzativa) - CodiTipusUnitatOrganitzativa cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindGroupTypeByName(CodiTipusUnitatOrganitzativa)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.GroupType) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupTypeService.class).
			warn ("Error on GroupTypeService.findGroupTypeByName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupTypeService.findGroupTypeByName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.GroupType handleFindGroupTypeByName(java.lang.String CodiTipusUnitatOrganitzativa) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupTypeService#	 * @see com.soffid.iam.iga.service.GroupTypeService#com.soffid.iam.iga.api.GroupType update(com.soffid.iam.iga.api.GroupType tipus)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.GroupType update(
		final com.soffid.iam.iga.api.GroupType tipus)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (tipus == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.GroupType com.soffid.iam.iga.service.GroupTypeService.update(com.soffid.iam.iga.api.GroupType tipus) - tipus cannot be null");
		}
		if (tipus.getName() == null || tipus.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.GroupType com.soffid.iam.iga.service.GroupTypeService.update(com.soffid.iam.iga.api.GroupType tipus) - tipus.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(tipus)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.GroupType) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupTypeService.class).
			warn ("Error on GroupTypeService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupTypeService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.GroupType handleUpdate(com.soffid.iam.iga.api.GroupType tipus) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupTypeService#	 * @see com.soffid.iam.iga.service.GroupTypeService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.GroupType> findGroupTypes(com.soffid.zkdb.api.Query q)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.GroupType> findGroupTypes(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (q == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.GroupType> com.soffid.iam.iga.service.GroupTypeService.findGroupTypes(com.soffid.zkdb.api.Query q) - q cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindGroupTypes(q)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.GroupType>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupTypeService.class).
			warn ("Error on GroupTypeService.findGroupTypes", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupTypeService.findGroupTypes", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.GroupType> handleFindGroupTypes(com.soffid.zkdb.api.Query q) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupTypeService#	 * @see com.soffid.iam.iga.service.GroupTypeService#java.util.List<com.soffid.iam.iga.api.GroupType> findAllGroupTypes()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.iga.api.GroupType> findAllGroupTypes()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAllGroupTypes()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.iga.api.GroupType>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupTypeService.class).
			warn ("Error on GroupTypeService.findAllGroupTypes", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupTypeService.findAllGroupTypes", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.iga.api.GroupType> handleFindAllGroupTypes() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupTypeService#	 * @see com.soffid.iam.iga.service.GroupTypeService#void delete(com.soffid.iam.iga.api.GroupType tipus)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.GroupType tipus)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (tipus == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.GroupTypeService.delete(com.soffid.iam.iga.api.GroupType tipus) - tipus cannot be null");
		}
		if (tipus.getName() == null || tipus.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.GroupTypeService.delete(com.soffid.iam.iga.api.GroupType tipus) - tipus.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(tipus);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupTypeService.class).
			warn ("Error on GroupTypeService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupTypeService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.GroupType tipus) throws Exception;

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
