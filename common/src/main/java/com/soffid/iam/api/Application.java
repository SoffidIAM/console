package com.soffid.iam.api;

import java.util.Map;

public class Application extends AbstractApplication {

	public Application() {
		super();
	}

	public Application(AbstractApplication otherBean) {
		super(otherBean);
	}

	public Application(String name, String relativeName, String description) {
		super(name, relativeName, description);
	}

	@Override
	public void setParent(String parent) {
		if (parent == null ?  getParent() != null: ! parent.equals(getParent())) {
			super.setParent(parent);
			super.setName(parent == null? getRelativeName(): parent+"/"+getRelativeName());
		}
	}

	@Override
	public void setRelativeName(String relativeName) {
		if (relativeName == null ?  getRelativeName() != null: ! relativeName.equals(getRelativeName())) {
			super.setRelativeName(relativeName);
			super.setName(getParent() == null || getParent().trim().isEmpty()? getRelativeName(): getParent()+"/"+getRelativeName());
		}
	}

	@Override
	public void setName(String name) {
		if (name == null ?  getName() != null: ! name.equals(getName())) {
			super.setName(name);
			int i = name.lastIndexOf('/');
			if (i < 0) {
				super.setParent(null);
				super.setRelativeName(name);
			} else {
				super.setParent(name.substring(0, i));
				super.setRelativeName(name.substring(i+1));
			}
		}
	}


}
