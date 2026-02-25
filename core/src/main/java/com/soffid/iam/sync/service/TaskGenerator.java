//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.service;
/**
 * Service TaskGenerator
 * Generador de tareas. Recupera las tareas a realizar de la tabla SC_TASQUE y
 * las encola en TaskQueue. Adicionalmente coordina tareas de sincronización
 * entre los dispatchers y otros threads relacionados con el SSO tales como el
 * acceso a base de datos<BR>
 * Consulta las siguientes propiedades del sistema:<BR>
 * <li>server.getlogs: true permite la lectura de logs</li>
 * <li>server.debuglevel: nivel de información de debug 0 = mínimo 10 = máximo</li>
 * <li>server.dispatcher.enabled: true si el dispatcher debe recuperar las
 * tareas de la tabla SC_TASQUE.</li>
 * <li>server.dispatcher.delay: pausa (en ms.) que realizará el task dispatcher
 * tras procesar la cola de tareas pendientes antes de volver a comenzar</li>
 * <li>server.dispatcher.timeout: pausa (en ms.) que realizará el task
 * dispatcher cuando se produzca en error de comunicaciones</li>
 * <li>server.privatekey: archivo donde se encuentra la clave privada DSA que
 * se utilizará para contactar con los agentes</li>
 * <li>server.query.connections: Número maximo de conexiones a abrir contra la
 * base de datos (por defecto 5)</li>
 * 
 * @author $Author: u07286 $
 * @version $Revision: 1.1 $

 */
public interface TaskGenerator {
	public final static String SERVICE_NAME = "com.soffid.iam.sync.service.TaskGenerator";

	/**
	 * Operation canGetLog

	 * @param td 
	 * @return 
	 */
	boolean canGetLog(
		final com.soffid.iam.sync.engine.DispatcherHandler td)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isEnabled

	 * @return 
	 */
	boolean isEnabled()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isMainServer

	 * @return 
	 */
	boolean isMainServer()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getDispatcher

	 * @param id 
	 * @return 
	 */
	com.soffid.iam.sync.engine.DispatcherHandler getDispatcher(
		final java.lang.String id)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getStatus

	 * @return 
	 */
	java.lang.String getStatus()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation startVirtualSourceTransaction

	 * @return 
	 */
	java.lang.String startVirtualSourceTransaction()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation startVirtualSourceTransaction

	 * @param readonly 
	 * @return 
	 */
	java.lang.String startVirtualSourceTransaction(
		final boolean readonly)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAllTenantsDispatchers

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.sync.engine.DispatcherHandler> getAllTenantsDispatchers()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getDispatchers

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.sync.engine.DispatcherHandler> getDispatchers()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getActiveTenants

	 * @return 
	 */
	java.util.Set<java.lang.Long> getActiveTenants()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation finishGetLog

	 * @param td 
	 */
	void finishGetLog(
		final com.soffid.iam.sync.engine.DispatcherHandler td)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation finishVirtualSourceTransaction

	 * @param virtualTransactionId 
	 */
	void finishVirtualSourceTransaction(
		final java.lang.String virtualTransactionId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation loadTasks

	 */
	void loadTasks()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation purgeServerInstances

	 */
	void purgeServerInstances()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation setEnabled

	 * @param enabled 
	 */
	void setEnabled(
		final boolean enabled)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation shutDown

	 */
	void shutDown()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateAgents

	 */
	void updateAgents()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateClusterStatus

	 */
	void updateClusterStatus()
			throws com.soffid.iam.exception.InternalErrorException;

}
