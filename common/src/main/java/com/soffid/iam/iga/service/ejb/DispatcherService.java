//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * EJB DispatcherService
 */
public interface DispatcherService

 {

	java.lang.String startVirtualSourceTransaction(
		final boolean readonly, 
		final java.lang.String server)
	throws com.soffid.iam.exception.InternalErrorException;

	void finishVirtualSourceTransaction(
		final java.lang.String transactionId)
	throws com.soffid.iam.exception.InternalErrorException;

	boolean isRegistrationTokenAlive(
		final java.lang.String token)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.AsyncProcessTracker applyConfigurationAsync(
		final com.soffid.iam.iga.api.System dispatcher)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.AsyncProcessTracker queryProcessStatus(
		final com.soffid.iam.base.api.AsyncProcessTracker process)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.AccessControl create(
		final com.soffid.iam.iga.api.AccessControl controlAcces)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.AccessControl update(
		final com.soffid.iam.iga.api.AccessControl controlAcces)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.AttributeMapping create(
		final com.soffid.iam.iga.api.AttributeMapping mapping)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.AttributeMapping update(
		final com.soffid.iam.iga.api.AttributeMapping mapping)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.ObjectMapping create(
		final com.soffid.iam.iga.api.ObjectMapping om)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.ObjectMapping update(
		final com.soffid.iam.iga.api.ObjectMapping om)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.ObjectMappingProperty create(
		final com.soffid.iam.iga.api.ObjectMappingProperty omp)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.ObjectMappingProperty update(
		final com.soffid.iam.iga.api.ObjectMappingProperty om)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.ObjectMappingTrigger create(
		final com.soffid.iam.iga.api.ObjectMappingTrigger trigger)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.ObjectMappingTrigger update(
		final com.soffid.iam.iga.api.ObjectMappingTrigger trigger)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.ReconcileTrigger create(
		final com.soffid.iam.iga.api.ReconcileTrigger rp)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.ReconcileTrigger update(
		final com.soffid.iam.iga.api.ReconcileTrigger rp)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.System create(
		final com.soffid.iam.iga.api.System dispatcher)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.System findDispatcherByName(
		final java.lang.String codi)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.System findSoffidDispatcher()
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.System update(
		final com.soffid.iam.iga.api.System dispatcher)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.SystemGroup create(
		final com.soffid.iam.iga.api.SystemGroup grupDispatcher)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.SystemGroup update(
		final com.soffid.iam.iga.api.SystemGroup grupDispatcher)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.UserTypeDispatcher create(
		final com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.UserTypeDispatcher update(
		final com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.sync.api.DebugTaskResults testPropagateObject(
		final java.lang.String dispatcher, 
		final com.soffid.iam.iga.api.SoffidObjectType type, 
		final java.lang.String object1, 
		final java.lang.String object2)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.sync.api.GetObjectResults getNativeObject(
		final java.lang.String dispatcher, 
		final com.soffid.iam.iga.api.SoffidObjectType type, 
		final java.lang.String object1, 
		final java.lang.String object2)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.sync.api.GetObjectResults getSoffidObject(
		final java.lang.String dispatcher, 
		final com.soffid.iam.iga.api.SoffidObjectType type, 
		final java.lang.String object1, 
		final java.lang.String object2)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.sync.api.GetObjectResults reconcile(
		final java.lang.String dispatcher, 
		final java.lang.String accountName)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.sync.api.Server create(
		final com.soffid.iam.sync.api.Server server)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.sync.api.Server update(
		final com.soffid.iam.sync.api.Server server)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.System> findSystems(
		final com.soffid.zkdb.api.Query q)
	throws com.soffid.iam.exception.InternalErrorException;

	java.lang.Long invokeAsync(
		final java.lang.String dispatcher, 
		final java.lang.String verb, 
		final java.lang.String object, 
		final java.util.Map<java.lang.String,java.lang.Object> attributes)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String generateChangesReport(
		final com.soffid.iam.iga.api.System dispatcher)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String preRegisterServer(
		final com.soffid.iam.sync.api.ServerRegistrationToken register)
	throws com.soffid.iam.exception.InternalErrorException;

	java.lang.String[] getServerTenants(
		final com.soffid.iam.sync.api.Server server)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.System> findAllActiveDispatchers()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.sync.api.Server> findAllServers()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.AttributeMapping> findAttributeMappingsByObject(
		final java.lang.Long objectId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.AccessControl> findAccessControlByDispatcherName(
		final java.lang.String codiAgent)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.ObjectMappingProperty> findObjectMappingPropertiesByObject(
		final java.lang.Long objectId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.ObjectMappingTrigger> findObjectMappingTriggersByObject(
		final java.lang.Long objectId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.ObjectMapping> findObjectMappingsByDispatcher(
		final java.lang.Long dispatcherId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.ReconcileTrigger> findReconcileTriggersByDispatcher(
		final java.lang.Long dispatcherId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.sync.api.ScheduledTask> findScheduledTasksByDispatcher(
		final com.soffid.iam.iga.api.System dispatcher)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.sync.api.Server> findTenantServers()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.AccessControl> getAccessControl(
		final com.soffid.iam.iga.api.System agent)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.SystemGroup> getDispatcherGroups(
		final com.soffid.iam.iga.api.System agent)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.UserTypeDispatcher> getDispatcherUserTypes(
		final com.soffid.iam.iga.api.System agent)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<java.util.Map<java.lang.String,java.lang.Object>> invoke(
		final java.lang.String dispatcher, 
		final java.lang.String verb, 
		final java.lang.String object, 
		final java.util.Map<java.lang.String,java.lang.Object> attributes)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Map<java.lang.String,java.lang.Object> testObjectMapping(
		final java.util.Map<java.lang.String,java.lang.String> sentences, 
		final java.lang.String dispatcher, 
		final com.soffid.iam.iga.api.SoffidObjectType type, 
		final java.lang.String object1, 
		final java.lang.String object2)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void applyConfiguration(
		final com.soffid.iam.iga.api.System dispatcher)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void checkConnectivity(
		final java.lang.String dispatcher)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.AccessControl controlAcces)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.AttributeMapping mapping)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.ObjectMapping om)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.ObjectMappingProperty omp)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.ObjectMappingTrigger tirger)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.ReconcileTrigger rp)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.System dispatcher)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.SystemGroup grupDispatcher)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.sync.api.Server server)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void propagateGroupsForSystem(
		final java.lang.String codiAgent, 
		final com.soffid.iam.sync.api.ScheduledTask task)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void propagateRolesForSystem(
		final java.lang.String codiAgent, 
		final com.soffid.iam.sync.api.ScheduledTask task)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void propagateUsersForSystem(
		final java.lang.String codiAgent, 
		final com.soffid.iam.sync.api.ScheduledTask task)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void renameAccounts(
		final com.soffid.iam.iga.api.System dispatcher)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void setDefaultMappingsByDispatcher(
		final java.lang.Long dispatcherId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
