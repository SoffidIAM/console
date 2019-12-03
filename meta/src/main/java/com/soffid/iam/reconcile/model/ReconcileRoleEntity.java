//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.reconcile.model;
import com.soffid.iam.model.TenantEntity;
import com.soffid.mda.annotation.*;

@Entity (table="SC_RECROL" )
@Depends ({com.soffid.iam.reconcile.common.ReconcileRole.class})
public abstract class ReconcileRoleEntity {

	@Column (name="RRL_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Column (name="RRL_ROLNAM")
	public java.lang.String roleName;

	@Column (name="RRL_DESC")
	public java.lang.String description;

	@Column (name="RRL_PROCID")
	public java.lang.Long processId;

	@Column (name="RRL_ACTION")
	public com.soffid.iam.reconcile.common.ProposedAction proposedAction;

	@Column (name="RRL_DISPAT")
	public java.lang.String dispatcher;

	@Column (name="RRL_APPNAM")
	@Nullable
	public java.lang.String appName;

	@Column (name="RRL_TEN_ID")
	public TenantEntity tenant;

	@DaoFinder
	public java.util.List<com.soffid.iam.reconcile.model.ReconcileRoleEntity> findByProcessId(
		java.lang.Long processId) {
	 return null;
	}
}
