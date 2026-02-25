//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
/**
 * Service ServerPluginService
 */
public interface ServerPluginService {
	public final static String SERVICE_NAME = "com.soffid.iam.base.service.ServerPluginService";

	/**
	 * Operation deployPlugin
	 * Returns true if the console must be restarted

	 * @param i 
	 * @return 
	 */
	boolean deployPlugin(
		final byte[] i)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.DuplicatedClassException;

	/**
	 * Operation getAgentDescriptor

	 * @param className 
	 * @return 
	 */
	com.soffid.iam.base.api.AgentDescriptor getAgentDescriptor(
		final java.lang.String className)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getServerVersion

	 * @return 
	 */
	java.lang.String getServerVersion()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAgentDescriptorWorkflows

	 * @param agent 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.AgentDescriptorWorkflow> findAgentDescriptorWorkflows(
		final com.soffid.iam.base.api.AgentDescriptor agent)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAgentDescriptors

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> getAgentDescriptors()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAllAgentDescriptorsInfo

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> getAllAgentDescriptorsInfo()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getPluginAgentDescriptors

	 * @param plugin 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> getPluginAgentDescriptors(
		final com.soffid.iam.base.api.ServerPlugin plugin)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation listServerPlugins

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.ServerPlugin> listServerPlugins()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation deletePlugin

	 * @param plugin 
	 */
	void deletePlugin(
		final com.soffid.iam.base.api.ServerPlugin plugin)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation enablePlugin

	 * @param plugin 
	 * @param status 
	 */
	void enablePlugin(
		final com.soffid.iam.base.api.ServerPlugin plugin, 
		final boolean status)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updatePlugin
	 * Only deploys if it's a newer version

	 * @param i 
	 */
	void updatePlugin(
		final byte[] i)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.DuplicatedClassException;

	/**
	 * Operation updatePlugin

	 * @param plugin 
	 */
	void updatePlugin(
		final com.soffid.iam.base.api.ServerPlugin plugin)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updatePlugin
	 * Only deploys if it's a newer version

	 * @param f 
	 */
	void updatePlugin(
		final java.io.File f)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.DuplicatedClassException;

}
