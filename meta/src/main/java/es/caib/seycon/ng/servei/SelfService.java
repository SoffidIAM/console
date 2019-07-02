//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;
import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.soffid.iam.service.EntitlementDelegationService;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.DadaUsuari;
import es.caib.seycon.ng.comu.PuntEntrada;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.model.DadaUsuariEntity;
import es.caib.seycon.ng.model.TipusDadaEntity;
import es.caib.seycon.ng.model.UsuariEntity;

@Service ( grantees={roles.Tothom.class},
		translatedName="SelfService",
		translatedPackage="com.soffid.iam.service")
@Depends ({es.caib.seycon.ng.servei.AccountService.class,
	es.caib.seycon.ng.servei.GrupService.class,
	es.caib.seycon.ng.servei.UsuariService.class,
	es.caib.seycon.ng.servei.AplicacioService.class,
	es.caib.seycon.ng.servei.PuntEntradaService.class,
	es.caib.seycon.ng.servei.XarxaService.class,
	es.caib.seycon.ng.servei.InternalPasswordService.class,
	es.caib.seycon.ng.model.AccountEntity.class,
	es.caib.seycon.ng.servei.DispatcherService.class,
	UsuariEntity.class,
	DadaUsuariEntity.class,
	TipusDadaEntity.class,
	DadesAddicionalsService.class,
	AutoritzacioService.class,
	AuditoriaService.class,
	EntitlementDelegationService.class})
public abstract class SelfService {

	@Operation(translated="getUserAccounts")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.Account> getUserAccounts()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="setAccountPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void setAccountPassword(
		es.caib.seycon.ng.comu.Account account, 
		es.caib.seycon.ng.comu.Password password)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation(translated="setHPAccountPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean setHPAccountPassword(
		es.caib.seycon.ng.comu.Account account, 
		es.caib.seycon.ng.comu.Password password, 
		java.util.Date untilDate, 
		boolean force)
		throws es.caib.seycon.ng.exception.InternalErrorException {
		return false;
	}
	@Operation (translated="getCurrentUser")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Usuari getCurrentUsuari()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation (translated="findUserGroupsByUserName")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.UsuariGrup> findUsuariGrupsByCodiUsuari()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	/** Application entry point methods **/
	
	@Operation(translated="findRoot")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.PuntEntrada findRoot()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Description("Finds entry points by name")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public Collection<PuntEntrada> findEntryPoints(String name)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Operation(translated="findChildren")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.PuntEntrada> findChildren(
		es.caib.seycon.ng.comu.PuntEntrada puntEntrada)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	@Operation ( grantees={roles.user_role_query.class,
			roles.application_query.class},
			translated="findRoleAccounts")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<es.caib.seycon.ng.comu.RolAccount> findRolAccounts()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="queryAccountPassword")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Password queryAccountPassword(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Password queryAccountPasswordBypassPolicy(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="getClientHost")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String getClientHost()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="passwordsStatus")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.EstatContrasenya passwordsStatus(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="queryOtherAffectedAccounts")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String queryOtherAffectedAccounts(
		es.caib.seycon.ng.comu.Account account)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation(translated="getDispatcherInformation")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Dispatcher getDispatcherInformation(
		java.lang.String dispatcherCode)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	public Collection<DadaUsuari> getUserAttributes(){
	 return null;
	}
	
	public DadaUsuari updateUserAttribute (DadaUsuari attribute)
	{return null;}

	public TipusDada getDataTypeDescription (@Nullable String systemName, String attName) { return null; }
	
	@Operation
	@Description("Gets the list of shared accounts granted to the user")
	public List<Account> getSharedAccounts (@Nullable String filter) {
		return null;
	}
	
	@Operation
	@Description("Updates an account, including ACLs if user is owner")
	public Account updateSharedAccount (Account account) {
		return null;
	}

	@Operation
	@Description("Updates an account attribute")
	public DadaUsuari updateSharedAccountData (DadaUsuari data) {
		return null;
	}

	@Operation
	@Description("Created an account attribute")
	public DadaUsuari createSharedAccountData (DadaUsuari data) {
		return null;
	}
	
	@Operation
	@Description("Get account attributes")
	public List<DadaUsuari> getAccountAttributes (Account acc) {
		return null;
	}
}
