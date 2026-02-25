//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject ObjectMappingProperty
 **/
public class ObjectMappingProperty

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
	 * Attribute property

	 */
	private java.lang.String property;

	/**
	 * Attribute value

	 */
	private java.lang.String value;

	/**
	 * Attribute objectId

	 */
	private java.lang.Long objectId;

	public ObjectMappingProperty()
	{
	}

	public ObjectMappingProperty(java.lang.Long id, java.lang.String property, java.lang.String value, java.lang.Long objectId)
	{
		super();
		this.id = id;
		this.property = property;
		this.value = value;
		this.objectId = objectId;
	}

	public ObjectMappingProperty(java.lang.String property, java.lang.Long objectId)
	{
		super();
		this.property = property;
		this.objectId = objectId;
	}

	public ObjectMappingProperty(ObjectMappingProperty otherBean)
	{
		this(otherBean.id, otherBean.property, otherBean.value, otherBean.objectId);
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
	 * Gets value for attribute property
	 */
	public java.lang.String getProperty() {
		return this.property;
	}

	/**
	 * Sets value for attribute property
	 */
	public void setProperty(java.lang.String property) {
		this.property = property;
	}

	/**
	 * Gets value for attribute value
	 */
	public java.lang.String getValue() {
		return this.value;
	}

	/**
	 * Sets value for attribute value
	 */
	public void setValue(java.lang.String value) {
		this.value = value;
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
		b.append (", property: ");
		b.append (this.property);
		b.append (", value: ");
		b.append (this.value);
		b.append (", objectId: ");
		b.append (this.objectId);
		b.append ("]");
		return b.toString();
	}

}
