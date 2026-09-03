//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * EJB RoleSuggestionService
 */
public interface RoleSuggestionService

 {

	java.util.List<com.soffid.iam.iga.api.RoleAccount> suggest(
		final java.lang.String user)
	throws com.soffid.iam.exception.InternalErrorException;

}
