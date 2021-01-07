package com.soffid.iam.api;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class Role extends AbstractRole {

	public Role() {
		super();
	}

	public Role(AbstractRole otherBean) {
		super(otherBean);
	}

	public Role(String name, String description, String system, String category, Boolean enableByDefault,
			Boolean password, String informationSystemName, Long id, String domain, Collection<RoleGrant> ownerRoles,
			Collection<Group> ownerGroups, Collection<RoleGrant> granteeGroups, Collection<RoleGrant> ownedRoles,
			Boolean bpmEnabled, Date approvalStart, Date approvalEnd, Map<String, Object> attributes) {
		super(name, description, system, category, enableByDefault, password, informationSystemName, id, domain, ownerRoles,
				ownerGroups, granteeGroups, ownedRoles, bpmEnabled, approvalStart, approvalEnd, attributes);
	}

	public Role(String name, String description, String system, String informationSystemName) {
		super(name, description, system, informationSystemName);
	}

}
