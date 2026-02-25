//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service;
/**
 * Service EntryPointService
 */
public interface EntryPointService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.am.service.EntryPointService";

	public final static String SERVICE_NAME = "com.soffid.iam.am.service.EntryPointService";

	/**
	 * Operation canAdmin

	 * @param puntEntrada 
	 * @return 
	 */
	boolean canAdmin(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation canExecute

	 * @param puntEntrada 
	 * @return 
	 */
	boolean canExecute(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation canQuery

	 * @param puntEntrada 
	 * @return 
	 */
	boolean canQuery(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation canView

	 * @param puntEntrada 
	 * @return 
	 */
	boolean canView(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation copyApplicationAccessLink

	 * @param puntEntradaCopiar 
	 * @param puntEntradaMenuDesti 
	 * @return 
	 */
	boolean copyApplicationAccessLink(
		final com.soffid.iam.am.api.AccessTree puntEntradaCopiar, 
		final com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation copyApplicationAccess

	 * @param puntEntradaCopiar 
	 * @param puntEntradaMenuDesti 
	 * @return 
	 */
	boolean copyApplicationAccess(
		final com.soffid.iam.am.api.AccessTree puntEntradaCopiar, 
		final com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isAuthorized

	 * @param puntEntrada 
	 * @param nivell 
	 * @return 
	 */
	boolean isAuthorized(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final java.lang.String nivell)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isAuthorized

	 * @param codiUsuari 
	 * @param idPuntEntrada 
	 * @param nivell 
	 * @return 
	 */
	boolean isAuthorized(
		final java.lang.String codiUsuari, 
		final java.lang.Long idPuntEntrada, 
		final java.lang.String nivell)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation applicationAccessTreeHasAnyACL

	 * @param codiUsuari 
	 * @return 
	 */
	boolean applicationAccessTreeHasAnyACL(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation moveApplicationAccessTreeMenu

	 * @param puntEntradaMoure 
	 * @param puntEntradaMenuDesti 
	 * @return 
	 */
	boolean moveApplicationAccessTreeMenu(
		final com.soffid.iam.am.api.AccessTree puntEntradaMoure, 
		final com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation reorderApplicationAccess

	 * @param puntEntradaOrdenar 
	 * @param puntEntradaSeguent 
	 * @return 
	 */
	boolean reorderApplicationAccess(
		final com.soffid.iam.am.api.AccessTree puntEntradaOrdenar, 
		final com.soffid.iam.am.api.AccessTree puntEntradaSeguent)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param puntEntrada 
	 * @return 
	 */
	com.soffid.iam.am.api.AccessTree create(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findApplicationAccessById

	 * @param id 
	 * @return 
	 */
	com.soffid.iam.am.api.AccessTree findApplicationAccessById(
		final long id)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRoot

	 * @return 
	 */
	com.soffid.iam.am.api.AccessTree findRoot()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param puntEntrada 
	 * @return 
	 */
	com.soffid.iam.am.api.AccessTree update(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createAuthorization

	 * @param puntEntrada 
	 * @param autoritzacio 
	 * @return 
	 */
	com.soffid.iam.am.api.AccessTreeAuthorization createAuthorization(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createExecution

	 * @param puntEntrada 
	 * @param execucio 
	 * @return 
	 */
	com.soffid.iam.am.api.AccessTreeExecution createExecution(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final com.soffid.iam.am.api.AccessTreeExecution execucio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateExecution

	 * @param puntEntrada 
	 * @param execucio 
	 * @return 
	 */
	com.soffid.iam.am.api.AccessTreeExecution updateExecution(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final com.soffid.iam.am.api.AccessTreeExecution execucio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAccessTrees

	 * @param q 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.AccessTree> findAccessTrees(
		final com.soffid.zkdb.api.Query q)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getScopeForAddress

	 * @param address 
	 * @return 
	 */
	java.lang.String getScopeForAddress(
		final java.lang.String address)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation validateXMLApplicationAccess

	 * @param puntEntrada 
	 * @return 
	 */
	java.lang.String validateXMLApplicationAccess(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findChildren

	 * @param puntEntrada 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.AccessTree> findChildren(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findMenuChildren

	 * @param puntEntrada 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.AccessTree> findMenuChildren(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findApplicationAccessByFilter

	 * @param nomPUE 
	 * @param codiPUE 
	 * @param codiAplicacio 
	 * @param codiRol 
	 * @param codiGrup 
	 * @param codiUsuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.AccessTree> findApplicationAccessByFilter(
		final java.lang.String nomPUE, 
		final java.lang.String codiPUE, 
		final java.lang.String codiAplicacio, 
		final java.lang.String codiRol, 
		final java.lang.String codiGrup, 
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAllMimeTypeExecution

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.AccessTreeExecutionType> getAllMimeTypeExecution()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getReverseApplicationAccessTree

	 * @param puntEntrada 
	 * @return 
	 */
	java.util.Collection<java.lang.String> getReverseApplicationAccessTree(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAuthorizationsApplicationAcessTree

	 * @param puntEntrada 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> getAuthorizationsApplicationAcessTree(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getExecutions

	 * @param puntEntrada 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.AccessTreeExecution> getExecutions(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param puntEntrada 
	 */
	void delete(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation deleteAuthorization

	 * @param puntEntrada 
	 * @param autoritzacio 
	 */
	void deleteAuthorization(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation deleteExecution

	 * @param puntEntrada 
	 * @param execucio 
	 */
	void deleteExecution(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final com.soffid.iam.am.api.AccessTreeExecution execucio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation sortChildren

	 * @param entryPointId 
	 */
	void sortChildren(
		final long entryPointId)
			throws com.soffid.iam.exception.InternalErrorException;

}
