//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.sync.servei;
import java.util.Map;

import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.comu.SoffidObjectType;
import es.caib.seycon.ng.exception.InternalErrorException;

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
	public Exception testPropagateObject(String dispatcher, 
			SoffidObjectType type, String object1, @Nullable String object2) throws InternalErrorException 
	{
		return null;
	}

	public void cancelTask (long taskId)
	{
		
	}
	
	public void boostTask (long taskId)
	{
		
	}
}
