//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * Entity CustomObjectTypeEntity implementation
 */
public class CustomObjectTypeEntityImpl extends com.soffid.iam.iga.model.CustomObjectTypeEntity {

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
