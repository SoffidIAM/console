//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * Entity RoleEntity implementation
 */
public class RoleEntityImpl extends com.soffid.iam.iga.model.RoleEntity {

	/**
	 * Operation isAllowed
	 * Returns true if the permission on this object is granted
	 * @param permission
	 * @return
	**/
	public boolean isAllowed(
		java.lang.String permission) {
		return true;
	}
	/**
	 * Operation toRoleDescription
	 * @return
	**/
	public java.lang.String toRoleDescription() {
		//TODO: Add custom implementaion
		return null;
	}
	/**
	 * Operation toString
	 * @return
	**/
	public java.lang.String toString() {
		//TODO: Add custom implementaion
		return null;
	}
}
