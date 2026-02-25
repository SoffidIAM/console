//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * Entity UserEntity implementation
 */
public class UserEntityImpl extends com.soffid.iam.base.model.UserEntity {

	/**
	 * Operation isAllowed
	 * Returns true if the permission on this object is granted
	 * @param permission
	 * @return
	**/
	public boolean isAllowed(
		java.lang.String permission) {
		//TODO: Add custom implementaion
		return true;
	}
	/**
	 * Operation getUserData
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.base.model.UserDataEntity> getUserData() {
		//TODO: Add custom implementaion
		return null;
	}
}
