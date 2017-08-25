package com.soffid.iam.service;

import java.util.Date;
import java.util.List;

import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.comu.RolAccount;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.AuditoriaEntity;
import es.caib.seycon.ng.model.DispatcherEntity;
import es.caib.seycon.ng.model.RolAccountEntity;
import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.servei.AccountService;
import es.caib.seycon.ng.servei.AplicacioService;
import roles.Tothom;

@Service(grantees={Tothom.class})
@Depends({
	AplicacioService.class, 
	AccountService.class,
	AccountEntity.class,
	UsuariEntity.class,
	DispatcherEntity.class,
	RolAccountEntity.class,
	AuditoriaEntity.class,
	RolEntity.class
})
public class EntitlementDelegationService {
	@Description("Gets the list of accounts that can be delegated for a user")
	public List<String> findAccountsToDelegate (RolAccount rolAccount, String user) { return null ;}
	
	@Description("Delegates an entitilement")
	public RolAccount delegate (RolAccount rolAccount, String user, @Nullable String account, Date since, @Nullable Date until) {return null;}

	@Description("Revokes an entitilement")
	public RolAccount cancelDelegation (RolAccount rolAccount) {return null;}

	@Description("Gets list of entitlements delegated to current user")
	public List<RolAccount> findDelegationsToAccept () {return null;}

	@Description("Gets list of entitlements delegated by current user")
	public List<RolAccount> findActiveDelegations () {return null;}

	@Description("Accepts a delegation not accepted yet")
	public RolAccount acceptDelegation (RolAccount ra) {return null;}

	@Description("Revernts expired delegation back to the caller")
	public void revertExpiredDelegations () {return ;}

}
