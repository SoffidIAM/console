package com.soffid.iam.model;

import java.util.LinkedList;

import com.soffid.iam.api.CustomObjectType;

import es.caib.seycon.ng.comu.AccountAccessLevelEnum;

public class CustomObjectTypeEntityDaoImpl extends CustomObjectTypeEntityDaoBase {

	@Override
	public void toCustomObjectType(CustomObjectTypeEntity source, CustomObjectType target) {
		super.toCustomObjectType(source, target);
		target.setManagerRoles(new LinkedList<>());
		target.setUserRoles(new LinkedList<>());
		if (source.getPublicAccess() == null)
			target.setPublicAccess(true);
		for (CustomObjectRoleEntity access: source.getAccessRoles()) {
			if (access.getLevel() == AccountAccessLevelEnum.ACCESS_MANAGER)
				target.getManagerRoles().add(access.getRole().getName()+"@"+access.getRole().getSystem().getName());
			if (access.getLevel() == AccountAccessLevelEnum.ACCESS_USER)
				target.getUserRoles().add(access.getRole().getName()+"@"+access.getRole().getSystem().getName());
		}
	}

}
