//
// (c) 2014 Soffid
//
//

package com.soffid.iam.base.model;

/**
 *  Entity ServerPluginModuleEntity
 */

public abstract class ServerPluginModuleEntity {

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
	 * Attribute name
	 */
	private java.lang.String name;
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
	 * Attribute contents
	 */
	private java.sql.Blob contents;
	/**
	 * Gets value for attribute contents
	 */
	public java.sql.Blob getContents() {
		return this.contents;
	}
	/**
	 * Sets value for attribute contents
	 */
	public void setContents(java.sql.Blob contents) {
		this.contents = contents;
	}
	/**
	 * Attribute type
	 */
	private com.soffid.iam.base.api.ServerPluginModuleType type;
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
	 * Attribute plugin
	 */
	private com.soffid.iam.base.model.ServerPluginEntity plugin;
	/**
	 * Gets value for attribute plugin
	 */
	public com.soffid.iam.base.model.ServerPluginEntity getPlugin() {
		return this.plugin;
	}
	/**
	 * Sets value for attribute plugin
	 */
	public void setPlugin(com.soffid.iam.base.model.ServerPluginEntity plugin) {
		this.plugin = plugin;
	}
	/**
	 * Attribute agents
	 */
	private java.util.Collection<com.soffid.iam.base.model.AgentDescriptorEntity> agents =  new java.util.HashSet<com.soffid.iam.base.model.AgentDescriptorEntity>();
	/**
	 * Gets value for attribute agents
	 */
	public java.util.Collection<com.soffid.iam.base.model.AgentDescriptorEntity> getAgents() {
		return this.agents;
	}
	/**
	 * Sets value for attribute agents
	 */
	public void setAgents(java.util.Collection<com.soffid.iam.base.model.AgentDescriptorEntity> agents) {
		this.agents = agents;
	}
	/**
	 * Attribute initClass
	 */
	private java.lang.String initClass;
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
	 * Attribute resourceName
	 */
	private java.lang.String resourceName;
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
	 * Returns <code>true</code> if the argument is an ServerPluginModuleEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof ServerPluginModuleEntity))
		{
			return false;
		}
		final ServerPluginModuleEntity that = (ServerPluginModuleEntity)object;
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
