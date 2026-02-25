//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity ObjectMappingEntity
 */

public abstract class ObjectMappingEntity {

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
	 * Attribute systemObject
	 */
	private java.lang.String systemObject;
	/**
	 * Gets value for attribute systemObject
	 */
	public java.lang.String getSystemObject() {
		return this.systemObject;
	}
	/**
	 * Sets value for attribute systemObject
	 */
	public void setSystemObject(java.lang.String systemObject) {
		this.systemObject = systemObject;
	}
	/**
	 * Attribute soffidObject
	 */
	private com.soffid.iam.iga.api.SoffidObjectType soffidObject;
	/**
	 * Gets value for attribute soffidObject
	 */
	public com.soffid.iam.iga.api.SoffidObjectType getSoffidObject() {
		return this.soffidObject;
	}
	/**
	 * Sets value for attribute soffidObject
	 */
	public void setSoffidObject(com.soffid.iam.iga.api.SoffidObjectType soffidObject) {
		this.soffidObject = soffidObject;
	}
	/**
	 * Attribute soffidCustomObject
	 */
	private com.soffid.iam.iga.model.CustomObjectTypeEntity soffidCustomObject;
	/**
	 * Gets value for attribute soffidCustomObject
	 */
	public com.soffid.iam.iga.model.CustomObjectTypeEntity getSoffidCustomObject() {
		return this.soffidCustomObject;
	}
	/**
	 * Sets value for attribute soffidCustomObject
	 */
	public void setSoffidCustomObject(com.soffid.iam.iga.model.CustomObjectTypeEntity soffidCustomObject) {
		this.soffidCustomObject = soffidCustomObject;
	}
	/**
	 * Attribute soffidExtensibleObject
	 */
	private java.lang.String soffidExtensibleObject;
	/**
	 * Gets value for attribute soffidExtensibleObject
	 */
	public java.lang.String getSoffidExtensibleObject() {
		return this.soffidExtensibleObject;
	}
	/**
	 * Sets value for attribute soffidExtensibleObject
	 */
	public void setSoffidExtensibleObject(java.lang.String soffidExtensibleObject) {
		this.soffidExtensibleObject = soffidExtensibleObject;
	}
	/**
	 * Attribute condition
	 */
	private java.lang.String condition;
	/**
	 * Gets value for attribute condition
	 */
	public java.lang.String getCondition() {
		return this.condition;
	}
	/**
	 * Sets value for attribute condition
	 */
	public void setCondition(java.lang.String condition) {
		this.condition = condition;
	}
	/**
	 * Attribute properties
	 */
	private java.util.Collection<com.soffid.iam.iga.model.ObjectMappingPropertyEntity> properties =  new java.util.HashSet<com.soffid.iam.iga.model.ObjectMappingPropertyEntity>();
	/**
	 * Gets value for attribute properties
	 */
	public java.util.Collection<com.soffid.iam.iga.model.ObjectMappingPropertyEntity> getProperties() {
		return this.properties;
	}
	/**
	 * Sets value for attribute properties
	 */
	public void setProperties(java.util.Collection<com.soffid.iam.iga.model.ObjectMappingPropertyEntity> properties) {
		this.properties = properties;
	}
	/**
	 * Attribute system
	 */
	private com.soffid.iam.iga.model.SystemEntity system;
	/**
	 * Gets value for attribute system
	 */
	public com.soffid.iam.iga.model.SystemEntity getSystem() {
		return this.system;
	}
	/**
	 * Sets value for attribute system
	 */
	public void setSystem(com.soffid.iam.iga.model.SystemEntity system) {
		this.system = system;
	}
	/**
	 * Attribute attributeMappings
	 */
	private java.util.Collection<com.soffid.iam.iga.model.AttributeMappingEntity> attributeMappings =  new java.util.HashSet<com.soffid.iam.iga.model.AttributeMappingEntity>();
	/**
	 * Gets value for attribute attributeMappings
	 */
	public java.util.Collection<com.soffid.iam.iga.model.AttributeMappingEntity> getAttributeMappings() {
		return this.attributeMappings;
	}
	/**
	 * Sets value for attribute attributeMappings
	 */
	public void setAttributeMappings(java.util.Collection<com.soffid.iam.iga.model.AttributeMappingEntity> attributeMappings) {
		this.attributeMappings = attributeMappings;
	}
	/**
	 * Attribute triggers

	 */
	private java.util.Collection<com.soffid.iam.iga.model.ObjectMappingTriggerEntity> triggers =  new java.util.HashSet<com.soffid.iam.iga.model.ObjectMappingTriggerEntity>();
	/**
	 * Gets value for attribute triggers
	 */
	public java.util.Collection<com.soffid.iam.iga.model.ObjectMappingTriggerEntity> getTriggers() {
		return this.triggers;
	}
	/**
	 * Sets value for attribute triggers
	 */
	public void setTriggers(java.util.Collection<com.soffid.iam.iga.model.ObjectMappingTriggerEntity> triggers) {
		this.triggers = triggers;
	}
	/**
	 * Returns <code>true</code> if the argument is an ObjectMappingEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof ObjectMappingEntity))
		{
			return false;
		}
		final ObjectMappingEntity that = (ObjectMappingEntity)object;
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
