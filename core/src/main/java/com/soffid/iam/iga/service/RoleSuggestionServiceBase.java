//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.iga.service.RoleSuggestionService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.iga.service.RoleSuggestionService
 */
public abstract class RoleSuggestionServiceBase
	implements com.soffid.iam.iga.service.RoleSuggestionService
 {

	/**
	 * @see com.soffid.iam.iga.service.RoleSuggestionService#	 * @see com.soffid.iam.iga.service.RoleSuggestionService#java.util.List<com.soffid.iam.iga.api.RoleAccount> suggest(java.lang.String user)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.iga.api.RoleAccount> suggest(
		final java.lang.String user)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.iga.api.RoleAccount> com.soffid.iam.iga.service.RoleSuggestionService.suggest(java.lang.String user) - user cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleSuggest(user)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.iga.api.RoleAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.RoleSuggestionService.class).
			warn ("Error on RoleSuggestionService.suggest", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on RoleSuggestionService.suggest", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.iga.api.RoleAccount> handleSuggest(java.lang.String user) throws Exception;

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
