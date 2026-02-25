//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity RoleGroupEntity
 */

public abstract class RoleGroupEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute group
	 */
	private com.soffid.iam.iga.model.GroupEntity group;
	/**
	 * Gets value for attribute group
	 */
	public com.soffid.iam.iga.model.GroupEntity getGroup() {
		return this.group;
	}
	/**
	 * Sets value for attribute group
	 */
	public void setGroup(com.soffid.iam.iga.model.GroupEntity group) {
		this.group = group;
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
	 * Attribute grantedRole
	 */
	private com.soffid.iam.iga.model.RoleEntity grantedRole;
	/**
	 * Gets value for attribute grantedRole
	 */
	public com.soffid.iam.iga.model.RoleEntity getGrantedRole() {
		return this.grantedRole;
	}
	/**
	 * Sets value for attribute grantedRole
	 */
	public void setGrantedRole(com.soffid.iam.iga.model.RoleEntity grantedRole) {
		this.grantedRole = grantedRole;
	}
	/**
	 * Attribute grantedApplicationDomain
	 */
	private com.soffid.iam.iga.model.InformationSystemEntity grantedApplicationDomain;
	/**
	 * Gets value for attribute grantedApplicationDomain
	 */
	public com.soffid.iam.iga.model.InformationSystemEntity getGrantedApplicationDomain() {
		return this.grantedApplicationDomain;
	}
	/**
	 * Sets value for attribute grantedApplicationDomain
	 */
	public void setGrantedApplicationDomain(com.soffid.iam.iga.model.InformationSystemEntity grantedApplicationDomain) {
		this.grantedApplicationDomain = grantedApplicationDomain;
	}
	/**
	 * Attribute grantedGroupDomain
	 */
	private com.soffid.iam.iga.model.GroupEntity grantedGroupDomain;
	/**
	 * Gets value for attribute grantedGroupDomain
	 */
	public com.soffid.iam.iga.model.GroupEntity getGrantedGroupDomain() {
		return this.grantedGroupDomain;
	}
	/**
	 * Sets value for attribute grantedGroupDomain
	 */
	public void setGrantedGroupDomain(com.soffid.iam.iga.model.GroupEntity grantedGroupDomain) {
		this.grantedGroupDomain = grantedGroupDomain;
	}
	/**
	 * Attribute grantedDomainValue
	 */
	private com.soffid.iam.iga.model.DomainValueEntity grantedDomainValue;
	/**
	 * Gets value for attribute grantedDomainValue
	 */
	public com.soffid.iam.iga.model.DomainValueEntity getGrantedDomainValue() {
		return this.grantedDomainValue;
	}
	/**
	 * Sets value for attribute grantedDomainValue
	 */
	public void setGrantedDomainValue(com.soffid.iam.iga.model.DomainValueEntity grantedDomainValue) {
		this.grantedDomainValue = grantedDomainValue;
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
	 * Operation toString
	 * @return
	**/
	 public abstract java.lang.String toString();

	/**
	 * Returns <code>true</code> if the argument is an RoleGroupEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof RoleGroupEntity))
		{
			return false;
		}
		final RoleGroupEntity that = (RoleGroupEntity)object;
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
