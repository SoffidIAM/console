//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity ReconcileTriggerEntity
 */

public abstract class ReconcileTriggerEntity {

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
	 * Attribute objectType
	 */
	private com.soffid.iam.iga.api.SoffidObjectType objectType;
	/**
	 * Gets value for attribute objectType
	 */
	public com.soffid.iam.iga.api.SoffidObjectType getObjectType() {
		return this.objectType;
	}
	/**
	 * Sets value for attribute objectType
	 */
	public void setObjectType(com.soffid.iam.iga.api.SoffidObjectType objectType) {
		this.objectType = objectType;
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
	 * Returns <code>true</code> if the argument is an ReconcileTriggerEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof ReconcileTriggerEntity))
		{
			return false;
		}
		final ReconcileTriggerEntity that = (ReconcileTriggerEntity)object;
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
