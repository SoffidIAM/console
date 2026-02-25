//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity ObjectMappingTriggerEntity
 */

public abstract class ObjectMappingTriggerEntity {

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
	 * Attribute trigger
	 */
	private com.soffid.iam.iga.api.SoffidObjectTrigger trigger;
	/**
	 * Gets value for attribute trigger
	 */
	public com.soffid.iam.iga.api.SoffidObjectTrigger getTrigger() {
		return this.trigger;
	}
	/**
	 * Sets value for attribute trigger
	 */
	public void setTrigger(com.soffid.iam.iga.api.SoffidObjectTrigger trigger) {
		this.trigger = trigger;
	}
	/**
	 * Attribute script
	 */
	private java.lang.String script;
	/**
	 * Gets value for attribute script
	 */
	public java.lang.String getScript() {
		return this.script;
	}
	/**
	 * Sets value for attribute script
	 */
	public void setScript(java.lang.String script) {
		this.script = script;
	}
	/**
	 * Attribute object
	 */
	private com.soffid.iam.iga.model.ObjectMappingEntity object;
	/**
	 * Gets value for attribute object
	 */
	public com.soffid.iam.iga.model.ObjectMappingEntity getObject() {
		return this.object;
	}
	/**
	 * Sets value for attribute object
	 */
	public void setObject(com.soffid.iam.iga.model.ObjectMappingEntity object) {
		this.object = object;
	}
	/**
	 * Returns <code>true</code> if the argument is an ObjectMappingTriggerEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof ObjectMappingTriggerEntity))
		{
			return false;
		}
		final ObjectMappingTriggerEntity that = (ObjectMappingTriggerEntity)object;
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
