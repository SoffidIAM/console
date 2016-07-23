package com.soffid.iam.utils;

import java.security.Permission;

public class NestedLoginPermission extends Permission {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NestedLoginPermission(String name) {
		super(name);
	}

	@Override
	public boolean implies(Permission permission) {
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof NestedLoginPermission);
	}

	@Override
	public int hashCode() {
		return getName().hashCode();
	}

	@Override
	public String getActions() {
		return "";
	}

}
