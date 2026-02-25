//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject AttributeMapping
 **/
public class AttributeMapping

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
	 * Attribute soffidAttribute

	 */
	private java.lang.String soffidAttribute;

	/**
	 * Attribute systemAttribute

	 */
	private java.lang.String systemAttribute;

	/**
	 * Attribute direction

	 */
	private com.soffid.iam.iga.api.AttributeDirection direction = com.soffid.iam.iga.api.AttributeDirection.INPUTOUTPUT;

	/**
	 * Attribute objectId

	 */
	private java.lang.Long objectId;

	public AttributeMapping()
	{
	}

	public AttributeMapping(java.lang.Long id, java.lang.String soffidAttribute, java.lang.String systemAttribute, com.soffid.iam.iga.api.AttributeDirection direction, java.lang.Long objectId)
	{
		super();
		this.id = id;
		this.soffidAttribute = soffidAttribute;
		this.systemAttribute = systemAttribute;
		this.direction = direction;
		this.objectId = objectId;
	}

	public AttributeMapping(java.lang.String soffidAttribute, java.lang.String systemAttribute, com.soffid.iam.iga.api.AttributeDirection direction, java.lang.Long objectId)
	{
		super();
		this.soffidAttribute = soffidAttribute;
		this.systemAttribute = systemAttribute;
		this.direction = direction;
		this.objectId = objectId;
	}

	public AttributeMapping(AttributeMapping otherBean)
	{
		this(otherBean.id, otherBean.soffidAttribute, otherBean.systemAttribute, otherBean.direction, otherBean.objectId);
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
	 * Gets value for attribute soffidAttribute
	 */
	public java.lang.String getSoffidAttribute() {
		return this.soffidAttribute;
	}

	/**
	 * Sets value for attribute soffidAttribute
	 */
	public void setSoffidAttribute(java.lang.String soffidAttribute) {
		this.soffidAttribute = soffidAttribute;
	}

	/**
	 * Gets value for attribute systemAttribute
	 */
	public java.lang.String getSystemAttribute() {
		return this.systemAttribute;
	}

	/**
	 * Sets value for attribute systemAttribute
	 */
	public void setSystemAttribute(java.lang.String systemAttribute) {
		this.systemAttribute = systemAttribute;
	}

	/**
	 * Gets value for attribute direction
	 */
	public com.soffid.iam.iga.api.AttributeDirection getDirection() {
		return this.direction;
	}

	/**
	 * Sets value for attribute direction
	 */
	public void setDirection(com.soffid.iam.iga.api.AttributeDirection direction) {
		this.direction = direction;
	}

	/**
	 * Gets value for attribute objectId
	 */
	public java.lang.Long getObjectId() {
		return this.objectId;
	}

	/**
	 * Sets value for attribute objectId
	 */
	public void setObjectId(java.lang.Long objectId) {
		this.objectId = objectId;
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
		b.append (", soffidAttribute: ");
		b.append (this.soffidAttribute);
		b.append (", systemAttribute: ");
		b.append (this.systemAttribute);
		b.append (", direction: ");
		b.append (this.direction);
		b.append (", objectId: ");
		b.append (this.objectId);
		b.append ("]");
		return b.toString();
	}

}
