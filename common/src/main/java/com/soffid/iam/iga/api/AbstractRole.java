//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject AbstractRole
 * A role object represents any kind of permission that can be granted to anyone. It can be mapped to security group in active directory, a database role or another kind of authorizations.
 **/
public abstract class AbstractRole

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
	 * Attribute key
	 * Key (name + system name)

	 */
	private java.lang.String key;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute system

	 */
	private java.lang.String system;

	/**
	 * Attribute category

	 */
	private java.lang.String category;

	/**
	 * Attribute enableByDefault

	 */
	private java.lang.Boolean enableByDefault = true;

	/**
	 * Attribute password

	 */
	private java.lang.Boolean password;

	/**
	 * Attribute informationSystemName

	 */
	private java.lang.String informationSystemName;

	/**
	 * Attribute domainType

	 */
	private java.lang.String domainType;

	/**
	 * Attribute ownerRoles

	 */
	private java.util.Collection<com.soffid.iam.iga.api.RoleGrant> ownerRoles = new java.util.LinkedList();

	/**
	 * Attribute ownerGroups

	 */
	private java.util.Collection<com.soffid.iam.iga.api.Group> ownerGroups;

	/**
	 * Attribute granteeGroups

	 */
	private java.util.Collection<com.soffid.iam.iga.api.RoleGrant> granteeGroups = new java.util.LinkedList();

	/**
	 * Attribute ownedRoles

	 */
	private java.util.Collection<com.soffid.iam.iga.api.RoleGrant> ownedRoles = new java.util.LinkedList();

	/**
	 * Attribute bpmEnabled

	 */
	private java.lang.Boolean bpmEnabled;

	/**
	 * Attribute externalId

	 */
	private java.lang.String externalId;

	/**
	 * Attribute approvalStart
	 * Last modification date

	 */
	private java.util.Date approvalStart;

	/**
	 * Attribute approvalEnd
	 * Approval date

	 */
	private java.util.Date approvalEnd;

	/**
	 * Attribute attributes
	 * Role custom attributes

	 */
	private java.util.Map<java.lang.String,java.lang.Object> attributes = new java.util.HashMap<String,Object>();

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

	/**
	 * Attribute deleted


	 */
	private java.lang.Boolean deleted;

	public AbstractRole()
	{
	}

	public AbstractRole(java.lang.Long id, java.lang.String name, java.lang.String key, java.lang.String description, java.lang.String system, java.lang.String category, java.lang.Boolean enableByDefault, java.lang.Boolean password, java.lang.String informationSystemName, java.lang.String domainType, java.util.Collection<com.soffid.iam.iga.api.RoleGrant> ownerRoles, java.util.Collection<com.soffid.iam.iga.api.Group> ownerGroups, java.util.Collection<com.soffid.iam.iga.api.RoleGrant> granteeGroups, java.util.Collection<com.soffid.iam.iga.api.RoleGrant> ownedRoles, java.lang.Boolean bpmEnabled, java.lang.String externalId, java.util.Date approvalStart, java.util.Date approvalEnd, java.util.Map<java.lang.String,java.lang.Object> attributes, java.util.Date createdOn, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy, java.lang.Boolean deleted)
	{
		super();
		this.id = id;
		this.name = name;
		this.key = key;
		this.description = description;
		this.system = system;
		this.category = category;
		this.enableByDefault = enableByDefault;
		this.password = password;
		this.informationSystemName = informationSystemName;
		this.domainType = domainType;
		this.ownerRoles = ownerRoles;
		this.ownerGroups = ownerGroups;
		this.granteeGroups = granteeGroups;
		this.ownedRoles = ownedRoles;
		this.bpmEnabled = bpmEnabled;
		this.externalId = externalId;
		this.approvalStart = approvalStart;
		this.approvalEnd = approvalEnd;
		this.attributes = attributes;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
		this.deleted = deleted;
	}

	public AbstractRole(java.lang.String name, java.lang.String key, java.lang.String description, java.lang.String system, java.lang.String informationSystemName)
	{
		super();
		this.name = name;
		this.key = key;
		this.description = description;
		this.system = system;
		this.informationSystemName = informationSystemName;
	}

	public AbstractRole(AbstractRole otherBean)
	{
		this(otherBean.id, otherBean.name, otherBean.key, otherBean.description, otherBean.system, otherBean.category, otherBean.enableByDefault, otherBean.password, otherBean.informationSystemName, otherBean.domainType, otherBean.ownerRoles, otherBean.ownerGroups, otherBean.granteeGroups, otherBean.ownedRoles, otherBean.bpmEnabled, otherBean.externalId, otherBean.approvalStart, otherBean.approvalEnd, otherBean.attributes, otherBean.createdOn, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy, otherBean.deleted);
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
	 * Gets value for attribute key
	 */
	public java.lang.String getKey() {
		return this.key;
	}

	/**
	 * Sets value for attribute key
	 */
	public void setKey(java.lang.String key) {
		this.key = key;
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
	 * Gets value for attribute system
	 */
	public java.lang.String getSystem() {
		return this.system;
	}

	/**
	 * Sets value for attribute system
	 */
	public void setSystem(java.lang.String system) {
		this.system = system;
	}

	/**
	 * Gets value for attribute category
	 */
	public java.lang.String getCategory() {
		return this.category;
	}

	/**
	 * Sets value for attribute category
	 */
	public void setCategory(java.lang.String category) {
		this.category = category;
	}

	/**
	 * Gets value for attribute enableByDefault
	 */
	public java.lang.Boolean getEnableByDefault() {
		return this.enableByDefault;
	}

	/**
	 * Sets value for attribute enableByDefault
	 */
	public void setEnableByDefault(java.lang.Boolean enableByDefault) {
		this.enableByDefault = enableByDefault;
	}

	/**
	 * Gets value for attribute password
	 */
	public java.lang.Boolean getPassword() {
		return this.password;
	}

	/**
	 * Sets value for attribute password
	 */
	public void setPassword(java.lang.Boolean password) {
		this.password = password;
	}

	/**
	 * Gets value for attribute informationSystemName
	 */
	public java.lang.String getInformationSystemName() {
		return this.informationSystemName;
	}

	/**
	 * Sets value for attribute informationSystemName
	 */
	public void setInformationSystemName(java.lang.String informationSystemName) {
		this.informationSystemName = informationSystemName;
	}

	/**
	 * Gets value for attribute domainType
	 */
	public java.lang.String getDomainType() {
		return this.domainType;
	}

	/**
	 * Sets value for attribute domainType
	 */
	public void setDomainType(java.lang.String domainType) {
		this.domainType = domainType;
	}

	/**
	 * Gets value for attribute domainType
	 */
	public java.lang.String getDomain() {
		return this.domainType;
	}

	/**
	 * Sets value for attribute domainType
	 */
	public void setDomain(java.lang.String domainType) {
		this.domainType = domainType;
	}

	/**
	 * Gets value for attribute ownerRoles
	 */
	public java.util.Collection<com.soffid.iam.iga.api.RoleGrant> getOwnerRoles() {
		return this.ownerRoles;
	}

	/**
	 * Sets value for attribute ownerRoles
	 */
	public void setOwnerRoles(java.util.Collection<com.soffid.iam.iga.api.RoleGrant> ownerRoles) {
		this.ownerRoles = ownerRoles;
	}

	/**
	 * Gets value for attribute ownerGroups
	 */
	public java.util.Collection<com.soffid.iam.iga.api.Group> getOwnerGroups() {
		return this.ownerGroups;
	}

	/**
	 * Sets value for attribute ownerGroups
	 */
	public void setOwnerGroups(java.util.Collection<com.soffid.iam.iga.api.Group> ownerGroups) {
		this.ownerGroups = ownerGroups;
	}

	/**
	 * Gets value for attribute granteeGroups
	 */
	public java.util.Collection<com.soffid.iam.iga.api.RoleGrant> getGranteeGroups() {
		return this.granteeGroups;
	}

	/**
	 * Sets value for attribute granteeGroups
	 */
	public void setGranteeGroups(java.util.Collection<com.soffid.iam.iga.api.RoleGrant> granteeGroups) {
		this.granteeGroups = granteeGroups;
	}

	/**
	 * Gets value for attribute ownedRoles
	 */
	public java.util.Collection<com.soffid.iam.iga.api.RoleGrant> getOwnedRoles() {
		return this.ownedRoles;
	}

	/**
	 * Sets value for attribute ownedRoles
	 */
	public void setOwnedRoles(java.util.Collection<com.soffid.iam.iga.api.RoleGrant> ownedRoles) {
		this.ownedRoles = ownedRoles;
	}

	/**
	 * Gets value for attribute bpmEnabled
	 */
	public java.lang.Boolean getBpmEnabled() {
		return this.bpmEnabled;
	}

	/**
	 * Sets value for attribute bpmEnabled
	 */
	public void setBpmEnabled(java.lang.Boolean bpmEnabled) {
		this.bpmEnabled = bpmEnabled;
	}

	/**
	 * Gets value for attribute bpmEnabled
	 */
	public java.lang.Boolean getBpmEnforced() {
		return this.bpmEnabled;
	}

	/**
	 * Sets value for attribute bpmEnabled
	 */
	public void setBpmEnforced(java.lang.Boolean bpmEnabled) {
		this.bpmEnabled = bpmEnabled;
	}

	/**
	 * Gets value for attribute externalId
	 */
	public java.lang.String getExternalId() {
		return this.externalId;
	}

	/**
	 * Sets value for attribute externalId
	 */
	public void setExternalId(java.lang.String externalId) {
		this.externalId = externalId;
	}

	/**
	 * Gets value for attribute approvalStart
	 */
	public java.util.Date getApprovalStart() {
		return this.approvalStart;
	}

	/**
	 * Sets value for attribute approvalStart
	 */
	public void setApprovalStart(java.util.Date approvalStart) {
		this.approvalStart = approvalStart;
	}

	/**
	 * Gets value for attribute approvalEnd
	 */
	public java.util.Date getApprovalEnd() {
		return this.approvalEnd;
	}

	/**
	 * Sets value for attribute approvalEnd
	 */
	public void setApprovalEnd(java.util.Date approvalEnd) {
		this.approvalEnd = approvalEnd;
	}

	/**
	 * Gets value for attribute attributes
	 */
	public java.util.Map<java.lang.String,java.lang.Object> getAttributes() {
		return this.attributes;
	}

	/**
	 * Sets value for attribute attributes
	 */
	public void setAttributes(java.util.Map<java.lang.String,java.lang.Object> attributes) {
		this.attributes = attributes;
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
		b.append (", key: ");
		b.append (this.key);
		b.append (", description: ");
		b.append (this.description);
		b.append (", system: ");
		b.append (this.system);
		b.append (", category: ");
		b.append (this.category);
		b.append (", enableByDefault: ");
		b.append (this.enableByDefault);
		b.append (", password: ");
		b.append (this.password);
		b.append (", informationSystemName: ");
		b.append (this.informationSystemName);
		b.append (", domainType: ");
		b.append (this.domainType);
		b.append (", ownerRoles: ");
		b.append (this.ownerRoles);
		b.append (", ownerGroups: ");
		b.append (this.ownerGroups);
		b.append (", granteeGroups: ");
		b.append (this.granteeGroups);
		b.append (", ownedRoles: ");
		b.append (this.ownedRoles);
		b.append (", bpmEnabled: ");
		b.append (this.bpmEnabled);
		b.append (", externalId: ");
		b.append (this.externalId);
		b.append (", approvalStart: ");
		b.append (this.approvalStart);
		b.append (", approvalEnd: ");
		b.append (this.approvalEnd);
		b.append (", attributes: ");
		b.append (this.attributes);
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
		b.append (", deleted: ");
		b.append (this.deleted);
		b.append ("]");
		return b.toString();
	}

}
