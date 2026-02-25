//
// (c) 2014 Soffid
//
//

package com.soffid.iam.base.model;

/**
 *  Entity DefaultAttributeMappingEntity
 */

public abstract class DefaultAttributeMappingEntity {

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
	 * Attribute soffidAttribute
	 */
	private java.lang.String soffidAttribute;
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
	 * Attribute systemAttribute
	 */
	private java.lang.String systemAttribute;
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
	 * Attribute direction
	 */
	private com.soffid.iam.iga.api.AttributeDirection direction;
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
	 * Attribute defaultObjectMapping
	 */
	private com.soffid.iam.base.model.DefaultObjectMappingEntity defaultObjectMapping;
	/**
	 * Gets value for attribute defaultObjectMapping
	 */
	public com.soffid.iam.base.model.DefaultObjectMappingEntity getDefaultObjectMapping() {
		return this.defaultObjectMapping;
	}
	/**
	 * Sets value for attribute defaultObjectMapping
	 */
	public void setDefaultObjectMapping(com.soffid.iam.base.model.DefaultObjectMappingEntity defaultObjectMapping) {
		this.defaultObjectMapping = defaultObjectMapping;
	}
	/**
	 * Returns <code>true</code> if the argument is an DefaultAttributeMappingEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof DefaultAttributeMappingEntity))
		{
			return false;
		}
		final DefaultAttributeMappingEntity that = (DefaultAttributeMappingEntity)object;
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
