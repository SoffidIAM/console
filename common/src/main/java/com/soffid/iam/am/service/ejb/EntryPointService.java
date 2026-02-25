//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service.ejb;
/**
 * EJB EntryPointService
 */
public interface EntryPointService

 {

	boolean canAdmin(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	boolean canExecute(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	boolean canQuery(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	boolean canView(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	boolean copyApplicationAccessLink(
		final com.soffid.iam.am.api.AccessTree puntEntradaCopiar, 
		final com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	boolean copyApplicationAccess(
		final com.soffid.iam.am.api.AccessTree puntEntradaCopiar, 
		final com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	boolean isAuthorized(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final java.lang.String nivell)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	boolean isAuthorized(
		final java.lang.String codiUsuari, 
		final java.lang.Long idPuntEntrada, 
		final java.lang.String nivell)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	boolean applicationAccessTreeHasAnyACL(
		final java.lang.String codiUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	boolean moveApplicationAccessTreeMenu(
		final com.soffid.iam.am.api.AccessTree puntEntradaMoure, 
		final com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	boolean reorderApplicationAccess(
		final com.soffid.iam.am.api.AccessTree puntEntradaOrdenar, 
		final com.soffid.iam.am.api.AccessTree puntEntradaSeguent)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.AccessTree create(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.AccessTree findApplicationAccessById(
		final long id)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.AccessTree findRoot()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.AccessTree update(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.AccessTreeAuthorization createAuthorization(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.AccessTreeExecution createExecution(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final com.soffid.iam.am.api.AccessTreeExecution execucio)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.AccessTreeExecution updateExecution(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final com.soffid.iam.am.api.AccessTreeExecution execucio)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.AccessTree> findAccessTrees(
		final com.soffid.zkdb.api.Query q)
	throws com.soffid.iam.exception.InternalErrorException;

	java.lang.String getScopeForAddress(
		final java.lang.String address)
	throws com.soffid.iam.exception.InternalErrorException;

	java.lang.String validateXMLApplicationAccess(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.am.api.AccessTree> findChildren(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.am.api.AccessTree> findMenuChildren(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.am.api.AccessTree> findApplicationAccessByFilter(
		final java.lang.String nomPUE, 
		final java.lang.String codiPUE, 
		final java.lang.String codiAplicacio, 
		final java.lang.String codiRol, 
		final java.lang.String codiGrup, 
		final java.lang.String codiUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.am.api.AccessTreeExecutionType> getAllMimeTypeExecution()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<java.lang.String> getReverseApplicationAccessTree(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> getAuthorizationsApplicationAcessTree(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.am.api.AccessTreeExecution> getExecutions(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void deleteAuthorization(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void deleteExecution(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final com.soffid.iam.am.api.AccessTreeExecution execucio)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void sortChildren(
		final long entryPointId)
	throws com.soffid.iam.exception.InternalErrorException;

}
