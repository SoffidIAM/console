package com.soffid.iam.api;

import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class MailListRoleMember {
	@Description ("Name of the role member")
	String roleName;
	
	@Description ("Managed system where role lives on")
	String dispatcherName;
	
	@Nullable
	@Description ("Scope value for scoped roles")
	String scope;
	
	@Nullable
	@Description ("Role description")
	String roleDescription;
}
