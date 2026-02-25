//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity RoleDependencyEntity
 */

public abstract class RoleDependencyEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute contained
	 * The role to be granted when the container has been granted
	 */
	private com.soffid.iam.iga.model.RoleEntity contained;
	/**
	 * Gets value for attribute contained
	 */
	public com.soffid.iam.iga.model.RoleEntity getContained() {
		return this.contained;
	}
	/**
	 * Sets value for attribute contained
	 */
	public void setContained(com.soffid.iam.iga.model.RoleEntity contained) {
		this.contained = contained;
	}
	/**
	 * Attribute container
	 * The role that grants another role
	 */
	private com.soffid.iam.iga.model.RoleEntity container;
	/**
	 * Gets value for attribute container
	 */
	public com.soffid.iam.iga.model.RoleEntity getContainer() {
		return this.container;
	}
	/**
	 * Sets value for attribute container
	 */
	public void setContainer(com.soffid.iam.iga.model.RoleEntity container) {
		this.container = container;
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
	 * Attribute granteeApplicationDomain
	 */
	private com.soffid.iam.iga.model.InformationSystemEntity granteeApplicationDomain;
	/**
	 * Gets value for attribute granteeApplicationDomain
	 */
	public com.soffid.iam.iga.model.InformationSystemEntity getGranteeApplicationDomain() {
		return this.granteeApplicationDomain;
	}
	/**
	 * Sets value for attribute granteeApplicationDomain
	 */
	public void setGranteeApplicationDomain(com.soffid.iam.iga.model.InformationSystemEntity granteeApplicationDomain) {
		this.granteeApplicationDomain = granteeApplicationDomain;
	}
	/**
	 * Attribute granteeGroupDomain
	 */
	private com.soffid.iam.iga.model.GroupEntity granteeGroupDomain;
	/**
	 * Gets value for attribute granteeGroupDomain
	 */
	public com.soffid.iam.iga.model.GroupEntity getGranteeGroupDomain() {
		return this.granteeGroupDomain;
	}
	/**
	 * Sets value for attribute granteeGroupDomain
	 */
	public void setGranteeGroupDomain(com.soffid.iam.iga.model.GroupEntity granteeGroupDomain) {
		this.granteeGroupDomain = granteeGroupDomain;
	}
	/**
	 * Attribute granteeDomainValue
	 */
	private com.soffid.iam.iga.model.DomainValueEntity granteeDomainValue;
	/**
	 * Gets value for attribute granteeDomainValue
	 */
	public com.soffid.iam.iga.model.DomainValueEntity getGranteeDomainValue() {
		return this.granteeDomainValue;
	}
	/**
	 * Sets value for attribute granteeDomainValue
	 */
	public void setGranteeDomainValue(com.soffid.iam.iga.model.DomainValueEntity granteeDomainValue) {
		this.granteeDomainValue = granteeDomainValue;
	}
	/**
	 * Attribute domainApplication
	 */
	private com.soffid.iam.iga.model.InformationSystemEntity domainApplication;
	/**
	 * Gets value for attribute domainApplication
	 */
	public com.soffid.iam.iga.model.InformationSystemEntity getDomainApplication() {
		return this.domainApplication;
	}
	/**
	 * Sets value for attribute domainApplication
	 */
	public void setDomainApplication(com.soffid.iam.iga.model.InformationSystemEntity domainApplication) {
		this.domainApplication = domainApplication;
	}
	/**
	 * Attribute domainGroup
	 */
	private com.soffid.iam.iga.model.GroupEntity domainGroup;
	/**
	 * Gets value for attribute domainGroup
	 */
	public com.soffid.iam.iga.model.GroupEntity getDomainGroup() {
		return this.domainGroup;
	}
	/**
	 * Sets value for attribute domainGroup
	 */
	public void setDomainGroup(com.soffid.iam.iga.model.GroupEntity domainGroup) {
		this.domainGroup = domainGroup;
	}
	/**
	 * Attribute domainApplicationValue
	 */
	private com.soffid.iam.iga.model.DomainValueEntity domainApplicationValue;
	/**
	 * Gets value for attribute domainApplicationValue
	 */
	public com.soffid.iam.iga.model.DomainValueEntity getDomainApplicationValue() {
		return this.domainApplicationValue;
	}
	/**
	 * Sets value for attribute domainApplicationValue
	 */
	public void setDomainApplicationValue(com.soffid.iam.iga.model.DomainValueEntity domainApplicationValue) {
		this.domainApplicationValue = domainApplicationValue;
	}
	/**
	 * Attribute status
	 */
	private com.soffid.iam.iga.api.RoleDependencyStatus status;
	/**
	 * Gets value for attribute status
	 */
	public com.soffid.iam.iga.api.RoleDependencyStatus getStatus() {
		return this.status;
	}
	/**
	 * Sets value for attribute status
	 */
	public void setStatus(com.soffid.iam.iga.api.RoleDependencyStatus status) {
		this.status = status;
	}
	/**
	 * Attribute mandatory
	 * True if the role is always granted. False if role grant is optional, and thus can be removed from user entitlements form
	 */
	private java.lang.Boolean mandatory = true;
	/**
	 * Gets value for attribute mandatory
	 */
	public java.lang.Boolean getMandatory() {
		return this.mandatory;
	}
	/**
	 * Sets value for attribute mandatory
	 */
	public void setMandatory(java.lang.Boolean mandatory) {
		this.mandatory = mandatory;
	}
	/**
	 * Operation isAllowed
	 * @param permission
	 * @return
	**/
	 public abstract boolean isAllowed(
		java.lang.String permission);

	/**
	 * Operation toString
	 * @return
	**/
	 public abstract java.lang.String toString();

	/**
	 * Operation customCache
	**/
	 public abstract void customCache();

	/**
	 * Returns <code>true</code> if the argument is an RoleDependencyEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof RoleDependencyEntity))
		{
			return false;
		}
		final RoleDependencyEntity that = (RoleDependencyEntity)object;
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
