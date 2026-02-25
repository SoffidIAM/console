//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.base.service.GenAIProviderService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.base.service.GenAIProviderService
 */
public abstract class GenAIProviderServiceBase
	implements com.soffid.iam.base.service.GenAIProviderService
 {

	/**
	 * @see com.soffid.iam.base.service.GenAIProviderService#	 * @see com.soffid.iam.base.service.GenAIProviderService#java.util.List<java.lang.String> getGenAIMetadata(java.lang.String engine)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<java.lang.String> getGenAIMetadata(
		final java.lang.String engine)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (engine == null || engine.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<java.lang.String> com.soffid.iam.base.service.GenAIProviderService.getGenAIMetadata(java.lang.String engine) - engine cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetGenAIMetadata(engine)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.GenAIProviderService.class).
			warn ("Error on GenAIProviderService.getGenAIMetadata", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GenAIProviderService.getGenAIMetadata", (Throwable) __r[1]);
	}

	protected abstract java.util.List<java.lang.String> handleGetGenAIMetadata(java.lang.String engine) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.GenAIProviderService#	 * @see com.soffid.iam.base.service.GenAIProviderService#java.util.List<java.lang.String> getGenAISentences(java.lang.String engine, java.lang.String[] context, java.lang.String sentence)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<java.lang.String> getGenAISentences(
		final java.lang.String engine, 
		final java.lang.String[] context, 
		final java.lang.String sentence)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (engine == null || engine.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<java.lang.String> com.soffid.iam.base.service.GenAIProviderService.getGenAISentences(java.lang.String engine, java.lang.String[] context, java.lang.String sentence) - engine cannot be null");
		}
		if (sentence == null || sentence.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<java.lang.String> com.soffid.iam.base.service.GenAIProviderService.getGenAISentences(java.lang.String engine, java.lang.String[] context, java.lang.String sentence) - sentence cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetGenAISentences(engine, context, sentence)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.GenAIProviderService.class).
			warn ("Error on GenAIProviderService.getGenAISentences", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GenAIProviderService.getGenAISentences", (Throwable) __r[1]);
	}

	protected abstract java.util.List<java.lang.String> handleGetGenAISentences(java.lang.String engine, java.lang.String[] context, java.lang.String sentence) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.GenAIProviderService#	 * @see com.soffid.iam.base.service.GenAIProviderService#void clearCache()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void clearCache()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleClearCache();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.GenAIProviderService.class).
			warn ("Error on GenAIProviderService.clearCache", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GenAIProviderService.clearCache", (Throwable) __r[1]);
	}

	protected abstract void handleClearCache() throws Exception;

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
