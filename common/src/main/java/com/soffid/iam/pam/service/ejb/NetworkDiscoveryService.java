//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.service.ejb;
/**
 * EJB NetworkDiscoveryService
 */
public interface NetworkDiscoveryService

 {

	com.soffid.iam.iga.api.System createSystemCandidate(
		final com.soffid.iam.am.api.Host host, 
		final java.lang.String type, 
		final java.lang.String userName, 
		final com.soffid.iam.am.api.Password password, 
		final java.lang.String instance)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.pam.api.HostService createHostService(
		final com.soffid.iam.pam.api.HostService service)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.pam.api.HostService updateHostService(
		final com.soffid.iam.pam.api.HostService service)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.sync.api.ScheduledTask findNetworkDiscoveryScheduledTask(
		final com.soffid.iam.am.api.Network network)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.am.api.AccessTree> findHostEntryPoints(
		final com.soffid.iam.am.api.Host host)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.pam.api.HostPort> findHostPorts(
		final com.soffid.iam.am.api.Host host)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.pam.api.HostService> findHostServices(
		final com.soffid.iam.am.api.Host host)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.iga.api.System> findHostSystems(
		final com.soffid.iam.am.api.Host host)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.base.api.Account> findNetworkAccount(
		final com.soffid.iam.am.api.Network network)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.am.api.Host> findSystemHosts(
		final com.soffid.iam.iga.api.System system)
	throws com.soffid.iam.exception.InternalErrorException;

	void createNetworkAccount(
		final com.soffid.iam.am.api.Network network, 
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException;

	void deleteHostService(
		final com.soffid.iam.pam.api.HostService service)
	throws com.soffid.iam.exception.InternalErrorException;

	void disconnectSystemFromHost(
		final com.soffid.iam.am.api.Host host, 
		final com.soffid.iam.iga.api.System system)
	throws com.soffid.iam.exception.InternalErrorException;

	void enableNetworkDiscoveryScheduledTask(
		final com.soffid.iam.am.api.Network network)
	throws com.soffid.iam.exception.InternalErrorException;

	void registerHostEntryPoint(
		final com.soffid.iam.am.api.Host host, 
		final com.soffid.iam.am.api.AccessTree entryPoint)
	throws com.soffid.iam.exception.InternalErrorException;

	void registerHostSystem(
		final com.soffid.iam.am.api.Host host, 
		final com.soffid.iam.iga.api.System system)
	throws com.soffid.iam.exception.InternalErrorException;

	void removeNetworkAccount(
		final com.soffid.iam.am.api.Network network, 
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException;

	void startDiscovery(
		final com.soffid.iam.am.api.Network network)
	throws com.soffid.iam.exception.InternalErrorException;

}
