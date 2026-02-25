//
// (c) 2014 Soffid
//
//

package com.soffid.iam.base.model;

/**
 *  Entity DefaultObjectMappingPropertyEntity
 */

public abstract class DefaultObjectMappingPropertyEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
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
	 * Attribute property
	 */
	private java.lang.String property;
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
	 * Attribute value
	 */
	private java.lang.String value;
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
	 * Attribute object
	 */
	private com.soffid.iam.base.model.DefaultObjectMappingEntity object;
	/**
	 * Gets value for attribute object
	 */
	public com.soffid.iam.base.model.DefaultObjectMappingEntity getObject() {
		return this.object;
	}
	/**
	 * Sets value for attribute object
	 */
	public void setObject(com.soffid.iam.base.model.DefaultObjectMappingEntity object) {
		this.object = object;
	}
	/**
	 * Returns <code>true</code> if the argument is an DefaultObjectMappingPropertyEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof DefaultObjectMappingPropertyEntity))
		{
			return false;
		}
		final DefaultObjectMappingPropertyEntity that = (DefaultObjectMappingPropertyEntity)object;
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
