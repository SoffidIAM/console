//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service.ejb;
/**
 * EJB SessionService
 */
public interface SessionService

 {

	java.util.Collection<com.soffid.iam.am.api.Session> findActiveSessions()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
