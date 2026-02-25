//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service.ejb;
/**
 * EJB CrudRegistryService
 */
public interface CrudRegistryService

 {

	<E> com.soffid.zkdb.api.CrudHandler<E> getHandler(
		final java.lang.Class<E> cl)
	throws com.soffid.iam.exception.InternalErrorException;

	<E> com.soffid.zkdb.api.CrudHandler<E> getHandler(
		final java.lang.String className)
	throws com.soffid.iam.exception.InternalErrorException;

}
