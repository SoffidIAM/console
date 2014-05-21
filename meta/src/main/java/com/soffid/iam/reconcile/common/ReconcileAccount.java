//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.reconcile.common;
import com.soffid.mda.annotation.*;

@ValueObject 
public abstract class ReconcileAccount {

	@Nullable
	public java.lang.Long id;

	public java.lang.String accountName;

	public java.lang.String description;

	public java.lang.Long processId;

	public com.soffid.iam.reconcile.common.AccountProposedAction proposedAction;

	public java.lang.String dispatcher;

	@Nullable
	public java.lang.String primaryGroup;

	@Nullable
	public java.lang.String userCode;

	@Nullable
	public java.lang.String userType;

	@Nullable
	public java.lang.String userFullName;

	@Nullable
	public es.caib.seycon.ng.comu.AccountType accountType;

	@Attribute(defaultValue = "true")
	public boolean active;

}
