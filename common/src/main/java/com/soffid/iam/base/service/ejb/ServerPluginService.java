//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service.ejb;
/**
 * EJB ServerPluginService
 */
public interface ServerPluginService

 {

	boolean deployPlugin(
		final byte[] i)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.DuplicatedClassException;

	com.soffid.iam.base.api.AgentDescriptor getAgentDescriptor(
		final java.lang.String className)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String getServerVersion()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.AgentDescriptorWorkflow> findAgentDescriptorWorkflows(
		final com.soffid.iam.base.api.AgentDescriptor agent)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> getAgentDescriptors()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> getAllAgentDescriptorsInfo()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> getPluginAgentDescriptors(
		final com.soffid.iam.base.api.ServerPlugin plugin)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.ServerPlugin> listServerPlugins()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void deletePlugin(
		final com.soffid.iam.base.api.ServerPlugin plugin)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void enablePlugin(
		final com.soffid.iam.base.api.ServerPlugin plugin, 
		final boolean status)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void updatePlugin(
		final com.soffid.iam.base.api.ServerPlugin plugin)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
