package com.soffid.iam.api;

import java.util.Map;

import es.caib.seycon.ng.comu.Aplicacio;

public class Group extends AbstractGroup {

	public Group() {
		super();
	}

	public Group(AbstractGroup otherBean) {
		super(otherBean);
	}

	public Group(String name, String description, String quota, String driveLetter, String parentGroup, String type,
			String driveServerName, Long id, Boolean obsolete, Boolean organizational, String section,
			Map<String, Object> attributes) {
		super(name, description, quota, driveLetter, parentGroup, type, driveServerName, id, obsolete, organizational, section,
				attributes);
	}

	public Group(String name, String description) {
		super(name, description);
	}
}
