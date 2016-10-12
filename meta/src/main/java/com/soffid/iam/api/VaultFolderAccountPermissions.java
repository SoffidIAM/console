package com.soffid.iam.api;

import java.util.Vector;

import com.soffid.mda.annotation.ValueObject;

import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.AccountAccessLevelEnum;

@ValueObject
public class VaultFolderAccountPermissions {
	Account account;
	
	Vector<AccountAccessLevelEnum> permissions;
}
