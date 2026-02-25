//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject CustomObjectType
 **/
public class CustomObjectType

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute id

	 */
	private java.lang.Long id;

	/**
	 * Attribute name

	 */
	private java.lang.String name;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute nlsDescription

	 */
	private java.util.Map<java.lang.String,java.lang.String> nlsDescription;

	/**
	 * Attribute scope

	 */
	private com.soffid.iam.base.api.MetadataScope scope = com.soffid.iam.base.api.MetadataScope.CUSTOM;

	/**
	 * Attribute builtin

	 */
	private boolean builtin;

	/**
	 * Attribute textIndex

	 */
	private boolean textIndex;

	/**
	 * Attribute extensibleObjectClass

	 */
	private java.lang.String extensibleObjectClass;

	/**
	 * Attribute publicAccess

	 */
	private java.lang.Boolean publicAccess = true;

	/**
	 * Attribute managerRoles

	 */
	private java.util.List<java.lang.String> managerRoles;

	/**
	 * Attribute userRoles

	 */
	private java.util.List<java.lang.String> userRoles;

	/**
	 * Attribute createdOn


	 */
	private java.util.Date createdOn;

	/**
	 * Attribute createdBy


	 */
	private java.lang.String createdBy;

	/**
	 * Attribute updatedOn


	 */
	private java.util.Date updatedOn;

	/**
	 * Attribute updatedBy


	 */
	private java.lang.String updatedBy;

	/**
	 * Attribute deletedOn


	 */
	private java.util.Date deletedOn;

	/**
	 * Attribute deletedBy


	 */
	private java.lang.String deletedBy;

	public CustomObjectType()
	{
	}

	public CustomObjectType(java.lang.Long id, java.lang.String name, java.lang.String description, java.util.Map<java.lang.String,java.lang.String> nlsDescription, com.soffid.iam.base.api.MetadataScope scope, boolean builtin, boolean textIndex, java.lang.String extensibleObjectClass, java.lang.Boolean publicAccess, java.util.List<java.lang.String> managerRoles, java.util.List<java.lang.String> userRoles, java.util.Date createdOn, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy)
	{
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.nlsDescription = nlsDescription;
		this.scope = scope;
		this.builtin = builtin;
		this.textIndex = textIndex;
		this.extensibleObjectClass = extensibleObjectClass;
		this.publicAccess = publicAccess;
		this.managerRoles = managerRoles;
		this.userRoles = userRoles;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
	}

	public CustomObjectType(java.lang.String name, java.lang.String description, boolean builtin, boolean textIndex)
	{
		super();
		this.name = name;
		this.description = description;
		this.builtin = builtin;
		this.textIndex = textIndex;
	}

	public CustomObjectType(CustomObjectType otherBean)
	{
		this(otherBean.id, otherBean.name, otherBean.description, otherBean.nlsDescription, otherBean.scope, otherBean.builtin, otherBean.textIndex, otherBean.extensibleObjectClass, otherBean.publicAccess, otherBean.managerRoles, otherBean.userRoles, otherBean.createdOn, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy);
	}

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
	 * Gets value for attribute nlsDescription
	 */
	public java.util.Map<java.lang.String,java.lang.String> getNlsDescription() {
		return this.nlsDescription;
	}

	/**
	 * Sets value for attribute nlsDescription
	 */
	public void setNlsDescription(java.util.Map<java.lang.String,java.lang.String> nlsDescription) {
		this.nlsDescription = nlsDescription;
	}

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
	 * Gets value for attribute managerRoles
	 */
	public java.util.List<java.lang.String> getManagerRoles() {
		return this.managerRoles;
	}

	/**
	 * Sets value for attribute managerRoles
	 */
	public void setManagerRoles(java.util.List<java.lang.String> managerRoles) {
		this.managerRoles = managerRoles;
	}

	/**
	 * Gets value for attribute userRoles
	 */
	public java.util.List<java.lang.String> getUserRoles() {
		return this.userRoles;
	}

	/**
	 * Sets value for attribute userRoles
	 */
	public void setUserRoles(java.util.List<java.lang.String> userRoles) {
		this.userRoles = userRoles;
	}

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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[id: ");
		b.append (this.id);
		b.append (", name: ");
		b.append (this.name);
		b.append (", description: ");
		b.append (this.description);
		b.append (", nlsDescription: ");
		b.append (this.nlsDescription);
		b.append (", scope: ");
		b.append (this.scope);
		b.append (", builtin: ");
		b.append (this.builtin);
		b.append (", textIndex: ");
		b.append (this.textIndex);
		b.append (", extensibleObjectClass: ");
		b.append (this.extensibleObjectClass);
		b.append (", publicAccess: ");
		b.append (this.publicAccess);
		b.append (", managerRoles: ");
		b.append (this.managerRoles);
		b.append (", userRoles: ");
		b.append (this.userRoles);
		b.append (", createdOn: ");
		b.append (this.createdOn);
		b.append (", createdBy: ");
		b.append (this.createdBy);
		b.append (", updatedOn: ");
		b.append (this.updatedOn);
		b.append (", updatedBy: ");
		b.append (this.updatedBy);
		b.append (", deletedOn: ");
		b.append (this.deletedOn);
		b.append (", deletedBy: ");
		b.append (this.deletedBy);
		b.append ("]");
		return b.toString();
	}

}
