//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * Entity UserGroupEntity implementation
 */
public class UserGroupEntityImpl extends com.soffid.iam.iga.model.UserGroupEntity {

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
	 * Operation customCache
	**/
	public void customCache() {
		//TODO: Add custom implementaion
	}
}
