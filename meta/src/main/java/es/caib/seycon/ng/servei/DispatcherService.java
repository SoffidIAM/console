//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;
import com.soffid.iam.model.TenantEntity;
import java.util.Map;

import com.soffid.iam.api.ReconcileTrigger;
import com.soffid.iam.model.ReconcileTriggerEntity;
import com.soffid.iam.service.ScheduledTaskService;
import com.soffid.iam.service.TenantService;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.comu.Server;
import es.caib.seycon.ng.comu.Dispatcher;
import es.caib.seycon.ng.comu.ObjectMappingTrigger;
import es.caib.seycon.ng.comu.SoffidObjectType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.model.ObjectMappingTriggerEntity;

import org.springframework.transaction.annotation.Transactional;

import roles.agent_create;
import roles.agent_update;

@Service (translatedName="DispatcherService",
	translatedPackage="com.soffid.iam.service")
@Depends ({es.caib.seycon.ng.model.ControlAccessEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.TasqueEntity.class,
	es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.model.DispatcherEntity.class,
	es.caib.seycon.ng.model.GrupDispatcherEntity.class,
	es.caib.seycon.ng.model.TipusUsuariDispatcherEntity.class,
	es.caib.seycon.ng.model.TipusUsuariEntity.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	es.caib.seycon.ng.servei.AplicacioService.class,
	es.caib.seycon.ng.model.ServerEntity.class,
	es.caib.seycon.ng.servei.ConfiguracioService.class,
	es.caib.seycon.ng.servei.UsuariService.class,
	es.caib.seycon.ng.servei.AccountService.class,
	es.caib.seycon.ng.model.AttributeMappingEntity.class,
	es.caib.seycon.ng.model.AgentDescriptorEntity.class,
	es.caib.seycon.ng.model.ObjectMappingEntity.class,
	ScheduledTaskService.class,
	ObjectMappingTriggerEntity.class,
	es.caib.seycon.ng.model.ObjectMappingPropertyEntity.class,
	SeyconServerService.class,
	AutoritzacioService.class,
	TenantEntity.class,
	ReconcileTriggerEntity.class})
public abstract class DispatcherService {

	@Operation ( grantees={roles.agent_create.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Dispatcher create(
		es.caib.seycon.ng.comu.Dispatcher dispatcher)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.agent_update.class,
			roles.agent_accessControl_set.class,
			roles.agent_accessControl_delete.class,
			roles.agent_accessControl_create.class,
			roles.agent_accessControl_update.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Dispatcher update(
		es.caib.seycon.ng.comu.Dispatcher dispatcher)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.agent_delete.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.Dispatcher dispatcher)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Operation ( grantees={roles.agent_query.class,
			roles.application_update.class},
			translated="findDispatchersByFilter")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Dispatcher> findDispatchersByFiltre(
		@Nullable java.lang.String codi, 
		@Nullable java.lang.String nomCla, 
		@Nullable java.lang.String url, 
		@Nullable java.lang.String basRol, 
		@Nullable java.lang.String segur, 
		@Nullable java.lang.Boolean actiu)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Operation ( grantees={roles.agent_query.class, roles.application_update.class})
	@Description ("Finds the dispatcher bound to soffid itself")
	public es.caib.seycon.ng.comu.Dispatcher findSoffidDispatcher() {
	 return null;
	}

	@Operation ( grantees={roles.agent_query.class},
			translated="findDispatcherByName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Dispatcher findDispatcherByCodi(
		java.lang.String codi)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.agent_accessControl_create.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.ControlAcces create(
		es.caib.seycon.ng.comu.ControlAcces controlAcces)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.agent_delete.class,
			roles.agent_accessControl_delete.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.ControlAcces controlAcces)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.agent_query.class},
			translated="findAccessControlByDispatcherName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.ControlAcces> findControlAccesByCodiAgent(
		java.lang.String codiAgent)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.agent_refreshUsers.class},
			translated="porpagateUsersDispatcher")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void propagateUsuarisDispatcher(
		java.lang.String codiAgent)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.agent_refreshRoles.class},
			translated="propagateDispatcherRoles")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void propagateRolsDispatcher(
		java.lang.String codiAgent)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Operation ( grantees={roles.agent_refreshGroups.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void propagateDispatcherGroups(
		java.lang.String codiAgent)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	
	@Operation ( grantees={roles.agent_accessControl_update.class,
			roles.agent_accessControl_create.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.ControlAcces update(
		es.caib.seycon.ng.comu.ControlAcces controlAcces)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.agent_create.class,roles.agent_update.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.TipusUsuariDispatcher create(
		es.caib.seycon.ng.comu.TipusUsuariDispatcher tipusUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.agent_delete.class,roles.agent_update.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.TipusUsuariDispatcher tipusUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.agent_create.class,roles.agent_update.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.GrupDispatcher create(
		es.caib.seycon.ng.comu.GrupDispatcher grupDispatcher)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.agent_delete.class,roles.agent_update.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.GrupDispatcher grupDispatcher)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.agent_update.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.TipusUsuariDispatcher update(
		es.caib.seycon.ng.comu.TipusUsuariDispatcher tipusUsuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.agent_update.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.GrupDispatcher update(
		es.caib.seycon.ng.comu.GrupDispatcher grupDispatcher)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.agent_query.class},
			translated="getAccessControl")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.ControlAcces> getControlAcces(
		es.caib.seycon.ng.comu.Dispatcher agent)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.agent_query.class},
			translated="getDispatcherGroups")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.GrupDispatcher> getGrupsDispatcher(
		es.caib.seycon.ng.comu.Dispatcher agent)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.agent_query.class},
			translated="getDispatcherUserTypes")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.TipusUsuariDispatcher> getTipusUsuariDispatcher(
		es.caib.seycon.ng.comu.Dispatcher agent)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="isUserAllowed")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean isUserAllowed(
		es.caib.seycon.ng.comu.Dispatcher dispatcher, 
		java.lang.String user)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
	@Operation ( grantees={roles.user_role_create.class,
			roles.agent_query.class},
			translated="findAllActiveDispatchers")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Dispatcher> findAllActiveDispatchers()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="isGroupAllowed")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean isGroupAllowed(
		es.caib.seycon.ng.comu.Dispatcher dispatcher, 
		java.lang.String group)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
	
	@Operation ( grantees={roles.server_query.class},
			translated="findAllServers")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Server> findAllServers()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Operation ( grantees={roles.server_query.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Server> findTenantServers()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Operation ( grantees={roles.server_manage_proxy.class, roles.server_manage_server.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Server update(
		es.caib.seycon.ng.comu.Server server)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.server_manage_proxy.class, roles.server_manage_server.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.Server server)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.server_manage_proxy.class, roles.server_manage_server.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Server create(
		es.caib.seycon.ng.comu.Server server)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.agent_update.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.AttributeMapping create(
		es.caib.seycon.ng.comu.AttributeMapping mapping)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.agent_update.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.AttributeMapping update(
		es.caib.seycon.ng.comu.AttributeMapping mapping)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.agent_update.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.AttributeMapping mapping)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.agent_query.class},
			translated="findAttributeMappingsByObject")
	public java.util.Collection<es.caib.seycon.ng.comu.AttributeMapping> findAttributeMappingsByObject(
		java.lang.Long objectId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	/** Trigger methods **/
	@Operation ( grantees={roles.agent_update.class})
	public ObjectMappingTrigger create(ObjectMappingTrigger trigger)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	@Operation ( grantees={roles.agent_update.class})
	public ObjectMappingTrigger update(ObjectMappingTrigger trigger)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		 return null;
		}

	@Operation ( grantees={roles.agent_update.class})
	public void delete(ObjectMappingTrigger tirger)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		}

	@Operation ( grantees={roles.agent_query.class})
	public java.util.Collection<ObjectMappingTrigger> findObjectMappingTriggersByObject(
		java.lang.Long objectId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	
	@Operation ( grantees={roles.agent_update.class},
			translated="setDefaultMappingsByDispatcher")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void setDefaultMappingsByDispatcher(
		java.lang.Long dispatcherId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.agent_update.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.ObjectMapping create(
		es.caib.seycon.ng.comu.ObjectMapping om)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.agent_update.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.ObjectMapping update(
		es.caib.seycon.ng.comu.ObjectMapping om)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.agent_update.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.ObjectMapping om)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.agent_update.class},
			translated="create")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.ObjectMappingProperty create(
		es.caib.seycon.ng.comu.ObjectMappingProperty omp)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.agent_update.class},
			translated="update")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.ObjectMappingProperty update(
		es.caib.seycon.ng.comu.ObjectMappingProperty om)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.agent_update.class},
			translated="delete")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		es.caib.seycon.ng.comu.ObjectMappingProperty omp)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.agent_query.class},
			translated="findObjectMappingsByDispatcher")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.ObjectMapping> findObjectMappingsByDispatcher(
		java.lang.Long dispatcherId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.agent_query.class},
			translated="findObjectMappingPropertiesByObject")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.ObjectMappingProperty> findObjectMappingPropertiesByObject(
		java.lang.Long objectId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	@Operation ( grantees={roles.service_query.class})
	@Transactional(rollbackFor={java.lang.Exception.class}, readOnly=true)
	public String[] getServerTenants (Server server)
	{
		return null;
	}

	@Operation (grantees={agent_create.class, agent_update.class})
	@Description("Tests an object mapping for a real object")
	public Map<String, Object> testObjectMapping(Map<String,String> sentences, String dispatcher, 
			SoffidObjectType type, String object1, @Nullable String object2) throws InternalErrorException 
	{
		return null;
	}

	@Operation (grantees={agent_create.class, agent_update.class})
	@Description("Tests and applies an object mapping")
	public Exception testPropagateObject(String dispatcher, 
			SoffidObjectType type, String object1, @Nullable String object2) throws InternalErrorException 
	{
		return null;
	}


	@Operation ( grantees={roles.agent_update.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(ReconcileTrigger rp)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	
	@Operation ( grantees={roles.agent_update.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public ReconcileTrigger create(ReconcileTrigger rp)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.agent_update.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public ReconcileTrigger update(ReconcileTrigger rp)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Operation ( grantees={roles.agent_query.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<ReconcileTrigger> findReconcileTriggersByDispatcher(Long dispatcherId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
}
