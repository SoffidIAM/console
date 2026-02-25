//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * Entity UserDataEntity implementation
 */
public class UserDataEntityImpl extends com.soffid.iam.base.model.UserDataEntity {

	/**
	 * Operation isAllowed
	 * @param permission
	 * @return
	**/
	public boolean isAllowed(
		java.lang.String permission) {
		//TODO: Add custom implementaion
		return true;
	}
	/**
	 * Operation getAttributeVisibility
	 * Gets the visibility level for an attribue
	 * @return
	**/
	public com.soffid.iam.base.api.AttributeVisibilityEnum getAttributeVisibility() {
		//TODO: Add custom implementaion
		return null;
	}
	/**
	 * Operation getObjectValue
	 * @return
	**/
	public java.lang.Object getObjectValue() {
		//TODO: Add custom implementaion
		return null;
	}
	/**
	 * Operation setObjectValue
	 * @param value
	**/
	public void setObjectValue(
		java.lang.Object value) {
		//TODO: Add custom implementaion
	}
}
