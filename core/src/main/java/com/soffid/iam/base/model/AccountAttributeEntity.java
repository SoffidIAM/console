//
// (c) 2014 Soffid
//
//

package com.soffid.iam.base.model;

/**
 *  Entity AccountAttributeEntity
 */

public abstract class AccountAttributeEntity {

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
	 * Attribute account
	 */
	private com.soffid.iam.base.model.AccountEntity account;
	/**
	 * Gets value for attribute account
	 */
	public com.soffid.iam.base.model.AccountEntity getAccount() {
		return this.account;
	}
	/**
	 * Sets value for attribute account
	 */
	public void setAccount(com.soffid.iam.base.model.AccountEntity account) {
		this.account = account;
	}
	/**
	 * Attribute systemMetadata
	 */
	private com.soffid.iam.base.model.AccountMetadataEntity systemMetadata;
	/**
	 * Gets value for attribute systemMetadata
	 */
	public com.soffid.iam.base.model.AccountMetadataEntity getSystemMetadata() {
		return this.systemMetadata;
	}
	/**
	 * Sets value for attribute systemMetadata
	 */
	public void setSystemMetadata(com.soffid.iam.base.model.AccountMetadataEntity systemMetadata) {
		this.systemMetadata = systemMetadata;
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
	 * Returns <code>true</code> if the argument is an AccountAttributeEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof AccountAttributeEntity))
		{
			return false;
		}
		final AccountAttributeEntity that = (AccountAttributeEntity)object;
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
