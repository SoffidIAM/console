package com.soffid.iam.api;

import com.soffid.iam.model.VaultFolderEntity;
import com.soffid.mda.annotation.Attribute;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.JsonObject;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.AccountAccessLevelEnum;

@ValueObject
public class VaultElement
{
	@Nullable
	Long id;
	
	@Nullable
	Long parentId;

	@Description("type can be account or folder")
	String type; 

	@Description("Account")
	@Nullable
	Account account;
	
	@Description("Falder")
	@Nullable
	VaultFolder folder;
}
