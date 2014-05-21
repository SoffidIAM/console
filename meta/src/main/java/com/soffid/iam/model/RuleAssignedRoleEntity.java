//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_RULROL" )
@Depends ({com.soffid.iam.api.RuleAssignedRole.class,
	com.soffid.iam.model.RuleEntity.class,
	es.caib.seycon.ng.model.RolEntity.class})
public abstract class RuleAssignedRoleEntity {

	@Column (name="RUR_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Column (name="RUR_DOMEXP")
	@Nullable
	public java.lang.String bshDomainValueExpression;

	@Column (name="RUR_RUL_ID")
	public com.soffid.iam.model.RuleEntity rule;

	@Column (name="RUR_ROL_ID")
	public es.caib.seycon.ng.model.RolEntity role;

	@Column (name="RUR_DOMVAL")
	@Nullable
	public java.lang.String domainValue;

}
