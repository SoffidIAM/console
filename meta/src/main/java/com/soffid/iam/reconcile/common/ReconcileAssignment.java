//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.reconcile.common;
import com.soffid.mda.annotation.*;

@ValueObject 
public abstract class ReconcileAssignment {

	@Nullable
	public java.lang.Long id;

	public java.lang.String assignmentName;

	public java.lang.Long processId;

	public java.lang.String accountName;

	public java.lang.String roleName;

	public com.soffid.iam.reconcile.common.ProposedAction proposedAction;

	public java.lang.String dispatcher;

}
