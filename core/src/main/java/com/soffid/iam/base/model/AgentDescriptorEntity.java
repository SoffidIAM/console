//
// (c) 2014 Soffid
//
//

package com.soffid.iam.base.model;

/**
 *  Entity AgentDescriptorEntity
 */

public abstract class AgentDescriptorEntity {

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
	 * Attribute description
	 */
	private java.lang.String description;
	/**
	 * Gets value for attribute description
	 */
	public java.lang.String getDescription() {
		return this.description;
	}
	/**
	 * Sets value for attribute description
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	/**
	 * Attribute className
	 */
	private java.lang.String className;
	/**
	 * Gets value for attribute className
	 */
	public java.lang.String getClassName() {
		return this.className;
	}
	/**
	 * Sets value for attribute className
	 */
	public void setClassName(java.lang.String className) {
		this.className = className;
	}
	/**
	 * Attribute userInterface
	 */
	private byte[] userInterface;
	/**
	 * Gets value for attribute userInterface
	 */
	public byte[] getUserInterface() {
		return this.userInterface;
	}
	/**
	 * Sets value for attribute userInterface
	 */
	public void setUserInterface(byte[] userInterface) {
		this.userInterface = userInterface;
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
	 * Attribute enableAccessControl
	 */
	private boolean enableAccessControl;
	/**
	 * Gets value for attribute enableAccessControl
	 */
	public boolean isEnableAccessControl() {
		return this.enableAccessControl;
	}
	/**
	 * Sets value for attribute enableAccessControl
	 */
	public void setEnableAccessControl(boolean enableAccessControl) {
		this.enableAccessControl = enableAccessControl;
	}
	/**
	 * Attribute module
	 */
	private com.soffid.iam.base.model.ServerPluginModuleEntity module;
	/**
	 * Gets value for attribute module
	 */
	public com.soffid.iam.base.model.ServerPluginModuleEntity getModule() {
		return this.module;
	}
	/**
	 * Sets value for attribute module
	 */
	public void setModule(com.soffid.iam.base.model.ServerPluginModuleEntity module) {
		this.module = module;
	}
	/**
	 * Attribute authoritativeSource
	 */
	private boolean authoritativeSource;
	/**
	 * Gets value for attribute authoritativeSource
	 */
	public boolean isAuthoritativeSource() {
		return this.authoritativeSource;
	}
	/**
	 * Sets value for attribute authoritativeSource
	 */
	public void setAuthoritativeSource(boolean authoritativeSource) {
		this.authoritativeSource = authoritativeSource;
	}
	/**
	 * Attribute enableAttributeMapping
	 */
	private boolean enableAttributeMapping = false;
	/**
	 * Gets value for attribute enableAttributeMapping
	 */
	public boolean isEnableAttributeMapping() {
		return this.enableAttributeMapping;
	}
	/**
	 * Sets value for attribute enableAttributeMapping
	 */
	public void setEnableAttributeMapping(boolean enableAttributeMapping) {
		this.enableAttributeMapping = enableAttributeMapping;
	}
	/**
	 * Attribute enableObjectTriggers
	 */
	private java.lang.Boolean enableObjectTriggers = false;
	/**
	 * Gets value for attribute enableObjectTriggers
	 */
	public java.lang.Boolean getEnableObjectTriggers() {
		return this.enableObjectTriggers;
	}
	/**
	 * Sets value for attribute enableObjectTriggers
	 */
	public void setEnableObjectTriggers(java.lang.Boolean enableObjectTriggers) {
		this.enableObjectTriggers = enableObjectTriggers;
	}
	/**
	 * Attribute service
	 */
	private java.lang.Boolean service = false;
	/**
	 * Gets value for attribute service
	 */
	public java.lang.Boolean getService() {
		return this.service;
	}
	/**
	 * Sets value for attribute service
	 */
	public void setService(java.lang.Boolean service) {
		this.service = service;
	}
	/**
	 * Attribute defaultObjectMappings
	 */
	private java.util.Collection<com.soffid.iam.base.model.DefaultObjectMappingEntity> defaultObjectMappings =  new java.util.HashSet<com.soffid.iam.base.model.DefaultObjectMappingEntity>();
	/**
	 * Gets value for attribute defaultObjectMappings
	 */
	public java.util.Collection<com.soffid.iam.base.model.DefaultObjectMappingEntity> getDefaultObjectMappings() {
		return this.defaultObjectMappings;
	}
	/**
	 * Sets value for attribute defaultObjectMappings
	 */
	public void setDefaultObjectMappings(java.util.Collection<com.soffid.iam.base.model.DefaultObjectMappingEntity> defaultObjectMappings) {
		this.defaultObjectMappings = defaultObjectMappings;
	}
	/**
	 * Attribute properties

	 */
	private java.util.Collection<com.soffid.iam.base.model.AgentPropertyEntity> properties =  new java.util.HashSet<com.soffid.iam.base.model.AgentPropertyEntity>();
	/**
	 * Gets value for attribute properties
	 */
	public java.util.Collection<com.soffid.iam.base.model.AgentPropertyEntity> getProperties() {
		return this.properties;
	}
	/**
	 * Sets value for attribute properties
	 */
	public void setProperties(java.util.Collection<com.soffid.iam.base.model.AgentPropertyEntity> properties) {
		this.properties = properties;
	}
	/**
	 * Returns <code>true</code> if the argument is an AgentDescriptorEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof AgentDescriptorEntity))
		{
			return false;
		}
		final AgentDescriptorEntity that = (AgentDescriptorEntity)object;
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
