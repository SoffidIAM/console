//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
/**
 * Service RoleSuggestionService
 */
public interface RoleSuggestionService {
	public final static String SERVICE_NAME = "com.soffid.iam.iga.service.RoleSuggestionService";

	/**
	 * Operation suggest

	 * @param user 
	 * @return 
	 */
	java.util.List<com.soffid.iam.iga.api.RoleAccount> suggest(
		final java.lang.String user)
			throws com.soffid.iam.exception.InternalErrorException;

}
