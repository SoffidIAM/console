//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject Rule
 **/
public class Rule

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
	 * Attribute bshExpression

	 */
	private java.lang.String bshExpression;

	/**
	 * Attribute bshRoles

	 */
	private java.lang.String bshRoles;

	public Rule()
	{
	}

	public Rule(java.lang.Long id, java.lang.String name, java.lang.String description, java.lang.String bshExpression, java.lang.String bshRoles)
	{
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.bshExpression = bshExpression;
		this.bshRoles = bshRoles;
	}

	public Rule(java.lang.String name, java.lang.String bshExpression)
	{
		super();
		this.name = name;
		this.bshExpression = bshExpression;
	}

	public Rule(Rule otherBean)
	{
		this(otherBean.id, otherBean.name, otherBean.description, otherBean.bshExpression, otherBean.bshRoles);
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
	 * Gets value for attribute bshExpression
	 */
	public java.lang.String getBshExpression() {
		return this.bshExpression;
	}

	/**
	 * Sets value for attribute bshExpression
	 */
	public void setBshExpression(java.lang.String bshExpression) {
		this.bshExpression = bshExpression;
	}

	/**
	 * Gets value for attribute bshRoles
	 */
	public java.lang.String getBshRoles() {
		return this.bshRoles;
	}

	/**
	 * Sets value for attribute bshRoles
	 */
	public void setBshRoles(java.lang.String bshRoles) {
		this.bshRoles = bshRoles;
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
		b.append (", bshExpression: ");
		b.append (this.bshExpression);
		b.append (", bshRoles: ");
		b.append (this.bshRoles);
		b.append ("]");
		return b.toString();
	}

}
