//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject ObjectMappingTrigger
 **/
public class ObjectMappingTrigger

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
	 * Attribute trigger

	 */
	private com.soffid.iam.iga.api.SoffidObjectTrigger trigger = com.soffid.iam.iga.api.SoffidObjectTrigger.PRE_INSERT;

	/**
	 * Attribute script

	 */
	private java.lang.String script;

	/**
	 * Attribute objectId

	 */
	private java.lang.Long objectId;

	public ObjectMappingTrigger()
	{
	}

	public ObjectMappingTrigger(java.lang.Long id, com.soffid.iam.iga.api.SoffidObjectTrigger trigger, java.lang.String script, java.lang.Long objectId)
	{
		super();
		this.id = id;
		this.trigger = trigger;
		this.script = script;
		this.objectId = objectId;
	}

	public ObjectMappingTrigger(com.soffid.iam.iga.api.SoffidObjectTrigger trigger, java.lang.Long objectId)
	{
		super();
		this.trigger = trigger;
		this.objectId = objectId;
	}

	public ObjectMappingTrigger(ObjectMappingTrigger otherBean)
	{
		this(otherBean.id, otherBean.trigger, otherBean.script, otherBean.objectId);
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
		b.append (", trigger: ");
		b.append (this.trigger);
		b.append (", script: ");
		b.append (this.script);
		b.append (", objectId: ");
		b.append (this.objectId);
		b.append ("]");
		return b.toString();
	}

}
