//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject ReconcileTrigger
 **/
public class ReconcileTrigger

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
	 * Attribute objectType

	 */
	private com.soffid.iam.iga.api.SoffidObjectType objectType = com.soffid.iam.iga.api.SoffidObjectType.OBJECT_ACCOUNT;

	/**
	 * Attribute trigger

	 */
	private com.soffid.iam.iga.api.SoffidObjectTrigger trigger = com.soffid.iam.iga.api.SoffidObjectTrigger.PRE_INSERT;

	/**
	 * Attribute script

	 */
	private java.lang.String script;

	/**
	 * Attribute system

	 */
	private java.lang.String system;

	public ReconcileTrigger()
	{
	}

	public ReconcileTrigger(java.lang.Long id, com.soffid.iam.iga.api.SoffidObjectType objectType, com.soffid.iam.iga.api.SoffidObjectTrigger trigger, java.lang.String script, java.lang.String system)
	{
		super();
		this.id = id;
		this.objectType = objectType;
		this.trigger = trigger;
		this.script = script;
		this.system = system;
	}

	public ReconcileTrigger(com.soffid.iam.iga.api.SoffidObjectTrigger trigger, java.lang.String script, java.lang.String system)
	{
		super();
		this.trigger = trigger;
		this.script = script;
		this.system = system;
	}

	public ReconcileTrigger(ReconcileTrigger otherBean)
	{
		this(otherBean.id, otherBean.objectType, otherBean.trigger, otherBean.script, otherBean.system);
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
	 * Gets value for attribute system
	 */
	public java.lang.String getSystem() {
		return this.system;
	}

	/**
	 * Sets value for attribute system
	 */
	public void setSystem(java.lang.String system) {
		this.system = system;
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
		b.append (", objectType: ");
		b.append (this.objectType);
		b.append (", trigger: ");
		b.append (this.trigger);
		b.append (", script: ");
		b.append (this.script);
		b.append (", system: ");
		b.append (this.system);
		b.append ("]");
		return b.toString();
	}

}
