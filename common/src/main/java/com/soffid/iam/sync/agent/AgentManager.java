//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.agent;
/**
 * Service AgentManager
 */
public interface AgentManager {
	public final static String REMOTE_PATH = "/seycon/AgentManager";

	public final static String SERVICE_NAME = "com.soffid.iam.sync.agent.AgentManager";

	/**
	 * Operation findLogs

	 * @param config 
	 * @param firstRow 
	 * @param numRows 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.sync.api.LogFileEntry> findLogs(
		final com.soffid.iam.sync.api.LogConfiguration config, 
		final long firstRow, 
		final long numRows)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getServerLogs

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.sync.api.LogConfiguration> getServerLogs()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getSyncServerInfo

	 * @param tenant 
	 * @return 
	 */
	com.soffid.iam.sync.api.SyncServerInfo getSyncServerInfo(
		final java.lang.String tenant)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.io.IOException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createLocalAgent

	 * @param dispatcher 
	 * @return 
	 */
	java.lang.Object createLocalAgent(
		final com.soffid.iam.iga.api.System dispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createLocalAgentDebug

	 * @param dispatcher 
	 * @return 
	 */
	java.lang.Object createLocalAgentDebug(
		final com.soffid.iam.iga.api.System dispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createAgent

	 * @param dispatcher 
	 * @return 
	 */
	java.lang.String createAgent(
		final com.soffid.iam.iga.api.System dispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createAgentDebug

	 * @param dispatcher 
	 * @return 
	 */
	java.lang.String createAgentDebug(
		final com.soffid.iam.iga.api.System dispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation tailServerLog

	 * @return 
	 */
	java.lang.String[] tailServerLog()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation generateNewKey

	 * @return 
	 */
	java.security.PublicKey generateNewKey()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getCertificateValidityDate

	 * @return 
	 */
	java.util.Date getCertificateValidityDate()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation reset

	 */
	void reset()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation storeNewCertificate

	 * @param cert 
	 * @param root 
	 */
	void storeNewCertificate(
		final java.security.cert.X509Certificate cert, 
		final java.security.cert.X509Certificate root)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation configureLog

	 * @param config 
	 */
	void configureLog(
		final com.soffid.iam.sync.api.LogConfiguration config)
			throws com.soffid.iam.exception.InternalErrorException;

}
