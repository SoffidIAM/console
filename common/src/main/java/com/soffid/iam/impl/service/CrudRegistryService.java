//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
/**
 * Service CrudRegistryService
 */
public interface CrudRegistryService {
	public final static String SERVICE_NAME = "com.soffid.iam.impl.service.CrudRegistryService";

	/**
	 * Operation getHandler

	 * @param cl 
	 * @return 
	 */
	<E> com.soffid.zkdb.api.CrudHandler<E> getHandler(
		final java.lang.Class<E> cl)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getHandler

	 * @param className 
	 * @return 
	 */
	<E> com.soffid.zkdb.api.CrudHandler<E> getHandler(
		final java.lang.String className)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation registerDefaultHandlers

	 */
	void registerDefaultHandlers()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation registerHandler

	 * @param cl 
	 * @param handler 
	 */
	<E> void registerHandler(
		final java.lang.Class<E> cl, 
		final com.soffid.zkdb.api.CrudHandler<E> handler)
			throws com.soffid.iam.exception.InternalErrorException;

}
