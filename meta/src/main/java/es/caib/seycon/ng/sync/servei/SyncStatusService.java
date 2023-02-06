//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.sync.servei;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.sync.engine.intf.DebugTaskResults;
import com.soffid.iam.sync.engine.intf.GetObjectResults;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.comu.PasswordValidation;
import es.caib.seycon.ng.comu.SoffidObjectType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.DispatcherService;
import roles.agent_create;
import roles.agent_update;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service ( serverOnly=true,
	 serverPath="SEU/SyncStatusService",
	 serverRole="SEU_CONSOLE",
	 translatedName="SyncStatusService",
	 translatedPackage="com.soffid.iam.sync.service")
@Depends ({es.caib.seycon.ng.sync.servei.TaskGenerator.class,
	es.caib.seycon.ng.sync.servei.TaskQueue.class,
	es.caib.seycon.ng.sync.servei.SecretStoreService.class,
	es.caib.seycon.ng.servei.AccountService.class,
	es.caib.seycon.ng.servei.InternalPasswordService.class,
	es.caib.seycon.ng.model.DispatcherEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.AccountEntity.class,
	DispatcherService.class,
	es.caib.seycon.ng.sync.servei.ServerService.class})
public abstract class SyncStatusService {

	@Transactional(rollbackFor={java.lang.Exception.class})
	@Operation(translated="getSyncAgentsInfo")
	public java.util.Collection<es.caib.seycon.ng.comu.AgentStatusInfo> getSeyconAgentsInfo(String tenant)
		throws es.caib.seycon.ng.exception.InternalErrorException, java.io.IOException, es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	@Operation(translated="getSyncServerStatus")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.SeyconServerInfo getSeyconServerStatus(String tenant)
		throws es.caib.seycon.ng.exception.InternalErrorException, java.io.IOException, es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Operation(translated="getSyncServerInfo")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.SeyconServerInfo getSeyconServerInfo(String tenant)
		throws es.caib.seycon.ng.exception.InternalErrorException, java.io.IOException, es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String getDBConnectionStatus()
		throws es.caib.seycon.ng.exception.InternalErrorException, java.io.IOException, es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.SeyconServerInfo> getServerAgentHostsURL()
		throws es.caib.seycon.ng.exception.InternalErrorException, java.io.FileNotFoundException, java.io.IOException, es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String resetAllServer()
		throws es.caib.seycon.ng.exception.InternalErrorException, java.io.IOException, es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String resetServerAgents(
		java.lang.String server)
		throws es.caib.seycon.ng.exception.InternalErrorException, java.io.IOException, es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Password getAccountPassword(
		java.lang.String user, 
		java.lang.Long accountId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Description("Method user for SSO. Provides the password if the user has the right access level on the account")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Password getAccountPassword(
		java.lang.String user, 
		java.lang.Long accountId,
		AccountAccessLevelEnum level)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Password getAccountSshKey(
		java.lang.String user, 
		java.lang.Long accountId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Description("Method user for SSO. Provides the password if the user has the right access level on the account")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Password getAccountSshKey(
		java.lang.String user, 
		java.lang.Long accountId,
		AccountAccessLevelEnum level)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public byte[] getMazingerRules(
		java.lang.String user)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void reconfigureDispatchers()
		throws es.caib.seycon.ng.exception.InternalErrorException, java.io.IOException, es.caib.seycon.ng.exception.InternalErrorException {
	}
	
	// Testar un mapper
	@Transactional(rollbackFor={java.lang.Exception.class}, propagation=Propagation.SUPPORTS)
	public Map<String, Object> testObjectMapping(Map<String,String> sentences, String dispatcher, 
			SoffidObjectType type, String object1, @Nullable String object2) throws InternalErrorException 
	{
		return null;
	}

	// Testar un mapper
	@Transactional(rollbackFor={java.lang.Exception.class})
	public DebugTaskResults testPropagateObject(String dispatcher, 
			SoffidObjectType type, String object1, @Nullable String object2) throws InternalErrorException 
	{
		return null;
	}

	@Description("Invokes a custom method")
	public Collection<Map<String,Object>> invoke(String dispatcher, String verb,
			@Nullable String object, @Nullable Map<String,Object> attributes) throws InternalErrorException {
		return null;
	}

	public void cancelTask (long taskId)
	{
		
	}
	
	public void boostTask (long taskId)
	{
		
	}
	
	public void startScheduledTask (ScheduledTask t)
	{
		
	}
	
	public GetObjectResults getNativeObject (String dispatcher, 
			SoffidObjectType type, String object1, @Nullable String object2)
	{ return null; }

	public GetObjectResults getSoffidObject (String dispatcher, 
			SoffidObjectType type, String object1, @Nullable String object2)
	{ return null; }

	public void checkConnectivity (String dispatcher) { }


	public GetObjectResults reconcile (String dispatcher, String accountName)
	{ return null; }

	@Operation(grantees = { roles.base_log_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public String[] tailServerLog(java.lang.String urlServer)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Transactional(rollbackFor = { java.lang.Exception.class })
	public PasswordValidation checkPasswordSynchronizationStatus(java.lang.String accountName, String serverName)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void setAccountPassword(java.lang.String accountName, String serverName, Password password, boolean mustChange)
			throws es.caib.seycon.ng.exception.InternalErrorException {	}

	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void setAccountSshPrivateKey(java.lang.String accountName, String serverName, Password privateKey)
			throws es.caib.seycon.ng.exception.InternalErrorException {	}
}
