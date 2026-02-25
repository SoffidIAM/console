//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
/**
 * Service DispatcherService
 */
public interface DispatcherService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.iga.service.DispatcherService";

	public final static String SERVICE_NAME = "com.soffid.iam.iga.service.DispatcherService";

	/**
	 * Operation createRemoteServer

	 * @param name 
	 * @param tenant 
	 * @return 
	 */
	java.lang.String createRemoteServer(
		final java.lang.String name, 
		final java.lang.String tenant)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findActiveDirectoryDomains

	 * @return 
	 */
	java.util.Map<java.lang.String,java.lang.String> findActiveDirectoryDomains()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation startVirtualSourceTransaction

	 * @param readonly 
	 * @param server 
	 * @return 
	 */
	java.lang.String startVirtualSourceTransaction(
		final boolean readonly, 
		final java.lang.String server)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation finishVirtualSourceTransaction

	 * @param transactionId 
	 */
	void finishVirtualSourceTransaction(
		final java.lang.String transactionId)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isGroupAllowed

	 * @param dispatcher 
	 * @param group 
	 * @return 
	 */
	boolean isGroupAllowed(
		final com.soffid.iam.iga.api.System dispatcher, 
		final java.lang.String group)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isRegistrationTokenAlive

	 * @param token 
	 * @return 
	 */
	boolean isRegistrationTokenAlive(
		final java.lang.String token)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isUserAllowed

	 * @param dispatcher 
	 * @param user 
	 * @return 
	 */
	boolean isUserAllowed(
		final com.soffid.iam.iga.api.System dispatcher, 
		final java.lang.String user)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isUserAllowed

	 * @param dispatcher 
	 * @param user 
	 * @param permissions 
	 * @return 
	 */
	boolean isUserAllowed(
		final com.soffid.iam.iga.api.System dispatcher, 
		final java.lang.String user, 
		final java.util.Collection<com.soffid.iam.iga.api.RoleGrant> permissions)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation applyConfigurationAsync

	 * @param dispatcher 
	 * @return 
	 */
	com.soffid.iam.base.api.AsyncProcessTracker applyConfigurationAsync(
		final com.soffid.iam.iga.api.System dispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation queryProcessStatus
	 * Query the apply process status

	 * @param process 
	 * @return 
	 */
	com.soffid.iam.base.api.AsyncProcessTracker queryProcessStatus(
		final com.soffid.iam.base.api.AsyncProcessTracker process)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param controlAcces 
	 * @return 
	 */
	com.soffid.iam.iga.api.AccessControl create(
		final com.soffid.iam.iga.api.AccessControl controlAcces)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param controlAcces 
	 * @return 
	 */
	com.soffid.iam.iga.api.AccessControl update(
		final com.soffid.iam.iga.api.AccessControl controlAcces)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param mapping 
	 * @return 
	 */
	com.soffid.iam.iga.api.AttributeMapping create(
		final com.soffid.iam.iga.api.AttributeMapping mapping)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param mapping 
	 * @return 
	 */
	com.soffid.iam.iga.api.AttributeMapping update(
		final com.soffid.iam.iga.api.AttributeMapping mapping)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param om 
	 * @return 
	 */
	com.soffid.iam.iga.api.ObjectMapping create(
		final com.soffid.iam.iga.api.ObjectMapping om)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param om 
	 * @return 
	 */
	com.soffid.iam.iga.api.ObjectMapping update(
		final com.soffid.iam.iga.api.ObjectMapping om)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param omp 
	 * @return 
	 */
	com.soffid.iam.iga.api.ObjectMappingProperty create(
		final com.soffid.iam.iga.api.ObjectMappingProperty omp)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param om 
	 * @return 
	 */
	com.soffid.iam.iga.api.ObjectMappingProperty update(
		final com.soffid.iam.iga.api.ObjectMappingProperty om)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param trigger 
	 * @return 
	 */
	com.soffid.iam.iga.api.ObjectMappingTrigger create(
		final com.soffid.iam.iga.api.ObjectMappingTrigger trigger)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param trigger 
	 * @return 
	 */
	com.soffid.iam.iga.api.ObjectMappingTrigger update(
		final com.soffid.iam.iga.api.ObjectMappingTrigger trigger)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param rp 
	 * @return 
	 */
	com.soffid.iam.iga.api.ReconcileTrigger create(
		final com.soffid.iam.iga.api.ReconcileTrigger rp)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param rp 
	 * @return 
	 */
	com.soffid.iam.iga.api.ReconcileTrigger update(
		final com.soffid.iam.iga.api.ReconcileTrigger rp)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param dispatcher 
	 * @return 
	 */
	com.soffid.iam.iga.api.System create(
		final com.soffid.iam.iga.api.System dispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findDispatcherByName

	 * @param codi 
	 * @return 
	 */
	com.soffid.iam.iga.api.System findDispatcherByName(
		final java.lang.String codi)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findSoffidDispatcher
	 * Finds the dispatcher bound to soffid itself

	 * @return 
	 */
	com.soffid.iam.iga.api.System findSoffidDispatcher()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param dispatcher 
	 * @return 
	 */
	com.soffid.iam.iga.api.System update(
		final com.soffid.iam.iga.api.System dispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param grupDispatcher 
	 * @return 
	 */
	com.soffid.iam.iga.api.SystemGroup create(
		final com.soffid.iam.iga.api.SystemGroup grupDispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param grupDispatcher 
	 * @return 
	 */
	com.soffid.iam.iga.api.SystemGroup update(
		final com.soffid.iam.iga.api.SystemGroup grupDispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param tipusUsuari 
	 * @return 
	 */
	com.soffid.iam.iga.api.UserTypeDispatcher create(
		final com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param tipusUsuari 
	 * @return 
	 */
	com.soffid.iam.iga.api.UserTypeDispatcher update(
		final com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation testPropagateObject
	 * Tests and applies an object mapping

	 * @param dispatcher 
	 * @param type 
	 * @param object1 
	 * @param object2 
	 * @return 
	 */
	com.soffid.iam.sync.api.DebugTaskResults testPropagateObject(
		final java.lang.String dispatcher, 
		final com.soffid.iam.iga.api.SoffidObjectType type, 
		final java.lang.String object1, 
		final java.lang.String object2)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getNativeObject
	 * Loads system object attributes

	 * @param dispatcher 
	 * @param type 
	 * @param object1 
	 * @param object2 
	 * @return 
	 */
	com.soffid.iam.sync.api.GetObjectResults getNativeObject(
		final java.lang.String dispatcher, 
		final com.soffid.iam.iga.api.SoffidObjectType type, 
		final java.lang.String object1, 
		final java.lang.String object2)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getSoffidObject
	 * Loads system object and transforms into Soffid Object

	 * @param dispatcher 
	 * @param type 
	 * @param object1 
	 * @param object2 
	 * @return 
	 */
	com.soffid.iam.sync.api.GetObjectResults getSoffidObject(
		final java.lang.String dispatcher, 
		final com.soffid.iam.iga.api.SoffidObjectType type, 
		final java.lang.String object1, 
		final java.lang.String object2)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation reconcile
	 * Loads account into soffid database

	 * @param dispatcher 
	 * @param accountName 
	 * @return 
	 */
	com.soffid.iam.sync.api.GetObjectResults reconcile(
		final java.lang.String dispatcher, 
		final java.lang.String accountName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param server 
	 * @return 
	 */
	com.soffid.iam.sync.api.Server create(
		final com.soffid.iam.sync.api.Server server)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param server 
	 * @return 
	 */
	com.soffid.iam.sync.api.Server update(
		final com.soffid.iam.sync.api.Server server)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation consumeRegistrationToken

	 * @param token 
	 * @return 
	 */
	com.soffid.iam.sync.api.ServerRegistrationToken consumeRegistrationToken(
		final java.lang.String token)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findSystems

	 * @param q 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.System> findSystems(
		final com.soffid.zkdb.api.Query q)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation invokeAsync
	 * Invokes a custom method

	 * @param dispatcher 
	 * @param verb 
	 * @param object 
	 * @param attributes 
	 * @return 
	 */
	java.lang.Long invokeAsync(
		final java.lang.String dispatcher, 
		final java.lang.String verb, 
		final java.lang.String object, 
		final java.util.Map<java.lang.String,java.lang.Object> attributes)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation generateChangesReport

	 * @param dispatcher 
	 * @return 
	 */
	java.lang.String generateChangesReport(
		final com.soffid.iam.iga.api.System dispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation preRegisterServer
	 * Pre-registers a new sync-server and returns the registration token

	 * @param register 
	 * @return 
	 */
	java.lang.String preRegisterServer(
		final com.soffid.iam.sync.api.ServerRegistrationToken register)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getServerTenants

	 * @param server 
	 * @return 
	 */
	java.lang.String[] getServerTenants(
		final com.soffid.iam.sync.api.Server server)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAllActiveDispatchers

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.System> findAllActiveDispatchers()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAllServers

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.sync.api.Server> findAllServers()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAttributeMappingsByObject

	 * @param objectId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.AttributeMapping> findAttributeMappingsByObject(
		final java.lang.Long objectId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAccessControlByDispatcherName

	 * @param codiAgent 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.AccessControl> findAccessControlByDispatcherName(
		final java.lang.String codiAgent)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findObjectMappingPropertiesByObject

	 * @param objectId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.ObjectMappingProperty> findObjectMappingPropertiesByObject(
		final java.lang.Long objectId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findObjectMappingTriggersByObject

	 * @param objectId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.ObjectMappingTrigger> findObjectMappingTriggersByObject(
		final java.lang.Long objectId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findObjectMappingsByDispatcher

	 * @param dispatcherId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.ObjectMapping> findObjectMappingsByDispatcher(
		final java.lang.Long dispatcherId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findReconcileTriggersByDispatcher

	 * @param dispatcherId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.ReconcileTrigger> findReconcileTriggersByDispatcher(
		final java.lang.Long dispatcherId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findScheduledTasksByDispatcher

	 * @param dispatcher 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.sync.api.ScheduledTask> findScheduledTasksByDispatcher(
		final com.soffid.iam.iga.api.System dispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findTenantServers

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.sync.api.Server> findTenantServers()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAccessControl

	 * @param agent 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.AccessControl> getAccessControl(
		final com.soffid.iam.iga.api.System agent)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getDispatcherGroups

	 * @param agent 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.SystemGroup> getDispatcherGroups(
		final com.soffid.iam.iga.api.System agent)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getDispatcherUserTypes

	 * @param agent 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.UserTypeDispatcher> getDispatcherUserTypes(
		final com.soffid.iam.iga.api.System agent)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation invoke
	 * Invokes a custom method

	 * @param dispatcher 
	 * @param verb 
	 * @param object 
	 * @param attributes 
	 * @return 
	 */
	java.util.Collection<java.util.Map<java.lang.String,java.lang.Object>> invoke(
		final java.lang.String dispatcher, 
		final java.lang.String verb, 
		final java.lang.String object, 
		final java.util.Map<java.lang.String,java.lang.Object> attributes)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation assignTemporaryPermissions

	 * @param host 
	 * @param accountName 
	 * @param accountSystem 
	 * @param permissions 
	 * @return 
	 */
	java.util.List<java.lang.String> assignTemporaryPermissions(
		final java.lang.String host, 
		final java.lang.String accountName, 
		final java.lang.String accountSystem, 
		final java.util.List<java.lang.String> permissions)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findValidCertificates

	 * @return 
	 */
	java.util.List<java.security.cert.X509Certificate> findValidCertificates()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation testObjectMapping
	 * Tests an object mapping for a real object

	 * @param sentences 
	 * @param dispatcher 
	 * @param type 
	 * @param object1 
	 * @param object2 
	 * @return 
	 */
	java.util.Map<java.lang.String,java.lang.Object> testObjectMapping(
		final java.util.Map<java.lang.String,java.lang.String> sentences, 
		final java.lang.String dispatcher, 
		final com.soffid.iam.iga.api.SoffidObjectType type, 
		final java.lang.String object1, 
		final java.lang.String object2)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation addCertificate

	 * @param server 
	 * @param cert 
	 */
	void addCertificate(
		final com.soffid.iam.sync.api.Server server, 
		final java.security.cert.X509Certificate cert)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation applyConfiguration

	 * @param dispatcher 
	 */
	void applyConfiguration(
		final com.soffid.iam.iga.api.System dispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation checkConnectivity
	 * Tests system connectivity

	 * @param dispatcher 
	 */
	void checkConnectivity(
		final java.lang.String dispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param controlAcces 
	 */
	void delete(
		final com.soffid.iam.iga.api.AccessControl controlAcces)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param mapping 
	 */
	void delete(
		final com.soffid.iam.iga.api.AttributeMapping mapping)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param om 
	 */
	void delete(
		final com.soffid.iam.iga.api.ObjectMapping om)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param omp 
	 */
	void delete(
		final com.soffid.iam.iga.api.ObjectMappingProperty omp)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param tirger 
	 */
	void delete(
		final com.soffid.iam.iga.api.ObjectMappingTrigger tirger)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param rp 
	 */
	void delete(
		final com.soffid.iam.iga.api.ReconcileTrigger rp)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param dispatcher 
	 */
	void delete(
		final com.soffid.iam.iga.api.System dispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param grupDispatcher 
	 */
	void delete(
		final com.soffid.iam.iga.api.SystemGroup grupDispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param tipusUsuari 
	 */
	void delete(
		final com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param server 
	 */
	void delete(
		final com.soffid.iam.sync.api.Server server)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation propagateGroupsForSystem

	 * @param codiAgent 
	 * @param task 
	 */
	void propagateGroupsForSystem(
		final java.lang.String codiAgent, 
		final com.soffid.iam.sync.api.ScheduledTask task)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation propagateRolesForSystem

	 * @param codiAgent 
	 * @param task 
	 */
	void propagateRolesForSystem(
		final java.lang.String codiAgent, 
		final com.soffid.iam.sync.api.ScheduledTask task)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation propagateUsersForSystem

	 * @param codiAgent 
	 * @param task 
	 */
	void propagateUsersForSystem(
		final java.lang.String codiAgent, 
		final com.soffid.iam.sync.api.ScheduledTask task)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation removeTemporaryPermissions

	 * @param host 
	 * @param accountName 
	 * @param accountSystem 
	 * @param permissions 
	 */
	void removeTemporaryPermissions(
		final java.lang.String host, 
		final java.lang.String accountName, 
		final java.lang.String accountSystem, 
		final java.util.List<java.lang.String> permissions)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation renameAccounts

	 * @param dispatcher 
	 */
	void renameAccounts(
		final com.soffid.iam.iga.api.System dispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation setDefaultMappingsByDispatcher

	 * @param dispatcherId 
	 */
	void setDefaultMappingsByDispatcher(
		final java.lang.Long dispatcherId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
