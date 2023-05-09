//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import com.soffid.iam.api.Rule;

/**
 * DAO RuleEntity implementation
 */
public class RuleEntityDaoImpl extends RuleEntityDaoBase
{
	@Override
	public void remove(RuleEntity entity) {
		getSession().createQuery("update com.soffid.iam.model.RoleAccountEntity "
				+ "set rule = null "
				+ "where enabled = :false and rule.id=:id")
			.setBoolean("false", false)
			.setLong("id", entity.getId())
			.executeUpdate();
		super.remove(entity);
	}
}
