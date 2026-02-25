//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * Entity UserPrinterEntity implementation
 */
public class UserPrinterEntityImpl extends com.soffid.iam.iga.model.UserPrinterEntity {

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
