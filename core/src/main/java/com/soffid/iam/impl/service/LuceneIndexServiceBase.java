//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.impl.service.LuceneIndexService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.impl.service.LuceneIndexService
 */
public abstract class LuceneIndexServiceBase
	implements com.soffid.iam.impl.service.LuceneIndexService
 {
	private com.soffid.iam.base.service.AdditionalDataService additionalDataService;

	/**
	 * Sets reference to <code>additionalDataService</code>.
	 */
	public void setAdditionalDataService (com.soffid.iam.base.service.AdditionalDataService additionalDataService) {
		this.additionalDataService = additionalDataService;
	}

	/**
	 * Gets reference to <code>additionalDataService</code>.
	 */
	public com.soffid.iam.base.service.AdditionalDataService getAdditionalDataService () {
		return additionalDataService;
	}

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

	private com.soffid.iam.iga.model.CustomObjectTypeEntityDao customObjectTypeEntityDao;

	/**
	 * Sets reference to <code>customObjectTypeEntityDao</code>.
	 */
	public void setCustomObjectTypeEntityDao (com.soffid.iam.iga.model.CustomObjectTypeEntityDao customObjectTypeEntityDao) {
		this.customObjectTypeEntityDao = customObjectTypeEntityDao;
	}

	/**
	 * Gets reference to <code>customObjectTypeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.CustomObjectTypeEntityDao getCustomObjectTypeEntityDao () {
		return customObjectTypeEntityDao;
	}

	private com.soffid.iam.base.model.LuceneIndexEntityDao luceneIndexEntityDao;

	/**
	 * Sets reference to <code>luceneIndexEntityDao</code>.
	 */
	public void setLuceneIndexEntityDao (com.soffid.iam.base.model.LuceneIndexEntityDao luceneIndexEntityDao) {
		this.luceneIndexEntityDao = luceneIndexEntityDao;
	}

	/**
	 * Gets reference to <code>luceneIndexEntityDao</code>.
	 */
	public com.soffid.iam.base.model.LuceneIndexEntityDao getLuceneIndexEntityDao () {
		return luceneIndexEntityDao;
	}

	private com.soffid.iam.base.model.LuceneIndexPartEntityDao luceneIndexPartEntityDao;

	/**
	 * Sets reference to <code>luceneIndexPartEntityDao</code>.
	 */
	public void setLuceneIndexPartEntityDao (com.soffid.iam.base.model.LuceneIndexPartEntityDao luceneIndexPartEntityDao) {
		this.luceneIndexPartEntityDao = luceneIndexPartEntityDao;
	}

	/**
	 * Gets reference to <code>luceneIndexPartEntityDao</code>.
	 */
	public com.soffid.iam.base.model.LuceneIndexPartEntityDao getLuceneIndexPartEntityDao () {
		return luceneIndexPartEntityDao;
	}


	/**
	 * @see com.soffid.iam.impl.service.LuceneIndexService#	 * @see com.soffid.iam.impl.service.LuceneIndexService#void addDocument(java.lang.String index, org.apache.lucene.document.Document doc)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void addDocument(
		final java.lang.String index, 
		final org.apache.lucene.document.Document doc)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (index == null || index.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.LuceneIndexService.addDocument(java.lang.String index, org.apache.lucene.document.Document doc) - index cannot be null");
		}
		if (doc == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.LuceneIndexService.addDocument(java.lang.String index, org.apache.lucene.document.Document doc) - doc cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleAddDocument(index, doc);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.LuceneIndexService.class).
			warn ("Error on LuceneIndexService.addDocument", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on LuceneIndexService.addDocument", (Throwable) __r[1]);
	}

	protected abstract void handleAddDocument(java.lang.String index, org.apache.lucene.document.Document doc) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.LuceneIndexService#	 * @see com.soffid.iam.impl.service.LuceneIndexService#void indexObject(java.lang.String index, java.lang.Object o)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void indexObject(
		final java.lang.String index, 
		final java.lang.Object o)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (index == null || index.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.LuceneIndexService.indexObject(java.lang.String index, java.lang.Object o) - index cannot be null");
		}
		if (o == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.LuceneIndexService.indexObject(java.lang.String index, java.lang.Object o) - o cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleIndexObject(index, o);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.LuceneIndexService.class).
			warn ("Error on LuceneIndexService.indexObject", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on LuceneIndexService.indexObject", (Throwable) __r[1]);
	}

	protected abstract void handleIndexObject(java.lang.String index, java.lang.Object o) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.LuceneIndexService#	 * @see com.soffid.iam.impl.service.LuceneIndexService#void resetIndex(java.lang.String index)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void resetIndex(
		final java.lang.String index)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (index == null || index.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.LuceneIndexService.resetIndex(java.lang.String index) - index cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleResetIndex(index);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.LuceneIndexService.class).
			warn ("Error on LuceneIndexService.resetIndex", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on LuceneIndexService.resetIndex", (Throwable) __r[1]);
	}

	protected abstract void handleResetIndex(java.lang.String index) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.LuceneIndexService#	 * @see com.soffid.iam.impl.service.LuceneIndexService#void search(java.lang.String index, java.lang.String query, org.apache.lucene.search.SimpleCollector collector)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void search(
		final java.lang.String index, 
		final java.lang.String query, 
		final org.apache.lucene.search.SimpleCollector collector)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (index == null || index.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.LuceneIndexService.search(java.lang.String index, java.lang.String query, org.apache.lucene.search.SimpleCollector collector) - index cannot be null");
		}
		if (collector == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.LuceneIndexService.search(java.lang.String index, java.lang.String query, org.apache.lucene.search.SimpleCollector collector) - collector cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSearch(index, query, collector);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.LuceneIndexService.class).
			warn ("Error on LuceneIndexService.search", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on LuceneIndexService.search", (Throwable) __r[1]);
	}

	protected abstract void handleSearch(java.lang.String index, java.lang.String query, org.apache.lucene.search.SimpleCollector collector) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.LuceneIndexService#	 * @see com.soffid.iam.impl.service.LuceneIndexService#void search(java.lang.String index, org.apache.lucene.search.Query query, org.apache.lucene.search.SimpleCollector collector)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void search(
		final java.lang.String index, 
		final org.apache.lucene.search.Query query, 
		final org.apache.lucene.search.SimpleCollector collector)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (index == null || index.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.LuceneIndexService.search(java.lang.String index, org.apache.lucene.search.Query query, org.apache.lucene.search.SimpleCollector collector) - index cannot be null");
		}
		if (collector == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.LuceneIndexService.search(java.lang.String index, org.apache.lucene.search.Query query, org.apache.lucene.search.SimpleCollector collector) - collector cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSearch(index, query, collector);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.LuceneIndexService.class).
			warn ("Error on LuceneIndexService.search", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on LuceneIndexService.search", (Throwable) __r[1]);
	}

	protected abstract void handleSearch(java.lang.String index, org.apache.lucene.search.Query query, org.apache.lucene.search.SimpleCollector collector) throws Exception;

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
