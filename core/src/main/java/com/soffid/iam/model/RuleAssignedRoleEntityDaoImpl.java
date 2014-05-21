//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import com.soffid.iam.api.RuleAssignedRole;

/**
 * DAO RuleAssignedRoleEntity implementation
 */
public class RuleAssignedRoleEntityDaoImpl extends RuleAssignedRoleEntityDaoBase
{

	@Override
	public void toRuleAssignedRole (RuleAssignedRoleEntity source,
					RuleAssignedRole target)
	{
		super.toRuleAssignedRole(source, target);
		// Missing attribute roleId on entity
		if (source.getRole() == null)
			target.setRoleId(null);
		else
			target.setRoleId(source.getRole().getId());
		if (source.getRule() == null)
			target.setRuleId(null);
		else
			target.setRuleId(source.getRule().getId());
	}

	@Override
	public void ruleAssignedRoleToEntity (RuleAssignedRole source,
					RuleAssignedRoleEntity target, boolean copyIfNull)
	{
		if (source.getRoleId() == null)
			target.setRole(null);
		else
			target.setRole(getRolEntityDao().load(source.getRoleId()));
		
		if (source.getRuleId() == null)
			target.setRule(null);
		else
			target.setRule(getRuleEntityDao().load(source.getRuleId()));

		super.ruleAssignedRoleToEntity(source, target, copyIfNull);
	}
}
