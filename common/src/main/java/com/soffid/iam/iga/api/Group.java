package com.soffid.iam.iga.api;

import java.util.Date;
import java.util.Map;

public class Group extends AbstractGroup {
	private static final long serialVersionUID = 1;

	public Group() {
		super();
	}

	public Group(AbstractGroup otherBean) {
		super(otherBean);
	}

	public Group(String name, String description) {
		super(name, description);
	}
}
