//
// (C) 2013 Soffid
//
//

package es.caib.seycon.ng.model;

import es.caib.seycon.ng.comu.SoDRole;

/**
 * DAO SoDRoleEntity implementation
 */
public class SoDRoleEntityDaoImpl extends SoDRoleEntityDaoBase
{

	@Override
	public void toSoDRole (SoDRoleEntity source, SoDRole target)
	{
		super.toSoDRole(source, target);
		target.setRole( getRolEntityDao().toRol(source.getRole()));
		target.setRuleId(source.getRule().getId());
	}

	@Override
	public void soDRoleToEntity (SoDRole source, SoDRoleEntity target, boolean copyIfNull)
	{
		super.soDRoleToEntity(source, target, copyIfNull);
		target.setRole( getRolEntityDao().load(source.getRole().getId()));
		target.setRule(getSoDRuleEntityDao().load(source.getRuleId()));
	}
}
