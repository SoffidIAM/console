//
// (c) 2014 Soffid
//
//

package com.soffid.iam.base.model;

/**
 *  Entity DefaultObjectMappingEntity
 */

public abstract class DefaultObjectMappingEntity {

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
	 * Attribute agentDescriptor
	 */
	private com.soffid.iam.base.model.AgentDescriptorEntity agentDescriptor;
	/**
	 * Gets value for attribute agentDescriptor
	 */
	public com.soffid.iam.base.model.AgentDescriptorEntity getAgentDescriptor() {
		return this.agentDescriptor;
	}
	/**
	 * Sets value for attribute agentDescriptor
	 */
	public void setAgentDescriptor(com.soffid.iam.base.model.AgentDescriptorEntity agentDescriptor) {
		this.agentDescriptor = agentDescriptor;
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
	private java.util.Collection<com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity> properties =  new java.util.HashSet<com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity>();
	/**
	 * Gets value for attribute properties
	 */
	public java.util.Collection<com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity> getProperties() {
		return this.properties;
	}
	/**
	 * Sets value for attribute properties
	 */
	public void setProperties(java.util.Collection<com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity> properties) {
		this.properties = properties;
	}
	/**
	 * Attribute defaultAttributeMappings
	 */
	private java.util.Collection<com.soffid.iam.base.model.DefaultAttributeMappingEntity> defaultAttributeMappings =  new java.util.HashSet<com.soffid.iam.base.model.DefaultAttributeMappingEntity>();
	/**
	 * Gets value for attribute defaultAttributeMappings
	 */
	public java.util.Collection<com.soffid.iam.base.model.DefaultAttributeMappingEntity> getDefaultAttributeMappings() {
		return this.defaultAttributeMappings;
	}
	/**
	 * Sets value for attribute defaultAttributeMappings
	 */
	public void setDefaultAttributeMappings(java.util.Collection<com.soffid.iam.base.model.DefaultAttributeMappingEntity> defaultAttributeMappings) {
		this.defaultAttributeMappings = defaultAttributeMappings;
	}
	/**
	 * Returns <code>true</code> if the argument is an DefaultObjectMappingEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof DefaultObjectMappingEntity))
		{
			return false;
		}
		final DefaultObjectMappingEntity that = (DefaultObjectMappingEntity)object;
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
