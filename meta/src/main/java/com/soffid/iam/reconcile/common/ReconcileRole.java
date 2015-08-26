//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.reconcile.common;
import com.soffid.mda.annotation.*;

@ValueObject 
public class ReconcileRole {

	@Nullable
	public java.lang.Long id;

	public java.lang.String roleName;

	public java.lang.String description;

	public java.lang.Long processId;

	public com.soffid.iam.reconcile.common.ProposedAction proposedAction;

	public java.lang.String dispatcher;

	@Nullable
	public java.lang.String appName;

}
