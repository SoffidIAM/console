//
// (c) 2014 Soffid
//
//

package com.soffid.iam.base.model;

/**
 *  Entity AgentPropertyEntity
 */

public abstract class AgentPropertyEntity {

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
	 * Attribute template
	 */
	private java.lang.String template;
	/**
	 * Gets value for attribute template
	 */
	public java.lang.String getTemplate() {
		return this.template;
	}
	/**
	 * Sets value for attribute template
	 */
	public void setTemplate(java.lang.String template) {
		this.template = template;
	}
	/**
	 * Attribute type
	 */
	private java.lang.String type;
	/**
	 * Gets value for attribute type
	 */
	public java.lang.String getType() {
		return this.type;
	}
	/**
	 * Sets value for attribute type
	 */
	public void setType(java.lang.String type) {
		this.type = type;
	}
	/**
	 * Attribute agent
	 */
	private com.soffid.iam.base.model.AgentDescriptorEntity agent;
	/**
	 * Gets value for attribute agent
	 */
	public com.soffid.iam.base.model.AgentDescriptorEntity getAgent() {
		return this.agent;
	}
	/**
	 * Sets value for attribute agent
	 */
	public void setAgent(com.soffid.iam.base.model.AgentDescriptorEntity agent) {
		this.agent = agent;
	}
	/**
	 * Returns <code>true</code> if the argument is an AgentPropertyEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof AgentPropertyEntity))
		{
			return false;
		}
		final AgentPropertyEntity that = (AgentPropertyEntity)object;
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
