//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity CustomObjectTypeEntity
 */

public abstract class CustomObjectTypeEntity {

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
	 * Attribute name
	 */
	private java.lang.String name;
	/**
	 * Gets value for attribute name
	 */
	public java.lang.String getName() {
		return this.name;
	}
	/**
	 * Sets value for attribute name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}
	/**
	 * Attribute description
	 */
	private java.lang.String description;
	/**
	 * Gets value for attribute description
	 */
	public java.lang.String getDescription() {
		return this.description;
	}
	/**
	 * Sets value for attribute description
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	/**
	 * Attribute scope
	 */
	private com.soffid.iam.base.api.MetadataScope scope;
	/**
	 * Gets value for attribute scope
	 */
	public com.soffid.iam.base.api.MetadataScope getScope() {
		return this.scope;
	}
	/**
	 * Sets value for attribute scope
	 */
	public void setScope(com.soffid.iam.base.api.MetadataScope scope) {
		this.scope = scope;
	}
	/**
	 * Attribute builtin
	 */
	private boolean builtin;
	/**
	 * Gets value for attribute builtin
	 */
	public boolean isBuiltin() {
		return this.builtin;
	}
	/**
	 * Sets value for attribute builtin
	 */
	public void setBuiltin(boolean builtin) {
		this.builtin = builtin;
	}
	/**
	 * Attribute textIndex
	 */
	private boolean textIndex;
	/**
	 * Gets value for attribute textIndex
	 */
	public boolean isTextIndex() {
		return this.textIndex;
	}
	/**
	 * Sets value for attribute textIndex
	 */
	public void setTextIndex(boolean textIndex) {
		this.textIndex = textIndex;
	}
	/**
	 * Attribute extensibleObjectClass
	 */
	private java.lang.String extensibleObjectClass;
	/**
	 * Gets value for attribute extensibleObjectClass
	 */
	public java.lang.String getExtensibleObjectClass() {
		return this.extensibleObjectClass;
	}
	/**
	 * Sets value for attribute extensibleObjectClass
	 */
	public void setExtensibleObjectClass(java.lang.String extensibleObjectClass) {
		this.extensibleObjectClass = extensibleObjectClass;
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
	 * Attribute publicAccess
	 */
	private java.lang.Boolean publicAccess = false;
	/**
	 * Gets value for attribute publicAccess
	 */
	public java.lang.Boolean getPublicAccess() {
		return this.publicAccess;
	}
	/**
	 * Sets value for attribute publicAccess
	 */
	public void setPublicAccess(java.lang.Boolean publicAccess) {
		this.publicAccess = publicAccess;
	}
	/**
	 * Attribute refererncedBy

	 */
	private java.util.Collection<com.soffid.iam.base.model.AccountMetadataEntity> refererncedBy =  new java.util.HashSet<com.soffid.iam.base.model.AccountMetadataEntity>();
	/**
	 * Gets value for attribute refererncedBy
	 */
	public java.util.Collection<com.soffid.iam.base.model.AccountMetadataEntity> getRefererncedBy() {
		return this.refererncedBy;
	}
	/**
	 * Sets value for attribute refererncedBy
	 */
	public void setRefererncedBy(java.util.Collection<com.soffid.iam.base.model.AccountMetadataEntity> refererncedBy) {
		this.refererncedBy = refererncedBy;
	}
	/**
	 * Attribute objects

	 */
	private java.util.Collection<com.soffid.iam.iga.model.CustomObjectEntity> objects =  new java.util.HashSet<com.soffid.iam.iga.model.CustomObjectEntity>();
	/**
	 * Gets value for attribute objects
	 */
	public java.util.Collection<com.soffid.iam.iga.model.CustomObjectEntity> getObjects() {
		return this.objects;
	}
	/**
	 * Sets value for attribute objects
	 */
	public void setObjects(java.util.Collection<com.soffid.iam.iga.model.CustomObjectEntity> objects) {
		this.objects = objects;
	}
	/**
	 * Attribute accessRoles

	 */
	private java.util.Collection<com.soffid.iam.iga.model.CustomObjectRoleEntity> accessRoles =  new java.util.HashSet<com.soffid.iam.iga.model.CustomObjectRoleEntity>();
	/**
	 * Gets value for attribute accessRoles
	 */
	public java.util.Collection<com.soffid.iam.iga.model.CustomObjectRoleEntity> getAccessRoles() {
		return this.accessRoles;
	}
	/**
	 * Sets value for attribute accessRoles
	 */
	public void setAccessRoles(java.util.Collection<com.soffid.iam.iga.model.CustomObjectRoleEntity> accessRoles) {
		this.accessRoles = accessRoles;
	}
	/**
	 * Attribute attributes

	 */
	private java.util.Collection<com.soffid.iam.iga.model.MetaDataEntity> attributes =  new java.util.HashSet<com.soffid.iam.iga.model.MetaDataEntity>();
	/**
	 * Gets value for attribute attributes
	 */
	public java.util.Collection<com.soffid.iam.iga.model.MetaDataEntity> getAttributes() {
		return this.attributes;
	}
	/**
	 * Sets value for attribute attributes
	 */
	public void setAttributes(java.util.Collection<com.soffid.iam.iga.model.MetaDataEntity> attributes) {
		this.attributes = attributes;
	}
	/**
	 * Attribute referencedBy

	 */
	private java.util.Collection<com.soffid.iam.iga.model.MetaDataEntity> referencedBy =  new java.util.HashSet<com.soffid.iam.iga.model.MetaDataEntity>();
	/**
	 * Gets value for attribute referencedBy
	 */
	public java.util.Collection<com.soffid.iam.iga.model.MetaDataEntity> getReferencedBy() {
		return this.referencedBy;
	}
	/**
	 * Sets value for attribute referencedBy
	 */
	public void setReferencedBy(java.util.Collection<com.soffid.iam.iga.model.MetaDataEntity> referencedBy) {
		this.referencedBy = referencedBy;
	}
	/**
	 * Attribute mappings

	 */
	private java.util.Collection<com.soffid.iam.iga.model.ObjectMappingEntity> mappings =  new java.util.HashSet<com.soffid.iam.iga.model.ObjectMappingEntity>();
	/**
	 * Gets value for attribute mappings
	 */
	public java.util.Collection<com.soffid.iam.iga.model.ObjectMappingEntity> getMappings() {
		return this.mappings;
	}
	/**
	 * Sets value for attribute mappings
	 */
	public void setMappings(java.util.Collection<com.soffid.iam.iga.model.ObjectMappingEntity> mappings) {
		this.mappings = mappings;
	}
	/**
	 * Attribute translations

	 */
	private java.util.Collection<com.soffid.iam.iga.model.TranslatedLabelEntity> translations =  new java.util.HashSet<com.soffid.iam.iga.model.TranslatedLabelEntity>();
	/**
	 * Gets value for attribute translations
	 */
	public java.util.Collection<com.soffid.iam.iga.model.TranslatedLabelEntity> getTranslations() {
		return this.translations;
	}
	/**
	 * Sets value for attribute translations
	 */
	public void setTranslations(java.util.Collection<com.soffid.iam.iga.model.TranslatedLabelEntity> translations) {
		this.translations = translations;
	}
	/**
	 * Attribute createdOn

	 */
	private java.util.Date createdOn;
	/**
	 * Gets value for attribute createdOn
	 */
	public java.util.Date getCreatedOn() {
		return this.createdOn;
	}
	/**
	 * Sets value for attribute createdOn
	 */
	public void setCreatedOn(java.util.Date createdOn) {
		this.createdOn = createdOn;
	}
	/**
	 * Attribute createdBy

	 */
	private java.lang.String createdBy;
	/**
	 * Gets value for attribute createdBy
	 */
	public java.lang.String getCreatedBy() {
		return this.createdBy;
	}
	/**
	 * Sets value for attribute createdBy
	 */
	public void setCreatedBy(java.lang.String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * Attribute updatedOn

	 */
	private java.util.Date updatedOn;
	/**
	 * Gets value for attribute updatedOn
	 */
	public java.util.Date getUpdatedOn() {
		return this.updatedOn;
	}
	/**
	 * Sets value for attribute updatedOn
	 */
	public void setUpdatedOn(java.util.Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	/**
	 * Attribute updatedBy

	 */
	private java.lang.String updatedBy;
	/**
	 * Gets value for attribute updatedBy
	 */
	public java.lang.String getUpdatedBy() {
		return this.updatedBy;
	}
	/**
	 * Sets value for attribute updatedBy
	 */
	public void setUpdatedBy(java.lang.String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * Attribute deletedOn

	 */
	private java.util.Date deletedOn;
	/**
	 * Gets value for attribute deletedOn
	 */
	public java.util.Date getDeletedOn() {
		return this.deletedOn;
	}
	/**
	 * Sets value for attribute deletedOn
	 */
	public void setDeletedOn(java.util.Date deletedOn) {
		this.deletedOn = deletedOn;
	}
	/**
	 * Attribute deletedBy

	 */
	private java.lang.String deletedBy;
	/**
	 * Gets value for attribute deletedBy
	 */
	public java.lang.String getDeletedBy() {
		return this.deletedBy;
	}
	/**
	 * Sets value for attribute deletedBy
	 */
	public void setDeletedBy(java.lang.String deletedBy) {
		this.deletedBy = deletedBy;
	}
	/**
	 * Operation isAllowed
	 * Returns true if the permission on this object is granted
	 * @param permission
	 * @return
	**/
	 public abstract boolean isAllowed(
		java.lang.String permission);

	/**
	 * Returns <code>true</code> if the argument is an CustomObjectTypeEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof CustomObjectTypeEntity))
		{
			return false;
		}
		final CustomObjectTypeEntity that = (CustomObjectTypeEntity)object;
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
