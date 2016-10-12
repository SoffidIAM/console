package com.soffid.iam.api;

import java.util.Vector;

import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.ValueObject;

import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.AccountAccessLevelEnum;

@ValueObject
public class VaultFolderPermissions {
	Long vaultId;
	
	@Description("List of users / roles / groups")
	Vector<Object> grantee;
	
	@Description ("List of accounts in the folder")
	Vector<VaultFolderAccountPermissions> accounts;
	
}
