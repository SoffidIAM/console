//
// (C) 2020 Soffid
//
//

package com.soffid.iam.rc.api;
/**
 * ValueObject SoDRole
 **/
public class SoDRole

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
	 * Attribute role

	 */
	private com.soffid.iam.iga.api.Role role;

	/**
	 * Attribute ruleId

	 */
	private java.lang.Long ruleId;

	public SoDRole()
	{
	}

	public SoDRole(java.lang.Long id, com.soffid.iam.iga.api.Role role, java.lang.Long ruleId)
	{
		super();
		this.id = id;
		this.role = role;
		this.ruleId = ruleId;
	}

	public SoDRole(com.soffid.iam.iga.api.Role role, java.lang.Long ruleId)
	{
		super();
		this.role = role;
		this.ruleId = ruleId;
	}

	public SoDRole(SoDRole otherBean)
	{
		this(otherBean.id, otherBean.role, otherBean.ruleId);
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
	 * Gets value for attribute role
	 */
	public com.soffid.iam.iga.api.Role getRole() {
		return this.role;
	}

	/**
	 * Sets value for attribute role
	 */
	public void setRole(com.soffid.iam.iga.api.Role role) {
		this.role = role;
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
		b.append (", role: ");
		b.append (this.role);
		b.append (", ruleId: ");
		b.append (this.ruleId);
		b.append ("]");
		return b.toString();
	}

}
