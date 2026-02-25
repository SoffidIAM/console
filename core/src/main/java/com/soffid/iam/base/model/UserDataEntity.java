//
// (c) 2014 Soffid
//
//

package com.soffid.iam.base.model;

/**
 *  Entity UserDataEntity
 */

public abstract class UserDataEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute value
	 */
	private java.lang.String value;
	/**
	 * Gets value for attribute value
	 */
	public java.lang.String getValue() {
		return this.value;
	}
	/**
	 * Sets value for attribute value
	 */
	public void setValue(java.lang.String value) {
		this.value = value;
	}
	/**
	 * Attribute user
	 */
	private com.soffid.iam.base.model.UserEntity user;
	/**
	 * Gets value for attribute user
	 */
	public com.soffid.iam.base.model.UserEntity getUser() {
		return this.user;
	}
	/**
	 * Sets value for attribute user
	 */
	public void setUser(com.soffid.iam.base.model.UserEntity user) {
		this.user = user;
	}
	/**
	 * Attribute dataType
	 */
	private com.soffid.iam.iga.model.MetaDataEntity dataType;
	/**
	 * Gets value for attribute dataType
	 */
	public com.soffid.iam.iga.model.MetaDataEntity getDataType() {
		return this.dataType;
	}
	/**
	 * Sets value for attribute dataType
	 */
	public void setDataType(com.soffid.iam.iga.model.MetaDataEntity dataType) {
		this.dataType = dataType;
	}
	/**
	 * Attribute id
	 */
	private java.lang.Long id;
	/**
	 * Gets value for attribute id
	 */
	public java.lang.Long getId() {
		return this.id;
	}
	/**
	 * Sets value for attribute id
	 */
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	/**
	 * Attribute blobDataValue
	 */
	private java.sql.Blob blobDataValue;
	/**
	 * Gets value for attribute blobDataValue
	 */
	public java.sql.Blob getBlobDataValue() {
		return this.blobDataValue;
	}
	/**
	 * Sets value for attribute blobDataValue
	 */
	public void setBlobDataValue(java.sql.Blob blobDataValue) {
		this.blobDataValue = blobDataValue;
	}
	/**
	 * Operation isAllowed
	 * @param permission
	 * @return
	**/
	 public abstract boolean isAllowed(
		java.lang.String permission);

	/**
	 * Operation getAttributeVisibility
	 * Gets the visibility level for an attribue
	 * @return
	**/
	 public abstract com.soffid.iam.base.api.AttributeVisibilityEnum getAttributeVisibility();

	/**
	 * Operation getObjectValue
	 * @return
	**/
	 public abstract java.lang.Object getObjectValue();

	/**
	 * Operation setObjectValue
	 * @param value
	**/
	 public abstract void setObjectValue(
		java.lang.Object value);

	/**
	 * Returns <code>true</code> if the argument is an UserDataEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof UserDataEntity))
		{
			return false;
		}
		final UserDataEntity that = (UserDataEntity)object;
		if (this.id == null || that.getId() == null || !this.id.equals(that.getId())) 
		{
			return false;
		}
		return true;
	}
	/**
	 * Returns a hash code based on this entity's identifiers.
	 */
	public int hashCode()
	{
		int hashCode = (id == null ? super.hashCode() : id.hashCode());
		return hashCode;
	}
}
