//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * Entity SessionEntity implementation
 */
public class SessionEntityImpl extends com.soffid.iam.am.model.SessionEntity {

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
}
