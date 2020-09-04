package com.soffid.iam.service;

import java.util.List;

import roles.Tothom;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.VaultElement;
import com.soffid.iam.api.VaultFolder;
import com.soffid.iam.api.VaultFolderPermissions;
import com.soffid.iam.model.VaultFolderAccessEntity;
import com.soffid.iam.model.VaultFolderEntity;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.model.AccountAccessEntity;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.model.TasqueEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.servei.AccountService;
import es.caib.seycon.ng.servei.UsuariService;

@Service
@Depends ({
	AccountEntity.class,
	AccountAccessEntity.class,
	VaultFolderEntity.class,
	VaultFolderAccessEntity.class,
	AccountService.class,
	UsuariEntity.class,
	RolEntity.class,
	GrupEntity.class,
	TasqueEntity.class,
	AsyncRunnerService.class,
	UsuariService.class
})
public class VaultService {
	@Operation(grantees={Tothom.class})
	public VaultFolder getPersonalFolder () {return null;}
	
	@Operation(grantees={Tothom.class})
	public List<VaultFolder> getRootFolders () {return null;}
	
	@Operation(grantees={Tothom.class})
	public List<VaultFolder> getPublicRootFolders () {return null;}
	
	@Operation(grantees={Tothom.class})
	public List<VaultFolder> getChildren (VaultFolder parent)  {return null;}

	@Operation(grantees={Tothom.class})
	public VaultFolder create (VaultFolder folder) { return null; }

	@Operation(grantees={Tothom.class})
	public VaultFolder update (VaultFolder folder) { return null; }

	@Operation(grantees={Tothom.class})
	public VaultFolder findFolder (long id) { return null; }

	@Operation(grantees={Tothom.class})
	public void remove (VaultFolder folder) { return; }

	@Operation(grantees={Tothom.class})
	public List<Account> list (VaultFolder folder) { return null; }
	
	public Account addToFolder (Account account) {return null;}

	@Operation(grantees={Tothom.class})
	public VaultFolderPermissions getFolderPermissions (VaultFolder folder) {return null;}

	@Operation(grantees={Tothom.class})
	public void applyFolderPermissions (VaultFolderPermissions permissions){}

	@Operation(grantees={Tothom.class})
	public List<VaultFolder> findFolders (@Nullable String filter){ return null;}

	@Operation(grantees={Tothom.class})
	public List<Account> findAccounts (@Nullable String filter){ return null; }

	@Operation(grantees={Tothom.class})
	public List<VaultElement> findVaultElementByText (@Nullable String filter){ return null;}

	@Operation(grantees = { Tothom.class })
	public java.util.List<VaultFolder> findFolderByTextAndJsonQuery(
			@Nullable String text,
			@Nullable String jsonQuery,
			@Nullable Integer start, @Nullable Integer pageSize) {
		return null;
	}

	@Operation(grantees = { roles.network_all_query.class })
	public AsyncList<VaultFolder> findFolderByTextAndJsonQueryAsync(
			@Nullable String text,
			@Nullable String jsonQuery) {
		return null;
	}

	@Operation(grantees={Tothom.class})
	public VaultElement create (VaultElement folder) { return null; }

	@Operation(grantees={Tothom.class})
	public VaultElement update (VaultElement folder) { return null; }

	@Operation(grantees={Tothom.class})
	public void remove (VaultElement folder) { return; }

	@Operation(grantees={Tothom.class})
	public VaultElement findVaultElement (long id) { return null; }

	@Operation(grantees={Tothom.class})
	public List<VaultElement> getChildren (VaultElement parent)  {return null;}

}
