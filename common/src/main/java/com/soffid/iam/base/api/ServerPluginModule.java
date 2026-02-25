//
// (C) 2020 Soffid
//
//

package com.soffid.iam.base.api;
/**
 * ValueObject ServerPluginModule
 **/
public class ServerPluginModule

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute name

	 */
	private java.lang.String name;

	/**
	 * Attribute type

	 */
	private com.soffid.iam.base.api.ServerPluginModuleType type;

	/**
	 * Attribute initClass

	 */
	private java.lang.String initClass;

	/**
	 * Attribute agents

	 */
	private java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> agents;

	/**
	 * Attribute resourceName

	 */
	private java.lang.String resourceName;

	public ServerPluginModule()
	{
	}

	public ServerPluginModule(java.lang.String name, com.soffid.iam.base.api.ServerPluginModuleType type, java.lang.String initClass, java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> agents, java.lang.String resourceName)
	{
		super();
		this.name = name;
		this.type = type;
		this.initClass = initClass;
		this.agents = agents;
		this.resourceName = resourceName;
	}

	public ServerPluginModule(java.lang.String name, com.soffid.iam.base.api.ServerPluginModuleType type)
	{
		super();
		this.name = name;
		this.type = type;
	}

	public ServerPluginModule(ServerPluginModule otherBean)
	{
		this(otherBean.name, otherBean.type, otherBean.initClass, otherBean.agents, otherBean.resourceName);
	}

	/**
	 * Gets value for attribute name
	 */
	public java.lang.String getName() {
		return this.name;
	}

	/**
	 * Sets value for attribute name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * Gets value for attribute type
	 */
	public com.soffid.iam.base.api.ServerPluginModuleType getType() {
		return this.type;
	}

	/**
	 * Sets value for attribute type
	 */
	public void setType(com.soffid.iam.base.api.ServerPluginModuleType type) {
		this.type = type;
	}

	/**
	 * Gets value for attribute initClass
	 */
	public java.lang.String getInitClass() {
		return this.initClass;
	}

	/**
	 * Sets value for attribute initClass
	 */
	public void setInitClass(java.lang.String initClass) {
		this.initClass = initClass;
	}

	/**
	 * Gets value for attribute agents
	 */
	public java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> getAgents() {
		return this.agents;
	}

	/**
	 * Sets value for attribute agents
	 */
	public void setAgents(java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> agents) {
		this.agents = agents;
	}

	/**
	 * Gets value for attribute resourceName
	 */
	public java.lang.String getResourceName() {
		return this.resourceName;
	}

	/**
	 * Sets value for attribute resourceName
	 */
	public void setResourceName(java.lang.String resourceName) {
		this.resourceName = resourceName;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[name: ");
		b.append (this.name);
		b.append (", type: ");
		b.append (this.type);
		b.append (", initClass: ");
		b.append (this.initClass);
		b.append (", agents: ");
		b.append (this.agents);
		b.append (", resourceName: ");
		b.append (this.resourceName);
		b.append ("]");
		return b.toString();
	}

}
