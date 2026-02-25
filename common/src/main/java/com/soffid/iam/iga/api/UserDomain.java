//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject UserDomain
 **/
public class UserDomain

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
	 * Attribute type

	 */
	private com.soffid.iam.iga.api.UserDomainType type;

	/**
	 * Attribute bshExpr

	 */
	private java.lang.String bshExpr;

	/**
	 * Attribute bshExprCreate

	 */
	private java.lang.String bshExprCreate;

	/**
	 * Attribute beanGenerator

	 */
	private java.lang.String beanGenerator;

	public UserDomain()
	{
	}

	public UserDomain(java.lang.Long id, java.lang.String name, java.lang.String description, com.soffid.iam.iga.api.UserDomainType type, java.lang.String bshExpr, java.lang.String bshExprCreate, java.lang.String beanGenerator)
	{
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.bshExpr = bshExpr;
		this.bshExprCreate = bshExprCreate;
		this.beanGenerator = beanGenerator;
	}

	public UserDomain(UserDomain otherBean)
	{
		this(otherBean.id, otherBean.name, otherBean.description, otherBean.type, otherBean.bshExpr, otherBean.bshExprCreate, otherBean.beanGenerator);
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
	 * Gets value for attribute name
	 */
	public java.lang.String getCode() {
		return this.name;
	}

	/**
	 * Sets value for attribute name
	 */
	public void setCode(java.lang.String name) {
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
	 * Gets value for attribute type
	 */
	public com.soffid.iam.iga.api.UserDomainType getType() {
		return this.type;
	}

	/**
	 * Sets value for attribute type
	 */
	public void setType(com.soffid.iam.iga.api.UserDomainType type) {
		this.type = type;
	}

	/**
	 * Gets value for attribute bshExpr
	 */
	public java.lang.String getBshExpr() {
		return this.bshExpr;
	}

	/**
	 * Sets value for attribute bshExpr
	 */
	public void setBshExpr(java.lang.String bshExpr) {
		this.bshExpr = bshExpr;
	}

	/**
	 * Gets value for attribute bshExprCreate
	 */
	public java.lang.String getBshExprCreate() {
		return this.bshExprCreate;
	}

	/**
	 * Sets value for attribute bshExprCreate
	 */
	public void setBshExprCreate(java.lang.String bshExprCreate) {
		this.bshExprCreate = bshExprCreate;
	}

	/**
	 * Gets value for attribute beanGenerator
	 */
	public java.lang.String getBeanGenerator() {
		return this.beanGenerator;
	}

	/**
	 * Sets value for attribute beanGenerator
	 */
	public void setBeanGenerator(java.lang.String beanGenerator) {
		this.beanGenerator = beanGenerator;
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
		b.append (", type: ");
		b.append (this.type);
		b.append (", bshExpr: ");
		b.append (this.bshExpr);
		b.append (", bshExprCreate: ");
		b.append (this.bshExprCreate);
		b.append (", beanGenerator: ");
		b.append (this.beanGenerator);
		b.append ("]");
		return b.toString();
	}

}
