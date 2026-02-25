//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity CustomObjectAttributeEntity
 */

public abstract class CustomObjectAttributeEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
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
	 * Attribute customObject
	 */
	private com.soffid.iam.iga.model.CustomObjectEntity customObject;
	/**
	 * Gets value for attribute customObject
	 */
	public com.soffid.iam.iga.model.CustomObjectEntity getCustomObject() {
		return this.customObject;
	}
	/**
	 * Sets value for attribute customObject
	 */
	public void setCustomObject(com.soffid.iam.iga.model.CustomObjectEntity customObject) {
		this.customObject = customObject;
	}
	/**
	 * Attribute metadata
	 */
	private com.soffid.iam.iga.model.MetaDataEntity metadata;
	/**
	 * Gets value for attribute metadata
	 */
	public com.soffid.iam.iga.model.MetaDataEntity getMetadata() {
		return this.metadata;
	}
	/**
	 * Sets value for attribute metadata
	 */
	public void setMetadata(com.soffid.iam.iga.model.MetaDataEntity metadata) {
		this.metadata = metadata;
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
	 * Returns <code>true</code> if the argument is an CustomObjectAttributeEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof CustomObjectAttributeEntity))
		{
			return false;
		}
		final CustomObjectAttributeEntity that = (CustomObjectAttributeEntity)object;
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
