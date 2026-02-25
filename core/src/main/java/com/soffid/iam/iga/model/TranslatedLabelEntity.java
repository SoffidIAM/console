//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity TranslatedLabelEntity
 */

public abstract class TranslatedLabelEntity {

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
	 * Attribute language
	 */
	private java.lang.String language;
	/**
	 * Gets value for attribute language
	 */
	public java.lang.String getLanguage() {
		return this.language;
	}
	/**
	 * Sets value for attribute language
	 */
	public void setLanguage(java.lang.String language) {
		this.language = language;
	}
	/**
	 * Attribute label
	 */
	private java.lang.String label;
	/**
	 * Gets value for attribute label
	 */
	public java.lang.String getLabel() {
		return this.label;
	}
	/**
	 * Sets value for attribute label
	 */
	public void setLabel(java.lang.String label) {
		this.label = label;
	}
	/**
	 * Attribute customObjectType
	 */
	private com.soffid.iam.iga.model.CustomObjectTypeEntity customObjectType;
	/**
	 * Gets value for attribute customObjectType
	 */
	public com.soffid.iam.iga.model.CustomObjectTypeEntity getCustomObjectType() {
		return this.customObjectType;
	}
	/**
	 * Sets value for attribute customObjectType
	 */
	public void setCustomObjectType(com.soffid.iam.iga.model.CustomObjectTypeEntity customObjectType) {
		this.customObjectType = customObjectType;
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
	 * Attribute accountMetadata
	 */
	private com.soffid.iam.base.model.AccountMetadataEntity accountMetadata;
	/**
	 * Gets value for attribute accountMetadata
	 */
	public com.soffid.iam.base.model.AccountMetadataEntity getAccountMetadata() {
		return this.accountMetadata;
	}
	/**
	 * Sets value for attribute accountMetadata
	 */
	public void setAccountMetadata(com.soffid.iam.base.model.AccountMetadataEntity accountMetadata) {
		this.accountMetadata = accountMetadata;
	}
	/**
	 * Attribute tenant
	 */
	private com.soffid.iam.base.model.TenantEntity tenant;
	/**
	 * Gets value for attribute tenant
	 */
	public com.soffid.iam.base.model.TenantEntity getTenant() {
		return this.tenant;
	}
	/**
	 * Sets value for attribute tenant
	 */
	public void setTenant(com.soffid.iam.base.model.TenantEntity tenant) {
		this.tenant = tenant;
	}
	/**
	 * Returns <code>true</code> if the argument is an TranslatedLabelEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof TranslatedLabelEntity))
		{
			return false;
		}
		final TranslatedLabelEntity that = (TranslatedLabelEntity)object;
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
