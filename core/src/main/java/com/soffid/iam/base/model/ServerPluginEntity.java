//
// (c) 2014 Soffid
//
//

package com.soffid.iam.base.model;

/**
 *  Entity ServerPluginEntity
 */

public abstract class ServerPluginEntity {

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
	 * Attribute version
	 */
	private java.lang.String version;
	/**
	 * Gets value for attribute version
	 */
	public java.lang.String getVersion() {
		return this.version;
	}
	/**
	 * Sets value for attribute version
	 */
	public void setVersion(java.lang.String version) {
		this.version = version;
	}
	/**
	 * Attribute content
	 */
	private java.sql.Blob content;
	/**
	 * Gets value for attribute content
	 */
	public java.sql.Blob getContent() {
		return this.content;
	}
	/**
	 * Sets value for attribute content
	 */
	public void setContent(java.sql.Blob content) {
		this.content = content;
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
	 * Attribute author
	 */
	private java.lang.String author;
	/**
	 * Gets value for attribute author
	 */
	public java.lang.String getAuthor() {
		return this.author;
	}
	/**
	 * Sets value for attribute author
	 */
	public void setAuthor(java.lang.String author) {
		this.author = author;
	}
	/**
	 * Attribute deployed
	 */
	private java.util.Date deployed;
	/**
	 * Gets value for attribute deployed
	 */
	public java.util.Date getDeployed() {
		return this.deployed;
	}
	/**
	 * Sets value for attribute deployed
	 */
	public void setDeployed(java.util.Date deployed) {
		this.deployed = deployed;
	}
	/**
	 * Attribute enabled
	 */
	private boolean enabled;
	/**
	 * Gets value for attribute enabled
	 */
	public boolean isEnabled() {
		return this.enabled;
	}
	/**
	 * Sets value for attribute enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	/**
	 * Attribute tenant
	 */
	private com.soffid.iam.base.model.TenantEntity tenant;
	/**
	 * Gets value for attribute tenant
	 */
	public com.soffid.iam.base.model.TenantEntity getTenant() {
		return this.tenant;
	}
	/**
	 * Sets value for attribute tenant
	 */
	public void setTenant(com.soffid.iam.base.model.TenantEntity tenant) {
		this.tenant = tenant;
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
	 * Attribute modules
	 */
	private java.util.Collection<com.soffid.iam.base.model.ServerPluginModuleEntity> modules =  new java.util.HashSet<com.soffid.iam.base.model.ServerPluginModuleEntity>();
	/**
	 * Gets value for attribute modules
	 */
	public java.util.Collection<com.soffid.iam.base.model.ServerPluginModuleEntity> getModules() {
		return this.modules;
	}
	/**
	 * Sets value for attribute modules
	 */
	public void setModules(java.util.Collection<com.soffid.iam.base.model.ServerPluginModuleEntity> modules) {
		this.modules = modules;
	}
	/**
	 * Returns <code>true</code> if the argument is an ServerPluginEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof ServerPluginEntity))
		{
			return false;
		}
		final ServerPluginEntity that = (ServerPluginEntity)object;
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
