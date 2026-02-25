//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * Entity AccessLogEntity implementation
 */
public class AccessLogEntityImpl extends com.soffid.iam.am.model.AccessLogEntity {

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
