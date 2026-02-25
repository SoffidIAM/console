//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject RoleGrantHierarchy
 **/
public class RoleGrantHierarchy
 extends com.soffid.iam.iga.api.RoleGrant

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute ruleName

	 */
	private java.lang.String ruleName;

	/**
	 * Attribute ruleDescription

	 */
	private java.lang.String ruleDescription;

	/**
	 * Attribute groupName

	 */
	private java.lang.String groupName;

	/**
	 * Attribute groupDescription

	 */
	private java.lang.String groupDescription;

	/**
	 * Attribute accountName

	 */
	private java.lang.String accountName;

	/**
	 * Attribute accountDescription

	 */
	private java.lang.String accountDescription;

	/**
	 * Attribute nested

	 */
	private java.util.List<com.soffid.iam.iga.api.RoleGrantHierarchy> nested = new java.util.LinkedList<>();

	public RoleGrantHierarchy()
	{
	}

	public RoleGrantHierarchy(java.lang.Long id, java.lang.Long roleId, java.lang.String roleName, java.lang.String roleDescription, java.lang.String system, java.lang.String informationSystem, boolean hasDomain, java.lang.String domainValue, java.lang.String domainDescription, java.lang.String ownerAccountName, java.lang.String ownerInformationSystem, java.lang.String ownerSystem, java.lang.String ownerGroup, java.lang.Long ownerRole, java.lang.String ownerRolDomainValue, java.lang.String ownerRoleName, java.lang.String ownerRoleDescription, java.lang.String user, java.util.Date startDate, java.util.Date endDate, boolean enabled, java.lang.String holderGroup, com.soffid.iam.iga.api.RoleDependencyStatus status, java.lang.Boolean mandatory, java.util.Map<java.lang.String,java.lang.Object> attributes, java.lang.String ruleName, java.lang.String ruleDescription, java.lang.String groupName, java.lang.String groupDescription, java.lang.String accountName, java.lang.String accountDescription, java.util.List<com.soffid.iam.iga.api.RoleGrantHierarchy> nested)
	{
		super(id, roleId, roleName, roleDescription, system, informationSystem, hasDomain, domainValue, domainDescription, ownerAccountName, ownerInformationSystem, ownerSystem, ownerGroup, ownerRole, ownerRolDomainValue, ownerRoleName, ownerRoleDescription, user, startDate, endDate, enabled, holderGroup, status, mandatory, attributes);
		this.ruleName = ruleName;
		this.ruleDescription = ruleDescription;
		this.groupName = groupName;
		this.groupDescription = groupDescription;
		this.accountName = accountName;
		this.accountDescription = accountDescription;
		this.nested = nested;
	}

	public RoleGrantHierarchy(java.lang.Long roleId, java.lang.String roleName, java.lang.String system, boolean hasDomain, boolean enabled)
	{
		super(roleId, roleName, system, hasDomain, enabled);
	}

	public RoleGrantHierarchy(RoleGrantHierarchy otherBean)
	{
		this(otherBean.getId(), otherBean.getRoleId(), otherBean.getRoleName(), otherBean.getRoleDescription(), otherBean.getSystem(), otherBean.getInformationSystem(), otherBean.isHasDomain(), otherBean.getDomainValue(), otherBean.getDomainDescription(), otherBean.getOwnerAccountName(), otherBean.getOwnerInformationSystem(), otherBean.getOwnerSystem(), otherBean.getOwnerGroup(), otherBean.getOwnerRole(), otherBean.getOwnerRolDomainValue(), otherBean.getOwnerRoleName(), otherBean.getOwnerRoleDescription(), otherBean.getUser(), otherBean.getStartDate(), otherBean.getEndDate(), otherBean.isEnabled(), otherBean.getHolderGroup(), otherBean.getStatus(), otherBean.getMandatory(), otherBean.getAttributes(), otherBean.ruleName, otherBean.ruleDescription, otherBean.groupName, otherBean.groupDescription, otherBean.accountName, otherBean.accountDescription, otherBean.nested);
	}

	/**
	 * Gets value for attribute ruleName
	 */
	public java.lang.String getRuleName() {
		return this.ruleName;
	}

	/**
	 * Sets value for attribute ruleName
	 */
	public void setRuleName(java.lang.String ruleName) {
		this.ruleName = ruleName;
	}

	/**
	 * Gets value for attribute ruleDescription
	 */
	public java.lang.String getRuleDescription() {
		return this.ruleDescription;
	}

	/**
	 * Sets value for attribute ruleDescription
	 */
	public void setRuleDescription(java.lang.String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}

	/**
	 * Gets value for attribute groupName
	 */
	public java.lang.String getGroupName() {
		return this.groupName;
	}

	/**
	 * Sets value for attribute groupName
	 */
	public void setGroupName(java.lang.String groupName) {
		this.groupName = groupName;
	}

	/**
	 * Gets value for attribute groupDescription
	 */
	public java.lang.String getGroupDescription() {
		return this.groupDescription;
	}

	/**
	 * Sets value for attribute groupDescription
	 */
	public void setGroupDescription(java.lang.String groupDescription) {
		this.groupDescription = groupDescription;
	}

	/**
	 * Gets value for attribute accountName
	 */
	public java.lang.String getAccountName() {
		return this.accountName;
	}

	/**
	 * Sets value for attribute accountName
	 */
	public void setAccountName(java.lang.String accountName) {
		this.accountName = accountName;
	}

	/**
	 * Gets value for attribute accountDescription
	 */
	public java.lang.String getAccountDescription() {
		return this.accountDescription;
	}

	/**
	 * Sets value for attribute accountDescription
	 */
	public void setAccountDescription(java.lang.String accountDescription) {
		this.accountDescription = accountDescription;
	}

	/**
	 * Gets value for attribute nested
	 */
	public java.util.List<com.soffid.iam.iga.api.RoleGrantHierarchy> getNested() {
		return this.nested;
	}

	/**
	 * Sets value for attribute nested
	 */
	public void setNested(java.util.List<com.soffid.iam.iga.api.RoleGrantHierarchy> nested) {
		this.nested = nested;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[ruleName: ");
		b.append (this.ruleName);
		b.append (", ruleDescription: ");
		b.append (this.ruleDescription);
		b.append (", groupName: ");
		b.append (this.groupName);
		b.append (", groupDescription: ");
		b.append (this.groupDescription);
		b.append (", accountName: ");
		b.append (this.accountName);
		b.append (", accountDescription: ");
		b.append (this.accountDescription);
		b.append (", nested: ");
		b.append (this.nested);
		b.append (", id: ");
		b.append (this.getId());
		b.append (", roleId: ");
		b.append (this.getRoleId());
		b.append (", roleName: ");
		b.append (this.getRoleName());
		b.append (", roleDescription: ");
		b.append (this.getRoleDescription());
		b.append (", system: ");
		b.append (this.getSystem());
		b.append (", informationSystem: ");
		b.append (this.getInformationSystem());
		b.append (", hasDomain: ");
		b.append (this.isHasDomain());
		b.append (", domainValue: ");
		b.append (this.getDomainValue());
		b.append (", domainDescription: ");
		b.append (this.getDomainDescription());
		b.append (", ownerAccountName: ");
		b.append (this.getOwnerAccountName());
		b.append (", ownerInformationSystem: ");
		b.append (this.getOwnerInformationSystem());
		b.append (", ownerSystem: ");
		b.append (this.getOwnerSystem());
		b.append (", ownerGroup: ");
		b.append (this.getOwnerGroup());
		b.append (", ownerRole: ");
		b.append (this.getOwnerRole());
		b.append (", ownerRolDomainValue: ");
		b.append (this.getOwnerRolDomainValue());
		b.append (", ownerRoleName: ");
		b.append (this.getOwnerRoleName());
		b.append (", ownerRoleDescription: ");
		b.append (this.getOwnerRoleDescription());
		b.append (", user: ");
		b.append (this.getUser());
		b.append (", startDate: ");
		b.append (this.getStartDate());
		b.append (", endDate: ");
		b.append (this.getEndDate());
		b.append (", enabled: ");
		b.append (this.isEnabled());
		b.append (", holderGroup: ");
		b.append (this.getHolderGroup());
		b.append (", status: ");
		b.append (this.getStatus());
		b.append (", mandatory: ");
		b.append (this.getMandatory());
		b.append (", attributes: ");
		b.append (this.getAttributes());
		b.append ("]");
		return b.toString();
	}

}
