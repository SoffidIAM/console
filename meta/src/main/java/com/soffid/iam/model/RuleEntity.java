//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_RULE" )
@Depends ({com.soffid.iam.api.Rule.class,
	com.soffid.iam.model.RuleAssignedRoleEntity.class,
	es.caib.seycon.ng.model.RolAccountEntity.class})
public abstract class RuleEntity {

	@Column (name="RUL_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Column (name="RUL_DESCRI", length=150)
	public java.lang.String description;

	@Column (name="RUL_EXPRES")
	public java.lang.String bshExpression;
	
	@Column (name="RUL_TEN_ID")
	public TenantEntity tenant;

	@ForeignKey (foreignColumn="RUR_RUL_ID")
	public java.util.Collection<com.soffid.iam.model.RuleAssignedRoleEntity> roles;

	@ForeignKey (foreignColumn="RLU_RUL_ID")
	public java.util.Collection<es.caib.seycon.ng.model.RolAccountEntity> generated;

	@DaoFinder("select rule \nfrom com.soffid.iam.model.RuleEntity as rule\n"
			+ "where rule.tenant.id=:tenantId and "
			+ "(rule.description like :description or :description is null)")
	public java.util.List<com.soffid.iam.model.RuleEntity> findByDescription(
		@Nullable java.lang.String description) {
	 return null;
	}
}
