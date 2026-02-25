//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.iga.service.AttributeTranslationService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.iga.service.AttributeTranslationService
 */
public abstract class AttributeTranslationServiceBase
	implements com.soffid.iam.iga.service.AttributeTranslationService
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

	private com.soffid.iam.iga.model.AttributeTranslationEntityDao attributeTranslationEntityDao;

	/**
	 * Sets reference to <code>attributeTranslationEntityDao</code>.
	 */
	public void setAttributeTranslationEntityDao (com.soffid.iam.iga.model.AttributeTranslationEntityDao attributeTranslationEntityDao) {
		this.attributeTranslationEntityDao = attributeTranslationEntityDao;
	}

	/**
	 * Gets reference to <code>attributeTranslationEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.AttributeTranslationEntityDao getAttributeTranslationEntityDao () {
		return attributeTranslationEntityDao;
	}


	/**
	 * @see com.soffid.iam.iga.service.AttributeTranslationService#	 * @see com.soffid.iam.iga.service.AttributeTranslationService#com.soffid.iam.iga.api.AttributeTranslation create(com.soffid.iam.iga.api.AttributeTranslation att)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.AttributeTranslation create(
		final com.soffid.iam.iga.api.AttributeTranslation att)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (att == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.AttributeTranslation com.soffid.iam.iga.service.AttributeTranslationService.create(com.soffid.iam.iga.api.AttributeTranslation att) - att cannot be null");
		}
		if (att.getDomain() == null || att.getDomain().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.AttributeTranslation com.soffid.iam.iga.service.AttributeTranslationService.create(com.soffid.iam.iga.api.AttributeTranslation att) - att.domain cannot be null");
		}
		if (att.getColumn1() == null || att.getColumn1().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.AttributeTranslation com.soffid.iam.iga.service.AttributeTranslationService.create(com.soffid.iam.iga.api.AttributeTranslation att) - att.column1 cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(att)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.AttributeTranslation) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.AttributeTranslationService.class).
			warn ("Error on AttributeTranslationService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AttributeTranslationService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.AttributeTranslation handleCreate(com.soffid.iam.iga.api.AttributeTranslation att) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.AttributeTranslationService#	 * @see com.soffid.iam.iga.service.AttributeTranslationService#com.soffid.iam.iga.api.AttributeTranslation update(com.soffid.iam.iga.api.AttributeTranslation att)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.AttributeTranslation update(
		final com.soffid.iam.iga.api.AttributeTranslation att)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (att == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.AttributeTranslation com.soffid.iam.iga.service.AttributeTranslationService.update(com.soffid.iam.iga.api.AttributeTranslation att) - att cannot be null");
		}
		if (att.getDomain() == null || att.getDomain().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.AttributeTranslation com.soffid.iam.iga.service.AttributeTranslationService.update(com.soffid.iam.iga.api.AttributeTranslation att) - att.domain cannot be null");
		}
		if (att.getColumn1() == null || att.getColumn1().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.AttributeTranslation com.soffid.iam.iga.service.AttributeTranslationService.update(com.soffid.iam.iga.api.AttributeTranslation att) - att.column1 cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(att)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.AttributeTranslation) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.AttributeTranslationService.class).
			warn ("Error on AttributeTranslationService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AttributeTranslationService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.AttributeTranslation handleUpdate(com.soffid.iam.iga.api.AttributeTranslation att) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.AttributeTranslationService#	 * @see com.soffid.iam.iga.service.AttributeTranslationService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.AttributeTranslation> findByQuery(com.soffid.zkdb.api.Query query)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.AttributeTranslation> findByQuery(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (query == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.AttributeTranslation> com.soffid.iam.iga.service.AttributeTranslationService.findByQuery(com.soffid.zkdb.api.Query query) - query cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindByQuery(query)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.AttributeTranslation>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.AttributeTranslationService.class).
			warn ("Error on AttributeTranslationService.findByQuery", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AttributeTranslationService.findByQuery", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.AttributeTranslation> handleFindByQuery(com.soffid.zkdb.api.Query query) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.AttributeTranslationService#	 * @see com.soffid.iam.iga.service.AttributeTranslationService#java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> findByColumn1(java.lang.String domain, java.lang.String column1)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> findByColumn1(
		final java.lang.String domain, 
		final java.lang.String column1)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (domain == null || domain.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> com.soffid.iam.iga.service.AttributeTranslationService.findByColumn1(java.lang.String domain, java.lang.String column1) - domain cannot be null");
		}
		if (column1 == null || column1.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> com.soffid.iam.iga.service.AttributeTranslationService.findByColumn1(java.lang.String domain, java.lang.String column1) - column1 cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindByColumn1(domain, column1)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.AttributeTranslationService.class).
			warn ("Error on AttributeTranslationService.findByColumn1", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AttributeTranslationService.findByColumn1", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> handleFindByColumn1(java.lang.String domain, java.lang.String column1) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.AttributeTranslationService#	 * @see com.soffid.iam.iga.service.AttributeTranslationService#java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> findByColumn2(java.lang.String domain, java.lang.String column2)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> findByColumn2(
		final java.lang.String domain, 
		final java.lang.String column2)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (domain == null || domain.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> com.soffid.iam.iga.service.AttributeTranslationService.findByColumn2(java.lang.String domain, java.lang.String column2) - domain cannot be null");
		}
		if (column2 == null || column2.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> com.soffid.iam.iga.service.AttributeTranslationService.findByColumn2(java.lang.String domain, java.lang.String column2) - column2 cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindByColumn2(domain, column2)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.AttributeTranslationService.class).
			warn ("Error on AttributeTranslationService.findByColumn2", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AttributeTranslationService.findByColumn2", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> handleFindByColumn2(java.lang.String domain, java.lang.String column2) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.AttributeTranslationService#	 * @see com.soffid.iam.iga.service.AttributeTranslationService#java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> findByExample(java.lang.String domain, java.lang.String column1, java.lang.String column2)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> findByExample(
		final java.lang.String domain, 
		final java.lang.String column1, 
		final java.lang.String column2)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindByExample(domain, column1, column2)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.AttributeTranslationService.class).
			warn ("Error on AttributeTranslationService.findByExample", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AttributeTranslationService.findByExample", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> handleFindByExample(java.lang.String domain, java.lang.String column1, java.lang.String column2) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.AttributeTranslationService#	 * @see com.soffid.iam.iga.service.AttributeTranslationService#java.util.Collection<java.lang.String> findDomains()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<java.lang.String> findDomains()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindDomains()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.AttributeTranslationService.class).
			warn ("Error on AttributeTranslationService.findDomains", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AttributeTranslationService.findDomains", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<java.lang.String> handleFindDomains() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.AttributeTranslationService#	 * @see com.soffid.iam.iga.service.AttributeTranslationService#void delete(com.soffid.iam.iga.api.AttributeTranslation att)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.AttributeTranslation att)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (att == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.AttributeTranslationService.delete(com.soffid.iam.iga.api.AttributeTranslation att) - att cannot be null");
		}
		if (att.getDomain() == null || att.getDomain().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.AttributeTranslationService.delete(com.soffid.iam.iga.api.AttributeTranslation att) - att.domain cannot be null");
		}
		if (att.getColumn1() == null || att.getColumn1().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.AttributeTranslationService.delete(com.soffid.iam.iga.api.AttributeTranslation att) - att.column1 cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(att);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.AttributeTranslationService.class).
			warn ("Error on AttributeTranslationService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AttributeTranslationService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.AttributeTranslation att) throws Exception;

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
