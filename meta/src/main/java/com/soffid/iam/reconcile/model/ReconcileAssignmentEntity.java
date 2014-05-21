//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.reconcile.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_RECASI" )
@Depends ({com.soffid.iam.reconcile.common.ReconcileAssignment.class})
public abstract class ReconcileAssignmentEntity {

	@Column (name="RAS_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Column (name="RAS_ASINAM")
	public java.lang.String assignmentName;

	@Column (name="RAS_PROCID")
	public java.lang.Long processId;

	@Column (name="RAS_ACCNAM")
	public java.lang.String accountName;

	@Column (name="RAS_ROLNAM")
	public java.lang.String roleName;

	@Column (name="RAS_ACTION")
	public com.soffid.iam.reconcile.common.ProposedAction proposedAction;

	@Column (name="RAS_DISPAT")
	public java.lang.String dispatcher;

	@DaoFinder
	public java.util.List<com.soffid.iam.reconcile.model.ReconcileAssignmentEntity> findByProcessId(
		java.lang.Long processId) {
	 return null;
	}
}
