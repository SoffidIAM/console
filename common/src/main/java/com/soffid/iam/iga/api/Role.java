package com.soffid.iam.iga.api;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class Role extends AbstractRole {

	@Override
	public String getKey() {
		return getName() == null ? null: getName()+"@"+getSystem();
	}

	@Override
	public void setKey(String key) {
		super.setKey(key);
		if (key == null || key.isBlank())
			return;
		int i = key.lastIndexOf("@");
		if (i > 0) {
			setName (key.substring(0,i));
			setSystem(key.substring(i+1));
		} else {
			setName(key);
			setSystem(null);
		}
	}

	public Role() {
		super();
	}

	public Role(AbstractRole otherBean) {
		super(otherBean);
	}

}
