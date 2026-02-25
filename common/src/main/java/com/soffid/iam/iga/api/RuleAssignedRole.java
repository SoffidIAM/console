//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject RuleAssignedRole
 **/
public class RuleAssignedRole

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
	 * Attribute bshDomainValueExpression

	 */
	private java.lang.String bshDomainValueExpression;

	/**
	 * Attribute domainValue

	 */
	private java.lang.String domainValue;

	/**
	 * Attribute roleId

	 */
	private java.lang.Long roleId;

	/**
	 * Attribute ruleId

	 */
	private java.lang.Long ruleId;

	public RuleAssignedRole()
	{
	}

	public RuleAssignedRole(java.lang.Long id, java.lang.String bshDomainValueExpression, java.lang.String domainValue, java.lang.Long roleId, java.lang.Long ruleId)
	{
		super();
		this.id = id;
		this.bshDomainValueExpression = bshDomainValueExpression;
		this.domainValue = domainValue;
		this.roleId = roleId;
		this.ruleId = ruleId;
	}

	public RuleAssignedRole(java.lang.Long roleId, java.lang.Long ruleId)
	{
		super();
		this.roleId = roleId;
		this.ruleId = ruleId;
	}

	public RuleAssignedRole(RuleAssignedRole otherBean)
	{
		this(otherBean.id, otherBean.bshDomainValueExpression, otherBean.domainValue, otherBean.roleId, otherBean.ruleId);
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
	 * Gets value for attribute bshDomainValueExpression
	 */
	public java.lang.String getBshDomainValueExpression() {
		return this.bshDomainValueExpression;
	}

	/**
	 * Sets value for attribute bshDomainValueExpression
	 */
	public void setBshDomainValueExpression(java.lang.String bshDomainValueExpression) {
		this.bshDomainValueExpression = bshDomainValueExpression;
	}

	/**
	 * Gets value for attribute domainValue
	 */
	public java.lang.String getDomainValue() {
		return this.domainValue;
	}

	/**
	 * Sets value for attribute domainValue
	 */
	public void setDomainValue(java.lang.String domainValue) {
		this.domainValue = domainValue;
	}

	/**
	 * Gets value for attribute roleId
	 */
	public java.lang.Long getRoleId() {
		return this.roleId;
	}

	/**
	 * Sets value for attribute roleId
	 */
	public void setRoleId(java.lang.Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * Gets value for attribute ruleId
	 */
	public java.lang.Long getRuleId() {
		return this.ruleId;
	}

	/**
	 * Sets value for attribute ruleId
	 */
	public void setRuleId(java.lang.Long ruleId) {
		this.ruleId = ruleId;
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
		b.append (", bshDomainValueExpression: ");
		b.append (this.bshDomainValueExpression);
		b.append (", domainValue: ");
		b.append (this.domainValue);
		b.append (", roleId: ");
		b.append (this.roleId);
		b.append (", ruleId: ");
		b.append (this.ruleId);
		b.append ("]");
		return b.toString();
	}

}
