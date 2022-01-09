//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.sync.servei;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.soffid.iam.api.AttributeTranslation;
import com.soffid.iam.api.CustomObject;
import com.soffid.iam.authoritative.service.AuthoritativeChangeService;
import com.soffid.iam.service.AttributeTranslationService;
import com.soffid.iam.service.CustomObjectService;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.AgentDescriptor;
import es.caib.seycon.ng.comu.Dispatcher;
import es.caib.seycon.ng.comu.RolAccount;
import es.caib.seycon.ng.comu.Server;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.model.AuditoriaEntity;
import es.caib.seycon.ng.model.ServerCertificateEntity;
import es.caib.seycon.ng.model.ServerEntity;
import es.caib.seycon.ng.model.ServerInstanceEntity;
import es.caib.seycon.ng.model.TasqueEntity;
import es.caib.seycon.ng.servei.PasswordService;
import es.caib.seycon.ng.servei.UsuariService;
import es.caib.seycon.ng.sync.intf.AuthoritativeChange;

import org.springframework.transaction.annotation.Transactional;

@Service ( serverOnly=true,
	 serverPath="/seycon/ServerService",
	 serverRole="agent",
	 translatedName="ServerService",
	 translatedPackage="com.soffid.iam.sync.service")
@Depends ({es.caib.seycon.ng.sync.servei.SecretStoreService.class,
	es.caib.seycon.ng.sync.servei.TaskQueue.class,
	es.caib.seycon.ng.sync.servei.TaskGenerator.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.model.MaquinaEntity.class,
	es.caib.seycon.ng.model.XarxaEntity.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	es.caib.seycon.ng.model.DispatcherEntity.class,
	es.caib.seycon.ng.model.ConfiguracioEntity.class,
	es.caib.seycon.ng.model.ServerPluginEntity.class,
	es.caib.seycon.ng.model.AgentDescriptorEntity.class,
	es.caib.seycon.ng.model.UsuariGrupEntity.class,
	es.caib.seycon.ng.model.RolAssociacioRolEntity.class,
	es.caib.seycon.ng.model.RolAccountEntity.class,
	es.caib.seycon.ng.model.RolsGrupEntity.class,
	es.caib.seycon.ng.model.DadaUsuariEntity.class,
	es.caib.seycon.ng.model.UsuariImpressoraEntity.class,
	es.caib.seycon.ng.model.LlistaCorreuEntity.class,
	es.caib.seycon.ng.model.AutoritzacioRolEntity.class,
	es.caib.seycon.ng.servei.InternalPasswordService.class,
	es.caib.seycon.ng.model.DominiUsuariEntity.class,
	es.caib.seycon.ng.model.DominiContrasenyaEntity.class,
	es.caib.seycon.ng.model.ControlAccessEntity.class,
	es.caib.seycon.ng.model.PuntEntradaEntity.class,
	es.caib.seycon.ng.model.PoliticaContrasenyaEntity.class,
	es.caib.seycon.ng.model.AccountEntity.class,
	es.caib.seycon.ng.servei.AplicacioService.class,
	es.caib.seycon.ng.servei.DispatcherService.class,
	es.caib.seycon.ng.servei.AccountService.class,
	es.caib.seycon.ng.model.UserAccountEntity.class,
	TasqueEntity.class,
	UsuariService.class,
	PasswordService.class,
	CustomObjectService.class,
	ServerEntity.class, ServerCertificateEntity.class,
	ServerInstanceEntity.class,
	AttributeTranslationService.class,
	AuditoriaEntity.class,
	AuthoritativeChangeService.class})
public abstract class ServerService {

	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownUserException"})
	public es.caib.seycon.ng.comu.Usuari getUserInfo(
		java.lang.String account, 
		@Nullable java.lang.String dispatcherId)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownUserException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Usuari> getGroupUsers(
		long groupId, 
		boolean nomesUsuarisActius, 
		@Nullable java.lang.String dispatcherId)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownGroupException {
	 return null;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownRoleException"})
	public java.util.Collection<es.caib.seycon.ng.comu.Account> getRoleAccounts(
		long roleId, 
		@Nullable java.lang.String dispatcherId)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownRoleException {
	 return null;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownRoleException"})
	public java.util.Collection<es.caib.seycon.ng.comu.Account> getRoleActiveAccounts(
		long roleId, 
		@Nullable java.lang.String dispatcher)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownRoleException {
	 return null;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownUserException"})
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> getUserGroups(
		java.lang.String accountName, 
		@Nullable java.lang.String dispatcherId)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownUserException {
	 return null;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownUserException"})
	public java.util.Collection<es.caib.seycon.ng.comu.UsuariGrup> getUserMemberships(
		java.lang.String accountName, 
		@Nullable java.lang.String dispatcherId)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownUserException {
	 return null;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownUserException"})
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> getUserGroupsHierarchy(
		java.lang.String accountName, 
		@Nullable java.lang.String dispatcherId)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownUserException {
	 return null;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownRoleException"})
	public java.util.Collection<es.caib.seycon.ng.comu.RolGrant> getRoleExplicitRoles(
		long roleId)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownRoleException {
	 return null;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownUserException"})
	public java.util.Collection<es.caib.seycon.ng.comu.RolGrant> getUserRoles(
		long userId, 
		@Nullable java.lang.String dispatcherid)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownUserException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.RolGrant> getGroupExplicitRoles(
		long groupId)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownUserException"})
	public java.util.Collection<es.caib.seycon.ng.comu.RolGrant> getUserExplicitRoles(
		long userId, 
		@Nullable java.lang.String dispatcherId)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownUserException {
	 return null;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownUserException"})
	public es.caib.seycon.ng.comu.DadaUsuari getUserData(
		long userId, 
		java.lang.String data)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownUserException {
	 return null;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownUserException"})
	public java.util.Collection<es.caib.seycon.ng.comu.UsuariImpressora> getUserPrinters(
		java.lang.Long userId)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownUserException {
	 return null;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownHostException"})
	public es.caib.seycon.ng.comu.Maquina getHostInfo(
		java.lang.String hostName)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownHostException, es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownHostException"})
	public es.caib.seycon.ng.comu.Maquina getHostInfoByIP(
		java.lang.String ip)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownHostException, es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownNetworkException"})
	public es.caib.seycon.ng.comu.Xarxa getNetworkInfo(
		java.lang.String network)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownNetworkException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> getGroupChildren(
		long groupId, 
		@Nullable java.lang.String dispatcherId)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownRoleException"})
	public es.caib.seycon.ng.comu.Rol getRoleInfo(
		java.lang.String role, 
		java.lang.String bd)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownRoleException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Xarxa> getNetworksList()
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void clientAgentStarted(
		java.lang.String serverName)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException {
	}

	public Collection<Dispatcher> getServices() { return null; }

	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Maquina> getHostsFromNetwork(
		long networkId)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownNetworkException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String getConfig(
		java.lang.String param)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownMailListException"})
	public es.caib.seycon.ng.comu.LlistaCorreu getMailList(
		java.lang.String list, 
		java.lang.String domain)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownMailListException {
	 return null;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownMailListException"})
	public java.util.Collection<java.lang.Object> getMailListMembers(
		java.lang.String mail, 
		java.lang.String domainName)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownMailListException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Dispatcher getDispatcherInfo(
		java.lang.String codi)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownUserException","UnknownHostException"})
	public boolean hasSupportAccessHost(
		long hostId, 
		long userId)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownUserException, es.caib.seycon.ng.exception.UnknownHostException {
	 return false;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public byte[] getPluginJar(
		java.lang.String classname)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Usuari getUserInfo(
		java.security.cert.X509Certificate[] certs)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownUserException","InvalidPasswrodException"})
	public es.caib.seycon.ng.comu.PasswordValidation validatePassword(
		java.lang.String account, 
		@Nullable java.lang.String dispatcherId, 
		es.caib.seycon.ng.comu.Password p)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InvalidPasswordException, es.caib.seycon.ng.exception.UnknownUserException {
	 return null;
	}

	@Transactional(rollbackFor={java.lang.Exception.class})
	public void changePassword(
		java.lang.String account, 
		java.lang.String dispatcherId, 
		es.caib.seycon.ng.comu.Password p, 
		boolean mustChange)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.BadPasswordException, es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Transactional(rollbackFor={java.lang.Exception.class})
	public void changePasswordSync(
		java.lang.String account, 
		java.lang.String dispatcherId, 
		es.caib.seycon.ng.comu.Password p, 
		boolean mustChange)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.BadPasswordException, es.caib.seycon.ng.exception.InternalErrorException {
	}
	
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownUserException"})
	public byte[] getUserMazingerRules(
		long userId, 
		java.lang.String version)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownUserException {
	 return null;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownUserException"})
	public java.util.Collection<es.caib.seycon.ng.comu.sso.Secret> getUserSecrets(
		long userId)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownUserException {
	 return null;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownGroupException"})
	public es.caib.seycon.ng.comu.Grup getGroupInfo(
		java.lang.String codi, 
		@Nullable java.lang.String dispatcherId)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownGroupException, es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownUserException"})
	public java.util.Collection<es.caib.seycon.ng.comu.DadaUsuari> getUserData(
		long userId)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownUserException {
	 return null;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"UnknownUserException"})
	public java.util.Map<String,Object> getUserAttributes(
		long userId)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.UnknownUserException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void cancelTask(
		long taskid, String taskHash)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Password generateFakePassword(
		java.lang.String account, 
		java.lang.String dispatcherId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.DispatcherAccessControl getDispatcherAccessControl(
		java.lang.Long dispatcherId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Password generateFakePassword(
		java.lang.String passwordDomain)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.PoliticaContrasenya getUserPolicy(
		java.lang.String account, 
		java.lang.String dispatcherId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Password getAccountPassword(
		java.lang.String account, 
		java.lang.String dispatcher)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Password getOrGenerateUserPassword(
		java.lang.String account, 
		java.lang.String dispatcherId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.RolGrant> getAccountRoles(
		java.lang.String account, 
		java.lang.String dispatcherId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.RolGrant> getAccountExplicitRoles(
		java.lang.String account, 
		java.lang.String dispatcherId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.UserAccount> getUserAccounts(
		long userId, 
		@Nullable java.lang.String dispatcherId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	@Description ("Retrieves an account from soffid database")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Account getAccountInfo(
		String accountName, 
		java.lang.String dispatcherId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Usuari getUserInfo(
		long userId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String getDefaultDispatcher()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> getUserGroups(
		java.lang.Long userId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> getUserGroupsHierarchy(
		java.lang.Long userId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.sync.agent.Plugin getPlugin(
		java.lang.String className)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Map propagateOBUser(
		es.caib.seycon.ng.comu.Usuari usuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean updateExpiredPasswords(
		es.caib.seycon.ng.comu.Usuari usuari, 
		boolean externalAuth)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.DominiContrasenya> getExpiredPasswordDomains(
		es.caib.seycon.ng.comu.Usuari usuari)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Properties getMyConfig()
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.ServerRedirectException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void getMainJar()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<java.lang.String> getAddonList()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public byte[] getAddonJar(
		java.lang.String addon)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	public CustomObject getCustomObject(String type, String name) { return null;}
	
	////// ** Attribute translation services **
	public String translate (String domain, String column1) { return null ; }
	public String reverseTranslate (String domain, String column2) { return null; }
	public Collection<AttributeTranslation> translate2 (String domain, String column1) { return null ; }
	public Collection<AttributeTranslation> reverseTranslate2 (String domain, String column2) { return null ; }
	
	/** Direct agent execution **/
	@Transactional(noRollbackFor={java.lang.Exception.class})
	Collection<Map<String,Object>> invoke(String agent, @Nullable String verb, @Nullable String command, @Nullable Map<String, Object> params) throws InternalErrorException {return null;}
	
	/** Syncserver gateway support **/
	Server findRemoteServerByUrl(String url) { return null;}
	
	/** Life updates support **/
	void processAuthoritativeChange (AuthoritativeChange change, boolean remove) {	}
	void reconcileAccount (String system, String account) 	{	}
	
	@Description("Parses a kerberos token.")
    public Account parseKerberosToken (String domain, String serviceName, byte keytab[], byte token[] ) throws InternalErrorException {return null;}

	@Description("Updates Soffid image of the reconciled account. Only triggers a UpdateUser for user accounts with new or removed roles.")
	public void reconcileAccount(Account account, @Nullable List<RolAccount> grants) {};
	
	public void addCertificate(X509Certificate cert) {};

}
