//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity MailListEntity
 */

public abstract class MailListEntity {

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
	 * Attribute externals
	 */
	private java.util.Collection<com.soffid.iam.iga.model.ExternalNameEntity> externals =  new java.util.HashSet<com.soffid.iam.iga.model.ExternalNameEntity>();
	/**
	 * Gets value for attribute externals
	 */
	public java.util.Collection<com.soffid.iam.iga.model.ExternalNameEntity> getExternals() {
		return this.externals;
	}
	/**
	 * Sets value for attribute externals
	 */
	public void setExternals(java.util.Collection<com.soffid.iam.iga.model.ExternalNameEntity> externals) {
		this.externals = externals;
	}
	/**
	 * Attribute domain
	 */
	private com.soffid.iam.iga.model.MailDomainEntity domain;
	/**
	 * Gets value for attribute domain
	 */
	public com.soffid.iam.iga.model.MailDomainEntity getDomain() {
		return this.domain;
	}
	/**
	 * Sets value for attribute domain
	 */
	public void setDomain(com.soffid.iam.iga.model.MailDomainEntity domain) {
		this.domain = domain;
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
	 * Attribute userMailLists
	 */
	private java.util.Collection<com.soffid.iam.iga.model.UserMailEntity> userMailLists =  new java.util.HashSet<com.soffid.iam.iga.model.UserMailEntity>();
	/**
	 * Gets value for attribute userMailLists
	 */
	public java.util.Collection<com.soffid.iam.iga.model.UserMailEntity> getUserMailLists() {
		return this.userMailLists;
	}
	/**
	 * Sets value for attribute userMailLists
	 */
	public void setUserMailLists(java.util.Collection<com.soffid.iam.iga.model.UserMailEntity> userMailLists) {
		this.userMailLists = userMailLists;
	}
	/**
	 * Attribute mailListPertain
	 */
	private java.util.Collection<com.soffid.iam.iga.model.MailListContainerEntity> mailListPertain =  new java.util.HashSet<com.soffid.iam.iga.model.MailListContainerEntity>();
	/**
	 * Gets value for attribute mailListPertain
	 */
	public java.util.Collection<com.soffid.iam.iga.model.MailListContainerEntity> getMailListPertain() {
		return this.mailListPertain;
	}
	/**
	 * Sets value for attribute mailListPertain
	 */
	public void setMailListPertain(java.util.Collection<com.soffid.iam.iga.model.MailListContainerEntity> mailListPertain) {
		this.mailListPertain = mailListPertain;
	}
	/**
	 * Attribute mailListContent
	 */
	private java.util.Collection<com.soffid.iam.iga.model.MailListContainerEntity> mailListContent =  new java.util.HashSet<com.soffid.iam.iga.model.MailListContainerEntity>();
	/**
	 * Gets value for attribute mailListContent
	 */
	public java.util.Collection<com.soffid.iam.iga.model.MailListContainerEntity> getMailListContent() {
		return this.mailListContent;
	}
	/**
	 * Sets value for attribute mailListContent
	 */
	public void setMailListContent(java.util.Collection<com.soffid.iam.iga.model.MailListContainerEntity> mailListContent) {
		this.mailListContent = mailListContent;
	}
	/**
	 * Attribute attributes

	 */
	private java.util.Collection<com.soffid.iam.iga.model.MailListAttributeEntity> attributes =  new java.util.HashSet<com.soffid.iam.iga.model.MailListAttributeEntity>();
	/**
	 * Gets value for attribute attributes
	 */
	public java.util.Collection<com.soffid.iam.iga.model.MailListAttributeEntity> getAttributes() {
		return this.attributes;
	}
	/**
	 * Sets value for attribute attributes
	 */
	public void setAttributes(java.util.Collection<com.soffid.iam.iga.model.MailListAttributeEntity> attributes) {
		this.attributes = attributes;
	}
	/**
	 * Attribute groups

	 */
	private java.util.Collection<com.soffid.iam.iga.model.MailListGroupMemberEntity> groups =  new java.util.HashSet<com.soffid.iam.iga.model.MailListGroupMemberEntity>();
	/**
	 * Gets value for attribute groups
	 */
	public java.util.Collection<com.soffid.iam.iga.model.MailListGroupMemberEntity> getGroups() {
		return this.groups;
	}
	/**
	 * Sets value for attribute groups
	 */
	public void setGroups(java.util.Collection<com.soffid.iam.iga.model.MailListGroupMemberEntity> groups) {
		this.groups = groups;
	}
	/**
	 * Attribute roles

	 */
	private java.util.Collection<com.soffid.iam.iga.model.MailListRoleMemberEntity> roles =  new java.util.HashSet<com.soffid.iam.iga.model.MailListRoleMemberEntity>();
	/**
	 * Gets value for attribute roles
	 */
	public java.util.Collection<com.soffid.iam.iga.model.MailListRoleMemberEntity> getRoles() {
		return this.roles;
	}
	/**
	 * Sets value for attribute roles
	 */
	public void setRoles(java.util.Collection<com.soffid.iam.iga.model.MailListRoleMemberEntity> roles) {
		this.roles = roles;
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
	 * Attribute deleted

	 */
	private java.lang.Boolean deleted;
	/**
	 * Gets value for attribute deleted
	 */
	public java.lang.Boolean getDeleted() {
		return this.deleted;
	}
	/**
	 * Sets value for attribute deleted
	 */
	public void setDeleted(java.lang.Boolean deleted) {
		this.deleted = deleted;
	}
	/**
	 * Returns <code>true</code> if the argument is an MailListEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof MailListEntity))
		{
			return false;
		}
		final MailListEntity that = (MailListEntity)object;
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
