//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.service;
/**
 * Service NetworkDiscoveryService
 */
public interface NetworkDiscoveryService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.pam.service.NetworkDiscoveryService";

	public final static String SERVICE_NAME = "com.soffid.iam.pam.service.NetworkDiscoveryService";

	/**
	 * Operation createSystemCandidate
	 * Generates a candidate system definiton

	 * @param host 
	 * @param type 
	 * @param userName 
	 * @param password 
	 * @param instance 
	 * @return 
	 */
	com.soffid.iam.iga.api.System createSystemCandidate(
		final com.soffid.iam.am.api.Host host, 
		final java.lang.String type, 
		final java.lang.String userName, 
		final com.soffid.iam.am.api.Password password, 
		final java.lang.String instance)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createHostService
	 * Method to register a manual host service

	 * @param service 
	 * @return 
	 */
	com.soffid.iam.pam.api.HostService createHostService(
		final com.soffid.iam.pam.api.HostService service)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateHostService
	 * Method to register a manual host service

	 * @param service 
	 * @return 
	 */
	com.soffid.iam.pam.api.HostService updateHostService(
		final com.soffid.iam.pam.api.HostService service)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findNetworkDiscoveryScheduledTask
	 * Internal method to get the discovery schedule

	 * @param network 
	 * @return 
	 */
	com.soffid.iam.sync.api.ScheduledTask findNetworkDiscoveryScheduledTask(
		final com.soffid.iam.am.api.Network network)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findHostEntryPoints
	 * Gets the entry points for a host

	 * @param host 
	 * @return 
	 */
	java.util.List<com.soffid.iam.am.api.AccessTree> findHostEntryPoints(
		final com.soffid.iam.am.api.Host host)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findHostPorts
	 * Gets the open ports for a host

	 * @param host 
	 * @return 
	 */
	java.util.List<com.soffid.iam.pam.api.HostPort> findHostPorts(
		final com.soffid.iam.am.api.Host host)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findHostServices
	 * Gets the account protected services for a host

	 * @param host 
	 * @return 
	 */
	java.util.List<com.soffid.iam.pam.api.HostService> findHostServices(
		final com.soffid.iam.am.api.Host host)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findHostSystems
	 * Gets the account protected services for a host

	 * @param host 
	 * @return 
	 */
	java.util.List<com.soffid.iam.iga.api.System> findHostSystems(
		final com.soffid.iam.am.api.Host host)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findNetworkAccount
	 * Retrieves account for network discovery

	 * @param network 
	 * @return 
	 */
	java.util.List<com.soffid.iam.base.api.Account> findNetworkAccount(
		final com.soffid.iam.am.api.Network network)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findSystemHosts
	 * Gets the account protected services for a host

	 * @param system 
	 * @return 
	 */
	java.util.List<com.soffid.iam.am.api.Host> findSystemHosts(
		final com.soffid.iam.iga.api.System system)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createNetworkAccount
	 * Register account for network discovery

	 * @param network 
	 * @param account 
	 */
	void createNetworkAccount(
		final com.soffid.iam.am.api.Network network, 
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation deleteHostService
	 * Method to register a manual host service

	 * @param service 
	 */
	void deleteHostService(
		final com.soffid.iam.pam.api.HostService service)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation disconnectSystemFromHost
	 * Removes a candidate system definiton

	 * @param host 
	 * @param system 
	 */
	void disconnectSystemFromHost(
		final com.soffid.iam.am.api.Host host, 
		final com.soffid.iam.iga.api.System system)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation enableNetworkDiscoveryScheduledTask
	 * Enable network discovery

	 * @param network 
	 */
	void enableNetworkDiscoveryScheduledTask(
		final com.soffid.iam.am.api.Network network)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation registerHostEntryPoint
	 * Method to register an entry point

	 * @param host 
	 * @param entryPoint 
	 */
	void registerHostEntryPoint(
		final com.soffid.iam.am.api.Host host, 
		final com.soffid.iam.am.api.AccessTree entryPoint)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation registerHostPorts
	 * Internal method to register the host open ports

	 * @param host 
	 * @param ports 
	 */
	void registerHostPorts(
		final com.soffid.iam.am.api.Host host, 
		final java.util.List<com.soffid.iam.pam.api.HostPort> ports)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation registerHostServices
	 * Internal method to register the account protected services

	 * @param host 
	 * @param dispatcher 
	 * @param services 
	 * @param domainToSystemMap 
	 */
	void registerHostServices(
		final com.soffid.iam.am.api.Host host, 
		final com.soffid.iam.iga.api.System dispatcher, 
		final java.util.List<com.soffid.iam.pam.api.HostService> services, 
		final java.util.Map<java.lang.String,java.lang.String> domainToSystemMap)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation registerHostSystem
	 * Method to register a accounts repository

	 * @param host 
	 * @param system 
	 */
	void registerHostSystem(
		final com.soffid.iam.am.api.Host host, 
		final com.soffid.iam.iga.api.System system)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation removeNetworkAccount
	 * Removes account for network discovery

	 * @param network 
	 * @param account 
	 */
	void removeNetworkAccount(
		final com.soffid.iam.am.api.Network network, 
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation startDiscovery
	 * Internal method to scan a network

	 * @param network 
	 */
	void startDiscovery(
		final com.soffid.iam.am.api.Network network)
			throws com.soffid.iam.exception.InternalErrorException;

}
