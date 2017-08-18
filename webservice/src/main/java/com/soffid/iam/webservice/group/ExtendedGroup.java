package com.soffid.iam.webservice.group;

import com.soffid.iam.api.Group;
import com.soffid.iam.webservice.ScimMeta;

public class ExtendedGroup extends Group {

	private static final long serialVersionUID = 4200340767670387678L;

	ScimMeta meta = new ScimMeta();

	public ExtendedGroup() {
	}

	public ExtendedGroup(Group group) {
		super(group);
	}

	public ScimMeta getMeta() {
		return meta;
	}

	public void setMeta(ScimMeta meta) {
		this.meta = meta;
	}
}
