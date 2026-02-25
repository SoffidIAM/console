//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * Entity GroupEntity implementation
 */
public class GroupEntityImpl extends com.soffid.iam.iga.model.GroupEntity {

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
	 * Operation toString
	 * @return
	**/
	public java.lang.String toString() {
		//TODO: Add custom implementaion
		return null;
	}
	/**
	 * Operation customCache
	**/
	public void customCache() {
		//TODO: Add custom implementaion
	}
}
