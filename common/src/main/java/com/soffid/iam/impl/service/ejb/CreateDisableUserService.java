//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service.ejb;
/**
 * EJB CreateDisableUserService
 */
public interface CreateDisableUserService

 {

	java.lang.String setInitialPasswordToUser(
		final java.lang.String codiUsuari, 
		final java.lang.String codiDominiContrasenyes)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.Group> getManagedGroups()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
